package forestry.api.arboriculture;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import forestry.api.genetics.IAllele;

public interface ITreeBreedingManager {

	void registerTreeTemplate(IAllele[] template);

	void registerTreeTemplate(String identifier, IAllele[] template);

	IAllele[] getTreeTemplate(String identifier);

	IAllele[] getDefaultTreeTemplate();

	/**
	 * @param world
	 * @return {@link IArboristTracker} associated with the passed world.
	 */
	IArboristTracker getArboristTracker(World world, String player);

	ArrayList<ITreekeepingMode> getTreekeepingModes();

	ITreekeepingMode getTreekeepingMode(World world);

	ITreekeepingMode getTreekeepingMode(String name);

	void registerTreekeepingMode(ITreekeepingMode mode);

	void setTreekeepingMode(World world, String name);

	IAllele[] getRandomTreeTemplate(Random rand);

}
