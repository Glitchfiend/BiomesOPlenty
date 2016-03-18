/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;

public class BlockBOPGem extends Block implements IBOPBlock
{
    // add properties
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BOPGems.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { VARIANT });}
 
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((BOPGems) state.getValue(VARIANT)).getName() + "_block";
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPGem()
    {        
        super(Material.rock);
        
        // set some defaults
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setHarvestLevel("pickaxe", 2);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, BOPGems.AMETHYST) );        
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BOPGems.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BOPGems) state.getValue(VARIANT)).ordinal();
    }
    
    // our blocks usually drop their current state as opposed to a single 'default' state
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }
}
