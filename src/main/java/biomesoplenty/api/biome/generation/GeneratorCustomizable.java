/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

// TODO - scrap this?
public abstract class GeneratorCustomizable implements IGenerator
{
    private final String identifier;
    private String name;
    private GeneratorStage stage;
    
    protected GeneratorCustomizable()
    {
        this.identifier = GeneratorRegistry.getIdentifier((Class<? extends IGenerator>)this.getClass());
        
        if (this.identifier == null)
        {
            throw new RuntimeException("The identifier for " + this.getClass().getCanonicalName() + " cannot be null!");
        }
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setStage(GeneratorStage stage)
    {
        this.stage = stage;
    }

    @Override
    public String getName()
    {
        return this.name;
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
    
    @Override
    public void configure(IConfigObj conf)
    {
        ;
    }
}
