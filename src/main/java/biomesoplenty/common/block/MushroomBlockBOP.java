/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class MushroomBlockBOP extends MushroomBlock
{
    public MushroomBlockBOP(Block.Properties properties)
    {
        super(properties);
    }
    
    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
    {
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        Block ground = worldIn.getBlockState(pos.down()).getBlock();
        BlockState BlockState = worldIn.getBlockState(pos.down());

        if (this == BOPBlocks.poison_puff)
        {
            return ground == Blocks.END_STONE || BlockState.canSustainPlant(worldIn, pos.down(), net.minecraft.util.Direction.UP, this);
        }

        return BlockState.canSustainPlant(worldIn, pos.down(), net.minecraft.util.Direction.UP, this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        Block block = stateIn.getBlock();

        if (block == BOPBlocks.poison_puff)
        {
            if (rand.nextInt(8) == 0)
            {
                worldIn.addParticle(ParticleTypes.SNEEZE, (double)(pos.getX() + 0.5D), (double)(pos.getY() + 0.5D), (double)(pos.getZ() + 0.5D), (rand.nextDouble() - rand.nextDouble()) / 10.0D, (rand.nextDouble() - rand.nextDouble()) / 10.0D, (rand.nextDouble() - rand.nextDouble()) / 10.0D);
            }
        }
    }

    @Override
    public void onEntityCollision(BlockState stateIn, World worldIn, BlockPos pos, Entity entityIn)
    {
        Block block = stateIn.getBlock();

        if (!worldIn.isRemote && worldIn.getDifficulty() != Difficulty.PEACEFUL)
        {
            if (block == BOPBlocks.poison_puff)
            {
                if (entityIn instanceof LivingEntity)
                {
                    LivingEntity livingentity = (LivingEntity) entityIn;
                    livingentity.addPotionEffect(new EffectInstance(Effects.POISON, 50));
                }
            }
        }
    }

    @Override
    public boolean generateBigMushroom(IWorld worldIn, BlockPos pos, BlockState state, Random rand)
    {
    	return false;
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
    	return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }
}
