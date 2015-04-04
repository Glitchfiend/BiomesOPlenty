/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPWoodEnums.allWoods;
import biomesoplenty.api.block.BOPWoodEnums.fourWoods;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockBOPLog extends BlockLog implements IBOPBlock
{
    
    // add properties (note we inherit LOG_AXIS property from parent BlockLog)
    // LOG_AXIS requires 2 meta bits, so we have 2 left for the VARIANT, which means we can have four woods per instance
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", fourWoods.class );
    protected int pageNum;
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LOG_AXIS, VARIANT });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getRenderProperties() { return new IProperty[] {LOG_AXIS, VARIANT}; }
    @Override
    public String getStateName(IBlockState state)
    {
        allWoods wood = ((fourWoods) state.getValue(VARIANT)).map(this.pageNum);
        switch (wood)
        {
            case GIANT_FLOWER:
                return wood.getName() + "_stem";
            default:
                return wood.getName() + "_log";
        }
    }

    
    public BlockBOPLog(int pageNum)
    {
        super();
        this.pageNum = pageNum;
        this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(VARIANT, fourWoods.A));
        this.setHarvestLevel("axe", 0);
    }
    
    // map from state to meta and vice verca - use high 2 bits for LOG_AXIS, low 2 bits for VARIANT
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(VARIANT, fourWoods.values()[meta & 3]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4 + ((fourWoods) state.getValue(VARIANT)).ordinal();
    }

    // discard the axis information - otherwise logs facing different directions would not stack together
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
    }
    
    
}
