/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;

public interface IBlockPosQuery
{
    boolean matches(LevelAccessor world, BlockPos pos);
}
