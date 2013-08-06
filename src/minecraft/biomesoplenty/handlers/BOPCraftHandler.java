package biomesoplenty.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.helpers.AchievementHelper;
import cpw.mods.fml.common.ICraftingHandler;

public class BOPCraftHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer var1, ItemStack var2, IInventory var3)
	{
	}

	@Override
	public void onSmelting(EntityPlayer var1, ItemStack var2) {}
}
