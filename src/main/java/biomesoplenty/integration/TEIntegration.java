package biomesoplenty.integration;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import biomesoplenty.api.BOPBlocks;
import cpw.mods.fml.common.event.FMLInterModComms;

public class TEIntegration
{
	protected static void init()
	{
		addPulverizerRecipes();
	}

	private static void addPulverizerRecipes()
	{
		addPulverizerRecipe(160, new ItemStack(BOPBlocks.bones.get(), 1, 0), new ItemStack(Item.dyePowder, 6, 15));
		addPulverizerRecipe(220, new ItemStack(BOPBlocks.bones.get(), 1, 1), new ItemStack(Item.dyePowder, 12, 15));
		addPulverizerRecipe(280, new ItemStack(BOPBlocks.bones.get(), 1, 2), new ItemStack(Item.dyePowder, 24, 15));
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
	    pulverizerCompound.setCompoundTag("input", new NBTTagCompound());
	    pulverizerCompound.setCompoundTag("primaryOutput", new NBTTagCompound());
	    pulverizerCompound.setCompoundTag("secondaryOutput", new NBTTagCompound());
	    input.writeToNBT(pulverizerCompound.getCompoundTag("input"));
	    primaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("primaryOutput"));
	    if (secondaryOutput != null) secondaryOutput.writeToNBT(pulverizerCompound.getCompoundTag("secondaryOutput"));
	    if (secondaryOutput != null && secondaryChance != 0) pulverizerCompound.setInteger("secondaryChance", secondaryChance);
	    
        FMLInterModComms.sendMessage("ThermalExpansion", "PulverizerRecipe", pulverizerCompound);
	}
}
