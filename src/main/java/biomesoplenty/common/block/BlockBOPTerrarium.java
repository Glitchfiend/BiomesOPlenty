/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;

public class BlockBOPTerrarium extends Block implements IBOPBlock
{
    
    // add properties
    public static enum TerrariumType implements IStringSerializable
    {
        FERN, MUSHROOM, CACTUS;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TerrariumType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}

    
    // implement IBOPBlock
    // need to use a custom item class because of the unique way lilies are placed
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state) {
        TerrariumType type = (TerrariumType) state.getValue(VARIANT);
        switch (type)
        {
            default:
                return "terrarium_"+type.getName();
        }
    }

    
    public BlockBOPTerrarium()
    {        
    	super(Material.glass);
    	this.setHardness(1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, TerrariumType.FERN));
        this.setBlockBoundsByRadiusAndHeight(0.8F , 0.8F);
    }
    
    public void setBlockBoundsByRadiusAndHeight(float radius, float height)
    {
        this.setBlockBoundsByRadiusAndHeight(radius, height, false);
    }
    public void setBlockBoundsByRadiusAndHeight(float radius, float height, boolean fromTop)
    {
        this.setBlockBounds(0.5F - radius, (fromTop ? 1.0F - height : 0.0F), 0.5F - radius, 0.5F + radius, (fromTop ? 1.0F : height), 0.5F + radius);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return this.getDefaultState().withProperty(VARIANT, TerrariumType.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        // only one property to worry about, the variant, so just map [0 => MEDIUM, 1 => SMALL, 2 => TINY]
        return ((TerrariumType) state.getValue(VARIANT)).ordinal();
    }
    
    // our blocks usually drop their current state as opposed to a single 'default' state
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean isFullCube()
    {
        return false;
    }
    
    @Override
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT;
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        this.setBlockBoundsByRadiusAndHeight(0.2F, 1.0F);
    }
    
}