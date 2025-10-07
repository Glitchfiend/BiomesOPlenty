/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.datagen.provider;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.neoforge.datagen.BOPBlockFamilies;
import biomesoplenty.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BOPRecipeProvider extends RecipeProvider
{
    public BOPRecipeProvider(HolderLookup.Provider provider, RecipeOutput output)
    {
        super(provider, output);
    }

    @Override
    protected void buildRecipes()
    {
        generateForEnabledBlockFamiliesBOP(FeatureFlagSet.of(FeatureFlags.VANILLA));

        // Planks from Logs
        planksFromLogs(BOPBlocks.ORIGIN_OAK_PLANKS, ModTags.Items.ORIGIN_OAK_LOGS, 4);
        planksFromLogs(BOPBlocks.FIR_PLANKS, ModTags.Items.FIR_LOGS, 4);
        planksFromLogs(BOPBlocks.PINE_PLANKS, ModTags.Items.PINE_LOGS, 4);
        planksFromLogs(BOPBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS, 4);
        planksFromLogs(BOPBlocks.REDWOOD_PLANKS, ModTags.Items.REDWOOD_LOGS, 4);
        planksFromLogs(BOPBlocks.MAHOGANY_PLANKS, ModTags.Items.MAHOGANY_LOGS, 4);
        planksFromLogs(BOPBlocks.JACARANDA_PLANKS, ModTags.Items.JACARANDA_LOGS, 4);
        planksFromLogs(BOPBlocks.PALM_PLANKS, ModTags.Items.PALM_LOGS, 4);
        planksFromLogs(BOPBlocks.WILLOW_PLANKS, ModTags.Items.WILLOW_LOGS, 4);
        planksFromLogs(BOPBlocks.DEAD_PLANKS, ModTags.Items.DEAD_LOGS, 4);
        planksFromLogs(BOPBlocks.MAGIC_PLANKS, ModTags.Items.MAGIC_LOGS, 4);
        planksFromLogs(BOPBlocks.UMBRAN_PLANKS, ModTags.Items.UMBRAN_LOGS, 4);
        planksFromLogs(BOPBlocks.HELLBARK_PLANKS, ModTags.Items.HELLBARK_LOGS, 4);
        planksFromLogs(BOPBlocks.EMPYREAL_PLANKS, ModTags.Items.EMPYREAL_LOGS, 4);

        // Wood from Logs
        woodFromLogs(BOPBlocks.ORIGIN_OAK_WOOD, BOPBlocks.ORIGIN_OAK_LOG);
        woodFromLogs(BOPBlocks.FIR_WOOD, BOPBlocks.FIR_LOG);
        woodFromLogs(BOPBlocks.PINE_WOOD, BOPBlocks.PINE_LOG);
        woodFromLogs(BOPBlocks.MAPLE_WOOD, BOPBlocks.MAPLE_LOG);
        woodFromLogs(BOPBlocks.REDWOOD_WOOD, BOPBlocks.REDWOOD_LOG);
        woodFromLogs(BOPBlocks.MAHOGANY_WOOD, BOPBlocks.MAHOGANY_LOG);
        woodFromLogs(BOPBlocks.JACARANDA_WOOD, BOPBlocks.JACARANDA_LOG);
        woodFromLogs(BOPBlocks.PALM_WOOD, BOPBlocks.PALM_LOG);
        woodFromLogs(BOPBlocks.WILLOW_WOOD, BOPBlocks.WILLOW_LOG);
        woodFromLogs(BOPBlocks.DEAD_WOOD, BOPBlocks.DEAD_LOG);
        woodFromLogs(BOPBlocks.MAGIC_WOOD, BOPBlocks.MAGIC_LOG);
        woodFromLogs(BOPBlocks.UMBRAN_WOOD, BOPBlocks.UMBRAN_LOG);
        woodFromLogs(BOPBlocks.HELLBARK_WOOD, BOPBlocks.HELLBARK_LOG);
        woodFromLogs(BOPBlocks.EMPYREAL_WOOD, BOPBlocks.EMPYREAL_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_ORIGIN_OAK_WOOD, BOPBlocks.STRIPPED_ORIGIN_OAK_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_FIR_WOOD, BOPBlocks.STRIPPED_FIR_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_PINE_WOOD, BOPBlocks.STRIPPED_PINE_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_MAPLE_WOOD, BOPBlocks.STRIPPED_MAPLE_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_REDWOOD_WOOD, BOPBlocks.STRIPPED_REDWOOD_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_MAHOGANY_WOOD, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_JACARANDA_WOOD, BOPBlocks.STRIPPED_JACARANDA_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_PALM_WOOD, BOPBlocks.STRIPPED_PALM_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_WILLOW_WOOD, BOPBlocks.STRIPPED_WILLOW_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_DEAD_WOOD, BOPBlocks.STRIPPED_DEAD_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_MAGIC_WOOD, BOPBlocks.STRIPPED_MAGIC_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_UMBRAN_WOOD, BOPBlocks.STRIPPED_UMBRAN_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_HELLBARK_WOOD, BOPBlocks.STRIPPED_HELLBARK_LOG);
        woodFromLogs(BOPBlocks.STRIPPED_EMPYREAL_WOOD, BOPBlocks.STRIPPED_EMPYREAL_LOG);

        // Boats
        woodenBoat(BOPItems.ORIGIN_OAK_BOAT, BOPBlocks.ORIGIN_OAK_PLANKS);
        woodenBoat(BOPItems.FIR_BOAT, BOPBlocks.FIR_PLANKS);
        woodenBoat(BOPItems.PINE_BOAT, BOPBlocks.PINE_PLANKS);
        woodenBoat(BOPItems.MAPLE_BOAT, BOPBlocks.MAPLE_PLANKS);
        woodenBoat(BOPItems.REDWOOD_BOAT, BOPBlocks.REDWOOD_PLANKS);
        woodenBoat(BOPItems.MAHOGANY_BOAT, BOPBlocks.MAHOGANY_PLANKS);
        woodenBoat(BOPItems.JACARANDA_BOAT, BOPBlocks.JACARANDA_PLANKS);
        woodenBoat(BOPItems.PALM_BOAT, BOPBlocks.PALM_PLANKS);
        woodenBoat(BOPItems.WILLOW_BOAT, BOPBlocks.WILLOW_PLANKS);
        woodenBoat(BOPItems.DEAD_BOAT, BOPBlocks.DEAD_PLANKS);
        woodenBoat(BOPItems.MAGIC_BOAT, BOPBlocks.MAGIC_PLANKS);
        woodenBoat(BOPItems.UMBRAN_BOAT, BOPBlocks.UMBRAN_PLANKS);
        woodenBoat(BOPItems.HELLBARK_BOAT, BOPBlocks.HELLBARK_PLANKS);
        woodenBoat(BOPItems.EMPYREAL_BOAT, BOPBlocks.EMPYREAL_PLANKS);
        chestBoat(BOPItems.ORIGIN_OAK_CHEST_BOAT, BOPItems.ORIGIN_OAK_BOAT);
        chestBoat(BOPItems.FIR_CHEST_BOAT, BOPItems.FIR_BOAT);
        chestBoat(BOPItems.PINE_CHEST_BOAT, BOPItems.PINE_BOAT);
        chestBoat(BOPItems.MAPLE_CHEST_BOAT, BOPItems.MAPLE_BOAT);
        chestBoat(BOPItems.REDWOOD_CHEST_BOAT, BOPItems.REDWOOD_BOAT);
        chestBoat(BOPItems.MAHOGANY_CHEST_BOAT, BOPItems.MAHOGANY_BOAT);
        chestBoat(BOPItems.JACARANDA_CHEST_BOAT, BOPItems.JACARANDA_BOAT);
        chestBoat(BOPItems.PALM_CHEST_BOAT, BOPItems.PALM_BOAT);
        chestBoat(BOPItems.WILLOW_CHEST_BOAT, BOPItems.WILLOW_BOAT);
        chestBoat(BOPItems.DEAD_CHEST_BOAT, BOPItems.DEAD_BOAT);
        chestBoat(BOPItems.MAGIC_CHEST_BOAT, BOPItems.MAGIC_BOAT);
        chestBoat(BOPItems.UMBRAN_CHEST_BOAT, BOPItems.UMBRAN_BOAT);
        chestBoat(BOPItems.HELLBARK_CHEST_BOAT, BOPItems.HELLBARK_BOAT);
        chestBoat(BOPItems.EMPYREAL_CHEST_BOAT, BOPItems.EMPYREAL_BOAT);

        // Shelves
        shelf(BOPBlocks.ORIGIN_OAK_SHELF, BOPItems.STRIPPED_ORIGIN_OAK_LOG);
        shelf(BOPBlocks.FIR_SHELF, BOPItems.STRIPPED_FIR_LOG);
        shelf(BOPBlocks.PINE_SHELF, BOPItems.STRIPPED_PINE_LOG);
        shelf(BOPBlocks.MAPLE_SHELF, BOPItems.STRIPPED_MAPLE_LOG);
        shelf(BOPBlocks.REDWOOD_SHELF, BOPItems.STRIPPED_REDWOOD_LOG);
        shelf(BOPBlocks.MAHOGANY_SHELF, BOPItems.STRIPPED_MAHOGANY_LOG);
        shelf(BOPBlocks.JACARANDA_SHELF, BOPItems.STRIPPED_JACARANDA_LOG);
        shelf(BOPBlocks.PALM_SHELF, BOPItems.STRIPPED_PALM_LOG);
        shelf(BOPBlocks.WILLOW_SHELF, BOPItems.STRIPPED_WILLOW_LOG);
        shelf(BOPBlocks.DEAD_SHELF, BOPItems.STRIPPED_DEAD_LOG);
        shelf(BOPBlocks.MAGIC_SHELF, BOPItems.STRIPPED_MAGIC_LOG);
        shelf(BOPBlocks.UMBRAN_SHELF, BOPItems.STRIPPED_UMBRAN_LOG);
        shelf(BOPBlocks.HELLBARK_SHELF, BOPItems.STRIPPED_HELLBARK_LOG);
        shelf(BOPBlocks.EMPYREAL_SHELF, BOPItems.STRIPPED_EMPYREAL_LOG);

        // Hanging Signs
        hangingSign(BOPItems.ORIGIN_OAK_HANGING_SIGN, BOPBlocks.STRIPPED_ORIGIN_OAK_LOG);
        hangingSign(BOPItems.FIR_HANGING_SIGN, BOPBlocks.STRIPPED_FIR_LOG);
        hangingSign(BOPItems.PINE_HANGING_SIGN, BOPBlocks.STRIPPED_PINE_LOG);
        hangingSign(BOPItems.MAPLE_HANGING_SIGN, BOPBlocks.STRIPPED_MAPLE_LOG);
        hangingSign(BOPItems.REDWOOD_HANGING_SIGN, BOPBlocks.STRIPPED_REDWOOD_LOG);
        hangingSign(BOPItems.MAHOGANY_HANGING_SIGN, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        hangingSign(BOPItems.JACARANDA_HANGING_SIGN, BOPBlocks.STRIPPED_JACARANDA_LOG);
        hangingSign(BOPItems.PALM_HANGING_SIGN, BOPBlocks.STRIPPED_PALM_LOG);
        hangingSign(BOPItems.WILLOW_HANGING_SIGN, BOPBlocks.STRIPPED_WILLOW_LOG);
        hangingSign(BOPItems.DEAD_HANGING_SIGN, BOPBlocks.STRIPPED_DEAD_LOG);
        hangingSign(BOPItems.MAGIC_HANGING_SIGN, BOPBlocks.STRIPPED_MAGIC_LOG);
        hangingSign(BOPItems.UMBRAN_HANGING_SIGN, BOPBlocks.STRIPPED_UMBRAN_LOG);
        hangingSign(BOPItems.HELLBARK_HANGING_SIGN, BOPBlocks.STRIPPED_HELLBARK_LOG);
        hangingSign(BOPItems.EMPYREAL_HANGING_SIGN, BOPBlocks.STRIPPED_EMPYREAL_LOG);

        // White Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_WHITE_SANDSTONE, Ingredient.of(BOPBlocks.WHITE_SANDSTONE_SLAB)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", has(BOPBlocks.CUT_WHITE_SANDSTONE)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE).define('#', BOPBlocks.WHITE_SAND).pattern("##").pattern("##").unlockedBy("has_white_sand", has(BOPBlocks.WHITE_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.WHITE_SANDSTONE, BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.WHITE_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.WHITE_SANDSTONE, BOPBlocks.CHISELED_WHITE_SANDSTONE, BOPBlocks.CUT_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", has(BOPBlocks.CUT_WHITE_SANDSTONE)).save(output);
        cut(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        wall(RecipeCategory.DECORATIONS, BOPBlocks.WHITE_SANDSTONE_WALL, BOPBlocks.WHITE_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.WHITE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_SLAB, BOPBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, BOPBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, BOPBlocks.CUT_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_STAIRS, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.WHITE_SANDSTONE_WALL, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, BOPBlocks.SMOOTH_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_WHITE_SANDSTONE);

        // Orange Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_ORANGE_SANDSTONE, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE_SLAB)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_cut_orange_sandstone", has(BOPBlocks.CUT_ORANGE_SANDSTONE)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE).define('#', BOPBlocks.ORANGE_SAND).pattern("##").pattern("##").unlockedBy("has_orange_sand", has(BOPBlocks.ORANGE_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE, BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.ORANGE_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE, BOPBlocks.CHISELED_ORANGE_SANDSTONE, BOPBlocks.CUT_ORANGE_SANDSTONE)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_cut_orange_sandstone", has(BOPBlocks.CUT_ORANGE_SANDSTONE)).save(output);
        cut(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        wall(RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_SANDSTONE_WALL, BOPBlocks.ORANGE_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.ORANGE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_SLAB, BOPBlocks.ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, BOPBlocks.ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, BOPBlocks.CUT_ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_STAIRS, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_SANDSTONE_WALL, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, BOPBlocks.SMOOTH_ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE);

        // Black Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BLACK_SANDSTONE, Ingredient.of(BOPBlocks.BLACK_SANDSTONE_SLAB)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_cut_black_sandstone", has(BOPBlocks.CUT_BLACK_SANDSTONE)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE).define('#', BOPBlocks.BLACK_SAND).pattern("##").pattern("##").unlockedBy("has_black_sand", has(BOPBlocks.BLACK_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.BLACK_SANDSTONE, BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.BLACK_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.BLACK_SANDSTONE, BOPBlocks.CHISELED_BLACK_SANDSTONE, BOPBlocks.CUT_BLACK_SANDSTONE)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_cut_black_sandstone", has(BOPBlocks.CUT_BLACK_SANDSTONE)).save(output);
        cut(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        wall(RecipeCategory.DECORATIONS, BOPBlocks.BLACK_SANDSTONE_WALL, BOPBlocks.BLACK_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.BLACK_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_SLAB, BOPBlocks.BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BOPBlocks.BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BOPBlocks.CUT_BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_STAIRS, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BLACK_SANDSTONE_WALL, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, BOPBlocks.SMOOTH_BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_BLACK_SANDSTONE);

        // Brimstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, Ingredient.of(BOPBlocks.BRIMSTONE_BRICK_SLAB)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_chiseled_brimstone_bricks", has(BOPBlocks.CHISELED_BRIMSTONE_BRICKS)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICKS, 4).define('#', BOPBlocks.BRIMSTONE).pattern("##").pattern("##").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, Ingredient.of(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, BOPBlocks.BRIMSTONE_BRICKS, 2);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, BOPBlocks.BRIMSTONE, 2);
        stairBuilder(BOPBlocks.BRIMSTONE_BRICK_STAIRS, Ingredient.of(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).save(output);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_STAIRS, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_STAIRS, BOPBlocks.BRIMSTONE);
        wall(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE);
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_CLUSTER, 2).define('#', BOPBlocks.BRIMSTONE).pattern("#").pattern("#").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_CLUSTER, BOPBlocks.BRIMSTONE, 2);
        this.shapeless(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BUD, 4).requires(BOPBlocks.BRIMSTONE).unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BUD, BOPBlocks.BRIMSTONE, 4);
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_FUMAROLE).define('#', BOPBlocks.BRIMSTONE).define('F', Items.FIRE_CHARGE).pattern(" # ").pattern("#F#").pattern("###").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);

        // Blackstone Decorations
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_BULB, 2).define('#', Blocks.BLACKSTONE).define('O', Blocks.CRYING_OBSIDIAN).pattern("O").pattern("#").unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(output);
        this.shapeless(RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_SPINES, 4).requires(Blocks.BLACKSTONE).unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(output);
        stonecutterResultFromBase(RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_SPINES, Blocks.BLACKSTONE, 4);

        // End Stone Blocks
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ALGAL_END_STONE).requires(Blocks.END_STONE).requires(BOPBlocks.ENDERPHYTE).unlockedBy("has_enderphyte", has(BOPBlocks.ENDERPHYTE)).save(output);
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.NULL_END_STONE).requires(Blocks.END_STONE).requires(BOPBlocks.NULL_LEAVES).unlockedBy("has_null_leaves", has(BOPBlocks.NULL_LEAVES)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.UNMAPPED_END_STONE, 4).define('#', Blocks.END_STONE).define('B', BOPItems.LIQUID_NULL_BUCKET).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy("has_liquid_null_bucket", has(BOPItems.LIQUID_NULL_BUCKET)).save(output);

        // Other Building Blocks
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.MOSSY_BLACK_SAND).requires(BOPBlocks.BLACK_SAND).requires(Blocks.MOSS_BLOCK).unlockedBy("has_moss_block", has(Blocks.MOSS_BLOCK)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.FLESH).define('#', Items.ROTTEN_FLESH).define('B', BOPItems.BLOOD_BUCKET).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy("has_blood_bucket", has(BOPItems.BLOOD_BUCKET)).save(output);
        this.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ROSE_QUARTZ_BLOCK).define('#', BOPItems.ROSE_QUARTZ_CHUNK).pattern("##").pattern("##").unlockedBy("has_rose_quartz_chunk", has(BOPItems.ROSE_QUARTZ_CHUNK)).save(output);

        // Leaf Litter
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.RED_MAPLE_LEAF_LITTER, 12).define('#', BOPBlocks.RED_MAPLE_LEAVES).pattern("###").unlockedBy("has_red_maple_leaves", has(BOPBlocks.RED_MAPLE_LEAVES)).save(output);
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_MAPLE_LEAF_LITTER, 12).define('#', BOPBlocks.ORANGE_MAPLE_LEAVES).pattern("###").unlockedBy("has_orange_maple_leaves", has(BOPBlocks.ORANGE_MAPLE_LEAVES)).save(output);
        this.shaped(RecipeCategory.DECORATIONS, BOPBlocks.YELLOW_MAPLE_LEAF_LITTER, 12).define('#', BOPBlocks.YELLOW_MAPLE_LEAVES).pattern("###").unlockedBy("has_yellow_maple_leaves", has(BOPBlocks.YELLOW_MAPLE_LEAVES)).save(output);

        // Dyes
        oneToOneConversionRecipe(Items.BROWN_DYE, BOPBlocks.CATTAIL, "brown_dye", 2);
        oneToOneConversionRecipe(Items.CYAN_DYE, BOPBlocks.GLOWFLOWER, "cyan_dye");
        oneToOneConversionRecipe(Items.GRAY_DYE, BOPBlocks.WILTED_LILY, "gray_dye");
        oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, BOPBlocks.BLUE_HYDRANGEA, "light_blue_dye", 2);
        oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, BOPBlocks.ICY_IRIS, "light_blue_dye", 2);
        oneToOneConversionRecipe(Items.LIGHT_GRAY_DYE, BOPBlocks.ENDBLOOM, "light_gray_dye");
        oneToOneConversionRecipe(Items.MAGENTA_DYE, BOPBlocks.PURPLE_WILDFLOWERS, "magenta_dye");
        oneToOneConversionRecipe(Items.ORANGE_DYE, BOPBlocks.BURNING_BLOSSOM, "orange_dye");
        oneToOneConversionRecipe(Items.ORANGE_DYE, BOPBlocks.ORANGE_COSMOS, "orange_dye");
        oneToOneConversionRecipe(Items.ORANGE_DYE, BOPBlocks.MARIGOLD, "orange_dye");
        oneToOneConversionRecipe(Items.PINK_DYE, BOPBlocks.PINK_DAFFODIL, "pink_dye");
        oneToOneConversionRecipe(Items.PINK_DYE, BOPBlocks.PINK_HIBISCUS, "pink_dye");
        oneToOneConversionRecipe(Items.PURPLE_DYE, BOPBlocks.LAVENDER, "purple_dye");
        oneToOneConversionRecipe(Items.PURPLE_DYE, BOPBlocks.TALL_LAVENDER, "purple_dye", 2);
        oneToOneConversionRecipe(Items.PURPLE_DYE, BOPBlocks.VIOLET, "purple_dye");
        oneToOneConversionRecipe(Items.RED_DYE, BOPBlocks.ORIGIN_ROSE, "red_dye");
        oneToOneConversionRecipe(Items.RED_DYE, BOPBlocks.WATERLILY, "red_dye");
        oneToOneConversionRecipe(Items.WHITE_DYE, BOPBlocks.WHITE_LAVENDER, "white_dye");
        oneToOneConversionRecipe(Items.WHITE_DYE, BOPBlocks.TALL_WHITE_LAVENDER, "white_dye", 2);
        oneToOneConversionRecipe(Items.WHITE_DYE, BOPBlocks.WHITE_PETALS, "white_dye");
        oneToOneConversionRecipe(Items.YELLOW_DYE, BOPBlocks.ORIGIN_DANDELION, "yellow_dye");
        oneToOneConversionRecipe(Items.YELLOW_DYE, BOPBlocks.GOLDENROD, "yellow_dye", 2);

        oneToOneConversionRecipe(Items.WHITE_DYE, BOPBlocks.WHITE_FLOWER_PETAL_BLOCK, "white_dye", 4);
        oneToOneConversionRecipe(Items.LIGHT_GRAY_DYE, BOPBlocks.LIGHT_GRAY_FLOWER_PETAL_BLOCK, "light_gray_dye", 4);
        oneToOneConversionRecipe(Items.GRAY_DYE, BOPBlocks.GRAY_FLOWER_PETAL_BLOCK, "gray_dye", 4);
        oneToOneConversionRecipe(Items.BLACK_DYE, BOPBlocks.BLACK_FLOWER_PETAL_BLOCK, "black_dye", 4);
        oneToOneConversionRecipe(Items.BROWN_DYE, BOPBlocks.BROWN_FLOWER_PETAL_BLOCK, "brown_dye", 4);
        oneToOneConversionRecipe(Items.RED_DYE, BOPBlocks.RED_FLOWER_PETAL_BLOCK, "red_dye", 4);
        oneToOneConversionRecipe(Items.ORANGE_DYE, BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK, "orange_dye", 4);
        oneToOneConversionRecipe(Items.YELLOW_DYE, BOPBlocks.YELLOW_FLOWER_PETAL_BLOCK, "yellow_dye", 4);
        oneToOneConversionRecipe(Items.LIME_DYE, BOPBlocks.LIME_FLOWER_PETAL_BLOCK, "lime_dye", 4);
        oneToOneConversionRecipe(Items.GREEN_DYE, BOPBlocks.GREEN_FLOWER_PETAL_BLOCK, "green_dye", 4);
        oneToOneConversionRecipe(Items.CYAN_DYE, BOPBlocks.CYAN_FLOWER_PETAL_BLOCK, "cyan_dye", 4);
        oneToOneConversionRecipe(Items.LIGHT_BLUE_DYE, BOPBlocks.LIGHT_BLUE_FLOWER_PETAL_BLOCK, "light_blue_dye", 4);
        oneToOneConversionRecipe(Items.BLUE_DYE, BOPBlocks.BLUE_FLOWER_PETAL_BLOCK, "blue_dye", 4);
        oneToOneConversionRecipe(Items.PURPLE_DYE, BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK, "purple_dye", 4);
        oneToOneConversionRecipe(Items.MAGENTA_DYE, BOPBlocks.MAGENTA_FLOWER_PETAL_BLOCK, "magenta_dye", 4);
        oneToOneConversionRecipe(Items.PINK_DYE, BOPBlocks.PINK_FLOWER_PETAL_BLOCK, "pink_dye", 4);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.TINY_CACTUS.asItem()), RecipeCategory.MISC, Items.GREEN_DYE, 1.0F, 200).unlockedBy("has_tiny_cactus", has(BOPBlocks.TINY_CACTUS)).save(this.output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Items.GREEN_DYE, BOPBlocks.TINY_CACTUS));

        // Vanilla Parity Recipes
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_COBBLESTONE).requires(Blocks.COBBLESTONE).requires(BOPBlocks.WILLOW_VINE).group("mossy_cobblestone").unlockedBy("has_willow_vine", has(BOPBlocks.WILLOW_VINE)).save(this.output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Blocks.MOSSY_COBBLESTONE, BOPBlocks.WILLOW_VINE));
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_STONE_BRICKS).requires(Blocks.STONE_BRICKS).requires(BOPBlocks.WILLOW_VINE).group("mossy_stone_bricks").unlockedBy("has_willow_vine", has(BOPBlocks.WILLOW_VINE)).save(this.output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Blocks.MOSSY_STONE_BRICKS, BOPBlocks.WILLOW_VINE));
        this.shapeless(RecipeCategory.FOOD, Items.RABBIT_STEW).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.BOWL).requires(Items.CARROT).requires(BOPBlocks.TOADSTOOL).group("rabbit_stew").unlockedBy("has_cooked_rabbit", has(Items.COOKED_RABBIT)).save(this.output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Items.RABBIT_STEW, BOPItems.TOADSTOOL));
        this.shaped(RecipeCategory.REDSTONE, Blocks.TNT).define('#', Ingredient.of(BOPBlocks.WHITE_SAND, BOPBlocks.ORANGE_SAND, BOPBlocks.BLACK_SAND)).define('X', Items.GUNPOWDER).pattern("X#X").pattern("#X#").pattern("X#X").unlockedBy("has_gunpowder", has(Items.GUNPOWDER)).save(this.output, BiomesOPlenty.MOD_ID + ":" + "tnt_from_bop_sand");
    }

    protected void generateForEnabledBlockFamiliesBOP(FeatureFlagSet flags) {
        BOPBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) -> generateRecipes(family, flags));
    }

    protected void planksFromLogs(RecipeOutput output, ItemLike planks, ItemLike log, int count)
    {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, count).requires(log).group("planks").unlockedBy("has_logs", has(log)).save(output);
    }

    protected void stonecutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike input)
    {
        this.stonecutterResultFromBase(category, result, input, 1);
    }

    protected void stonecutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike input, int count)
    {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, result, count).unlockedBy(getHasName(input), has(input)).save(this.output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(result, input) + "_stonecutting");
    }

    protected void oneToOneConversionRecipe(RecipeOutput p_299023_, ItemLike p_176553_, ItemLike p_176554_, @Nullable String p_176555_) {
        this.oneToOneConversionRecipe(p_299023_, p_176553_, p_176554_, p_176555_, 1);
    }

    protected void oneToOneConversionRecipe(RecipeOutput p_301230_, ItemLike p_176558_, ItemLike p_176559_, @Nullable String p_176560_, int p_176561_) {
        this.shapeless(RecipeCategory.MISC, p_176558_, p_176561_).requires(p_176559_).group(p_176560_).unlockedBy(getHasName(p_176559_), has(p_176559_)).save(p_301230_, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(p_176558_, p_176559_));
    }

    public static class Runner extends RecipeProvider.Runner
    {
        public Runner(PackOutput p_365442_, CompletableFuture<HolderLookup.Provider> p_362168_) {
            super(p_365442_, p_362168_);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output)
        {
            return new BOPRecipeProvider(provider, output);
        }

        @Override
        public String getName() {
            return "BOP Recipes";
        }
    }
}
