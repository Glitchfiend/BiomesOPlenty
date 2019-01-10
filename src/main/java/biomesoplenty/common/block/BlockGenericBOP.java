/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.block.Block;

public class BlockGenericBOP extends Block
{
    public BlockGenericBOP(Block.Builder properties)
    {
        // TODO: Sound and Harvest Tool
        super(properties);
        //this.setHarvestLevel("shovel", 0);
    }
}
