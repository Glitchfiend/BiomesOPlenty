/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPLilypad;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;

public class BlockBOPLilypad extends BlockLilyPad implements IBOPBlock
{
    
    // add properties
    public static enum LilypadType implements IStringSerializable
    {
        MEDIUM, SMALL, TINY, DUCKWEED;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    };
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", LilypadType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}

    
    // implement IBOPBlock
    // need to use a custom item class because of the unique way lilies are placed
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPLilypad.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {
        LilypadType type = (LilypadType) state.getValue(VARIANT);
        switch (type)
        {
            case DUCKWEED:
                return type.getName();
            default:
                return "lily_"+type.getName();
        }
    }

    
    public BlockBOPLilypad()
    {        
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, LilypadType.MEDIUM));
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return this.getDefaultState().withProperty(VARIANT, LilypadType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return ((LilypadType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
    {
        return net.minecraftforge.common.EnumPlantType.Water;
    }
    
}