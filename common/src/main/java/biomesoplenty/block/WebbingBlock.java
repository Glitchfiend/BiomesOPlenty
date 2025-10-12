/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class WebbingBlock extends MultifaceBlock
{
    public static final MapCodec<WebbingBlock> CODEC = simpleCodec(WebbingBlock::new);

    public WebbingBlock(BlockBehaviour.Properties p_153282_) {
        super(p_153282_);
    }

    @Override
    public MapCodec<WebbingBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean b)
    {
        entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.625D, 0.75D, 0.625D));
    }

    @Override
    public boolean canBeReplaced(BlockState p_153299_, BlockPlaceContext p_153300_) {
        return !p_153300_.getItemInHand().is(BOPBlocks.WEBBING.asItem()) || super.canBeReplaced(p_153299_, p_153300_);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_181225_) {
        return p_181225_.getFluidState().isEmpty();
    }
}