/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.WorldGenerationContext;

import java.util.function.Function;

public class BOPSurfaceRules extends SurfaceRules
{
    protected static final class Context extends SurfaceRules.Context
    {
        private final BOPNoiseChunk bopNoiseChunk;

        protected Context(BOPSurfaceSystem surfaceSystem, ChunkAccess chunkAccess, BOPNoiseChunk noiseChunk, Function<BlockPos, Biome> p_189565_, Registry<Biome> p_189566_, WorldGenerationContext p_189567_)
        {
            super(surfaceSystem, chunkAccess, null, p_189565_, p_189566_, p_189567_);
            this.bopNoiseChunk = noiseChunk;
        }

        // Replace usage of noiseChunk with bopNoiseChunk
        @Override
        public int getMinSurfaceLevel()
        {
            if (this.lastMinSurfaceLevelUpdate != this.lastUpdateXZ) {
                this.lastMinSurfaceLevelUpdate = this.lastUpdateXZ;
                int i = blockCoordToSurfaceCell(this.blockX);
                int j = blockCoordToSurfaceCell(this.blockZ);
                long k = ChunkPos.asLong(i, j);
                if (this.lastPreliminarySurfaceCellOrigin != k) {
                    this.lastPreliminarySurfaceCellOrigin = k;
                    this.preliminarySurfaceCache[0] = this.bopNoiseChunk.preliminarySurfaceLevel(surfaceCellToBlockCoord(i), surfaceCellToBlockCoord(j));
                    this.preliminarySurfaceCache[1] = this.bopNoiseChunk.preliminarySurfaceLevel(surfaceCellToBlockCoord(i + 1), surfaceCellToBlockCoord(j));
                    this.preliminarySurfaceCache[2] = this.bopNoiseChunk.preliminarySurfaceLevel(surfaceCellToBlockCoord(i), surfaceCellToBlockCoord(j + 1));
                    this.preliminarySurfaceCache[3] = this.bopNoiseChunk.preliminarySurfaceLevel(surfaceCellToBlockCoord(i + 1), surfaceCellToBlockCoord(j + 1));
                }

                int l = Mth.floor(Mth.lerp2((double)((float)(this.blockX & 15) / 16.0F), (double)((float)(this.blockZ & 15) / 16.0F), (double)this.preliminarySurfaceCache[0], (double)this.preliminarySurfaceCache[1], (double)this.preliminarySurfaceCache[2], (double)this.preliminarySurfaceCache[3]));
                this.minSurfaceLevel = l + this.surfaceDepth - 8;
            }

            return this.minSurfaceLevel;
        }
    }
}
