package biomesoplenty.common.handler;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPPlanks;
import biomesoplenty.common.block.BlockBOPWoodStairs;
import biomesoplenty.common.enums.BOPWoods;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterialEventHandler
{
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Coniferous Forest
		if (event.getBiome() == BOPBiomes.coniferous_forest.get())
		{
			//Planks
			if (event.getOriginal().getBlock() == Blocks.PLANKS)
			{
				event.setReplacement(BlockBOPPlanks.paging.getVariantState(BOPWoods.FIR));
				event.setResult(Result.DENY);
			}
			//Logs
			if (event.getOriginal().getBlock() == Blocks.LOG)
			{
				event.setReplacement(BlockBOPLog.paging.getVariantState(BOPWoods.FIR));
				event.setResult(Result.DENY);
			}
			if (event.getOriginal().getBlock() == Blocks.OAK_STAIRS)
			{
				int meta = BlockStairs.getStateId(event.getOriginal());
				event.setReplacement(BlockBOPWoodStairs.getBlock(BOPWoods.FIR).getStateFromMeta(meta));
				event.setResult(Result.DENY);
			}
		}
	}
}
