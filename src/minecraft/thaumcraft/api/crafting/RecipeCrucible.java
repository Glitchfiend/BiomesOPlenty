package thaumcraft.api.crafting;

import net.minecraft.item.ItemStack;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;

public class RecipeCrucible {

	public ItemStack recipeOutput;
	public ObjectTags tags;
	public String key;
	public String researchKey;
	public int cost;
	
	public RecipeCrucible(String researchKey, String key, ItemStack result, ObjectTags tags, int cost) {
		recipeOutput = result;
		this.tags = tags;
		this.key = key;
		this.researchKey = researchKey;
		this.cost = cost;
	}
	
	public RecipeCrucible(String key, ItemStack result, ObjectTags tags, int cost) {
		recipeOutput = result;
		this.tags = tags;
		this.key = key;
		this.researchKey = key;
		this.cost = cost;
	}

	public boolean matches(ObjectTags itags) {
		if (itags==null) return false;
		for (EnumTag tag:tags.getAspects()) {
			if (itags.getAmount(tag)<tags.getAmount(tag)) return false;
		}
		return true;
	}
	
	public ObjectTags removeMatching(ObjectTags itags) {
		ObjectTags temptags = new ObjectTags();
		temptags.tags.putAll(itags.tags);
		
		for (EnumTag tag:tags.getAspects()) {
			if (!temptags.reduceAmount(tag, tags.getAmount(tag))) return null;
		}
		
		itags = temptags;
		return itags;
	}
	
}
