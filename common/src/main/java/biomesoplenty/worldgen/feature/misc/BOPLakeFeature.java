/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class BOPLakeFeature extends net.minecraft.world.level.levelgen.feature.LakeFeature
{
    private static final BlockState AIR;

    public BOPLakeFeature(Codec<net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration> featurePlaceContext)
    {
        BlockPos blockPos = featurePlaceContext.origin();
        WorldGenLevel worldGenLevel = featurePlaceContext.level();
        RandomSource randomSource = featurePlaceContext.random();
        net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration configuration = (net.minecraft.world.level.levelgen.feature.LakeFeature.Configuration)featurePlaceContext.config();
        if (blockPos.getY() <= worldGenLevel.getMinBuildHeight() + 4) {
            return false;
        } else {
            blockPos = blockPos.below(4);
            boolean[] bls = new boolean[2048];
            int i = randomSource.nextInt(4) + 4;

            for(int j = 0; j < i; ++j) {
                double d = randomSource.nextDouble() * 6.0 + 3.0;
                double e = randomSource.nextDouble() * 4.0 + 2.0;
                double f = randomSource.nextDouble() * 6.0 + 3.0;
                double g = randomSource.nextDouble() * (16.0 - d - 2.0) + 1.0 + d / 2.0;
                double h = randomSource.nextDouble() * (8.0 - e - 4.0) + 2.0 + e / 2.0;
                double k = randomSource.nextDouble() * (16.0 - f - 2.0) + 1.0 + f / 2.0;

                for(int l = 1; l < 15; ++l) {
                    for(int m = 1; m < 15; ++m) {
                        for(int n = 1; n < 7; ++n) {
                            double o = ((double)l - g) / (d / 2.0);
                            double p = ((double)n - h) / (e / 2.0);
                            double q = ((double)m - k) / (f / 2.0);
                            double r = o * o + p * p + q * q;
                            if (r < 1.0) {
                                bls[(l * 16 + m) * 8 + n] = true;
                            }
                        }
                    }
                }
            }

            BlockState blockState = configuration.fluid().getState(randomSource, blockPos);

            int t;
            boolean w;
            int s;
            int u;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        w = !bls[(s * 16 + t) * 8 + u] && (s < 15 && bls[((s + 1) * 16 + t) * 8 + u] || s > 0 && bls[((s - 1) * 16 + t) * 8 + u] || t < 15 && bls[(s * 16 + t + 1) * 8 + u] || t > 0 && bls[(s * 16 + (t - 1)) * 8 + u] || u < 7 && bls[(s * 16 + t) * 8 + u + 1] || u > 0 && bls[(s * 16 + t) * 8 + (u - 1)]);
                        if (w) {
                            BlockState blockState2 = worldGenLevel.getBlockState(blockPos.offset(s, u, t));
                            if (u >= 4 && blockState2.liquid()) {
                                return false;
                            }

                            if (u < 4 && !blockState2.isSolid() && worldGenLevel.getBlockState(blockPos.offset(s, u, t)) != blockState) {
                                return false;
                            }
                        }
                    }
                }
            }

            boolean bl2;
            for(s = 0; s < 16; ++s) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 8; ++u) {
                        if (bls[(s * 16 + t) * 8 + u]) {
                            BlockPos blockPos2 = blockPos.offset(s, u, t);
                            if (this.canReplaceBlock(worldGenLevel.getBlockState(blockPos2))) {
                                bl2 = u >= 4;
                                worldGenLevel.setBlock(blockPos2, bl2 ? AIR : blockState, 2);
                                if (bl2) {
                                    worldGenLevel.scheduleTick(blockPos2, AIR.getBlock(), 0);
                                    this.markAboveForPostProcessing(worldGenLevel, blockPos2);
                                }
                            }
                        }
                    }
                }
            }

            BlockState blockState3 = configuration.barrier().getState(randomSource, blockPos);
            if (!blockState3.isAir()) {
                for(t = 0; t < 16; ++t) {
                    for(u = 0; u < 16; ++u) {
                        for(int v = 0; v < 8; ++v) {
                            bl2 = !bls[(t * 16 + u) * 8 + v] && (t < 15 && bls[((t + 1) * 16 + u) * 8 + v] || t > 0 && bls[((t - 1) * 16 + u) * 8 + v] || u < 15 && bls[(t * 16 + u + 1) * 8 + v] || u > 0 && bls[(t * 16 + (u - 1)) * 8 + v] || v < 7 && bls[(t * 16 + u) * 8 + v + 1] || v > 0 && bls[(t * 16 + u) * 8 + (v - 1)]);
                            if (bl2 && (v < 4 || randomSource.nextInt(2) != 0)) {
                                BlockState blockState4 = worldGenLevel.getBlockState(blockPos.offset(t, v, u));
                                if (blockState4.isSolid() && !blockState4.is(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                    BlockPos blockPos3 = blockPos.offset(t, v, u);
                                    worldGenLevel.setBlock(blockPos3, blockState3, 2);
                                    this.markAboveForPostProcessing(worldGenLevel, blockPos3);
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private boolean canReplaceBlock(BlockState blockState) {
        return !blockState.is(BlockTags.FEATURES_CANNOT_REPLACE);
    }

    static {
        AIR = Blocks.CAVE_AIR.defaultBlockState();
    }
}

