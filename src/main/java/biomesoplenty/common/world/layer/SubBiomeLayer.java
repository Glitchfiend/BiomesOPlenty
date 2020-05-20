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

    private static final int BIRCH_FOREST = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BIRCH_FOREST.getRegistryName());
    private static final int BIRCH_FOREST_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BIRCH_FOREST_HILLS.getRegistryName());
    private static final int DESERT = BiomeUtil.GetBiomeIDFromRegistry(Biomes.DESERT.getRegistryName());
    private static final int DESERT_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.DESERT_HILLS.getRegistryName());
    private static final int MOUNTAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.MOUNTAINS.getRegistryName());
    private static final int WOODED_MOUNTAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.WOODED_MOUNTAINS.getRegistryName());
    private static final int FOREST = BiomeUtil.GetBiomeIDFromRegistry(Biomes.FOREST.getRegistryName());
    private static final int WOODED_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.WOODED_HILLS.getRegistryName());
    private static final int SNOWY_TUNDRA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_TUNDRA.getRegistryName());
    private static final int SNOWY_MOUNTAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_MOUNTAINS.getRegistryName());
    private static final int JUNGLE = BiomeUtil.GetBiomeIDFromRegistry(Biomes.JUNGLE.getRegistryName());
    private static final int JUNGLE_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.JUNGLE_HILLS.getRegistryName());
    private static final int BAMBOO_JUNGLE = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BAMBOO_JUNGLE.getRegistryName());
    private static final int BAMBOO_JUNGLE_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BAMBOO_JUNGLE_HILLS.getRegistryName());
    private static final int BADLANDS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BADLANDS.getRegistryName());
    private static final int WOODED_BADLANDS_PLATEAU = BiomeUtil.GetBiomeIDFromRegistry(Biomes.WOODED_BADLANDS_PLATEAU.getRegistryName());
    private static final int PLAINS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.PLAINS.getRegistryName());
    private static final int GIANT_TREE_TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.GIANT_TREE_TAIGA.getRegistryName());
    private static final int GIANT_TREE_TAIGA_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.GIANT_TREE_TAIGA_HILLS.getRegistryName());
    private static final int DARK_FOREST = BiomeUtil.GetBiomeIDFromRegistry(Biomes.DARK_FOREST.getRegistryName());
    private static final int SAVANNA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SAVANNA.getRegistryName());
    private static final int SAVANA_PLATEAU = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SAVANNA_PLATEAU.getRegistryName());
    private static final int TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.TAIGA.getRegistryName());
    private static final int SNOWY_TAIGA = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_TAIGA.getRegistryName());
    private static final int SNOWY_TAIGA_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.SNOWY_TAIGA_HILLS.getRegistryName());
    private static final int TAIGA_HILLS = BiomeUtil.GetBiomeIDFromRegistry(Biomes.TAIGA_HILLS.getRegistryName());

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
            Biome biome = BiomeUtil.GetBiomeFromID(biomeId);
            if (biome == null || !biome.isMutated()) {
                mutatedBiome = Biome.getMutatedVariant(biome);
                return mutatedBiome == null ? biomeId : BiomeUtil.GetBiomeIDFromRegistry(mutatedBiome.getRegistryName());
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
                mutatedBiome = Biome.getMutatedVariant(BiomeUtil.GetBiomeFromID(mutatedBiomeId));
                mutatedBiomeId = mutatedBiome == null ? biomeId : BiomeUtil.GetBiomeIDFromRegistry(mutatedBiome.getRegistryName());
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

        selectedBiomeId = BiomeUtil.GetBiomeIDFromRegistry(item.biome.getRegistryName());
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
        else if (originalBiomeId == PLAINS && BOPBiomes.orchard.isPresent()) mutatedBiomeId = BiomeUtil.GetBiomeIDFromRegistry(BOPBiomes.orchard.get().getRegistryName());
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
