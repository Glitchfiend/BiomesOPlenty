/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.enums.BOPFlowers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBOPFlower extends ItemBOPBlock {

    public ItemBOPFlower(Block block) {
        super(block);
    }
    
    public BOPFlowers getFlower(ItemStack stack)
    {
        if (! (this.block instanceof BlockBOPFlower)) {return null;}
        return BlockBOPFlower.paging.getVariant((BlockBOPFlower)this.block, stack.getMetadata());
    }

}