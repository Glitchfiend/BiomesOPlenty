/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.config;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonBiome 
{
	public static final Gson serializer = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(IBlockState.class, new JsonBlockState()).create();
	
	public String biomeName;
	public int biomeId;
	public IBlockState topBlock;
	public IBlockState fillerBlock;
	public float rootHeight;
	public float rootVariation;
	public float temperature;
	public float rainfall;
	public int color;
	public int waterColorMultiplier;
	public ArrayList<JsonEntitySpawn> entities;
	
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
		
		return biome;
	}
}
