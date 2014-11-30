/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWithVariants extends ItemBlock
{
    public ItemBlockWithVariants(Block block)
    {
	    super(block);
	    
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
    	BOPBlock bopBlock = (BOPBlock)this.block;
    	
    	if (bopBlock.hasVariants())
    	{
    		return super.getUnlocalizedName() + "." + bopBlock.getVariantFromMeta(stack.getMetadata()).getName();
    	}
    	else return super.getUnlocalizedName();
    }
}
