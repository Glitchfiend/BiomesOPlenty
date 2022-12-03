/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.common.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BigPumpkinFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected SimpleBlockPredicate replace = TreeFeature::isAirOrLeaves;

    public BigPumpkinFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos.offset(2, 0, 2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above()))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        for (int x = 1; x < 4; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                for (int z = 1; z < 4; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        for (int x = 0; x < 5; x++)
        {
            for (int y = 1; y < 4; y++)
            {
                for (int z = 1; z < 4; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        for (int x = 1; x < 4; x++)
        {
            for (int y = 1; y < 4; y++)
            {
                for (int z = 0; z < 5; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        this.setBlock(world, pos.offset(2,5,2), Blocks.OAK_LOG.defaultBlockState());

        this.setBlock(world, pos.offset(1,5,2), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(0,4,1), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

        this.setBlock(world, pos.offset(2,5,3), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,5,3), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(2,4,4), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,4,4), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

        this.setBlock(world, pos.offset(3,5,1), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,4,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,4,2), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,3,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,2,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

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

    public boolean checkSpace(WorldGenLevel world, BlockPos pos)
    {
        for (int y = 0; y <= 6; y++)
        {
            for (int x = 0; x <= 5; x++)
            {
                for (int z = 0; z <= 5; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
