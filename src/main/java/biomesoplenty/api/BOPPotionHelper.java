package biomesoplenty.api;

import java.util.HashMap;

import net.minecraft.potion.Potion;

public class BOPPotionHelper 
{
	public static HashMap<String, Potion> potionList = new HashMap<String, Potion>();
	
	public static void registerPotion(Potion potion, String name)
	{
		potionList.put(name, potion);
	}
	
	public static Potion get(String name)
	{
		return potionList.get(name);
	}
}
