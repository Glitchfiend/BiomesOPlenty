/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemBOPFood extends ItemFood
{
    public int eatDuration;
    
    public ItemBOPFood(int amount, float saturation, int eatDuration)
    {
        super(amount, saturation, false);
        this.eatDuration = eatDuration;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return this.eatDuration;
    }
    
}