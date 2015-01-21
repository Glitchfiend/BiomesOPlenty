/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.inventory;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

//TODO: Fix icon
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
        return Item.getItemFromBlock(Blocks.sapling);
    }
}
