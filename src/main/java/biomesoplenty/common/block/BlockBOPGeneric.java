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
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockBOPGeneric extends Block implements IBOPBlock
{
    
    // implement IDHBlock
    protected Map<String, IBlockState> namedStates = new HashMap<String, IBlockState>();
    public Map<String, IBlockState> getNamedStates() {return this.namedStates;}
    public IBlockState getNamedState(String name) {return this.namedStates.get(name);}
    public Class<? extends ItemBlock> getItemClass() {return ItemBOPBlock.class;}
    public int getItemRenderColor(IBlockState state, int tintIndex) {return this.getRenderColor(state);}

    
    public BlockBOPGeneric() {
        // use rock as default material
        this(Material.rock);
    }
    
    public BlockBOPGeneric(Material material)
    {
        super(material);
        // set some defaults
        this.setHardness(1.0F);
        this.setStepSound(Block.soundTypePiston);
    }
    
}