/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class HangingSignBlockEntityBOP extends HangingSignBlockEntity {
    public HangingSignBlockEntityBOP(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return BOPBlockEntities.HANGING_SIGN;
    }

    @Override
    public boolean isValidBlockState(@NotNull BlockState state) {
        return getType().isValid(state);
    }
}
