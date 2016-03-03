/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.feature.GeneratorSpike;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorRedwoodTree extends GeneratorTreeBase
{
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorRedwoodTree> implements IGeneratorBuilder<GeneratorRedwoodTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 40;
            this.maxHeight = 60;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.replaceable;
            this.log = BlockBOPLog.paging.getVariantState(BOPWoods.REDWOOD);
            this.leaves = BlockBOPLeaves.paging.getVariantState(BOPTrees.REDWOOD);
            this.vine = null;
            this.hanging = null;
            this.altLeaves = null;
        }

        @Override
        public GeneratorRedwoodTree create() 
        {
            return new GeneratorRedwoodTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
        
    }

    protected GeneratorRedwoodTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight) 
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }
    
    public boolean canPlaceHere(World world, BlockPos pos, int height, int radius)
    {  
        if (pos.getY() < 1 || pos.getY() + height > 255)
        {
            return false;
        }
        for (int y = pos.getY(); y <= pos.getY() + height; ++y)
        {
            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    if (y == pos.getY() && !this.placeOn.matches(world, new BlockPos(x, y - 1, z)))
                    {
                        return false;
                    }
                    
                    if (!this.replace.matches(world, new BlockPos(x, y, z)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public void generateTrunk(World world, Random random, BlockPos pos, int trunkHeight)
    {
        for (int i = 0; i < trunkHeight; i++)
        {
            this.setLog(world, pos);
            this.setLog(world, pos.north());
            this.setLog(world, pos.east());
            this.setLog(world, pos.south());
            this.setLog(world, pos.west());
            pos = pos.up();
        }
    }
    
    public void generateBranches(World world, Random rand, BlockPos pos, int length)
    {
        //Iterate over the possible directions
        for (EnumFacing direction = EnumFacing.NORTH; direction.ordinal() < 5; direction = EnumFacing.values()[direction.ordinal() + 1])
        {
            EnumFacing.Axis axis = direction.getAxis();
            EnumFacing sideways = direction.rotateY();
            for (int i = 1; i <= length; i++)
            {
                BlockPos pos1 = pos.offset(direction, i);
                this.setLog(world, pos1, axis);
            }
        }
    }
    
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int leafLayerNum)
    {
        //Repeat in intervals of 6, 2 small radius, 4 large
        int index = leafLayerNum % 7;
        int leavesRadius;
        
        //Alternate between a smaller radius and a larger radius
        if (index < 2) leavesRadius = 2;
        else leavesRadius = 3;

        //This may break for larger radii however it will do for this purpose
        double increment = 0.05D;
        
        for (int radius = leavesRadius; radius >= 0; radius--) 
        {
            for (double angle = 0.0F; angle <= Math.PI * 2; angle += increment)
            {
                BlockPos leavesPos = pos.add(Math.round(radius * Math.cos(angle)), 0, Math.round(radius * Math.sin(angle)));
                
                if (radius < leavesRadius || index < 2 || rand.nextInt(4) == 0)
                    this.setLeaves(world, leavesPos);
            }
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) 
    {
        // Move down until we reach the ground
        while (pos.getY() > 1 && world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isLeaves(world.getBlockState(pos), world, pos)) {pos = pos.down();}
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 20) {return false;}
        
        // Move up to space above ground
        pos = pos.up();
        
        GeneratorSpike spikeGenerator = (new GeneratorSpike.Builder().with(this.log).replace(BlockQueries.anything).minRadius(4).maxRadius(4).create());
        
        // check that there's room and if the blocks below are suitable
        if (!this.canPlaceHere(world, pos, height, 1) || !spikeGenerator.canPlaceHere(world, pos, height, 4)) {return false;}
        
        //Generate the base of the tree
        spikeGenerator.generate(world, random, pos);
        
        BlockPos trunkTop = pos;
        //Move upwards until the block above this is air
        for (; !world.isAirBlock(trunkTop.up()); trunkTop = trunkTop.up())
        {
            if (trunkTop.getY() >= 255)
            {
                return false;
            }
        }
        
        int baseHeight = trunkTop.getY() - pos.getY();
        int trunkHeight = height - baseHeight;
        
        //Generate the trunk to 1 block below the height
        this.generateTrunk(world, random, pos, height - 1);
        
        //Generate the layers of leaves
        for (int i = 0; i < trunkHeight * 0.75F; i++)
        {
            this.generateLeafLayer(world, random, pos.up(height - i), i);
        }
        
        GeneratorBush bushGenerator = new GeneratorBush.Builder().amountPerChunk(2F).log(this.log).leaves(this.leaves).placeOn(this.log).maxHeight(2).create();
        
        //Add bushes around the base
        for (int i = 0; i < 10; i++)
        {
            bushGenerator.generate(world, random, pos.add(random.nextInt(10) - 5, baseHeight, random.nextInt(10) - 5));
        }
        
        return true;
    }

    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("minHeight", this.maxHeight);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
}
