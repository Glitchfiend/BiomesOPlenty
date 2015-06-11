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
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.*;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorWaterside extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorWaterside>
    {
        protected float amountPerChunk = 1.0F;
        protected int maxRadius = 7;
        protected IBlockPosQuery replace = new BlockQueryMaterial(Material.grass, Material.ground);
        protected IBlockState with = Blocks.gravel.getDefaultState();
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}        
        public Builder with(IBlockState a) {this.with = a; return this;}
        public Builder maxRadius(int a) {this.maxRadius = a; return this;}

        @Override
        public GeneratorWaterside create()
        {
            return new GeneratorWaterside(this.amountPerChunk, this.replace, this.with, this.maxRadius);
        }
    }
    
    
    
   
    protected IBlockPosQuery replace;
    protected IBlockState with;
    protected int maxRadius;
    
    public GeneratorWaterside(float amountPerChunk, IBlockPosQuery replace, IBlockState with, int maxRadius)
    {
        super(amountPerChunk);
        this.replace = replace;
        this.with = with;
        this.maxRadius = maxRadius;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        //Check we are generating around water
        if (world.getBlockState(pos).getBlock().getMaterial() != Material.water)
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
