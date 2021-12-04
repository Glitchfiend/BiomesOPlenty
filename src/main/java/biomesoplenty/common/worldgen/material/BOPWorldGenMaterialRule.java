/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.material;

import javax.annotation.Nullable;

import biomesoplenty.common.worldgen.BOPNoiseChunk;
import net.minecraft.world.level.block.state.BlockState;

public interface BOPWorldGenMaterialRule
{
    @Nullable
    BlockState apply(BOPNoiseChunk p_191553_, int p_191554_, int p_191555_, int p_191556_);
}
