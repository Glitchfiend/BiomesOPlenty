/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class HotSpringVentBlock extends Block
{
    public HotSpringVentBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos p_153778_, BlockState p_153779_, Entity p_153780_)
    {
        if (!p_153780_.fireImmune() && p_153780_ instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)p_153780_)) {
            p_153780_.hurt(level.damageSources().source(BOPDamageTypes.FUMAROLE), 1.0F);
        }

        super.stepOn(level, p_153778_, p_153779_, p_153780_);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (worldIn.getBlockState(pos.above()).getBlock() == Blocks.WATER)
        {
            worldIn.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.02D, 0.0D);
            worldIn.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.0D, 0.0D);
        }
        else
        {
            worldIn.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.02D, 0.0D);
        }
    }

    @Override
    public boolean isPathfindable(BlockState p_154341_, BlockGetter p_154342_, BlockPos p_154343_, PathComputationType p_154344_) {
        return false;
    }
}
