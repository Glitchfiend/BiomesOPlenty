/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import com.google.common.collect.Maps;
import com.google.gson.annotations.SerializedName;

import java.util.TreeMap;

public class BiomeConfigData
{
    @SerializedName("standard_weights")
    public TreeMap<String, StandardBiomeEntry> standardBiomeWeights = Maps.newTreeMap();

    @SerializedName("technical_biome_toggles")
    public TreeMap<String, TechnicalBiomeEntry> technicalBiomeEntries = Maps.newTreeMap();

    @SerializedName("sub_biome_weights")
    public TreeMap<String, SubBiomeEntry> subBiomeEntries = Maps.newTreeMap();

    @SerializedName("island_biome_toggles")
    public TreeMap<String, IslandBiomeEntry> islandBiomeEntries = Maps.newTreeMap();

    @SerializedName("vanilla_biome_weights")
    public TreeMap<String, VanillaBiomeEntry> vanillaBiomeEntries = Maps.newTreeMap();

    public static abstract class BiomeEntry {
        public abstract boolean shouldRegister();
    };

    public static class WeightedBiomeEntry extends BiomeEntry
    {
        public int weight;

        public WeightedBiomeEntry(int weight)
        {
            this.weight = weight;
        }

        @Override
        public boolean shouldRegister() {
            return this.weight > 0 ? true : false;
        }
    }

    public static class ToggleableBiomeEntry extends BiomeEntry
    {
        public boolean enabled;

        public ToggleableBiomeEntry(boolean enabled)
        {
            this.enabled = enabled;
        }

        @Override
        public boolean shouldRegister() {
            return this.enabled;
        }
    }

    public static class SubBiomeEntry extends BiomeEntry
    {
        public int weight;
        public float rarity;

        public SubBiomeEntry(int weight, float rarity)
        {
            this.weight = weight;
            this.rarity = rarity;
        }

        @Override
        public boolean shouldRegister() {
            return this.weight > 0 ? true : false;
        }
    }

    public static class StandardBiomeEntry extends WeightedBiomeEntry {
        StandardBiomeEntry(int weight) {
            super(weight);
        }
    }

    public static class TechnicalBiomeEntry extends ToggleableBiomeEntry {
        TechnicalBiomeEntry(boolean enabled) {
            super(enabled);
        }
    }

    public static class IslandBiomeEntry extends ToggleableBiomeEntry {
        IslandBiomeEntry(boolean enabled) {
            super(enabled);
        }
    }

    public static class VanillaBiomeEntry extends WeightedBiomeEntry {
        VanillaBiomeEntry(int weight) {
            super(weight);
        }
    }
}
