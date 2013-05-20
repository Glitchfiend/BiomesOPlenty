package forestry.api.arboriculture;

import net.minecraftforge.common.EnumPlantType;
import forestry.api.genetics.IFruitFamily;

public enum EnumTreeChromosome {

	/**
	 * Determines the following: - WorldGen, including the used wood blocks - {@link IFruitFamily}s supported. Limits which {@IFruitProvider}
	 * will actually yield fruit with this species. - Native {@link EnumPlantType} for this tree. Combines with the PLANT chromosome.
	 */
	SPECIES,
	/**
	 * {@link IGrowthProvider}, determines conditions required by the tree to grow.
	 */
	GROWTH,
	/**
	 * A float modifying the height of the tree. Taken into account at worldgen.
	 */
	HEIGHT,
	/**
	 * Chance for saplings.
	 */
	FERTILITY,
	/**
	 * {@link IFruitProvider}, determines if and what fruits are grown on the tree. Limited by the {@link IFruitFamily}s the species supports.
	 */
	FRUITS,
	/**
	 * Chance for fruit leaves and/or drops.
	 */
	YIELD,
	/**
	 * May add additional tolerances for {@link EnumPlantTypes}.
	 */
	PLANT,
	/**
	 * Determines the speed at which fruit will ripen on this tree.
	 */
	SAPPINESS,
	/**
	 * Territory for leaf effects. Unused.
	 */
	TERRITORY,
	/**
	 * Leaf effect. Unused.
	 */
	EFFECT,
	/**
	 * Amount of random ticks which need to elapse before a sapling will grow into a tree.
	 */
	MATURATION,

	GIRTH

}
