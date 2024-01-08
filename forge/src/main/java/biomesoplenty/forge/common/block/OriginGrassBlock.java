package biomesoplenty.forge.common.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

public class OriginGrassBlock extends GrassBlock
{
    public OriginGrassBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
    {
        if (ToolActions.HOE_TILL == toolAction)
        {
            Block block = state.getBlock();

            if (block == this && context.getLevel().getBlockState(context.getClickedPos().above()).isAir())
            {
                return Blocks.FARMLAND.defaultBlockState();
            }
        }

        return null;
    }
}
