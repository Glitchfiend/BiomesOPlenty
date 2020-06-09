/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;


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
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BiomeRegistry
{
    private static final String CONFIG_FILE_NAME = "biomes.json";

    private static Map<RegistrationType, List<DeferredRegistration>> deferrances = Maps.newHashMap();
    private static Map<ResourceLocation, Supplier<Biome>> BOPBiomeSuppliers = Maps.newHashMap();
   
    //get the config file now if it exists, and use it for operations.
    private static final BiomeConfigData config = getConfigData(new BiomeConfigData());
    
    private static final ForgeRegistry<Biome> forgeBiomeRegistry = (ForgeRegistry<Biome>) ForgeRegistries.BIOMES;

    
    private static void registerBiome(Biome biome, ResourceLocation name) {
        if(BOPBiomeSuppliers.containsKey(name)) {
            BiomesOPlenty.logger.debug(name.toString() + " has already been registered with BOP");
            return;
        }

        BOPBiomeSuppliers.put(name, () -> biome);

        biome.setRegistryName(name);
        forgeBiomeRegistry.register(biome);
    }
    
    public static Supplier<Biome> getBiomeSupplier(ResourceLocation name) {
        return BOPBiomeSuppliers.getOrDefault(name, () -> null);
    }
    
    public static Biome getBiome(ResourceLocation name) {
        return getBiomeSupplier(name).get();
    }
    
    public static void writeConfig( ) {
        JsonUtil.writeFile(getConfigFile(), config);
    }
    
    public static int getId(Biome biome) {
        return forgeBiomeRegistry.getID(biome);
    }
    
    public static Biome byId(int biomeID) {
        return forgeBiomeRegistry.getValue(biomeID);
    }
    
    public static RegistryObject<Biome> getBiomeRegistryObject(String namespace, String path) {
        ResourceLocation loc = new ResourceLocation(namespace, path);
        return RegistryObject.of(loc, forgeBiomeRegistry);
    }
    
    public static RegistryObject<Biome> getBOPRegistryObject(String name) {
        return getBiomeRegistryObject(BiomesOPlenty.MOD_ID, name);
    }

    public static void deferStandardRegistration(BiomeBOP biome, ResourceLocation name)
    {
        defer(RegistrationType.STANDARD_BIOME, new StandardBiomeRegistrationData(biome, name));
    }

    public static void deferTechnicalBiomeRegistration(BiomeBOP biome, ResourceLocation name)
    {
        defer(RegistrationType.TECHNICAL_BIOME, new ToggleableStandardBiomeRegistrationData(biome, name, true));
    }

    public static void deferSubBiomeRegistration(Biome parent, Biome child, ResourceLocation name, int weight, float rarity)
    {
        defer(RegistrationType.SUB_BIOME, new SubBiomeRegistrationData(parent, child, name, weight, rarity));
    }

    public static void deferIslandBiomeRegistration(Biome biome, ResourceLocation name, BOPClimates climate, int weight)
    {
        defer(RegistrationType.ISLAND_BIOME, new IslandBiomeRegistrationData(biome, name, climate, weight));
    }

    public static void deferVanillaBiomeRegistration(Biome biome, ResourceLocation name, BOPClimates climate, int weight)
    {
        defer(RegistrationType.VANILLA_BIOME, new VanillaBiomeRegistrationData(biome, name, climate, weight));
    }
    
    /* 
     *  Config File Methods
     */
    
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
            data.register();
        }),
        TECHNICAL_BIOME((ToggleableStandardBiomeRegistrationData data) -> {
            data.register();
        }),
        SUB_BIOME((SubBiomeRegistrationData data) -> {
            data.register();
        }),
        ISLAND_BIOME((IslandBiomeRegistrationData data) -> {
            data.register();
        }),
        VANILLA_BIOME((VanillaBiomeRegistrationData data) -> {
            data.register();
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
        public final ResourceLocation name;

        public RegistrationData(Biome biome, ResourceLocation name)
        {
            this.name = name;
            this.biome = biome;
        }

        public Biome getBiome()
        {
            return this.biome;
        }
        
        public abstract boolean canRegister();
        public abstract void register();
    }

    private static class StandardBiomeRegistrationData extends RegistrationData
    {
        private Map<BOPClimates, Integer> weightMap;

        public StandardBiomeRegistrationData(BiomeBOP biome, ResourceLocation name)
        {
            super(biome, name);
            this.weightMap = Maps.newHashMap(biome.getWeightMap());
            this.ensureSingleWeight();
        }

        public ImmutableMap<BOPClimates, Integer> getWeights()
        {
            return ImmutableMap.copyOf(this.weightMap);
        }

        public Pair<BOPClimates, Integer> getPrimaryWeight()
        {
            List<Pair<BOPClimates, Integer>> pairs = Lists.newArrayList();
            this.weightMap.entrySet().forEach((entry) -> pairs.add(Pair.of(entry.getKey(), entry.getValue())));
            return pairs.get(0);
        }
        
        public boolean canRegister() {
            return this.weightMap.isEmpty() ? false : getPrimaryWeight().getValue() > 0;
        }

        // This limitation is enforced for config file simplicity, and because we don't need it at this time
        private void ensureSingleWeight()
        {
            if (this.weightMap.size() > 1)
            {
                throw new RuntimeException(String.format("%s cannot be assigned to multiple climates!\n%s", this.name.toString(), this.weightMap));
            }
        }      
        
        @Override
        public void register() {
            // Get a config entry if one exists, otherwise make one
            BiomeConfigData.StandardBiomeEntry entry = Optional.ofNullable(config.standardBiomeWeights.get(this.name.toString()))
                    .orElse( 
                        new BiomeConfigData.StandardBiomeEntry(
                            this.canRegister() ? this.getPrimaryWeight().getValue() : 0
                        )
                    );
            // override/add the entry
            config.standardBiomeWeights.put(this.name.toString(), entry);
            
            if (!entry.shouldRegister()) {
                BiomesOPlenty.logger.debug("Weights absent for " + this.name.toString() + ", disabling...");
                return;
            }
      
            //Actually schedule the biome for registration
            registerBiome(this.getBiome(), this.name);
            BiomeBOP biome = (BiomeBOP) this.getBiome();
            
            if(biome.canSpawnInBiome) {
                BiomeManager.addSpawnBiome(biome);
            }
            
            for(Entry<BOPClimates, Integer> climates : this.getWeights().entrySet()) {
                if (climates != null && climates.getValue() > 0)
                {
                    BOPClimates climate = climates.getKey();
                    int weight = climates.getValue();
                    BiomesOPlenty.logger.debug(String.format("%s weight set to %d for climate %s", name.toString(), weight, climate.name()));
                    climate.addBiome(weight, biome);
                }
            }
        }
    }

    private static class SubBiomeRegistrationData extends RegistrationData
    {
        private final Biome parent;
        private int weight;
        private float rarity;

        public SubBiomeRegistrationData(Biome parent, Biome child, ResourceLocation name, int weight, float rarity)
        {
            super(child, name);
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

        public float getRarity()
        {
            return this.rarity;
        }
        
        public boolean canRegister() {
            return this.getWeight() > 0;
        }

        @Override
        public void register() {
            BiomeConfigData.SubBiomeEntry entry = Optional.ofNullable(config.subBiomeEntries.get(this.name.toString()))
                    .orElse( new BiomeConfigData.SubBiomeEntry(
                                this.canRegister() ? this.getWeight() : 0,
                                this.canRegister() ? this.getRarity() : 0.0f
                            )
                    );
            
            config.subBiomeEntries.put(this.name.toString(), entry);

            String childName = this.name.toString();
            
            if (!entry.shouldRegister())
            {
                BiomesOPlenty.logger.debug("Weights absent for sub biome" + this.getChild().getRegistryName() + ", disabling...");
                return;
            }
            registerBiome(this.getChild(), this.name);
            
            BiomesOPlenty.logger.debug(String.format("Sub biome %s weight set to %d", childName, this.getWeight()));
            ModBiomes.subBiomes.put(BiomeRegistry.getId(this.getParent()), new ModBiomes.WeightedSubBiome(this.getChild(), entry.rarity, entry.weight));
        }
    }

    private static class ToggleableStandardBiomeRegistrationData extends RegistrationData
    {
        private boolean enabled;

        public ToggleableStandardBiomeRegistrationData(Biome biome, ResourceLocation name, boolean enabled)
        {
            super(biome, name);
            this.enabled = enabled;
        }

        public boolean getEnabled()
        {
            return this.enabled;
        }
        
        public boolean canRegister() {
            return getEnabled();
        }

        @Override
        public void register() {
            BiomeConfigData.TechnicalBiomeEntry entry = Optional.ofNullable(config.technicalBiomeEntries.get(this.name.toString()))
                    .orElse( 
                        new BiomeConfigData.TechnicalBiomeEntry(this.canRegister())
                    );
            
            config.technicalBiomeEntries.put(this.name.toString(), entry);
            
            BiomeBOP biome = (BiomeBOP)this.getBiome();

            if (!entry.shouldRegister())
            {
                BiomesOPlenty.logger.debug("Technical biome " + this.name + " is disabled.");
                return;
            }
            
            BiomesOPlenty.logger.debug("Registering Technical Biome: " + this.name);
            registerBiome(biome, this.name);
            
            if (biome.canSpawnInBiome)
            {
                BiomeManager.addSpawnBiome(biome);
            }
        }
    }

    // Make abstract and make inheritances for the two different use cases as the register function differs based on who is using it.
    private static abstract class SingleClimateRegistrationData extends RegistrationData
    {
        private final BOPClimates climate;
        private int weight;

        public SingleClimateRegistrationData(Biome biome, ResourceLocation name, BOPClimates climate, int weight)
        {
            super(biome, name);
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
        
        public boolean canRegister() {
            return this.getWeight() > 0;
        }
    }
    
    private static class IslandBiomeRegistrationData extends SingleClimateRegistrationData {

        public IslandBiomeRegistrationData(Biome biome, ResourceLocation name, BOPClimates climate, int weight) {
            super(biome, name, climate, weight);
        }
        
        @Override
        public void register() {
            BiomeConfigData.IslandBiomeEntry entry = Optional.ofNullable(config.islandBiomeEntries.get(this.name.toString()))
                    .orElse(
                        new BiomeConfigData.IslandBiomeEntry(this.canRegister())
                    );
            
            config.islandBiomeEntries.put(this.name.toString(), entry);
            
            if (!entry.shouldRegister())
            {
                BiomesOPlenty.logger.debug("Weights absent for island biome" + this.name.toString() + ", disabling...");
                return;
            }

            String biomeName = this.name.toString();
            BiomesOPlenty.logger.debug(String.format("Island biome %s weight set to %d for climate %s", biomeName, this.getWeight(), this.getClimate().name()));
            ModBiomes.islandBiomeIds.add(BiomeRegistry.getId(this.getBiome()));
            this.getClimate().addIslandBiome(this.getWeight(), this.getBiome());
        }
    }
    
    private static class VanillaBiomeRegistrationData extends SingleClimateRegistrationData {

        public VanillaBiomeRegistrationData(Biome biome, ResourceLocation name, BOPClimates climate, int weight) {
            super(biome, name, climate, weight);
        }
        @Override
        public void register() {
            BiomeConfigData.VanillaBiomeEntry entry = Optional.ofNullable(config.vanillaBiomeEntries.get(this.name.toString()))
                    .orElse(
                        new BiomeConfigData.VanillaBiomeEntry(this.getWeight())
                    );
            
            config.vanillaBiomeEntries.put(this.name.toString(), entry);
            
            if (!entry.shouldRegister())
            {
                BiomesOPlenty.logger.debug("Weights absent for vanilla biome" + this.name + ", disabling...");
                return;
            }

            this.getClimate().addBiome(this.getWeight(), this.getBiome());
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
