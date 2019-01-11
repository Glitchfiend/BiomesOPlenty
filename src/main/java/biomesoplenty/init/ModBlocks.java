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
import biomesoplenty.common.block.BlockFlowerBOP;
import biomesoplenty.common.block.BlockFoliageBOP;
import biomesoplenty.common.block.BlockLeavesBOP;
import biomesoplenty.common.block.BlockMud;
import biomesoplenty.common.block.BlockMushroomBOP;
import biomesoplenty.common.block.BlockPlantBOP;
import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.Block.Builder;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks
{
    public static void init()
    {
        mud = registerBlock(new BlockMud(Builder.create(Material.GROUND, MapColor.BROWN).hardnessAndResistance(0.6F)), "mud");
        mud_brick_block = registerBlock(new Block(Builder.create(Material.ROCK, MapColor.BROWN_TERRACOTTA).hardnessAndResistance(1.0F)), "mud_brick_block");
        
        dried_sand = registerBlock(new Block(Builder.create(Material.GROUND, MapColor.BROWN_TERRACOTTA).hardnessAndResistance(1.0F)), "dried_sand");
        ash_block = registerBlock(new BlockAsh(Builder.create(Material.SAND, MapColor.BLACK).hardnessAndResistance(0.4F).sound(SoundType.SAND)), "ash_block");
        
        //Fir Wood
        fir_log = registerBlock(new BlockLog(MapColor.WOOD, Block.Builder.create(Material.WOOD, MapColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_log");
        fir_wood = registerBlock(new BlockRotatedPillar(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "fir_wood");
        stripped_fir_log = registerBlock(new BlockLog(MapColor.WOOD, Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_log");
        stripped_fir_wood = registerBlock(new BlockRotatedPillar(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)), "stripped_fir_wood");
        fir_leaves = registerBlock(new BlockLeavesBOP(Block.Builder.create(Material.LEAVES).hardnessAndResistance(0.2F).needsRandomTick().sound(SoundType.PLANT)), "fir_leaves");
        fir_planks = registerBlock(new Block(Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_planks");
        fir_slab = registerBlock(new BlockSlab(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_slab");
        
        // TODO: Make required fields visible?
        fir_stairs = registerBlock(new BlockStairs(fir_planks.getDefaultState(), Block.Builder.from(fir_planks)), "fir_stairs");
        fir_fence = registerBlock(new BlockFence(Block.Builder.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence");
        fir_fence_gate = registerBlock(new BlockFenceGate(Block.Builder.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "fir_fence_gate");
        fir_door = registerBlock(new BlockDoor(Block.Builder.create(Material.WOOD, fir_planks.materialColor).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_door");
        fir_trapdoor = registerBlock(new BlockTrapDoor(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD)), "fir_trapdoor");
        fir_button = registerBlock(new BlockButtonWood(Block.Builder.create(Material.CIRCUITS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_button");
        fir_pressure_plate = registerBlock(new BlockPressurePlate(BlockPressurePlate.Sensitivity.EVERYTHING, Block.Builder.create(Material.WOOD, fir_planks.materialColor).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "fir_pressure_plate");
        
        rose = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "rose");
        blue_hydrangea = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "blue_hydrangea");
        violet = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "violet");
        lavender = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "lavender");
        goldenrod = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "goldenrod");
        wildflower = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wildflower");
        orange_cosmos = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "orange_cosmos");
        pink_daffodil = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_daffodil");
        pink_hibiscus = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "pink_hibiscus");
        glowflower = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(9)), "glowflower");
        deathbloom = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "deathbloom");
        wilted_lily = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "wilted_lily");
        burning_blossom = registerBlock(new BlockFlowerBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(8)), "burning_blossom");
        
        ivy = registerBlock(new BlockVine(Block.Builder.create(Material.VINE).doesNotBlockMovement().needsRandomTick().hardnessAndResistance(0.2F).sound(SoundType.PLANT)), "ivy");
        willow_vine = registerBlock(new BlockVine(Block.Builder.create(Material.VINE).doesNotBlockMovement().needsRandomTick().hardnessAndResistance(0.2F).sound(SoundType.PLANT)), "willow_vine");
        
        short_grass = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "short_grass");
        bush = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "bush");
        barley = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "barley");
        dune_grass = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dune_grass");
        desert_grass = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "desert_grass");
        dead_grass = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "dead_grass");
        devilweed = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "devilweed");
        spectral_fern = registerBlock(new BlockFoliageBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "spectral_fern");
        
        thorn = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "thorn");
        tiny_cactus = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "tiny_cactus");
        cattail = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "cattail");
        root = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "root");
        reed = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "reed");
        watergrass = registerBlock(new BlockPlantBOP(Block.Builder.create(Material.VINE).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "watergrass");
        
        toadstool = registerBlock(new BlockMushroomBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), "toadstool");
        glowshroom = registerBlock(new BlockMushroomBOP(Block.Builder.create(Material.PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).lightValue(6)), "glowshroom");
    }

    public static Block registerBlock(Block block, String name)
    {
        ItemBlock itemBlock = new ItemBlock(block, new Item.Builder().group(ItemGroupBOP.instance));
        block.setRegistryName(name);
        itemBlock.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(itemBlock);
        return block;
    }
}
