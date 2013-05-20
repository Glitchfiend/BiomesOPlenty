package forestry.api.lepidopterology;

import forestry.api.genetics.IAlleleEffect;
import forestry.api.genetics.IEffectData;

public interface IAlleleButterflyEffect extends IAlleleEffect {

	/**
	 * Used by butterflies to trigger effects in the world.
	 * @param butterfly {@link IEntityButterfly}
	 * @param entity
	 * @param storedData
	 * @return
	 */
	IEffectData doEffect(IEntityButterfly butterfly, IEffectData storedData);

}
