/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.inventory;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabBOP extends CreativeTabs
{
    public static final CreativeTabs instance = new CreativeTabBOP(CreativeTabs.getNextID(), "tabBiomesOPlenty");

    private CreativeTabBOP(int index, String label)
    {
        super(index, label);
    }

    @Override
    public Item getTabIconItem()
    {
        return BOPItems.earth;
    }
}
