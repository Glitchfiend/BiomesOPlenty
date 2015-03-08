/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.biome.ExtendedBiomeRegistry;
import biomesoplenty.common.config.BiomeConfigurationHandler;
import biomesoplenty.common.world.WorldTypeBOP;

public class ModBiomes
{
    public static WorldTypeBOP worldTypeBOP;

    public static void init()
    {
        worldTypeBOP = new WorldTypeBOP();
    
        registerExternalBiomes();
    }
    
    private static void registerExternalBiomes()
    {
        registerExternalBiome(BiomeGenBase.ocean, "ocean");
        registerExternalBiome(BiomeGenBase.plains, "plains");
        registerExternalBiome(BiomeGenBase.desert, "desert");
        registerExternalBiome(BiomeGenBase.extremeHills, "extreme_hills");
        registerExternalBiome(BiomeGenBase.forest, "forest");
        registerExternalBiome(BiomeGenBase.taiga, "taiga");
        registerExternalBiome(BiomeGenBase.swampland, "swampland");
        registerExternalBiome(BiomeGenBase.river, "river");
        registerExternalBiome(BiomeGenBase.hell, "hell");
        registerExternalBiome(BiomeGenBase.sky, "sky");
        registerExternalBiome(BiomeGenBase.frozenOcean, "frozen_ocean");
        registerExternalBiome(BiomeGenBase.frozenRiver, "frozen_river");
        registerExternalBiome(BiomeGenBase.icePlains, "ice_plains");
        registerExternalBiome(BiomeGenBase.iceMountains, "ice_mountains");
        registerExternalBiome(BiomeGenBase.mushroomIsland, "mushroom_island");
        registerExternalBiome(BiomeGenBase.mushroomIslandShore, "mushroom_island_shore");
        registerExternalBiome(BiomeGenBase.beach, "beach");
        registerExternalBiome(BiomeGenBase.desertHills, "beach_hills");
        registerExternalBiome(BiomeGenBase.forestHills, "forest_hills");
        registerExternalBiome(BiomeGenBase.taigaHills, "taiga_hills");
        registerExternalBiome(BiomeGenBase.extremeHillsEdge, "extreme_hills_edge");
        registerExternalBiome(BiomeGenBase.jungle, "jungle");
        registerExternalBiome(BiomeGenBase.jungleHills, "jungle_hills");
        registerExternalBiome(BiomeGenBase.jungleEdge, "jungle_edge");
        registerExternalBiome(BiomeGenBase.deepOcean, "deep_ocean");
        registerExternalBiome(BiomeGenBase.stoneBeach, "stone_beach");
        registerExternalBiome(BiomeGenBase.coldBeach, "cold_beach");
        registerExternalBiome(BiomeGenBase.birchForest, "birch_forest");
        registerExternalBiome(BiomeGenBase.birchForestHills, "birch_forest_hills");
        registerExternalBiome(BiomeGenBase.roofedForest, "roofed_forest");
        registerExternalBiome(BiomeGenBase.coldTaiga, "cold_taiga");
        registerExternalBiome(BiomeGenBase.coldTaigaHills, "cold_taiga_hills");
        registerExternalBiome(BiomeGenBase.megaTaiga, "mega_taiga");
        registerExternalBiome(BiomeGenBase.megaTaigaHills, "mega_taiga_hills");
        registerExternalBiome(BiomeGenBase.extremeHillsPlus, "extreme_hills_plus");
        registerExternalBiome(BiomeGenBase.savanna, "savanna");
        registerExternalBiome(BiomeGenBase.savannaPlateau, "savanna_plateau");
        registerExternalBiome(BiomeGenBase.mesa, "mesa");
        registerExternalBiome(BiomeGenBase.mesaPlateau_F, "mesa_plateau_f");
        registerExternalBiome(BiomeGenBase.mesaPlateau, "mesa_plateau");
    }
    
    private static void registerExternalBiome(BiomeGenBase biome, String id)
    {
    	ExtendedBiomeRegistry.createExtension(biome);
        BiomeConfigurationHandler.translateVanillaValues(biome);
        BiomeConfigurationHandler.getConfigFileMap().put(biome, id);
    }
    
}
