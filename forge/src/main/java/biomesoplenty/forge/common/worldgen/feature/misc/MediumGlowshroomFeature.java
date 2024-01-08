/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.worldgen.feature.misc;

import biomesoplenty.forge.api.block.BOPBlocks;
import biomesoplenty.forge.common.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class MediumGlowshroomFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.MYCELIUM || world.getBlockState(pos).getBlock() == Blocks.STONE || world.getBlockState(pos).getBlock() == Blocks.DEEPSLATE || world.getBlockState(pos).getBlock() == BOPBlocks.GLOWING_MOSS_BLOCK.get() || world.getBlockState(pos).getBlock() == Blocks.MUD;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock || world.getBlockState(pos).getBlock() == BOPBlocks.GLOWING_MOSS_CARPET.get() || world.getBlockState(pos).getBlock() == Blocks.MOSS_CARPET;

    public MediumGlowshroomFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        while (startPos.getY() >= world.getMinBuildHeight()+1 && this.replace.matches(world, startPos)) {
            startPos = startPos.below();
        }

        if (!this.placeOn.matches(world, startPos.offset(0, 0, 0))) {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above())) {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        int height = 4 + rand.nextInt(4);
        int radius = 2 + rand.nextInt(1);

        for (int y = 0; y < height; y++) {
            this.setBlock(world, pos.above(y), Blocks.MUSHROOM_STEM.defaultBlockState());
        }

        for (int x = -(radius-1); x <= (radius-1); x++)
        {
            for (int z = -(radius-1); z <= (radius-1); z++)
            {
                this.setBlock(world, pos.offset(x, height, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
            }
        }

        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                if ((x == -radius || x == radius) && (z == -radius || z == radius))
                {
                    continue;
                }
                else
                {
                    if (x == radius)
                    {
                        this.setBlock(world, pos.offset(x, height - 1, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.WEST, false));
                        this.setBlock(world, pos.offset(x, height - 2, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.WEST, false));
                    }
                    if (x == -radius)
                    {
                        this.setBlock(world, pos.offset(x, height - 1, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.EAST, false));
                        this.setBlock(world, pos.offset(x, height - 2, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.EAST, false));
                    }
                    if (z == radius)
                    {
                        this.setBlock(world, pos.offset(x, height - 1, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.NORTH, false));
                        this.setBlock(world, pos.offset(x, height - 2, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.NORTH, false));
                    }
                    if (z == -radius)
                    {
                        this.setBlock(world, pos.offset(x, height - 1, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.SOUTH, false));
                        this.setBlock(world, pos.offset(x, height - 2, z), BOPBlocks.GLOWSHROOM_BLOCK.get().defaultBlockState().setValue(HugeMushroomBlock.SOUTH, false));
                    }
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

    public boolean checkSpace(WorldGenLevel world, BlockPos pos)
    {
        for (int y = 0; y <= 6; y++)
        {
            for (int x = -2; x <= 2; x++)
            {
                for (int z = -2; z <= 2; z++)
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
