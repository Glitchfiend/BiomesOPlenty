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
    public BOPRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> holderLookup)
    {
        super(output, holderLookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        generateForEnabledBlockFamiliesBOP(output, FeatureFlagSet.of(FeatureFlags.VANILLA));

        // Planks from Logs
        planksFromLogs(output, BOPBlocks.FIR_PLANKS, ModTags.Items.FIR_LOGS, 4);
        planksFromLogs(output, BOPBlocks.PINE_PLANKS, ModTags.Items.PINE_LOGS, 4);
        planksFromLogs(output, BOPBlocks.MAPLE_PLANKS, ModTags.Items.MAPLE_LOGS, 4);
        planksFromLogs(output, BOPBlocks.REDWOOD_PLANKS, ModTags.Items.REDWOOD_LOGS, 4);
        planksFromLogs(output, BOPBlocks.MAHOGANY_PLANKS, ModTags.Items.MAHOGANY_LOGS, 4);
        planksFromLogs(output, BOPBlocks.JACARANDA_PLANKS, ModTags.Items.JACARANDA_LOGS, 4);
        planksFromLogs(output, BOPBlocks.PALM_PLANKS, ModTags.Items.PALM_LOGS, 4);
        planksFromLogs(output, BOPBlocks.WILLOW_PLANKS, ModTags.Items.WILLOW_LOGS, 4);
        planksFromLogs(output, BOPBlocks.DEAD_PLANKS, ModTags.Items.DEAD_LOGS, 4);
        planksFromLogs(output, BOPBlocks.MAGIC_PLANKS, ModTags.Items.MAGIC_LOGS, 4);
        planksFromLogs(output, BOPBlocks.UMBRAN_PLANKS, ModTags.Items.UMBRAN_LOGS, 4);
        planksFromLogs(output, BOPBlocks.HELLBARK_PLANKS, ModTags.Items.HELLBARK_LOGS, 4);
        planksFromLogs(output, BOPBlocks.EMPYREAL_PLANKS, ModTags.Items.EMPYREAL_LOGS, 4);

        // Wood from Logs
        woodFromLogs(output, BOPBlocks.FIR_WOOD, BOPBlocks.FIR_LOG);
        woodFromLogs(output, BOPBlocks.PINE_WOOD, BOPBlocks.PINE_LOG);
        woodFromLogs(output, BOPBlocks.MAPLE_WOOD, BOPBlocks.MAPLE_LOG);
        woodFromLogs(output, BOPBlocks.REDWOOD_WOOD, BOPBlocks.REDWOOD_LOG);
        woodFromLogs(output, BOPBlocks.MAHOGANY_WOOD, BOPBlocks.MAHOGANY_LOG);
        woodFromLogs(output, BOPBlocks.JACARANDA_WOOD, BOPBlocks.JACARANDA_LOG);
        woodFromLogs(output, BOPBlocks.PALM_WOOD, BOPBlocks.PALM_LOG);
        woodFromLogs(output, BOPBlocks.WILLOW_WOOD, BOPBlocks.WILLOW_LOG);
        woodFromLogs(output, BOPBlocks.DEAD_WOOD, BOPBlocks.DEAD_LOG);
        woodFromLogs(output, BOPBlocks.MAGIC_WOOD, BOPBlocks.MAGIC_LOG);
        woodFromLogs(output, BOPBlocks.UMBRAN_WOOD, BOPBlocks.UMBRAN_LOG);
        woodFromLogs(output, BOPBlocks.HELLBARK_WOOD, BOPBlocks.HELLBARK_LOG);
        woodFromLogs(output, BOPBlocks.EMPYREAL_WOOD, BOPBlocks.EMPYREAL_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_FIR_WOOD, BOPBlocks.STRIPPED_FIR_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_PINE_WOOD, BOPBlocks.STRIPPED_PINE_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_MAPLE_WOOD, BOPBlocks.STRIPPED_MAPLE_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_REDWOOD_WOOD, BOPBlocks.STRIPPED_REDWOOD_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_MAHOGANY_WOOD, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_JACARANDA_WOOD, BOPBlocks.STRIPPED_JACARANDA_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_PALM_WOOD, BOPBlocks.STRIPPED_PALM_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_WILLOW_WOOD, BOPBlocks.STRIPPED_WILLOW_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_DEAD_WOOD, BOPBlocks.STRIPPED_DEAD_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_MAGIC_WOOD, BOPBlocks.STRIPPED_MAGIC_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_UMBRAN_WOOD, BOPBlocks.STRIPPED_UMBRAN_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_HELLBARK_WOOD, BOPBlocks.STRIPPED_HELLBARK_LOG);
        woodFromLogs(output, BOPBlocks.STRIPPED_EMPYREAL_WOOD, BOPBlocks.STRIPPED_EMPYREAL_LOG);

        // Boats
        woodenBoat(output, BOPItems.FIR_BOAT, BOPBlocks.FIR_PLANKS);
        woodenBoat(output, BOPItems.PINE_BOAT, BOPBlocks.PINE_PLANKS);
        woodenBoat(output, BOPItems.MAPLE_BOAT, BOPBlocks.MAPLE_PLANKS);
        woodenBoat(output, BOPItems.REDWOOD_BOAT, BOPBlocks.REDWOOD_PLANKS);
        woodenBoat(output, BOPItems.MAHOGANY_BOAT, BOPBlocks.MAHOGANY_PLANKS);
        woodenBoat(output, BOPItems.JACARANDA_BOAT, BOPBlocks.JACARANDA_PLANKS);
        woodenBoat(output, BOPItems.PALM_BOAT, BOPBlocks.PALM_PLANKS);
        woodenBoat(output, BOPItems.WILLOW_BOAT, BOPBlocks.WILLOW_PLANKS);
        woodenBoat(output, BOPItems.DEAD_BOAT, BOPBlocks.DEAD_PLANKS);
        woodenBoat(output, BOPItems.MAGIC_BOAT, BOPBlocks.MAGIC_PLANKS);
        woodenBoat(output, BOPItems.UMBRAN_BOAT, BOPBlocks.UMBRAN_PLANKS);
        woodenBoat(output, BOPItems.HELLBARK_BOAT, BOPBlocks.HELLBARK_PLANKS);
        woodenBoat(output, BOPItems.EMPYREAL_BOAT, BOPBlocks.EMPYREAL_PLANKS);
        chestBoat(output, BOPItems.FIR_CHEST_BOAT, BOPItems.FIR_BOAT);
        chestBoat(output, BOPItems.PINE_CHEST_BOAT, BOPItems.PINE_BOAT);
        chestBoat(output, BOPItems.MAPLE_CHEST_BOAT, BOPItems.MAPLE_BOAT);
        chestBoat(output, BOPItems.REDWOOD_CHEST_BOAT, BOPItems.REDWOOD_BOAT);
        chestBoat(output, BOPItems.MAHOGANY_CHEST_BOAT, BOPItems.MAHOGANY_BOAT);
        chestBoat(output, BOPItems.JACARANDA_CHEST_BOAT, BOPItems.JACARANDA_BOAT);
        chestBoat(output, BOPItems.PALM_CHEST_BOAT, BOPItems.PALM_BOAT);
        chestBoat(output, BOPItems.WILLOW_CHEST_BOAT, BOPItems.WILLOW_BOAT);
        chestBoat(output, BOPItems.DEAD_CHEST_BOAT, BOPItems.DEAD_BOAT);
        chestBoat(output, BOPItems.MAGIC_CHEST_BOAT, BOPItems.MAGIC_BOAT);
        chestBoat(output, BOPItems.UMBRAN_CHEST_BOAT, BOPItems.UMBRAN_BOAT);
        chestBoat(output, BOPItems.HELLBARK_CHEST_BOAT, BOPItems.HELLBARK_BOAT);
        chestBoat(output, BOPItems.EMPYREAL_CHEST_BOAT, BOPItems.EMPYREAL_BOAT);

        // Hanging Signs
        hangingSign(output, BOPItems.FIR_HANGING_SIGN, BOPBlocks.STRIPPED_FIR_LOG);
        hangingSign(output, BOPItems.PINE_HANGING_SIGN, BOPBlocks.STRIPPED_PINE_LOG);
        hangingSign(output, BOPItems.MAPLE_HANGING_SIGN, BOPBlocks.STRIPPED_MAPLE_LOG);
        hangingSign(output, BOPItems.REDWOOD_HANGING_SIGN, BOPBlocks.STRIPPED_REDWOOD_LOG);
        hangingSign(output, BOPItems.MAHOGANY_HANGING_SIGN, BOPBlocks.STRIPPED_MAHOGANY_LOG);
        hangingSign(output, BOPItems.JACARANDA_HANGING_SIGN, BOPBlocks.STRIPPED_JACARANDA_LOG);
        hangingSign(output, BOPItems.PALM_HANGING_SIGN, BOPBlocks.STRIPPED_PALM_LOG);
        hangingSign(output, BOPItems.WILLOW_HANGING_SIGN, BOPBlocks.STRIPPED_WILLOW_LOG);
        hangingSign(output, BOPItems.DEAD_HANGING_SIGN, BOPBlocks.STRIPPED_DEAD_LOG);
        hangingSign(output, BOPItems.MAGIC_HANGING_SIGN, BOPBlocks.STRIPPED_MAGIC_LOG);
        hangingSign(output, BOPItems.UMBRAN_HANGING_SIGN, BOPBlocks.STRIPPED_UMBRAN_LOG);
        hangingSign(output, BOPItems.HELLBARK_HANGING_SIGN, BOPBlocks.STRIPPED_HELLBARK_LOG);
        hangingSign(output, BOPItems.EMPYREAL_HANGING_SIGN, BOPBlocks.STRIPPED_EMPYREAL_LOG);

        // White Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_WHITE_SANDSTONE, Ingredient.of(BOPBlocks.WHITE_SANDSTONE_SLAB)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", has(BOPBlocks.CUT_WHITE_SANDSTONE)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE).define('#', BOPBlocks.WHITE_SAND).pattern("##").pattern("##").unlockedBy("has_white_sand", has(BOPBlocks.WHITE_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.WHITE_SANDSTONE, BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.WHITE_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.WHITE_SANDSTONE, BOPBlocks.CHISELED_WHITE_SANDSTONE, BOPBlocks.CUT_WHITE_SANDSTONE)).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).unlockedBy("has_chiseled_white_sandstone", has(BOPBlocks.CHISELED_WHITE_SANDSTONE)).unlockedBy("has_cut_white_sandstone", has(BOPBlocks.CUT_WHITE_SANDSTONE)).save(output);
        cut(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        wall(output, RecipeCategory.DECORATIONS, BOPBlocks.WHITE_SANDSTONE_WALL, BOPBlocks.WHITE_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.WHITE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_white_sandstone", has(BOPBlocks.WHITE_SANDSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_SLAB, BOPBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, BOPBlocks.WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_WHITE_SANDSTONE_SLAB, BOPBlocks.CUT_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.WHITE_SANDSTONE_STAIRS, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.WHITE_SANDSTONE_WALL, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_WHITE_SANDSTONE, BOPBlocks.WHITE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE_SLAB, BOPBlocks.SMOOTH_WHITE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_WHITE_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_WHITE_SANDSTONE);

        // Orange Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_ORANGE_SANDSTONE, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE_SLAB)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_cut_orange_sandstone", has(BOPBlocks.CUT_ORANGE_SANDSTONE)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE).define('#', BOPBlocks.ORANGE_SAND).pattern("##").pattern("##").unlockedBy("has_orange_sand", has(BOPBlocks.ORANGE_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE, BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.ORANGE_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.ORANGE_SANDSTONE, BOPBlocks.CHISELED_ORANGE_SANDSTONE, BOPBlocks.CUT_ORANGE_SANDSTONE)).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).unlockedBy("has_chiseled_orange_sandstone", has(BOPBlocks.CHISELED_ORANGE_SANDSTONE)).unlockedBy("has_cut_orange_sandstone", has(BOPBlocks.CUT_ORANGE_SANDSTONE)).save(output);
        cut(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        wall(output, RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_SANDSTONE_WALL, BOPBlocks.ORANGE_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.ORANGE_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_orange_sandstone", has(BOPBlocks.ORANGE_SANDSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_SLAB, BOPBlocks.ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, BOPBlocks.ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_ORANGE_SANDSTONE_SLAB, BOPBlocks.CUT_ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ORANGE_SANDSTONE_STAIRS, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_SANDSTONE_WALL, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_ORANGE_SANDSTONE, BOPBlocks.ORANGE_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_SLAB, BOPBlocks.SMOOTH_ORANGE_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_ORANGE_SANDSTONE);

        // Black Sandstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BLACK_SANDSTONE, Ingredient.of(BOPBlocks.BLACK_SANDSTONE_SLAB)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_cut_black_sandstone", has(BOPBlocks.CUT_BLACK_SANDSTONE)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE).define('#', BOPBlocks.BLACK_SAND).pattern("##").pattern("##").unlockedBy("has_black_sand", has(BOPBlocks.BLACK_SAND)).save(output);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_SLAB, Ingredient.of(BOPBlocks.BLACK_SANDSTONE, BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).save(output);
        stairBuilder(BOPBlocks.BLACK_SANDSTONE_STAIRS, Ingredient.of(BOPBlocks.BLACK_SANDSTONE, BOPBlocks.CHISELED_BLACK_SANDSTONE, BOPBlocks.CUT_BLACK_SANDSTONE)).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).unlockedBy("has_chiseled_black_sandstone", has(BOPBlocks.CHISELED_BLACK_SANDSTONE)).unlockedBy("has_cut_black_sandstone", has(BOPBlocks.CUT_BLACK_SANDSTONE)).save(output);
        cut(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        wall(output, RecipeCategory.DECORATIONS, BOPBlocks.BLACK_SANDSTONE_WALL, BOPBlocks.BLACK_SANDSTONE);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.BLACK_SANDSTONE), RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE.asItem(), 0.1F, 200).unlockedBy("has_black_sandstone", has(BOPBlocks.BLACK_SANDSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_SLAB, BOPBlocks.BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BOPBlocks.BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CUT_BLACK_SANDSTONE_SLAB, BOPBlocks.CUT_BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BLACK_SANDSTONE_STAIRS, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BLACK_SANDSTONE_WALL, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BLACK_SANDSTONE, BOPBlocks.BLACK_SANDSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE_SLAB, BOPBlocks.SMOOTH_BLACK_SANDSTONE, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.SMOOTH_BLACK_SANDSTONE_STAIRS, BOPBlocks.SMOOTH_BLACK_SANDSTONE);

        // Brimstone
        chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, Ingredient.of(BOPBlocks.BRIMSTONE_BRICK_SLAB)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_chiseled_brimstone_bricks", has(BOPBlocks.CHISELED_BRIMSTONE_BRICKS)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICKS, 4).define('#', BOPBlocks.BRIMSTONE).pattern("##").pattern("##").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE);
        slabBuilder(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, Ingredient.of(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, BOPBlocks.BRIMSTONE_BRICKS, 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_SLAB, BOPBlocks.BRIMSTONE, 2);
        stairBuilder(BOPBlocks.BRIMSTONE_BRICK_STAIRS, Ingredient.of(BOPBlocks.BRIMSTONE_BRICKS)).unlockedBy("has_brimstone_bricks", has(BOPBlocks.BRIMSTONE_BRICKS)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_STAIRS, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.BRIMSTONE_BRICK_STAIRS, BOPBlocks.BRIMSTONE);
        wall(output, RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BRICK_WALL, BOPBlocks.BRIMSTONE);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE_BRICKS);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, BOPBlocks.CHISELED_BRIMSTONE_BRICKS, BOPBlocks.BRIMSTONE);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_CLUSTER, 2).define('#', BOPBlocks.BRIMSTONE).pattern("#").pattern("#").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_CLUSTER, BOPBlocks.BRIMSTONE, 2);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BUD, 4).requires(BOPBlocks.BRIMSTONE).unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_BUD, BOPBlocks.BRIMSTONE, 4);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BRIMSTONE_FUMAROLE).define('#', BOPBlocks.BRIMSTONE).define('F', Items.FIRE_CHARGE).pattern(" # ").pattern("#F#").pattern("###").unlockedBy("has_brimstone", has(BOPBlocks.BRIMSTONE)).save(output);

        // Blackstone Decorations
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_BULB, 2).define('#', Blocks.BLACKSTONE).define('O', Blocks.CRYING_OBSIDIAN).pattern("O").pattern("#").unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_SPINES, 4).requires(Blocks.BLACKSTONE).unlockedBy("has_blackstone", has(Blocks.BLACKSTONE)).save(output);
        stonecutterResultFromBase(output, RecipeCategory.DECORATIONS, BOPBlocks.BLACKSTONE_SPINES, Blocks.BLACKSTONE, 4);

        // End Stone Blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ALGAL_END_STONE).requires(Blocks.END_STONE).requires(BOPBlocks.ENDERPHYTE).unlockedBy("has_enderphyte", has(BOPBlocks.ENDERPHYTE)).save(output);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.NULL_END_STONE).requires(Blocks.END_STONE).requires(BOPBlocks.NULL_LEAVES).unlockedBy("has_null_leaves", has(BOPBlocks.NULL_LEAVES)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.UNMAPPED_END_STONE, 4).define('#', Blocks.END_STONE).define('B', BOPItems.LIQUID_NULL_BUCKET).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy("has_liquid_null_bucket", has(BOPItems.LIQUID_NULL_BUCKET)).save(output);

        // Other Building Blocks
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.MOSSY_BLACK_SAND).requires(BOPBlocks.BLACK_SAND).requires(Blocks.MOSS_BLOCK).unlockedBy("has_moss_block", has(Blocks.MOSS_BLOCK)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.FLESH).define('#', Items.ROTTEN_FLESH).define('B', BOPItems.BLOOD_BUCKET).pattern(" # ").pattern("#B#").pattern(" # ").unlockedBy("has_blood_bucket", has(BOPItems.BLOOD_BUCKET)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, BOPBlocks.ROSE_QUARTZ_BLOCK).define('#', BOPItems.ROSE_QUARTZ_CHUNK).pattern("##").pattern("##").unlockedBy("has_rose_quartz_chunk", has(BOPItems.ROSE_QUARTZ_CHUNK)).save(output);

        // Leaf Piles
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.RED_MAPLE_LEAF_PILE, 3).define('#', BOPBlocks.RED_MAPLE_LEAF_PILE).pattern("###").unlockedBy("has_red_maple_leaves", has(BOPBlocks.RED_MAPLE_LEAVES)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.ORANGE_MAPLE_LEAF_PILE, 3).define('#', BOPBlocks.ORANGE_MAPLE_LEAF_PILE).pattern("###").unlockedBy("has_orange_maple_leaves", has(BOPBlocks.ORANGE_MAPLE_LEAVES)).save(output);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BOPBlocks.YELLOW_MAPLE_LEAF_PILE, 3).define('#', BOPBlocks.YELLOW_MAPLE_LEAF_PILE).pattern("###").unlockedBy("has_yellow_maple_leaves", has(BOPBlocks.YELLOW_MAPLE_LEAVES)).save(output);

        // Dyes
        oneToOneConversionRecipe(output, Items.BROWN_DYE, BOPBlocks.CATTAIL, "brown_dye", 2);
        oneToOneConversionRecipe(output, Items.CYAN_DYE, BOPBlocks.GLOWFLOWER, "cyan_dye");
        oneToOneConversionRecipe(output, Items.GRAY_DYE, BOPBlocks.WILTED_LILY, "gray_dye");
        oneToOneConversionRecipe(output, Items.LIGHT_BLUE_DYE, BOPBlocks.BLUE_HYDRANGEA, "light_blue_dye", 2);
        oneToOneConversionRecipe(output, Items.LIGHT_BLUE_DYE, BOPBlocks.ICY_IRIS, "light_blue_dye", 2);
        oneToOneConversionRecipe(output, Items.LIGHT_GRAY_DYE, BOPBlocks.ENDBLOOM, "light_gray_dye");
        oneToOneConversionRecipe(output, Items.MAGENTA_DYE, BOPBlocks.WILDFLOWER, "magenta_dye");
        oneToOneConversionRecipe(output, Items.ORANGE_DYE, BOPBlocks.BURNING_BLOSSOM, "orange_dye");
        oneToOneConversionRecipe(output, Items.ORANGE_DYE, BOPBlocks.ORANGE_COSMOS, "orange_dye");
        oneToOneConversionRecipe(output, Items.PINK_DYE, BOPBlocks.PINK_DAFFODIL, "pink_dye");
        oneToOneConversionRecipe(output, Items.PINK_DYE, BOPBlocks.PINK_HIBISCUS, "pink_dye");
        oneToOneConversionRecipe(output, Items.PURPLE_DYE, BOPBlocks.LAVENDER, "purple_dye");
        oneToOneConversionRecipe(output, Items.PURPLE_DYE, BOPBlocks.TALL_LAVENDER, "purple_dye", 2);
        oneToOneConversionRecipe(output, Items.PURPLE_DYE, BOPBlocks.VIOLET, "purple_dye");
        oneToOneConversionRecipe(output, Items.RED_DYE, BOPBlocks.ROSE, "red_dye");
        oneToOneConversionRecipe(output, Items.RED_DYE, BOPBlocks.WATERLILY, "red_dye");
        oneToOneConversionRecipe(output, Items.WHITE_DYE, BOPBlocks.WHITE_LAVENDER, "white_dye");
        oneToOneConversionRecipe(output, Items.WHITE_DYE, BOPBlocks.TALL_WHITE_LAVENDER, "white_dye", 2);
        oneToOneConversionRecipe(output, Items.WHITE_DYE, BOPBlocks.WHITE_PETALS, "white_dye");
        oneToOneConversionRecipe(output, Items.YELLOW_DYE, BOPBlocks.GOLDENROD, "yellow_dye", 2);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(BOPBlocks.TINY_CACTUS.asItem()), RecipeCategory.MISC, Items.GREEN_DYE, 1.0F, 200).unlockedBy("has_tiny_cactus", has(BOPBlocks.TINY_CACTUS)).save(output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Items.GREEN_DYE, BOPBlocks.TINY_CACTUS));

        // Vanilla Parity Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_COBBLESTONE).requires(Blocks.COBBLESTONE).requires(BOPBlocks.WILLOW_VINE).group("mossy_cobblestone").unlockedBy("has_willow_vine", has(BOPBlocks.WILLOW_VINE)).save(output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Blocks.MOSSY_COBBLESTONE, BOPBlocks.WILLOW_VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, Blocks.MOSSY_STONE_BRICKS).requires(Blocks.STONE_BRICKS).requires(BOPBlocks.WILLOW_VINE).group("mossy_stone_bricks").unlockedBy("has_willow_vine", has(BOPBlocks.WILLOW_VINE)).save(output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Blocks.MOSSY_STONE_BRICKS, BOPBlocks.WILLOW_VINE));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.RABBIT_STEW).requires(Items.BAKED_POTATO).requires(Items.COOKED_RABBIT).requires(Items.BOWL).requires(Items.CARROT).requires(BOPBlocks.TOADSTOOL).group("rabbit_stew").unlockedBy("has_cooked_rabbit", has(Items.COOKED_RABBIT)).save(output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(Items.RABBIT_STEW, BOPItems.TOADSTOOL));
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, Blocks.TNT).define('#', Ingredient.of(BOPBlocks.WHITE_SAND, BOPBlocks.ORANGE_SAND, BOPBlocks.BLACK_SAND)).define('X', Items.GUNPOWDER).pattern("X#X").pattern("#X#").pattern("X#X").unlockedBy("has_gunpowder", has(Items.GUNPOWDER)).save(output, BiomesOPlenty.MOD_ID + ":" + "tnt_from_bop_sand");
    }

    protected static void generateForEnabledBlockFamiliesBOP(RecipeOutput output, FeatureFlagSet flags) {
        BOPBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach((family) -> generateRecipes(output, family, flags));
    }

    protected static void planksFromLogs(RecipeOutput output, ItemLike planks, ItemLike log, int count)
    {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, planks, count).requires(log).group("planks").unlockedBy("has_logs", has(log)).save(output);
    }

    protected static void stonecutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike input)
    {
        stonecutterResultFromBase(output, category, result, input, 1);
    }

    protected static void stonecutterResultFromBase(RecipeOutput output, RecipeCategory category, ItemLike result, ItemLike input, int count)
    {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(input), category, result, count).unlockedBy(getHasName(input), has(input)).save(output, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(result, input) + "_stonecutting");
    }

    protected static void oneToOneConversionRecipe(RecipeOutput p_299023_, ItemLike p_176553_, ItemLike p_176554_, @Nullable String p_176555_) {
        oneToOneConversionRecipe(p_299023_, p_176553_, p_176554_, p_176555_, 1);
    }

    protected static void oneToOneConversionRecipe(RecipeOutput p_301230_, ItemLike p_176558_, ItemLike p_176559_, @Nullable String p_176560_, int p_176561_) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, p_176558_, p_176561_).requires(p_176559_).group(p_176560_).unlockedBy(getHasName(p_176559_), has(p_176559_)).save(p_301230_, BiomesOPlenty.MOD_ID + ":" + getConversionRecipeName(p_176558_, p_176559_));
    }
}
