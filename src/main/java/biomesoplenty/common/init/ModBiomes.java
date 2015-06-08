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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    public static Map<Integer, List<Integer>> subBiomesMap;
    public static Map<Integer, List<Integer>> mutatedBiomesMap;

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
        
        initSubBiomes();
        initMutatedBiomes();
        
        registerBiomes();
        
        // save the biome ids to the config file (creating it if it doesn't exist)
        BOPConfig.writeFile(biomeIdMapFile, biomeIdMap);
        
    }
    
    public static void initSubBiomes()
    {
        subBiomesMap = new HashMap<Integer, List<Integer>>();

        // Add vanilla sub biomes
        
        setSubBiome(BiomeGenBase.desert, BiomeGenBase.desertHills);
        setSubBiome(BiomeGenBase.forest, BiomeGenBase.forestHills);
        setSubBiome(BiomeGenBase.birchForest, BiomeGenBase.birchForestHills);
        setSubBiome(BiomeGenBase.roofedForest, BiomeGenBase.plains);
        setSubBiome(BiomeGenBase.taiga, BiomeGenBase.taigaHills);
        setSubBiome(BiomeGenBase.megaTaiga, BiomeGenBase.megaTaigaHills);
        setSubBiome(BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills);
        setSubBiome(BiomeGenBase.plains, BiomeGenBase.forestHills, BiomeGenBase.forest);
        setSubBiome(BiomeGenBase.icePlains, BiomeGenBase.iceMountains);
        setSubBiome(BiomeGenBase.jungle, BiomeGenBase.jungleHills);
        setSubBiome(BiomeGenBase.ocean, BiomeGenBase.deepOcean);
        setSubBiome(BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsPlus);
        setSubBiome(BiomeGenBase.savanna, BiomeGenBase.savannaPlateau);
        setSubBiome(BiomeGenBase.mesaPlateau_F, BiomeGenBase.mesa);
        setSubBiome(BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.deepOcean, BiomeGenBase.plains, BiomeGenBase.forest);  // occasional islands within the oceans
        
    }
    
    public static void initMutatedBiomes()
    {
        mutatedBiomesMap = new HashMap<Integer, List<Integer>>();
        
        // Add vanilla mutated biomes
        
        // the mutated versions of vanilla biomes aren't actually saved to static variables in BiomeGenBase
        // instead, they just manually create mutated versions of many of their biomes via a hard coded list in BiomeGenBase
        // and by default assume a biome id which is the old one + 128
        // this severely limits the number of new biomes we can add (we'd have to keep the number below 128 to avoid clashes)
        // we hard code the list of vanilla biomes with mutated versions below, which enables other biomes to use the biome ids which are not taken
        
        setSubBiome(BiomeGenBase.plains, BiomeGenBase.getBiome(BiomeGenBase.plains.biomeID + 128));
        setSubBiome(BiomeGenBase.desert, BiomeGenBase.getBiome(BiomeGenBase.desert.biomeID + 128));
        setSubBiome(BiomeGenBase.forest, BiomeGenBase.getBiome(BiomeGenBase.forest.biomeID + 128));
        setSubBiome(BiomeGenBase.taiga, BiomeGenBase.getBiome(BiomeGenBase.taiga.biomeID + 128));
        setSubBiome(BiomeGenBase.swampland, BiomeGenBase.getBiome(BiomeGenBase.swampland.biomeID + 128));
        setSubBiome(BiomeGenBase.icePlains, BiomeGenBase.getBiome(BiomeGenBase.icePlains.biomeID + 128));
        setSubBiome(BiomeGenBase.jungle, BiomeGenBase.getBiome(BiomeGenBase.jungle.biomeID + 128));
        setSubBiome(BiomeGenBase.jungleEdge, BiomeGenBase.getBiome(BiomeGenBase.jungleEdge.biomeID + 128));
        setSubBiome(BiomeGenBase.coldTaiga, BiomeGenBase.getBiome(BiomeGenBase.coldTaiga.biomeID + 128));
        setSubBiome(BiomeGenBase.savanna, BiomeGenBase.getBiome(BiomeGenBase.savanna.biomeID + 128));
        setSubBiome(BiomeGenBase.savannaPlateau, BiomeGenBase.getBiome(BiomeGenBase.savannaPlateau.biomeID + 128));
        setSubBiome(BiomeGenBase.mesa, BiomeGenBase.getBiome(BiomeGenBase.mesa.biomeID + 128));
        setSubBiome(BiomeGenBase.mesaPlateau, BiomeGenBase.getBiome(BiomeGenBase.mesaPlateau.biomeID + 128));
        setSubBiome(BiomeGenBase.mesaPlateau_F, BiomeGenBase.getBiome(BiomeGenBase.mesaPlateau_F.biomeID + 128));
        setSubBiome(BiomeGenBase.birchForest, BiomeGenBase.getBiome(BiomeGenBase.birchForest.biomeID + 128));
        setSubBiome(BiomeGenBase.birchForestHills, BiomeGenBase.getBiome(BiomeGenBase.birchForestHills.biomeID + 128));
        setSubBiome(BiomeGenBase.roofedForest, BiomeGenBase.getBiome(BiomeGenBase.roofedForest.biomeID + 128));
        setSubBiome(BiomeGenBase.megaTaiga, BiomeGenBase.getBiome(BiomeGenBase.megaTaiga.biomeID + 128));
        setSubBiome(BiomeGenBase.extremeHills, BiomeGenBase.getBiome(BiomeGenBase.extremeHills.biomeID + 128));
        setSubBiome(BiomeGenBase.extremeHillsPlus, BiomeGenBase.getBiome(BiomeGenBase.extremeHillsPlus.biomeID + 128));
        setSubBiome(BiomeGenBase.megaTaigaHills, BiomeGenBase.getBiome(BiomeGenBase.megaTaigaHills.biomeID + 128));        
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
        heathland = registerBOPBiome(new BiomeGenHeathland(), "Heathland");
        highland = registerBOPBiome(new BiomeGenHighland(), "Highland");
        lavenderFields = registerBOPBiome(new BiomeGenLavenderFields(), "Lavender Fields");
        marsh = registerBOPBiome(new BiomeGenMarsh(), "Marsh");
        moor = registerBOPBiome(new BiomeGenMoor(), "Moor");
        mountain = registerBOPBiome(new BiomeGenMountain(), "Mountain");
        originValley = registerBOPBiome(new BiomeGenOriginValley(), "Origin Valley");
        outback = registerBOPBiome(new BiomeGenOutback(), "Outback");
        shrubland = registerBOPBiome(new BiomeGenShrubland(), "Shrubland");
        steppe = registerBOPBiome(new BiomeGenSteppe(), "Steppe");
        thicket = registerBOPBiome(new BiomeGenThicket(), "Thicket");
        tundra = registerBOPBiome(new BiomeGenTundra(), "Tundra");
        woodland = registerBOPBiome(new BiomeGenWoodland(), "Woodland");
        
        // sub biomes
        
        glacier = registerBOPBiome(new BiomeGenGlacier(), "Glacier"); // TODO: implement glacier
        
        setSubBiome(Optional.of(BiomeGenBase.frozenOcean), arctic); // add some arctic regions in frozen oceans        
        setSubBiome(arctic, glacier);

    }
    

    private static void setSubBiome(Optional<BiomeGenBase> parent, Optional<BiomeGenBase>... subBiomes)
    {
        setSubBiome(parent, false, subBiomes);
    }
    
    private static void setSubBiome(Optional<BiomeGenBase> parent, boolean mutated, Optional<BiomeGenBase>... subBiomes)
    {
        if (parent.isPresent())
        {
            for (Optional<BiomeGenBase> subBiome : subBiomes)
            {
                if (subBiome.isPresent())
                {
                    setSubBiome(parent.get(), mutated, subBiome.get());
                }
            }
        }
    }
    
    private static void setSubBiome(BiomeGenBase parent, BiomeGenBase... subBiomes)
    {
        setSubBiome(parent, false, subBiomes);
    }
    
    private static void setSubBiome(BiomeGenBase parent, boolean mutated, BiomeGenBase... subBiomes)
    {
        Map<Integer, List<Integer>> map = mutated ? mutatedBiomesMap : subBiomesMap;
        int parentId = parent.biomeID;
        if (!map.containsKey(parentId))
        {
            map.put(parentId, new ArrayList<Integer>());
        }
        for (BiomeGenBase subBiome : subBiomes)
        {
            map.get(parentId).add(subBiome.biomeID);
        }
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
