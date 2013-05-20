package forestry.api.genetics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.IIconProvider;

public interface IAlleleSpecies extends IAllele {
	
	/**
	 * @return the {@link ISpeciesRoot} associated with this species.
	 */
	ISpeciesRoot getRoot();
	
	/**
	 * @return Localized short, human-readable identifier used in tooltips and beealyzer.
	 */
	String getName();

	/**
	 * @return Localized short description of this species. (May be null.)
	 */
	String getDescription();

	/**
	 * @return Preferred temperature
	 */
	EnumTemperature getTemperature();

	/**
	 * @return Preferred humidity
	 */
	EnumHumidity getHumidity();

	/**
	 * @return true if the species icon should have a glowing effect.
	 */
	boolean hasEffect();

	/**
	 * @return true if the species should not be displayed in NEI or creative inventory.
	 */
	boolean isSecret();

	/**
	 * @return true to have the species count against the species total.
	 */
	boolean isCounted();

	/**
	 * Binomial name of the species sans genus ("Apis"). Returning "humboldti" will have the bee species flavour name be "Apis humboldti". Feel free to use fun
	 * names or return null.
	 * 
	 * @return flavour text (may be null)
	 */
	String getBinomial();

	/**
	 * Authority for the binomial name, e.g. "Sengir" on species of base Forestry.
	 * 
	 * @return flavour text (may be null)
	 */
	String getAuthority();

	/**
	 * @return Branch this species is associated with.
	 */
	IClassification getBranch();

	@SideOnly(Side.CLIENT)
	IIconProvider getIconProvider();

}
