/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.worldgen.feature.configurations.BasicTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.function.BiConsumer;

public class BasicTreeFeature extends BOPTreeFeature<BasicTreeConfiguration>
{
    public BasicTreeFeature(Codec<BasicTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos pos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, TreeConfiguration configBase)
    {
        BasicTreeConfiguration config = (BasicTreeConfiguration)configBase;

        int height = random.nextInt(config.maxHeight - config.minHeight) + config.minHeight;
        boolean hasSpace = true;

        //Generate only if we are above the lowest bedrock level (1) and reach less than the world height
        //There must be a gap of 1 between the top leaf block and the world height
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256)
        {
            int radius;

            for (int y = pos.getY(); y <= pos.getY() + 1 + height; y++)
            {
                radius = 1;

                //Don't check for space on the first level, if we are a sapling then there will
                //already be a block here (the sapling itself)
                if (y == pos.getY())
                {
                    radius = 0;
                }

                //At and above the top log block, require a radius of 2 to be empty
                if (y >= pos.getY() + 1 + height - 2)
                {
                    radius = 2;
                }

                for (int x = pos.getX() - radius; x <= pos.getX() + radius && hasSpace; ++x)
                {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius && hasSpace; ++z)
                    {
                        if (y >= 0 && y < 256)
                        {
                            if (!this.canReplace(world, new BlockPos(x, y, z)))
                            {
                                hasSpace = false;
                            }
                        }
                        else
                        {
                            hasSpace = false;
                        }
                    }
                }
            }

            if (!hasSpace)
            {
                return false;
            }
            else
            {
                BlockPos soilPos = pos.below();
                Block soil = world.getBlockState(soilPos).getBlock();

                world.setBlock(soilPos, Blocks.DIRT.defaultBlockState(), 3);
                int leavesLayers = (config.leafLayers - 1);

                //Generates leaves at the top of the tree, going one block above the top log (<= rather than <)
                for (int y = pos.getY() + height - leavesLayers; y <= pos.getY() + height; y++)
                {
                    //Determines the distance from the top of the tree as a negative number
                    int currentLayer = y - (pos.getY() + height);
                    //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                    //the radius closer to the top of the tree. (2, 2, 1, 1)
                    int leavesRadius =  config.maxLeavesRadius - currentLayer / config.leavesLayerHeight;

                    for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                    {
                        int xDiff = x - pos.getX();

                        for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; ++z)
                        {
                            int zDiff = z - pos.getZ();

                            //Randomly prevent the generation of leaves on the corners of each layer
                            //If the layer is the top layer, never generate the corners
                            if (Math.abs(xDiff) != leavesRadius || Math.abs(zDiff) != leavesRadius || random.nextInt(2) != 0 && currentLayer != 0)
                            {
                                BlockPos leavesPos = new BlockPos(x, y, z);
                                if (this.canReplace(world, leavesPos))
                                {
                                    if (config.altFoliageProvider.getState(random, pos) != Blocks.AIR.defaultBlockState())
                                    {
                                        if (random.nextInt(4) == 0)
                                        {
                                            this.placeAltLeaves(world, leavesPos, leaves, config);
                                        }
                                        else
                                        {
                                            this.placeLeaves(world, leavesPos, leaves, config);
                                        }
                                    }
                                    else
                                    {
                                        this.placeLeaves(world, leavesPos, leaves, config);
                                    }
                                }
                            }
                        }
                    }
                }

                this.generateTrunk(world, pos, height, logs, config);

                if (config.vineProvider.getState(random, pos) != Blocks.AIR.defaultBlockState())
                {
                    for (int y = pos.getY() - leavesLayers + height; y <= pos.getY() + height; y++)
                    {
                        //Determines the distance from the top of the tree as a negative number
                        int currentLayer = y - (pos.getY() + height);
                        //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                        //the radius closer to the top of the tree. (3, 3, 2, 2)
                        int leavesRadius = (config.maxLeavesRadius + config.leavesOffset) - currentLayer / config.leavesLayerHeight;

                        for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                        {
                            for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; z++)
                            {
                                BlockPos blockpos3 = new BlockPos(x, y, z);

                                //Surround leaves on the edge of the tree with vines and extend them downwards
                                if (world.getBlockState(blockpos3).getMaterial() == Material.LEAVES)
                                {
                                    BlockPos westPos = blockpos3.west();
                                    BlockPos eastPos = blockpos3.east();
                                    BlockPos northPos = blockpos3.north();
                                    BlockPos southPos = blockpos3.south();

                                    if (random.nextInt(4) == 0 && this.canPlaceVinesOn(world, westPos))
                                    {
                                        this.extendVines(world, random, westPos, Direction.EAST, config);
                                    }

                                    if (random.nextInt(4) == 0 && this.canPlaceVinesOn(world, eastPos))
                                    {
                                        this.extendVines(world, random, eastPos, Direction.WEST, config);
                                    }

                                    if (random.nextInt(4) == 0 && this.canPlaceVinesOn(world, northPos))
                                    {
                                        this.extendVines(world, random, northPos, Direction.SOUTH, config);
                                    }

                                    if (random.nextInt(4) == 0 && this.canPlaceVinesOn(world, southPos))
                                    {
                                        this.extendVines(world, random, southPos, Direction.NORTH, config);
                                    }
                                }
                            }
                        }
                    }
                }

                //Generate fruit or any other blocks that may hang off of the tree
                if (config.hangingProvider.getState(random, pos) != Blocks.AIR.defaultBlockState()) this.generateHanging(world, pos, random, height,config);

                if (config.trunkFruitProvider.getState(random, pos) != Blocks.AIR.defaultBlockState())
                {
                    if (random.nextInt(5) == 0 && height > 5)
                    {
                        for (int l3 = 0; l3 < 2; ++l3)
                        {
                            for (Direction Direction : Direction.Plane.HORIZONTAL)
                            {
                                if (random.nextInt(4 - l3) == 0)
                                {
                                    Direction Direction1 = Direction.getOpposite();
                                    this.generateTrunkFruit(world, random, random.nextInt(3), pos.offset(Direction1.getStepX(), height - 5 + l3, Direction1.getStepZ()), Direction, config);
                                }
                            }
                        }
                    }
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }

    protected void generateTrunk(LevelAccessor world, BlockPos start, int height, BiConsumer<BlockPos, BlockState> logs, BasicTreeConfiguration config)
    {
        //Create the trunk from the bottom up, using < to ensure it is covered with one layer of leaves
        for (int layer = 0; layer < height; ++layer)
        {
            BlockPos blockpos2 = start.above(layer);
            if (this.canReplace(world, blockpos2))
            {
                this.placeLog(world, start.above(layer), logs, config);
            }
        }
    }

    protected void generateHanging(LevelAccessor world, BlockPos start, RandomSource rand, int height, BasicTreeConfiguration config)
    {
        //Generate below the bottom layer of leaves
        int y = start.getY() + (height - config.leafLayers);

        for (int x = start.getX() - (config.maxLeavesRadius + 1); x <= start.getX() + (config.maxLeavesRadius + 1); x++)
        {
            for (int z = start.getZ() - (config.maxLeavesRadius + 1); z <= start.getZ() + (config.maxLeavesRadius + 1); z++)
            {
                BlockPos hangingPos = new BlockPos(x, y, z);

                if (!world.isEmptyBlock(hangingPos.above()) && (world.isEmptyBlock(hangingPos)) && rand.nextFloat() <= config.hangingChance)
                {
                    this.setHanging(world, hangingPos, config);
                }
            }
        }
    }

    private void generateTrunkFruit(LevelAccessor world, RandomSource random, int age, BlockPos pos, Direction direction, BasicTreeConfiguration config)
    {
        if (config.trunkFruitProvider.getState(random, pos) == Blocks.COCOA.defaultBlockState())
        {
            this.setBlock(world, pos, config.trunkFruitProvider.getState(random, pos).setValue(CocoaBlock.AGE, Integer.valueOf(age)).setValue(CocoaBlock.FACING, direction));
        }
        else
        {
            this.setBlock(world, pos, config.trunkFruitProvider.getState(random, pos).setValue(CocoaBlock.FACING, direction));
        }
    }

    private BlockState getVineStateForSide(RandomSource random, BlockPos pos, Direction side, BasicTreeConfiguration config)
    {
        return config.vineProvider.getState(random, pos).getBlock() instanceof VineBlock ? config.vineProvider.getState(random, pos).setValue(VineBlock.getPropertyForFace(side), Boolean.valueOf(true)) : config.vineProvider.getState(random, pos);
    }

    private void extendVines(LevelAccessor world, RandomSource random, BlockPos pos, Direction side, BasicTreeConfiguration config)
    {
        BlockState vineState = this.getVineStateForSide(random, pos, side, config);
        this.setBlock(world, pos, vineState);

        int length = 4;

        //Extend vine downwards for a maximum of 4 blocks
        for (pos = pos.below(); this.canPlaceVinesOn(world, pos) && length > 0; length--)
        {
            this.setBlock(world, pos, vineState);
            pos = pos.below();
        }
    }

    protected boolean canPlaceVinesOn(LevelAccessor world, BlockPos pos) {
        Material mat = world.getBlockState(pos).getMaterial();
        return mat == Material.AIR;
    }
}