/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockBOPGeneric extends Block implements IBOPBlock
{
    
    // implement IBOPBlock
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    public IProperty[] getRenderProperties() { return new IProperty[] {}; }
    public String getStateName(IBlockState state) {return "";}

    
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