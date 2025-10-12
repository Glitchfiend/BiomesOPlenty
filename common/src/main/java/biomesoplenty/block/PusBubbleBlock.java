/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.init.ModParticles;
import biomesoplenty.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
        return groundState.is(ModTags.Blocks.FLESH_DECORATION_PLACEABLE) && groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, tickAccess, pos, facing, facingPos, facingState, random);
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
    public void wasExploded(ServerLevel p_54184_, BlockPos p_54185_, Explosion p_54186_)
    {
        if (p_54184_ instanceof ServerLevel)
        {
            this.spawnParticles((ServerLevel)p_54184_, p_54185_);
        }
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean b)
    {
        if (entity instanceof LivingEntity)
        {
            level.destroyBlock(pos, false);
            spawnParticles(level, pos);
        }
    }

    public static void spawnParticles(Level p_55480_, BlockPos pos)
    {
        RandomSource rand = p_55480_.random;

        for (int i = 0; i < 10; i++)
        {
            p_55480_.addParticle(ModParticles.PUS, (double)(pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 8.0D)), (double) (pos.getY() + 0.25D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 8.0D)), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState p_53910_, BlockPlaceContext p_53911_)
    {
        return true;
    }
}
