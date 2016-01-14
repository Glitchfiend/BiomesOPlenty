/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import biomesoplenty.common.enums.BOPGems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGem extends Item
{

    public ItemGem()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    // add all the gem types as separate items in the creative tab
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (BOPGems gemType : BOPGems.values())
        {
            subItems.add(new ItemStack(itemIn, 1, gemType.ordinal()));
        }        
    }

    // default behavior in Item is to return 0, but the meta value is important here because it determines which gem to use
    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    // get the correct name for this item by looking up the meta value in the BlockGem.GemType enum
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        String gemName;
        int meta = stack.getMetadata();
        try {
            gemName = BOPGems.values()[meta].getName();
        } catch (Exception e) {
            // if lookup fails for whatever reason, just use the meta number
            gemName = Integer.toString(meta);
        }
        return super.getUnlocalizedName() + "_" + gemName;
    }
    
    
}
