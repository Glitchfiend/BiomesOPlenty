/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import java.util.Random;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entities.EntityWasp;
import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPWhiteSandstone extends Block implements IBOPBlock
{
    
    // add properties
    public enum StoneType implements IStringSerializable
    {
        DEFAULT, CHISELED, SMOOTH;
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
    }

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", StoneType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, VARIANT);}
    
    
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
        switch ((StoneType) state.getValue(VARIANT))
        {
            case CHISELED:
                return "chiseled_white_sandstone";
            case SMOOTH:
                return "smooth_white_sandstone";
            default:
                return "white_sandstone";
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPWhiteSandstone()
    {
        super(Material.ROCK);
                
        // set some defaults
        this.setHardness(0.8F);
        this.setSoundType(SoundType.STONE);
        this.setDefaultState( this.blockState.getBaseState().withProperty(VARIANT, StoneType.DEFAULT) );
    }
    
    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return MapColor.SAND;
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, StoneType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((StoneType) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(this, 1, this.getMetaFromState(state));
    }
}
