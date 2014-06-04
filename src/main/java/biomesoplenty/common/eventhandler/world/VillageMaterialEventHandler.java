package biomesoplenty.common.eventhandler.world;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBiomes;
import biomesoplenty.api.content.BOPCBlocks;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterialEventHandler
{
	//TODO: FEATURE All commented out
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Brushland
		if (event.biome == BOPCBiomes.brushland)
		{
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
		
		//Grove
		if (event.biome == BOPCBiomes.grove)
		{	
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
			}
		}
		
		//Heathland
		if (event.biome == BOPCBiomes.heathland)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPCBlocks.logs4;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPCBlocks.planks;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPCBlocks.jacarandaStairs;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow
		if (event.biome == BOPCBiomes.meadow)
		{	
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
			}
		}
		
		//Outback
		if (event.biome == BOPCBiomes.outback)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = Blocks.log2;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = Blocks.planks;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
		
		//Prairie
		if (event.biome == BOPCBiomes.prairie)
		{
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
	}
	
	@SubscribeEvent
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{	
		//Grove
		if (event.biome == BOPCBiomes.grove)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = 5;
				event.setResult(Result.DENY);
			}
		}
		
		//Heathland
		if (event.biome == BOPCBiomes.heathland)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 13;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow
		if (event.biome == BOPCBiomes.meadow)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Outback
		if (event.biome == BOPCBiomes.outback)
		{
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 4;
				event.setResult(Result.DENY);
			}
		}
	}
}