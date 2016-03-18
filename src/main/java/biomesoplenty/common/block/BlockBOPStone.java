/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPBlock;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockBOPStone extends Block implements IBOPBlock
{
    
    // add properties
    public static enum StoneType implements IStringSerializable
    {
        LIMESTONE, SILTSTONE, SHALE;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", StoneType.class);
    public static PropertyBool POLISHED = PropertyBool.create("polished");
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { VARIANT, POLISHED });}
    
    
    // implement IBOPBlock
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT, POLISHED}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return null; }
    @Override
    public String getStateName(IBlockState state)
    {
        return (Boolean.TRUE.equals(state.getValue(POLISHED)) ? "polished_" : "") + ((StoneType) state.getValue(VARIANT)).getName();
    }
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPStone()
    {
        super(Material.rock);

        // set some defaults
        this.setSoundType(SoundType.STONE);
        this.setHarvestLevel("pickaxe", 1, this.getDefaultState().withProperty(VARIANT, StoneType.LIMESTONE));
        this.setHarvestLevel("pickaxe", 2, this.getDefaultState().withProperty(VARIANT, StoneType.SILTSTONE));
        this.setHarvestLevel("pickaxe", 3, this.getDefaultState().withProperty(VARIANT, StoneType.SHALE));
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, StoneType.LIMESTONE).withProperty(POLISHED, Boolean.valueOf(false)) );
        
    }

    // TODO: can we get rid of these and just use a single hardness / resistance value?
    // These don't work completely as expected - sometimes the block at pos is not this (when destroyed it becomes air for example)
    @Override
    public float getBlockHardness(IBlockState state, World world, BlockPos pos)
    {
        return (state.getBlock() == this && (Boolean)state.getValue(POLISHED)) ? 1.5F : 3.0F;
    }
    @Override
    public float getExplosionResistance(World world, BlockPos pos, Entity exploder, Explosion explosion)
    {
        IBlockState state = world.getBlockState(pos);
        return (state.getBlock() == this && (Boolean)state.getValue(POLISHED)) ? 7.0F : 5.0F;        
    }

    // map from state to meta and vice verca - use highest bit for polished boolean, use low 2 bits for variant
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        boolean polished = (meta & 8) > 0;
        int type = meta & 3;
        return this.getDefaultState().withProperty(VARIANT, StoneType.values()[type]).withProperty(POLISHED, Boolean.valueOf(polished));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int type = ((StoneType) state.getValue(VARIANT)).ordinal();
        boolean polished = (Boolean) state.getValue(POLISHED);
        return type + (polished ? 8 : 0);
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(state);
    }

}