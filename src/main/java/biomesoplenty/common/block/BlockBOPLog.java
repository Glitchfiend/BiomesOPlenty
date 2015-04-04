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
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockBOPLog extends BlockLog implements IBOPBlock
{
    
    // add properties (note we inherit LOG_AXIS property from parent BlockLog)
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LOG_AXIS });}
    
    
    // implement IBOPBlock
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    public IProperty[] getPresetProperties() { return new IProperty[] {}; }
    public IProperty[] getRenderProperties() { return new IProperty[] {LOG_AXIS}; }
    public String getStateName(IBlockState state) {return "";}

    
    public BlockBOPLog()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal();
    }

    // discard the axis information - otherwise logs facing different directions would not stack together
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState( this.getDefaultState() );
    }
    
    
}
