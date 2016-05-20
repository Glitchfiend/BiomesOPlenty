/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;

public class ItemBiomeEssence extends Item
{
    public ItemBiomeEssence() {}
    
    public Biome getBiome(ItemStack itemStack)
    {
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("biomeID"))
        {
            return Biome.getBiome(itemStack.getTagCompound().getInteger("biomeID"));
        }
        return null;
    }
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List infoList, boolean advancedItemTooltips) {
        Biome biome = this.getBiome(itemStack);
        if (biome != null) {
            infoList.add(biome.getBiomeName());
        }
    }
    
    @Override
    public boolean hasEffect(ItemStack itemStack)
    {
        return true;
    }

    
}