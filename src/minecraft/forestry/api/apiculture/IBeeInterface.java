package forestry.api.apiculture;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IChromosome;

public interface IBeeInterface {
	
	/**
	 * @return type of bee encoded on the itemstack. EnumBeeType.NONE if it isn't a bee.
	 */
	EnumBeeType getType(ItemStack stack);
	
	/**
	 * @return true if passed item is a Forestry bee. Equal to getType(ItemStack stack) != EnumBeeType.NONE
	 */
	boolean isBee(ItemStack stack);
	
	/**
	 * @return true if passed item is a drone. Equal to getType(ItemStack stack) == EnumBeeType.DRONE
	 */
	boolean isDrone(ItemStack stack);

	/**
	 * @return true if passed item is mated (i.e. a queen)
	 */
	boolean isMated(ItemStack stack);

	/**
	 * @return {@link IBee} pattern parsed from the passed stack's nbt data.
	 */
	IBee getBee(ItemStack stack);

	/**
	 * @param genome
	 *            Valid {@link IBeeGenome}
	 * @return {@link IBee} from the passed genome
	 */
	IBee getBee(World world, IBeeGenome genome);

	/**
	 * Creates an IBee suitable for a queen containing the necessary second genome for the mate.
	 * 
	 * @param genome
	 *            Valid {@link IBeeGenome}
	 * @param mate
	 *            Valid {@link IBee} representing the mate.
	 * @return Mated {@link IBee} from the passed genomes.
	 */
	IBee getBee(World world, IBeeGenome genome, IBee mate);

	/**
	 * @param bee
	 *            Bee object to use in creating the itemstack. If a queen is to be created, make sure the mate genome is set.
	 * @param type
	 *            {@link EnumBeeType} according to whether a princess, drone or queen is wanted.
	 * @return ItemStack representing a Forestry bee.
	 */
	ItemStack getBeeStack(IBee bee, EnumBeeType type);

	IChromosome[] templateAsChromosomes(IAllele[] template);

	IChromosome[] templateAsChromosomes(IAllele[] templateActive, IAllele[] templateInactive);

	IBeeGenome templateAsGenome(IAllele[] template);

	IBeeGenome templateAsGenome(IAllele[] templateActive, IAllele[] templateInactive);
}
