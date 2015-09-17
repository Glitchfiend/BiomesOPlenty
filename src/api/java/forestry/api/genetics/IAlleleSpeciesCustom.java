/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.genetics;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;

public interface IAlleleSpeciesCustom extends IAlleleSpecies {

	IAlleleSpeciesCustom setTemperature(EnumTemperature temperature);
	IAlleleSpeciesCustom setHumidity(EnumHumidity humidity);

	IAlleleSpeciesCustom setHasEffect();

	/** Secret species are not shown in creative mode. */
	IAlleleSpeciesCustom setIsSecret();

	/** Uncounted species do not count toward total species discovered. */
	IAlleleSpeciesCustom setIsNotCounted();
}
