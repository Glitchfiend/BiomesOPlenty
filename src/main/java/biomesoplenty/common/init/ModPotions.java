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
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class ModPotions
{
    
    public static void init()
    {                
        // create the BOP potions
        paralysis = registerPotion("paralysis", new PotionParalysis(true, 16767262)).setPotionName("potion.paralysis");
        possession = registerPotion("possession", new PotionPossession(true, 1280)).setPotionName("potion.possession");   
    }
    
    public static Potion registerPotion(String name, Potion potion)
    {
        Potion.potionRegistry.register(getSparePotionId(), new ResourceLocation(BiomesOPlenty.MOD_ID, name), potion);
        return potion;
    }
    

    // gets the next free potion id
    // will expand the potions array if necessary
    // this isn't very efficient, but it only has to run once, right at the start, so clarity and simplicity are more important than speed
    public static int getSparePotionId()
    {
        int nextId = 1;
        
        // look for a free slot in the Potions array
        // (note we start counting from 1 - vanilla MC doens't use ID 0, nor will we)
        for (; Potion.potionRegistry.getObjectById(nextId) != null; nextId++) {}

        return nextId;
    }
    
}
