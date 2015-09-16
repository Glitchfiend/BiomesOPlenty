/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 * 
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

import forestry.api.core.IErrorState;
import forestry.api.genetics.IEffectData;
import forestry.api.genetics.IIndividual;
import forestry.api.genetics.IIndividualLiving;

/**
 * Other implementations than Forestry's default one are not supported.
 * 
 * @author SirSengir
 */
public interface IBee extends IIndividualLiving {

	/**
	 * @return Bee's genetic information.
	 */
	IBeeGenome getGenome();

	/**
	 * @return Genetic information of the bee's mate, null if unmated.
	 */
	IBeeGenome getMate();

	/**
	 * @return true if the individual is originally of natural origin.
	 */
	boolean isNatural();

	/**
	 * @return generation this individual is removed from the original individual.
	 */
	int getGeneration();

	/**
	 * Set the natural flag on this bee.
	 * @param flag
	 */
	void setIsNatural(boolean flag);

	IEffectData[] doEffect(IEffectData[] storedData, IBeeHousing housing);

	IEffectData[] doFX(IEffectData[] storedData, IBeeHousing housing);

	/**
	 * @return true if the bee may spawn offspring
	 */
	boolean canSpawn();

	/**
	 * Determines whether the queen can work.
	 * @param housing the {@link IBeeHousing} the bee currently resides in.
	 * @return an empty set if the queen can work, a set of error states if the queen can not work
	 */
	Set<IErrorState> getCanWork(IBeeHousing housing);

	boolean hasFlower(IBeeHousing housing);

	ArrayList<BiomeGenBase> getSuitableBiomes();

	ItemStack[] getProduceList();

	ItemStack[] getSpecialtyList();

	ItemStack[] produceStacks(IBeeHousing housing);

	IBee spawnPrincess(IBeeHousing housing);

	IBee[] spawnDrones(IBeeHousing housing);

	void plantFlowerRandom(IBeeHousing housing);

	IIndividual retrievePollen(IBeeHousing housing);

	boolean pollinateRandom(IBeeHousing housing, IIndividual pollen);

	/**
	 * Determines whether the queen can work.
	 *
	 * @param housing the {@link IBeeHousing} the bee currently resides in.
	 * @return the error code encountered.
	 * @deprecated since Forestry 3.6. Use getCanWork
	 */
	@Deprecated
	IErrorState canWork(IBeeHousing housing);

}
