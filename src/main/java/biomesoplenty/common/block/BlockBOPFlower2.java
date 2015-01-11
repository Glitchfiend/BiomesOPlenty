/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPPlant;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBOPFlower2 extends BOPPlant
{
	public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", FlowerType.class);

    public BlockBOPFlower2()
    {
    	this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, FlowerType.LAVENDER));
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
    	Block ground = world.getBlockState(pos.offsetDown()).getBlock();
    	FlowerType type = (FlowerType)state.getValue(VARIANT_PROP);
    	
    	switch (type)
    	{
    		case MINERS_DELIGHT:
    			return ground == Blocks.stone;
    			
    		default:
    	        return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland;
    	}
    }
    
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT_PROP, FlowerType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int meta = ((FlowerType)state.getValue(VARIANT_PROP)).ordinal();

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
		return ((FlowerType)state.getValue(VARIANT_PROP)).getName();
	}
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
    	this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
    }
	
	public static enum FlowerType implements IStringSerializable
	{
		LAVENDER,
		GOLDENROD,
		BLUEBELLS,
		MINERS_DELIGHT,
		ICY_IRIS,
		ROSE;

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
