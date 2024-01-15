/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.RadiantHandsPlantBlock;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RadiantHandsFeature extends Feature<NoneFeatureConfiguration>
{
    public RadiantHandsFeature(Codec<NoneFeatureConfiguration> p_67292_)
    {
        super(p_67292_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_160558_)
    {
        WorldGenLevel worldgenlevel = p_160558_.level();
        BlockPos blockpos = p_160558_.origin();
        if (isInvalidPlacementLocation(worldgenlevel, blockpos))
        {
            return false;
        }
        else
        {
            RandomSource randomsource = p_160558_.random();
            int i = 8;
            int j = 4;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int l = 0; l < i * i; ++l)
            {
                int xx = Mth.nextInt(randomsource, -i, i);
                int zz = Mth.nextInt(randomsource, -i, i);

                blockpos$mutableblockpos.set(blockpos).move(xx, Mth.nextInt(randomsource, -j, j), zz);
                if (randomsource.nextInt(4) == 0 && findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos))
                {
                    int i1 = 1 + (((i+i)-(Mth.abs(xx)+Mth.abs(zz)))/2);

                    placeWeepingVinesColumn(worldgenlevel, randomsource, blockpos$mutableblockpos, i1, 1, 8);
                }
            }

            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor p_67294_, BlockPos.MutableBlockPos p_67295_) {
        do {
            p_67295_.move(0, -1, 0);
            if (p_67294_.isOutsideBuildHeight(p_67295_)) {
                return false;
            }
        } while (p_67294_.getBlockState(p_67295_).isAir());

        p_67295_.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(LevelAccessor p_225301_, RandomSource p_225302_, BlockPos.MutableBlockPos p_225303_, int p_225304_, int p_225305_, int p_225306_) {
        for (int i = 1; i <= p_225304_; ++i) {
            if (p_225301_.isEmptyBlock(p_225303_)) {
                if (i == p_225304_ || !p_225301_.isEmptyBlock(p_225303_.above())) {
                    p_225301_.setBlock(p_225303_, BOPBlocks.RADIANT_HANDS.defaultBlockState().setValue(RadiantHandsPlantBlock.LIT, p_225302_.nextInt(7) == 0).setValue(GrowingPlantHeadBlock.AGE, Integer.valueOf(Mth.nextInt(p_225302_, p_225305_, p_225306_))), 2);
                    break;
                }

                p_225301_.setBlock(p_225303_, BOPBlocks.RADIANT_HANDS_PLANT.defaultBlockState().setValue(RadiantHandsPlantBlock.LIT, p_225302_.nextInt(7) == 0), 2);
            }

            p_225303_.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor p_67297_, BlockPos p_67298_) {
        if (!p_67297_.isEmptyBlock(p_67298_)) {
            return true;
        }
        else
        {
            BlockState blockstate = p_67297_.getBlockState(p_67298_.below());
            return !blockstate.is(Blocks.END_STONE) && !blockstate.is(BOPBlocks.ALGAL_END_STONE);
        }
    }
}