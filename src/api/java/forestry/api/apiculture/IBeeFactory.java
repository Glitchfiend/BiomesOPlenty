/*******************************************************************************
 * Copyright 2011-2014 SirSengir
 *
 * This work (the API) is licensed under the "MIT" License, see LICENSE.txt for details.
 ******************************************************************************/
package forestry.api.apiculture;

import forestry.api.genetics.IClassification;

public interface IBeeFactory {

	/**
	 * Creates a new bee species.
	 * Automatically registered with AlleleManager.alleleRegistry.registerAllele()
	 * See IAlleleBeeSpeciesCustom and IAlleleSpeciesCustom for adding additional properties to the returned species.
	 *
	 * @param uid Unique Identifier for this species
	 * @param dominant Whether this species is genetically dominant (false means it is recessive)
	 * @param authority Authority for the binomial name, e.g. "Sengir" on species of base Forestry.
	 * @param unlocalizedName Unlocalized name for this species
	 * @param unlocalizedDescription Unlocalized description for this species
	 * @param branch Classification of this species
	 * @param binomial Binomial name of the species sans genus ("Apis"). "humboldti" will have the bee species flavour name be "Apis humboldti". Feel free to use fun names or null.
	 * @param primaryColor The outline color of this species
	 * @param secondaryColor The body color of this species
	 * @return a new bee species allele.
	 */
	IAlleleBeeSpeciesCustom createSpecies(String uid, boolean dominant, String authority, String unlocalizedName, String unlocalizedDescription, IClassification branch, String binomial, int primaryColor, int secondaryColor);

	/**
	 * Creates a new bee branch.
	 * Must be registered with AlleleManager.alleleRegistry.getClassification("family.apidae").addMemberGroup();
	 *
	 * @param uid Unique Identifier for this branch
	 * @param scientific approximates a "genus" in real life. Real life examples: "Micrapis", "Megapis"
	 * @return a new bee branch
	 */
	IClassification createBranch(String uid, String scientific);
}
