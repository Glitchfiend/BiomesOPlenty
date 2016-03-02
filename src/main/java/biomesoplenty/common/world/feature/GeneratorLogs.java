/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorLogs extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorLogs> implements IGeneratorBuilder<GeneratorLogs>
    {
        protected int minLength;
        protected int maxLength;

        public Builder with(BOPWoods a) {this.with = BlockBOPLog.paging.getVariantState(a); return this.self();}
        public Builder with(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.with = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, a);
            } else {
                this.with = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, a);
            }
            return this.self();
        }
        public Builder minLength(int a) {this.minLength = a; return this.self();}
        public Builder maxLength(int a) {this.maxLength = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.grass, Material.ground);
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = Blocks.log.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.minLength = 3;
            this.maxLength = 5;
        }

        @Override
        public GeneratorLogs create()
        {
            return new GeneratorLogs(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minLength, this.maxLength);
        }
    } 
  
    protected IProperty axisProperty;
    protected int minLength;
    protected int maxLength;
    
    public GeneratorLogs(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minLength, int maxLength)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.axisProperty = GeneratorUtils.getAxisProperty(with);
        this.minLength = minLength;
        this.maxLength = maxLength;

    }
    
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        // if we can't start placing the log, abandon now
        if (!this.placeOn.matches(world, pos.down())) {return false;}
        
        // choose random direction and target length
        BlockLog.EnumAxis direction = (random.nextInt(2) == 0) ? BlockLog.EnumAxis.X : BlockLog.EnumAxis.Z;
        int length = this.minLength + random.nextInt(this.maxLength - this.minLength);
        IBlockState state = (this.axisProperty == null) ? this.with : this.with.withProperty(this.axisProperty, direction);
        
        // keep placing logs along the chosen direction (as long as the block beneath is suitable)
        while(length > 0 && this.replace.matches(world, pos) && this.placeOn.matches(world, pos.down()))
        {
            world.setBlockState(pos, state);
            pos = (direction == BlockLog.EnumAxis.X) ? pos.east() : pos.north();
            length--;
        }
        return true;
    }
 
    
    @Override
    public void configure(IConfigObj conf)
    {        
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.with = conf.getBlockState("with", this.with);
        this.axisProperty = GeneratorUtils.getAxisProperty(with);
        this.minLength = conf.getInt("minLength", this.minLength);
        this.maxLength = conf.getInt("maxLength", this.maxLength);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
    
}