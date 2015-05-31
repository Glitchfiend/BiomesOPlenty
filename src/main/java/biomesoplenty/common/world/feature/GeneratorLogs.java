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
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorLogs extends BOPGeneratorBase
{
    
    protected IBlockState log;
    protected IProperty axisProperty;
    protected IBlockQuery placeOn;
    protected int minLength;
    protected int maxLength;
    
    public GeneratorLogs()
    {
        // default
        this(6, Blocks.log.getDefaultState(), 1, 5, new BlockQueryAny(new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass)));
    }
    
    public GeneratorLogs(float amountPerChunk, IBlockState log, int minLength, int maxLength, String from) throws BlockQueryParseException
    {
        this(amountPerChunk, log, minLength, maxLength, BlockQueryUtils.parseQueryString(from));
    }
    
    public GeneratorLogs(float amountPerChunk, IBlockState log, int minLength, int maxLength, Block from)
    {
        this(amountPerChunk, log, minLength, maxLength, new BlockQueryUtils.BlockQueryBlock(from));
    }
    
    public GeneratorLogs(float amountPerChunk, IBlockState log, int minLength, int maxLength, IBlockState from)
    {
        this(amountPerChunk, log, minLength, maxLength, new BlockQueryUtils.BlockQueryState(from));
    }
    
    public GeneratorLogs(float amountPerChunk, IBlockState log, int minLength, int maxLength, IBlockQuery placeOn)
    {
        
        super(amountPerChunk);
        this.log = log;
        this.axisProperty = getAxisProperty(log);
        if (this.axisProperty == null)
        {
            throw new RuntimeException("Block state " + log + " has no axis property with X and Z values - is it actually a log?");
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
        if (!this.placeOn.matches(world.getBlockState(pos.down()))) {return false;}
        
        // choose random direction and target length
        BlockLog.EnumAxis direction = (random.nextInt(2) == 0) ? BlockLog.EnumAxis.X : BlockLog.EnumAxis.Z;
        int length = this.minLength + random.nextInt(this.maxLength - this.minLength);
        
        // keep placing logs along the chosen direction (as long as the block beaneath is suitable)
        while(length > 0 && this.placeOn.matches(world.getBlockState(pos.down())))
        {
            world.setBlockState(pos, this.log.withProperty(this.axisProperty, direction));
            pos = (direction == BlockLog.EnumAxis.X) ? pos.east() : pos.north();
            length--;
        }
        return true;
    }
 
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        IBlockState log = conf.getBlockState("log", null);
        if (log != null)
        {
            IProperty axisProperty = getAxisProperty(log);
            if (axisProperty == null)
            {
                conf.addMessage("log", "Block state " + log + " has no axis property with X and Z values - is it actually a log?");
            } else {
                this.log = log;
                this.axisProperty = axisProperty;
            }
        }
        this.minLength = conf.getInt("minLength", this.minLength);
        this.maxLength = conf.getInt("maxLength", this.maxLength);
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
    }
    
}