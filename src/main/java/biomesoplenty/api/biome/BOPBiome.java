/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.util.config.ConfigHelper;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

public class BOPBiome extends BiomeGenBase implements IExtendedBiome
{
    private GenerationManager generationManager = new GenerationManager();
    private Map<BiomeType, Integer> weightMap = new HashMap<BiomeType, Integer>();
    
    public BOPBiome()
    {
        super(-1, false);

        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.grassPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk = -999;
        this.theBiomeDecorator.sandPerChunk2 = -999;
        this.theBiomeDecorator.clayPerChunk = -999;
    }
    
    public void configure(ConfigHelper conf)
    {
        
        // Allow name to be overridden
        this.biomeName = conf.getString("biomeName",this.biomeName);
        
        // Allow basic properties to be overridden
        this.topBlock = conf.getBlockState("topBlock", this.topBlock);
        this.fillerBlock = conf.getBlockState("fillerBlock", this.fillerBlock);
        this.minHeight = conf.getFloat("rootHeight", this.minHeight);
        this.maxHeight = conf.getFloat("variation", this.maxHeight);
        this.temperature = conf.getFloat("temperature", this.temperature);
        this.rainfall = conf.getFloat("rainfall", this.rainfall);
        this.waterColorMultiplier = conf.getInt("waterColorMultiplier", this.waterColorMultiplier);
        
        // Allow weights to be overridden
        WrappedJsonObject confWeights = conf.getObject("weights");
        if (confWeights != null)
        {
            for (BiomeType type : BiomeType.values())
            {
                Integer weight = confWeights.getInt(type.name().toLowerCase(), null);
                if (weight == null) {continue;}
                if (weight.intValue() == 0)
                {
                    this.weightMap.remove(type);
                }
                else
                {
                    this.weightMap.put(type, weight);
                }
            }
        }
        
    }
    

    @Override
    public BiomeOwner getBiomeOwner()
    {
        return BiomeOwner.BIOMESOPLENTY;
    }

    @Override
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator)
    {
        this.generationManager.addGenerator(name, stage, generator);
    }
    
    @Override
    public GenerationManager getGenerationManager()
    {
        return this.generationManager;
    }

    @Override
    public Map<BiomeType, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    @Override
    public void addWeight(BiomeType type, int weight)
    {
        this.weightMap.put(type, weight);
    }
    
    @Override
    public void clearWeights()
    {
        this.weightMap.clear();
    }
    
    // whether or not a biome essence item corresponding to this biome should be able to drop from biome blocks
    public boolean hasBiomeEssence()
    {
        return true;
    }
}
