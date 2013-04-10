package forestry.api.apiculture;

import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.world.World;
import forestry.api.core.IStructureLogic;
import forestry.api.genetics.IAllele;

public interface IBreedingManager {

	ArrayList<IBeekeepingMode> getBeekeepingModes();

	IBeekeepingMode getBeekeepingMode(World world);

	IBeekeepingMode getBeekeepingMode(String name);

	void registerBeekeepingMode(IBeekeepingMode mode);

	void setBeekeepingMode(World world, String name);

	/**
	 * @return Integer denoting the number of (counted) bee species in the world.
	 */
	int getBeeSpeciesCount();

	/**
	 * Moved to IAlleleRegistry
	 */
	@Deprecated
	void blacklistBeeSpecies(String uid);

	/**
	 * Moved to IAlleleRegistry
	 */
	@Deprecated
	ArrayList<String> getBeeSpeciesBlacklist();

	/**
	 * Moved to IAlleleRegistry
	 */
	@Deprecated
	boolean isBlacklisted(String uid);

	/**
	 * @param housing
	 *            Object implementing IBeeHousing.
	 * @return IBeekeepingLogic
	 */
	IBeekeepingLogic createBeekeepingLogic(IBeeHousing housing);

	/**
	 * TileEntities wanting to function as alveary components need to implement structure logic for validation.
	 * 
	 * @return IStructureLogic for alvearies.
	 */
	IStructureLogic createAlvearyStructureLogic(IAlvearyComponent structure);

	/**
	 * Registers a bee template using the UID of the first allele as identifier.
	 * 
	 * @param template
	 */
	void registerBeeTemplate(IAllele[] template);

	/**
	 * Registers a bee template using the passed identifier.
	 * 
	 * @param template
	 */
	void registerBeeTemplate(String identifier, IAllele[] template);

	/**
	 * Retrieves a registered template using the passed identifier.
	 * 
	 * @param identifier
	 * @return
	 */
	IAllele[] getBeeTemplate(String identifier);

	/**
	 * @return Default bee template for use in emergencies.
	 */
	IAllele[] getDefaultBeeTemplate();

	/**
	 * @param world
	 * @return {@link IApiaristTracker} associated with the passed world.
	 */
	IApiaristTracker getApiaristTracker(World world, String player);

	/**
	 * Use to register bee mutations.
	 * 
	 * @param mutation
	 */
	void registerBeeMutation(IBeeMutation mutation);

	/**
	 * @return All registered mutations.
	 */
	Collection<IBeeMutation> getMutations(boolean shuffle);
}
