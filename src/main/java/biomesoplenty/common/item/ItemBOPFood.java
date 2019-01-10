/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.item;

import biomesoplenty.common.util.inventory.ItemGroupBOP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemBOPFood extends ItemFood
{
    public int eatDuration;
    
    public ItemBOPFood(int amount, float saturation, int eatDuration)
    {
        super(amount, saturation, false, new Item.Builder().group(ItemGroupBOP.instance));
        this.eatDuration = eatDuration;
    }
    
    @Override
    public int getUseDuration(ItemStack stack)
    {
        return this.eatDuration;
    }
}
