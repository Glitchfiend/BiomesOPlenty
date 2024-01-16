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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralPlantTypeBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class TidepoolFeature extends Feature<NoneFeatureConfiguration>
{
    public TidepoolFeature(Codec<NoneFeatureConfiguration> deserializer)
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

        for(int j = 0; j < 96; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
            boolean canPlace = true;

            if (!world.getBlockState(blockpos.above()).is(BlockTags.REPLACEABLE) || !world.getFluidState(blockpos.above()).is(Fluids.EMPTY))
            {
                canPlace = false;
            }

            if (!world.getBlockState(blockpos).is(ModTags.Blocks.TIDEPOOL_REPLACEABLE) || !world.getBlockState(blockpos.below()).is(ModTags.Blocks.TIDEPOOL_REPLACEABLE))
            {
                canPlace = false;
            }

            for (Direction direction : Direction.Plane.HORIZONTAL)
            {
                if (!world.getBlockState(blockpos.relative(direction)).is(ModTags.Blocks.TIDEPOOL_REPLACEABLE) && !world.getFluidState(blockpos.relative(direction)).is(Fluids.WATER))
                {
                    canPlace = false;
                }
            }

            if (canPlace)
            {
                BlockState coral;
                switch (rand.nextInt(12))
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

                    case 10:
                        coral = BOPBlocks.BARNACLES.defaultBlockState().setValue(PipeBlock.DOWN, true);
                        break;

                    case 11:
                        coral = Blocks.GLOW_LICHEN.defaultBlockState().setValue(PipeBlock.DOWN, true);
                        break;
                }

                BlockState coralBlock;
                switch (rand.nextInt(5))
                {
                    default:
                    case 0:
                        coralBlock = Blocks.DEAD_TUBE_CORAL_BLOCK.defaultBlockState();
                        break;

                    case 1:
                        coralBlock = Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState();
                        break;

                    case 2:
                        coralBlock = Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState();
                        break;

                    case 3:
                        coralBlock = Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState();
                        break;

                    case 4:
                        coralBlock = Blocks.DEAD_HORN_CORAL_BLOCK.defaultBlockState();
                        break;
                }

                world.setBlock(blockpos.below(), coralBlock, 2);

                for (Direction direction : Direction.Plane.HORIZONTAL)
                {
                    if (!world.getFluidState(blockpos.relative(direction)).is(Fluids.WATER))
                    {
                        world.setBlock(blockpos.relative(direction), coralBlock, 2);
                    }
                }

                world.setBlock(blockpos, coral.setValue(BaseCoralPlantTypeBlock.WATERLOGGED, true), 2);
                world.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);

                ++i;
            }
        }

        return i > 0;
    }
}
