/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.level.newbiome.area.LazyArea;
import net.minecraft.world.level.newbiome.context.LazyAreaContext;

public class LazyAreaLayerContextBOP extends LazyAreaContext implements IBOPContextExtended<LazyArea>
{
    private long worldSeed;

    public LazyAreaLayerContextBOP(int maxCacheSize, long seed, long seedModifier)
    {
        super(maxCacheSize, seed, seedModifier);
        this.worldSeed = seed;
    }

    @Override
    public long getWorldSeed()
    {
        return this.worldSeed;
    }
}
