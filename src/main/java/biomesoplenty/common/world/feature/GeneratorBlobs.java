/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;

public class GeneratorBlobs extends GeneratorCustomizable
{
    protected int amountPerChunk;
    protected IBlockQuery from;
    protected IBlockState to;
    protected float innerRadius;
    protected float radiusFalloff; // should normally be between 0 and 1 so that balls get smaller
    protected int numBalls;    
    
    public GeneratorBlobs()
    {
        this(1, Blocks.cobblestone.getDefaultState(), 4.0F, 0.8F, 3, new BlockQueryAny(new BlockQueryBlock(Blocks.stone), new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass)));
    }

    public GeneratorBlobs(int amountPerChunk, IBlockState to, float innerRadius, float radiusFalloff, int numBalls, IBlockQuery from)
    {
        this.amountPerChunk = amountPerChunk;
        this.to = to;
        this.innerRadius = innerRadius;
        this.numBalls = numBalls;
        this.radiusFalloff = radiusFalloff;
        this.from = new BlockQueryAny(new BlockQueryBlock(Blocks.stone), new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass));
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = pos.add(x, 0, z);
            int y = GeneratorUtils.safeNextInt(random, world.getHeight(genPos).getY() + 32);
            genPos = genPos.add(0, y, 0);
            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        // move downwards until we find a block matching this.from
        while (pos.getY() > 3 && !from.matches(world.getBlockState(pos.down())))
        {
            pos = pos.down();
        }
        if (pos.getY() <= 3) {return false;}
        

        float innerRadius = this.innerRadius;
        float x = (float)pos.getX();
        float y = (float)pos.getY();
        float z = (float)pos.getZ();

        for (int i = 0; innerRadius >= 0 && i < this.numBalls; ++i)
        {
            // fill a ball
            this.fillBall(world, rand, x, y, z, innerRadius);
         
            // move to a nearby point (downward bias)
            x += rand.nextFloat()*(innerRadius + 2) - (innerRadius + 1);
            y -= rand.nextFloat()*2;
            z += rand.nextFloat()*(innerRadius + 2) - (innerRadius + 1);          

            // reduce radius of successive balls
            innerRadius = innerRadius * this.radiusFalloff;
        }

        return true;
    }
    
    protected void fillBall(World world, Random rand, float centerX, float centerY, float centerZ, float innerRadius)
    {
        float radiusX = innerRadius + (rand.nextFloat() * 2);
        float radiusY = innerRadius + (rand.nextFloat() * 2);
        float radiusZ = innerRadius + (rand.nextFloat() * 2);
        float outerRadius = (float)(radiusX + radiusY + radiusZ) * 0.333F + 0.5F;
        
        int x0 = (int)(centerX - radiusX + 0.5F);
        int y0 = (int)(centerY - radiusY + 0.5F);
        int z0 = (int)(centerZ - radiusZ + 0.5F);
        int x1 = (int)(centerX + radiusX + 0.5F);
        int y1 = (int)(centerY + radiusY + 0.5F);
        int z1 = (int)(centerZ + radiusZ + 0.5F);
        
        for (int x = x0; x <= x1; x++)
        {
            for (int y = y0; y <= y1; y++)
            {
                for (int z = z0; z <= z1; z++)
                {
                    float dx = x - centerX;
                    float dy = y - centerY;
                    float dz = z - centerZ;

                    if (dx * dx + dz * dz + dy * dy <= outerRadius * outerRadius)
                    {
                        world.setBlockState(new BlockPos(x, y, z), this.to);
                    } 
                }
            }
        }
    }
    

}