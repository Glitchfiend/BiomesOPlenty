package biomesoplenty.common.core;

import static biomesoplenty.api.content.BOPCPotions.paralysis;
import static biomesoplenty.api.content.BOPCPotions.possession;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.potion.Potion;
import biomesoplenty.common.potions.PotionParalysis;
import biomesoplenty.common.potions.PotionPossession;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;


public class BOPPotions 
{
	
	public static void init()
	{
		paralysis = new PotionParalysis(getSparePotionId(), true, 16767262).setPotionName("potion.paralysis");
		possession = new PotionPossession(getSparePotionId(), true, 1280).setPotionName("potion.possession");
	}

    
    // gets the next free potion id
    // will expand the potions array if necessary
    public static int getSparePotionId()
    {
        // look for a free slot in the Potions array
        // (note we start counting from 1 - vanilla MC doens't use ID 0, nor will we)
        int n = Potion.potionTypes.length;
        for (int i = 1; i < n; i++)
        {
            if (Potion.potionTypes[i] == null)
            {
                return i;
            }
        }
        
        // if there isn't a free slot, we'll expand the array...
        
        // things go wrong if you try and have more than 128 potions
        if (n >= 128)
        {
            throw new RuntimeException("There are not enough spare potion IDs - Biomes O Plenty cannot create potions - you might have to remove some mods");
        }
        
        // create a new potionTypes array - one bigger than the previous, and copy the old potions into it
        Potion[] expandedPotionTypes = new Potion[n + 1];
        System.arraycopy(Potion.potionTypes, 0, expandedPotionTypes, 0, n);
        
        // use reflection to replace Potion.potionTypes with our new expanded array
        // note - need to specify both the obfuscated and de-obfuscated field names so that it works in dev and in the normal game        
        Field field = ReflectionHelper.findField(Potion.class, ObfuscationReflectionHelper.remapFieldNames(Potion.class.getName(), "potionTypes", "field_76425_a"));
        try
        {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, expandedPotionTypes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return n;

    }
 
}
