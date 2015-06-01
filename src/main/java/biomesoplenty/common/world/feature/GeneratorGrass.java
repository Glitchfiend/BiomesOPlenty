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
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils;

public class GeneratorGrass extends GeneratorFlora
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorGrass>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockState grass = Blocks.tallgrass.getDefaultState();
        protected int generationAttempts = 64;
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder grass(IBlockState a) {this.grass = a; return this;}
        public Builder grass(BOPPlants a) {this.grass = BlockBOPPlant.paging.getVariantState(a); return this;}
        public Builder grass(BlockTallGrass.EnumType a) {this.grass = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, a); return this;}        
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this;}
        
        @Override
        public GeneratorGrass create()
        {
            return new GeneratorGrass(this.amountPerChunk, this.grass, this.generationAttempts);
        }
    }
    
    public GeneratorGrass(float amountPerChunk, IBlockState state, int generationAttempts)
    {
        super(amountPerChunk, state, generationAttempts);
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block;
        Block stateBlock = this.state.getBlock();

        do
        {
            block = world.getBlockState(pos).getBlock();
            if (!block.isAir(world, pos) && !block.isLeaves(world, pos)) break;
            pos = pos.down();
        } 
        while (pos.getY() > 0);

        for (int i = 0; i < this.generationAttempts; i++)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            boolean canStay = stateBlock instanceof BlockDecoration ? ((BlockDecoration)stateBlock).canBlockStay(world, genPos, this.state) : stateBlock.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && canStay)
            {
                world.setBlockState(genPos, this.state, 2);
            }
        }

        return true;
    }
}
