/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GeneratorSplotches extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorSplotches> implements IGeneratorBuilder<GeneratorSplotches>
    {
        protected int splotchSize;

        public Builder splotchSize(int a) {this.splotchSize = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.grass, Material.ground);
            this.replace = new BlockQueryMaterial(Material.grass, Material.ground);
            this.with = Blocks.cobblestone.getDefaultState();
            this.scatterYMethod = ScatterYMethod.BELOW_SURFACE;
            this.splotchSize = 8;
        }

        @Override
        public GeneratorSplotches create()
        {
            return new GeneratorSplotches(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.splotchSize);
        }
    }  
   
    
    protected int splotchSize;
 
    public GeneratorSplotches(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int splotchSize)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.splotchSize = splotchSize;
    } 
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        if (! this.placeOn.matches(world, pos.down())) {return false;}

        float a = random.nextFloat() * (float)Math.PI;
        
        // choose a start point
        double x0 = pos.getX() + MathHelper.sin(a) * this.splotchSize / 8.0F;
        double y0 = pos.getY() + random.nextInt(3) - 1;
        double z0 = pos.getZ() + MathHelper.cos(a) * this.splotchSize / 8.0F;
        
        // choose an end point
        double x1 = pos.getX() - MathHelper.sin(a) * this.splotchSize / 8.0F;
        double y1 = pos.getY() + random.nextInt(3) - 1;
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
        
        int x0 = MathHelper.floor_double(centerX - radiusX + 0.5D);
        int y0 = MathHelper.floor_double(centerY - radiusY + 0.5D);
        int z0 = MathHelper.floor_double(centerZ - radiusZ + 0.5D);
        int x1 = MathHelper.floor_double(centerX + radiusX + 0.5D);
        int y1 = MathHelper.floor_double(centerY + radiusY + 0.5D);
        int z1 = MathHelper.floor_double(centerZ + radiusZ + 0.5D);  
        
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
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.with, 2);
        }
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.with = conf.getBlockState("with", this.with);
        this.splotchSize = conf.getInt("splotchSize", this.splotchSize);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
    }

}
