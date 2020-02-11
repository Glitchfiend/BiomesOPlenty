/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class BiomeRegistry
{
    private static Map<RegistrationType, List<DeferredRegistration>> deferrances = Maps.newHashMap();

    public static void deferStandardRegistration(BiomeBOP biome, String name)
    {
        defer(RegistrationType.STANDARD_BIOME, new StandardBiomeRegistrationData(biome, name));
    }

    public static void deferSubBiomeRegistration(Biome parent, Biome child, int weight, float rarity)
    {
        defer(RegistrationType.SUB_BIOME, new SubBiomeRegistrationData(parent, child, weight, rarity));
    }

    public static void deferIslandBiomeRegistration(Biome biome, BOPClimates climate, int weight)
    {
        defer(RegistrationType.ISLAND_BIOME, new IslandBiomeRegistrationData(biome, climate, weight));
    }

    private static <T extends RegistrationData> void defer(RegistrationType type, T data)
    {
        if (!deferrances.containsKey(type))
            deferrances.put(type, Lists.newArrayList());

        List<DeferredRegistration> list = deferrances.get(type);
        list.add(new DeferredRegistration(type.regFunc, data));
    }

    public static void finalizeRegistrations(RegistrationType type)
    {
        if (!deferrances.containsKey(type))
            return;

        for (DeferredRegistration reg : deferrances.get(type))
        {
            reg.register();
        }
    }

    public enum RegistrationType
    {
        STANDARD_BIOME((StandardBiomeRegistrationData data) -> {
            BiomeBOP biome = (BiomeBOP)data.getBiome();
            String name = data.getName();

            // Don't register biomes with their weight set to 0, that normally have weights that are non-zero
            if (!biome.getWeightMap().isEmpty() && (data.weightMap.isEmpty() || data.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0))))
            {
                BiomesOPlenty.logger.warn("Weights absent for " + data.getName() + ", disabling...");
                return;
            }

            biome.setRegistryName(name);
            ForgeRegistries.BIOMES.register(biome);

            if (biome.canSpawnInBiome)
            {
                BiomeManager.addSpawnBiome(biome);
            }

            for (Map.Entry<BOPClimates, Integer> entry : data.getWeights().entrySet())
            {
                if (entry != null && entry.getValue() > 0)
                {
                    BOPClimates climate = entry.getKey();
                    int weight = entry.getValue();
                    climate.addBiome(weight, biome);
                }
            }

            // Set field in BOPBiomes
            try
            {
                BOPBiomes.class.getField(name).set(null, Optional.of(biome));
            }
            catch (Exception e)
            {
                throw new RuntimeException("Failed to set biome field " + name, e);
            }
        }),
        SUB_BIOME((SubBiomeRegistrationData data) -> {
            if (data.getWeight() == 0)
            {
                BiomesOPlenty.logger.warn("Weights absent for sub biome" + data.getChild().getName() + ", disabling...");
                return;
            }

            ModBiomes.subBiomes.put(Registry.BIOME.getId(data.getParent()), new ModBiomes.WeightedSubBiome(data.getChild(), data.getRarity(), data.getWeight()));
        }),
        ISLAND_BIOME((IslandBiomeRegistrationData data) -> {
            if (data.getWeight() == 0)
            {
                BiomesOPlenty.logger.warn("Weights absent for island biome" + data.getBiome().getName() + ", disabling...");
                return;
            }

            ModBiomes.islandBiomeIds.add(Registry.BIOME.getId(data.getBiome()));
            data.getClimate().addIslandBiome(data.getWeight(), data.getBiome());
        });

        public final Consumer<? extends RegistrationData> regFunc;

        RegistrationType(Consumer<? extends RegistrationData> regFunc)
        {
            this.regFunc = regFunc;
        }
    }

    private static abstract class RegistrationData
    {
        private final Biome biome;

        public RegistrationData(Biome biome)
        {
            this.biome = biome;
        }

        public Biome getBiome()
        {
            return this.biome;
        }
    }

    private static class StandardBiomeRegistrationData extends RegistrationData
    {
        private final String name;
        private final Map<BOPClimates, Integer> weightMap;

        public StandardBiomeRegistrationData(BiomeBOP biome, String name)
        {
            super(biome);
            this.name = name;
            this.weightMap = Maps.newHashMap(biome.getWeightMap());
        }

        public String getName()
        {
            return this.name;
        }

        public ImmutableMap<BOPClimates, Integer> getWeights()
        {
            return ImmutableMap.copyOf(this.weightMap);
        }

        public int getWeight(BOPClimates climate)
        {
            return this.weightMap.get(climate);
        }

        public void setWeight(BOPClimates climate, int weight)
        {
            this.weightMap.put(climate, weight);
        }
    }

    private static class SubBiomeRegistrationData extends RegistrationData
    {
        private final Biome parent;
        private int weight;
        private float rarity;

        public SubBiomeRegistrationData(Biome parent, Biome child, int weight, float rarity)
        {
            super(child);
            this.parent = parent;
            this.weight = weight;
            this.rarity = rarity;
        }

        public Biome getParent()
        {
            return this.parent;
        }

        public Biome getChild()
        {
            return this.getBiome();
        }

        public int getWeight()
        {
            return this.weight;
        }

        public void setWeight(int weight)
        {
            this.weight = weight;
        }

        public float getRarity()
        {
            return this.rarity;
        }

        public void setRarity(float rarity)
        {
            this.rarity = rarity;
        }
    }

    private static class IslandBiomeRegistrationData extends RegistrationData
    {
        private final BOPClimates climate;
        private int weight;

        public IslandBiomeRegistrationData(Biome biome, BOPClimates climate, int weight)
        {
            super(biome);
            this.climate = climate;
            this.weight = weight;
        }

        public BOPClimates getClimate()
        {
            return this.climate;
        }

        public int getWeight()
        {
            return this.weight;
        }

        public void setWeight(int weight)
        {
            this.weight = weight;
        }
    }

    private static class DeferredRegistration<T extends RegistrationData>
    {
        private final Consumer<T> regFunc;
        private final T regData;

        public DeferredRegistration(Consumer<T> regFunc, T regData)
        {
            this.regFunc = regFunc;
            this.regData = regData;
        }

        public void register()
        {
            this.regFunc.accept(this.regData);
        }
    }
}
