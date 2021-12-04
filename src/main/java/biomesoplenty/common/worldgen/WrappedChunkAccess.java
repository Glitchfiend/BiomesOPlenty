/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import net.minecraft.core.QuartPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.blending.Blender;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class WrappedChunkAccess
{
    private final ChunkAccess chunkAccess;

    @Nullable
    protected BOPNoiseChunk noiseChunk;

    public WrappedChunkAccess(ChunkAccess chunkAccess)
    {
        this.chunkAccess = chunkAccess;
    }

    public BOPNoiseChunk getOrCreateNoiseChunk(BOPNoiseSampler sampler, Supplier<BOPNoiseChunk.NoiseFiller> noiseFilter, NoiseGeneratorSettings noiseGenSettings, Aquifer.FluidPicker p_187644_, Blender blender)
    {
        if (this.noiseChunk == null)
        {
            this.noiseChunk = BOPNoiseChunk.forChunk(this.chunkAccess, sampler, noiseFilter, noiseGenSettings, p_187644_, blender);
        }

        return this.noiseChunk;
    }

    public void fillBiomesFromNoise(BiomeResolver biomeResolver, BOPClimate.Sampler sampler)
    {
        ChunkPos chunkpos = this.chunkAccess.getPos();
        int i = QuartPos.fromBlock(chunkpos.getMinBlockX());
        int j = QuartPos.fromBlock(chunkpos.getMinBlockZ());
        LevelHeightAccessor levelheightaccessor = this.chunkAccess.getHeightAccessorForGeneration();

        for (int k = levelheightaccessor.getMinSection(); k < levelheightaccessor.getMaxSection(); ++k) {
            LevelChunkSection levelchunksection = this.chunkAccess.getSection(this.chunkAccess.getSectionIndexFromSectionY(k));
            levelchunksection.fillBiomesFromNoise(biomeResolver, sampler, i, j);
        }
    }
}
