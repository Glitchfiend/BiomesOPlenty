/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
    {
        //Standard Trees
        registerFeatures(BOPBiomeFeatures.SMALL_JUNGLE_TREE, "small_jungle_tree");
        registerFeatures(BOPBiomeFeatures.ORIGIN_TREE, "origin_tree");
        registerFeatures(BOPBiomeFeatures.FLOWERING_OAK_TREE, "flowering_oak_tree");
        registerFeatures(BOPBiomeFeatures.RAINBOW_BIRCH_TREE, "rainbow_birch_tree");
        registerFeatures(BOPBiomeFeatures.YELLOW_AUTUMN_TREE, "yellow_autumn_tree");
        registerFeatures(BOPBiomeFeatures.ORANGE_AUTUMN_TREE, "orange_autumn_tree");
        registerFeatures(BOPBiomeFeatures.MAPLE_TREE, "maple_tree");
        registerFeatures(BOPBiomeFeatures.PINK_CHERRY_TREE, "pink_cherry_tree");
        registerFeatures(BOPBiomeFeatures.WHITE_CHERRY_TREE, "white_cherry_tree");
        registerFeatures(BOPBiomeFeatures.JACARANDA_TREE, "jacaranda_tree");
        registerFeatures(BOPBiomeFeatures.SMALL_DEAD_TREE, "small_dead_tree");
        registerFeatures(BOPBiomeFeatures.SILK_TREE, "silk_tree");
        registerFeatures(BOPBiomeFeatures.FULL_SILK_TREE, "full_silk_tree");
        registerFeatures(BOPBiomeFeatures.MAGIC_TREE, "magic_tree");

        //Big Trees
        registerFeatures(BOPBiomeFeatures.BIG_OAK_TREE, "big_oak_tree");
        registerFeatures(BOPBiomeFeatures.BIG_ORIGIN_TREE, "big_origin_tree");
        registerFeatures(BOPBiomeFeatures.BIG_FLOWERING_OAK_TREE, "big_flowering_oak_tree");
        registerFeatures(BOPBiomeFeatures.BIG_RAINBOW_BIRCH_TREE, "big_rainbow_birch_tree");
        registerFeatures(BOPBiomeFeatures.BIG_YELLOW_AUTUMN_TREE, "big_yellow_autumn_tree");
        registerFeatures(BOPBiomeFeatures.BIG_ORANGE_AUTUMN_TREE, "big_orange_autumn_tree");
        registerFeatures(BOPBiomeFeatures.BIG_MAPLE_TREE, "big_maple_tree");
        registerFeatures(BOPBiomeFeatures.BIG_PINK_CHERRY_TREE, "big_pink_cherry_tree");
        registerFeatures(BOPBiomeFeatures.BIG_WHITE_CHERRY_TREE, "big_white_cherry_tree");
        registerFeatures(BOPBiomeFeatures.BIG_JACARANDA_TREE, "big_jacaranda_tree");
        registerFeatures(BOPBiomeFeatures.BIG_MAGIC_TREE, "big_magic_tree");

        registerFeatures(BOPBiomeFeatures.GIANT_TREE, "giant_tree");

        //Conifer Trees
        registerFeatures(BOPBiomeFeatures.TALL_SPRUCE_TREE, "tall_spruce_tree");
        registerFeatures(BOPBiomeFeatures.ALPS_SPRUCE_TREE, "alps_spruce_tree");
        registerFeatures(BOPBiomeFeatures.FIR_TREE_SMALL, "fir_tree_small");
        registerFeatures(BOPBiomeFeatures.FIR_TREE, "fir_tree");
        registerFeatures(BOPBiomeFeatures.FIR_TREE_LARGE, "fir_tree_large");
        registerFeatures(BOPBiomeFeatures.SEQUOIA_TREE, "sequoia_tree");
        registerFeatures(BOPBiomeFeatures.SEQUOIA_TREE_LARGE, "sequoia_tree_large");
        registerFeatures(BOPBiomeFeatures.UMBRAN_TREE, "umbran_tree");
        registerFeatures(BOPBiomeFeatures.TALL_UMBRAN_TREE, "tall_umbran_tree");

        //Poplar Trees
        registerFeatures(BOPBiomeFeatures.SPRUCE_POPLAR, "spruce_poplar");
        registerFeatures(BOPBiomeFeatures.BIRCH_POPLAR, "birch_poplar");
        registerFeatures(BOPBiomeFeatures.DARK_OAK_POPLAR, "dark_oak_poplar");
        registerFeatures(BOPBiomeFeatures.YELLOW_POPLAR_TREE, "yellow_poplar_tree");

        //Swamp Trees
        registerFeatures(BOPBiomeFeatures.CYPRESS_TREE, "cypress_tree");
        registerFeatures(BOPBiomeFeatures.CYPRESS_TREE_MEDIUM, "cypress_tree_medium");
        registerFeatures(BOPBiomeFeatures.TALL_SWAMP_TREE, "tall_swamp_tree");
        registerFeatures(BOPBiomeFeatures.WILLOW_TREE, "willow_tree");

        //Sparse Trees
        registerFeatures(BOPBiomeFeatures.SPARSE_OAK_TREE, "sparse_oak_tree");
        registerFeatures(BOPBiomeFeatures.DYING_TREE, "dying_tree");
        registerFeatures(BOPBiomeFeatures.DYING_TREE_WASTELAND, "dying_tree_wasteland");
        registerFeatures(BOPBiomeFeatures.DYING_TREE_VOLCANO, "dying_tree_volcano");

        //Bushes/Twiglets
        registerFeatures(BOPBiomeFeatures.BUSH, "bush");
        registerFeatures(BOPBiomeFeatures.ACACIA_BUSH, "acacia_bush");
        registerFeatures(BOPBiomeFeatures.FLOWERING_BUSH, "flowering_bush");
        registerFeatures(BOPBiomeFeatures.COBWEB_BUSH, "cobweb_bush");
        registerFeatures(BOPBiomeFeatures.FULL_COBWEB_BUSH, "full_cobweb_bush");
        registerFeatures(BOPBiomeFeatures.HELLBARK_TREE, "hellbark_tree");

        registerFeatures(BOPBiomeFeatures.TWIGLET_TREE, "twiglet_tree");
        registerFeatures(BOPBiomeFeatures.TALL_TWIGLET_TREE, "tall_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.SPRUCE_TWIGLET_TREE, "spruce_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.JUNGLE_TWIGLET_TREE, "jungle_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.OASIS_JUNGLE_TWIGLET_TREE, "oasis_jungle_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.DARK_OAK_TWIGLET_TREE, "dark_oak_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.ACACIA_TWIGLET_TREE, "acacia_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.ACACIA_TWIGLET_SMALL, "acacia_twiglet_small");
        registerFeatures(BOPBiomeFeatures.ACACIA_TWIGLET, "acacia_twiglet");
        registerFeatures(BOPBiomeFeatures.MAPLE_TWIGLET_TREE, "maple_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.DEAD_TWIGLET_TREE_SMALL, "dead_twiglet_tree_small");
        registerFeatures(BOPBiomeFeatures.DEAD_TWIGLET_TREE, "dead_twiglet_tree");
        registerFeatures(BOPBiomeFeatures.DEAD_TWIGLET_TREE_TALL, "dead_twiglet_tree_tall");

        //Special Trees
        registerFeatures(BOPBiomeFeatures.REDWOOD_TREE, "redwood_tree");
        registerFeatures(BOPBiomeFeatures.REDWOOD_TREE_MEDIUM, "redwood_tree_medium");
        registerFeatures(BOPBiomeFeatures.REDWOOD_TREE_LARGE, "redwood_tree_large");
        registerFeatures(BOPBiomeFeatures.MAHOGANY_TREE, "mahogany_tree");
        registerFeatures(BOPBiomeFeatures.PALM_TREE, "palm_tree");
        registerFeatures(BOPBiomeFeatures.DEAD_TREE, "dead_tree");
        registerFeatures(BOPBiomeFeatures.DEAD_TREE_WASTELAND, "dead_tree_wasteland");
        registerFeatures(BOPBiomeFeatures.DEAD_TREE_VOLCANO, "dead_tree_volcano");

        /////////////////////////////////////////////////////////////////////////////////

        //Features
        registerFeatures(BOPBiomeFeatures.BRAMBLE, "bramble");
        registerFeatures(BOPBiomeFeatures.MANGROVE, "mangrove");
        registerFeatures(BOPBiomeFeatures.PUMPKIN_PATCH, "pumpkin_patch");
        registerFeatures(BOPBiomeFeatures.BIG_PUMPKIN, "big_pumpkin");
        registerFeatures(BOPBiomeFeatures.SHORT_BAMBOO, "short_bamboo");
        registerFeatures(BOPBiomeFeatures.SCRUB, "scrub");
        registerFeatures(BOPBiomeFeatures.SCATTERED_ROCKS, "scattered_rocks");
        registerFeatures(BOPBiomeFeatures.NETHER_VINES, "nether_vines");
        registerFeatures(BOPBiomeFeatures.BONE_SPINE, "bone_spine");
        registerFeatures(BOPBiomeFeatures.PODZOL_SPLATTER, "podzol_splatter");
        registerFeatures(BOPBiomeFeatures.MYCELIUM_SPLATTER, "mycelium_splatter");
        registerFeatures(BOPBiomeFeatures.OBSIDIAN_SPLATTER, "obsidian_splatter");
        registerFeatures(BOPBiomeFeatures.SMALL_RED_MUSHROOM, "small_red_mushroom");
        registerFeatures(BOPBiomeFeatures.SMALL_BROWN_MUSHROOM, "small_brown_mushroom");
        registerFeatures(BOPBiomeFeatures.SMALL_GLOWSHROOM, "small_glowshroom");
        registerFeatures(BOPBiomeFeatures.SMALL_TOADSTOOL, "small_toadstool");
        registerFeatures(BOPBiomeFeatures.HUGE_GLOWSHROOM, "huge_glowshroom");
        registerFeatures(BOPBiomeFeatures.HUGE_TOADSTOOL, "huge_toadstool");
        registerFeatures(BOPBiomeFeatures.SHROOMLIGHT_SCATTER, "shroomlight_scatter");
        registerFeatures(BOPBiomeFeatures.SMALL_CRYSTAL, "small_crystal");
        registerFeatures(BOPBiomeFeatures.LARGE_CRYSTAL, "large_crystal");
        registerFeatures(BOPBiomeFeatures.FLESH_TENDON, "flesh_tendon");

        //Flowers
        registerFeatures(BOPBiomeFeatures.CHAPARRAL_FLOWERS, "chaparral_flowers");
        registerFeatures(BOPBiomeFeatures.CHERRY_BLOSSOM_GROVE_FLOWERS, "cherry_blossom_grove_flowers");
        registerFeatures(BOPBiomeFeatures.CONIFEROUS_FOREST_FLOWERS, "coniferous_forest_flowers");
        registerFeatures(BOPBiomeFeatures.EXTENDED_FLOWERS, "extended_flowers");
        registerFeatures(BOPBiomeFeatures.FLOWER_MEADOW_FLOWERS, "flower_meadow_flowers");
        registerFeatures(BOPBiomeFeatures.JUNGLE_FLOWERS, "jungle_flowers");
        registerFeatures(BOPBiomeFeatures.LAVENDER_FLOWERS, "lavender_flowers");
        registerFeatures(BOPBiomeFeatures.LUSH_GRASSLAND_FLOWERS, "lush_grassland_flowers");
        registerFeatures(BOPBiomeFeatures.LUSH_SWAMP_FLOWERS, "lush_swamp_flowers");
        registerFeatures(BOPBiomeFeatures.MEADOW_FLOWERS, "meadow_flowers");
        registerFeatures(BOPBiomeFeatures.MOOR_FLOWERS, "moor_flowers");
        registerFeatures(BOPBiomeFeatures.MYSTIC_GROVE_FLOWERS, "mystic_grove_flowers");
        registerFeatures(BOPBiomeFeatures.OMINOUS_WOODS_FLOWERS, "ominous_woods_flowers");
        registerFeatures(BOPBiomeFeatures.ORIGIN_FLOWERS, "origin_flowers");
        registerFeatures(BOPBiomeFeatures.RAINBOW_VALLEY_FLOWERS, "rainbow_valley_flowers");
        registerFeatures(BOPBiomeFeatures.RAINFOREST_FLOWERS, "rainforest_flowers");
        registerFeatures(BOPBiomeFeatures.SHRUBLAND_FLOWERS, "shrubland_flowers");
        registerFeatures(BOPBiomeFeatures.SNOWY_FLOWERS, "snowy_flowers");
        registerFeatures(BOPBiomeFeatures.TROPICS_FLOWERS, "tropics_flowers");
        registerFeatures(BOPBiomeFeatures.WASTELAND_FLOWERS, "wasteland_flowers");
        registerFeatures(BOPBiomeFeatures.WETLAND_FLOWERS, "wetland_flowers");

        //Vanilla Biomes Features
        registerFeatures(BOPBiomeFeatures.VIOLET_FEATURE, "violet_feature");
        registerFeatures(BOPBiomeFeatures.ORANGE_COSMOS_FEATURE, "orange_cosmos_feature");
        registerFeatures(BOPBiomeFeatures.WILDFLOWER_FEATURE, "wildflower_feature");
        registerFeatures(BOPBiomeFeatures.POPPY_FEATURE, "poppy_feature");
    }


    public static Feature<?> registerFeatures(Feature<?> entry, String name)
    {
        entry.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, name));
        ForgeRegistries.FEATURES.register(entry);
        return entry;
    }
}
