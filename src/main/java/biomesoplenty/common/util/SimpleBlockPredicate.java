/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;

import java.util.function.BiPredicate;

public interface SimpleBlockPredicate extends BiPredicate<WorldGenLevel, BlockPos>
{
    // Wrapper
    default boolean matches(WorldGenLevel world, BlockPos pos) {
        return this.test(world, pos);
    }
}
