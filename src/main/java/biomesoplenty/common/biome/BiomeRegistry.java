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
import com.google.common.collect.Sets;
import com.google.gson.reflect.TypeToken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class BiomeRegistry
{
    private static final String CONFIG_FILE_NAME = "biomes.json";

    private static Map<RegistrationType, List<DeferredRegistration>> deferrances = Maps.newHashMap();

    public static void deferStandardRegistration(BiomeTemplate biome, String name)
    {
        defer(RegistrationType.STANDARD_BIOME, new StandardBiomeRegistrationData(biome, name));
    }

    public static void deferTechnicalBiomeRegistration(BiomeTemplate biome, String name)
    {
        defer(RegistrationType.TECHNICAL_BIOME, new ToggleableStandardBiomeRegistrationData(biome, name, true));
    }

    public static void deferSubBiomeRegistration(Biome parent, Biome child, int weight, float rarity)
    {
        defer(RegistrationType.SUB_BIOME, new SubBiomeRegistrationData(parent, child, weight, rarity));
    }

    public static void deferIslandBiomeRegistration(Biome biome, BOPClimates climate, int weight)
    {
        defer(RegistrationType.ISLAND_BIOME, new SingleClimateRegistrationData(biome, climate, weight));
    }

    public static void deferVanillaBiomeRegistration(Biome biome, BOPClimates climate, int weight)
    {
        defer(RegistrationType.VANILLA_BIOME, new SingleClimateRegistrationData(biome, climate, weight));
    }

    public static void configureStandardBiomes()
    {
        List<DeferredRegistration> standardRegistrations = deferrances.get(RegistrationType.STANDARD_BIOME);
        TreeMap<String, BiomeConfigData.WeightedBiomeEntry> defaultEntries = Maps.newTreeMap();
        Map<String, StandardBiomeRegistrationData> regDataMap = Maps.newHashMap();

        for (DeferredRegistration<StandardBiomeRegistrationData> registration : standardRegistrations)
        {
            StandardBiomeRegistrationData regData = registration.regData;

            // Ignore biomes which don't have any weights set by default
            if (((BiomeTemplate)regData.getBiome()).hasWeights())
            {
                String biomeName = new ResourceLocation(BiomesOPlenty.MOD_ID, regData.getName()).toString();
                Pair<BOPClimates, Integer> primaryWeight = regData.getPrimaryWeight();
                defaultEntries.put(biomeName, new BiomeConfigData.WeightedBiomeEntry(primaryWeight.getValue()));
                regDataMap.put(biomeName, registration.regData);
            }
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.standardBiomeWeights = defaultEntries;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        TreeMap<String, BiomeConfigData.WeightedBiomeEntry> revisedStandardBiomeWeights = Maps.newTreeMap(defaultEntries);

        // Merge the config file with the default values
        for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.standardBiomeWeights.entrySet())
        {
            if (revisedStandardBiomeWeights.containsKey(biomeEntry.getKey()))
            {
                revisedStandardBiomeWeights.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.standardBiomeWeights = revisedStandardBiomeWeights;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.standardBiomeWeights.entrySet())
        {
            String name = biomeEntry.getKey();
            BiomeConfigData.WeightedBiomeEntry weight = biomeEntry.getValue();

            // Replace the default weight map for this biome with those from the config file
            if (regDataMap.containsKey(name))
            {
                StandardBiomeRegistrationData registrationData = regDataMap.get(name);
                registrationData.setPrimaryWeight(weight.weight);
            }
        }
    }

    public static void configureTechnicalBiomes()
    {
        List<DeferredRegistration> biomeRegistrations = deferrances.get(RegistrationType.TECHNICAL_BIOME);
        TreeMap<String, BiomeConfigData.ToggleableBiomeEntry> defaultBiomeEntries = Maps.newTreeMap();

        for (DeferredRegistration<ToggleableStandardBiomeRegistrationData> registration : biomeRegistrations)
        {
            ToggleableStandardBiomeRegistrationData regData = registration.regData;
            String biomeName = new ResourceLocation(BiomesOPlenty.MOD_ID, regData.getName()).toString();
            defaultBiomeEntries.put(biomeName, new BiomeConfigData.ToggleableBiomeEntry(true));
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.technicalBiomeEntries = defaultBiomeEntries;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        TreeMap<String, BiomeConfigData.ToggleableBiomeEntry> revisedBiomeEntries = Maps.newTreeMap(defaultBiomeEntries);

        // Merge the config file with the default values
        for (Map.Entry<String, BiomeConfigData.ToggleableBiomeEntry> biomeEntry : configData.technicalBiomeEntries.entrySet())
        {
            if (revisedBiomeEntries.containsKey(biomeEntry.getKey()))
            {
                revisedBiomeEntries.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.technicalBiomeEntries = revisedBiomeEntries;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (DeferredRegistration<ToggleableStandardBiomeRegistrationData> registration : biomeRegistrations)
        {
            ToggleableStandardBiomeRegistrationData regData = registration.regData;
            String biomeName = new ResourceLocation(BiomesOPlenty.MOD_ID, regData.getName()).toString();

            if (revisedBiomeEntries.containsKey(biomeName))
            {
                BiomeConfigData.ToggleableBiomeEntry entry = revisedBiomeEntries.get(biomeName);

                if (!entry.enabled)
                {
                    registration.regData.setEnabled(false);
                }
            }
        }
    }

    public static void configureSubBiomes()
    {
        List<DeferredRegistration> subBiomeRegistrations = deferrances.get(RegistrationType.SUB_BIOME);
        TreeMap<String, BiomeConfigData.SubBiomeEntry> defaultSubBiomeEntries = Maps.newTreeMap();
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

        TreeMap<String, BiomeConfigData.SubBiomeEntry> revisedSubBiomeEntries = Maps.newTreeMap(defaultSubBiomeEntries);

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
        List<DeferredRegistration> biomeRegistrations = deferrances.get(RegistrationType.ISLAND_BIOME);
        TreeMap<String, BiomeConfigData.ToggleableBiomeEntry> defaultBiomeEntries = Maps.newTreeMap();

        for (DeferredRegistration<SingleClimateRegistrationData> registration : biomeRegistrations)
        {
            SingleClimateRegistrationData regData = registration.regData;
            String biomeName = regData.getBiome().delegate.name().toString();
            defaultBiomeEntries.put(biomeName, new BiomeConfigData.ToggleableBiomeEntry(true));
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.islandBiomeEntries = defaultBiomeEntries;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        TreeMap<String, BiomeConfigData.ToggleableBiomeEntry> revisedBiomeEntries = Maps.newTreeMap(defaultBiomeEntries);

        // Merge the config file with the default values
        for (Map.Entry<String, BiomeConfigData.ToggleableBiomeEntry> biomeEntry : configData.islandBiomeEntries.entrySet())
        {
            if (revisedBiomeEntries.containsKey(biomeEntry.getKey()))
            {
                revisedBiomeEntries.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.islandBiomeEntries = revisedBiomeEntries;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (DeferredRegistration<SingleClimateRegistrationData> registration : biomeRegistrations)
        {
            SingleClimateRegistrationData regData = registration.regData;
            String biomeName = regData.getBiome().delegate.name().toString();

            if (revisedBiomeEntries.containsKey(biomeName))
            {
                BiomeConfigData.ToggleableBiomeEntry entry = revisedBiomeEntries.get(biomeName);

                if (!entry.enabled)
                {
                    registration.regData.setWeight(0);
                }
            }
        }
    }

    public static void configureVanillaBiomes()
    {
        List<DeferredRegistration> biomeRegistrations = deferrances.get(RegistrationType.VANILLA_BIOME);
        TreeMap<String, BiomeConfigData.WeightedBiomeEntry> defaultBiomeEntries = Maps.newTreeMap();
        Map<String, SingleClimateRegistrationData> regDataMap = Maps.newHashMap();

        for (DeferredRegistration<SingleClimateRegistrationData> registration : biomeRegistrations)
        {
            SingleClimateRegistrationData regData = registration.regData;
            String biomeName = registration.regData.getBiome().delegate.name().toString();
            defaultBiomeEntries.put(biomeName, new BiomeConfigData.WeightedBiomeEntry(regData.getWeight()));
            regDataMap.put(biomeName, registration.regData);
        }

        BiomeConfigData defaultConfigData = new BiomeConfigData();
        defaultConfigData.vanillaBiomeEntries = defaultBiomeEntries;
        BiomeConfigData configData = getConfigData(defaultConfigData);

        TreeMap<String, BiomeConfigData.WeightedBiomeEntry> revisedBiomeEntries = Maps.newTreeMap(defaultBiomeEntries);

        // Merge the config file with the default values
        for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.vanillaBiomeEntries.entrySet())
        {
            if (revisedBiomeEntries.containsKey(biomeEntry.getKey()))
            {
                revisedBiomeEntries.put(biomeEntry.getKey(), biomeEntry.getValue());
            }
        }

        // Write back to the config file
        configData.vanillaBiomeEntries = revisedBiomeEntries;
        JsonUtil.writeFile(getConfigFile(), configData);

        for (Map.Entry<String, BiomeConfigData.WeightedBiomeEntry> biomeEntry : configData.vanillaBiomeEntries.entrySet())
        {
            String name = biomeEntry.getKey();
            BiomeConfigData.WeightedBiomeEntry islandBiomeEntry = biomeEntry.getValue();

            // Replace the default values for this biome with those from the config file
            if (regDataMap.containsKey(name))
            {
                SingleClimateRegistrationData registrationData = regDataMap.get(name);
                registrationData.setWeight(islandBiomeEntry.weight);
            }
        }
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

        if (type == RegistrationType.SUB_BIOME)
        {
            Set<Biome> children = Sets.newHashSet();
            deferrances.get(RegistrationType.SUB_BIOME).forEach((reg) -> {
                Biome biome = ((SubBiomeRegistrationData)reg.regData).getChild();
                if (children.contains(biome))
                {
                    throw new RuntimeException(String.format("Sub biome %s cannot be added to multiple parents", biome.delegate.name().toString()));
                }
                children.add(biome);
            });

        }

        for (DeferredRegistration reg : deferrances.get(type))
        {
            reg.register();
        }
    }

    public enum RegistrationType
    {
        STANDARD_BIOME((StandardBiomeRegistrationData data) -> {
            BiomeTemplate biome = (BiomeTemplate)data.getBiome();
            String name = data.getName();

            // Don't register biomes with their weight set to 0, that normally have weights that are non-zero
            if (!biome.getWeightMap().isEmpty() && (data.weightMap.isEmpty() || data.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0))))
            {
                BiomesOPlenty.logger.debug("Weights absent for " + data.getName() + ", disabling...");
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
                    BiomesOPlenty.logger.debug(String.format("%s weight set to %d for climate %s", name, weight, climate.name()));
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
        TECHNICAL_BIOME((ToggleableStandardBiomeRegistrationData data) -> {
            BiomeTemplate biome = (BiomeTemplate)data.getBiome();
            String name = data.getName();

            if (!data.getEnabled())
            {
                BiomesOPlenty.logger.debug("Technical biome " + data.getName() + " is disabled.");
                return;
            }

            biome.setRegistryName(name);
            ForgeRegistries.BIOMES.register(biome);

            if (biome.canSpawnInBiome)
            {
                BiomeManager.addSpawnBiome(biome);
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
                BiomesOPlenty.logger.debug("Weights absent for sub biome" + data.getChild().getName() + ", disabling...");
                return;
            }

            String childName = data.getChild().delegate.name().toString();
            BiomesOPlenty.logger.debug(String.format("Sub biome %s weight set to %d", childName, data.getWeight()));
            ModBiomes.subBiomes.put(Registry.BIOME.getId(data.getParent()), new ModBiomes.WeightedSubBiome(data.getChild(), data.getRarity(), data.getWeight()));
        }),
        ISLAND_BIOME((SingleClimateRegistrationData data) -> {
            if (data.getWeight() == 0)
            {
                BiomesOPlenty.logger.debug("Weights absent for island biome" + data.getBiome().getName() + ", disabling...");
                return;
            }

            String biomeName = data.getBiome().delegate.name().toString();
            BiomesOPlenty.logger.debug(String.format("Island biome %s weight set to %d for climate %s", biomeName, data.getWeight(), data.getClimate().name()));
            ModBiomes.islandBiomeIds.add(Registry.BIOME.getId(data.getBiome()));
            data.getClimate().addIslandBiome(data.getWeight(), data.getBiome());
        }),
        VANILLA_BIOME((SingleClimateRegistrationData data) -> {
            if (data.getWeight() == 0)
            {
                BiomesOPlenty.logger.debug("Weights absent for vanilla biome" + data.getBiome().getName() + ", disabling...");
                return;
            }

            data.getClimate().addBiome(data.getWeight(), data.getBiome());
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

        public StandardBiomeRegistrationData(BiomeTemplate biome, String name)
        {
            super(biome);
            this.name = name;
            this.weightMap = Maps.newHashMap(biome.getWeightMap());
            this.ensureSingleWeight();
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
            this.ensureSingleWeight();
        }

        public Pair<BOPClimates, Integer> getPrimaryWeight()
        {
            List<Pair<BOPClimates, Integer>> pairs = Lists.newArrayList();
            this.weightMap.entrySet().forEach((entry) -> pairs.add(Pair.of(entry.getKey(), entry.getValue())));
            return pairs.get(0);
        }

        public void setPrimaryWeight(int value)
        {
            BOPClimates climate = this.getPrimaryWeight().getKey();
            this.setWeight(climate, value);
        }

        // This limitation is enforced for config file simplicity, and because we don't need it at this time
        private void ensureSingleWeight()
        {
            if (this.weightMap.size() > 1)
            {
                throw new RuntimeException(String.format("%s cannot be assigned to multiple climates!\n%s", new ResourceLocation(BiomesOPlenty.MOD_ID, name).toString(), this.weightMap));
            }
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

    private static class SingleClimateRegistrationData extends RegistrationData
    {
        private final BOPClimates climate;
        private int weight;

        public SingleClimateRegistrationData(Biome biome, BOPClimates climate, int weight)
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

    private static class ToggleableStandardBiomeRegistrationData extends RegistrationData
    {
        private final String name;
        private boolean enabled;

        public ToggleableStandardBiomeRegistrationData(Biome biome, String name, boolean enabled)
        {
            super(biome);
            this.name = name;
            this.enabled = enabled;
        }

        public String getName()
        {
            return this.name;
        }

        public boolean getEnabled()
        {
            return this.enabled;
        }

        public void setEnabled(boolean enabled)
        {
            this.enabled = enabled;
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
