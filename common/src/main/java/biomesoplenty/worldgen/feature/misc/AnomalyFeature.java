/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.AnomalyBlock;
import biomesoplenty.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class AnomalyFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.END_STONE;
    protected SimpleBlockPredicate replace = (world, pos) -> world.getBlockState(pos).is(BlockTags.REPLACEABLE_BY_TREES) || world.getBlockState(pos).getBlock() instanceof BushBlock || world.getBlockState(pos).getBlock() == Blocks.END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.ALGAL_END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SAND || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SANDSTONE || world.getBlockState(pos).getBlock() == BOPBlocks.NULL_END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.NULL_BLOCK;

    public AnomalyFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        while (startPos.getY() >= world.getMinBuildHeight()+1 && !this.placeOn.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos.offset(0, 0, 0)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        int size = rand.nextInt(3) + 2;
        int anomalyHeight = size+3;

        if (!this.checkSpace(world, startPos, size, anomalyHeight))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos;

        for (int y = 4; y > -128; y--)
        {
            for (int x = -3; x <= size+3; x++)
            {
                for (int z = -3; z <= size+3; z++)
                {
                    if (x == -3 || x == size+3 || z == -3 || z == size+3)
                    {
                        if (rand.nextInt(3) == 0 && (world.getBlockState(pos.offset(x,y,z)).getBlock() == Blocks.END_STONE || world.getBlockState(pos.offset(x,y,z)).getBlock() == BOPBlocks.ALGAL_END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SAND || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SANDSTONE || world.getBlockState(pos.offset(x,y,z)).getBlock() == BOPBlocks.NULL_END_STONE))
                        {
                            world.setBlock(pos.offset(x,y,z), BOPBlocks.NULL_END_STONE.defaultBlockState(), 2);
                        }
                    }
                    else if (x == -2 || x == size+2 || z == -2 || z == size+2)
                    {
                        if (world.getBlockState(pos.offset(x,y,z)).getBlock() == Blocks.END_STONE || world.getBlockState(pos.offset(x,y,z)).getBlock() == BOPBlocks.ALGAL_END_STONE || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SAND || world.getBlockState(pos).getBlock() == BOPBlocks.WHITE_SANDSTONE || world.getBlockState(pos.offset(x,y,z)).getBlock() == BOPBlocks.NULL_END_STONE)
                        {
                            world.setBlock(pos.offset(x,y,z), BOPBlocks.NULL_END_STONE.defaultBlockState(), 2);
                        }
                    }
                    else if (x == -1 || x == size+1 || z == -1 || z == size+1)
                    {
                        this.setBlock(world, pos.offset(x,y,z), BOPBlocks.NULL_BLOCK.defaultBlockState());
                    }
                    else
                    {
                        this.setBlock(world, pos.offset(x,y,z), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }

        for (int y = 0; y <= size; y++)
        {
            for (int x = 0; x <= size; x++)
            {
                for (int z = 0; z <= size; z++)
                {
                    AnomalyBlock.AnomalyType type = AnomalyBlock.AnomalyType.STABLE;
                    if (y == 0 || y == size || x == 0 || x == size || z == 0 || z == size)
                    {
                        switch (rand.nextInt(6))
                        {
                            default:
                            case 0:
                                type = AnomalyBlock.AnomalyType.VOLATILE;
                                break;

                            case 1:
                                type = AnomalyBlock.AnomalyType.QUIRKY;
                                break;

                            case 2:
                                type = AnomalyBlock.AnomalyType.UNSTABLE;
                                break;
                        }
                    }

                    world.setBlock(pos.offset(x,anomalyHeight+y,z), BOPBlocks.ANOMALY.defaultBlockState().setValue(AnomalyBlock.ANOMALY_TYPE, type), 2);
                }
            }
        }

        return true;
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, int size, int anomalyHeight)
    {
        for (int y = 0; y <= size; y++)
        {
            for (int x = 0; x <= size; x++)
            {
                for (int z = 0; z <= size; z++)
                {
                    BlockPos pos1 = pos.offset(x, anomalyHeight+y, z);
                    if (pos1.getY() >= 255 || (!world.getBlockState(pos1).isAir() && world.getBlockState(pos1).getBlock() != BOPBlocks.ANOMALY))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
