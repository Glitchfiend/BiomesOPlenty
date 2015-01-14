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
import java.util.Map.Entry;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.commons.io.FileUtils;

import biomesoplenty.api.biome.BiomeOwner;
import biomesoplenty.api.biome.BiomeProperty;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.common.util.config.JsonBiome;
import biomesoplenty.common.util.config.JsonEntitySpawn;

import com.google.gson.JsonSyntaxException;

public class BiomeConfigurationHandler 
{
	private static HashMap<BiomeGenBase, String> configFileMap = new HashMap();

	public static void init(File configDirectory)
	{
		registerConfigurableBiomes();
		load(configDirectory);
	}
	
	private static void registerConfigurableBiomes()
	{
	    registerConfigurableBiome(BiomeGenBase.ocean, "ocean");
	    registerConfigurableBiome(BiomeGenBase.plains, "plains");
	    registerConfigurableBiome(BiomeGenBase.desert, "desert");
	    registerConfigurableBiome(BiomeGenBase.extremeHills, "extreme_hills");
	    registerConfigurableBiome(BiomeGenBase.forest, "forest");
	    registerConfigurableBiome(BiomeGenBase.taiga, "taiga");
	    registerConfigurableBiome(BiomeGenBase.swampland, "swampland");
	    registerConfigurableBiome(BiomeGenBase.river, "river");
	    registerConfigurableBiome(BiomeGenBase.hell, "hell");
	    registerConfigurableBiome(BiomeGenBase.sky, "sky");
	    registerConfigurableBiome(BiomeGenBase.frozenOcean, "frozen_ocean");
	    registerConfigurableBiome(BiomeGenBase.frozenRiver, "frozen_river");
	    registerConfigurableBiome(BiomeGenBase.icePlains, "ice_plains");
	    registerConfigurableBiome(BiomeGenBase.iceMountains, "ice_mountains");
	    registerConfigurableBiome(BiomeGenBase.mushroomIsland, "mushroom_island");
	    registerConfigurableBiome(BiomeGenBase.mushroomIslandShore, "mushroom_island_shore");
	    registerConfigurableBiome(BiomeGenBase.beach, "beach");
	    registerConfigurableBiome(BiomeGenBase.desertHills, "beach_hills");
	    registerConfigurableBiome(BiomeGenBase.forestHills, "forest_hills");
	    registerConfigurableBiome(BiomeGenBase.taigaHills, "taiga_hills");
	    registerConfigurableBiome(BiomeGenBase.extremeHillsEdge, "extreme_hills_edge");
	    registerConfigurableBiome(BiomeGenBase.jungle, "jungle");
	    registerConfigurableBiome(BiomeGenBase.jungleHills, "jungle_hills");
	    registerConfigurableBiome(BiomeGenBase.jungleEdge, "jungle_edge");
	    registerConfigurableBiome(BiomeGenBase.deepOcean, "deep_ocean");
	    registerConfigurableBiome(BiomeGenBase.stoneBeach, "stone_beach");
	    registerConfigurableBiome(BiomeGenBase.coldBeach, "cold_beach");
	    registerConfigurableBiome(BiomeGenBase.birchForest, "birch_forest");
	    registerConfigurableBiome(BiomeGenBase.birchForestHills, "birch_forest_hills");
	    registerConfigurableBiome(BiomeGenBase.roofedForest, "roofed_forest");
	    registerConfigurableBiome(BiomeGenBase.coldTaiga, "cold_taiga");
	    registerConfigurableBiome(BiomeGenBase.coldTaigaHills, "cold_taiga_hills");
	    registerConfigurableBiome(BiomeGenBase.megaTaiga, "mega_taiga");
	    registerConfigurableBiome(BiomeGenBase.megaTaigaHills, "mega_taiga_hills");
	    registerConfigurableBiome(BiomeGenBase.extremeHillsPlus, "extreme_hills_plus");
	    registerConfigurableBiome(BiomeGenBase.savanna, "savanna");
	    registerConfigurableBiome(BiomeGenBase.savannaPlateau, "savanna_plateau");
	    registerConfigurableBiome(BiomeGenBase.mesa, "mesa");
	    registerConfigurableBiome(BiomeGenBase.mesaPlateau_F, "mesa_plateau_f");
	    registerConfigurableBiome(BiomeGenBase.mesaPlateau, "mesa_plateau");  
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
	
	public static void registerConfigurableBiome(BiomeGenBase biome, String configFileName)
	{
		translateVanillaValues(biome);
		configFileMap.put(biome, configFileName);
	}
	
	private static void translateVanillaValues(BiomeGenBase biome)
	{
		IExtendedBiome extendedBiome = (IExtendedBiome)biome; 
		
		if (extendedBiome.getBiomeOwner() == BiomeOwner.OTHER)
		{
			extendedBiome.setProperty(BiomeProperty.GRASS_PER_CHUNK, biome.theBiomeDecorator.grassPerChunk);
			//TODO: Create our own implementations
			//biome.theBiomeDecorator.grassPerChunk = 0;
			extendedBiome.setProperty(BiomeProperty.FLOWERS_PER_CHUNK, biome.theBiomeDecorator.flowersPerChunk);
			//biome.theBiomeDecorator.flowersPerChunk = 0;
		}
	}
	
	private static void configureBiomeWithJson(BiomeGenBase biome, JsonBiome jsonBiome)
	{
		//TODO: Reflect and modify biome id biome.biomeId = jsonBiome.biomeID;
		biome.biomeName = jsonBiome.biomeName;
		biome.topBlock = jsonBiome.topBlock;
		biome.fillerBlock = jsonBiome.fillerBlock;
		biome.setHeight(new BiomeGenBase.Height(jsonBiome.rootHeight, jsonBiome.rootVariation));
		biome.temperature = jsonBiome.temperature;
		biome.rainfall = jsonBiome.rainfall;
		//TODO: Reflect and modify enableRain and enableSnow
		biome.color = jsonBiome.color;
		biome.waterColorMultiplier = jsonBiome.waterColorMultiplier;
		JsonEntitySpawn.addBiomeEntitySpawns(biome, jsonBiome);
		
		IExtendedBiome extendedBiome = (IExtendedBiome)biome;

		if (!jsonBiome.decorationProperties.isEmpty())
		{
			for (Entry<BiomeProperty, Object> entry : jsonBiome.decorationProperties.entrySet())
			{
				BiomeProperty property = entry.getKey();
				Object value = entry.getValue();
				
				if (property != null)
				{
					extendedBiome.setProperty(property, value);
				}
			}
		}
	}
}
