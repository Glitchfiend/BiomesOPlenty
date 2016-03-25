/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPFarmland;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.entity.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UseHoeEventHandler
{

    @SubscribeEvent
    public void useHoe(UseHoeEvent event)
    {
        if (event.getResult() != Event.Result.DEFAULT || event.isCanceled())
        {
            return;
        }

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        boolean result = false;

        if (block instanceof BlockBOPDirt)
        {
            result = true;
            if (state.getValue(BlockBOPDirt.COARSE))
            {
                world.setBlockState(pos, state.withProperty(BlockBOPDirt.COARSE, Boolean.valueOf(false)));
            } else
            {
                world.setBlockState(pos, BlockBOPFarmland.paging.getVariantState((BlockBOPDirt.BOPDirtType) state.getValue(BlockBOPDirt.VARIANT)));
            }
        }
        else if (block instanceof BlockBOPGrass)
        {
            result = true;
            BlockBOPGrass grass = (BlockBOPGrass) state.getBlock();
            Block dirtBlock = grass.getDirtBlockState(state).getBlock();

            if (dirtBlock instanceof BlockBOPDirt)
            {
                BlockBOPDirt.BOPDirtType dirtType = (BlockBOPDirt.BOPDirtType) grass.getDirtBlockState(state).getValue(BlockBOPDirt.VARIANT);
                world.setBlockState(pos, BlockBOPFarmland.paging.getVariantState(dirtType));
            }
            else if (dirtBlock instanceof BlockDirt && state.getValue(BlockBOPGrass.VARIANT) != BlockBOPGrass.BOPGrassType.SMOLDERING)
            {
                world.setBlockState(pos, Blocks.farmland.getDefaultState());
            }
        }

        if (result)
        {
            if (!event.getEntityPlayer().capabilities.isCreativeMode)
            {
                event.getCurrent().damageItem(1, event.getEntityLiving());
            }
            event.getWorld().playSound(event.getEntityPlayer(), (double) ((float) pos.getX() + 0.5F), (double) ((float) pos.getY() + 0.5F), (double) ((float) pos.getZ() + 0.5F), block.getSoundType().getStepSound(), SoundCategory.BLOCKS, (state.getBlock().getSoundType().getVolume() + 1.0F) / 2.0F, state.getBlock().getSoundType().getPitch() * 0.8F);
            event.getEntityPlayer().swingArm(PlayerUtil.getHandForItemAndMeta(event.getEntityPlayer(), event.getCurrent().getItem(), event.getCurrent().getMetadata()));
        }
    }
}