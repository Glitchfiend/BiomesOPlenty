package forestry.api.apiculture;

import forestry.api.genetics.IBreedingTracker;
import forestry.api.genetics.IIndividual;

/**
 * Can be used to garner information on bee breeding. See {@link IBreedingManager}
 * 
 * @author SirSengir
 */
public interface IApiaristTracker extends IBreedingTracker {

	/**
	 * Register the birth of a queen. Will mark species as discovered.
	 * 
	 * @param bee
	 *            Created queen.
	 */
	void registerQueen(IIndividual queen);

	/**
	 * @return Amount of queens bred with this tracker.
	 */
	int getQueenCount();

	/**
	 * Register the birth of a princess. Will mark species as discovered.
	 * 
	 * @param bee
	 *            Created princess.
	 */
	void registerPrincess(IIndividual princess);

	/**
	 * @return Amount of princesses bred with this tracker.
	 */
	int getPrincessCount();

	/**
	 * Register the birth of a drone. Will mark species as discovered.
	 * 
	 * @param bee
	 *            Created drone.
	 */
	void registerDrone(IIndividual drone);

	/**
	 * @return Amount of drones bred with this tracker.
	 */
	int getDroneCount();

}
