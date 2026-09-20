package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.util.RandomSource;

public class OriginGravelCliffFeature implements Feature
{
    public static final MapCodec<OriginGravelCliffFeature> CODEC = MapCodec.unit(OriginGravelCliffFeature::new);

    @Override
    public MapCodec<OriginGravelCliffFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel worldgenlevel, ChunkGenerator chunkGenerator, RandomSource random, BlockPos blockpos)
    {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 16; ++i)
        {
            for(int j = 0; j < 16; ++j)
            {
                int k = blockpos.getX() + i;
                int l = blockpos.getZ() + j;
                int i1 = 64;
                blockpos$mutableblockpos.set(k, i1, l);
                blockpos$mutableblockpos1.set(blockpos$mutableblockpos).move(Direction.UP, 1);
                Biome biome = worldgenlevel.getBiome(blockpos$mutableblockpos).value();

                if (worldgenlevel.getBlockState(blockpos$mutableblockpos).getBlock() == Blocks.GRAVEL && (worldgenlevel.getBlockState(blockpos$mutableblockpos1).isAir() || worldgenlevel.getBlockState(blockpos$mutableblockpos1).getBlock() == Blocks.SNOW))
                {
                    worldgenlevel.setBlock(blockpos$mutableblockpos1, Blocks.AIR.defaultBlockState(), 2);

                    if (biome.shouldSnow(worldgenlevel, blockpos$mutableblockpos1))
                    {
                        if (Blocks.SNOW.defaultBlockState().canSurvive(worldgenlevel, blockpos$mutableblockpos))
                        {
                            worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.SNOW.defaultBlockState(), 2);
                        }
                        else
                        {
                            worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                    else
                    {
                        worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }

        return true;
    }
}