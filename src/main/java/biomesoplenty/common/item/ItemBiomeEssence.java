/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// TODO: This is probably all kinds of wrong
public class ItemBiomeEssence extends Item
{
    public ItemBiomeEssence() {}
    
    @Override
    public boolean hasContainerItem(ItemStack itemStack)
    {
        return true;
    }
    
    // TODO: wtf?
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return itemStack;
    }
    
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List infoList, boolean advancedItemTooltips)
    {
        if (itemStack.hasTagCompound())
        {
            if (itemStack.getTagCompound().hasKey("biomeID")) 
            {
                BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[itemStack.getTagCompound().getInteger("biomeID")];

                if (biome != null)
                {
                    infoList.add(biome.biomeName);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int par2)
    {
        if (itemStack.hasTagCompound())
        {
            if (itemStack.getTagCompound().hasKey("biomeID"))
            {
                BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[itemStack.getTagCompound().getInteger("biomeID")];
                
                if (biome != null)
                {
                    return biome.color;
                }
            }
        }
        
        return 16777215;
    }
    
    @Override
    public boolean hasEffect(ItemStack itemStack)
    {
        return true;
    }

    
}