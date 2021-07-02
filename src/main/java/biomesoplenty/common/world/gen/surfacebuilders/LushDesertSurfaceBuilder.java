package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.Random;

public class LushDesertSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration> {
    public LushDesertSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232124_1_) {
        super(p_i232124_1_);
    }

    @Override
    public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config)
    {
        if (noise > 1.9D)
        {
            BOPSurfaceBuilders.ORANGE_SANDSTONE.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BOPSurfaceBuilders.ORANGE_SANDSTONE_SURFACE);
        }
        else
        {
            BOPSurfaceBuilders.ORANGE_SANDSTONE.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BOPSurfaceBuilders.ORANGE_SAND_SURFACE);
        }
    }
}