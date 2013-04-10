package forestry.api.arboriculture;

import net.minecraft.world.World;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutation;

public interface ITreeMutation extends IMutation {
	int getChance(World world, int x, int y, int z, IAllele allele0, IAllele allele1, IGenome genome0, IGenome genome1);
}
