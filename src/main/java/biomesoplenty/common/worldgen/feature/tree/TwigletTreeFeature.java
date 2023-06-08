/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.worldgen.feature.configurations.TwigletTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class TwigletTreeFeature extends BOPTreeFeature<TwigletTreeConfiguration>
{
    public TwigletTreeFeature(Codec<TwigletTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        TwigletTreeConfiguration config = (TwigletTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES))
        {
            startPos = startPos.below();
        }

        // choose a random height
        int height = config.minHeight + random.nextInt(1 + config.maxHeight - config.minHeight);
        int baseHeight = height / 3;

        // start from the block above the ground block
        BlockPos pos = startPos.above();

        // add log and leaves on each level
        float leafChance;
        for (int y = 0; y < height; y++)
        {
            if (!this.placeLog(world, pos.above(y), logs, config))
            {
                // abandon if the log can't grow
                return true;
            }
            leafChance = ((height - y) % 2 == 0) ? config.leafChanceEven : config.leafChanceOdd;
            if (y <= baseHeight)
            {
                continue;
            } // no leaves below base height
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(1, y, 0), leaves, config);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(-1, y, 0), leaves, config);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(0, y, 1), leaves, config);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(0, y, -1), leaves, config);
            }

            for (Direction dir : Direction.Plane.HORIZONTAL)
            {
                BlockPos fruitPos = pos.offset(dir.getStepX(), y, dir.getStepZ());
                BlockState trunkFruit = config.trunkFruitProvider.getState(random, fruitPos);

                if (trunkFruit.getBlock() != Blocks.AIR && random.nextInt(4) == 0)
                {
                    if (trunkFruit.getBlock() == Blocks.COCOA)
                        fruitPos = pos.offset(dir.getOpposite().getStepX(), 0, dir.getOpposite().getStepZ());

                    this.generateTrunkFruit(world, random.nextInt(3), fruitPos, dir, config);
                }
            }
        }
        // finish with leaves on top
        this.placeLeaves(world, pos.offset(0, height, 0), leaves, config);

        return true;
    }

    private void generateTrunkFruit(LevelAccessor world, int age, BlockPos pos, Direction direction, TwigletTreeConfiguration config)
    {
        BlockState trunkFruit = config.trunkFruitProvider.getState(world.getRandom(), pos);

        if (trunkFruit == Blocks.COCOA.defaultBlockState())
        {
            if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() instanceof BushBlock)
            {
                this.setBlock(world, pos, trunkFruit.setValue(CocoaBlock.AGE, Integer.valueOf(age)).setValue(CocoaBlock.FACING, direction));
            }
        }
        else
        {
            if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() instanceof BushBlock)
            {
                this.setBlock(world, pos, trunkFruit.setValue(CocoaBlock.FACING, direction));
            }
        }
    }
}
