/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;


import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.world.BOPLayerUtil;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import net.minecraft.Util;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.Layers;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer2;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

import java.util.Iterator;
import java.util.List;

public enum SubBiomeLayer implements AreaTransformer2, DimensionOffset0Transformer
{
    INSTANCE;

    private static final int BIRCH_FOREST = BiomeUtil.getBiomeId(Biomes.BIRCH_FOREST);
    private static final int BIRCH_FOREST_HILLS = BiomeUtil.getBiomeId(Biomes.BIRCH_FOREST_HILLS);
    private static final int DESERT = BiomeUtil.getBiomeId(Biomes.DESERT);
    private static final int DESERT_HILLS = BiomeUtil.getBiomeId(Biomes.DESERT_HILLS);
    private static final int MOUNTAINS = BiomeUtil.getBiomeId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = BiomeUtil.getBiomeId(Biomes.WOODED_MOUNTAINS);
    private static final int FOREST = BiomeUtil.getBiomeId(Biomes.FOREST);
    private static final int WOODED_HILLS = BiomeUtil.getBiomeId(Biomes.WOODED_HILLS);
    private static final int SNOWY_TUNDRA = BiomeUtil.getBiomeId(Biomes.SNOWY_TUNDRA);
    private static final int SNOWY_MOUNTAINS = BiomeUtil.getBiomeId(Biomes.SNOWY_MOUNTAINS);
    private static final int JUNGLE = BiomeUtil.getBiomeId(Biomes.JUNGLE);
    private static final int JUNGLE_HILLS = BiomeUtil.getBiomeId(Biomes.JUNGLE_HILLS);
    private static final int BAMBOO_JUNGLE = BiomeUtil.getBiomeId(Biomes.BAMBOO_JUNGLE);
    private static final int BAMBOO_JUNGLE_HILLS = BiomeUtil.getBiomeId(Biomes.BAMBOO_JUNGLE_HILLS);
    private static final int BADLANDS = BiomeUtil.getBiomeId(Biomes.BADLANDS);
    private static final int WOODED_BADLANDS_PLATEAU = BiomeUtil.getBiomeId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int PLAINS = BiomeUtil.getBiomeId(Biomes.PLAINS);
    private static final int GIANT_TREE_TAIGA = BiomeUtil.getBiomeId(Biomes.GIANT_TREE_TAIGA);
    private static final int GIANT_TREE_TAIGA_HILLS = BiomeUtil.getBiomeId(Biomes.GIANT_TREE_TAIGA_HILLS);
    private static final int DARK_FOREST = BiomeUtil.getBiomeId(Biomes.DARK_FOREST);
    private static final int SAVANNA = BiomeUtil.getBiomeId(Biomes.SAVANNA);
    private static final int SAVANA_PLATEAU = BiomeUtil.getBiomeId(Biomes.SAVANNA_PLATEAU);
    private static final int TAIGA = BiomeUtil.getBiomeId(Biomes.TAIGA);
    private static final int SNOWY_TAIGA = BiomeUtil.getBiomeId(Biomes.SNOWY_TAIGA);
    private static final int SNOWY_TAIGA_HILLS = BiomeUtil.getBiomeId(Biomes.SNOWY_TAIGA_HILLS);
    private static final int TAIGA_HILLS = BiomeUtil.getBiomeId(Biomes.TAIGA_HILLS);

    private static final Int2IntMap MUTATIONS = Util.make(new Int2IntOpenHashMap(), (map) -> {
        map.put(1, 129);
        map.put(2, 130);
        map.put(3, 131);
        map.put(4, 132);
        map.put(5, 133);
        map.put(6, 134);
        map.put(12, 140);
        map.put(21, 149);
        map.put(23, 151);
        map.put(27, 155);
        map.put(28, 156);
        map.put(29, 157);
        map.put(30, 158);
        map.put(32, 160);
        map.put(33, 161);
        map.put(34, 162);
        map.put(35, 163);
        map.put(36, 164);
        map.put(37, 165);
        map.put(38, 166);
        map.put(39, 167);
    });

    @Override
    public int applyPixel(Context context, Area biomeArea, Area riverAndSubBiomesInitArea, int x, int z)
    {
        int biomeId = biomeArea.get(this.getParentX(x + 1), this.getParentY(z + 1));
        int initVal = riverAndSubBiomesInitArea.get(this.getParentX(x + 1), this.getParentY(z + 1));

        int subBiomeType = (initVal - 2) % 29;
        boolean tryRareHillsBiome = subBiomeType == 0;
        boolean tryRareBiome = subBiomeType == 1;

        Biome mutatedBiome;
        if (!BOPLayerUtil.isShallowOcean(biomeId) && initVal >= 2 && tryRareBiome)
        {
            return MUTATIONS.getOrDefault(biomeId, biomeId);
        }

        if (context.nextRandom(3) == 0 || tryRareHillsBiome)
        {
            int mutatedBiomeId = this.getCommonSubBiomeId(context, biomeId);

            if (mutatedBiomeId != biomeId)
                return mutatedBiomeId;

            mutatedBiomeId = this.getRareSubBiomeId(context, biomeId);

            if (subBiomeType == 0 && mutatedBiomeId != biomeId)
            {
                mutatedBiomeId = MUTATIONS.getOrDefault(mutatedBiomeId, biomeId);
            }

            if (mutatedBiomeId != biomeId)
            {
                int surroundingSimilarCount = 0;
                if (Layers.isSame(biomeArea.get(x + 1, z + 0), biomeId))
                    ++surroundingSimilarCount;
                if (Layers.isSame(biomeArea.get(x + 2, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (Layers.isSame(biomeArea.get(x + 0, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (Layers.isSame(biomeArea.get(x + 1, z + 2), biomeId))
                    ++surroundingSimilarCount;

                if (surroundingSimilarCount >= 3)
                    return mutatedBiomeId;
            }
        }

        return biomeId;
    }

    public int getCommonSubBiomeId(Context context, int originalBiomeId)
    {
        float rarity = (float)context.nextRandom(100) / 100.0f;
        List<BOPClimates.WeightedBiomeEntry> weightedBiomeEntryList = Lists.newArrayList();
        int selectedBiomeId = originalBiomeId;
        int totalWeight = 0;

        // Find suitable candidates
        for (ModBiomes.WeightedSubBiome entry : ModBiomes.subBiomes.get(originalBiomeId))
        {
            if (entry.rarity >= rarity)
            {
                weightedBiomeEntryList.add(new BOPClimates.WeightedBiomeEntry(entry.weight, entry.biome));
                totalWeight += entry.weight;
            }
        }

        if (totalWeight <= 0)
            return selectedBiomeId;

        int weight = context.nextRandom(totalWeight);
        Iterator<BOPClimates.WeightedBiomeEntry> iterator = weightedBiomeEntryList.iterator();
        BOPClimates.WeightedBiomeEntry item;
        do
        {
            item = iterator.next();
            weight -= item.weight;
        }
        while (weight >= 0);

        selectedBiomeId = BiomeUtil.getBiomeId(item.biome);
        return selectedBiomeId;
    }

    public int getRareSubBiomeId(Context context, int originalBiomeId)
    {
        int mutatedBiomeId = originalBiomeId;
        if (originalBiomeId == DESERT) mutatedBiomeId = DESERT_HILLS;
        else if (originalBiomeId == FOREST) mutatedBiomeId = WOODED_HILLS;
        else if (originalBiomeId == BIRCH_FOREST) mutatedBiomeId = BIRCH_FOREST_HILLS;
        else if (originalBiomeId == DARK_FOREST) mutatedBiomeId = PLAINS;
        else if (originalBiomeId == TAIGA) mutatedBiomeId = TAIGA_HILLS;
        else if (originalBiomeId == GIANT_TREE_TAIGA) mutatedBiomeId = GIANT_TREE_TAIGA_HILLS;
        else if (originalBiomeId == SNOWY_TAIGA) mutatedBiomeId = SNOWY_TAIGA_HILLS;
        //Use BOP orchard instead of vanilla forest
        //else if (originalBiomeId == PLAINS) mutatedBiomeId = context.random(3) == 0 ? WOODED_HILLS : FOREST;
        else if (originalBiomeId == PLAINS && BiomeUtil.exists(BOPBiomes.orchard)) mutatedBiomeId = BiomeUtil.getBiomeId(BOPBiomes.orchard);
        //////////
        else if (originalBiomeId == SNOWY_TUNDRA) mutatedBiomeId = SNOWY_MOUNTAINS;
        else if (originalBiomeId == JUNGLE) mutatedBiomeId = JUNGLE_HILLS;
        else if (originalBiomeId == BAMBOO_JUNGLE) mutatedBiomeId = BAMBOO_JUNGLE_HILLS;
        else if (originalBiomeId == BOPLayerUtil.OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.LUKEWARM_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_LUKEWARM_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.COLD_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_COLD_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.FROZEN_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_FROZEN_OCEAN;
        else if (originalBiomeId == MOUNTAINS) mutatedBiomeId = WOODED_MOUNTAINS;
        else if (originalBiomeId == SAVANNA) mutatedBiomeId = SAVANA_PLATEAU;
        else if (Layers.isSame(originalBiomeId, WOODED_BADLANDS_PLATEAU)) mutatedBiomeId = BADLANDS;
        /*else if ((originalBiomeId == BOPLayerUtil.DEEP_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_LUKEWARM_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_COLD_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_FROZEN_OCEAN) && context.random(3) == 0)
        {
            mutatedBiomeId = context.random(2) == 0 ? PLAINS : FOREST;
        }*/

        return mutatedBiomeId;
    }
}
