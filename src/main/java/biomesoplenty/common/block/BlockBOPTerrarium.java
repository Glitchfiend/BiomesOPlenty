/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.common.item.ItemBOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

public class BlockBOPTerrarium extends Block implements IBOPBlock
{
	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.75D, 0.75D);
    
    // add properties
    public enum TerrariumType implements IStringSerializable
    {
        FERN, MUSHROOM, CACTUS, FLAX, FLOWER, KORU, BAMBOO, BEACH, GLOWSHROOM, DEAD, MYSTIC, OMINOUS, WASTELAND, ORIGIN, NETHER, ENDER;
        @Override
        public String getName()
        {
            return this.name().toLowerCase(Locale.ENGLISH);
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    }

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", TerrariumType.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, VARIANT);}

    
    // implement IBOPBlock
    // need to use a custom item class because of the unique way lilies are placed
    @Override
    public Class<? extends ItemBlock> getItemClass() { return ItemBOPBlock.class; }
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
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockColor getBlockColor() { return null; }
    @Override
    @SideOnly(Side.CLIENT)
    public IItemColor getItemColor() { return null; }
    
    public BlockBOPTerrarium()
    {        
    	super(Material.GLASS);
    	this.setHardness(1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, TerrariumType.FERN));
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOX;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return BOUNDING_BOX;
    }
    
    @Override
    public int getLightValue(IBlockState state)
    {
        switch ((TerrariumType) state.getValue(VARIANT))
        {
            case GLOWSHROOM:
                return 5;

            case MYSTIC:
                return 8;

            case NETHER:
                return 8;

            default:
                return 0;
        }
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
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos) && worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos, EnumFacing.UP);
    }
    
    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.CENTER;
    }
}