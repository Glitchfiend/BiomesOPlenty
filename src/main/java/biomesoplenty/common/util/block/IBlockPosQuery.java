/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public interface IBlockPosQuery
{
    boolean matches(LevelAccessor world, BlockPos pos);
}
