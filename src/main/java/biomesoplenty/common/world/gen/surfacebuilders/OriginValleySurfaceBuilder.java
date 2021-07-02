package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.common.world.AlphaOctavePerlinNoise;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.Random;
import java.util.stream.IntStream;

public class OriginValleySurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration>
{
    protected long seed;
    protected AlphaOctavePerlinNoise sandNoise;
    protected AlphaOctavePerlinNoise gravelNoise;
    public OriginValleySurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232124_1_)
    {
        super(p_i232124_1_);
    }

    public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderBaseConfiguration config) {
        this.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), seaLevel);
    }

    protected void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
        BlockState topState = top;
        BlockState middleState = middle;
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        int placedDepth = -1;
        int grassDepth = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int localX = x & 15;
        int localZ = z & 15;

        boolean gravelGen = gravelNoise.sample(x, 109.0134, z, 0.03125, 1, 0.03125) + random.nextDouble() * 0.2 > 3.0;
        boolean sandGen = sandNoise.sample(x, z, 0, 0.03125, 0.03125, 1) + random.nextDouble() * 0.2 > 0.0;

        for (int y = startHeight; y >= 0; --y)
        {
            mutable.set(localX, y, localZ);
            BlockState blockstate2 = chunkIn.getBlockState(mutable);
            if (blockstate2.isAir())
            {
                placedDepth = -1;
            }
            else if (blockstate2.is(defaultBlock.getBlock()))
            {
                if (placedDepth == -1)
                {
                    if (grassDepth <= 0)
                    {
                        topState = Blocks.AIR.defaultBlockState();
                        middleState = defaultBlock;
                    }
                    else if (y >= sealevel - 4 && y <= sealevel + 1)
                    {
                        topState = top;
                        middleState = middle;

                        if (gravelGen)
                        {
                            topState = Blocks.AIR.defaultBlockState();
                            middleState = Blocks.GRAVEL.defaultBlockState();
                        }
                        if (sandGen)
                        {
                            topState = Blocks.SAND.defaultBlockState();
                            middleState = Blocks.SAND.defaultBlockState();
                        }
                    }

                    if (y < sealevel && (topState == null || topState.isAir()))
                    {
                        if (biomeIn.getTemperature(mutable.set(x, y, z)) < 0.15F)
                        {
                            topState = Blocks.ICE.defaultBlockState();
                        }
                        else
                        {
                            topState = defaultFluid;
                        }

                        mutable.set(localX, y, localZ);
                    }

                    placedDepth = grassDepth;
                    if (y >= sealevel - 1)
                    {
                        chunkIn.setBlockState(mutable, topState, false);
                    }
                    else
                    {
                        chunkIn.setBlockState(mutable, middleState, false);
                    }
                }
                else if (placedDepth > 0)
                {
                    --placedDepth;
                    chunkIn.setBlockState(mutable, middleState, false);
                }
            }
        }
    }

    @Override
    public void initNoise(long seed)
    {
        // If the seed has changed, then re-initialize the noise.
        if (this.seed != seed || this.sandNoise == null || this.gravelNoise == null)
        {
            WorldgenRandom random = new WorldgenRandom(seed);
            this.sandNoise = new AlphaOctavePerlinNoise(random, 4);
            this.gravelNoise = new AlphaOctavePerlinNoise(random, 4);
        }

        this.seed = seed;
    }
}