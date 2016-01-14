/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemDart extends Item
{
    
    public static enum DartType implements IStringSerializable
    {
        // in order of preference when selecting from inventory to use in the dart blower, items on the right are preferred to items on the left
        DART, POISONDART;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
        public float getDamageInflicted()
        {
            switch(this)
            {
                case POISONDART:
                    return 1.0F;
                case DART: default:
                    return 2.0F;
            }
        }
        public static DartType fromMeta(int meta)
        {
            return DartType.values()[meta % DartType.values().length];
        }
    };
    
    public ItemDart()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }
   
    // add all the gem types as separate items in the creative tab
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (DartType dartType : DartType.values())
        {
            subItems.add(new ItemStack(itemIn, 1, dartType.ordinal()));
        }
    }
    
    // default behavior in Item is to return 0, but the meta value is important here because it determines which dart type to use
    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }

    // get the correct name for this item by looking up the meta value in the DartType enum
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + DartType.fromMeta(stack.getMetadata()).toString();
    }
    
    
}
