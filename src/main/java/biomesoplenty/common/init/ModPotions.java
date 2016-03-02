/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.potion.BOPPotions.paralysis;
import static biomesoplenty.api.potion.BOPPotions.possession;

import biomesoplenty.common.potion.PotionParalysis;
import biomesoplenty.common.potion.PotionPossession;
import biomesoplenty.common.util.BOPReflectionHelper;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ModPotions
{
    
    public static void init()
    {                
        // create the BOP potions
        paralysis = (new PotionParalysis(getSparePotionId(), new ResourceLocation("paralysis"), true, 16767262)).setPotionName("potion.paralysis");
        possession = (new PotionPossession(getSparePotionId(), new ResourceLocation("possession"), true, 1280)).setPotionName("potion.possession");   
    }

    // gets the next free potion id
    // will expand the potions array if necessary
    // this isn't very efficient, but it only has to run once, right at the start, so clarity and simplicity are more important than speed
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
        
        // replace Potion.potionTypes with our new expanded array
        // note - need to specify both the obfuscated and de-obfuscated field names so that it works in dev and in the normal game
        BOPReflectionHelper.setPrivateFinalValue(Potion.class, null, expandedPotionTypes, "potionTypes", "field_76425_a");

        return n;

    }
    
    
}
