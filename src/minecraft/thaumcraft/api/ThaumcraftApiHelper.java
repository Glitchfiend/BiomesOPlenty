package thaumcraft.api;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class ThaumcraftApiHelper {
	public static ObjectTags cullTags(ObjectTags temp) {
		while (temp!=null && temp.size()>5) {
			EnumTag lowest = null;
			int low = Integer.MAX_VALUE;
			for (EnumTag tag:temp.getAspects()) {
				if (temp.getAmount(tag)<low) {
					low = temp.getAmount(tag);
					lowest = tag;
				}
			}
			temp.tags.remove(lowest);
		}
		return temp; 
	}
	
	public static boolean areItemsEqual(ItemStack s1,ItemStack s2)
    {
		if (s1.isItemStackDamageable() && s2.isItemStackDamageable())
		{
			return s1.itemID == s2.itemID;
		} else
			return s1.itemID == s2.itemID && s1.getItemDamage() == s2.getItemDamage();
    }

	static Method isResearchComplete;
	static Method getObjectTags;
	static Method getBonusTags;
	static Method generateTags;
	public static boolean isResearchComplete(String username, String researchkey) {
		boolean ot = false;
	    try {
	        if(isResearchComplete == null) {
	            Class fake = Class.forName("thaumcraft.common.research.ResearchManager");
	            isResearchComplete = fake.getMethod("isResearchComplete", String.class, String.class);
	        }
	        ot = (Boolean) isResearchComplete.invoke(null, username, researchkey);
	    } catch(Exception ex) { 
	    	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.research.ResearchManager method isResearchComplete");
	    }
		return ot;
	}

	public static ItemStack getStackInRowAndColumn(Object instance, int row, int column) {
		ItemStack ot = null;
	    try {
	        Class fake = Class.forName("thaumcraft.common.tiles.TileMagicWorkbench");
	        Method getStackInRowAndColumn = fake.getMethod("getStackInRowAndColumn", int.class, int.class);
	        ot = (ItemStack) getStackInRowAndColumn.invoke(instance, row, column);
	    } catch(Exception ex) { 
	    	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.tiles.TileMagicWorkbench method getStackInRowAndColumn");
	    }
		return ot;
	}

	public static ObjectTags getObjectTags(ItemStack is) {
		ObjectTags ot = null;
	    try {
	        if(getObjectTags == null) {
	            Class fake = Class.forName("thaumcraft.common.lib.ThaumcraftCraftingManager");
	            getObjectTags = fake.getMethod("getObjectTags", ItemStack.class);
	        }
	        ot = (ObjectTags) getObjectTags.invoke(null, is);
	    } catch(Exception ex) { 
	    	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.lib.ThaumcraftCraftingManager method getObjectTags");
	    }
		return ot;
	}

	public static ObjectTags getBonusObjectTags(ItemStack is,ObjectTags ot) {
		
	    try {
	        if(getBonusTags == null) {
	            Class fake = Class.forName("thaumcraft.common.lib.ThaumcraftCraftingManager");
	            getBonusTags = fake.getMethod("getBonusTags", ItemStack.class, ObjectTags.class);
	        }
	        ot = (ObjectTags) getBonusTags.invoke(null, is, ot);
	    } catch(Exception ex) { 
	    	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.lib.ThaumcraftCraftingManager method getBonusTags");
	    }
		return ot;
	}

	public static ObjectTags generateTags(int id, int meta) {
	    try {
	        if(generateTags == null) {
	            Class fake = Class.forName("thaumcraft.common.lib.ThaumcraftCraftingManager");
	            generateTags = fake.getMethod("generateTags", int.class, int.class);
	        }
	        return (ObjectTags) generateTags.invoke(null, id, meta);
	    } catch(Exception ex) { 
	    	FMLLog.warning("[Thaumcraft API] Could not invoke thaumcraft.common.lib.ThaumcraftCraftingManager method generateTags");
	    }
		return null;
	}
}
