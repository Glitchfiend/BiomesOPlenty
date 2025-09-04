/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;

public class TallFlowerBlockBOP extends TallFlowerBlock
{
    public TallFlowerBlockBOP(Properties properties)
    {
        super(properties);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        Block block = stateIn.getBlock();

        if (block == BOPBlocks.ICY_IRIS && stateIn.getValue(HALF) == DoubleBlockHalf.UPPER)
        {
            if (rand.nextInt(24) == 0)
            {
                worldIn.addParticle(ParticleTypes.SNOWFLAKE, (double)(pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) * 2.0D)), (double)(pos.getY() + 1.0D + ((rand.nextDouble() - rand.nextDouble()) * 2.0D)), (double)(pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) * 2.0D)), 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
