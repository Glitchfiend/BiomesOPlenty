package forestry.api.arboriculture;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IChromosome;
import forestry.api.genetics.IIndividual;

public interface ITreeInterface {
	boolean isGermling(ItemStack itemstack);

	boolean isPollen(ItemStack itemstack);

	boolean isPollinated(ItemStack itemstack);

	ITree getTree(World world, int x, int y, int z);

	ITree getTree(ItemStack itemstack);

	ITree getTree(World world, ITreeGenome genome);

	ItemStack getGermlingStack(ITree tree, EnumGermlingType type);

	boolean plantSapling(World world, ITree tree, String owner, int x, int y, int z);

	boolean setLeaves(World world, IIndividual tree, String owner, int x, int y, int z);

	IChromosome[] templateAsChromosomes(IAllele[] template);

	IChromosome[] templateAsChromosomes(IAllele[] templateActive, IAllele[] templateInactive);

	ITreeGenome templateAsGenome(IAllele[] template);

	ITreeGenome templateAsGenome(IAllele[] templateActive, IAllele[] templateInactive);

	boolean setFruitBlock(World world, IAlleleFruit allele, float sappiness, short[] indices, int x, int y, int z);

}
