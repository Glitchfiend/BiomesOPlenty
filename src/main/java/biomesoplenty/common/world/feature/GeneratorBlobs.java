/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GeneratorBlobs extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorBlobs> implements IGeneratorBuilder<GeneratorBlobs>
    {
        protected float minRadius;
        protected float maxRadius;
        protected float radiusFalloff;
        protected int numBalls;
        
        public Builder minRadius(float a) {this.minRadius = a; return this.self();}
        public Builder maxRadius(float a) {this.maxRadius = a; return this.self();}
        public Builder radiusFalloff(float a) {this.radiusFalloff = a; return this.self();}
        public Builder numBalls(int a) {this.numBalls = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQuery.buildOr().blocks(Blocks.stone).materials(Material.ground, Material.grass).create();
            this.replace = BlockQueries.breakable;
            this.with = Blocks.cobblestone.getDefaultState();
            this.scatterYMethod = ScatterYMethod.BELOW_SURFACE;
            this.minRadius = 2.0F;
            this.maxRadius = 5.0F;
            this.radiusFalloff = 0.5F;
            this.numBalls = 3;
        }

        @Override
        public GeneratorBlobs create()
        {
            return new GeneratorBlobs(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minRadius, this.maxRadius, this.radiusFalloff, this.numBalls);
        }
    }
    
    
    protected float minRadius;
    protected float maxRadius;
    protected float radiusFalloff; // should normally be between 0 and 1 so that balls get smaller
    protected int numBalls;

    public GeneratorBlobs(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, float minRadius, float maxRadius, float radiusFalloff, int numBalls)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.radiusFalloff = radiusFalloff;
        this.numBalls = numBalls;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        // move downwards until we find a block matching this.placeOn
        while (pos.getY() > 3 && !placeOn.matches(world, pos.down()))
        {
            pos = pos.down();
        }
        if (pos.getY() <= 3) {return false;}
        

        float minRadius = this.minRadius;
        float maxRadius = this.maxRadius;
        
        float x = (float)pos.getX() + rand.nextFloat() + 0.5F;
        float y = (float)pos.getY() + rand.nextFloat() + 0.5F;
        float z = (float)pos.getZ() + rand.nextFloat() + 0.5F;

        for (int i = 0; minRadius >= 0 && i < this.numBalls; ++i)
        {
            // fill a ball
            this.fillBall(world, rand, x, y, z, minRadius, maxRadius - minRadius);
         
            // move to a nearby point (downward bias)
            x += rand.nextFloat()*(minRadius + 2) - (minRadius + 1);
            y -= rand.nextFloat()*2;
            z += rand.nextFloat()*(minRadius + 2) - (minRadius + 1);          

            // reduce radius of successive balls
            minRadius = minRadius * this.radiusFalloff;
            maxRadius = maxRadius * this.radiusFalloff;
        }

        return true;
    }
    
    protected void fillBall(World world, Random rand, float centerX, float centerY, float centerZ, float minRadius, float radiusVariation)
    {
        float radiusRand = rand.nextFloat() * 0.5F * radiusVariation;
        float radiusX = minRadius + radiusRand + (rand.nextFloat() * radiusVariation * 0.5F);
        float radiusY = minRadius + radiusRand + (rand.nextFloat() * radiusVariation * 0.5F);
        float radiusZ = minRadius + radiusRand + (rand.nextFloat() * radiusVariation * 0.5F);
        
        int x0 = MathHelper.floor_float(centerX - radiusX + 0.5F);
        int y0 = MathHelper.floor_float(centerY - radiusY + 0.5F);
        int z0 = MathHelper.floor_float(centerZ - radiusZ + 0.5F);
        int x1 = MathHelper.floor_float(centerX + radiusX + 0.5F);
        int y1 = MathHelper.floor_float(centerY + radiusY + 0.5F);
        int z1 = MathHelper.floor_float(centerZ + radiusZ + 0.5F);        
        
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
                                BlockPos pos = new BlockPos(x, y, z);
                                if (this.replace.matches(world, pos))
                                {
                                    world.setBlockState(pos, this.with);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
    }
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.with = conf.getBlockState("with", this.with);
        this.minRadius = conf.getFloat("innerRadius", this.minRadius);
        this.radiusFalloff = conf.getFloat("radiusFalloff", this.radiusFalloff);
        this.numBalls = conf.getInt("numBalls", this.numBalls);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
    }
    

}