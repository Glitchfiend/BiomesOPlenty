/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.gen.feature.BOPBiomeFeatures;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class MushroomBlockBOP extends MushroomBlock implements IGrowable
{
    public MushroomBlockBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.below()).getBlock();
        BlockState BlockState = worldIn.getBlockState(pos.below());

        return BlockState.canSustainPlant(worldIn, pos.below(), net.minecraft.util.Direction.UP, this);
    }

    @Override
    public boolean growMushroom(ServerWorld world, BlockPos p_226940_2_, BlockState p_226940_3_, Random p_226940_4_)
    {
        world.removeBlock(p_226940_2_, false);
        ConfiguredFeature<NoFeatureConfig, ?> configuredfeature;
        if (this == BOPBlocks.glowshroom)
        {
            configuredfeature = BOPBiomeFeatures.HUGE_GLOWSHROOM.configured(IFeatureConfig.NONE);
        }
        else
        {
            if (this != BOPBlocks.toadstool)
            {
                world.setBlock(p_226940_2_, p_226940_3_, 3);
                return false;
            }

            configuredfeature = BOPBiomeFeatures.HUGE_TOADSTOOL.configured(IFeatureConfig.NONE);
        }

        if (configuredfeature.place(world, world.getChunkSource().getGenerator(), p_226940_4_, p_226940_2_))
        {
            return true;
        }
        else
        {
            world.setBlock(p_226940_2_, p_226940_3_, 3);
            return false;
        }
    }

    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return (double)rand.nextFloat() < 0.4D;
    }

    public void performBonemeal(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
        this.growMushroom(p_225535_1_, p_225535_3_, p_225535_4_, p_225535_2_);
    }

    public boolean hasPostProcess(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return true;
    }
}
