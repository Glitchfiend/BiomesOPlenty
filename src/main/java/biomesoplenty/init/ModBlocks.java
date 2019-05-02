/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.block.BOPBlocks.*;

import biomesoplenty.common.block.BlockAsh;
import biomesoplenty.common.block.BlockBramble;
import biomesoplenty.common.block.BlockDoublePlantBOP;
import biomesoplenty.common.block.BlockDriedSand;
import biomesoplenty.common.block.BlockFlesh;
import biomesoplenty.common.block.BlockFlowerBOP;
import biomesoplenty.common.block.BlockFoliageBOP;
import biomesoplenty.common.block.BlockGrassBOP;
import biomesoplenty.common.block.BlockLeavesBOP;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.block.BlockMushroomBOP;
import biomesoplenty.common.block.BlockPlantBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static void init()
    {
    	origin_grass_block = registerBlock(new BlockGrassBOP(Block.Properties.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.PLANT)), "origin_grass_block");
    	
    	loamy_grass_block = registerBlock(new BlockGrassBOP(Block.Properties.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.PLANT)), "loamy_grass_block");
    	loamy_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "loamy_dirt");
    	coarse_loamy_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "coarse_loamy_dirt");
    	loamy_grass_path = registerBlock(new BlockGrassPath(Block.Properties.create(Material.GROUND).hardnessAndResistance(0.65F).sound(SoundType.PLANT)), "loamy_grass_path");
    	loamy_farmland = registerBlock(new BlockFarmland(Block.Properties.create(Material.GROUND, MaterialColor.GRAY_TERRACOTTA).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.GROUND)), "loamy_farmland");
    	
    	sandy_grass_block = registerBlock(new BlockGrassBOP(Block.Properties.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.PLANT)), "sandy_grass_block");
    	sandy_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.WOOD).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "sandy_dirt");
    	coarse_sandy_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.WOOD).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "coarse_sandy_dirt");
    	sandy_grass_path = registerBlock(new BlockGrassPath(Block.Properties.create(Material.GROUND).hardnessAndResistance(0.65F).sound(SoundType.PLANT)), "sandy_grass_path");
    	sandy_farmland = registerBlock(new BlockFarmland(Block.Properties.create(Material.GROUND, MaterialColor.WOOD).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.GROUND)), "sandy_farmland");
    	
    	silty_grass_block = registerBlock(new BlockGrassBOP(Block.Properties.create(Material.GRASS).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.PLANT)), "silty_grass_block");
    	silty_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "silty_dirt");
    	coarse_silty_dirt = registerBlock(new Block(Block.Properties.create(Material.GROUND, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), "coarse_silty_dirt");
    	silty_grass_path = registerBlock(new BlockGrassPath(Block.Properties.create(Material.GROUND).hardnessAndResistance(0.65F).sound(SoundType.PLANT)), "silty_grass_path");
    	silty_farmland = registerBlock(new BlockFarmland(Block.Properties.create(Material.GROUND, MaterialColor.LIGHT_GRAY_TERRACOTTA).needsRandomTick().hardnessAndResistance(0.6F).sound(SoundType.GROUND)), "silty_farmland");
    	
    	white_sand = registerBlock(new BlockSand(0xF3F1E4, Block.Properties.create(Material.SAND, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.SAND)), "white_sand");
    	white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "white_sandstone");
    	chiseled_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "chiseled_white_sandstone");
    	cut_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.8F)), "cut_white_sandstone");
    	smooth_white_sandstone = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F)), "smooth_white_sandstone");
    	white_sandstone_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F)), "white_sandstone_slab");
        white_sandstone_stairs = registerBlock(new BlockStairs(white_sandstone.getDefaultState(), Block.Properties.from(white_sandstone)), "white_sandstone_stairs");
    	
        mud = registerBlock(new BlockMud(Block.Properties.create(Material.GROUND, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(0.6F)), "mud");
        mud_brick_block = registerBlock(new Block(Block.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(1.0F)), "mud_brick_block");
        mud_brick_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.ROCK, MaterialColor.BROWN_TERRACOTTA).hardnessAndResistance(2.0F, 6.0F)), "mud_brick_slab");
        mud_brick_stairs = registerBlock(new BlockStairs(mud_brick_block.getDefaultState(), Block.Properties.from(mud_brick_block)), "mud_brick_stairs");
        
        dried_sand = registerBlock(new BlockDriedSand(Block.Properties.create(Material.GROUND, MaterialColor.WOOD).hardnessAndResistance(1.0F)), "dried_sand");
        ash_block = registerBlock(new BlockAsh(Block.Properties.create(Material.SAND, MaterialColor.BLACK_TERRACOTTA).hardnessAndResistance(0.4F).sound(SoundType.SAND)), "ash_block");
        flesh = registerBlock(new BlockFlesh(Block.Properties.create(Material.SPONGE, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(0.4F).sound(SoundType.SLIME)), "flesh");
        
        origin_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "origin_sapling");
        origin_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.EMERALD).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "origin_leaves");
        flowering_oak_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "flowering_oak_sapling");
        flowering_oak_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "flowering_oak_leaves");
        yellow_autumn_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "yellow_autumn_sapling");
        yellow_autumn_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "yellow_autumn_leaves");
        orange_autumn_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "orange_autumn_sapling");
        orange_autumn_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "orange_autumn_leaves");
        maple_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "maple_sapling");
        maple_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.RED).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "maple_leaves");

        fir_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "fir_sapling");
        fir_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "fir_leaves");
        fir_log = registerBlock(new BlockLog(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_log");
        fir_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_wood");
        stripped_fir_log = registerBlock(new BlockLog(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_log");
        stripped_fir_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        fir_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        fir_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        fir_stairs = registerBlock(new BlockStairs(fir_planks.getDefaultState(), Block.Properties.from(fir_planks)), "fir_stairs");
        fir_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        fir_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        fir_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_door");
        fir_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_trapdoor");
        fir_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, fir_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        fir_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_button");
        
        redwood_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "redwood_sapling");
        redwood_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "redwood_leaves");
        redwood_log = registerBlock(new BlockLog(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "redwood_log");
        redwood_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        stripped_redwood_log = registerBlock(new BlockLog(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_redwood_log");
        stripped_redwood_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        redwood_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        redwood_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        redwood_stairs = registerBlock(new BlockStairs(redwood_planks.getDefaultState(), Block.Properties.from(redwood_planks)), "redwood_stairs");
        redwood_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        redwood_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence_gate");
        redwood_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, redwood_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "redwood_door");
        redwood_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "redwood_trapdoor");
        redwood_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, redwood_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "redwood_pressure_plate");
        redwood_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "redwood_button");
        
        white_cherry_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "white_cherry_sapling");
        white_cherry_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.SNOW).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "white_cherry_leaves");
        pink_cherry_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_cherry_sapling");
        pink_cherry_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.PINK).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "pink_cherry_leaves");
        cherry_log = registerBlock(new BlockLog(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.RED_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_log");
        cherry_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        stripped_cherry_log = registerBlock(new BlockLog(MaterialColor.RED, Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_cherry_log");
        stripped_cherry_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        cherry_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        cherry_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        cherry_stairs = registerBlock(new BlockStairs(cherry_planks.getDefaultState(), Block.Properties.from(cherry_planks)), "cherry_stairs");
        cherry_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        cherry_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate");
        cherry_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, cherry_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_door");
        cherry_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.RED).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "cherry_trapdoor");
        cherry_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, cherry_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate");
        cherry_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "cherry_button");
        
        mahogany_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "mahogany_sapling");
        mahogany_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "mahogany_leaves");
        mahogany_log = registerBlock(new BlockLog(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.DIRT).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "mahogany_log");
        mahogany_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        stripped_mahogany_log = registerBlock(new BlockLog(MaterialColor.PINK_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_log");
        stripped_mahogany_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        mahogany_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        mahogany_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        mahogany_stairs = registerBlock(new BlockStairs(mahogany_planks.getDefaultState(), Block.Properties.from(mahogany_planks)), "mahogany_stairs");
        mahogany_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        mahogany_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence_gate");
        mahogany_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "mahogany_door");
        mahogany_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "mahogany_trapdoor");
        mahogany_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, mahogany_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "mahogany_pressure_plate");
        mahogany_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "mahogany_button");
        
        jacaranda_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "jacaranda_sapling");
        jacaranda_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.PURPLE).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "jacaranda_leaves");
        jacaranda_log = registerBlock(new BlockLog(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "jacaranda_log");
        jacaranda_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        stripped_jacaranda_log = registerBlock(new BlockLog(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_log");
        stripped_jacaranda_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        jacaranda_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        jacaranda_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        jacaranda_stairs = registerBlock(new BlockStairs(jacaranda_planks.getDefaultState(), Block.Properties.from(jacaranda_planks)), "jacaranda_stairs");
        jacaranda_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        jacaranda_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence_gate");
        jacaranda_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "jacaranda_door");
        jacaranda_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "jacaranda_trapdoor");
        jacaranda_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, jacaranda_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "jacaranda_pressure_plate");
        jacaranda_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "jacaranda_button");
        
        palm_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "palm_sapling");
        palm_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "palm_leaves");
        palm_log = registerBlock(new BlockLog(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "palm_log");
        palm_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "palm_wood");
        stripped_palm_log = registerBlock(new BlockLog(MaterialColor.YELLOW_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_palm_log");
        stripped_palm_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        palm_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        palm_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        palm_stairs = registerBlock(new BlockStairs(palm_planks.getDefaultState(), Block.Properties.from(palm_planks)), "palm_stairs");
        palm_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        palm_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence_gate");
        palm_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, palm_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "palm_door");
        palm_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "palm_trapdoor");
        palm_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, palm_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "palm_pressure_plate");
        palm_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "palm_button");
        
        willow_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "willow_sapling");
        willow_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "willow_leaves");
        willow_log = registerBlock(new BlockLog(MaterialColor.LIME_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "willow_log");
        willow_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "willow_wood");
        stripped_willow_log = registerBlock(new BlockLog(MaterialColor.LIME_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_willow_log");
        stripped_willow_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        willow_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        willow_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        willow_stairs = registerBlock(new BlockStairs(willow_planks.getDefaultState(), Block.Properties.from(willow_planks)), "willow_stairs");
        willow_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        willow_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence_gate");
        willow_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, willow_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "willow_door");
        willow_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "willow_trapdoor");
        willow_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, willow_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "willow_pressure_plate");
        willow_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "willow_button");
        
        dead_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dead_sapling");
        dead_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.WOOD).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "dead_leaves");
        dead_log = registerBlock(new BlockLog(MaterialColor.STONE, Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "dead_log");
        dead_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "dead_wood");
        stripped_dead_log = registerBlock(new BlockLog(MaterialColor.STONE, Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_dead_log");
        stripped_dead_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        dead_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        dead_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        dead_stairs = registerBlock(new BlockStairs(dead_planks.getDefaultState(), Block.Properties.from(dead_planks)), "dead_stairs");
        dead_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        dead_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence_gate");
        dead_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, dead_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "dead_door");
        dead_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.STONE).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "dead_trapdoor");
        dead_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, dead_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "dead_pressure_plate");
        dead_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "dead_button");
        
        magic_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "magic_sapling");
        magic_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.CYAN).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "magic_leaves");
        magic_log = registerBlock(new BlockLog(MaterialColor.BLUE, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "magic_log");
        magic_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "magic_wood");
        stripped_magic_log = registerBlock(new BlockLog(MaterialColor.BLUE, Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_magic_log");
        stripped_magic_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        magic_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        magic_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        magic_stairs = registerBlock(new BlockStairs(magic_planks.getDefaultState(), Block.Properties.from(magic_planks)), "magic_stairs");
        magic_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        magic_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence_gate");
        magic_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, magic_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "magic_door");
        magic_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.BLUE).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "magic_trapdoor");
        magic_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, magic_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "magic_pressure_plate");
        magic_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "magic_button");
        
        umbran_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "umbran_sapling");
        umbran_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "umbran_leaves");
        umbran_log = registerBlock(new BlockLog(MaterialColor.BLUE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "umbran_log");
        umbran_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        stripped_umbran_log = registerBlock(new BlockLog(MaterialColor.BLUE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_umbran_log");
        stripped_umbran_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        umbran_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        umbran_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        umbran_stairs = registerBlock(new BlockStairs(umbran_planks.getDefaultState(), Block.Properties.from(umbran_planks)), "umbran_stairs");
        umbran_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        umbran_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence_gate");
        umbran_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, umbran_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "umbran_door");
        umbran_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.BLUE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "umbran_trapdoor");
        umbran_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, umbran_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "umbran_pressure_plate");
        umbran_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "umbran_button");
        
        hellbark_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "hellbark_sapling");
        hellbark_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES, MaterialColor.PURPLE_TERRACOTTA).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "hellbark_leaves");
        hellbark_log = registerBlock(new BlockLog(MaterialColor.GRAY_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.LIGHT_GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "hellbark_log");
        hellbark_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        stripped_hellbark_log = registerBlock(new BlockLog(MaterialColor.GRAY_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_log");
        stripped_hellbark_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        hellbark_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        hellbark_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        hellbark_stairs = registerBlock(new BlockStairs(hellbark_planks.getDefaultState(), Block.Properties.from(hellbark_planks)), "hellbark_stairs");
        hellbark_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        hellbark_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence_gate");
        hellbark_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "hellbark_door");
        hellbark_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.GRAY_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "hellbark_trapdoor");
        hellbark_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, hellbark_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "hellbark_pressure_plate");
        hellbark_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "hellbark_button");
        
        ethereal_sapling = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "ethereal_sapling");
        ethereal_leaves = registerBlock(new BlockLeavesBOP(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "ethereal_leaves");
        ethereal_log = registerBlock(new BlockLog(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.LIME_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ethereal_log");
        ethereal_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "ethereal_wood");
        stripped_ethereal_log = registerBlock(new BlockLog(MaterialColor.CYAN, Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_ethereal_log");
        stripped_ethereal_wood = registerBlock(new BlockRotatedPillar(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_ethereal_wood");
        ethereal_planks = registerBlock(new Block(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_planks");
        ethereal_slab = registerBlock(new BlockSlab(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_slab");
        ethereal_stairs = registerBlock(new BlockStairs(ethereal_planks.getDefaultState(), Block.Properties.from(ethereal_planks)), "ethereal_stairs");
        ethereal_fence = registerBlock(new BlockFence(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_fence");
        ethereal_fence_gate = registerBlock(new BlockFenceGate(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ethereal_fence_gate");
        ethereal_door = registerBlock(new BlockDoor(Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ethereal_door");
        ethereal_trapdoor = registerBlock(new BlockTrapDoor(Block.Properties.create(Material.WOOD, MaterialColor.CYAN).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "ethereal_trapdoor");
        ethereal_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, ethereal_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ethereal_pressure_plate");
        ethereal_button = registerBlock(new BlockButtonWood(Block.Properties.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ethereal_button");
        
        rose = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "rose");
        blue_hydrangea = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "blue_hydrangea");
        violet = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "violet");
        lavender = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS, MaterialColor.MAGENTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "lavender");
        goldenrod = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS, MaterialColor.YELLOW_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "goldenrod");
        wildflower = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wildflower");
        orange_cosmos = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "orange_cosmos");
        pink_daffodil = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_daffodil");
        pink_hibiscus = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_hibiscus");
        glowflower = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(9)), "glowflower");
        deathbloom = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "deathbloom");
        wilted_lily = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wilted_lily");
        burning_blossom = registerBlock(new BlockFlowerBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(8)), "burning_blossom");
        
        ivy = registerBlock(new BlockVine(Block.Properties.create(Material.VINE).doesNotBlockMovement().needsRandomTick().hardnessAndResistance(0.2F).sound(SoundType.PLANT)), "ivy");
        willow_vine = registerBlock(new BlockVine(Block.Properties.create(Material.VINE).doesNotBlockMovement().needsRandomTick().hardnessAndResistance(0.2F).sound(SoundType.PLANT)), "willow_vine");
        
        short_grass = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "short_grass");
        bush = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "bush");
        barley = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE, MaterialColor.YELLOW_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "barley");
        dune_grass = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE, MaterialColor.LIME_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dune_grass");
        desert_grass = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE, MaterialColor.ORANGE_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "desert_grass");
        dead_grass = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE, MaterialColor.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dead_grass");
        devilweed = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "devilweed");
        spectral_fern = registerBlock(new BlockFoliageBOP(Block.Properties.create(Material.VINE, MaterialColor.RED).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "spectral_fern");
        
        thorn = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.PLANTS, MaterialColor.LIGHT_GRAY_TERRACOTTA).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "thorn");
        tiny_cactus = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "tiny_cactus");
        cattail = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "cattail");
        tall_cattail = registerBlock(new BlockDoublePlantBOP(cattail, Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "tall_cattail");
        root = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "root");
        reed = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.PLANTS, MaterialColor.DIRT).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "reed");
        watergrass = registerBlock(new BlockPlantBOP(Block.Properties.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "watergrass");
        
        bramble = registerBlock(new BlockBramble(Block.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).hardnessAndResistance(0.4F).sound(SoundType.WOOD)), "bramble");
        
        toadstool = registerBlock(new BlockMushroomBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "toadstool");
        glowshroom = registerBlock(new BlockMushroomBOP(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(6)), "glowshroom");
    }

    public static Block registerBlock(Block block, String name)
    {
        ItemBlock itemBlock = new ItemBlock(block, new Item.Properties().group(ItemGroupBOP.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
}
