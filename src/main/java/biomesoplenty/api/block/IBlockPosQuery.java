/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

// for queries on a particular block position in the world
public interface IBlockPosQuery
{
    public boolean matches(World world, BlockPos pos);
}