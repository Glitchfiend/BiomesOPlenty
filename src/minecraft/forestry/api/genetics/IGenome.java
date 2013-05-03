package forestry.api.genetics;

import forestry.api.core.INBTTagable;

/**
 * Implementations other than Forestry's default one are not supported!
 */
public interface IGenome extends INBTTagable {

	IAlleleSpecies getPrimary();

	IAlleleSpecies getSecondary();

	IChromosome[] getChromosomes();

	IAllele getActiveAllele(int chromosome);

	IAllele getInactiveAllele(int chromosome);

	boolean isGeneticEqual(IGenome other);
}
