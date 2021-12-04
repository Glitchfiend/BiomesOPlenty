/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nullable;

import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import net.minecraft.SharedConstants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NoiseUtils;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;

public class BOPNoiseSampler implements BOPClimate.Sampler
{
    private static final float ORE_VEIN_RARITY = 1.0F;
    private static final float ORE_THICKNESS = 0.08F;
    private static final float VEININESS_THRESHOLD = 0.4F;
    private static final double VEININESS_FREQUENCY = 1.5D;
    private static final int EDGE_ROUNDOFF_BEGIN = 20;
    private static final double MAX_EDGE_ROUNDOFF = 0.2D;
    private static final float VEIN_SOLIDNESS = 0.7F;
    private static final float MIN_RICHNESS = 0.1F;
    private static final float MAX_RICHNESS = 0.3F;
    private static final float MAX_RICHNESS_THRESHOLD = 0.6F;
    private static final float CHANCE_OF_RAW_ORE_BLOCK = 0.02F;
    private static final float SKIP_ORE_IF_GAP_NOISE_IS_BELOW = -0.3F;
    private static final double NOODLE_SPACING_AND_STRAIGHTNESS = 1.5D;
    protected final NoiseSettings noiseSettings;
    private final boolean isNoiseCavesEnabled;
    private final BOPNoiseChunk.InterpolatableNoise baseNoise;
    public final BlendedNoise blendedNoise;
    @Nullable
    public final SimplexNoise islandNoise;
    private final NormalNoise jaggedNoise;
    private final NormalNoise barrierNoise;
    private final NormalNoise fluidLevelFloodednessNoise;
    private final NormalNoise fluidLevelSpreadNoise;
    private final NormalNoise lavaNoise;
    private final NormalNoise layerNoiseSource;
    private final NormalNoise pillarNoiseSource;
    private final NormalNoise pillarRarenessModulator;
    private final NormalNoise pillarThicknessModulator;
    private final NormalNoise spaghetti2DNoiseSource;
    private final NormalNoise spaghetti2DElevationModulator;
    private final NormalNoise spaghetti2DRarityModulator;
    private final NormalNoise spaghetti2DThicknessModulator;
    private final NormalNoise spaghetti3DNoiseSource1;
    private final NormalNoise spaghetti3DNoiseSource2;
    private final NormalNoise spaghetti3DRarityModulator;
    private final NormalNoise spaghetti3DThicknessModulator;
    private final NormalNoise spaghettiRoughnessNoise;
    private final NormalNoise spaghettiRoughnessModulator;
    private final NormalNoise bigEntranceNoiseSource;
    private final NormalNoise cheeseNoiseSource;
    private final NormalNoise temperatureNoise;
    private final NormalNoise humidityNoise;
    private final NormalNoise continentalnessNoise;
    private final NormalNoise erosionNoise;
    private final NormalNoise weirdnessNoise;
    private final NormalNoise uniquenessNoise;
    private final NormalNoise offsetNoise;
    private final NormalNoise gapNoise;
    private final BOPNoiseChunk.InterpolatableNoise veininess;
    private final BOPNoiseChunk.InterpolatableNoise veinA;
    private final BOPNoiseChunk.InterpolatableNoise veinB;
    private final BOPNoiseChunk.InterpolatableNoise noodleToggle;
    private final BOPNoiseChunk.InterpolatableNoise noodleThickness;
    private final BOPNoiseChunk.InterpolatableNoise noodleRidgeA;
    private final BOPNoiseChunk.InterpolatableNoise noodleRidgeB;
    private final PositionalRandomFactory aquiferPositionalRandomFactory;
    private final PositionalRandomFactory oreVeinsPositionalRandomFactory;
    private final PositionalRandomFactory depthBasedLayerPositionalRandomFactory;
    private final List<BOPClimate.ParameterPoint> spawnTarget = (new BOPOverworldBiomeBuilder()).spawnTarget();
    private final boolean amplified;

    public BOPNoiseSampler(NoiseSettings p_188950_, boolean p_188951_, long p_188952_, Registry<NormalNoise.NoiseParameters> p_188953_, WorldgenRandom.Algorithm p_188954_) {
        this.noiseSettings = p_188950_;
        this.isNoiseCavesEnabled = p_188951_;
        this.baseNoise = (p_189039_) -> {
            return p_189039_.createNoiseInterpolator((p_189042_, p_189043_, p_189044_) -> {
                return this.calculateBaseNoise(p_189042_, p_189043_, p_189044_, p_189039_.noiseData(QuartPos.fromBlock(p_189042_), QuartPos.fromBlock(p_189044_)).terrainInfo(), p_189039_.getBlender());
            });
        };
        if (p_188950_.islandNoiseOverride()) {
            RandomSource randomsource = p_188954_.newInstance(p_188952_);
            randomsource.consumeCount(17292);
            this.islandNoise = new SimplexNoise(randomsource);
        } else {
            this.islandNoise = null;
        }

        this.amplified = p_188950_.isAmplified();
        int i1 = p_188950_.minY();
        int i = Stream.of(BOPNoiseSampler.VeinType.values()).mapToInt((p_189087_) -> {
            return p_189087_.minY;
        }).min().orElse(i1);
        int j = Stream.of(BOPNoiseSampler.VeinType.values()).mapToInt((p_189061_) -> {
            return p_189061_.maxY;
        }).max().orElse(i1);
        float f = 4.0F;
        double d0 = 2.6666666666666665D;
        int k = i1 + 4;
        int l = i1 + p_188950_.height();
        boolean flag = p_188950_.largeBiomes();
        PositionalRandomFactory positionalrandomfactory = p_188954_.newInstance(p_188952_).forkPositional();
        if (p_188954_ != WorldgenRandom.Algorithm.LEGACY) {
            this.blendedNoise = new BlendedNoise(positionalrandomfactory.fromHashOf(new ResourceLocation("terrain")), p_188950_.noiseSamplingSettings(), p_188950_.getCellWidth(), p_188950_.getCellHeight());
            this.temperatureNoise = Noises.instantiate(p_188953_, positionalrandomfactory, flag ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE);
            this.humidityNoise = Noises.instantiate(p_188953_, positionalrandomfactory, flag ? Noises.VEGETATION_LARGE : Noises.VEGETATION);
            this.offsetNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SHIFT);
        } else {
            this.blendedNoise = new BlendedNoise(p_188954_.newInstance(p_188952_), p_188950_.noiseSamplingSettings(), p_188950_.getCellWidth(), p_188950_.getCellHeight());
            this.temperatureNoise = NormalNoise.createLegacyNetherBiome(p_188954_.newInstance(p_188952_), new NormalNoise.NoiseParameters(-7, 1.0D, 1.0D));
            this.humidityNoise = NormalNoise.createLegacyNetherBiome(p_188954_.newInstance(p_188952_ + 1L), new NormalNoise.NoiseParameters(-7, 1.0D, 1.0D));
            this.offsetNoise = NormalNoise.create(positionalrandomfactory.fromHashOf(Noises.SHIFT.location()), new NormalNoise.NoiseParameters(0, 0.0D));
        }

        this.aquiferPositionalRandomFactory = positionalrandomfactory.fromHashOf(new ResourceLocation("aquifer")).forkPositional();
        this.oreVeinsPositionalRandomFactory = positionalrandomfactory.fromHashOf(new ResourceLocation("ore")).forkPositional();
        this.depthBasedLayerPositionalRandomFactory = positionalrandomfactory.fromHashOf(new ResourceLocation("depth_based_layer")).forkPositional();
        this.barrierNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.AQUIFER_BARRIER);
        this.fluidLevelFloodednessNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS);
        this.lavaNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.AQUIFER_LAVA);
        this.fluidLevelSpreadNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.AQUIFER_FLUID_LEVEL_SPREAD);
        this.pillarNoiseSource = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.PILLAR);
        this.pillarRarenessModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.PILLAR_RARENESS);
        this.pillarThicknessModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.PILLAR_THICKNESS);
        this.spaghetti2DNoiseSource = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_2D);
        this.spaghetti2DElevationModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_2D_ELEVATION);
        this.spaghetti2DRarityModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_2D_MODULATOR);
        this.spaghetti2DThicknessModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_2D_THICKNESS);
        this.spaghetti3DNoiseSource1 = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_3D_1);
        this.spaghetti3DNoiseSource2 = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_3D_2);
        this.spaghetti3DRarityModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_3D_RARITY);
        this.spaghetti3DThicknessModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_3D_THICKNESS);
        this.spaghettiRoughnessNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_ROUGHNESS);
        this.spaghettiRoughnessModulator = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.SPAGHETTI_ROUGHNESS_MODULATOR);
        this.bigEntranceNoiseSource = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.CAVE_ENTRANCE);
        this.layerNoiseSource = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.CAVE_LAYER);
        this.cheeseNoiseSource = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.CAVE_CHEESE);
        this.continentalnessNoise = Noises.instantiate(p_188953_, positionalrandomfactory, flag ? Noises.CONTINENTALNESS_LARGE : Noises.CONTINENTALNESS);
        this.erosionNoise = Noises.instantiate(p_188953_, positionalrandomfactory, flag ? Noises.EROSION_LARGE : Noises.EROSION);
        this.weirdnessNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.RIDGE);
        this.uniquenessNoise = Noises.instantiate(p_188953_, positionalrandomfactory, BOPNoises.UNIQUENESS);
        this.veininess = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.ORE_VEININESS), i, j, 0, 1.5D);
        this.veinA = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.ORE_VEIN_A), i, j, 0, 4.0D);
        this.veinB = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.ORE_VEIN_B), i, j, 0, 4.0D);
        this.gapNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.ORE_GAP);
        this.noodleToggle = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.NOODLE), k, l, -1, 1.0D);
        this.noodleThickness = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.NOODLE_THICKNESS), k, l, 0, 1.0D);
        this.noodleRidgeA = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.NOODLE_RIDGE_A), k, l, 0, 2.6666666666666665D);
        this.noodleRidgeB = yLimitedInterpolatableNoise(Noises.instantiate(p_188953_, positionalrandomfactory, Noises.NOODLE_RIDGE_B), k, l, 0, 2.6666666666666665D);
        this.jaggedNoise = Noises.instantiate(p_188953_, positionalrandomfactory, Noises.JAGGED);
    }

    private static BOPNoiseChunk.InterpolatableNoise yLimitedInterpolatableNoise(NormalNoise p_189069_, int p_189070_, int p_189071_, int p_189072_, double p_189073_) {
        BOPNoiseChunk.NoiseFiller BOPNoiseChunk$noisefiller = (p_189002_, p_189003_, p_189004_) -> {
            return p_189003_ <= p_189071_ && p_189003_ >= p_189070_ ? p_189069_.getValue((double)p_189002_ * p_189073_, (double)p_189003_ * p_189073_, (double)p_189004_ * p_189073_) : (double)p_189072_;
        };
        return (p_189018_) -> {
            return p_189018_.createNoiseInterpolator(BOPNoiseChunk$noisefiller);
        };
    }

    private double calculateBaseNoise(int p_188991_, int p_188992_, int p_188993_, TerrainInfo p_188994_, Blender p_188995_) {
        double d0 = this.blendedNoise.calculateNoise(p_188991_, p_188992_, p_188993_);
        boolean flag = !this.isNoiseCavesEnabled;
        return this.calculateBaseNoise(p_188991_, p_188992_, p_188993_, p_188994_, d0, flag, true, p_188995_);
    }

    private double calculateBaseNoise(int p_188982_, int p_188983_, int p_188984_, TerrainInfo p_188985_, double p_188986_, boolean p_188987_, boolean p_188988_, Blender p_188989_) {
        double d0;
        if (this.islandNoise != null) {
            d0 = ((double)TheEndBiomeSource.getHeightValue(this.islandNoise, p_188982_ / 8, p_188984_ / 8) - 8.0D) / 128.0D;
        } else {
            double d1 = p_188988_ ? this.sampleJaggedNoise(p_188985_.jaggedness(), (double)p_188982_, (double)p_188984_) : 0.0D;
            double d2 = (this.computeBaseDensity(p_188983_, p_188985_) + d1) * p_188985_.factor();
            d0 = d2 * (double)(d2 > 0.0D ? 4 : 1);
        }

        double d16 = d0 + p_188986_;
        double d17 = 1.5625D;
        double d3;
        double d4;
        double d5;
        if (!p_188987_ && !(d16 < -64.0D)) {
            double d6 = d16 - 1.5625D;
            boolean flag = d6 < 0.0D;
            double d7 = this.getBigEntrances(p_188982_, p_188983_, p_188984_);
            double d8 = this.spaghettiRoughness(p_188982_, p_188983_, p_188984_);
            double d9 = this.getSpaghetti3D(p_188982_, p_188983_, p_188984_);
            double d10 = Math.min(d7, d9 + d8);
            if (flag) {
                d3 = d16;
                d4 = d10 * 5.0D;
                d5 = -64.0D;
            } else {
                double d11 = this.getLayerizedCaverns(p_188982_, p_188983_, p_188984_);
                if (d11 > 64.0D) {
                    d3 = 64.0D;
                } else {
                    double d12 = this.cheeseNoiseSource.getValue((double)p_188982_, (double)p_188983_ / 1.5D, (double)p_188984_);
                    double d13 = Mth.clamp(d12 + 0.27D, -1.0D, 1.0D);
                    double d14 = d6 * 1.28D;
                    double d15 = d13 + Mth.clampedLerp(0.5D, 0.0D, d14);
                    d3 = d15 + d11;
                }

                double d19 = this.getSpaghetti2D(p_188982_, p_188983_, p_188984_);
                d4 = Math.min(d10, d19 + d8);
                d5 = this.getPillars(p_188982_, p_188983_, p_188984_);
            }
        } else {
            d3 = d16;
            d4 = 64.0D;
            d5 = -64.0D;
        }

        double d18 = Math.max(Math.min(d3, d4), d5);
        d18 = this.applySlide(d18, p_188983_ / this.noiseSettings.getCellHeight());
        d18 = p_188989_.blendDensity(p_188982_, p_188983_, p_188984_, d18);
        return Mth.clamp(d18, -64.0D, 64.0D);
    }

    private double sampleJaggedNoise(double p_189099_, double p_189100_, double p_189101_) {
        if (p_189099_ == 0.0D) {
            return 0.0D;
        } else {
            float f = 1500.0F;
            double d0 = this.jaggedNoise.getValue(p_189100_ * 1500.0D, 0.0D, p_189101_ * 1500.0D);
            return d0 > 0.0D ? p_189099_ * d0 : p_189099_ / 2.0D * d0;
        }
    }

    private double computeBaseDensity(int p_189014_, TerrainInfo p_189015_) {
        double d0 = 1.0D - (double)p_189014_ / 128.0D;
        return d0 + p_189015_.offset();
    }

    protected double applySlide(double p_158668_, int p_158669_) {
        int i = p_158669_ - this.noiseSettings.getMinCellY();
        p_158668_ = this.noiseSettings.topSlideSettings().applySlide(p_158668_, this.noiseSettings.getCellCountY() - i);
        return this.noiseSettings.bottomSlideSettings().applySlide(p_158668_, i);
    }

    protected BOPNoiseChunk.BlockStateFiller makeBaseNoiseFiller(BOPNoiseChunk p_189054_, BOPNoiseChunk.NoiseFiller p_189055_, boolean p_189056_) {
        BOPNoiseChunk.Sampler BOPNoiseChunk$sampler = this.baseNoise.instantiate(p_189054_);
        BOPNoiseChunk.Sampler BOPNoiseChunk$sampler1 = p_189056_ ? this.noodleToggle.instantiate(p_189054_) : () -> {
            return -1.0D;
        };
        BOPNoiseChunk.Sampler BOPNoiseChunk$sampler2 = p_189056_ ? this.noodleThickness.instantiate(p_189054_) : () -> {
            return 0.0D;
        };
        BOPNoiseChunk.Sampler BOPNoiseChunk$sampler3 = p_189056_ ? this.noodleRidgeA.instantiate(p_189054_) : () -> {
            return 0.0D;
        };
        BOPNoiseChunk.Sampler BOPNoiseChunk$sampler4 = p_189056_ ? this.noodleRidgeB.instantiate(p_189054_) : () -> {
            return 0.0D;
        };
        return (p_189035_, p_189036_, p_189037_) -> {
            double d0 = BOPNoiseChunk$sampler.sample();
            double $$11 = Mth.clamp(d0 * 0.64D, -1.0D, 1.0D);
            $$11 = $$11 / 2.0D - $$11 * $$11 * $$11 / 24.0D;
            if (BOPNoiseChunk$sampler1.sample() >= 0.0D) {
                double d2 = 0.05D;
                double d3 = 0.1D;
                double d4 = Mth.clampedMap(BOPNoiseChunk$sampler2.sample(), -1.0D, 1.0D, 0.05D, 0.1D);
                double d5 = Math.abs(1.5D * BOPNoiseChunk$sampler3.sample()) - d4;
                double d6 = Math.abs(1.5D * BOPNoiseChunk$sampler4.sample()) - d4;
                $$11 = Math.min($$11, Math.max(d5, d6));
            }

            $$11 += p_189055_.calculateNoise(p_189035_, p_189036_, p_189037_);
            return p_189054_.aquifer().computeSubstance(p_189035_, p_189036_, p_189037_, d0, $$11);
        };
    }

    protected BOPNoiseChunk.BlockStateFiller makeOreVeinifier(BOPNoiseChunk p_189058_, boolean p_189059_) {
        if (!p_189059_) {
            return (p_189129_, p_189130_, p_189131_) -> {
                return null;
            };
        } else {
            BOPNoiseChunk.Sampler BOPNoiseChunk$sampler = this.veininess.instantiate(p_189058_);
            BOPNoiseChunk.Sampler BOPNoiseChunk$sampler1 = this.veinA.instantiate(p_189058_);
            BOPNoiseChunk.Sampler BOPNoiseChunk$sampler2 = this.veinB.instantiate(p_189058_);
            BlockState blockstate = null;
            return (p_189024_, p_189025_, p_189026_) -> {
                RandomSource randomsource = this.oreVeinsPositionalRandomFactory.at(p_189024_, p_189025_, p_189026_);
                double d0 = BOPNoiseChunk$sampler.sample();
                BOPNoiseSampler.VeinType BOPNoiseSampler$veintype = this.getVeinType(d0, p_189025_);
                if (BOPNoiseSampler$veintype == null) {
                    return blockstate;
                } else if (randomsource.nextFloat() > 0.7F) {
                    return blockstate;
                } else if (this.isVein(BOPNoiseChunk$sampler1.sample(), BOPNoiseChunk$sampler2.sample())) {
                    double d1 = Mth.clampedMap(Math.abs(d0), (double)0.4F, (double)0.6F, (double)0.1F, (double)0.3F);
                    if ((double)randomsource.nextFloat() < d1 && this.gapNoise.getValue((double)p_189024_, (double)p_189025_, (double)p_189026_) > (double)-0.3F) {
                        return randomsource.nextFloat() < 0.02F ? BOPNoiseSampler$veintype.rawOreBlock : BOPNoiseSampler$veintype.ore;
                    } else {
                        return BOPNoiseSampler$veintype.filler;
                    }
                } else {
                    return blockstate;
                }
            };
        }
    }

    protected int getPreliminarySurfaceLevel(int p_189006_, int p_189007_, TerrainInfo p_189008_) {
        for(int i = this.noiseSettings.getMinCellY() + this.noiseSettings.getCellCountY(); i >= this.noiseSettings.getMinCellY(); --i) {
            int j = i * this.noiseSettings.getCellHeight();
            double d0 = -0.703125D;
            double d1 = this.calculateBaseNoise(p_189006_, j, p_189007_, p_189008_, -0.703125D, true, false, Blender.empty());
            if (d1 > 0.390625D) {
                return j;
            }
        }

        return Integer.MAX_VALUE;
    }

    protected Aquifer createAquifer(BOPNoiseChunk p_189046_, int p_189047_, int p_189048_, int p_189049_, int p_189050_, Aquifer.FluidPicker p_189051_, boolean p_189052_) {
        if (!p_189052_) {
            return Aquifer.createDisabled(p_189051_);
        } else {
            int i = SectionPos.blockToSectionCoord(p_189047_);
            int j = SectionPos.blockToSectionCoord(p_189048_);
            return BOPNoiseBasedAquifer.create(p_189046_, new ChunkPos(i, j), this.barrierNoise, this.fluidLevelFloodednessNoise, this.fluidLevelSpreadNoise, this.lavaNoise, this.aquiferPositionalRandomFactory, p_189049_ * this.noiseSettings.getCellHeight(), p_189050_ * this.noiseSettings.getCellHeight(), p_189051_);
        }
    }

    @VisibleForDebug
    public BOPNoiseSampler.FlatNoiseData noiseData(int x, int z, Blender p_189012_) {
        double shiftedX = (double)x + this.getOffset(x, 0, z);
        double shiftedZ = (double)z + this.getOffset(z, x, 0);
        double continentalness = this.getContinentalness(shiftedX, 0.0D, shiftedZ);
        double weirdness = this.getWeirdness(shiftedX, 0.0D, shiftedZ);
        double uniqueness = this.getUniqueness(shiftedX, 0.0D, shiftedZ);
        double erosion = this.getErosion(shiftedX, 0.0D, shiftedZ);
        TerrainInfo terraininfo = this.terrainInfo(QuartPos.toBlock(x), QuartPos.toBlock(z), (float)continentalness, (float)weirdness, (float)erosion, p_189012_);
        return new BOPNoiseSampler.FlatNoiseData(shiftedX, shiftedZ, continentalness, weirdness, uniqueness, erosion, terraininfo);
    }

    public BOPClimate.TargetPoint sampleBOP(int p_189133_, int p_189134_, int p_189135_) {
        return this.target(p_189133_, p_189134_, p_189135_, this.noiseData(p_189133_, p_189135_, Blender.empty()));
    }

    @VisibleForDebug
    public BOPClimate.TargetPoint target(int p_188977_, int p_188978_, int p_188979_, BOPNoiseSampler.FlatNoiseData p_188980_) {
        double d0 = p_188980_.shiftedX();
        double d1 = (double)p_188978_ + this.getOffset(p_188978_, p_188979_, p_188977_);
        double d2 = p_188980_.shiftedZ();
        double d3 = this.computeBaseDensity(QuartPos.toBlock(p_188978_), p_188980_.terrainInfo());
        return BOPClimate.target((float)this.getTemperature(d0, d1, d2), (float)this.getHumidity(d0, d1, d2), (float)p_188980_.continentalness(), (float)p_188980_.erosion(), (float)d3, (float)p_188980_.weirdness(), (float)p_188980_.uniqueness());
    }

    public TerrainInfo terrainInfo(int p_188966_, int p_188967_, float p_188968_, float p_188969_, float p_188970_, Blender p_188971_) {
        TerrainShaper terrainshaper = this.noiseSettings.terrainShaper();
        TerrainShaper.Point terrainshaper$point = terrainshaper.makePoint(p_188968_, p_188970_, p_188969_);
        float f = terrainshaper.offset(terrainshaper$point);
        float f1 = terrainshaper.factor(terrainshaper$point);
        float f2 = terrainshaper.jaggedness(terrainshaper$point);
        TerrainInfo terraininfo = new TerrainInfo((double)f, (double)f1, (double)f2);
        return p_188971_.blendOffsetAndFactor(p_188966_, p_188967_, terraininfo);
    }

    public BlockPos findSpawnPosition() {
        return BOPClimate.findSpawnPosition(this.spawnTarget, this);
    }

    @VisibleForDebug
    public double getOffset(int p_188973_, int p_188974_, int p_188975_) {
        return this.offsetNoise.getValue((double)p_188973_, (double)p_188974_, (double)p_188975_) * 4.0D;
    }

    private double getTemperature(double p_189108_, double p_189109_, double p_189110_) {
        return this.temperatureNoise.getValue(p_189108_, 0.0D, p_189110_);
    }

    private double getHumidity(double p_189117_, double p_189118_, double p_189119_) {
        return this.humidityNoise.getValue(p_189117_, 0.0D, p_189119_);
    }

    @VisibleForDebug
    public double getContinentalness(double p_188962_, double p_188963_, double p_188964_) {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise) {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(p_188962_)), QuartPos.toSection(Mth.floor(p_188964_))))) {
                return -1.0D;
            } else {
                double d1 = Mth.frac(p_188962_ / 2048.0D) * 2.0D - 1.0D;
                return d1 * d1 * (double)(d1 < 0.0D ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise) {
            double d0 = p_188962_ * 0.005D;
            return Math.sin(d0 + 0.5D * Math.sin(d0));
        } else {
            return this.continentalnessNoise.getValue(p_188962_, p_188963_, p_188964_);
        }
    }

    @VisibleForDebug
    public double getErosion(double p_189076_, double p_189077_, double p_189078_) {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise) {
            if (SharedConstants.debugVoidTerrain(new ChunkPos(QuartPos.toSection(Mth.floor(p_189076_)), QuartPos.toSection(Mth.floor(p_189078_))))) {
                return -1.0D;
            } else {
                double d1 = Mth.frac(p_189078_ / 256.0D) * 2.0D - 1.0D;
                return d1 * d1 * (double)(d1 < 0.0D ? -1 : 1);
            }
        } else if (SharedConstants.debugGenerateStripedTerrainWithoutNoise) {
            double d0 = p_189078_ * 0.005D;
            return Math.sin(d0 + 0.5D * Math.sin(d0));
        } else {
            return this.erosionNoise.getValue(p_189076_, p_189077_, p_189078_);
        }
    }

    @VisibleForDebug
    public double getWeirdness(double x, double y, double z) {
        return this.weirdnessNoise.getValue(x, y, z);
    }

    @VisibleForDebug
    public double getUniqueness(double x, double y, double z) {
        return this.uniquenessNoise.getValue(x, y, z);
    }

    private double getBigEntrances(int p_189083_, int p_189084_, int p_189085_) {
        double d0 = 0.75D;
        double d1 = 0.5D;
        double d2 = 0.37D;
        double d3 = this.bigEntranceNoiseSource.getValue((double)p_189083_ * 0.75D, (double)p_189084_ * 0.5D, (double)p_189085_ * 0.75D) + 0.37D;
        int i = -10;
        double d4 = (double)(p_189084_ - -10) / 40.0D;
        double d5 = 0.3D;
        return d3 + Mth.clampedLerp(0.3D, 0.0D, d4);
    }

    private double getPillars(int p_189094_, int p_189095_, int p_189096_) {
        double d0 = 0.0D;
        double d1 = 2.0D;
        double d2 = NoiseUtils.sampleNoiseAndMapToRange(this.pillarRarenessModulator, (double)p_189094_, (double)p_189095_, (double)p_189096_, 0.0D, 2.0D);
        double d3 = 0.0D;
        double d4 = 1.1D;
        double d5 = NoiseUtils.sampleNoiseAndMapToRange(this.pillarThicknessModulator, (double)p_189094_, (double)p_189095_, (double)p_189096_, 0.0D, 1.1D);
        d5 = Math.pow(d5, 3.0D);
        double d6 = 25.0D;
        double d7 = 0.3D;
        double d8 = this.pillarNoiseSource.getValue((double)p_189094_ * 25.0D, (double)p_189095_ * 0.3D, (double)p_189096_ * 25.0D);
        d8 = d5 * (d8 * 2.0D - d2);
        return d8 > 0.03D ? d8 : Double.NEGATIVE_INFINITY;
    }

    private double getLayerizedCaverns(int p_189103_, int p_189104_, int p_189105_) {
        double d0 = this.layerNoiseSource.getValue((double)p_189103_, (double)(p_189104_ * 8), (double)p_189105_);
        return Mth.square(d0) * 4.0D;
    }

    private double getSpaghetti3D(int p_189112_, int p_189113_, int p_189114_) {
        double d0 = this.spaghetti3DRarityModulator.getValue((double)(p_189112_ * 2), (double)p_189113_, (double)(p_189114_ * 2));
        double d1 = BOPNoiseSampler.QuantizedSpaghettiRarity.getSpaghettiRarity3D(d0);
        double d2 = 0.065D;
        double d3 = 0.088D;
        double d4 = NoiseUtils.sampleNoiseAndMapToRange(this.spaghetti3DThicknessModulator, (double)p_189112_, (double)p_189113_, (double)p_189114_, 0.065D, 0.088D);
        double d5 = sampleWithRarity(this.spaghetti3DNoiseSource1, (double)p_189112_, (double)p_189113_, (double)p_189114_, d1);
        double d6 = Math.abs(d1 * d5) - d4;
        double d7 = sampleWithRarity(this.spaghetti3DNoiseSource2, (double)p_189112_, (double)p_189113_, (double)p_189114_, d1);
        double d8 = Math.abs(d1 * d7) - d4;
        return clampToUnit(Math.max(d6, d8));
    }

    private double getSpaghetti2D(int p_189121_, int p_189122_, int p_189123_) {
        double d0 = this.spaghetti2DRarityModulator.getValue((double)(p_189121_ * 2), (double)p_189122_, (double)(p_189123_ * 2));
        double d1 = BOPNoiseSampler.QuantizedSpaghettiRarity.getSphaghettiRarity2D(d0);
        double d2 = 0.6D;
        double d3 = 1.3D;
        double d4 = NoiseUtils.sampleNoiseAndMapToRange(this.spaghetti2DThicknessModulator, (double)(p_189121_ * 2), (double)p_189122_, (double)(p_189123_ * 2), 0.6D, 1.3D);
        double d5 = sampleWithRarity(this.spaghetti2DNoiseSource, (double)p_189121_, (double)p_189122_, (double)p_189123_, d1);
        double d6 = 0.083D;
        double d7 = Math.abs(d1 * d5) - 0.083D * d4;
        int i = this.noiseSettings.getMinCellY();
        int j = 8;
        double d8 = NoiseUtils.sampleNoiseAndMapToRange(this.spaghetti2DElevationModulator, (double)p_189121_, 0.0D, (double)p_189123_, (double)i, 8.0D);
        double d9 = Math.abs(d8 - (double)p_189122_ / 8.0D) - 1.0D * d4;
        d9 = d9 * d9 * d9;
        return clampToUnit(Math.max(d9, d7));
    }

    private double spaghettiRoughness(int p_189125_, int p_189126_, int p_189127_) {
        double d0 = NoiseUtils.sampleNoiseAndMapToRange(this.spaghettiRoughnessModulator, (double)p_189125_, (double)p_189126_, (double)p_189127_, 0.0D, 0.1D);
        return (0.4D - Math.abs(this.spaghettiRoughnessNoise.getValue((double)p_189125_, (double)p_189126_, (double)p_189127_))) * d0;
    }

    public PositionalRandomFactory getDepthBasedLayerPositionalRandom() {
        return this.depthBasedLayerPositionalRandomFactory;
    }

    private static double clampToUnit(double p_188957_) {
        return Mth.clamp(p_188957_, -1.0D, 1.0D);
    }

    private static double sampleWithRarity(NormalNoise p_189063_, double p_189064_, double p_189065_, double p_189066_, double p_189067_) {
        return p_189063_.getValue(p_189064_ / p_189067_, p_189065_ / p_189067_, p_189066_ / p_189067_);
    }

    private boolean isVein(double p_188959_, double p_188960_) {
        double d0 = Math.abs(1.0D * p_188959_) - (double)0.08F;
        double d1 = Math.abs(1.0D * p_188960_) - (double)0.08F;
        return Math.max(d0, d1) < 0.0D;
    }

    @Nullable
    private BOPNoiseSampler.VeinType getVeinType(double p_189080_, int p_189081_) {
        BOPNoiseSampler.VeinType BOPNoiseSampler$veintype = p_189080_ > 0.0D ? BOPNoiseSampler.VeinType.COPPER : BOPNoiseSampler.VeinType.IRON;
        int i = BOPNoiseSampler$veintype.maxY - p_189081_;
        int j = p_189081_ - BOPNoiseSampler$veintype.minY;
        if (j >= 0 && i >= 0) {
            int k = Math.min(i, j);
            double d0 = Mth.clampedMap((double)k, 0.0D, 20.0D, -0.2D, 0.0D);
            return Math.abs(p_189080_) + d0 < (double)0.4F ? null : BOPNoiseSampler$veintype;
        } else {
            return null;
        }
    }

    public static record FlatNoiseData(double shiftedX, double shiftedZ, double continentalness, double weirdness, double uniqueness, double erosion, TerrainInfo terrainInfo) {
    }

    static final class QuantizedSpaghettiRarity {
        private QuantizedSpaghettiRarity() {
        }

        static double getSphaghettiRarity2D(double p_189161_) {
            if (p_189161_ < -0.75D) {
                return 0.5D;
            } else if (p_189161_ < -0.5D) {
                return 0.75D;
            } else if (p_189161_ < 0.5D) {
                return 1.0D;
            } else {
                return p_189161_ < 0.75D ? 2.0D : 3.0D;
            }
        }

        static double getSpaghettiRarity3D(double p_189163_) {
            if (p_189163_ < -0.5D) {
                return 0.75D;
            } else if (p_189163_ < 0.0D) {
                return 1.0D;
            } else {
                return p_189163_ < 0.5D ? 1.5D : 2.0D;
            }
        }
    }

    static enum VeinType {
        COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
        IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

        final BlockState ore;
        final BlockState rawOreBlock;
        final BlockState filler;
        final int minY;
        final int maxY;

        private VeinType(BlockState p_189176_, BlockState p_189177_, BlockState p_189178_, int p_189179_, int p_189180_) {
            this.ore = p_189176_;
            this.rawOreBlock = p_189177_;
            this.filler = p_189178_;
            this.minY = p_189179_;
            this.maxY = p_189180_;
        }
    }
}