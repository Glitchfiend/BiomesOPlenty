/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.block;

import biomesoplenty.api.IConfigurable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public abstract class BOPBlock extends Block implements IConfigurable
{
    protected BOPBlock(Material material)
    {
	    super(material);
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
}
