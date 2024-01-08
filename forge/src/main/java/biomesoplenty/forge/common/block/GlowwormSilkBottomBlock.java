/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.block;

import biomesoplenty.forge.api.block.BOPBlocks;
import biomesoplenty.forge.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GlowwormSilkBottomBlock extends HangingStrandBottomBlock {

    public GlowwormSilkBottomBlock(Properties p_i241195_1_) {
        super(p_i241195_1_);
    }

    @Override
    protected Block getBodyBlock()
    {
        return BOPBlocks.GLOWWORM_SILK_STRAND.get();
    }

    @Override
    public boolean canSurvive(BlockState p_196260_1_, LevelReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.relative(this.growthDirection.getOpposite());
        BlockState blockstate = p_196260_2_.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (!this.canAttachTo(blockstate))
        {
            return false;
        }
        else
        {
            return block == this.getHeadBlock() || block == this.getBodyBlock() || blockstate.getBlock() == BOPBlocks.GLOWING_MOSS_BLOCK.get() || (blockstate.is(BlockTags.DRIPSTONE_REPLACEABLE) && blockstate.isFaceSturdy(p_196260_2_, blockpos, Direction.DOWN));
        }
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (rand.nextInt(6) == 0)
        {
            worldIn.addParticle(ModParticles.GLOWWORM.get(), (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), (double) (pos.getY() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), 0.0D, 0.0D, 0.0D);
        }
    }
}