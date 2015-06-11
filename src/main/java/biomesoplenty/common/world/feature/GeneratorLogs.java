/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorLogs extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorLogs>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockState with = Blocks.log.getDefaultState();
        protected IBlockPosQuery placeOn = new BlockQueryMaterial(Material.ground, Material.grass);
        protected int minLength = 3;
        protected int maxLength = 5;
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder log(IBlockState a) {this.with = a; return this;}
        public Builder log(BOPWoods a) {this.with = BlockBOPLog.paging.getVariantState(a); return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this;}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this;}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this;}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this;}      
        public Builder minLength(int a) {this.minLength = a; return this;}
        public Builder maxLength(int a) {this.maxLength = a; return this;}

        @Override
        public GeneratorLogs create()
        {
            return new GeneratorLogs(this.amountPerChunk, this.with, this.minLength, this.maxLength, this.placeOn);
        }
    }
    
    protected IBlockState with;
    protected IProperty axisProperty;
    protected IBlockPosQuery placeOn;
    protected int minLength;
    protected int maxLength;
    
    public GeneratorLogs(float amountPerChunk, IBlockState with, int minLength, int maxLength, IBlockPosQuery placeOn)
    {
        
        super(amountPerChunk);
        this.with = with;
        this.axisProperty = getAxisProperty(with);
        if (this.axisProperty == null)
        {
            throw new RuntimeException("Block state " + with + " has no axis property with X and Z values - is it actually a log?");
        }
        this.placeOn = placeOn;
        this.minLength = minLength;
        this.maxLength = maxLength;

    }
    
    // Find the property of the block state which stores axis values
    public static IProperty getAxisProperty(IBlockState log)
    {
        for (Object property : log.getProperties().keySet())
        {
            Collection allowedValues = ((IProperty)property).getAllowedValues();
            if (allowedValues.contains(BlockLog.EnumAxis.X) && allowedValues.contains(BlockLog.EnumAxis.Z))
            {
                return(IProperty)property;
            }
        }
        return null;      
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return world.getHeight(new BlockPos(x, 0, z));
    }
    
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        
        // move upwards until we find an air block
        while (!world.isAirBlock(pos)) {pos = pos.up();}
        
        // if we can't start placing the log, abandon now
        if (!this.placeOn.matches(world, pos.down())) {return false;}
        
        // choose random direction and target length
        BlockLog.EnumAxis direction = (random.nextInt(2) == 0) ? BlockLog.EnumAxis.X : BlockLog.EnumAxis.Z;
        int length = this.minLength + random.nextInt(this.maxLength - this.minLength);
        
        // keep placing logs along the chosen direction (as long as the block beneath is suitable)
        while(length > 0 && world.isAirBlock(pos) && this.placeOn.matches(world, pos.down()))
        {
            world.setBlockState(pos, this.with.withProperty(this.axisProperty, direction));
            pos = (direction == BlockLog.EnumAxis.X) ? pos.east() : pos.north();
            length--;
        }
        return true;
    }
 
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        IBlockState with = conf.getBlockState("with", null);
        if (with != null)
        {
            IProperty axisProperty = getAxisProperty(with);
            if (axisProperty == null)
            {
                conf.addMessage("log", "Block state " + with + " has no axis property with X and Z values - is it actually a log?");
            } else {
                this.with = with;
                this.axisProperty = axisProperty;
            }
        }
        this.minLength = conf.getInt("minLength", this.minLength);
        this.maxLength = conf.getInt("maxLength", this.maxLength);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
    
}