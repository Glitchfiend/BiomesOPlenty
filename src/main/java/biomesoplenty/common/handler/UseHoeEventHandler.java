package biomesoplenty.common.handler;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UseHoeEventHandler {

    @SubscribeEvent
    public void useHoe(UseHoeEvent event)
    {
        if (event.getResult() != Event.Result.DEFAULT || event.isCanceled())
        {
            return;
        }

        BlockPos pos = event.pos;
        IBlockState state = event.world.getBlockState(pos);
        Block block = state.getBlock();
        boolean result = false;

        if (block instanceof BlockBOPDirt || block instanceof BlockBOPGrass)
        {
            result = true;
            if (!(block instanceof BlockBOPGrass))
            {
                if ((state.getBlock().getMetaFromState(state) & 1) == 1)
                {
                    event.world.setBlockState(pos, state.getBlock().getStateFromMeta(state.getBlock().getMetaFromState(state) - 1), 2);
                }
                else
                {
                    event.world.setBlockState(pos, BOPBlocks.farmland.getDefaultState(), 2);
                }
            }
            else
            {

                event.world.setBlockState(pos, Blocks.farmland.getDefaultState());
            }
        }

        if (result)
        {
            if (!event.entityPlayer.capabilities.isCreativeMode)
            {
                event.current.damageItem(1, event.entityLiving);
            }
            event.world.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), block.stepSound.getStepSound(), (state.getBlock().stepSound.getVolume() + 1.0F) / 2.0F, state.getBlock().stepSound.getFrequency() * 0.8F);
            event.entityPlayer.swingItem();
        }
    }
}
