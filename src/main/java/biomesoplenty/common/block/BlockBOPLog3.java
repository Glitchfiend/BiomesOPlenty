/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.IBOPVariant;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public class BlockBOPLog3 extends BlockBOPLogBase
{
	public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", LogType.class);
	
    public BlockBOPLog3()
    {
	    super(VARIANT_PROP);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	int axis = meta % 3;
    	int type = (meta - axis) / 3;
    	
        return this.getDefaultState().withProperty(VARIANT_PROP, LogType.values()[type]).withProperty(AXIS_PROP, EnumFacing.Axis.values()[axis]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	int baseMeta = ((LogType)state.getValue(VARIANT_PROP)).ordinal();
    	
    	return baseMeta * 3 + ((EnumFacing.Axis)state.getValue(AXIS_PROP)).ordinal();
    }
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { AXIS_PROP, VARIANT_PROP });
    }
    
	public static enum LogType implements IBOPVariant
	{
		DEAD,
		GIANT_FLOWER_STEM,
		PINE,
		HELL_BARK,
		JACARANDA;
		
		@Override
		public String getBaseName()
		{
			return this.equals(GIANT_FLOWER_STEM) ? null : "log";
		}
		
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
        
        @Override
        public int getDefaultMetadata()
        {
        	return this.ordinal() * 3;
        }
	}
}
