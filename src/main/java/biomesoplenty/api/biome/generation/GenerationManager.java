/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;
import biomesoplenty.common.world.feature.*;
import biomesoplenty.common.world.feature.tree.*;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

public class GenerationManager
{
    private Map<String, IGenerator> generators = new HashMap<String, IGenerator>();
    
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator)
    {
        if (this.generators.containsKey(name))
        {
            throw new RuntimeException("A generator with name " + name + " already exists!");
        }
        generator.setName(name);
        generator.setStage(stage);
        this.generators.put(name, generator);
    }
    
    public ImmutableCollection<IGenerator> getGeneratorsForStage(GeneratorStage stage)
    {
        ArrayList<IGenerator> out = new ArrayList<IGenerator>();
        for (IGenerator generator : this.generators.values())
        {
            if (generator.getStage() == stage)
            {
                out.add(generator);
            }
        }        
        return ImmutableList.<IGenerator>copyOf(out);
    }
    
    public void removeGenerator(String name)
    {
        this.generators.remove(name);
    }
    
    public IGenerator getGenerator(String name)
    {
        return this.generators.get(name);
    }

    public void configureWith(String name, WrappedJsonObject conf)
    {
        if (this.generators.containsKey(name))
        {
            if (conf.getBool("enable", true))
            {
                // configure the existing generator
                this.generators.get(name).configure(conf);
            }
            else
            {
                // remove this generator
                this.generators.remove(name);
            }
        }
        else
        {
            // there was previously no generator of this name - attempt to add it
            IGenerator generator = GeneratorFactory.create(conf);
            if (generator != null)
            {
                this.generators.put(name, generator);
            }
        }
    }
    
    // TODO: use GeneratorRegistry instead?
    public enum GeneratorFactory
    {
        FLORA, DOUBLE_FLORA, GRASS, ORE_CLUSTER, ORE_SINGLE, WATERSIDE, BASIC_TREE, BIG_TREE, BUSH;
        
        public static IGenerator create(WrappedJsonObject conf)
        {
            GeneratorStage stage = conf.getEnum("stage", null, GeneratorStage.class);
            GeneratorFactory factory = conf.getEnum("type", null, GeneratorFactory.class);
            if (stage == null || factory == null) {return null;}
            IGenerator generator = factory.create();
            generator.setStage(stage);
            generator.configure(conf);
            return generator;
        }
        
        public IGenerator create()
        {
            switch (this)
            {
                case FLORA:
                    return new GeneratorFlora();
                case DOUBLE_FLORA:
                    return new GeneratorDoubleFlora();
                case GRASS:
                    return new GeneratorGrass();
                case ORE_CLUSTER:
                    return new GeneratorOreCluster();
                case ORE_SINGLE:
                    return new GeneratorOreSingle();
                case WATERSIDE:
                    return new GeneratorWaterside();
                case BASIC_TREE:
                    return new GeneratorBasicTree();
                case BIG_TREE:
                    return new GeneratorBigTree();
                case BUSH:
                    return new GeneratorBush();                    
                default:
                    return null;
            }
        }
        
    }

}