package biomesoplenty.configuration;

import cpw.mods.fml.common.registry.LanguageRegistry;
import biomesoplenty.helpers.BOPLiquidHelper;
import biomesoplenty.potions.PotionEventHandler;
import biomesoplenty.potions.PotionNourishment;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;

public class BOPPotions 
{
	public static Potion nourishment;
	
	public static void init()
	{
		intializePotions();
		registerPotionNames();
		
		MinecraftForge.EVENT_BUS.register(new PotionEventHandler());
	}

	private static void intializePotions()
	{
		nourishment = (new PotionNourishment(32, false, 0)).setPotionName("potion.nourishment");
	}
	
	private static void registerPotionNames()
	{
		LanguageRegistry.instance().addStringLocalization("potion.nourishment", "en_US", "Nourishment");
	}
}
