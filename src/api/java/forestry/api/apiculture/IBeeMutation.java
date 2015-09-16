/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;

public interface IBeeMutation extends IMutation {
	
	IBeeRoot getRoot();
	
	/**
	 * @param housing
	 * @param allele0
	 * @param allele1
	 * @param genome0
	 * @param genome1
	 * @return float representing the chance for mutation to occur. note that this is 0 - 100 based, since it was an integer previously!
	 * @deprecated since Forestry 3.6, use the IAlleleBeeSpecies / IBeeGenome version
	 */
	@Deprecated
	float getChance(IBeeHousing housing, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1);

	float getChance(IBeeHousing housing, IAlleleBeeSpecies allele0, IAlleleBeeSpecies allele1, IBeeGenome genome0, IBeeGenome genome1);
}
