package forestry.api.apiculture;

import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IEffectData;

public interface IAlleleBeeEffect extends IAlleleEffect {
	/**
	 * Called by apiaries to cause an effect in the world.
	 * 
	 * @param genome
	 *            Genome of the bee queen causing this effect
	 * @param storedData
	 *            Object containing the stored effect data for the apiary/hive the bee is in.
	 * @param world
	 * @param biomeid
	 * @param x
	 * @param y
	 * @param z
	 * @return storedData, may have been manipulated.
	 */
	IEffectData doEffect(IBeeGenome genome, IEffectData storedData, IBeeHousing housing);

	/**
	 * Is called to produce bee effects.
	 * 
	 * @param genome
	 * @param storedData
	 *            Object containing the stored effect data for the apiary/hive the bee is in.
	 * @param world
	 * @param biomeid
	 * @param x
	 * @param y
	 * @param z
	 * @return storedData, may have been manipulated.
	 */
	IEffectData doFX(IBeeGenome genome, IEffectData storedData, IBeeHousing housing);

	/**
	 * @return A texture file containing an icon representing the effect
	 */
	String getIconTextureFile();

	/**
	 * @return Icon index of the effect's icon, return -1 if there isn't one
	 */
	int getIconIndex();
}
