package thaumcraft.api.internal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public interface IInternalMethodHandler {

	public void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color);
	public boolean isResearchComplete(String username, String researchkey);
	public ItemStack getStackInRowAndColumn(Object instance, int row, int column);
	public AspectList getObjectAspects(ItemStack is);
	public AspectList getBonusObjectTags(ItemStack is,AspectList ot);
	public AspectList generateTags(Item item, int meta);
	public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting);
	public boolean consumeVisFromWandCrafting(ItemStack wand,EntityPlayer player, AspectList cost, boolean doit);
	public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost);
	public void addWarpToPlayer(EntityPlayer player, int amount,boolean temporary);
	public void addStickyWarpToPlayer(EntityPlayer player, int amount);
	public boolean hasDiscoveredAspect(String username, Aspect aspect);
	public AspectList getDiscoveredAspects(String username);
	
}
