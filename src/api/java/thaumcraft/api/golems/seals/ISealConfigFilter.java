package thaumcraft.api.golems.seals;

import net.minecraft.item.ItemStack;

public interface ISealConfigFilter {
	
	public ItemStack[] getInv();
	
	public int getFilterSize();
	
	public ItemStack getFilterSlot(int i);
	
	public void setFilterSlot(int i, ItemStack stack);
	
	public boolean isBlacklist();
	
	public void setBlacklist(boolean black);
}
