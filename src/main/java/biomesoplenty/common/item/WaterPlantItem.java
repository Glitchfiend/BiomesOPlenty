package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class WaterPlantItem extends BlockItem
{
	public WaterPlantItem(Block blockIn, Item.Properties builder)
	{
		super(blockIn, builder);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context)
	{
		return ActionResultType.PASS;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return new ActionResult<>(ActionResultType.PASS, itemstack);
		} else {
			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
				BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
				BlockPos blockpos = blockraytraceresult.getPos();
				Direction direction = blockraytraceresult.getFace();
				if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack)) {
					return new ActionResult<>(ActionResultType.FAIL, itemstack);
				}

				BlockPos blockpos1 = blockpos.up();
				BlockState blockstate = worldIn.getBlockState(blockpos);
				Material material = blockstate.getMaterial();
				IFluidState ifluidstate = worldIn.getFluidState(blockpos);

				Block ground = worldIn.getBlockState(blockpos.down()).getBlock();
				if ((ifluidstate.getFluid() == Fluids.WATER || material == Material.ICE) && worldIn.isAirBlock(blockpos1) && (ground == Blocks.DIORITE || ground == Blocks.GRANITE || ground == Blocks.ANDESITE || ground == Blocks.STONE || ground == Blocks.DIRT || ground == Blocks.COARSE_DIRT || ground == Blocks.GRASS_BLOCK || ground == Blocks.GRAVEL || ground == Blocks.SAND || ground == Blocks.RED_SAND || ground == BOPBlocks.white_sand || ground == BOPBlocks.mud || ground == BOPBlocks.dried_sand))
				{
					// special case for handling block placement with water lilies
					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
					worldIn.setBlockState(blockpos1, this.getBlock().getDefaultState(), 11);
					if (net.minecraftforge.event.ForgeEventFactory.onBlockPlace(playerIn, blocksnapshot, net.minecraft.util.Direction.UP)) {
						blocksnapshot.restore(true, false);
						return new ActionResult<ItemStack>(ActionResultType.FAIL, itemstack);
					}

					if (playerIn instanceof ServerPlayerEntity) {
						CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, blockpos1, itemstack);
					}

					if (!playerIn.abilities.isCreativeMode) {
						itemstack.shrink(1);
					}

					playerIn.addStat(Stats.ITEM_USED.get(this));
					worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
				}
			}

			return new ActionResult<>(ActionResultType.FAIL, itemstack);
		}
	}
}