/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.feature;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorSpike extends GeneratorReplacing
{
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorSpike> implements IGeneratorBuilder<GeneratorSpike>
    {
        protected int minHeight;
        protected int maxHeight;
        protected int minRadius;
        protected int maxRadius;
        
        public Builder minHeight(int a) {this.minHeight = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder minRadius(int a) {this.minRadius = a; return this.self();}
        public Builder maxRadius(int a) {this.maxRadius = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.solid;
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = BOPBlocks.dried_sand.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.minHeight = 8;
            this.maxHeight = 12;
            this.minRadius = 3;
            this.maxRadius = 3;
        }
        
        @Override
        public GeneratorSpike create()
        {
            return new GeneratorSpike(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minHeight, this.maxHeight, this.minRadius, this.maxRadius);
        }
    }
    
    protected int minHeight;
    protected int maxHeight;
    protected int minRadius;
    protected int maxRadius;
    
    public GeneratorSpike(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minHeight, int maxHeight, int minRadius, int maxRadius)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) 
    {
        int maxRadius = this.minRadius + rand.nextInt(this.maxRadius - this.minRadius + 1);
        int centreHeight = this.minHeight + rand.nextInt(this.maxHeight - this.minHeight + 1);
        
        // check that there's room and if the blocks below are suitable
        if (!this.canPlaceHere(world, position, centreHeight, maxRadius)) {return false;}
        
        //Distribute the height excluding the spire and the base between the radius (other than the base)
        int layerHeight = centreHeight / (maxRadius - 1);
        
        //
        // Generate the base
        //
        
        //Fill the inner section of the circle
        createCircleWithChance(world, position, this.with, maxRadius - 1, true, 1.0F); 
        //Randomly remove the outer edges
        createCircleWithChance(world, position, this.with, maxRadius, true, 0.15F); 
        
        //
        // Generate the centre and the spire
        //
        
        BlockPos layerStartPos = position.up();
        
        //Add 2 for the spire
        for (int y = 0; y <= centreHeight + 2; y++)
        {
            //Generate the spire
            if (y > centreHeight) 
            {
                world.setBlockState(layerStartPos.add(0, y, 0), this.with);
            }
            else //Generate the layers
            {
                //Bottom layer is 0, then 1 etc
                int layer = y / layerHeight;
                int layerIndex = y % layerHeight; //Get the position of y within the current layer, ignoring the base
                int radius = maxRadius - 1 - layer; //The radius for this layer, with the base radius subtracted

                //Treat any layers with a radius of 0 as an extension of the spire
                if (radius == 0)
                {
                    world.setBlockState(layerStartPos.add(0, y, 0), this.with);
                    continue;
                }
                
                if (layerIndex == layerHeight - 1) //Generate midpoints randomly
                {
                    //Fill the inner section of the circle
                    createCircleWithChance(world, layerStartPos.add(0, y, 0), this.with, radius - 1, true, 1.0F);
                    createMidpointsWithChance(world, layerStartPos.add(0, y, 0), this.with, radius, 0.7F);
                }
                else if (layerIndex == layerHeight - 2)
                {
                    //Fill the inner section of the circle
                    createCircleWithChance(world, layerStartPos.add(0, y, 0), this.with, radius - 1, true, 1.0F); 
                    //Randomly remove the outer edges
                    createCircleWithChance(world, layerStartPos.add(0, y, 0), this.with, radius, true, 0.1F / (layer + 1)); 
                }
                else
                {
                    createCircleWithChance(world, layerStartPos.add(0, y, 0), this.with, radius, true, 1.0F);
                }
            }
        }

        return true;
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
    
    @Override
    public void configure(IConfigObj conf) 
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.placeOn = conf.getBlockPosQuery("placeUnder", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.with = conf.getBlockState("with", this.with);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
        int minHeight = conf.getInt("minHeight", this.minHeight).intValue();
        int maxHeight = conf.getInt("maxHeight", this.maxHeight).intValue();
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
        
        int minRadius = conf.getInt("minRadius", this.minRadius).intValue();
        int maxRadius = conf.getInt("maxRadius", this.maxRadius).intValue();
        
        Pair<Integer, Integer> radii = GeneratorUtils.validateMinMaxHeight(minRadius, maxRadius);
        this.minRadius = radii.getLeft();
        this.maxRadius = radii.getRight();
    }
    
    private void createCircleWithChance(World world, BlockPos middle, IBlockState state, int maxRadius, boolean fill, float chance)
    {
        //This may break for larger radii however it will do for this purpose
        double increment = 0.05D;
        
        for (int radius = maxRadius; radius >= 0; radius--) 
        {
            for (double angle = 0.0F; angle <= Math.PI * 2; angle += increment)
            {
                BlockPos pos = middle.add(Math.round(radius * Math.cos(angle)), 0, Math.round(radius * Math.sin(angle)));
                
                setBlockWithChance(world, pos, state, chance);
            }
            
            if (!fill) break;
        }
    }
    
    private void createMidpointsWithChance(World world, BlockPos middle, IBlockState state, int radius, float chance)
    {
        BlockPos midpoint;
        
        if (world.getBlockState((midpoint = middle.add(-radius, 0, 0)).down()) == state) setBlockWithChance(world, midpoint, state, chance);
        if (world.getBlockState((midpoint = middle.add(radius, 0, 0)).down()) == state) setBlockWithChance(world, midpoint, state, chance);
        if (world.getBlockState((midpoint = middle.add(0, 0, -radius)).down()) == state) setBlockWithChance(world, midpoint, state, chance);
        if (world.getBlockState((midpoint = middle.add(0, 0, radius)).down()) == state) setBlockWithChance(world, midpoint, state, chance);
    }
    
    private void setBlockWithChance(World world, BlockPos pos, IBlockState state, float chance)
    {
        if (world.rand.nextFloat() < chance)
            world.setBlockState(pos, state);
    }
}
