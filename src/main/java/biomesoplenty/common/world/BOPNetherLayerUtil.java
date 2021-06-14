/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.common.world.layer.BOPShoreLayer;
import biomesoplenty.common.world.layer.LandLayer;
import biomesoplenty.common.world.layer.NetherBiomeLayer;
import biomesoplenty.common.world.layer.traits.LazyAreaLayerContextBOP;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class BOPNetherLayerUtil
{
    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createBiomeFactory(IAreaFactory<T> landFactory, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> biomeFactory = NetherBiomeLayer.INSTANCE.run(contextFactory.apply(200L));
        // magnify the biome layer
        biomeFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, biomeFactory, 2, contextFactory);
        return biomeFactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createAreaFactories(LongFunction<C> contextFactory)
    {
        int biomeSize = 4;

        // The nether has no oceans, only land
        IAreaFactory<T> landFactory = LandLayer.INSTANCE.run(contextFactory.apply(1L));

        // Allocate the biomes
        IAreaFactory<T> biomesFactory = createBiomeFactory(landFactory, contextFactory);

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
        IAreaFactory<LazyArea> factory = createAreaFactories((seedModifier) ->
        {
            return new LazyAreaLayerContextBOP(1, seed, seedModifier);
        });

        return new Layer(factory);
    }
}
