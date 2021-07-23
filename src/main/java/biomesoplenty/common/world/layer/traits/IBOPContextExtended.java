/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.context.BigContext;

/***
 * Provides extra information beyond that used by Mojang.
 */
public interface IBOPContextExtended<R extends Area> extends BigContext<R>
{
    long getWorldSeed();
}
