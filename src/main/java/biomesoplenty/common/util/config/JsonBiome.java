/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.api.biome.generation.IGenerator;
import biomesoplenty.common.biome.BOPBiomeManager;
import biomesoplenty.common.biome.ExtendedBiomeRegistry;

import com.google.common.base.Optional;
import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonBiome
{
    public static final Gson serializer = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).registerTypeHierarchyAdapter(IBlockState.class, new JsonBlockState()).registerTypeHierarchyAdapter(IGenerator.class, new GeneratorTypeAdaptor()).create();

    public String biomeName;
    public int biomeId;
    public Map<BiomeType, Integer> weights;
    public IBlockState topBlock;
    public IBlockState fillerBlock;
    public float rootHeight;
    public float rootVariation;
    public float temperature;
    public float rainfall;
    public int color;
    public int waterColorMultiplier;
    public ArrayList<JsonEntitySpawn> entities;
    public Map<String, IGenerator> decoration;

    public static JsonBiome createFromBiomeGenBase(BiomeGenBase baseBiome)
    {
        JsonBiome biome = new JsonBiome();
        
        biome.biomeId = baseBiome.biomeID;
        biome.biomeName = baseBiome.biomeName;
        biome.topBlock = baseBiome.topBlock;
        biome.fillerBlock = baseBiome.fillerBlock;
        biome.rootHeight = baseBiome.minHeight;
        biome.rootVariation = baseBiome.maxHeight;
        biome.temperature = baseBiome.temperature;
        biome.rainfall = baseBiome.rainfall;
        biome.color = baseBiome.color;
        biome.waterColorMultiplier = baseBiome.waterColorMultiplier;
        biome.entities = JsonEntitySpawn.getBiomeEntitySpawns(baseBiome);

        IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(baseBiome);
        
        if (extendedBiome != null)
        {
        	GenerationManager generationManager = extendedBiome.getGenerationManager();

        	biome.weights = extendedBiome.getWeightMap();
        	biome.decoration = generationManager.createGeneratorMap();
        	
            if (extendedBiome.getBiomeOwner() == BiomeOwner.BIOMESOPLENTY)
            {
                //Add the biome to the array if it is ours because the registration in configureBiomeWithJson is
                //not called on the first run
                BiomeGenBase.getBiomeGenArray()[baseBiome.biomeID] = baseBiome;
            }
        	
            //TODO: Add a system for making Vanilla biome weights configurable. This won't necessarily be in this class, however it's worth noting.
            for (Entry<BiomeType, Integer> entry : extendedBiome.getWeightMap().entrySet())
            {
                if (entry != null)
                {
                    BiomeType biomeType = entry.getKey();
                    int weight = entry.getValue();
                    
                    BOPBiomeManager.addBiome(biomeType, new BiomeEntry(baseBiome, weight));
                }
            }
        }

        return biome;
    }

    public static Optional<BiomeGenBase> configureBiomeWithJson(BiomeGenBase biome, JsonBiome jsonBiome)
    {
        IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(biome);
    
        if (extendedBiome != null)
        {
            if (extendedBiome.getBiomeOwner() == BiomeOwner.BIOMESOPLENTY)
            {
                if (jsonBiome.biomeId != -1)
                {
                    biome.biomeID = jsonBiome.biomeId;
                    BiomeGenBase.getBiomeGenArray()[jsonBiome.biomeId] = biome;
                }
                else
                {
                    return Optional.absent();
                }
            }
    
            Map<BiomeType, Integer> weightMap = jsonBiome.weights;
            
            //Removes the default weights set by us as they are about to be set from the config file
            extendedBiome.clearWeights();
            
            //TODO: Add a system for making Vanilla biome weights configurable. This won't necessarily be in this class, however it's worth noting.
            if (biome.biomeID != -1)
            {
                for (Entry<BiomeType, Integer> entry : weightMap.entrySet())
                {
                    if (entry != null)
                    {
                        BiomeType biomeType = entry.getKey();
                        int weight = entry.getValue();

                        //Updates the biome's weights to be in line with the config file
                        extendedBiome.addWeight(biomeType, weight);
                        BOPBiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
                    }
                }
            }

            biome.biomeName = jsonBiome.biomeName;
            biome.topBlock = jsonBiome.topBlock;
            biome.fillerBlock = jsonBiome.fillerBlock;
            biome.setHeight(new BiomeGenBase.Height(jsonBiome.rootHeight, jsonBiome.rootVariation));
            biome.temperature = jsonBiome.temperature;
            biome.rainfall = jsonBiome.rainfall;
            // TODO: Reflect and modify enableRain and enableSnow
            biome.color = jsonBiome.color;
            biome.waterColorMultiplier = jsonBiome.waterColorMultiplier;
            JsonEntitySpawn.addBiomeEntitySpawns(biome, jsonBiome);
    
            GenerationManager generationManager = extendedBiome.getGenerationManager();
            
            generationManager.createGeneratorTable(jsonBiome.decoration);
            
            return Optional.of(biome);
        }
        
        return Optional.absent();
    }
}
