package forestry.api.genetics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISpeciesRoot {
	
	String getUID();
	
	/**
	 * @return Integer denoting the number of (counted) species of this type in the world.
	 */
	int getSpeciesCount();

	boolean isMember(ItemStack stack);

	boolean isMember(ItemStack stack, int type);
	
	boolean isMember(IIndividual individual);
	
	IIndividual getMember(ItemStack stack);
	
	ItemStack getMemberStack(IIndividual individual, int type);

	/* BREEDING TRACKER */
	IBreedingTracker getBreedingTracker(World world, String player);
	
	/* GENOME MANIPULATION */
	IIndividual templateAsIndividual(IAllele[] template);
	
	IIndividual templateAsIndividual(IAllele[] templateActive, IAllele[] templateInactive);
	
	IChromosome[] templateAsChromosomes(IAllele[] template);

	IChromosome[] templateAsChromosomes(IAllele[] templateActive, IAllele[] templateInactive);

	IGenome templateAsGenome(IAllele[] template);

	IGenome templateAsGenome(IAllele[] templateActive, IAllele[] templateInactive);

	/* TEMPLATES */
	/**
	 * Registers a bee template using the UID of the first allele as identifier.
	 * 
	 * @param template
	 */
	void registerTemplate(IAllele[] template);

	/**
	 * Registers a bee template using the passed identifier.
	 * 
	 * @param template
	 */
	void registerTemplate(String identifier, IAllele[] template);

	/**
	 * Retrieves a registered template using the passed identifier.
	 * 
	 * @param identifier
	 * @return
	 */
	IAllele[] getTemplate(String identifier);

	/**
	 * @return Default bee template for use in emergencies.
	 */
	IAllele[] getDefaultTemplate();

	IAllele[] getRandomTemplate(Random rand);

	Map<String, IAllele[]> getGenomeTemplates();
	ArrayList<? extends IIndividual> getIndividualTemplates();

	/* MUTATIONS */
	/**
	 * Use to register mutations.
	 * 
	 * @param mutation
	 */
	void registerMutation(IMutation mutation);

	/**
	 * @return All registered mutations.
	 */
	Collection<? extends IMutation> getMutations(boolean shuffle);


}
