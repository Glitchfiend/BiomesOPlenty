/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class RadiantHandsPlantBlock extends GrowingPlantBodyBlock
{
    public static final MapCodec<RadiantHandsPlantBlock> CODEC = simpleCodec(RadiantHandsPlantBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public RadiantHandsPlantBlock(Properties p_i241195_1_)
    {
        super(p_i241195_1_, Direction.UP, SHAPE, false);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    public MapCodec<RadiantHandsPlantBlock> codec()
    {
        return CODEC;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) BOPBlocks.RADIANT_HANDS;
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_)
    {
        BlockPos blockpos = p_196260_3_.relative(this.growthDirection.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate))
        {
            return false;
        }
        else
        {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || block == Blocks.END_STONE || block == BOPBlocks.ALGAL_END_STONE;
        }
    }

    public static ToIntFunction<BlockState> lightLevel(int level)
    {
        return (state) -> { return state.getValue(LIT) ? level : 0; };
    }

    @Override
    protected BlockState updateHeadAfterConvertedFromBody(BlockState $$0, BlockState $$1)
    {
        return (BlockState)$$1.setValue(LIT, (Boolean)$$0.getValue(LIT));
    }

    @Override
    public void performBonemeal(ServerLevel $$0, RandomSource $$1, BlockPos $$2, BlockState $$3)
    {
        $$0.setBlock($$2, (BlockState)$$3.setValue(LIT, true), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56651_)
    {
        p_56651_.add(LIT);
    }
}