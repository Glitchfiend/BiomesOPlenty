/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.inventory;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupBOP extends ItemGroup
{
    public static final ItemGroupBOP instance = new ItemGroupBOP(ItemGroup.TABS.length, "biomesoplenty");

    private ItemGroupBOP(int index, String label)
    {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return new ItemStack(BOPItems.bop_icon);
    }
}
