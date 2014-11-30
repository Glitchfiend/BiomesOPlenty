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

import org.apache.commons.io.FileUtils;

import com.google.gson.JsonSyntaxException;

import biomesoplenty.common.util.config.JsonBiome;
import biomesoplenty.common.util.config.JsonEntitySpawn;
import net.minecraft.world.biome.BiomeGenBase;

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
	    registerConfigurableBiome(BiomeGenBase.extremeHills, "extremeHills");
	    registerConfigurableBiome(BiomeGenBase.forest, "forest");
	    registerConfigurableBiome(BiomeGenBase.taiga, "taiga");
	    registerConfigurableBiome(BiomeGenBase.swampland, "swampland");
	    registerConfigurableBiome(BiomeGenBase.river, "river");
	    registerConfigurableBiome(BiomeGenBase.hell, "hell");
	    registerConfigurableBiome(BiomeGenBase.sky, "sky");
	    registerConfigurableBiome(BiomeGenBase.frozenOcean, "frozenOcean");
	    registerConfigurableBiome(BiomeGenBase.frozenRiver, "frozenRiver");
	    registerConfigurableBiome(BiomeGenBase.icePlains, "icePlains");
	    registerConfigurableBiome(BiomeGenBase.iceMountains, "iceMountains");
	    registerConfigurableBiome(BiomeGenBase.mushroomIsland, "mushroomIsland");
	    registerConfigurableBiome(BiomeGenBase.mushroomIslandShore, "mushroomIslandShore");
	    registerConfigurableBiome(BiomeGenBase.beach, "beach");
	    registerConfigurableBiome(BiomeGenBase.desertHills, "beachHills");
	    registerConfigurableBiome(BiomeGenBase.forestHills, "forestHills");
	    registerConfigurableBiome(BiomeGenBase.taigaHills, "taigaHills");
	    registerConfigurableBiome(BiomeGenBase.extremeHillsEdge, "extremeHillsEdge");
	    registerConfigurableBiome(BiomeGenBase.jungle, "jungle");
	    registerConfigurableBiome(BiomeGenBase.jungleHills, "jungleHills");
	    registerConfigurableBiome(BiomeGenBase.jungleEdge, "jungleEdge");
	    registerConfigurableBiome(BiomeGenBase.deepOcean, "deepOcean");
	    registerConfigurableBiome(BiomeGenBase.stoneBeach, "stoneBeach");
	    registerConfigurableBiome(BiomeGenBase.coldBeach, "coldBeach");
	    registerConfigurableBiome(BiomeGenBase.birchForest, "birchForest");
	    registerConfigurableBiome(BiomeGenBase.birchForestHills, "birchForestHills");
	    registerConfigurableBiome(BiomeGenBase.roofedForest, "roofedForest");
	    registerConfigurableBiome(BiomeGenBase.coldTaiga, "coldTaiga");
	    registerConfigurableBiome(BiomeGenBase.coldTaigaHills, "coldTaigaHills");
	    registerConfigurableBiome(BiomeGenBase.megaTaiga, "megaTaiga");
	    registerConfigurableBiome(BiomeGenBase.megaTaigaHills, "megaTaigaHills");
	    registerConfigurableBiome(BiomeGenBase.extremeHillsPlus, "extremeHillsPlus");
	    registerConfigurableBiome(BiomeGenBase.savanna, "savanna");
	    registerConfigurableBiome(BiomeGenBase.savannaPlateau, "savannaPlateau");
	    registerConfigurableBiome(BiomeGenBase.mesa, "mesa");
	    registerConfigurableBiome(BiomeGenBase.mesaPlateau_F, "mesaPlatea_F");
	    registerConfigurableBiome(BiomeGenBase.mesaPlateau, "mesaPlateau");  
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
		configFileMap.put(biome, configFileName);
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
	}
}
