/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.biome;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.biome.Climate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeProviders
{
    private static final ImmutableList<BiomeProvider> DEFAULT_PROVIDERS;
    private static List<BiomeProvider> biomeProviders;
    private static Map<String, Integer> biomeIndices;
    private static int nextIndex = 0;

    public static void register(String modId, int weight)
    {
        if (biomeProviders.stream().anyMatch(provider -> provider.getModId() == modId))
            return;

        BiomesOPlenty.logger.info("Registered biome provider for " + modId);
        biomeProviders.add(new BiomeProvider(modId, weight));
    }

    public static ImmutableList<BiomeProvider> getProviders()
    {
        return ImmutableList.copyOf(biomeProviders);
    }

    public static int getIndex(String modId)
    {
        return biomeIndices.containsKey(modId) ? biomeIndices.get(modId) : 0;
    }

    public static int getCount()
    {
        return nextIndex;
    }

    public static float getUniquenessRangeSize()
    {
        return 2.0F / (float)getCount();
    }

    public static float getUniquenessMidPoint(int index)
    {
        float rangeSize = getUniquenessRangeSize();
        return -1.0F + rangeSize * index + (rangeSize * 0.5F);
    }

    public static Climate.Parameter getUniquenessParameter(int index)
    {
        float min = -1.0F + getUniquenessRangeSize() * index;
        float max = -1.0F + getUniquenessRangeSize() * (index + 1);
        return Climate.Parameter.span(min, max);
    }

    public static void reset()
    {
        nextIndex = 2;
        biomeIndices = Maps.newHashMap();
        biomeProviders = Lists.newArrayList(DEFAULT_PROVIDERS);
    }

    private static void addIndex(String modId, int index)
    {
        biomeIndices.put(modId, index);
    }

    public static class BiomeProvider extends WeightedEntry.IntrusiveBase
    {
        private final String modId;
        private final int index;

        private BiomeProvider(String modId, int weight)
        {
            super(weight);
            this.modId = modId;
            this.index = BiomeProviders.nextIndex++;
            BiomeProviders.addIndex(modId, index);
        }

        public String getModId()
        {
            return this.modId;
        }

        public int getIndex()
        {
            return this.index;
        }
    }

    static
    {
        biomeIndices = Maps.newHashMap();
        DEFAULT_PROVIDERS = ImmutableList.of(new BiomeProvider("minecraft", 10), new BiomeProvider("biomesoplenty", 15));
        biomeProviders = Lists.newArrayList(DEFAULT_PROVIDERS);
    }
}
