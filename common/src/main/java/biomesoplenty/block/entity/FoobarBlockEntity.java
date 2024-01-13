/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block.entity;

import biomesoplenty.api.block.BOPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FoobarBlockEntity extends BlockEntity
{
    public FoobarBlockEntity(BlockPos pos, BlockState state) {
        super(BOPBlockEntities.FOOBAR, pos, state);
    }
}
