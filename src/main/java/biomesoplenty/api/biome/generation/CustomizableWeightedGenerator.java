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

public abstract class CustomizableWeightedGenerator extends WeightedRandom.Item implements IGeneratorDelegate
{
    private final String identifier;
    private GeneratorStage stage;
    
    protected CustomizableWeightedGenerator() 
    {
        this(-1);
    }
    
    protected CustomizableWeightedGenerator(int weight)
    {
        super(weight);
        
        this.identifier = GeneratorRegistry.getIdentifier((Class<? extends IGeneratorBase>)this.getClass());
        this.stage = GeneratorStage.PARENT;
        
        if (this.identifier == null)
        {
            throw new RuntimeException("The identifier for " + this.getClass().getCanonicalName() + " cannot be null!");
        }
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
}
