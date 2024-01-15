/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.tree;

import biomesoplenty.util.biome.GeneratorUtil;
import biomesoplenty.worldgen.feature.configurations.EmpyrealTreeConfiguration;
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

public class EmpyrealTreeFeature extends BOPTreeFeature<EmpyrealTreeConfiguration>
{
    public EmpyrealTreeFeature(Codec<EmpyrealTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        EmpyrealTreeConfiguration config = (EmpyrealTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() >= world.getMinBuildHeight()+1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES)) {startPos = startPos.below();}

        // Generation settings
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);

        // Move up to space above ground
        BlockPos pos = startPos.above();

        if (!this.checkSpace(world, pos, height + 20, 2))
        {
            // Abandon if there isn't enough room
            return false;
        }

        for (int i = 0; i < height; i++)
        {
            this.placeLog(world, pos.above(i), logs, config);
        }

        BlockPos topPos = pos.above(height);

        this.placeLog(world, topPos.offset(0, 0, 0), logs, config);

        if (random.nextInt(2) == 0)
        {
            this.placeLog(world, topPos.offset(0, 0, -1), logs, config);
            this.placeLog(world, topPos.offset(0, 1, -1), logs, config);
            this.placeLog(world, topPos.offset(-1, 1, -1), logs, config);
            this.placeLog(world, topPos.offset(-1, 2, -1), logs, config);
            this.placeLog(world, topPos.offset(-1, 3, -1), logs, config);
            this.placeLog(world, topPos.offset(-2, 3, 0), logs, config);
            this.placeLog(world, topPos.offset(-2, 4, 0), logs, config);
            this.placeLog(world, topPos.offset(-2, 4, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 5, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 5, 2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 5, 3), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-1, 5, 3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-1, 6, 3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(0, 6, 3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(1, 6, 3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(2, 6, 3), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 6, 2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 7, 2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 7, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(1, 8, 0), Direction.Axis.X, logs, config);

            this.placeLog(world, topPos.offset(0, 0, 1), logs, config);
            this.placeLog(world, topPos.offset(0, 1, 1), logs, config);
            this.placeLog(world, topPos.offset(1, 1, 1), logs, config);
            this.placeLog(world, topPos.offset(1, 2, 1), logs, config);
            this.placeLog(world, topPos.offset(1, 3, 1), logs, config);
            this.placeLog(world, topPos.offset(2, 3, 0), logs, config);
            this.placeLog(world, topPos.offset(2, 4, 0), logs, config);
            this.placeLog(world, topPos.offset(2, 4, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 5, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 5, -2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(2, 5, -3), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(1, 5, -3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(1, 6, -3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(0, 6, -3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-1, 6, -3), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-2, 6, -3), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 6, -2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 7, -2), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-2, 7, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-1, 8, 0), Direction.Axis.X, logs, config);

            this.placeLeaves(world, topPos.offset(0, 1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 0, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 0, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 0, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 0, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(1, -1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, -1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -1, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -1, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(1, -2, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, -2, 0), leaves, config);

            this.placeLeaves(world, topPos.offset(0, 7, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 6, 2), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 6, 4), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, 2), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, 4), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 5, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 4, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 4, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 4, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 3, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 3, 3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 2, 3), leaves, config);

            this.placeLeaves(world, topPos.offset(0, 7, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 6, -2), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 6, -4), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, -2), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, -4), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 5, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 5, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 4, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 4, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 4, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 3, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 3, -3), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 2, -3), leaves, config);

            this.placeLeaves(world, topPos.offset(1, 6, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 6, 0), leaves, config);
        }
        else
        {
            this.placeLog(world, topPos.offset(-1, 0, 0), logs, config);
            this.placeLog(world, topPos.offset(-1, 1, 0), logs, config);
            this.placeLog(world, topPos.offset(-1, 1, -1), logs, config);
            this.placeLog(world, topPos.offset(-1, 2, -1), logs, config);
            this.placeLog(world, topPos.offset(-1, 3, -1), logs, config);
            this.placeLog(world, topPos.offset(0, 3, -2), logs, config);
            this.placeLog(world, topPos.offset(0, 4, -2), logs, config);
            this.placeLog(world, topPos.offset(1, 4, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(1, 5, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(2, 5, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(3, 5, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(3, 5, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(3, 6, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(3, 6, 0), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(3, 6, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(3, 6, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(2, 6, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(2, 7, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(1, 7, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(0, 8, 1), Direction.Axis.Z, logs, config);

            this.placeLog(world, topPos.offset(1, 0, 0), logs, config);
            this.placeLog(world, topPos.offset(1, 1, 0), logs, config);
            this.placeLog(world, topPos.offset(1, 1, 1), logs, config);
            this.placeLog(world, topPos.offset(1, 2, 1), logs, config);
            this.placeLog(world, topPos.offset(1, 3, 1), logs, config);
            this.placeLog(world, topPos.offset(0, 3, 2), logs, config);
            this.placeLog(world, topPos.offset(0, 4, 2), logs, config);
            this.placeLog(world, topPos.offset(-1, 4, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-1, 5, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-2, 5, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-3, 5, 2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-3, 5, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-3, 6, 1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-3, 6, 0), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-3, 6, -1), Direction.Axis.Z, logs, config);
            this.placeLog(world, topPos.offset(-3, 6, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-2, 6, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-2, 7, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(-1, 7, -2), Direction.Axis.X, logs, config);
            this.placeLog(world, topPos.offset(0, 8, -1), Direction.Axis.Z, logs, config);

            this.placeLeaves(world, topPos.offset(0, 1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 0, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 0, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(1, 0, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, 0, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -1, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -1, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(1, -1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-1, -1, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -2, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, -2, -1), leaves, config);

            this.placeLeaves(world, topPos.offset(3, 7, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(2, 6, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(4, 6, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(2, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(4, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 5, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 4, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 4, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 4, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 3, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 3, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(3, 2, -1), leaves, config);

            this.placeLeaves(world, topPos.offset(-3, 7, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-2, 6, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-4, 6, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-2, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-4, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 5, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 5, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 4, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 4, 0), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 4, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 3, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 3, -1), leaves, config);
            this.placeLeaves(world, topPos.offset(-3, 2, 1), leaves, config);

            this.placeLeaves(world, topPos.offset(0, 6, 1), leaves, config);
            this.placeLeaves(world, topPos.offset(0, 6, -1), leaves, config);
        }

        this.placeLog(world, topPos.offset(0, 9, 0), logs, config);

        this.placeLeaves(world, topPos.offset(0, 10, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(1, 9, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(-1, 9, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 9, 1), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 9, -1), leaves, config);
        this.placeLeaves(world, topPos.offset(1, 8, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(-1, 8, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 8, 1), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 8, -1), leaves, config);
        this.placeLeaves(world, topPos.offset(1, 7, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(-1, 7, 0), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 7, 1), leaves, config);
        this.placeLeaves(world, topPos.offset(0, 7, -1), leaves, config);

        if (random.nextInt(2) == 0)
        {
            generateTop(world, topPos.above(12), logs, leaves, config);
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

    // generate the top of the tree
    public void generateTop(LevelAccessor world, BlockPos pos, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, EmpyrealTreeConfiguration config)
    {
        placeLeaves(world, pos.offset(0, 0, 0), leaves, config);
        placeLeaves(world, pos.offset(1, 0, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 0, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 0, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 0, -1), leaves, config);

        placeLog(world, pos.offset(0, 1, 0), logs, config);
        placeLeaves(world, pos.offset(1, 1, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 1, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 1, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 1, -1), leaves, config);
        placeLeaves(world, pos.offset(1, 1, 1), leaves, config);
        placeLeaves(world, pos.offset(1, 1, -1), leaves, config);
        placeLeaves(world, pos.offset(-1, 1, 1), leaves, config);
        placeLeaves(world, pos.offset(-1, 1, -1), leaves, config);
        placeLeaves(world, pos.offset(2, 1, 0), leaves, config);
        placeLeaves(world, pos.offset(-2, 1, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 1, 2), leaves, config);
        placeLeaves(world, pos.offset(0, 1, -2), leaves, config);

        placeLog(world, pos.offset(0, 2, 0), logs, config);
        placeLeaves(world, pos.offset(1, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 2, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 2, -1), leaves, config);
        placeLeaves(world, pos.offset(1, 2, 1), leaves, config);
        placeLeaves(world, pos.offset(1, 2, -1), leaves, config);
        placeLeaves(world, pos.offset(-1, 2, 1), leaves, config);
        placeLeaves(world, pos.offset(-1, 2, -1), leaves, config);
        placeLeaves(world, pos.offset(2, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(-2, 2, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 2, 2), leaves, config);
        placeLeaves(world, pos.offset(0, 2, -2), leaves, config);
        placeLeaves(world, pos.offset(2, 2, -1), leaves, config);
        placeLeaves(world, pos.offset(-2, 2, 1), leaves, config);
        placeLeaves(world, pos.offset(-1, 2, 2), leaves, config);
        placeLeaves(world, pos.offset(1, 2, -2), leaves, config);
        placeLeaves(world, pos.offset(2, 2, 1), leaves, config);
        placeLeaves(world, pos.offset(-2, 2, -1), leaves, config);
        placeLeaves(world, pos.offset(1, 2, 2), leaves, config);
        placeLeaves(world, pos.offset(-1, 2, -2), leaves, config);

        placeLog(world, pos.offset(0, 3, 0), logs, config);
        placeLeaves(world, pos.offset(1, 3, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 3, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 3, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 3, -1), leaves, config);
        placeLeaves(world, pos.offset(1, 3, 1), leaves, config);
        placeLeaves(world, pos.offset(1, 3, -1), leaves, config);
        placeLeaves(world, pos.offset(-1, 3, 1), leaves, config);
        placeLeaves(world, pos.offset(-1, 3, -1), leaves, config);
        placeLeaves(world, pos.offset(2, 3, 0), leaves, config);
        placeLeaves(world, pos.offset(-2, 3, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 3, 2), leaves, config);
        placeLeaves(world, pos.offset(0, 3, -2), leaves, config);

        placeLog(world, pos.offset(0, 4, 0), logs, config);
        placeLeaves(world, pos.offset(1, 4, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 4, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 4, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 4, -1), leaves, config);

        placeLog(world, pos.offset(0, 5, 0), logs, config);
        placeLeaves(world, pos.offset(1, 5, 0), leaves, config);
        placeLeaves(world, pos.offset(-1, 5, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 5, 1), leaves, config);
        placeLeaves(world, pos.offset(0, 5, -1), leaves, config);

        placeLeaves(world, pos.offset(0, 6, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 7, 0), leaves, config);
        placeLeaves(world, pos.offset(0, 8, 0), leaves, config);
    }
}
