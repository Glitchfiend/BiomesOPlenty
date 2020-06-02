/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraftforge.common.BiomeManager;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableList;

public class ModCompatibility
{
	
	// Set containing remapped mod biomes.
    private static final Set<WrappedBiomeEntry> MOD_BIOMES = new HashSet<>();
	
    public static void setup()
    {
        copyModBiomeWeights();
    }

    private static void copyModBiomeWeights()
    {
        for (BiomeManager.BiomeType type : BiomeManager.BiomeType.values())
        {
            for(BiomeManager.BiomeEntry entry : BiomeManager.getBiomes(type))
            {
                if(!isMinecraft(entry))
                    remapBiomeToBoP(entry, type);
            }
        }
    }

    // TODO: Make this more accurate, possibly analyze heights, temps, rainfall and/or biome dictionary tags
    private static void remapBiomeToBoP(BiomeManager.BiomeEntry entry, BiomeManager.BiomeType type)
    {
        if(!mapEntry(entry))
            return;
        
        for (BOPClimates climate : BOPClimates.values())
        {
            if (climate.biomeType == type)
            {
                climate.addBiome(entry.weight, entry.biome);
            }
        }
    }
    
    /**
     * Determines if the entry is a Minecraft entry.
     * 
     * Does so by comparing the biomes registry namespace to the minecraft default namespace.
     * 
     * @param entry
     * @return true if the entry belongs to Minecraft, false otherwise
     */
    private static Boolean isMinecraft(BiomeManager.BiomeEntry entry)
    {
        return entry.biome.getRegistryName().getNamespace() == "minecraft";
    }
    
    // Debugging function to ensure mod biomes are mapped and added
    public static ImmutableList<WrappedBiomeEntry> getMappedModBiomes() {
        return ImmutableList.copyOf(MOD_BIOMES);
    }
    /**
     * Attempts to add element to the static Set of WrappeBiomeEntry's
     * <p>
     * returns true if successful and false if failure based on java.util.Set.add() behavior.
     * 
     * @param entry
     * @return true if the set did not already contain the element
     */
    private static Boolean mapEntry(BiomeManager.BiomeEntry entry)
    {
        return MOD_BIOMES.add(new WrappedBiomeEntry(entry));
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
