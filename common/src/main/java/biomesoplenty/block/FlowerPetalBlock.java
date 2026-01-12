/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FlowerPetalBlock extends Block
{
    protected final float leafParticleChance;
    protected final ParticleOptions leafParticle;

    public FlowerPetalBlock(float $$0, ParticleOptions $$1, BlockBehaviour.Properties $$2)
    {
        super($$2);
        this.leafParticleChance = $$0;
        this.leafParticle = $$1;
    }

    @Override
    public void animateTick(BlockState p_221374_, Level p_221375_, BlockPos p_221376_, RandomSource p_221377_)
    {
        super.animateTick(p_221374_, p_221375_, p_221376_, p_221377_);
        BlockPos blockpos = p_221376_.below();
        BlockState blockstate = p_221375_.getBlockState(blockpos);
        this.makeFallingLeavesParticles(p_221375_, p_221376_, p_221377_, blockstate, blockpos);
    }

    private void makeFallingLeavesParticles(Level p_392380_, BlockPos p_391974_, RandomSource p_396413_, BlockState p_391693_, BlockPos p_397349_)
    {
        if (!(p_396413_.nextFloat() >= this.leafParticleChance))
        {
            if (!isFaceFull(p_391693_.getCollisionShape(p_392380_, p_397349_), Direction.UP))
            {
                this.spawnFallingLeavesParticle(p_392380_, p_391974_, p_396413_);
            }
        }
    }

    protected void spawnFallingLeavesParticle(Level $$0, BlockPos $$1, RandomSource $$2)
    {
        ParticleUtils.spawnParticleBelow($$0, $$1, $$2, this.leafParticle);
    }
}