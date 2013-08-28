package thaumcraft.api.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class RecipeCrucible {

	public ItemStack recipeOutput;
	public Object catalyst;
	public AspectList aspects;
	public String key;
	
	public RecipeCrucible(String researchKey, ItemStack result, Object catalyst, AspectList tags) {
		recipeOutput = result;
		this.aspects = tags;
		this.key = researchKey;
		this.catalyst = catalyst;
		if (catalyst instanceof String) {
			catalyst = OreDictionary.getOres((String) catalyst);
		}
	}
	

	public boolean matches(AspectList itags, ItemStack cat) {
		if (catalyst instanceof ItemStack && !cat.isItemEqual((ItemStack) catalyst)) {
			return false;
		} else if (catalyst instanceof ArrayList && ((ArrayList<ItemStack>)catalyst).size()>0) {
			//if (!OreDictionary.containsMatch(true, ((ArrayList<ItemStack>)catalyst).toArray(new ItemStack[]{}), cat)) return false;
		}
		if (itags==null) return false;
		for (Aspect tag:aspects.getAspects()) {
			if (itags.getAmount(tag)<aspects.getAmount(tag)) return false;
		}
		return true;
	}
	
	public AspectList removeMatching(AspectList itags) {
		AspectList temptags = new AspectList();
		temptags.aspects.putAll(itags.aspects);
		
		for (Aspect tag:aspects.getAspects()) {
			temptags.remove(tag, aspects.getAmount(tag));
//			if (!temptags.remove(tag, aspects.getAmount(tag))) return null;
		}
		
		itags = temptags;
		return itags;
	}
	
}
