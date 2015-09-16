/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import forestry.api.genetics.IAllele;

public interface IBeeMutationFactory {
	/**
	 * Creates a new bee mutation.
	 * Automatically registered with BeeManager.beeRoot.registerMutation()
	 * See IBeeMutationCustom and IMutationCustom for adding additional properties to the returned mutation.
	 *
	 * @param parentBee0 A parent bee for this mutation
	 * @param parentBee1 A parent bee for this mutation
	 * @param result The resulting alleles for this mutation
	 * @param chance The chance that breeding the two parent bees will result in this mutation
	 * @return a new bee mutation.
	 */
	IBeeMutationCustom createMutation(IAlleleBeeSpecies parentBee0, IAlleleBeeSpecies parentBee1, IAllele[] result, int chance);
}
