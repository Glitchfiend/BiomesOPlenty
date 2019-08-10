/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.function.Function;

public class ModCompatibility
{
    public static void setup()
    {
        copyModBiomeWeights();
    }

    private static void copyModBiomeWeights()
    {
        try
        {
            // An array containing lists of default biome entries for only standard BiomeTypes
            List<BiomeManager.BiomeEntry>[] vanillaBiomes = (List<BiomeManager.BiomeEntry>[]) ObfuscationReflectionHelper.findMethod(BiomeManager.class, "setupBiomes").invoke(null);

            for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values())
            {
                // Creates a mutable version of the current biome type's biome array and wraps entries to support .equals()
                List<WrappedBiomeEntry> entries = Lists.newArrayList();
                List<WrappedBiomeEntry> vanillaEntries = Lists.newArrayList();

                for (BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(type))
                {
                    entries.add(new WrappedBiomeEntry(entry));
                }

                for (BiomeManager.BiomeEntry entry : vanillaBiomes[type.ordinal()])
                {
                    vanillaEntries.add(new WrappedBiomeEntry(entry));
                }

                //Remove all default biomes from the entries list
                entries.removeAll(vanillaEntries);

                for (WrappedBiomeEntry wrappedEntry : entries)
                {
                    remapBiomeToBoP(wrappedEntry.biomeEntry.biome, type, wrappedEntry.biomeEntry.itemWeight);
                }
            }
        }
        catch (Exception e)
        {
            BiomesOPlenty.logger.error("An error has occurred whilst copying mod biomes");
            e.printStackTrace();
            return;
        }
    }

    // TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(Biome biome, BiomeManager.BiomeType type, int weight)
    {
        for (BOPClimates climate : BOPClimates.values())
        {
            if (climate.biomeType == type)
            {
                climate.addBiome(weight, biome);
            }
        }
    }

    /**
     * Provides working equals functionality for BiomeEntries
     * */
    private static class WrappedBiomeEntry
    {
        private BiomeManager.BiomeEntry biomeEntry;

        private WrappedBiomeEntry(BiomeManager.BiomeEntry biomeEntry)
        {
            this.biomeEntry = biomeEntry;
        }

        @Override
        public boolean equals(Object input)
        {
            if (input == null) return false;
            if (input == this) return true;
            if (!(input instanceof WrappedBiomeEntry)) return false;

            WrappedBiomeEntry other = (WrappedBiomeEntry)input;

            return other.biomeEntry.itemWeight == this.biomeEntry.itemWeight && other.biomeEntry.biome == this.biomeEntry.biome;
        }
    }
}
