/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.util.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import com.google.common.collect.ImmutableSet;

public class BlockStateUtils 
{
	public static IProperty getPropertyByName(IBlockState blockState, String propertyName)
	{
		for (IProperty property : (ImmutableSet<IProperty>)blockState.getProperties().keySet())
		{
			if (property.getName().equals(propertyName)) return property;
		}
		
		return null;
	}
	
	public static boolean isValidPropertyName(IBlockState blockState, String propertyName)
	{
		return getPropertyByName(blockState, propertyName) != null;
	}
	
	public static Comparable getPropertyValueByName(IBlockState blockState, IProperty property, String valueName)
	{
		for (Comparable value : (ImmutableSet<Comparable>)property.getAllowedValues())
		{
			if (value.toString().equals(valueName)) return value;
		}
		
		return null;
	}
}
