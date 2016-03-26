/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GeneratorPalmTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorPalmTree> implements IGeneratorBuilder<GeneratorPalmTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 10;
            this.maxHeight = 12;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.replaceable;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = null;
            this.hanging = null;
            this.altLeaves = null;
        }

        @Override
        public GeneratorPalmTree create() {
            return new GeneratorPalmTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
        
    }
    
    public GeneratorPalmTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }
    

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        // Move down until we reach the ground
        while (pos.getY() > 1 && world.isAirBlock(pos) || world.getBlockState(pos).getBlock().isLeaves(world.getBlockState(pos), world, pos)) {pos = pos.down();}
        
        if (!this.placeOn.matches(world, pos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Generation settings
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        int leavesRadius = 2;
        int heightMinusTop = height - leavesRadius;
        EnumFacing direction = EnumFacing.random(random); //The direction the palm tree curves towards
        double baseSlant = random.nextInt(35) / 100D; 
        double slantMultiplier = 1.3D;
        
        if (height < 8) {return false;} //Prevent trees from being too small 
        
        // Move up to space above ground
        pos = pos.up();
        
        if (!this.checkSpace(world, pos, height, 1))
        {
            // Abandon if there isn't enough room
            return false;
        }

        double slantOffset = baseSlant;
        
        // Generate trunk of tree (trunk only)
        for(int step = 0; step <= heightMinusTop; step++)
        {
        	BlockPos offsetPos = pos.up(step).offset(direction, (int)Math.round(slantOffset));
            
            if (step == heightMinusTop)
            {
                // Generate top of tree
                generateLeavesTop(world, offsetPos, leavesRadius);
                break;
            }
            
            this.setLog(world, offsetPos);
            
            //As the height increases, slant more drastically
            slantOffset *= slantMultiplier;
        }
        
        return true;
    }
    
    public boolean checkSpace(World world, BlockPos pos, int height, int radius)
    {
        for (int y = 0; y <= height; y++)
        {
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos pos1 = pos.add(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;       
    }

    // generate the top of the tree (3 blocks)
    public void generateLeavesTop(World world, BlockPos pos, int maxRadius)
    {
        setLeaves(world, pos.add(2, -1, 0));
        setLeaves(world, pos.add(-2, -1, 0));
        setLeaves(world, pos.add(0, -1, 2));
        setLeaves(world, pos.add(0, -1, -2));

        setLeaves(world, pos.add(1, 0, 0));
        setLeaves(world, pos.add(-1, 0, 0));
        setLeaves(world, pos.add(0, 0, 1));
        setLeaves(world, pos.add(0, 0, -1));
        setLeaves(world, pos.add(2, 0, 2));
        setLeaves(world, pos.add(-2, 0, -2));
        setLeaves(world, pos.add(2, 0, -2));
        setLeaves(world, pos.add(-2, 0, 2));

        setLeaves(world, pos.add(1, 1, -1));
        setLeaves(world, pos.add(-1, 1, 1));
        setLeaves(world, pos.add(1, 1, 1));
        setLeaves(world, pos.add(-1, 1, -1));
        setLeaves(world, pos.add(0, 1, 0));

        setLeaves(world, pos.add(2, 2, 0));
        setLeaves(world, pos.add(-2, 2, 0));
        setLeaves(world, pos.add(0, 2, 2));
        setLeaves(world, pos.add(0, 2, -2));
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