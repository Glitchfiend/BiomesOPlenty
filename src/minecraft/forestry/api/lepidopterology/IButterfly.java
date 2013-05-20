package forestry.api.lepidopterology;

import net.minecraft.world.World;
import forestry.api.genetics.IIndividualLiving;

public interface IButterfly extends IIndividualLiving {

	IButterflyGenome getGenome();

	float getSize();
	
	/**
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return true if the butterfly can take flight at the given location at this time. (Used to auto-spawn butterflies from dropped items.)
	 */
	boolean canTakeFlight(World world, double x, double y, double z);

	/**
	 * Create an exact copy of this butterfly.
	 */
	IButterfly copy();
}
