/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.potion.BOPPotions.*;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import biomesoplenty.common.potion.*;
import biomesoplenty.common.util.BOPReflectionHelper;

public class ModPotions
{
    private static int lastId;
    
    public static void init()
    {
        lastId = Potion.potionTypes.length;
        
        // Need to expand the potions array to hold all our potions
        // Irritatingly this has to be done before creating the potion instances, so it can't just be done dynamically
        // ...anyway, increase the number below for every new potion you add
        int numberOfPotionsToAdd = 2;
        expandPotionsArray(numberOfPotionsToAdd);
        
        // public static final Potion moveSpeed = (new Potion(1, new ResourceLocation("speed"), false, 8171462)).setPotionName("potion.moveSpeed").setIconIndex(0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
        
        // create the BOP potions
        paralysis = (new PotionParalysis(getNextId(), new ResourceLocation("paralysis"), true, 16767262)).setPotionName("potion.paralysis");
        possession = (new PotionPossession(getNextId(), new ResourceLocation("possession"), true, 1280)).setPotionName("potion.possession");        
        
    }
    
    private static void expandPotionsArray(int numberOfPotionsToAdd)
    {
        int oldNumOfPotions = Potion.potionTypes.length;
        
        // create a new potionTypes array, big enough to hold the old potions and the new BOP ones
        Potion[] potionTypes = new Potion[oldNumOfPotions + numberOfPotionsToAdd];
        
        // copy the old potions into the array
        System.arraycopy(Potion.potionTypes, 0, potionTypes, 0, oldNumOfPotions);
        
        // replace Potion.potionTypes with our new expanded array
        BOPReflectionHelper.setPrivateFinalValue(Potion.class, null, potionTypes, "potionTypes" /* TODO: what's this? "field_76425_a" */);
    }
    
    private static int getNextId()
    {
        return lastId++;
    }
    
    
}
