/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import forestry.api.core.INBTTagable;
import forestry.api.genetics.IEffectData;

public interface IBeekeepingLogic extends INBTTagable {

	/* STATE INFORMATION */
	int getBreedingTime();

	int getTotalBreedingTime();

	IBee getQueen();

	IBeeHousing getHousing();
	
	IEffectData[] getEffectData();

	/**
	 * Checks that the bees can work, setting error conditions on the housing where needed
	 * @return true if no errors are present and doWork should be called
	 */
	boolean canWork();

	/**
	 * Performs actual work, breeding, production, etc.
	 */
	void doWork();

	/**
	 * @deprecated since Forestry 3.6. use canWork() and doWork() instead
	 */
	@Deprecated
	void update();

}
