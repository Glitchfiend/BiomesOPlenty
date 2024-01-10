/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import java.util.OptionalInt;

public class ThermalCalciteBlock extends Block
{
    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 5);

    public ThermalCalciteBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(5)));
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand)
    {
        super.animateTick(stateIn, worldIn, pos, rand);
        if (worldIn.getBlockState(pos.above()).getFluidState().getType() == Fluids.WATER && worldIn.getBlockState(pos.above()).getFluidState().getAmount() == 8)
        {
            worldIn.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double) (pos.getX() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), (double) (pos.getY() + 1.0D), (double) (pos.getZ() + 0.5D + ((rand.nextDouble() - rand.nextDouble()) / 2.0D)), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick(BlockState p_221369_, ServerLevel p_221370_, BlockPos p_221371_, RandomSource p_221372_) {
        p_221370_.setBlock(p_221371_, updateDistance(p_221369_, p_221370_, p_221371_), 3);
    }

    @Override
    public InteractionResult use(BlockState p_55289_, Level p_55290_, BlockPos p_55291_, Player p_55292_, InteractionHand p_55293_, BlockHitResult p_55294_)
    {
        ItemStack itemstack = p_55292_.getItemInHand(p_55293_);
        if (itemstack.is(ItemTags.PICKAXES) && p_55289_.getBlock() == BOPBlocks.THERMAL_CALCITE)
        {
            if (!p_55290_.isClientSide)
            {
                int distance = p_55289_.getValue(DISTANCE);
                p_55290_.playSound((Player)null, p_55291_, SoundEvents.CALCITE_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_55290_.setBlock(p_55291_, BOPBlocks.THERMAL_CALCITE_VENT.defaultBlockState().setValue(DISTANCE, distance), 11);
                itemstack.hurtAndBreak(1, p_55292_, (p_55287_) -> {
                    p_55287_.broadcastBreakEvent(p_55293_);
                });
                p_55290_.gameEvent(p_55292_, GameEvent.BLOCK_CHANGE, p_55291_);
                p_55292_.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
            }

            return InteractionResult.sidedSuccess(p_55290_.isClientSide);
        }
        else
        {
            return super.use(p_55289_, p_55290_, p_55291_, p_55292_, p_55293_, p_55294_);
        }
    }

    @Override
    public BlockState updateShape(BlockState p_54440_, Direction p_54441_, BlockState p_54442_, LevelAccessor p_54443_, BlockPos p_54444_, BlockPos p_54445_) {
        int i = getDistanceAt(p_54442_) + 1;
        if (i != 1 || p_54440_.getValue(DISTANCE) != i) {
            p_54443_.scheduleTick(p_54444_, this, 1);
        }

        return p_54440_;
    }

    private static BlockState updateDistance(BlockState p_54436_, LevelAccessor p_54437_, BlockPos p_54438_) {
        int i = 5;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(p_54438_, direction);
            i = Math.min(i, getDistanceAt(p_54437_.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return p_54436_.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceAt(BlockState p_54464_) {
        return getOptionalDistanceAt(p_54464_).orElse(5);
    }

    public static OptionalInt getOptionalDistanceAt(BlockState p_277868_) {
        if (p_277868_.getFluidState().getType() == Fluids.WATER || p_277868_.getFluidState().getType() == Fluids.LAVA || p_277868_.getBlock() instanceof IceBlock || p_277868_.getBlock() instanceof MagmaBlock) {
            return OptionalInt.of(0);
        } else {
            return p_277868_.hasProperty(DISTANCE) ? OptionalInt.of(p_277868_.getValue(DISTANCE)) : OptionalInt.empty();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_54447_) {
        p_54447_.add(DISTANCE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_54424_) {
        BlockState blockstate = this.defaultBlockState();
        return updateDistance(blockstate, p_54424_.getLevel(), p_54424_.getClickedPos());
    }
}
