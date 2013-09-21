package biomesoplenty.configuration;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import biomesoplenty.worldgen.structure.BOPStructureScatteredFeatureStart;

public class BOPStructures 
{
	public static void init()
	{
        MapGenStructureIO.func_143034_b(BOPStructureScatteredFeatureStart.class, "BOPTemple");
	}
}
