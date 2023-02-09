/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.api.block.BOPWoodTypes;
import biomesoplenty.common.block.*;
import biomesoplenty.common.block.trees.*;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.Suppliers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModBlocks
{
    public static void setup()
    {
        registerBlocks();
        registerBlockEntities();
    }

    public static void registerBlocks()
    {
        //Fluids
        Supplier<? extends FlowingFluid> BLOOD_SUPPLIER = Suppliers.memoize(() -> (FlowingFluid) BOPFluids.BLOOD.get());
        BLOOD = registerBlockNoGroup(() -> new BloodBlock(BLOOD_SUPPLIER, BlockBehaviour.Properties.of(ModMaterials.BLOOD).noCollission().randomTicks().strength(100.0F)), "blood");

        //Terrain
        WHITE_SAND = registerBlock(() -> new SandBlockBOP(0xF3F1E4, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.QUARTZ).strength(0.5F).sound(SoundType.SAND)), "white_sand");
        WHITE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "white_sandstone");
        WHITE_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(WHITE_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(WHITE_SANDSTONE.get())), "white_sandstone_stairs");
        WHITE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(WHITE_SANDSTONE.get())), "white_sandstone_slab");
        WHITE_SANDSTONE_WALL = registerBlock(() -> new WallBlock(Block.Properties.copy(WHITE_SANDSTONE.get())), "white_sandstone_wall");
        SMOOTH_WHITE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(2.0F, 6.0F)), "smooth_white_sandstone");
        SMOOTH_WHITE_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(WHITE_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(SMOOTH_WHITE_SANDSTONE.get())), "smooth_white_sandstone_stairs");
        SMOOTH_WHITE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(SMOOTH_WHITE_SANDSTONE.get())), "smooth_white_sandstone_slab");
        CUT_WHITE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "cut_white_sandstone");
        CUT_WHITE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(CUT_WHITE_SANDSTONE.get())), "cut_white_sandstone_slab");
        CHISELED_WHITE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "chiseled_white_sandstone");

        ORANGE_SAND = registerBlock(() -> new SandBlockBOP(0xCC9A61, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.SAND)), "orange_sand");
        ORANGE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "orange_sandstone");
        ORANGE_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(ORANGE_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(ORANGE_SANDSTONE.get())), "orange_sandstone_stairs");
        ORANGE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(ORANGE_SANDSTONE.get())), "orange_sandstone_slab");
        ORANGE_SANDSTONE_WALL = registerBlock(() -> new WallBlock(Block.Properties.copy(ORANGE_SANDSTONE.get())), "orange_sandstone_wall");
        SMOOTH_ORANGE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2.0F, 6.0F)), "smooth_orange_sandstone");
        SMOOTH_ORANGE_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(ORANGE_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE.get())), "smooth_orange_sandstone_stairs");
        SMOOTH_ORANGE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE.get())), "smooth_orange_sandstone_slab");
        CUT_ORANGE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "cut_orange_sandstone");
        CUT_ORANGE_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(CUT_ORANGE_SANDSTONE.get())), "cut_orange_sandstone_slab");
        CHISELED_ORANGE_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(0.8F)), "chiseled_orange_sandstone");

        BLACK_SAND = registerBlock(() -> new SandBlockBOP(0x2D2C2F, BlockBehaviour.Properties.of(Material.SAND, MaterialColor.COLOR_BLACK).strength(0.5F).sound(SoundType.SAND)), "black_sand");
        BLACK_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "black_sandstone");
        BLACK_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(BLACK_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(BLACK_SANDSTONE.get())), "black_sandstone_stairs");
        BLACK_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(BLACK_SANDSTONE.get())), "black_sandstone_slab");
        BLACK_SANDSTONE_WALL = registerBlock(() -> new WallBlock(Block.Properties.copy(BLACK_SANDSTONE.get())), "black_sandstone_wall");
        SMOOTH_BLACK_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0F, 6.0F)), "smooth_black_sandstone");
        SMOOTH_BLACK_SANDSTONE_STAIRS = registerBlock(() -> new StairBlock(BLACK_SANDSTONE.get()::defaultBlockState, Block.Properties.copy(SMOOTH_BLACK_SANDSTONE.get())), "smooth_black_sandstone_stairs");
        SMOOTH_BLACK_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(SMOOTH_BLACK_SANDSTONE.get())), "smooth_black_sandstone_slab");
        CUT_BLACK_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "cut_black_sandstone");
        CUT_BLACK_SANDSTONE_SLAB = registerBlock(() -> new SlabBlock(Block.Properties.copy(CUT_BLACK_SANDSTONE.get())), "cut_black_sandstone_slab");
        CHISELED_BLACK_SANDSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)), "chiseled_black_sandstone");

        ORIGIN_GRASS_BLOCK = registerBlock(() -> new GrassBlock(BlockBehaviour.Properties.of(Material.GRASS).randomTicks().strength(0.6F).sound(SoundType.GRASS)), "origin_grass_block");
        ROOTED_SAND = registerBlock(() -> new RootedSandBlock(BlockBehaviour.Properties.of(Material.SAND, MaterialColor.SAND).strength(0.7F).sound(SoundType.SAND)), "rooted_sand");
        DRIED_SALT = registerBlock(() -> new DriedSaltBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.WOOD).strength(1.0F).sound(new SoundType(1.0F, 0.5F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE, SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL))), "dried_salt");
        FLESH = registerBlock(() -> new FleshBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).strength(0.4F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh");
        POROUS_FLESH = registerBlock(() -> new FleshBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).strength(0.4F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "porous_flesh");
        BRIMSTONE = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(0.5F)), "brimstone");
        BRIMSTONE_FUMAROLE = registerBlock(() -> new BrimstoneFumaroleBlock(BlockBehaviour.Properties.copy(BRIMSTONE.get())), "brimstone_fumarole");
        ROSE_QUARTZ_BLOCK = registerBlock(() -> new AmethystBlock(BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.CRIMSON_STEM).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().lightLevel((state) -> 10)), "rose_quartz_block");

        TOADSTOOL_BLOCK = registerBlock(() -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.WOOD)), "toadstool_block");
        GLOWSHROOM_BLOCK = registerBlock(() -> new HugeMushroomBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIAMOND).strength(0.2F).sound(SoundType.WOOD).lightLevel((state) -> 10).emissiveRendering((state, world, pos) -> true)), "glowshroom_block");

        GLOWING_MOSS_CARPET = registerBlock(() -> new CarpetBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIAMOND).strength(0.1F).sound(SoundType.MOSS_CARPET).lightLevel((state) -> 6)), "glowing_moss_carpet");
        GLOWING_MOSS_BLOCK = registerBlock(() -> new GlowingMossBlock(BlockBehaviour.Properties.of(Material.MOSS, MaterialColor.DIAMOND).strength(0.1F).sound(SoundType.MOSS).lightLevel((state) -> 6)), "glowing_moss_block");

        SPIDER_EGG = registerBlock(() -> new SpiderEggBlock(BlockBehaviour.Properties.of(Material.EGG).strength(0.1F).sound(SoundType.METAL).lightLevel((state) -> 5)), "spider_egg");

        //Trees
        ORIGIN_SAPLING = registerBlock(() -> new SaplingBlockBOP(new OriginTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "origin_sapling");
        ORIGIN_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.EMERALD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "origin_leaves");
        FLOWERING_OAK_SAPLING = registerBlock(() -> new SaplingBlockBOP(new FloweringOakTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "flowering_oak_sapling");
        FLOWERING_OAK_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "flowering_oak_leaves");
        RAINBOW_BIRCH_SAPLING = registerBlock(() -> new SaplingBlockBOP(new RainbowBirchTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "rainbow_birch_sapling");
        RAINBOW_BIRCH_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "rainbow_birch_leaves");
        YELLOW_AUTUMN_SAPLING = registerBlock(() -> new SaplingBlockBOP(new YellowAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "yellow_autumn_sapling");
        YELLOW_AUTUMN_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "yellow_autumn_leaves");
        ORANGE_AUTUMN_SAPLING = registerBlock(() -> new SaplingBlockBOP(new OrangeAutumnTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "orange_autumn_sapling");
        ORANGE_AUTUMN_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_ORANGE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "orange_autumn_leaves");
        MAPLE_SAPLING = registerBlock(() -> new SaplingBlockBOP(new MapleTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "maple_sapling");
        MAPLE_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_RED).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "maple_leaves");

        FIR_SAPLING = registerBlock(() -> new SaplingBlockBOP(new FirTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "fir_sapling");
        FIR_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "fir_leaves");
        FIR_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "fir_log");
        FIR_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "fir_wood");
        STRIPPED_FIR_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_fir_log");
        STRIPPED_FIR_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        FIR_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        FIR_STAIRS = registerBlock(() -> new StairBlock(FIR_PLANKS.get()::defaultBlockState, Block.Properties.copy(FIR_PLANKS.get())), "fir_stairs");
        FIR_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        FIR_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        FIR_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "fir_fence_gate");
        FIR_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "fir_door");
        FIR_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "fir_trapdoor");
        FIR_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, FIR_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "fir_pressure_plate");
        FIR_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "fir_button");
        FIR_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.FIR), "fir_sign");
        FIR_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(FIR_SIGN), BOPWoodTypes.FIR), "fir_wall_sign");

        REDWOOD_SAPLING = registerBlock(() -> new SaplingBlockBOP(new RedwoodTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        REDWOOD_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "redwood_leaves");
        REDWOOD_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "redwood_log");
        REDWOOD_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        STRIPPED_REDWOOD_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_ORANGE, MaterialColor.TERRACOTTA_ORANGE), "stripped_redwood_log");
        STRIPPED_REDWOOD_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        REDWOOD_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        REDWOOD_STAIRS = registerBlock(() -> new StairBlock(REDWOOD_PLANKS.get()::defaultBlockState, Block.Properties.copy(REDWOOD_PLANKS.get())), "redwood_stairs");
        REDWOOD_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        REDWOOD_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        REDWOOD_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "redwood_fence_gate");
        REDWOOD_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "redwood_door");
        REDWOOD_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "redwood_trapdoor");
        REDWOOD_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, REDWOOD_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "redwood_pressure_plate");
        REDWOOD_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "redwood_button");
        REDWOOD_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.REDWOOD), "redwood_sign");
        REDWOOD_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(REDWOOD_SIGN), BOPWoodTypes.REDWOOD), "redwood_wall_sign");

        WHITE_CHERRY_SAPLING = registerBlock(() -> new SaplingBlockBOP(new WhiteCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "white_cherry_sapling");
        WHITE_CHERRY_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.SNOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "white_cherry_leaves");
        PINK_CHERRY_SAPLING = registerBlock(() -> new SaplingBlockBOP(new PinkCherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "pink_cherry_sapling");
        PINK_CHERRY_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "pink_cherry_leaves");
        CHERRY_LOG = registerBlock(() -> log(MaterialColor.COLOR_RED, MaterialColor.TERRACOTTA_RED), "cherry_log");
        CHERRY_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        STRIPPED_CHERRY_LOG = registerBlock(() -> log(MaterialColor.COLOR_RED, MaterialColor.COLOR_RED), "stripped_cherry_log");
        STRIPPED_CHERRY_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        CHERRY_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        CHERRY_STAIRS = registerBlock(() -> new StairBlock(CHERRY_PLANKS.get()::defaultBlockState, Block.Properties.copy(CHERRY_PLANKS.get())), "cherry_stairs");
        CHERRY_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        CHERRY_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        CHERRY_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "cherry_fence_gate");
        CHERRY_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "cherry_door");
        CHERRY_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "cherry_trapdoor");
        CHERRY_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "cherry_pressure_plate");
        CHERRY_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "cherry_button");
        CHERRY_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.CHERRY), "cherry_sign");
        CHERRY_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(CHERRY_SIGN), BOPWoodTypes.CHERRY), "cherry_wall_sign");

        MAHOGANY_SAPLING = registerBlock(() -> new SaplingBlockBOP(new MahoganyTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "mahogany_sapling");
        MAHOGANY_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "mahogany_leaves");
        MAHOGANY_LOG = registerBlock(() -> log(MaterialColor.TERRACOTTA_PINK, MaterialColor.DIRT), "mahogany_log");
        MAHOGANY_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        STRIPPED_MAHOGANY_LOG = registerBlock(() -> log(MaterialColor.TERRACOTTA_PINK, MaterialColor.TERRACOTTA_PINK), "stripped_mahogany_log");
        STRIPPED_MAHOGANY_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        MAHOGANY_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        MAHOGANY_STAIRS = registerBlock(() -> new StairBlock(MAHOGANY_PLANKS.get()::defaultBlockState, Block.Properties.copy(MAHOGANY_PLANKS.get())), "mahogany_stairs");
        MAHOGANY_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        MAHOGANY_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        MAHOGANY_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "mahogany_fence_gate");
        MAHOGANY_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "mahogany_door");
        MAHOGANY_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "mahogany_trapdoor");
        MAHOGANY_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MAHOGANY_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "mahogany_pressure_plate");
        MAHOGANY_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "mahogany_button");
        MAHOGANY_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.MAHOGANY), "mahogany_sign");
        MAHOGANY_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(MAHOGANY_SIGN), BOPWoodTypes.MAHOGANY), "mahogany_wall_sign");

        JACARANDA_SAPLING = registerBlock(() -> new SaplingBlockBOP(new JacarandaTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "jacaranda_sapling");
        JACARANDA_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "jacaranda_leaves");
        JACARANDA_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_LIGHT_GRAY), "jacaranda_log");
        JACARANDA_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        STRIPPED_JACARANDA_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_WHITE, MaterialColor.TERRACOTTA_WHITE), "stripped_jacaranda_log");
        STRIPPED_JACARANDA_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        JACARANDA_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        JACARANDA_STAIRS = registerBlock(() -> new StairBlock(JACARANDA_PLANKS.get()::defaultBlockState, Block.Properties.copy(JACARANDA_PLANKS.get())), "jacaranda_stairs");
        JACARANDA_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        JACARANDA_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        JACARANDA_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "jacaranda_fence_gate");
        JACARANDA_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "jacaranda_door");
        JACARANDA_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "jacaranda_trapdoor");
        JACARANDA_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, JACARANDA_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "jacaranda_pressure_plate");
        JACARANDA_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "jacaranda_button");
        JACARANDA_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.JACARANDA), "jacaranda_sign");
        JACARANDA_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(JACARANDA_SIGN), BOPWoodTypes.JACARANDA), "jacaranda_wall_sign");

        PALM_SAPLING = registerBlock(() -> new SaplingBlockBOP(new PalmTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "palm_sapling");
        PALM_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "palm_leaves");
        PALM_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.PODZOL), "palm_log");
        PALM_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "palm_wood");
        STRIPPED_PALM_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_YELLOW, MaterialColor.TERRACOTTA_YELLOW), "stripped_palm_log");
        STRIPPED_PALM_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        PALM_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        PALM_STAIRS = registerBlock(() -> new StairBlock(PALM_PLANKS.get()::defaultBlockState, Block.Properties.copy(PALM_PLANKS.get())), "palm_stairs");
        PALM_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        PALM_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        PALM_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "palm_fence_gate");
        PALM_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "palm_door");
        PALM_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "palm_trapdoor");
        PALM_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, PALM_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "palm_pressure_plate");
        PALM_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "palm_button");
        PALM_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.PALM), "palm_sign");
        PALM_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(PALM_SIGN), BOPWoodTypes.PALM), "palm_wall_sign");

        WILLOW_SAPLING = registerBlock(() -> new SaplingBlockBOP(new WillowTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "willow_sapling");
        WILLOW_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "willow_leaves");
        WILLOW_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "willow_log");
        WILLOW_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_wood");
        STRIPPED_WILLOW_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_LIGHT_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN), "stripped_willow_log");
        STRIPPED_WILLOW_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        WILLOW_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        WILLOW_STAIRS = registerBlock(() -> new StairBlock(WILLOW_PLANKS.get()::defaultBlockState, Block.Properties.copy(WILLOW_PLANKS.get())), "willow_stairs");
        WILLOW_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        WILLOW_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        WILLOW_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "willow_fence_gate");
        WILLOW_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "willow_door");
        WILLOW_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "willow_trapdoor");
        WILLOW_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, WILLOW_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "willow_pressure_plate");
        WILLOW_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "willow_button");
        WILLOW_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.WILLOW), "willow_sign");
        WILLOW_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(WILLOW_SIGN), BOPWoodTypes.WILLOW), "willow_wall_sign");

        DEAD_SAPLING = registerBlock(() -> new SaplingBlockBOP(new DeadTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "dead_sapling");
        DEAD_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.WOOD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "dead_leaves");
        DEAD_LOG = registerBlock(() -> Blocks.log(MaterialColor.STONE, MaterialColor.COLOR_GRAY), "dead_log");
        DEAD_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "dead_wood");
        STRIPPED_DEAD_LOG = registerBlock(() -> Blocks.log(MaterialColor.STONE, MaterialColor.STONE), "stripped_dead_log");
        STRIPPED_DEAD_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        DEAD_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        DEAD_STAIRS = registerBlock(() -> new StairBlock(DEAD_PLANKS.get()::defaultBlockState, Block.Properties.copy(DEAD_PLANKS.get())), "dead_stairs");
        DEAD_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        DEAD_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        DEAD_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "dead_fence_gate");
        DEAD_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "dead_door");
        DEAD_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.STONE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "dead_trapdoor");
        DEAD_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, DEAD_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "dead_pressure_plate");
        DEAD_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "dead_button");
        DEAD_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.DEAD), "dead_sign");
        DEAD_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(DEAD_SIGN), BOPWoodTypes.DEAD), "dead_wall_sign");

        MAGIC_SAPLING = registerBlock(() -> new SaplingBlockBOP(new MagicTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "magic_sapling");
        MAGIC_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "magic_leaves");
        MAGIC_LOG = registerBlock(() -> Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.TERRACOTTA_LIGHT_BLUE), "magic_log");
        MAGIC_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "magic_wood");
        STRIPPED_MAGIC_LOG = registerBlock(() -> Blocks.log(MaterialColor.COLOR_BLUE, MaterialColor.COLOR_BLUE), "stripped_magic_log");
        STRIPPED_MAGIC_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        MAGIC_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        MAGIC_STAIRS = registerBlock(() -> new StairBlock(MAGIC_PLANKS.get()::defaultBlockState, Block.Properties.copy(MAGIC_PLANKS.get())), "magic_stairs");
        MAGIC_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        MAGIC_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        MAGIC_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "magic_fence_gate");
        MAGIC_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "magic_door");
        MAGIC_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "magic_trapdoor");
        MAGIC_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MAGIC_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "magic_pressure_plate");
        MAGIC_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "magic_button");
        MAGIC_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.MAGIC), "magic_sign");
        MAGIC_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(MAGIC_SIGN), BOPWoodTypes.MAGIC), "magic_wall_sign");

        UMBRAN_SAPLING = registerBlock(() -> new SaplingBlockBOP(new UmbranTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "umbran_sapling");
        UMBRAN_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_BLUE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "umbran_leaves");
        UMBRAN_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "umbran_log");
        UMBRAN_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        STRIPPED_UMBRAN_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_BLUE, MaterialColor.TERRACOTTA_BLUE), "stripped_umbran_log");
        STRIPPED_UMBRAN_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        UMBRAN_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        UMBRAN_STAIRS = registerBlock(() -> new StairBlock(UMBRAN_PLANKS.get()::defaultBlockState, Block.Properties.copy(UMBRAN_PLANKS.get())), "umbran_stairs");
        UMBRAN_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        UMBRAN_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        UMBRAN_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "umbran_fence_gate");
        UMBRAN_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "umbran_door");
        UMBRAN_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "umbran_trapdoor");
        UMBRAN_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, UMBRAN_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "umbran_pressure_plate");
        UMBRAN_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "umbran_button");
        UMBRAN_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.UMBRAN), "umbran_sign");
        UMBRAN_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(UMBRAN_SIGN), BOPWoodTypes.UMBRAN), "umbran_wall_sign");

        HELLBARK_SAPLING = registerBlock(() -> new SaplingBlockBOP(new HellbarkTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "hellbark_sapling");
        HELLBARK_LEAVES = registerBlock(() -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "hellbark_leaves");
        HELLBARK_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.COLOR_LIGHT_GRAY), "hellbark_log");
        HELLBARK_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        STRIPPED_HELLBARK_LOG = registerBlock(() -> Blocks.log(MaterialColor.TERRACOTTA_GRAY, MaterialColor.TERRACOTTA_GRAY), "stripped_hellbark_log");
        STRIPPED_HELLBARK_WOOD = registerBlock(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        HELLBARK_PLANKS = registerBlock(() -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        HELLBARK_STAIRS = registerBlock(() -> new StairBlock(HELLBARK_PLANKS.get()::defaultBlockState, Block.Properties.copy(HELLBARK_PLANKS.get())), "hellbark_stairs");
        HELLBARK_SLAB = registerBlock(() -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        HELLBARK_FENCE = registerBlock(() -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        HELLBARK_FENCE_GATE = registerBlock(() -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.get().defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN), "hellbark_fence_gate");
        HELLBARK_DOOR = registerBlock(() -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.get().defaultMaterialColor()).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN), "hellbark_door");
        HELLBARK_TRAPDOOR = registerBlock(() -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion(), SoundEvents.WOODEN_TRAPDOOR_CLOSE, SoundEvents.WOODEN_TRAPDOOR_OPEN), "hellbark_trapdoor");
        HELLBARK_PRESSURE_PLATE = registerBlock(() -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, HELLBARK_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5F).sound(SoundType.WOOD), SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.WOODEN_PRESSURE_PLATE_CLICK_ON), "hellbark_pressure_plate");
        HELLBARK_BUTTON = registerBlock(() -> new ButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD), 30, true, SoundEvents.WOODEN_BUTTON_CLICK_OFF, SoundEvents.WOODEN_BUTTON_CLICK_ON), "hellbark_button");
        HELLBARK_SIGN = registerBlockNoBlockItem(() -> new StandingSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BOPWoodTypes.HELLBARK), "hellbark_sign");
        HELLBARK_WALL_SIGN = registerBlockNoBlockItem(() -> new WallSignBlockBOP(BlockBehaviour.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).lootFrom(HELLBARK_SIGN), BOPWoodTypes.HELLBARK), "hellbark_wall_sign");

        //Flowers
        ROSE = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.MOVEMENT_SPEED, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "rose");
        VIOLET = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.CONFUSION, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "violet");
        LAVENDER = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.HEALTH_BOOST, 5, BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_MAGENTA).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "lavender");
        WILDFLOWER = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.HUNGER, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "wildflower");
        ORANGE_COSMOS = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.ABSORPTION, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "orange_cosmos");
        PINK_DAFFODIL = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.INVISIBILITY, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "pink_daffodil");
        PINK_HIBISCUS = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "pink_hibiscus");
        GLOWFLOWER = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.GLOWING, 10, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 9)), "glowflower");
        WILTED_LILY = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.UNLUCK, 5, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "wilted_lily");
        BURNING_BLOSSOM = registerBlock(() -> new FlowerBlockBOP(() -> MobEffects.FIRE_RESISTANCE, 7, BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 7)), "burning_blossom");

        //Tall Flowers
        TALL_LAVENDER = registerBlock(() -> new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "tall_lavender");
        BLUE_HYDRANGEA = registerBlock(() -> new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "blue_hydrangea");
        GOLDENROD = registerBlock(() -> new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "goldenrod");
        ICY_IRIS = registerBlock(() -> new TallFlowerBlockBOP(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "icy_iris");

        //Vines
        WILLOW_VINE = registerBlock(() -> new VineBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");
        SPANISH_MOSS = registerBlock(() -> new SpanishMossBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).randomTicks().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss");
        SPANISH_MOSS_PLANT = registerBlockNoGroup(() -> new SpanishMossBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss_plant");
        GLOWWORM_SILK = registerBlock(() -> new GlowwormSilkBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "glowworm_silk");
        GLOWWORM_SILK_STRAND = registerBlockNoGroup(() -> new GlowwormSilkBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "glowworm_silk_strand");
        HANGING_COBWEB = registerBlock(() -> new HangingCobwebBottomBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb");
        HANGING_COBWEB_STRAND = registerBlockNoGroup(() -> new HangingCobwebBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb_strand");
        STRINGY_COBWEB = registerBlock(() -> new StringyCobwebBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "stringy_cobweb");
        WEBBING = registerBlock(() -> new WebbingBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.WOOL)), "webbing");

        //Plants
        SPROUT = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "sprout");
        BUSH = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "bush");
        CLOVER = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "clover");
        HUGE_CLOVER_PETAL = registerBlock(() -> new HugeCloverPetalBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS)), "huge_clover_petal");
        DUNE_GRASS = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_LIGHT_GREEN).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "dune_grass");
        DESERT_GRASS = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_ORANGE).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "desert_grass");
        DEAD_GRASS = registerBlock(() -> new FoliageBlockBOP(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "dead_grass");

        //Tall Plants
        CATTAIL = registerBlock(() -> new DoubleWatersidePlantBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "cattail");
        BARLEY = registerBlock(() -> new DoublePlantBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_YELLOW).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "barley");
        SEA_OATS = registerBlock(() -> new SeaOatsBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.SAND).randomTicks().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "sea_oats");
        REED = registerBlock(() -> new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "reed");
        WATERGRASS = registerBlock(() -> new DoubleWaterPlantBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_WATER_PLANT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "watergrass");

        DEAD_BRANCH = registerBlock(() -> new DeadBranchBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).noCollission().instabreak().sound(SoundType.WOOD)), "dead_branch");
        BRAMBLE = registerBlock(() -> new BrambleBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.NETHER).strength(0.4F).sound(SoundType.WOOD)), "bramble");
        TOADSTOOL = registerBlock(() -> new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "toadstool");
        GLOWSHROOM = registerBlock(() -> new MushroomBlockBOP(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.DIAMOND).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 6).emissiveRendering((state, world, pos) -> true)), "glowshroom");

        PUS_BUBBLE = registerBlock(() -> new PusBubbleBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_PINK).noCollission().instabreak().sound(new SoundType(1.0F, 1.0F, SoundEvents.LAVA_POP, SoundEvents.LAVA_POP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.LAVA_POP, SoundEvents.LAVA_POP))), "pus_bubble");
        FLESH_TENDONS = registerBlock(() -> new FleshTendonsBottomBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh_tendons");
        FLESH_TENDONS_STRAND = registerBlockNoGroup(() -> new FleshTendonsBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh_tendons_strand");
        EYEBULB = registerBlock(() -> new EyebulbBlock(BlockBehaviour.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL)).offsetType(BlockBehaviour.OffsetType.NONE)), "eyebulb");
        HAIR = registerBlock(() -> new HairBlock(BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.RAW_IRON).noCollission().instabreak().sound(SoundType.WOOL).offsetType(BlockBehaviour.OffsetType.XYZ)), "hair");
        BRIMSTONE_BUD = registerBlock(() -> new BrimstoneBudBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ)), "brimstone_bud");
        BRIMSTONE_CLUSTER = registerBlock(() -> new BrimstoneClusterBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_YELLOW).strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.NONE)), "brimstone_cluster");

        ROSE_QUARTZ_CLUSTER = registerBlock(() -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST, MaterialColor.CRIMSON_STEM).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((state) -> 8)), "rose_quartz_cluster");
        LARGE_ROSE_QUARTZ_BUD = registerBlock(() -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((state) -> 7)), "large_rose_quartz_bud");
        MEDIUM_ROSE_QUARTZ_BUD = registerBlock(() -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((state) -> 6)), "medium_rose_quartz_bud");
        SMALL_ROSE_QUARTZ_BUD = registerBlock(() -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((state) -> 5)), "small_rose_quartz_bud");

        BLACKSTONE_SPINES = registerBlock(() -> new BlackstoneDecorationBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ)), "blackstone_spines");
        BLACKSTONE_BULB = registerBlock(() -> new BlackstoneDecorationBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "blackstone_bulb");

        //Potted Plants
        POTTED_ORIGIN_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(ORIGIN_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_origin_sapling");
        POTTED_FLOWERING_OAK_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(FLOWERING_OAK_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_flowering_oak_sapling");
        POTTED_RAINBOW_BIRCH_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(RAINBOW_BIRCH_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rainbow_birch_sapling");
        POTTED_YELLOW_AUTUMN_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(YELLOW_AUTUMN_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_yellow_autumn_sapling");
        POTTED_ORANGE_AUTUMN_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(ORANGE_AUTUMN_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_autumn_sapling");
        POTTED_MAPLE_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(MAPLE_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_maple_sapling");
        POTTED_FIR_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(FIR_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_fir_sapling");
        POTTED_REDWOOD_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(REDWOOD_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_redwood_sapling");
        POTTED_WHITE_CHERRY_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(WHITE_CHERRY_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_white_cherry_sapling");
        POTTED_PINK_CHERRY_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(PINK_CHERRY_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_cherry_sapling");
        POTTED_MAHOGANY_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(MAHOGANY_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_mahogany_sapling");
        POTTED_JACARANDA_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(JACARANDA_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_jacaranda_sapling");
        POTTED_PALM_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(PALM_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_palm_sapling");
        POTTED_WILLOW_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(WILLOW_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_willow_sapling");
        POTTED_DEAD_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(DEAD_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_dead_sapling");
        POTTED_MAGIC_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(MAGIC_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_magic_sapling");
        POTTED_UMBRAN_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(UMBRAN_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_umbran_sapling");
        POTTED_HELLBARK_SAPLING = registerBlockNoGroup(() -> new FlowerPotBlock(HELLBARK_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_hellbark_sapling");
        POTTED_ROSE = registerBlockNoGroup(() -> new FlowerPotBlock(ROSE.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_rose");
        POTTED_VIOLET = registerBlockNoGroup(() -> new FlowerPotBlock(VIOLET.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_violet");
        POTTED_LAVENDER = registerBlockNoGroup(() -> new FlowerPotBlock(LAVENDER.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_lavender");
        POTTED_WILDFLOWER = registerBlockNoGroup(() -> new FlowerPotBlock(WILDFLOWER.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wildflower");
        POTTED_ORANGE_COSMOS = registerBlockNoGroup(() -> new FlowerPotBlock(ORANGE_COSMOS.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_orange_cosmos");
        POTTED_PINK_DAFFODIL = registerBlockNoGroup(() -> new FlowerPotBlock(PINK_DAFFODIL.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_daffodil");
        POTTED_PINK_HIBISCUS = registerBlockNoGroup(() -> new FlowerPotBlock(PINK_HIBISCUS.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_pink_hibiscus");
        POTTED_GLOWFLOWER = registerBlockNoGroup(() -> new FlowerPotBlock(GLOWFLOWER.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 9)), "potted_glowflower");
        POTTED_WILTED_LILY = registerBlockNoGroup(() -> new FlowerPotBlock(WILTED_LILY.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_wilted_lily");
        POTTED_BURNING_BLOSSOM = registerBlockNoGroup(() -> new FlowerPotBlock(BURNING_BLOSSOM.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 7)), "potted_burning_blossom");
        POTTED_SPROUT = registerBlockNoGroup(() -> new FlowerPotBlock(SPROUT.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_sprout");
        POTTED_CLOVER = registerBlockNoGroup(() -> new FlowerPotBlock(CLOVER.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_clover");
        POTTED_TOADSTOOL = registerBlockNoGroup(() -> new FlowerPotBlock(TOADSTOOL.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak()), "potted_toadstool");
        POTTED_GLOWSHROOM = registerBlockNoGroup(() -> new FlowerPotBlock(GLOWSHROOM.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().lightLevel((state) -> 6)), "potted_glowshroom");
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerWoodTypes()
    {
        Sheets.addWoodType(BOPWoodTypes.FIR);
        Sheets.addWoodType(BOPWoodTypes.REDWOOD);
        Sheets.addWoodType(BOPWoodTypes.CHERRY);
        Sheets.addWoodType(BOPWoodTypes.MAHOGANY);
        Sheets.addWoodType(BOPWoodTypes.JACARANDA);
        Sheets.addWoodType(BOPWoodTypes.PALM);
        Sheets.addWoodType(BOPWoodTypes.WILLOW);
        Sheets.addWoodType(BOPWoodTypes.DEAD);
        Sheets.addWoodType(BOPWoodTypes.MAGIC);
        Sheets.addWoodType(BOPWoodTypes.UMBRAN);
        Sheets.addWoodType(BOPWoodTypes.HELLBARK);
    }

    public static void registerBlockEntities()
    {
        BOPBlockEntities.SIGN = registerBlockEntityType("sign", SignBlockEntityBOP::new,
                () -> List.of(FIR_SIGN.get(), REDWOOD_SIGN.get(), CHERRY_SIGN.get(), MAHOGANY_SIGN.get(), JACARANDA_SIGN.get(), PALM_SIGN.get(), WILLOW_SIGN.get(), DEAD_SIGN.get(), MAGIC_SIGN.get(), UMBRAN_SIGN.get(), HELLBARK_SIGN.get(),
            FIR_WALL_SIGN.get(), REDWOOD_WALL_SIGN.get(), CHERRY_WALL_SIGN.get(), MAHOGANY_WALL_SIGN.get(), JACARANDA_WALL_SIGN.get(), PALM_WALL_SIGN.get(), WILLOW_WALL_SIGN.get(), DEAD_WALL_SIGN.get(), MAGIC_WALL_SIGN.get(), UMBRAN_WALL_SIGN.get(), HELLBARK_WALL_SIGN.get()));
    }

    private static RotatedPillarBlock log(MaterialColor materialColor, MaterialColor materialColor2) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, (blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? materialColor : materialColor2).strength(2.0F).sound(SoundType.WOOD));
    }

    public static RegistryObject<Block> registerBlock(Supplier<Block> blockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = BiomesOPlenty.BLOCK_REGISTER.register(name, blockSupplier);
        BiomesOPlenty.ITEM_REGISTER.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }

    public static RegistryObject<Block> registerBlockNoGroup(Supplier<Block> blockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = BiomesOPlenty.BLOCK_REGISTER.register(name, blockSupplier);
        BiomesOPlenty.ITEM_REGISTER.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }

    public static RegistryObject<Block> registerBlockNoBlockItem(Supplier<Block> blockSupplier, String name)
    {
        return registerBlock(blockSupplier, null, name);
    }

    public static RegistryObject<Block> registerBlock(Supplier<Block> blockSupplier, Supplier<BlockItem> itemBlockSupplier, String name)
    {
        RegistryObject<Block> blockRegistryObject = BiomesOPlenty.BLOCK_REGISTER.register(name, blockSupplier);

        if (itemBlockSupplier != null)
        {
            BiomesOPlenty.ITEM_REGISTER.register(name, itemBlockSupplier);
        }

        return blockRegistryObject;
    }

    public static RegistryObject<BlockEntityType<?>> registerBlockEntityType(String name, BlockEntityType.BlockEntitySupplier<?> factoryIn, Supplier<List<Block>> validBlocks)
    {
        return BiomesOPlenty.BLOCK_ENTITY_REGISTER.register(name, () -> BlockEntityType.Builder.of(factoryIn, validBlocks.get().toArray(new Block[0])).build(null));
    }
}
