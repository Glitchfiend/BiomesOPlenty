/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DeadCoralPatchFeature extends Feature<NoneFeatureConfiguration>
{
    public DeadCoralPatchFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        int i = 0;

        for(int j = 0; j < 64; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (TreeFeature.isAirOrLeaves(world, blockpos) && world.getBlockState(blockpos.below()).is(ModTags.Blocks.TIDEPOOL_REPLACEABLE))
            {
                BlockState coral;
                switch (rand.nextInt(10))
                {
                    default:
                    case 0:
                        coral = Blocks.DEAD_TUBE_CORAL.defaultBlockState();
                        break;

                    case 1:
                        coral = Blocks.DEAD_BRAIN_CORAL.defaultBlockState();
                        break;

                    case 2:
                        coral = Blocks.DEAD_BUBBLE_CORAL.defaultBlockState();
                        break;

                    case 3:
                        coral = Blocks.DEAD_FIRE_CORAL.defaultBlockState();
                        break;

                    case 4:
                        coral = Blocks.DEAD_HORN_CORAL.defaultBlockState();
                        break;

                    case 5:
                        coral = Blocks.DEAD_TUBE_CORAL_FAN.defaultBlockState();
                        break;

                    case 6:
                        coral = Blocks.DEAD_BRAIN_CORAL_FAN.defaultBlockState();
                        break;

                    case 7:
                        coral = Blocks.DEAD_BUBBLE_CORAL_FAN.defaultBlockState();
                        break;

                    case 8:
                        coral = Blocks.DEAD_FIRE_CORAL_FAN.defaultBlockState();
                        break;

                    case 9:
                        coral = Blocks.DEAD_HORN_CORAL_FAN.defaultBlockState();
                        break;
                }

                world.setBlock(blockpos, coral.setValue(BaseCoralPlantTypeBlock.WATERLOGGED, false), 2);

                ++i;
            }
        }

        return i > 0;
    }
}
