/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class FleshBlock extends Block
{
    public FleshBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState blockState, Entity entityIn)
    {
        entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0.95D, 1.0D, 0.95D));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource p_221787_)
    {
        boolean bloodAdjacent = false;
        for (Direction direction : Direction.values())
        {
            if (worldIn.getFluidState(pos.relative(direction)).is(BOPFluids.BLOOD.get()))
            {
                bloodAdjacent = true;
            }
        }

        if (bloodAdjacent && p_221787_.nextInt(15) == 0)
        {
            switch (p_221787_.nextInt(4))
            {
                default:
                case 0:
                    if (worldIn.isEmptyBlock(pos.above()))
                    {
                        worldIn.setBlock(pos.above(), BOPBlocks.HAIR.get().defaultBlockState(), 2);
                    }
                    break;

                case 1:
                    if (worldIn.isEmptyBlock(pos.above()))
                    {
                        worldIn.setBlock(pos.above(), BOPBlocks.PUS_BUBBLE.get().defaultBlockState(), 2);
                    }
                    break;

                case 2:
                    if (worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above(2)))
                    {
                        DoublePlantBlock.placeAt(worldIn, BOPBlocks.EYEBULB.get().defaultBlockState(), pos.above(), 2);
                    }
                    break;

                case 3:
                    if (worldIn.isEmptyBlock(pos.below()))
                    {
                        worldIn.setBlock(pos.below(), BOPBlocks.FLESH_TENDONS.get().defaultBlockState(), 2);
                    }
                    break;
            }
        }
    }

    @Override
    public InteractionResult use(BlockState p_55289_, Level p_55290_, BlockPos p_55291_, Player p_55292_, InteractionHand p_55293_, BlockHitResult p_55294_)
    {
        ItemStack itemstack = p_55292_.getItemInHand(p_55293_);
        if (p_55289_.getBlock() == BOPBlocks.FLESH.get() && itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_CARVE))
        {
            if (!p_55290_.isClientSide)
            {
                Direction direction = p_55294_.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? p_55292_.getDirection().getOpposite() : direction;
                p_55290_.playSound((Player)null, p_55291_, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                p_55290_.setBlock(p_55291_, BOPBlocks.POROUS_FLESH.get().defaultBlockState(), 11);
                ItemEntity itementity = new ItemEntity(p_55290_, (double)p_55291_.getX() + 0.5D + (double)direction1.getStepX() * 0.65D, (double)p_55291_.getY() + 0.1D, (double)p_55291_.getZ() + 0.5D + (double)direction1.getStepZ() * 0.65D, new ItemStack(Items.ROTTEN_FLESH, 1));
                itementity.setDeltaMovement(0.05D * (double)direction1.getStepX() + p_55290_.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction1.getStepZ() + p_55290_.random.nextDouble() * 0.02D);
                p_55290_.addFreshEntity(itementity);
                itemstack.hurtAndBreak(1, p_55292_, (p_55287_) -> {
                    p_55287_.broadcastBreakEvent(p_55293_);
                });
                p_55290_.gameEvent(p_55292_, GameEvent.SHEAR, p_55291_);
                p_55292_.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return InteractionResult.sidedSuccess(p_55290_.isClientSide);
        }
        else
        {
            return super.use(p_55289_, p_55290_, p_55291_, p_55292_, p_55293_, p_55294_);
        }
    }
}
