package biomesoplenty.common.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPPotionHelper;
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

	private static void intializePotions()
	{
		registerPotion(new PotionParalysis(getNextID(), true, 16767262).setPotionName("potion.paralysis"));
		registerPotion(new PotionPossession(getNextID(), true, 1280).setPotionName("potion.possession"));
	}

	private static void extendPotionsArray()
	{
		FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Extending Potions Array.");
		potionOffset = Potion.potionTypes.length;

		Potion[] potionTypes = new Potion[potionOffset + MAXNEWPOTIONS];
		System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, potionOffset);

		Field field = null;
		Field[] fields = Potion.class.getDeclaredFields();

		for (Field f : fields)
		{
			if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a"))
			{
				field = f;
				break;
			}
		}

		try
		{
			field.setAccessible(true);

			Field modfield = Field.class.getDeclaredField("modifiers");
			modfield.setAccessible(true);
			modfield.setInt(field, field.getModifiers() & ~Modifier.FINAL);

			field.set(null, potionTypes);
		}
		catch (Exception e)
		{
			System.err.println("[BiomesOPlenty] Severe error, please report this to the mod author:");
			System.err.println(e);
		}
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
