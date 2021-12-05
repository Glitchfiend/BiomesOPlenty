/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;

public class BOPNoiseChunk extends NoiseChunk
{
    private final BOPNoiseSampler.BOPFlatNoiseData[][] bopNoiseData;

    public static BOPNoiseChunk forChunk(ChunkAccess chunkAccess, BOPNoiseSampler sampler, Supplier<BOPNoiseChunk.NoiseFiller> noiseFiller, NoiseGeneratorSettings noiseGenSettings, Aquifer.FluidPicker p_188778_, Blender blender)
    {
        ChunkPos chunkpos = chunkAccess.getPos();
        NoiseSettings noisesettings = noiseGenSettings.noiseSettings();
        int i = Math.max(noisesettings.minY(), chunkAccess.getMinBuildHeight());
        int j = Math.min(noisesettings.minY() + noisesettings.height(), chunkAccess.getMaxBuildHeight());
        int cellNoiseMinY = Mth.intFloorDiv(i, noisesettings.getCellHeight());
        int cellCountY = Mth.intFloorDiv(j - i, noisesettings.getCellHeight());

        AtomicReference<BOPNoiseChunk> noiseChunk = new AtomicReference();
        sampler.doWithNoiseDataCallsAllowed((initSampler) -> {
            noiseChunk.set(new BOPNoiseChunk(16 / noisesettings.getCellWidth(), cellCountY, cellNoiseMinY, sampler, chunkpos.getMinBlockX(), chunkpos.getMinBlockZ(), noiseFiller.get(), noiseGenSettings, p_188778_, blender));
        });
        return noiseChunk.get();
    }

    public static BOPNoiseChunk forColumn(int x, int z, int cellNoiseMinY, int cellCountY, BOPNoiseSampler sampler, NoiseGeneratorSettings noiseGenSettings, Aquifer.FluidPicker fluidPicker)
    {
        AtomicReference<BOPNoiseChunk> noiseChunk = new AtomicReference();
        sampler.doWithNoiseDataCallsAllowed((initSampler) -> {
            noiseChunk.set(new BOPNoiseChunk(1, cellCountY, cellNoiseMinY, sampler, x, z, (noiseX, noiseY, noiseZ) -> {
                return 0.0D;
            }, noiseGenSettings, fluidPicker, Blender.empty()));
        });
        return noiseChunk.get();
    }

    private BOPNoiseChunk(int cellCountXZ, int cellCountY, int cellNoiseMinY, BOPNoiseSampler sampler, int chunkX, int chunkZ, NoiseChunk.NoiseFiller noiseFiller, NoiseGeneratorSettings noiseGenSettings, Aquifer.FluidPicker fluidPicker, Blender blender)
    {
        super(cellCountXZ, cellCountY, cellNoiseMinY, sampler, chunkX, chunkZ, noiseFiller, noiseGenSettings, fluidPicker, blender);

        int cellWidth = this.noiseSettings.getCellWidth();
        int j = QuartPos.fromBlock(cellCountXZ * cellWidth);

        this.bopNoiseData = new BOPNoiseSampler.BOPFlatNoiseData[j + 1][];

        for (int k = 0; k <= j; ++k)
        {
            int l = this.firstNoiseX + k;
            this.bopNoiseData[k] = new BOPNoiseSampler.BOPFlatNoiseData[j + 1];

            for (int i1 = 0; i1 <= j; ++i1)
            {
                int j1 = this.firstNoiseZ + i1;
                this.bopNoiseData[k][i1] = sampler.noiseDataBOP(l, j1, blender);
            }
        }
    }

    @Override
    public NoiseSampler.FlatNoiseData noiseData(int x, int z)
    {
        throw new RuntimeException("Vanilla noiseData called on BOPNoiseChunk!");
    }

    public BOPNoiseSampler.BOPFlatNoiseData noiseDataBOP(int x, int z)
    {
        return this.bopNoiseData[x - this.firstNoiseX][z - this.firstNoiseZ];
    }

    @Override
    public int preliminarySurfaceLevel(int x, int z)
    {
        return this.preliminarySurfaceLevel.computeIfAbsent(ChunkPos.asLong(QuartPos.fromBlock(x), QuartPos.fromBlock(z)), this::computePreliminarySurfaceLevel);
    }

    private int computePreliminarySurfaceLevel(long chunkPos)
    {
        int i = ChunkPos.getX(chunkPos);
        int j = ChunkPos.getZ(chunkPos);
        int k = i - this.firstNoiseX;
        int l = j - this.firstNoiseZ;
        int i1 = this.bopNoiseData.length;
        TerrainInfo terraininfo;
        if (k >= 0 && l >= 0 && k < i1 && l < i1) {
            terraininfo = this.bopNoiseData[k][l].terrainInfo();
        } else {
            terraininfo = ((BOPNoiseSampler)this.sampler).noiseDataBOP(i, j, this.blender).terrainInfo();
        }

        return this.sampler.getPreliminarySurfaceLevel(QuartPos.toBlock(i), QuartPos.toBlock(j), terraininfo);
    }

}
