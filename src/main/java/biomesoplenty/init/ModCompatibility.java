/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;


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
            /*
             * Get non-minecraft entries by filtering based on each entries registry namespace value,
             * then create a wrapped entry to remap to a BOP climate.
             */
            BiomeManager
                .getBiomes(type)
                .stream()
                .filter(entry -> entry.biome.getRegistryName().getNamespace() != "minecraft")
                .forEach(filteredEntry ->
                    {
                        final WrappedBiomeEntry wrappedEntry = new WrappedBiomeEntry(filteredEntry);
                        remapBiomeToBoP(wrappedEntry.biomeEntry.biome, type, wrappedEntry.biomeEntry.weight);
                    });
        }
    }

    // TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(Biome biome, BiomeManager.BiomeType type, int weight)
    {
        /*  If any of our climates already have the biome (from a mod using our api), then skip this biome. */
        
        if(BOPClimates.getRegisteredBiomes().contains(biome))
        {
            return;
        }

        /*
         * Support for nether biomes as the NETHER climate has a null BiomeType, making it impossible for modded nether biomes
         * to be added to the climate by comparing BiomeTypes.
         */
        
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER))
        {
            BOPClimates.NETHER.addBiome(weight, biome);
            return;
        }

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

            return other.biomeEntry.weight == this.biomeEntry.weight && other.biomeEntry.biome == this.biomeEntry.biome;
        }
    }
}
