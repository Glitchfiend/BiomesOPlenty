/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.carver.CarvingContext;

import java.util.Optional;
import java.util.function.Function;

public class BOPCarvingContext extends CarvingContext
{
    private final BOPNoiseBasedChunkGenerator generator;
    private final BOPNoiseChunk noiseChunk;

    public BOPCarvingContext(BOPNoiseBasedChunkGenerator p_190642_, RegistryAccess p_190643_, LevelHeightAccessor p_190644_, BOPNoiseChunk p_190645_) {
        super(p_190642_, p_190643_, p_190644_, null);
        this.generator = p_190642_;
        this.noiseChunk = p_190645_;
    }

    /** @deprecated */
    @Deprecated
    public Optional<BlockState> topMaterial(Function<BlockPos, Biome> p_190647_, ChunkAccess p_190648_, BlockPos p_190649_, boolean p_190650_)
    {
        return this.generator.topMaterial(this, p_190647_, p_190648_, this.noiseChunk, p_190649_, p_190650_);
    }
}
