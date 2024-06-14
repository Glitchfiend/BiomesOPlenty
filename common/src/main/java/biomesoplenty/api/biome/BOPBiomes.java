/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.biome;

import biomesoplenty.api.BOPAPI;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BOPBiomes
{
    private static List<ResourceKey<Biome>> overworldBiomes = Lists.newArrayList();
    private static List<ResourceKey<Biome>> allBiomes = Lists.newArrayList();

    public static final ResourceKey<Biome> ASPEN_GLADE = registerOverworld("aspen_glade");
    public static final ResourceKey<Biome> AURORAL_GARDEN = registerOverworld("auroral_garden");
    public static final ResourceKey<Biome> BAYOU = registerOverworld("bayou");
    public static final ResourceKey<Biome> BOG = registerOverworld("bog");
    public static final ResourceKey<Biome> COLD_DESERT = registerOverworld("cold_desert");
    public static final ResourceKey<Biome> CONIFEROUS_FOREST = registerOverworld("coniferous_forest");
    public static final ResourceKey<Biome> CRAG = registerOverworld("crag");
    public static final ResourceKey<Biome> CRYSTALLINE_CHASM = register("crystalline_chasm");
    public static final ResourceKey<Biome> DEAD_FOREST = registerOverworld("dead_forest");
    public static final ResourceKey<Biome> DRYLAND = registerOverworld("dryland");
    public static final ResourceKey<Biome> DUNE_BEACH = registerOverworld("dune_beach");
    public static final ResourceKey<Biome> END_WILDS = registerOverworld("end_wilds");
    public static final ResourceKey<Biome> END_REEF = registerOverworld("end_reef");
    public static final ResourceKey<Biome> END_CORRUPTION = registerOverworld("end_corruption");
    public static final ResourceKey<Biome> ERUPTING_INFERNO = register("erupting_inferno");
    public static final ResourceKey<Biome> FIELD = registerOverworld("field");
    public static final ResourceKey<Biome> FIR_CLEARING = registerOverworld("fir_clearing");
    public static final ResourceKey<Biome> FLOODPLAIN = registerOverworld("floodplain");
    public static final ResourceKey<Biome> FORESTED_FIELD = registerOverworld("forested_field");
    public static final ResourceKey<Biome> FUNGAL_JUNGLE = registerOverworld("fungal_jungle");
    public static final ResourceKey<Biome> GLOWING_GROTTO = register("glowing_grotto");
    public static final ResourceKey<Biome> GRASSLAND = registerOverworld("grassland");
    public static final ResourceKey<Biome> GRAVEL_BEACH = registerOverworld("gravel_beach");
    public static final ResourceKey<Biome> HIGHLAND = registerOverworld("highland");
    public static final ResourceKey<Biome> HOT_SPRINGS = registerOverworld("hot_springs");
    public static final ResourceKey<Biome> JACARANDA_GLADE = registerOverworld("jacaranda_glade");
    public static final ResourceKey<Biome> JADE_CLIFFS = registerOverworld("jade_cliffs");
    public static final ResourceKey<Biome> LAVENDER_FIELD = registerOverworld("lavender_field");
    public static final ResourceKey<Biome> LUSH_DESERT = registerOverworld("lush_desert");
    public static final ResourceKey<Biome> LUSH_SAVANNA = registerOverworld("lush_savanna");
    public static final ResourceKey<Biome> MAPLE_WOODS = registerOverworld("maple_woods");
    public static final ResourceKey<Biome> MARSH = registerOverworld("marsh");
    public static final ResourceKey<Biome> MEDITERRANEAN_FOREST = registerOverworld("mediterranean_forest");
    public static final ResourceKey<Biome> MOOR = registerOverworld("moor");
    public static final ResourceKey<Biome> MUSKEG = registerOverworld("muskeg");
    public static final ResourceKey<Biome> MYSTIC_GROVE = registerOverworld("mystic_grove");
    public static final ResourceKey<Biome> OLD_GROWTH_DEAD_FOREST = registerOverworld("old_growth_dead_forest");
    public static final ResourceKey<Biome> OLD_GROWTH_WOODLAND = registerOverworld("old_growth_woodland");
    public static final ResourceKey<Biome> OMINOUS_WOODS = registerOverworld("ominous_woods");
    public static final ResourceKey<Biome> ORCHARD = registerOverworld("orchard");
    public static final ResourceKey<Biome> ORIGIN_VALLEY = registerOverworld("origin_valley");
    public static final ResourceKey<Biome> OVERGROWN_GREENS = registerOverworld("overgrown_greens");
    public static final ResourceKey<Biome> PASTURE = registerOverworld("pasture");
    public static final ResourceKey<Biome> PRAIRIE = registerOverworld("prairie");
    public static final ResourceKey<Biome> PUMPKIN_PATCH = registerOverworld("pumpkin_patch");
    public static final ResourceKey<Biome> RAINFOREST = registerOverworld("rainforest");
    public static final ResourceKey<Biome> REDWOOD_FOREST = registerOverworld("redwood_forest");
    public static final ResourceKey<Biome> ROCKY_RAINFOREST = registerOverworld("rocky_rainforest");
    public static final ResourceKey<Biome> ROCKY_SHRUBLAND = registerOverworld("rocky_shrubland");
    public static final ResourceKey<Biome> SCRUBLAND = registerOverworld("scrubland");
    public static final ResourceKey<Biome> SEASONAL_FOREST = registerOverworld("seasonal_forest");
    public static final ResourceKey<Biome> SHRUBLAND = registerOverworld("shrubland");
    public static final ResourceKey<Biome> SNOWBLOSSOM_GROVE = registerOverworld("snowblossom_grove");
    public static final ResourceKey<Biome> SNOWY_CONIFEROUS_FOREST = registerOverworld("snowy_coniferous_forest");
    public static final ResourceKey<Biome> SNOWY_FIR_CLEARING = registerOverworld("snowy_fir_clearing");
    public static final ResourceKey<Biome> SNOWY_MAPLE_WOODS = registerOverworld("snowy_maple_woods");
    public static final ResourceKey<Biome> SPIDER_NEST = register("spider_nest");
    public static final ResourceKey<Biome> TROPICS = registerOverworld("tropics");
    public static final ResourceKey<Biome> TUNDRA = registerOverworld("tundra");
    public static final ResourceKey<Biome> UNDERGROWTH = register("undergrowth");
    public static final ResourceKey<Biome> VISCERAL_HEAP = register("visceral_heap");
    public static final ResourceKey<Biome> VOLCANO = registerOverworld("volcano");
    public static final ResourceKey<Biome> VOLCANIC_PLAINS = registerOverworld("volcanic_plains");
    public static final ResourceKey<Biome> WASTELAND = registerOverworld("wasteland");
    public static final ResourceKey<Biome> WASTELAND_STEPPE = registerOverworld("wasteland_steppe");
    public static final ResourceKey<Biome> WETLAND = registerOverworld("wetland");
    public static final ResourceKey<Biome> WINTRY_ORIGIN_VALLEY = registerOverworld("wintry_origin_valley");
    public static final ResourceKey<Biome> WITHERED_ABYSS = register("withered_abyss");
    public static final ResourceKey<Biome> WOODLAND = registerOverworld("woodland");

    public static List<ResourceKey<Biome>> getOverworldBiomes()
    {
        return ImmutableList.copyOf(overworldBiomes);
    }

    public static List<ResourceKey<Biome>> getAllBiomes()
    {
        return ImmutableList.copyOf(allBiomes);
    }

    private static ResourceKey<Biome> registerOverworld(String name)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(BOPAPI.MOD_ID, name));
        overworldBiomes.add(key);
        allBiomes.add(key);
        return key;
    }

    private static ResourceKey<Biome> register(String name)
    {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(BOPAPI.MOD_ID, name));
        allBiomes.add(key);
        return key;
    }
}
