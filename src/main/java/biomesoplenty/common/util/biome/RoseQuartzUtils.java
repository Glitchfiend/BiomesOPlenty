/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

import java.util.function.Consumer;

public class RoseQuartzUtils
{
    public static double getRoseQuartzHeight(double p_159624_, double p_159625_, double p_159626_, double p_159627_) {
        if (p_159624_ < p_159627_) {
            p_159624_ = p_159627_;
        }

        double d0 = 0.384D;
        double d1 = p_159624_ / p_159625_ * 0.384D;
        double d2 = 0.75D * Math.pow(d1, 1.3333333333333333D);
        double d3 = Math.pow(d1, 0.6666666666666666D);
        double d4 = 0.3333333333333333D * Math.log(d1);
        double d5 = p_159626_ * (d2 - d3 - d4);
        d5 = Math.max(d5, 0.0D);
        return d5 / 0.384D * p_159625_;
    }

    public static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel p_159640_, BlockPos p_159641_, int p_159642_)
    {
        if (isEmptyOrWaterOrLava(p_159640_, p_159641_))
        {
            return false;
        }
        else
        {
            float f = 6.0F;
            float f1 = 6.0F / (float)p_159642_;

            for(float f2 = 0.0F; f2 < ((float)Math.PI * 2F); f2 += f1) {
                int i = (int)(Mth.cos(f2) * (float)p_159642_);
                int j = (int)(Mth.sin(f2) * (float)p_159642_);
                if (isEmptyOrWaterOrLava(p_159640_, p_159641_.offset(i, 0, j))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isEmptyOrWater(LevelAccessor p_159629_, BlockPos p_159630_)
    {
        return p_159629_.isStateAtPosition(p_159630_, RoseQuartzUtils::isEmptyOrWater);
    }

    public static boolean isEmptyOrWaterOrLava(LevelAccessor p_159660_, BlockPos p_159661_) {
        return p_159660_.isStateAtPosition(p_159661_, RoseQuartzUtils::isEmptyOrWaterOrLava);
    }

    public static void buildBaseToTipColumn(Direction p_159652_, int p_159653_, boolean p_159654_, Consumer<BlockState> p_159655_) {
        if (p_159653_ >= 3) {
            p_159655_.accept(createPointedRoseQuartz(p_159652_, DripstoneThickness.BASE));

            for(int i = 0; i < p_159653_ - 3; ++i) {
                p_159655_.accept(createPointedRoseQuartz(p_159652_, DripstoneThickness.MIDDLE));
            }
        }

        if (p_159653_ >= 2) {
            p_159655_.accept(createPointedRoseQuartz(p_159652_, DripstoneThickness.FRUSTUM));
        }

        if (p_159653_ >= 1) {
            p_159655_.accept(createPointedRoseQuartz(p_159652_, p_159654_ ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
        }

    }

    public static void growPointedRoseQuartz(WorldGenLevel p_159644_, BlockPos p_159645_, Direction p_159646_, int p_159647_, boolean p_159648_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = p_159645_.mutable();
        buildBaseToTipColumn(p_159646_, p_159647_, p_159648_, (p_159635_) -> {
            if (p_159635_.is(Blocks.POINTED_DRIPSTONE)) {
                p_159635_ = p_159635_.setValue(PointedDripstoneBlock.WATERLOGGED, Boolean.valueOf(p_159644_.isWaterAt(blockpos$mutableblockpos)));
            }

            p_159644_.setBlock(blockpos$mutableblockpos, p_159635_, 2);
            blockpos$mutableblockpos.move(p_159646_);
        });
    }

    public static boolean placeRoseQuartzBlockIfPossible(WorldGenLevel p_159637_, BlockPos p_159638_)
    {
        BlockState blockstate = p_159637_.getBlockState(p_159638_);
        if (blockstate.is(Blocks.NETHERRACK))
        {
            p_159637_.setBlock(p_159638_, BOPBlocks.ROSE_QUARTZ_BLOCK.defaultBlockState(), 2);
            return true;
        }
        else
        {
            return false;
        }
    }

    public static BlockState createPointedRoseQuartz(Direction p_159657_, DripstoneThickness p_159658_)
    {
        return Blocks.POINTED_DRIPSTONE.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, p_159657_).setValue(PointedDripstoneBlock.THICKNESS, p_159658_);
    }

    public static boolean isRoseQuartzBaseOrLava(BlockState p_159650_)
    {
        return isRoseQuartzBase(p_159650_) || p_159650_.is(Blocks.LAVA);
    }

    public static boolean isRoseQuartzBase(BlockState p_159663_)
    {
        return p_159663_.is(BOPBlocks.ROSE_QUARTZ_BLOCK) || p_159663_.is(Blocks.NETHERRACK);
    }

    public static boolean isEmptyOrWater(BlockState p_159665_)
    {
        return p_159665_.isAir() || p_159665_.is(Blocks.WATER);
    }

    public static boolean isEmptyOrWaterOrLava(BlockState p_159667_) {
        return p_159667_.isAir() || p_159667_.is(Blocks.WATER) || p_159667_.is(Blocks.LAVA);
    }
}
