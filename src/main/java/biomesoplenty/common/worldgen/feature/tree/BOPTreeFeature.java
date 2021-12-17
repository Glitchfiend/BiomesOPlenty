/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.configurations.BOPTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.material.Material;

import java.util.Collection;
import java.util.Random;
import java.util.function.BiConsumer;

public abstract class BOPTreeFeature<FC extends BOPTreeConfiguration> extends TreeFeature
{
    protected BOPTreeFeature(Codec<FC> codec)
    {
        super((Codec)codec);
    }

    public boolean placeLeaves(LevelAccessor level, BlockPos pos, BiConsumer<BlockPos, BlockState> leaves, FC config)
    {
        if (canReplace(level, pos))
        {
            leaves.accept(pos, config.foliageProvider.getState(level.getRandom(), pos));
            return true;
        }
        return false;
    }

    public boolean placeAltLeaves(LevelAccessor level, BlockPos pos, BiConsumer<BlockPos, BlockState> leaves, FC config)
    {
        if (canReplace(level, pos))
        {
            leaves.accept(pos, config.altFoliageProvider.getState(level.getRandom(), pos));
            return true;
        }
        return false;
    }

    public boolean placeLog(LevelAccessor world, BlockPos pos, BiConsumer<BlockPos, BlockState> logs, FC config)
    {
        return this.placeLog(world, pos, null, logs, config);
    }

    public boolean placeLog(LevelAccessor level, BlockPos pos, Direction.Axis axis, BiConsumer<BlockPos, BlockState> logs, FC config)
    {
        Property logAxisProperty = this.getLogAxisProperty(level, pos, config);
        BlockState log = config.trunkProvider.getState(level.getRandom(), pos);
        BlockState directedLog = (axis != null && logAxisProperty != null) ? log.setValue(logAxisProperty, axis) : log;

        if (canReplace(level, pos))
        {
            // Logs must be added to the "changedBlocks" so that the leaves have their distance property updated,
            // preventing incorrect decay
            logs.accept(pos, directedLog);
            return true;
        }
        return false;
    }

    public boolean setVine(LevelAccessor world, Random rand, BlockPos pos, Direction side, int length, FC config)
    {
        BlockState vine = config.vineProvider.getState(rand, pos);
        BlockState directedVine = vine.getBlock() instanceof VineBlock ? vine.setValue(VineBlock.NORTH, Boolean.valueOf(side == Direction.NORTH)).setValue(VineBlock.EAST, Boolean.valueOf(side == Direction.EAST)).setValue(VineBlock.SOUTH, Boolean.valueOf(side == Direction.SOUTH)).setValue(VineBlock.WEST, Boolean.valueOf(side == Direction.WEST)) : vine;
        boolean setOne = false;
        while (world.getBlockState(pos).isAir() && length > 0 && rand.nextInt(12) > 0)
        {
            setBlock(world, pos, directedVine);
            setOne = true;
            length--;
            pos = pos.below();
        }
        return setOne;
    }

    public boolean setHanging(LevelAccessor level, BlockPos pos, FC config)
    {
        BlockState hanging = config.hangingProvider.getState(level.getRandom(), pos);

        if (this.canReplace(level, pos))
        {
            setBlock(level, pos, hanging);
        }
        return false;
    }

    public boolean setTrunkFruit(LevelAccessor level, BlockPos pos, FC config)
    {
        BlockState trunkFruit = config.trunkFruitProvider.getState(level.getRandom(), pos);

        if (trunkFruit == null)
        {
            return false;
        }
        if (this.canReplace(level, pos))
        {
            setBlock(level, pos, trunkFruit);
        }
        return false;
    }

    protected boolean canReplace(LevelAccessor level, BlockPos pos)
    {
        return TreeFeature.isAirOrLeaves(level, pos) || level.isStateAtPosition(pos, (state) -> {
            Material material = state.getMaterial();
            Block block = state.getBlock();
            return material == Material.REPLACEABLE_PLANT || state.is(BlockTags.SAPLINGS) || block == Blocks.VINE || block == BOPBlocks.WILLOW_VINE || block == BOPBlocks.DEAD_BRANCH || block == Blocks.MOSS_CARPET || block instanceof BushBlock;
        });
    }

    protected Property getLogAxisProperty(LevelAccessor level, BlockPos pos, FC config)
    {
        BlockState log = config.trunkProvider.getState(level.getRandom(), pos);

        for (Property property : log.getProperties())
        {
            Collection allowedValues = property.getPossibleValues();
            if (allowedValues.contains(Direction.Axis.X) && allowedValues.contains(Direction.Axis.Y) && allowedValues.contains(Direction.Axis.Z))
            {
                return property;
            }
        }
        return null;
    }
}