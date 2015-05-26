/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

public final class GeneratorWeightedEntry extends WeightedRandom.Item implements IGenerator
{
    private final String identifier;
    private GeneratorStage stage;
    private IGenerator wrappedGenerator;
    
    public GeneratorWeightedEntry() 
    {
        this(-1, null);
    }
    
    public GeneratorWeightedEntry(int weight, IGenerator wrappedGenerator)
    {
        super(weight);
        
        this.identifier = GeneratorRegistry.getIdentifier((Class<? extends IGenerator>)this.getClass());
        this.stage = GeneratorStage.PARENT;
        this.wrappedGenerator = wrappedGenerator;
        
        if (this.identifier == null)
        {
            throw new RuntimeException("The identifier for " + this.getClass().getCanonicalName() + " cannot be null!");
        }
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        this.wrappedGenerator.scatter(world, random, pos);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        return this.wrappedGenerator.generate(world, random, pos);
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        // this should never be used directly
        ;
    }
    

    @Override
    public void setStage(GeneratorStage stage)
    {
        this.stage = stage;
    }

    @Override
    public GeneratorStage getStage()
    {
        return this.stage;
    }
    
    @Override
    public final String getIdentifier()
    {
        return this.identifier;
    }

    //Unnecessary, this shouldn't be used as a standard generator
    @Override
    public void setName(String name) {}

    @Override
    public String getName()
    {
        return null;
    }

}
