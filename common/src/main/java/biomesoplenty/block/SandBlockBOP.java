/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class SandBlockBOP extends FallingBlock
{
    private final int dustColor;
    public SandBlockBOP(int dustColor, Block.Properties properties)
    {
        super(properties);
        this.dustColor = dustColor;
    }

    @Override
    public int getDustColor(BlockState $$0, BlockGetter $$1, BlockPos $$2) {
        return dustColor;
    }
}
