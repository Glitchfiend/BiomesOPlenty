package biomesoplenty.api;

import net.minecraft.entity.Entity;
import net.minecraft.world.biome.BiomeGenBase;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;

public class Entities {
	
	public static Class Mudball = getClass("biomesoplenty.items.projectiles.EntityMudball");
	public static Class JungleSpider = getClass("biomesoplenty.mobs.EntityJungleSpider");
	public static Class Rosester = getClass("biomesoplenty.mobs.EntityRosester");
	
	public static Class getClass(String inputstring)
	{
		Class foundclass = null;
		try 
		{
			foundclass = Class.forName(inputstring);
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		return foundclass;
	}
}
