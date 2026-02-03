/*******************************************************************************
 * Copyright 2026, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.item;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.StringyCobwebBlock;
import biomesoplenty.block.properties.ConnectedProperty;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.feature.misc.StringyCobwebFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StringyCobwebBlockItem extends BlockItem
{
    public StringyCobwebBlockItem(Block $$0, Properties $$1)
    {
        super($$0, $$1);
    }

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state)
    {
        Direction dir = context.getHorizontalDirection();
        return getStringyCobwebLength(context.getLevel(), context.getClickedPos(), dir) > 0;
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state)
    {
        Direction dir = context.getHorizontalDirection();
        int length = getStringyCobwebLength(context.getLevel(), context.getClickedPos(), dir);

        if (length > 0)
        {
            placeCobweb(context.getLevel(), context.getClickedPos(), length, dir);
            return true;
        }

        return false;
    }

    public void placeCobweb(Level world, BlockPos pos, int length, Direction dir)
    {
        world.setBlock(pos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.BOTTOM), 11);
        BlockPos nextStringPos = pos;

        for (int i = 0; i < length - 1; ++i)
        {
            nextStringPos = nextStringPos.relative(dir, 1).above(1);
            world.setBlock(nextStringPos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.MIDDLE), 27);
        }

        // Set the end of the string to the top state
        world.setBlock(nextStringPos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.TOP), 27);
    }

    public static int getStringyCobwebLength(Level level, BlockPos pos, Direction dir)
    {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);

        if (!StringyCobwebFeature.isStringyCobwebReplaceable(level.getBlockState(pos)) || !belowState.isFaceSturdy(level, belowPos, Direction.UP))
            return 0;

        BlockPos stringTopPos = pos;
        int length = 1;

        for (; length < StringyCobwebFeature.MAX_DISTANCE; ++length)
        {
            BlockPos nextPos = stringTopPos.relative(dir, 1).above(1);

            if (!StringyCobwebFeature.isStringyCobwebReplaceable(level.getBlockState(nextPos)))
                break;

            stringTopPos = nextPos;
        }

        BlockPos abovePos = stringTopPos.above();
        BlockState aboveState = level.getBlockState(abovePos);

        if (!aboveState.isFaceSturdy(level, abovePos, Direction.DOWN)) {
            return 0;
        }

        return length;
    }
}
