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
import net.minecraft.util.IStringSerializable;
import biomesoplenty.api.block.BOPBlock;

//TODO: Add wasp spawn on honeycomb breaking, add correct drops
public class BlockHive extends BOPBlock 
{
	public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", HiveType.class);
	
	public BlockHive()
	{
		super(Material.wood);
		
    	this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, HiveType.HIVE));
    	
    	this.setHardness(0.5F);
	    this.setStepSound(Block.soundTypeGrass);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT_PROP, HiveType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int meta = ((HiveType)state.getValue(VARIANT_PROP)).ordinal();
		
		return meta;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT_PROP });
	}
	
    @Override
    public IProperty[] getPresetProperties()
    {
    	return new IProperty[] { VARIANT_PROP };
    }
    
    @Override
    public String getStateName(IBlockState state, boolean fullName)
    {
    	return ((HiveType)state.getValue(VARIANT_PROP)).getName() + (fullName ? "_block" : "");
    }
	
	public static enum HiveType implements IStringSerializable
	{
		HIVE,
		HONEYCOMB,
		EMPTY_HONEYCOMB,
		FILLED_HONEYCOMB;
		
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
