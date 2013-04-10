package forestry.api.arboriculture;

import java.util.ArrayList;

public class TreeManager {
	public static int treeSpeciesCount = 0;
	public static ITreeInterface treeInterface;
	public static ITreeBreedingManager breedingManager;

	/**
	 * List of possible mutations on fruit alleles.
	 */
	public static ArrayList<ITreeMutation> treeMutations = new ArrayList<ITreeMutation>();

}
