/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TinyCactusBlock extends VegetationBlockBOP
{
    public static final MapCodec<TinyCactusBlock> CODEC = simpleCodec(TinyCactusBlock::new);
    protected static final VoxelShape NORMAL = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 13.0D, 13.0D);

    public TinyCactusBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public MapCodec<TinyCactusBlock> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return NORMAL;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());

        return groundState.is(BlockTags.DIRT) || groundState.is(BlockTags.SAND);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean b)
    {
        if (entity instanceof Player)
        {
            Player playerEntity = (Player) entity;
            playerEntity.hurt(entity.damageSources().cactus(), 1.0F);
        }
    }
}
