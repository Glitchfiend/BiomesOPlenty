/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class FleshBlock extends Block
{
    public FleshBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState blockState, Entity entityIn)
    {
        entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0.95D, 1.0D, 0.95D));
    }
}
