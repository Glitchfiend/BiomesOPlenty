/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static biomesoplenty.api.item.BOPItems.*;

public class ModItems
{
    public static void setup(BiConsumer<ResourceLocation, Item> func)
    {
        registerItems(func);
        ModVanillaCompat.setup();
    }

    private static void registerItems(BiConsumer<ResourceLocation, Item> func)
    {
        registerBlockItems(func);

        BOP_ICON = registerItem(func, "bop_icon", Item::new, new Item.Properties());

        ROSE_QUARTZ_CHUNK = registerItem(func, "rose_quartz_chunk", Item::new, new Item.Properties());
        MUSIC_DISC_WANDERER = registerItem(func, "music_disc_wanderer", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).jukeboxPlayable(ModJukeboxSongs.WANDERER));
        BLOOD_BUCKET = registerItem(func, "blood_bucket", (properties) -> new BucketItem(BOPFluids.BLOOD, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
        LIQUID_NULL_BUCKET = registerItem(func, "liquid_null_bucket", (properties) -> new BucketItem(BOPFluids.LIQUID_NULL, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

        ORIGIN_OAK_SIGN = registerBlock(func, BOPBlocks.ORIGIN_OAK_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.ORIGIN_OAK_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        ORIGIN_OAK_HANGING_SIGN = registerBlock(func, BOPBlocks.ORIGIN_OAK_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.ORIGIN_OAK_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        FIR_SIGN = registerBlock(func, BOPBlocks.FIR_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.FIR_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        FIR_HANGING_SIGN = registerBlock(func, BOPBlocks.FIR_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.FIR_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        PINE_SIGN = registerBlock(func, BOPBlocks.PINE_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.PINE_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        PINE_HANGING_SIGN = registerBlock(func, BOPBlocks.PINE_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.PINE_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        MAPLE_SIGN = registerBlock(func, BOPBlocks.MAPLE_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAPLE_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        MAPLE_HANGING_SIGN = registerBlock(func, BOPBlocks.MAPLE_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAPLE_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        REDWOOD_SIGN = registerBlock(func, BOPBlocks.REDWOOD_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.REDWOOD_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        REDWOOD_HANGING_SIGN = registerBlock(func, BOPBlocks.REDWOOD_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.REDWOOD_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        MAHOGANY_SIGN = registerBlock(func, BOPBlocks.MAHOGANY_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAHOGANY_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        MAHOGANY_HANGING_SIGN = registerBlock(func, BOPBlocks.MAHOGANY_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAHOGANY_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        JACARANDA_SIGN = registerBlock(func, BOPBlocks.JACARANDA_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.JACARANDA_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        JACARANDA_HANGING_SIGN = registerBlock(func, BOPBlocks.JACARANDA_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.JACARANDA_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        PALM_SIGN = registerBlock(func, BOPBlocks.PALM_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.PALM_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        PALM_HANGING_SIGN = registerBlock(func, BOPBlocks.PALM_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.PALM_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        WILLOW_SIGN = registerBlock(func, BOPBlocks.WILLOW_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.WILLOW_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        WILLOW_HANGING_SIGN = registerBlock(func, BOPBlocks.WILLOW_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.WILLOW_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        DEAD_SIGN = registerBlock(func, BOPBlocks.DEAD_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.DEAD_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        DEAD_HANGING_SIGN = registerBlock(func, BOPBlocks.DEAD_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.DEAD_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        MAGIC_SIGN = registerBlock(func, BOPBlocks.MAGIC_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAGIC_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        MAGIC_HANGING_SIGN = registerBlock(func, BOPBlocks.MAGIC_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.MAGIC_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        UMBRAN_SIGN = registerBlock(func, BOPBlocks.UMBRAN_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.UMBRAN_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        UMBRAN_HANGING_SIGN = registerBlock(func, BOPBlocks.UMBRAN_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.UMBRAN_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        HELLBARK_SIGN = registerBlock(func, BOPBlocks.HELLBARK_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.HELLBARK_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        HELLBARK_HANGING_SIGN = registerBlock(func, BOPBlocks.HELLBARK_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.HELLBARK_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));
        EMPYREAL_SIGN = registerBlock(func, BOPBlocks.EMPYREAL_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.EMPYREAL_WALL_SIGN, properties), new Item.Properties().stacksTo(16));
        EMPYREAL_HANGING_SIGN = registerBlock(func, BOPBlocks.EMPYREAL_HANGING_SIGN, (block, properties) -> new SignItem(block, BOPBlocks.EMPYREAL_WALL_HANGING_SIGN, properties), new Item.Properties().stacksTo(16));

        ORIGIN_OAK_BOAT = registerItem(func, "origin_oak_boat", (properties) -> new BoatItem(BOPEntities.ORIGIN_OAK_BOAT, properties), new Item.Properties().stacksTo(1));
        ORIGIN_OAK_CHEST_BOAT = registerItem(func, "origin_oak_chest_boat", (properties) -> new BoatItem(BOPEntities.ORIGIN_OAK_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        FIR_BOAT = registerItem(func, "fir_boat", (properties) -> new BoatItem(BOPEntities.FIR_BOAT, properties), new Item.Properties().stacksTo(1));
        FIR_CHEST_BOAT = registerItem(func, "fir_chest_boat", (properties) -> new BoatItem(BOPEntities.FIR_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        PINE_BOAT = registerItem(func, "pine_boat", (properties) -> new BoatItem(BOPEntities.PINE_BOAT, properties), new Item.Properties().stacksTo(1));
        PINE_CHEST_BOAT = registerItem(func, "pine_chest_boat", (properties) -> new BoatItem(BOPEntities.PINE_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        MAPLE_BOAT = registerItem(func, "maple_boat", (properties) -> new BoatItem(BOPEntities.MAPLE_BOAT, properties), new Item.Properties().stacksTo(1));
        MAPLE_CHEST_BOAT = registerItem(func, "maple_chest_boat", (properties) -> new BoatItem(BOPEntities.MAPLE_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        REDWOOD_BOAT = registerItem(func, "redwood_boat", (properties) -> new BoatItem(BOPEntities.REDWOOD_BOAT, properties), new Item.Properties().stacksTo(1));
        REDWOOD_CHEST_BOAT = registerItem(func, "redwood_chest_boat", (properties) -> new BoatItem(BOPEntities.REDWOOD_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        MAHOGANY_BOAT = registerItem(func, "mahogany_boat", (properties) -> new BoatItem(BOPEntities.MAHOGANY_BOAT, properties), new Item.Properties().stacksTo(1));
        MAHOGANY_CHEST_BOAT = registerItem(func, "mahogany_chest_boat", (properties) -> new BoatItem(BOPEntities.MAHOGANY_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        JACARANDA_BOAT = registerItem(func, "jacaranda_boat", (properties) -> new BoatItem(BOPEntities.JACARANDA_BOAT, properties), new Item.Properties().stacksTo(1));
        JACARANDA_CHEST_BOAT = registerItem(func, "jacaranda_chest_boat", (properties) -> new BoatItem(BOPEntities.JACARANDA_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        PALM_BOAT = registerItem(func, "palm_boat", (properties) -> new BoatItem(BOPEntities.PALM_BOAT, properties), new Item.Properties().stacksTo(1));
        PALM_CHEST_BOAT = registerItem(func, "palm_chest_boat", (properties) -> new BoatItem(BOPEntities.PALM_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        WILLOW_BOAT = registerItem(func, "willow_boat", (properties) -> new BoatItem(BOPEntities.WILLOW_BOAT, properties), new Item.Properties().stacksTo(1));
        WILLOW_CHEST_BOAT = registerItem(func, "willow_chest_boat", (properties) -> new BoatItem(BOPEntities.WILLOW_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        DEAD_BOAT = registerItem(func, "dead_boat", (properties) -> new BoatItem(BOPEntities.DEAD_BOAT, properties), new Item.Properties().stacksTo(1));
        DEAD_CHEST_BOAT = registerItem(func, "dead_chest_boat", (properties) -> new BoatItem(BOPEntities.DEAD_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        MAGIC_BOAT = registerItem(func, "magic_boat", (properties) -> new BoatItem(BOPEntities.MAGIC_BOAT, properties), new Item.Properties().stacksTo(1));
        MAGIC_CHEST_BOAT = registerItem(func, "magic_chest_boat", (properties) -> new BoatItem(BOPEntities.MAGIC_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        UMBRAN_BOAT = registerItem(func, "umbran_boat", (properties) -> new BoatItem(BOPEntities.UMBRAN_BOAT, properties), new Item.Properties().stacksTo(1));
        UMBRAN_CHEST_BOAT = registerItem(func, "umbran_chest_boat", (properties) -> new BoatItem(BOPEntities.UMBRAN_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        HELLBARK_BOAT = registerItem(func, "hellbark_boat", (properties) -> new BoatItem(BOPEntities.HELLBARK_BOAT, properties), new Item.Properties().stacksTo(1));
        HELLBARK_CHEST_BOAT = registerItem(func, "hellbark_chest_boat", (properties) -> new BoatItem(BOPEntities.HELLBARK_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
        EMPYREAL_BOAT = registerItem(func, "empyreal_boat", (properties) -> new BoatItem(BOPEntities.EMPYREAL_BOAT, properties), new Item.Properties().stacksTo(1));
        EMPYREAL_CHEST_BOAT = registerItem(func, "empyreal_chest_boat", (properties) -> new BoatItem(BOPEntities.EMPYREAL_CHEST_BOAT, properties), new Item.Properties().stacksTo(1));
    }

    public static void registerBlockItems(BiConsumer<ResourceLocation, Item> func)
    {
        BLOOD = registerBlock(func, BOPBlocks.BLOOD);
        LIQUID_NULL = registerBlock(func, BOPBlocks.LIQUID_NULL);
        WHITE_SAND = registerBlock(func, BOPBlocks.WHITE_SAND);
        WHITE_SANDSTONE = registerBlock(func, BOPBlocks.WHITE_SANDSTONE);
        WHITE_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.WHITE_SANDSTONE_STAIRS);
        WHITE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.WHITE_SANDSTONE_SLAB);
        WHITE_SANDSTONE_WALL = registerBlock(func, BOPBlocks.WHITE_SANDSTONE_WALL);
        SMOOTH_WHITE_SANDSTONE = registerBlock(func, BOPBlocks.SMOOTH_WHITE_SANDSTONE);
        SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS);
        SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB);
        CUT_WHITE_SANDSTONE = registerBlock(func, BOPBlocks.CUT_WHITE_SANDSTONE);
        CUT_WHITE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.CUT_WHITE_SANDSTONE_SLAB);
        CHISELED_WHITE_SANDSTONE = registerBlock(func, BOPBlocks.CHISELED_WHITE_SANDSTONE);
        ORANGE_SAND = registerBlock(func, BOPBlocks.ORANGE_SAND);
        ORANGE_SANDSTONE = registerBlock(func, BOPBlocks.ORANGE_SANDSTONE);
        ORANGE_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.ORANGE_SANDSTONE_STAIRS);
        ORANGE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.ORANGE_SANDSTONE_SLAB);
        ORANGE_SANDSTONE_WALL = registerBlock(func, BOPBlocks.ORANGE_SANDSTONE_WALL);
        SMOOTH_ORANGE_SANDSTONE = registerBlock(func, BOPBlocks.SMOOTH_ORANGE_SANDSTONE);
        SMOOTH_ORANGE_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_STAIRS);
        SMOOTH_ORANGE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB);
        CUT_ORANGE_SANDSTONE = registerBlock(func, BOPBlocks.CUT_ORANGE_SANDSTONE);
        CUT_ORANGE_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB);
        CHISELED_ORANGE_SANDSTONE = registerBlock(func, BOPBlocks.CHISELED_ORANGE_SANDSTONE);
        MOSSY_BLACK_SAND = registerBlock(func, BOPBlocks.MOSSY_BLACK_SAND);
        BLACK_SAND = registerBlock(func, BOPBlocks.BLACK_SAND);
        BLACK_SANDSTONE = registerBlock(func, BOPBlocks.BLACK_SANDSTONE);
        BLACK_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.BLACK_SANDSTONE_STAIRS);
        BLACK_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.BLACK_SANDSTONE_SLAB);
        BLACK_SANDSTONE_WALL = registerBlock(func, BOPBlocks.BLACK_SANDSTONE_WALL);
        SMOOTH_BLACK_SANDSTONE = registerBlock(func, BOPBlocks.SMOOTH_BLACK_SANDSTONE);
        SMOOTH_BLACK_SANDSTONE_STAIRS = registerBlock(func, BOPBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS);
        SMOOTH_BLACK_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB);
        CUT_BLACK_SANDSTONE = registerBlock(func, BOPBlocks.CUT_BLACK_SANDSTONE);
        CUT_BLACK_SANDSTONE_SLAB = registerBlock(func, BOPBlocks.CUT_BLACK_SANDSTONE_SLAB);
        CHISELED_BLACK_SANDSTONE = registerBlock(func, BOPBlocks.CHISELED_BLACK_SANDSTONE);
        THERMAL_CALCITE = registerBlock(func, BOPBlocks.THERMAL_CALCITE);
        THERMAL_CALCITE_VENT = registerBlock(func, BOPBlocks.THERMAL_CALCITE_VENT);
        DRIED_SALT = registerBlock(func, BOPBlocks.DRIED_SALT);
        FLESH = registerBlock(func, BOPBlocks.FLESH);
        POROUS_FLESH = registerBlock(func, BOPBlocks.POROUS_FLESH);
        FLESH_TENDONS = registerBlock(func, BOPBlocks.FLESH_TENDONS);
        FLESH_TENDONS_STRAND = registerBlock(func, BOPBlocks.FLESH_TENDONS_STRAND);
        EYEBULB = registerBlock(func, BOPBlocks.EYEBULB);
        HAIR = registerBlock(func, BOPBlocks.HAIR);
        PUS_BUBBLE = registerBlock(func, BOPBlocks.PUS_BUBBLE);
        BRIMSTONE = registerBlock(func, BOPBlocks.BRIMSTONE);
        BRIMSTONE_BRICKS = registerBlock(func, BOPBlocks.BRIMSTONE_BRICKS);
        BRIMSTONE_BRICK_STAIRS = registerBlock(func, BOPBlocks.BRIMSTONE_BRICK_STAIRS);
        BRIMSTONE_BRICK_SLAB = registerBlock(func, BOPBlocks.BRIMSTONE_BRICK_SLAB);
        BRIMSTONE_BRICK_WALL = registerBlock(func, BOPBlocks.BRIMSTONE_BRICK_WALL);
        CHISELED_BRIMSTONE_BRICKS = registerBlock(func, BOPBlocks.CHISELED_BRIMSTONE_BRICKS);
        BRIMSTONE_FUMAROLE = registerBlock(func, BOPBlocks.BRIMSTONE_FUMAROLE);
        BRIMSTONE_CLUSTER = registerBlock(func, BOPBlocks.BRIMSTONE_CLUSTER);
        BRIMSTONE_BUD = registerBlock(func, BOPBlocks.BRIMSTONE_BUD);
        BLACKSTONE_SPINES = registerBlock(func, BOPBlocks.BLACKSTONE_SPINES);
        BLACKSTONE_BULB = registerBlock(func, BOPBlocks.BLACKSTONE_BULB);
        ROSE_QUARTZ_BLOCK = registerBlock(func, BOPBlocks.ROSE_QUARTZ_BLOCK);
        ROSE_QUARTZ_CLUSTER = registerBlock(func, BOPBlocks.ROSE_QUARTZ_CLUSTER);
        LARGE_ROSE_QUARTZ_BUD = registerBlock(func, BOPBlocks.LARGE_ROSE_QUARTZ_BUD);
        MEDIUM_ROSE_QUARTZ_BUD = registerBlock(func, BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD);
        SMALL_ROSE_QUARTZ_BUD = registerBlock(func, BOPBlocks.SMALL_ROSE_QUARTZ_BUD);
        BARNACLES = registerBlock(func, BOPBlocks.BARNACLES);
        WISPJELLY = registerBlock(func, BOPBlocks.WISPJELLY);
        //VOIDCAP = registerBlock(func, BOPBlocks.VOIDCAP);
        //VOIDCAP_BLOCK = registerBlock(func, BOPBlocks.VOIDCAP_BLOCK);
        ALGAL_END_STONE = registerBlock(func, BOPBlocks.ALGAL_END_STONE);
        UNMAPPED_END_STONE = registerBlock(func, BOPBlocks.UNMAPPED_END_STONE);
        NULL_END_STONE = registerBlock(func, BOPBlocks.NULL_END_STONE);
        NULL_BLOCK = registerBlock(func, BOPBlocks.NULL_BLOCK);
        NULL_LEAVES = registerBlock(func, BOPBlocks.NULL_LEAVES);
        NULL_PLANT = registerBlock(func, BOPBlocks.NULL_PLANT);
        ANOMALY = registerBlock(func, BOPBlocks.ANOMALY);
        TOADSTOOL = registerBlock(func, BOPBlocks.TOADSTOOL);
        TOADSTOOL_BLOCK = registerBlock(func, BOPBlocks.TOADSTOOL_BLOCK);
        GLOWSHROOM = registerBlock(func, BOPBlocks.GLOWSHROOM);
        GLOWSHROOM_BLOCK = registerBlock(func, BOPBlocks.GLOWSHROOM_BLOCK);
        GLOWING_MOSS_BLOCK = registerBlock(func, BOPBlocks.GLOWING_MOSS_BLOCK);
        GLOWING_MOSS_CARPET = registerBlock(func, BOPBlocks.GLOWING_MOSS_CARPET);
        GLOWWORM_SILK = registerBlock(func, BOPBlocks.GLOWWORM_SILK);
        GLOWWORM_SILK_STRAND = registerBlock(func, BOPBlocks.GLOWWORM_SILK_STRAND);
        SPIDER_EGG = registerBlock(func, BOPBlocks.SPIDER_EGG);
        HANGING_COBWEB = registerBlock(func, BOPBlocks.HANGING_COBWEB);
        HANGING_COBWEB_STRAND = registerBlock(func, BOPBlocks.HANGING_COBWEB_STRAND);
        STRINGY_COBWEB = registerBlock(func, BOPBlocks.STRINGY_COBWEB);
        WEBBING = registerBlock(func, BOPBlocks.WEBBING);
        WHITE_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.WHITE_FLOWER_PETAL_BLOCK);
        LIGHT_GRAY_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.LIGHT_GRAY_FLOWER_PETAL_BLOCK);
        GRAY_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.GRAY_FLOWER_PETAL_BLOCK);
        BLACK_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.BLACK_FLOWER_PETAL_BLOCK);
        BROWN_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.BROWN_FLOWER_PETAL_BLOCK);
        RED_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.RED_FLOWER_PETAL_BLOCK);
        ORANGE_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK);
        YELLOW_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.YELLOW_FLOWER_PETAL_BLOCK);
        LIME_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.LIME_FLOWER_PETAL_BLOCK);
        GREEN_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.GREEN_FLOWER_PETAL_BLOCK);
        CYAN_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.CYAN_FLOWER_PETAL_BLOCK);
        LIGHT_BLUE_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.LIGHT_BLUE_FLOWER_PETAL_BLOCK);
        BLUE_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.BLUE_FLOWER_PETAL_BLOCK);
        PURPLE_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK);
        MAGENTA_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.MAGENTA_FLOWER_PETAL_BLOCK);
        PINK_FLOWER_PETAL_BLOCK = registerBlock(func, BOPBlocks.PINK_FLOWER_PETAL_BLOCK);
        FLOWER_STEM = registerBlock(func, BOPBlocks.FLOWER_STEM);
        ORIGIN_GRASS_BLOCK = registerBlock(func, BOPBlocks.ORIGIN_GRASS_BLOCK);

        FLOWERING_OAK_SAPLING = registerBlock(func, BOPBlocks.FLOWERING_OAK_SAPLING);
        FLOWERING_OAK_LEAVES = registerBlock(func, BOPBlocks.FLOWERING_OAK_LEAVES);
        CYPRESS_SAPLING = registerBlock(func, BOPBlocks.CYPRESS_SAPLING);
        CYPRESS_LEAVES = registerBlock(func, BOPBlocks.CYPRESS_LEAVES);
        SNOWBLOSSOM_SAPLING = registerBlock(func, BOPBlocks.SNOWBLOSSOM_SAPLING);
        SNOWBLOSSOM_LEAVES = registerBlock(func, BOPBlocks.SNOWBLOSSOM_LEAVES);

        ORIGIN_OAK_SAPLING = registerBlock(func, BOPBlocks.ORIGIN_OAK_SAPLING);
        ORIGIN_OAK_LEAVES = registerBlock(func, BOPBlocks.ORIGIN_OAK_LEAVES);
        ORIGIN_OAK_LOG = registerBlock(func, BOPBlocks.ORIGIN_OAK_LOG);
        ORIGIN_OAK_WOOD = registerBlock(func, BOPBlocks.ORIGIN_OAK_WOOD);
        STRIPPED_ORIGIN_OAK_LOG = registerBlock(func, BOPBlocks.STRIPPED_ORIGIN_OAK_LOG);
        STRIPPED_ORIGIN_OAK_WOOD = registerBlock(func, BOPBlocks.STRIPPED_ORIGIN_OAK_WOOD);
        ORIGIN_OAK_PLANKS = registerBlock(func, BOPBlocks.ORIGIN_OAK_PLANKS);
        ORIGIN_OAK_STAIRS = registerBlock(func, BOPBlocks.ORIGIN_OAK_STAIRS);
        ORIGIN_OAK_SLAB = registerBlock(func, BOPBlocks.ORIGIN_OAK_SLAB);
        ORIGIN_OAK_FENCE = registerBlock(func, BOPBlocks.ORIGIN_OAK_FENCE);
        ORIGIN_OAK_FENCE_GATE = registerBlock(func, BOPBlocks.ORIGIN_OAK_FENCE_GATE);
        ORIGIN_OAK_DOOR = registerBlock(func, BOPBlocks.ORIGIN_OAK_DOOR);
        ORIGIN_OAK_TRAPDOOR = registerBlock(func, BOPBlocks.ORIGIN_OAK_TRAPDOOR);
        ORIGIN_OAK_PRESSURE_PLATE = registerBlock(func, BOPBlocks.ORIGIN_OAK_PRESSURE_PLATE);
        ORIGIN_OAK_BUTTON = registerBlock(func, BOPBlocks.ORIGIN_OAK_BUTTON);
        ORIGIN_OAK_SHELF = registerBlock(func, BOPBlocks.ORIGIN_OAK_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        FIR_SAPLING = registerBlock(func, BOPBlocks.FIR_SAPLING);
        FIR_LEAVES = registerBlock(func, BOPBlocks.FIR_LEAVES);
        FIR_LOG = registerBlock(func, BOPBlocks.FIR_LOG);
        FIR_WOOD = registerBlock(func, BOPBlocks.FIR_WOOD);
        STRIPPED_FIR_LOG = registerBlock(func, BOPBlocks.STRIPPED_FIR_LOG);
        STRIPPED_FIR_WOOD = registerBlock(func, BOPBlocks.STRIPPED_FIR_WOOD);
        FIR_PLANKS = registerBlock(func, BOPBlocks.FIR_PLANKS);
        FIR_STAIRS = registerBlock(func, BOPBlocks.FIR_STAIRS);
        FIR_SLAB = registerBlock(func, BOPBlocks.FIR_SLAB);
        FIR_FENCE = registerBlock(func, BOPBlocks.FIR_FENCE);
        FIR_FENCE_GATE = registerBlock(func, BOPBlocks.FIR_FENCE_GATE);
        FIR_DOOR = registerBlock(func, BOPBlocks.FIR_DOOR);
        FIR_TRAPDOOR = registerBlock(func, BOPBlocks.FIR_TRAPDOOR);
        FIR_PRESSURE_PLATE = registerBlock(func, BOPBlocks.FIR_PRESSURE_PLATE);
        FIR_BUTTON = registerBlock(func, BOPBlocks.FIR_BUTTON);
        FIR_SHELF = registerBlock(func, BOPBlocks.FIR_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        PINE_SAPLING = registerBlock(func, BOPBlocks.PINE_SAPLING);
        PINE_LEAVES = registerBlock(func, BOPBlocks.PINE_LEAVES);
        PINE_LOG = registerBlock(func, BOPBlocks.PINE_LOG);
        PINE_WOOD = registerBlock(func, BOPBlocks.PINE_WOOD);
        STRIPPED_PINE_LOG = registerBlock(func, BOPBlocks.STRIPPED_PINE_LOG);
        STRIPPED_PINE_WOOD = registerBlock(func, BOPBlocks.STRIPPED_PINE_WOOD);
        PINE_PLANKS = registerBlock(func, BOPBlocks.PINE_PLANKS);
        PINE_STAIRS = registerBlock(func, BOPBlocks.PINE_STAIRS);
        PINE_SLAB = registerBlock(func, BOPBlocks.PINE_SLAB);
        PINE_FENCE = registerBlock(func, BOPBlocks.PINE_FENCE);
        PINE_FENCE_GATE = registerBlock(func, BOPBlocks.PINE_FENCE_GATE);
        PINE_DOOR = registerBlock(func, BOPBlocks.PINE_DOOR);
        PINE_TRAPDOOR = registerBlock(func, BOPBlocks.PINE_TRAPDOOR);
        PINE_PRESSURE_PLATE = registerBlock(func, BOPBlocks.PINE_PRESSURE_PLATE);
        PINE_BUTTON = registerBlock(func, BOPBlocks.PINE_BUTTON);
        PINE_SHELF = registerBlock(func, BOPBlocks.PINE_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        RED_MAPLE_SAPLING = registerBlock(func, BOPBlocks.RED_MAPLE_SAPLING);
        RED_MAPLE_LEAF_LITTER = registerBlock(func, BOPBlocks.RED_MAPLE_LEAF_LITTER);
        RED_MAPLE_LEAVES = registerBlock(func, BOPBlocks.RED_MAPLE_LEAVES);
        ORANGE_MAPLE_SAPLING = registerBlock(func, BOPBlocks.ORANGE_MAPLE_SAPLING);
        ORANGE_MAPLE_LEAF_LITTER = registerBlock(func, BOPBlocks.ORANGE_MAPLE_LEAF_LITTER);
        ORANGE_MAPLE_LEAVES = registerBlock(func, BOPBlocks.ORANGE_MAPLE_LEAVES);
        YELLOW_MAPLE_SAPLING = registerBlock(func, BOPBlocks.YELLOW_MAPLE_SAPLING);
        YELLOW_MAPLE_LEAF_LITTER = registerBlock(func, BOPBlocks.YELLOW_MAPLE_LEAF_LITTER);
        YELLOW_MAPLE_LEAVES = registerBlock(func, BOPBlocks.YELLOW_MAPLE_LEAVES);
        MAPLE_LOG = registerBlock(func, BOPBlocks.MAPLE_LOG);
        MAPLE_WOOD = registerBlock(func, BOPBlocks.MAPLE_WOOD);
        STRIPPED_MAPLE_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAPLE_LOG);
        STRIPPED_MAPLE_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAPLE_WOOD);
        MAPLE_PLANKS = registerBlock(func, BOPBlocks.MAPLE_PLANKS);
        MAPLE_STAIRS = registerBlock(func, BOPBlocks.MAPLE_STAIRS);
        MAPLE_SLAB = registerBlock(func, BOPBlocks.MAPLE_SLAB);
        MAPLE_FENCE = registerBlock(func, BOPBlocks.MAPLE_FENCE);
        MAPLE_FENCE_GATE = registerBlock(func, BOPBlocks.MAPLE_FENCE_GATE);
        MAPLE_DOOR = registerBlock(func, BOPBlocks.MAPLE_DOOR);
        MAPLE_TRAPDOOR = registerBlock(func, BOPBlocks.MAPLE_TRAPDOOR);
        MAPLE_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAPLE_PRESSURE_PLATE);
        MAPLE_BUTTON = registerBlock(func, BOPBlocks.MAPLE_BUTTON);
        MAPLE_SHELF = registerBlock(func, BOPBlocks.MAPLE_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        REDWOOD_SAPLING = registerBlock(func, BOPBlocks.REDWOOD_SAPLING);
        REDWOOD_LEAVES = registerBlock(func, BOPBlocks.REDWOOD_LEAVES);
        REDWOOD_LOG = registerBlock(func, BOPBlocks.REDWOOD_LOG);
        REDWOOD_WOOD = registerBlock(func, BOPBlocks.REDWOOD_WOOD);
        STRIPPED_REDWOOD_LOG = registerBlock(func, BOPBlocks.STRIPPED_REDWOOD_LOG);
        STRIPPED_REDWOOD_WOOD = registerBlock(func, BOPBlocks.STRIPPED_REDWOOD_WOOD);
        REDWOOD_PLANKS = registerBlock(func, BOPBlocks.REDWOOD_PLANKS);
        REDWOOD_STAIRS = registerBlock(func, BOPBlocks.REDWOOD_STAIRS);
        REDWOOD_SLAB = registerBlock(func, BOPBlocks.REDWOOD_SLAB);
        REDWOOD_FENCE = registerBlock(func, BOPBlocks.REDWOOD_FENCE);
        REDWOOD_FENCE_GATE = registerBlock(func, BOPBlocks.REDWOOD_FENCE_GATE);
        REDWOOD_DOOR = registerBlock(func, BOPBlocks.REDWOOD_DOOR);
        REDWOOD_TRAPDOOR = registerBlock(func, BOPBlocks.REDWOOD_TRAPDOOR);
        REDWOOD_PRESSURE_PLATE = registerBlock(func, BOPBlocks.REDWOOD_PRESSURE_PLATE);
        REDWOOD_BUTTON = registerBlock(func, BOPBlocks.REDWOOD_BUTTON);
        REDWOOD_SHELF = registerBlock(func, BOPBlocks.REDWOOD_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        MAHOGANY_SAPLING = registerBlock(func, BOPBlocks.MAHOGANY_SAPLING);
        MAHOGANY_LEAVES = registerBlock(func, BOPBlocks.MAHOGANY_LEAVES);
        MAHOGANY_LOG = registerBlock(func, BOPBlocks.MAHOGANY_LOG);
        MAHOGANY_WOOD = registerBlock(func, BOPBlocks.MAHOGANY_WOOD);
        STRIPPED_MAHOGANY_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        STRIPPED_MAHOGANY_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAHOGANY_WOOD);
        MAHOGANY_PLANKS = registerBlock(func, BOPBlocks.MAHOGANY_PLANKS);
        MAHOGANY_STAIRS = registerBlock(func, BOPBlocks.MAHOGANY_STAIRS);
        MAHOGANY_SLAB = registerBlock(func, BOPBlocks.MAHOGANY_SLAB);
        MAHOGANY_FENCE = registerBlock(func, BOPBlocks.MAHOGANY_FENCE);
        MAHOGANY_FENCE_GATE = registerBlock(func, BOPBlocks.MAHOGANY_FENCE_GATE);
        MAHOGANY_DOOR = registerBlock(func, BOPBlocks.MAHOGANY_DOOR);
        MAHOGANY_TRAPDOOR = registerBlock(func, BOPBlocks.MAHOGANY_TRAPDOOR);
        MAHOGANY_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAHOGANY_PRESSURE_PLATE);
        MAHOGANY_BUTTON = registerBlock(func, BOPBlocks.MAHOGANY_BUTTON);
        MAHOGANY_SHELF = registerBlock(func, BOPBlocks.MAHOGANY_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        JACARANDA_SAPLING = registerBlock(func, BOPBlocks.JACARANDA_SAPLING);
        JACARANDA_LEAVES = registerBlock(func, BOPBlocks.JACARANDA_LEAVES);
        JACARANDA_LOG = registerBlock(func, BOPBlocks.JACARANDA_LOG);
        JACARANDA_WOOD = registerBlock(func, BOPBlocks.JACARANDA_WOOD);
        STRIPPED_JACARANDA_LOG = registerBlock(func, BOPBlocks.STRIPPED_JACARANDA_LOG);
        STRIPPED_JACARANDA_WOOD = registerBlock(func, BOPBlocks.STRIPPED_JACARANDA_WOOD);
        JACARANDA_PLANKS = registerBlock(func, BOPBlocks.JACARANDA_PLANKS);
        JACARANDA_STAIRS = registerBlock(func, BOPBlocks.JACARANDA_STAIRS);
        JACARANDA_SLAB = registerBlock(func, BOPBlocks.JACARANDA_SLAB);
        JACARANDA_FENCE = registerBlock(func, BOPBlocks.JACARANDA_FENCE);
        JACARANDA_FENCE_GATE = registerBlock(func, BOPBlocks.JACARANDA_FENCE_GATE);
        JACARANDA_DOOR = registerBlock(func, BOPBlocks.JACARANDA_DOOR);
        JACARANDA_TRAPDOOR = registerBlock(func, BOPBlocks.JACARANDA_TRAPDOOR);
        JACARANDA_PRESSURE_PLATE = registerBlock(func, BOPBlocks.JACARANDA_PRESSURE_PLATE);
        JACARANDA_BUTTON = registerBlock(func, BOPBlocks.JACARANDA_BUTTON);
        JACARANDA_SHELF = registerBlock(func, BOPBlocks.JACARANDA_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        PALM_SAPLING = registerBlock(func, BOPBlocks.PALM_SAPLING);
        PALM_LEAVES = registerBlock(func, BOPBlocks.PALM_LEAVES);
        PALM_LOG = registerBlock(func, BOPBlocks.PALM_LOG);
        PALM_WOOD = registerBlock(func, BOPBlocks.PALM_WOOD);
        STRIPPED_PALM_LOG = registerBlock(func, BOPBlocks.STRIPPED_PALM_LOG);
        STRIPPED_PALM_WOOD = registerBlock(func, BOPBlocks.STRIPPED_PALM_WOOD);
        PALM_PLANKS = registerBlock(func, BOPBlocks.PALM_PLANKS);
        PALM_STAIRS = registerBlock(func, BOPBlocks.PALM_STAIRS);
        PALM_SLAB = registerBlock(func, BOPBlocks.PALM_SLAB);
        PALM_FENCE = registerBlock(func, BOPBlocks.PALM_FENCE);
        PALM_FENCE_GATE = registerBlock(func, BOPBlocks.PALM_FENCE_GATE);
        PALM_DOOR = registerBlock(func, BOPBlocks.PALM_DOOR);
        PALM_TRAPDOOR = registerBlock(func, BOPBlocks.PALM_TRAPDOOR);
        PALM_PRESSURE_PLATE = registerBlock(func, BOPBlocks.PALM_PRESSURE_PLATE);
        PALM_BUTTON = registerBlock(func, BOPBlocks.PALM_BUTTON);
        PALM_SHELF = registerBlock(func, BOPBlocks.PALM_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        WILLOW_SAPLING = registerBlock(func, BOPBlocks.WILLOW_SAPLING);
        WILLOW_VINE = registerBlock(func, BOPBlocks.WILLOW_VINE);
        SPANISH_MOSS = registerBlock(func, BOPBlocks.SPANISH_MOSS);
        SPANISH_MOSS_PLANT = registerBlock(func, BOPBlocks.SPANISH_MOSS_PLANT);
        WILLOW_LEAVES = registerBlock(func, BOPBlocks.WILLOW_LEAVES);
        WILLOW_LOG = registerBlock(func, BOPBlocks.WILLOW_LOG);
        WILLOW_WOOD = registerBlock(func, BOPBlocks.WILLOW_WOOD);
        STRIPPED_WILLOW_LOG = registerBlock(func, BOPBlocks.STRIPPED_WILLOW_LOG);
        STRIPPED_WILLOW_WOOD = registerBlock(func, BOPBlocks.STRIPPED_WILLOW_WOOD);
        WILLOW_PLANKS = registerBlock(func, BOPBlocks.WILLOW_PLANKS);
        WILLOW_STAIRS = registerBlock(func, BOPBlocks.WILLOW_STAIRS);
        WILLOW_SLAB = registerBlock(func, BOPBlocks.WILLOW_SLAB);
        WILLOW_FENCE = registerBlock(func, BOPBlocks.WILLOW_FENCE);
        WILLOW_FENCE_GATE = registerBlock(func, BOPBlocks.WILLOW_FENCE_GATE);
        WILLOW_DOOR = registerBlock(func, BOPBlocks.WILLOW_DOOR);
        WILLOW_TRAPDOOR = registerBlock(func, BOPBlocks.WILLOW_TRAPDOOR);
        WILLOW_PRESSURE_PLATE = registerBlock(func, BOPBlocks.WILLOW_PRESSURE_PLATE);
        WILLOW_BUTTON = registerBlock(func, BOPBlocks.WILLOW_BUTTON);
        WILLOW_SHELF = registerBlock(func, BOPBlocks.WILLOW_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        DEAD_SAPLING = registerBlock(func, BOPBlocks.DEAD_SAPLING);
        DEAD_BRANCH = registerBlock(func, BOPBlocks.DEAD_BRANCH);
        DEAD_LEAVES = registerBlock(func, BOPBlocks.DEAD_LEAVES);
        DEAD_LOG = registerBlock(func, BOPBlocks.DEAD_LOG);
        DEAD_WOOD = registerBlock(func, BOPBlocks.DEAD_WOOD);
        STRIPPED_DEAD_LOG = registerBlock(func, BOPBlocks.STRIPPED_DEAD_LOG);
        STRIPPED_DEAD_WOOD = registerBlock(func, BOPBlocks.STRIPPED_DEAD_WOOD);
        DEAD_PLANKS = registerBlock(func, BOPBlocks.DEAD_PLANKS);
        DEAD_STAIRS = registerBlock(func, BOPBlocks.DEAD_STAIRS);
        DEAD_SLAB = registerBlock(func, BOPBlocks.DEAD_SLAB);
        DEAD_FENCE = registerBlock(func, BOPBlocks.DEAD_FENCE);
        DEAD_FENCE_GATE = registerBlock(func, BOPBlocks.DEAD_FENCE_GATE);
        DEAD_DOOR = registerBlock(func, BOPBlocks.DEAD_DOOR);
        DEAD_TRAPDOOR = registerBlock(func, BOPBlocks.DEAD_TRAPDOOR);
        DEAD_PRESSURE_PLATE = registerBlock(func, BOPBlocks.DEAD_PRESSURE_PLATE);
        DEAD_BUTTON = registerBlock(func, BOPBlocks.DEAD_BUTTON);
        DEAD_SHELF = registerBlock(func, BOPBlocks.DEAD_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        MAGIC_SAPLING = registerBlock(func, BOPBlocks.MAGIC_SAPLING);
        MAGIC_LEAVES = registerBlock(func, BOPBlocks.MAGIC_LEAVES);
        MAGIC_LOG = registerBlock(func, BOPBlocks.MAGIC_LOG);
        MAGIC_WOOD = registerBlock(func, BOPBlocks.MAGIC_WOOD);
        STRIPPED_MAGIC_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAGIC_LOG);
        STRIPPED_MAGIC_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAGIC_WOOD);
        MAGIC_PLANKS = registerBlock(func, BOPBlocks.MAGIC_PLANKS);
        MAGIC_STAIRS = registerBlock(func, BOPBlocks.MAGIC_STAIRS);
        MAGIC_SLAB = registerBlock(func, BOPBlocks.MAGIC_SLAB);
        MAGIC_FENCE = registerBlock(func, BOPBlocks.MAGIC_FENCE);
        MAGIC_FENCE_GATE = registerBlock(func, BOPBlocks.MAGIC_FENCE_GATE);
        MAGIC_DOOR = registerBlock(func, BOPBlocks.MAGIC_DOOR);
        MAGIC_TRAPDOOR = registerBlock(func, BOPBlocks.MAGIC_TRAPDOOR);
        MAGIC_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAGIC_PRESSURE_PLATE);
        MAGIC_BUTTON = registerBlock(func, BOPBlocks.MAGIC_BUTTON);
        MAGIC_SHELF = registerBlock(func, BOPBlocks.MAGIC_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        UMBRAN_SAPLING = registerBlock(func, BOPBlocks.UMBRAN_SAPLING);
        UMBRAN_LEAVES = registerBlock(func, BOPBlocks.UMBRAN_LEAVES);
        UMBRAN_LOG = registerBlock(func, BOPBlocks.UMBRAN_LOG);
        UMBRAN_WOOD = registerBlock(func, BOPBlocks.UMBRAN_WOOD);
        STRIPPED_UMBRAN_LOG = registerBlock(func, BOPBlocks.STRIPPED_UMBRAN_LOG);
        STRIPPED_UMBRAN_WOOD = registerBlock(func, BOPBlocks.STRIPPED_UMBRAN_WOOD);
        UMBRAN_PLANKS = registerBlock(func, BOPBlocks.UMBRAN_PLANKS);
        UMBRAN_STAIRS = registerBlock(func, BOPBlocks.UMBRAN_STAIRS);
        UMBRAN_SLAB = registerBlock(func, BOPBlocks.UMBRAN_SLAB);
        UMBRAN_FENCE = registerBlock(func, BOPBlocks.UMBRAN_FENCE);
        UMBRAN_FENCE_GATE = registerBlock(func, BOPBlocks.UMBRAN_FENCE_GATE);
        UMBRAN_DOOR = registerBlock(func, BOPBlocks.UMBRAN_DOOR);
        UMBRAN_TRAPDOOR = registerBlock(func, BOPBlocks.UMBRAN_TRAPDOOR);
        UMBRAN_PRESSURE_PLATE = registerBlock(func, BOPBlocks.UMBRAN_PRESSURE_PLATE);
        UMBRAN_BUTTON = registerBlock(func, BOPBlocks.UMBRAN_BUTTON);
        UMBRAN_SHELF = registerBlock(func, BOPBlocks.UMBRAN_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        HELLBARK_SAPLING = registerBlock(func, BOPBlocks.HELLBARK_SAPLING);
        HELLBARK_LEAVES = registerBlock(func, BOPBlocks.HELLBARK_LEAVES);
        HELLBARK_LOG = registerBlock(func, BOPBlocks.HELLBARK_LOG);
        HELLBARK_WOOD = registerBlock(func, BOPBlocks.HELLBARK_WOOD);
        STRIPPED_HELLBARK_LOG = registerBlock(func, BOPBlocks.STRIPPED_HELLBARK_LOG);
        STRIPPED_HELLBARK_WOOD = registerBlock(func, BOPBlocks.STRIPPED_HELLBARK_WOOD);
        HELLBARK_PLANKS = registerBlock(func, BOPBlocks.HELLBARK_PLANKS);
        HELLBARK_STAIRS = registerBlock(func, BOPBlocks.HELLBARK_STAIRS);
        HELLBARK_SLAB = registerBlock(func, BOPBlocks.HELLBARK_SLAB);
        HELLBARK_FENCE = registerBlock(func, BOPBlocks.HELLBARK_FENCE);
        HELLBARK_FENCE_GATE = registerBlock(func, BOPBlocks.HELLBARK_FENCE_GATE);
        HELLBARK_DOOR = registerBlock(func, BOPBlocks.HELLBARK_DOOR);
        HELLBARK_TRAPDOOR = registerBlock(func, BOPBlocks.HELLBARK_TRAPDOOR);
        HELLBARK_PRESSURE_PLATE = registerBlock(func, BOPBlocks.HELLBARK_PRESSURE_PLATE);
        HELLBARK_BUTTON = registerBlock(func, BOPBlocks.HELLBARK_BUTTON);
        HELLBARK_SHELF = registerBlock(func, BOPBlocks.HELLBARK_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        EMPYREAL_SAPLING = registerBlock(func, BOPBlocks.EMPYREAL_SAPLING);
        EMPYREAL_LEAVES = registerBlock(func, BOPBlocks.EMPYREAL_LEAVES);
        EMPYREAL_LOG = registerBlock(func, BOPBlocks.EMPYREAL_LOG);
        EMPYREAL_WOOD = registerBlock(func, BOPBlocks.EMPYREAL_WOOD);
        STRIPPED_EMPYREAL_LOG = registerBlock(func, BOPBlocks.STRIPPED_EMPYREAL_LOG);
        STRIPPED_EMPYREAL_WOOD = registerBlock(func, BOPBlocks.STRIPPED_EMPYREAL_WOOD);
        EMPYREAL_PLANKS = registerBlock(func, BOPBlocks.EMPYREAL_PLANKS);
        EMPYREAL_STAIRS = registerBlock(func, BOPBlocks.EMPYREAL_STAIRS);
        EMPYREAL_SLAB = registerBlock(func, BOPBlocks.EMPYREAL_SLAB);
        EMPYREAL_FENCE = registerBlock(func, BOPBlocks.EMPYREAL_FENCE);
        EMPYREAL_FENCE_GATE = registerBlock(func, BOPBlocks.EMPYREAL_FENCE_GATE);
        EMPYREAL_DOOR = registerBlock(func, BOPBlocks.EMPYREAL_DOOR);
        EMPYREAL_TRAPDOOR = registerBlock(func, BOPBlocks.EMPYREAL_TRAPDOOR);
        EMPYREAL_PRESSURE_PLATE = registerBlock(func, BOPBlocks.EMPYREAL_PRESSURE_PLATE);
        EMPYREAL_BUTTON = registerBlock(func, BOPBlocks.EMPYREAL_BUTTON);
        EMPYREAL_SHELF = registerBlock(func, BOPBlocks.EMPYREAL_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY));

        FLOWER_BUD = registerBlock(func, BOPBlocks.FLOWER_BUD);
        ORIGIN_DANDELION = registerBlock(func, BOPBlocks.ORIGIN_DANDELION);
        ORIGIN_ROSE = registerBlock(func, BOPBlocks.ORIGIN_ROSE);
        MARIGOLD = registerBlock(func, BOPBlocks.MARIGOLD);
        VIOLET = registerBlock(func, BOPBlocks.VIOLET);
        LAVENDER = registerBlock(func, BOPBlocks.LAVENDER);
        TALL_LAVENDER = registerBlock(func, BOPBlocks.TALL_LAVENDER);
        WHITE_LAVENDER = registerBlock(func, BOPBlocks.WHITE_LAVENDER);
        TALL_WHITE_LAVENDER = registerBlock(func, BOPBlocks.TALL_WHITE_LAVENDER);
        BLUE_HYDRANGEA = registerBlock(func, BOPBlocks.BLUE_HYDRANGEA);
        PURPLE_WILDFLOWERS = registerBlock(func, BOPBlocks.PURPLE_WILDFLOWERS);
        GOLDENROD = registerBlock(func, BOPBlocks.GOLDENROD);
        ORANGE_COSMOS = registerBlock(func, BOPBlocks.ORANGE_COSMOS);
        PINK_DAFFODIL = registerBlock(func, BOPBlocks.PINK_DAFFODIL);
        PINK_HIBISCUS = registerBlock(func, BOPBlocks.PINK_HIBISCUS);
        WHITE_PETALS = registerBlock(func, BOPBlocks.WHITE_PETALS);
        ICY_IRIS = registerBlock(func, BOPBlocks.ICY_IRIS);
        GLOWFLOWER = registerBlock(func, BOPBlocks.GLOWFLOWER);
        WILTED_LILY = registerBlock(func, BOPBlocks.WILTED_LILY);
        BURNING_BLOSSOM = registerBlock(func, BOPBlocks.BURNING_BLOSSOM);
        ENDBLOOM = registerBlock(func, BOPBlocks.ENDBLOOM);
        SPROUT = registerBlock(func, BOPBlocks.SPROUT);
        HIGH_GRASS = registerBlock(func, BOPBlocks.HIGH_GRASS);
        HIGH_GRASS_PLANT = registerBlock(func, BOPBlocks.HIGH_GRASS_PLANT);
        CLOVER = registerBlock(func, BOPBlocks.CLOVER);
        HUGE_CLOVER_PETAL = registerBlock(func, BOPBlocks.HUGE_CLOVER_PETAL);
        HUGE_LILY_PAD = registerBlock(func, BOPBlocks.HUGE_LILY_PAD, PlaceOnWaterBlockItem::new);
        WATERLILY = registerBlock(func, BOPBlocks.WATERLILY, PlaceOnWaterBlockItem::new);
        DUNE_GRASS = registerBlock(func, BOPBlocks.DUNE_GRASS);
        DESERT_GRASS = registerBlock(func, BOPBlocks.DESERT_GRASS);
        DEAD_GRASS = registerBlock(func, BOPBlocks.DEAD_GRASS);
        TUNDRA_SHRUB = registerBlock(func, BOPBlocks.TUNDRA_SHRUB);
        ENDERPHYTE = registerBlock(func, BOPBlocks.ENDERPHYTE);
        LUMALOOP = registerBlock(func, BOPBlocks.LUMALOOP);
        LUMALOOP_PLANT = registerBlock(func, BOPBlocks.LUMALOOP_PLANT);
        BARLEY = registerBlock(func, BOPBlocks.BARLEY);
        SEA_OATS = registerBlock(func, BOPBlocks.SEA_OATS);
        CATTAIL = registerBlock(func, BOPBlocks.CATTAIL);
        REED = registerBlock(func, BOPBlocks.REED);
        WATERGRASS = registerBlock(func, BOPBlocks.WATERGRASS);
        TINY_CACTUS = registerBlock(func, BOPBlocks.TINY_CACTUS);
        BRAMBLE = registerBlock(func, BOPBlocks.BRAMBLE);
        BRAMBLE_LEAVES = registerBlock(func, BOPBlocks.BRAMBLE_LEAVES);

        POTTED_FLOWERING_OAK_SAPLING = registerBlock(func, BOPBlocks.POTTED_FLOWERING_OAK_SAPLING);
        POTTED_CYPRESS_SAPLING = registerBlock(func, BOPBlocks.POTTED_CYPRESS_SAPLING);
        POTTED_SNOWBLOSSOM_SAPLING = registerBlock(func, BOPBlocks.POTTED_SNOWBLOSSOM_SAPLING);
        POTTED_ORIGIN_OAK_SAPLING = registerBlock(func, BOPBlocks.POTTED_ORIGIN_OAK_SAPLING);
        POTTED_FIR_SAPLING = registerBlock(func, BOPBlocks.POTTED_FIR_SAPLING);
        POTTED_PINE_SAPLING = registerBlock(func, BOPBlocks.POTTED_PINE_SAPLING);
        POTTED_RED_MAPLE_SAPLING = registerBlock(func, BOPBlocks.POTTED_RED_MAPLE_SAPLING);
        POTTED_ORANGE_MAPLE_SAPLING = registerBlock(func, BOPBlocks.POTTED_ORANGE_MAPLE_SAPLING);
        POTTED_YELLOW_MAPLE_SAPLING = registerBlock(func, BOPBlocks.POTTED_YELLOW_MAPLE_SAPLING);
        POTTED_REDWOOD_SAPLING = registerBlock(func, BOPBlocks.POTTED_REDWOOD_SAPLING);
        POTTED_MAHOGANY_SAPLING = registerBlock(func, BOPBlocks.POTTED_MAHOGANY_SAPLING);
        POTTED_JACARANDA_SAPLING = registerBlock(func, BOPBlocks.POTTED_JACARANDA_SAPLING);
        POTTED_PALM_SAPLING = registerBlock(func, BOPBlocks.POTTED_PALM_SAPLING);
        POTTED_WILLOW_SAPLING = registerBlock(func, BOPBlocks.POTTED_WILLOW_SAPLING);
        POTTED_DEAD_SAPLING = registerBlock(func, BOPBlocks.POTTED_DEAD_SAPLING);
        POTTED_MAGIC_SAPLING = registerBlock(func, BOPBlocks.POTTED_MAGIC_SAPLING);
        POTTED_UMBRAN_SAPLING = registerBlock(func, BOPBlocks.POTTED_UMBRAN_SAPLING);
        POTTED_HELLBARK_SAPLING = registerBlock(func, BOPBlocks.POTTED_HELLBARK_SAPLING);
        POTTED_EMPYREAL_SAPLING = registerBlock(func, BOPBlocks.POTTED_EMPYREAL_SAPLING);
        POTTED_FLOWER_BUD = registerBlock(func, BOPBlocks.POTTED_FLOWER_BUD);
        POTTED_ORIGIN_DANDELION = registerBlock(func, BOPBlocks.POTTED_ORIGIN_DANDELION);
        POTTED_ORIGIN_ROSE = registerBlock(func, BOPBlocks.POTTED_ORIGIN_ROSE);
        POTTED_MARIGOLD = registerBlock(func, BOPBlocks.POTTED_MARIGOLD);
        POTTED_VIOLET = registerBlock(func, BOPBlocks.POTTED_VIOLET);
        POTTED_LAVENDER = registerBlock(func, BOPBlocks.POTTED_LAVENDER);
        POTTED_WHITE_LAVENDER = registerBlock(func, BOPBlocks.POTTED_WHITE_LAVENDER);
        POTTED_ORANGE_COSMOS = registerBlock(func, BOPBlocks.POTTED_ORANGE_COSMOS);
        POTTED_PINK_DAFFODIL = registerBlock(func, BOPBlocks.POTTED_PINK_DAFFODIL);
        POTTED_PINK_HIBISCUS = registerBlock(func, BOPBlocks.POTTED_PINK_HIBISCUS);
        POTTED_GLOWFLOWER = registerBlock(func, BOPBlocks.POTTED_GLOWFLOWER);
        POTTED_WILTED_LILY = registerBlock(func, BOPBlocks.POTTED_WILTED_LILY);
        POTTED_BURNING_BLOSSOM = registerBlock(func, BOPBlocks.POTTED_BURNING_BLOSSOM);
        POTTED_ENDBLOOM = registerBlock(func, BOPBlocks.POTTED_ENDBLOOM);
        POTTED_SPROUT = registerBlock(func, BOPBlocks.POTTED_SPROUT);
        POTTED_TINY_CACTUS = registerBlock(func, BOPBlocks.POTTED_TINY_CACTUS);
        POTTED_TOADSTOOL = registerBlock(func, BOPBlocks.POTTED_TOADSTOOL);
        POTTED_GLOWSHROOM = registerBlock(func, BOPBlocks.POTTED_GLOWSHROOM);
        //POTTED_VOIDCAP = registerBlock(func, BOPBlocks.POTTED_VOIDCAP);
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block)
    {
        return registerBlock(func, block, BlockItem::new);
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory)
    {
        return registerBlock(func, block, factory, new Item.Properties());
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block, UnaryOperator<Item.Properties> operator)
    {
        return registerBlock(func, block, (p_371022_, p_371023_) -> new BlockItem(p_371022_, operator.apply(p_371023_)), new Item.Properties());
    }

    public static Item registerBlock(BiConsumer<ResourceLocation, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, blockIdToItemId(block.builtInRegistryHolder().key()), p_370785_ -> factory.apply(block, p_370785_), properties.useBlockDescriptionPrefix()
        );
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        Item item = factory.apply(properties.setId(key));
        func.accept(key.location(), item);
        return item;
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, String name, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, itemId(name), factory, properties);
    }

    private static Item registerItem(BiConsumer<ResourceLocation, Item> func, String name, Item.Properties properties)
    {
        return registerItem(func, itemId(name), Item::new, properties);
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> key)
    {
        return ResourceKey.create(Registries.ITEM, key.location());
    }

    private static ResourceKey<Item> itemId(String name)
    {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name));
    }
}