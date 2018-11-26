/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.biome.BOPBiomes.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.biome.IExtendedBiome;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.nether.BOPHellBiome;
import biomesoplenty.common.biome.nether.BiomeCorruptedSands;
import biomesoplenty.common.biome.nether.BiomeFungiForest;
import biomesoplenty.common.biome.nether.BiomePhantasmagoricInferno;
import biomesoplenty.common.biome.nether.BiomeUndergarden;
import biomesoplenty.common.biome.nether.BiomeVisceralHeap;
import biomesoplenty.common.biome.overworld.BOPOverworldBiome;
import biomesoplenty.common.biome.overworld.BiomeGenAlps;
import biomesoplenty.common.biome.overworld.BiomeGenBambooForest;
import biomesoplenty.common.biome.overworld.BiomeGenBayou;
import biomesoplenty.common.biome.overworld.BiomeGenBog;
import biomesoplenty.common.biome.overworld.BiomeGenBorealForest;
import biomesoplenty.common.biome.overworld.BiomeGenBrushland;
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
import biomesoplenty.common.biome.overworld.BiomeGenFlowerIsland;
import biomesoplenty.common.biome.overworld.BiomeGenGlacier;
import biomesoplenty.common.biome.overworld.BiomeGenGrassland;
import biomesoplenty.common.biome.overworld.BiomeGenGravelBeach;
import biomesoplenty.common.biome.overworld.BiomeGenGrove;
import biomesoplenty.common.biome.overworld.BiomeGenHighland;
import biomesoplenty.common.biome.overworld.BiomeGenKelpForest;
import biomesoplenty.common.biome.overworld.BiomeGenLandOfLakes;
import biomesoplenty.common.biome.overworld.BiomeGenLavenderFields;
import biomesoplenty.common.biome.overworld.BiomeGenLushDesert;
import biomesoplenty.common.biome.overworld.BiomeGenLushSwamp;
import biomesoplenty.common.biome.overworld.BiomeGenMangrove;
import biomesoplenty.common.biome.overworld.BiomeGenMapleWoods;
import biomesoplenty.common.biome.overworld.BiomeGenMarsh;
import biomesoplenty.common.biome.overworld.BiomeGenMeadow;
import biomesoplenty.common.biome.overworld.BiomeGenMoor;
import biomesoplenty.common.biome.overworld.BiomeGenMountain;
import biomesoplenty.common.biome.overworld.BiomeGenMysticGrove;
import biomesoplenty.common.biome.overworld.BiomeGenOasis;
import biomesoplenty.common.biome.overworld.BiomeGenOminousWoods;
import biomesoplenty.common.biome.overworld.BiomeGenOrchard;
import biomesoplenty.common.biome.overworld.BiomeGenOriginBeach;
import biomesoplenty.common.biome.overworld.BiomeGenOriginIsland;
import biomesoplenty.common.biome.overworld.BiomeGenOutback;
import biomesoplenty.common.biome.overworld.BiomeGenOvergrownCliffs;
import biomesoplenty.common.biome.overworld.BiomeGenPasture;
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
import biomesoplenty.common.biome.overworld.BiomeGenWhiteBeach;
import biomesoplenty.common.biome.overworld.BiomeGenWoodland;
import biomesoplenty.common.biome.overworld.BiomeGenXericShrubland;
import biomesoplenty.common.biome.vanilla.BiomeExtBeach;
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
import biomesoplenty.common.biome.vanilla.BiomeExtHell;
import biomesoplenty.common.biome.vanilla.BiomeExtIceMountains;
import biomesoplenty.common.biome.vanilla.BiomeExtIcePlains;
import biomesoplenty.common.biome.vanilla.BiomeExtJungle;
import biomesoplenty.common.biome.vanilla.BiomeExtJungleHills;
import biomesoplenty.common.biome.vanilla.BiomeExtMegaTaiga;
import biomesoplenty.common.biome.vanilla.BiomeExtMegaTaigaHills;
import biomesoplenty.common.biome.vanilla.BiomeExtMesa;
import biomesoplenty.common.biome.vanilla.BiomeExtMesaPlateau;
import biomesoplenty.common.biome.vanilla.BiomeExtMushroomIsland;
import biomesoplenty.common.biome.vanilla.BiomeExtOcean;
import biomesoplenty.common.biome.vanilla.BiomeExtPlains;
import biomesoplenty.common.biome.vanilla.BiomeExtRoofedForest;
import biomesoplenty.common.biome.vanilla.BiomeExtSavanna;
import biomesoplenty.common.biome.vanilla.BiomeExtSavannaPlateau;
import biomesoplenty.common.biome.vanilla.BiomeExtSwampland;
import biomesoplenty.common.biome.vanilla.BiomeExtTaiga;
import biomesoplenty.common.biome.vanilla.BiomeExtTaigaHills;
import biomesoplenty.common.command.BOPCommand;
import biomesoplenty.common.util.config.BOPConfig;
import biomesoplenty.common.world.WorldProviderBOPHell;
import biomesoplenty.common.world.WorldTypeBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes implements BOPBiomes.IBiomeRegistry
{
    public static final File BIOMES_DIR = new File(BiomesOPlenty.configDirectory, "biomes");
    public static final File DEFAULTS_DIR = new File(BIOMES_DIR, "defaults");
    public static final File BOP_DEFAULTS_DIR = new File(DEFAULTS_DIR, "biomesoplenty");
    public static final File VANILLA_DEFAULTS_DIR = new File(DEFAULTS_DIR, "vanilla");

    public static WorldTypeBOP worldTypeBOP;

    private static int nextBiomeId = 40;
    private static File biomeIdMapFile;
    private static IConfigObj biomeIdMapConf;
    protected static Map<String, Integer> biomeIdMap;
    private static Set<Integer> idsReservedInConfig;
    private static Map<Integer, IExtendedBiome> biomeWrapperMap;
    
    public static Set<Biome> presentBiomes;
    public static Map<Integer, List<Integer>> subBiomesMap;
    
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
        if (!BIOMES_DIR.exists())
        {
            BIOMES_DIR.mkdir();
        	
        	try 
        	{
				(new File(BIOMES_DIR, "Put biome config files here")).createNewFile();
			} 
        	catch (IOException e) {}
        }

        // remove any existing default files and recreate the directory
        try {
            FileUtils.deleteDirectory(DEFAULTS_DIR);
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("Could not delete default biome config directory!");
        }
        DEFAULTS_DIR.mkdir();

        initSubBiomes();

        registerBiomes();
        registerBiomeDictionaryTags();
        
        //After normal biomes to account for adding custom beaches
        initExtendedBiomes();
        
        registerNetherOverride();

        // save the biome ids to the config file (creating it if it doesn't exist)
        BOPConfig.writeFile(biomeIdMapFile, biomeIdMap);
        
        //Exclude biome decoration from certain worldtypes
        excludedDecoratedWorldTypes.add(WorldType.AMPLIFIED);
        excludedDecoratedWorldTypes.add(WorldType.CUSTOMIZED);
        excludedDecoratedWorldTypes.add(WorldType.DEFAULT);
        excludedDecoratedWorldTypes.add(WorldType.DEFAULT_1_1);
        excludedDecoratedWorldTypes.add(WorldType.FLAT);
        excludedDecoratedWorldTypes.add(WorldType.LARGE_BIOMES);
    }
    
    public static void initSubBiomes()
    {
        subBiomesMap = new HashMap<Integer, List<Integer>>();

        // Add vanilla sub biomes
        
        setSubBiome(Biomes.DESERT, Biomes.DESERT_HILLS);
        setSubBiome(Biomes.FOREST, Biomes.FOREST_HILLS);
        setSubBiome(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS);
        setSubBiome(Biomes.ROOFED_FOREST, Biomes.PLAINS);
        setSubBiome(Biomes.TAIGA, Biomes.TAIGA_HILLS);
        setSubBiome(Biomes.REDWOOD_TAIGA, Biomes.REDWOOD_TAIGA_HILLS);
        setSubBiome(Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS);
        setSubBiome(Biomes.PLAINS, Biomes.FOREST_HILLS, Biomes.FOREST);
        setSubBiome(Biomes.ICE_PLAINS, Biomes.ICE_MOUNTAINS);
        setSubBiome(Biomes.JUNGLE, Biomes.JUNGLE_HILLS);
        setSubBiome(Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES);
        setSubBiome(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU);
        setSubBiome(Biomes.MESA_ROCK, Biomes.MESA);
        
        // oceans get occasional patches of deep ocean
        // don't add any islands - those are done per climate in GenLayerBiomeIslands
        setSubBiome(Biomes.OCEAN, Biomes.DEEP_OCEAN);
        
    }

    private static void registerBiomes()
    {
        // beach biomes (normal biomes rely on these being registered first)
        
        gravel_beach = registerOverworldBiome(new BiomeGenGravelBeach());
        white_beach = registerOverworldBiome(new BiomeGenWhiteBeach());
        origin_beach = registerOverworldBiome(new BiomeGenOriginBeach());
        
        // normal biomes which have weights
        alps = registerOverworldBiome(new BiomeGenAlps(BiomeGenAlps.AlpsType.ALPS));
        bamboo_forest = registerOverworldBiome(new BiomeGenBambooForest());
        bayou = registerOverworldBiome(new BiomeGenBayou());
        bog = registerOverworldBiome(new BiomeGenBog());
        boreal_forest = registerOverworldBiome(new BiomeGenBorealForest());
        brushland = registerOverworldBiome(new BiomeGenBrushland());
        chaparral = registerOverworldBiome(new BiomeGenChaparral());
        cherry_blossom_grove = registerOverworldBiome(new BiomeGenCherryBlossomGrove());
        cold_desert = registerOverworldBiome(new BiomeGenColdDesert());
        coniferous_forest = registerOverworldBiome(new BiomeGenConiferousForest());
        crag = registerOverworldBiome(new BiomeGenCrag());
        dead_forest = registerOverworldBiome(new BiomeGenDeadForest());
        dead_swamp = registerOverworldBiome(new BiomeGenDeadSwamp());
        eucalyptus_forest = registerOverworldBiome(new BiomeGenEucalyptusForest());
        fen = registerOverworldBiome(new BiomeGenFen());
        flower_field = registerOverworldBiome(new BiomeGenFlowerField());
        grassland = registerOverworldBiome(new BiomeGenGrassland());
        grove = registerOverworldBiome(new BiomeGenGrove());
        highland = registerOverworldBiome(new BiomeGenHighland());
        land_of_lakes = registerOverworldBiome(new BiomeGenLandOfLakes());
        lavender_fields = registerOverworldBiome(new BiomeGenLavenderFields());
        lush_desert = registerOverworldBiome(new BiomeGenLushDesert());
        lush_swamp = registerOverworldBiome(new BiomeGenLushSwamp());
        mangrove = registerOverworldBiome(new BiomeGenMangrove());
        maple_woods = registerOverworldBiome(new BiomeGenMapleWoods());
        marsh = registerOverworldBiome(new BiomeGenMarsh());
        meadow = registerOverworldBiome(new BiomeGenMeadow());
        moor = registerOverworldBiome(new BiomeGenMoor());
        mountain = registerOverworldBiome(new BiomeGenMountain(BiomeGenMountain.MountainType.MOUNTAIN));
        mystic_grove = registerOverworldBiome(new BiomeGenMysticGrove());
        ominous_woods = registerOverworldBiome(new BiomeGenOminousWoods());
        orchard = registerOverworldBiome(new BiomeGenOrchard());
        outback = registerOverworldBiome(new BiomeGenOutback());
        overgrown_cliffs = registerOverworldBiome(new BiomeGenOvergrownCliffs());
        prairie = registerOverworldBiome(new BiomeGenPrairie());
        quagmire = registerOverworldBiome(new BiomeGenQuagmire());
        rainforest = registerOverworldBiome(new BiomeGenRainforest());
        redwood_forest = registerOverworldBiome(new BiomeGenRedwoodForest(BiomeGenRedwoodForest.ForestType.REDWOOD_FOREST));
        sacred_springs = registerOverworldBiome(new BiomeGenSacredSprings());
        seasonal_forest = registerOverworldBiome(new BiomeGenSeasonalForest());
        shield = registerOverworldBiome(new BiomeGenShield());
        shrubland = registerOverworldBiome(new BiomeGenShrubland());
        snowy_coniferous_forest = registerOverworldBiome(new BiomeGenSnowyConiferousForest());
        snowy_forest = registerOverworldBiome(new BiomeGenSnowyForest());
        steppe = registerOverworldBiome(new BiomeGenSteppe());
        temperate_rainforest = registerOverworldBiome(new BiomeGenTemperateRainforest());
        tropical_rainforest = registerOverworldBiome(new BiomeGenTropicalRainforest());
        tundra = registerOverworldBiome(new BiomeGenTundra());
        wasteland = registerOverworldBiome(new BiomeGenWasteland());
        wetland = registerOverworldBiome(new BiomeGenWetland());
        woodland = registerOverworldBiome(new BiomeGenWoodland());
        xeric_shrubland = registerOverworldBiome(new BiomeGenXericShrubland());
        
        // edge-biomes, sub-biomes and mutated-biomes
        
        alps_foothills = registerOverworldBiome(new BiomeGenAlps(BiomeGenAlps.AlpsType.ALPS_FOOTHILLS));
        mountain_foothills = registerOverworldBiome(new BiomeGenMountain(BiomeGenMountain.MountainType.MOUNTAIN_FOOTHILLS));
        redwood_forest_edge = registerOverworldBiome(new BiomeGenRedwoodForest(BiomeGenRedwoodForest.ForestType.REDWOOD_FOREST_EDGE));
        pasture = registerOverworldBiome(new BiomeGenPasture());
        glacier = registerOverworldBiome(new BiomeGenGlacier());
        oasis = registerOverworldBiome(new BiomeGenOasis());
        coral_reef = registerOverworldBiome(new BiomeGenCoralReef());
        kelp_forest = registerOverworldBiome(new BiomeGenKelpForest());

        setSubBiome(BOPBiomes.prairie, BOPBiomes.pasture);
        setSubBiome(Optional.of(Biomes.ICE_PLAINS), BOPBiomes.glacier);
        setSubBiome(Optional.of(Biomes.DESERT), BOPBiomes.oasis);
        setSubBiome(Optional.of(Biomes.OCEAN), BOPBiomes.coral_reef);
        setSubBiome(Optional.of(Biomes.OCEAN), BOPBiomes.kelp_forest);

        // island biomes
        
        origin_island = registerOverworldBiome(new BiomeGenOriginIsland());
        tropical_island = registerOverworldBiome(new BiomeGenTropicalIsland());
        volcanic_island = registerOverworldBiome(new BiomeGenVolcanicIsland());
        flower_island = registerOverworldBiome(new BiomeGenFlowerIsland());
    
        addIslandBiome(origin_island, 1);
        addIslandBiome(tropical_island, 3);
        addIslandBiome(volcanic_island, 5);
        addIslandBiome(flower_island, 7);

        // nether biomes
        corrupted_sands = registerNetherBiome(new BiomeCorruptedSands());
        fungi_forest = registerNetherBiome(new BiomeFungiForest());
        phantasmagoric_inferno = registerNetherBiome(new BiomePhantasmagoricInferno());
        undergarden = registerNetherBiome(new BiomeUndergarden());
        visceral_heap = registerNetherBiome(new BiomeVisceralHeap());
    }
    
    public static void initExtendedBiomes()
    {
        biomeWrapperMap = new HashMap<>();
        
        end_extension = registerWrappedBiome(new BiomeExtEnd());
        mushroom_island_extension = registerWrappedBiome(new BiomeExtMushroomIsland());
        birch_forest_extension = registerWrappedBiome(new BiomeExtBirchForest());
        birch_forest_hills_extension = registerWrappedBiome(new BiomeExtBirchForestHills());
        cold_taiga_extension = registerWrappedBiome(new BiomeExtColdTaiga());
        cold_taiga_hills_extension = registerWrappedBiome(new BiomeExtColdTaigaHills());
        desert_extension = registerWrappedBiome(new BiomeExtDesert());
        desert_hills_extension = registerWrappedBiome(new BiomeExtDesertHills());
        extreme_hills_extension = registerWrappedBiome(new BiomeExtExtremeHills());
        extreme_hills_plus_extension = registerWrappedBiome(new BiomeExtExtremeHillsPlus());
        forest_extension = registerWrappedBiome(new BiomeExtForest());
        forest_hills_extension = registerWrappedBiome(new BiomeExtForestHills());
        ice_plains_extension = registerWrappedBiome(new BiomeExtIcePlains());
        ice_mountains_extension = registerWrappedBiome(new BiomeExtIceMountains());
        jungle_extension = registerWrappedBiome(new BiomeExtJungle());
        jungle_hills_extension = registerWrappedBiome(new BiomeExtJungleHills());
        mega_taiga_extension = registerWrappedBiome(new BiomeExtMegaTaiga());
        mega_taiga_hills_extension = registerWrappedBiome(new BiomeExtMegaTaigaHills());
        mesa_extension = registerWrappedBiome(new BiomeExtMesa());
        mesa_plateau_extension = registerWrappedBiome(new BiomeExtMesaPlateau());
        ocean_extension = registerWrappedBiome(new BiomeExtOcean());
        plains_extension = registerWrappedBiome(new BiomeExtPlains());
        roofed_forest_extension = registerWrappedBiome(new BiomeExtRoofedForest());
        savanna_extension = registerWrappedBiome(new BiomeExtSavanna());
        savanna_plateau_extension = registerWrappedBiome(new BiomeExtSavannaPlateau());
        swampland_extension = registerWrappedBiome(new BiomeExtSwampland());
        taiga_extension = registerWrappedBiome(new BiomeExtTaiga());
        taiga_hills_extension = registerWrappedBiome(new BiomeExtTaigaHills());
        hell_extension = registerWrappedBiome(new BiomeExtHell());
        beach_extension = registerWrappedBiome(new BiomeExtBeach());
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
        registerBiomeToDictionary(BOPBiomes.grassland, Type.PLAINS, Type.HILLS, Type.WET);    
        registerBiomeToDictionary(BOPBiomes.grove, Type.FOREST, Type.PLAINS, Type.LUSH, Type.WET, Type.SPARSE);   
        registerBiomeToDictionary(BOPBiomes.highland, Type.MOUNTAIN, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.land_of_lakes, Type.FOREST, Type.SWAMP, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.lavender_fields, Type.PLAINS, Type.MAGICAL, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.lush_desert, Type.SANDY, Type.HOT, Type.SAVANNA, Type.LUSH, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.lush_swamp, Type.SWAMP, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.mangrove, Type.WATER, Type.SWAMP, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.maple_woods, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.marsh, Type.SWAMP, Type.WET, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.meadow, Type.PLAINS, Type.FOREST, Type.LUSH, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.moor, Type.SWAMP, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.mountain, Type.MOUNTAIN, Type.FOREST, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.mystic_grove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.ominous_woods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.DEAD, Type.WET, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.orchard, Type.FOREST, Type.PLAINS, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.outback, Type.SANDY, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.overgrown_cliffs, Type.MOUNTAIN, Type.HILLS, Type.LUSH, Type.JUNGLE, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.quagmire, Type.SWAMP, Type.DEAD, Type.WASTELAND, Type.WET);
        registerBiomeToDictionary(BOPBiomes.rainforest, Type.JUNGLE, Type.FOREST, Type.LUSH, Type.HILLS, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.redwood_forest, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.sacred_springs, Type.MAGICAL, Type.FOREST, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.seasonal_forest, Type.FOREST, Type.COLD, Type.DENSE);
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
        registerBiomeToDictionary(BOPBiomes.alps_foothills, Type.MOUNTAIN, Type.SNOWY, Type.FOREST, Type.SPARSE, Type.COLD, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.mountain_foothills, Type.HILLS, Type.MOUNTAIN, Type.FOREST, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.redwood_forest_edge, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.pasture, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.glacier, Type.SNOWY, Type.WASTELAND, Type.COLD);
        registerBiomeToDictionary(BOPBiomes.oasis, Type.SANDY, Type.LUSH, Type.JUNGLE, Type.HOT, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.coral_reef, Type.WATER, Type.OCEAN);
        registerBiomeToDictionary(BOPBiomes.kelp_forest, Type.WATER, Type.OCEAN);

        //Origin Island not tagged purposely
        registerBiomeToDictionary(BOPBiomes.tropical_island, Type.WATER, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.volcanic_island, Type.WATER, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.flower_island, Type.WATER, Type.PLAINS, Type.LUSH, Type.DENSE, Type.MAGICAL);
        registerBiomeToDictionary(BOPBiomes.origin_island, Type.WATER, Type.FOREST, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.gravel_beach, Type.BEACH);
        registerBiomeToDictionary(BOPBiomes.white_beach, Type.BEACH);
        registerBiomeToDictionary(BOPBiomes.origin_beach, Type.BEACH, Type.RARE);
        
        registerBiomeToDictionary(BOPBiomes.corrupted_sands, Type.NETHER, Type.HOT, Type.DRY, Type.SANDY, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.fungi_forest, Type.NETHER, Type.HOT, Type.MUSHROOM, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.phantasmagoric_inferno, Type.NETHER, Type.HOT, Type.WASTELAND, Type.DRY, Type.MAGICAL, Type.SPOOKY);
        registerBiomeToDictionary(BOPBiomes.undergarden, Type.NETHER, Type.HOT, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.visceral_heap, Type.NETHER, Type.HOT, Type.WET);
        
    }
    
    public static void registerNetherOverride()
    {
    	//Unregister vanilla Nether dimension
    	DimensionManager.unregisterDimension(-1);
    	
    	//Add override
        DimensionType netherBOP = DimensionType.register("Nether", "_nether", -1, WorldProviderBOPHell.class, false);
        DimensionManager.registerDimension(-1, netherBOP);
    }
    
    @Override
    public IExtendedBiome registerBiome(IExtendedBiome extendedBiome, String idName)
    {
        if (extendedBiome == null)
            throw new IllegalArgumentException("Extended biome to register cannot be null!");
            
        //Add to the set of present biomes
        presentBiomes.add(extendedBiome.getBaseBiome());
        
        //Extra functionality builtin, such as with BOPBiome
        if (extendedBiome instanceof Biome)
        {
            for (Entry<BOPClimates, Integer> entry : extendedBiome.getWeightMap().entrySet())
            {
                if (entry != null)
                {
                    BOPClimates climate = entry.getKey();
                    int weight = entry.getValue();
                    climate.addBiome(weight, extendedBiome.getBaseBiome());
                }
            }
        }
        else //extendedBiome is a wrapper
        {
            biomeWrapperMap.put(Biome.getIdForBiome(extendedBiome.getBaseBiome()), extendedBiome);
        }
        
        return extendedBiome;
    }
    
    @Override
    public IExtendedBiome getExtendedBiome(Biome biome) 
    {
        //Extra functionality builtin, such as with BOPBiome
        if (biome instanceof IExtendedBiome)
        {
            return (IExtendedBiome)biome;
        }
        else
        {
            IExtendedBiome wrapper = biomeWrapperMap.get(Biome.getIdForBiome(biome));
            
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
    public ImmutableSet<Biome> getPresentBiomes()
    {
        return ImmutableSet.copyOf(presentBiomes);
    }

    public static IConfigObj readConfigFile(String idName)
    {
        File configFile = new File(new File(BiomesOPlenty.configDirectory, "biomes"), idName + ".json");
        IConfigObj conf = new BOPConfig.ConfigFileObj(configFile, false, true);
        
        return conf;
    }

    public static void writeDefaultConfigFile(File basePath, String idName, IConfigObj conf)
    {
        File defaultFile = new File(basePath, idName + ".json");
        String json = BOPConfig.serializer.toJson(conf.serializeDefaults());

        try {
            FileUtils.write(defaultFile, json);
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("Could not write default biome config file " + defaultFile.getName());
        }
    }
    
    private static void setSubBiome(Optional<Biome> parent, Optional<Biome>... subBiomes)
    {
        if (parent.isPresent())
        {
            for (Optional<Biome> subBiome : subBiomes)
            {
                if (subBiome.isPresent())
                {
                    setSubBiome(parent.get(), subBiome.get());
                }
            }
        }
    }
    
    private static void setSubBiome(Biome parent, Biome... subBiomes)
    {
        Map<Integer, List<Integer>> map = subBiomesMap;
        int parentId = Biome.getIdForBiome(parent);
        if (!map.containsKey(parentId))
        {
            map.put(parentId, new ArrayList<Integer>());
        }
        for (Biome subBiome : subBiomes)
        {
            map.get(parentId).add(Biome.getIdForBiome(subBiome));
        }
    }
    
    private static void addIslandBiome(Optional<Biome> biome, int weight)
    {
        if (biome.isPresent())
        {
            totalIslandBiomesWeight += weight;
            islandBiomesMap.put(Biome.getIdForBiome(biome.get()), weight);
        }
    }
    
    private static IExtendedBiome registerWrappedBiome(IExtendedBiome extendedBiome)
    {
        //Non-wrapped biomes should not be registered this way
        if (extendedBiome.getBaseBiome() instanceof IExtendedBiome)
            throw new IllegalArgumentException("Biome already implements IExtendedBiome, it should be registered appropriately");

        String idName = extendedBiome.getResourceLocation().getResourcePath();
        extendedBiome.configure(readConfigFile(idName));
        return BOPBiomes.REG_INSTANCE.registerBiome(extendedBiome, idName);
    }
    
    private static Optional<Biome> registerOverworldBiome(BOPOverworldBiome biome)
    {
        String idName = biome.getResourceLocation().getResourcePath();
        Integer id = biomeIdMapConf.getInt(idName, null);
        if (id == null) {id = new Integer(getNextFreeBiomeId());}
        biomeIdMap.put(idName, id);
        
        if (id > -1) {
            biome.configure(biome.conf);
            BOPCommand.biomeCount++;

            BOPBiomes.REG_INSTANCE.registerBiome(biome, idName);
            biome.setRegistryName(biome.getResourceLocation());
            ForgeRegistries.BIOMES.register(biome);
            
            //Enable spwning and village generation in the biome
            if (biome.canSpawnInBiome)
                BiomeManager.addSpawnBiome(biome);
            
            if (biome.canGenerateVillages)
                BiomeManager.addVillageBiome(biome, true);
            
            return Optional.of(biome);
            
        } else {
            return Optional.absent();
        }
    }

    private static Optional<Biome> registerNetherBiome(BOPHellBiome biome)
    {
        String idName = biome.getResourceLocation().getResourcePath();
        Integer id = biomeIdMapConf.getInt(idName, null);
        if (id == null) {id = new Integer(getNextFreeBiomeId());}
        biomeIdMap.put(idName, id);

        if (id > -1) {
            biome.configure(biome.conf);
            BOPCommand.biomeCount++;

            BOPBiomes.REG_INSTANCE.registerBiome(biome, idName);
            biome.setRegistryName(biome.getResourceLocation());
            ForgeRegistries.BIOMES.register(biome);

            return Optional.of(biome);

        } else {
            return Optional.absent();
        }
    }
    
    private static void registerBiomeToDictionary(Optional<Biome> biome, Type...types)
    {
        if (biome.isPresent())
        {
            BiomeDictionary.addTypes(biome.get(), types);
        }
    }

    public static int getNextFreeBiomeId()
    {
        for (int i = nextBiomeId; i < 256; i++)
        {
            if (Biome.getBiome(i) != null)
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
