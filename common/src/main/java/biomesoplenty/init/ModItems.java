/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import net.minecraft.core.Direction;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.api.entity.BOPEntities;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.item.StringyCobwebBlockItem;
import biomesoplenty.worldgen.BOPMaterialRuleData;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import terrablender.api.MaterialRuleManager;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static biomesoplenty.api.item.BOPItems.*;

public class ModItems
{
    public static void setup(BiConsumer<Identifier, Item> func)
    {
        registerItems(func);
        ModVanillaCompat.setup();
    }

    private static void registerItems(BiConsumer<Identifier, Item> func)
    {
        registerBlockItems(func);

        BOP_ICON = registerItem(func, "bop_icon", Item::new, new Item.Properties());

        ROSE_QUARTZ_CHUNK = registerItem(func, "rose_quartz_chunk", Item::new, new Item.Properties().trimMaterial(ModTrimMaterials.ROSE_QUARTZ));
        MUSIC_DISC_WANDERER = registerItem(func, "music_disc_wanderer", Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).jukeboxPlayable(ModJukeboxSongs.WANDERER));
        BLOOD_BUCKET = registerItem(func, "blood_bucket", (properties) -> new BucketItem(BOPFluids.BLOOD, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));
        LIQUID_NULL_BUCKET = registerItem(func, "liquid_null_bucket", (properties) -> new BucketItem(BOPFluids.LIQUID_NULL, properties), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1));

        ORIGIN_OAK_SIGN = registerBlock(func, BOPBlocks.ORIGIN_OAK_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.ORIGIN_OAK_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        ORIGIN_OAK_HANGING_SIGN = registerBlock(func, BOPBlocks.ORIGIN_OAK_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.ORIGIN_OAK_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        FIR_SIGN = registerBlock(func, BOPBlocks.FIR_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.FIR_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        FIR_HANGING_SIGN = registerBlock(func, BOPBlocks.FIR_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.FIR_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        PINE_SIGN = registerBlock(func, BOPBlocks.PINE_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.PINE_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        PINE_HANGING_SIGN = registerBlock(func, BOPBlocks.PINE_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.PINE_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        MAPLE_SIGN = registerBlock(func, BOPBlocks.MAPLE_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.MAPLE_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        MAPLE_HANGING_SIGN = registerBlock(func, BOPBlocks.MAPLE_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.MAPLE_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        REDWOOD_SIGN = registerBlock(func, BOPBlocks.REDWOOD_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.REDWOOD_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        REDWOOD_HANGING_SIGN = registerBlock(func, BOPBlocks.REDWOOD_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.REDWOOD_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        MAHOGANY_SIGN = registerBlock(func, BOPBlocks.MAHOGANY_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.MAHOGANY_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        MAHOGANY_HANGING_SIGN = registerBlock(func, BOPBlocks.MAHOGANY_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.MAHOGANY_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        JACARANDA_SIGN = registerBlock(func, BOPBlocks.JACARANDA_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.JACARANDA_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        JACARANDA_HANGING_SIGN = registerBlock(func, BOPBlocks.JACARANDA_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.JACARANDA_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        PALM_SIGN = registerBlock(func, BOPBlocks.PALM_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.PALM_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        PALM_HANGING_SIGN = registerBlock(func, BOPBlocks.PALM_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.PALM_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        WILLOW_SIGN = registerBlock(func, BOPBlocks.WILLOW_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.WILLOW_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        WILLOW_HANGING_SIGN = registerBlock(func, BOPBlocks.WILLOW_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.WILLOW_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        DEAD_SIGN = registerBlock(func, BOPBlocks.DEAD_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.DEAD_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        DEAD_HANGING_SIGN = registerBlock(func, BOPBlocks.DEAD_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.DEAD_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        MAGIC_SIGN = registerBlock(func, BOPBlocks.MAGIC_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.MAGIC_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        MAGIC_HANGING_SIGN = registerBlock(func, BOPBlocks.MAGIC_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.MAGIC_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        UMBRAN_SIGN = registerBlock(func, BOPBlocks.UMBRAN_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.UMBRAN_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        UMBRAN_HANGING_SIGN = registerBlock(func, BOPBlocks.UMBRAN_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.UMBRAN_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());
        HELLBARK_SIGN = registerBlock(func, BOPBlocks.HELLBARK_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.HELLBARK_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        HELLBARK_HANGING_SIGN = registerBlock(func, BOPBlocks.HELLBARK_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.HELLBARK_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        EMPYREAL_SIGN = registerBlock(func, BOPBlocks.EMPYREAL_SIGN, (block, properties) -> new StandingAndWallBlockItem(block, BOPBlocks.EMPYREAL_WALL_SIGN, Direction.DOWN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE).stacksTo(16).signText());
        EMPYREAL_HANGING_SIGN = registerBlock(func, BOPBlocks.EMPYREAL_HANGING_SIGN, (block, properties) -> new HangingSignItem(block, BOPBlocks.EMPYREAL_WALL_HANGING_SIGN, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_HANGING_SIGNS).stacksTo(16).signText());

        ORIGIN_OAK_BOAT = registerItem(func, "origin_oak_boat", (properties) -> new BoatItem(BOPEntities.ORIGIN_OAK_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        ORIGIN_OAK_CHEST_BOAT = registerItem(func, "origin_oak_chest_boat", (properties) -> new BoatItem(BOPEntities.ORIGIN_OAK_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        FIR_BOAT = registerItem(func, "fir_boat", (properties) -> new BoatItem(BOPEntities.FIR_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        FIR_CHEST_BOAT = registerItem(func, "fir_chest_boat", (properties) -> new BoatItem(BOPEntities.FIR_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        PINE_BOAT = registerItem(func, "pine_boat", (properties) -> new BoatItem(BOPEntities.PINE_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        PINE_CHEST_BOAT = registerItem(func, "pine_chest_boat", (properties) -> new BoatItem(BOPEntities.PINE_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAPLE_BOAT = registerItem(func, "maple_boat", (properties) -> new BoatItem(BOPEntities.MAPLE_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAPLE_CHEST_BOAT = registerItem(func, "maple_chest_boat", (properties) -> new BoatItem(BOPEntities.MAPLE_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        REDWOOD_BOAT = registerItem(func, "redwood_boat", (properties) -> new BoatItem(BOPEntities.REDWOOD_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        REDWOOD_CHEST_BOAT = registerItem(func, "redwood_chest_boat", (properties) -> new BoatItem(BOPEntities.REDWOOD_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAHOGANY_BOAT = registerItem(func, "mahogany_boat", (properties) -> new BoatItem(BOPEntities.MAHOGANY_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAHOGANY_CHEST_BOAT = registerItem(func, "mahogany_chest_boat", (properties) -> new BoatItem(BOPEntities.MAHOGANY_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        JACARANDA_BOAT = registerItem(func, "jacaranda_boat", (properties) -> new BoatItem(BOPEntities.JACARANDA_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        JACARANDA_CHEST_BOAT = registerItem(func, "jacaranda_chest_boat", (properties) -> new BoatItem(BOPEntities.JACARANDA_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        PALM_BOAT = registerItem(func, "palm_boat", (properties) -> new BoatItem(BOPEntities.PALM_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        PALM_CHEST_BOAT = registerItem(func, "palm_chest_boat", (properties) -> new BoatItem(BOPEntities.PALM_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        WILLOW_BOAT = registerItem(func, "willow_boat", (properties) -> new BoatItem(BOPEntities.WILLOW_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        WILLOW_CHEST_BOAT = registerItem(func, "willow_chest_boat", (properties) -> new BoatItem(BOPEntities.WILLOW_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        DEAD_BOAT = registerItem(func, "dead_boat", (properties) -> new BoatItem(BOPEntities.DEAD_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        DEAD_CHEST_BOAT = registerItem(func, "dead_chest_boat", (properties) -> new BoatItem(BOPEntities.DEAD_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAGIC_BOAT = registerItem(func, "magic_boat", (properties) -> new BoatItem(BOPEntities.MAGIC_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        MAGIC_CHEST_BOAT = registerItem(func, "magic_chest_boat", (properties) -> new BoatItem(BOPEntities.MAGIC_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        UMBRAN_BOAT = registerItem(func, "umbran_boat", (properties) -> new BoatItem(BOPEntities.UMBRAN_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        UMBRAN_CHEST_BOAT = registerItem(func, "umbran_chest_boat", (properties) -> new BoatItem(BOPEntities.UMBRAN_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        HELLBARK_BOAT = registerItem(func, "hellbark_boat", (properties) -> new BoatItem(BOPEntities.HELLBARK_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        HELLBARK_CHEST_BOAT = registerItem(func, "hellbark_chest_boat", (properties) -> new BoatItem(BOPEntities.HELLBARK_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        EMPYREAL_BOAT = registerItem(func, "empyreal_boat", (properties) -> new BoatItem(BOPEntities.EMPYREAL_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));
        EMPYREAL_CHEST_BOAT = registerItem(func, "empyreal_chest_boat", (properties) -> new BoatItem(BOPEntities.EMPYREAL_CHEST_BOAT, properties), new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1));


        // Register surface rules
        MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.OVERWORLD, BiomesOPlenty.MOD_ID, BOPMaterialRuleData::overworld);
        MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.NETHER, BiomesOPlenty.MOD_ID, BOPMaterialRuleData::nether);
        MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.END, BiomesOPlenty.MOD_ID, BOPMaterialRuleData::end);
    }

    public static void registerBlockItems(BiConsumer<Identifier, Item> func)
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

        DRIED_SALT = registerBlock(func, BOPBlocks.DRIED_SALT);

        SPHALERITE = registerBlock(func, BOPBlocks.SPHALERITE);
        SPHALERITE_SLAB = registerBlock(func, BOPBlocks.SPHALERITE_SLAB);
        SPHALERITE_STAIRS = registerBlock(func, BOPBlocks.SPHALERITE_STAIRS);
        SPHALERITE_WALL = registerBlock(func, BOPBlocks.SPHALERITE_WALL);
        POLISHED_SPHALERITE = registerBlock(func, BOPBlocks.POLISHED_SPHALERITE);
        POLISHED_SPHALERITE_SLAB = registerBlock(func, BOPBlocks.POLISHED_SPHALERITE_SLAB);
        POLISHED_SPHALERITE_STAIRS = registerBlock(func, BOPBlocks.POLISHED_SPHALERITE_STAIRS);
        POLISHED_SPHALERITE_WALL = registerBlock(func, BOPBlocks.POLISHED_SPHALERITE_WALL);
        SPHALERITE_BRICKS = registerBlock(func, BOPBlocks.SPHALERITE_BRICKS);
        SPHALERITE_BRICK_SLAB = registerBlock(func, BOPBlocks.SPHALERITE_BRICK_SLAB);
        SPHALERITE_BRICK_STAIRS = registerBlock(func, BOPBlocks.SPHALERITE_BRICK_STAIRS);
        SPHALERITE_BRICK_WALL = registerBlock(func, BOPBlocks.SPHALERITE_BRICK_WALL);
        CHISELED_SPHALERITE = registerBlock(func, BOPBlocks.CHISELED_SPHALERITE);
        POTENT_SPHALERITE = registerBlock(func, BOPBlocks.POTENT_SPHALERITE);

        ORPIMENT = registerBlock(func, BOPBlocks.ORPIMENT);
        ORPIMENT_SLAB = registerBlock(func, BOPBlocks.ORPIMENT_SLAB);
        ORPIMENT_STAIRS = registerBlock(func, BOPBlocks.ORPIMENT_STAIRS);
        ORPIMENT_WALL = registerBlock(func, BOPBlocks.ORPIMENT_WALL);
        POLISHED_ORPIMENT = registerBlock(func, BOPBlocks.POLISHED_ORPIMENT);
        POLISHED_ORPIMENT_SLAB = registerBlock(func, BOPBlocks.POLISHED_ORPIMENT_SLAB);
        POLISHED_ORPIMENT_STAIRS = registerBlock(func, BOPBlocks.POLISHED_ORPIMENT_STAIRS);
        POLISHED_ORPIMENT_WALL = registerBlock(func, BOPBlocks.POLISHED_ORPIMENT_WALL);
        ORPIMENT_BRICKS = registerBlock(func, BOPBlocks.ORPIMENT_BRICKS);
        ORPIMENT_BRICK_SLAB = registerBlock(func, BOPBlocks.ORPIMENT_BRICK_SLAB);
        ORPIMENT_BRICK_STAIRS = registerBlock(func, BOPBlocks.ORPIMENT_BRICK_STAIRS);
        ORPIMENT_BRICK_WALL = registerBlock(func, BOPBlocks.ORPIMENT_BRICK_WALL);
        CHISELED_ORPIMENT = registerBlock(func, BOPBlocks.CHISELED_ORPIMENT);
        ORPIMENT_FUMAROLE = registerBlock(func, BOPBlocks.ORPIMENT_FUMAROLE);
        ORPIMENT_CLUSTER = registerBlock(func, BOPBlocks.ORPIMENT_CLUSTER);
        ORPIMENT_BUD = registerBlock(func, BOPBlocks.ORPIMENT_BUD);

        FLESH = registerBlock(func, BOPBlocks.FLESH);
        POROUS_FLESH = registerBlock(func, BOPBlocks.POROUS_FLESH);
        FLESH_TENDONS = registerBlock(func, BOPBlocks.FLESH_TENDONS);
        FLESH_TENDONS_STRAND = registerBlock(func, BOPBlocks.FLESH_TENDONS_STRAND);
        EYEBULB = registerBlock(func, BOPBlocks.EYEBULB);
        HAIR = registerBlock(func, BOPBlocks.HAIR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_BAMBOO));
        PUS_BUBBLE = registerBlock(func, BOPBlocks.PUS_BUBBLE);
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
        GLOWWORM_SILK = registerBlock(func, BOPBlocks.GLOWWORM_SILK, BlockItem::new, new Item.Properties().trimMaterial(ModTrimMaterials.GLOWWORM_SILK));
        GLOWWORM_SILK_STRAND = registerBlock(func, BOPBlocks.GLOWWORM_SILK_STRAND);
        SPIDER_EGG = registerBlock(func, BOPBlocks.SPIDER_EGG);
        HANGING_COBWEB = registerBlock(func, BOPBlocks.HANGING_COBWEB);
        HANGING_COBWEB_STRAND = registerBlock(func, BOPBlocks.HANGING_COBWEB_STRAND);
        STRINGY_COBWEB = registerBlock(func, BOPBlocks.STRINGY_COBWEB, StringyCobwebBlockItem::new);
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
        FLOWER_STEM = registerBlock(func, BOPBlocks.FLOWER_STEM, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_GRASS_BLOCK = registerBlock(func, BOPBlocks.ORIGIN_GRASS_BLOCK);

        FLOWERING_OAK_SAPLING = registerBlock(func, BOPBlocks.FLOWERING_OAK_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        FLOWERING_OAK_LEAVES = registerBlock(func, BOPBlocks.FLOWERING_OAK_LEAVES);
        CYPRESS_SAPLING = registerBlock(func, BOPBlocks.CYPRESS_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        CYPRESS_LEAVES = registerBlock(func, BOPBlocks.CYPRESS_LEAVES);
        SNOWBLOSSOM_SAPLING = registerBlock(func, BOPBlocks.SNOWBLOSSOM_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        SNOWBLOSSOM_LEAVES = registerBlock(func, BOPBlocks.SNOWBLOSSOM_LEAVES);

        ORIGIN_OAK_SAPLING = registerBlock(func, BOPBlocks.ORIGIN_OAK_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        ORIGIN_OAK_LEAVES = registerBlock(func, BOPBlocks.ORIGIN_OAK_LEAVES);
        ORIGIN_OAK_LOG = registerBlock(func, BOPBlocks.ORIGIN_OAK_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_WOOD = registerBlock(func, BOPBlocks.ORIGIN_OAK_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_ORIGIN_OAK_LOG = registerBlock(func, BOPBlocks.STRIPPED_ORIGIN_OAK_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_ORIGIN_OAK_WOOD = registerBlock(func, BOPBlocks.STRIPPED_ORIGIN_OAK_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_PLANKS = registerBlock(func, BOPBlocks.ORIGIN_OAK_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_STAIRS = registerBlock(func, BOPBlocks.ORIGIN_OAK_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_SLAB = registerBlock(func, BOPBlocks.ORIGIN_OAK_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        ORIGIN_OAK_FENCE = registerBlock(func, BOPBlocks.ORIGIN_OAK_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_FENCE_GATE = registerBlock(func, BOPBlocks.ORIGIN_OAK_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_DOOR = registerBlock(func, BOPBlocks.ORIGIN_OAK_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        ORIGIN_OAK_TRAPDOOR = registerBlock(func, BOPBlocks.ORIGIN_OAK_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_PRESSURE_PLATE = registerBlock(func, BOPBlocks.ORIGIN_OAK_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        ORIGIN_OAK_BUTTON = registerBlock(func, BOPBlocks.ORIGIN_OAK_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        ORIGIN_OAK_SHELF = registerBlock(func, BOPBlocks.ORIGIN_OAK_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        FIR_SAPLING = registerBlock(func, BOPBlocks.FIR_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        FIR_LEAVES = registerBlock(func, BOPBlocks.FIR_LEAVES);
        FIR_LOG = registerBlock(func, BOPBlocks.FIR_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_WOOD = registerBlock(func, BOPBlocks.FIR_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_FIR_LOG = registerBlock(func, BOPBlocks.STRIPPED_FIR_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_FIR_WOOD = registerBlock(func, BOPBlocks.STRIPPED_FIR_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_PLANKS = registerBlock(func, BOPBlocks.FIR_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_STAIRS = registerBlock(func, BOPBlocks.FIR_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_SLAB = registerBlock(func, BOPBlocks.FIR_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        FIR_FENCE = registerBlock(func, BOPBlocks.FIR_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_FENCE_GATE = registerBlock(func, BOPBlocks.FIR_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_DOOR = registerBlock(func, BOPBlocks.FIR_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        FIR_TRAPDOOR = registerBlock(func, BOPBlocks.FIR_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_PRESSURE_PLATE = registerBlock(func, BOPBlocks.FIR_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        FIR_BUTTON = registerBlock(func, BOPBlocks.FIR_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        FIR_SHELF = registerBlock(func, BOPBlocks.FIR_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        PINE_SAPLING = registerBlock(func, BOPBlocks.PINE_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        PINE_LEAVES = registerBlock(func, BOPBlocks.PINE_LEAVES);
        PINE_LOG = registerBlock(func, BOPBlocks.PINE_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_WOOD = registerBlock(func, BOPBlocks.PINE_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_PINE_LOG = registerBlock(func, BOPBlocks.STRIPPED_PINE_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_PINE_WOOD = registerBlock(func, BOPBlocks.STRIPPED_PINE_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_PLANKS = registerBlock(func, BOPBlocks.PINE_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_STAIRS = registerBlock(func, BOPBlocks.PINE_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_SLAB = registerBlock(func, BOPBlocks.PINE_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        PINE_FENCE = registerBlock(func, BOPBlocks.PINE_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_FENCE_GATE = registerBlock(func, BOPBlocks.PINE_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_DOOR = registerBlock(func, BOPBlocks.PINE_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        PINE_TRAPDOOR = registerBlock(func, BOPBlocks.PINE_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_PRESSURE_PLATE = registerBlock(func, BOPBlocks.PINE_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PINE_BUTTON = registerBlock(func, BOPBlocks.PINE_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        PINE_SHELF = registerBlock(func, BOPBlocks.PINE_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        MAPLE_SAPLING = registerBlock(func, BOPBlocks.MAPLE_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        MAPLE_LEAF_LITTER = registerBlock(func, BOPBlocks.MAPLE_LEAF_LITTER, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        PINK_MAPLE_LEAVES = registerBlock(func, BOPBlocks.PINK_MAPLE_LEAVES);
        MAGENTA_MAPLE_LEAVES = registerBlock(func, BOPBlocks.MAGENTA_MAPLE_LEAVES);
        PURPLE_MAPLE_LEAVES = registerBlock(func, BOPBlocks.PURPLE_MAPLE_LEAVES);
        MAPLE_LOG = registerBlock(func, BOPBlocks.MAPLE_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_WOOD = registerBlock(func, BOPBlocks.MAPLE_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAPLE_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAPLE_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAPLE_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAPLE_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_PLANKS = registerBlock(func, BOPBlocks.MAPLE_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_STAIRS = registerBlock(func, BOPBlocks.MAPLE_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_SLAB = registerBlock(func, BOPBlocks.MAPLE_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        MAPLE_FENCE = registerBlock(func, BOPBlocks.MAPLE_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_FENCE_GATE = registerBlock(func, BOPBlocks.MAPLE_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_DOOR = registerBlock(func, BOPBlocks.MAPLE_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        MAPLE_TRAPDOOR = registerBlock(func, BOPBlocks.MAPLE_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAPLE_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAPLE_BUTTON = registerBlock(func, BOPBlocks.MAPLE_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        MAPLE_SHELF = registerBlock(func, BOPBlocks.MAPLE_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        REDWOOD_SAPLING = registerBlock(func, BOPBlocks.REDWOOD_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        REDWOOD_LEAVES = registerBlock(func, BOPBlocks.REDWOOD_LEAVES);
        REDWOOD_LOG = registerBlock(func, BOPBlocks.REDWOOD_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_WOOD = registerBlock(func, BOPBlocks.REDWOOD_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_REDWOOD_LOG = registerBlock(func, BOPBlocks.STRIPPED_REDWOOD_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_REDWOOD_WOOD = registerBlock(func, BOPBlocks.STRIPPED_REDWOOD_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_PLANKS = registerBlock(func, BOPBlocks.REDWOOD_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_STAIRS = registerBlock(func, BOPBlocks.REDWOOD_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_SLAB = registerBlock(func, BOPBlocks.REDWOOD_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        REDWOOD_FENCE = registerBlock(func, BOPBlocks.REDWOOD_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_FENCE_GATE = registerBlock(func, BOPBlocks.REDWOOD_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_DOOR = registerBlock(func, BOPBlocks.REDWOOD_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        REDWOOD_TRAPDOOR = registerBlock(func, BOPBlocks.REDWOOD_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_PRESSURE_PLATE = registerBlock(func, BOPBlocks.REDWOOD_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        REDWOOD_BUTTON = registerBlock(func, BOPBlocks.REDWOOD_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        REDWOOD_SHELF = registerBlock(func, BOPBlocks.REDWOOD_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        MAHOGANY_SAPLING = registerBlock(func, BOPBlocks.MAHOGANY_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        MAHOGANY_LEAVES = registerBlock(func, BOPBlocks.MAHOGANY_LEAVES);
        MAHOGANY_LOG = registerBlock(func, BOPBlocks.MAHOGANY_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_WOOD = registerBlock(func, BOPBlocks.MAHOGANY_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAHOGANY_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAHOGANY_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAHOGANY_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAHOGANY_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_PLANKS = registerBlock(func, BOPBlocks.MAHOGANY_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_STAIRS = registerBlock(func, BOPBlocks.MAHOGANY_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_SLAB = registerBlock(func, BOPBlocks.MAHOGANY_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        MAHOGANY_FENCE = registerBlock(func, BOPBlocks.MAHOGANY_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_FENCE_GATE = registerBlock(func, BOPBlocks.MAHOGANY_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_DOOR = registerBlock(func, BOPBlocks.MAHOGANY_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        MAHOGANY_TRAPDOOR = registerBlock(func, BOPBlocks.MAHOGANY_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAHOGANY_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAHOGANY_BUTTON = registerBlock(func, BOPBlocks.MAHOGANY_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        MAHOGANY_SHELF = registerBlock(func, BOPBlocks.MAHOGANY_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        JACARANDA_SAPLING = registerBlock(func, BOPBlocks.JACARANDA_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        JACARANDA_LEAVES = registerBlock(func, BOPBlocks.JACARANDA_LEAVES);
        JACARANDA_LOG = registerBlock(func, BOPBlocks.JACARANDA_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_WOOD = registerBlock(func, BOPBlocks.JACARANDA_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_JACARANDA_LOG = registerBlock(func, BOPBlocks.STRIPPED_JACARANDA_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_JACARANDA_WOOD = registerBlock(func, BOPBlocks.STRIPPED_JACARANDA_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_PLANKS = registerBlock(func, BOPBlocks.JACARANDA_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_STAIRS = registerBlock(func, BOPBlocks.JACARANDA_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_SLAB = registerBlock(func, BOPBlocks.JACARANDA_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        JACARANDA_FENCE = registerBlock(func, BOPBlocks.JACARANDA_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_FENCE_GATE = registerBlock(func, BOPBlocks.JACARANDA_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_DOOR = registerBlock(func, BOPBlocks.JACARANDA_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        JACARANDA_TRAPDOOR = registerBlock(func, BOPBlocks.JACARANDA_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_PRESSURE_PLATE = registerBlock(func, BOPBlocks.JACARANDA_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        JACARANDA_BUTTON = registerBlock(func, BOPBlocks.JACARANDA_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        JACARANDA_SHELF = registerBlock(func, BOPBlocks.JACARANDA_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        PALM_SAPLING = registerBlock(func, BOPBlocks.PALM_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        PALM_LEAVES = registerBlock(func, BOPBlocks.PALM_LEAVES);
        PALM_LOG = registerBlock(func, BOPBlocks.PALM_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_WOOD = registerBlock(func, BOPBlocks.PALM_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_PALM_LOG = registerBlock(func, BOPBlocks.STRIPPED_PALM_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_PALM_WOOD = registerBlock(func, BOPBlocks.STRIPPED_PALM_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_PLANKS = registerBlock(func, BOPBlocks.PALM_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_STAIRS = registerBlock(func, BOPBlocks.PALM_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_SLAB = registerBlock(func, BOPBlocks.PALM_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        PALM_FENCE = registerBlock(func, BOPBlocks.PALM_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_FENCE_GATE = registerBlock(func, BOPBlocks.PALM_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_DOOR = registerBlock(func, BOPBlocks.PALM_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        PALM_TRAPDOOR = registerBlock(func, BOPBlocks.PALM_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_PRESSURE_PLATE = registerBlock(func, BOPBlocks.PALM_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        PALM_BUTTON = registerBlock(func, BOPBlocks.PALM_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        PALM_SHELF = registerBlock(func, BOPBlocks.PALM_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        WILLOW_SAPLING = registerBlock(func, BOPBlocks.WILLOW_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        WILLOW_VINE = registerBlock(func, BOPBlocks.WILLOW_VINE);
        SPANISH_MOSS = registerBlock(func, BOPBlocks.SPANISH_MOSS);
        SPANISH_MOSS_PLANT = registerBlock(func, BOPBlocks.SPANISH_MOSS_PLANT);
        WILLOW_LEAVES = registerBlock(func, BOPBlocks.WILLOW_LEAVES);
        WILLOW_LOG = registerBlock(func, BOPBlocks.WILLOW_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_WOOD = registerBlock(func, BOPBlocks.WILLOW_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_WILLOW_LOG = registerBlock(func, BOPBlocks.STRIPPED_WILLOW_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_WILLOW_WOOD = registerBlock(func, BOPBlocks.STRIPPED_WILLOW_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_PLANKS = registerBlock(func, BOPBlocks.WILLOW_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_STAIRS = registerBlock(func, BOPBlocks.WILLOW_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_SLAB = registerBlock(func, BOPBlocks.WILLOW_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        WILLOW_FENCE = registerBlock(func, BOPBlocks.WILLOW_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_FENCE_GATE = registerBlock(func, BOPBlocks.WILLOW_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_DOOR = registerBlock(func, BOPBlocks.WILLOW_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        WILLOW_TRAPDOOR = registerBlock(func, BOPBlocks.WILLOW_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_PRESSURE_PLATE = registerBlock(func, BOPBlocks.WILLOW_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        WILLOW_BUTTON = registerBlock(func, BOPBlocks.WILLOW_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        WILLOW_SHELF = registerBlock(func, BOPBlocks.WILLOW_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        DEAD_SAPLING = registerBlock(func, BOPBlocks.DEAD_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        DEAD_BRANCH = registerBlock(func, BOPBlocks.DEAD_BRANCH, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        DEAD_LEAVES = registerBlock(func, BOPBlocks.DEAD_LEAVES);
        DEAD_LOG = registerBlock(func, BOPBlocks.DEAD_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_WOOD = registerBlock(func, BOPBlocks.DEAD_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_DEAD_LOG = registerBlock(func, BOPBlocks.STRIPPED_DEAD_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_DEAD_WOOD = registerBlock(func, BOPBlocks.STRIPPED_DEAD_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_PLANKS = registerBlock(func, BOPBlocks.DEAD_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_STAIRS = registerBlock(func, BOPBlocks.DEAD_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_SLAB = registerBlock(func, BOPBlocks.DEAD_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        DEAD_FENCE = registerBlock(func, BOPBlocks.DEAD_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_FENCE_GATE = registerBlock(func, BOPBlocks.DEAD_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_DOOR = registerBlock(func, BOPBlocks.DEAD_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        DEAD_TRAPDOOR = registerBlock(func, BOPBlocks.DEAD_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_PRESSURE_PLATE = registerBlock(func, BOPBlocks.DEAD_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        DEAD_BUTTON = registerBlock(func, BOPBlocks.DEAD_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        DEAD_SHELF = registerBlock(func, BOPBlocks.DEAD_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        MAGIC_SAPLING = registerBlock(func, BOPBlocks.MAGIC_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        MAGIC_LEAVES = registerBlock(func, BOPBlocks.MAGIC_LEAVES);
        MAGIC_LOG = registerBlock(func, BOPBlocks.MAGIC_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_WOOD = registerBlock(func, BOPBlocks.MAGIC_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAGIC_LOG = registerBlock(func, BOPBlocks.STRIPPED_MAGIC_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_MAGIC_WOOD = registerBlock(func, BOPBlocks.STRIPPED_MAGIC_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_PLANKS = registerBlock(func, BOPBlocks.MAGIC_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_STAIRS = registerBlock(func, BOPBlocks.MAGIC_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_SLAB = registerBlock(func, BOPBlocks.MAGIC_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        MAGIC_FENCE = registerBlock(func, BOPBlocks.MAGIC_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_FENCE_GATE = registerBlock(func, BOPBlocks.MAGIC_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_DOOR = registerBlock(func, BOPBlocks.MAGIC_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        MAGIC_TRAPDOOR = registerBlock(func, BOPBlocks.MAGIC_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_PRESSURE_PLATE = registerBlock(func, BOPBlocks.MAGIC_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        MAGIC_BUTTON = registerBlock(func, BOPBlocks.MAGIC_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        MAGIC_SHELF = registerBlock(func, BOPBlocks.MAGIC_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

        UMBRAN_SAPLING = registerBlock(func, BOPBlocks.UMBRAN_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        UMBRAN_LEAVES = registerBlock(func, BOPBlocks.UMBRAN_LEAVES);
        UMBRAN_LOG = registerBlock(func, BOPBlocks.UMBRAN_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_WOOD = registerBlock(func, BOPBlocks.UMBRAN_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_UMBRAN_LOG = registerBlock(func, BOPBlocks.STRIPPED_UMBRAN_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_UMBRAN_WOOD = registerBlock(func, BOPBlocks.STRIPPED_UMBRAN_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_PLANKS = registerBlock(func, BOPBlocks.UMBRAN_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_STAIRS = registerBlock(func, BOPBlocks.UMBRAN_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_SLAB = registerBlock(func, BOPBlocks.UMBRAN_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        UMBRAN_FENCE = registerBlock(func, BOPBlocks.UMBRAN_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_FENCE_GATE = registerBlock(func, BOPBlocks.UMBRAN_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_DOOR = registerBlock(func, BOPBlocks.UMBRAN_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        UMBRAN_TRAPDOOR = registerBlock(func, BOPBlocks.UMBRAN_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_PRESSURE_PLATE = registerBlock(func, BOPBlocks.UMBRAN_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        UMBRAN_BUTTON = registerBlock(func, BOPBlocks.UMBRAN_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        UMBRAN_SHELF = registerBlock(func, BOPBlocks.UMBRAN_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

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

        EMPYREAL_SAPLING = registerBlock(func, BOPBlocks.EMPYREAL_SAPLING, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        EMPYREAL_LEAVES = registerBlock(func, BOPBlocks.EMPYREAL_LEAVES);
        EMPYREAL_LOG = registerBlock(func, BOPBlocks.EMPYREAL_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_WOOD = registerBlock(func, BOPBlocks.EMPYREAL_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_EMPYREAL_LOG = registerBlock(func, BOPBlocks.STRIPPED_EMPYREAL_LOG, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        STRIPPED_EMPYREAL_WOOD = registerBlock(func, BOPBlocks.STRIPPED_EMPYREAL_WOOD, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_PLANKS = registerBlock(func, BOPBlocks.EMPYREAL_PLANKS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_STAIRS = registerBlock(func, BOPBlocks.EMPYREAL_STAIRS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_SLAB = registerBlock(func, BOPBlocks.EMPYREAL_SLAB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_SLABS));
        EMPYREAL_FENCE = registerBlock(func, BOPBlocks.EMPYREAL_FENCE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_FENCE_GATE = registerBlock(func, BOPBlocks.EMPYREAL_FENCE_GATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_DOOR = registerBlock(func, BOPBlocks.EMPYREAL_DOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_LARGE));
        EMPYREAL_TRAPDOOR = registerBlock(func, BOPBlocks.EMPYREAL_TRAPDOOR, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_PRESSURE_PLATE = registerBlock(func, BOPBlocks.EMPYREAL_PRESSURE_PLATE, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));
        EMPYREAL_BUTTON = registerBlock(func, BOPBlocks.EMPYREAL_BUTTON, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_ITEMS_EXTRA_SMALL));
        EMPYREAL_SHELF = registerBlock(func, BOPBlocks.EMPYREAL_SHELF, p_432579_ -> p_432579_.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY).cookingFuel(ContextIntProviders.COOKING_TIME_WOOD_BLOCKS));

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
        BURNING_BLOSSOM = registerBlock(func, BOPBlocks.BURNING_BLOSSOM, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_ROOTS));
        ENDBLOOM = registerBlock(func, BOPBlocks.ENDBLOOM);
        SPROUT = registerBlock(func, BOPBlocks.SPROUT);
        HIGH_GRASS = registerBlock(func, BOPBlocks.HIGH_GRASS);
        HIGH_GRASS_PLANT = registerBlock(func, BOPBlocks.HIGH_GRASS_PLANT);
        CLOVER = registerBlock(func, BOPBlocks.CLOVER);
        HUGE_CLOVER_PETAL = registerBlock(func, BOPBlocks.HUGE_CLOVER_PETAL);
        HUGE_LILY_PAD = registerBlock(func, BOPBlocks.HUGE_LILY_PAD, PlaceOnWaterBlockItem::new);
        WATERLILY = registerBlock(func, BOPBlocks.WATERLILY, PlaceOnWaterBlockItem::new);
        DUNE_GRASS = registerBlock(func, BOPBlocks.DUNE_GRASS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        DESERT_GRASS = registerBlock(func, BOPBlocks.DESERT_GRASS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        DEAD_GRASS = registerBlock(func, BOPBlocks.DEAD_GRASS, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
        TUNDRA_SHRUB = registerBlock(func, BOPBlocks.TUNDRA_SHRUB, p -> p.cookingFuel(ContextIntProviders.COOKING_TIME_DRY_PLANTS));
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
        POTTED_MAPLE_SAPLING = registerBlock(func, BOPBlocks.POTTED_MAPLE_SAPLING);
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

        ModLegacy.setupItems();
    }

    public static Item registerBlock(BiConsumer<Identifier, Item> func, Block block)
    {
        return registerBlock(func, block, BlockItem::new);
    }

    public static Item registerBlock(BiConsumer<Identifier, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory)
    {
        return registerBlock(func, block, factory, new Item.Properties());
    }

    public static Item registerBlock(BiConsumer<Identifier, Item> func, Block block, UnaryOperator<Item.Properties> operator)
    {
        return registerBlock(func, block, (p_371022_, p_371023_) -> new BlockItem(p_371022_, operator.apply(p_371023_)), new Item.Properties());
    }

    public static Item registerBlock(BiConsumer<Identifier, Item> func, Block block, BiFunction<Block, Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, blockIdToItemId(block.builtInRegistryHolder().key()), p_370785_ -> factory.apply(block, p_370785_), properties.useBlockDescriptionPrefix()
        );
    }

    private static Item registerItem(BiConsumer<Identifier, Item> func, ResourceKey<Item> key, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        Item item = factory.apply(properties.setId(key));
        func.accept(key.identifier(), item);
        return item;
    }

    private static Item registerItem(BiConsumer<Identifier, Item> func, String name, Function<Item.Properties, Item> factory, Item.Properties properties)
    {
        return registerItem(func, itemId(name), factory, properties);
    }

    private static Item registerItem(BiConsumer<Identifier, Item> func, String name, Item.Properties properties)
    {
        return registerItem(func, itemId(name), Item::new, properties);
    }

    private static ResourceKey<Item> blockIdToItemId(ResourceKey<Block> key)
    {
        return ResourceKey.create(Registries.ITEM, key.identifier());
    }

    private static ResourceKey<Item> itemId(String name)
    {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name));
    }
}