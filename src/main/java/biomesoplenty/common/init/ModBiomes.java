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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.common.biome.BOPBiomeManager;
import biomesoplenty.common.biome.overworld.*;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.base.Optional;

public class ModBiomes
{
    public static WorldTypeBOP worldTypeBOP;

    private static int nextBiomeId = 40;
    private static File biomeIdMapFile;
    private static BOPConfig.IConfigObj biomeIdMapConf;
    private static Map<String, Integer> biomeIdMap;
    private static Set<Integer> idsReservedInConfig;

    public static void init()
    {
        worldTypeBOP = new WorldTypeBOP();
        
        // get BOP biome ids from the config file (if it exists)
        biomeIdMapFile = new File(BiomesOPlenty.configDirectory, "biome_ids.json");
        biomeIdMapConf = new BOPConfig.ConfigFileObj(biomeIdMapFile);
        biomeIdMap = new HashMap<String, Integer>();
        
        // make a list of biome ids which are reserved in the config file for a particular biome, to ensure they are not used for a new biome
        idsReservedInConfig = new HashSet<Integer>();
        for (String biomeIdName : biomeIdMapConf.getKeys())
        {
            Integer reservedId = biomeIdMapConf.getInt(biomeIdName);
            if (reservedId != null && reservedId.intValue() > -1)
            {
                idsReservedInConfig.add(reservedId);
            }
        }
        
        registerBiomes();
        
        // save the biome ids to the config file (creating it if it doesn't exist)
        BOPConfig.writeFile(biomeIdMapFile, biomeIdMap);
        
    }
    

    private static void registerBiomes()
    {
        alps = registerBOPBiome(new BiomeGenAlps(), "Alps");
        arctic = registerBOPBiome(new BiomeGenArctic(), "Arctic");
        crag = registerBOPBiome(new BiomeGenCrag(), "Crag");
        chaparral = registerBOPBiome(new BiomeGenChaparral(), "Chaparral");
        denseForest = registerBOPBiome(new BiomeGenDenseForest(), "Dense Forest");
        flowerField = registerBOPBiome(new BiomeGenFlowerField(), "Flower Field");
        grassland = registerBOPBiome(new BiomeGenGrassland(), "Grassland");
        highland = registerBOPBiome(new BiomeGenHighland(), "Highland");
        lavenderFields = registerBOPBiome(new BiomeGenLavenderFields(), "Lavender Fields");
        marsh = registerBOPBiome(new BiomeGenMarsh(), "Marsh");
        originValley = registerBOPBiome(new BiomeGenOriginValley(), "Origin Valley");
        shrubland = registerBOPBiome(new BiomeGenShrubland(), "Shrubland");
        steppe = registerBOPBiome(new BiomeGenSteppe(), "Steppe");
        thicket = registerBOPBiome(new BiomeGenThicket(), "Thicket");
        tundra = registerBOPBiome(new BiomeGenTundra(), "Tundra");
        woodland = registerBOPBiome(new BiomeGenWoodland(), "Woodland");        
    }
    
    private static Optional<BiomeGenBase> registerBOPBiome(BOPBiome biome, String name)
    {
        
        biome.setBiomeName(name);
        String idName = BiomeUtils.getBiomeIdentifier(biome);
        
        Integer id = biomeIdMapConf.getInt(idName, null);
        if (id == null) {id = new Integer(getNextFreeBiomeId());}
        biomeIdMap.put(idName, id);
        
        if (id > -1) {
            
            File configFile = new File(new File(BiomesOPlenty.configDirectory, "biomes"), idName + ".json");
            BOPConfig.IConfigObj conf = new BOPConfig.ConfigFileObj(configFile);
            
            BOPCommand.biomeCount++;
            biome.biomeID = id;
            // If there was a valid config file, then use it to configure the biome
            if (!conf.isEmpty()) {biome.configure(conf);}
            // log any warnings from parsing the config file
            for (String msg : conf.flushMessages()) {BiomesOPlenty.logger.warn(msg);}

            BiomeGenBase.getBiomeGenArray()[id] = biome;
            
            for (Entry<BiomeType, Integer> entry : ((IExtendedBiome)biome).getWeightMap().entrySet())
            {
                if (entry != null)
                {
                    BiomeType biomeType = entry.getKey();
                    int weight = entry.getValue();
                    BOPBiomeManager.addBiome(biomeType, new BiomeEntry(biome, weight));
                }
            }
            
            return Optional.of((BiomeGenBase)biome);
            
        } else {
            return Optional.absent();
        }
    }

    public static int getNextFreeBiomeId()
    {
        for (int i = nextBiomeId; i < 256; i++)
        {
            if (BiomeGenBase.getBiomeGenArray()[i] != null) 
            {
                if (i == 255) throw new IllegalArgumentException("There are no more biome ids avaliable!");
                continue;
            }
            else if (idsReservedInConfig.contains(Integer.valueOf(i)))
            {
                // this id is reserved for a particular biome
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
 
}
