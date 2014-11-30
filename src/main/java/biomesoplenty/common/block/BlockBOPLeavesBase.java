/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlock;
import net.minecraft.block.material.Material;

public abstract class BlockBOPLeavesBase extends BOPBlock
{
	protected boolean fastGraphics;

    protected BlockBOPLeavesBase()
    {
	    super(Material.leaves);
	    
	    this.fastGraphics = false;
    }

}
