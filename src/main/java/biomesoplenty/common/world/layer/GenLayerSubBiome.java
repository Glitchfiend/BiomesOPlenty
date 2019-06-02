/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;


import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.world.BOPLayerUtil;
import biomesoplenty.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

public enum GenLayerSubBiome implements IAreaTransformer2, IDimOffset1Transformer
{
    INSTANCE;

    private static final int BIRCH_FOREST = IRegistry.BIOME.getId(Biomes.BIRCH_FOREST);
    private static final int BIRCH_FOREST_HILLS = IRegistry.BIOME.getId(Biomes.BIRCH_FOREST_HILLS);
    private static final int DESERT = IRegistry.BIOME.getId(Biomes.DESERT);
    private static final int DESERT_HILLS = IRegistry.BIOME.getId(Biomes.DESERT_HILLS);
    private static final int MOUNTAINS = IRegistry.BIOME.getId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = IRegistry.BIOME.getId(Biomes.WOODED_MOUNTAINS);
    private static final int FOREST = IRegistry.BIOME.getId(Biomes.FOREST);
    private static final int WOODED_HILLS = IRegistry.BIOME.getId(Biomes.WOODED_HILLS);
    private static final int SNOWY_TUNDRA = IRegistry.BIOME.getId(Biomes.SNOWY_TUNDRA);
    private static final int SNOWY_MOUNTAINS = IRegistry.BIOME.getId(Biomes.SNOWY_MOUNTAINS);
    private static final int JUNGLE = IRegistry.BIOME.getId(Biomes.JUNGLE);
    private static final int JUNGLE_HILLS = IRegistry.BIOME.getId(Biomes.JUNGLE_HILLS);
    private static final int BADLANDS = IRegistry.BIOME.getId(Biomes.BADLANDS);
    private static final int WOODED_BADLANDS_PLATEAU = IRegistry.BIOME.getId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int PLAINS = IRegistry.BIOME.getId(Biomes.PLAINS);
    private static final int GIANT_TREE_TAIGA = IRegistry.BIOME.getId(Biomes.GIANT_TREE_TAIGA);
    private static final int GIANT_TREE_TAIGA_HILLS = IRegistry.BIOME.getId(Biomes.GIANT_TREE_TAIGA_HILLS);
    private static final int DARK_FOREST = IRegistry.BIOME.getId(Biomes.DARK_FOREST);
    private static final int SAVANNA = IRegistry.BIOME.getId(Biomes.SAVANNA);
    private static final int SAVANA_PLATEAU = IRegistry.BIOME.getId(Biomes.SAVANNA_PLATEAU);
    private static final int TAIGA = IRegistry.BIOME.getId(Biomes.TAIGA);
    private static final int SNOWY_TAIGA = IRegistry.BIOME.getId(Biomes.SNOWY_TAIGA);
    private static final int SNOWY_TAIGA_HILLS = IRegistry.BIOME.getId(Biomes.SNOWY_TAIGA_HILLS);
    private static final int TAIGA_HILLS = IRegistry.BIOME.getId(Biomes.TAIGA_HILLS);

    @Override
    public int apply(IContext context, AreaDimension dimension, IArea biomeArea, IArea riverAndSubBiomesInitArea, int x, int z)
    {
        int biomeId = biomeArea.getValue(x + 1, z + 1);
        int initVal = riverAndSubBiomesInitArea.getValue(x + 1, z + 1);

        int subBiomeType = (initVal - 2) % 29;
        boolean tryRareHillsBiome = subBiomeType == 0;
        boolean tryRareBiome = subBiomeType == 1;

        Biome mutatedBiome;
        if (!BOPLayerUtil.isShallowOcean(biomeId) && initVal >= 2 && tryRareBiome)
        {
            Biome biome = IRegistry.BIOME.get(biomeId);
            if (biome == null || !biome.isMutation()) {
                mutatedBiome = Biome.getMutationForBiome(biome);
                return mutatedBiome == null ? biomeId : IRegistry.BIOME.getId(mutatedBiome);
            }
        }

        if (context.random(3) == 0 || tryRareHillsBiome)
        {
            int mutatedBiomeId = this.getCommonSubBiomeId(context, biomeId);

            if (mutatedBiomeId != biomeId)
                return mutatedBiomeId;

            mutatedBiomeId = this.getRareSubBiomeId(context, biomeId);

            if (subBiomeType == 0 && mutatedBiomeId != biomeId)
            {
                mutatedBiome = Biome.getMutationForBiome(IRegistry.BIOME.get(mutatedBiomeId));
                mutatedBiomeId = mutatedBiome == null ? biomeId : IRegistry.BIOME.getId(mutatedBiome);
            }

            if (mutatedBiomeId != biomeId)
            {
                int surroundingSimilarCount = 0;
                if (LayerUtil.areBiomesSimilar(biomeArea.getValue(x + 1, z + 0), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.areBiomesSimilar(biomeArea.getValue(x + 2, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.areBiomesSimilar(biomeArea.getValue(x + 0, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.areBiomesSimilar(biomeArea.getValue(x + 1, z + 2), biomeId))
                    ++surroundingSimilarCount;

                if (surroundingSimilarCount >= 3)
                    return mutatedBiomeId;
            }
        }

        return biomeId;
    }

    public int getCommonSubBiomeId(IContext context, int originalBiomeId)
    {
        float rarity = (float)context.random(100) / 100.0f;
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

        int weight = context.random(totalWeight);
        Iterator<BOPClimates.WeightedBiomeEntry> iterator = weightedBiomeEntryList.iterator();
        BOPClimates.WeightedBiomeEntry item;
        do
        {
            item = iterator.next();
            weight -= item.weight;
        }
        while (weight >= 0);

        selectedBiomeId = IRegistry.BIOME.getId(item.biome);
        return selectedBiomeId;
    }

    public int getRareSubBiomeId(IContext context, int originalBiomeId)
    {
        int mutatedBiomeId = originalBiomeId;
        if (originalBiomeId == DESERT) mutatedBiomeId = DESERT_HILLS;
        else if (originalBiomeId == FOREST) mutatedBiomeId = WOODED_HILLS;
        else if (originalBiomeId == BIRCH_FOREST) mutatedBiomeId = BIRCH_FOREST_HILLS;
        else if (originalBiomeId == DARK_FOREST) mutatedBiomeId = PLAINS;
        else if (originalBiomeId == TAIGA) mutatedBiomeId = TAIGA_HILLS;
        else if (originalBiomeId == GIANT_TREE_TAIGA) mutatedBiomeId = GIANT_TREE_TAIGA_HILLS;
        else if (originalBiomeId == SNOWY_TAIGA) mutatedBiomeId = SNOWY_TAIGA_HILLS;
        else if (originalBiomeId == PLAINS) mutatedBiomeId = context.random(3) == 0 ? WOODED_HILLS : FOREST;
        else if (originalBiomeId == SNOWY_TUNDRA) mutatedBiomeId = SNOWY_MOUNTAINS;
        else if (originalBiomeId == JUNGLE) mutatedBiomeId = JUNGLE_HILLS;
        else if (originalBiomeId == BOPLayerUtil.OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.LUKEWARM_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_LUKEWARM_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.COLD_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_COLD_OCEAN;
        else if (originalBiomeId == BOPLayerUtil.FROZEN_OCEAN) mutatedBiomeId = BOPLayerUtil.DEEP_FROZEN_OCEAN;
        else if (originalBiomeId == MOUNTAINS) mutatedBiomeId = WOODED_MOUNTAINS;
        else if (originalBiomeId == SAVANNA) mutatedBiomeId = SAVANA_PLATEAU;
        else if (LayerUtil.areBiomesSimilar(originalBiomeId, WOODED_BADLANDS_PLATEAU)) mutatedBiomeId = BADLANDS;
        else if ((originalBiomeId == BOPLayerUtil.DEEP_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_LUKEWARM_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_COLD_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_FROZEN_OCEAN) && context.random(3) == 0)
        {
            mutatedBiomeId = context.random(2) == 0 ? PLAINS : FOREST;
        }

        return mutatedBiomeId;
    }
}
