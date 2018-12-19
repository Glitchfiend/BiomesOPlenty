package biomesoplenty.common.handler;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPPlanks;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterialEventHandler
{
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Coniferous Forest
		if (BOPBiomes.coniferous_forest.isPresent() && event.getBiome() == BOPBiomes.coniferous_forest.get())
		{
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(BlockBOPPlanks.paging.getVariantState(BOPWoods.FIR));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
			{
				EnumAxis axis = event.getOriginal().getValue(BlockLog.LOG_AXIS);
				event.setReplacement(BlockBOPLog.paging.getVariantState(BOPWoods.FIR).withProperty(BlockLog.LOG_AXIS, axis));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
			{
				event.setReplacement(BlockBOPLog.paging.getVariantState(BOPWoods.FIR).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
			{
				EnumFacing facing = event.getOriginal().getValue(BlockStairs.FACING);
				event.setReplacement(BOPBlocks.fir_stairs.getDefaultState().withProperty(BlockStairs.FACING, facing));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
			{
				event.setReplacement(BOPBlocks.fir_fence.getDefaultState());
				event.setResult(Result.DENY);
			}
		}
		
		//Grove
		if (BOPBiomes.grove.isPresent() && event.getBiome() == BOPBiomes.grove.get())
		{
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
			{
				EnumAxis axis = event.getOriginal().getValue(BlockLog.LOG_AXIS);
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLog.LOG_AXIS, axis));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
			{
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
			{
				EnumFacing facing = event.getOriginal().getValue(BlockStairs.FACING);
				event.setReplacement(Blocks.DARK_OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, facing));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
			{
				event.setReplacement(Blocks.DARK_OAK_FENCE.getDefaultState());
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow
		if (BOPBiomes.meadow.isPresent() && event.getBiome() == BOPBiomes.meadow.get())
		{
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
			{
				EnumAxis axis = event.getOriginal().getValue(BlockLog.LOG_AXIS);
				event.setReplacement(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockLog.LOG_AXIS, axis));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
			{
				EnumFacing facing = event.getOriginal().getValue(BlockStairs.FACING);
				event.setReplacement(Blocks.SPRUCE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, facing));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
			{
				event.setReplacement(Blocks.SPRUCE_FENCE.getDefaultState());
				event.setResult(Result.DENY);
			}
		}
		
		//Outback
		if (BOPBiomes.outback.isPresent() && event.getBiome() == BOPBiomes.outback.get())
		{
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.LOG || event.getOriginal().getBlock() == Blocks.LOG2)
			{
				EnumAxis axis = event.getOriginal().getValue(BlockLog.LOG_AXIS);
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, axis));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.COBBLESTONE)
			{
				event.setReplacement(Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
			{
				EnumFacing facing = event.getOriginal().getValue(BlockStairs.FACING);
				event.setReplacement(Blocks.ACACIA_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, facing));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_FENCE)
			{
				event.setReplacement(Blocks.ACACIA_FENCE.getDefaultState());
				event.setResult(Result.DENY);
			}
		}
	}
}
