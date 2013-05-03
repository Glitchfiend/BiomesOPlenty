package forestry.api.apiculture;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;

/**
 * Other implementations than Forestry's default one are not supported.
 * 
 * @author SirSengir
 */
public interface IBee extends IIndividual {

	/**
	 * @return true if the individual is originally of natural origin.
	 */
	boolean isNatural();

	/**
	 * @return generation this individual is removed from the original individual.
	 */
	int getGeneration();

	IBee setNatural(boolean flag);

	boolean isIrregularMating();

	void age(World world, float ageModifier);

	void mate(IBee drone);

	void setIsNatural(boolean flag);

	IEffectData[] doEffect(IEffectData[] storedData, IBeeHousing housing);

	IEffectData[] doFX(IEffectData[] storedData, IBeeHousing housing);

	boolean isAlive();

	boolean isPureBred(EnumBeeChromosome chromosome);

	/**
	 * @return true if the bee may spawn offspring
	 */
	boolean canSpawn();

	/**
	 * Determines whether the queen can work.
	 * 
	 * @param world
	 * @param isAlveary
	 * @param biomeid
	 * @param temperature
	 * @param humidity
	 * @param x
	 * @param y
	 * @param z
	 * @return Ordinal of the error code encountered. 0 - EnumErrorCode.OK
	 */
	int isWorking(IBeeHousing housing);

	boolean hasFlower(IBeeHousing housing);

	ArrayList<Integer> getSuitableBiomeIds();

	ItemStack[] getProduceList();

	ItemStack[] getSpecialtyList();

	ItemStack[] produceStacks(IBeeHousing housing);

	IBee spawnPrincess(IBeeHousing housing);

	IBee[] spawnDrones(IBeeHousing housing);

	void plantFlowerRandom(IBeeHousing housing);

	int getHealth();

	int getMaxHealth();

	IBeeGenome getGenome();

	IBeeGenome getMate();

	IIndividual retrievePollen(IBeeHousing housing);

	boolean pollinateRandom(IBeeHousing housing, IIndividual pollen);

}
