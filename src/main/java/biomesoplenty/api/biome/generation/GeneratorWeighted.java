/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorWeighted extends BOPGeneratorBase
{
    private HashMap<String, IGenerator> generators = new HashMap<String, IGenerator>();
    private HashMap<IGenerator, Integer> weights = new HashMap<IGenerator, Integer>();
        
    public GeneratorWeighted()
    {
        // default
        this(1);
    }

    public GeneratorWeighted(float amountPerChunk)
    {
        super(amountPerChunk);
    }
    
    public void add(String name, int weight, IGenerator entry)
    {
        if (this.generators.containsKey(name))
        {
            throw new RuntimeException("A generator with name " + name + " already exists!");
        }
        if (weight < 1)
        {
            throw new IllegalArgumentException("Generator weight must be positive");
        }
        entry.setStage(GeneratorStage.PARENT);
        this.generators.put(name, entry);
        this.weights.put(entry, weight);
    }
    
    public void clear()
    {
        this.generators.clear();
        this.weights.clear();
    }
    
    public IGenerator getGenerator(String name)
    {
        return this.generators.get(name);
    }
    
    public void removeGenerator(String name)
    {
        IGenerator generator = this.generators.get(name);
        if (generator != null)
        {
            this.generators.remove(name);
            this.weights.remove(generator);
        }
    }
    
    public IGenerator getRandomGenerator(Random random)
    {
        if (this.weights.isEmpty()) {return null;}
        int totalWeight = 0;
        for (int weight : this.weights.values()) {totalWeight += weight;}
        int j = random.nextInt(totalWeight);
        for (Entry<IGenerator, Integer> entry : this.weights.entrySet())
        {
            j -= entry.getValue();
            if (j < 0) {return entry.getKey();}
        }
        return null;
    }
    
    // never used - the scatter method is overriden
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z) {return null;}
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        int amount = this.getAmountToScatter(random); 
        for (int i = 0; i < amount; i++)
        {
            this.getRandomGenerator(random).scatter(world, random, pos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        return this.getRandomGenerator(random).generate(world, random, pos);
    }
    

    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        IConfigObj confGenerators = conf.getObject("generators");
        if (confGenerators != null)
        {
            for (String name : confGenerators.getKeys())
            {
                if (this.generators.containsKey(name))
                {
                    IGenerator generator = this.getGenerator(name);
                    Integer weight = conf.getInt("weight", this.weights.get(generator));
                    if (weight.intValue() < 1)
                    {
                        // remove this generator if the weight is zero (or negative)
                        this.removeGenerator(name);                       
                    }
                    else
                    {
                        // adjust weight
                        this.weights.put(generator, weight);
                        // configure the existing generator
                        generator.configure(conf);
                    }
                }
                else
                {
                    // there was previously no generator of this name - attempt to add it
                    Integer weight = conf.getInt("weight", null);
                    IGenerator generator = GeneratorRegistry.createGenerator(conf);
                    if (weight != null && generator != null)
                    {
                        this.add(name, weight, generator);
                    }
                }
            }
        }
    }
}
