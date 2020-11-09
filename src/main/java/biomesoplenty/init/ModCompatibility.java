/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Lists;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

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
                    .filter(biomeEntry -> !biomeEntry.getKey().getRegistryName().getNamespace().equals("minecraft"))
                    .collect(Collectors.toList());

            // calls remapBiomeToBoP on each modded biome and its weight.
            moddedBiomesInType.stream().forEach(biomeEntry -> remapBiomeToBoP(biomeEntry.getKey(), type, biomeEntry.weight));
        }
    }

    // TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(RegistryKey<Biome> biome, BiomeManager.BiomeType type, int weight)
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