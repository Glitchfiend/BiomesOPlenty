package forestry.api.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

/**
 * Provides an interface to the recipe manager of the bottler.
 * 
 * The manager is initialized at the beginning of Forestry's BaseMod.load() cycle. Begin adding recipes in BaseMod.ModsLoaded() and this shouldn't be null even
 * if your mod loads before Forestry.
 * 
 * Accessible via {@link RecipeManagers.bottlerManager}
 * 
 * Note that this is untested with anything other than biofuel->fuelcan conversion.
 * 
 * @author SirSengir
 */
public interface IBottlerManager extends ICraftingProvider {
	/**
	 * Add a recipe to the bottler
	 * 
	 * @param cyclesPerUnit
	 *            Amount of work cycles required to run through the conversion once.
	 * @param input
	 *            ItemStack representing the input liquid.
	 * @param inputAmount
	 *            Amount of liquid required for a single conversion.
	 * @param can
	 *            ItemStack representing the cans, capsules and/or cells required
	 * @param bottled
	 *            ItemStack representing the finished product
	 */
	public void addRecipe(int cyclesPerUnit, LiquidStack input, ItemStack can, ItemStack bottled);
}
