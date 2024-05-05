/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class BOPBlockFamilies
{
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    private static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    private static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";
    public static final BlockFamily FIR_PLANKS = familyBuilder(BOPBlocks.FIR_PLANKS).button(BOPBlocks.FIR_BUTTON).fence(BOPBlocks.FIR_FENCE).fenceGate(BOPBlocks.FIR_FENCE_GATE).pressurePlate(BOPBlocks.FIR_PRESSURE_PLATE).sign(BOPBlocks.FIR_SIGN, BOPBlocks.FIR_WALL_SIGN).slab(BOPBlocks.FIR_SLAB).stairs(BOPBlocks.FIR_STAIRS).door(BOPBlocks.FIR_DOOR).trapdoor(BOPBlocks.FIR_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily PINE_PLANKS = familyBuilder(BOPBlocks.PINE_PLANKS).button(BOPBlocks.PINE_BUTTON).fence(BOPBlocks.PINE_FENCE).fenceGate(BOPBlocks.PINE_FENCE_GATE).pressurePlate(BOPBlocks.PINE_PRESSURE_PLATE).sign(BOPBlocks.PINE_SIGN, BOPBlocks.PINE_WALL_SIGN).slab(BOPBlocks.PINE_SLAB).stairs(BOPBlocks.PINE_STAIRS).door(BOPBlocks.PINE_DOOR).trapdoor(BOPBlocks.PINE_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily MAPLE_PLANKS = familyBuilder(BOPBlocks.MAPLE_PLANKS).button(BOPBlocks.MAPLE_BUTTON).fence(BOPBlocks.MAPLE_FENCE).fenceGate(BOPBlocks.MAPLE_FENCE_GATE).pressurePlate(BOPBlocks.MAPLE_PRESSURE_PLATE).sign(BOPBlocks.MAPLE_SIGN, BOPBlocks.MAPLE_WALL_SIGN).slab(BOPBlocks.MAPLE_SLAB).stairs(BOPBlocks.MAPLE_STAIRS).door(BOPBlocks.MAPLE_DOOR).trapdoor(BOPBlocks.MAPLE_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily REDWOOD_PLANKS = familyBuilder(BOPBlocks.REDWOOD_PLANKS).button(BOPBlocks.REDWOOD_BUTTON).fence(BOPBlocks.REDWOOD_FENCE).fenceGate(BOPBlocks.REDWOOD_FENCE_GATE).pressurePlate(BOPBlocks.REDWOOD_PRESSURE_PLATE).sign(BOPBlocks.REDWOOD_SIGN, BOPBlocks.REDWOOD_WALL_SIGN).slab(BOPBlocks.REDWOOD_SLAB).stairs(BOPBlocks.REDWOOD_STAIRS).door(BOPBlocks.REDWOOD_DOOR).trapdoor(BOPBlocks.REDWOOD_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily MAHOGANY_PLANKS = familyBuilder(BOPBlocks.MAHOGANY_PLANKS).button(BOPBlocks.MAHOGANY_BUTTON).fence(BOPBlocks.MAHOGANY_FENCE).fenceGate(BOPBlocks.MAHOGANY_FENCE_GATE).pressurePlate(BOPBlocks.MAHOGANY_PRESSURE_PLATE).sign(BOPBlocks.MAHOGANY_SIGN, BOPBlocks.MAHOGANY_WALL_SIGN).slab(BOPBlocks.MAHOGANY_SLAB).stairs(BOPBlocks.MAHOGANY_STAIRS).door(BOPBlocks.MAHOGANY_DOOR).trapdoor(BOPBlocks.MAHOGANY_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily JACARANDA_PLANKS = familyBuilder(BOPBlocks.JACARANDA_PLANKS).button(BOPBlocks.JACARANDA_BUTTON).fence(BOPBlocks.JACARANDA_FENCE).fenceGate(BOPBlocks.JACARANDA_FENCE_GATE).pressurePlate(BOPBlocks.JACARANDA_PRESSURE_PLATE).sign(BOPBlocks.JACARANDA_SIGN, BOPBlocks.JACARANDA_WALL_SIGN).slab(BOPBlocks.JACARANDA_SLAB).stairs(BOPBlocks.JACARANDA_STAIRS).door(BOPBlocks.JACARANDA_DOOR).trapdoor(BOPBlocks.JACARANDA_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily PALM_PLANKS = familyBuilder(BOPBlocks.PALM_PLANKS).button(BOPBlocks.PALM_BUTTON).fence(BOPBlocks.PALM_FENCE).fenceGate(BOPBlocks.PALM_FENCE_GATE).pressurePlate(BOPBlocks.PALM_PRESSURE_PLATE).sign(BOPBlocks.PALM_SIGN, BOPBlocks.PALM_WALL_SIGN).slab(BOPBlocks.PALM_SLAB).stairs(BOPBlocks.PALM_STAIRS).door(BOPBlocks.PALM_DOOR).trapdoor(BOPBlocks.PALM_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily WILLOW_PLANKS = familyBuilder(BOPBlocks.WILLOW_PLANKS).button(BOPBlocks.WILLOW_BUTTON).fence(BOPBlocks.WILLOW_FENCE).fenceGate(BOPBlocks.WILLOW_FENCE_GATE).pressurePlate(BOPBlocks.WILLOW_PRESSURE_PLATE).sign(BOPBlocks.WILLOW_SIGN, BOPBlocks.WILLOW_WALL_SIGN).slab(BOPBlocks.WILLOW_SLAB).stairs(BOPBlocks.WILLOW_STAIRS).door(BOPBlocks.WILLOW_DOOR).trapdoor(BOPBlocks.WILLOW_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily DEAD_PLANKS = familyBuilder(BOPBlocks.DEAD_PLANKS).button(BOPBlocks.DEAD_BUTTON).fence(BOPBlocks.DEAD_FENCE).fenceGate(BOPBlocks.DEAD_FENCE_GATE).pressurePlate(BOPBlocks.DEAD_PRESSURE_PLATE).sign(BOPBlocks.DEAD_SIGN, BOPBlocks.DEAD_WALL_SIGN).slab(BOPBlocks.DEAD_SLAB).stairs(BOPBlocks.DEAD_STAIRS).door(BOPBlocks.DEAD_DOOR).trapdoor(BOPBlocks.DEAD_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily MAGIC_PLANKS = familyBuilder(BOPBlocks.MAGIC_PLANKS).button(BOPBlocks.MAGIC_BUTTON).fence(BOPBlocks.MAGIC_FENCE).fenceGate(BOPBlocks.MAGIC_FENCE_GATE).pressurePlate(BOPBlocks.MAGIC_PRESSURE_PLATE).sign(BOPBlocks.MAGIC_SIGN, BOPBlocks.MAGIC_WALL_SIGN).slab(BOPBlocks.MAGIC_SLAB).stairs(BOPBlocks.MAGIC_STAIRS).door(BOPBlocks.MAGIC_DOOR).trapdoor(BOPBlocks.MAGIC_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily UMBRAN_PLANKS = familyBuilder(BOPBlocks.UMBRAN_PLANKS).button(BOPBlocks.UMBRAN_BUTTON).fence(BOPBlocks.UMBRAN_FENCE).fenceGate(BOPBlocks.UMBRAN_FENCE_GATE).pressurePlate(BOPBlocks.UMBRAN_PRESSURE_PLATE).sign(BOPBlocks.UMBRAN_SIGN, BOPBlocks.UMBRAN_WALL_SIGN).slab(BOPBlocks.UMBRAN_SLAB).stairs(BOPBlocks.UMBRAN_STAIRS).door(BOPBlocks.UMBRAN_DOOR).trapdoor(BOPBlocks.UMBRAN_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily HELLBARK_PLANKS = familyBuilder(BOPBlocks.HELLBARK_PLANKS).button(BOPBlocks.HELLBARK_BUTTON).fence(BOPBlocks.HELLBARK_FENCE).fenceGate(BOPBlocks.HELLBARK_FENCE_GATE).pressurePlate(BOPBlocks.HELLBARK_PRESSURE_PLATE).sign(BOPBlocks.HELLBARK_SIGN, BOPBlocks.HELLBARK_WALL_SIGN).slab(BOPBlocks.HELLBARK_SLAB).stairs(BOPBlocks.HELLBARK_STAIRS).door(BOPBlocks.HELLBARK_DOOR).trapdoor(BOPBlocks.HELLBARK_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily EMPYREAL_PLANKS = familyBuilder(BOPBlocks.EMPYREAL_PLANKS).button(BOPBlocks.EMPYREAL_BUTTON).fence(BOPBlocks.EMPYREAL_FENCE).fenceGate(BOPBlocks.EMPYREAL_FENCE_GATE).pressurePlate(BOPBlocks.EMPYREAL_PRESSURE_PLATE).sign(BOPBlocks.EMPYREAL_SIGN, BOPBlocks.EMPYREAL_WALL_SIGN).slab(BOPBlocks.EMPYREAL_SLAB).stairs(BOPBlocks.EMPYREAL_STAIRS).door(BOPBlocks.EMPYREAL_DOOR).trapdoor(BOPBlocks.EMPYREAL_TRAPDOOR).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();
    public static final BlockFamily WHITE_SANDSTONE = familyBuilder(BOPBlocks.WHITE_SANDSTONE).wall(BOPBlocks.WHITE_SANDSTONE_WALL).stairs(BOPBlocks.WHITE_SANDSTONE_STAIRS).slab(BOPBlocks.WHITE_SANDSTONE_SLAB).chiseled(BOPBlocks.CHISELED_WHITE_SANDSTONE).cut(BOPBlocks.CUT_WHITE_SANDSTONE).dontGenerateRecipe().getFamily();
    public static final BlockFamily CUT_WHITE_SANDSTONE = familyBuilder(BOPBlocks.CUT_WHITE_SANDSTONE).slab(BOPBlocks.CUT_WHITE_SANDSTONE_SLAB).getFamily();
    public static final BlockFamily SMOOTH_WHITE_SANDSTONE = familyBuilder(BOPBlocks.SMOOTH_WHITE_SANDSTONE).slab(BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB).stairs(BOPBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS).getFamily();
    public static final BlockFamily ORANGE_SANDSTONE = familyBuilder(BOPBlocks.ORANGE_SANDSTONE).wall(BOPBlocks.ORANGE_SANDSTONE_WALL).stairs(BOPBlocks.ORANGE_SANDSTONE_STAIRS).slab(BOPBlocks.ORANGE_SANDSTONE_SLAB).chiseled(BOPBlocks.CHISELED_ORANGE_SANDSTONE).cut(BOPBlocks.CUT_ORANGE_SANDSTONE).dontGenerateRecipe().getFamily();
    public static final BlockFamily CUT_ORANGE_SANDSTONE = familyBuilder(BOPBlocks.CUT_ORANGE_SANDSTONE).slab(BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB).getFamily();
    public static final BlockFamily SMOOTH_ORANGE_SANDSTONE = familyBuilder(BOPBlocks.SMOOTH_ORANGE_SANDSTONE).slab(BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB).stairs(BOPBlocks.SMOOTH_ORANGE_SANDSTONE_STAIRS).getFamily();
    public static final BlockFamily BLACK_SANDSTONE = familyBuilder(BOPBlocks.BLACK_SANDSTONE).wall(BOPBlocks.BLACK_SANDSTONE_WALL).stairs(BOPBlocks.BLACK_SANDSTONE_STAIRS).slab(BOPBlocks.BLACK_SANDSTONE_SLAB).chiseled(BOPBlocks.CHISELED_BLACK_SANDSTONE).cut(BOPBlocks.CUT_BLACK_SANDSTONE).dontGenerateRecipe().getFamily();
    public static final BlockFamily CUT_BLACK_SANDSTONE = familyBuilder(BOPBlocks.CUT_BLACK_SANDSTONE).slab(BOPBlocks.CUT_BLACK_SANDSTONE_SLAB).getFamily();
    public static final BlockFamily SMOOTH_BLACK_SANDSTONE = familyBuilder(BOPBlocks.SMOOTH_BLACK_SANDSTONE).slab(BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB).stairs(BOPBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS).getFamily();
    public static final BlockFamily BRIMSTONE_BRICK = familyBuilder(BOPBlocks.BRIMSTONE_BRICKS).wall(BOPBlocks.BRIMSTONE_BRICK_WALL).stairs(BOPBlocks.BRIMSTONE_BRICK_STAIRS).slab(BOPBlocks.BRIMSTONE_BRICK_SLAB).chiseled(BOPBlocks.CHISELED_BRIMSTONE_BRICKS).dontGenerateRecipe().getFamily();

    private static BlockFamily.Builder familyBuilder(Block block)
    {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(block);
        BlockFamily blockfamily = MAP.put(block, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(block));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
