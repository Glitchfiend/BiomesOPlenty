/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.init.ModParticles;
import biomesoplenty.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class PusBubbleBlock extends Block
{
    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);

    public PusBubbleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        return groundState.is(ModTags.Blocks.FLESH);
    }

    @Override
    public BlockState updateShape(BlockState p_51032_, Direction p_51033_, BlockState p_51034_, LevelAccessor p_51035_, BlockPos p_51036_, BlockPos p_51037_)
    {
        return !p_51032_.canSurvive(p_51035_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_51033_, p_51034_, p_51035_, p_51036_, p_51037_);
    }

    @Override
    public void onProjectileHit(Level p_57381_, BlockState p_57382_, BlockHitResult p_57383_, Projectile p_57384_)
    {
        p_57381_.destroyBlock(p_57383_.getBlockPos(), false);
        this.spawnParticles(p_57381_, p_57383_.getBlockPos());
    }

    @Override
    public void attack(BlockState p_55467_, Level p_55468_, BlockPos p_55469_, Player p_55470_)
    {
        this.spawnParticles(p_55468_, p_55469_);
        super.attack(p_55467_, p_55468_, p_55469_, p_55470_);
    }

    @Override
    public void wasExploded(Level p_54184_, BlockPos p_54185_, Explosion p_54186_)
    {
        if (p_54184_ instanceof ServerLevel)
        {
            this.spawnParticles((ServerLevel)p_54184_, p_54185_);
        }
    }

    @Override
    public void entityInside(BlockState stateIn, Level worldIn, BlockPos pos, Entity entityIn)
    {
        if (entityIn instanceof LivingEntity)
        {
            worldIn.destroyBlock(pos, false);
            spawnParticles(worldIn, pos);
        }
    }

    public static void spawnParticles(Level p_55480_, BlockPos pos)
    {
        Random rand = p_55480_.random;

        for (int i = 0; i < 10; i++)
        {
            p_55480_.addParticle(ModParticles.PUS.get(), (double)(pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 8.0D)), (double) (pos.getY() + 0.25D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 8.0D)), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState p_53910_, BlockPlaceContext p_53911_)
    {
        return true;
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState p_54173_)
    {
        return PushReaction.DESTROY;
    }
}
