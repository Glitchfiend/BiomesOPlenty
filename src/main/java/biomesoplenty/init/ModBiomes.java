/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeMetadata;
import biomesoplenty.common.biome.BiomeRegistry;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.biome.nether.CrystallineChasmBiome;
import biomesoplenty.common.biome.nether.UndergrowthBiome;
import biomesoplenty.common.biome.nether.VisceralHeapBiome;
import biomesoplenty.common.biome.nether.WitheredAbyssBiome;
import biomesoplenty.common.biome.overworld.*;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.world.BOPBiomeProvider;
import biomesoplenty.common.world.BOPNetherBiomeProvider;
import biomesoplenty.common.world.BOPWorldType;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.List;
import java.util.Map;

import static biomesoplenty.api.biome.BOPBiomes.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static BOPWorldType bopWorldType = new BOPWorldType();

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();
    public static List<Integer> islandBiomeIds = Lists.newArrayList();
    public static Map<ResourceKey<Biome>, BiomeMetadata> biomeMetadata = Maps.newHashMap();

    public static void setup()
    {
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            ColorResolver grassColorResolver = BiomeColors.GRASS_COLOR_RESOLVER;
            ColorResolver foliageColorResolver = BiomeColors.FOLIAGE_COLOR_RESOLVER;
            ColorResolver waterColorResolver = BiomeColors.WATER_COLOR_RESOLVER;

            BiomeColors.GRASS_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                ResourceKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getGrassColorFunction() != null)
                {
                    return meta.getGrassColorFunction().apply(posX, posZ);
                }

                return grassColorResolver.getColor(biome, posX, posZ);
            };

            BiomeColors.FOLIAGE_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                ResourceKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getFoliageColorFunction() != null)
                {
                    return meta.getFoliageColorFunction().apply(posX, posZ);
                }

                return foliageColorResolver.getColor(biome, posX, posZ);
            };

            BiomeColors.WATER_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                ResourceKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getWaterColorFunction() != null)
                {
                    return meta.getWaterColorFunction().apply(posX, posZ);
                }

                return waterColorResolver.getColor(biome, posX, posZ);
            };
        }

        // Obtain the game data logger and disable it temporarily
        Logger gameDataLogger = (Logger)LogManager.getLogger(GameData.class);
        Level oldLevel = gameDataLogger.getLevel();
        gameDataLogger.setLevel(Level.OFF);

        // Register our world type
        // We intentionally use the minecraft namespace so we continue using "biomesoplenty" in server.properties
        // This is markedly better than the alternative of biomesoplenty:biomesoplenty.
        // We do this with GameData logging disabled to prevent people whining at us.
        bopWorldType.setRegistryName(new ResourceLocation("biomesoplenty"));
        ForgeRegistries.WORLD_TYPES.register(bopWorldType);

        // Re-enable the game data logger
        gameDataLogger.setLevel(oldLevel);

        // Register biome providers
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_overworld", BOPBiomeProvider.CODEC);
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_nether", BOPNetherBiomeProvider.CODEC);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        //Technical Biomes (Need to be registered before main biomes that use them)
        registerTechnicalBiome(new DunesBiome(), "dunes");
        registerTechnicalBiome(new GravelBeachBiome(), "gravel_beach");
        registerTechnicalBiome(new TropicBeachBiome(), "tropic_beach");
        registerTechnicalBiome(new AlpsFoothillsBiome(), "alps_foothills");
        registerTechnicalBiome(new RedwoodForestEdgeBiome(), "redwood_forest_edge");
        registerTechnicalBiome(new VolcanicPlainsBiome(), "volcanic_plains");
        registerTechnicalBiome(new OrchardBiome(), "orchard");

        BiomeRegistry.configureTechnicalBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.TECHNICAL_BIOME);

        // Both a standard biome and a technical biome
        registerBiome(new BayouMangroveBiome(), "bayou_mangrove");

        //Overworld Biomes
    	registerBiome(new AlpsBiome(), "alps");
        registerBiome(new BambooBlossomGroveBiome(), "bamboo_blossom_grove");
    	registerBiome(new BayouBiome(), "bayou");
        registerBiome(new BurntForestBiome(), "burnt_forest");
    	registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
        registerBiome(new ColdDesertBiome(), "cold_desert");
        registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        registerBiome(new ConiferousLakesBiome(), "coniferous_lakes");
        registerBiome(new DeadForestBiome(), "dead_forest");
        registerBiome(new DeepBayouBiome(), "deep_bayou");
        registerBiome(new DenseMarshBiome(), "dense_marsh");
        registerBiome(new DenseWoodlandBiome(), "dense_woodland");
        registerBiome(new DrylandBiome(), "dryland");
        registerBiome(new DryBoneyardBiome(), "dry_boneyard");
        registerBiome(new FirClearingBiome(), "fir_clearing");
        registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        registerBiome(new FungalFieldBiome(), "fungal_field");
        registerBiome(new FungalJungleBiome(), "fungal_jungle");
        registerBiome(new GoldenPrairieBiome(), "golden_prairie");
        registerBiome(new GrasslandBiome(), "grassland");
        registerBiome(new GrasslandCloverPatchBiome(), "grassland_clover_patch");
        registerBiome(new GroveBiome(), "grove");
        registerBiome(new GroveClearingBiome(), "grove_clearing");
        registerBiome(new GroveLakesBiome(), "grove_lakes");
        registerBiome(new HighlandBiome(), "highland");
        registerBiome(new HighlandCragBiome(), "highland_crag");
        registerBiome(new HighlandMoorBiome(), "highland_moor");
        registerBiome(new JadeCliffsBiome(), "jade_cliffs");
        registerBiome(new LavenderFieldBiome(), "lavender_field");
        registerBiome(new LavenderForestBiome(), "lavender_forest");
        registerBiome(new LushDesertBiome(), "lush_desert");
        registerBiome(new LushSavannaBiome(), "lush_savanna");
        registerBiome(new MarshBiome(), "marsh");
        registerBiome(new MeadowBiome(), "meadow");
        registerBiome(new MeadowForestBiome(), "meadow_forest");
        registerBiome(new MuskegBiome(), "muskeg");
        registerBiome(new MysticGroveBiome(), "mystic_grove");
        registerBiome(new MysticPlainsBiome(), "mystic_plains");
        registerBiome(new OminousWoodsBiome(), "ominous_woods");
        registerBiome(new OminousMireBiome(), "ominous_mire");
        registerBiome(new OriginValleyBiome(), "origin_valley");
        registerBiome(new PrairieBiome(), "prairie");
        registerBiome(new RainbowHillsBiome(), "rainbow_hills");
        registerBiome(new RainforestBiome(), "rainforest");
        registerBiome(new RainforestCliffsBiome(), "rainforest_cliffs");
        registerBiome(new RainforestFloodplainBiome(), "rainforest_floodplain");
        registerBiome(new RedwoodForestBiome(), "redwood_forest");
        registerBiome(new RedwoodHillsBiome(), "redwood_hills");
        registerBiome(new ScrublandBiome(), "scrubland");
        registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        registerBiome(new SeasonalOrchardBiome(), "seasonal_orchard");
        registerBiome(new SeasonalPumpkinPatchBiome(), "seasonal_pumpkin_patch");
        registerBiome(new ShroomyWetlandBiome(), "shroomy_wetland");
        registerBiome(new ShrublandBiome(), "shrubland");
        registerBiome(new ShrublandHillsBiome(), "shrubland_hills");
        registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        registerBiome(new SnowyMapleForestBiome(), "snowy_maple_forest");
        registerBiome(new SnowyFirClearingBiome(), "snowy_fir_clearing");
        registerBiome(new TallDeadForestBiome(), "tall_dead_forest");
        registerBiome(new TropicsBiome(), "tropics");
        registerBiome(new TundraBiome(), "tundra");
        registerBiome(new TundraBasinBiome(), "tundra_basin");
        registerBiome(new TundraBogBiome(), "tundra_bog");
        registerBiome(new VolcanoBiome(), "volcano");
        registerBiome(new WastelandBiome(), "wasteland");
        registerBiome(new WetlandBiome(), "wetland");
        registerBiome(new WetlandForestBiome(), "wetland_forest");
        registerBiome(new WoodedScrublandBiome(), "wooded_scrubland");
        registerBiome(new WoodlandBiome(), "woodland");

        //Nether Biomes
        registerBiome(new CrystallineChasmBiome(), "crystalline_chasm");
        registerBiome(new UndergrowthBiome(), "undergrowth");
        registerBiome(new VisceralHeapBiome(), "visceral_heap");
        registerBiome(new WitheredAbyssBiome(), "withered_abyss");

        BiomeRegistry.configureStandardBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.STANDARD_BIOME);

        //Sub/Island Biomes (Note: Rarity supports two decimal places)
        registerSubBiome(bayou, deep_bayou, 1.0F, 100);
        registerSubBiome(bayou, bayou_mangrove, 1.0F, 100);

        registerSubBiome(cherry_blossom_grove, bamboo_blossom_grove, 0.8F, 100);

        registerSubBiome(coniferous_forest, fir_clearing, 0.75F, 100);
        registerSubBiome(coniferous_forest, coniferous_lakes, 1.0F, 100);

        registerSubBiome(dead_forest, tall_dead_forest, 0.75F, 100);
        registerSubBiome(dead_forest, burnt_forest, 0.6F, 100);

        registerSubBiome(dryland, dry_boneyard, 0.75F, 100);

        registerSubBiome(fungal_jungle, fungal_field, 0.9F, 100);

        registerSubBiome(grassland, grassland_clover_patch, 1.0F, 100);

        registerSubBiome(grove, grove_clearing, 1.0F, 100);
        registerSubBiome(grove, grove_lakes, 1.0F, 100);

        registerSubBiome(highland, highland_crag, 1.25F, 100);
        registerSubBiome(highland, highland_moor, 0.75F, 100);

        registerSubBiome(lavender_field, lavender_forest, 0.6F, 100);

        registerSubBiome(lush_desert, lush_savanna, 0.65F, 100);

        registerSubBiome(marsh, dense_marsh, 1.0F, 100);

        registerSubBiome(meadow, flower_meadow, 1.0F, 100);
        registerSubBiome(meadow, meadow_forest, 1.0F, 100);

        registerSubBiome(mystic_grove, mystic_plains, 0.75F, 100);

        registerSubBiome(ominous_woods, ominous_mire, 0.75F, 100);

        registerSubBiome(prairie, golden_prairie, 1.0F, 100);

        registerSubBiome(rainforest, rainforest_cliffs, 2.0F, 100);
        registerSubBiome(rainforest, rainforest_floodplain, 2.0F, 100);

        registerSubBiome(redwood_forest, redwood_hills, 0.75F, 100);

        registerSubBiome(scrubland, wooded_scrubland, 1.0F, 100);

        registerSubBiome(seasonal_forest, seasonal_orchard, 1.0F, 100);
        registerSubBiome(seasonal_forest, seasonal_pumpkin_patch, 1.0F, 100);

        registerSubBiome(shrubland, shrubland_hills, 0.75F, 100);

        registerSubBiome(snowy_coniferous_forest, snowy_fir_clearing, 0.75F, 100);
        registerSubBiome(snowy_coniferous_forest, snowy_maple_forest, 1.25F, 100);

        registerSubBiome(tundra, tundra_basin, 1.5F, 100);
        registerSubBiome(tundra, tundra_bog, 2.0F, 100);

        registerSubBiome(wetland, wetland_forest, 1.5F, 100);
        registerSubBiome(wetland, shroomy_wetland, 1.5F, 100);

        registerSubBiome(woodland, dense_woodland, 0.9F, 100);

        BiomeRegistry.configureSubBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.SUB_BIOME);

        registerIslandBiome(origin_valley, BOPClimates.COOL_TEMPERATE, 50);
        registerIslandBiome(origin_valley, BOPClimates.DRY_TEMPERATE, 50);
        registerIslandBiome(origin_valley, BOPClimates.WET_TEMPERATE, 75);
        
        registerIslandBiome(volcano, BOPClimates.WARM_TEMPERATE, 75);
        registerIslandBiome(volcano, BOPClimates.MEDITERRANEAN, 75);
        registerIslandBiome(volcano, BOPClimates.SAVANNA, 50);

        registerIslandBiome(rainbow_hills, BOPClimates.WET_TEMPERATE, 25);
        registerIslandBiome(rainbow_hills, BOPClimates.WARM_TEMPERATE, 25);
        registerIslandBiome(rainbow_hills, BOPClimates.MEDITERRANEAN, 25);

        registerIslandBiome(tropics, BOPClimates.SUBTROPICAL, 75);
        registerIslandBiome(tropics, BOPClimates.TROPICAL, 50);
        registerIslandBiome(tropics, BOPClimates.HOT_DESERT, 50);

        BiomeRegistry.configureIslandBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.ISLAND_BIOME);

        // Set up vanilla biomes
        registerVanillaBiome(Biomes.SNOWY_TUNDRA, BOPClimates.ICE_CAP, 10);
        registerVanillaBiome(Biomes.MOUNTAINS, BOPClimates.TUNDRA, 10);
        registerVanillaBiome(Biomes.SNOWY_TAIGA, BOPClimates.TUNDRA, 7);
        registerVanillaBiome(Biomes.TAIGA, BOPClimates.WET_BOREAL, 10);
        registerVanillaBiome(Biomes.GIANT_TREE_TAIGA, BOPClimates.DRY_BOREAL, 5);
        registerVanillaBiome(Biomes.DARK_FOREST, BOPClimates.WET_TEMPERATE, 7);
        registerVanillaBiome(Biomes.BIRCH_FOREST, BOPClimates.DRY_TEMPERATE, 5);
        registerVanillaBiome(Biomes.FOREST, BOPClimates.COOL_TEMPERATE, 10);
        registerVanillaBiome(Biomes.PLAINS, BOPClimates.WARM_TEMPERATE, 10);
        registerVanillaBiome(Biomes.SWAMP, BOPClimates.SUBTROPICAL, 7);
        registerVanillaBiome(Biomes.JUNGLE, BOPClimates.TROPICAL, 10);
        registerVanillaBiome(Biomes.SAVANNA, BOPClimates.SAVANNA, 10);
        registerVanillaBiome(Biomes.DESERT, BOPClimates.HOT_DESERT, 15);
        registerVanillaBiome(Biomes.BADLANDS_PLATEAU, BOPClimates.HOT_DESERT, 10);
        registerVanillaBiome(Biomes.WOODED_BADLANDS_PLATEAU, BOPClimates.HOT_DESERT, 3);

        registerVanillaBiome(Biomes.BASALT_DELTAS, BOPClimates.NETHER, 10);
        registerVanillaBiome(Biomes.CRIMSON_FOREST, BOPClimates.NETHER, 10);
        registerVanillaBiome(Biomes.NETHER_WASTES, BOPClimates.NETHER, 10);
        registerVanillaBiome(Biomes.SOUL_SAND_VALLEY, BOPClimates.NETHER, 10);
        registerVanillaBiome(Biomes.WARPED_FOREST, BOPClimates.NETHER, 10);

        BiomeRegistry.configureVanillaBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.VANILLA_BIOME);

        registerBiomeDictionaryTags();
        registerVillagerTypes();
    }

    private static void registerBiomeDictionaryTags()
    {
        //Overworld Biomes
        registerBiomeToDictionary(alps, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.CONIFEROUS, Type.MOUNTAIN, Type.SNOWY);
        registerBiomeToDictionary(alps_foothills, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST, Type.MOUNTAIN, Type.SNOWY);
        registerBiomeToDictionary(bamboo_blossom_grove, Type.OVERWORLD, Type.WET, Type.LUSH, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(bayou, Type.OVERWORLD, Type.HOT, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(bayou_mangrove, Type.OVERWORLD, Type.HOT, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(burnt_forest, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DRY, Type.DEAD, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(cherry_blossom_grove, Type.OVERWORLD, Type.WET, Type.LUSH, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(cold_desert, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.SNOWY, Type.DRY);
        registerBiomeToDictionary(coniferous_forest, Type.OVERWORLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(coniferous_lakes, Type.OVERWORLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(dead_forest, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DEAD, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(deep_bayou, Type.OVERWORLD, Type.HOT, Type.DENSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(dense_marsh, Type.OVERWORLD, Type.DENSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(dense_woodland, Type.OVERWORLD, Type.DENSE, Type.FOREST);
        registerBiomeToDictionary(dryland, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.SANDY);
        registerBiomeToDictionary(dry_boneyard, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.DEAD, Type.SANDY, Type.WASTELAND);
        registerBiomeToDictionary(dunes, Type.OVERWORLD, Type.BEACH, Type.LUSH);
        registerBiomeToDictionary(fir_clearing, Type.OVERWORLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(flower_meadow, Type.OVERWORLD, Type.SPARSE, Type.CONIFEROUS, Type.LUSH, Type.PLAINS);
        registerBiomeToDictionary(fungal_field, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.MUSHROOM, Type.RARE, Type.PLAINS);
        registerBiomeToDictionary(fungal_jungle, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.MUSHROOM, Type.RARE);
        registerBiomeToDictionary(golden_prairie, Type.OVERWORLD, Type.DRY, Type.PLAINS);
        registerBiomeToDictionary(gravel_beach, Type.OVERWORLD, Type.SPARSE, Type.BEACH);
        registerBiomeToDictionary(grassland, Type.OVERWORLD, Type.SPARSE, Type.PLAINS, Type.WET);
        registerBiomeToDictionary(grassland_clover_patch, Type.OVERWORLD, Type.DENSE, Type.PLAINS, Type.WET);
        registerBiomeToDictionary(grove, Type.OVERWORLD, Type.FOREST, Type.HILLS);
        registerBiomeToDictionary(grove_clearing, Type.OVERWORLD, Type.SPARSE, Type.PLAINS);
        registerBiomeToDictionary(grove_lakes, Type.OVERWORLD, Type.SPARSE);
        registerBiomeToDictionary(highland, Type.OVERWORLD, Type.SPARSE, Type.MOUNTAIN, Type.PLAINS);
        registerBiomeToDictionary(highland_crag, Type.OVERWORLD, Type.SPARSE, Type.MOUNTAIN, Type.WASTELAND);
        registerBiomeToDictionary(highland_moor, Type.OVERWORLD, Type.SPARSE, Type.WET, Type.MOUNTAIN, Type.SWAMP);
        registerBiomeToDictionary(jade_cliffs, Type.OVERWORLD, Type.CONIFEROUS, Type.MAGICAL, Type.RARE, Type.FOREST, Type.MOUNTAIN);
        registerBiomeToDictionary(lavender_field, Type.OVERWORLD, Type.LUSH, Type.PLAINS);
        registerBiomeToDictionary(lavender_forest, Type.OVERWORLD, Type.DENSE, Type.LUSH, Type.FOREST);
        registerBiomeToDictionary(lush_desert, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA, Type.LUSH, Type.RARE, Type.SANDY);
        registerBiomeToDictionary(lush_savanna, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA, Type.LUSH, Type.RARE, Type.PLAINS);
        registerBiomeToDictionary(marsh, Type.OVERWORLD, Type.SPARSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(meadow, Type.OVERWORLD, Type.WET, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(meadow_forest, Type.OVERWORLD, Type.WET, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(muskeg, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.WET, Type.DEAD, Type.RARE, Type.SWAMP, Type.SNOWY);
        registerBiomeToDictionary(mystic_grove, Type.OVERWORLD, Type.WET, Type.LUSH, Type.MAGICAL, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(mystic_plains, Type.OVERWORLD, Type.SPARSE, Type.WET, Type.LUSH, Type.MAGICAL, Type.RARE, Type.PLAINS);
        registerBiomeToDictionary(ominous_mire, Type.OVERWORLD, Type.COLD, Type.WET, Type.CONIFEROUS, Type.SPOOKY, Type.RARE, Type.FOREST, Type.SWAMP);
        registerBiomeToDictionary(ominous_woods, Type.OVERWORLD, Type.COLD, Type.WET, Type.CONIFEROUS, Type.SPOOKY, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(orchard, Type.OVERWORLD, Type.SPARSE, Type.LUSH, Type.FOREST, Type.PLAINS);
        registerBiomeToDictionary(origin_valley, Type.OVERWORLD, Type.RARE);
        registerBiomeToDictionary(prairie, Type.OVERWORLD, Type.SPARSE, Type.DRY, Type.PLAINS);
        registerBiomeToDictionary(rainbow_hills, Type.OVERWORLD, Type.DENSE, Type.WET, Type.LUSH, Type.MAGICAL, Type.FOREST, Type.HILLS);
        registerBiomeToDictionary(rainforest, Type.OVERWORLD, Type.HOT, Type.DENSE, Type.WET, Type.JUNGLE, Type.LUSH, Type.FOREST);
        registerBiomeToDictionary(rainforest_cliffs, Type.OVERWORLD, Type.HOT, Type.DENSE, Type.WET, Type.JUNGLE, Type.LUSH, Type.PLATEAU);
        registerBiomeToDictionary(rainforest_floodplain, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.SWAMP);
        registerBiomeToDictionary(redwood_forest, Type.OVERWORLD, Type.DENSE, Type.FOREST);
        registerBiomeToDictionary(redwood_forest_edge, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(redwood_hills, Type.OVERWORLD, Type.DENSE, Type.FOREST, Type.HILLS);
        registerBiomeToDictionary(scrubland, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA);
        registerBiomeToDictionary(seasonal_forest, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(seasonal_orchard, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(seasonal_pumpkin_patch, Type.OVERWORLD, Type.SPARSE, Type.FOREST);
        registerBiomeToDictionary(shroomy_wetland, Type.OVERWORLD, Type.WET, Type.FOREST, Type.SWAMP, Type.MUSHROOM);
        registerBiomeToDictionary(shrubland, Type.OVERWORLD, Type.SPARSE, Type.PLAINS);
        registerBiomeToDictionary(shrubland_hills, Type.OVERWORLD, Type.PLAINS, Type.HILLS);
        registerBiomeToDictionary(snowy_coniferous_forest, Type.OVERWORLD, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(snowy_fir_clearing, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(snowy_maple_forest, Type.OVERWORLD, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(tall_dead_forest, Type.OVERWORLD, Type.COLD, Type.DEAD, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(tropic_beach, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.RARE, Type.BEACH, Type.SANDY);
        registerBiomeToDictionary(tropics, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.RARE);
        registerBiomeToDictionary(tundra, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DEAD, Type.PLAINS);
        registerBiomeToDictionary(tundra_basin, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DRY, Type.DEAD, Type.WASTELAND);
        registerBiomeToDictionary(tundra_bog, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(volcanic_plains, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.RARE, Type.BEACH, Type.WASTELAND);
        registerBiomeToDictionary(volcano, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.DEAD, Type.RARE, Type.MOUNTAIN, Type.WASTELAND);
        registerBiomeToDictionary(wasteland, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.DEAD, Type.RARE, Type.WASTELAND);
        registerBiomeToDictionary(wetland, Type.OVERWORLD, Type.WET, Type.FOREST, Type.SWAMP);
        registerBiomeToDictionary(wetland_forest, Type.OVERWORLD, Type.WET, Type.DENSE, Type.FOREST, Type.SWAMP);
        registerBiomeToDictionary(wooded_scrubland, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA);
        registerBiomeToDictionary(woodland, Type.OVERWORLD, Type.FOREST);

        //Nether Biomes
        registerBiomeToDictionary(crystalline_chasm, Type.NETHER, Type.HOT, Type.DRY, Type.MAGICAL);
        registerBiomeToDictionary(undergrowth, Type.NETHER, Type.HOT, Type.DRY, Type.FOREST);
        registerBiomeToDictionary(visceral_heap, Type.NETHER, Type.HOT, Type.DRY);
        registerBiomeToDictionary(withered_abyss, Type.NETHER, Type.HOT, Type.DRY, Type.VOID);
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(alps, VillagerType.SNOW);
        registerVillagerType(alps_foothills, VillagerType.SNOW);
        registerVillagerType(bamboo_blossom_grove, VillagerType.PLAINS);
        registerVillagerType(bayou, VillagerType.SWAMP);
        registerVillagerType(bayou_mangrove, VillagerType.SWAMP);
        registerVillagerType(burnt_forest, VillagerType.TAIGA);
        registerVillagerType(cherry_blossom_grove, VillagerType.PLAINS);
        registerVillagerType(cold_desert, VillagerType.SNOW);
        registerVillagerType(coniferous_forest, VillagerType.TAIGA);
        registerVillagerType(coniferous_lakes, VillagerType.TAIGA);
        registerVillagerType(dead_forest, VillagerType.TAIGA);
        registerVillagerType(deep_bayou, VillagerType.SWAMP);
        registerVillagerType(dense_marsh, VillagerType.SWAMP);
        registerVillagerType(dense_woodland, VillagerType.PLAINS);
        registerVillagerType(dryland, VillagerType.PLAINS);
        registerVillagerType(dry_boneyard, VillagerType.PLAINS);
        registerVillagerType(dunes, VillagerType.PLAINS);
        registerVillagerType(fir_clearing, VillagerType.TAIGA);
        registerVillagerType(flower_meadow, VillagerType.TAIGA);
        registerVillagerType(fungal_field, VillagerType.JUNGLE);
        registerVillagerType(fungal_jungle, VillagerType.JUNGLE);
        registerVillagerType(golden_prairie, VillagerType.PLAINS);
        registerVillagerType(grassland, VillagerType.PLAINS);
        registerVillagerType(grassland_clover_patch, VillagerType.PLAINS);
        registerVillagerType(gravel_beach, VillagerType.PLAINS);
        registerVillagerType(grove, VillagerType.PLAINS);
        registerVillagerType(grove_clearing, VillagerType.PLAINS);
        registerVillagerType(grove_lakes, VillagerType.PLAINS);
        registerVillagerType(highland, VillagerType.PLAINS);
        registerVillagerType(highland_crag, VillagerType.PLAINS);
        registerVillagerType(highland_moor, VillagerType.PLAINS);
        registerVillagerType(jade_cliffs, VillagerType.PLAINS);
        registerVillagerType(lavender_field, VillagerType.PLAINS);
        registerVillagerType(lavender_forest, VillagerType.PLAINS);
        registerVillagerType(lush_desert, VillagerType.SAVANNA);
        registerVillagerType(lush_savanna, VillagerType.SAVANNA);
        registerVillagerType(marsh, VillagerType.SWAMP);
        registerVillagerType(meadow, VillagerType.TAIGA);
        registerVillagerType(meadow_forest, VillagerType.TAIGA);
        registerVillagerType(muskeg, VillagerType.SNOW);
        registerVillagerType(mystic_grove, VillagerType.PLAINS);
        registerVillagerType(mystic_plains, VillagerType.PLAINS);
        registerVillagerType(ominous_mire, VillagerType.SWAMP);
        registerVillagerType(ominous_woods, VillagerType.SWAMP);
        registerVillagerType(orchard, VillagerType.PLAINS);
        registerVillagerType(origin_valley, VillagerType.PLAINS);
        registerVillagerType(prairie, VillagerType.PLAINS);
        registerVillagerType(rainbow_hills, VillagerType.PLAINS);
        registerVillagerType(rainforest, VillagerType.JUNGLE);
        registerVillagerType(rainforest_cliffs, VillagerType.JUNGLE);
        registerVillagerType(rainforest_floodplain, VillagerType.JUNGLE);
        registerVillagerType(redwood_forest, VillagerType.PLAINS);
        registerVillagerType(redwood_forest_edge, VillagerType.PLAINS);
        registerVillagerType(redwood_hills, VillagerType.PLAINS);
        registerVillagerType(scrubland, VillagerType.SAVANNA);
        registerVillagerType(seasonal_forest, VillagerType.PLAINS);
        registerVillagerType(seasonal_orchard, VillagerType.PLAINS);
        registerVillagerType(seasonal_pumpkin_patch, VillagerType.PLAINS);
        registerVillagerType(shroomy_wetland, VillagerType.SWAMP);
        registerVillagerType(shrubland, VillagerType.PLAINS);
        registerVillagerType(shrubland_hills, VillagerType.PLAINS);
        registerVillagerType(snowy_coniferous_forest, VillagerType.SNOW);
        registerVillagerType(snowy_fir_clearing, VillagerType.SNOW);
        registerVillagerType(snowy_maple_forest, VillagerType.SNOW);
        registerVillagerType(tall_dead_forest, VillagerType.TAIGA);
        registerVillagerType(tropic_beach, VillagerType.JUNGLE);
        registerVillagerType(tropics, VillagerType.JUNGLE);
        registerVillagerType(tundra, VillagerType.TAIGA);
        registerVillagerType(tundra_basin, VillagerType.TAIGA);
        registerVillagerType(tundra_bog, VillagerType.TAIGA);
        registerVillagerType(volcanic_plains, VillagerType.PLAINS);
        registerVillagerType(volcano, VillagerType.PLAINS);
        registerVillagerType(wasteland, VillagerType.DESERT);
        registerVillagerType(wetland, VillagerType.SWAMP);
        registerVillagerType(wetland_forest, VillagerType.SWAMP);
        registerVillagerType(wooded_scrubland, VillagerType.SAVANNA);
        registerVillagerType(woodland, VillagerType.PLAINS);
    }

    private static void registerBiomeToDictionary(ResourceKey<Biome> key, Type...type)
    {
        if (BiomeUtil.exists(key))
        {
            BiomeDictionary.addTypes(key, type);
        }
    }

    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type)
    {
        if (BiomeUtil.exists(key))
        {
            VillagerType.BY_BIOME.put(key, type);
        }
    }

    /*
     * Biome registration helpers
     */

    public static void registerBiome(BiomeTemplate biome, String name)
    {
        BiomeRegistry.deferStandardRegistration(biome, name);
    }

    public static void registerTechnicalBiome(BiomeTemplate biome, String name)
    {
        BiomeRegistry.deferTechnicalBiomeRegistration(biome, name);
    }

    public static void registerSubBiome(ResourceKey<Biome> parent, ResourceKey<Biome> child, float rarity, int weight)
    {
        BiomeRegistry.deferSubBiomeRegistration(parent, child, weight, rarity);
    }

    public static void registerIslandBiome(ResourceKey<Biome> key, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferIslandBiomeRegistration(key, climate, weight);
    }

    private static void registerVanillaBiome(ResourceKey<Biome> key, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferVanillaBiomeRegistration(key, climate, weight);
    }

    public static class WeightedSubBiome
    {
        public final ResourceKey<Biome> biome;
        public final float rarity;
        public final int weight;

        public WeightedSubBiome(ResourceKey<Biome> biome, float rarity, int weight)
        {
            this.biome = biome;
            this.rarity = rarity;
            this.weight = weight;
        }
    }
}
