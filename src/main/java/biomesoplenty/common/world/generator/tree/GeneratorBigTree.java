/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator.tree;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/*This class is heavily based on https://gist.github.com/grum/62cfdec0537e8db24eb3#file-bigtreefeature-java
additional information has been added from http://pastebin.com/XBLdGqXQ. This class has been cross-checked
against WorldGenBigTree to ensure any subsequent changes from Forge/Mojang have been included.*/
public class GeneratorBigTree extends GeneratorTreeBase
{
    
    // TODO: update neighbours in builder?
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorBigTree> implements IGeneratorBuilder<GeneratorBigTree>
    {        
        private int trunkWidth;
        private int foliageHeight;
        private double foliageDensity;
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.LOG.getDefaultState();
            this.leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
            this.vine = null;
            this.hanging = null;
            this.trunkFruit = null;
            this.altLeaves = null;
            this.minHeight = 5;
            this.maxHeight = 17;
            
            this.trunkWidth = 1;
            this.foliageHeight = 4;
            this.foliageDensity = 1.0F;
            
        }
        
        public Builder trunkWidth(int a) {this.trunkWidth = a; return this.self();}
        public Builder foliageHeight(int a) {this.foliageHeight = a; return this.self();}
        public Builder foliageDensity(double a) {this.foliageDensity = a; return this.self();}


        @Override
        public GeneratorBigTree create()
        {
            return new GeneratorBigTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.trunkFruit, this.altLeaves, this.minHeight, this.maxHeight, this.trunkWidth, this.foliageHeight, this.foliageDensity, false);
        }
    }
    
    
    private Random random;
    private World world;
    private BlockPos origin;
    
    private int height;
    private int trunkHeight;
    private double trunkHeightScale = 0.618;
    private double branchSlope = 0.381;
    private double widthScale = 1;
    private int trunkWidth = 1;
    //private int heightVariance = 12;
    
    //Configurable fields
    private boolean updateNeighbours;
    private int foliageHeight;
    private double foliageDensity;
    
    private List<FoliageCoords> foliageCoords;

    
    public GeneratorBigTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, IBlockState altLeaves, int minHeight, int maxHeight, int trunkWidth, int foliageHeight, double foliageDensity, boolean updateNeighbours)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, trunkFruit, altLeaves, minHeight, maxHeight);
        this.foliageHeight = foliageHeight;
        this.foliageDensity = foliageDensity;
        this.trunkWidth = trunkWidth;
        this.updateNeighbours = updateNeighbours;
    }

    protected void prepare() 
    {
        // Populate the list of foliage cluster locations.
        // Designed to be overridden in child classes to change basic
        // tree properties (trunk width, branch angle, foliage density, etc..).
        
        trunkHeight = (int) (height * trunkHeightScale);
        
        if (trunkHeight >= height) 
        {
            trunkHeight = height - 1;
        }
 
        // Define foliage clusters per y
        int clustersPerY = (int) (1.382 + Math.pow(foliageDensity * height / 13.0, 2));
        if (clustersPerY < 1) {
            clustersPerY = 1;
        }
 
        final int trunkTop = origin.getY() + trunkHeight;
        int relativeY = height - foliageHeight;
 
        foliageCoords = Lists.newArrayList();
        foliageCoords.add(new FoliageCoords(origin.up(relativeY), trunkTop));
 
        for (; relativeY >= 0; relativeY--) 
        {
            float treeShape = treeShape(relativeY);
            
            if (treeShape < 0) 
            {
                continue;
            }
 
            for (int i = 0; i < clustersPerY; i++) 
            {
                final double radius = widthScale * treeShape * (random.nextFloat() + 0.328);
                final double angle = random.nextFloat() * 2 * Math.PI;
 
                final double x = radius * Math.sin(angle) + 0.5;
                final double z = radius * Math.cos(angle) + 0.5;
 
                final BlockPos checkStart = origin.add(x, relativeY - 1, z);
                final BlockPos checkEnd = checkStart.up(foliageHeight);
 
                // check the center column of the cluster for obstructions.
                if (checkLine(checkStart, checkEnd) == -1) 
                {
                    // If the cluster can be created, check the branch path for obstructions.
                    final int dx = origin.getX() - checkStart.getX();
                    final int dz = origin.getZ() - checkStart.getZ();
 
                    final double height = checkStart.getY() - Math.sqrt(dx * dx + dz * dz) * branchSlope;
                    final int branchTop = height > trunkTop ? trunkTop : (int) height;
                    final BlockPos checkBranchBase = new BlockPos(origin.getX(), branchTop, origin.getZ());
 
                    // Now check the branch path
                    if (checkLine(checkBranchBase, checkStart) == -1) 
                    {
                        // If the branch path is clear, add the position to the list of foliage positions
                        foliageCoords.add(new FoliageCoords(checkStart, checkBranchBase.getY()));
                    }
                }
            }
        }
    }

    private void crossection(BlockPos pos, float radius) 
    {
        // Create a circular cross section.
        //
        // Used to nearly everything in the foliage, branches, and trunk.
        // This is a good target for performance optimization.
 
        // Passed values:
        // pos is the center location of the cross section
        // radius is the radius of the section from the center
        // direction is the direction the cross section is pointed, 0 for x, 1
        // for y, 2 for z material is the index number for the material to use
        
        final int r = (int) (radius + 0.618);

        for (int dx = -r; dx <= r; dx++) 
        {
            for (int dz = -r; dz <= r; dz++) 
            {
                if (Math.pow(Math.abs(dx) + 0.5, 2) + Math.pow(Math.abs(dz) + 0.5, 2) <= radius * radius) 
                {
                    BlockPos checkedPos = pos.add(dx, 0, dz);
                    if (this.replace.matches(world, checkedPos))
                    {
                        if (this.altLeaves != null) 
                        {
                            int rand = new Random().nextInt(4);
                            
                            if (rand == 0)
                            {
                                this.setBlockAndNotifyAdequately(world, checkedPos, this.altLeaves.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
                            }
                            else
                            {
                                this.setBlockAndNotifyAdequately(world, checkedPos, this.leaves.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
                            }
                        }
                        else
                        {
                            this.setBlockAndNotifyAdequately(world, checkedPos, this.leaves.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
                        }
                    }
                }
            }
        }
    }

    protected float treeShape(int y) 
    {
        // Take the y position relative to the base of the tree.
        // Return the distance the foliage should be from the trunk axis.
        // Return a negative number if foliage should not be created at this
        // height.  This method is intended for overriding in child classes,
        // allowing different shaped trees.  This method should return a
        // consistent value for each y (don't randomize).
        
        if (y < height * 0.3f) {
            return -1f;
        }
 
        final float radius = height / 2.0f;
        final float adjacent = radius - y;
 
        float distance = MathHelper.sqrt(radius * radius - adjacent * adjacent);
        
        if (adjacent == 0) {
            distance = radius;
        } else if (Math.abs(adjacent) >= radius) {
            return 0f;
        }
 
        // Alter this factor to change the overall width of the tree.
        return distance * 0.5f;
    }
    
    protected float foliageShape(int y) 
    {
        // Take the y position relative to the base of the foliage cluster.
        // Return the radius of the cluster at this y
        // Return a negative number if no foliage should be created at this
        // level. This method is intended for overriding in child classes,
        // allowing foliage of different sizes and shapes.
        
        if (y < 0 || y >= foliageHeight) 
        {
            return -1f;
        } 
        //The following has been replaced as recommended by
        //http://www.reddit.com/r/Minecraft/comments/1m97cw/while_you_are_all_crying_over_the_name_change_of/ccfgc3k
        //The change should fix the decaying leaves
        else if (y == 0 || y == foliageHeight - 1) 
        {
            return 2f;
        }
        else 
        {
            return 3f;
        }
    }

    private void foliageCluster(BlockPos blockPos) 
    {
        // Generate a cluster of foliage, with the base at blockPos
        // The shape of the cluster is derived from foliageShape
        // crossection is called to make each level.
        
        for (int y = 0; y < foliageHeight; y++) 
        {
        	crossection(blockPos.up(y), foliageShape(y));
        }
    }

    private void limb(BlockPos startPos, BlockPos endPos, IBlockState state)
    {
        // Create a limb from the start position to the end position.
        // Used for creating the branches and trunk.
        // Similar to checkLine, however it is setting rather than checking
        
        //The distance between the two points, may be negative if the second pos is smaller
        BlockPos delta = endPos.add(-startPos.getX(), -startPos.getY(), -startPos.getZ());
 
       int steps = getSteps(delta);
 
        //How much should be incremented with each iteration relative
        //to the greatest distance which will have a value of 1.0F.
        float dx = delta.getX() / (float) steps;
        float dy = delta.getY() / (float) steps;
        float dz = delta.getZ() / (float) steps;
 
        //Iterates over all values between the start pos and end pos
        for (int i = 0; i <= steps; i++) 
        {
            //A delta position between the start pos and end pos. Increments are added to the x, y and z coords
            //so that they meet their corresponding values in the end pos when j reaches the greatest distance. 0.5F
            //is added to ensure the final point is reached.
            BlockPos blockPos = startPos.add(.5f + i * dx, .5f + i * dy, .5f + i * dz);
            BlockLog.EnumAxis logAxis = getLogAxis(startPos, blockPos);
 
            this.setBlockAndNotifyAdequately(world, blockPos, state.withProperty(BlockLog.LOG_AXIS, logAxis));
        }
    }
    
    private int getSteps(BlockPos pos) 
    {
        final int absX = MathHelper.abs(pos.getX());
        final int absY = MathHelper.abs(pos.getY());
        final int absZ = MathHelper.abs(pos.getZ());
 
        //Determine which axis has the greatest distance from the origin (0, 0, 0)
        if (absZ > absX && absZ > absY) {
            return absZ;
        } else if (absY > absX) {
            return absY;
        }
 
        return absX;
    }

    private int getGreatestDistance(BlockPos posIn)
    {
        int i = MathHelper.abs(posIn.getX());
        int j = MathHelper.abs(posIn.getY());
        int k = MathHelper.abs(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private BlockLog.EnumAxis getLogAxis(BlockPos startPos, BlockPos endPos)
    {
        BlockLog.EnumAxis axis = BlockLog.EnumAxis.Y;
        
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
                axis = BlockLog.EnumAxis.X;
            } 
            else if (zDiff == maxDiff)
            {
                axis = BlockLog.EnumAxis.Z;
            }
        }
        
        return axis;
    }
    
    private void makeFoliage() 
    {
        // Create the tree foliage.
        // Call foliageCluster at the correct locations
        
        for (FoliageCoords foliageCoord : foliageCoords) 
        {
            foliageCluster(foliageCoord);
        }
    }

    protected boolean trimBranches(int localY) 
    {
        // For larger trees, randomly "prune" the branches so there
        // aren't too many.
        // Return true if the branch should be created.
        // This method is intended for overriding in child classes, allowing
        // decent amounts of branches on very large trees.
        // Can also be used to disable branches on some tree types, or
        // make branches more sparse.
        return localY >= height * 0.2;
    }

    private void makeTrunk() 
    {
        // Create the trunk of the tree.
        BlockPos start = origin;
        BlockPos end = origin.up(trunkHeight);
        IBlockState materialState = this.log;
        
        limb(start, end, materialState);
        
        if (trunkWidth == 2) 
        {
            limb(start.east(), end.east(), materialState);
            limb(start.east().south(), end.east().south(), materialState);
            limb(start.south(), end.south(), materialState);
        }
    }

    private void makeBranches()
    {
        for (FoliageCoords endCoord : foliageCoords) 
        {
            int branchBase = endCoord.getBranchBase();
            BlockPos baseCoord = new BlockPos(this.origin.getX(), branchBase, this.origin.getZ());

            if (this.trimBranches(branchBase - this.origin.getY()))
            {
                this.limb(baseCoord, endCoord, this.log);
            }
        }
    }

    private int checkLine(BlockPos startPos, BlockPos endPos)
    {
        // Check from coordinates start to end (both inclusive) for blocks
        // other than air and foliage If a block other than air and foliage is
        // found, return the number of steps taken.
        // If no block other than air and foliage is found, return -1.
        // Examples:
        // If the third block searched is stone, return 2
        // If the first block searched is lava, return 0
        
        //The distance between the two points, may be negative if the second pos is smaller
        BlockPos delta = endPos.add(-startPos.getX(), -startPos.getY(), -startPos.getZ());
        
        int steps = this.getGreatestDistance(delta);
        
        //How much should be incremented with each iteration relative
        //to the greatest distance which will have a value of 1.0F.
        float dx = (float)delta.getX() / (float)steps;
        float dy = (float)delta.getY() / (float)steps;
        float dz = (float)delta.getZ() / (float)steps;

        //Check if both positions are the same
        if (steps == 0)
        {
            return -1;
        }

        //Iterates over all values between the start pos and end pos
        for (int i = 0; i <= steps; i++)
        {
            //A delta position between the start pos and end pos. Increments are added to the x, y and z coords
            //so that they meet their corresponding values in the end pos when j reaches the greatest distance. 0.5F
            //is added to ensure the final point is reached.
            BlockPos deltaPos = startPos.add((double)(0.5F + (float)i * dx), (double)(0.5F + (float)i * dy), (double)(0.5F + (float)i * dz));

            if (!this.replace.matches(world, deltaPos))
            {
                return i;
            }
        }

        //The line is unobstructed
        return -1;
    }
    
   
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        this.world = world;
        this.origin = pos;

        this.random = new Random(rand.nextLong());

        this.height = random.nextInt(this.maxHeight - this.minHeight) + this.minHeight;

        if (!this.checkLocation())
        {
            this.world = null; //Fix vanilla Mem leak, holds latest world
            return false;
        }

        try {
            prepare();
            makeFoliage();
            makeTrunk();
            makeBranches();
        } catch (RuntimeException e) {
            // TODO: deal with this.
            ;
        }
        
        this.world = null; //Fix vanilla Mem leak, holds latest world

        return true;
    }


    private boolean checkLocation()
    {
        BlockPos down = this.origin.down();
        IBlockState state = this.world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, EnumFacing.UP, ((BlockSapling)Blocks.SAPLING));

        //Don't grow the tree here if the location can't sustain a sapling
        if (!isSoil && !this.placeOn.matches(world, down))
        {
            return false;
        }

        // Examine center column for how tall the tree can be.
        int allowedHeight = checkLine(this.origin, this.origin.up(height - 1));

        if (trunkWidth == 2) 
        {
            allowedHeight = Math.min(checkLine(this.origin.east(), this.origin.east().up(height - 1)), allowedHeight);
            allowedHeight = Math.min(checkLine(this.origin.east().south(), this.origin.east().south().up(height - 1)), allowedHeight);
            allowedHeight = Math.min(checkLine(this.origin.south(), this.origin.south().up(height - 1)), allowedHeight);
        }
        
        // If the set height is good, go with that
        if (allowedHeight == -1) {
            return true;
        }
 
        // If the space is too short, tell the build to abort
        if (allowedHeight < this.minHeight) {
            return false;
        }

        height = allowedHeight;
        return true;
    }
    
    public void setBlockAndNotifyAdequately(World world, BlockPos pos, IBlockState state)
    {
        if (this.updateNeighbours)
        {
            world.setBlockState(pos, state, 3);
        }
        else
        {
            world.setBlockState(pos, state, 2);
        }
    }

    private static class FoliageCoords extends BlockPos 
    {
        private final int branchBase;
 
        public FoliageCoords(BlockPos pos, int branchBase) 
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.branchBase = branchBase;
        }
 
        public int getBranchBase() 
        {
            return branchBase;
        }
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.updateNeighbours = conf.getBool("updateNeighbours", this.updateNeighbours);
        int minHeight = conf.getInt("minHeight", this.minHeight);
        int maxHeight = conf.getInt("maxHeight", this.maxHeight);
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
        
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
        this.foliageHeight = conf.getInt("foliageHeight", this.foliageHeight);
        this.foliageDensity = conf.getFloat("foliageDensity", (float)this.foliageDensity);
    }
    
}
