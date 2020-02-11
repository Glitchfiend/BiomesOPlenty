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
import biomesoplenty.common.util.config.JsonUtil;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class BiomeRegistry
{
    private static final String CONFIG_FILE_NAME = "biome_weights.json";

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

    public static void configureStandardBiomes()
    {
        List<DeferredRegistration> standardRegistrations = deferrances.get(RegistrationType.STANDARD_BIOME);
        Map<String, Map<BOPClimates, Integer>> defaultStandardBiomeWeights = Maps.newHashMap();
        Map<String, StandardBiomeRegistrationData> regDataMap = Maps.newHashMap();

        for (DeferredRegistration<StandardBiomeRegistrationData> registration : standardRegistrations)
        {
            StandardBiomeRegistrationData regData = registration.regData;

            // Ignore biomes which don't have any weights set by default
            if (!((BiomeBOP)regData.getBiome()).getWeightMap().isEmpty())
            {
                defaultStandardBiomeWeights.put(registration.regData.getName(), registration.regData.getWeights());
                regDataMap.put(registration.regData.getName(), registration.regData);
            }
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.standardBiomeWeights = defaultStandardBiomeWeights;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        Map<String, Map<BOPClimates, Integer>> revisedStandardBiomeWeights = Maps.newHashMap(defaultStandardBiomeWeights);

        // Merge the config file with the default values
        for (Map.Entry<String, Map<BOPClimates, Integer>> biomeEntry : configData.standardBiomeWeights.entrySet())
        {
            if (revisedStandardBiomeWeights.containsKey(biomeEntry.getKey()))
            {
                revisedStandardBiomeWeights.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.standardBiomeWeights = revisedStandardBiomeWeights;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (Map.Entry<String, Map<BOPClimates, Integer>> biomeEntry : configData.standardBiomeWeights.entrySet())
        {
            String name = biomeEntry.getKey();
            Map<BOPClimates, Integer> weightMap = biomeEntry.getValue();

            // Replace the default weight map for this biome with those from the config file
            if (regDataMap.containsKey(name))
            {
                StandardBiomeRegistrationData registrationData = regDataMap.get(name);
                registrationData.setWeights(weightMap);
            }
        }
    }

    public static void configureSubBiomes()
    {
        List<DeferredRegistration> subBiomeRegistrations = deferrances.get(RegistrationType.SUB_BIOME);
        Map<String, BiomeConfigData.SubBiomeEntry> defaultSubBiomeEntries = Maps.newHashMap();
        Map<String, SubBiomeRegistrationData> regDataMap = Maps.newHashMap();

        for (DeferredRegistration<SubBiomeRegistrationData> registration : subBiomeRegistrations)
        {
            SubBiomeRegistrationData regData = registration.regData;
            String biomeName = registration.regData.getChild().delegate.name().toString();
            defaultSubBiomeEntries.put(biomeName, new BiomeConfigData.SubBiomeEntry(regData.getWeight(), regData.getRarity()));
            regDataMap.put(biomeName, registration.regData);
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.subBiomeEntries = defaultSubBiomeEntries;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        Map<String, BiomeConfigData.SubBiomeEntry> revisedSubBiomeEntries = Maps.newHashMap(defaultSubBiomeEntries);

        // Merge the config file with the default values
        for (Map.Entry<String, BiomeConfigData.SubBiomeEntry> biomeEntry : configData.subBiomeEntries.entrySet())
        {
            if (revisedSubBiomeEntries.containsKey(biomeEntry.getKey()))
            {
                revisedSubBiomeEntries.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.subBiomeEntries = revisedSubBiomeEntries;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (Map.Entry<String, BiomeConfigData.SubBiomeEntry> biomeEntry : configData.subBiomeEntries.entrySet())
        {
            String name = biomeEntry.getKey();
            BiomeConfigData.SubBiomeEntry subBiomeEntry = biomeEntry.getValue();

            // Replace the default values  for this biome with those from the config file
            if (regDataMap.containsKey(name))
            {
                SubBiomeRegistrationData registrationData = regDataMap.get(name);
                registrationData.setWeight(subBiomeEntry.weight);
                registrationData.setRarity(subBiomeEntry.rarity);
            }
        }
    }

    public static void configureIslandBiomes()
    {
        // Island biomes are currently not configurable due to them being registered multiple times for different climates
//        List<DeferredRegistration> islandBiomeReistrations = deferrances.get(RegistrationType.ISLAND_BIOME);
//        Map<String, BiomeConfigData.IslandBiomeEntry> defaultIslandBiomeEntries = Maps.newHashMap();
//        Map<String, IslandBiomeRegistrationData> regDataMap = Maps.newHashMap();
//
//        for (DeferredRegistration<IslandBiomeRegistrationData> registration : islandBiomeReistrations)
//        {
//            IslandBiomeRegistrationData regData = registration.regData;
//            String biomeName = registration.regData.getBiome().delegate.name().toString();
//            defaultIslandBiomeEntries.put(biomeName, new BiomeConfigData.IslandBiomeEntry(regData.getWeight()));
//            regDataMap.put(biomeName, registration.regData);
//        }
//
//        BiomeConfigData defaultConfigData = new BiomeConfigData();
//        defaultConfigData.islandBiomeEntries = defaultIslandBiomeEntries;
//        BiomeConfigData configData = getConfigData(defaultConfigData);
//
//        Map<String, BiomeConfigData.IslandBiomeEntry> revisedIslandBiomeEntries = Maps.newHashMap(defaultIslandBiomeEntries);
//
//        // Merge the config file with the default values
//        for (Map.Entry<String, BiomeConfigData.IslandBiomeEntry> biomeEntry : configData.islandBiomeEntries.entrySet())
//        {
//            if (revisedIslandBiomeEntries.containsKey(biomeEntry.getKey()))
//            {
//                revisedIslandBiomeEntries.put(biomeEntry.getKey(), biomeEntry.getValue());
//            }
//        }
//
//        // Write back to the config file
//        configData.islandBiomeEntries = revisedIslandBiomeEntries;
//        JsonUtil.writeFile(getConfigFile(), configData);
//
//        for (Map.Entry<String, BiomeConfigData.IslandBiomeEntry> biomeEntry : configData.islandBiomeEntries.entrySet())
//        {
//            String name = biomeEntry.getKey();
//            BiomeConfigData.IslandBiomeEntry islandBiomeEntry = biomeEntry.getValue();
//
//            // Replace the default values for this biome with those from the config file
//            if (regDataMap.containsKey(name))
//            {
//                IslandBiomeRegistrationData registrationData = regDataMap.get(name);
//                registrationData.setWeight(islandBiomeEntry.weight);
//            }
//        }
    }

    private static File getConfigDirFile()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path bopConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "biomesoplenty");
        return bopConfigPath.toFile();
    }

    private static File getConfigFile()
    {
        return new File(getConfigDirFile(), CONFIG_FILE_NAME);
    }

    private static BiomeConfigData getConfigData(BiomeConfigData defaultConfigData)
    {
        BiomeConfigData configData = JsonUtil.getOrCreateConfigFile(getConfigDirFile(), CONFIG_FILE_NAME, defaultConfigData, new TypeToken<BiomeConfigData>(){}.getType());
        return configData;
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
                BiomesOPlenty.logger.info("Weights absent for " + data.getName() + ", disabling...");
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
                    BiomesOPlenty.logger.info(String.format("%s weight set to %d for climate %s", name, weight, climate.name()));
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

            String childName = data.getChild().delegate.name().toString();
            BiomesOPlenty.logger.info(String.format("Sub biome %s weight set to %d", childName, data.getWeight()));
            ModBiomes.subBiomes.put(Registry.BIOME.getId(data.getParent()), new ModBiomes.WeightedSubBiome(data.getChild(), data.getRarity(), data.getWeight()));
        }),
        ISLAND_BIOME((IslandBiomeRegistrationData data) -> {
            if (data.getWeight() == 0)
            {
                BiomesOPlenty.logger.warn("Weights absent for island biome" + data.getBiome().getName() + ", disabling...");
                return;
            }

            String biomeName = data.getBiome().delegate.name().toString();
            BiomesOPlenty.logger.info(String.format("Island biome %s weight set to %d for climate %s", biomeName, data.getWeight(), data.getClimate().name()));
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
        private Map<BOPClimates, Integer> weightMap;

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

        public void setWeights(Map<BOPClimates, Integer> weights)
        {
            this.weightMap = weights;
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
