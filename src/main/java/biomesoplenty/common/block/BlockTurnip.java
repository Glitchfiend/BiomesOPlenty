/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.api.item.BOPItems;

// TODO: stop snow settling on this (floats above it)
public class BlockTurnip extends BlockCrops implements IBOPBlock
{
    
    // implement IDHBlock
    private Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return null;}

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