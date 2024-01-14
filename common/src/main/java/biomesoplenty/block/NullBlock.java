/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class NullBlock extends Block
{
    public NullBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void animateTick(BlockState p_221253_, Level p_221254_, BlockPos p_221255_, RandomSource p_221256_)
    {
        if (p_221256_.nextInt(5) == 0)
        {
            Direction direction = Direction.getRandom(p_221256_);
            Direction.Axis direction$axis = direction.getAxis();
            BlockPos blockpos = p_221255_.relative(direction);
            BlockState blockstate = p_221254_.getBlockState(blockpos);

            if (!isFaceFull(blockstate.getCollisionShape(p_221254_, blockpos), direction))
            {
                double d0 = (double)p_221255_.getX() + 0.5D;
                double d1 = (double)p_221255_.getY() + 0.5D;
                double d2 = (double)p_221255_.getZ() + 0.5D;

                double d3 = (p_221256_.nextDouble() * 0.5D) - (p_221256_.nextDouble() * 0.5D);
                double d4 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.55D : d3;
                double d5 = direction$axis == Direction.Axis.Y ? (double)direction.getStepY() * 0.55D : d3;
                double d6 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.55D : d3;

                double ymove = 0.1D;
                if (direction == Direction.DOWN)
                {
                    ymove = -0.1D;
                }

                p_221254_.addParticle(ModParticles.NULL, d0 + d4, d1 + d5, d2 + d6, 0.0D, ymove, 0.0D);
            }
        }
    }
}
