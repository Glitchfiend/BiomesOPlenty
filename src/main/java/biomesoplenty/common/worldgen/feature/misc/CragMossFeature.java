/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class CragMossFeature extends Feature<NoneFeatureConfiguration>
{
    public CragMossFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel worldIn = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        BlockPos.MutableBlockPos mutable = pos.mutable();
        int placed = 0;
        int radius = rand.nextInt(8 - 2) + 2;

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
        {
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
            {
                int dx = x - pos.getX();
                int dz = z - pos.getZ();
                if (dx * dx + dz * dz <= radius * radius)
                {
                    for (int k1 = pos.getY() - 2; k1 <= pos.getY() + 2; ++k1)
                    {
                        mutable.set(x, k1, z);
                        BlockState blockstate = worldIn.getBlockState(mutable);
                        BlockState blockstate1 = worldIn.getBlockState(mutable.move(Direction.UP));
                        mutable.move(Direction.DOWN);

                        if (blockstate1.getBlock() == Blocks.AIR && blockstate.getBlock() == Blocks.STONE || blockstate.getBlock() == Blocks.GRAVEL || blockstate.getBlock() == Blocks.ANDESITE || blockstate.getBlock() == Blocks.DIORITE || blockstate.getBlock() == Blocks.GRANITE || blockstate.getBlock() == Blocks.DIRT)
                        {
                            switch (rand.nextInt(2))
                            {
                                default:
                                case 0:
                                    worldIn.setBlock(mutable, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
                                    if (rand.nextInt(3) != 0 && worldIn.isStateAtPosition(mutable.move(Direction.UP), BlockBehaviour.BlockStateBase::isAir))
                                    {
                                        worldIn.setBlock(mutable, Blocks.MOSS_CARPET.defaultBlockState(), 2);
                                    }
                                    break;

                                case 1:
                                    worldIn.setBlock(mutable, Blocks.MOSS_BLOCK.defaultBlockState(), 2);
                                    if (worldIn.isStateAtPosition(mutable.move(Direction.UP), BlockBehaviour.BlockStateBase::isAir))
                                    {
                                        switch (rand.nextInt(2))
                                        {
                                            default:
                                            case 0:
                                                worldIn.setBlock(mutable, Blocks.GRASS.defaultBlockState(), 2);
                                                break;

                                            case 1:
                                                worldIn.setBlock(mutable, Blocks.MOSS_CARPET.defaultBlockState(), 2);
                                                break;
                                        }
                                    }
                                    break;
                            }

                            ++placed;
                            break;
                        }
                    }
                }
            }
        }

        return placed > 0;
    }
}
