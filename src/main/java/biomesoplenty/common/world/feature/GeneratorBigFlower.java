/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBigFlower extends BOPGeneratorBase
{
    
    public static enum BigFlowerType
    {
        RED, YELLOW;  
    }
    
    protected static IBlockState stem = BlockBOPLog.paging.getVariantState(BOPWoods.GIANT_FLOWER);
    
    public static class Builder extends BOPGeneratorBase.InnerBuilder<Builder, GeneratorBigFlower> implements IGeneratorBuilder<GeneratorBigFlower>
    {
        protected BigFlowerType flowerType;
        protected IBlockPosQuery placeOn;
        protected IBlockPosQuery replace; 
        
        public Builder flowerType(BigFlowerType a) {this.flowerType = a; return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this.self();}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this.self();}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this.self();}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this.self();}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this.self();}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this.self();}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this.self();}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this.self();}
        
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.flowerType = BigFlowerType.RED;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
        }
        
        @Override
        public GeneratorBigFlower create()
        {
            return new GeneratorBigFlower(this.amountPerChunk, this.placeOn, this.replace, this.flowerType);
        }
    }
    
    protected IBlockPosQuery placeOn;
    protected IBlockPosQuery replace;
    protected BigFlowerType flowerType;
    protected IBlockState petalState;
    
    public GeneratorBigFlower(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, BigFlowerType flowerType)
    {
        super(amountPerChunk);
        this.placeOn = placeOn;
        this.replace = replace;
        this.setFlowerType(flowerType);
    }
    
    public void setFlowerType(BigFlowerType type)
    {
        this.flowerType = type;
        switch (type)
        {
            case RED:
                this.petalState = BlockBOPLeaves.paging.getVariantState(BOPTrees.RED_BIG_FLOWER);
                break;
            case YELLOW:
                this.petalState = BlockBOPLeaves.paging.getVariantState(BOPTrees.YELLOW_BIG_FLOWER);
                break;
        }
        this.petalState = this.petalState.withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockLeaves.DECAYABLE, false);
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }
    
    public boolean isEnoughSpace(World world, BlockPos pos)
    {  
        if (pos.getY() < 1 || pos.getY() + 8 > 255)
        {
            return false;
        }
        for (int y = pos.getY(); y <= pos.getY() + 8; ++y)
        {
            int radius = (y <= pos.getY() + 4) ? 0 : 2;

            for (int x = pos.getX() - radius; x <= pos.getX() + radius; ++x)
            {
                for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z)
                {
                    if (!this.replace.matches(world, new BlockPos(x, y, z)))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    
    public boolean setStem(World world, BlockPos pos)
    {
        return this.setStem(world, pos, EnumFacing.Axis.Y);
    }
    
    public boolean setStem(World world, BlockPos pos, EnumFacing.Axis axis)
    {        
        IBlockState directedStem = (axis == null) ? stem : stem.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(axis));
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, directedStem, 2);
            return true;
        }
        return false;
    }
    
    public boolean setPetal(World world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.petalState, 2);
            return true;
        }
        return false;       
    }
    
    
    
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        
        // check that there's room
        if (!this.isEnoughSpace(world, pos)) {return false;}
        
        if (!this.placeOn.matches(world, pos.down()))
        {
            return false;
        }

        world.getBlockState(pos.down()).getBlock().onPlantGrow(world.getBlockState(pos.down()), world, pos.down(), pos);

        this.setStem(world, pos);
        this.setStem(world, pos.up(1));
        this.setStem(world, pos.up(2));
        this.setStem(world, pos.up(3));
        this.setStem(world, pos.up(4));
        
        switch(this.flowerType)
        {
            case RED:
                
                this.setPetal(world, pos.add(-1, 4, 0));
                this.setPetal(world, pos.add(1, 4, 0));
                this.setPetal(world, pos.add(0, 4, -1));
                this.setPetal(world, pos.add(0, 4, 1));

                this.setPetal(world, pos.add(0, 5, 0));
                this.setPetal(world, pos.add(-1, 5, 0));
                this.setPetal(world, pos.add(1, 5, 0));
                this.setPetal(world, pos.add(0, 5, -1));
                this.setPetal(world, pos.add(0, 5, 1));
                this.setPetal(world, pos.add(1, 5, 1));
                this.setPetal(world, pos.add(1, 5, -1));
                this.setPetal(world, pos.add(-1, 5, 1));
                this.setPetal(world, pos.add(-1, 5, -1));
                this.setPetal(world, pos.add(2, 5, 0));
                this.setPetal(world, pos.add(-2, 5, 0));
                this.setPetal(world, pos.add(0, 5, 2));
                this.setPetal(world, pos.add(0, 5, -2));

                this.setPetal(world, pos.add(1, 6, 1));
                this.setPetal(world, pos.add(1, 6, -1));
                this.setPetal(world, pos.add(-1, 6, 1));
                this.setPetal(world, pos.add(-1, 6, -1));
                this.setPetal(world, pos.add(2, 6, 0));
                this.setPetal(world, pos.add(-2, 6, 0));
                this.setPetal(world, pos.add(0, 6, 2));
                this.setPetal(world, pos.add(0, 6, -2));
                this.setPetal(world, pos.add(2, 6, 2));
                this.setPetal(world, pos.add(2, 6, -2));
                this.setPetal(world, pos.add(-2, 6, 2));
                this.setPetal(world, pos.add(-2, 6, -2));

                this.setPetal(world, pos.add(2, 7, 0));
                this.setPetal(world, pos.add(-2, 7, 0));
                this.setPetal(world, pos.add(0, 7, 2));
                this.setPetal(world, pos.add(0, 7, -2));

                this.setPetal(world, pos.add(3, 8, 0));
                this.setPetal(world, pos.add(-3, 8, 0));
                this.setPetal(world, pos.add(0, 8, 3));
                this.setPetal(world, pos.add(0, 8, -3));
                
                break;
            
            case YELLOW:
                
                this.setPetal(world, pos.add(-1, 4, 0));
                this.setPetal(world, pos.add(1, 4, 0));
                this.setPetal(world, pos.add(0, 4, -1));
                this.setPetal(world, pos.add(0, 4, 1));
                this.setPetal(world, pos.add(2, 4, 2));
                this.setPetal(world, pos.add(2, 4, -2));
                this.setPetal(world, pos.add(-2, 4, 2));
                this.setPetal(world, pos.add(-2, 4, -2));

                this.setPetal(world, pos.add(0, 5, 0));
                this.setPetal(world, pos.add(-1, 5, 0));
                this.setPetal(world, pos.add(1, 5, 0));
                this.setPetal(world, pos.add(0, 5, -1));
                this.setPetal(world, pos.add(0, 5, 1));
                this.setPetal(world, pos.add(1, 5, 1));
                this.setPetal(world, pos.add(1, 5, -1));
                this.setPetal(world, pos.add(-1, 5, 1));
                this.setPetal(world, pos.add(-1, 5, -1)); 
                this.setPetal(world, pos.add(2, 5, 0));
                this.setPetal(world, pos.add(-2, 5, 0));
                this.setPetal(world, pos.add(0, 5, 2));
                this.setPetal(world, pos.add(0, 5, -2));

                this.setPetal(world, pos.add(0, 6, 0));                
                this.setPetal(world, pos.add(3, 6, 0));
                this.setPetal(world, pos.add(-3, 6, 0));
                this.setPetal(world, pos.add(0, 6, 3));
                this.setPetal(world, pos.add(0, 6, -3));

        }
        
        return true;
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.setFlowerType(conf.getEnum("type", this.flowerType, BigFlowerType.class));
    }
    
}
