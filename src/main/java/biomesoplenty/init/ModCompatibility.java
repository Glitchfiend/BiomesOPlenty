/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeManager;

import java.util.List;
import java.util.stream.Collectors;

public class ModCompatibility
{
    public static void setup()
    {
        copyModBiomeWeights();
    }

    private static void copyModBiomeWeights()
    {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values())
        {
            // Removes vanilla entries by checking if the biome namespace is not "minecraft"
            // If a mod makes their biome with minecraft namespace, they need a bug report as that's a big no-no.
            List<BiomeManager.BiomeEntry> moddedBiomesInType = BiomeManager.getBiomes(type).stream()
                    .filter(biomeEntry -> !biomeEntry.getKey().location().getNamespace().equals("minecraft"))
                    .collect(Collectors.toList());

            // calls remapBiomeToBoP on each modded biome and its weight.
            moddedBiomesInType.stream().forEach(biomeEntry -> remapBiomeToBoP(biomeEntry.getKey(), type, biomeEntry.weight));
        }
    }

    // TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(ResourceKey<Biome> biome, BiomeManager.BiomeType type, int weight)
    {
        /* If any of our climates already have the biome (from a mod using our api), then skip this biome */
        for (BOPClimates climate : BOPClimates.values())
        {
            List<BOPClimates.WeightedBiomeEntry> entries = Lists.newArrayList();
            entries.addAll(climate.getLandBiomes());
            entries.addAll(climate.getIslandBiomes());

            for (BOPClimates.WeightedBiomeEntry entry : entries)
            {
                if (entry.biome == biome)
                {
                    return;
                }
            }
        }


        for (BOPClimates climate : BOPClimates.values())
        {
            if (climate.biomeType == type)
            {
                climate.addBiome(weight, biome);
            }
        }
    }
}