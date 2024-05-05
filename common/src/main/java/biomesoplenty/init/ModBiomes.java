/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.biome.BOPEndBiomes;
import biomesoplenty.biome.BOPNetherBiomes;
import biomesoplenty.biome.BOPOverworldBiomes;
import biomesoplenty.worldgen.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class ModBiomes
{
    public static void setup()
    {
        // Register our biomes
        registerVillagerTypes();
    }

    public static void setupTerraBlender()
    {
        // Register our regions
        Regions.register(new BOPOverworldRegionPrimary(ModConfig.generation.bopPrimaryOverworldRegionWeight));
        Regions.register(new BOPOverworldRegionSecondary(ModConfig.generation.bopSecondaryOverworldRegionWeight));
        Regions.register(new BOPOverworldRegionRare(ModConfig.generation.bopOverworldRareRegionWeight));
        Regions.register(new BOPNetherRegionCommon(ModConfig.generation.bopNetherRegionWeight));
        Regions.register(new BOPNetherRegionRare(ModConfig.generation.bopNetherRareRegionWeight));

        // Register end biomes
        EndBiomeRegistry.registerHighlandsBiome(BOPBiomes.END_WILDS, 9);
        EndBiomeRegistry.registerHighlandsBiome(BOPBiomes.END_REEF, 6);
        EndBiomeRegistry.registerHighlandsBiome(BOPBiomes.END_CORRUPTION, 3);
    }

    public static void bootstrapBiomes(BootstrapContext<Biome> context)
    {
        HolderGetter<ConfiguredWorldCarver<?>> carverGetter = context.lookup(Registries.CONFIGURED_CARVER);
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        register(context, BOPBiomes.ASPEN_GLADE, BOPOverworldBiomes.aspenGlade(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.AURORAL_GARDEN, BOPOverworldBiomes.auroralGarden(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.BAYOU, BOPOverworldBiomes.bayou(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.BOG, BOPOverworldBiomes.bog(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.COLD_DESERT, BOPOverworldBiomes.coldDesert(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(placedFeatureGetter, carverGetter, false));
        register(context, BOPBiomes.CRAG, BOPOverworldBiomes.crag(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.DEAD_FOREST, BOPOverworldBiomes.deadForest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.DRYLAND, BOPOverworldBiomes.dryland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.DUNE_BEACH, BOPOverworldBiomes.duneBeach(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.FIELD, BOPOverworldBiomes.field(placedFeatureGetter, carverGetter, false));
        register(context, BOPBiomes.FIR_CLEARING, BOPOverworldBiomes.firClearing(placedFeatureGetter, carverGetter, false));
        register(context, BOPBiomes.FLOODPLAIN, BOPOverworldBiomes.floodplain(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.FORESTED_FIELD, BOPOverworldBiomes.field(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.FUNGAL_JUNGLE, BOPOverworldBiomes.fungalJungle(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.GRASSLAND, BOPOverworldBiomes.grassland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.GRAVEL_BEACH, BOPOverworldBiomes.gravelBeach(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.HIGHLAND, BOPOverworldBiomes.highland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.HOT_SPRINGS, BOPOverworldBiomes.hotSprings(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.JACARANDA_GLADE, BOPOverworldBiomes.jacarandaGlade(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.JADE_CLIFFS, BOPOverworldBiomes.jadeCliffs(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.LAVENDER_FIELD, BOPOverworldBiomes.lavenderField(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.LUSH_DESERT, BOPOverworldBiomes.lushDesert(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.LUSH_SAVANNA, BOPOverworldBiomes.lushSavanna(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.MAPLE_WOODS, BOPOverworldBiomes.mapleWoods(placedFeatureGetter, carverGetter, false));
        register(context, BOPBiomes.MARSH, BOPOverworldBiomes.marsh(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.MEDITERRANEAN_FOREST, BOPOverworldBiomes.mediterraneanForest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.MOOR, BOPOverworldBiomes.moor(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.MUSKEG, BOPOverworldBiomes.muskeg(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.MYSTIC_GROVE, BOPOverworldBiomes.mysticGrove(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.OLD_GROWTH_DEAD_FOREST, BOPOverworldBiomes.oldGrowthDeadForest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.OLD_GROWTH_WOODLAND, BOPOverworldBiomes.woodland(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.OMINOUS_WOODS, BOPOverworldBiomes.ominousWoods(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.ORCHARD, BOPOverworldBiomes.orchard(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.ORIGIN_VALLEY, BOPOverworldBiomes.originValley(placedFeatureGetter, carverGetter, false));
        register(context, BOPBiomes.OVERGROWN_GREENS, BOPOverworldBiomes.overgrownGreens(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.PASTURE, BOPOverworldBiomes.pasture(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.PRAIRIE, BOPOverworldBiomes.prairie(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.PUMPKIN_PATCH, BOPOverworldBiomes.pumpkinPatch(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.RAINFOREST, BOPOverworldBiomes.rainforest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.REDWOOD_FOREST, BOPOverworldBiomes.redwoodForest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.ROCKY_RAINFOREST, BOPOverworldBiomes.rockyRainforest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.ROCKY_SHRUBLAND, BOPOverworldBiomes.rockyShrubland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SCRUBLAND, BOPOverworldBiomes.scrubland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SEASONAL_FOREST, BOPOverworldBiomes.seasonalForest(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SHRUBLAND, BOPOverworldBiomes.shrubland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SNOWBLOSSOM_GROVE, BOPOverworldBiomes.snowblossomGrove(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.SNOWY_FIR_CLEARING, BOPOverworldBiomes.firClearing(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.SNOWY_MAPLE_WOODS, BOPOverworldBiomes.mapleWoods(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.TROPICS, BOPOverworldBiomes.tropics(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.TUNDRA, BOPOverworldBiomes.tundra(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.VOLCANIC_PLAINS, BOPOverworldBiomes.volcanicPlains(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.VOLCANO, BOPOverworldBiomes.volcano(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.WASTELAND, BOPOverworldBiomes.wasteland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.WASTELAND_STEPPE, BOPOverworldBiomes.wastelandSteppe(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.WETLAND, BOPOverworldBiomes.wetland(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.WINTRY_ORIGIN_VALLEY, BOPOverworldBiomes.originValley(placedFeatureGetter, carverGetter, true));
        register(context, BOPBiomes.WOODLAND, BOPOverworldBiomes.woodland(placedFeatureGetter, carverGetter, false));

        // Cave biomes
        register(context, BOPBiomes.GLOWING_GROTTO, BOPOverworldBiomes.glowingGrotto(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.SPIDER_NEST, BOPOverworldBiomes.spiderNest(placedFeatureGetter, carverGetter));

        // Nether biomes
        register(context, BOPBiomes.CRYSTALLINE_CHASM, BOPNetherBiomes.crystallineChasm(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.ERUPTING_INFERNO, BOPNetherBiomes.eruptingInferno(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.UNDERGROWTH, BOPNetherBiomes.undergrowth(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.VISCERAL_HEAP, BOPNetherBiomes.visceralHeap(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.WITHERED_ABYSS, BOPNetherBiomes.witheredAbyss(placedFeatureGetter, carverGetter));

        // End biomes
        register(context, BOPBiomes.END_WILDS, BOPEndBiomes.endWilds(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.END_REEF, BOPEndBiomes.endReef(placedFeatureGetter, carverGetter));
        register(context, BOPBiomes.END_CORRUPTION, BOPEndBiomes.endCorruption(placedFeatureGetter, carverGetter));
    }
    
    private static void registerVillagerTypes()
    {
        registerVillagerType(BOPBiomes.ASPEN_GLADE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.AURORAL_GARDEN, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.BAYOU, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.BOG, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.COLD_DESERT, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.CONIFEROUS_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.CRAG, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.DRYLAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.DUNE_BEACH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.FIR_CLEARING, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FLOODPLAIN, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.FORESTED_FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FUNGAL_JUNGLE, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.GRASSLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.GRAVEL_BEACH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HIGHLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HOT_SPRINGS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.JACARANDA_GLADE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.JADE_CLIFFS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LAVENDER_FIELD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LUSH_DESERT, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.LUSH_SAVANNA, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.MARSH, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.MEDITERRANEAN_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.MOOR, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.MUSKEG, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.MYSTIC_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OLD_GROWTH_DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.OLD_GROWTH_WOODLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OMINOUS_WOODS, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.ORCHARD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ORIGIN_VALLEY, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OVERGROWN_GREENS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PASTURE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PRAIRIE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PUMPKIN_PATCH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.RAINFOREST, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.ROCKY_RAINFOREST, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.REDWOOD_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ROCKY_SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SCRUBLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.SEASONAL_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SNOWBLOSSOM_GROVE, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.SNOWY_CONIFEROUS_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.SNOWY_FIR_CLEARING, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.SNOWY_MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.TROPICS, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.TUNDRA, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.VOLCANIC_PLAINS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.VOLCANO, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.WASTELAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WASTELAND_STEPPE, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WETLAND, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.WINTRY_ORIGIN_VALLEY, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.WOODLAND, VillagerType.PLAINS);
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome)
    {
        context.register(key, biome);
    }

    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            VillagerType.BY_BIOME.put(key, type);
        }
    }
}
