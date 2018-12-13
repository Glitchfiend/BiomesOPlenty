/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.block.BlockBOPSapling;
import net.minecraft.block.Block;

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
}