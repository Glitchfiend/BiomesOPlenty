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
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryState;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorWaterside extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorWaterside>
    {
        protected float amountPerChunk = 1.0F;
        protected int maxRadius = 7;
        protected IBlockState to = Blocks.gravel.getDefaultState();                
        protected IBlockQuery from = new BlockQueryAny(new BlockQueryBlock(Blocks.grass), new BlockQueryBlock(Blocks.dirt));
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder from(IBlockQuery a) {this.from = a; return this;}
        public Builder from(String a) throws BlockQueryParseException {this.from = BlockQueryUtils.parseQueryString(a); return this;}
        public Builder from(Block a) {this.from = new BlockQueryBlock(a); return this;}
        public Builder from(IBlockState a) {this.from = new BlockQueryState(a); return this;}        
        public Builder to(IBlockState a) {this.to = a; return this;}
        public Builder maxRadius(int a) {this.maxRadius = a; return this;}

        @Override
        public GeneratorWaterside create()
        {
            return new GeneratorWaterside(this.amountPerChunk, this.maxRadius, this.to, this.from);
        }
    }
    
    
    protected int maxRadius;
    protected IBlockState to;
    protected IBlockQuery from;
    
    public GeneratorWaterside(float amountPerChunk, int maxRadius, IBlockState to, IBlockQuery from)
    {
        super(amountPerChunk);
        this.maxRadius = maxRadius;
        this.to = to;
        this.from = from;
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
                            IBlockState stateToReplace = world.getBlockState(posToReplace);

                            // If the current block is applicable, replace it
                            if (this.from.matches(stateToReplace))
                            {
                                world.setBlockState(posToReplace, this.to, 2);
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
        this.maxRadius = conf.getInt("maxRadius", this.maxRadius);
        this.to = conf.getBlockState("to", this.to);        
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
