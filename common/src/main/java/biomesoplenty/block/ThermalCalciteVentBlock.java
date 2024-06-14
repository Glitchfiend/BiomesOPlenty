/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.damagesource.BOPDamageTypes;
import biomesoplenty.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class ThermalCalciteVentBlock extends ThermalCalciteBlock
{
    public ThermalCalciteVentBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos p_153778_, BlockState p_153779_, Entity p_153780_)
    {
        if (!p_153780_.fireImmune() && p_153780_ instanceof LivingEntity)
        {
            p_153780_.hurt(level.damageSources().source(BOPDamageTypes.FUMAROLE), 1.0F);
        }

        super.stepOn(level, p_153778_, p_153779_, p_153780_);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        worldIn.addAlwaysVisibleParticle(ModParticles.STEAM, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 6.0D)), 0.0D, 0.02D, 0.0D);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
