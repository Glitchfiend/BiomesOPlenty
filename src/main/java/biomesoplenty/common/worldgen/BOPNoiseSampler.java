/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;
import java.util.function.Consumer;

public class BOPNoiseSampler extends NoiseSampler implements BOPClimate.Sampler
{
    private final NormalNoise uniquenessNoise;

    private final List<BOPClimate.ParameterPoint> spawnTarget = (new BOPOverworldBiomeBuilder()).spawnTarget();
    private boolean noiseDataCallsAllowed = false;

    public BOPNoiseSampler(NoiseSettings noiseSettings, boolean isNoiseCavesEnabled, long seed, Registry<NormalNoise.NoiseParameters> noiseParamRegistry, WorldgenRandom.Algorithm randomSource)
    {
        super(noiseSettings, isNoiseCavesEnabled, seed, noiseParamRegistry, randomSource);

        // Replace baseNoise to use our version of noiseData
        this.baseNoise = (instance) -> {
            return instance.createNoiseInterpolator((noiseX, noiseY, noiseZ) -> {
                return this.calculateBaseNoise(noiseX, noiseY, noiseZ, ((BOPNoiseChunk)instance).noiseDataBOP(QuartPos.fromBlock(noiseX), QuartPos.fromBlock(noiseZ)).terrainInfo(), instance.getBlender());
            });
        };

        PositionalRandomFactory positionalrandomfactory = randomSource.newInstance(seed).forkPositional();
        this.uniquenessNoise = Noises.instantiate(noiseParamRegistry, positionalrandomfactory, BOPNoises.UNIQUENESS);
    }

    @Override
    public NoiseSampler.FlatNoiseData noiseData(int p_189010_, int p_189011_, Blender blender)
    {
        if (this.noiseDataCallsAllowed)
            return null;

        throw new RuntimeException("Vanilla noiseData called on BOPNoiseSampler!");
    }

    @Override
    public Climate.TargetPoint sample(int p_189133_, int p_189134_, int p_189135_)
    {
        throw new RuntimeException("Vanilla sample called on BOPNoiseSampler!");
    }

    @Override
    public Climate.TargetPoint target(int p_188977_, int p_188978_, int p_188979_, NoiseSampler.FlatNoiseData noiseData)
    {
        throw new RuntimeException("Vanilla target called on BOPNoiseSampler!");
    }

    @VisibleForDebug
    public BOPFlatNoiseData noiseDataBOP(int x, int z, Blender blender)
    {
        double shiftedX = (double)x + this.getOffset(x, 0, z);
        double shiftedZ = (double)z + this.getOffset(z, x, 0);
        double continentalness = this.getContinentalness(shiftedX, 0.0D, shiftedZ);
        double weirdness = this.getWeirdness(shiftedX, 0.0D, shiftedZ);
        double uniqueness = this.getUniqueness(shiftedX, 0.0D, shiftedZ);
        double erosion = this.getErosion(shiftedX, 0.0D, shiftedZ);
        TerrainInfo terraininfo = this.terrainInfo(QuartPos.toBlock(x), QuartPos.toBlock(z), (float)continentalness, (float)weirdness, (float)erosion, blender);
        return new BOPFlatNoiseData(shiftedX, shiftedZ, continentalness, weirdness, uniqueness, erosion, terraininfo);
    }

    @Override
    public BOPClimate.TargetPoint sampleBOP(int p_189133_, int p_189134_, int p_189135_)
    {
        return this.targetBOP(p_189133_, p_189134_, p_189135_, this.noiseDataBOP(p_189133_, p_189135_, Blender.empty()));
    }

    @VisibleForDebug
    public BOPClimate.TargetPoint targetBOP(int p_188977_, int p_188978_, int p_188979_, BOPFlatNoiseData p_188980_)
    {
        double d0 = p_188980_.shiftedX();
        double d1 = (double)p_188978_ + this.getOffset(p_188978_, p_188979_, p_188977_);
        double d2 = p_188980_.shiftedZ();
        double d3 = this.computeBaseDensity(QuartPos.toBlock(p_188978_), p_188980_.terrainInfo());
        return BOPClimate.target((float)this.getTemperature(d0, d1, d2), (float)this.getHumidity(d0, d1, d2), (float)p_188980_.continentalness(), (float)p_188980_.erosion(), (float)d3, (float)p_188980_.weirdness(), (float)p_188980_.uniqueness());
    }

    @Override
    public BlockPos findSpawnPosition()
    {
        return BOPClimate.findSpawnPosition(this.spawnTarget, this);
    }

    @VisibleForDebug
    public double getUniqueness(double x, double y, double z) {
        return this.uniquenessNoise.getValue(x, y, z);
    }

    public synchronized void doWithNoiseDataCallsAllowed(Consumer<BOPNoiseSampler> consumer)
    {
        this.noiseDataCallsAllowed = true;
        consumer.accept(this);
        this.noiseDataCallsAllowed = false;
    }

    public record BOPFlatNoiseData(double shiftedX, double shiftedZ, double continentalness, double weirdness, double uniqueness, double erosion, TerrainInfo terrainInfo) {}
}