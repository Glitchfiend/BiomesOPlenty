/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.biome.BiomeRegistry;
import biomesoplenty.common.biome.nether.*;
import biomesoplenty.common.biome.overworld.*;
import biomesoplenty.common.world.BOPBiomeGeneratorTypeScreen;
import biomesoplenty.common.world.BOPBiomeProvider;
import biomesoplenty.common.world.BOPNetherBiomeProvider;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;
import java.util.Optional;

import static biomesoplenty.api.biome.BOPBiomes.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static BOPBiomeGeneratorTypeScreen biomeGeneratorTypeScreenBOP;

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();
    public static List<Integer> islandBiomeIds = Lists.newArrayList();

    public static void setup()
    {
        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            biomeGeneratorTypeScreenBOP = new BOPBiomeGeneratorTypeScreen();
            BiomeGeneratorTypeScreens.PRESETS.add(biomeGeneratorTypeScreenBOP);
        }

        // Register biome providers
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_overworld", BOPBiomeProvider.CODEC);
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_nether", BOPNetherBiomeProvider.CODEC);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        //Technical Biomes (Need to be registered before main biomes that use them)
        registerTechnicalBiome(new GravelBeachBiome(), "gravel_beach");
        registerTechnicalBiome(new TropicBeachBiome(), "tropic_beach");
        registerTechnicalBiome(new AlpsFoothillsBiome(), "alps_foothills");
        registerTechnicalBiome(new RedwoodForestEdgeBiome(), "redwood_forest_edge");
        registerTechnicalBiome(new VolcanoEdgeBiome(), "volcano_edge");
        registerTechnicalBiome(new OrchardBiome(), "orchard");

        BiomeRegistry.configureTechnicalBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.TECHNICAL_BIOME);

        // Both a standard biome and a technical biome
        registerBiome(new MangroveBiome(), "mangrove");

        //Overworld Biomes
    	registerBiome(new AlpsBiome(), "alps");
    	registerBiome(new BayouBiome(), "bayou");
        registerBiome(new BogBiome(), "bog");
        registerBiome(new BorealForestBiome(), "boreal_forest");
    	registerBiome(new BrushlandBiome(), "brushland");
        registerBiome(new ChaparralBiome(), "chaparral");
    	registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
    	registerBiome(new ColdDesertBiome(), "cold_desert");
        registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        registerBiome(new DeadForestBiome(), "dead_forest");
        registerBiome(new FirClearingBiome(), "fir_clearing");
        registerBiome(new FloodplainBiome(), "floodplain");
        registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        registerBiome(new FungalJungleBiome(), "fungal_jungle");
        registerBiome(new GrasslandBiome(), "grassland");
        registerBiome(new GroveBiome(), "grove");
        registerBiome(new HighlandBiome(), "highland");
        registerBiome(new HighlandMoorBiome(), "highland_moor");
        registerBiome(new LavenderFieldBiome(), "lavender_field");
        registerBiome(new LushGrasslandBiome(), "lush_grassland");
        registerBiome(new LushSwampBiome(), "lush_swamp");
        registerBiome(new MapleWoodsBiome(), "maple_woods");
        registerBiome(new MarshBiome(), "marsh");
        registerBiome(new MeadowBiome(), "meadow");
        registerBiome(new MireBiome(), "mire");
        registerBiome(new MuskegBiome(), "muskeg");
        registerBiome(new MysticGroveBiome(), "mystic_grove");
        registerBiome(new OasisBiome(), "oasis");
        registerBiome(new OminousWoodsBiome(), "ominous_woods");
        registerBiome(new OriginHillsBiome(), "origin_hills");
        registerBiome(new OutbackBiome(), "outback");
        registerBiome(new OvergrownCliffsBiome(), "overgrown_cliffs");
        registerBiome(new PastureBiome(), "pasture");
        registerBiome(new PoppyFieldBiome(), "poppy_field");
        registerBiome(new PrairieBiome(), "prairie");
        registerBiome(new PumpkinPatchBiome(), "pumpkin_patch");
        registerBiome(new RainbowValleyBiome(), "rainbow_valley");
        registerBiome(new RainforestBiome(), "rainforest");
        registerBiome(new RedwoodForestBiome(), "redwood_forest");
        registerBiome(new ScrublandBiome(), "scrubland");
        registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        registerBiome(new ShieldBiome(), "shield");
        registerBiome(new ShrublandBiome(), "shrubland");
        registerBiome(new SilkgladeBiome(), "silkglade");
        registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        registerBiome(new SnowyFirClearingBiome(), "snowy_fir_clearing");
        registerBiome(new SnowyForestBiome(), "snowy_forest");
        registerBiome(new SteppeBiome(), "steppe");
        registerBiome(new TemperateRainforestBiome(), "temperate_rainforest");
        registerBiome(new TemperateRainforestHillsBiome(), "temperate_rainforest_hills");
        registerBiome(new TropicalRainforestBiome(), "tropical_rainforest");
        registerBiome(new TropicsBiome(), "tropics");
        registerBiome(new TundraBiome(), "tundra");
        registerBiome(new VolcanoBiome(), "volcano");
        registerBiome(new WastelandBiome(), "wasteland");
        registerBiome(new WetlandBiome(), "wetland");
        registerBiome(new WoodlandBiome(), "woodland");
        registerBiome(new XericShrublandBiome(), "xeric_shrubland");

        //Nether Biomes
        registerBiome(new CrystallineChasmBiome(), "crystalline_chasm");
        registerBiome(new UndergrowthBiome(), "undergrowth");
        registerBiome(new VisceralHeapBiome(), "visceral_heap");
        registerBiome(new WitheredAbyssBiome(), "withered_abyss");

        BiomeRegistry.configureStandardBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.STANDARD_BIOME);

        //Sub/Island Biomes (Note: Rarity supports two decimal places)
        registerSubBiome(Biomes.DESERT, oasis, 0.1F, 100);
        registerSubBiome(brushland, xeric_shrubland, 1.0F, 100);
        registerSubBiome(coniferous_forest, fir_clearing, 0.38F, 100);
        registerSubBiome(highland, highland_moor, 0.75F, 100);
        registerSubBiome(meadow, flower_meadow, 0.5F, 100);
        registerSubBiome(prairie, pasture, 1.0F, 100);
        registerSubBiome(seasonal_forest, pumpkin_patch, 0.45F, 100);
        registerSubBiome(snowy_coniferous_forest, snowy_fir_clearing, 0.5F, 100);
        registerSubBiome(temperate_rainforest, temperate_rainforest_hills, 0.8F, 100);

        BiomeRegistry.configureSubBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.SUB_BIOME);

        registerIslandBiome(origin_hills, BOPClimates.COOL_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.DRY_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.WET_TEMPERATE, 75);
        
        registerIslandBiome(volcano, BOPClimates.WARM_TEMPERATE, 75);
        registerIslandBiome(volcano, BOPClimates.MEDITERRANEAN, 75);
        registerIslandBiome(volcano, BOPClimates.SAVANNA, 50);

        registerIslandBiome(rainbow_valley, BOPClimates.WET_TEMPERATE, 25);
        registerIslandBiome(rainbow_valley, BOPClimates.WARM_TEMPERATE, 25);
        registerIslandBiome(rainbow_valley, BOPClimates.MEDITERRANEAN, 25);

        registerIslandBiome(tropics, BOPClimates.SUBTROPICAL, 75);
        registerIslandBiome(tropics, BOPClimates.TROPICAL, 50);
        registerIslandBiome(tropics, BOPClimates.HOT_DESERT, 50);

        BiomeRegistry.configureIslandBiomes();
        BiomeRegistry.finalizeRegistrations(BiomeRegistry.RegistrationType.ISLAND_BIOME);

        // Set up vanilla biomes
        registerVanillaBiome(Biomes.SNOWY_TUNDRA, BOPClimates.ICE_CAP, 10);
        registerVanillaBiome(Biomes.MOUNTAINS, BOPClimates.TUNDRA, 7);
        registerVanillaBiome(Biomes.SNOWY_TAIGA, BOPClimates.TUNDRA, 10);
        registerVanillaBiome(Biomes.TAIGA, BOPClimates.WET_BOREAL, 10);
        registerVanillaBiome(Biomes.GIANT_TREE_TAIGA, BOPClimates.DRY_BOREAL, 5);
        registerVanillaBiome(Biomes.DARK_FOREST, BOPClimates.WET_TEMPERATE, 5);
        registerVanillaBiome(Biomes.SWAMP, BOPClimates.WET_TEMPERATE, 7);
        registerVanillaBiome(Biomes.BIRCH_FOREST, BOPClimates.DRY_TEMPERATE, 7);
        registerVanillaBiome(Biomes.FOREST, BOPClimates.COOL_TEMPERATE, 10);
        registerVanillaBiome(Biomes.PLAINS, BOPClimates.WARM_TEMPERATE, 10);
        registerVanillaBiome(Biomes.JUNGLE, BOPClimates.TROPICAL, 15);
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
        registerBiomeToDictionary(alps, Type.OVERWORLD, Type.MOUNTAIN, Type.SNOWY, Type.COLD);
        registerBiomeToDictionary(alps_foothills, Type.OVERWORLD, Type.MOUNTAIN, Type.SNOWY, Type.FOREST, Type.SPARSE, Type.COLD);
        registerBiomeToDictionary(bayou, Type.OVERWORLD, Type.SWAMP, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(bog, Type.OVERWORLD, Type.CONIFEROUS, Type.WET);
        registerBiomeToDictionary(boreal_forest, Type.OVERWORLD, Type.FOREST, Type.CONIFEROUS, Type.HILLS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(brushland, Type.OVERWORLD, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(chaparral, Type.OVERWORLD, Type.PLAINS, Type.DRY, Type.HILLS);
        registerBiomeToDictionary(cherry_blossom_grove, Type.OVERWORLD, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(cold_desert, Type.OVERWORLD, Type.SNOWY, Type.DRY, Type.COLD);
        registerBiomeToDictionary(coniferous_forest, Type.OVERWORLD, Type.CONIFEROUS, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(dead_forest, Type.OVERWORLD, Type.FOREST, Type.DEAD, Type.COLD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(fir_clearing, Type.OVERWORLD, Type.CONIFEROUS, Type.FOREST, Type.SPARSE);
        registerBiomeToDictionary(floodplain, Type.OVERWORLD, Type.JUNGLE, Type.WATER, Type.HOT, Type.WET);
        registerBiomeToDictionary(flower_meadow, Type.OVERWORLD, Type.PLAINS);
        registerBiomeToDictionary(fungal_jungle, Type.OVERWORLD, Type.MAGICAL, Type.MUSHROOM, Type.JUNGLE, Type.HOT, Type.LUSH, Type.RARE);
        registerBiomeToDictionary(grassland, Type.OVERWORLD, Type.PLAINS, Type.HILLS, Type.WET);
        registerBiomeToDictionary(gravel_beach, Type.OVERWORLD, Type.BEACH);
        registerBiomeToDictionary(grove, Type.OVERWORLD, Type.FOREST, Type.PLAINS, Type.SPARSE);
        registerBiomeToDictionary(highland, Type.OVERWORLD, Type.MOUNTAIN, Type.HILLS, Type.WET);
        registerBiomeToDictionary(highland_moor, Type.OVERWORLD, Type.HILLS, Type.WET);
        registerBiomeToDictionary(lavender_field, Type.OVERWORLD, Type.PLAINS, Type.MAGICAL);
        registerBiomeToDictionary(lush_grassland, Type.OVERWORLD, Type.JUNGLE, Type.PLAINS, Type.HILLS, Type.WET, Type.HOT, Type.LUSH);
        registerBiomeToDictionary(lush_swamp, Type.OVERWORLD, Type.SWAMP, Type.LUSH, Type.HOT, Type.WET);
        registerBiomeToDictionary(mangrove, Type.OVERWORLD, Type.WATER, Type.WET, Type.HOT, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(maple_woods, Type.OVERWORLD, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(marsh, Type.OVERWORLD, Type.WET);
        registerBiomeToDictionary(meadow, Type.OVERWORLD, Type.PLAINS, Type.FOREST);
        registerBiomeToDictionary(mire, Type.OVERWORLD, Type.SWAMP, Type.DEAD, Type.WET);
        registerBiomeToDictionary(muskeg, Type.OVERWORLD, Type.FOREST, Type.SWAMP, Type.COLD, Type.SNOWY, Type.SPARSE, Type.DEAD, Type.WET);
        registerBiomeToDictionary(mystic_grove, Type.OVERWORLD, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(oasis, Type.OVERWORLD, Type.SANDY, Type.LUSH, Type.JUNGLE, Type.HOT, Type.SPARSE);
        registerBiomeToDictionary(ominous_woods, Type.OVERWORLD, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.DEAD, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(orchard, Type.OVERWORLD, Type.PLAINS);
        registerBiomeToDictionary(origin_hills, Type.OVERWORLD, Type.RARE);
        registerBiomeToDictionary(outback, Type.OVERWORLD, Type.SANDY, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(overgrown_cliffs, Type.OVERWORLD, Type.MOUNTAIN, Type.HILLS, Type.LUSH, Type.JUNGLE, Type.DENSE, Type.HOT);
        registerBiomeToDictionary(pasture, Type.OVERWORLD, Type.PLAINS, Type.DRY);
        registerBiomeToDictionary(poppy_field, Type.OVERWORLD, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(prairie, Type.OVERWORLD, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(pumpkin_patch, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(rainbow_valley, Type.OVERWORLD, Type.FOREST, Type.LUSH, Type.DENSE, Type.MAGICAL, Type.RARE);
        registerBiomeToDictionary(rainforest, Type.OVERWORLD, Type.JUNGLE, Type.FOREST, Type.HOT, Type.LUSH, Type.HILLS, Type.WET, Type.DENSE);
        registerBiomeToDictionary(redwood_forest, Type.OVERWORLD, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(redwood_forest_edge, Type.OVERWORLD, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(scrubland, Type.OVERWORLD, Type.SAVANNA, Type.HOT, Type.SPARSE, Type.DRY);
        registerBiomeToDictionary(seasonal_forest, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(shield, Type.OVERWORLD, Type.FOREST, Type.COLD, Type.WET);
        registerBiomeToDictionary(shrubland, Type.OVERWORLD, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(silkglade, Type.OVERWORLD, Type.FOREST, Type.DEAD, Type.SPOOKY, Type.DRY);
        registerBiomeToDictionary(snowy_coniferous_forest, Type.OVERWORLD, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(snowy_fir_clearing, Type.OVERWORLD, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(snowy_forest, Type.OVERWORLD, Type.SNOWY, Type.FOREST, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(steppe, Type.OVERWORLD, Type.PLAINS, Type.HILLS, Type.DRY);
        registerBiomeToDictionary(temperate_rainforest, Type.OVERWORLD, Type.FOREST, Type.LUSH, Type.WET);
        registerBiomeToDictionary(temperate_rainforest_hills, Type.OVERWORLD, Type.FOREST, Type.HILLS, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(tropical_rainforest, Type.OVERWORLD, Type.JUNGLE, Type.LUSH, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(tropic_beach, Type.OVERWORLD, Type.BEACH, Type.HOT);
        registerBiomeToDictionary(tropics, Type.OVERWORLD, Type.JUNGLE, Type.LUSH, Type.HOT);
        registerBiomeToDictionary(tundra, Type.OVERWORLD, Type.COLD, Type.WASTELAND, Type.DEAD, Type.SPARSE);
        registerBiomeToDictionary(volcano, Type.OVERWORLD, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(volcano_edge, Type.OVERWORLD, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(wasteland, Type.OVERWORLD, Type.WASTELAND, Type.DEAD, Type.DRY, Type.SPARSE, Type.HOT);
        registerBiomeToDictionary(wetland, Type.OVERWORLD, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(woodland, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(xeric_shrubland, Type.OVERWORLD, Type.SANDY, Type.SAVANNA, Type.LUSH, Type.HOT, Type.DRY, Type.SPARSE);

        //Nether Biomes
        registerBiomeToDictionary(crystalline_chasm, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(undergrowth, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(visceral_heap, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(withered_abyss, Type.NETHER, Type.HOT);
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(alps, IVillagerType.SNOW);
        registerVillagerType(alps_foothills, IVillagerType.SNOW);
        registerVillagerType(bayou, IVillagerType.SWAMP);
        registerVillagerType(bog, IVillagerType.SWAMP);
        registerVillagerType(boreal_forest, IVillagerType.TAIGA);
        registerVillagerType(brushland, IVillagerType.SAVANNA);
        registerVillagerType(chaparral, IVillagerType.PLAINS);
        registerVillagerType(cherry_blossom_grove, IVillagerType.PLAINS);
        registerVillagerType(cold_desert, IVillagerType.SNOW);
        registerVillagerType(coniferous_forest, IVillagerType.TAIGA);
        registerVillagerType(dead_forest, IVillagerType.TAIGA);
        registerVillagerType(fir_clearing, IVillagerType.TAIGA);
        registerVillagerType(floodplain, IVillagerType.JUNGLE);
        registerVillagerType(flower_meadow, IVillagerType.TAIGA);
        registerVillagerType(fungal_jungle, IVillagerType.JUNGLE);
        registerVillagerType(grassland, IVillagerType.PLAINS);
        registerVillagerType(gravel_beach, IVillagerType.PLAINS);
        registerVillagerType(grove, IVillagerType.PLAINS);
        registerVillagerType(highland, IVillagerType.PLAINS);
        registerVillagerType(highland_moor, IVillagerType.PLAINS);
        registerVillagerType(lavender_field, IVillagerType.PLAINS);
        registerVillagerType(lush_grassland, IVillagerType.JUNGLE);
        registerVillagerType(lush_swamp, IVillagerType.JUNGLE);
        registerVillagerType(mangrove, IVillagerType.SWAMP);
        registerVillagerType(maple_woods, IVillagerType.TAIGA);
        registerVillagerType(marsh, IVillagerType.SWAMP);
        registerVillagerType(meadow, IVillagerType.TAIGA);
        registerVillagerType(mire, IVillagerType.SWAMP);
        registerVillagerType(muskeg, IVillagerType.SNOW);
        registerVillagerType(mystic_grove, IVillagerType.PLAINS);
        registerVillagerType(oasis, IVillagerType.DESERT);
        registerVillagerType(ominous_woods, IVillagerType.SWAMP);
        registerVillagerType(orchard, IVillagerType.PLAINS);
        registerVillagerType(origin_hills, IVillagerType.PLAINS);
        registerVillagerType(outback, IVillagerType.SAVANNA);
        registerVillagerType(overgrown_cliffs, IVillagerType.JUNGLE);
        registerVillagerType(pasture, IVillagerType.PLAINS);
        registerVillagerType(poppy_field, IVillagerType.PLAINS);
        registerVillagerType(prairie, IVillagerType.PLAINS);
        registerVillagerType(pumpkin_patch, IVillagerType.PLAINS);
        registerVillagerType(rainbow_valley, IVillagerType.PLAINS);
        registerVillagerType(rainforest, IVillagerType.JUNGLE);
        registerVillagerType(redwood_forest, IVillagerType.PLAINS);
        registerVillagerType(redwood_forest_edge, IVillagerType.PLAINS);
        registerVillagerType(scrubland, IVillagerType.SAVANNA);
        registerVillagerType(seasonal_forest, IVillagerType.PLAINS);
        registerVillagerType(shield, IVillagerType.TAIGA);
        registerVillagerType(shrubland, IVillagerType.PLAINS);
        registerVillagerType(silkglade, IVillagerType.SWAMP);
        registerVillagerType(snowy_coniferous_forest, IVillagerType.SNOW);
        registerVillagerType(snowy_fir_clearing, IVillagerType.SNOW);
        registerVillagerType(snowy_forest, IVillagerType.SNOW);
        registerVillagerType(steppe, IVillagerType.PLAINS);
        registerVillagerType(temperate_rainforest, IVillagerType.PLAINS);
        registerVillagerType(temperate_rainforest_hills, IVillagerType.PLAINS);
        registerVillagerType(tropical_rainforest, IVillagerType.JUNGLE);
        registerVillagerType(tropic_beach, IVillagerType.JUNGLE);
        registerVillagerType(tropics, IVillagerType.JUNGLE);
        registerVillagerType(tundra, IVillagerType.TAIGA);
        registerVillagerType(volcano, IVillagerType.PLAINS);
        registerVillagerType(volcano_edge, IVillagerType.PLAINS);
        registerVillagerType(wasteland, IVillagerType.DESERT);
        registerVillagerType(wetland, IVillagerType.SWAMP);
        registerVillagerType(woodland, IVillagerType.PLAINS);
        registerVillagerType(xeric_shrubland, IVillagerType.DESERT);
    }
    
    private static void registerBiomeToDictionary(Optional<Biome> biome, Type...types)
    {
        if (biome.isPresent())
        {
            BiomeDictionary.addTypes(biome.get(), types);
        }
    }

    private static void registerVillagerType(Optional<Biome> biome, IVillagerType type)
    {
        if (biome.isPresent())
        {
            IVillagerType.BY_BIOME.put(biome.get(), type);
        }
    }

    /*
     * Biome registration helpers
     */

    public static void registerBiome(BiomeBOP biome, String name)
    {
        BiomeRegistry.deferStandardRegistration(biome, name);
    }

    public static void registerTechnicalBiome(BiomeBOP biome, String name)
    {
        BiomeRegistry.deferTechnicalBiomeRegistration(biome, name);
    }

    public static void registerSubBiome(Biome parent, Optional<Biome> child, float rarity, int weight)
    {
        registerSubBiome(Optional.of(parent), child, rarity, weight);
    }
    
    public static void registerSubBiome(Optional<Biome> parent, Optional<Biome> child, float rarity, int weight)
    {
    	if (!parent.isPresent())
            return;

        if (!child.isPresent())
            return;

        BiomeRegistry.deferSubBiomeRegistration(parent.get(), child.get(), weight, rarity);
    }

    public static void registerIslandBiome(Biome biome, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferIslandBiomeRegistration(biome, climate, weight);
    }

    public static void registerIslandBiome(Optional<Biome> biome, BOPClimates climate, int weight)
    {
        if (!biome.isPresent())
            return;

        registerIslandBiome(biome.get(), climate, weight);
    }

    private static void registerVanillaBiome(Biome biome, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferVanillaBiomeRegistration(biome, climate, weight);
    }

    public static class WeightedSubBiome
    {
        public final Biome biome;
        public final float rarity;
        public final int weight;

        public WeightedSubBiome(Biome biome, float rarity, int weight)
        {
            this.biome = biome;
            this.rarity = rarity;
            this.weight = weight;
        }
    }
}
