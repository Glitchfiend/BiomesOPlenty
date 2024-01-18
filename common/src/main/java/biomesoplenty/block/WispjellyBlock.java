/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WispjellyBlock extends Block
{
    public static final MapCodec<WispjellyBlock> CODEC = simpleCodec(WispjellyBlock::new);

    public WispjellyBlock(Properties p_153282_)
    {
        super(p_153282_);
    }

    @Override
    public MapCodec<WispjellyBlock> codec()
    {
        return CODEC;
    }

    @Override
    public boolean skipRendering(BlockState p_53972_, BlockState p_53973_, Direction p_53974_) {
        return p_53973_.is(this) ? true : super.skipRendering(p_53972_, p_53973_, p_53974_);
    }

    @Override
    public VoxelShape getVisualShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        return Shapes.empty();
    }

    @Override
    public boolean propagatesSkylightDown(BlockState $$0, BlockGetter $$1, BlockPos $$2) {
        return true;
    }
}