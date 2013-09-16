package biomesoplenty.handlers;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent;
import biomesoplenty.api.Biomes;
import biomesoplenty.api.Blocks;

public class VillageMaterialEventHandler
{
	@ForgeSubscribe
	public void getVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		//Arctic
		if (event.biome == Biomes.arctic.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Glass Panes
			if (event.original == Block.thinGlass.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Iron Bars
			if (event.original == Block.fenceIron.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Fences
			if (event.original == Block.fence.blockID)
			{
				event.replacement = Block.cobblestoneWall.blockID;
				event.setResult(Result.DENY);
			}
			
			//Double Stone Slabs
			if (event.original == Block.stoneDoubleSlab.blockID)
			{
				event.replacement = Block.blockSnow.blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.ice.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.ice.blockID;
				event.setResult(Result.DENY);
			}
			
			//Water
			if (event.original == Block.waterStill.blockID)
			{
				event.replacement = Block.ice.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.waterMoving.blockID)
			{
				event.replacement = Block.ice.blockID;
				event.setResult(Result.DENY);
			}
			
			//Crops
			if (event.original == Block.crops.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.potato.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.carrot.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
			
			//Farmland
			if (event.original == Block.tilledField.blockID)
			{
				event.replacement = Block.dirt.blockID;
				event.setResult(Result.DENY);
			}
			
			//Pressure Plate
			if (event.original == Block.pressurePlatePlanks.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
		}
		
		//Birch Forest
		if (event.biome == Biomes.birchForest.get())
		{	
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodBirch.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Brushland
		if (event.biome == Biomes.brushland.get())
		{
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Coniferous Forest
		if (event.biome == Biomes.coniferousForest.get())
		{	
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.firStairs.get().blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Desert
		if (event.biome == Biomes.desertNew.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsSandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.stairsSandStone.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sandStone.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Grove
		if (event.biome == Biomes.grove.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.wood.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.leaves.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Block.woodSingleSlab.blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Heathland
		if (event.biome == Biomes.heathland.get())
		{
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Lush Desert
		if (event.biome == Biomes.lushDesert.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Blocks.redRock.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Blocks.redCobbleStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Blocks.stoneSingleSlab.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Lush Swamp
		if (event.biome == Biomes.lushSwamp.get())
		{
			//Gravel
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.cobblestoneMossy.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Maple Woods
		if (event.biome == Biomes.mapleWoods.get())
		{	
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow
		if (event.biome == Biomes.meadow.get())
		{	
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.wood.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Block.woodSingleSlab.blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow Forest
		if (event.biome == Biomes.meadowForest.get())
		{	
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.wood.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Block.woodSingleSlab.blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Outback
		if (event.biome == Biomes.outback.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.leaves.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Blocks.woodenSingleSlab1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Overgrown Greens
		if (event.biome == Biomes.overgrownGreens.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.cobblestoneMossy.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.leaves.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.grass.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Prairie
		if (event.biome == Biomes.prairie.get())
		{
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Savanna
		if (event.biome == Biomes.savanna.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Blocks.woodenSingleSlab1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Scrubland
		if (event.biome == Biomes.scrubland.get())
		{
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Snowy Coniferous Forest
		if (event.biome == Biomes.coniferousForestSnow.get())
		{	
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.firStairs.get().blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Spruce Woods
		if (event.biome == Biomes.spruceWoods.get())
		{	
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodSpruce.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Steppe
		if (event.biome == Biomes.steppe.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Blocks.acaciaStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Blocks.woodenSingleSlab1.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Tropical Rainforest
		if (event.biome == Biomes.tropicalRainforest.get())
		{	
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Block.wood.blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Block.stairsWoodJungle.blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Block.stairsWoodJungle.blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
			
			//Gravel
			if (event.original == Block.gravel.blockID)
			{
				event.replacement = Block.sand.blockID;
				event.setResult(Result.DENY);
			}
		}
		
		//Wetland
		if (event.biome == Biomes.wetland.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = Blocks.logs3.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = Blocks.logs3.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = Blocks.planks.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = Blocks.willowStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Cobblestone Stairs
			if (event.original == Block.stairsCobblestone.blockID)
			{
				event.replacement = Blocks.willowStairs.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Stone Slabs
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = Blocks.woodenSingleSlab2.get().blockID;
				event.setResult(Result.DENY);
			}
			
			//Lava
			if (event.original == Block.lavaStill.blockID)
			{
				event.replacement = Block.waterStill.blockID;
				event.setResult(Result.DENY);
			}
			if (event.original == Block.lavaMoving.blockID)
			{
				event.replacement = Block.waterMoving.blockID;
				event.setResult(Result.DENY);
			}
		}
	}
	
	@ForgeSubscribe
	public void getVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
	{
		//Birch Forest
		if (event.biome == Biomes.birchForest.get())
		{	
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
		}
		
		//Coniferous Forest
		if (event.biome == Biomes.coniferousForest.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
		}
		
		//Desert
		if (event.biome == Biomes.desertNew.get())
		{
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 2;
				event.setResult(Result.DENY);
			}
		}
		
		//Grove
		if (event.biome == Biomes.grove.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = 5;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slab
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Lush Desert
		if (event.biome == Biomes.lushDesert.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slab
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = 0;
				event.setResult(Result.DENY);
			}
		}
		
		//Maple Woods
		if (event.biome == Biomes.mapleWoods.get())
		{
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow
		if (event.biome == Biomes.meadow.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slab
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Meadow Forest
		if (event.biome == Biomes.meadowForest.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slab
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Outback
		if (event.biome == Biomes.outback.get())
		{
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = 4;
				event.setResult(Result.DENY);
			}
		}
		
		//Overgrown Greens
		if (event.biome == Biomes.overgrownGreens.get())
		{
			//Wooden Stairs
			if (event.original == Block.stairsWoodOak.blockID)
			{
				event.replacement = 4;
				event.setResult(Result.DENY);
			}
		}
		
		//Snowy Coniferous Forest
		if (event.biome == Biomes.coniferousForestSnow.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
		}
		
		//Spruce Woods
		if (event.biome == Biomes.spruceWoods.get())
		{
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
		
		//Tropical Rainforest
		if (event.biome == Biomes.tropicalRainforest.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 3;
				event.setResult(Result.DENY);
			}
		}
		
		//Wetland
		if (event.biome == Biomes.wetland.get())
		{
			//Cobblestone
			if (event.original == Block.cobblestone.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Logs
			if (event.original == Block.wood.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
			
			//Wooden Planks
			if (event.original == Block.planks.blockID)
			{
				event.replacement = 9;
				event.setResult(Result.DENY);
			}
			
			//Single Stone Slab
			if (event.original == Block.stoneSingleSlab.blockID)
			{
				event.replacement = 1;
				event.setResult(Result.DENY);
			}
		}
	}
}