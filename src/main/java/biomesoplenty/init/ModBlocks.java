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
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;

import static biomesoplenty.api.block.BOPBlocks.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        //Terrain
    	white_sand = registerBlock(new WhiteSandBlock(0xF3F1E4, Block.Properties.of(Material.SAND, MaterialColor.QUARTZ).strength(0.5F).sound(SoundType.SAND).harvestLevel(0).harvestTool(ToolType.SHOVEL)), "white_sand");
    	white_sandstone = registerBlock(new Block(Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "white_sandstone");
        white_sandstone_stairs = registerBlock(new StairsBlock(white_sandstone.defaultBlockState(), Block.Properties.copy(white_sandstone)), "white_sandstone_stairs");
    	white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(white_sandstone)), "white_sandstone_slab");
        white_sandstone_wall = registerBlock(new WallBlock(Block.Properties.copy(white_sandstone)),"white_sandstone_wall");
        smooth_white_sandstone = registerBlock(new Block(Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(2.0F, 6.0F)), "smooth_white_sandstone");
        smooth_white_sandstone_stairs = registerBlock(new StairsBlock(white_sandstone.defaultBlockState(), Block.Properties.copy(smooth_white_sandstone)), "smooth_white_sandstone_stairs");
        smooth_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(smooth_white_sandstone)), "smooth_white_sandstone_slab");
    	cut_white_sandstone = registerBlock(new Block(Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "cut_white_sandstone");
        cut_white_sandstone_slab = registerBlock(new SlabBlock(Block.Properties.copy(cut_white_sandstone)), "cut_white_sandstone_slab");
        chiseled_white_sandstone = registerBlock(new Block(Block.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8F)), "chiseled_white_sandstone");

        mud = registerBlock(new MudBlock(Block.Properties.of(Material.DIRT, MaterialColor.TERRACOTTA_BROWN).strength(0.6F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(new SoundType(1.0F, 0.5F, SoundEvents.SLIME_BLOCK_BREAK, SoundEvents.SLIME_BLOCK_STEP, SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_HIT, SoundEvents.SLIME_BLOCK_FALL))), "mud");
        mud_bricks = registerBlock(new Block(Block.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).strength(1.0F)), "mud_bricks");
        mud_brick_stairs = registerBlock(new StairsBlock(mud_bricks.defaultBlockState(), Block.Properties.copy(mud_bricks)), "mud_brick_stairs");
        mud_brick_slab = registerBlock(new SlabBlock(Block.Properties.copy(mud_bricks)), "mud_brick_slab");
        mud_brick_wall = registerBlock(new WallBlock(Block.Properties.copy(mud_bricks)),"mud_brick_wall");

        dried_salt = registerBlock(new DriedSaltBlock(Block.Properties.of(Material.STONE, MaterialColor.WOOD).strength(1.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.GRAVEL_BREAK, SoundEvents.GRAVEL_STEP, SoundEvents.GRAVEL_PLACE, SoundEvents.GRAVEL_HIT, SoundEvents.GRAVEL_FALL))), "dried_salt");
        ash_block = registerBlock(new AshBlock(Block.Properties.of(Material.SAND, MaterialColor.TERRACOTTA_BLACK).strength(0.4F).harvestLevel(0).harvestTool(ToolType.SHOVEL).sound(SoundType.SAND)), "ash_block");
        flesh = registerBlock(new FleshBlock(Block.Properties.of(Material.SPONGE, MaterialColor.TERRACOTTA_RED).strength(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(new SoundType(1.0F, 0.5F, SoundEvents.CORAL_BLOCK_BREAK, SoundEvents.CORAL_BLOCK_STEP, SoundEvents.CORAL_BLOCK_PLACE, SoundEvents.CORAL_BLOCK_HIT, SoundEvents.CORAL_BLOCK_FALL))), "flesh");

        //Trees
        origin_sapling = registerBlock(new SaplingBlockBOP(new OriginTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "origin_sapling");
        origin_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.EMERALD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "origin_leaves");
        flowering_oak_sapling = registerBlock(new SaplingBlockBOP(new FloweringOakTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "flowering_oak_sapling");
        flowering_oak_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "flowering_oak_leaves");
        rainbow_birch_sapling = registerBlock(new SaplingBlockBOP(new RainbowBirchTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "rainbow_birch_sapling");
        rainbow_birch_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "rainbow_birch_leaves");
        yellow_autumn_sapling = registerBlock(new SaplingBlockBOP(new YellowAutumnTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "yellow_autumn_sapling");
        yellow_autumn_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "yellow_autumn_leaves");
        orange_autumn_sapling = registerBlock(new SaplingBlockBOP(new OrangeAutumnTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "orange_autumn_sapling");
        orange_autumn_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_ORANGE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "orange_autumn_leaves");
        maple_sapling = registerBlock(new SaplingBlockBOP(new MapleTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "maple_sapling");
        maple_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.COLOR_RED).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "maple_leaves");

        fir_sapling = registerBlock(new SaplingBlockBOP(new FirTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "fir_sapling");
        fir_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "fir_leaves");
        fir_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_WHITE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)), "fir_log");
        fir_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "fir_wood");
        stripped_fir_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_WHITE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_log");
        stripped_fir_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        fir_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        fir_stairs = registerBlock(new StairsBlock(fir_planks.defaultBlockState(), Block.Properties.copy(fir_planks)), "fir_stairs");
        fir_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        fir_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, fir_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        fir_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, fir_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        fir_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, fir_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_door");
        fir_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "fir_trapdoor");
        fir_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, fir_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        fir_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "fir_button");
        
        redwood_sapling = registerBlock(new SaplingBlockBOP(new RedwoodTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "redwood_sapling");
        redwood_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "redwood_leaves");
        redwood_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_ORANGE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_log");
        redwood_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "redwood_wood");
        stripped_redwood_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_ORANGE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_log");
        stripped_redwood_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F).sound(SoundType.WOOD)), "stripped_redwood_wood");
        redwood_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_planks");
        redwood_stairs = registerBlock(new StairsBlock(redwood_planks.defaultBlockState(), Block.Properties.copy(redwood_planks)), "redwood_stairs");
        redwood_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_slab");
        redwood_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, redwood_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence");
        redwood_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, redwood_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "redwood_fence_gate");
        redwood_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, redwood_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_door");
        redwood_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_ORANGE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "redwood_trapdoor");
        redwood_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, redwood_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_pressure_plate");
        redwood_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "redwood_button");
        
        white_cherry_sapling = registerBlock(new SaplingBlockBOP(new WhiteCherryTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "white_cherry_sapling");
        white_cherry_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.SNOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "white_cherry_leaves");
        pink_cherry_sapling = registerBlock(new SaplingBlockBOP(new PinkCherryTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "pink_cherry_sapling");
        pink_cherry_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.COLOR_PINK).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "pink_cherry_leaves");
        cherry_log = registerBlock(new LogBlock(MaterialColor.COLOR_RED, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_RED).strength(2.0F).sound(SoundType.WOOD)), "cherry_log");
        cherry_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "cherry_wood");
        stripped_cherry_log = registerBlock(new LogBlock(MaterialColor.COLOR_RED, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "stripped_cherry_log");
        stripped_cherry_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F).sound(SoundType.WOOD)), "stripped_cherry_wood");
        cherry_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_planks");
        cherry_stairs = registerBlock(new StairsBlock(cherry_planks.defaultBlockState(), Block.Properties.copy(cherry_planks)), "cherry_stairs");
        cherry_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_slab");
        cherry_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, cherry_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence");
        cherry_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, cherry_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "cherry_fence_gate");
        cherry_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, cherry_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_door");
        cherry_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "cherry_trapdoor");
        cherry_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, cherry_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_pressure_plate");
        cherry_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "cherry_button");
        
        mahogany_sapling = registerBlock(new SaplingBlockBOP(new MahoganyTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "mahogany_sapling");
        mahogany_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "mahogany_leaves");
        mahogany_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_PINK, Block.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0F).sound(SoundType.WOOD)), "mahogany_log");
        mahogany_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "mahogany_wood");
        stripped_mahogany_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_PINK, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_log");
        stripped_mahogany_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F).sound(SoundType.WOOD)), "stripped_mahogany_wood");
        mahogany_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_planks");
        mahogany_stairs = registerBlock(new StairsBlock(mahogany_planks.defaultBlockState(), Block.Properties.copy(mahogany_planks)), "mahogany_stairs");
        mahogany_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_slab");
        mahogany_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, mahogany_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence");
        mahogany_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, mahogany_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "mahogany_fence_gate");
        mahogany_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, mahogany_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_door");
        mahogany_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_PINK).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "mahogany_trapdoor");
        mahogany_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, mahogany_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_pressure_plate");
        mahogany_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "mahogany_button");
        
        jacaranda_sapling = registerBlock(new SaplingBlockBOP(new JacarandaTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "jacaranda_sapling");
        jacaranda_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.COLOR_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "jacaranda_leaves");
        jacaranda_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_WHITE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)), "jacaranda_log");
        jacaranda_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "jacaranda_wood");
        stripped_jacaranda_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_WHITE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_log");
        stripped_jacaranda_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F).sound(SoundType.WOOD)), "stripped_jacaranda_wood");
        jacaranda_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_planks");
        jacaranda_stairs = registerBlock(new StairsBlock(jacaranda_planks.defaultBlockState(), Block.Properties.copy(jacaranda_planks)), "jacaranda_stairs");
        jacaranda_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_slab");
        jacaranda_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, jacaranda_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence");
        jacaranda_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, jacaranda_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "jacaranda_fence_gate");
        jacaranda_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, jacaranda_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_door");
        jacaranda_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_WHITE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "jacaranda_trapdoor");
        jacaranda_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, jacaranda_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_pressure_plate");
        jacaranda_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "jacaranda_button");
        
        palm_sapling = registerBlock(new SaplingBlockBOP(new PalmTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "palm_sapling");
        palm_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "palm_leaves");
        palm_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_YELLOW, Block.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0F).sound(SoundType.WOOD)), "palm_log");
        palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "palm_wood");
        stripped_palm_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_YELLOW, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_log");
        stripped_palm_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)), "stripped_palm_wood");
        palm_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_planks");
        palm_stairs = registerBlock(new StairsBlock(palm_planks.defaultBlockState(), Block.Properties.copy(palm_planks)), "palm_stairs");
        palm_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_slab");
        palm_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, palm_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence");
        palm_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, palm_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "palm_fence_gate");
        palm_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, palm_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_door");
        palm_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "palm_trapdoor");
        palm_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, palm_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_pressure_plate");
        palm_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "palm_button");
        
        willow_sapling = registerBlock(new SaplingBlockBOP(new WillowTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "willow_sapling");
        willow_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "willow_leaves");
        willow_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_LIGHT_GREEN, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_log");
        willow_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "willow_wood");
        stripped_willow_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_LIGHT_GREEN, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_log");
        stripped_willow_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F).sound(SoundType.WOOD)), "stripped_willow_wood");
        willow_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_planks");
        willow_stairs = registerBlock(new StairsBlock(willow_planks.defaultBlockState(), Block.Properties.copy(willow_planks)), "willow_stairs");
        willow_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_slab");
        willow_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, willow_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence");
        willow_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, willow_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "willow_fence_gate");
        willow_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, willow_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_door");
        willow_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_GREEN).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "willow_trapdoor");
        willow_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, willow_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_pressure_plate");
        willow_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "willow_button");
        
        dead_sapling = registerBlock(new SaplingBlockBOP(new DeadTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "dead_sapling");
        dead_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.WOOD).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "dead_leaves");
        dead_log = registerBlock(new LogBlock(MaterialColor.STONE, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)), "dead_log");
        dead_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "dead_wood");
        stripped_dead_log = registerBlock(new LogBlock(MaterialColor.STONE, Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_log");
        stripped_dead_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F).sound(SoundType.WOOD)), "stripped_dead_wood");
        dead_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_planks");
        dead_stairs = registerBlock(new StairsBlock(dead_planks.defaultBlockState(), Block.Properties.copy(dead_planks)), "dead_stairs");
        dead_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_slab");
        dead_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, dead_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence");
        dead_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, dead_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "dead_fence_gate");
        dead_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, dead_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_door");
        dead_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.STONE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "dead_trapdoor");
        dead_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, dead_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_pressure_plate");
        dead_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "dead_button");
        
        magic_sapling = registerBlock(new SaplingBlockBOP(new MagicTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "magic_sapling");
        magic_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.COLOR_CYAN).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "magic_leaves");
        magic_log = registerBlock(new LogBlock(MaterialColor.COLOR_BLUE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_LIGHT_BLUE).strength(2.0F).sound(SoundType.WOOD)), "magic_log");
        magic_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "magic_wood");
        stripped_magic_log = registerBlock(new LogBlock(MaterialColor.COLOR_BLUE, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_magic_log");
        stripped_magic_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_magic_wood");
        magic_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_planks");
        magic_stairs = registerBlock(new StairsBlock(magic_planks.defaultBlockState(), Block.Properties.copy(magic_planks)), "magic_stairs");
        magic_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_slab");
        magic_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, magic_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence");
        magic_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, magic_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "magic_fence_gate");
        magic_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, magic_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_door");
        magic_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "magic_trapdoor");
        magic_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, magic_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_pressure_plate");
        magic_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "magic_button");
        
        umbran_sapling = registerBlock(new SaplingBlockBOP(new UmbranTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "umbran_sapling");
        umbran_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_BLUE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "umbran_leaves");
        umbran_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_BLUE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "umbran_log");
        umbran_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "umbran_wood");
        stripped_umbran_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_BLUE, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_umbran_log");
        stripped_umbran_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F).sound(SoundType.WOOD)), "stripped_umbran_wood");
        umbran_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_planks");
        umbran_stairs = registerBlock(new StairsBlock(umbran_planks.defaultBlockState(), Block.Properties.copy(umbran_planks)), "umbran_stairs");
        umbran_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_slab");
        umbran_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, umbran_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence");
        umbran_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, umbran_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "umbran_fence_gate");
        umbran_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, umbran_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_door");
        umbran_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BLUE).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "umbran_trapdoor");
        umbran_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, umbran_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_pressure_plate");
        umbran_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "umbran_button");
        
        hellbark_sapling = registerBlock(new SaplingBlockBOP(new HellbarkTree(), Block.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), "hellbark_sapling");
        hellbark_leaves = registerBlock(new LeavesBlock(Block.Properties.of(Material.LEAVES, MaterialColor.TERRACOTTA_PURPLE).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion()), "hellbark_leaves");
        hellbark_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_GRAY, Block.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)), "hellbark_log");
        hellbark_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "hellbark_wood");
        stripped_hellbark_log = registerBlock(new LogBlock(MaterialColor.TERRACOTTA_GRAY, Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_log");
        stripped_hellbark_wood = registerBlock(new RotatedPillarBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)), "stripped_hellbark_wood");
        hellbark_planks = registerBlock(new Block(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_planks");
        hellbark_stairs = registerBlock(new StairsBlock(hellbark_planks.defaultBlockState(), Block.Properties.copy(hellbark_planks)), "hellbark_stairs");
        hellbark_slab = registerBlock(new SlabBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_slab");
        hellbark_fence = registerBlock(new FenceBlock(Block.Properties.of(Material.WOOD, hellbark_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence");
        hellbark_fence_gate = registerBlock(new FenceGateBlock(Block.Properties.of(Material.WOOD, hellbark_planks.materialColor).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "hellbark_fence_gate");
        hellbark_door = registerBlock(new DoorBlock(Block.Properties.of(Material.WOOD, hellbark_planks.materialColor).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_door");
        hellbark_trapdoor = registerBlock(new TrapDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(3.0F).sound(SoundType.WOOD).noOcclusion()), "hellbark_trapdoor");
        hellbark_pressure_plate = registerBlock(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.of(Material.WOOD, hellbark_planks.materialColor).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_pressure_plate");
        hellbark_button = registerBlock(new WoodButtonBlock(Block.Properties.of(Material.DECORATION).noCollission().strength(0.5F).sound(SoundType.WOOD)), "hellbark_button");

        //Flowers
        rose = registerBlock(new FlowerBlockBOP(Effects.MOVEMENT_SPEED, 7, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "rose");
        violet = registerBlock(new FlowerBlockBOP(Effects.CONFUSION, 10, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "violet");
        lavender = registerBlock(new FlowerBlockBOP(Effects.HEALTH_BOOST, 5, Block.Properties.of(Material.PLANT, MaterialColor.COLOR_MAGENTA).noCollission().instabreak().sound(SoundType.GRASS)), "lavender");
        wildflower = registerBlock(new FlowerBlockBOP(Effects.HUNGER, 10, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wildflower");
        orange_cosmos = registerBlock(new FlowerBlockBOP(Effects.ABSORPTION, 7, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "orange_cosmos");
        pink_daffodil = registerBlock(new FlowerBlockBOP(Effects.INVISIBILITY, 7, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_daffodil");
        pink_hibiscus = registerBlock(new FlowerBlockBOP(Effects.REGENERATION, 5, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "pink_hibiscus");
        glowflower = registerBlock(new FlowerBlockBOP(Effects.GLOWING, 10, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(9)), "glowflower");
        wilted_lily = registerBlock(new FlowerBlockBOP(Effects.UNLUCK, 5, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "wilted_lily");
        burning_blossom = registerBlock(new FlowerBlockBOP(Effects.FIRE_RESISTANCE, 7, Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(7)), "burning_blossom");

        //Tall Flowers
        blue_hydrangea = registerBlock(new TallFlowerBlockBOP(Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "blue_hydrangea");
        goldenrod = registerBlock(new TallFlowerBlockBOP(Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "goldenrod");

        //Vines
        willow_vine = registerBlock(new VineBlock(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().randomTicks().strength(0.2F).sound(SoundType.GRASS)), "willow_vine");

        //Plants
        sprout = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "sprout");
        bush = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "bush");
        barley = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_YELLOW).noCollission().instabreak().sound(SoundType.GRASS)), "barley");
        dune_grass = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_LIGHT_GREEN).noCollission().instabreak().sound(SoundType.GRASS)), "dune_grass");
        desert_grass = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.TERRACOTTA_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "desert_grass");
        dead_grass = registerBlock(new FoliageBlockBOP(Block.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)), "dead_grass");
        cattail = registerBlock(new WatersidePlantBlock(Block.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "cattail");

        //Tall Plants
        tall_cattail = registerBlock(new DoubleWatersidePlantBlock(Block.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "tall_cattail");
        reed = registerBlock(new DoubleWaterPlantBlock(Block.Properties.of(Material.PLANT, MaterialColor.DIRT).noCollission().instabreak().sound(SoundType.GRASS)), "reed");
        watergrass = registerBlock(new DoubleWaterPlantBlock(Block.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)), "watergrass");
        mangrove_root = registerBlock(new DoubleWaterPlantBlock(Block.Properties.of(Material.PLANT, MaterialColor.TERRACOTTA_WHITE).noCollission().strength(1.0F, 1.5F).sound(SoundType.WOOD)), "mangrove_root");

        bramble = registerBlock(new BrambleBlock(Block.Properties.of(Material.PLANT, MaterialColor.NETHER).strength(0.4F).harvestLevel(0).harvestTool(ToolType.AXE).sound(SoundType.WOOD)), "bramble");
        toadstool = registerBlock(new MushroomBlockBOP(Block.Properties.of(Material.PLANT, MaterialColor.COLOR_ORANGE).noCollission().instabreak().sound(SoundType.GRASS)), "toadstool");
        glowshroom = registerBlock(new MushroomBlockBOP(Block.Properties.of(Material.PLANT, MaterialColor.DIAMOND).noCollission().instabreak().sound(SoundType.GRASS).lightLevel(6)), "glowshroom");

        //Potted Plants
        potted_origin_sapling = registerBlockNoGroup(new FlowerPotBlock(origin_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_origin_sapling");
        potted_flowering_oak_sapling = registerBlockNoGroup(new FlowerPotBlock(flowering_oak_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_flowering_oak_sapling");
        potted_rainbow_birch_sapling = registerBlockNoGroup(new FlowerPotBlock(rainbow_birch_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_rainbow_birch_sapling");
        potted_yellow_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(yellow_autumn_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_yellow_autumn_sapling");
        potted_orange_autumn_sapling = registerBlockNoGroup(new FlowerPotBlock(orange_autumn_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_orange_autumn_sapling");
        potted_maple_sapling = registerBlockNoGroup(new FlowerPotBlock(maple_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_maple_sapling");
        potted_fir_sapling = registerBlockNoGroup(new FlowerPotBlock(fir_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_fir_sapling");
        potted_redwood_sapling = registerBlockNoGroup(new FlowerPotBlock(redwood_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_redwood_sapling");
        potted_white_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(white_cherry_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_white_cherry_sapling");
        potted_pink_cherry_sapling = registerBlockNoGroup(new FlowerPotBlock(pink_cherry_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_pink_cherry_sapling");
        potted_mahogany_sapling = registerBlockNoGroup(new FlowerPotBlock(mahogany_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_mahogany_sapling");
        potted_jacaranda_sapling = registerBlockNoGroup(new FlowerPotBlock(jacaranda_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_jacaranda_sapling");
        potted_palm_sapling = registerBlockNoGroup(new FlowerPotBlock(palm_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_palm_sapling");
        potted_willow_sapling = registerBlockNoGroup(new FlowerPotBlock(willow_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_willow_sapling");
        potted_dead_sapling = registerBlockNoGroup(new FlowerPotBlock(dead_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_dead_sapling");
        potted_magic_sapling = registerBlockNoGroup(new FlowerPotBlock(magic_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_magic_sapling");
        potted_umbran_sapling = registerBlockNoGroup(new FlowerPotBlock(umbran_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_umbran_sapling");
        potted_hellbark_sapling = registerBlockNoGroup(new FlowerPotBlock(hellbark_sapling, Block.Properties.of(Material.DECORATION).instabreak()), "potted_hellbark_sapling");
        potted_rose = registerBlockNoGroup(new FlowerPotBlock(rose, Block.Properties.of(Material.DECORATION).instabreak()), "potted_rose");
        potted_violet = registerBlockNoGroup(new FlowerPotBlock(violet, Block.Properties.of(Material.DECORATION).instabreak()), "potted_violet");
        potted_lavender = registerBlockNoGroup(new FlowerPotBlock(lavender, Block.Properties.of(Material.DECORATION).instabreak()), "potted_lavender");
        potted_wildflower = registerBlockNoGroup(new FlowerPotBlock(wildflower, Block.Properties.of(Material.DECORATION).instabreak()), "potted_wildflower");
        potted_orange_cosmos = registerBlockNoGroup(new FlowerPotBlock(orange_cosmos, Block.Properties.of(Material.DECORATION).instabreak()), "potted_orange_cosmos");
        potted_pink_daffodil = registerBlockNoGroup(new FlowerPotBlock(pink_daffodil, Block.Properties.of(Material.DECORATION).instabreak()), "potted_pink_daffodil");
        potted_pink_hibiscus = registerBlockNoGroup(new FlowerPotBlock(pink_hibiscus, Block.Properties.of(Material.DECORATION).instabreak()), "potted_pink_hibiscus");
        potted_glowflower = registerBlockNoGroup(new FlowerPotBlock(glowflower, Block.Properties.of(Material.DECORATION).instabreak().lightLevel(9)), "potted_glowflower");
        potted_wilted_lily = registerBlockNoGroup(new FlowerPotBlock(wilted_lily, Block.Properties.of(Material.DECORATION).instabreak()), "potted_wilted_lily");
        potted_burning_blossom = registerBlockNoGroup(new FlowerPotBlock(burning_blossom, Block.Properties.of(Material.DECORATION).instabreak().lightLevel(7)), "potted_burning_blossom");
        potted_sprout = registerBlockNoGroup(new FlowerPotBlock(sprout, Block.Properties.of(Material.DECORATION).instabreak()), "potted_sprout");
        potted_toadstool = registerBlockNoGroup(new FlowerPotBlock(toadstool, Block.Properties.of(Material.DECORATION).instabreak()), "potted_toadstool");
        potted_glowshroom = registerBlockNoGroup(new FlowerPotBlock(glowshroom, Block.Properties.of(Material.DECORATION).instabreak().lightLevel(6)), "potted_glowshroom");

        if (FMLEnvironment.dist == Dist.CLIENT)
        {
            RenderType transparentRenderType = RenderType.cutoutMipped();
            RenderType cutoutRenderType = RenderType.cutout();
            RenderType translucentRenderType = RenderType.translucent();

            RenderTypeLookup.setRenderLayer(origin_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(flowering_oak_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(rainbow_birch_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(yellow_autumn_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(orange_autumn_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(maple_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(fir_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(redwood_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(white_cherry_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(pink_cherry_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(mahogany_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(jacaranda_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(palm_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(willow_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(dead_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(magic_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(umbran_leaves, transparentRenderType);
            RenderTypeLookup.setRenderLayer(hellbark_leaves, transparentRenderType);

            RenderTypeLookup.setRenderLayer(origin_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(flowering_oak_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(rainbow_birch_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(yellow_autumn_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(orange_autumn_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(maple_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(fir_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(redwood_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(white_cherry_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(pink_cherry_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(mahogany_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(jacaranda_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(palm_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(willow_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(dead_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(magic_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(umbran_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(hellbark_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(rose, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(violet, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(lavender, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(wildflower, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(orange_cosmos, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(pink_daffodil, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(pink_hibiscus, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(glowflower, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(wilted_lily, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(burning_blossom, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(blue_hydrangea, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(goldenrod, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(willow_vine, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(sprout, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(bush, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(barley, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(dune_grass, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(desert_grass, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(dead_grass, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(cattail, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(tall_cattail, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(reed, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(watergrass, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(mangrove_root, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(bramble, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(toadstool, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(glowshroom, cutoutRenderType);

            RenderTypeLookup.setRenderLayer(potted_origin_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_flowering_oak_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_rainbow_birch_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_yellow_autumn_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_orange_autumn_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_maple_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_fir_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_redwood_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_white_cherry_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_pink_cherry_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_mahogany_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_jacaranda_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_palm_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_willow_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_dead_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_magic_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_umbran_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_hellbark_sapling, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_rose, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_violet, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_lavender, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_wildflower, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_orange_cosmos, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_pink_daffodil, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_pink_hibiscus, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_glowflower, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_wilted_lily, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_burning_blossom, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_sprout, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_toadstool, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(potted_glowshroom, cutoutRenderType);

            RenderTypeLookup.setRenderLayer(fir_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(redwood_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(cherry_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(mahogany_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(jacaranda_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(palm_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(willow_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(dead_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(magic_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(umbran_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(hellbark_door, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(fir_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(redwood_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(cherry_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(mahogany_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(jacaranda_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(palm_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(willow_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(dead_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(magic_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(umbran_trapdoor, cutoutRenderType);
            RenderTypeLookup.setRenderLayer(hellbark_trapdoor, cutoutRenderType);
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
