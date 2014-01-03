package biomesoplenty.common.world;

import java.util.HashMap;
import java.util.Set;

import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.features.WorldGenBOPFlora;
import biomesoplenty.common.world.features.WorldGenMud;
import biomesoplenty.common.world.features.WorldGenRiverCane;

public class WorldGenFieldAssociation 
{
	public static HashMap<String, WorldGenerator> worldGeneratorMap = new HashMap();
	
	public static void init()
	{
		associateFieldsWithGenerators();
	}
	
	private static void associateFieldsWithGenerators()
	{
		associateField("mudPerChunk", new WorldGenMud(7));
		associateField("riverCanePerChunk", new WorldGenRiverCane());
		associateField("shrubsPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 9));
		associateField("bushesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 4));
		associateField("cloverPatchesPerChunk", new WorldGenBOPFlora(BOPBlockHelper.get("foliage"), 13, 128));
	}
	
	public static void associateField(String fieldName, WorldGenerator generator)
	{
		worldGeneratorMap.put(fieldName, generator);
	}
	
	public static WorldGenerator getAssociatedWorldGenerator(String fieldName)
	{
		return worldGeneratorMap.get(fieldName);
	}
}
