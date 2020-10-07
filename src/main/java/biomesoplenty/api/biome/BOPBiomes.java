/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.biome;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.Optional;

public class BOPBiomes
{
    public static RegistryKey<Biome> alps = register("alps");
    public static RegistryKey<Biome> alps_foothills = register("alps_foothills");
    public static RegistryKey<Biome> bayou = register("bayou");
    public static RegistryKey<Biome> bayou_mangrove = register("bayou_mangrove");
    public static RegistryKey<Biome> cherry_blossom_grove = register("cherry_blossom_grove");
    public static RegistryKey<Biome> coniferous_forest = register("coniferous_forest");
    public static RegistryKey<Biome> coniferous_lakes = register("coniferous_lakes");
    public static RegistryKey<Biome> dead_forest = register("dead_forest");
    public static RegistryKey<Biome> dense_bayou = register("dense_bayou");
    public static RegistryKey<Biome> dryland = register("dryland");
    public static RegistryKey<Biome> dry_steppe = register("dry_steppe");
    public static RegistryKey<Biome> fir_clearing = register("fir_clearing");
    public static RegistryKey<Biome> flower_meadow = register("flower_meadow");
    public static RegistryKey<Biome> fungal_jungle = register("fungal_jungle");
    public static RegistryKey<Biome> gravel_beach = register("gravel_beach");
    public static RegistryKey<Biome> grove = register("grove");
    public static RegistryKey<Biome> highland = register("highland");
    public static RegistryKey<Biome> highland_moor = register("highland_moor");
    public static RegistryKey<Biome> jade_cliffs = register("jade_cliffs");
    public static RegistryKey<Biome> lavender_field = register("lavender_field");
    public static RegistryKey<Biome> lush_desert = register("lush_desert");
    public static RegistryKey<Biome> maple_woods = register("maple_woods");
    public static RegistryKey<Biome> meadow = register("meadow");
    public static RegistryKey<Biome> muskeg = register("muskeg");
    public static RegistryKey<Biome> mystic_grove = register("mystic_grove");
    public static RegistryKey<Biome> mystic_plains = register("mystic_plains");
    public static RegistryKey<Biome> ominous_woods = register("ominous_woods");
    public static RegistryKey<Biome> orchard = register("orchard");
    public static RegistryKey<Biome> origin_valley = register("origin_valley");
    public static RegistryKey<Biome> pasture = register("pasture");
    public static RegistryKey<Biome> poppy_field = register("poppy_field");
    public static RegistryKey<Biome> prairie = register("prairie");
    public static RegistryKey<Biome> pumpkin_patch = register("pumpkin_patch");
    public static RegistryKey<Biome> rainbow_hills = register("rainbow_hills");
    public static RegistryKey<Biome> rainforest = register("rainforest");
    public static RegistryKey<Biome> rainforest_clearing = register("rainforest_clearing");
    public static RegistryKey<Biome> rainforest_cliffs = register("rainforest_cliffs");
    public static RegistryKey<Biome> redwood_forest = register("redwood_forest");
    public static RegistryKey<Biome> redwood_forest_edge = register("redwood_forest_edge");
    public static RegistryKey<Biome> seasonal_forest = register("seasonal_forest");
    public static RegistryKey<Biome> shroomy_wetland = register("shroomy_wetland");
    public static RegistryKey<Biome> shrubland = register("shrubland");
    public static RegistryKey<Biome> shrubland_hills = register("shrubland_hills");
    public static RegistryKey<Biome> silkglade = register("silkglade");
    public static RegistryKey<Biome> snowy_coniferous_forest = register("snowy_coniferous_forest");
    public static RegistryKey<Biome> snowy_fir_clearing = register("snowy_fir_clearing");
    public static RegistryKey<Biome> tropic_beach = register("tropic_beach");
    public static RegistryKey<Biome> tropics = register("tropics");
    public static RegistryKey<Biome> tundra = register("tundra");
    public static RegistryKey<Biome> tundra_basin = register("tundra_basin");
    public static RegistryKey<Biome> tundra_bog = register("tundra_bog");
    public static RegistryKey<Biome> volcanic_plains = register("volcanic_plains");
    public static RegistryKey<Biome> volcano = register("volcano");
    public static RegistryKey<Biome> wasteland = register("wasteland");
    public static RegistryKey<Biome> wetland = register("wetland");
    public static RegistryKey<Biome> wetland_marsh = register("wetland_marsh");

    public static RegistryKey<Biome> crystalline_chasm = register("crystalline_chasm");
    public static RegistryKey<Biome> undergrowth = register("undergrowth");
    public static RegistryKey<Biome> visceral_heap = register("visceral_heap");
    public static RegistryKey<Biome> withered_abyss = register("withered_abyss");

    private static RegistryKey<Biome> register(String name)
    {
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
