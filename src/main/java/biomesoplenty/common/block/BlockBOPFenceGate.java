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

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

//TODO:  work out how statemapper works, use it to ignore the powered property when generating models
//see: package net.minecraft.client.renderer BlockModelShapes registerAllBlocks 
//this kind of thing going on:   this.registerBlockWithStateMapper(Blocks.oak_fence_gate, (new StateMap.Builder()).addPropertiesToIgnore(new IProperty[] {BlockFenceGate.POWERED}).build());
public class BlockBOPFenceGate extends BlockFenceGate implements IBOPBlock
{
  
    // implement IDHBlock
    protected Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
    public int getItemRenderColor(IBlockState state, int tintIndex) {return this.getRenderColor(state);}

    
    public BlockBOPFenceGate()
    {
        super();
    }
    
}
    