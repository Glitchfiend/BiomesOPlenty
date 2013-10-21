package ttftcuts.atg.api;

import net.minecraft.world.World;

public class ATGAPI {

	public static boolean WorldIsATG(World world) {
		return world.provider.terrainType.getWorldTypeName() == "ATG";
	}
	
}
