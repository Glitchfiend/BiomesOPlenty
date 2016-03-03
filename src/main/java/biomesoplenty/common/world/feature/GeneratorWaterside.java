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
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorWaterside extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorWaterside> implements IGeneratorBuilder<GeneratorWaterside>
    {
        protected int maxRadius;

        public Builder maxRadius(int a) {this.maxRadius = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.grass, Material.ground);
            this.with = Blocks.gravel.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_GROUND;
            this.maxRadius = 7;
        }

        @Override
        public GeneratorWaterside create()
        {
            return new GeneratorWaterside(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.maxRadius);
        }
    }  
    
   
    protected int maxRadius;
    
    public GeneratorWaterside(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int maxRadius)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.maxRadius = maxRadius;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        //Check we are generating around water
        if (world.getBlockState(pos).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            //The minimum radius must be 2, so two is subtracted from the random number calculation
            //and is added back on at the end
            int radius = random.nextInt(this.maxRadius - 2) + 2;
            byte heightRadius = 2;

            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    int xDiff = x - pos.getX();
                    int zDiff = z - pos.getZ();

                    //Only replace blocks if the point is within an actual circle of the given radius
                    //The for loops merely create a square with a length and width 2 * radius
                    //x^2 + y^2 = r^2 is the equation of a circle
                    if (xDiff * xDiff + zDiff * zDiff <= radius * radius)
                    {
                        for (int y = pos.getY() - heightRadius; y <= pos.getY() + heightRadius; y++)
                        {
                            BlockPos posToReplace = new BlockPos(x, y, z);

                            // If the current block is applicable, replace it
                            if (this.replace.matches(world, posToReplace))
                            {
                                world.setBlockState(posToReplace, this.with, 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.replace = conf.getBlockPosQuery("replace", this.replace); 
        this.with = conf.getBlockState("with", this.with);
        this.maxRadius = conf.getInt("maxRadius", this.maxRadius);   
    }

}
