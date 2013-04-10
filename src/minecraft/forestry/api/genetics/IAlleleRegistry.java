package forestry.api.genetics;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IClassification.EnumClassLevel;

public interface IAlleleRegistry {

	/* INDIVIDUAL */

	/**
	 * Tests the itemstack for genetic information.
	 * 
	 * @param stack
	 * @return true if the itemstack is an individual.
	 */
	boolean isIndividual(ItemStack stack);

	/**
	 * Retrieve genetic information from an itemstack.
	 * 
	 * @param stack
	 *            Stack to retrieve genetic information for.
	 * @return IIndividual containing genetic information, null if none could be extracted.
	 */
	IIndividual getIndividual(ItemStack stack);

	/* ALLELES */

	/**
	 * @return HashMap of all currently registered alleles.
	 */
	LinkedHashMap<String, IAllele> getRegisteredAlleles();

	/**
	 * Registers an allele.
	 * 
	 * @param allele
	 *            IAllele to register.
	 */
	void registerAllele(IAllele allele);

	/**
	 * Gets an allele
	 * 
	 * @param uid
	 *            String based unique identifier of the allele to retrieve.
	 * @return IAllele if found, null otherwise.
	 */
	IAllele getAllele(String uid);

	/* THIS SHOULD BE PHASED OUT */
	@Deprecated
	void reloadMetaMap(World world);

	@Deprecated
	IAllele getFromMetaMap(int meta);

	@Deprecated
	int getFromUIDMap(String uid);

	/* CLASSIFICATIONS */
	/**
	 * @return HashMap of all currently registered classifications.
	 */
	LinkedHashMap<String, IClassification> getRegisteredClassifications();

	/**
	 * Registers a classification.
	 * 
	 * @param classification
	 *            IClassification to register.
	 */
	void registerClassification(IClassification classification);

	/**
	 * Creates and returns a classification.
	 * 
	 * @param level
	 *            EnumClassLevel of the classification to create.
	 * @param uid
	 *            String based unique identifier. Implementation will throw an exception if the key is already taken.
	 * @param scientific
	 *            Binomial for the given classification.
	 * @return
	 */
	IClassification createAndRegisterClassification(EnumClassLevel level, String uid, String scientific);

	/**
	 * Gets a classification.
	 * 
	 * @param uid
	 *            String based unique identifier of the classification to retrieve.
	 * @return Classification if found, null otherwise.
	 */
	IClassification getClassification(String uid);

	/* FRUIT FAMILIES */
	/**
	 * Get all registered fruit families.
	 * 
	 * @return
	 */
	LinkedHashMap<String, IFruitFamily> getRegisteredFruitFamilies();

	/**
	 * Registers a new fruit family.
	 * 
	 * @param family
	 */
	void registerFruitFamily(IFruitFamily family);

	/**
	 * Retrieves a fruit family identified by uid.
	 * 
	 * @param uid
	 * @return
	 */
	IFruitFamily getFruitFamily(String uid);

	/* ALLELE HANDLERS */
	/**
	 * Registers a new IAlleleHandler
	 * 
	 * @param handler
	 *            IAlleleHandler to register.
	 */
	void registerAlleleHandler(IAlleleHandler handler);

	/* BLACKLIST */
	/**
	 * Blacklist an allele identified by its UID from mutation.
	 * 
	 * @param uid
	 *            UID of the allele to blacklist.
	 */
	void blacklistAllele(String uid);

	/**
	 * @return Current blacklisted alleles.
	 */
	ArrayList<String> getAlleleBlacklist();

	/**
	 * @param uid
	 *            UID of the species to vet.
	 * @return true if the allele is blacklisted.
	 */
	boolean isBlacklisted(String uid);

}
