/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.potion.BOPPotions.curse;

import biomesoplenty.common.potion.PotionCurse;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions
{
    public static void init()
    {                
        curse = registerPotion("curse", new PotionCurse().setPotionName("potion.curse"));  
    }
    
    public static Potion registerPotion(String name, Potion potion)
    {
        ResourceLocation location = new ResourceLocation(BiomesOPlenty.MOD_ID, name);
        potion.setRegistryName(location);
        ForgeRegistries.POTIONS.register(potion);
        return potion;
    }
}
