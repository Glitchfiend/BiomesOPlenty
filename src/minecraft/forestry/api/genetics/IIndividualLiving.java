package forestry.api.genetics;

import net.minecraft.world.World;

public interface IIndividualLiving extends IIndividual {

	/**
	 * @return Current health of the individual.
	 */
	int getHealth();

	/**
	 * @return Maximum health of the individual.
	 */
	int getMaxHealth();

	/**
	 * Age the individual.
	 * @param world
	 * @param ageModifier
	 */
	void age(World world, float ageModifier);

	/**
	 * Mate the bee with the given individual.
	 * @param drone
	 */
	void mate(IIndividual individual);

	/**
	 * @return true if the individual is among the living.
	 */
	boolean isAlive();

	/**
	 * @param chromosome
	 * @return true if both primary and secondary species allele match.
	 */
	boolean isPureBred(int chromosomeOrdinal);

}
