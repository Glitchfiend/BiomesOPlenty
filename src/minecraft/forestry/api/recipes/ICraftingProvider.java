package forestry.api.recipes;

import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;

public interface ICraftingProvider {
	/**
	 * DOES NOT WORK FOR MANY MACHINES, DON'T USE IT!
	 * 
	 * Access to the full list of recipes contained in the crafting provider.
	 * 
	 * @return List of the given format where the first array represents inputs and the second outputs. Input and output liquids are returned as itemstacks as
	 *         well, representing itemID and damage.
	 */
	@Deprecated
	public List<Map.Entry<ItemStack[], ItemStack[]>> getRecipes();
}
