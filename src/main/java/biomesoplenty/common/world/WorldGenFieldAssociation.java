package biomesoplenty.common.world;

import java.util.HashMap;
import java.util.Set;

import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.generators.WorldGenMud;

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
	}
	
	public static void associateField(String fieldName, WorldGenerator generator)
	{
		worldGeneratorMap.put(fieldName, generator);
	}
	
	public static WorldGenerator getAssociatedWorldGenerator(String fieldName)
	{
		return worldGeneratorMap.get(fieldName);
	}
	
	public static Set<String> getDeclaredFields()
	{
		return worldGeneratorMap.keySet();
	}
}
