package biomesoplenty.common.integration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import forestry.api.apiculture.FlowerManager;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;

public class ForestryIntegration 
{
	private static final String LIQUID_WATER = "water";
	
	private static final String LIQUID_BIOMASS = "biomass";
	private static final String LIQUID_JUICE = "juice";
	private static final String LIQUID_HONEY = "honey";
	
	private static final int MINER = 0;
	private static final int DIGGER = 1;
	private static final int FORESTER = 2;
	
	private static final Item mulch = GameRegistry.findItem("Forestry", "mulch");
	
	public static void init()
	{
		addFermenterRecipes();
		addSqueezerRecipes();
		addFlowers();
		addBlocksToBackpack();
	}
	
	private static void addFermenterRecipes()
	{
		addFermenterRecipe(new ItemStack(BOPCBlocks.saplings, 1, OreDictionary.WILDCARD_VALUE), 250, LIQUID_BIOMASS);
		addFermenterRecipe(new ItemStack(BOPCBlocks.colorizedSaplings, 1, OreDictionary.WILDCARD_VALUE), 250, LIQUID_BIOMASS);
	}
	
	private static void addSqueezerRecipes()
	{
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] { new ItemStack(BOPCItems.food) }, FluidRegistry.getFluidStack(LIQUID_JUICE, 50), new ItemStack(mulch), 5);
		RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] { new ItemStack(BOPCItems.food, 1, 8) }, FluidRegistry.getFluidStack(LIQUID_JUICE, 200), new ItemStack(mulch), 20);
	}
	
	private static void addFlowers()
	{
		FlowerManager.plainFlowers.add(new ItemStack(BOPCBlocks.flowers, 1, OreDictionary.WILDCARD_VALUE));
		FlowerManager.plainFlowers.add(new ItemStack(BOPCBlocks.flowers2, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	private static void addBlocksToBackpack()
	{
		for (int i = 0; i < 16; i++)
		{
			if (i % 2 == 0) BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCBlocks.gemOre, 1, i));
		}
		
		BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCBlocks.driedDirt));
		BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCBlocks.overgrownNetherrack));
		BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCBlocks.cragRock));
		BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCBlocks.ashStone));
		BackpackManager.backpackItems[MINER].add(new ItemStack(BOPCItems.gems, 1, OreDictionary.WILDCARD_VALUE));
		
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.bopGrass, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.longGrass));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.mud));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.hardDirt));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.hardSand));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.originGrass));
		BackpackManager.backpackItems[DIGGER].add(new ItemStack(BOPCBlocks.ash));
		
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.logs1, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.logs2, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.logs3, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.logs4, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.colorizedSaplings, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.saplings, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.leaves1, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.leaves2, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.leaves3, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.leaves4, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.colorizedLeaves1, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.colorizedLeaves2, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.appleLeaves, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.persimmonLeaves, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.bamboo, 1, OreDictionary.WILDCARD_VALUE));
		BackpackManager.backpackItems[FORESTER].add(new ItemStack(BOPCBlocks.petals, 1, OreDictionary.WILDCARD_VALUE));
	}
	
	private static void addFermenterRecipe(ItemStack resource, int fermentationValue, String output)
	{
		RecipeManagers.fermenterManager.addRecipe(resource, fermentationValue, 1.0F, FluidRegistry.getFluidStack(output, 1), FluidRegistry.getFluidStack(LIQUID_WATER, 1));

		if (FluidRegistry.isFluidRegistered(LIQUID_JUICE))
		{
			RecipeManagers.fermenterManager.addRecipe(resource, fermentationValue, 1.5F, FluidRegistry.getFluidStack(output, 1), FluidRegistry.getFluidStack(LIQUID_JUICE, 1));
		}

		if (FluidRegistry.isFluidRegistered(LIQUID_HONEY))
		{
			RecipeManagers.fermenterManager.addRecipe(resource, fermentationValue, 1.5F, FluidRegistry.getFluidStack(output, 1), FluidRegistry.getFluidStack(LIQUID_HONEY, 1));
		}		
	}
}
