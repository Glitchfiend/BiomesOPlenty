package biomesoplenty.common.world.generation;

import java.util.HashMap;

import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMelon;
import biomesoplenty.common.world.forcedworldgenerators.LakesForcedGenerator;
import biomesoplenty.common.world.forcedworldgenerators.MelonForcedGenerator;
import biomesoplenty.common.world.forcedworldgenerators.PondForcedGenerator;

public class ForcedBOPWorldGenerators 
{
	public static HashMap<Class, ForcedWorldGeneratorBOP> forcedGeneratorMap = new HashMap();
	
	public static void init()
	{
		addForcedGenerators();
	}
	
	private static void addForcedGenerators()
	{
		addForcedGenerator(WorldGenLiquids.class, new PondForcedGenerator());
		addForcedGenerator(WorldGenLakes.class, new LakesForcedGenerator());
		addForcedGenerator(WorldGenMelon.class, new MelonForcedGenerator());
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
