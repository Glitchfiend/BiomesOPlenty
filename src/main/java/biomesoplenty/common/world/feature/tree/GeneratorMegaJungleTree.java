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
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GeneratorMegaJungleTree extends GeneratorHugeTree
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorMegaJungleTree> implements IGeneratorBuilder<GeneratorMegaJungleTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 6;
            this.maxHeight = 20;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log(BlockPlanks.EnumType.JUNGLE);
            this.leaves(BlockPlanks.EnumType.JUNGLE);
            this.vine = Blocks.vine.getDefaultState();
            this.hanging = null;
            this.altLeaves = null;
        }

        @Override
        public GeneratorMegaJungleTree create() {
            return new GeneratorMegaJungleTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
        
    }
    
    protected GeneratorMegaJungleTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
    }
    

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos startPos)
    {
        int height = this.chooseHeight(rand);

        if (!this.canGrowHere(worldIn, rand, startPos, height))
        {
            return false;
        }
        else
        {
            this.addTop(worldIn, startPos.up(height), 2);

            for (int y = startPos.getY() + height - 2 - rand.nextInt(4); y > startPos.getY() + height / 2; y -= 2 + rand.nextInt(4))
            {
                // sprout branch in random direction
                float direction = rand.nextFloat() * (float)Math.PI * 2.0F;
                float cosDir = MathHelper.cos(direction);
                float sinDir = MathHelper.sin(direction);
                int x = 0;
                int z = 0;
                
                // draw outward from trunk in direction of branch
                for (int s = 0; s < 5; ++s)
                {
                    x = startPos.getX() + (int)(1.5F + cosDir * (float)s);
                    z = startPos.getZ() + (int)(1.5F + sinDir * (float)s);
                    this.setLog(worldIn, new BlockPos(x, y - 3 + s / 2, z));
                }
                
                // draw leaves at end of branch
                int clusterHeight = 1 + rand.nextInt(2);
                for (int i = 0; i <= clusterHeight; i++)
                {
                    this.addLeafLayer(worldIn, new BlockPos(x, y - i, z), i + 1);
                }
                
            }

            // create main trunk layer by layer
            for (int y = 0; y < height; ++y)
            {
                BlockPos trunkPosNW = startPos.up(y);

                if (this.setLog(worldIn, trunkPosNW))
                {
                    if (y > 0)
                    {
                        this.maybeAddVine(worldIn, rand, trunkPosNW.west(), EnumFacing.EAST);
                        this.maybeAddVine(worldIn, rand, trunkPosNW.north(), EnumFacing.SOUTH);
                    }
                }

                if (y < height - 1)
                {
                    BlockPos trunkPosNE = trunkPosNW.east();
                    if (this.setLog(worldIn, trunkPosNE))
                    {
                        if (y > 0)
                        {
                            this.maybeAddVine(worldIn, rand, trunkPosNE.east(), EnumFacing.WEST);
                            this.maybeAddVine(worldIn, rand, trunkPosNE.north(), EnumFacing.SOUTH);
                        }
                    }

                    BlockPos trunkPosSE = trunkPosNW.south().east();
                    if (this.setLog(worldIn, trunkPosSE))
                    {
                        if (y > 0)
                        {
                            this.maybeAddVine(worldIn, rand, trunkPosSE.east(), EnumFacing.WEST);
                            this.maybeAddVine(worldIn, rand, trunkPosSE.south(), EnumFacing.NORTH);
                        }
                    }

                    BlockPos trunkPosSW = trunkPosNW.south();

                    if (this.setLog(worldIn, trunkPosSW))
                    {
                        if (y > 0)
                        {
                            this.maybeAddVine(worldIn, rand, trunkPosSW.west(), EnumFacing.EAST);
                            this.maybeAddVine(worldIn, rand, trunkPosSW.south(), EnumFacing.NORTH);
                        }
                    }
                }
            }

            return true;
        }
    }

    private void maybeAddVine(World worldIn, Random rand, BlockPos pos, EnumFacing side)
    {
        if (this.vine == null) {return;}
        if (rand.nextInt(8) > 0)
        {
            this.setVine(worldIn, rand, pos, side, 1);            
        }
    }

    private void addTop(World worldIn, BlockPos pos, int radius)
    {
        for (int j = -2; j <= 0; ++j)
        {
            this.addWideLeafLayer(worldIn, pos.up(j), radius + 1 - j);
        }
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