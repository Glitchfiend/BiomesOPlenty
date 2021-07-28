/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.biome;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BOPBiomes
{
    public static ResourceKey<Biome> alps = register("alps");
    public static ResourceKey<Biome> alps_foothills = register("alps_foothills");
    public static ResourceKey<Biome> bamboo_blossom_grove = register("bamboo_blossom_grove");
    public static ResourceKey<Biome> bayou = register("bayou");
    public static ResourceKey<Biome> bayou_mangrove = register("bayou_mangrove");
    public static ResourceKey<Biome> burnt_forest = register("burnt_forest");
    public static ResourceKey<Biome> cherry_blossom_grove = register("cherry_blossom_grove");
    public static ResourceKey<Biome> cold_desert = register("cold_desert");
    public static ResourceKey<Biome> coniferous_forest = register("coniferous_forest");
    public static ResourceKey<Biome> coniferous_lakes = register("coniferous_lakes");
    public static ResourceKey<Biome> dead_forest = register("dead_forest");
    public static ResourceKey<Biome> deep_bayou = register("deep_bayou");
    public static ResourceKey<Biome> dense_marsh = register("dense_marsh");
    public static ResourceKey<Biome> dense_woodland = register("dense_woodland");
    public static ResourceKey<Biome> dryland = register("dryland");
    public static ResourceKey<Biome> dry_boneyard = register("dry_boneyard");
    public static ResourceKey<Biome> dunes = register("dunes");
    public static ResourceKey<Biome> fir_clearing = register("fir_clearing");
    public static ResourceKey<Biome> flower_meadow = register("flower_meadow");
    public static ResourceKey<Biome> fungal_field = register("fungal_field");
    public static ResourceKey<Biome> fungal_jungle = register("fungal_jungle");
    public static ResourceKey<Biome> golden_prairie = register("golden_prairie");
    public static ResourceKey<Biome> grassland = register("grassland");
    public static ResourceKey<Biome> grassland_clover_patch = register("grassland_clover_patch");
    public static ResourceKey<Biome> gravel_beach = register("gravel_beach");
    public static ResourceKey<Biome> grove = register("grove");
    public static ResourceKey<Biome> grove_clearing = register("grove_clearing");
    public static ResourceKey<Biome> grove_lakes = register("grove_lakes");
    public static ResourceKey<Biome> highland = register("highland");
    public static ResourceKey<Biome> highland_crag = register("highland_crag");
    public static ResourceKey<Biome> highland_moor = register("highland_moor");
    public static ResourceKey<Biome> jade_cliffs = register("jade_cliffs");
    public static ResourceKey<Biome> lavender_field = register("lavender_field");
    public static ResourceKey<Biome> lavender_forest = register("lavender_forest");
    public static ResourceKey<Biome> lush_desert = register("lush_desert");
    public static ResourceKey<Biome> lush_savanna = register("lush_savanna");
    public static ResourceKey<Biome> marsh = register("marsh");
    public static ResourceKey<Biome> meadow = register("meadow");
    public static ResourceKey<Biome> meadow_forest = register("meadow_forest");
    public static ResourceKey<Biome> muskeg = register("muskeg");
    public static ResourceKey<Biome> mystic_grove = register("mystic_grove");
    public static ResourceKey<Biome> mystic_plains = register("mystic_plains");
    public static ResourceKey<Biome> ominous_woods = register("ominous_woods");
    public static ResourceKey<Biome> ominous_mire = register("ominous_mire");
    public static ResourceKey<Biome> orchard = register("orchard");
    public static ResourceKey<Biome> origin_valley = register("origin_valley");
    public static ResourceKey<Biome> prairie = register("prairie");
    public static ResourceKey<Biome> rainbow_hills = register("rainbow_hills");
    public static ResourceKey<Biome> rainforest = register("rainforest");
    public static ResourceKey<Biome> rainforest_cliffs = register("rainforest_cliffs");
    public static ResourceKey<Biome> rainforest_floodplain = register("rainforest_floodplain");
    public static ResourceKey<Biome> redwood_forest = register("redwood_forest");
    public static ResourceKey<Biome> redwood_forest_edge = register("redwood_forest_edge");
    public static ResourceKey<Biome> redwood_hills = register("redwood_hills");
    public static ResourceKey<Biome> scrubland = register("scrubland");
    public static ResourceKey<Biome> seasonal_forest = register("seasonal_forest");
    public static ResourceKey<Biome> seasonal_orchard = register("seasonal_orchard");
    public static ResourceKey<Biome> seasonal_pumpkin_patch = register("seasonal_pumpkin_patch");
    public static ResourceKey<Biome> shroomy_wetland = register("shroomy_wetland");
    public static ResourceKey<Biome> shrubland = register("shrubland");
    public static ResourceKey<Biome> shrubland_hills = register("shrubland_hills");
    public static ResourceKey<Biome> snowy_coniferous_forest = register("snowy_coniferous_forest");
    public static ResourceKey<Biome> snowy_fir_clearing = register("snowy_fir_clearing");
    public static ResourceKey<Biome> snowy_maple_forest = register("snowy_maple_forest");
    public static ResourceKey<Biome> tall_dead_forest = register("tall_dead_forest");
    public static ResourceKey<Biome> tropic_beach = register("tropic_beach");
    public static ResourceKey<Biome> tropics = register("tropics");
    public static ResourceKey<Biome> tundra = register("tundra");
    public static ResourceKey<Biome> tundra_basin = register("tundra_basin");
    public static ResourceKey<Biome> tundra_bog = register("tundra_bog");
    public static ResourceKey<Biome> volcanic_plains = register("volcanic_plains");
    public static ResourceKey<Biome> volcano = register("volcano");
    public static ResourceKey<Biome> wasteland = register("wasteland");
    public static ResourceKey<Biome> wetland = register("wetland");
    public static ResourceKey<Biome> wetland_forest = register("wetland_forest");
    public static ResourceKey<Biome> wooded_scrubland = register("wooded_scrubland");
    public static ResourceKey<Biome> woodland = register("woodland");

    public static ResourceKey<Biome> crystalline_chasm = register("crystalline_chasm");
    public static ResourceKey<Biome> undergrowth = register("undergrowth");
    public static ResourceKey<Biome> visceral_heap = register("visceral_heap");
    public static ResourceKey<Biome> withered_abyss = register("withered_abyss");

    public static ResourceKey<Biome> glowing_grotto = register("glowing_grotto");
    public static ResourceKey<Biome> spider_nest = register("spider_nest");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
