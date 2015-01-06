/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.api.block.BOPBlock;

public class BlockBones extends BOPBlock
{
	public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BoneType.class);
    public static final PropertyEnum AXIS_PROP = PropertyEnum.create("axis", EnumFacing.Axis.class);
	
    public BlockBones()
    {
	    super(Material.rock);
	    
    	this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, BoneType.SMALL).withProperty(AXIS_PROP, EnumFacing.Axis.Y));
	    
		this.setHardness(3.0F);
		this.setResistance(5.0F);
	    this.setStepSound(Block.soundTypeStone);
    }
    
    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity)
    {
        return super.onBlockPlaced(world, pos, side, hitX, hitY, hitZ, metadata, entity).withProperty(AXIS_PROP, side.getAxis());
    }
    
	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(this.getDefaultState().withProperty(VARIANT_PROP, state.getValue(VARIANT_PROP)));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		int axis = meta % 3;
		int type = (meta - axis) / 3;

		return this.getDefaultState().withProperty(VARIANT_PROP, BoneType.values()[type]).withProperty(AXIS_PROP, EnumFacing.Axis.values()[axis]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int baseMeta = ((BoneType)state.getValue(VARIANT_PROP)).ordinal();

		return baseMeta * 3 + ((EnumFacing.Axis)state.getValue(AXIS_PROP)).ordinal();
	}
    
	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { AXIS_PROP, VARIANT_PROP });
	}
	
    @Override
    public IProperty[] getPresetProperties()
    {
    	return new IProperty[] { VARIANT_PROP };
    }
    
    @Override
	public String getStateName(IBlockState state, boolean fullName)
	{
		return ((BoneType)state.getValue(VARIANT_PROP)).getName() + (fullName ? "_bone_segment" : "");
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getSelectedBoundingBox(worldIn, pos);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getCollisionBoundingBox(worldIn, pos, state);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
    	IBlockState state = world.getBlockState(pos);
    	
    	if (state.getBlock() != this) return;
    	
    	float width;
    	
    	switch ((BoneType)state.getValue(VARIANT_PROP))
    	{
    		case SMALL: 
    			width = 0.25F;
    			break;
    			
    		case MEDIUM:
    			width = 0.625F;
    			break;
    			
    		case LARGE:
    			width = 1F;
    			break;
    			
    		default:
    			width = 1F;
    			break;
    	}
    	
    	float min = (1.0F - width) / 2F;
    	float max = 1.0F - min;
    	
    	switch ((EnumFacing.Axis)state.getValue(AXIS_PROP))
    	{
    		case X:
    			this.setBlockBounds(0F, min, min, 1.0F, max, max);
    			break;
    	
    		case Y:
    			this.setBlockBounds(min, 0F, min, max, 1.0F, max);
    			break;
    			
    		case Z:
    			this.setBlockBounds(min, min, 0F, max, max, 1.0F);
    			break;
    	}
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
    
	public static enum BoneType implements IStringSerializable
	{
		SMALL,
		MEDIUM,
		LARGE;

		@Override
		public String getName()
		{
			return this.name().toLowerCase();
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}
}
