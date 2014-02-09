package biomesoplenty.common.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPPotionHelper;
import biomesoplenty.common.helpers.BOPReflectionHelper;
import biomesoplenty.common.potions.PotionParalysis;
import biomesoplenty.common.potions.PotionPossession;
import cpw.mods.fml.common.FMLCommonHandler;

public class BOPPotions 
{
	public static int potionOffset;
	private static final int MAXNEWPOTIONS = 8;

	public static void init()
	{
		extendPotionsArray();
		intializePotions();
	}
	
	private static void extendPotionsArray()
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Extending Potions Array.");
		potionOffset = Potion.potionTypes.length;
		
		Potion[] potionTypes = new Potion[potionOffset + MAXNEWPOTIONS];
		System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, potionOffset);

		BOPReflectionHelper.setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes", "field_76425_a");
	}

	private static void intializePotions()
	{
		registerPotion(new PotionParalysis(getNextID(), true, 16767262).setPotionName("potion.paralysis"));
		registerPotion(new PotionPossession(getNextID(), true, 1280).setPotionName("potion.possession"));
	}
	
	public static int getNextID()
	{
		return potionOffset++ - 1;
	}
	
	public static void registerPotion(Potion potion)
	{
		BOPPotionHelper.registerPotion(potion, potion.getName().replace("potion.", ""));
	}
}
