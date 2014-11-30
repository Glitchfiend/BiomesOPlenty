/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import java.util.Collection;
import java.util.List;

import biomesoplenty.common.util.inventory.CreativeTabBOP;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BOPBlock extends Block
{
	private final PropertyEnum variantProperty;
	
    protected BOPBlock(Material material, PropertyEnum variantProperty)
    {
	    super(material);
	    
	    this.variantProperty = variantProperty;
	    
	    if (variantProperty != null) this.setDefaultState(this.blockState.getBaseState().withProperty(variantProperty, (Comparable)variantProperty.getAllowedValues().toArray()[0]));
	    
	    this.setCreativeTab(CreativeTabBOP.instance);
    }
	
	protected BOPBlock(Material material)
	{
		this(material, null);
	}

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return hasVariants() ? this.getDefaultState().withProperty(variantProperty, (Comparable)variantProperty.getAllowedValues().toArray()[meta]) : super.getStateFromMeta(meta);
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	return hasVariants() ? ((Enum)state.getValue(variantProperty)).ordinal() : super.getMetaFromState(state);
    }
	
	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		if (hasVariants())
		{
			for (IBOPVariant value : getVariants())
			{
				list.add(new ItemStack(item, 1, value.getDefaultMetadata()));
			}
		}
		else
		{
	        list.add(new ItemStack(item, 1, 0));
		}
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}
    
    public final boolean hasVariants()
    {
    	return variantProperty != null;
    }
    
    public final Collection<IBOPVariant> getVariants()
    {
    	return (Collection<IBOPVariant>)variantProperty.getAllowedValues();
    }
    
    public final IBOPVariant getVariantFromMeta(int metadata)
    {
    	return (IBOPVariant)this.getStateFromMeta(metadata).getValue(variantProperty);
    }
}
