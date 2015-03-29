/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;

import org.apache.commons.io.FileUtils;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.common.biome.BOPBiomeManager;
import biomesoplenty.common.biome.ExtendedBiomeRegistry;
import biomesoplenty.common.biome.ExtendedBiomeRegistry.GenerationManager;
import biomesoplenty.common.util.config.JsonBiome;
import biomesoplenty.common.util.config.JsonEntitySpawn;

import com.google.gson.JsonSyntaxException;

public class BiomeConfigurationHandler
{
    private static Map<BiomeGenBase, String> configFileMap = new HashMap();

    public static void init(File configDirectory)
    {
        load(configDirectory);
    }

    private static void load(File configDirectory)
    {
        for (Entry<BiomeGenBase, String> entry : configFileMap.entrySet())
        {
            BiomeGenBase biome = entry.getKey();
            String configFileName = entry.getValue();
            File configFile = new File(configDirectory, configFileName + ".json");

            if (configFile.exists())
            {
                try
                {
                    JsonBiome jsonBiome = JsonBiome.serializer.fromJson(FileUtils.readFileToString(configFile), JsonBiome.class);

                    configureBiomeWithJson(biome, jsonBiome);
                }
                catch (JsonSyntaxException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try
                {
                    FileUtils.write(configFile, JsonBiome.serializer.toJson(JsonBiome.createFromBiomeGenBase(biome), JsonBiome.class));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void translateVanillaValues(BiomeGenBase biome)
    {
        /*IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(biome);
        GenerationManager generationManager = extendedBiome.getGenerationManager();

        if (extendedBiome.getBiomeOwner() == BiomeOwner.OTHER)
        {
            if (biome.theBiomeDecorator.cactiPerChunk > 0)
            {
                WorldGenCactus cactusGen = new WorldGenCactus();
                IExtendedCactusGen extendedCactusGen = (IExtendedCactusGen) cactusGen;

                extendedCactusGen.setCactiPerChunk(biome.theBiomeDecorator.cactiPerChunk);
                generationManager.addGenerator("cactus", extendedCactusGen, Decorate.EventType.CACTUS);
                biome.theBiomeDecorator.cactiPerChunk = 0;
            }
        }*/
    }

    private static void configureBiomeWithJson(BiomeGenBase biome, JsonBiome jsonBiome)
    {
        IExtendedBiome extendedBiome = ExtendedBiomeRegistry.getExtension(biome);

        if (extendedBiome != null)
        {
            GenerationManager generationManager = extendedBiome.getGenerationManager();

            if (extendedBiome.getBiomeOwner() == BiomeOwner.BIOMESOPLENTY)
            {
                if (jsonBiome.biomeId != -1)
                {
                    biome.biomeID = jsonBiome.biomeId;
                    BiomeGenBase.getBiomeGenArray()[jsonBiome.biomeId] = biome;
                }
                else
                {
                    biome.biomeID = -1;
                }
            }

            Map<BiomeType, Integer> weightMap = jsonBiome.weights;
            
            //Removes the default weights set by us as they are about to be set from the config file
            extendedBiome.clearWeights();
            
            //TODO: Add a system for making Vanilla biome weights configurable. This won't necessarily be in this class, however it's worth noting.
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

            generationManager.configureGenerators(jsonBiome.decoration);
        }
    }

    public static Map<BiomeGenBase, String> getConfigFileMap()
    {
        return BiomeConfigurationHandler.configFileMap;
    }
}
