/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.recipes;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.ItemStack;

public interface ICentrifugeRecipe {

	/** The item for this recipe to match against. **/
	ItemStack getInput();

	/** The time it takes to process one item. Default is 20. **/
	int getProcessingTime();

	/** Returns the randomized products from processing one input item. **/
	Collection<ItemStack> getProducts(Random random);

	/**
	 * Returns a list of all possible products and their estimated probabilities (0.0 to 1.0],
	 * to help mods that display recipes
	 **/
	Map<ItemStack, Float> getAllProducts();
}
