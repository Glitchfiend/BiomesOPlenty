/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.noise;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class InitialLayer implements AreaTransformer0
{
    private final WeightedRandomList weightedEntries;

    public InitialLayer()
    {
        this.weightedEntries = WeightedRandomList.create(BiomeProviders.getProviders());
    }

    @Override
    public int apply(AreaContext context, int x, int y)
    {
        Optional<BiomeProviders.BiomeProvider> entry = weightedEntries.getRandom(context);
        return entry.isPresent() ? entry.get().getIndex() : 0;
    }

    private static class WeightedRandomList<E extends WeightedEntry>
    {
        private final int totalWeight;
        private final ImmutableList<E> items;

        WeightedRandomList(List<? extends E> items)
        {
            this.items = ImmutableList.copyOf(items);
            this.totalWeight = WeightedRandom.getTotalWeight(items);
        }

        public static <E extends WeightedEntry> WeightedRandomList<E> create() {
            return new WeightedRandomList<>(ImmutableList.of());
        }

        @SafeVarargs
        public static <E extends WeightedEntry> WeightedRandomList<E> create(E... entries)
        {
            return new WeightedRandomList<>(ImmutableList.copyOf(entries));
        }

        public static <E extends WeightedEntry> WeightedRandomList<E> create(List<E> entries)
        {
            return new WeightedRandomList<>(entries);
        }

        public Optional<E> getRandom(AreaContext context)
        {
            if (this.totalWeight == 0) {
                return Optional.empty();
            } else {
                int i = context.nextRandom(this.totalWeight);
                return WeightedRandom.getWeightedItem(this.items, i);
            }
        }
    }
}
