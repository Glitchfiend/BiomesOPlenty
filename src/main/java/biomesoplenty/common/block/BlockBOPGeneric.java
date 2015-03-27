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

public class BlockBOPGeneric extends BOPBlock
{
    public BlockBOPGeneric() {
        // use rock as default material
        this(Material.rock);
    }
    
    public BlockBOPGeneric(Material material)
    {
        super(material);
        // set some defaults
        this.setHardness(1.0F);
        this.setStepSound(Block.soundTypePiston);
    }
    
}