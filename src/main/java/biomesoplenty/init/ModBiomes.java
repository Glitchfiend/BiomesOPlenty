/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPNetherBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomes;
import biomesoplenty.common.worldgen.BOPNetherRegionCommon;
import biomesoplenty.common.worldgen.BOPNetherRegionRare;
import biomesoplenty.common.worldgen.BOPOverworldRegionCommon;
import biomesoplenty.common.worldgen.BOPOverworldRegionRare;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.Regions;

import java.util.function.Supplier;

public class ModBiomes
{
    public static void setup()
    {
        // Register our biomes
        registerBiomes();
        registerVillagerTypes();

        // Register our regions
        Regions.register(new BOPOverworldRegionCommon(ModConfig.GenerationConfig.bopOverworldRegionWeight.get()));
        Regions.register(new BOPOverworldRegionRare(ModConfig.GenerationConfig.bopOverworldRareRegionWeight.get()));
        Regions.register(new BOPNetherRegionCommon(ModConfig.GenerationConfig.bopNetherRegionWeight.get()));
        Regions.register(new BOPNetherRegionRare(ModConfig.GenerationConfig.bopNetherRareRegionWeight.get()));
    }

    public static void registerBiomes()
    {
        registerBiome(BOPBiomes.BAMBOO_GROVE, () -> BOPOverworldBiomes.bambooGrove());
        registerBiome(BOPBiomes.BAYOU, () -> BOPOverworldBiomes.bayou());
        registerBiome(BOPBiomes.BOG, () -> BOPOverworldBiomes.bog());
        registerBiome(BOPBiomes.BOREAL_FOREST, () -> BOPOverworldBiomes.borealForest());
        registerBiome(BOPBiomes.CHERRY_BLOSSOM_GROVE, () -> BOPOverworldBiomes.cherryBlossomGrove());
        registerBiome(BOPBiomes.CLOVER_PATCH, () -> BOPOverworldBiomes.cloverPatch());
        registerBiome(BOPBiomes.COLD_DESERT, () -> BOPOverworldBiomes.coldDesert());
        registerBiome(BOPBiomes.CONIFEROUS_FOREST, () -> BOPOverworldBiomes.coniferousForest(false));
        registerBiome(BOPBiomes.CRAG, () -> BOPOverworldBiomes.crag());
        registerBiome(BOPBiomes.DEAD_FOREST, () -> BOPOverworldBiomes.deadForest());
        registerBiome(BOPBiomes.DRYLAND, () -> BOPOverworldBiomes.dryland());
        registerBiome(BOPBiomes.DUNE_BEACH, () -> BOPOverworldBiomes.duneBeach());
        registerBiome(BOPBiomes.FIELD, () -> BOPOverworldBiomes.field(false));
        registerBiome(BOPBiomes.FIR_CLEARING, () -> BOPOverworldBiomes.firClearing(false));
        registerBiome(BOPBiomes.FLOODPLAIN, () -> BOPOverworldBiomes.floodplain());
        registerBiome(BOPBiomes.FORESTED_FIELD, () -> BOPOverworldBiomes.field(true));
        registerBiome(BOPBiomes.FUNGAL_JUNGLE, () -> BOPOverworldBiomes.fungalJungle());
        registerBiome(BOPBiomes.GRASSLAND, () -> BOPOverworldBiomes.grassland());
        registerBiome(BOPBiomes.HIGHLAND, () -> BOPOverworldBiomes.highland(false));
        registerBiome(BOPBiomes.HIGHLAND_MOOR, () -> BOPOverworldBiomes.highland(true));
        registerBiome(BOPBiomes.JADE_CLIFFS, () -> BOPOverworldBiomes.jadeCliffs());
        registerBiome(BOPBiomes.LAVENDER_FIELD, () -> BOPOverworldBiomes.lavenderField(false));
        registerBiome(BOPBiomes.LAVENDER_FOREST, () -> BOPOverworldBiomes.lavenderField(true));
        registerBiome(BOPBiomes.LUSH_DESERT, () -> BOPOverworldBiomes.lushDesert());
        registerBiome(BOPBiomes.LUSH_SAVANNA, () -> BOPOverworldBiomes.lushSavanna());
        registerBiome(BOPBiomes.MAPLE_WOODS, () -> BOPOverworldBiomes.mapleWoods(false));
        registerBiome(BOPBiomes.MARSH, () -> BOPOverworldBiomes.marsh());
        registerBiome(BOPBiomes.MEDITERRANEAN_FOREST, () -> BOPOverworldBiomes.mediterraneanForest());
        registerBiome(BOPBiomes.MUSKEG, () -> BOPOverworldBiomes.muskeg());
        registerBiome(BOPBiomes.MYSTIC_GROVE, () -> BOPOverworldBiomes.mysticGrove());
        registerBiome(BOPBiomes.OLD_GROWTH_DEAD_FOREST, () -> BOPOverworldBiomes.oldGrowthDeadForest());
        registerBiome(BOPBiomes.OLD_GROWTH_WOODLAND, () -> BOPOverworldBiomes.woodland(true));
        registerBiome(BOPBiomes.OMINOUS_WOODS, () -> BOPOverworldBiomes.ominousWoods());
        registerBiome(BOPBiomes.ORCHARD, () -> BOPOverworldBiomes.orchard());
        registerBiome(BOPBiomes.ORIGIN_VALLEY, () -> BOPOverworldBiomes.originValley());
        registerBiome(BOPBiomes.PASTURE, () -> BOPOverworldBiomes.pasture());
        registerBiome(BOPBiomes.PRAIRIE, () -> BOPOverworldBiomes.prairie());
        registerBiome(BOPBiomes.PUMPKIN_PATCH, () -> BOPOverworldBiomes.pumpkinPatch());
        registerBiome(BOPBiomes.RAINBOW_HILLS, () -> BOPOverworldBiomes.rainbowHills());
        registerBiome(BOPBiomes.RAINFOREST, () -> BOPOverworldBiomes.rainforest());
        registerBiome(BOPBiomes.REDWOOD_FOREST, () -> BOPOverworldBiomes.redwoodForest());
        registerBiome(BOPBiomes.ROCKY_RAINFOREST, () -> BOPOverworldBiomes.rockyRainforest());
        registerBiome(BOPBiomes.ROCKY_SHRUBLAND, () -> BOPOverworldBiomes.rockyShrubland());
        registerBiome(BOPBiomes.SCRUBLAND, () -> BOPOverworldBiomes.scrubland(false));
        registerBiome(BOPBiomes.SEASONAL_FOREST, () -> BOPOverworldBiomes.seasonalForest());
        registerBiome(BOPBiomes.SHRUBLAND, () -> BOPOverworldBiomes.shrubland());
        registerBiome(BOPBiomes.SNOWY_CONIFEROUS_FOREST, () -> BOPOverworldBiomes.coniferousForest(true));
        registerBiome(BOPBiomes.SNOWY_FIR_CLEARING, () -> BOPOverworldBiomes.firClearing(true));
        registerBiome(BOPBiomes.SNOWY_MAPLE_WOODS, () -> BOPOverworldBiomes.mapleWoods(true));
        registerBiome(BOPBiomes.TROPICS, () -> BOPOverworldBiomes.tropics());
        registerBiome(BOPBiomes.TUNDRA, () -> BOPOverworldBiomes.tundra());
        registerBiome(BOPBiomes.VOLCANIC_PLAINS, () -> BOPOverworldBiomes.volcanicPlains());
        registerBiome(BOPBiomes.VOLCANO, () -> BOPOverworldBiomes.volcano());
        registerBiome(BOPBiomes.WASTELAND, () -> BOPOverworldBiomes.wasteland(false));
        registerBiome(BOPBiomes.WETLAND, () -> BOPOverworldBiomes.wetland());
        registerBiome(BOPBiomes.WOODED_SCRUBLAND, () -> BOPOverworldBiomes.scrubland(true));
        registerBiome(BOPBiomes.WOODED_WASTELAND, () -> BOPOverworldBiomes.wasteland(true));
        registerBiome(BOPBiomes.WOODLAND, () -> BOPOverworldBiomes.woodland(false));

        // Cave biomes
        registerBiome(BOPBiomes.GLOWING_GROTTO, () -> BOPOverworldBiomes.glowingGrotto());
        registerBiome(BOPBiomes.SPIDER_NEST, () -> BOPOverworldBiomes.spiderNest());

        // Nether biomes
        registerBiome(BOPBiomes.CRYSTALLINE_CHASM, () -> BOPNetherBiomes.crystallineChasm());
        registerBiome(BOPBiomes.ERUPTING_INFERNO, () -> BOPNetherBiomes.eruptingInferno());
        registerBiome(BOPBiomes.UNDERGROWTH, () -> BOPNetherBiomes.undergrowth());
        registerBiome(BOPBiomes.VISCERAL_HEAP, () -> BOPNetherBiomes.visceralHeap());
        registerBiome(BOPBiomes.WITHERED_ABYSS, () -> BOPNetherBiomes.witheredAbyss());
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(BOPBiomes.BAMBOO_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.BAYOU, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.BOG, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.BOREAL_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.CHERRY_BLOSSOM_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.CLOVER_PATCH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.COLD_DESERT, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.CONIFEROUS_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.CRAG, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.DRYLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.DUNE_BEACH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.FIR_CLEARING, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FLOODPLAIN, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.FORESTED_FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FUNGAL_JUNGLE, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.GRASSLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HIGHLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HIGHLAND_MOOR, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.JADE_CLIFFS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LAVENDER_FIELD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LAVENDER_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LUSH_DESERT, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.LUSH_SAVANNA, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.MARSH, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.MEDITERRANEAN_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.MUSKEG, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.MYSTIC_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OLD_GROWTH_DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.OLD_GROWTH_WOODLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OMINOUS_WOODS, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.ORCHARD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ORIGIN_VALLEY, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PASTURE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PRAIRIE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PUMPKIN_PATCH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.RAINBOW_HILLS, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.RAINFOREST, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.ROCKY_RAINFOREST, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.REDWOOD_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ROCKY_SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SCRUBLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.SEASONAL_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SNOWY_CONIFEROUS_FOREST, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.SNOWY_FIR_CLEARING, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.SNOWY_MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.TROPICS, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.TUNDRA, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.VOLCANIC_PLAINS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.VOLCANO, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.WASTELAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WETLAND, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.WOODED_SCRUBLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.WOODED_WASTELAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WOODLAND, VillagerType.PLAINS);
    }

    public static void registerBiome(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            BiomesOPlenty.BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
        }
    }

    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            VillagerType.BY_BIOME.put(key, type);
        }
    }
}
