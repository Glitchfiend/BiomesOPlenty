/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BarnacleFeature extends Feature<NoneFeatureConfiguration>
{
    public BarnacleFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        int i = 0;
        int j = rand.nextInt(8 - 2) + 2;

        for (int k = pos.getX() - j; k <= pos.getX() + j; ++k)
        {
            for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l)
            {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= j * j)
                {
                    for (int k1 = pos.getY() - j; k1 <= pos.getY() + j; ++k1)
                    {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = worldIn.getBlockState(blockpos);
                        BlockState barnaclestate = BOPBlocks.BARNACLES.defaultBlockState();

                        int faces = 0;

                        for (Direction direction : Direction.values())
                        {
                            BlockState blockstate1 = worldIn.getBlockState(blockpos.relative(direction));
                            if (blockstate1 == Blocks.END_STONE.defaultBlockState() || blockstate1 == BOPBlocks.ALGAL_END_STONE.defaultBlockState() || blockstate1.is(ModTags.Blocks.DEAD_CORAL_BLOCKS))
                            {
                                barnaclestate = barnaclestate.setValue(MultifaceBlock.getFaceProperty(direction), true);
                                faces++;
                            }
                        }

                        if (blockstate.isAir() && faces > 0)
                        {
                            worldIn.setBlock(blockpos, barnaclestate, 2);

                            ++i;
                            break;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
