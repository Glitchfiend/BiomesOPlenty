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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeProviders
{
    private static final ImmutableList<BiomeProvider> DEFAULT_PROVIDERS = ImmutableList.of(new BiomeProvider("minecraft", 10), new BiomeProvider("biomesoplenty", 15));
    private static List<BiomeProvider> biomeProviders = Lists.newArrayList(DEFAULT_PROVIDERS);
    private static Map<String, Integer> biomeIndices;

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
        if (biomeIndices == null)
            biomeIndices = Maps.newHashMap();
        return biomeIndices.containsKey(modId) ? biomeIndices.get(modId) : 0;
    }

    private static void addIndex(String modId, int index)
    {
        if (biomeIndices == null)
            biomeIndices = Maps.newHashMap();
        biomeIndices.put(modId, index);
    }

    public static class BiomeProvider extends WeightedEntry.IntrusiveBase
    {
        private static int nextIndex = 0;

        private final String modId;
        private final int index;

        private BiomeProvider(String modId, int weight)
        {
            super(weight);
            this.modId = modId;
            this.index = nextIndex++;
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
}
