/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BiomeConfigData
{
    @SerializedName("standard_weights")
    public Map<String, StandardBiomeEntry> standardBiomeWeights = Maps.newHashMap();

    @SerializedName("sub_biome_weights")
    public Map<String, SubBiomeEntry> subBiomeEntries = Maps.newHashMap();

    //@SerializedName("island_biome_weights")
    //public Map<String, IslandBiomeEntry> islandBiomeEntries = Maps.newHashMap();

    public static class StandardBiomeEntry
    {
        public int weight;

        public StandardBiomeEntry(int weight)
        {
            this.weight = weight;
        }
    }

    public static class SubBiomeEntry
    {
        public int weight;
        public float rarity;

        public SubBiomeEntry(int weight, float rarity)
        {
            this.weight = weight;
            this.rarity = rarity;
        }
    }

    public static class IslandBiomeEntry
    {
        public int weight;

        public IslandBiomeEntry(int weight)
        {
            this.weight = weight;
        }
    }
}
