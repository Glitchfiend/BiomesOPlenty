/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.genetics;

import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import com.mojang.authlib.GameProfile;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IErrorState;

/**
 * Any housing, hatchery or nest which is a fixed location in the world.
 */
public interface IHousing {

	/**
	 * @return String containing the login of this housing's owner.
	 */
	GameProfile getOwnerName();

	World getWorld();

	int getXCoord();

	int getYCoord();

	int getZCoord();

	BiomeGenBase getBiome();

	EnumTemperature getTemperature();

	EnumHumidity getHumidity();

	/**
	 * Sets the errorState when condition is true, and unsets it when condition is false.
	 * @return condition
	 */
	boolean setErrorCondition(boolean condition, IErrorState errorState);

	/**
	 * @return the error states for this housing. An empty Set indicates no errors.
	 */
	Set<IErrorState> getErrorStates();

	/**
	 * Adds products to the housing's inventory.
	 * 
	 * @param product
	 *            ItemStack with the product to add.
	 * @param all
	 *            if true, success requires that all products are added
	 * @return Boolean indicating success or failure.
	 */
	boolean addProduct(ItemStack product, boolean all);

	/**
	 * @deprecated since Forestry 3.6. use setErrorCondition()
	 */
	@Deprecated
	void setErrorState(IErrorState state);

	/**
	 * @deprecated since Forestry 3.6. use getErrorStates()
	 */
	@Deprecated
	IErrorState getErrorState();

}
