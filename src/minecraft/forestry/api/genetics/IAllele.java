package forestry.api.genetics;

/**
 * Should be extended for different types of alleles. ISpeciesAllele, IBiomeAllele, etc.
 */
public interface IAllele {

	/**
	 * @return A unique string identifier for this allele.
	 */
	String getUID();

	/**
	 * @return true if the allele is dominant, false otherwise.
	 */
	boolean isDominant();
}
