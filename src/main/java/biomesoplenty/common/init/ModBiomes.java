/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.biome.BOPBiomes.*;

import java.io.File;
import java.io.IOException;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.commons.io.FileUtils;

import biomesoplenty.common.biome.overworld.BiomeGenAlps;
import biomesoplenty.common.biome.overworld.BiomeGenArctic;
import biomesoplenty.common.biome.overworld.BiomeGenCrag;
import biomesoplenty.common.biome.overworld.BiomeGenOriginValley;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.util.config.JsonBiome;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.base.Optional;
import com.google.gson.JsonSyntaxException;

public class ModBiomes
{
    public static WorldTypeBOP worldTypeBOP;

    private static int nextBiomeId = 40;

    public static void init()
    {
        worldTypeBOP = new WorldTypeBOP();

        registerBiomes();
        registerExternalBiomes();
    }

    private static void registerBiomes()
    {
        alps = registerBiome(new BiomeGenAlps().setBiomeName("Alps"), "alps");
        arctic = registerBiome(new BiomeGenArctic().setBiomeName("Arctic"), "arctic");
        crag = registerBiome(new BiomeGenCrag().setBiomeName("Crag"), "crag");
        originValley = registerBiome(new BiomeGenOriginValley().setBiomeName("Origin Valley"), "origin_valley");
    }

    private static void registerExternalBiomes()
    {
        /*registerExternalBiome(BiomeGenBase.ocean, "ocean");
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
        registerExternalBiome(BiomeGenBase.mesaPlateau, "mesa_plateau");*/
    }

    private static Optional<BiomeGenBase> registerBiome(BiomeGenBase biome, String id)
    {
        biome.biomeID = getNextFreeBiomeId();
        BOPCommand.biomeCount++;
        
        return loadOrCreateConfig(biome, id);
    }

    /*private static void registerExternalBiome(BiomeGenBase biome, String id)
    {
        ExtendedBiomeRegistry.createExtension(biome);
        BiomeConfigurationHandler.translateVanillaValues(biome);
    }*/

    public static int getNextFreeBiomeId()
    {
        for (int i = nextBiomeId; i < 256; i++)
        {
            if (BiomeGenBase.getBiomeGenArray()[i] != null) 
            {
                if (i == 255) throw new IllegalArgumentException("There are no more biome ids avaliable!");
                continue;
            }
            else
            {
                nextBiomeId = i + 1;
                return i;
            }
        }

        return -1;
    }
    
    private static Optional<BiomeGenBase> loadOrCreateConfig(BiomeGenBase biome, String fileName)
    {
        File configFile = new File(new File(BiomesOPlenty.configDirectory, "biomes"), fileName + ".json");
        
        if (configFile.exists())
        {
            try
            {
                JsonBiome jsonBiome = JsonBiome.serializer.fromJson(FileUtils.readFileToString(configFile), JsonBiome.class);

                return JsonBiome.configureBiomeWithJson(biome, jsonBiome);
            }
            catch (JsonSyntaxException e)
            {
                BiomesOPlenty.logger.error("An error occurred reading " + configFile.getName(), e);
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
                
                return Optional.of(biome);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        return Optional.absent();
    }
}
