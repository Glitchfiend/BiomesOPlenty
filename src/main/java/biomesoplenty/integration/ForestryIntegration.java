package biomesoplenty.integration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfigurationIDs;
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
	    EnumTemperature.hellishBiomeIds.add(BOPConfigurationIDs.netherBaseID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfigurationIDs.netherBoneID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfigurationIDs.netherDesertID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfigurationIDs.netherGardenID);
	    EnumTemperature.hellishBiomeIds.add(BOPConfigurationIDs.netherLavaID);
	    
	    EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.netherBaseID);
	    EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.netherBoneID);
	    EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.netherDesertID);
	    EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.netherGardenID);
	    EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.netherLavaID);
	    
		//Hot - Arid
		//Desert Hives
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.badlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.beachGravelID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.beachOvergrownID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.brushlandID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.deadlandsID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.dunesID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.mesaID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.steppeID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.volcanoID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.wastelandID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.promisedLandPlainsID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.promisedLandShrubID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.deadForestID);
		EnumTemperature.hotBiomeIds.add(BOPConfigurationIDs.desertNewID);

		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.badlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.brushlandID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.deadlandsID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.dunesID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.mesaID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.steppeID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.volcanoID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.wastelandID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.promisedLandPlainsID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.promisedLandShrubID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.deadForestID);
		EnumHumidity.aridBiomeIds.add(BOPConfigurationIDs.desertNewID);



		//Warm - Damp
		//Jungle Hives
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.bambooForestID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.hotSpringsID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.sacredSpringsID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.tropicalRainforestID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.promisedLandSwampID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.oasisID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.rainforestID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.tropicsID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.tropicsMountainID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.woodlandID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.jungleNewID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.jungleHillsNewID);
		EnumTemperature.warmBiomeIds.add(BOPConfigurationIDs.overgrownGreensID);

		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.bambooForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.hotSpringsID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.sacredSpringsID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.tropicalRainforestID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.promisedLandSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.oasisID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.rainforestID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.tropicsID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.tropicsMountainID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.woodlandID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.jungleNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.jungleHillsNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.overgrownGreensID);


		//Normal - Damp
		//Marshy Hives
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.bayouID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.bogID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.deadSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.fungiForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.lushSwampID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.mangroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.marshID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.moorID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.mysticGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.ominousWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.quagmireID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.sludgepitID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.wetlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.fenID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.swamplandNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.silkgladesID);

		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.bayouID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.bogID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.deadSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.fungiForestID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.lushSwampID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.mangroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.marshID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.moorID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.mysticGroveID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.ominousWoodsID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.quagmireID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.sludgepitID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.wetlandID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.fenID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.swamplandNewID);
		EnumHumidity.dampBiomeIds.add(BOPConfigurationIDs.silkgladesID);

		//Normal
		//Forest and Meadows Hives
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.birchForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.borealForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.canyonID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.chaparralID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.cherryBlossomGroveID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.coniferousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.deciduousForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.fieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.gardenID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.grasslandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.groveID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.highlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.lushDesertID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.mapleWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.meadowID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.orchardID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.originValleyID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.pastureID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.prairieID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.redwoodForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.seasonalForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.shieldID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.shoreID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.spruceWoodsID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.temperateRainforestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.heathlandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.promisedLandForestID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.savannaID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.scrublandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.cragID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.jadeCliffsID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.outbackID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.shrublandID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.thicketID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.timberID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.plainsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.extremeHillsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.forestNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.forestHillsNewID);
		EnumTemperature.normalBiomeIds.add(BOPConfigurationIDs.autumnHillsID);

		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.beachGravelID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.beachOvergrownID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.birchForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.borealForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.canyonID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.chaparralID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.cherryBlossomGroveID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.coniferousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.deciduousForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.fieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.gardenID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.grasslandID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.groveID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.highlandID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.lushDesertID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.mapleWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.meadowID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.orchardID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.originValleyID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.pastureID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.prairieID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.redwoodForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.seasonalForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.shieldID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.shoreID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.spruceWoodsID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.temperateRainforestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.heathlandID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.promisedLandForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.savannaID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.scrublandID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.cragID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.jadeCliffsID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.outbackID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.shrublandID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.thicketID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.timberID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.plainsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.extremeHillsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.forestNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.forestHillsNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.autumnHillsID);


		//Cold- Normal
		//Wintry Bees
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.tundraID);
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.arcticID);
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.deadForestSnowID);
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.mountainID);
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.taigaNewID);
		EnumTemperature.coldBiomeIds.add(BOPConfigurationIDs.taigaHillsNewID);

		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.arcticID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.tundraID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.deadForestSnowID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.mountainID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.taigaNewID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.taigaHillsNewID);

		//Icy - Normal
		//Wintry Bees
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.alpsID);
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.coniferousForestSnowID);
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.frostForestID);
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.glacierID);
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.icyHillsID);
		EnumTemperature.icyBiomeIds.add(BOPConfigurationIDs.polarID);

		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.alpsID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.coniferousForestSnowID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.frostForestID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.glacierID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.icyHillsID);
		EnumHumidity.normalBiomeIds.add(BOPConfigurationIDs.polarID);
	}

	private static void addFermenterRecipes()
	{
		addFermenterRecipeSapling(new ItemStack(Blocks.saplings.get(), 1, OreDictionary.WILDCARD_VALUE));
		addFermenterRecipeSapling(new ItemStack(Blocks.colorizedSaplings.get(), 1, OreDictionary.WILDCARD_VALUE));

		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(Items.food.get(), 0)}, new FluidStack(FluidRegistry.getFluid("juice"), 50), ItemInterface.getItem("mulch"), 5);
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {new ItemStack(Items.food.get(), 8)}, new FluidStack(FluidRegistry.getFluid("juice"), 50), ItemInterface.getItem("mulch"), 5);
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
		GlobalManager.leafBlockIds.add(Blocks.leaves3.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leaves4.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesColorized1.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesColorized2.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesFruit.get().blockID);
		GlobalManager.leafBlockIds.add(Blocks.leavesFruit2.get().blockID);

		GlobalManager.dirtBlockIds.add(Blocks.ash.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.hardDirt.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.holyGrass.get().blockID);
		GlobalManager.dirtBlockIds.add(Blocks.longGrass.get().blockID);
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
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 2));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 4));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 6));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 8));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 10));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.amethystOre.get(), 1, 12));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.holyStone.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.redRock.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.driedDirt.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.overgrownNetherrack.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.cragRock.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Blocks.ashStone.get(), 1, 0));
		BackpackManager.backpackItems[MINER].add(new ItemStack(Items.gems.get(), 1, OreDictionary.WILDCARD_VALUE));

		// Digger's Backpack
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.holyGrass.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.holyDirt.get(), 1, 0));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(Blocks.longGrass.get(), 1, 0));
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
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves3.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leaves4.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesColorized1.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesColorized2.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesFruit.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.leavesFruit2.get(), 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.bamboo.get(), 1, 0));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(Blocks.petals.get(), 1, OreDictionary.WILDCARD_VALUE));
	}
}
