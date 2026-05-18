/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ErodedPillarFeature extends Feature<NoneFeatureConfiguration>
{
    private static final int BASE_RADIUS = 8;
    private static final int PEAK_MIN_RADIUS = 3;

    public ErodedPillarFeature(Codec<NoneFeatureConfiguration> codec)
    {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos pos = context.origin();
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        int width = BASE_RADIUS;
        int height = random.nextInt(10) + 10;
        if (random.nextDouble() > 0.75) {
            height += random.nextInt(15) + 15;
        }

        int radius = Math.min(height + random.nextInt(7) - random.nextInt(5), width);

        for (int x = -width; x < width; x++)
        {
            for (int z = -width; z < width; z++)
            {
                for (int y = 0; y < height; y++)
                {
                    int k2 = this.heightDependentRadiusSteep(random, y, height, radius);
                    if (x < k2)
                    {
                        this.generateBlock(level, random, pos, height, x, y, z, k2);
                    }
                }
            }
        }

        this.smooth(level, pos, radius + PEAK_MIN_RADIUS, height);
        return true;
    }

    private void generateBlock(LevelAccessor level, RandomSource random,
                               BlockPos origin,
                               int pillarHeight,
                               int xOffset,
                               int yOffset,
                               int zOffset,
                               int radius
    ) {
        double distance = this.signedDistanceCircle(xOffset, zOffset, BlockPos.ZERO, radius, random);
        if (distance < 0.0) {
            BlockPos pos = origin.offset(xOffset, yOffset, zOffset);
            double discardThreshold = -6 - random.nextInt(3);
            if (distance > discardThreshold && random.nextDouble() > 0.9) {
                return;
            }

            this.setPillarBlock(pos, level, random, pillarHeight - yOffset, pillarHeight);
        }
    }

    private void setPillarBlock(BlockPos pos, LevelAccessor level, RandomSource random, int heightFromTop, int height)
    {
        BlockState state = level.getBlockState(pos);
        BlockState stateBelow = level.getBlockState(pos.below());

        if (state.isAir() || state.is(Blocks.DIRT) || state.is(Blocks.STONE) || state.is(Blocks.WATER))
        {
            // Return grass blocks below to dirt
            if (level.getBlockState(pos.below()).is(Blocks.GRASS_BLOCK))
            {
                this.setBlock(level, pos.below(), Blocks.DIRT.defaultBlockState());
            }

            if (heightFromTop <= random.nextInt((int)(height * 0.6)))
            {
                this.setBlock(level, pos, Blocks.GRASS_BLOCK.defaultBlockState());
            }
            else if (heightFromTop == height && stateBelow.is(BlockTags.SUPPORTS_VEGETATION) && random.nextDouble() > 0.5)
            {
                this.setBlock(level, pos, Blocks.DIRT.defaultBlockState());
            }
            else
            {
                this.setBlock(level, pos, Blocks.STONE.defaultBlockState());
            }
        }
    }

    private double signedDistanceCircle(int p_225089_, int p_225090_, BlockPos p_225091_, int p_225092_, RandomSource p_225093_) {
        float f = 10.0F * Mth.clamp(p_225093_.nextFloat(), 0.2F, 0.8F) / p_225092_;
        return f + Math.pow(p_225089_ - p_225091_.getX(), 2.0) + Math.pow(p_225090_ - p_225091_.getZ(), 2.0) - Math.pow(p_225092_, 2.0);
    }

    private int heightDependentRadiusSteep(RandomSource random, int height, int maxHeight, int baseRadius) {
        float randomScale = 1.0F + random.nextFloat() / 2.0F;
        float f1 = (1.0F - height / (maxHeight * randomScale)) * baseRadius;
        return Mth.ceil(f1 / 2.0F) + PEAK_MIN_RADIUS;
    }

    private static boolean isPillarState(BlockState state)
    {
        return state.is(Blocks.STONE) || state.is(Blocks.DIRT) || state.is(Blocks.GRASS_BLOCK);
    }

    private boolean belowIsAir(BlockGetter p_66046_, BlockPos p_66047_) {
        return p_66046_.getBlockState(p_66047_.below()).isAir() || p_66046_.getBlockState(p_66047_.below()).getBlock() instanceof LeavesBlock || p_66046_.getBlockState(p_66047_.below()).getBlock() instanceof VegetationBlock || p_66046_.getBlockState(p_66047_.below()).is(BlockTags.REPLACEABLE);
    }

    private void smooth(LevelAccessor level, BlockPos pos, int radius, int height)
    {
        int i = radius;

        for (int j = -i; j <= i; j++) {
            for (int k = -i; k <= i; k++) {
                for (int l = 0; l <= height; l++) {
                    BlockPos blockpos = pos.offset(j, l, k);
                    BlockState blockstate = level.getBlockState(blockpos);
                    if (isPillarState(blockstate)) {
                        if (this.belowIsAir(level, blockpos)) {
                            this.setBlock(level, blockpos, Blocks.AIR.defaultBlockState());
                            this.setBlock(level, blockpos.above(), Blocks.AIR.defaultBlockState());
                        } else if (isPillarState(blockstate)) {
                            BlockState[] ablockstate = new BlockState[]{
                                    level.getBlockState(blockpos.west()),
                                    level.getBlockState(blockpos.east()),
                                    level.getBlockState(blockpos.north()),
                                    level.getBlockState(blockpos.south())
                            };
                            int i1 = 0;

                            for (BlockState blockstate1 : ablockstate) {
                                if (!isPillarState(blockstate1)) {
                                    i1++;
                                }
                            }

                            if (i1 >= 3) {
                                this.setBlock(level, blockpos, Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                }
            }
        }
    }
}
