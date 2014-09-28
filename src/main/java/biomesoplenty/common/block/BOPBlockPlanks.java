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
import biomesoplenty.common.util.inventory.CreativeTabBOP;

//TODO: Commented methods and calls
public class BOPBlockPlanks extends BOPBlock
{
	public static PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", PlankType.class);
	
    public BOPBlockPlanks()
    {
	    super(Material.wood, VARIANT_PROP);
	    
	    this.setDefaultBlockState(this.blockState.getBaseState().withProperty(VARIANT_PROP, PlankType.SACRED_OAK));
	    
		this.setHardness(2.0F);
		//this.setHarvestLevel("axe", 0);
		this.setStepSound(Block.soundTypeWood);
	    this.setCreativeTab(CreativeTabBOP.instance);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, PlankType.values()[meta]);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return ((PlankType)state.getValue(VARIANT_PROP)).ordinal();
    }
    
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] { VARIANT_PROP });
    }

	public static enum PlankType implements IStringSerializable
	{
	    SACRED_OAK,
	    CHERRY,
	    DARK,
	    FIR,
	    ETHEREAL,
	    MAGIC,
	    MANGROVE,
	    PALM,
	    REDWOOD,
	    WILLOW,
	    BAMBOO_THATCHING,
	    PINE,
	    HELL_BARK,
	    JACARANDA,
	    MAHOGANY;
		
        @Override
        public String getName()
        {
	        return this.name().toLowerCase();
        }
	}
}
