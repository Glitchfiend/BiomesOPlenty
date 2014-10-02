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

import biomesoplenty.api.IConfigurable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BOPBlock extends Block implements IConfigurable
{
	private final PropertyEnum variantProperty;
	
    protected BOPBlock(Material material, PropertyEnum variantProperty)
    {
	    super(material);
	    
	    this.variantProperty = variantProperty;
    }
	
	protected BOPBlock(Material material)
	{
		this(material, null);
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
	
    @Override
    //TODO: Account for configurations (which are provided by Forge and thus, cannot be done at this time)
    public boolean isEnabled(Object... args)
    {
    	if (args[0] instanceof IBlockState)
    	{
    		IBlockState blockState = (IBlockState)args[0];
    		
    		return true;
    	}
    	
    	return false;
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
