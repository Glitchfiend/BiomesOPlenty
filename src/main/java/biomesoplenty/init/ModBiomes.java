/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
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
import biomesoplenty.common.world.BOPBiomeGeneratorTypeScreen;
import biomesoplenty.common.world.BOPBiomeProvider;
import biomesoplenty.common.world.BOPNetherBiomeProvider;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.BiomeGeneratorTypeScreens;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;
import java.util.Map;

import static biomesoplenty.api.biome.BOPBiomes.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static BOPBiomeGeneratorTypeScreen biomeGeneratorTypeScreenBOP;

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();
    public static List<Integer> islandBiomeIds = Lists.newArrayList();
    public static Map<RegistryKey<Biome>, BiomeMetadata> biomeMetadata = Maps.newHashMap();

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
        registerTechnicalBiome(new VolcanicPlainsBiome(), "volcanic_plains");
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

        registerVillagerTypes();
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(alps, VillagerType.SNOW);
        registerVillagerType(alps_foothills, VillagerType.SNOW);
        registerVillagerType(bayou, VillagerType.SWAMP);
        registerVillagerType(bog, VillagerType.SWAMP);
        registerVillagerType(boreal_forest, VillagerType.TAIGA);
        registerVillagerType(brushland, VillagerType.SAVANNA);
        registerVillagerType(chaparral, VillagerType.PLAINS);
        registerVillagerType(cherry_blossom_grove, VillagerType.PLAINS);
        registerVillagerType(cold_desert, VillagerType.SNOW);
        registerVillagerType(coniferous_forest, VillagerType.TAIGA);
        registerVillagerType(dead_forest, VillagerType.TAIGA);
        registerVillagerType(fir_clearing, VillagerType.TAIGA);
        registerVillagerType(floodplain, VillagerType.JUNGLE);
        registerVillagerType(flower_meadow, VillagerType.TAIGA);
        registerVillagerType(fungal_jungle, VillagerType.JUNGLE);
        registerVillagerType(grassland, VillagerType.PLAINS);
        registerVillagerType(gravel_beach, VillagerType.PLAINS);
        registerVillagerType(grove, VillagerType.PLAINS);
        registerVillagerType(highland, VillagerType.PLAINS);
        registerVillagerType(highland_moor, VillagerType.PLAINS);
        registerVillagerType(lavender_field, VillagerType.PLAINS);
        registerVillagerType(lush_grassland, VillagerType.JUNGLE);
        registerVillagerType(lush_swamp, VillagerType.JUNGLE);
        registerVillagerType(mangrove, VillagerType.SWAMP);
        registerVillagerType(maple_woods, VillagerType.TAIGA);
        registerVillagerType(marsh, VillagerType.SWAMP);
        registerVillagerType(meadow, VillagerType.TAIGA);
        registerVillagerType(mire, VillagerType.SWAMP);
        registerVillagerType(muskeg, VillagerType.SNOW);
        registerVillagerType(mystic_grove, VillagerType.PLAINS);
        registerVillagerType(oasis, VillagerType.DESERT);
        registerVillagerType(ominous_woods, VillagerType.SWAMP);
        registerVillagerType(orchard, VillagerType.PLAINS);
        registerVillagerType(origin_hills, VillagerType.PLAINS);
        registerVillagerType(outback, VillagerType.SAVANNA);
        registerVillagerType(overgrown_cliffs, VillagerType.JUNGLE);
        registerVillagerType(pasture, VillagerType.PLAINS);
        registerVillagerType(poppy_field, VillagerType.PLAINS);
        registerVillagerType(prairie, VillagerType.PLAINS);
        registerVillagerType(pumpkin_patch, VillagerType.PLAINS);
        registerVillagerType(rainbow_valley, VillagerType.PLAINS);
        registerVillagerType(rainforest, VillagerType.JUNGLE);
        registerVillagerType(redwood_forest, VillagerType.PLAINS);
        registerVillagerType(redwood_forest_edge, VillagerType.PLAINS);
        registerVillagerType(scrubland, VillagerType.SAVANNA);
        registerVillagerType(seasonal_forest, VillagerType.PLAINS);
        registerVillagerType(shield, VillagerType.TAIGA);
        registerVillagerType(shrubland, VillagerType.PLAINS);
        registerVillagerType(silkglade, VillagerType.SWAMP);
        registerVillagerType(snowy_coniferous_forest, VillagerType.SNOW);
        registerVillagerType(snowy_fir_clearing, VillagerType.SNOW);
        registerVillagerType(snowy_forest, VillagerType.SNOW);
        registerVillagerType(steppe, VillagerType.PLAINS);
        registerVillagerType(temperate_rainforest, VillagerType.PLAINS);
        registerVillagerType(temperate_rainforest_hills, VillagerType.PLAINS);
        registerVillagerType(tropical_rainforest, VillagerType.JUNGLE);
        registerVillagerType(tropic_beach, VillagerType.JUNGLE);
        registerVillagerType(tropics, VillagerType.JUNGLE);
        registerVillagerType(tundra, VillagerType.TAIGA);
        registerVillagerType(volcanic_plains, VillagerType.PLAINS);
        registerVillagerType(volcano, VillagerType.PLAINS);
        registerVillagerType(wasteland, VillagerType.DESERT);
        registerVillagerType(wetland, VillagerType.SWAMP);
        registerVillagerType(woodland, VillagerType.PLAINS);
        registerVillagerType(xeric_shrubland, VillagerType.DESERT);
    }

    private static void registerVillagerType(RegistryKey<Biome> key, VillagerType type)
    {
        Biome biome = BiomeUtil.getBiome(key);

        if (biome != null)
        {
            // TODO
            //VillagerType.BY_BIOME.put(biome, type);
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

    public static void registerSubBiome(RegistryKey<Biome> parent, RegistryKey<Biome> child, float rarity, int weight)
    {
        BiomeRegistry.deferSubBiomeRegistration(parent, child, weight, rarity);
    }

    public static void registerIslandBiome(RegistryKey<Biome> key, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferIslandBiomeRegistration(key, climate, weight);
    }

    private static void registerVanillaBiome(RegistryKey<Biome> key, BOPClimates climate, int weight)
    {
        BiomeRegistry.deferVanillaBiomeRegistration(key, climate, weight);
    }

    public static class WeightedSubBiome
    {
        public final RegistryKey<Biome> biome;
        public final float rarity;
        public final int weight;

        public WeightedSubBiome(RegistryKey<Biome> biome, float rarity, int weight)
        {
            this.biome = biome;
            this.rarity = rarity;
            this.weight = weight;
        }
    }
}
