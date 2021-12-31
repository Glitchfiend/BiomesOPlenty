/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.noise;

import java.util.function.LongFunction;

public class LayeredNoiseUtil
{
    public static Area uniqueness(long worldSeed, int modBiomeBlockSize)
    {
        LongFunction<AreaContext> contextFactory = (seedModifier) -> new AreaContext(25, worldSeed, seedModifier);
        AreaFactory factory = InitialLayer.INSTANCE.run(contextFactory.apply(1L));
        factory = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), factory);
        factory = zoom(2001L, ZoomLayer.NORMAL, factory, 3, contextFactory);
        factory = zoom(1001L, ZoomLayer.NORMAL, factory, modBiomeBlockSize, contextFactory);
        return factory.make();
    }

    private static AreaFactory zoom(long seedModifier, AreaTransformer1 transformer, AreaFactory initialAreaFactory, int times, LongFunction<AreaContext> contextFactory)
    {
        AreaFactory areaFactory = initialAreaFactory;

        for (int i = 0; i < times; ++i)
        {
            areaFactory = transformer.run(contextFactory.apply(seedModifier + (long)i), areaFactory);
        }

        return areaFactory;
    }
}
