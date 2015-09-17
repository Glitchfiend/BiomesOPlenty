package forestry.api.genetics;

/**
 * Creates new alleles with smart localization.
 *
 *
 * UID is created like this:
 * 	modId + '.' + category + WordUtils.capitalize(valueName);
 * For Example:
 * 	modId:forestry, category:height, valueName:smallest => forestry.heightSmallest
 * This is mainly for legacy compatibility and may change in future major versions.
 *
 *
 * The default localization uses:
 * [modId].allele.[valueName]
 *
 * Languages that need category-specific names can override it by defining:
 * [modId].allele.[category].[valueName]
 *
 * For example:
 * en_US
 * 	forestry.allele.smallest=Smallest
 * ru_RU
 * 	forestry.allele.smallest=????? ?????????
 * 	forestry.allele.height.smallest=????? ??????
 */
public interface IAlleleFactory {
	/**
	 * @param modId mod prefix for uid and localization (i.e. "forestry")
	 * @param category allele category for uid and localization (i.e. "height")
	 * @param valueName allele value name for uid and localization (i.e. "smallest")
	 * @param value allele float value
	 * @param isDominant allele dominance
	 * @return a new IAlleleFloat
	 */
	IAlleleFloat createFloat(String modId, String category, String valueName, float value, boolean isDominant);

	/**
	 * @param modId mod prefix for uid and localization (i.e. "forestry")
	 * @param category allele category for uid and localization (i.e. "territory")
	 * @param valueName allele value name for uid and localization (i.e. "large")
	 * @param xDimValue allele area X Size
	 * @param yDimValue allele area Y Size
	 * @param zDimValue	allele area Z Size
	 * @param isDominant allele dominance
	 * @return a new IAlleleArea
	 */
	IAlleleArea createArea(String modId, String category, String valueName, int xDimValue, int yDimValue, int zDimValue, boolean isDominant);

	/**
	 * @param modId mod prefix for uid and localization (i.e. "forestry")
	 * @param category allele category for uid and localization (i.e. "fertility")
	 * @param valueName allele value name for uid and localization (i.e. "low")
	 * @param value allele int value
	 * @param isDominant allele dominance
	 * @return a new IAlleleInteger
	 */
	IAlleleInteger createInteger(String modId, String category, String valueName, int value, boolean isDominant);

	/**
	 * @param modId mod prefix for uid and localization (i.e. "forestry")
	 * @param category allele category for uid and localization (i.e. "fireproof")
	 * @param value allele boolean value
	 * @param isDominant allele dominance
	 * @return a new IAlleleBoolean
	 * Note that valueName will always be "true" or "false"
	 */
	IAlleleBoolean createBoolean(String modId, String category, boolean value, boolean isDominant);

	/**
	 * @param modId mod prefix for uid (i.e. "forestry")
	 * @param category allele category for uid (i.e. "flowers")
	 * @param valueName allele value name for uid (i.e. "vanilla")
	 * @param value allele IFlowerProvider value
	 * @param isDominant allele dominance
	 * @return a new IAlleleFlowers
	 * IAlleleFlowers localization is handled by the IFlowerProvider.getDescription(), unlike the other alleles.
	 */
	IAlleleFlowers createFlowers(String modId, String category, String valueName, IFlowerProvider value, boolean isDominant);
}
