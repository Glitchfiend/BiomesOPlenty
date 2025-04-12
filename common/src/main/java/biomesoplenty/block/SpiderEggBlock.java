/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.sound.BOPSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpiderEggBlock extends Block
{
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D);

    public SpiderEggBlock(Properties properties)
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
        return groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, tickAccess, pos, facing, facingPos, facingState, random);
    }

    @Override
    public void onProjectileHit(Level p_57381_, BlockState p_57382_, BlockHitResult p_57383_, Projectile p_57384_)
    {
        p_57381_.playSound((Player)null, p_57383_.getBlockPos(), BOPSounds.SPIDER_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57381_.random.nextFloat() * 0.2F);
        p_57381_.destroyBlock(p_57383_.getBlockPos(), false);
        this.spawnSpider(p_57381_, p_57383_.getBlockPos());
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double p_396999_)
    {
        level.playSound((Player)null, pos, BOPSounds.SPIDER_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        level.destroyBlock(pos, false);
        this.spawnSpider(level, pos);
    }

    @Override
    public void spawnAfterBreak(BlockState p_54188_, ServerLevel level, BlockPos p_54190_, ItemStack p_54191_, boolean p_222953_)
    {
        super.spawnAfterBreak(p_54188_, level, p_54190_, p_54191_, p_222953_);
        HolderLookup.RegistryLookup<Enchantment> registrylookup = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && EnchantmentHelper.getItemEnchantmentLevel(registrylookup.getOrThrow(Enchantments.SILK_TOUCH), p_54191_) == 0)
        {
            this.spawnSpider(level, p_54190_);
        }
    }

    @Override
    public void wasExploded(ServerLevel p_54184_, BlockPos p_54185_, Explosion p_54186_) {
        if (p_54184_ instanceof ServerLevel)
        {
            this.spawnSpider((ServerLevel)p_54184_, p_54185_);
        }
    }

    public void spawnSpider(Level p_154567_, BlockPos p_154569_)
    {
        CaveSpider spider = EntityType.CAVE_SPIDER.create(p_154567_, EntitySpawnReason.TRIGGERED);
        spider.snapTo((double)p_154569_.getX() + 0.5D, (double)p_154569_.getY(), (double)p_154569_.getZ() + 0.5D, 0.0F, 0.0F);
        p_154567_.addFreshEntity(spider);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
