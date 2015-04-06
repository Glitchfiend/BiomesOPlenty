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
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;

//TODO: Add gem ore drops, make gem item seperate
public class BlockGem extends Block implements IBOPBlock
{
    // add properties
    public static enum GemType implements IStringSerializable
    {
        AMETHYST, RUBY, PERIDOT, TOPAZ, TANZANITE, MALACHITE, SAPPHIRE, AMBER;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", GemType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { VARIANT });}
 
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public int getItemRenderColor(IBlockState state, int tintIndex) { return this.getRenderColor(state); }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return ((GemType) state.getValue(VARIANT)).getName() + "_block";
    }
    

    public BlockGem()
    {        
        super(Material.rock);
        
        // set some defaults
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, GemType.AMETHYST) );        
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, GemType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((GemType) state.getValue(VARIANT)).ordinal();
    }
    

}
