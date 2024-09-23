/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.init.ModParticles;
import biomesoplenty.worldgen.placement.BOPEndPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

public class NullBlock extends Block implements BonemealableBlock
{
    public NullBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void animateTick(BlockState p_221253_, Level p_221254_, BlockPos p_221255_, RandomSource p_221256_)
    {
        if (p_221256_.nextInt(10) == 0)
        {
            Direction direction = Direction.getRandom(p_221256_);
            Direction.Axis direction$axis = direction.getAxis();
            BlockPos blockpos = p_221255_.relative(direction);
            BlockState blockstate = p_221254_.getBlockState(blockpos);

            if (direction != Direction.DOWN && !isFaceFull(blockstate.getCollisionShape(p_221254_, blockpos), direction))
            {
                double d0 = (double)p_221255_.getX() + 0.5D;
                double d1 = (double)p_221255_.getY() + 0.5D;
                double d2 = (double)p_221255_.getZ() + 0.5D;

                double d3 = (p_221256_.nextDouble() * 0.5D) - (p_221256_.nextDouble() * 0.5D);
                double d4 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.55D : d3;
                double d5 = direction$axis == Direction.Axis.Y ? (double)direction.getStepY() * 0.55D : d3;
                double d6 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.55D : d3;

                p_221254_.addParticle(ModParticles.NULL, d0 + d4, d1 + d5, d2 + d6, 0.0D, 0.1D, 0.0D);
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_, boolean b)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_)
    {
        BlockPos blockpos = p_221272_.above();
        Optional<Holder.Reference<PlacedFeature>> optional = p_221270_.registryAccess().registryOrThrow(Registries.PLACED_FEATURE).getHolder(BOPEndPlacements.NULL_PLANT_BONEMEAL);

        label49:
        for(int i = 0; i < 128; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3) - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
                if (!p_221270_.getBlockState(blockpos1.below()).is(this) || p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_, blockpos1)) {
                    continue label49;
                }
            }

            BlockState blockstate1 = p_221270_.getBlockState(blockpos1);
            if (blockstate1.isAir()) {
                Holder<PlacedFeature> holder;
                if (!optional.isPresent()) {
                    continue;
                }

                holder = optional.get();

                holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(), p_221271_, blockpos1);
            }
        }
    }
}
