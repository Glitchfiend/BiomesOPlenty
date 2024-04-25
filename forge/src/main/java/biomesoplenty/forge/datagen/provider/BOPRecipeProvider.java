/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.datagen.provider;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.forge.datagen.BOPBlockFamilies;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class BOPRecipeProvider extends RecipeProvider
{
    public BOPRecipeProvider(PackOutput output)
    {
        super(output);
    }

    @Override
    protected void buildRecipes(RecipeOutput output)
    {
        generateForEnabledBlockFamilies(output, FeatureFlagSet.of(FeatureFlags.VANILLA));

        // Planks from logs
        planksFromLogs(output, BOPBlocks.FIR_PLANKS, BOPBlocks.FIR_LOG, 4);
        planksFromLogs(output, BOPBlocks.PINE_PLANKS, BOPBlocks.PINE_LOG, 4);
        planksFromLogs(output, BOPBlocks.MAPLE_PLANKS, BOPBlocks.MAPLE_LOG, 4);
        planksFromLogs(output, BOPBlocks.REDWOOD_PLANKS, BOPBlocks.REDWOOD_LOG, 4);
        planksFromLogs(output, BOPBlocks.MAHOGANY_PLANKS, BOPBlocks.MAHOGANY_LOG, 4);
        planksFromLogs(output, BOPBlocks.JACARANDA_PLANKS, BOPBlocks.JACARANDA_LOG, 4);
        planksFromLogs(output, BOPBlocks.PALM_PLANKS, BOPBlocks.PALM_LOG, 4);
        planksFromLogs(output, BOPBlocks.WILLOW_PLANKS, BOPBlocks.WILLOW_LOG, 4);
        planksFromLogs(output, BOPBlocks.DEAD_PLANKS, BOPBlocks.DEAD_LOG, 4);
        planksFromLogs(output, BOPBlocks.MAGIC_PLANKS, BOPBlocks.MAGIC_LOG, 4);
        planksFromLogs(output, BOPBlocks.UMBRAN_PLANKS, BOPBlocks.UMBRAN_LOG, 4);
        planksFromLogs(output, BOPBlocks.HELLBARK_PLANKS, BOPBlocks.HELLBARK_LOG, 4);
        planksFromLogs(output, BOPBlocks.EMPYREAL_PLANKS, BOPBlocks.EMPYREAL_LOG, 4);

        // Wood from logs
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
        chestBoat(output, BOPItems.FIR_CHEST_BOAT, BOPBlocks.FIR_PLANKS);
        chestBoat(output, BOPItems.PINE_CHEST_BOAT, BOPBlocks.PINE_PLANKS);
        chestBoat(output, BOPItems.MAPLE_CHEST_BOAT, BOPBlocks.MAPLE_PLANKS);
        chestBoat(output, BOPItems.REDWOOD_CHEST_BOAT, BOPBlocks.REDWOOD_PLANKS);
        chestBoat(output, BOPItems.MAHOGANY_CHEST_BOAT, BOPBlocks.MAHOGANY_PLANKS);
        chestBoat(output, BOPItems.JACARANDA_CHEST_BOAT, BOPBlocks.JACARANDA_PLANKS);
        chestBoat(output, BOPItems.PALM_CHEST_BOAT, BOPBlocks.PALM_PLANKS);
        chestBoat(output, BOPItems.WILLOW_CHEST_BOAT, BOPBlocks.WILLOW_PLANKS);
        chestBoat(output, BOPItems.DEAD_CHEST_BOAT, BOPBlocks.DEAD_PLANKS);
        chestBoat(output, BOPItems.MAGIC_CHEST_BOAT, BOPBlocks.MAGIC_PLANKS);
        chestBoat(output, BOPItems.UMBRAN_CHEST_BOAT, BOPBlocks.UMBRAN_PLANKS);
        chestBoat(output, BOPItems.HELLBARK_CHEST_BOAT, BOPBlocks.HELLBARK_PLANKS);
        chestBoat(output, BOPItems.EMPYREAL_CHEST_BOAT, BOPBlocks.EMPYREAL_PLANKS);

        // Hanging signs
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

        // White sandstone
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

        // Orange sandstone
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

        // Black sandstone
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
    }

    protected static void generateForEnabledBlockFamilies(RecipeOutput output, FeatureFlagSet flags) {
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
}
