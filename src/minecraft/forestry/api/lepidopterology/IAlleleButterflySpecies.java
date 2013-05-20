package forestry.api.lepidopterology;

import forestry.api.genetics.IAlleleSpecies;

public interface IAlleleButterflySpecies extends IAlleleSpecies {
	
	/**
	 * @return Texture to be used for the Entity of this species.
	 */
	String getEntityTexture();
	
	/**
	 * @return Float between 0 and 1 representing the rarity of the species, will affect spawn rate.
	 */
	float getRarity();
	
	/**
	 * @return true if this species is only active at night.
	 */
	boolean isNocturnal();
}
