/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HangingFleshTendonFeature extends Feature<NoneFeatureConfiguration>
{
    private static final Direction[] DIRECTIONS = Direction.values();

    public HangingFleshTendonFeature(Codec<NoneFeatureConfiguration> p_67375_)
    {
        super(p_67375_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_160661_)
    {
        WorldGenLevel worldgenlevel = p_160661_.level();
        BlockPos blockpos = p_160661_.origin();
        Random random = p_160661_.random();
        if (!worldgenlevel.isEmptyBlock(blockpos))
        {
            return false;
        }
        else
        {
            BlockState blockstate = worldgenlevel.getBlockState(blockpos.above());
            if (!blockstate.is(ModTags.Blocks.FLESH))
            {
                return false;
            }
            else
            {
                this.placeFleshTendons(worldgenlevel, random, blockpos);
                return true;
            }
        }
    }

    private void placeFleshTendons(LevelAccessor p_67400_, Random p_67401_, BlockPos p_67402_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i)
        {
            blockpos$mutableblockpos.setWithOffset(p_67402_, p_67401_.nextInt(8) - p_67401_.nextInt(8), p_67401_.nextInt(8) - p_67401_.nextInt(8), p_67401_.nextInt(8) - p_67401_.nextInt(8));
            if (p_67400_.isEmptyBlock(blockpos$mutableblockpos))
            {
                BlockState blockstate = p_67400_.getBlockState(blockpos$mutableblockpos.above());
                if (blockstate.is(ModTags.Blocks.FLESH))
                {
                    int j = Mth.nextInt(p_67401_, 1, 4);
                    placeFleshTendonColumn(p_67400_, p_67401_, blockpos$mutableblockpos, j);
                }
            }
        }

    }

    public static void placeFleshTendonColumn(LevelAccessor p_67377_, Random p_67378_, BlockPos.MutableBlockPos p_67379_, int p_67380_) {
        for(int i = 0; i <= p_67380_; ++i)
        {
            if (p_67377_.isEmptyBlock(p_67379_))
            {
                if (i == p_67380_ || !p_67377_.isEmptyBlock(p_67379_.below()))
                {
                    p_67377_.setBlock(p_67379_, BOPBlocks.FLESH_TENDONS.defaultBlockState(), 2);
                    break;
                }

                p_67377_.setBlock(p_67379_, BOPBlocks.FLESH_TENDONS_STRAND.defaultBlockState(), 2);
            }

            p_67379_.move(Direction.DOWN);
        }
    }
}