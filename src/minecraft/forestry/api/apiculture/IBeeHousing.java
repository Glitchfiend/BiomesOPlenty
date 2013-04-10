package forestry.api.apiculture;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

public interface IBeeHousing extends IBeeModifier, IBeeListener {

	int getXCoord();

	int getYCoord();

	int getZCoord();

	ItemStack getQueen();

	ItemStack getDrone();

	void setQueen(ItemStack itemstack);

	void setDrone(ItemStack itemstack);

	int getBiomeId();

	EnumTemperature getTemperature();

	EnumHumidity getHumidity();

	World getWorld();

	/**
	 * @return String containing the login of this housing's owner.
	 */
	String getOwnerName();

	void setErrorState(int state);

	int getErrorOrdinal();

	/**
	 * @return true if princesses and drones can (currently) mate in this housing to generate queens.
	 */
	boolean canBreed();

	/**
	 * Called by IBeekeepingLogic to add products to the housing's inventory.
	 * 
	 * @param product
	 *            ItemStack with the product to add.
	 * @param all
	 * @return Boolean indicating success or failure.
	 */
	boolean addProduct(ItemStack product, boolean all);

}
