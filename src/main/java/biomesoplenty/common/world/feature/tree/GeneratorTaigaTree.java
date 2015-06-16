/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
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
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorTaigaTree extends GeneratorTreeBase
{
    // TODO: fruit
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorTaigaTree> implements IGeneratorBuilder<GeneratorTaigaTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 6;
            this.maxHeight = 12;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.leaves = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.vine = Blocks.vine.getDefaultState();  
        }

        @Override
        public GeneratorTaigaTree create() {
            return new GeneratorTaigaTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.minHeight, this.maxHeight);
        }
        
    }    
    
    public GeneratorTaigaTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, minHeight, maxHeight);
    }
    
    public boolean checkSpace(World world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            // require 3x3 for the leaves, 1x1 for the trunk
            int radius = (y <= baseHeight ? 0 : 1);
            
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
    
    // generates a layer of leafs
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int radius)
    {
        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                // skip corners
                if (Math.abs(x) < radius || Math.abs(z) < radius || radius == 0)
                {
                    this.setLeaves(world, pos.add(x, 0, z));
                }
            }
        }
    }
    
    
    @Override
    public boolean generate(World world, Random random, BlockPos startPos)
    {
        
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getBlock().isLeaves(world, startPos)) {startPos = startPos.down();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        int baseHeight = GeneratorUtils.nextIntBetween(random, height / 6, height / 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}
        int leavesMaxRadius = 2 + random.nextInt(2);
        
        if (!this.checkSpace(world, startPos.up(), baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }
        
        // Start at the top of the tree
        BlockPos pos = startPos.up(height);
        
        // Add layers of leaves
        int localMinRadius = 0;
        int radius = random.nextInt(2);
        int localMaxRadius = 1;
        for (int i = 0; i < leavesHeight; i++)
        {
            this.generateLeafLayer(world, random, pos, radius);
            if (radius < localMaxRadius)
            {
                radius++; 
            } else {
                radius = localMinRadius;
                if (localMinRadius == 0) {localMinRadius = 1;}
                if (localMaxRadius < leavesMaxRadius) {localMaxRadius++;}
            }
            pos = pos.down();
        }
        
        // Go back to the top of the tree and generate the trunk
        pos = startPos.up(height - 1);
        for(int i = 0; i < height - 1; i++)
        {
            this.setLog(world, pos);
            pos = pos.down();
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
        this.vine = conf.getBlockState("vinesState", this.vine);
    }

}