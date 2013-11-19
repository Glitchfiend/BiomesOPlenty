package biomesoplenty.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.common.event.FMLInterModComms;

public class TEIntegration
{
	protected static void init()
	{
		//addSawMillRecipes();
		addPulverizerRecipes();
	}

	/*private static void addSawMillRecipes()
	{
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs1.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 0));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs1.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 1));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs1.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 2));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs1.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 3));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs2.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 4));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs2.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 5));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs2.get(), 1, 2), new ItemStack(Blocks.planks.get(), 6, 6));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs2.get(), 1, 3), new ItemStack(Blocks.planks.get(), 6, 7));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs3.get(), 1, 0), new ItemStack(Blocks.planks.get(), 6, 8));
		CraftingHelpers.addSawmillLogToPlankRecipe(new ItemStack(Blocks.logs3.get(), 1, 1), new ItemStack(Blocks.planks.get(), 6, 9));
	}*/

	private static void addPulverizerRecipes()
	{
		/*ItemStack woodchips = ItemRegistry.getItem("woodchips", 1);

		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs1.get(), 1, 0), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs1.get(), 1, 1), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs1.get(), 1, 2), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs1.get(), 1, 3), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs2.get(), 1, 0), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs2.get(), 1, 1), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs2.get(), 1, 2), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs2.get(), 1, 3), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs3.get(), 1, 0), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs3.get(), 1, 1), woodchips);
		CraftingManagers.pulverizerManager.addRecipe(160, new ItemStack(Blocks.logs3.get(), 1, 2), woodchips);*/

		addPulverizerRecipe(160, new ItemStack(Blocks.bones.get(), 1, 0), new ItemStack(Item.dyePowder, 6, 15));
		addPulverizerRecipe(220, new ItemStack(Blocks.bones.get(), 1, 1), new ItemStack(Item.dyePowder, 12, 15));
		addPulverizerRecipe(280, new ItemStack(Blocks.bones.get(), 1, 2), new ItemStack(Item.dyePowder, 24, 15));
	}
	
	/*private static void addSawmillRecipe()
	{
	    FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", value)
	}*/
	
    private static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput)
    {
        addPulverizerRecipe(energy, input, primaryOutput, null, 0);
    }
	
	private static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput)
	{
        addPulverizerRecipe(energy, input, primaryOutput, secondaryOutput, 0);
	}

	private static void addPulverizerRecipe(int energy, ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, int secondaryChance)
	{
	    NBTTagCompound pulverizerCompound = new NBTTagCompound();
	    
	    pulverizerCompound.setInteger("energy", energy);
	    input.writeToNBT(pulverizerCompound.getCompoundTag("input"));
	    primaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("primaryOutput"));
	    if (secondaryOutput != null) secondaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("secondaryOutput"));
	    if (secondaryOutput != null && secondaryChance != 0) pulverizerCompound.setInteger("secondaryChance", secondaryChance);
	    
        FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", pulverizerCompound);
	}
}
