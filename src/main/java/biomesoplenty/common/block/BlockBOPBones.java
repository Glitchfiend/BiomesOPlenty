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
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBOPBones extends Block implements IBOPBlock
{
    
    // add properties
    public static enum BoneType implements IStringSerializable
    {
        SMALL, MEDIUM, LARGE;
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
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BoneType.class);
    public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);
    @Override
    protected BlockStateContainer createBlockState() {return new BlockStateContainer(this, new IProperty[] { AXIS, VARIANT });}
    @Override
    public IBlockColor getBlockColor() { return null; }
    @Override
    public IItemColor getItemColor() { return null; }
    
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
        return ((BoneType) state.getValue(VARIANT)).getName() + "_bone_segment";
    }
    
    public ItemStack getVariantItem(BoneType type)
    {
        return this.getVariantItem(type, 1);
    }
    public ItemStack getVariantItem(BoneType type, int howMany)
    {
        return new ItemStack(this, howMany, this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, type)));
    }
    
    public BlockBOPBones()
    {
        super(Material.rock);
        
        // set some defaults
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.STONE);
     
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(VARIANT, BoneType.LARGE));
    }
    
    
    // map from state to meta and vice verca 
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        int axis = meta % 3;
        int type = (meta - axis) / 3;
        return this.getDefaultState().withProperty(VARIANT, BoneType.values()[type]).withProperty(AXIS, EnumFacing.Axis.values()[axis]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int baseMeta = ((BoneType) state.getValue(VARIANT)).ordinal();
        return baseMeta * 3 + ((EnumFacing.Axis) state.getValue(AXIS)).ordinal();
    }
    
      
    // align placed block according to side clicked on
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
    {
        return super.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, metadata, entity).withProperty(AXIS, side.getAxis());
    }

    // discard axis info in dropped block
    @Override
    public int damageDropped(IBlockState state)
    {
        return this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, state.getValue(VARIANT)));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        double width = 1.0D;

        switch ((BoneType) state.getValue(VARIANT))
        {
            case SMALL:
                width = 0.25D;
                break;

            case MEDIUM:
                width = 0.625D;
                break;
        }

        double min = (1.0D - width) / 2D;
        double max = 1.0D - min;

        switch ((EnumFacing.Axis) state.getValue(AXIS))
        {
            case X:
                return new AxisAlignedBB(0.0D, min, min, 1.0D, max, max);

            case Y:
                return new AxisAlignedBB(min, 0.0D, min, max, 1.0D, max);

            case Z:
                return new AxisAlignedBB(min, min, 0.0D, max, max, 1.0D);
        }

        return FULL_BLOCK_AABB;
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

}
