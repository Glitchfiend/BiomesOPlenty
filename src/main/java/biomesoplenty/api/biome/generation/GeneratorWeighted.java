/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

// TODO implement so that we don't rely on minecraft WeightedRandom class and GeneratorWeightedEntry class - can be much simpler
public class GeneratorWeighted extends GeneratorCustomizable
{
    private int amountPerChunk;
    private List<GeneratorWeightedEntry> weightedEntries = new ArrayList<GeneratorWeightedEntry>();
    
    public GeneratorWeighted() {}

    public GeneratorWeighted(int amountPerChunk)
    {
        this.amountPerChunk = amountPerChunk;
    }
    
    public void add(int weight, IGenerator entry)
    {
        entry.setStage(GeneratorStage.PARENT);
        this.weightedEntries.add(new GeneratorWeightedEntry(weight, entry));
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            GeneratorWeightedEntry generator = (GeneratorWeightedEntry)WeightedRandom.getRandomItem(random, this.weightedEntries);

            generator.scatter(world, random, pos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        GeneratorWeightedEntry generator = (GeneratorWeightedEntry)WeightedRandom.getRandomItem(random, this.weightedEntries);
        
        return generator.generate(world, random, pos);
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        ArrayList<WrappedJsonObject> weightedEntriesConf = conf.getObjectArray("weightedEntries");
        if (!weightedEntriesConf.isEmpty())
        {
            this.weightedEntries.clear();
            for (WrappedJsonObject weightedEntryConf : weightedEntriesConf)
            {
                Integer weight = weightedEntryConf.getInt("weight", null);
                if (weight == null || weight.intValue() < 1) {continue;}
                WrappedJsonObject generatorConf = weightedEntryConf.getObject("generator");
                if (generatorConf == null) {continue;}
                IGenerator generator = GenerationManager.GeneratorFactory.create(generatorConf);
                if (generator == null) {continue;}
                this.add(weight, generator);
            }
        }
        // TODO: at the moment, because the weighted entries aren't named, there's no way to just adjust one part of one of them
        // The only thing you can do is replace the whole array.  Perhaps should use named entries in a Map<String,Generator> kind of arrangement
        // Then they could be individually altered
    }
}
