/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.item.ItemStack;
/**
 * 
 * Some miscellaneous lists and settings for bees.
 * 
 * @author SirSengir
 */
public class BeeManager {

	/**
	 * 0 - Common Village Bees 1 - Uncommon Village Bees (20 % of spawns)
	 */
	public static ArrayList<IBeeGenome>[] villageBees;

	/**
	 * List of items that can induce swarming. Integer denotes x in 1000 chance.
	 */
	public static HashMap<ItemStack, Integer> inducers = new HashMap<ItemStack, Integer>();

	/**
	 * Convenient access to AlleleManager.alleleRegistry.getSpeciesRoot("rootBees")
	 */
	public static IBeeRoot beeRoot;

	/**
	 * Used to create new bees.
	 */
	public static IBeeFactory beeFactory;

	/**
	 * Used to create new bee mutations.
	 */
	public static IBeeMutationFactory beeMutationFactory;

	/**
	 * Used to get Forestry's jubilance implementations.
	 */
	public static IJubilanceFactory jubilanceFactory;
}
