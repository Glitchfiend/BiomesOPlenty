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
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, name);
        potion.setRegistryName(location);
        ForgeRegistries.POTIONS.register(potion);
        return potion;
    }
}
