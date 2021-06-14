/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.world.layer.*;
import biomesoplenty.common.world.layer.traits.LazyAreaLayerContextBOP;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;

import java.util.function.LongFunction;

public class BOPLayerUtil
{
    public static final int WARM_OCEAN = BiomeUtil.getBiomeId(Biomes.WARM_OCEAN);
    public static final int LUKEWARM_OCEAN = BiomeUtil.getBiomeId(Biomes.LUKEWARM_OCEAN);
    public static final int OCEAN = BiomeUtil.getBiomeId(Biomes.OCEAN);
    public static final int COLD_OCEAN = BiomeUtil.getBiomeId(Biomes.COLD_OCEAN);
    public static final int FROZEN_OCEAN = BiomeUtil.getBiomeId(Biomes.FROZEN_OCEAN);
    public static final int DEEP_WARM_OCEAN = BiomeUtil.getBiomeId(Biomes.DEEP_WARM_OCEAN);
    public static final int DEEP_LUKEWARM_OCEAN = BiomeUtil.getBiomeId(Biomes.DEEP_LUKEWARM_OCEAN);
    public static final int DEEP_OCEAN = BiomeUtil.getBiomeId(Biomes.DEEP_OCEAN);
    public static final int DEEP_COLD_OCEAN = BiomeUtil.getBiomeId(Biomes.DEEP_COLD_OCEAN);
    public static final int DEEP_FROZEN_OCEAN = BiomeUtil.getBiomeId(Biomes.DEEP_FROZEN_OCEAN);

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createInitialLandAndSeaFactory(LongFunction<C> contextFactory)
    {
        // NOTE: Normally AddSnow, CoolWarm, HeatIce and Special GenLayers occur here, but we handle those ourselves
        IAreaFactory<T> factory = IslandLayer.INSTANCE.run(contextFactory.apply(1L));
        factory = ZoomLayer.FUZZY.run(contextFactory.apply(2000L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(1L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2001L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(2L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(50L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(70L), factory);
        factory = RemoveTooMuchOceanLayer.INSTANCE.run(contextFactory.apply(2L), factory);
        //factory = GenLayerAddSnow.INSTANCE.<T>apply((IContextExtended)contextFactory.apply(2L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), factory);
        //factory = GenLayerEdge.CoolWarm.INSTANCE.run(contextFactory.apply(2L), factory);
        //factory = GenLayerEdge.HeatIce.INSTANCE.run(contextFactory.apply(2L), factory);
        //factory = GenLayerEdge.Special.INSTANCE.run(contextFactory.apply(3L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2002L), factory);
        factory = ZoomLayer.NORMAL.run(contextFactory.apply(2003L), factory);
        factory = AddIslandLayer.INSTANCE.run(contextFactory.apply(4L), factory);
        return factory;
    }

    // superimpose hot and cold regions an a land and sea layer
    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createClimateFactory(LongFunction<C> contextFactory, BOPOverworldGenSettings settings)
    {
        IAreaFactory<T> temperatureFactory;

        switch (settings.getTempScheme())
        {
            case LATITUDE: default:
                temperatureFactory = TemperatureLatitudeLayer.INSTANCE.run(contextFactory.apply(2L));
                break;
            case SMALL_ZONES:
                temperatureFactory = TemperatureNoiseLayer.SMALL_ZONES.run(contextFactory.apply(3L));
                break;
            case MEDIUM_ZONES:
                temperatureFactory = TemperatureNoiseLayer.MEDIUM_ZONES.run(contextFactory.apply(4L));
                break;
            case LARGE_ZONES:
                temperatureFactory = TemperatureNoiseLayer.LARGE_ZONES.run(contextFactory.apply(5L));
                break;
            case RANDOM:
                temperatureFactory = TemperatureRandomLayer.INSTANCE.run(contextFactory.apply(6L));
                break;
        }

        IAreaFactory<T> rainfallFactory;
        switch(settings.getRainScheme())
        {
            case SMALL_ZONES:
                rainfallFactory = RainfallNoiseLayer.SMALL_ZONES.run(contextFactory.apply(7L));
                break;
            case MEDIUM_ZONES: default:
                rainfallFactory = RainfallNoiseLayer.MEDIUM_ZONES.run(contextFactory.apply(8L));
                break;
            case LARGE_ZONES:
                rainfallFactory = RainfallNoiseLayer.LARGE_ZONES.run(contextFactory.apply(9L));
                break;
            case RANDOM:
                rainfallFactory = RainfallRandomLayer.INSTANCE.run(contextFactory.apply(10L));
                break;
        }

        return ClimateLayer.INSTANCE.run(contextFactory.apply(103L), temperatureFactory, rainfallFactory);
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createBiomeFactory(IAreaFactory<T> landSeaAreaFactory, IAreaFactory<T> climateAreaFactory, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> biomeFactory = BOPBiomeLayer.INSTANCE.run(contextFactory.apply(200L), landSeaAreaFactory, climateAreaFactory);
        biomeFactory = AddBambooForestLayer.INSTANCE.run(contextFactory.apply(1001L), biomeFactory);
        biomeFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, biomeFactory, 2, contextFactory);
        biomeFactory = BOPBiomeEdgeLayer.INSTANCE.run(contextFactory.apply(1000L), biomeFactory);
        return biomeFactory;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> createAreaFactories(BOPOverworldGenSettings settings, LongFunction<C> contextFactory)
    {
        // Create the initial land and sea layer. Is also responsible for adding deep oceans
        // and mushroom islands
        IAreaFactory<T> landSeaFactory = createInitialLandAndSeaFactory(contextFactory);

        // Determines positions for all of the new ocean subbiomes added in 1.13
        IAreaFactory<T> oceanBiomeFactory = OceanLayer.INSTANCE.run(contextFactory.apply(2L));
        oceanBiomeFactory = LayerUtil.zoom(2001L, ZoomLayer.NORMAL, oceanBiomeFactory, 6, contextFactory);

        int biomeSize = 4;
        int riverSize = biomeSize;
        if (settings != null)
        {
            biomeSize = settings.getBiomeSize();
            riverSize = settings.getRiverSize();
        }

        // Create the climates
        IAreaFactory<T> climateFactory = createClimateFactory(contextFactory, settings);

        // Add islands and deep oceans
        landSeaFactory = AddMushroomIslandLayer.INSTANCE.run(contextFactory.apply(5L), landSeaFactory);
        landSeaFactory = LargeIslandLayer.INSTANCE.run(contextFactory.apply(5L), landSeaFactory, climateFactory);
        landSeaFactory = DeepOceanLayer.INSTANCE.run(contextFactory.apply(4L), landSeaFactory);

        // Allocate the biomes
        IAreaFactory<T> biomesFactory = createBiomeFactory(landSeaFactory, climateFactory, contextFactory);

        // Fork off a new branch as a seed for rivers and sub biomes
        IAreaFactory<T> riverAndSubBiomesInitFactory = StartRiverLayer.INSTANCE.run(contextFactory.apply(100L), landSeaFactory);
        riverAndSubBiomesInitFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, riverAndSubBiomesInitFactory, 2, contextFactory);
        biomesFactory = SubBiomeLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory, riverAndSubBiomesInitFactory);

        // Develop the rivers branch
        IAreaFactory<T> riversInitFactory = LayerUtil.zoom(1000L, ZoomLayer.NORMAL, riverAndSubBiomesInitFactory, riverSize, contextFactory);
        riversInitFactory = RiverLayer.INSTANCE.run(contextFactory.apply(1L), riversInitFactory);
        riversInitFactory = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), riversInitFactory);

        // Mix in rare biomes into biomes branch
        biomesFactory = RareBiomeLayer.INSTANCE.run(contextFactory.apply(1001L), biomesFactory);

        // Zoom more based on the biome size
        for (int i = 0; i < biomeSize; ++i)
        {
            biomesFactory = ZoomLayer.NORMAL.run(contextFactory.apply((long)(1000 + i)), biomesFactory);
            if (i == 0) biomesFactory = AddIslandLayer.INSTANCE.run(contextFactory.apply(3L), biomesFactory);
            if (i == 1 || biomeSize == 1) biomesFactory = BOPShoreLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);
        }

        biomesFactory = SmoothLayer.INSTANCE.run(contextFactory.apply(1000L), biomesFactory);

        // Mix rivers into the biomes branch
        biomesFactory = BOPRiverMixLayer.INSTANCE.run(contextFactory.apply(100L), biomesFactory, riversInitFactory);

        climateFactory = LayerUtil.zoom(2001L, ZoomLayer.NORMAL, climateFactory, biomeSize + 2, contextFactory);
        biomesFactory = BOPMixOceansLayer.INSTANCE.run(contextFactory.apply(100L), biomesFactory, oceanBiomeFactory, climateFactory);
        return biomesFactory;
    }

    public static Layer createGenLayers(long seed, BOPOverworldGenSettings settings)
    {
        IAreaFactory<LazyArea> factory = createAreaFactories(settings, (seedModifier) ->
        {
            return new LazyAreaLayerContextBOP(1, seed, seedModifier);
        });
        return new Layer(factory);
    }

    public static boolean isOcean(int biomeIn)
    {
        return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN || biomeIn == DEEP_WARM_OCEAN || biomeIn == DEEP_LUKEWARM_OCEAN || biomeIn == DEEP_OCEAN || biomeIn == DEEP_COLD_OCEAN || biomeIn == DEEP_FROZEN_OCEAN;
    }

    public static boolean isShallowOcean(int biomeIn)
    {
        return biomeIn == WARM_OCEAN || biomeIn == LUKEWARM_OCEAN || biomeIn == OCEAN || biomeIn == COLD_OCEAN || biomeIn == FROZEN_OCEAN;
    }
}
