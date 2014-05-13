package biomesoplenty.common.eventhandler.world;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.terraingen.BiomeEvent;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBiomes;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class VillageMaterialEventHandler
{
	//TODO: FEATURE All commented out
	@SubscribeEvent
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Birch Forest
		/*if (event.biome == Biomes.birchForest.get())
		{	
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Block.stairsWoodBirch.blockID;
				event.setResult(Result.DENY);
			}
		}*/
		
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
		
		//Coniferous Forest
		if (event.biome == BOPCBiomes.coniferousForest)
		{	
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPBlockHelper.get("firStairs");
				event.setResult(Result.DENY);
			}
		}
		
		//Desert
		/*if (event.biome == Biomes.desertNew.get())
		{
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Block.stairsSandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Blocks.stone_brick_stairs)
			{
				event.replacement = Block.stairsSandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
		}*/
		
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
				event.replacement = BOPBlockHelper.get("logs4");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPBlockHelper.get("jacarandaStairs");
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
		
		//Lush Desert
		if (event.biome == BOPCBiomes.lushDesert)
		{		
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = Blocks.log2;
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
		
		//Maple Woods
		if (event.biome == BOPCBiomes.mapleWoods)
		{	
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
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
		
		//Meadow Forest
		/*if (event.biome == Biomes.meadowForest.get())
		{	
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = Blocks.log;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Blocks.stone_brick_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Blocks.stone_slab)
			{
				event.replacement = Blocks.wooden_slab;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Blocks.lava)
			{
				event.replacement = Blocks.water;
				event.setResult(Result.DENY);
			}
			if (event.original == Blocks.flowing_lava)
			{
				event.replacement = Blocks.flowing_water;
				event.setResult(Result.DENY);
			}
			
			//Glass Panes
			if (event.original == Blocks.glass_pane)
			{
				event.replacement = Blocks.iron_bars;
				event.setResult(Result.DENY);
			}
		}*/
		
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
		
		//Overgrown Greens
		/*if (event.biome == Biomes.overgrownGreens.get())
		{
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = Blocks.mossy_cobblestone;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.leaves;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.grass;
				event.setResult(Result.DENY);
			}
		}*/
		
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
		
		//Savanna
		/*if (event.biome == Biomes.savanna.get())
		{
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Blocks.stone_brick_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Blocks.stone_slab)
			{
				event.replacement = BOPBlockHelper.get("woodenSingleSlab1");
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Blocks.lava)
			{
				event.replacement = Blocks.water;
				event.setResult(Result.DENY);
			}
			if (event.original == Blocks.flowing_lava)
			{
				event.replacement = Blocks.flowing_water;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
			
			//Glass Panes
			if (event.original == Blocks.glass_pane)
			{
				event.replacement = Blocks.iron_bars;
				event.setResult(Result.DENY);
			}
		}*/
		
		//Scrubland
		/*if (event.biome == Biomes.scrubland.get())
		{
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}*/
		
		//Snowy Coniferous Forest
		if (event.biome == BOPCBiomes.snowyConiferousForest)
		{	
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPBlockHelper.get("firStairs");
				event.setResult(Result.DENY);
			}
		}
		
		//Spruce Woods
		if (event.biome == BOPCBiomes.spruceWoods)
		{	
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.spruce_stairs;
				event.setResult(Result.DENY);
			}
		}
		
		//Steppe
		/*if (event.biome == BOPBiomeHelper.get("steppe"))
		{
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs1");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Blocks.stone_brick_stairs)
			{
				event.replacement = Blocks.acacia_stairs;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Blocks.stone_slab)
			{
				event.replacement = BOPBlockHelper.get("woodenSingleSlab1");
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Blocks.lava)
			{
				event.replacement = Blocks.water;
				event.setResult(Result.DENY);
			}
			if (event.original == Blocks.flowing_lava)
			{
				event.replacement = Blocks.flowing_water;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
			
			//Glass Panes
			if (event.original == Blocks.glass_pane)
			{
				event.replacement = Blocks.iron_bars;
				event.setResult(Result.DENY);
			}
		}*/
		
		//Tropical Rainforest
		if (event.biome == BOPCBiomes.tropicalRainforest)
		{	
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs4");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPBlockHelper.get("mahoganyStairs");
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Blocks.gravel)
			{
				event.replacement = Blocks.sand;
				event.setResult(Result.DENY);
			}
		}
		
		//Wetland
		if (event.biome == BOPCBiomes.wetland)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = BOPBlockHelper.get("logs3");
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = BOPBlockHelper.get("planks");
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = BOPBlockHelper.get("willowStairs");
				event.setResult(Result.DENY);
			}
		}
	}
	
	@SubscribeEvent
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{
		//Birch Forest
		/*if (event.biome == Biomes.birchForest.get())
		{	
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
		}*/
		
		//Coniferous Forest
		if (event.biome == BOPCBiomes.coniferousForest)
		{	
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
		}
		
		//Desert
		/*if (event.biome == Biomes.desertNew.get())
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
		}*/
		
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
		
		//Lush Desert
		if (event.biome == BOPCBiomes.lushDesert)
		{
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 4;
				event.setResult(Result.DENY);
			}
		}
		
		//Maple Woods
		if (event.biome == BOPCBiomes.mapleWoods)
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
		
		//Meadow Forest
		/*if (event.biome == Biomes.meadowForest.get())
		{
			//Cobblestone
			if (event.original == Blocks.cobblestone)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
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
			
			//Single Stone Slab
			if (event.original == Blocks.stone_slab)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}*/
		
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
		
		//Overgrown Greens
		/*if (event.biome == Biomes.overgrownGreens.get())
		{
			//Wooden Stairs
			if (event.original == Blocks.oak_stairs)
			{
				event.replacement = 4;
				event.setResult(Result.DENY);
			}
		}*/
		
		//Snowy Coniferous Forest
		if (event.biome == BOPCBiomes.snowyConiferousForest)
		{		
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
		}
		
		//Spruce Woods
		if (event.biome == BOPCBiomes.spruceWoods)
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
		
		//Tropical Rainforest
		if (event.biome == BOPCBiomes.tropicalRainforest)
		{
			//Logs
			if (event.original == Blocks.log)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Blocks.planks)
			{
				event.replacement = 14;
				event.setResult(Result.DENY);
			}
		}
		
		//Wetland
		if (event.biome == BOPCBiomes.wetland)
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
				event.replacement = 9;
				event.setResult(Result.DENY);
			}
		}
	}
}