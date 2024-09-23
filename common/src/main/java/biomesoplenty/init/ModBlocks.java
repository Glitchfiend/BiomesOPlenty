/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlockSetTypes;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.api.block.BOPWoodTypes;
import biomesoplenty.api.sound.BOPSounds;
import biomesoplenty.block.*;
import biomesoplenty.block.trees.BOPTreeGrowers;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.BOPSurfaceRuleData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import terrablender.api.SurfaceRuleManager;

import java.util.function.BiConsumer;

import static biomesoplenty.api.block.BOPBlocks.*;

public class ModBlocks
{
    public static void setup(BiConsumer<ResourceLocation, Block> func)
    {
        registerBlocks(func);
        registerSurfaceRules();
    }

    private static void registerSurfaceRules()
    {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, BiomesOPlenty.MOD_ID, BOPSurfaceRuleData.overworld());
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, BiomesOPlenty.MOD_ID, BOPSurfaceRuleData.nether());
    }

    private static void registerBlocks(BiConsumer<ResourceLocation, Block> func)
    {
        //Fluids
        BLOOD = register(func, new BloodBlock(BOPFluids.FLOWING_BLOOD, BlockBehaviour.Properties.of().replaceable().pushReaction(PushReaction.DESTROY).liquid().noCollission().randomTicks().noLootTable().mapColor(MapColor.CRIMSON_NYLIUM).sound(SoundType.EMPTY).strength(100.0F)), "blood");
        LIQUID_NULL = register(func, new BloodBlock(BOPFluids.FLOWING_LIQUID_NULL, BlockBehaviour.Properties.of().replaceable().pushReaction(PushReaction.DESTROY).liquid().noCollission().randomTicks().noLootTable().sound(SoundType.EMPTY).strength(100.0F)), "liquid_null");

        //Terrain
        WHITE_SAND = register(func, new SandBlockBOP(0xF3F1E4, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.QUARTZ).strength(0.5F).sound(SoundType.SAND)), "white_sand");
        WHITE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F)), "white_sandstone");
        WHITE_SANDSTONE_STAIRS = register(func, new StairBlock(WHITE_SANDSTONE.defaultBlockState(), Block.Properties.copy(WHITE_SANDSTONE)), "white_sandstone_stairs");
        WHITE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(WHITE_SANDSTONE)), "white_sandstone_slab");
        WHITE_SANDSTONE_WALL = register(func, new WallBlock(Block.Properties.copy(WHITE_SANDSTONE).forceSolidOn()), "white_sandstone_wall");
        SMOOTH_WHITE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(2.0F, 6.0F)), "smooth_white_sandstone");
        SMOOTH_WHITE_SANDSTONE_STAIRS = register(func, new StairBlock(WHITE_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_WHITE_SANDSTONE)), "smooth_white_sandstone_stairs");
        SMOOTH_WHITE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(SMOOTH_WHITE_SANDSTONE)), "smooth_white_sandstone_slab");
        CUT_WHITE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F)), "cut_white_sandstone");
        CUT_WHITE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(CUT_WHITE_SANDSTONE)), "cut_white_sandstone_slab");
        CHISELED_WHITE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.QUARTZ).requiresCorrectToolForDrops().strength(0.8F)), "chiseled_white_sandstone");

        ORANGE_SAND = register(func, new SandBlockBOP(0xCC9A61, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.COLOR_ORANGE).strength(0.5F).sound(SoundType.SAND)), "orange_sand");
        ORANGE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(0.8F)), "orange_sandstone");
        ORANGE_SANDSTONE_STAIRS = register(func, new StairBlock(ORANGE_SANDSTONE.defaultBlockState(), Block.Properties.copy(ORANGE_SANDSTONE)), "orange_sandstone_stairs");
        ORANGE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(ORANGE_SANDSTONE)), "orange_sandstone_slab");
        ORANGE_SANDSTONE_WALL = register(func, new WallBlock(Block.Properties.copy(ORANGE_SANDSTONE).forceSolidOn()), "orange_sandstone_wall");
        SMOOTH_ORANGE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(2.0F, 6.0F)), "smooth_orange_sandstone");
        SMOOTH_ORANGE_SANDSTONE_STAIRS = register(func, new StairBlock(ORANGE_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE)), "smooth_orange_sandstone_stairs");
        SMOOTH_ORANGE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(SMOOTH_ORANGE_SANDSTONE)), "smooth_orange_sandstone_slab");
        CUT_ORANGE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(0.8F)), "cut_orange_sandstone");
        CUT_ORANGE_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(CUT_ORANGE_SANDSTONE)), "cut_orange_sandstone_slab");
        CHISELED_ORANGE_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_ORANGE).requiresCorrectToolForDrops().strength(0.8F)), "chiseled_orange_sandstone");

        MOSSY_BLACK_SAND = register(func, new OvergrownSandBlock(0x2D2C2F, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.COLOR_GREEN).strength(0.5F).randomTicks().sound(SoundType.MOSS)), "mossy_black_sand");
        BLACK_SAND = register(func, new SandBlockBOP(0x2D2C2F, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SNARE).mapColor(MapColor.COLOR_BLACK).strength(0.5F).sound(SoundType.SAND)), "black_sand");
        BLACK_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F)), "black_sandstone");
        BLACK_SANDSTONE_STAIRS = register(func, new StairBlock(BLACK_SANDSTONE.defaultBlockState(), Block.Properties.copy(BLACK_SANDSTONE)), "black_sandstone_stairs");
        BLACK_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(BLACK_SANDSTONE)), "black_sandstone_slab");
        BLACK_SANDSTONE_WALL = register(func, new WallBlock(Block.Properties.copy(BLACK_SANDSTONE).forceSolidOn()), "black_sandstone_wall");
        SMOOTH_BLACK_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(2.0F, 6.0F)), "smooth_black_sandstone");
        SMOOTH_BLACK_SANDSTONE_STAIRS = register(func, new StairBlock(BLACK_SANDSTONE.defaultBlockState(), Block.Properties.copy(SMOOTH_BLACK_SANDSTONE)), "smooth_black_sandstone_stairs");
        SMOOTH_BLACK_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(SMOOTH_BLACK_SANDSTONE)), "smooth_black_sandstone_slab");
        CUT_BLACK_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F)), "cut_black_sandstone");
        CUT_BLACK_SANDSTONE_SLAB = register(func, new SlabBlock(Block.Properties.copy(CUT_BLACK_SANDSTONE)), "cut_black_sandstone_slab");
        CHISELED_BLACK_SANDSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(0.8F)), "chiseled_black_sandstone");

        MapColor tc1 = MapColor.GOLD;
        MapColor tc2 = MapColor.COLOR_YELLOW;
        MapColor tc3 = MapColor.COLOR_ORANGE;
        MapColor tc4 = MapColor.TERRACOTTA_ORANGE;
        MapColor tc5 = MapColor.TERRACOTTA_RED;
        THERMAL_CALCITE = register(func, new ThermalCalciteBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(ThermalCalciteBlock.DISTANCE) == 1 ? tc1 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 2 ? tc2 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 3 ? tc3 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 4 ? tc4 : tc5).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(0.75F)), "thermal_calcite");
        THERMAL_CALCITE_VENT = register(func, new ThermalCalciteVentBlock(BlockBehaviour.Properties.of().mapColor((blockState) -> blockState.getValue(ThermalCalciteBlock.DISTANCE) == 1 ? tc1 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 2 ? tc2 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 3 ? tc3 : blockState.getValue(ThermalCalciteBlock.DISTANCE) == 4 ? tc4 : tc5).instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.CALCITE).requiresCorrectToolForDrops().strength(0.75F)), "thermal_calcite_vent");
        DRIED_SALT = register(func, new DriedSaltBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F).sound(new SoundType(1.0F, 0.5F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE, SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL))), "dried_salt");

        FLESH = register(func, new FleshBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.TERRACOTTA_RED).strength(0.4F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh");
        POROUS_FLESH = register(func, new FleshBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.TERRACOTTA_RED).strength(0.4F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "porous_flesh");
        FLESH_TENDONS = register(func, new FleshTendonsBottomBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_RED).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh_tendons");
        FLESH_TENDONS_STRAND = register(func, new FleshTendonsBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_RED).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh_tendons_strand");
        EYEBULB = register(func, new EyebulbBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_RED).pushReaction(PushReaction.DESTROY).noCollission().strength(0.2F).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL)).offsetType(BlockBehaviour.OffsetType.NONE)), "eyebulb");
        HAIR = register(func, new HairBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.WOOL).offsetType(BlockBehaviour.OffsetType.XYZ)), "hair");
        PUS_BUBBLE = register(func, new PusBubbleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(new SoundType(1.0F, 1.0F, BOPSounds.PUS_BUBBLE_POP, BOPSounds.PUS_BUBBLE_POP, SoundEvents.CORAL_BLOCK_PLACE, BOPSounds.PUS_BUBBLE_POP, BOPSounds.PUS_BUBBLE_POP))), "pus_bubble");

        BRIMSTONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(0.5F)), "brimstone");
        BRIMSTONE_BRICKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(1.0F, 3.0F)), "brimstone_bricks");
        BRIMSTONE_BRICK_STAIRS = register(func, new StairBlock(BRIMSTONE_BRICKS.defaultBlockState(), Block.Properties.copy(BRIMSTONE_BRICKS)), "brimstone_brick_stairs");
        BRIMSTONE_BRICK_SLAB = register(func, new SlabBlock(Block.Properties.copy(BRIMSTONE_BRICKS)), "brimstone_brick_slab");
        BRIMSTONE_BRICK_WALL = register(func, new WallBlock(Block.Properties.copy(BRIMSTONE_BRICKS).forceSolidOn()), "brimstone_brick_wall");
        CHISELED_BRIMSTONE_BRICKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.COLOR_YELLOW).requiresCorrectToolForDrops().strength(1.0F, 3.0F)), "chiseled_brimstone_bricks");
        BRIMSTONE_FUMAROLE = register(func, new BrimstoneFumaroleBlock(BlockBehaviour.Properties.copy(BRIMSTONE)), "brimstone_fumarole");
        BRIMSTONE_CLUSTER = register(func, new BrimstoneClusterBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).pushReaction(PushReaction.DESTROY).strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.NONE)), "brimstone_cluster");
        BRIMSTONE_BUD = register(func, new BrimstoneBudBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).pushReaction(PushReaction.DESTROY).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ)), "brimstone_bud");
        BLACKSTONE_SPINES = register(func, new BlackstoneDecorationBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.DESTROY).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ)), "blackstone_spines");
        BLACKSTONE_BULB = register(func, new BlackstoneDecorationBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).pushReaction(PushReaction.DESTROY).noCollission().strength(0.2F).sound(SoundType.STONE).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "blackstone_bulb");

        ROSE_QUARTZ_BLOCK = register(func, new AmethystBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_STEM).strength(1.5F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops().lightLevel((state) -> 10)), "rose_quartz_block");
        ROSE_QUARTZ_CLUSTER = register(func, new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).forceSolidOn().mapColor(MapColor.CRIMSON_STEM).noOcclusion().randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((state) -> 8)), "rose_quartz_cluster");
        LARGE_ROSE_QUARTZ_BUD = register(func, new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER).pushReaction(PushReaction.DESTROY).forceSolidOn().sound(SoundType.MEDIUM_AMETHYST_BUD).lightLevel((state) -> 7)), "large_rose_quartz_bud");
        MEDIUM_ROSE_QUARTZ_BUD = register(func, new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER).pushReaction(PushReaction.DESTROY).forceSolidOn().sound(SoundType.LARGE_AMETHYST_BUD).lightLevel((state) -> 6)), "medium_rose_quartz_bud");
        SMALL_ROSE_QUARTZ_BUD = register(func, new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.copy(ROSE_QUARTZ_CLUSTER).pushReaction(PushReaction.DESTROY).forceSolidOn().sound(SoundType.SMALL_AMETHYST_BUD).lightLevel((state) -> 5)), "small_rose_quartz_bud");

        BARNACLES = register(func, new BarnaclesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).pushReaction(PushReaction.DESTROY).replaceable().noCollission().sound(SoundType.CORAL_BLOCK)), "barnacles");
        WISPJELLY = register(func, new WispjellyBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.GLOW_LICHEN).noOcclusion().noCollission().sound(SoundType.CORAL_BLOCK).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).strength(0.2F).lightLevel((state) -> 1).emissiveRendering((state, world, pos) -> true)), "wispjelly");
        ALGAL_END_STONE = register(func, new AlgalEndStoneBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.TERRACOTTA_YELLOW).randomTicks().requiresCorrectToolForDrops().strength(3.0F, 9.0F)), "algal_end_stone");
        UNMAPPED_END_STONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0F, 9.0F)), "unmapped_end_stone");
        NULL_END_STONE = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASEDRUM).mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(1.5F, 4.5F)), "null_end_stone");
        NULL_BLOCK = register(func, new NullBlock(BlockBehaviour.Properties.of()), "null_block");
        NULL_LEAVES = register(func, new NullLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).isRedstoneConductor(ModBlocks::never)), "null_leaves");
        NULL_PLANT = register(func, new NullPlantBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).replaceable().noCollission().instabreak().offsetType(BlockBehaviour.OffsetType.XZ)), "null_plant");
        ANOMALY = register(func, new AnomalyBlock(BlockBehaviour.Properties.of().lightLevel((state) -> 5)), "anomaly");

        TOADSTOOL = register(func, new MushroomBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "toadstool");
        TOADSTOOL_BLOCK = register(func, new HugeMushroomBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.WOOD)), "toadstool_block");
        GLOWSHROOM = register(func, new MushroomBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIAMOND).noCollission().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 6).emissiveRendering((state, world, pos) -> true)), "glowshroom");
        GLOWSHROOM_BLOCK = register(func, new HugeMushroomBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIAMOND).strength(0.2F).sound(SoundType.WOOD).lightLevel((state) -> 10).emissiveRendering((state, world, pos) -> true)), "glowshroom_block");
        GLOWING_MOSS_BLOCK = register(func, new GlowingMossBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIAMOND).strength(0.1F).sound(SoundType.MOSS).lightLevel((state) -> 6)), "glowing_moss_block");
        GLOWING_MOSS_CARPET = register(func, new CarpetBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIAMOND).strength(0.1F).sound(SoundType.MOSS_CARPET).lightLevel((state) -> 6)), "glowing_moss_carpet");
        GLOWWORM_SILK = register(func, new GlowwormSilkBottomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "glowworm_silk");
        GLOWWORM_SILK_STRAND = register(func, new GlowwormSilkBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL).lightLevel((state) -> 2).emissiveRendering((state, world, pos) -> true)), "glowworm_silk_strand");

        SPIDER_EGG = register(func, new SpiderEggBlock(BlockBehaviour.Properties.of().strength(0.1F).mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY).sound(SoundType.METAL).lightLevel((state) -> 5)), "spider_egg");
        HANGING_COBWEB = register(func, new HangingCobwebBottomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb");
        HANGING_COBWEB_STRAND = register(func, new HangingCobwebBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL)), "hanging_cobweb_strand");
        STRINGY_COBWEB = register(func, new StringyCobwebBlock(BlockBehaviour.Properties.of().noLootTable().mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL)), "stringy_cobweb");
        WEBBING = register(func, new WebbingBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.WOOL)), "webbing");

        ORIGIN_GRASS_BLOCK = register(func, new OriginGrassBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.GRASS).strength(0.6F).randomTicks().sound(SoundType.GRASS)), "origin_grass_block");

        //Trees
        ORIGIN_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.ORIGIN, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "origin_sapling");
        ORIGIN_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.EMERALD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "origin_leaves");
        FLOWERING_OAK_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.FLOWERING_OAK, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "flowering_oak_sapling");
        FLOWERING_OAK_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "flowering_oak_leaves");
        CYPRESS_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.CYPRESS, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "cypress_sapling");
        CYPRESS_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "cypress_leaves");
        SNOWBLOSSOM_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.SNOWBLOSSOM, BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "snowblossom_sapling");
        SNOWBLOSSOM_LEAVES = register(func, new SnowblossomLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.SNOW).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "snowblossom_leaves");
        RAINBOW_BIRCH_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.RAINBOW_BIRCH, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS).lightLevel((state) -> 5)), "rainbow_birch_sapling");
        RAINBOW_BIRCH_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never).lightLevel((state) -> 8)), "rainbow_birch_leaves");

        FIR_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.FIR, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "fir_sapling");
        FIR_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "fir_leaves");
        FIR_LOG = register(func, log(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.WOOD), "fir_log");
        FIR_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)), "fir_wood");
        STRIPPED_FIR_LOG = register(func, log(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_WHITE, SoundType.WOOD), "stripped_fir_log");
        STRIPPED_FIR_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        FIR_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        FIR_STAIRS = register(func, new StairBlock(FIR_PLANKS.defaultBlockState(), Block.Properties.copy(FIR_PLANKS)), "fir_stairs");
        FIR_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        FIR_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(FIR_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        FIR_FENCE_GATE = register(func, new FenceGateBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(FIR_PLANKS.defaultMapColor()).strength(2.0F, 3.0F), BOPWoodTypes.FIR), "fir_fence_gate");
        FIR_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.FIR, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(FIR_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "fir_door");
        FIR_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.FIR, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_WHITE).strength(3.0F).noOcclusion()), "fir_trapdoor");
        FIR_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.FIR, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(FIR_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "fir_pressure_plate");
        FIR_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.FIR, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "fir_button");
        FIR_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(FIR_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.FIR), "fir_sign");
        FIR_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(FIR_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(FIR_SIGN), BOPWoodTypes.FIR), "fir_wall_sign");
        FIR_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(FIR_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.FIR), "fir_hanging_sign");
        FIR_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(FIR_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(FIR_HANGING_SIGN), BOPWoodTypes.FIR), "fir_wall_hanging_sign");

        PINE_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.PINE, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "pine_sapling");
        PINE_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "pine_leaves");
        PINE_LOG = register(func, log(MapColor.TERRACOTTA_LIGHT_GREEN, MapColor.DIRT, SoundType.WOOD), "pine_log");
        PINE_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(2.0F).sound(SoundType.WOOD)), "pine_wood");
        STRIPPED_PINE_LOG = register(func, log(MapColor.TERRACOTTA_LIGHT_GREEN, MapColor.TERRACOTTA_LIGHT_GREEN, SoundType.WOOD), "stripped_pine_log");
        STRIPPED_PINE_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_pine_wood");
        PINE_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "pine_planks");
        PINE_STAIRS = register(func, new StairBlock(PINE_PLANKS.defaultBlockState(), Block.Properties.copy(PINE_PLANKS)), "pine_stairs");
        PINE_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "pine_slab");
        PINE_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PINE_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "pine_fence");
        PINE_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PINE_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.PINE), "pine_fence_gate");
        PINE_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.PINE, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(PINE_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "pine_door");
        PINE_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.PINE, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).noOcclusion()), "pine_trapdoor");
        PINE_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.PINE, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PINE_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "pine_pressure_plate");
        PINE_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.PINE, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "pine_button");
        PINE_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PINE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.PINE), "pine_sign");
        PINE_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PINE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(PINE_SIGN), BOPWoodTypes.PINE), "pine_wall_sign");
        PINE_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PINE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.PINE), "pine_hanging_sign");
        PINE_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PINE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(PINE_HANGING_SIGN), BOPWoodTypes.PINE), "pine_wall_hanging_sign");

        RED_MAPLE_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.RED_MAPLE, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "red_maple_sapling");
        RED_MAPLE_LEAF_PILE = register(func, new LeafPileBlock(BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_RED).replaceable().noCollission().instabreak().sound(SoundType.CHERRY_LEAVES)), "red_maple_leaf_pile");
        RED_MAPLE_LEAVES = register(func, new RedMapleLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_RED).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "red_maple_leaves");
        ORANGE_MAPLE_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.ORANGE_MAPLE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "orange_maple_sapling");
        ORANGE_MAPLE_LEAF_PILE = register(func, new LeafPileBlock(BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_ORANGE).replaceable().noCollission().instabreak().sound(SoundType.CHERRY_LEAVES)), "orange_maple_leaf_pile");
        ORANGE_MAPLE_LEAVES = register(func, new OrangeMapleLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_ORANGE).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "orange_maple_leaves");
        YELLOW_MAPLE_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.YELLOW_MAPLE, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "yellow_maple_sapling");
        YELLOW_MAPLE_LEAF_PILE = register(func, new LeafPileBlock(BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().noCollission().instabreak().sound(SoundType.CHERRY_LEAVES)), "yellow_maple_leaf_pile");
        YELLOW_MAPLE_LEAVES = register(func, new YellowMapleLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "yellow_maple_leaves");
        MAPLE_LOG = register(func, log(MapColor.DIRT, MapColor.TERRACOTTA_BROWN, SoundType.CHERRY_WOOD), "maple_log");
        MAPLE_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "maple_wood");
        STRIPPED_MAPLE_LOG = register(func, log(MapColor.DIRT, MapColor.DIRT, SoundType.CHERRY_WOOD), "stripped_maple_log");
        STRIPPED_MAPLE_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "stripped_maple_wood");
        MAPLE_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "maple_planks");
        MAPLE_STAIRS = register(func, new StairBlock(MAPLE_PLANKS.defaultBlockState(), Block.Properties.copy(MAPLE_PLANKS)), "maple_stairs");
        MAPLE_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "maple_slab");
        MAPLE_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAPLE_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "maple_fence");
        MAPLE_FENCE_GATE = register(func, new FenceGateBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAPLE_PLANKS.defaultMapColor()).strength(2.0F, 3.0F), BOPWoodTypes.MAPLE), "maple_fence_gate");
        MAPLE_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.MAPLE, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MAPLE_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "maple_door");
        MAPLE_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.MAPLE, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(3.0F).noOcclusion()), "maple_trapdoor");
        MAPLE_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.MAPLE, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAPLE_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "maple_pressure_plate");
        MAPLE_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.MAPLE, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "maple_button");
        MAPLE_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAPLE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.MAPLE), "maple_sign");
        MAPLE_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAPLE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(MAPLE_SIGN), BOPWoodTypes.MAPLE), "maple_wall_sign");
        MAPLE_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAPLE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.MAPLE), "maple_hanging_sign");
        MAPLE_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAPLE_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(MAPLE_HANGING_SIGN), BOPWoodTypes.MAPLE), "maple_wall_hanging_sign");

        REDWOOD_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.REDWOOD, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        REDWOOD_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "redwood_leaves");
        REDWOOD_LOG = register(func, log(MapColor.CRIMSON_NYLIUM, MapColor.TERRACOTTA_ORANGE, SoundType.WOOD), "redwood_log");
        REDWOOD_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        STRIPPED_REDWOOD_LOG = register(func, log(MapColor.CRIMSON_NYLIUM, MapColor.CRIMSON_NYLIUM, SoundType.WOOD), "stripped_redwood_log");
        STRIPPED_REDWOOD_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.CRIMSON_NYLIUM).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        REDWOOD_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.CRIMSON_NYLIUM).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        REDWOOD_STAIRS = register(func, new StairBlock(REDWOOD_PLANKS.defaultBlockState(), Block.Properties.copy(REDWOOD_PLANKS)), "redwood_stairs");
        REDWOOD_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.CRIMSON_NYLIUM).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        REDWOOD_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(REDWOOD_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        REDWOOD_FENCE_GATE = register(func, new FenceGateBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(REDWOOD_PLANKS.defaultMapColor()).strength(2.0F, 3.0F), BOPWoodTypes.REDWOOD), "redwood_fence_gate");
        REDWOOD_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.REDWOOD, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(REDWOOD_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "redwood_door");
        REDWOOD_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.REDWOOD, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.CRIMSON_NYLIUM).strength(3.0F).noOcclusion()), "redwood_trapdoor");
        REDWOOD_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.REDWOOD, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(REDWOOD_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "redwood_pressure_plate");
        REDWOOD_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.REDWOOD, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "redwood_button");
        REDWOOD_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(REDWOOD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.REDWOOD), "redwood_sign");
        REDWOOD_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(REDWOOD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(REDWOOD_SIGN), BOPWoodTypes.REDWOOD), "redwood_wall_sign");
        REDWOOD_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(REDWOOD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.REDWOOD), "redwood_hanging_sign");
        REDWOOD_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(REDWOOD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(REDWOOD_HANGING_SIGN), BOPWoodTypes.REDWOOD), "redwood_wall_hanging_sign");

        MAHOGANY_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.MAHOGANY, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "mahogany_sapling");
        MAHOGANY_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "mahogany_leaves");
        MAHOGANY_LOG = register(func, log(MapColor.TERRACOTTA_MAGENTA, MapColor.DIRT, SoundType.WOOD), "mahogany_log");
        MAHOGANY_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.DIRT).strength(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        STRIPPED_MAHOGANY_LOG = register(func, log(MapColor.TERRACOTTA_MAGENTA, MapColor.TERRACOTTA_MAGENTA, SoundType.WOOD), "stripped_mahogany_log");
        STRIPPED_MAHOGANY_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        MAHOGANY_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        MAHOGANY_STAIRS = register(func, new StairBlock(MAHOGANY_PLANKS.defaultBlockState(), Block.Properties.copy(MAHOGANY_PLANKS)), "mahogany_stairs");
        MAHOGANY_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        MAHOGANY_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAHOGANY_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        MAHOGANY_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAHOGANY_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.MAHOGANY), "mahogany_fence_gate");
        MAHOGANY_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.MAHOGANY, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MAHOGANY_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "mahogany_door");
        MAHOGANY_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.MAHOGANY, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_MAGENTA).strength(3.0F).noOcclusion()), "mahogany_trapdoor");
        MAHOGANY_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.MAHOGANY, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAHOGANY_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "mahogany_pressure_plate");
        MAHOGANY_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.MAHOGANY, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "mahogany_button");
        MAHOGANY_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAHOGANY_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.MAHOGANY), "mahogany_sign");
        MAHOGANY_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAHOGANY_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(MAHOGANY_SIGN), BOPWoodTypes.MAHOGANY), "mahogany_wall_sign");
        MAHOGANY_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAHOGANY_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.MAHOGANY), "mahogany_hanging_sign");
        MAHOGANY_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAHOGANY_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(MAHOGANY_HANGING_SIGN), BOPWoodTypes.MAHOGANY), "mahogany_wall_hanging_sign");

        JACARANDA_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.JACARANDA, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "jacaranda_sapling");
        JACARANDA_LEAVES = register(func, new JacarandaLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PURPLE).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "jacaranda_leaves");
        JACARANDA_LOG = register(func, log(MapColor.QUARTZ, MapColor.TERRACOTTA_LIGHT_GRAY, SoundType.CHERRY_WOOD), "jacaranda_log");
        JACARANDA_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "jacaranda_wood");
        STRIPPED_JACARANDA_LOG = register(func, log(MapColor.QUARTZ, MapColor.QUARTZ, SoundType.CHERRY_WOOD), "stripped_jacaranda_log");
        STRIPPED_JACARANDA_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.QUARTZ).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "stripped_jacaranda_wood");
        JACARANDA_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.QUARTZ).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "jacaranda_planks");
        JACARANDA_STAIRS = register(func, new StairBlock(JACARANDA_PLANKS.defaultBlockState(), Block.Properties.copy(JACARANDA_PLANKS)), "jacaranda_stairs");
        JACARANDA_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.QUARTZ).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "jacaranda_slab");
        JACARANDA_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(JACARANDA_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "jacaranda_fence");
        JACARANDA_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(JACARANDA_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.JACARANDA), "jacaranda_fence_gate");
        JACARANDA_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.JACARANDA, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(JACARANDA_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "jacaranda_door");
        JACARANDA_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.JACARANDA, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.QUARTZ).strength(3.0F).noOcclusion()), "jacaranda_trapdoor");
        JACARANDA_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.JACARANDA, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(JACARANDA_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "jacaranda_pressure_plate");
        JACARANDA_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.JACARANDA, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "jacaranda_button");
        JACARANDA_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(JACARANDA_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.JACARANDA), "jacaranda_sign");
        JACARANDA_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(JACARANDA_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(JACARANDA_SIGN), BOPWoodTypes.JACARANDA), "jacaranda_wall_sign");
        JACARANDA_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(JACARANDA_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.JACARANDA), "jacaranda_hanging_sign");
        JACARANDA_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(JACARANDA_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(JACARANDA_HANGING_SIGN), BOPWoodTypes.JACARANDA), "jacaranda_wall_hanging_sign");

        PALM_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.PALM, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "palm_sapling");
        PALM_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "palm_leaves");
        PALM_LOG = register(func, log(MapColor.TERRACOTTA_YELLOW, MapColor.PODZOL, SoundType.WOOD), "palm_log");
        PALM_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), "palm_wood");
        STRIPPED_PALM_LOG = register(func, log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, SoundType.WOOD), "stripped_palm_log");
        STRIPPED_PALM_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        PALM_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        PALM_STAIRS = register(func, new StairBlock(PALM_PLANKS.defaultBlockState(), Block.Properties.copy(PALM_PLANKS)), "palm_stairs");
        PALM_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        PALM_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PALM_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        PALM_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PALM_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.PALM), "palm_fence_gate");
        PALM_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.PALM, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(PALM_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "palm_door");
        PALM_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.PALM, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_YELLOW).strength(3.0F).noOcclusion()), "palm_trapdoor");
        PALM_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.PALM, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(PALM_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "palm_pressure_plate");
        PALM_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.PALM, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "palm_button");
        PALM_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PALM_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.PALM), "palm_sign");
        PALM_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PALM_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(PALM_SIGN), BOPWoodTypes.PALM), "palm_wall_sign");
        PALM_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PALM_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.PALM), "palm_hanging_sign");
        PALM_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(PALM_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(PALM_HANGING_SIGN), BOPWoodTypes.PALM), "palm_wall_hanging_sign");

        WILLOW_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.WILLOW, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "willow_sapling");
        WILLOW_VINE = register(func, new VineBlock(BlockBehaviour.Properties.of().randomTicks().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");
        SPANISH_MOSS = register(func, new SpanishMossBottomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().randomTicks().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss");
        SPANISH_MOSS_PLANT = register(func, new SpanishMossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS)), "spanish_moss_plant");
        WILLOW_LEAVES = register(func, new WillowLeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "willow_leaves");
        WILLOW_LOG = register(func, log(MapColor.TERRACOTTA_LIGHT_GREEN, MapColor.TERRACOTTA_LIGHT_GREEN, SoundType.WOOD), "willow_log");
        WILLOW_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_wood");
        STRIPPED_WILLOW_LOG = register(func, log(MapColor.TERRACOTTA_LIGHT_GREEN, MapColor.TERRACOTTA_LIGHT_GREEN, SoundType.WOOD), "stripped_willow_log");
        STRIPPED_WILLOW_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        WILLOW_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        WILLOW_STAIRS = register(func, new StairBlock(WILLOW_PLANKS.defaultBlockState(), Block.Properties.copy(WILLOW_PLANKS)), "willow_stairs");
        WILLOW_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        WILLOW_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(WILLOW_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        WILLOW_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(WILLOW_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.WILLOW), "willow_fence_gate");
        WILLOW_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.WILLOW, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(WILLOW_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "willow_door");
        WILLOW_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.WILLOW, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).noOcclusion()), "willow_trapdoor");
        WILLOW_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.WILLOW, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(WILLOW_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "willow_pressure_plate");
        WILLOW_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.WILLOW, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "willow_button");
        WILLOW_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(WILLOW_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.WILLOW), "willow_sign");
        WILLOW_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(WILLOW_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(WILLOW_SIGN), BOPWoodTypes.WILLOW), "willow_wall_sign");
        WILLOW_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(WILLOW_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.WILLOW), "willow_hanging_sign");
        WILLOW_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(WILLOW_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(WILLOW_HANGING_SIGN), BOPWoodTypes.WILLOW), "willow_wall_hanging_sign");

        DEAD_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.DEAD, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "dead_sapling");
        DEAD_BRANCH = register(func, new DeadBranchBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_GRAY).ignitedByLava().noCollission().instabreak().sound(SoundType.WOOD)), "dead_branch");
        DEAD_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.WOOD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "dead_leaves");
        DEAD_LOG = register(func, log(MapColor.STONE, MapColor.COLOR_GRAY, SoundType.WOOD), "dead_log");
        DEAD_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)), "dead_wood");
        STRIPPED_DEAD_LOG = register(func, log(MapColor.STONE, MapColor.STONE, SoundType.WOOD), "stripped_dead_log");
        STRIPPED_DEAD_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        DEAD_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        DEAD_STAIRS = register(func, new StairBlock(DEAD_PLANKS.defaultBlockState(), Block.Properties.copy(DEAD_PLANKS)), "dead_stairs");
        DEAD_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        DEAD_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(DEAD_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        DEAD_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(DEAD_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.DEAD), "dead_fence_gate");
        DEAD_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.DEAD, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(DEAD_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "dead_door");
        DEAD_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.DEAD, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.STONE).strength(3.0F).noOcclusion()), "dead_trapdoor");
        DEAD_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.DEAD, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(DEAD_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "dead_pressure_plate");
        DEAD_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.DEAD, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "dead_button");
        DEAD_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(DEAD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.DEAD), "dead_sign");
        DEAD_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(DEAD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(DEAD_SIGN), BOPWoodTypes.DEAD), "dead_wall_sign");
        DEAD_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(DEAD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.DEAD), "dead_hanging_sign");
        DEAD_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(DEAD_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(DEAD_HANGING_SIGN), BOPWoodTypes.DEAD), "dead_wall_hanging_sign");

        MAGIC_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.MAGIC, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.CHERRY_SAPLING)), "magic_sapling");
        MAGIC_LEAVES = register(func, new MagicLeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.CHERRY_LEAVES).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "magic_leaves");
        MAGIC_LOG = register(func, log(MapColor.COLOR_BLUE, MapColor.TERRACOTTA_LIGHT_BLUE, SoundType.CHERRY_WOOD), "magic_log");
        MAGIC_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "magic_wood");
        STRIPPED_MAGIC_LOG = register(func, log(MapColor.COLOR_BLUE, MapColor.COLOR_BLUE, SoundType.CHERRY_WOOD), "stripped_magic_log");
        STRIPPED_MAGIC_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_BLUE).strength(2.0F).sound(SoundType.CHERRY_WOOD)), "stripped_magic_wood");
        MAGIC_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "magic_planks");
        MAGIC_STAIRS = register(func, new StairBlock(MAGIC_PLANKS.defaultBlockState(), Block.Properties.copy(MAGIC_PLANKS)), "magic_stairs");
        MAGIC_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "magic_slab");
        MAGIC_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAGIC_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.CHERRY_WOOD)), "magic_fence");
        MAGIC_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAGIC_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.MAGIC), "magic_fence_gate");
        MAGIC_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.MAGIC, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MAGIC_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "magic_door");
        MAGIC_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.MAGIC, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_BLUE).strength(3.0F).noOcclusion()), "magic_trapdoor");
        MAGIC_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.MAGIC, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(MAGIC_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "magic_pressure_plate");
        MAGIC_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.MAGIC, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "magic_button");
        MAGIC_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAGIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().forceSolidOn().strength(1.0F), BOPWoodTypes.MAGIC), "magic_sign");
        MAGIC_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAGIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().noCollission().forceSolidOn().strength(1.0F).dropsLike(MAGIC_SIGN), BOPWoodTypes.MAGIC), "magic_wall_sign");
        MAGIC_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAGIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.MAGIC), "magic_hanging_sign");
        MAGIC_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(MAGIC_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(MAGIC_HANGING_SIGN), BOPWoodTypes.MAGIC), "magic_wall_hanging_sign");

        UMBRAN_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.UMBRAN, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "umbran_sapling");
        UMBRAN_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_BLUE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "umbran_leaves");
        UMBRAN_LOG = register(func, log(MapColor.TERRACOTTA_BLUE, MapColor.TERRACOTTA_BLUE, SoundType.NETHER_WOOD), "umbran_log");
        UMBRAN_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.NETHER_WOOD)), "umbran_wood");
        STRIPPED_UMBRAN_LOG = register(func, log(MapColor.TERRACOTTA_BLUE, MapColor.TERRACOTTA_BLUE, SoundType.NETHER_WOOD), "stripped_umbran_log");
        STRIPPED_UMBRAN_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.NETHER_WOOD)), "stripped_umbran_wood");
        UMBRAN_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "umbran_planks");
        UMBRAN_STAIRS = register(func, new StairBlock(UMBRAN_PLANKS.defaultBlockState(), Block.Properties.copy(UMBRAN_PLANKS)), "umbran_stairs");
        UMBRAN_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "umbran_slab");
        UMBRAN_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(UMBRAN_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "umbran_fence");
        UMBRAN_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(UMBRAN_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.UMBRAN), "umbran_fence_gate");
        UMBRAN_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.UMBRAN, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(UMBRAN_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "umbran_door");
        UMBRAN_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.UMBRAN, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.TERRACOTTA_BLUE).strength(3.0F).noOcclusion()), "umbran_trapdoor");
        UMBRAN_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.UMBRAN, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(UMBRAN_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "umbran_pressure_plate");
        UMBRAN_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.UMBRAN, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "umbran_button");
        UMBRAN_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(UMBRAN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.UMBRAN), "umbran_sign");
        UMBRAN_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(UMBRAN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(UMBRAN_SIGN), BOPWoodTypes.UMBRAN), "umbran_wall_sign");
        UMBRAN_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(UMBRAN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.UMBRAN), "umbran_hanging_sign");
        UMBRAN_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(UMBRAN_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(UMBRAN_HANGING_SIGN), BOPWoodTypes.UMBRAN), "umbran_wall_hanging_sign");

        HELLBARK_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.HELLBARK, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "hellbark_sapling");
        HELLBARK_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).isRedstoneConductor(ModBlocks::never)), "hellbark_leaves");
        HELLBARK_LOG = register(func, logNonIgniting(MapColor.TERRACOTTA_GRAY, MapColor.COLOR_LIGHT_GRAY, SoundType.NETHER_WOOD), "hellbark_log");
        HELLBARK_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.NETHER_WOOD)), "hellbark_wood");
        STRIPPED_HELLBARK_LOG = register(func, logNonIgniting(MapColor.TERRACOTTA_GRAY, MapColor.TERRACOTTA_GRAY, SoundType.NETHER_WOOD), "stripped_hellbark_log");
        STRIPPED_HELLBARK_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.NETHER_WOOD)), "stripped_hellbark_wood");
        HELLBARK_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "hellbark_planks");
        HELLBARK_STAIRS = register(func, new StairBlock(HELLBARK_PLANKS.defaultBlockState(), Block.Properties.copy(HELLBARK_PLANKS)), "hellbark_stairs");
        HELLBARK_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "hellbark_slab");
        HELLBARK_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).forceSolidOn().mapColor(HELLBARK_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "hellbark_fence");
        HELLBARK_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).forceSolidOn().mapColor(HELLBARK_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.HELLBARK), "hellbark_fence_gate");
        HELLBARK_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.HELLBARK, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).mapColor(HELLBARK_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "hellbark_door");
        HELLBARK_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.HELLBARK, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor(MapColor.TERRACOTTA_GRAY).strength(3.0F).noOcclusion()), "hellbark_trapdoor");
        HELLBARK_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.HELLBARK, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).forceSolidOn().mapColor(HELLBARK_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "hellbark_pressure_plate");
        HELLBARK_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.HELLBARK, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "hellbark_button");
        HELLBARK_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(HELLBARK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.HELLBARK), "hellbark_sign");
        HELLBARK_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(HELLBARK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1.0F).dropsLike(HELLBARK_SIGN), BOPWoodTypes.HELLBARK), "hellbark_wall_sign");
        HELLBARK_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(HELLBARK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.HELLBARK), "hellbark_hanging_sign");
        HELLBARK_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(HELLBARK_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).forceSolidOn().noCollission().strength(1.0F).dropsLike(HELLBARK_HANGING_SIGN), BOPWoodTypes.HELLBARK), "hellbark_wall_hanging_sign");

        EMPYREAL_SAPLING = register(func, new SaplingBlockBOP(BOPTreeGrowers.EMPYREAL, BlockBehaviour.Properties.of().mapColor(MapColor.SAND).pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "empyreal_sapling");
        EMPYREAL_LEAVES = register(func, new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).pushReaction(PushReaction.DESTROY).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(ModBlocks::ocelotOrParrot).isSuffocating(ModBlocks::never).isViewBlocking(ModBlocks::never).ignitedByLava().isRedstoneConductor(ModBlocks::never)), "empyreal_leaves");
        EMPYREAL_LOG = register(func, log(MapColor.COLOR_PURPLE, MapColor.QUARTZ, SoundType.NETHER_WOOD), "empyreal_log");
        EMPYREAL_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.QUARTZ).strength(2.0F).sound(SoundType.NETHER_WOOD)), "empyreal_wood");
        STRIPPED_EMPYREAL_LOG = register(func, log(MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE, SoundType.NETHER_WOOD), "stripped_empyreal_log");
        STRIPPED_EMPYREAL_WOOD = register(func, new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.NETHER_WOOD)), "stripped_empyreal_wood");
        EMPYREAL_PLANKS = register(func, new Block(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "empyreal_planks");
        EMPYREAL_STAIRS = register(func, new StairBlock(EMPYREAL_PLANKS.defaultBlockState(), Block.Properties.copy(EMPYREAL_PLANKS)), "empyreal_stairs");
        EMPYREAL_SLAB = register(func, new SlabBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "empyreal_slab");
        EMPYREAL_FENCE = register(func, new FenceBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(EMPYREAL_PLANKS.defaultMapColor()).strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)), "empyreal_fence");
        EMPYREAL_FENCE_GATE = register(func, new FenceGateBlock(
            BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(EMPYREAL_PLANKS.defaultMapColor()).strength(2.0F, 3.0F),
            BOPWoodTypes.EMPYREAL), "empyreal_fence_gate");
        EMPYREAL_DOOR = register(func, new DoorBlockBOP(BOPBlockSetTypes.EMPYREAL, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(EMPYREAL_PLANKS.defaultMapColor()).strength(3.0F).noOcclusion()), "empyreal_door");
        EMPYREAL_TRAPDOOR = register(func, new TrapDoorBlockBOP(BOPBlockSetTypes.EMPYREAL, BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor(MapColor.COLOR_PURPLE).strength(3.0F).noOcclusion()), "empyreal_trapdoor");
        EMPYREAL_PRESSURE_PLATE = register(func, new PressurePlateBlockBOP(BOPBlockSetTypes.EMPYREAL, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().mapColor(EMPYREAL_PLANKS.defaultMapColor()).noCollission().strength(0.5F), Sensitivity.EVERYTHING), "empyreal_pressure_plate");
        EMPYREAL_BUTTON = register(func, new ButtonBlockBOP(BOPBlockSetTypes.EMPYREAL, 30, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F)), "empyreal_button");
        EMPYREAL_SIGN = register(func, new StandingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(EMPYREAL_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.EMPYREAL), "empyreal_sign");
        EMPYREAL_WALL_SIGN = register(func, new WallSignBlockBOP(BlockBehaviour.Properties.of().mapColor(EMPYREAL_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(EMPYREAL_SIGN), BOPWoodTypes.EMPYREAL), "empyreal_wall_sign");
        EMPYREAL_HANGING_SIGN = register(func, new CeilingHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(EMPYREAL_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F), BOPWoodTypes.EMPYREAL), "empyreal_hanging_sign");
        EMPYREAL_WALL_HANGING_SIGN = register(func, new WallHangingSignBlockBOP(BlockBehaviour.Properties.of().mapColor(EMPYREAL_PLANKS.defaultMapColor()).instrument(NoteBlockInstrument.BASS).ignitedByLava().forceSolidOn().noCollission().strength(1.0F).dropsLike(EMPYREAL_HANGING_SIGN), BOPWoodTypes.EMPYREAL), "empyreal_wall_hanging_sign");

        //Flowers
        ROSE = register(func, new FlowerBlockBOP(MobEffects.MOVEMENT_SPEED, 7, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "rose");
        VIOLET = register(func, new FlowerBlockBOP(MobEffects.CONFUSION, 10, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "violet");
        LAVENDER = register(func, new FlowerBlockBOP(MobEffects.HEALTH_BOOST, 5, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_MAGENTA).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "lavender");
        TALL_LAVENDER = register(func, new TallFlowerBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "tall_lavender");
        WHITE_LAVENDER = register(func, new FlowerBlockBOP(MobEffects.HEALTH_BOOST, 5, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.SNOW).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "white_lavender");
        TALL_WHITE_LAVENDER = register(func, new TallFlowerBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "tall_white_lavender");
        BLUE_HYDRANGEA = register(func, new TallFlowerBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "blue_hydrangea");
        GOLDENROD = register(func, new TallFlowerBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "goldenrod");
        ORANGE_COSMOS = register(func, new FlowerBlockBOP(MobEffects.ABSORPTION, 7, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "orange_cosmos");
        PINK_DAFFODIL = register(func, new FlowerBlockBOP(MobEffects.INVISIBILITY, 7, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "pink_daffodil");
        PINK_HIBISCUS = register(func, new FlowerBlockBOP(MobEffects.REGENERATION, 5, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "pink_hibiscus");
        WILDFLOWER = register(func, new WildflowerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)), "wildflower");
        WHITE_PETALS = register(func, new PinkPetalsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)), "white_petals");
        ICY_IRIS = register(func, new TallFlowerBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "icy_iris");
        GLOWFLOWER = register(func, new FlowerBlockBOP(MobEffects.GLOWING, 10, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 9)), "glowflower");
        WILTED_LILY = register(func, new FlowerBlockBOP(MobEffects.UNLUCK, 5, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "wilted_lily");
        BURNING_BLOSSOM = register(func, new FlowerBlockBOP(MobEffects.FIRE_RESISTANCE, 7, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).lightLevel((state) -> 7)), "burning_blossom");
        ENDBLOOM = register(func, new FlowerBlockBOP(MobEffects.WATER_BREATHING, 5, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).pushReaction(PushReaction.DESTROY).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "endbloom");

        //Plants
        SPROUT = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "sprout");
        BUSH = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "bush");
        HIGH_GRASS = register(func, new HighGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).randomTicks().replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "high_grass");
        HIGH_GRASS_PLANT = register(func, new HighGrassPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "high_grass_plant");
        CLOVER = register(func, new CloverBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)), "clover");
        HUGE_CLOVER_PETAL = register(func, new HugeCloverPetalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).ignitedByLava().instabreak().sound(SoundType.PINK_PETALS)), "huge_clover_petal");
        HUGE_LILY_PAD = register(func, new HugeLilyPadBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).pushReaction(PushReaction.DESTROY).instabreak().sound(SoundType.LILY_PAD).noOcclusion()), "huge_lily_pad");
        WATERLILY = register(func, new WaterlilyBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.COLOR_PINK).noOcclusion().noCollission().instabreak().sound(SoundType.LILY_PAD).offsetType(BlockBehaviour.OffsetType.XZ)), "waterlily");

        DUNE_GRASS = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "dune_grass");
        DESERT_GRASS = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_ORANGE).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "desert_grass");
        DEAD_GRASS = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.WOOD).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "dead_grass");
        TUNDRA_SHRUB = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.PLANT).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "tundra_shrub");
        ENDERPHYTE = register(func, new FoliageBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_YELLOW).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ)), "enderphyte");
        LUMALOOP = register(func, new LumaloopBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).pushReaction(PushReaction.DESTROY).randomTicks().replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).lightLevel(LumaloopBlock.lightLevel(10)).offsetType(BlockBehaviour.OffsetType.XZ)), "lumaloop");
        LUMALOOP_PLANT = register(func, new LumaloopPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).pushReaction(PushReaction.DESTROY).replaceable().ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).lightLevel(LumaloopPlantBlock.lightLevel(10)).offsetType(BlockBehaviour.OffsetType.XZ)), "lumaloop_plant");
        BARLEY = register(func, new DoublePlantBlockBOP(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.TERRACOTTA_YELLOW).ignitedByLava().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "barley");
        SEA_OATS = register(func, new SeaOatsBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.SAND).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "sea_oats");
        CATTAIL = register(func, new DoubleWatersidePlantBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "cattail");
        REED = register(func, new DoubleWaterPlantBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.DIRT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "reed");
        WATERGRASS = register(func, new DoubleWaterPlantBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)), "watergrass");

        TINY_CACTUS = register(func, new TinyCactusBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WOOL).offsetType(BlockBehaviour.OffsetType.XZ)), "tiny_cactus");
        BRAMBLE = register(func, new BrambleBlock(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).mapColor(MapColor.NETHER).strength(0.4F).sound(SoundType.WOOD)), "bramble");
        BRAMBLE_LEAVES = register(func, new BrambleLeavesBlock(BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).mapColor(MapColor.PLANT).instabreak().sound(SoundType.GRASS)), "bramble_leaves");

        //Potted Plants
        POTTED_ORIGIN_SAPLING = register(func, new FlowerPotBlock(ORIGIN_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_origin_sapling");
        POTTED_FLOWERING_OAK_SAPLING = register(func, new FlowerPotBlock(FLOWERING_OAK_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_flowering_oak_sapling");
        POTTED_CYPRESS_SAPLING = register(func, new FlowerPotBlock(CYPRESS_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_cypress_sapling");
        POTTED_SNOWBLOSSOM_SAPLING = register(func, new FlowerPotBlock(SNOWBLOSSOM_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_snowblossom_sapling");
        POTTED_RAINBOW_BIRCH_SAPLING = register(func, new FlowerPotBlock(RAINBOW_BIRCH_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak().lightLevel((state) -> 5)), "potted_rainbow_birch_sapling");
        POTTED_FIR_SAPLING = register(func, new FlowerPotBlock(FIR_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_fir_sapling");
        POTTED_PINE_SAPLING = register(func, new FlowerPotBlock(PINE_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_pine_sapling");
        POTTED_RED_MAPLE_SAPLING = register(func, new FlowerPotBlock(RED_MAPLE_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_red_maple_sapling");
        POTTED_ORANGE_MAPLE_SAPLING = register(func, new FlowerPotBlock(ORANGE_MAPLE_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_orange_maple_sapling");
        POTTED_YELLOW_MAPLE_SAPLING = register(func, new FlowerPotBlock(YELLOW_MAPLE_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_yellow_maple_sapling");
        POTTED_REDWOOD_SAPLING = register(func, new FlowerPotBlock(REDWOOD_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_redwood_sapling");
        POTTED_MAHOGANY_SAPLING = register(func, new FlowerPotBlock(MAHOGANY_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_mahogany_sapling");
        POTTED_JACARANDA_SAPLING = register(func, new FlowerPotBlock(JACARANDA_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_jacaranda_sapling");
        POTTED_PALM_SAPLING = register(func, new FlowerPotBlock(PALM_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_palm_sapling");
        POTTED_WILLOW_SAPLING = register(func, new FlowerPotBlock(WILLOW_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_willow_sapling");
        POTTED_DEAD_SAPLING = register(func, new FlowerPotBlock(DEAD_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_dead_sapling");
        POTTED_MAGIC_SAPLING = register(func, new FlowerPotBlock(MAGIC_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_magic_sapling");
        POTTED_UMBRAN_SAPLING = register(func, new FlowerPotBlock(UMBRAN_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_umbran_sapling");
        POTTED_HELLBARK_SAPLING = register(func, new FlowerPotBlock(HELLBARK_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_hellbark_sapling");
        POTTED_EMPYREAL_SAPLING = register(func, new FlowerPotBlock(EMPYREAL_SAPLING, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_empyreal_sapling");
        POTTED_ROSE = register(func, new FlowerPotBlock(ROSE, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_rose");
        POTTED_VIOLET = register(func, new FlowerPotBlock(VIOLET, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_violet");
        POTTED_LAVENDER = register(func, new FlowerPotBlock(LAVENDER, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_lavender");
        POTTED_WHITE_LAVENDER = register(func, new FlowerPotBlock(WHITE_LAVENDER, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_white_lavender");
        POTTED_ORANGE_COSMOS = register(func, new FlowerPotBlock(ORANGE_COSMOS, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_orange_cosmos");
        POTTED_PINK_DAFFODIL = register(func, new FlowerPotBlock(PINK_DAFFODIL, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_pink_daffodil");
        POTTED_PINK_HIBISCUS = register(func, new FlowerPotBlock(PINK_HIBISCUS, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_pink_hibiscus");
        POTTED_GLOWFLOWER = register(func, new FlowerPotBlock(GLOWFLOWER, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak().lightLevel((state) -> 9)), "potted_glowflower");
        POTTED_WILTED_LILY = register(func, new FlowerPotBlock(WILTED_LILY, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_wilted_lily");
        POTTED_BURNING_BLOSSOM = register(func, new FlowerPotBlock(BURNING_BLOSSOM, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak().lightLevel((state) -> 7)), "potted_burning_blossom");
        POTTED_ENDBLOOM = register(func, new FlowerPotBlock(ENDBLOOM, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_endbloom");
        POTTED_SPROUT = register(func, new FlowerPotBlock(SPROUT, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_sprout");
        POTTED_TINY_CACTUS = register(func, new FlowerPotBlock(TINY_CACTUS, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_tiny_cactus");
        POTTED_TOADSTOOL = register(func, new FlowerPotBlock(TOADSTOOL, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak()), "potted_toadstool");
        POTTED_GLOWSHROOM = register(func, new FlowerPotBlock(GLOWSHROOM, BlockBehaviour.Properties.of().noOcclusion().pushReaction(PushReaction.DESTROY).instabreak().lightLevel((state) -> 6).emissiveRendering((state, world, pos) -> true)), "potted_glowshroom");
    }

    private static RotatedPillarBlock log(MapColor MapColor, MapColor MapColor2, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).ignitedByLava().mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(soundType));
    }

    private static RotatedPillarBlock logNonIgniting(MapColor MapColor, MapColor MapColor2, SoundType soundType) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.BASS).mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor : MapColor2).strength(2.0F).sound(soundType));
    }

    private static Block register(BiConsumer<ResourceLocation, Block> func, Block block, String name)
    {
        func.accept(new ResourceLocation(BiomesOPlenty.MOD_ID, name), block);
        return block;
    }

    private static Item register(BiConsumer<ResourceLocation, Item> func, String name, Item item)
    {
        func.accept(new ResourceLocation(BiomesOPlenty.MOD_ID, name), item);
        return item;
    }

    private static Boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)true;
    }

    private static Boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return (boolean)false;
    }

    private static Boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return (boolean)(p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT);
    }
}
