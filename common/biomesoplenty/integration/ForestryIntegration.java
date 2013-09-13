package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.GlobalManager;
import forestry.api.core.ItemInterface;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;

public class ForestryIntegration
{
	private static final int MINER = 0;
	private static final int DIGGER = 1;
	private static final int FORESTER = 2;

	protected static void init()
	{
		addClimateInfo();
		addFermenterRecipes();
		addFlowers();
		addBlocksToManager();
		addBlocksToBackpacks();
	}

	private static void addClimateInfo()
	{
	    //Hell
	    EnumTemperature.hellishBiomeIds.add(BOPConfiguration.IDs.netherBaseID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfiguration.IDs.netherBoneID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfiguration.IDs.netherDesertID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfiguration.IDs.netherGardenID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfiguration.IDs.netherLavaID);
	    
	    EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.netherBaseID);
	    EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.netherBoneID);
	    EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.netherDesertID);
	    EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.netherGardenID);
	    EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.netherLavaID);
	    
		//Hot - Arid
		//Desert Hives
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.badlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.beachGravelID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.beachOvergrownID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.brushlandID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.deadlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.dunesID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.mesaID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.steppeID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.volcanoID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.wastelandID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.promisedLandPlainsID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.deadForestID);
		EnumTemperature.hotBiomeIds.add(BOPConfiguration.IDs.desertNewID);

		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.badlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.brushlandID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.deadlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.dunesID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.mesaID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.steppeID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.volcanoID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.wastelandID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.promisedLandPlainsID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.deadForestID);
		EnumHumidity.aridBiomeIds.add(BOPConfiguration.IDs.desertNewID);



		//Warm - Damp
		//Jungle Hives
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.bambooForestID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.hotSpringsID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.sacredSpringsID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.tropicalRainforestID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.promisedLandSwampID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.oasisID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.rainforestID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.tropicsID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.woodlandID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.jungleNewID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.jungleHillsNewID);
		EnumTemperature.warmBiomeIds.add(BOPConfiguration.IDs.overgrownGreensID);

		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.bambooForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.hotSpringsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.sacredSpringsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.tropicalRainforestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.promisedLandSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.oasisID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.rainforestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.tropicsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.woodlandID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.jungleNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.jungleHillsNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.overgrownGreensID);


		//Normal - Damp
		//Marshy Hives
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.bayouID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.bogID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.deadSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.fungiForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.lushSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.mangroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.marshID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.moorID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.mysticGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.ominousWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.quagmireID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.sludgepitID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.wetlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.fenID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.swamplandNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.silkgladesID);

		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.bayouID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.bogID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.deadSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.fungiForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.lushSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.mangroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.marshID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.moorID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.mysticGroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.ominousWoodsID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.quagmireID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.sludgepitID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.wetlandID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.fenID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.swamplandNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfiguration.IDs.silkgladesID);

		//Normal
		//Forest and Meadows Hives
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.birchForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.borealForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.canyonID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.chaparralID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.cherryBlossomGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.coniferousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.deciduousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.fieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.gardenID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.grasslandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.groveID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.highlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.lushDesertID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.mapleWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.meadowID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.orchardID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.originValleyID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.pastureID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.prairieID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.redwoodForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.seasonalForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.shieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.shoreID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.spruceWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.temperateRainforestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.heathlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.promisedLandForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.savannaID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.scrublandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.cragID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.jadeCliffsID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.outbackID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.shrublandID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.thicketID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.timberID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.plainsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.extremeHillsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.forestNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.forestHillsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfiguration.IDs.autumnHillsID);

		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.beachGravelID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.beachOvergrownID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.birchForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.borealForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.canyonID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.chaparralID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.cherryBlossomGroveID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.coniferousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.deciduousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.fieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.gardenID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.grasslandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.groveID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.highlandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.lushDesertID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.mapleWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.meadowID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.orchardID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.originValleyID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.pastureID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.prairieID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.redwoodForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.seasonalForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.shieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.shoreID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.spruceWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.temperateRainforestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.heathlandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.promisedLandForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.savannaID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.scrublandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.cragID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.jadeCliffsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.outbackID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.shrublandID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.thicketID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.timberID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.plainsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.extremeHillsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.forestNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.forestHillsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.autumnHillsID);


		//Cold- Normal
		//Wintry Bees
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.tundraID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.arcticID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.deadForestSnowID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.mountainID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.taigaNewID);
		EnumTemperature.coldBiomeIds.add(BOPConfiguration.IDs.taigaHillsNewID);

		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.arcticID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.tundraID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.deadForestSnowID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.mountainID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.taigaNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.taigaHillsNewID);

		//Icy - Normal
		//Wintry Bees
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.alpsID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.coniferousForestSnowID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.frostForestID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.glacierID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.icyHillsID);
		EnumTemperature.icyBiomeIds.add(BOPConfiguration.IDs.polarID);

		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.alpsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.coniferousForestSnowID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.frostForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.glacierID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.icyHillsID);
		EnumHumidity.normalBiomeIds.add(BOPConfiguration.IDs.polarID);
	}

	private static void addFermenterRecipes()
	{
		addFermenterRecipeSapling(new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		addFermenterRecipeSapling(new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));

		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(Items.food.get(), 0)}, new FluidStack(FluidRegistry.getFluid("juice"), 50), ItemInterface.getItem("mulch"), 5);
	}

	private static void addFermenterRecipeSapling(ItemStack resource) 
	{

		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f,
				FluidRegistry.getFluidStack("biomass", 1),
				new FluidStack(FluidRegistry.getFluid("water"), 1));

		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f,
				FluidRegistry.getFluidStack("biomass", 1),
				FluidRegistry.getFluidStack("juice", 1));

		RecipeManagers.fermenterManager.addRecipe(resource, 250, 1.0f,
				FluidRegistry.getFluidStack("biomass", 1),
				FluidRegistry.getFluidStack("honey", 1));
	}

	private static void addFlowers()
	{
		for (int i = 0; i < 10; ++i) 
		{
			if (i != 2)
				FlowerManager.plainFlowers.add(new ItemStack(Blocks.flowers.get(), 1, i));
		}
	}

	private static void addBlocksToManager()
	{
		GlobalManager.leafBlockIds.add(Blocks.leaves1.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leaves2.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesColorized.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesFruit.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesFruit2.get().blockID);

		GlobalManager.dirtBlockIds.add(Blocks.ash.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.hardDirt.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.holyGrass.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.mud.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.originGrass.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.redRock.get().blockID);

		GlobalManager.sandBlockIds.add(Blocks.hardSand.get().blockID);

		GlobalManager.snowBlockIds.add(Blocks.hardIce.get().blockID);
	}

	private static void addBlocksToBackpacks()
	{
		// Miner's Backpack
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyDirt.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyStone.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.redRock.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.driedDirt.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyGrass.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.cragRock.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.ashStone.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Items.miscItems.get(), 1, 2));

		// Digger's Backpack
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.holyGrass.get(), 1, 1));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.mud.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.hardDirt.get(), 1, 0));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.hardSand.get(), 1, 0));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.originGrass.get(), 1, 0));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.ash.get(), 1, 0));

		// Forester's Backpack
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs1.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs2.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs3.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.logs4.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves1.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves2.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesColorized.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesFruit2.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.bamboo.get(), 1, 0));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.petals.get(), 1, OreDictionary.WILDCARD_VALUE));
	}
}
