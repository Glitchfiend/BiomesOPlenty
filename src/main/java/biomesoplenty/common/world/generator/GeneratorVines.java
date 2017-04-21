/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator;

import java.util.List;
import java.util.Random;
import java.util.Set;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorVines extends GeneratorReplacing
{
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorVines> implements IGeneratorBuilder<GeneratorVines>
    {
        protected int minHeight;
        protected int maxHeight;
        protected int generationAttempts;
        
        public Builder minHeight(int a) {this.minHeight = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.GROUND, Material.GRASS);
            this.replace = BlockQueries.air;
            this.with = Blocks.VINE.getDefaultState();
            this.scatterYMethod = ScatterYMethod.NETHER_ROOF;
            this.minHeight = 2;
            this.maxHeight = 4;
            this.generationAttempts = 12;
        }

        @Override
        public GeneratorVines create()
        {
            return new GeneratorVines(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minHeight, this.maxHeight, this.generationAttempts);
        }
    }
    
    protected int minHeight;
    protected int maxHeight;
    protected int generationAttempts;

    public GeneratorVines(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minHeight, int maxHeight, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        boolean ret = true;

        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));

            if (!this.replace.matches(world, genPos) || !this.placeOn.matches(world, genPos.up())) continue;

            IBlockState vineState = this.with;

            // make sure there is an adjacent block for the vine to attach to
            List<EnumFacing> validDirections = Lists.newArrayList();

            for (EnumFacing facing : EnumFacing.values()) {
                if (facing == EnumFacing.UP || facing == EnumFacing.DOWN) continue;
                if (this.placeOn.matches(world, genPos.offset(facing))) validDirections.add(facing);
            }

            if (validDirections.isEmpty()) continue;

            if (this.with.getBlock() instanceof BlockVine)
            {
                EnumFacing direction = validDirections.get(rand.nextInt(validDirections.size()));
                vineState = this.with.withProperty(BlockVine.getPropertyFor(direction), Boolean.valueOf(true));
            }

            // choose random target height
            int targetHeight = GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);

            // keep placing blocks upwards (if there's room)
            for (int height = 0; height <= targetHeight; height++)
            {
                BlockPos offsetPos = genPos.down(height);

                if (replace.matches(world, offsetPos) && vineState.getBlock().canPlaceBlockAt(world, offsetPos))
                {
                    world.setBlockState(offsetPos, vineState);
                }
                else
                {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {          
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.with = conf.getBlockState("with", this.with);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("maxHeight", this.maxHeight);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
}