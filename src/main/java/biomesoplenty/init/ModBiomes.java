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
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.level.ColorResolver;
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

            ColorResolver grassColorResolver = BiomeColors.GRASS_COLOR_RESOLVER;
            ColorResolver foliageColorResolver = BiomeColors.FOLIAGE_COLOR_RESOLVER;
            ColorResolver waterColorResolver = BiomeColors.WATER_COLOR_RESOLVER;

            BiomeColors.GRASS_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                RegistryKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getGrassColorFunction() != null)
                {
                    return meta.getGrassColorFunction().apply(posX, posZ);
                }

                return grassColorResolver.getColor(biome, posX, posZ);
            };

            BiomeColors.FOLIAGE_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                RegistryKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getFoliageColorFunction() != null)
                {
                    return meta.getFoliageColorFunction().apply(posX, posZ);
                }

                return foliageColorResolver.getColor(biome, posX, posZ);
            };

            BiomeColors.WATER_COLOR_RESOLVER = (biome, posX, posZ) ->
            {
                RegistryKey<Biome> key = BiomeUtil.getClientKey(biome);
                BiomeMetadata meta = BiomeUtil.getMetadata(key);

                if (meta != null && meta.getWaterColorFunction() != null)
                {
                    return meta.getWaterColorFunction().apply(posX, posZ);
                }

                return waterColorResolver.getColor(biome, posX, posZ);
            };
        }

        // Register biome providers
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_overworld", BOPBiomeProvider.CODEC.stable());
        Registry.register(Registry.BIOME_SOURCE, "biomesoplenty_nether", BOPNetherBiomeProvider.CODEC.stable());
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
        registerBiome(new BayouMangrove(), "bayou_mangrove");

        //Overworld Biomes
    	registerBiome(new AlpsBiome(), "alps");
        registerBiome(new BambooGroveBiome(), "bamboo_grove");
    	registerBiome(new BayouBiome(), "bayou");
    	registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
        registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        registerBiome(new ConiferousLakesBiome(), "coniferous_lakes");
        registerBiome(new DeadForestBiome(), "dead_forest");
        registerBiome(new DeadSwampBiome(), "dead_swamp");
        registerBiome(new DeepBayouBiome(), "deep_bayou");
        registerBiome(new DrylandBiome(), "dryland");
        registerBiome(new DrySteppeBiome(), "dry_steppe");
        registerBiome(new FirClearingBiome(), "fir_clearing");
        registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        registerBiome(new FungalJungleBiome(), "fungal_jungle");
        registerBiome(new GoldenPrairieBiome(), "golden_prairie");
        registerBiome(new GroveBiome(), "grove");
        registerBiome(new GroveLakesBiome(), "grove_lakes");
        registerBiome(new HighlandBiome(), "highland");
        registerBiome(new HighlandCragBiome(), "highland_crag");
        registerBiome(new HighlandMoorBiome(), "highland_moor");
        registerBiome(new JadeCliffsBiome(), "jade_cliffs");
        registerBiome(new JadeGrasslandBiome(), "jade_grassland");
        registerBiome(new LavenderFieldBiome(), "lavender_field");
        registerBiome(new LavenderForestBiome(), "lavender_forest");
        registerBiome(new LushDesertBiome(), "lush_desert");
        registerBiome(new LushSavannaBiome(), "lush_savanna");
        registerBiome(new MeadowBiome(), "meadow");
        registerBiome(new MeadowForestBiome(), "meadow_forest");
        registerBiome(new MysticGroveBiome(), "mystic_grove");
        registerBiome(new MysticPlainsBiome(), "mystic_plains");
        registerBiome(new OminousWoodsBiome(), "ominous_woods");
        registerBiome(new OminousMireBiome(), "ominous_mire");
        registerBiome(new OriginValleyBiome(), "origin_valley");
        registerBiome(new OvergrownFungalJungleBiome(), "overgrown_fungal_jungle");
        registerBiome(new PrairieBiome(), "prairie");
        registerBiome(new RainbowHillsBiome(), "rainbow_hills");
        registerBiome(new RainforestBiome(), "rainforest");
        registerBiome(new RainforestClearingBiome(), "rainforest_clearing");
        registerBiome(new RainforestCliffsBiome(), "rainforest_cliffs");
        registerBiome(new RedwoodForestBiome(), "redwood_forest");
        registerBiome(new RedwoodHillsBiome(), "redwood_hills");
        registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        registerBiome(new SeasonalOrchardBiome(), "seasonal_orchard");
        registerBiome(new SeasonalPumpkinPatchBiome(), "seasonal_pumpkin_patch");
        registerBiome(new ShrublandBiome(), "shrubland");
        registerBiome(new ShrublandHillsBiome(), "shrubland_hills");
        registerBiome(new SilkgladeBiome(), "silkglade");
        registerBiome(new SilkgladeNestBiome(), "silkglade_nest");
        registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        registerBiome(new SnowyMapleForestBiome(), "snowy_maple_forest");
        registerBiome(new SnowyFirClearingBiome(), "snowy_fir_clearing");
        registerBiome(new TropicsBiome(), "tropics");
        registerBiome(new TundraBiome(), "tundra");
        registerBiome(new TundraBasinBiome(), "tundra_basin");
        registerBiome(new TundraBogBiome(), "tundra_bog");
        registerBiome(new VolcanoBiome(), "volcano");
        registerBiome(new WastelandBiome(), "wasteland");
        registerBiome(new WetlandBiome(), "wetland");
        registerBiome(new WetlandMarshBiome(), "wetland_marsh");
        registerBiome(new WoodedWastelandBiome(), "wooded_wasteland");

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

        registerSubBiome(cherry_blossom_grove, bamboo_grove, 0.8F, 100);

        registerSubBiome(coniferous_forest, fir_clearing, 0.75F, 100);
        registerSubBiome(coniferous_forest, coniferous_lakes, 1.0F, 100);

        registerSubBiome(dead_forest, dead_swamp, 0.75F, 100);

        registerSubBiome(dryland, dry_steppe, 1.0F, 100);

        registerSubBiome(fungal_jungle, overgrown_fungal_jungle, 0.8F, 100);

        registerSubBiome(grove, grove_lakes, 0.75F, 100);

        registerSubBiome(highland, highland_crag, 1.25F, 100);
        registerSubBiome(highland, highland_moor, 0.75F, 100);

        registerSubBiome(jade_cliffs, jade_grassland, 0.85F, 100);

        registerSubBiome(lavender_field, lavender_forest, 0.6F, 100);

        registerSubBiome(lush_desert, lush_savanna, 0.65F, 100);

        registerSubBiome(meadow, flower_meadow, 1.0F, 100);
        registerSubBiome(meadow, meadow_forest, 1.0F, 100);

        registerSubBiome(mystic_grove, mystic_plains, 0.75F, 100);

        registerSubBiome(ominous_woods, ominous_mire, 0.75F, 100);

        registerSubBiome(prairie, golden_prairie, 1.0F, 100);

        registerSubBiome(rainforest, rainforest_cliffs, 2.0F, 100);
        registerSubBiome(rainforest, rainforest_clearing, 2.0F, 100);

        registerSubBiome(redwood_forest, redwood_hills, 0.75F, 100);

        registerSubBiome(seasonal_forest, seasonal_orchard, 0.75F, 100);
        registerSubBiome(seasonal_forest, seasonal_pumpkin_patch, 0.75F, 100);

        registerSubBiome(shrubland, shrubland_hills, 0.75F, 100);

        registerSubBiome(silkglade, silkglade_nest, 0.45F, 100);

        registerSubBiome(snowy_coniferous_forest, snowy_fir_clearing, 0.75F, 100);
        registerSubBiome(snowy_coniferous_forest, snowy_maple_forest, 1.25F, 100);

        registerSubBiome(tundra, tundra_basin, 1.5F, 100);
        registerSubBiome(tundra, tundra_bog, 2.0F, 100);

        registerSubBiome(wasteland, wooded_wasteland, 0.85F, 100);

        registerSubBiome(wetland, wetland_marsh, 1.0F, 100);

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
        registerVanillaBiome(Biomes.FROZEN_OCEAN, BOPClimates.ICE_CAP, 3);
        registerVanillaBiome(Biomes.MOUNTAINS, BOPClimates.TUNDRA, 10);
        registerVanillaBiome(Biomes.SNOWY_TAIGA, BOPClimates.TUNDRA, 7);
        registerVanillaBiome(Biomes.TAIGA, BOPClimates.WET_BOREAL, 10);
        registerVanillaBiome(Biomes.GIANT_TREE_TAIGA, BOPClimates.DRY_BOREAL, 5);
        registerVanillaBiome(Biomes.DARK_FOREST, BOPClimates.WET_TEMPERATE, 5);
        registerVanillaBiome(Biomes.BIRCH_FOREST, BOPClimates.DRY_TEMPERATE, 5);
        registerVanillaBiome(Biomes.FOREST, BOPClimates.COOL_TEMPERATE, 10);
        registerVanillaBiome(Biomes.PLAINS, BOPClimates.WARM_TEMPERATE, 10);
        registerVanillaBiome(Biomes.SWAMP, BOPClimates.SUBTROPICAL, 7);
        registerVanillaBiome(Biomes.LUKEWARM_OCEAN, BOPClimates.SUBTROPICAL, 3);
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

        registerVillagerTypes();
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(alps, VillagerType.SNOW);
        registerVillagerType(alps_foothills, VillagerType.SNOW);
        registerVillagerType(bamboo_grove, VillagerType.PLAINS);
        registerVillagerType(bayou, VillagerType.SWAMP);
        registerVillagerType(bayou_mangrove, VillagerType.SWAMP);
        registerVillagerType(cherry_blossom_grove, VillagerType.PLAINS);
        registerVillagerType(coniferous_forest, VillagerType.TAIGA);
        registerVillagerType(coniferous_lakes, VillagerType.TAIGA);
        registerVillagerType(dead_forest, VillagerType.TAIGA);
        registerVillagerType(dead_swamp, VillagerType.TAIGA);
        registerVillagerType(deep_bayou, VillagerType.SWAMP);
        registerVillagerType(dryland, VillagerType.SAVANNA);
        registerVillagerType(dry_steppe, VillagerType.SAVANNA);
        registerVillagerType(fir_clearing, VillagerType.TAIGA);
        registerVillagerType(flower_meadow, VillagerType.TAIGA);
        registerVillagerType(fungal_jungle, VillagerType.JUNGLE);
        registerVillagerType(golden_prairie, VillagerType.PLAINS);
        registerVillagerType(gravel_beach, VillagerType.PLAINS);
        registerVillagerType(grove, VillagerType.PLAINS);
        registerVillagerType(grove_lakes, VillagerType.PLAINS);
        registerVillagerType(highland, VillagerType.PLAINS);
        registerVillagerType(highland_crag, VillagerType.PLAINS);
        registerVillagerType(highland_moor, VillagerType.PLAINS);
        registerVillagerType(jade_cliffs, VillagerType.PLAINS);
        registerVillagerType(lavender_field, VillagerType.PLAINS);
        registerVillagerType(lavender_forest, VillagerType.PLAINS);
        registerVillagerType(lush_desert, VillagerType.SAVANNA);
        registerVillagerType(lush_savanna, VillagerType.SAVANNA);
        registerVillagerType(meadow, VillagerType.TAIGA);
        registerVillagerType(meadow_forest, VillagerType.TAIGA);
        registerVillagerType(mystic_grove, VillagerType.PLAINS);
        registerVillagerType(mystic_plains, VillagerType.PLAINS);
        registerVillagerType(ominous_woods, VillagerType.SWAMP);
        registerVillagerType(ominous_mire, VillagerType.SWAMP);
        registerVillagerType(orchard, VillagerType.PLAINS);
        registerVillagerType(origin_valley, VillagerType.PLAINS);
        registerVillagerType(overgrown_fungal_jungle, VillagerType.JUNGLE);
        registerVillagerType(prairie, VillagerType.PLAINS);
        registerVillagerType(rainbow_hills, VillagerType.PLAINS);
        registerVillagerType(rainforest, VillagerType.JUNGLE);
        registerVillagerType(rainforest_clearing, VillagerType.JUNGLE);
        registerVillagerType(rainforest_cliffs, VillagerType.JUNGLE);
        registerVillagerType(redwood_forest, VillagerType.PLAINS);
        registerVillagerType(redwood_forest_edge, VillagerType.PLAINS);
        registerVillagerType(redwood_hills, VillagerType.PLAINS);
        registerVillagerType(seasonal_forest, VillagerType.PLAINS);
        registerVillagerType(seasonal_orchard, VillagerType.PLAINS);
        registerVillagerType(seasonal_pumpkin_patch, VillagerType.PLAINS);
        registerVillagerType(shrubland, VillagerType.PLAINS);
        registerVillagerType(shrubland_hills, VillagerType.PLAINS);
        registerVillagerType(silkglade, VillagerType.SWAMP);
        registerVillagerType(silkglade_nest, VillagerType.SWAMP);
        registerVillagerType(snowy_coniferous_forest, VillagerType.SNOW);
        registerVillagerType(snowy_fir_clearing, VillagerType.SNOW);
        registerVillagerType(snowy_maple_forest, VillagerType.SNOW);
        registerVillagerType(tropic_beach, VillagerType.JUNGLE);
        registerVillagerType(tropics, VillagerType.JUNGLE);
        registerVillagerType(tundra, VillagerType.TAIGA);
        registerVillagerType(tundra_basin, VillagerType.TAIGA);
        registerVillagerType(tundra_bog, VillagerType.TAIGA);
        registerVillagerType(volcanic_plains, VillagerType.PLAINS);
        registerVillagerType(volcano, VillagerType.PLAINS);
        registerVillagerType(wasteland, VillagerType.DESERT);
        registerVillagerType(wetland, VillagerType.SWAMP);
        registerVillagerType(wetland_marsh, VillagerType.SWAMP);
        registerVillagerType(wooded_wasteland, VillagerType.DESERT);
    }

    private static void registerVillagerType(RegistryKey<Biome> key, VillagerType type)
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
