/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class SpiderEggBlock extends Block
{
    public SpiderEggBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());

        return groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState p_51032_, Direction p_51033_, BlockState p_51034_, LevelAccessor p_51035_, BlockPos p_51036_, BlockPos p_51037_)
    {
        if (!p_51032_.canSurvive(p_51035_, p_51036_))
        {
            //this.spawnSpider(p_154567_, p_51036_);
            return Blocks.AIR.defaultBlockState();
        }
        else
        {
            return super.updateShape(p_51032_, p_51033_, p_51034_, p_51035_, p_51036_, p_51037_);
        }
    }

    @Override
    public void onProjectileHit(Level p_57381_, BlockState p_57382_, BlockHitResult p_57383_, Projectile p_57384_)
    {
        p_57381_.playSound((Player)null, p_57383_.getBlockPos(), SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57381_.random.nextFloat() * 0.2F);
        p_57381_.destroyBlock(p_57383_.getBlockPos(), false);
        this.spawnSpider(p_57381_, p_57383_.getBlockPos());
    }

    @Override
    public void playerDestroy(Level p_57771_, Player p_57772_, BlockPos p_57773_, BlockState p_57774_, @Nullable BlockEntity p_57775_, ItemStack p_57776_)
    {
        super.playerDestroy(p_57771_, p_57772_, p_57773_, p_57774_, p_57775_, p_57776_);
        this.spawnSpider(p_57771_, p_57773_);
    }

    @Override
    public void fallOn(Level p_154567_, BlockState p_154568_, BlockPos p_154569_, Entity p_154570_, float p_154571_)
    {
        p_154567_.playSound((Player)null, p_154569_, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_154567_.random.nextFloat() * 0.2F);
        p_154567_.destroyBlock(p_154569_, false);
        this.spawnSpider(p_154567_, p_154569_);
    }

    public void spawnSpider(Level p_154567_, BlockPos p_154569_)
    {
        CaveSpider spider = EntityType.CAVE_SPIDER.create(p_154567_);
        spider.moveTo((double)p_154569_.getX() + 0.3D, (double)p_154569_.getY(), (double)p_154569_.getZ() + 0.3D, 0.0F, 0.0F);
        p_154567_.addFreshEntity(spider);
    }
}
