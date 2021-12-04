/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;

public class BOPNoiseChunk 
{
    private final BOPNoiseSampler sampler;
    final NoiseSettings noiseSettings;
    final int cellCountXZ;
    final int cellCountY;
    final int cellNoiseMinY;
    final int firstCellX;
    final int firstCellZ;
    private final int firstNoiseX;
    private final int firstNoiseZ;
    final List<BOPNoiseChunk.NoiseInterpolator> interpolators;
    private final BOPNoiseSampler.FlatNoiseData[][] noiseData;
    private final Long2IntMap preliminarySurfaceLevel = new Long2IntOpenHashMap();
    private final Aquifer aquifer;
    private final BOPNoiseChunk.BlockStateFiller baseNoise;
    private final BOPNoiseChunk.BlockStateFiller oreVeins;
    private final Blender blender;

    public static BOPNoiseChunk forChunk(ChunkAccess p_188774_, BOPNoiseSampler p_188775_, Supplier<BOPNoiseChunk.NoiseFiller> p_188776_, NoiseGeneratorSettings p_188777_, Aquifer.FluidPicker p_188778_, Blender p_188779_) {
        ChunkPos chunkpos = p_188774_.getPos();
        NoiseSettings noisesettings = p_188777_.noiseSettings();
        int i = Math.max(noisesettings.minY(), p_188774_.getMinBuildHeight());
        int j = Math.min(noisesettings.minY() + noisesettings.height(), p_188774_.getMaxBuildHeight());
        int k = Mth.intFloorDiv(i, noisesettings.getCellHeight());
        int l = Mth.intFloorDiv(j - i, noisesettings.getCellHeight());
        return new BOPNoiseChunk(16 / noisesettings.getCellWidth(), l, k, p_188775_, chunkpos.getMinBlockX(), chunkpos.getMinBlockZ(), p_188776_.get(), p_188777_, p_188778_, p_188779_);
    }

    public static BOPNoiseChunk forColumn(int p_188759_, int p_188760_, int p_188761_, int p_188762_, BOPNoiseSampler p_188763_, NoiseGeneratorSettings p_188764_, Aquifer.FluidPicker p_188765_) {
        return new BOPNoiseChunk(1, p_188762_, p_188761_, p_188763_, p_188759_, p_188760_, (p_188814_, p_188815_, p_188816_) -> {
            return 0.0D;
        }, p_188764_, p_188765_, Blender.empty());
    }

    private BOPNoiseChunk(int p_188733_, int p_188734_, int p_188735_, BOPNoiseSampler p_188736_, int p_188737_, int p_188738_, BOPNoiseChunk.NoiseFiller p_188739_, NoiseGeneratorSettings p_188740_, Aquifer.FluidPicker p_188741_, Blender p_188742_) {
        this.noiseSettings = p_188740_.noiseSettings();
        this.cellCountXZ = p_188733_;
        this.cellCountY = p_188734_;
        this.cellNoiseMinY = p_188735_;
        this.sampler = p_188736_;
        int i = this.noiseSettings.getCellWidth();
        this.firstCellX = Math.floorDiv(p_188737_, i);
        this.firstCellZ = Math.floorDiv(p_188738_, i);
        this.interpolators = Lists.newArrayList();
        this.firstNoiseX = QuartPos.fromBlock(p_188737_);
        this.firstNoiseZ = QuartPos.fromBlock(p_188738_);
        int j = QuartPos.fromBlock(p_188733_ * i);
        this.noiseData = new BOPNoiseSampler.FlatNoiseData[j + 1][];
        this.blender = p_188742_;

        for(int k = 0; k <= j; ++k) {
            int l = this.firstNoiseX + k;
            this.noiseData[k] = new BOPNoiseSampler.FlatNoiseData[j + 1];

            for(int i1 = 0; i1 <= j; ++i1) {
                int j1 = this.firstNoiseZ + i1;
                this.noiseData[k][i1] = p_188736_.noiseData(l, j1, p_188742_);
            }
        }

        this.aquifer = p_188736_.createAquifer(this, p_188737_, p_188738_, p_188735_, p_188734_, p_188741_, p_188740_.isAquifersEnabled());
        this.baseNoise = p_188736_.makeBaseNoiseFiller(this, p_188739_, p_188740_.isNoodleCavesEnabled());
        this.oreVeins = p_188736_.makeOreVeinifier(this, p_188740_.isOreVeinsEnabled());
    }

    public BOPNoiseSampler.FlatNoiseData noiseData(int p_188752_, int p_188753_)
    {
        return this.noiseData[p_188752_ - this.firstNoiseX][p_188753_ - this.firstNoiseZ];
    }

    public int preliminarySurfaceLevel(int p_198257_, int p_198258_) {
        return this.preliminarySurfaceLevel.computeIfAbsent(ChunkPos.asLong(QuartPos.fromBlock(p_198257_), QuartPos.fromBlock(p_198258_)), this::computePreliminarySurfaceLevel);
    }

    private int computePreliminarySurfaceLevel(long p_198250_) {
        int i = ChunkPos.getX(p_198250_);
        int j = ChunkPos.getZ(p_198250_);
        int k = i - this.firstNoiseX;
        int l = j - this.firstNoiseZ;
        int i1 = this.noiseData.length;
        TerrainInfo terraininfo;
        if (k >= 0 && l >= 0 && k < i1 && l < i1) {
            terraininfo = this.noiseData[k][l].terrainInfo();
        } else {
            terraininfo = this.sampler.noiseData(i, j, this.blender).terrainInfo();
        }

        return this.sampler.getPreliminarySurfaceLevel(QuartPos.toBlock(i), QuartPos.toBlock(j), terraininfo);
    }

    protected BOPNoiseChunk.NoiseInterpolator createNoiseInterpolator(BOPNoiseChunk.NoiseFiller p_188781_) {
        return new BOPNoiseChunk.NoiseInterpolator(p_188781_);
    }

    public Blender getBlender() {
        return this.blender;
    }

    public void initializeForFirstCellX() {
        this.interpolators.forEach((p_198252_) -> {
            p_198252_.initializeForFirstCellX();
        });
    }

    public void advanceCellX(int p_188750_) {
        this.interpolators.forEach((p_198248_) -> {
            p_198248_.advanceCellX(p_188750_);
        });
    }

    public void selectCellYZ(int p_188811_, int p_188812_) {
        this.interpolators.forEach((p_198245_) -> {
            p_198245_.selectCellYZ(p_188811_, p_188812_);
        });
    }

    public void updateForY(double p_188745_) {
        this.interpolators.forEach((p_198261_) -> {
            p_198261_.updateForY(p_188745_);
        });
    }

    public void updateForX(double p_188793_) {
        this.interpolators.forEach((p_198255_) -> {
            p_198255_.updateForX(p_188793_);
        });
    }

    public void updateForZ(double p_188806_) {
        this.interpolators.forEach((p_198241_) -> {
            p_198241_.updateForZ(p_188806_);
        });
    }

    public void swapSlices() {
        this.interpolators.forEach(BOPNoiseChunk.NoiseInterpolator::swapSlices);
    }

    public Aquifer aquifer() {
        return this.aquifer;
    }

    @Nullable
    protected BlockState updateNoiseAndGenerateBaseState(int p_188755_, int p_188756_, int p_188757_) {
        return this.baseNoise.calculate(p_188755_, p_188756_, p_188757_);
    }

    @Nullable
    protected BlockState oreVeinify(int p_188801_, int p_188802_, int p_188803_) {
        return this.oreVeins.calculate(p_188801_, p_188802_, p_188803_);
    }

    @FunctionalInterface
    public interface BlockStateFiller {
        @Nullable
        BlockState calculate(int p_188819_, int p_188820_, int p_188821_);
    }

    @FunctionalInterface
    public interface InterpolatableNoise {
        BOPNoiseChunk.Sampler instantiate(BOPNoiseChunk p_188823_);
    }

    @FunctionalInterface
    public interface NoiseFiller {
        double calculateNoise(int p_188824_, int p_188825_, int p_188826_);
    }

    public class NoiseInterpolator implements BOPNoiseChunk.Sampler {
        private double[][] slice0;
        private double[][] slice1;
        private final BOPNoiseChunk.NoiseFiller noiseFiller;
        private double noise000;
        private double noise001;
        private double noise100;
        private double noise101;
        private double noise010;
        private double noise011;
        private double noise110;
        private double noise111;
        private double valueXZ00;
        private double valueXZ10;
        private double valueXZ01;
        private double valueXZ11;
        private double valueZ0;
        private double valueZ1;
        private double value;

        NoiseInterpolator(BOPNoiseChunk.NoiseFiller p_188848_) {
            this.noiseFiller = p_188848_;
            this.slice0 = this.allocateSlice(BOPNoiseChunk.this.cellCountY, BOPNoiseChunk.this.cellCountXZ);
            this.slice1 = this.allocateSlice(BOPNoiseChunk.this.cellCountY, BOPNoiseChunk.this.cellCountXZ);
            BOPNoiseChunk.this.interpolators.add(this);
        }

        private double[][] allocateSlice(int p_188855_, int p_188856_) {
            int i = p_188856_ + 1;
            int j = p_188855_ + 1;
            double[][] adouble = new double[i][j];

            for(int k = 0; k < i; ++k) {
                adouble[k] = new double[j];
            }

            return adouble;
        }

        void initializeForFirstCellX() {
            this.fillSlice(this.slice0, BOPNoiseChunk.this.firstCellX);
        }

        void advanceCellX(int p_188853_) {
            this.fillSlice(this.slice1, BOPNoiseChunk.this.firstCellX + p_188853_ + 1);
        }

        private void fillSlice(double[][] p_188858_, int p_188859_) {
            int i = BOPNoiseChunk.this.noiseSettings.getCellWidth();
            int j = BOPNoiseChunk.this.noiseSettings.getCellHeight();

            for(int k = 0; k < BOPNoiseChunk.this.cellCountXZ + 1; ++k) {
                int l = BOPNoiseChunk.this.firstCellZ + k;

                for(int i1 = 0; i1 < BOPNoiseChunk.this.cellCountY + 1; ++i1) {
                    int j1 = i1 + BOPNoiseChunk.this.cellNoiseMinY;
                    int k1 = j1 * j;
                    double d0 = this.noiseFiller.calculateNoise(p_188859_ * i, k1, l * i);
                    p_188858_[k][i1] = d0;
                }
            }

        }

        void selectCellYZ(int p_188864_, int p_188865_) {
            this.noise000 = this.slice0[p_188865_][p_188864_];
            this.noise001 = this.slice0[p_188865_ + 1][p_188864_];
            this.noise100 = this.slice1[p_188865_][p_188864_];
            this.noise101 = this.slice1[p_188865_ + 1][p_188864_];
            this.noise010 = this.slice0[p_188865_][p_188864_ + 1];
            this.noise011 = this.slice0[p_188865_ + 1][p_188864_ + 1];
            this.noise110 = this.slice1[p_188865_][p_188864_ + 1];
            this.noise111 = this.slice1[p_188865_ + 1][p_188864_ + 1];
        }

        void updateForY(double p_188851_) {
            this.valueXZ00 = Mth.lerp(p_188851_, this.noise000, this.noise010);
            this.valueXZ10 = Mth.lerp(p_188851_, this.noise100, this.noise110);
            this.valueXZ01 = Mth.lerp(p_188851_, this.noise001, this.noise011);
            this.valueXZ11 = Mth.lerp(p_188851_, this.noise101, this.noise111);
        }

        void updateForX(double p_188862_) {
            this.valueZ0 = Mth.lerp(p_188862_, this.valueXZ00, this.valueXZ10);
            this.valueZ1 = Mth.lerp(p_188862_, this.valueXZ01, this.valueXZ11);
        }

        void updateForZ(double p_188867_) {
            this.value = Mth.lerp(p_188867_, this.valueZ0, this.valueZ1);
        }

        public double sample() {
            return this.value;
        }

        private void swapSlices() {
            double[][] adouble = this.slice0;
            this.slice0 = this.slice1;
            this.slice1 = adouble;
        }
    }

    @FunctionalInterface
    public interface Sampler {
        double sample();
    }
}
