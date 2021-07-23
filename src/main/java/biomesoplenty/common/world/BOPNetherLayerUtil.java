/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.common.world.layer.BOPShoreLayer;
import biomesoplenty.common.world.layer.LandLayer;
import biomesoplenty.common.world.layer.NetherBiomeLayer;
import biomesoplenty.common.world.layer.traits.LazyAreaLayerContextBOP;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.area.LazyArea;
import net.minecraft.world.level.newbiome.context.BigContext;
import net.minecraft.world.level.newbiome.layer.*;

import java.util.function.LongFunction;

public class BOPNetherLayerUtil
{
    public static <T extends Area, C extends BigContext<T>> AreaFactory<T> createBiomeFactory(AreaFactory<T> landFactory, LongFunction<C> contextFactory)
    {
        AreaFactory<T> biomeFactory = NetherBiomeLayer.INSTANCE.run(contextFactory.apply(200L));
        // magnify the biome layer
        biomeFactory = Layers.zoom(1000L, ZoomLayer.NORMAL, biomeFactory, 2, contextFactory);
        return biomeFactory;
    }

    public static <T extends Area, C extends BigContext<T>> AreaFactory<T> createAreaFactories(LongFunction<C> contextFactory)
    {
        int biomeSize = 4;

        // The nether has no oceans, only land
        AreaFactory<T> landFactory = LandLayer.INSTANCE.run(contextFactory.apply(1L));

        // Allocate the biomes
        AreaFactory<T> biomesFactory = createBiomeFactory(landFactory, contextFactory);

        // Zoom more based on the biome size
        for (int i = 0; i < biomeSize; ++i)
        {
            biomesFactory = ZoomLayer.NORMAL.run(contextFactory.apply((long)(1000 + i)), biomesFactory);
            if (i == 0) biomesFactory = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), biomesFactory);
            if (i == 1 || biomeSize == 1) biomesFactory = BOPShoreLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);
        }

        biomesFactory = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);
        return biomesFactory;
    }

    public static Layer createGenLayers(long seed)
    {
        AreaFactory<LazyArea> factory = createAreaFactories((seedModifier) ->
        {
            return new LazyAreaLayerContextBOP(1, seed, seedModifier);
        });

        return new Layer(factory);
    }
}
