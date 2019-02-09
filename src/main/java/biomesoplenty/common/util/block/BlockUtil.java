/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.state.IProperty;
import net.minecraft.util.EnumFacing;

import java.util.Collection;

public class BlockUtil
{
    // Given a blockstate for some block which has an axis orientation (such as logs), try to determine the axis property
    public static IProperty getAxisProperty(IBlockState log)
    {
        for (IProperty property : log.getProperties())
        {
            Collection allowedValues = property.getAllowedValues();
            if (allowedValues.contains(EnumFacing.Axis.X) && allowedValues.contains(EnumFacing.Axis.Y) && allowedValues.contains(EnumFacing.Axis.Z))
            {
                return property;
            }
        }
        return null;
    }
}
