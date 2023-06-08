/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.worldgen.feature.configurations.PalmTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class PalmTreeFeature extends BOPTreeFeature<PalmTreeConfiguration>
{
    public PalmTreeFeature(Codec<PalmTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        PalmTreeConfiguration config = (PalmTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES)) {startPos = startPos.below();}

        // Generation settings
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);
        int leavesRadius = 2;
        int heightMinusTop = height - leavesRadius - 1;
        boolean slant = true;
        Direction direction = Direction.getRandom(random); //The direction the palm tree curves towards
        if (direction == Direction.DOWN || direction == Direction.UP)
        {
            slant = false;
        }
        double baseSlant = random.nextInt(35) / 100D;
        double slantMultiplier = 1.3D;

        if (height < 8) {return false;} //Prevent trees from being too small

        // Move up to space above ground
        BlockPos pos = startPos.above();

        if (!this.checkSpace(world, pos, height, 1))
        {
            // Abandon if there isn't enough room
            return false;
        }

        double slantOffset = baseSlant;

        // Generate trunk of tree (trunk only)
        for(int step = 0; step <= heightMinusTop; step++)
        {
            BlockPos offsetPos = pos.above(step);

            if (slant == true)
            {
                offsetPos = pos.above(step).relative(direction, (int)Math.floor(slantOffset));
            }

            if (step == heightMinusTop)
            {
                // Generate top of tree
                this.placeLog(world, offsetPos, logs, config);
                generateLeavesTop(world, offsetPos, leavesRadius, leaves, config);
                break;
            }

            this.placeLog(world, offsetPos, logs, config);

            //As the height increases, slant more drastically
            slantOffset *= slantMultiplier;
        }

        return true;
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos, int height, int radius)
    {
        for (int y = 0; y <= height; y++)
        {
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.canReplace(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // generate the top of the tree (3 blocks)
    public void generateLeavesTop(LevelAccessor world, BlockPos pos, int maxRadius, FoliagePlacer.FoliageSetter leaves, PalmTreeConfiguration config)
    {
        placeLeaves(world, pos.offset(2, -1, 0), leaves, config);
        placeLeaves(world, pos.offset(-2, -1, 0), leaves, config);
        placeLeaves(world, pos.offset(0, -1, 2), leaves, config);
        placeLeaves(world, pos.offset(0, -1, -2), leaves, config);

        placeLeaves(world, pos.offset(1, 0, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 0, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 0, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 0, -1), leaves, config);
        placeLeaves(world, pos.offset(2, 0, 2), leaves, config);
        placeLeaves(world, pos.offset(-2, 0, -2), leaves, config);
        placeLeaves(world, pos.offset(2, 0, -2), leaves, config);
        placeLeaves(world, pos.offset(-2, 0, 2), leaves, config);

        placeLeaves(world, pos.offset(1, 1, -1), leaves, config);
        placeLeaves(world, pos.offset(-1, 1, 1), leaves, config);
        placeLeaves(world, pos.offset(1, 1, 1), leaves, config);
        placeLeaves(world, pos.offset(-1, 1, -1), leaves, config);
        placeLeaves(world, pos.offset(0, 1, 0), leaves, config);

        placeLeaves(world, pos.offset(2, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(-2, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 2, 2), leaves, config);
        placeLeaves(world, pos.offset(0, 2, -2), leaves, config);
    }
}
