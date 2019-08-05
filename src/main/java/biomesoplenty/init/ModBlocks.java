/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.*;
import biomesoplenty.common.block.trees.*;
import biomesoplenty.common.item.WaterPlantItem;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModBlocks
{
    public static void init()
    {
        //Terrain
    	white_sand = registerBlock(new WhiteSandBlock(0xF3F1E4, Block.Properties.create(Material.SAND, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestLevel(0).harvestTool(ToolType.SHOVEL)), "white_sand");
    	white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "white_sandstone");
    	chiseled_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "chiseled_white_sandstone");
    	cut_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "cut_white_sandstone");
        cut_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(cut_white_sandstone)), "cut_white_sandstone_slab");
    	smooth_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F)), "smooth_white_sandstone");
        smooth_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(smooth_white_sandstone)), "smooth_white_sandstone_slab");
        smooth_white_sandstone_stairs = registerBlock(new StairsBlock(white_sandstone.getDefaultState(), Block.Properties.from(smooth_white_sandstone)), "smooth_white_sandstone_stairs");
        white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.from(white_sandstone)), "white_sandstone_slab");
        white_sandstone_stairs = registerBlock(new StairsBlock(white_sandstone.getDefaultState(), Block.Properties.from(white_sandstone)), "white_sandstone_stairs");
    	white_sandstone_wall = registerBlock(new WallBlock(Block.Properties.from(white_sandstone)),"white_sandstone_wall");

        mud = registerBlock(new MudBlock(Block.Properties.create(Material.EARTH, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(0.6F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(new SoundType(1.0F, 0.5F, SoundEvents.BLOCK_SLIME_BLOCK_BREAK, SoundEvents.BLOCK_SLIME_BLOCK_STEP, SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundEvents.BLOCK_SLIME_BLOCK_HIT, SoundEvents.BLOCK_SLIME_BLOCK_FALL))), "mud");
        mud_bricks = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(1.0F)), "mud_bricks");
        mud_brick_slab = registerBlock(new SlabBlock(Block.Properties.from(mud_bricks)), "mud_brick_slab");
        mud_brick_stairs = registerBlock(new StairsBlock(mud_bricks.getDefaultState(), Block.Properties.from(mud_bricks)), "mud_brick_stairs");
        mud_brick_wall = registerBlock(new WallBlock(Block.Properties.from(mud_bricks)),"mud_brick_wall");

        dried_sand = registerBlock(new DriedSandBlock(Block.Properties.create(Material.EARTH, MaterialColor.WOOD).hardnessAndResistance(1.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_GRAVEL_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_FALL))), "dried_sand");
        ash_block = registerBlock(new AshBlock(Block.Properties.create(Material.SAND, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(0.4F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(SoundType.SAND)), "ash_block");
        flesh = registerBlock(new FleshBlock(Block.Properties.create(Material.SPONGE, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.BLOCK_CORAL_BLOCK_BREAK, SoundEvents.BLOCK_CORAL_BLOCK_STEP, SoundEvents.BLOCK_CORAL_BLOCK_PLACE, SoundEvents.BLOCK_CORAL_BLOCK_HIT, SoundEvents.BLOCK_CORAL_BLOCK_FALL))), "flesh");

        //Trees
        origin_sapling = registerBlock(new SaplingBlockBOP(new OriginTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "origin_sapling");
        origin_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.EMERALD).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "origin_leaves");
        flowering_oak_sapling = registerBlock(new SaplingBlockBOP(new FloweringOakTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "flowering_oak_sapling");
        flowering_oak_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "flowering_oak_leaves");
        yellow_autumn_sapling = registerBlock(new SaplingBlockBOP(new YellowAutumnTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "yellow_autumn_sapling");
        yellow_autumn_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "yellow_autumn_leaves");
        orange_autumn_sapling = registerBlock(new SaplingBlockBOP(new OrangeAutumnTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "orange_autumn_sapling");
        orange_autumn_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "orange_autumn_leaves");
        maple_sapling = registerBlock(new SaplingBlockBOP(new MapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "maple_sapling");
        maple_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.RED).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "maple_leaves");

        fir_sapling = registerBlock(new SaplingBlockBOP(new FirTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "fir_sapling");
        fir_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "fir_leaves");
        fir_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_log");
        fir_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_wood");
        stripped_fir_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_log");
        stripped_fir_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        fir_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        fir_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        fir_stairs = registerBlock(new StairsBlock(fir_planks.getDefaultState(), Block.Properties.from(fir_planks)), "fir_stairs");
        fir_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        fir_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        fir_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_door");
        fir_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_trapdoor");
        fir_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, fir_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        fir_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_button");
        
        redwood_sapling = registerBlock(new SaplingBlockBOP(new RedwoodTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "redwood_sapling");
        redwood_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "redwood_leaves");
        redwood_log = registerBlock(new LogBlock(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "redwood_log");
        redwood_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        stripped_redwood_log = registerBlock(new LogBlock(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_redwood_log");
        stripped_redwood_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        redwood_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        redwood_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        redwood_stairs = registerBlock(new StairsBlock(redwood_planks.getDefaultState(), Block.Properties.from(redwood_planks)), "redwood_stairs");
        redwood_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        redwood_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence_gate");
        redwood_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "redwood_door");
        redwood_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "redwood_trapdoor");
        redwood_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, redwood_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "redwood_pressure_plate");
        redwood_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "redwood_button");
        
        white_cherry_sapling = registerBlock(new SaplingBlockBOP(new WhiteCherryTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "white_cherry_sapling");
        white_cherry_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.SNOW).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "white_cherry_leaves");
        pink_cherry_sapling = registerBlock(new SaplingBlockBOP(new PinkCherryTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_cherry_sapling");
        pink_cherry_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.PINK).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "pink_cherry_leaves");
        cherry_log = registerBlock(new LogBlock(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_log");
        cherry_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        stripped_cherry_log = registerBlock(new LogBlock(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_cherry_log");
        stripped_cherry_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        cherry_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        cherry_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        cherry_stairs = registerBlock(new StairsBlock(cherry_planks.getDefaultState(), Block.Properties.from(cherry_planks)), "cherry_stairs");
        cherry_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        cherry_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate");
        cherry_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_door");
        cherry_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_trapdoor");
        cherry_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, cherry_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate");
        cherry_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_button");
        
        mahogany_sapling = registerBlock(new SaplingBlockBOP(new MahoganyTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "mahogany_sapling");
        mahogany_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "mahogany_leaves");
        mahogany_log = registerBlock(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "mahogany_log");
        mahogany_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        stripped_mahogany_log = registerBlock(new LogBlock(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_log");
        stripped_mahogany_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        mahogany_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        mahogany_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        mahogany_stairs = registerBlock(new StairsBlock(mahogany_planks.getDefaultState(), Block.Properties.from(mahogany_planks)), "mahogany_stairs");
        mahogany_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        mahogany_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence_gate");
        mahogany_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "mahogany_door");
        mahogany_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "mahogany_trapdoor");
        mahogany_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "mahogany_pressure_plate");
        mahogany_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "mahogany_button");
        
        jacaranda_sapling = registerBlock(new SaplingBlockBOP(new JacarandaTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "jacaranda_sapling");
        jacaranda_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.PURPLE).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "jacaranda_leaves");
        jacaranda_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "jacaranda_log");
        jacaranda_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        stripped_jacaranda_log = registerBlock(new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_log");
        stripped_jacaranda_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        jacaranda_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        jacaranda_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        jacaranda_stairs = registerBlock(new StairsBlock(jacaranda_planks.getDefaultState(), Block.Properties.from(jacaranda_planks)), "jacaranda_stairs");
        jacaranda_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        jacaranda_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence_gate");
        jacaranda_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "jacaranda_door");
        jacaranda_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "jacaranda_trapdoor");
        jacaranda_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "jacaranda_pressure_plate");
        jacaranda_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "jacaranda_button");
        
        palm_sapling = registerBlock(new SaplingBlockBOP(new PalmTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "palm_sapling");
        palm_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "palm_leaves");
        palm_log = registerBlock(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "palm_log");
        palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "palm_wood");
        stripped_palm_log = registerBlock(new LogBlock(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_palm_log");
        stripped_palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        palm_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        palm_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        palm_stairs = registerBlock(new StairsBlock(palm_planks.getDefaultState(), Block.Properties.from(palm_planks)), "palm_stairs");
        palm_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        palm_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence_gate");
        palm_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "palm_door");
        palm_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "palm_trapdoor");
        palm_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, palm_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "palm_pressure_plate");
        palm_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "palm_button");
        
        willow_sapling = registerBlock(new SaplingBlockBOP(new WillowTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "willow_sapling");
        willow_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "willow_leaves");
        willow_log = registerBlock(new LogBlock(MaterialColor.LIME_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "willow_log");
        willow_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "willow_wood");
        stripped_willow_log = registerBlock(new LogBlock(MaterialColor.LIME_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_willow_log");
        stripped_willow_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        willow_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        willow_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        willow_stairs = registerBlock(new StairsBlock(willow_planks.getDefaultState(), Block.Properties.from(willow_planks)), "willow_stairs");
        willow_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        willow_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence_gate");
        willow_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "willow_door");
        willow_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "willow_trapdoor");
        willow_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, willow_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "willow_pressure_plate");
        willow_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "willow_button");
        
        dead_sapling = registerBlock(new SaplingBlockBOP(new DeadTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dead_sapling");
        dead_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.WOOD).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "dead_leaves");
        dead_log = registerBlock(new LogBlock(MaterialColor.STONE, Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "dead_log");
        dead_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "dead_wood");
        stripped_dead_log = registerBlock(new LogBlock(MaterialColor.STONE, Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_dead_log");
        stripped_dead_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        dead_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        dead_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        dead_stairs = registerBlock(new StairsBlock(dead_planks.getDefaultState(), Block.Properties.from(dead_planks)), "dead_stairs");
        dead_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        dead_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence_gate");
        dead_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "dead_door");
        dead_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "dead_trapdoor");
        dead_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, dead_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "dead_pressure_plate");
        dead_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "dead_button");
        
        magic_sapling = registerBlock(new SaplingBlockBOP(new MagicTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "magic_sapling");
        magic_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.CYAN).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "magic_leaves");
        magic_log = registerBlock(new LogBlock(MaterialColor.BLUE, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "magic_log");
        magic_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "magic_wood");
        stripped_magic_log = registerBlock(new LogBlock(MaterialColor.BLUE, Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_magic_log");
        stripped_magic_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        magic_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        magic_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        magic_stairs = registerBlock(new StairsBlock(magic_planks.getDefaultState(), Block.Properties.from(magic_planks)), "magic_stairs");
        magic_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        magic_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence_gate");
        magic_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "magic_door");
        magic_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "magic_trapdoor");
        magic_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, magic_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "magic_pressure_plate");
        magic_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "magic_button");
        
        umbran_sapling = registerBlock(new SaplingBlockBOP(new UmbranTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "umbran_sapling");
        umbran_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "umbran_leaves");
        umbran_log = registerBlock(new LogBlock(MaterialColor.BLUE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "umbran_log");
        umbran_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        stripped_umbran_log = registerBlock(new LogBlock(MaterialColor.BLUE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_umbran_log");
        stripped_umbran_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        umbran_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        umbran_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        umbran_stairs = registerBlock(new StairsBlock(umbran_planks.getDefaultState(), Block.Properties.from(umbran_planks)), "umbran_stairs");
        umbran_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        umbran_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence_gate");
        umbran_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "umbran_door");
        umbran_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "umbran_trapdoor");
        umbran_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, umbran_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "umbran_pressure_plate");
        umbran_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "umbran_button");
        
        hellbark_sapling = registerBlock(new SaplingBlockBOP(new HellbarkTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "hellbark_sapling");
        hellbark_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "hellbark_leaves");
        hellbark_log = registerBlock(new LogBlock(MaterialColor.GRAY_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "hellbark_log");
        hellbark_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        stripped_hellbark_log = registerBlock(new LogBlock(MaterialColor.GRAY_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_log");
        stripped_hellbark_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        hellbark_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        hellbark_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        hellbark_stairs = registerBlock(new StairsBlock(hellbark_planks.getDefaultState(), Block.Properties.from(hellbark_planks)), "hellbark_stairs");
        hellbark_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        hellbark_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence_gate");
        hellbark_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "hellbark_door");
        hellbark_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "hellbark_trapdoor");
        hellbark_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "hellbark_pressure_plate");
        hellbark_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "hellbark_button");
        
        ethereal_sapling = registerBlock(new SaplingBlockBOP(new EtherealTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)), "ethereal_sapling");
        ethereal_leaves = registerBlock(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "ethereal_leaves");
        ethereal_log = registerBlock(new LogBlock(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ethereal_log");
        ethereal_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ethereal_wood");
        stripped_ethereal_log = registerBlock(new LogBlock(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_ethereal_log");
        stripped_ethereal_wood = registerBlock(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_ethereal_wood");
        ethereal_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_planks");
        ethereal_slab = registerBlock(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_slab");
        ethereal_stairs = registerBlock(new StairsBlock(ethereal_planks.getDefaultState(), Block.Properties.from(ethereal_planks)), "ethereal_stairs");
        ethereal_fence = registerBlock(new FenceBlock(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_fence");
        ethereal_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_fence_gate");
        ethereal_door = registerBlock(new DoorBlock(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ethereal_door");
        ethereal_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ethereal_trapdoor");
        ethereal_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ethereal_pressure_plate");
        ethereal_button = registerBlock(new WoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ethereal_button");

        //Flowers
        rose = registerBlock(new FlowerBlockBOP(Effects.SPEED, 7, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "rose");
        blue_hydrangea = registerBlock(new FlowerBlockBOP(Effects.WATER_BREATHING, 7, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "blue_hydrangea");
        violet = registerBlock(new FlowerBlockBOP(Effects.NAUSEA, 10, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "violet");
        lavender = registerBlock(new FlowerBlockBOP(Effects.HEALTH_BOOST, 5, Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "lavender");
        goldenrod = registerBlock(new FlowerBlockBOP(Effects.LUCK, 5, Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "goldenrod");
        wildflower = registerBlock(new FlowerBlockBOP(Effects.HUNGER, 10, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wildflower");
        orange_cosmos = registerBlock(new FlowerBlockBOP(Effects.ABSORPTION, 7, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "orange_cosmos");
        pink_daffodil = registerBlock(new FlowerBlockBOP(Effects.INVISIBILITY, 7, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_daffodil");
        pink_hibiscus = registerBlock(new FlowerBlockBOP(Effects.REGENERATION, 5, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_hibiscus");
        glowflower = registerBlock(new FlowerBlockBOP(Effects.GLOWING, 10, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(9)), "glowflower");
        wilted_lily = registerBlock(new FlowerBlockBOP(Effects.UNLUCK, 5, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wilted_lily");
        burning_blossom = registerBlock(new FlowerBlockBOP(Effects.FIRE_RESISTANCE, 7, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(7)), "burning_blossom");

        //Vines
        willow_vine = registerBlock(new VineBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT)), "willow_vine");

        //Plants
        sprout = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "sprout");
        bush = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "bush");
        barley = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.YELLOW_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "barley");
        dune_grass = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.LIME_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dune_grass");
        desert_grass = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.ORANGE_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "desert_grass");
        dead_grass = registerBlock(new FoliageBlockBOP(Block.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dead_grass");
        cattail = registerBlock(new WatersidePlantBlock(Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "cattail");
        tall_cattail = registerBlock(new DoubleWatersidePlantBlock(cattail, Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "tall_cattail");

        reed = new WaterPlantBlock(Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT));
        registerBlock(reed, new WaterPlantItem(BOPBlocks.reed, new Item.Properties().group(ItemGroupBOP.instance)), "reed");
        watergrass = new WaterPlantBlock(Block.Properties.create(Material.PLANTS, MaterialColor.GRASS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT));
        registerBlock(watergrass, new WaterPlantItem(BOPBlocks.watergrass, new Item.Properties().group(ItemGroupBOP.instance)), "watergrass");
        mangrove_root = new WaterPlantBlock(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.WOOD));
        registerBlock(mangrove_root, new WaterPlantItem(BOPBlocks.mangrove_root, new Item.Properties().group(ItemGroupBOP.instance)), "mangrove_root");

        bramble = registerBlock(new BrambleBlock(Block.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).hardnessAndResistance(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(SoundType.WOOD)), "bramble");
        toadstool = registerBlock(new MushroomBlockBOP(Block.Properties.create(Material.PLANTS, MaterialColor.ADOBE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "toadstool");
        glowshroom = registerBlock(new MushroomBlockBOP(Block.Properties.create(Material.PLANTS, MaterialColor.DIAMOND).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(6)), "glowshroom");

        //Potted Plants
        potted_origin_sapling = registerBlockNoGroup(new FlowerPotBlock(origin_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_origin_sapling");
        potted_flowering_oak_sapling = registerBlockNoGroup(new FlowerPotBlock(flowering_oak_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_flowering_oak_sapling");
        potted_yellow_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(yellow_autumn_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_yellow_autumn_sapling");
        potted_orange_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(orange_autumn_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_orange_autumn_sapling");
        potted_maple_sapling = registerBlockNoGroup(new FlowerPotBlock(maple_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_maple_sapling");
        potted_fir_sapling = registerBlockNoGroup(new FlowerPotBlock(fir_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_fir_sapling");
        potted_redwood_sapling = registerBlockNoGroup(new FlowerPotBlock(redwood_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_redwood_sapling");
        potted_white_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(white_cherry_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_white_cherry_sapling");
        potted_pink_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(pink_cherry_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_pink_cherry_sapling");
        potted_mahogany_sapling = registerBlockNoGroup(new FlowerPotBlock(mahogany_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_mahogany_sapling");
        potted_jacaranda_sapling = registerBlockNoGroup(new FlowerPotBlock(jacaranda_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_jacaranda_sapling");
        potted_palm_sapling = registerBlockNoGroup(new FlowerPotBlock(palm_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_palm_sapling");
        potted_willow_sapling = registerBlockNoGroup(new FlowerPotBlock(willow_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_willow_sapling");
        potted_dead_sapling = registerBlockNoGroup(new FlowerPotBlock(dead_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_dead_sapling");
        potted_magic_sapling = registerBlockNoGroup(new FlowerPotBlock(magic_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_magic_sapling");
        potted_umbran_sapling = registerBlockNoGroup(new FlowerPotBlock(umbran_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_umbran_sapling");
        potted_hellbark_sapling = registerBlockNoGroup(new FlowerPotBlock(hellbark_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_hellbark_sapling");
        potted_ethereal_sapling = registerBlockNoGroup(new FlowerPotBlock(ethereal_sapling, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_ethereal_sapling");
        potted_rose = registerBlockNoGroup(new FlowerPotBlock(rose, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_rose");
        potted_blue_hydrangea = registerBlockNoGroup(new FlowerPotBlock(blue_hydrangea, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_blue_hydrangea");
        potted_violet = registerBlockNoGroup(new FlowerPotBlock(violet, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_violet");
        potted_lavender = registerBlockNoGroup(new FlowerPotBlock(lavender, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_lavender");
        potted_goldenrod = registerBlockNoGroup(new FlowerPotBlock(goldenrod, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_goldenrod");
        potted_wildflower = registerBlockNoGroup(new FlowerPotBlock(wildflower, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_wildflower");
        potted_orange_cosmos = registerBlockNoGroup(new FlowerPotBlock(orange_cosmos, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_orange_cosmos");
        potted_pink_daffodil = registerBlockNoGroup(new FlowerPotBlock(pink_daffodil, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_pink_daffodil");
        potted_pink_hibiscus = registerBlockNoGroup(new FlowerPotBlock(pink_hibiscus, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_pink_hibiscus");
        potted_glowflower = registerBlockNoGroup(new FlowerPotBlock(glowflower, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().lightValue(9)), "potted_glowflower");
        potted_wilted_lily = registerBlockNoGroup(new FlowerPotBlock(wilted_lily, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_wilted_lily");
        potted_burning_blossom = registerBlockNoGroup(new FlowerPotBlock(burning_blossom, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().lightValue(7)), "potted_burning_blossom");
        potted_sprout = registerBlockNoGroup(new FlowerPotBlock(sprout, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_sprout");
        potted_toadstool = registerBlockNoGroup(new FlowerPotBlock(toadstool, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()), "potted_toadstool");
        potted_glowshroom = registerBlockNoGroup(new FlowerPotBlock(glowshroom, Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().lightValue(6)), "potted_glowshroom");
    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(ItemGroupBOP.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlockNoGroup(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(null));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
    
    public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);

        if (itemBlock != null) {
            itemBlock.setRegistryName(name);
            ForgeRegistries.ITEMS.register(itemBlock);
        }

        return block;
    }
}
