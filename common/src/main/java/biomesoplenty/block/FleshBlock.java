/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
            if (worldIn.getFluidState(pos.relative(direction)).is(BOPFluids.BLOOD))
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
                        worldIn.setBlock(pos.above(), BOPBlocks.HAIR.defaultBlockState(), 2);
                    }
                    break;

                case 1:
                    if (worldIn.isEmptyBlock(pos.above()))
                    {
                        worldIn.setBlock(pos.above(), BOPBlocks.PUS_BUBBLE.defaultBlockState(), 2);
                    }
                    break;

                case 2:
                    if (worldIn.isEmptyBlock(pos.above()) && worldIn.isEmptyBlock(pos.above(2)))
                    {
                        DoublePlantBlock.placeAt(worldIn, BOPBlocks.EYEBULB.defaultBlockState(), pos.above(), 2);
                    }
                    break;

                case 3:
                    if (worldIn.isEmptyBlock(pos.below()))
                    {
                        worldIn.setBlock(pos.below(), BOPBlocks.FLESH_TENDONS.defaultBlockState(), 2);
                    }
                    break;
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult)
    {
        if (stack.is(ModTags.Items.SHEARS) && state.getBlock() == BOPBlocks.FLESH)
        {
            if (!level.isClientSide)
            {
                Direction direction = hitResult.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? player.getDirection().getOpposite() : direction;
                level.playSound((Player)null, pos, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlock(pos, BOPBlocks.POROUS_FLESH.defaultBlockState(), 11);
                ItemEntity itementity = new ItemEntity(level, (double)pos.getX() + 0.5D + (double)direction1.getStepX() * 0.65D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D + (double)direction1.getStepZ() * 0.65D, new ItemStack(Items.ROTTEN_FLESH, 1));
                itementity.setDeltaMovement(0.05D * (double)direction1.getStepX() + level.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double)direction1.getStepZ() + level.random.nextDouble() * 0.02D);
                level.addFreshEntity(itementity);
                stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                level.gameEvent(player, GameEvent.SHEAR, pos);
                player.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        else
        {
            return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
        }
    }
}
