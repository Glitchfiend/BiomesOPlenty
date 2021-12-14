package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.worldgen.feature.configurations.BasicTreeConfiguration;
import biomesoplenty.common.worldgen.feature.configurations.BigTreeConfiguration;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;

public class BigTreeFeature extends BOPTreeFeature<BigTreeConfiguration>
{
    private final double trunkHeightScale = 0.618;
    private final double branchSlope = 0.381;
    private final double widthScale = 1;
    private final int trunkWidth = 1;

    public BigTreeFeature(Codec<BigTreeConfiguration> codec)
    {
        super(codec);
    }

    // Create a circular cross section.
    //
    // Used to nearly everything in the foliage, branches, and trunk.
    // This is a good target for performance optimization.

    // Passed values:
    // pos is the center location of the cross section
    // radius is the radius of the section from the center
    // direction is the direction the cross section is pointed, 0 for x, 1
    // for y, 2 for z material is the index number for the material to use
    private void crossSection(LevelAccessor world, BlockPos pos, float radius, Random random, BiConsumer<BlockPos, BlockState> leaves, BigTreeConfiguration config)
    {
        final int r = (int)((double)radius + trunkHeightScale);

        for (int dx = -r; dx <= r; dx++)
        {
            for (int dz = -r; dz <= r; dz++)
            {
                if (Math.pow((double)Math.abs(dx) + 0.5D, 2.0D) + Math.pow((double)Math.abs(dz) + 0.5D, 2.0D) <= (double)(radius * radius))
                {
                    BlockPos blockpos = pos.offset(dx, 0, dz);
                    if (this.canReplace(world, blockpos))
                    {
                        // Mojang sets leaves via the method used for logs. Probably intentional?
                        if (config.altFoliageProvider.getState(random, pos) != Blocks.AIR.defaultBlockState())
                        {
                            int rand = random.nextInt(4);

                            if (rand == 0)
                            {
                                this.placeAltLeaves(world, blockpos, leaves, config);
                            }
                            else
                            {
                                this.placeLeaves(world, blockpos,leaves, config);
                            }
                        }
                        else
                        {
                            this.placeLeaves(world, blockpos, leaves, config);
                        }
                    }
                }
            }
        }

    }

    // Take the y position relative to the base of the tree.
    // Return the distance the foliage should be from the trunk axis.
    // Return a negative number if foliage should not be created at this
    // height.  This method is intended for overriding in child classes,
    // allowing different shaped trees.  This method should return a
    // consistent value for each y (don't randomize).
    private float treeShape(int height, int y)
    {
        if ((float)y < (float)height * 0.3F)
        {
            return -1.0F;
        }

        float radius = (float)height / 2.0F;
        float adjacent = radius - (float)y;

        float distance = Mth.sqrt(radius * radius - adjacent * adjacent);

        if (adjacent == 0.0F)
        {
            distance = radius;
        }
        else if (Math.abs(adjacent) >= radius)
        {
            return 0.0F;
        }

        return distance * 0.5F;
    }


    // Take the y position relative to the base of the foliage cluster.
    // Return the radius of the cluster at this y
    // Return a negative number if no foliage should be created at this
    // level. This method is intended for overriding in child classes,
    // allowing foliage of different sizes and shapes.
    private float foliageShape(int y, BigTreeConfiguration config)
    {
        /*if (y >= 0 && y < foliageHeight)
        {
            return y != 0 && y != 4 ? 3.0F : 2.0F;
        }
        else
        {
            return -1.0F;
        }*/


        if (y < 0 || y >= config.foliageHeight)
        {
            return -1.0F;
        }
        else if (y == 0 || y == config.foliageHeight - 1)
        {
            return 2.0F;
        }
        else
        {
            return 3.0F;
        }
    }

    // Generate a cluster of foliage, with the base at blockPos
    // The shape of the cluster is derived from foliageShape
    // crossection is called to make each level.
    private void foliageCluster(LevelAccessor world, BlockPos pos, Random random, BiConsumer<BlockPos, BlockState> leaves, BigTreeConfiguration config)
    {
        for (int y = 0; y < config.foliageHeight; y++)
        {
            this.crossSection(world, pos.above(y), this.foliageShape(y, config), random, leaves, config);
        }
    }

    // Check from coordinates start to end (both inclusive) for blocks
    // other than air and foliage If a block other than air and foliage is
    // found, return the number of steps taken.
    // If no block other than air and foliage is found, return -1.
    // Examples:
    // If the third block searched is stone, return 2
    // If the first block searched is lava, return 0
    private int checkLineAndOptionallySet(LevelAccessor world, BlockPos startPos, BlockPos endPos, boolean set, BiConsumer<BlockPos, BlockState> logs, BigTreeConfiguration config)
    {
        if (!set && Objects.equals(startPos, endPos)) {
            return -1;
        } else {
            //The distance between the two points, may be negative if the second pos is smaller
            BlockPos delta = endPos.offset(-startPos.getX(), -startPos.getY(), -startPos.getZ());

            int steps = this.getGreatestDistance(delta);

            //How much should be incremented with each iteration relative
            //to the greatest distance which will have a value of 1.0F.
            float dx = (float)delta.getX() / (float)steps;
            float dy = (float)delta.getY() / (float)steps;
            float dz = (float)delta.getZ() / (float)steps;

            //Iterates over all values between the start pos and end pos
            for (int j = 0; j <= steps; ++j)
            {
                BlockPos deltaPos = startPos.offset((double)(0.5F + (float)j * dx), (double)(0.5F + (float)j * dy), (double)(0.5F + (float)j * dz));
                if (set)
                {
                    this.placeLog(world, deltaPos, this.getLogAxis(startPos, deltaPos), logs, config);
                }
                else if (!this.isFree(world, deltaPos))
                {
                    return j;
                }
            }

            return -1;
        }
    }

    /**
     * Returns the absolute greatest distance in the BlockPos object.
     */
    private int getGreatestDistance(BlockPos posIn)
    {
        int i = Mth.abs(posIn.getX());
        int j = Mth.abs(posIn.getY());
        int k = Mth.abs(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private Direction.Axis getLogAxis(BlockPos startPos, BlockPos endPos)
    {
        Direction.Axis axis = Direction.Axis.Y;

        //Find the difference between the start and end pos
        int xDiff = Math.abs(endPos.getX() - startPos.getX());
        int zDiff = Math.abs(endPos.getZ() - startPos.getZ());
        int maxDiff = Math.max(xDiff, zDiff);

        //Check if the distance between the two positions is greater than 0 on either
        //the x or the z axis. axis is set to the axis with the greatest distance
        if (maxDiff > 0)
        {
            if (xDiff == maxDiff)
            {
                axis = Direction.Axis.X;
            }
            else if (zDiff == maxDiff)
            {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }

    private void makeFoliage(LevelAccessor worldIn, int height, BlockPos pos, List<FoliageCoordinates> coordinates, Random random, BiConsumer<BlockPos, BlockState> leaves, BigTreeConfiguration config)
    {
        for (FoliageCoordinates coordinate : coordinates)
        {
            if (this.trimBranches(height, coordinate.getBranchBase() - pos.getY()))
            {
                this.foliageCluster(worldIn, coordinate, random, leaves, config);
            }
        }
    }

    private boolean trimBranches(int height, int localY)
    {
        return (double)localY >= (double)height * 0.2D;
    }

    private void makeTrunk(LevelAccessor world, BlockPos pos, int height, BiConsumer<BlockPos, BlockState> logs, BigTreeConfiguration config)
    {
        this.checkLineAndOptionallySet(world, pos, pos.above(height), true, logs, config);

        if (trunkWidth == 2)
        {
            this.checkLineAndOptionallySet(world, pos.east(), pos.above(height).east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.east().south(), pos.above(height).east().south(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.south(), pos.above(height).south(), true, logs, config);
        }

        if (trunkWidth == 4)
        {
            this.checkLineAndOptionallySet(world, pos.east(), pos.above(height).east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.east().south(), pos.above(height).east().south(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.south(), pos.above(height).south(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.north(), pos.above(height).north(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.north().east(), pos.above(height).north().east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.east().east(), pos.above(height).east().east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.south().east().east(), pos.above(height).south().east().east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.south().south().east(), pos.above(height).south().south().east(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.south().south(), pos.above(height).south().south(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.west().south(), pos.above(height).west().south(), true, logs, config);
            this.checkLineAndOptionallySet(world, pos.west(), pos.above(height).west(), true, logs, config);
        }
    }

    private void makeBranches(LevelAccessor world, int height, BlockPos origin, List<FoliageCoordinates> coordinates, BiConsumer<BlockPos, BlockState> logs, BigTreeConfiguration config)
    {
        for (FoliageCoordinates coordinate : coordinates)
        {
            int branchBase = coordinate.getBranchBase();
            BlockPos baseCoord = new BlockPos(origin.getX(), branchBase, origin.getZ());
            if (!baseCoord.equals(coordinate) && this.trimBranches(height, branchBase - origin.getY()))
            {
                this.checkLineAndOptionallySet(world, baseCoord, coordinate, true, logs, config);
            }
        }
    }

    public boolean doPlace(WorldGenLevel world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, TreeConfiguration configBase)
    {
        BigTreeConfiguration config = (BigTreeConfiguration)configBase;

        int height = this.checkLocation(world, pos, config.minHeight + random.nextInt(config.maxHeight), logs, config);
        if (height == -1) {
            return false;
        } else {
            this.setBlock(world, pos.below(), Blocks.DIRT.defaultBlockState());
            int trunkHeight = (int)((double)height * this.trunkHeightScale);

            if (trunkHeight >= height) {
                trunkHeight = height - 1;
            }

            // Define foliage clusters per y
            int clustersPerY = (int)(1.382D + Math.pow(config.foliageDensity * (double)height / 13.0D, 2.0D));

            if (clustersPerY < 1)
            {
                clustersPerY = 1;
            }

            int trunkTop = pos.getY() + trunkHeight;
            int relativeY = height - config.foliageHeight;

            List<FoliageCoordinates> foliageCoords = Lists.newArrayList();
            foliageCoords.add(new FoliageCoordinates(pos.above(relativeY), trunkTop));

            for(; relativeY >= 0; --relativeY)
            {
                float treeShape = this.treeShape(height, relativeY);

                if (treeShape < 0.0F)
                {
                    continue;
                }

                for (int i = 0; i < clustersPerY; ++i)
                {
                    final double radius = 1.0D * treeShape * (random.nextFloat() + 0.328D);
                    final double angle = random.nextFloat() * 2.0F * Math.PI;

                    final double x = radius * Math.sin(angle) + 0.5D;
                    final double z = radius * Math.cos(angle) + 0.5D;

                    final BlockPos checkStart = pos.offset(x, relativeY - 1, z);
                    final BlockPos checkEnd = checkStart.above(5);

                    // check the center column of the cluster for obstructions.
                    if (this.checkLineAndOptionallySet(world, checkStart, checkEnd, false, logs, config) == -1)
                    {
                        // If the cluster can be created, check the branch path for obstructions.
                        final int dx = pos.getX() - checkStart.getX();
                        final int dz = pos.getZ() - checkStart.getZ();

                        final double branchHeight = checkStart.getY() - Math.sqrt(dx * dx + dz * dz) * this.branchSlope;
                        final int branchTop = branchHeight > trunkTop ? trunkTop : (int)branchHeight;
                        final BlockPos checkBranchBase = new BlockPos(pos.getX(), branchTop, pos.getZ());

                        // Now check the branch path
                        if (this.checkLineAndOptionallySet(world, checkBranchBase, checkStart, false, logs, config) == -1)
                        {
                            // If the branch path is clear, add the position to the list of foliage positions
                            foliageCoords.add(new FoliageCoordinates(checkStart, checkBranchBase.getY()));
                        }
                    }
                }
            }

            this.makeFoliage(world, height, pos, foliageCoords, random, leaves, config);
            this.makeTrunk(world, pos, trunkHeight, logs, config);
            this.makeBranches(world, height, pos, foliageCoords, logs, config);
            return true;
        }
    }

    private int checkLocation(LevelAccessor world, BlockPos pos, int height, BiConsumer<BlockPos, BlockState> logs, BigTreeConfiguration config)
    {
        int step = this.checkLineAndOptionallySet(world, pos, pos.above(height - 1), false, logs, config);

        if (step == -1)
        {
            return height;
        }
        else
        {
            return step < 6 ? -1 : step;
        }
    }

    static class FoliageCoordinates extends BlockPos
    {
        private final int branchBase;

        public FoliageCoordinates(BlockPos pos, int branchBase)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.branchBase = branchBase;
        }

        public int getBranchBase()
        {
            return this.branchBase;
        }
    }
}
