package biomesoplenty.common.handler;

import biomesoplenty.common.block.IBOPBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SilkTouchEventHandler {

    @SubscribeEvent
    public void onSilkTouched(BlockEvent.HarvestDropsEvent event)
    {
        IBlockState state = event.getState();

        if (state.getBlock() instanceof IBOPBlock && event.isSilkTouching())
        {
            event.getDrops().clear();
            event.getDrops().add(state.getBlock().getPickBlock(state, null, event.getWorld(), event.getPos(), event.getHarvester()));
        }
    }
}