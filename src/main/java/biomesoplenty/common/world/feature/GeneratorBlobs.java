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
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorBlobs extends BOPGeneratorBase
{
    protected IBlockQuery placeOn;
    protected IBlockState to;
    protected float minRadius;
    protected float maxRadius;
    protected float radiusFalloff; // should normally be between 0 and 1 so that balls get smaller
    protected int numBalls;
    protected ScatterYMethod scatterYMethod;
    
    public GeneratorBlobs()
    {
        this(1, Blocks.cobblestone.getDefaultState(), 2.0F, 5.0F, 0.5F, 3, new BlockQueryAny(new BlockQueryBlock(Blocks.stone), new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass)), ScatterYMethod.AT_OR_BELOW_SURFACE);
    }
    
    public GeneratorBlobs(float amountPerChunk, IBlockState to, float minRadius, float maxRadius, float radiusFalloff, int numBalls, String from, ScatterYMethod scatterYMethod) throws BlockQueryParseException
    {
        this(amountPerChunk, to, minRadius, maxRadius, radiusFalloff, numBalls, BlockQueryUtils.parseQueryString(from), scatterYMethod);
    }
    
    public GeneratorBlobs(float amountPerChunk, IBlockState to, float minRadius, float maxRadius, float radiusFalloff, int numBalls, Block from, ScatterYMethod scatterYMethod)
    {
        this(amountPerChunk, to, minRadius, maxRadius, radiusFalloff, numBalls, new BlockQueryUtils.BlockQueryBlock(from), scatterYMethod);
    }
    
    public GeneratorBlobs(float amountPerChunk, IBlockState to, float minRadius, float maxRadius, float radiusFalloff, int numBalls, IBlockState from, ScatterYMethod scatterYMethod)
    {
        this(amountPerChunk, to, minRadius, maxRadius, radiusFalloff, numBalls, new BlockQueryUtils.BlockQueryState(from), scatterYMethod);
    }

    public GeneratorBlobs(float amountPerChunk, IBlockState to, float minRadius, float maxRadius, float radiusFalloff, int numBalls, IBlockQuery placeOn, ScatterYMethod scatterYMethod)
    {
        super(amountPerChunk);
        this.to = to;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.radiusFalloff = radiusFalloff;
        this.numBalls = numBalls;
        this.placeOn = placeOn;
        this.scatterYMethod = scatterYMethod;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return this.scatterYMethod.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        // move downwards until we find a block matching this.placeOn
        while (pos.getY() > 3 && !placeOn.matches(world.getBlockState(pos.down())))
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
                                world.setBlockState(pos, this.to);
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
        this.to = conf.getBlockState("to", this.to);
        this.minRadius = conf.getFloat("innerRadius", this.minRadius);
        this.radiusFalloff = conf.getFloat("radiusFalloff", this.radiusFalloff);
        this.numBalls = conf.getInt("numBalls", this.numBalls);
        String placeOnString = conf.getString("placeOn", null);
        if (placeOnString != null)
        {
            try {
                IBlockQuery placeOn = BlockQueryUtils.parseQueryString(placeOnString);
                this.placeOn = placeOn;
            } catch (BlockQueryParseException e) {
                conf.addMessage("placeOn", e.getMessage());
            }
        }
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
    }
    

}