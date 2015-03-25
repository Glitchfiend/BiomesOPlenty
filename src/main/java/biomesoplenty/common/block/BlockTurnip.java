/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.item.Item;
import biomesoplenty.api.block.BOPCrops;
import biomesoplenty.api.item.BOPItems;

public class BlockTurnip extends BOPCrops
{
    
    public BlockTurnip()
    {
        this.setCreativeTab(null);
    }

    @Override
    protected Item getSeed()
    {
        return BOPItems.turnip_seeds;
    }
    
    @Override
    protected Item getCrop()
    {
        return BOPItems.turnip;
    }

}    