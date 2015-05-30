/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorSplotches extends GeneratorCustomizable
{    
    protected int amountPerChunk;
    protected IBlockQuery from;
    protected IBlockState to;
    protected int splotchSize;
    
    public GeneratorSplotches()
    {
        // default
        this(1, Blocks.stone.getDefaultState(), 8, Blocks.grass);
    }

    public GeneratorSplotches(int amountPerChunk, IBlockState to, int splotchSize, String from) throws BlockQueryParseException
    {
        this(amountPerChunk, to, splotchSize, BlockQueryUtils.parseQueryString(from));
    }
    
    public GeneratorSplotches(int amountPerChunk, IBlockState to, int splotchSize, Block from)
    {
        this(amountPerChunk, to, splotchSize, new BlockQueryUtils.BlockQueryBlock(from));
    }
    
    public GeneratorSplotches(int amountPerChunk, IBlockState to, int splotchSize, IBlockState from)
    {
        this(amountPerChunk, to, splotchSize, new BlockQueryUtils.BlockQueryState(from));
    }
    
    public GeneratorSplotches(int amountPerChunk, IBlockState to, int splotchSize, IBlockQuery from)
    {
        this.amountPerChunk = amountPerChunk;
        this.to = to;
        this.splotchSize = splotchSize;
        this.from = from;
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
    public boolean generate(World world, Random random, BlockPos pos)
    {
        
        float a = random.nextFloat() * (float)Math.PI;
        
        // choose a start point
        double x0 = pos.getX() + MathHelper.sin(a) * this.splotchSize / 8.0F;
        double y0 = pos.getY() + random.nextInt(3) - 2;
        double z0 = pos.getZ() + MathHelper.cos(a) * this.splotchSize / 8.0F;
        
        // choose an end point
        double x1 = pos.getX() - MathHelper.sin(a) * this.splotchSize / 8.0F;
        double y1 = pos.getY() + random.nextInt(3) - 2;
        double z1 = pos.getZ() - MathHelper.cos(a) * this.splotchSize / 8.0F;

        // move along a line from the start point to the end point and replace in random ellipsoids along the way (bigger at the center)
        for (int i = 0; i <= this.splotchSize; ++i)
        {
            double x = x0 + (x1 - x0) * i / this.splotchSize;
            double y = y0 + (y1 - y0) * i / this.splotchSize;
            double z = z0 + (z1 - z0) * i / this.splotchSize;
            
            double b = random.nextDouble() * this.splotchSize / 16.0D;
            double diameterXZ = (MathHelper.sin(i * (float)Math.PI / this.splotchSize) + 1.0F) * b + 1.0D;
            double diameterY = (MathHelper.sin(i * (float)Math.PI / this.splotchSize) + 1.0F) * b + 1.0D;
            
            this.replaceInEllipsoid(world, x, y, z, diameterXZ / 2.0D, diameterY / 2.0D, diameterXZ / 2.0D);            
        }

        return true;
    }

    public void replaceInEllipsoid(World world, double centerX, double centerY, double centerZ, double radiusX, double radiusY, double radiusZ)
    {
        
        int x0 = MathHelper.floor_double(centerX - radiusX);
        int y0 = MathHelper.floor_double(centerY - radiusY);
        int z0 = MathHelper.floor_double(centerZ - radiusZ);
        int x1 = MathHelper.floor_double(centerX + radiusX);
        int y1 = MathHelper.floor_double(centerY + radiusY);
        int z1 = MathHelper.floor_double(centerZ + radiusZ);
        
        for (int x = x0; x <= x1; ++x)
        {
            double px = (x + 0.5D - centerX) / radiusX;

            if (px * px < 1.0D)
            {
                for (int y = y0; y <= y1; ++y)
                {
                    double py = (y + 0.5D - centerY) / radiusY;

                    if (px * px + py * py < 1.0D)
                    {
                        for (int z = z0; z <= z1; ++z)
                        {
                            double pz = (z + 0.5D - centerZ) / radiusZ;

                            if (px * px + py * py + pz * pz < 1.0D)
                            {
                                this.replaceAt(world, new BlockPos(x, y, z));
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void replaceAt(World world, BlockPos pos)
    {
        if (from.matches(world.getBlockState(pos)))
        {
            world.setBlockState(pos, this.to, 2);
        }
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        this.to = conf.getBlockState("to", this.to);
        this.splotchSize = conf.getInt("splotchSize", this.splotchSize);
        String fromString = conf.getString("from", null);
        if (fromString != null)
        {
            try {
                IBlockQuery from = BlockQueryUtils.parseQueryString(fromString);
                this.from = from;
            } catch (BlockQueryParseException e) {
                conf.addMessage("from", e.getMessage());
            }
        }
    }

}
