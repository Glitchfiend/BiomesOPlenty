/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

//TODO: Add gem ore drops, make gem item seperate
public class BlockGem extends BOPBlock
{
	public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", GemType.class);
	
	public BlockGem()
	{
		super(Material.rock);
		
    	this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, GemType.AMETHYST));
    	
    	for (IBlockState state : presetStates)
    	{
        	this.setHarvestLevel("pickaxe", 2, state);	
    	}
    	
		this.setHarvestLevel("pickaxe", 3, this.getDefaultState().withProperty(VARIANT_PROP, GemType.AMETHYST));
    	
    	this.setHardness(5.0F);
    	this.setResistance(10.0F);
	    this.setStepSound(Block.soundTypeMetal);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT_PROP, GemType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int meta = ((GemType)state.getValue(VARIANT_PROP)).ordinal();
		
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
    	return ((GemType)state.getValue(VARIANT_PROP)).getName() + (fullName ? "_block" : "");
    }
	
	public static enum GemType implements IStringSerializable
	{
		AMETHYST,
		RUBY,
		PERIDOT,
		TOPAZ,
		TANZANITE,
		MALACHITE,
		SAPPHIRE,
		AMBER;
		
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
