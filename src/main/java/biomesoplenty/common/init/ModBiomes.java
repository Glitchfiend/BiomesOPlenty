/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.biome.BOPBiomes.alps;
import static biomesoplenty.api.biome.BOPBiomes.bamboo_forest;
import static biomesoplenty.api.biome.BOPBiomes.bayou;
import static biomesoplenty.api.biome.BOPBiomes.birch_forest_extension;
import static biomesoplenty.api.biome.BOPBiomes.birch_forest_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.bog;
import static biomesoplenty.api.biome.BOPBiomes.boreal_forest;
import static biomesoplenty.api.biome.BOPBiomes.brushland;
import static biomesoplenty.api.biome.BOPBiomes.canyon;
import static biomesoplenty.api.biome.BOPBiomes.canyon_ravine;
import static biomesoplenty.api.biome.BOPBiomes.chaparral;
import static biomesoplenty.api.biome.BOPBiomes.cherry_blossom_grove;
import static biomesoplenty.api.biome.BOPBiomes.cold_desert;
import static biomesoplenty.api.biome.BOPBiomes.cold_taiga_extension;
import static biomesoplenty.api.biome.BOPBiomes.cold_taiga_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.coniferous_forest;
import static biomesoplenty.api.biome.BOPBiomes.coral_reef;
import static biomesoplenty.api.biome.BOPBiomes.crag;
import static biomesoplenty.api.biome.BOPBiomes.dead_forest;
import static biomesoplenty.api.biome.BOPBiomes.dead_swamp;
import static biomesoplenty.api.biome.BOPBiomes.desert_extension;
import static biomesoplenty.api.biome.BOPBiomes.desert_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.end_extension;
import static biomesoplenty.api.biome.BOPBiomes.eucalyptus_forest;
import static biomesoplenty.api.biome.BOPBiomes.extreme_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.extreme_hills_plus_extension;
import static biomesoplenty.api.biome.BOPBiomes.fen;
import static biomesoplenty.api.biome.BOPBiomes.flower_field;
import static biomesoplenty.api.biome.BOPBiomes.forest_extension;
import static biomesoplenty.api.biome.BOPBiomes.forest_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.frozen_desert;
import static biomesoplenty.api.biome.BOPBiomes.garden;
import static biomesoplenty.api.biome.BOPBiomes.glacier;
import static biomesoplenty.api.biome.BOPBiomes.grassland;
import static biomesoplenty.api.biome.BOPBiomes.gravel_beach;
import static biomesoplenty.api.biome.BOPBiomes.grove;
import static biomesoplenty.api.biome.BOPBiomes.heathland;
import static biomesoplenty.api.biome.BOPBiomes.highland;
import static biomesoplenty.api.biome.BOPBiomes.ice_plains_extension;
import static biomesoplenty.api.biome.BOPBiomes.ice_mountains_extension;
import static biomesoplenty.api.biome.BOPBiomes.jungle_extension;
import static biomesoplenty.api.biome.BOPBiomes.jungle_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.kelp_forest;
import static biomesoplenty.api.biome.BOPBiomes.land_of_lakes;
import static biomesoplenty.api.biome.BOPBiomes.lavender_fields;
import static biomesoplenty.api.biome.BOPBiomes.lush_desert;
import static biomesoplenty.api.biome.BOPBiomes.lush_swamp;
import static biomesoplenty.api.biome.BOPBiomes.maple_woods;
import static biomesoplenty.api.biome.BOPBiomes.marsh;
import static biomesoplenty.api.biome.BOPBiomes.meadow;
import static biomesoplenty.api.biome.BOPBiomes.mega_taiga_extension;
import static biomesoplenty.api.biome.BOPBiomes.mega_taiga_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.mesa_extension;
import static biomesoplenty.api.biome.BOPBiomes.mesa_plateau_extension;
import static biomesoplenty.api.biome.BOPBiomes.moor;
import static biomesoplenty.api.biome.BOPBiomes.mountain;
import static biomesoplenty.api.biome.BOPBiomes.mountain_foothills;
import static biomesoplenty.api.biome.BOPBiomes.mystic_grove;
import static biomesoplenty.api.biome.BOPBiomes.oasis;
import static biomesoplenty.api.biome.BOPBiomes.ocean_extension;
import static biomesoplenty.api.biome.BOPBiomes.ominous_woods;
import static biomesoplenty.api.biome.BOPBiomes.origin_valley;
import static biomesoplenty.api.biome.BOPBiomes.orchard;
import static biomesoplenty.api.biome.BOPBiomes.outback;
import static biomesoplenty.api.biome.BOPBiomes.overgrown_cliffs;
import static biomesoplenty.api.biome.BOPBiomes.plains_extension;
import static biomesoplenty.api.biome.BOPBiomes.prairie;
import static biomesoplenty.api.biome.BOPBiomes.quagmire;
import static biomesoplenty.api.biome.BOPBiomes.rainforest;
import static biomesoplenty.api.biome.BOPBiomes.redwood_forest;
import static biomesoplenty.api.biome.BOPBiomes.roofed_forest_extension;
import static biomesoplenty.api.biome.BOPBiomes.sacred_springs;
import static biomesoplenty.api.biome.BOPBiomes.savanna_extension;
import static biomesoplenty.api.biome.BOPBiomes.savanna_plateau_extension;
import static biomesoplenty.api.biome.BOPBiomes.seasonal_forest;
import static biomesoplenty.api.biome.BOPBiomes.shield;
import static biomesoplenty.api.biome.BOPBiomes.shrubland;
import static biomesoplenty.api.biome.BOPBiomes.snowy_coniferous_forest;
import static biomesoplenty.api.biome.BOPBiomes.snowy_forest;
import static biomesoplenty.api.biome.BOPBiomes.steppe;
import static biomesoplenty.api.biome.BOPBiomes.swampland_extension;
import static biomesoplenty.api.biome.BOPBiomes.taiga_extension;
import static biomesoplenty.api.biome.BOPBiomes.taiga_hills_extension;
import static biomesoplenty.api.biome.BOPBiomes.temperate_rainforest;
import static biomesoplenty.api.biome.BOPBiomes.tropical_island;
import static biomesoplenty.api.biome.BOPBiomes.tropical_rainforest;
import static biomesoplenty.api.biome.BOPBiomes.tundra;
import static biomesoplenty.api.biome.BOPBiomes.volcanic_island;
import static biomesoplenty.api.biome.BOPBiomes.wasteland;
import static biomesoplenty.api.biome.BOPBiomes.wetland;
import static biomesoplenty.api.biome.BOPBiomes.woodland;
import static biomesoplenty.api.biome.BOPBiomes.xeric_shrubland;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.common.biome.overworld.BiomeGenAlps;
import biomesoplenty.common.biome.overworld.BiomeGenBambooForest;
import biomesoplenty.common.biome.overworld.BiomeGenBayou;
import biomesoplenty.common.biome.overworld.BiomeGenBog;
import biomesoplenty.common.biome.overworld.BiomeGenBorealForest;
import biomesoplenty.common.biome.overworld.BiomeGenBrushland;
import biomesoplenty.common.biome.overworld.BiomeGenCanyon;
import biomesoplenty.common.biome.overworld.BiomeGenChaparral;
import biomesoplenty.common.biome.overworld.BiomeGenCherryBlossomGrove;
import biomesoplenty.common.biome.overworld.BiomeGenColdDesert;
import biomesoplenty.common.biome.overworld.BiomeGenConiferousForest;
import biomesoplenty.common.biome.overworld.BiomeGenCoralReef;
import biomesoplenty.common.biome.overworld.BiomeGenCrag;
import biomesoplenty.common.biome.overworld.BiomeGenDeadForest;
import biomesoplenty.common.biome.overworld.BiomeGenDeadSwamp;
import biomesoplenty.common.biome.overworld.BiomeGenEucalyptusForest;
import biomesoplenty.common.biome.overworld.BiomeGenFen;
import biomesoplenty.common.biome.overworld.BiomeGenFlowerField;
import biomesoplenty.common.biome.overworld.BiomeGenGarden;
import biomesoplenty.common.biome.overworld.BiomeGenGlacier;
import biomesoplenty.common.biome.overworld.BiomeGenGrassland;
import biomesoplenty.common.biome.overworld.BiomeGenGravelBeach;
import biomesoplenty.common.biome.overworld.BiomeGenGrove;
import biomesoplenty.common.biome.overworld.BiomeGenHeathland;
import biomesoplenty.common.biome.overworld.BiomeGenHighland;
import biomesoplenty.common.biome.overworld.BiomeGenKelpForest;
import biomesoplenty.common.biome.overworld.BiomeGenLandOfLakes;
import biomesoplenty.common.biome.overworld.BiomeGenLavenderFields;
import biomesoplenty.common.biome.overworld.BiomeGenLushDesert;
import biomesoplenty.common.biome.overworld.BiomeGenLushSwamp;
import biomesoplenty.common.biome.overworld.BiomeGenMapleWoods;
import biomesoplenty.common.biome.overworld.BiomeGenMarsh;
import biomesoplenty.common.biome.overworld.BiomeGenMeadow;
import biomesoplenty.common.biome.overworld.BiomeGenMoor;
import biomesoplenty.common.biome.overworld.BiomeGenMountain;
import biomesoplenty.common.biome.overworld.BiomeGenMysticGrove;
import biomesoplenty.common.biome.overworld.BiomeGenOasis;
import biomesoplenty.common.biome.overworld.BiomeGenOminousWoods;
import biomesoplenty.common.biome.overworld.BiomeGenOrchard;
import biomesoplenty.common.biome.overworld.BiomeGenOriginValley;
import biomesoplenty.common.biome.overworld.BiomeGenOutback;
import biomesoplenty.common.biome.overworld.BiomeGenOvergrownCliffs;
import biomesoplenty.common.biome.overworld.BiomeGenPrairie;
import biomesoplenty.common.biome.overworld.BiomeGenQuagmire;
import biomesoplenty.common.biome.overworld.BiomeGenRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenRedwoodForest;
import biomesoplenty.common.biome.overworld.BiomeGenSacredSprings;
import biomesoplenty.common.biome.overworld.BiomeGenSeasonalForest;
import biomesoplenty.common.biome.overworld.BiomeGenShield;
import biomesoplenty.common.biome.overworld.BiomeGenShrubland;
import biomesoplenty.common.biome.overworld.BiomeGenSnowyConiferousForest;
import biomesoplenty.common.biome.overworld.BiomeGenSnowyForest;
import biomesoplenty.common.biome.overworld.BiomeGenSteppe;
import biomesoplenty.common.biome.overworld.BiomeGenTemperateRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenTropicalIsland;
import biomesoplenty.common.biome.overworld.BiomeGenTropicalRainforest;
import biomesoplenty.common.biome.overworld.BiomeGenTundra;
import biomesoplenty.common.biome.overworld.BiomeGenVolcanicIsland;
import biomesoplenty.common.biome.overworld.BiomeGenWasteland;
import biomesoplenty.common.biome.overworld.BiomeGenWetland;
import biomesoplenty.common.biome.overworld.BiomeGenWoodland;
import biomesoplenty.common.biome.overworld.BiomeGenXericShrubland;
import biomesoplenty.common.biome.vanilla.BiomeExtBirchForest;
import biomesoplenty.common.biome.vanilla.BiomeExtBirchForestHills;
import biomesoplenty.common.biome.vanilla.BiomeExtColdTaiga;
import biomesoplenty.common.biome.vanilla.BiomeExtColdTaigaHills;
import biomesoplenty.common.biome.vanilla.BiomeExtDesert;
import biomesoplenty.common.biome.vanilla.BiomeExtDesertHills;
import biomesoplenty.common.biome.vanilla.BiomeExtEnd;
import biomesoplenty.common.biome.vanilla.BiomeExtExtremeHills;
import biomesoplenty.common.biome.vanilla.BiomeExtExtremeHillsPlus;
import biomesoplenty.common.biome.vanilla.BiomeExtForest;
import biomesoplenty.common.biome.vanilla.BiomeExtForestHills;
import biomesoplenty.common.biome.vanilla.BiomeExtIcePlains;
import biomesoplenty.common.biome.vanilla.BiomeExtIceMountains;
import biomesoplenty.common.biome.vanilla.BiomeExtJungle;
import biomesoplenty.common.biome.vanilla.BiomeExtJungleHills;
import biomesoplenty.common.biome.vanilla.BiomeExtMegaTaiga;
import biomesoplenty.common.biome.vanilla.BiomeExtMegaTaigaHills;
import biomesoplenty.common.biome.vanilla.BiomeExtMesa;
import biomesoplenty.common.biome.vanilla.BiomeExtMesaPlateau;
import biomesoplenty.common.biome.vanilla.BiomeExtOcean;
import biomesoplenty.common.biome.vanilla.BiomeExtPlains;
import biomesoplenty.common.biome.vanilla.BiomeExtRoofedForest;
import biomesoplenty.common.biome.vanilla.BiomeExtSavanna;
import biomesoplenty.common.biome.vanilla.BiomeExtSavannaPlateau;
import biomesoplenty.common.biome.vanilla.BiomeExtSwampland;
import biomesoplenty.common.biome.vanilla.BiomeExtTaiga;
import biomesoplenty.common.biome.vanilla.BiomeExtTaigaHills;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPClimates.WeightedBiomeEntry;
import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class ModBiomes implements BOPBiomes.IBiomeRegistry
{
    public static WorldTypeBOP worldTypeBOP;

    private static int nextBiomeId = 40;
    private static File biomeIdMapFile;
    private static BOPConfig.IConfigObj biomeIdMapConf;
    protected static Map<String, Integer> biomeIdMap;
    private static Set<Integer> idsReservedInConfig;
    private static Map<Integer, IExtendedBiome> biomeWrapperMap;
    
    public static Set<BiomeGenBase> presentBiomes;
    public static Map<Integer, List<Integer>> subBiomesMap;
    public static Map<Integer, List<Integer>> mutatedBiomesMap;
    
    public static Map<Integer, Integer> islandBiomesMap = new HashMap<Integer, Integer>();
    public static int totalIslandBiomesWeight;
    
    public static void init()
    {
        worldTypeBOP = new WorldTypeBOP();
        
        // get BOP biome ids from the config file (if it exists)
        biomeIdMapFile = new File(BiomesOPlenty.configDirectory, "biome_ids.json");
        biomeIdMapConf = new BOPConfig.ConfigFileObj(biomeIdMapFile);
        biomeIdMap = new HashMap<String, Integer>();
        presentBiomes = Sets.newHashSet();
        
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
        
        //Create a folder and temp file to show people where to put biome config files
        File biomesDir = new File(BiomesOPlenty.configDirectory, "biomes");
        
        if (!biomesDir.exists())
        {
        	biomesDir.mkdir();
        	
        	try 
        	{
				(new File(biomesDir, "Put biome config files here")).createNewFile();
			} 
        	catch (IOException e) {}
        }
        
        initSubBiomes();
        initMutatedBiomes();

        registerBiomes();
        registerBiomeDictionaryTags();
        
        //After normal biomes to account for adding custom beaches
        initExtendedBiomes();
        
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
        setSubBiome(BiomeGenBase.extremeHills, BiomeGenBase.extremeHillsPlus);
        setSubBiome(BiomeGenBase.savanna, BiomeGenBase.savannaPlateau);
        setSubBiome(BiomeGenBase.mesaPlateau_F, BiomeGenBase.mesa);
        
        // oceans get occasional patches of deep ocean
        // don't add any islands - those are done per climate in GenLayerBiomeIslands
        setSubBiome(BiomeGenBase.ocean, BiomeGenBase.deepOcean);
        
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
        // beach biomes (normal biomes rely on these being registered first)
        
        gravel_beach = registerBOPBiome(new BiomeGenGravelBeach(), "Gravel Beach");
        
        // normal biomes which have weights
        alps = registerBOPBiome(new BiomeGenAlps(), "Alps");
        bamboo_forest = registerBOPBiome(new BiomeGenBambooForest(), "Bamboo Forest");
        bayou = registerBOPBiome(new BiomeGenBayou(), "Bayou");
        bog = registerBOPBiome(new BiomeGenBog(), "Bog");
        boreal_forest = registerBOPBiome(new BiomeGenBorealForest(), "Boreal Forest");
        brushland = registerBOPBiome(new BiomeGenBrushland(), "Brushland");
        canyon = registerBOPBiome(new BiomeGenCanyon(BiomeGenCanyon.CanyonType.PLATEAU), "Canyon");
        chaparral = registerBOPBiome(new BiomeGenChaparral(), "Chaparral");
        cherry_blossom_grove = registerBOPBiome(new BiomeGenCherryBlossomGrove(), "Cherry Blossom Grove");
        cold_desert = registerBOPBiome(new BiomeGenColdDesert(BiomeGenColdDesert.ColdDesertType.COLD), "Cold Desert");
        coniferous_forest = registerBOPBiome(new BiomeGenConiferousForest(), "Coniferous Forest");
        crag = registerBOPBiome(new BiomeGenCrag(), "Crag");
        dead_forest = registerBOPBiome(new BiomeGenDeadForest(), "Dead Forest");
        dead_swamp = registerBOPBiome(new BiomeGenDeadSwamp(), "Dead Swamp");
        eucalyptus_forest = registerBOPBiome(new BiomeGenEucalyptusForest(), "Eucalyptus Forest");
        fen = registerBOPBiome(new BiomeGenFen(), "Fen");
        flower_field = registerBOPBiome(new BiomeGenFlowerField(), "Flower Field");
        frozen_desert = registerBOPBiome(new BiomeGenColdDesert(BiomeGenColdDesert.ColdDesertType.FROZEN), "Frozen Desert");
        garden = registerBOPBiome(new BiomeGenGarden(), "Garden");
        grassland = registerBOPBiome(new BiomeGenGrassland(), "Grassland");
        grove = registerBOPBiome(new BiomeGenGrove(), "Grove");
        heathland = registerBOPBiome(new BiomeGenHeathland(), "Heathland");
        highland = registerBOPBiome(new BiomeGenHighland(), "Highland");
        land_of_lakes = registerBOPBiome(new BiomeGenLandOfLakes(), "Land Of Lakes");
        lavender_fields = registerBOPBiome(new BiomeGenLavenderFields(), "Lavender Fields");
        lush_desert = registerBOPBiome(new BiomeGenLushDesert(), "Lush Desert");
        lush_swamp = registerBOPBiome(new BiomeGenLushSwamp(), "Lush Swamp");
        maple_woods = registerBOPBiome(new BiomeGenMapleWoods(), "Maple Woods");
        marsh = registerBOPBiome(new BiomeGenMarsh(), "Marsh");
        meadow = registerBOPBiome(new BiomeGenMeadow(), "Meadow");
        moor = registerBOPBiome(new BiomeGenMoor(), "Moor");
        mountain = registerBOPBiome(new BiomeGenMountain(BiomeGenMountain.MountainType.PEAKS), "Mountain");
        mystic_grove = registerBOPBiome(new BiomeGenMysticGrove(), "Mystic Grove");
        ominous_woods = registerBOPBiome(new BiomeGenOminousWoods(), "Ominous Woods");
        orchard = registerBOPBiome(new BiomeGenOrchard(), "Orchard");
        origin_valley = registerBOPBiome(new BiomeGenOriginValley(), "Origin Valley");
        outback = registerBOPBiome(new BiomeGenOutback(), "Outback");
        overgrown_cliffs = registerBOPBiome(new BiomeGenOvergrownCliffs(), "Overgrown Cliffs");
        prairie = registerBOPBiome(new BiomeGenPrairie(), "Prairie");
        quagmire = registerBOPBiome(new BiomeGenQuagmire(), "Quagmire");
        rainforest = registerBOPBiome(new BiomeGenRainforest(), "Rainforest");
        redwood_forest = registerBOPBiome(new BiomeGenRedwoodForest(), "Redwood Forest");
        sacred_springs = registerBOPBiome(new BiomeGenSacredSprings(), "Sacred Springs");
        seasonal_forest = registerBOPBiome(new BiomeGenSeasonalForest(), "Seasonal Forest");
        shield = registerBOPBiome(new BiomeGenShield(), "Shield");
        shrubland = registerBOPBiome(new BiomeGenShrubland(), "Shrubland");
        snowy_coniferous_forest = registerBOPBiome(new BiomeGenSnowyConiferousForest(), "Snowy Coniferous Forest");
        snowy_forest = registerBOPBiome(new BiomeGenSnowyForest(), "Snowy Forest");
        steppe = registerBOPBiome(new BiomeGenSteppe(), "Steppe");
        temperate_rainforest = registerBOPBiome(new BiomeGenTemperateRainforest(), "Temperate Rainforest");
        tropical_rainforest = registerBOPBiome(new BiomeGenTropicalRainforest(), "Tropical Rainforest");
        tundra = registerBOPBiome(new BiomeGenTundra(), "Tundra");
        wasteland = registerBOPBiome(new BiomeGenWasteland(), "Wasteland");
        wetland = registerBOPBiome(new BiomeGenWetland(), "Wetland");
        woodland = registerBOPBiome(new BiomeGenWoodland(), "Woodland");
        xeric_shrubland = registerBOPBiome(new BiomeGenXericShrubland(), "Xeric Shrubland");
        
        // edge-biomes, sub-biomes and mutated-biomes
        
        mountain_foothills = registerBOPBiome(new BiomeGenMountain(BiomeGenMountain.MountainType.FOOTHILLS), "Mountain Foothills");
        canyon_ravine = registerBOPBiome(new BiomeGenCanyon(BiomeGenCanyon.CanyonType.RAVINE), "Canyon Ravine");
        glacier = registerBOPBiome(new BiomeGenGlacier(), "Glacier");
        oasis = registerBOPBiome(new BiomeGenOasis(), "Oasis");
        coral_reef = registerBOPBiome(new BiomeGenCoralReef(), "Coral Reef");
        kelp_forest = registerBOPBiome(new BiomeGenKelpForest(), "Kelp Forest");

        setSubBiome(canyon, canyon_ravine);
        setSubBiome(Optional.of(BiomeGenBase.icePlains), BOPBiomes.glacier);
        setSubBiome(Optional.of(BiomeGenBase.desert), BOPBiomes.oasis);
        setSubBiome(Optional.of(BiomeGenBase.ocean), BOPBiomes.coral_reef);
        setSubBiome(Optional.of(BiomeGenBase.ocean), BOPBiomes.kelp_forest);

        // island biomes
        
        tropical_island = registerBOPBiome(new BiomeGenTropicalIsland(), "Tropical Island");
        volcanic_island = registerBOPBiome(new BiomeGenVolcanicIsland(), "Volcanic Island");
    
        addIslandBiome(tropical_island, 10);
        addIslandBiome(volcanic_island, 3);
    }
    
    public static void initExtendedBiomes()
    {
        biomeWrapperMap = new HashMap<Integer, IExtendedBiome>();
        
        end_extension = registerWrappedBiome(new BiomeExtEnd(), "end");
        birch_forest_extension = registerWrappedBiome(new BiomeExtBirchForest(), "birch_forest");
        birch_forest_hills_extension = registerWrappedBiome(new BiomeExtBirchForestHills(), "birch_forest_hills");
        cold_taiga_extension = registerWrappedBiome(new BiomeExtColdTaiga(), "cold_taiga");
        cold_taiga_hills_extension = registerWrappedBiome(new BiomeExtColdTaigaHills(), "cold_taiga_hills");
        desert_extension = registerWrappedBiome(new BiomeExtDesert(), "desert");
        desert_hills_extension = registerWrappedBiome(new BiomeExtDesertHills(), "desert_hills");
        extreme_hills_extension = registerWrappedBiome(new BiomeExtExtremeHills(), "extreme_hills");
        extreme_hills_plus_extension = registerWrappedBiome(new BiomeExtExtremeHillsPlus(), "extreme_hills+");
        forest_extension = registerWrappedBiome(new BiomeExtForest(), "forest");
        forest_hills_extension = registerWrappedBiome(new BiomeExtForestHills(), "forest_hills");
        ice_plains_extension = registerWrappedBiome(new BiomeExtIcePlains(), "ice_plains");
        ice_mountains_extension = registerWrappedBiome(new BiomeExtIceMountains(), "ice_mountains");
        jungle_extension = registerWrappedBiome(new BiomeExtJungle(), "jungle");
        jungle_hills_extension = registerWrappedBiome(new BiomeExtJungleHills(), "jungle_hills");
        mega_taiga_extension = registerWrappedBiome(new BiomeExtMegaTaiga(), "mega_taiga");
        mega_taiga_hills_extension = registerWrappedBiome(new BiomeExtMegaTaigaHills(), "mega_taiga_hills");
        mesa_extension = registerWrappedBiome(new BiomeExtMesa(), "mesa");
        mesa_plateau_extension = registerWrappedBiome(new BiomeExtMesaPlateau(), "mesa_plateau");
        ocean_extension = registerWrappedBiome(new BiomeExtOcean(), "ocean");
        plains_extension = registerWrappedBiome(new BiomeExtPlains(), "plains");
        roofed_forest_extension = registerWrappedBiome(new BiomeExtRoofedForest(), "roofed_forest");
        savanna_extension = registerWrappedBiome(new BiomeExtSavanna(), "savanna");
        savanna_plateau_extension = registerWrappedBiome(new BiomeExtSavannaPlateau(), "savanna_plateau");
        swampland_extension = registerWrappedBiome(new BiomeExtSwampland(), "swampland");
        taiga_extension = registerWrappedBiome(new BiomeExtTaiga(), "taiga");
        taiga_hills_extension = registerWrappedBiome(new BiomeExtTaigaHills(), "taiga_hills");
        
    }
    
    private static void registerBiomeDictionaryTags()
    {
        //TODO: Add biome dictionary tags for biomes that haven't been added yet
        
        registerBiomeToDictionary(BOPBiomes.alps, Type.MOUNTAIN, Type.SNOWY, Type.COLD, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.bamboo_forest, Type.JUNGLE, Type.FOREST, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.bayou, Type.SWAMP, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.bog, Type.SWAMP, Type.FOREST, Type.COLD, Type.WET);
        registerBiomeToDictionary(BOPBiomes.boreal_forest, Type.FOREST, Type.CONIFEROUS, Type.HILLS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.brushland, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.canyon, Type.MOUNTAIN, Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.chaparral, Type.PLAINS, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.cherry_blossom_grove, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.cold_desert, Type.SNOWY, Type.DRY, Type.COLD);
        registerBiomeToDictionary(BOPBiomes.coniferous_forest, Type.CONIFEROUS, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.crag, Type.MOUNTAIN, Type.WASTELAND, Type.HILLS, Type.MAGICAL, Type.COLD, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.dead_forest, Type.FOREST, Type.DEAD, Type.COLD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.dead_swamp, Type.SWAMP, Type.DEAD, Type.SPOOKY, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.eucalyptus_forest, Type.FOREST, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.fen, Type.SWAMP, Type.FOREST, Type.COLD, Type.DEAD, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.flower_field, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.frozen_desert, Type.SNOWY, Type.DRY, Type.COLD);
        registerBiomeToDictionary(BOPBiomes.garden, Type.PLAINS, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.grassland, Type.PLAINS, Type.HILLS, Type.WET);    
        registerBiomeToDictionary(BOPBiomes.grove, Type.FOREST, Type.PLAINS, Type.LUSH, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.heathland, Type.PLAINS, Type.FOREST, Type.DRY, Type.SPARSE);    
        registerBiomeToDictionary(BOPBiomes.highland, Type.MOUNTAIN, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.land_of_lakes, Type.FOREST, Type.SWAMP, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.lavender_fields, Type.PLAINS, Type.MAGICAL, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.lush_desert, Type.SANDY, Type.HOT, Type.SAVANNA, Type.LUSH, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.lush_swamp, Type.SWAMP, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.maple_woods, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.marsh, Type.SWAMP, Type.WET, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.meadow, Type.PLAINS, Type.FOREST, Type.LUSH, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.moor, Type.SWAMP, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.mountain, Type.MOUNTAIN, Type.FOREST, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.mystic_grove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.ominous_woods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.DEAD, Type.WET, Type.DENSE);
        //registerBiomeToDictionary(BOPBiomes.origin_valley, Type.FOREST, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.outback, Type.SANDY, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.overgrown_cliffs, Type.MOUNTAIN, Type.HILLS, Type.LUSH, Type.JUNGLE, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.quagmire, Type.SWAMP, Type.DEAD, Type.WASTELAND, Type.WET);
        registerBiomeToDictionary(BOPBiomes.rainforest, Type.JUNGLE, Type.FOREST, Type.LUSH, Type.HILLS, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.redwood_forest, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.seasonal_forest, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.sacred_springs, Type.MAGICAL, Type.FOREST, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.shield, Type.FOREST, Type.COLD, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.shrubland, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.snowy_coniferous_forest, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.snowy_forest, Type.SNOWY, Type.FOREST, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.steppe, Type.PLAINS, Type.SANDY, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.temperate_rainforest, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.tropical_rainforest, Type.JUNGLE, Type.LUSH, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.tundra, Type.COLD, Type.WASTELAND, Type.DEAD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.wasteland, Type.WASTELAND, Type.DEAD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.wetland, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.woodland, Type.FOREST, Type.DRY, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.xeric_shrubland, Type.SANDY, Type.SAVANNA, Type.LUSH, Type.HOT, Type.DRY, Type.SPARSE);
        
        // edge-biomes, sub-biomes and mutated-biomes
        registerBiomeToDictionary(BOPBiomes.mountain_foothills, Type.HILLS, Type.MOUNTAIN, Type.FOREST, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.canyon_ravine, Type.MOUNTAIN, Type.DRY, Type.HILLS, Type.HOT, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.glacier, Type.SNOWY, Type.WASTELAND, Type.COLD);
        registerBiomeToDictionary(BOPBiomes.oasis, Type.SANDY, Type.LUSH, Type.JUNGLE, Type.HOT, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.coral_reef, Type.WATER, Type.OCEAN);
        registerBiomeToDictionary(BOPBiomes.kelp_forest, Type.WATER, Type.OCEAN);
        registerBiomeToDictionary(BOPBiomes.tropical_island, Type.WATER, Type.OCEAN, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.volcanic_island, Type.WATER, Type.OCEAN, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.gravel_beach, Type.BEACH);   
        
    }
    
    @Override
    public IExtendedBiome registerBiome(IExtendedBiome extendedBiome, String idName)
    {
        if (extendedBiome == null)
            throw new IllegalArgumentException("Extended biome to register cannot be null!");
            
        //Add to the set of present biomes
        presentBiomes.add(extendedBiome.getBaseBiome());
        configureBiome(extendedBiome, idName);
        
        //Extra functionality builtin, such as with BOPBiome
        if (extendedBiome instanceof BiomeGenBase)
        {
            for (Entry<BOPClimates, Integer> entry : extendedBiome.getWeightMap().entrySet())
            {
                if (entry != null)
                {
                    BOPClimates climate = entry.getKey();
                    int weight = entry.getValue();
                    climate.addLandBiome(weight, extendedBiome.getBaseBiome());
                }
            }
        }
        else //extendedBiome is a wrapper
        {
            biomeWrapperMap.put(extendedBiome.getBaseBiome().biomeID, extendedBiome);
        }
        
        return extendedBiome;
    }
    
    @Override
    public IExtendedBiome getExtendedBiome(BiomeGenBase biome) 
    {
        //Extra functionality builtin, such as with BOPBiome
        if (biome instanceof IExtendedBiome)
        {
            return (IExtendedBiome)biome;
        }
        else
        {
            IExtendedBiome wrapper = biomeWrapperMap.get(biome.biomeID);
            
            //This biome may not have a wrapper
            if (wrapper != null)
            {
                return wrapper;
            }
        }
        
        //No extension exists
        return null;
    }
    
    @Override
    public ImmutableSet<BiomeGenBase> getPresentBiomes()
    {
        return ImmutableSet.copyOf(presentBiomes);
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
    
    private static void addIslandBiome(Optional<BiomeGenBase> biome, int weight)
    {
        if (biome.isPresent())
        {
            totalIslandBiomesWeight += weight;
            islandBiomesMap.put(biome.get().biomeID, weight);
        }
    }
    
    private static void configureBiome(IExtendedBiome biome, String idName)
    {
        File configFile = new File(new File(BiomesOPlenty.configDirectory, "biomes"), idName + ".json");
        BOPConfig.IConfigObj conf = new BOPConfig.ConfigFileObj(configFile);
        
        // If there was a valid config file, then use it to configure the biome
        if (!conf.isEmpty()) {biome.configure(conf);}
        // log any warnings from parsing the config file
        for (String msg : conf.flushMessages()) {BiomesOPlenty.logger.warn(msg);}
    }
    
    private static IExtendedBiome registerWrappedBiome(IExtendedBiome extendedBiome, String idName)
    {
        //Non-wrapped biomes should not be registered this way
        if (extendedBiome.getBaseBiome() instanceof IExtendedBiome)
            throw new IllegalArgumentException("Biome already implements IExtendedBiome, it should be registered appropriately");
        
        return BOPBiomes.REG_INSTANCE.registerBiome(extendedBiome, idName);
    }
    
    private static Optional<BiomeGenBase> registerBOPBiome(BOPBiome biome, String name)
    {
        
        biome.setBiomeName(name);
        String idName = BiomeUtils.getBiomeIdentifier(biome);
        
        Integer id = biomeIdMapConf.getInt(idName, null);
        if (id == null) {id = new Integer(getNextFreeBiomeId());}
        biomeIdMap.put(idName, id);
        
        if (id > -1) {
            BOPCommand.biomeCount++;
            biome.biomeID = id;

            BOPBiomes.REG_INSTANCE.registerBiome(biome, idName);

            BiomeGenBase.getBiomeGenArray()[id] = biome;
            
            //Enable spwning and village generation in the biome
            if (biome.canSpawnInBiome)
                BiomeManager.addSpawnBiome(biome);
            
            if (biome.canGenerateVillages)
                BiomeManager.addVillageBiome(biome, true);
            
            return Optional.of((BiomeGenBase)biome);
            
        } else {
            return Optional.absent();
        }
    }
    
    private static void registerBiomeToDictionary(Optional<BiomeGenBase> biome, Type...types)
    {
        if (biome.isPresent())
        {
            BiomeDictionary.registerBiomeType(biome.get(), types);
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
