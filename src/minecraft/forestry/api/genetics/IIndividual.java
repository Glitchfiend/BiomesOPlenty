package forestry.api.genetics;

import java.util.List;

import forestry.api.core.INBTTagable;

/**
 * Implementations other than Forestry's default one are not supported!
 */
public interface IIndividual extends INBTTagable {

	String getIdent();

	String getDisplayName();

	void addTooltip(List<String> list);

	/**
	 * Call to mark the IIndividual as analyzed. 
	 * @return true if the IIndividual has not been analyzed previously.
	 */
	boolean analyze();

	boolean isAnalyzed();

	boolean hasEffect();

	boolean isSecret();

	IGenome getGenome();

	/**
	 * Check whether the genetic makeup of two IIndividuals is identical. Ignores additional data like generations, irregular mating, etc..
	 * @param other
	 * @return true if the given other IIndividual has the amount of chromosomes and their alleles are identical.
	 */
	boolean isGeneticEqual(IIndividual other);

	IIndividual copy();

}
