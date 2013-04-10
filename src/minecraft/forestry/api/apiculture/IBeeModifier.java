package forestry.api.apiculture;

public interface IBeeModifier {

	/**
	 * 
	 * @param genome
	 * @return Float used to modify the base territory.
	 */
	float getTerritoryModifier(IBeeGenome genome, float currentModifier);

	/**
	 * @param genome
	 * @param mate
	 * @return Float used to modify the base mutation chance.
	 */
	float getMutationModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier);

	/**
	 * @param genome
	 * @param mate
	 * @return Float used to modify the life span of queens.
	 */
	float getLifespanModifier(IBeeGenome genome, IBeeGenome mate, float currentModifier);

	/**
	 * @param genome
	 * @param mate
	 * @return Fload modifying the production speed of queens.
	 */
	float getProductionModifier(IBeeGenome genome, float currentModifier);

	/**
	 * @param genome
	 * @param mate
	 * @return Fload modifying the flowering of queens.
	 */
	float getFloweringModifier(IBeeGenome genome, float currentModifier);

	/**
	 * @return Boolean indicating if housing can ignore rain
	 */
	boolean isSealed();

	/**
	 * @return Boolean indicating if housing can ignore darkness/night
	 */
	boolean isSelfLighted();

	/**
	 * @return Boolean indicating if housing can ignore not seeing the sky
	 */
	boolean isSunlightSimulated();

	/**
	 * @return Boolean indicating whether this housing simulates the nether
	 */
	boolean isHellish();

}
