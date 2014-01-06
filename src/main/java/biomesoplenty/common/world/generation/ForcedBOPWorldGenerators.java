package biomesoplenty.common.world.generation;

import java.util.HashMap;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.forceddecorators.ForcedDecorator;

public class ForcedBOPWorldGenerators 
{
	public static HashMap<Class, ForcedWorldGeneratorBOP> forcedGeneratorMap = new HashMap();
	
	public static void init()
	{
		addForcedGenerators();
	}
	
	private static void addForcedGenerators()
	{
		
	}
	
	public static void addForcedGenerator(Class worldGenClass, ForcedWorldGeneratorBOP generator)
	{
		forcedGeneratorMap.put(worldGenClass, generator);
	}
	
	public static ForcedWorldGeneratorBOP getForcedGenerator(Class worldGenClass)
	{
		return forcedGeneratorMap.get(worldGenClass);
	}
	
	public static boolean hasForcedGenerator(Class worldGenClass)
	{
		return forcedGeneratorMap.containsKey(worldGenClass);
	}
}
