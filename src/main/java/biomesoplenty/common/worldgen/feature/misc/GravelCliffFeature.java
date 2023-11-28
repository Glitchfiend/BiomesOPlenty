package biomesoplenty.common.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GravelCliffFeature extends Feature<NoneFeatureConfiguration>
{
    public GravelCliffFeature(Codec<NoneFeatureConfiguration> p_66836_)
    {
        super(p_66836_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_160368_)
    {
        WorldGenLevel worldgenlevel = p_160368_.level();
        BlockPos blockpos = p_160368_.origin();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 16; ++i)
        {
            for(int j = 0; j < 16; ++j)
            {
                int k = blockpos.getX() + i;
                int l = blockpos.getZ() + j;
                int i1 = worldgenlevel.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, k, l);
                blockpos$mutableblockpos.set(k, i1 - 1, l);
                blockpos$mutableblockpos1.set(blockpos$mutableblockpos).move(Direction.UP, 1);
                Biome biome = worldgenlevel.getBiome(blockpos$mutableblockpos).value();

                if (worldgenlevel.getBlockState(blockpos$mutableblockpos).getBlock() == Blocks.GRAVEL && (worldgenlevel.getBlockState(blockpos$mutableblockpos1).isAir() || worldgenlevel.getBlockState(blockpos$mutableblockpos1).getBlock() == Blocks.SNOW))
                {
                    worldgenlevel.setBlock(blockpos$mutableblockpos1, Blocks.AIR.defaultBlockState(), 2);

                    if (blockpos$mutableblockpos.getY() > 62)
                    {
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
                    else
                    {
                        if (biome.shouldFreeze(worldgenlevel, blockpos$mutableblockpos, false))
                        {
                            worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.ICE.defaultBlockState(), 2);
                        }
                        else
                        {
                            worldgenlevel.setBlock(blockpos$mutableblockpos, Blocks.AIR.defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}