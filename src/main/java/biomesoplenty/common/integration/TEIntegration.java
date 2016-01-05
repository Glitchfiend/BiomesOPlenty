package biomesoplenty.common.integration;

import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEIntegration
{
	protected static void init()
	{
		addPulverizerRecipes();
	}

	private static void addPulverizerRecipes()
	{
		addPulverizerRecipe(160, new ItemStack(BOPCBlocks.bones, 1, 0), new ItemStack(Items.dye, 6, 15));
		addPulverizerRecipe(220, new ItemStack(BOPCBlocks.bones, 1, 1), new ItemStack(Items.dye, 12, 15));
		addPulverizerRecipe(280, new ItemStack(BOPCBlocks.bones, 1, 2), new ItemStack(Items.dye, 24, 15));
		addPulverizerRecipe(80, new ItemStack(BOPCBlocks.mushrooms, 1, 0), new ItemStack(BOPCItems.food, 2, 1));
		addPulverizerRecipe(280, new ItemStack(BOPCBlocks.ash, 1), new ItemStack(BOPCItems.misc, 4, 1));
		addPulverizerRecipe(280, new ItemStack(BOPCBlocks.flesh, 1), new ItemStack(BOPCItems.misc, 4, 3));
		addPulverizerRecipe(280, new ItemStack(BOPCBlocks.crystal, 1), new ItemStack(BOPCItems.misc, 4, 4));
	}

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
		pulverizerCompound.setTag("input", new NBTTagCompound());
		pulverizerCompound.setTag("primaryOutput", new NBTTagCompound());
		pulverizerCompound.setTag("secondaryOutput", new NBTTagCompound());
		input.writeToNBT(pulverizerCompound.getCompoundTag("input"));
		primaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("primaryOutput"));
		if (secondaryOutput != null) secondaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("secondaryOutput"));
		if (secondaryOutput != null && secondaryChance != 0) pulverizerCompound.setInteger("secondaryChance", secondaryChance);

		FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", pulverizerCompound);
	}
}
