package forestry.api.apiculture;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import forestry.api.genetics.IPollinatable;

public interface IFlowerProvider {
	/**
	 * @param world
	 * @param species
	 *            Integer representing a species' ordinal matching {@EnumBeeBreed}
	 * @param x
	 * @param y
	 * @param z
	 * @return True if the block at the passed coordinates is a valid flower for the species.
	 */
	boolean isAcceptedFlower(World world, IBeeGenome genome, int x, int y, int z);

	boolean isAcceptedPollinatable(World world, IPollinatable pollinatable);

	/**
	 * @param world
	 * @param species
	 *            Integer representing a species' ordinal matching {@EnumBeeBreed}
	 * @param x
	 * @param y
	 * @param z
	 * @return True if a flower was planted.
	 */
	boolean growFlower(World world, IBeeGenome genome, int x, int y, int z);

	/**
	 * @return Short, human-readable identifier used in the beealyzer.
	 */
	String getDescription();

	ItemStack[] affectProducts(World world, IBeeGenome genome, int x, int y, int z, ItemStack[] products);

	/**
	 * @return Array of itemstacks representing valid flowers for the flower provider. The first in the array is for use as an icon Return null or an empty
	 *         array if the flower does not have an itemstack
	 */
	ItemStack[] getItemStacks();
}
