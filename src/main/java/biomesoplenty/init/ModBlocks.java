/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.block.*;
import biomesoplenty.common.block.trees.*;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.block.BOPBlocks.*;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        //Terrain
    	white_sand = registerBlock(new SandBlockBOP(0xF3F1E4, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.QUARTZ).strength(0.5F).sound(SoundType.SAND).harvestLevel(0).harvestTool(ToolType.SHOVEL)), "white_sand");
    	white_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "white_sandstone");
        white_sandstone_stairs = registerBlock(new StairBlock(white_sandstone.defaultBlockState(), Block.Properties.copy(white_sandstone)), "white_sandstone_stairs");
    	white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(white_sandstone)), "white_sandstone_slab");
        white_sandstone_wall = registerBlock(new WallBlock(Block.Properties.copy(white_sandstone)),"white_sandstone_wall");
        smooth_white_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(2.0F, 6.0F)), "smooth_white_sandstone");
        smooth_white_sandstone_stairs = registerBlock(new StairBlock(white_sandstone.defaultBlockState(), Block.Properties.copy(smooth_white_sandstone)), "smooth_white_sandstone_stairs");
        smooth_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(smooth_white_sandstone)), "smooth_white_sandstone_slab");
    	cut_white_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "cut_white_sandstone");
        cut_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(cut_white_sandstone)), "cut_white_sandstone_slab");
        chiseled_white_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "chiseled_white_sandstone");

        orange_sand = registerBlock(new SandBlockBOP(0xCC9A61, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.SAND).harvestLevel(0).harvestTool(ToolType.SHOVEL)), "orange_sand");
        orange_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "orange_sandstone");
        orange_sandstone_stairs = registerBlock(new StairBlock(orange_sandstone.defaultBlockState(), Block.Properties.copy(orange_sandstone)), "orange_sandstone_stairs");
        orange_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(orange_sandstone)), "orange_sandstone_slab");
        orange_sandstone_wall = registerBlock(new WallBlock(Block.Properties.copy(orange_sandstone)),"orange_sandstone_wall");
        smooth_orange_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2.0F, 6.0F)), "smooth_orange_sandstone");
        smooth_orange_sandstone_stairs = registerBlock(new StairBlock(orange_sandstone.defaultBlockState(), Block.Properties.copy(smooth_orange_sandstone)), "smooth_orange_sandstone_stairs");
        smooth_orange_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(smooth_orange_sandstone)), "smooth_orange_sandstone_slab");
        cut_orange_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "cut_orange_sandstone");
        cut_orange_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(cut_orange_sandstone)), "cut_orange_sandstone_slab");
        chiseled_orange_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "chiseled_orange_sandstone");

        black_sand = registerBlock(new SandBlockBOP(0x2D2C2F, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_BLACK).strength(0.5F).sound(SoundType.SAND).harvestLevel(0).harvestTool(ToolType.SHOVEL)), "black_sand");
        black_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "black_sandstone");
        black_sandstone_stairs = registerBlock(new StairBlock(black_sandstone.defaultBlockState(), Block.Properties.copy(black_sandstone)), "black_sandstone_stairs");
        black_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(black_sandstone)), "black_sandstone_slab");
        black_sandstone_wall = registerBlock(new WallBlock(Block.Properties.copy(black_sandstone)),"black_sandstone_wall");
        smooth_black_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F, 6.0F)), "smooth_black_sandstone");
        smooth_black_sandstone_stairs = registerBlock(new StairBlock(black_sandstone.defaultBlockState(), Block.Properties.copy(smooth_black_sandstone)), "smooth_black_sandstone_stairs");
        smooth_black_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(smooth_black_sandstone)), "smooth_black_sandstone_slab");
        cut_black_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "cut_black_sandstone");
        cut_black_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(cut_black_sandstone)), "cut_black_sandstone_slab");
        chiseled_black_sandstone = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "chiseled_black_sandstone");

        mud = registerBlock(new MudBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_BROWN).strength(0.6F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(new SoundType(1.0F, 0.5F, SoundEvents.SLIME_BLOCK_BREAK, SoundEvents.SLIME_BLOCK_STEP, SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_HIT, SoundEvents.SLIME_BLOCK_FALL))), "mud");
        mud_bricks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).strength(1.0F)), "mud_bricks");
        mud_brick_stairs = registerBlock(new StairBlock(mud_bricks.defaultBlockState(), Block.Properties.copy(mud_bricks)), "mud_brick_stairs");
        mud_brick_slab = registerBlock(new SlabBlock(Block.Properties.copy(mud_bricks)), "mud_brick_slab");
        mud_brick_wall = registerBlock(new WallBlock(Block.Properties.copy(mud_bricks)),"mud_brick_wall");

        origin_grass_block = registerBlock(new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS).harvestTool(ToolType.SHOVEL)), "origin_grass_block");
        dried_salt = registerBlock(new DriedSaltBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).strength(1.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE, SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL))), "dried_salt");
        flesh = registerBlock(new FleshBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).strength(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh");

        nether_crystal_block = registerBlock(new Block(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.CRIMSON_STEM).strength(0.4F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(new SoundType(1.0F, 0.75F, SoundEvents.GLASS_BREAK, SoundEvents.GLASS_STEP, SoundEvents.GLASS_PLACE, SoundEvents.GLASS_HIT, SoundEvents.GLASS_FALL)).lightLevel((state) -> 10)), "nether_crystal_block");
        nether_crystal = registerBlock(new NetherCrystalBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.CRIMSON_STEM).noCollission().strength(0.3F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(new SoundType(1.0F, 0.75F, SoundEvents.GLASS_BREAK, SoundEvents.GLASS_STEP, SoundEvents.GLASS_PLACE, SoundEvents.GLASS_HIT, SoundEvents.GLASS_FALL)).lightLevel((state) -> 8)), "nether_crystal");

        toadstool_block = registerBlock(new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.WOOD)), "toadstool_block");
        glowshroom_block = registerBlock(new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIAMOND).strength(0.2F).sound(SoundType.WOOD).lightLevel((state) -> 10)), "glowshroom_block");

        //Trees
        origin_sapling = registerBlock(new SaplingBlockBOP(new OriginTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "origin_sapling");
        origin_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.EMERALD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "origin_leaves");
        flowering_oak_sapling = registerBlock(new SaplingBlockBOP(new FloweringOakTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "flowering_oak_sapling");
        flowering_oak_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "flowering_oak_leaves");
        rainbow_birch_sapling = registerBlock(new SaplingBlockBOP(new RainbowBirchTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "rainbow_birch_sapling");
        rainbow_birch_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "rainbow_birch_leaves");
        yellow_autumn_sapling = registerBlock(new SaplingBlockBOP(new YellowAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "yellow_autumn_sapling");
        yellow_autumn_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "yellow_autumn_leaves");
        orange_autumn_sapling = registerBlock(new SaplingBlockBOP(new OrangeAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "orange_autumn_sapling");
        orange_autumn_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_ORANGE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "orange_autumn_leaves");
        maple_sapling = registerBlock(new SaplingBlockBOP(new MapleTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "maple_sapling");
        maple_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_RED).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "maple_leaves");

        fir_sapling = registerBlock(new SaplingBlockBOP(new FirTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "fir_sapling");
        fir_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "fir_leaves");
        fir_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "fir_log");
        fir_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "fir_wood");
        stripped_fir_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_fir_log");
        stripped_fir_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        fir_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        fir_stairs = registerBlock(new StairBlock(fir_planks.defaultBlockState(), Block.Properties.copy(fir_planks)), "fir_stairs");
        fir_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        fir_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, fir_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        fir_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, fir_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        fir_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, fir_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_door");
        fir_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_trapdoor");
        fir_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, fir_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        fir_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_button");

        redwood_sapling = registerBlock(new SaplingBlockBOP(new RedwoodTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        redwood_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "redwood_leaves");
        redwood_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "redwood_log");
        redwood_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        stripped_redwood_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "stripped_redwood_log");
        stripped_redwood_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        redwood_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        redwood_stairs = registerBlock(new StairBlock(redwood_planks.defaultBlockState(), Block.Properties.copy(redwood_planks)), "redwood_stairs");
        redwood_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        redwood_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, redwood_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        redwood_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, redwood_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence_gate");
        redwood_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, redwood_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_door");
        redwood_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_trapdoor");
        redwood_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, redwood_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_pressure_plate");
        redwood_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_button");
        
        white_cherry_sapling = registerBlock(new SaplingBlockBOP(new WhiteCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "white_cherry_sapling");
        white_cherry_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.SNOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "white_cherry_leaves");
        pink_cherry_sapling = registerBlock(new SaplingBlockBOP(new PinkCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "pink_cherry_sapling");
        pink_cherry_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "pink_cherry_leaves");
        cherry_log = registerBlock(Blocks.log(MaterialColor.COLOR_RED, MaterialColor.TERRACOTTA_RED), "cherry_log");
        cherry_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        stripped_cherry_log = registerBlock(Blocks.log(MaterialColor.COLOR_RED, MaterialColor.COLOR_RED), "stripped_cherry_log");
        stripped_cherry_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        cherry_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        cherry_stairs = registerBlock(new StairBlock(cherry_planks.defaultBlockState(), Block.Properties.copy(cherry_planks)), "cherry_stairs");
        cherry_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        cherry_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, cherry_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        cherry_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, cherry_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate");
        cherry_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, cherry_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_door");
        cherry_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_trapdoor");
        cherry_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, cherry_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate");
        cherry_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_button");
        
        mahogany_sapling = registerBlock(new SaplingBlockBOP(new MahoganyTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "mahogany_sapling");
        mahogany_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "mahogany_leaves");
        mahogany_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_PINK, MaterialColor.DIRT), "mahogany_log");
        mahogany_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        stripped_mahogany_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_PINK, MaterialColor.TERRACOTTA_PINK), "stripped_mahogany_log");
        stripped_mahogany_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        mahogany_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        mahogany_stairs = registerBlock(new StairBlock(mahogany_planks.defaultBlockState(), Block.Properties.copy(mahogany_planks)), "mahogany_stairs");
        mahogany_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        mahogany_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, mahogany_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        mahogany_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, mahogany_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence_gate");
        mahogany_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, mahogany_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_door");
        mahogany_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_trapdoor");
        mahogany_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, mahogany_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_pressure_plate");
        mahogany_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_button");
        
        jacaranda_sapling = registerBlock(new SaplingBlockBOP(new JacarandaTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "jacaranda_sapling");
        jacaranda_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "jacaranda_leaves");
        jacaranda_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "jacaranda_log");
        jacaranda_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        stripped_jacaranda_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_jacaranda_log");
        stripped_jacaranda_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        jacaranda_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        jacaranda_stairs = registerBlock(new StairBlock(jacaranda_planks.defaultBlockState(), Block.Properties.copy(jacaranda_planks)), "jacaranda_stairs");
        jacaranda_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        jacaranda_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, jacaranda_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        jacaranda_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, jacaranda_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence_gate");
        jacaranda_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, jacaranda_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_door");
        jacaranda_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_trapdoor");
        jacaranda_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, jacaranda_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_pressure_plate");
        jacaranda_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_button");
        
        palm_sapling = registerBlock(new SaplingBlockBOP(new PalmTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "palm_sapling");
        palm_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "palm_leaves");
        palm_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.PODZOL), "palm_log");
        palm_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "palm_wood");
        stripped_palm_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.TERRACOTTA_YELLOW), "stripped_palm_log");
        stripped_palm_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        palm_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        palm_stairs = registerBlock(new StairBlock(palm_planks.defaultBlockState(), Block.Properties.copy(palm_planks)), "palm_stairs");
        palm_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        palm_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, palm_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        palm_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, palm_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence_gate");
        palm_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, palm_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_door");
        palm_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_trapdoor");
        palm_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, palm_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_pressure_plate");
        palm_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_button");
        
        willow_sapling = registerBlock(new SaplingBlockBOP(new WillowTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "willow_sapling");
        willow_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "willow_leaves");
        willow_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "willow_log");
        willow_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_wood");
        stripped_willow_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "stripped_willow_log");
        stripped_willow_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        willow_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        willow_stairs = registerBlock(new StairBlock(willow_planks.defaultBlockState(), Block.Properties.copy(willow_planks)), "willow_stairs");
        willow_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        willow_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, willow_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        willow_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, willow_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence_gate");
        willow_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, willow_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_door");
        willow_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_trapdoor");
        willow_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, willow_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_pressure_plate");
        willow_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_button");
        
        dead_sapling = registerBlock(new SaplingBlockBOP(new DeadTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "dead_sapling");
        dead_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.WOOD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "dead_leaves");
        dead_log = registerBlock(Blocks.log(MaterialColor.STONE, MaterialColor.COLOR_GRAY), "dead_log");
        dead_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "dead_wood");
        stripped_dead_log = registerBlock(Blocks.log(MaterialColor.STONE, MaterialColor.STONE), "stripped_dead_log");
        stripped_dead_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        dead_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        dead_stairs = registerBlock(new StairBlock(dead_planks.defaultBlockState(), Block.Properties.copy(dead_planks)), "dead_stairs");
        dead_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        dead_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, dead_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        dead_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, dead_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence_gate");
        dead_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, dead_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_door");
        dead_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_trapdoor");
        dead_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, dead_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_pressure_plate");
        dead_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_button");
        
        magic_sapling = registerBlock(new SaplingBlockBOP(new MagicTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "magic_sapling");
        magic_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "magic_leaves");
        magic_log = registerBlock(Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.TERRACOTTA_LIGHT_BLUE), "magic_log");
        magic_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "magic_wood");
        stripped_magic_log = registerBlock(Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), "stripped_magic_log");
        stripped_magic_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        magic_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        magic_stairs = registerBlock(new StairBlock(magic_planks.defaultBlockState(), Block.Properties.copy(magic_planks)), "magic_stairs");
        magic_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        magic_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, magic_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        magic_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, magic_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence_gate");
        magic_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, magic_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_door");
        magic_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_trapdoor");
        magic_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, magic_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_pressure_plate");
        magic_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_button");
        
        umbran_sapling = registerBlock(new SaplingBlockBOP(new UmbranTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "umbran_sapling");
        umbran_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_BLUE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "umbran_leaves");
        umbran_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "umbran_log");
        umbran_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        stripped_umbran_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "stripped_umbran_log");
        stripped_umbran_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        umbran_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        umbran_stairs = registerBlock(new StairBlock(umbran_planks.defaultBlockState(), Block.Properties.copy(umbran_planks)), "umbran_stairs");
        umbran_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        umbran_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, umbran_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        umbran_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, umbran_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence_gate");
        umbran_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, umbran_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_door");
        umbran_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_trapdoor");
        umbran_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, umbran_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_pressure_plate");
        umbran_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_button");
        
        hellbark_sapling = registerBlock(new SaplingBlockBOP(new HellbarkTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "hellbark_sapling");
        hellbark_leaves = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "hellbark_leaves");
        hellbark_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.COLOR_LIGHT_GRAY), "hellbark_log");
        hellbark_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        stripped_hellbark_log = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.TERRACOTTA_GRAY), "stripped_hellbark_log");
        stripped_hellbark_wood = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        hellbark_planks = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        hellbark_stairs = registerBlock(new StairBlock(hellbark_planks.defaultBlockState(), Block.Properties.copy(hellbark_planks)), "hellbark_stairs");
        hellbark_slab = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        hellbark_fence = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, hellbark_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        hellbark_fence_gate = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, hellbark_planks.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence_gate");
        hellbark_door = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, hellbark_planks.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_door");
        hellbark_trapdoor = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_trapdoor");
        hellbark_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, hellbark_planks.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_pressure_plate");
        hellbark_button = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_button");

        //Flowers
        rose = registerBlock(new FlowerBlockBOP(MobEffects.MOVEMENT_SPEED, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "rose");
        violet = registerBlock(new FlowerBlockBOP(MobEffects.CONFUSION, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "violet");
        lavender = registerBlock(new FlowerBlockBOP(MobEffects.HEALTH_BOOST, 5, BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_MAGENTA).noCollission().instabreak().sound(SoundType.GRASS)), "lavender");
        wildflower = registerBlock(new FlowerBlockBOP(MobEffects.HUNGER, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wildflower");
        orange_cosmos = registerBlock(new FlowerBlockBOP(MobEffects.ABSORPTION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "orange_cosmos");
        pink_daffodil = registerBlock(new FlowerBlockBOP(MobEffects.INVISIBILITY, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_daffodil");
        pink_hibiscus = registerBlock(new FlowerBlockBOP(MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_hibiscus");
        glowflower = registerBlock(new FlowerBlockBOP(MobEffects.GLOWING, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 9)), "glowflower");
        wilted_lily = registerBlock(new FlowerBlockBOP(MobEffects.UNLUCK, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wilted_lily");
        burning_blossom = registerBlock(new FlowerBlockBOP(MobEffects.FIRE_RESISTANCE, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 7)), "burning_blossom");

        //Tall Flowers
        blue_hydrangea = registerBlock(new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "blue_hydrangea");
        goldenrod = registerBlock(new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "goldenrod");

        //Vines
        willow_vine = registerBlock(new VineBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");
        spanish_moss = registerBlock(new SpanishMossBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss");
        spanish_moss_plant = registerBlockNoGroup(new SpanishMossBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss_plant");
        tree_roots = registerBlock(new TreeRootsBottomBlock(BlockBehaviour.Properties.of(Material.WOOD).randomTicks().noCollission().harvestLevel(0).harvestTool(ToolType.AXE).strength(0.3F).sound(SoundType.WOOD)), "tree_roots");
        tree_roots_stem = registerBlockNoGroup(new TreeRootsBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().harvestLevel(0).harvestTool(ToolType.AXE).strength(0.3F).sound(SoundType.WOOD)), "tree_roots_stem");
        glowworm_silk = registerBlock(new GlowwormSilkBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 8)), "glowworm_silk");
        glowworm_silk_strand = registerBlockNoGroup(new GlowwormSilkBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 10)), "glowworm_silk_strand");
        hanging_cobweb = registerBlock(new HangingCobwebBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb");
        hanging_cobweb_strand = registerBlockNoGroup(new HangingCobwebBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb_strand");

        //Plants
        sprout = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "sprout");
        bush = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "bush");
        clover = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "clover");
        huge_clover_petal = registerBlock(new HugeCloverPetalBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS)), "huge_clover_petal");
        dune_grass = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_LIGHT_GREEN).noCollission().instabreak().sound(SoundType.GRASS)), "dune_grass");
        desert_grass = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "desert_grass");
        dead_grass = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)), "dead_grass");

        //Tall Plants
        cattail = registerBlock(new DoubleWatersidePlantBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "cattail");
        barley = registerBlock(new DoublePlantBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_YELLOW).noCollission().instabreak().sound(SoundType.GRASS)), "barley");
        reed = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "reed");
        watergrass = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "watergrass");
        mangrove_root = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT, MaterialColor.TERRACOTTA_WHITE).noCollission().strength(1.0F, 1.5F).sound(SoundType.WOOD)), "mangrove_root");

        dead_branch = registerBlock(new DeadBranchBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).noCollission().instabreak().sound(SoundType.WOOD)), "dead_branch");
        bramble = registerBlock(new BrambleBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.NETHER).strength(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(SoundType.WOOD)), "bramble");
        toadstool = registerBlock(new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "toadstool");
        glowshroom = registerBlock(new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIAMOND).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 6)), "glowshroom");

        //Potted Plants
        potted_origin_sapling = registerBlockNoGroup(new FlowerPotBlock(origin_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_origin_sapling");
        potted_flowering_oak_sapling = registerBlockNoGroup(new FlowerPotBlock(flowering_oak_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_flowering_oak_sapling");
        potted_rainbow_birch_sapling = registerBlockNoGroup(new FlowerPotBlock(rainbow_birch_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rainbow_birch_sapling");
        potted_yellow_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(yellow_autumn_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_yellow_autumn_sapling");
        potted_orange_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(orange_autumn_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_autumn_sapling");
        potted_maple_sapling = registerBlockNoGroup(new FlowerPotBlock(maple_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_maple_sapling");
        potted_fir_sapling = registerBlockNoGroup(new FlowerPotBlock(fir_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_fir_sapling");
        potted_redwood_sapling = registerBlockNoGroup(new FlowerPotBlock(redwood_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_redwood_sapling");
        potted_white_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(white_cherry_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_white_cherry_sapling");
        potted_pink_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(pink_cherry_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_cherry_sapling");
        potted_mahogany_sapling = registerBlockNoGroup(new FlowerPotBlock(mahogany_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_mahogany_sapling");
        potted_jacaranda_sapling = registerBlockNoGroup(new FlowerPotBlock(jacaranda_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_jacaranda_sapling");
        potted_palm_sapling = registerBlockNoGroup(new FlowerPotBlock(palm_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_palm_sapling");
        potted_willow_sapling = registerBlockNoGroup(new FlowerPotBlock(willow_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_willow_sapling");
        potted_dead_sapling = registerBlockNoGroup(new FlowerPotBlock(dead_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_dead_sapling");
        potted_magic_sapling = registerBlockNoGroup(new FlowerPotBlock(magic_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_magic_sapling");
        potted_umbran_sapling = registerBlockNoGroup(new FlowerPotBlock(umbran_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_umbran_sapling");
        potted_hellbark_sapling = registerBlockNoGroup(new FlowerPotBlock(hellbark_sapling, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_hellbark_sapling");
        potted_rose = registerBlockNoGroup(new FlowerPotBlock(rose, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rose");
        potted_violet = registerBlockNoGroup(new FlowerPotBlock(violet, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_violet");
        potted_lavender = registerBlockNoGroup(new FlowerPotBlock(lavender, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_lavender");
        potted_wildflower = registerBlockNoGroup(new FlowerPotBlock(wildflower, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wildflower");
        potted_orange_cosmos = registerBlockNoGroup(new FlowerPotBlock(orange_cosmos, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_cosmos");
        potted_pink_daffodil = registerBlockNoGroup(new FlowerPotBlock(pink_daffodil, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_daffodil");
        potted_pink_hibiscus = registerBlockNoGroup(new FlowerPotBlock(pink_hibiscus, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_hibiscus");
        potted_glowflower = registerBlockNoGroup(new FlowerPotBlock(glowflower, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 9)), "potted_glowflower");
        potted_wilted_lily = registerBlockNoGroup(new FlowerPotBlock(wilted_lily, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wilted_lily");
        potted_burning_blossom = registerBlockNoGroup(new FlowerPotBlock(burning_blossom, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 7)), "potted_burning_blossom");
        potted_sprout = registerBlockNoGroup(new FlowerPotBlock(sprout, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_sprout");
        potted_clover = registerBlockNoGroup(new FlowerPotBlock(clover, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_clover");
        potted_toadstool = registerBlockNoGroup(new FlowerPotBlock(toadstool, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_toadstool");
        potted_glowshroom = registerBlockNoGroup(new FlowerPotBlock(glowshroom, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 6)), "potted_glowshroom");

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.cutoutMipped();
            RenderType cutoutRenderType = RenderType.cutout();
            RenderType translucentRenderType = RenderType.translucent();

            ItemBlockRenderTypes.setRenderLayer(origin_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(flowering_oak_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(rainbow_birch_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(yellow_autumn_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(orange_autumn_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(maple_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(fir_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(redwood_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(white_cherry_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(pink_cherry_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(mahogany_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(jacaranda_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(palm_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(willow_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(magic_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(umbran_leaves, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(hellbark_leaves, transparentRenderType);

            ItemBlockRenderTypes.setRenderLayer(nether_crystal, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(origin_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(flowering_oak_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(rainbow_birch_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(yellow_autumn_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(orange_autumn_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(maple_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(fir_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(redwood_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(white_cherry_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(pink_cherry_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(mahogany_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(jacaranda_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(palm_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(willow_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(magic_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(umbran_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(hellbark_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(rose, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(violet, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(lavender, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(wildflower, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(orange_cosmos, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(pink_daffodil, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(pink_hibiscus, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(glowflower, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(wilted_lily, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(burning_blossom, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(blue_hydrangea, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(goldenrod, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(willow_vine, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(spanish_moss, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(spanish_moss_plant, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(tree_roots, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(tree_roots_stem, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(glowworm_silk, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(glowworm_silk_strand, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(hanging_cobweb, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(hanging_cobweb_strand, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(sprout, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(bush, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(clover, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(huge_clover_petal, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dune_grass, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(desert_grass, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_grass, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(cattail, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(barley, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(reed, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(watergrass, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(mangrove_root, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_branch, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(bramble, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(toadstool, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(glowshroom, cutoutRenderType);

            ItemBlockRenderTypes.setRenderLayer(potted_origin_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_flowering_oak_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_rainbow_birch_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_yellow_autumn_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_orange_autumn_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_maple_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_fir_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_redwood_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_white_cherry_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_pink_cherry_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_mahogany_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_jacaranda_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_palm_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_willow_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_dead_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_magic_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_umbran_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_hellbark_sapling, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_rose, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_violet, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_lavender, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_wildflower, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_orange_cosmos, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_pink_daffodil, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_pink_hibiscus, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_glowflower, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_wilted_lily, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_burning_blossom, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_sprout, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_clover, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_toadstool, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(potted_glowshroom, cutoutRenderType);

            ItemBlockRenderTypes.setRenderLayer(fir_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(redwood_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(cherry_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(mahogany_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(jacaranda_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(palm_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(willow_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(magic_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(umbran_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(hellbark_door, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(fir_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(redwood_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(cherry_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(mahogany_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(jacaranda_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(palm_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(willow_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(dead_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(magic_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(umbran_trapdoor, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(hellbark_trapdoor, cutoutRenderType);
        }
    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(ItemGroupBOP.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }

    public static Block registerBlockNoGroup(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(null));
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
