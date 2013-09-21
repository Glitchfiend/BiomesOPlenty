package biomesoplenty.configuration;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import biomesoplenty.worldgen.structure.BOPStructureScatteredFeatureStart;
import biomesoplenty.worldgen.structure.BOPStructureVillageStart;

public class BOPStructures 
{
	public static void init()
	{
        MapGenStructureIO.func_143034_b(BOPStructureVillageStart.class, "Village");
        MapGenStructureIO.func_143034_b(BOPStructureScatteredFeatureStart.class, "Temple");
	}
}
