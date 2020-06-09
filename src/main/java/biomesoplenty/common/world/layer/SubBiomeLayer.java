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
import biomesoplenty.common.biome.BiomeRegistry;
import biomesoplenty.common.world.BOPLayerUtil;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

import java.util.Iterator;
import java.util.List;

public enum SubBiomeLayer implements IAreaTransformer2, IDimOffset1Transformer
{
    INSTANCE;

    private static final int BIRCH_FOREST = BiomeRegistry.getId(Biomes.BIRCH_FOREST);
    private static final int BIRCH_FOREST_HILLS = BiomeRegistry.getId(Biomes.BIRCH_FOREST_HILLS);
    private static final int DESERT = BiomeRegistry.getId(Biomes.DESERT);
    private static final int DESERT_HILLS = BiomeRegistry.getId(Biomes.DESERT_HILLS);
    private static final int MOUNTAINS = BiomeRegistry.getId(Biomes.MOUNTAINS);
    private static final int WOODED_MOUNTAINS = BiomeRegistry.getId(Biomes.WOODED_MOUNTAINS);
    private static final int FOREST = BiomeRegistry.getId(Biomes.FOREST);
    private static final int WOODED_HILLS = BiomeRegistry.getId(Biomes.WOODED_HILLS);
    private static final int SNOWY_TUNDRA = BiomeRegistry.getId(Biomes.SNOWY_TUNDRA);
    private static final int SNOWY_MOUNTAINS = BiomeRegistry.getId(Biomes.SNOWY_MOUNTAINS);
    private static final int JUNGLE = BiomeRegistry.getId(Biomes.JUNGLE);
    private static final int JUNGLE_HILLS = BiomeRegistry.getId(Biomes.JUNGLE_HILLS);
    private static final int BAMBOO_JUNGLE = BiomeRegistry.getId(Biomes.BAMBOO_JUNGLE);
    private static final int BAMBOO_JUNGLE_HILLS = BiomeRegistry.getId(Biomes.BAMBOO_JUNGLE_HILLS);
    private static final int BADLANDS = BiomeRegistry.getId(Biomes.BADLANDS);
    private static final int WOODED_BADLANDS_PLATEAU = BiomeRegistry.getId(Biomes.WOODED_BADLANDS_PLATEAU);
    private static final int PLAINS = BiomeRegistry.getId(Biomes.PLAINS);
    private static final int GIANT_TREE_TAIGA = BiomeRegistry.getId(Biomes.GIANT_TREE_TAIGA);
    private static final int GIANT_TREE_TAIGA_HILLS = BiomeRegistry.getId(Biomes.GIANT_TREE_TAIGA_HILLS);
    private static final int DARK_FOREST = BiomeRegistry.getId(Biomes.DARK_FOREST);
    private static final int SAVANNA = BiomeRegistry.getId(Biomes.SAVANNA);
    private static final int SAVANA_PLATEAU = BiomeRegistry.getId(Biomes.SAVANNA_PLATEAU);
    private static final int TAIGA = BiomeRegistry.getId(Biomes.TAIGA);
    private static final int SNOWY_TAIGA = BiomeRegistry.getId(Biomes.SNOWY_TAIGA);
    private static final int SNOWY_TAIGA_HILLS = BiomeRegistry.getId(Biomes.SNOWY_TAIGA_HILLS);
    private static final int TAIGA_HILLS = BiomeRegistry.getId(Biomes.TAIGA_HILLS);

    @Override
    public int applyPixel(INoiseRandom context, IArea biomeArea, IArea riverAndSubBiomesInitArea, int x, int z)
    {
        int biomeId = biomeArea.get(this.getParentX(x + 1), this.getParentY(z + 1));
        int initVal = riverAndSubBiomesInitArea.get(this.getParentX(x + 1), this.getParentY(z + 1));

        int subBiomeType = (initVal - 2) % 29;
        boolean tryRareHillsBiome = subBiomeType == 0;
        boolean tryRareBiome = subBiomeType == 1;

        Biome mutatedBiome;
        if (!BOPLayerUtil.isShallowOcean(biomeId) && initVal >= 2 && tryRareBiome)
        {
            Biome biome = BiomeRegistry.byId(biomeId);
            if (biome == null || !biome.isMutated()) {
                mutatedBiome = Biome.getMutatedVariant(biome);
                return mutatedBiome == null ? biomeId : BiomeRegistry.getId(mutatedBiome);
            }
        }

        if (context.nextRandom(3) == 0 || tryRareHillsBiome)
        {
            int mutatedBiomeId = this.getCommonSubBiomeId(context, biomeId);

            if (mutatedBiomeId != biomeId)
                return mutatedBiomeId;

            mutatedBiomeId = this.getRareSubBiomeId(context, biomeId);

            if (subBiomeType == 0 && mutatedBiomeId != biomeId)
            {
                mutatedBiome = Biome.getMutatedVariant(BiomeRegistry.byId(mutatedBiomeId));
                mutatedBiomeId = mutatedBiome == null ? biomeId : BiomeRegistry.getId(mutatedBiome);
            }

            if (mutatedBiomeId != biomeId)
            {
                int surroundingSimilarCount = 0;
                if (LayerUtil.isSame(biomeArea.get(x + 1, z + 0), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.isSame(biomeArea.get(x + 2, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.isSame(biomeArea.get(x + 0, z + 1), biomeId))
                    ++surroundingSimilarCount;
                if (LayerUtil.isSame(biomeArea.get(x + 1, z + 2), biomeId))
                    ++surroundingSimilarCount;

                if (surroundingSimilarCount >= 3)
                    return mutatedBiomeId;
            }
        }

        return biomeId;
    }

    public int getCommonSubBiomeId(INoiseRandom context, int originalBiomeId)
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

        selectedBiomeId = BiomeRegistry.getId(item.biome);
        return selectedBiomeId;
    }

    public int getRareSubBiomeId(INoiseRandom context, int originalBiomeId)
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
        else if (originalBiomeId == PLAINS && BOPBiomes.orchard.isPresent()) mutatedBiomeId = BiomeRegistry.getId(BOPBiomes.orchard.get());
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
        else if (LayerUtil.isSame(originalBiomeId, WOODED_BADLANDS_PLATEAU)) mutatedBiomeId = BADLANDS;
        /*else if ((originalBiomeId == BOPLayerUtil.DEEP_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_LUKEWARM_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_COLD_OCEAN || originalBiomeId == BOPLayerUtil.DEEP_FROZEN_OCEAN) && context.random(3) == 0)
        {
            mutatedBiomeId = context.random(2) == 0 ? PLAINS : FOREST;
        }*/

        return mutatedBiomeId;
    }
}
