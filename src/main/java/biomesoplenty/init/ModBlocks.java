/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.block.*;
import biomesoplenty.common.block.trees.*;
import biomesoplenty.common.util.CreativeModeTabBOP;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModBlocks
{
    public static void registerBlocks()
    {
        //Terrain
        WHITE_SAND = registerBlock(new SandBlockBOP(0xF3F1E4, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.QUARTZ).strength(0.5F).sound(SoundType.SAND)), "white_sand");
        WHITE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "white_sandstone");
        WHITE_SANDSTONE_STAIRS = registerBlock(new StairBlock(WHITE_SANDSTONE.defaultBlockState(), Block.Properties.copy(WHITE_SANDSTONE)), "white_sandstone_stairs");
        WHITE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(WHITE_SANDSTONE)), "white_sandstone_slab");
        WHITE_SANDSTONE_WALL = registerBlock(new WallBlock(Block.Properties.copy(WHITE_SANDSTONE)),"white_sandstone_wall");
        SMOOTH_WHITE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(2.0F, 6.0F)), "smooth_white_sandstone");
        SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock(new StairBlock(WHITE_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_WHITE_SANDSTONE)), "smooth_white_sandstone_stairs");
        SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(SMOOTH_WHITE_SANDSTONE)), "smooth_white_sandstone_slab");
        CUT_WHITE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "cut_white_sandstone");
        CUT_WHITE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(CUT_WHITE_SANDSTONE)), "cut_white_sandstone_slab");
        CHISELED_WHITE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "chiseled_white_sandstone");

        ORANGE_SAND = registerBlock(new SandBlockBOP(0xCC9A61, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.SAND)), "orange_sand");
        ORANGE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "orange_sandstone");
        ORANGE_SANDSTONE_STAIRS = registerBlock(new StairBlock(ORANGE_SANDSTONE.defaultBlockState(), Block.Properties.copy(ORANGE_SANDSTONE)), "orange_sandstone_stairs");
        ORANGE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(ORANGE_SANDSTONE)), "orange_sandstone_slab");
        ORANGE_SANDSTONE_WALL = registerBlock(new WallBlock(Block.Properties.copy(ORANGE_SANDSTONE)),"orange_sandstone_wall");
        SMOOTH_ORANGE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2.0F, 6.0F)), "smooth_orange_sandstone");
        SMOOTH_ORANGE_SANDSTONE_STAIRS = registerBlock(new StairBlock(ORANGE_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE)), "smooth_orange_sandstone_stairs");
        SMOOTH_ORANGE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE)), "smooth_orange_sandstone_slab");
        CUT_ORANGE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "cut_orange_sandstone");
        CUT_ORANGE_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(CUT_ORANGE_SANDSTONE)), "cut_orange_sandstone_slab");
        CHISELED_ORANGE_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "chiseled_orange_sandstone");

        BLACK_SAND = registerBlock(new SandBlockBOP(0x2D2C2F, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_BLACK).strength(0.5F).sound(SoundType.SAND)), "black_sand");
        BLACK_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "black_sandstone");
        BLACK_SANDSTONE_STAIRS = registerBlock(new StairBlock(BLACK_SANDSTONE.defaultBlockState(), Block.Properties.copy(BLACK_SANDSTONE)), "black_sandstone_stairs");
        BLACK_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(BLACK_SANDSTONE)), "black_sandstone_slab");
        BLACK_SANDSTONE_WALL = registerBlock(new WallBlock(Block.Properties.copy(BLACK_SANDSTONE)),"black_sandstone_wall");
        SMOOTH_BLACK_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F, 6.0F)), "smooth_black_sandstone");
        SMOOTH_BLACK_SANDSTONE_STAIRS = registerBlock(new StairBlock(BLACK_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_BLACK_SANDSTONE)), "smooth_black_sandstone_stairs");
        SMOOTH_BLACK_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(SMOOTH_BLACK_SANDSTONE)), "smooth_black_sandstone_slab");
        CUT_BLACK_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "cut_black_sandstone");
        CUT_BLACK_SANDSTONE_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(CUT_BLACK_SANDSTONE)), "cut_black_sandstone_slab");
        CHISELED_BLACK_SANDSTONE = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "chiseled_black_sandstone");

        MUD = registerBlock(new MudBlock(BlockBehaviour.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_BROWN).strength(0.6F).sound(new SoundType(1.0F, 0.5F, SoundEvents.SLIME_BLOCK_BREAK, SoundEvents.SLIME_BLOCK_STEP, SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_HIT, SoundEvents.SLIME_BLOCK_FALL))), "mud");
        MUD_BRICKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).strength(1.0F)), "mud_bricks");
        MUD_BRICK_STAIRS = registerBlock(new StairBlock(MUD_BRICKS.defaultBlockState(), Block.Properties.copy(MUD_BRICKS)), "mud_brick_stairs");
        MUD_BRICK_SLAB = registerBlock(new SlabBlock(Block.Properties.copy(MUD_BRICKS)), "mud_brick_slab");
        MUD_BRICK_WALL = registerBlock(new WallBlock(Block.Properties.copy(MUD_BRICKS)),"mud_brick_wall");

        ORIGIN_GRASS_BLOCK = registerBlock(new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)), "origin_grass_block");
        DRIED_SALT = registerBlock(new DriedSaltBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).strength(1.0F).sound(new SoundType(1.0F, 0.5F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE, SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL))), "dried_salt");
        FLESH = registerBlock(new FleshBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).strength(0.4F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh");

        NETHER_CRYSTAL_BLOCK = registerBlock(new Block(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.CRIMSON_STEM).strength(0.4F).sound(new SoundType(1.0F, 0.75F, SoundEvents.GLASS_BREAK, SoundEvents.GLASS_STEP, SoundEvents.GLASS_PLACE, SoundEvents.GLASS_HIT, SoundEvents.GLASS_FALL)).lightLevel((state) -> 10)), "nether_crystal_block");
        NETHER_CRYSTAL = registerBlock(new NetherCrystalBlock(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.CRIMSON_STEM).noCollission().strength(0.3F).sound(new SoundType(1.0F, 0.75F, SoundEvents.GLASS_BREAK, SoundEvents.GLASS_STEP, SoundEvents.GLASS_PLACE, SoundEvents.GLASS_HIT, SoundEvents.GLASS_FALL)).lightLevel((state) -> 8)), "nether_crystal");

        TOADSTOOL_BLOCK = registerBlock(new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.WOOD)), "toadstool_block");
        GLOWSHROOM_BLOCK = registerBlock(new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIAMOND).strength(0.2F).sound(SoundType.WOOD).lightLevel((state) -> 10)), "glowshroom_block");

        //Trees
        ORIGIN_SAPLING = registerBlock(new SaplingBlockBOP(new OriginTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "origin_sapling");
        ORIGIN_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.EMERALD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "origin_leaves");
        FLOWERING_OAK_SAPLING = registerBlock(new SaplingBlockBOP(new FloweringOakTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "flowering_oak_sapling");
        FLOWERING_OAK_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "flowering_oak_leaves");
        RAINBOW_BIRCH_SAPLING = registerBlock(new SaplingBlockBOP(new RainbowBirchTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "rainbow_birch_sapling");
        RAINBOW_BIRCH_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "rainbow_birch_leaves");
        YELLOW_AUTUMN_SAPLING = registerBlock(new SaplingBlockBOP(new YellowAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "yellow_autumn_sapling");
        YELLOW_AUTUMN_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "yellow_autumn_leaves");
        ORANGE_AUTUMN_SAPLING = registerBlock(new SaplingBlockBOP(new OrangeAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "orange_autumn_sapling");
        ORANGE_AUTUMN_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_ORANGE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "orange_autumn_leaves");
        MAPLE_SAPLING = registerBlock(new SaplingBlockBOP(new MapleTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "maple_sapling");
        MAPLE_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_RED).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "maple_leaves");

        FIR_SAPLING = registerBlock(new SaplingBlockBOP(new FirTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "fir_sapling");
        FIR_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "fir_leaves");
        FIR_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "fir_log");
        FIR_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "fir_wood");
        STRIPPED_FIR_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_fir_log");
        STRIPPED_FIR_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        FIR_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        FIR_STAIRS = registerBlock(new StairBlock(FIR_PLANKS.defaultBlockState(), Block.Properties.copy(FIR_PLANKS)), "fir_stairs");
        FIR_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        FIR_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        FIR_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        FIR_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_door");
        FIR_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_trapdoor");
        FIR_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        FIR_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_button");

        REDWOOD_SAPLING = registerBlock(new SaplingBlockBOP(new RedwoodTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        REDWOOD_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "redwood_leaves");
        REDWOOD_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "redwood_log");
        REDWOOD_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        STRIPPED_REDWOOD_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "stripped_redwood_log");
        STRIPPED_REDWOOD_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        REDWOOD_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        REDWOOD_STAIRS = registerBlock(new StairBlock(REDWOOD_PLANKS.defaultBlockState(), Block.Properties.copy(REDWOOD_PLANKS)), "redwood_stairs");
        REDWOOD_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        REDWOOD_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        REDWOOD_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence_gate");
        REDWOOD_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_door");
        REDWOOD_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_trapdoor");
        REDWOOD_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_pressure_plate");
        REDWOOD_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_button");

        WHITE_CHERRY_SAPLING = registerBlock(new SaplingBlockBOP(new WhiteCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "white_cherry_sapling");
        WHITE_CHERRY_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.SNOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "white_cherry_leaves");
        PINK_CHERRY_SAPLING = registerBlock(new SaplingBlockBOP(new PinkCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "pink_cherry_sapling");
        PINK_CHERRY_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "pink_cherry_leaves");
        CHERRY_LOG = registerBlock(log(MaterialColor.COLOR_RED, MaterialColor.TERRACOTTA_RED), "cherry_log");
        CHERRY_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        STRIPPED_CHERRY_LOG = registerBlock(log(MaterialColor.COLOR_RED, MaterialColor.COLOR_RED), "stripped_cherry_log");
        STRIPPED_CHERRY_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        CHERRY_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        CHERRY_STAIRS = registerBlock(new StairBlock(CHERRY_PLANKS.defaultBlockState(), Block.Properties.copy(CHERRY_PLANKS)), "cherry_stairs");
        CHERRY_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        CHERRY_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        CHERRY_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate");
        CHERRY_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_door");
        CHERRY_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_trapdoor");
        CHERRY_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate");
        CHERRY_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_button");

        MAHOGANY_SAPLING = registerBlock(new SaplingBlockBOP(new MahoganyTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "mahogany_sapling");
        MAHOGANY_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "mahogany_leaves");
        MAHOGANY_LOG = registerBlock(log(MaterialColor.TERRACOTTA_PINK, MaterialColor.DIRT), "mahogany_log");
        MAHOGANY_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        STRIPPED_MAHOGANY_LOG = registerBlock(log(MaterialColor.TERRACOTTA_PINK, MaterialColor.TERRACOTTA_PINK), "stripped_mahogany_log");
        STRIPPED_MAHOGANY_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        MAHOGANY_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        MAHOGANY_STAIRS = registerBlock(new StairBlock(MAHOGANY_PLANKS.defaultBlockState(), Block.Properties.copy(MAHOGANY_PLANKS)), "mahogany_stairs");
        MAHOGANY_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        MAHOGANY_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        MAHOGANY_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence_gate");
        MAHOGANY_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_door");
        MAHOGANY_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_trapdoor");
        MAHOGANY_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_pressure_plate");
        MAHOGANY_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_button");

        JACARANDA_SAPLING = registerBlock(new SaplingBlockBOP(new JacarandaTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "jacaranda_sapling");
        JACARANDA_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "jacaranda_leaves");
        JACARANDA_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "jacaranda_log");
        JACARANDA_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        STRIPPED_JACARANDA_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_jacaranda_log");
        STRIPPED_JACARANDA_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        JACARANDA_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        JACARANDA_STAIRS = registerBlock(new StairBlock(JACARANDA_PLANKS.defaultBlockState(), Block.Properties.copy(JACARANDA_PLANKS)), "jacaranda_stairs");
        JACARANDA_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        JACARANDA_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        JACARANDA_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence_gate");
        JACARANDA_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_door");
        JACARANDA_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_trapdoor");
        JACARANDA_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_pressure_plate");
        JACARANDA_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_button");

        PALM_SAPLING = registerBlock(new SaplingBlockBOP(new PalmTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "palm_sapling");
        PALM_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "palm_leaves");
        PALM_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.PODZOL), "palm_log");
        PALM_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "palm_wood");
        STRIPPED_PALM_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.TERRACOTTA_YELLOW), "stripped_palm_log");
        STRIPPED_PALM_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        PALM_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        PALM_STAIRS = registerBlock(new StairBlock(PALM_PLANKS.defaultBlockState(), Block.Properties.copy(PALM_PLANKS)), "palm_stairs");
        PALM_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        PALM_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        PALM_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence_gate");
        PALM_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_door");
        PALM_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_trapdoor");
        PALM_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_pressure_plate");
        PALM_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_button");

        WILLOW_SAPLING = registerBlock(new SaplingBlockBOP(new WillowTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "willow_sapling");
        WILLOW_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "willow_leaves");
        WILLOW_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "willow_log");
        WILLOW_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_wood");
        STRIPPED_WILLOW_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "stripped_willow_log");
        STRIPPED_WILLOW_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        WILLOW_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        WILLOW_STAIRS = registerBlock(new StairBlock(WILLOW_PLANKS.defaultBlockState(), Block.Properties.copy(WILLOW_PLANKS)), "willow_stairs");
        WILLOW_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        WILLOW_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        WILLOW_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence_gate");
        WILLOW_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_door");
        WILLOW_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_trapdoor");
        WILLOW_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_pressure_plate");
        WILLOW_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_button");

        DEAD_SAPLING = registerBlock(new SaplingBlockBOP(new DeadTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "dead_sapling");
        DEAD_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.WOOD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "dead_leaves");
        DEAD_LOG = registerBlock(Blocks.log(MaterialColor.STONE, MaterialColor.COLOR_GRAY), "dead_log");
        DEAD_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "dead_wood");
        STRIPPED_DEAD_LOG = registerBlock(Blocks.log(MaterialColor.STONE, MaterialColor.STONE), "stripped_dead_log");
        STRIPPED_DEAD_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        DEAD_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        DEAD_STAIRS = registerBlock(new StairBlock(DEAD_PLANKS.defaultBlockState(), Block.Properties.copy(DEAD_PLANKS)), "dead_stairs");
        DEAD_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        DEAD_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        DEAD_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence_gate");
        DEAD_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_door");
        DEAD_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_trapdoor");
        DEAD_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_pressure_plate");
        DEAD_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_button");

        MAGIC_SAPLING = registerBlock(new SaplingBlockBOP(new MagicTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "magic_sapling");
        MAGIC_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "magic_leaves");
        MAGIC_LOG = registerBlock(Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.TERRACOTTA_LIGHT_BLUE), "magic_log");
        MAGIC_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "magic_wood");
        STRIPPED_MAGIC_LOG = registerBlock(Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), "stripped_magic_log");
        STRIPPED_MAGIC_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        MAGIC_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        MAGIC_STAIRS = registerBlock(new StairBlock(MAGIC_PLANKS.defaultBlockState(), Block.Properties.copy(MAGIC_PLANKS)), "magic_stairs");
        MAGIC_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        MAGIC_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        MAGIC_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence_gate");
        MAGIC_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_door");
        MAGIC_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_trapdoor");
        MAGIC_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_pressure_plate");
        MAGIC_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_button");

        UMBRAN_SAPLING = registerBlock(new SaplingBlockBOP(new UmbranTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "umbran_sapling");
        UMBRAN_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_BLUE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "umbran_leaves");
        UMBRAN_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "umbran_log");
        UMBRAN_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        STRIPPED_UMBRAN_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "stripped_umbran_log");
        STRIPPED_UMBRAN_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        UMBRAN_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        UMBRAN_STAIRS = registerBlock(new StairBlock(UMBRAN_PLANKS.defaultBlockState(), Block.Properties.copy(UMBRAN_PLANKS)), "umbran_stairs");
        UMBRAN_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        UMBRAN_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        UMBRAN_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence_gate");
        UMBRAN_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_door");
        UMBRAN_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_trapdoor");
        UMBRAN_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_pressure_plate");
        UMBRAN_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_button");

        HELLBARK_SAPLING = registerBlock(new SaplingBlockBOP(new HellbarkTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "hellbark_sapling");
        HELLBARK_LEAVES = registerBlock(new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "hellbark_leaves");
        HELLBARK_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.COLOR_LIGHT_GRAY), "hellbark_log");
        HELLBARK_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        STRIPPED_HELLBARK_LOG = registerBlock(Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.TERRACOTTA_GRAY), "stripped_hellbark_log");
        STRIPPED_HELLBARK_WOOD = registerBlock(new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        HELLBARK_PLANKS = registerBlock(new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        HELLBARK_STAIRS = registerBlock(new StairBlock(HELLBARK_PLANKS.defaultBlockState(), Block.Properties.copy(HELLBARK_PLANKS)), "hellbark_stairs");
        HELLBARK_SLAB = registerBlock(new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        HELLBARK_FENCE = registerBlock(new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        HELLBARK_FENCE_GATE = registerBlock(new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence_gate");
        HELLBARK_DOOR = registerBlock(new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_door");
        HELLBARK_TRAPDOOR = registerBlock(new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_trapdoor");
        HELLBARK_PRESSURE_PLATE = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_pressure_plate");
        HELLBARK_BUTTON = registerBlock(new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_button");

        //Flowers
        ROSE = registerBlock(new FlowerBlockBOP(MobEffects.MOVEMENT_SPEED, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "rose");
        VIOLET = registerBlock(new FlowerBlockBOP(MobEffects.CONFUSION, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "violet");
        LAVENDER = registerBlock(new FlowerBlockBOP(MobEffects.HEALTH_BOOST, 5, BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_MAGENTA).noCollission().instabreak().sound(SoundType.GRASS)), "lavender");
        WILDFLOWER = registerBlock(new FlowerBlockBOP(MobEffects.HUNGER, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wildflower");
        ORANGE_COSMOS = registerBlock(new FlowerBlockBOP(MobEffects.ABSORPTION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "orange_cosmos");
        PINK_DAFFODIL = registerBlock(new FlowerBlockBOP(MobEffects.INVISIBILITY, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_daffodil");
        PINK_HIBISCUS = registerBlock(new FlowerBlockBOP(MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_hibiscus");
        GLOWFLOWER = registerBlock(new FlowerBlockBOP(MobEffects.GLOWING, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 9)), "glowflower");
        WILTED_LILY = registerBlock(new FlowerBlockBOP(MobEffects.UNLUCK, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wilted_lily");
        BURNING_BLOSSOM = registerBlock(new FlowerBlockBOP(MobEffects.FIRE_RESISTANCE, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 7)), "burning_blossom");

        //Tall Flowers
        BLUE_HYDRANGEA = registerBlock(new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "blue_hydrangea");
        GOLDENROD = registerBlock(new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "goldenrod");

        //Vines
        WILLOW_VINE = registerBlock(new VineBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");
        SPANISH_MOSS = registerBlock(new SpanishMossBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss");
        SPANISH_MOSS_PLANT = registerBlockNoGroup(new SpanishMossBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss_plant");
        TREE_ROOTS = registerBlock(new TreeRootsBottomBlock(BlockBehaviour.Properties.of(Material.WOOD).randomTicks().noCollission().strength(0.3F).sound(SoundType.WOOD)), "tree_roots");
        TREE_ROOTS_STEM = registerBlockNoGroup(new TreeRootsBlock(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(0.3F).sound(SoundType.WOOD)), "tree_roots_stem");
        GLOWWORM_SILK = registerBlock(new GlowwormSilkBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 8)), "glowworm_silk");
        GLOWWORM_SILK_STRAND = registerBlockNoGroup(new GlowwormSilkBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 10)), "glowworm_silk_strand");
        HANGING_COBWEB = registerBlock(new HangingCobwebBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb");
        HANGING_COBWEB_STRAND = registerBlockNoGroup(new HangingCobwebBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb_strand");

        //Plants
        SPROUT = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "sprout");
        BUSH = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "bush");
        CLOVER = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "clover");
        HUGE_CLOVER_PETAL = registerBlock(new HugeCloverPetalBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS)), "huge_clover_petal");
        DUNE_GRASS = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_LIGHT_GREEN).noCollission().instabreak().sound(SoundType.GRASS)), "dune_grass");
        DESERT_GRASS = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "desert_grass");
        DEAD_GRASS = registerBlock(new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)), "dead_grass");

        //Tall Plants
        CATTAIL = registerBlock(new DoubleWatersidePlantBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "cattail");
        BARLEY = registerBlock(new DoublePlantBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_YELLOW).noCollission().instabreak().sound(SoundType.GRASS)), "barley");
        REED = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "reed");
        WATERGRASS = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "watergrass");
        MANGROVE_ROOT = registerBlock(new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT, MaterialColor.TERRACOTTA_WHITE).noCollission().strength(1.0F, 1.5F).sound(SoundType.WOOD)), "mangrove_root");

        DEAD_BRANCH = registerBlock(new DeadBranchBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).noCollission().instabreak().sound(SoundType.WOOD)), "dead_branch");
        BRAMBLE = registerBlock(new BrambleBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.NETHER).strength(0.4F).sound(SoundType.WOOD)), "bramble");
        TOADSTOOL = registerBlock(new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "toadstool");
        GLOWSHROOM = registerBlock(new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIAMOND).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 6)), "glowshroom");

        //Potted Plants
        POTTED_ORIGIN_SAPLING = registerBlockNoGroup(new FlowerPotBlock(ORIGIN_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_origin_sapling");
        POTTED_FLOWERING_OAK_SAPLING = registerBlockNoGroup(new FlowerPotBlock(FLOWERING_OAK_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_flowering_oak_sapling");
        POTTED_RAINBOW_BIRCH_SAPLING = registerBlockNoGroup(new FlowerPotBlock(RAINBOW_BIRCH_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rainbow_birch_sapling");
        POTTED_YELLOW_AUTUMN_SAPLING = registerBlockNoGroup(new FlowerPotBlock(YELLOW_AUTUMN_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_yellow_autumn_sapling");
        POTTED_ORANGE_AUTUMN_SAPLING = registerBlockNoGroup(new FlowerPotBlock(ORANGE_AUTUMN_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_autumn_sapling");
        POTTED_MAPLE_SAPLING = registerBlockNoGroup(new FlowerPotBlock(MAPLE_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_maple_sapling");
        POTTED_FIR_SAPLING = registerBlockNoGroup(new FlowerPotBlock(FIR_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_fir_sapling");
        POTTED_REDWOOD_SAPLING = registerBlockNoGroup(new FlowerPotBlock(REDWOOD_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_redwood_sapling");
        POTTED_WHITE_CHERRY_SAPLING = registerBlockNoGroup(new FlowerPotBlock(WHITE_CHERRY_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_white_cherry_sapling");
        POTTED_PINK_CHERRY_SAPLING = registerBlockNoGroup(new FlowerPotBlock(PINK_CHERRY_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_cherry_sapling");
        POTTED_MAHOGANY_SAPLING = registerBlockNoGroup(new FlowerPotBlock(MAHOGANY_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_mahogany_sapling");
        POTTED_JACARANDA_SAPLING = registerBlockNoGroup(new FlowerPotBlock(JACARANDA_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_jacaranda_sapling");
        POTTED_PALM_SAPLING = registerBlockNoGroup(new FlowerPotBlock(PALM_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_palm_sapling");
        POTTED_WILLOW_SAPLING = registerBlockNoGroup(new FlowerPotBlock(WILLOW_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_willow_sapling");
        POTTED_DEAD_SAPLING = registerBlockNoGroup(new FlowerPotBlock(DEAD_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_dead_sapling");
        POTTED_MAGIC_SAPLING = registerBlockNoGroup(new FlowerPotBlock(MAGIC_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_magic_sapling");
        POTTED_UMBRAN_SAPLING = registerBlockNoGroup(new FlowerPotBlock(UMBRAN_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_umbran_sapling");
        POTTED_HELLBARK_SAPLING = registerBlockNoGroup(new FlowerPotBlock(HELLBARK_SAPLING, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_hellbark_sapling");
        POTTED_ROSE = registerBlockNoGroup(new FlowerPotBlock(ROSE, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rose");
        POTTED_VIOLET = registerBlockNoGroup(new FlowerPotBlock(VIOLET, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_violet");
        POTTED_LAVENDER = registerBlockNoGroup(new FlowerPotBlock(LAVENDER, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_lavender");
        POTTED_WILDFLOWER = registerBlockNoGroup(new FlowerPotBlock(WILDFLOWER, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wildflower");
        POTTED_ORANGE_COSMOS = registerBlockNoGroup(new FlowerPotBlock(ORANGE_COSMOS, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_cosmos");
        POTTED_PINK_DAFFODIL = registerBlockNoGroup(new FlowerPotBlock(PINK_DAFFODIL, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_daffodil");
        POTTED_PINK_HIBISCUS = registerBlockNoGroup(new FlowerPotBlock(PINK_HIBISCUS, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_hibiscus");
        POTTED_GLOWFLOWER = registerBlockNoGroup(new FlowerPotBlock(GLOWFLOWER, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 9)), "potted_glowflower");
        POTTED_WILTED_LILY = registerBlockNoGroup(new FlowerPotBlock(WILTED_LILY, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wilted_lily");
        POTTED_BURNING_BLOSSOM = registerBlockNoGroup(new FlowerPotBlock(BURNING_BLOSSOM, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 7)), "potted_burning_blossom");
        POTTED_SPROUT = registerBlockNoGroup(new FlowerPotBlock(SPROUT, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_sprout");
        POTTED_CLOVER = registerBlockNoGroup(new FlowerPotBlock(CLOVER, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_clover");
        POTTED_TOADSTOOL = registerBlockNoGroup(new FlowerPotBlock(TOADSTOOL, BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_toadstool");
        POTTED_GLOWSHROOM = registerBlockNoGroup(new FlowerPotBlock(GLOWSHROOM, BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 6)), "potted_glowshroom");

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.cutoutMipped();
            RenderType cutoutRenderType = RenderType.cutout();
            RenderType translucentRenderType = RenderType.translucent();

            ItemBlockRenderTypes.setRenderLayer(ORIGIN_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(FLOWERING_OAK_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(RAINBOW_BIRCH_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(YELLOW_AUTUMN_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(ORANGE_AUTUMN_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAPLE_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(FIR_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(REDWOOD_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(WHITE_CHERRY_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(PINK_CHERRY_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAHOGANY_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(JACARANDA_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(PALM_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILLOW_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAGIC_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(UMBRAN_LEAVES, transparentRenderType);
            ItemBlockRenderTypes.setRenderLayer(HELLBARK_LEAVES, transparentRenderType);

            ItemBlockRenderTypes.setRenderLayer(NETHER_CRYSTAL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(ORIGIN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(FLOWERING_OAK_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(RAINBOW_BIRCH_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(YELLOW_AUTUMN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(ORANGE_AUTUMN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAPLE_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(FIR_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(REDWOOD_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WHITE_CHERRY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PINK_CHERRY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAHOGANY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(JACARANDA_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PALM_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILLOW_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAGIC_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(UMBRAN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HELLBARK_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(ROSE, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(VIOLET, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(LAVENDER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILDFLOWER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(ORANGE_COSMOS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PINK_DAFFODIL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PINK_HIBISCUS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(GLOWFLOWER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILTED_LILY, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(BURNING_BLOSSOM, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(BLUE_HYDRANGEA, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(GOLDENROD, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILLOW_VINE, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(SPANISH_MOSS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(SPANISH_MOSS_PLANT, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(TREE_ROOTS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(TREE_ROOTS_STEM, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(GLOWWORM_SILK, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(GLOWWORM_SILK_STRAND, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HANGING_COBWEB, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HANGING_COBWEB_STRAND, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(SPROUT, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(BUSH, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(CLOVER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HUGE_CLOVER_PETAL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DUNE_GRASS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DESERT_GRASS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_GRASS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(CATTAIL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(BARLEY, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(REED, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WATERGRASS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MANGROVE_ROOT, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_BRANCH, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(BRAMBLE, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(TOADSTOOL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(GLOWSHROOM, cutoutRenderType);

            ItemBlockRenderTypes.setRenderLayer(POTTED_ORIGIN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FLOWERING_OAK_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_RAINBOW_BIRCH_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_YELLOW_AUTUMN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_ORANGE_AUTUMN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_MAPLE_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_FIR_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_REDWOOD_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_WHITE_CHERRY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_PINK_CHERRY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_MAHOGANY_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_JACARANDA_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_PALM_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_WILLOW_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_DEAD_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_MAGIC_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_UMBRAN_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_HELLBARK_SAPLING, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_ROSE, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_VIOLET, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_LAVENDER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_WILDFLOWER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_ORANGE_COSMOS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_PINK_DAFFODIL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_PINK_HIBISCUS, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_GLOWFLOWER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_WILTED_LILY, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_BURNING_BLOSSOM, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_SPROUT, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_CLOVER, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_TOADSTOOL, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(POTTED_GLOWSHROOM, cutoutRenderType);

            ItemBlockRenderTypes.setRenderLayer(FIR_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(REDWOOD_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(CHERRY_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAHOGANY_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(JACARANDA_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PALM_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILLOW_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAGIC_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(UMBRAN_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HELLBARK_DOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(FIR_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(REDWOOD_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(CHERRY_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAHOGANY_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(JACARANDA_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(PALM_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(WILLOW_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(DEAD_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(MAGIC_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(UMBRAN_TRAPDOOR, cutoutRenderType);
            ItemBlockRenderTypes.setRenderLayer(HELLBARK_TRAPDOOR, cutoutRenderType);
        }
    }

    private static RotatedPillarBlock log(MaterialColor materialColor, MaterialColor materialColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> {
            return blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? materialColor : materialColor2;
        }).strength(2.0F).sound(SoundType.WOOD));
    }

    public static Block registerBlock(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(CreativeModeTabBOP.INSTANCE));
        Registry.register(Registry.BLOCK, new ResourceLocation(BiomesOPlenty.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new ResourceLocation(BiomesOPlenty.MOD_ID, name), itemBlock);
        return block;
    }

    public static Block registerBlockNoGroup(Block block, String name)
    {
        BlockItem itemBlock = new BlockItem(block, new Item.Properties().tab(null));
        Registry.register(Registry.BLOCK, new ResourceLocation(BiomesOPlenty.MOD_ID, name), block);
        Registry.register(Registry.ITEM, new ResourceLocation(BiomesOPlenty.MOD_ID, name), itemBlock);
        return block;
    }

    public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
        Registry.register(Registry.BLOCK, new ResourceLocation(BiomesOPlenty.MOD_ID, name), block);

        if (itemBlock != null) {
            Registry.register(Registry.ITEM, new ResourceLocation(BiomesOPlenty.MOD_ID, name), itemBlock);
        }

        return block;
    }
}
