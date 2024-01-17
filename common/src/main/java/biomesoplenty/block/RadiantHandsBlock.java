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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class RadiantHandsBlock extends GrowingPlantHeadBlock
{
    public static final MapCodec<RadiantHandsBlock> CODEC = simpleCodec(RadiantHandsBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
    public static final int MAX_AGE = 8;
    private final double growPerTickProbability;

    public RadiantHandsBlock(Properties p_i241195_1_)
    {
        super(p_i241195_1_, Direction.UP, SHAPE, false, 0.01D);
        this.growPerTickProbability = 0.01D;
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0).setValue(LIT, false));
    }

    @Override
    public MapCodec<RadiantHandsBlock> codec()
    {
        return CODEC;
    }

    @Override
    public BlockState getStateForPlacement(LevelAccessor p_53949_) {
        return this.defaultBlockState().setValue(AGE, Integer.valueOf(p_53949_.getRandom().nextInt(MAX_AGE)));
    }

    @Override
    public boolean isRandomlyTicking(BlockState p_53961_) {
        return p_53961_.getValue(AGE) < MAX_AGE;
    }

    @Override
    public BlockState getMaxAgeState(BlockState p_187439_) {
        return p_187439_.setValue(AGE, Integer.valueOf(MAX_AGE));
    }

    @Override
    public boolean isMaxAge(BlockState p_187441_) {
        return p_187441_.getValue(AGE) == MAX_AGE;
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource p_220928_) {
        return 1;
    }

    @Override
    protected BlockState getGrowIntoState(BlockState p_220935_, RandomSource p_220936_) {
        return super.getGrowIntoState(p_220935_, p_220936_).setValue(LIT, Boolean.valueOf(p_220936_.nextFloat() < 0.4F));
    }

    @Override
    public void performBonemeal(ServerLevel p_221337_, RandomSource p_221338_, BlockPos p_221339_, BlockState p_221340_) {
        BlockPos blockpos = p_221339_.relative(this.growthDirection);
        int i = Math.min(p_221340_.getValue(AGE) + 1, MAX_AGE);
        int j = this.getBlocksToGrowWhenBonemealed(p_221338_);

        p_221337_.setBlock(p_221339_, p_221340_.setValue(LIT, Boolean.valueOf(true)), 2);

        for(int k = 0; k < j && this.canGrowInto(p_221337_.getBlockState(blockpos)); ++k)
        {
            p_221337_.setBlockAndUpdate(blockpos, p_221340_.setValue(AGE, Integer.valueOf(i)));
            blockpos = blockpos.relative(this.growthDirection);
            i = Math.min(i + 1, MAX_AGE);
        }
    }

    @Override
    protected BlockState updateBodyAfterConvertedFromHead(BlockState p_152987_, BlockState p_152988_) {
        return p_152988_.setValue(LIT, p_152987_.getValue(LIT));
    }

    @Override
    protected boolean canGrowInto(BlockState p_230334_1_) {
        return NetherVines.isValidGrowthState(p_230334_1_);
    }

    @Override
    protected Block getBodyBlock() {
        return BOPBlocks.RADIANT_HANDS_PLANT;
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
            return block == this.getHeadBlock() || block == this.getBodyBlock() || block == Blocks.END_STONE || block == BOPBlocks.UNMAPPED_END_STONE || block == BOPBlocks.NULL_END_STONE || block == BOPBlocks.ALGAL_END_STONE;
        }
    }

    public static ToIntFunction<BlockState> lightLevel(int level)
    {
        return (state) -> { return state.getValue(LIT) ? level : 0; };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56651_) {
        p_56651_.add(AGE, LIT);
    }
}