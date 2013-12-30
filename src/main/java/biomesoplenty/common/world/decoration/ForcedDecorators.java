package biomesoplenty.common.world.decoration;

import java.util.HashMap;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.forceddecorators.SwampForcedDecorator;

public class ForcedDecorators 
{
	public static HashMap<Integer, IBOPDecoration> forcedDecoratorMap = new HashMap();
	
	public static void init()
	{
		addForcedDecorators();
	}
	
	private static void addForcedDecorators()
	{
		addForcedDecorator(BiomeGenBase.swampland.biomeID, new SwampForcedDecorator());
	}
	
	public static void addForcedDecorator(int biomeID, IBOPDecoration decorator)
	{
		forcedDecoratorMap.put(biomeID, decorator);
	}
	
	public static IBOPDecoration getForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.get(biomeID);
	}
	
	public static boolean biomeHasForcedDecorator(int biomeID)
	{
		return forcedDecoratorMap.containsKey(biomeID);
	}
}
