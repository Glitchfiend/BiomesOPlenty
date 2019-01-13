/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.IContextExtended;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

import java.util.function.LongFunction;

public class BOPLayerUtil
{
    public static <T extends IArea, C extends IContextExtended<T>> IAreaFactory<T> createBiomeAreaFactory(LongFunction<C> contextFactory)
    {
        IAreaFactory<T> factory = GenLayerIsland.INSTANCE.apply(contextFactory.apply(1L));
        factory = GenLayerZoom.FUZZY.apply(contextFactory.apply(2000L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(1L), factory);
        factory = GenLayerZoom.NORMAL.apply(contextFactory.apply(2001L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(2L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(50L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(70L), factory);
        factory = GenLayerRemoveTooMuchOcean.INSTANCE.apply(contextFactory.apply(2L), factory);
        factory = GenLayerAddSnow.INSTANCE.apply(contextFactory.apply(2L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(3L), factory);
        factory = GenLayerEdge.CoolWarm.INSTANCE.apply(contextFactory.apply(2L), factory);
        factory = GenLayerEdge.HeatIce.INSTANCE.apply(contextFactory.apply(2L), factory);
        factory = GenLayerEdge.Special.INSTANCE.apply(contextFactory.apply(3L), factory);
        factory = GenLayerZoom.NORMAL.apply(contextFactory.apply(2002L), factory);
        factory = GenLayerZoom.NORMAL.apply(contextFactory.apply(2003L), factory);
        factory = GenLayerAddIsland.INSTANCE.apply(contextFactory.apply(4L), factory);
        factory = GenLayerAddMushroomIsland.INSTANCE.apply(contextFactory.apply(5L), factory);
        factory = GenLayerDeepOcean.INSTANCE.apply(contextFactory.apply(4L), factory);
        factory = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, factory, 0, contextFactory);
        return factory;
    }

    public static <T extends IArea, C extends IContextExtended<T>> ImmutableList<IAreaFactory<T>> buildOverworldProcedure(WorldType worldTypeIn, OverworldGenSettings settings, LongFunction<C> contextFactory)
    {
        IAreaFactory<T> biomeAreaFactory = createBiomeAreaFactory(contextFactory);
        IAreaFactory<T> iareafactory1 = OceanLayer.INSTANCE.<T>apply((IContextExtended)contextFactory.apply(2L));
        iareafactory1 = LayerUtil.repeat(2001L, GenLayerZoom.NORMAL, iareafactory1, 6, contextFactory);

        int i = 4;
        int j = i;
        if (settings != null) {
            i = settings.getBiomeSize();
            j = settings.getRiverSize();
        }

        if (worldTypeIn == WorldType.LARGE_BIOMES) {
            i = 6;
        }

        i = LayerUtil.getModdedBiomeSize(worldTypeIn, i);

        IAreaFactory<T> lvt_7_1_ = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, biomeAreaFactory, 0, contextFactory);
        lvt_7_1_ = GenLayerRiverInit.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_7_1_);
        IAreaFactory<T> lvt_8_1_ = worldTypeIn.getBiomeLayer(biomeAreaFactory, settings, contextFactory);
        IAreaFactory<T> lvt_9_1_ = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, lvt_7_1_, 2, contextFactory);
        lvt_8_1_ = GenLayerHills.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_, lvt_9_1_);
        lvt_7_1_ = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, lvt_7_1_, 2, contextFactory);
        lvt_7_1_ = LayerUtil.repeat(1000L, GenLayerZoom.NORMAL, lvt_7_1_, j, contextFactory);
        lvt_7_1_ = GenLayerRiver.INSTANCE.apply((IContextExtended)contextFactory.apply(1L), lvt_7_1_);
        lvt_7_1_ = GenLayerSmooth.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_7_1_);
        lvt_8_1_ = GenLayerRareBiome.INSTANCE.apply((IContextExtended)contextFactory.apply(1001L), lvt_8_1_);

        for(int k = 0; k < i; ++k) {
            lvt_8_1_ = GenLayerZoom.NORMAL.apply((IContextExtended)contextFactory.apply((long)(1000 + k)), lvt_8_1_);
            if (k == 0) {
                lvt_8_1_ = GenLayerAddIsland.INSTANCE.apply((IContextExtended)contextFactory.apply(3L), lvt_8_1_);
            }

            if (k == 1 || i == 1) {
                lvt_8_1_ = GenLayerShore.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_);
            }
        }

        lvt_8_1_ = GenLayerSmooth.INSTANCE.apply((IContextExtended)contextFactory.apply(1000L), lvt_8_1_);
        lvt_8_1_ = GenLayerRiverMix.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_8_1_, lvt_7_1_);
        lvt_8_1_ = GenLayerMixOceans.INSTANCE.apply((IContextExtended)contextFactory.apply(100L), lvt_8_1_, iareafactory1);
        IAreaFactory<T> iareafactory5 = GenLayerVoronoiZoom.INSTANCE.<T>apply((IContextExtended)contextFactory.apply(10L), lvt_8_1_);
        return ImmutableList.<IAreaFactory<T>>of(biomeAreaFactory, iareafactory5, lvt_8_1_);
    }

    public static GenLayer[] buildOverworldProcedure(long seed, WorldType typeIn, OverworldGenSettings settings)
    {
        int i = 1;
        int[] aint = new int[1];
        ImmutableList<IAreaFactory<LazyArea>> immutablelist = buildOverworldProcedure(typeIn, settings, (p_202825_3_) -> {
            ++aint[0];
            return new LazyAreaLayerContext(1, aint[0], seed, p_202825_3_);
        });
        GenLayer genlayer = new GenLayer(immutablelist.get(0));
        GenLayer genlayer1 = new GenLayer(immutablelist.get(1));
        GenLayer genlayer2 = new GenLayer(immutablelist.get(2));
        return new GenLayer[]{genlayer, genlayer1, genlayer2};
    }
}
