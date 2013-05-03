package forestry.api.apiculture;

import forestry.api.genetics.IAllele;

public interface IAlleleFlowers extends IAllele {

	/**
	 * @return FlowerProvider
	 */
	IFlowerProvider getProvider();

}
