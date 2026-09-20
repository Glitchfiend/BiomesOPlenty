/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.worldgen.feature.configurations.BOPTreeConfiguration;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.BiConsumer;

public abstract class BOPTreeFeature<FC extends BOPTreeConfiguration> implements Feature
{
    private static final int BLOCK_UPDATE_FLAGS = 19;

    protected final FC config;

    protected BOPTreeFeature(FC config)
    {
        this.config = config;
    }

    public FC config()
    {
        return this.config;
    }

    protected abstract boolean doPlace(WorldGenLevel level, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves);

    @Override
    public void setBlock(LevelWriter level, BlockPos pos, BlockState state)
    {
        level.setBlock(pos, state, BLOCK_UPDATE_FLAGS);
    }

    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        Set<BlockPos> rootPositions = Sets.newHashSet();
        Set<BlockPos> trunks = Sets.newHashSet();
        final Set<BlockPos> foliage = Sets.newHashSet();
        Set<BlockPos> decorations = Sets.newHashSet();

        BiConsumer<BlockPos, BlockState> rootSetter = (pos, state) -> {
            rootPositions.add(pos.immutable());
            level.setBlock(pos, state, BLOCK_UPDATE_FLAGS);
        };
        BiConsumer<BlockPos, BlockState> trunkSetter = (pos, state) -> {
            trunks.add(pos.immutable());
            level.setBlock(pos, state, BLOCK_UPDATE_FLAGS);
        };
        FoliagePlacer.FoliageSetter foliageSetter = new FoliagePlacer.FoliageSetter()
        {
            @Override
            public void set(BlockPos pos, BlockState state)
            {
                foliage.add(pos.immutable());
                level.setBlock(pos, state, BLOCK_UPDATE_FLAGS);
            }

            @Override
            public boolean isSet(BlockPos pos)
            {
                return foliage.contains(pos);
            }
        };
        BiConsumer<BlockPos, BlockState> decorationSetter = (pos, state) -> {
            decorations.add(pos.immutable());
            level.setBlock(pos, state, BLOCK_UPDATE_FLAGS);
        };

        boolean result = this.doPlace(level, random, origin, rootSetter, trunkSetter, foliageSetter);

        if (result && (!trunks.isEmpty() || !foliage.isEmpty()))
        {
            if (!this.config.decorators.isEmpty())
            {
                TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, decorationSetter, random, trunks, foliage, rootPositions);
                this.config.decorators.forEach(decorator -> decorator.place(decoratorContext));
            }

            return BoundingBox.encapsulatingPositions(Iterables.concat(rootPositions, trunks, foliage, decorations)).map(bounds -> {
                DiscreteVoxelShape shape = updateLeaves(level, bounds, trunks, decorations, rootPositions);
                StructureTemplate.updateShapeAtEdge(level, 3, shape, bounds.minX(), bounds.minY(), bounds.minZ());
                return true;
            }).orElse(false);
        }

        return false;
    }

    private static DiscreteVoxelShape updateLeaves(LevelAccessor level, BoundingBox bounds, Set<BlockPos> logs, Set<BlockPos> decorationSet, Set<BlockPos> rootPositions)
    {
        DiscreteVoxelShape shape = new BitSetDiscreteVoxelShape(bounds.getXSpan(), bounds.getYSpan(), bounds.getZSpan());
        int maxDistance = 7;
        List<Set<BlockPos>> toCheck = Lists.newArrayList();

        for (int i = 0; i < maxDistance; i++)
        {
            toCheck.add(Sets.newHashSet());
        }

        for (BlockPos pos : Lists.newArrayList(Sets.union(decorationSet, rootPositions)))
        {
            if (bounds.isInside(pos))
            {
                shape.fill(pos.getX() - bounds.minX(), pos.getY() - bounds.minY(), pos.getZ() - bounds.minZ());
            }
        }

        BlockPos.MutableBlockPos neighborPos = new BlockPos.MutableBlockPos();
        int smallestDistance = 0;
        toCheck.get(0).addAll(logs);

        while (true)
        {
            while (smallestDistance >= maxDistance || !toCheck.get(smallestDistance).isEmpty())
            {
                if (smallestDistance >= maxDistance)
                {
                    return shape;
                }

                Iterator<BlockPos> iterator = toCheck.get(smallestDistance).iterator();
                BlockPos pos = iterator.next();
                iterator.remove();

                if (bounds.isInside(pos))
                {
                    if (smallestDistance != 0)
                    {
                        BlockState state = level.getBlockState(pos);

                        if (state.hasProperty(BlockStateProperties.DISTANCE))
                        {
                            level.setBlock(pos, state.setValue(BlockStateProperties.DISTANCE, smallestDistance), BLOCK_UPDATE_FLAGS);
                        }
                    }

                    shape.fill(pos.getX() - bounds.minX(), pos.getY() - bounds.minY(), pos.getZ() - bounds.minZ());

                    for (Direction direction : Direction.values())
                    {
                        neighborPos.setWithOffset(pos, direction);

                        if (bounds.isInside(neighborPos))
                        {
                            int xInShape = neighborPos.getX() - bounds.minX();
                            int yInShape = neighborPos.getY() - bounds.minY();
                            int zInShape = neighborPos.getZ() - bounds.minZ();

                            if (!shape.isFull(xInShape, yInShape, zInShape))
                            {
                                BlockState currentState = level.getBlockState(neighborPos);
                                OptionalInt distance = LeavesBlock.getOptionalDistanceAt(currentState);

                                if (!distance.isEmpty())
                                {
                                    int newDistance = Math.min(distance.getAsInt(), smallestDistance + 1);

                                    if (newDistance < maxDistance)
                                    {
                                        toCheck.get(newDistance).add(neighborPos.immutable());
                                        smallestDistance = Math.min(smallestDistance, newDistance);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            smallestDistance++;
        }
    }

    public boolean placeLeaves(WorldGenLevel level, BlockPos pos, FoliagePlacer.FoliageSetter leaves, FC config)
    {
        if (canReplace(level, pos))
        {
            leaves.set(pos, config.foliageProvider.getState(level, level.getRandom(), pos));
            return true;
        }
        return false;
    }

    public boolean placeAltLeaves(WorldGenLevel level, BlockPos pos, FoliagePlacer.FoliageSetter leaves, FC config)
    {
        if (canReplace(level, pos))
        {
            leaves.set(pos, config.altFoliageProvider.getState(level, level.getRandom(), pos));
            return true;
        }
        return false;
    }

    public boolean placeLog(WorldGenLevel world, BlockPos pos, BiConsumer<BlockPos, BlockState> logs, FC config)
    {
        return this.placeLog(world, pos, null, logs, config);
    }

    public boolean placeLog(WorldGenLevel level, BlockPos pos, Direction.Axis axis, BiConsumer<BlockPos, BlockState> logs, FC config)
    {
        Property logAxisProperty = this.getLogAxisProperty(level, pos, config);
        BlockState log = config.trunkProvider.getState(level, level.getRandom(), pos);
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

    public boolean setVine(WorldGenLevel world, RandomSource rand, BlockPos pos, Direction side, int length, FC config)
    {
        BlockState vine = config.vineProvider.getState(world, rand, pos);
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

    public boolean setHanging(WorldGenLevel level, BlockPos pos, FC config)
    {
        BlockState hanging = config.hangingProvider.getState(level, level.getRandom(), pos);

        if (this.canReplace(level, pos))
        {
            setBlock(level, pos, hanging);
        }
        return false;
    }

    public boolean setTrunkFruit(WorldGenLevel level, BlockPos pos, FC config)
    {
        BlockState trunkFruit = config.trunkFruitProvider.getState(level, level.getRandom(), pos);

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
            Block block = state.getBlock();
            return state.is(BlockTags.REPLACEABLE_BY_TREES) || state.is(BlockItemTags.SAPLINGS.block()) || block == Blocks.VINE || block == BOPBlocks.WILLOW_VINE || block == BOPBlocks.DEAD_BRANCH || block == Blocks.MOSS_CARPET || block == BOPBlocks.SPANISH_MOSS || block instanceof VegetationBlock;
        });
    }

    protected Property getLogAxisProperty(WorldGenLevel level, BlockPos pos, FC config)
    {
        BlockState log = config.trunkProvider.getState(level, level.getRandom(), pos);

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

    public static boolean isFree(LevelSimulatedReader level, BlockPos pos)
    {
        return TreeFeature.validTreePos(level, pos) || level.isStateAtPosition(pos, (state) -> {
            return state.is(BlockTags.LOGS);
        });
    }
}
