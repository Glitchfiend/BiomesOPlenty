/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.RoseQuartzUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class LargeRoseQuartzFeature extends Feature<LargeDripstoneConfiguration>
{
    public LargeRoseQuartzFeature(Codec<LargeDripstoneConfiguration> p_159960_)
    {
        super(p_159960_);
    }

    public boolean place(FeaturePlaceContext<LargeDripstoneConfiguration> p_159967_)
    {
        WorldGenLevel worldgenlevel = p_159967_.level();
        BlockPos blockpos = p_159967_.origin();
        LargeDripstoneConfiguration largedripstoneconfiguration = p_159967_.config();
        RandomSource random = p_159967_.random();
        if (!RoseQuartzUtils.isEmptyOrWater(worldgenlevel, blockpos))
        {
            return false;
        }
        else
        {
            Optional<Column> optional = Column.scan(worldgenlevel, blockpos, largedripstoneconfiguration.floorToCeilingSearchRange, RoseQuartzUtils::isEmptyOrWater, RoseQuartzUtils::isRoseQuartzBaseOrLava);
            if (optional.isPresent() && optional.get() instanceof Column.Range)
            {
                Column.Range column$range = (Column.Range)optional.get();
                if (column$range.height() < 4)
                {
                    return false;
                }
                else
                {
                    int i = (int)((float)column$range.height() * largedripstoneconfiguration.maxColumnRadiusToCaveHeightRatio);
                    int j = Mth.clamp(i, largedripstoneconfiguration.columnRadius.getMinValue(), largedripstoneconfiguration.columnRadius.getMaxValue());
                    int k = Mth.randomBetweenInclusive(random, largedripstoneconfiguration.columnRadius.getMinValue(), j);
                    LargeRoseQuartzFeature.LargeRoseQuartz largedripstonefeature$largedripstone = makeRoseQuartz(blockpos.atY(column$range.ceiling() - 1), false, random, k, largedripstoneconfiguration.stalactiteBluntness, largedripstoneconfiguration.heightScale);
                    LargeRoseQuartzFeature.LargeRoseQuartz largedripstonefeature$largedripstone1 = makeRoseQuartz(blockpos.atY(column$range.floor() + 1), true, random, k, largedripstoneconfiguration.stalagmiteBluntness, largedripstoneconfiguration.heightScale);
                    LargeRoseQuartzFeature.WindOffsetter largedripstonefeature$windoffsetter;
                    if (largedripstonefeature$largedripstone.isSuitableForWind(largedripstoneconfiguration) && largedripstonefeature$largedripstone1.isSuitableForWind(largedripstoneconfiguration)) {
                        largedripstonefeature$windoffsetter = new LargeRoseQuartzFeature.WindOffsetter(blockpos.getY(), random, largedripstoneconfiguration.windSpeed);
                    }
                    else
                    {
                        largedripstonefeature$windoffsetter = LargeRoseQuartzFeature.WindOffsetter.noWind();
                    }

                    boolean flag = largedripstonefeature$largedripstone.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldgenlevel, largedripstonefeature$windoffsetter);
                    boolean flag1 = largedripstonefeature$largedripstone1.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(worldgenlevel, largedripstonefeature$windoffsetter);
                    if (flag)
                    {
                        largedripstonefeature$largedripstone.placeBlocks(worldgenlevel, random, largedripstonefeature$windoffsetter);
                    }

                    if (flag1)
                    {
                        largedripstonefeature$largedripstone1.placeBlocks(worldgenlevel, random, largedripstonefeature$windoffsetter);
                    }

                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    private static LargeRoseQuartzFeature.LargeRoseQuartz makeRoseQuartz(BlockPos p_159969_, boolean p_159970_, RandomSource p_159971_, int p_159972_, FloatProvider p_159973_, FloatProvider p_159974_)
    {
        return new LargeRoseQuartzFeature.LargeRoseQuartz(p_159969_, p_159970_, p_159972_, (double)p_159973_.sample(p_159971_), (double)p_159974_.sample(p_159971_));
    }

    static final class LargeRoseQuartz
    {
        private BlockPos root;
        private final boolean pointingUp;
        private int radius;
        private final double bluntness;
        private final double scale;

        LargeRoseQuartz(BlockPos p_159981_, boolean p_159982_, int p_159983_, double p_159984_, double p_159985_)
        {
            this.root = p_159981_;
            this.pointingUp = p_159982_;
            this.radius = p_159983_;
            this.bluntness = p_159984_;
            this.scale = p_159985_;
        }

        private int getHeight() {
            return this.getHeightAtRadius(0.0F);
        }

        private int getMinY() {
            return this.pointingUp ? this.root.getY() : this.root.getY() - this.getHeight();
        }

        private int getMaxY() {
            return !this.pointingUp ? this.root.getY() : this.root.getY() + this.getHeight();
        }

        boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel p_159990_, LargeRoseQuartzFeature.WindOffsetter p_159991_)
        {
            while(this.radius > 1)
            {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.mutable();
                int i = Math.min(10, this.getHeight());

                for(int j = 0; j < i; ++j)
                {
                    if (p_159990_.getBlockState(blockpos$mutableblockpos).is(Blocks.LAVA))
                    {
                        return false;
                    }

                    if (RoseQuartzUtils.isCircleMostlyEmbeddedInStone(p_159990_, p_159991_.offset(blockpos$mutableblockpos), this.radius))
                    {
                        this.root = blockpos$mutableblockpos;
                        return true;
                    }

                    blockpos$mutableblockpos.move(this.pointingUp ? Direction.DOWN : Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        private int getHeightAtRadius(float p_159988_)
        {
            return (int) RoseQuartzUtils.getRoseQuartzHeight((double)p_159988_, (double)this.radius, this.scale, this.bluntness);
        }

        void placeBlocks(WorldGenLevel p_159993_, RandomSource p_159994_, LargeRoseQuartzFeature.WindOffsetter p_159995_)
        {
            for(int i = -this.radius; i <= this.radius; ++i)
            {
                for(int j = -this.radius; j <= this.radius; ++j)
                {
                    float f = Mth.sqrt((float)(i * i + j * j));
                    if (!(f > (float)this.radius))
                    {
                        int k = this.getHeightAtRadius(f);
                        if (k > 0)
                        {
                            if ((double)p_159994_.nextFloat() < 0.2D)
                            {
                                k = (int)((float)k * Mth.randomBetween(p_159994_, 0.8F, 1.0F));
                            }

                            BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.offset(i, 0, j).mutable();
                            boolean flag = false;

                            for(int l = 0; l < k; ++l)
                            {
                                BlockPos blockpos = p_159995_.offset(blockpos$mutableblockpos);
                                if (RoseQuartzUtils.isEmptyOrWaterOrLava(p_159993_, blockpos) || p_159993_.getBlockState(blockpos).getMaterial() == Material.AMETHYST)
                                {
                                    flag = true;
                                    Block block = BOPBlocks.ROSE_QUARTZ_BLOCK.get();
                                    p_159993_.setBlock(blockpos, block.defaultBlockState(), 2);

                                    for (Direction direction : Direction.values())
                                    {
                                        if (p_159994_.nextInt(4) == 0)
                                        {
                                            BlockState cluster_state;
                                            switch (p_159994_.nextInt(6))
                                            {
                                                case 3:
                                                    cluster_state = BOPBlocks.ROSE_QUARTZ_CLUSTER.get().defaultBlockState();
                                                    break;

                                                case 2:
                                                default:
                                                    cluster_state = BOPBlocks.LARGE_ROSE_QUARTZ_BUD.get().defaultBlockState();
                                                    break;

                                                case 1:
                                                    cluster_state = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD.get().defaultBlockState();
                                                    break;

                                                case 0:
                                                    cluster_state = BOPBlocks.SMALL_ROSE_QUARTZ_BUD.get().defaultBlockState();
                                                    break;
                                            }

                                            BlockState state = cluster_state.setValue(AmethystClusterBlock.FACING, direction);
                                            if (p_159993_.getBlockState(blockpos.relative(direction)).isAir() && state.canSurvive(p_159993_, blockpos.relative(direction)))
                                            {
                                                p_159993_.setBlock(blockpos.relative(direction), state, 2);
                                            }
                                        }
                                    }
                                }
                                else if (flag && p_159993_.getBlockState(blockpos).is(Blocks.NETHERRACK))
                                {
                                    break;
                                }

                                blockpos$mutableblockpos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }

        }

        boolean isSuitableForWind(LargeDripstoneConfiguration p_159997_) {
            return this.radius >= p_159997_.minRadiusForWind && this.bluntness >= (double)p_159997_.minBluntnessForWind;
        }
    }

    static final class WindOffsetter {
        private final int originY;
        @Nullable
        private final Vec3 windSpeed;

        WindOffsetter(int p_160004_, RandomSource p_160005_, FloatProvider p_160006_) {
            this.originY = p_160004_;
            float f = p_160006_.sample(p_160005_);
            float f1 = Mth.randomBetween(p_160005_, 0.0F, (float)Math.PI);
            this.windSpeed = new Vec3((double)(Mth.cos(f1) * f), 0.0D, (double)(Mth.sin(f1) * f));
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
        }

        static LargeRoseQuartzFeature.WindOffsetter noWind() {
            return new LargeRoseQuartzFeature.WindOffsetter();
        }

        BlockPos offset(BlockPos p_160009_) {
            if (this.windSpeed == null) {
                return p_160009_;
            } else {
                int i = this.originY - p_160009_.getY();
                Vec3 vec3 = this.windSpeed.scale((double)i);
                return p_160009_.offset(vec3.x, 0.0D, vec3.z);
            }
        }
    }
}
