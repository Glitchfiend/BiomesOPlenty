package forestry.api.apiculture;

import forestry.api.genetics.EnumTolerance;
import forestry.api.genetics.IGenome;

/**
 * Only the default implementation is supported.
 * 
 * @author SirSengir
 * 
 */
public interface IBeeGenome extends IGenome {

	IAlleleBeeSpecies getPrimaryAsBee();

	IAlleleBeeSpecies getSecondaryAsBee();

	float getSpeed();

	int getLifespan();

	int getFertility();

	boolean getNocturnal();

	boolean getTolerantFlyer();

	boolean getCaveDwelling();

	IFlowerProvider getFlowerProvider();

	int getFlowering();

	int[] getTerritory();

	IAlleleBeeEffect getEffect();

	EnumTolerance getToleranceTemp();

	EnumTolerance getToleranceHumid();

}
