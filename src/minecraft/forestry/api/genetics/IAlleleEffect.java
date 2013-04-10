package forestry.api.genetics;

public interface IAlleleEffect extends IAllele {
	/**
	 * @return true if this effect can combine with the effect on other allele (i.e. run before or after). combination can only occur if both effects are
	 *         combinable.
	 */
	boolean isCombinable();

	/**
	 * Returns the passed data storage if it is valid for this effect or a new one if the passed storage object was invalid for this effect.
	 * 
	 * @param storedData
	 * @return
	 */
	IEffectData validateStorage(IEffectData storedData);

	/**
	 * @return Short, human-readable identifier used in the beealyzer.
	 */
	String getIdentifier();

}
