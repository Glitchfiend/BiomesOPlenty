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
import net.minecraft.init.SoundEvents;
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

        if (block instanceof BlockBOPDirt && world.isAirBlock(pos.up()))
        {
            result = true;
            if (state.getValue(BlockBOPDirt.COARSE))
            {
                world.setBlockState(pos, state.withProperty(BlockBOPDirt.COARSE, false));
            } else
            {
                world.setBlockState(pos, BlockBOPFarmland.paging.getVariantState((BlockBOPDirt.BOPDirtType) state.getValue(BlockBOPDirt.VARIANT)));
            }
        }
        else if (block instanceof BlockBOPGrass && world.isAirBlock(pos.up()))
        {
            result = true;
            IBlockState dirtState = BlockBOPGrass.getDirtBlockState(state);

            if (dirtState.getBlock() instanceof BlockBOPDirt)
            {
                BlockBOPDirt.BOPDirtType dirtType = (BlockBOPDirt.BOPDirtType) dirtState.getValue(BlockBOPDirt.VARIANT);
                world.setBlockState(pos, BlockBOPFarmland.paging.getVariantState(dirtType));
            }
            else if (dirtState.getBlock() instanceof BlockDirt)
            {
                world.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
            }
            else
            {
                // There is no associated farmland, so just turn it to its associated dirt
                // Mostly useful for overgrown grass turning to stone
                world.setBlockState(pos, dirtState);
            }
        }

        if (result)
        {
            event.setResult(Event.Result.ALLOW);

            world.playSound(event.getEntityPlayer(), pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            event.getEntityPlayer().swingArm(PlayerUtil.getHandForItemAndMeta(event.getEntityPlayer(), event.getCurrent().getItem(), event.getCurrent().getMetadata()));
            event.getCurrent().damageItem(1, event.getEntityPlayer());
        }
    }
}