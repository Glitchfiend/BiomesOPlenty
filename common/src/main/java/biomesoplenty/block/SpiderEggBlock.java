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
import net.minecraft.world.entity.Entity;
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
    public BlockState updateShape(BlockState p_51032_, Direction p_51033_, BlockState p_51034_, LevelAccessor p_51035_, BlockPos p_51036_, BlockPos p_51037_) {
        return !p_51032_.canSurvive(p_51035_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_51033_, p_51034_, p_51035_, p_51036_, p_51037_);
    }

    @Override
    public void onProjectileHit(Level p_57381_, BlockState p_57382_, BlockHitResult p_57383_, Projectile p_57384_)
    {
        p_57381_.playSound((Player)null, p_57383_.getBlockPos(), BOPSounds.SPIDER_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_57381_.random.nextFloat() * 0.2F);
        p_57381_.destroyBlock(p_57383_.getBlockPos(), false);
        this.spawnSpider(p_57381_, p_57383_.getBlockPos());
    }

    @Override
    public void fallOn(Level p_154567_, BlockState p_154568_, BlockPos p_154569_, Entity p_154570_, float p_154571_)
    {
        p_154567_.playSound((Player)null, p_154569_, BOPSounds.SPIDER_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + p_154567_.random.nextFloat() * 0.2F);
        p_154567_.destroyBlock(p_154569_, false);
        this.spawnSpider(p_154567_, p_154569_);
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
    public void wasExploded(Level p_54184_, BlockPos p_54185_, Explosion p_54186_) {
        if (p_54184_ instanceof ServerLevel)
        {
            this.spawnSpider((ServerLevel)p_54184_, p_54185_);
        }
    }

    public void spawnSpider(Level p_154567_, BlockPos p_154569_)
    {
        CaveSpider spider = EntityType.CAVE_SPIDER.create(p_154567_);
        spider.moveTo((double)p_154569_.getX() + 0.5D, (double)p_154569_.getY(), (double)p_154569_.getZ() + 0.5D, 0.0F, 0.0F);
        p_154567_.addFreshEntity(spider);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
