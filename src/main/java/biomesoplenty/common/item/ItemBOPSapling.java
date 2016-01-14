/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.enums.BOPTrees;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBOPSapling extends ItemBOPBlock
{
    
    public BlockBOPSapling saplingBlock;

    public ItemBOPSapling(Block block) {
        super(block);
        if (!(block instanceof BlockBOPSapling))
        {
            throw new IllegalArgumentException("ItemBOPSapling must be created with a BlockBOPSapling block");
        }
        this.saplingBlock = (BlockBOPSapling)block;
    }
    
    @Override
    public boolean hasEffect(ItemStack stack)
    {
        BOPTrees tree = BlockBOPSapling.paging.getVariant(this.saplingBlock, stack.getMetadata());
        switch (tree)
        {
            case SACRED_OAK:
                return true;
            default:
                return super.hasEffect(stack); 
        }
    }
    
}