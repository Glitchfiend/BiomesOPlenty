/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FoliageBlockBOP extends VegetationBlockBOP
{
    public static final MapCodec<FoliageBlockBOP> CODEC = simpleCodec(FoliageBlockBOP::new);
    protected static final VoxelShape NORMAL = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape SHORT = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);

    public FoliageBlockBOP(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public MapCodec<FoliageBlockBOP> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        Block block = state.getBlock();

        if (block == BOPBlocks.DESERT_GRASS)
        {
            return SHORT;
        }

        return NORMAL;
    }

    @Override
    public void playerDestroy(Level worldIn, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity te, ItemStack stack)
    {
        if (!worldIn.isClientSide() && stack.getItem() == Items.SHEARS)
        {
            player.awardStat(Stats.BLOCK_MINED.get(this));
            player.causeFoodExhaustion(0.005F);
            this.popResource(worldIn, pos, new ItemStack(this));
        }
        else
        {
            super.playerDestroy(worldIn, player, pos, state, te, stack);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        Block ground = groundState.getBlock();

        if (this == BOPBlocks.SPROUT)
        {
            return groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP) || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.DUNE_GRASS)
        {
            return groundState.is(BlockTags.SAND);
        }
        if (this == BOPBlocks.DESERT_GRASS || this == BOPBlocks.DEAD_GRASS)
        {
            return groundState.is(BlockTags.SAND) || ground == BOPBlocks.DRIED_SALT || ground == Blocks.GRAVEL || ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
        }
        if (this == BOPBlocks.ENDERPHYTE)
        {
            return ground == BOPBlocks.ALGAL_END_STONE || ground == BOPBlocks.UNMAPPED_END_STONE || ground == BOPBlocks.NULL_END_STONE || ground == Blocks.END_STONE;
        }

        return super.canSurvive(state, worldIn, pos);
    }

    @Override
    public void animateTick(BlockState p_401875_, Level p_401809_, BlockPos p_401789_, RandomSource p_401918_)
    {
        if (this == BOPBlocks.DUNE_GRASS)
            {
            if (p_401918_.nextInt(75) == 0)
            {
                BlockState blockstate = p_401809_.getBlockState(p_401789_.below());
                if (blockstate.is(Blocks.SAND) && p_401918_.nextInt(10) != 0)
                {
                    return;
                }

                BlockState blockstate1 = p_401809_.getBlockState(p_401789_.below(2));
                if (blockstate.is(BlockTags.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS) && blockstate1.is(BlockTags.TRIGGERS_AMBIENT_DESERT_DRY_VEGETATION_BLOCK_SOUNDS))
                {
                    p_401809_.playLocalSound(p_401789_.getX(), p_401789_.getY(), p_401789_.getZ(), SoundEvents.DEAD_BUSH_IDLE, SoundSource.AMBIENT, 1.0F, 1.0F, false);
                }
            }
        }
    }
}
