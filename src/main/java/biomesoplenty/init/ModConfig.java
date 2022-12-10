/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.config.JsonUtil;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ModConfig
{
    private static final String BIOME_CONFIG_FILE_NAME = "biome_toggles.json";
    private static Map<String, Boolean> biomeToggles;
    private static final TreeMap<String, Boolean> defaultBiomeToggles = Maps.newTreeMap();

    public static class GenerationConfig
    {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.IntValue bopPrimaryOverworldRegionWeight;
        public static final ForgeConfigSpec.IntValue bopSecondaryOverworldRegionWeight;
        public static final ForgeConfigSpec.IntValue bopNetherRegionWeight;
        public static final ForgeConfigSpec.IntValue bopOverworldRareRegionWeight;
        public static final ForgeConfigSpec.IntValue bopNetherRareRegionWeight;
        static
        {
            BUILDER.comment("World generation related options.");
            BUILDER.push("overworld");
            bopPrimaryOverworldRegionWeight = BUILDER.comment("The weighting of primary bop biome regions in the overworld.").defineInRange("bop_primary_overworld_region_weight", 10, 0, Integer.MAX_VALUE);
            bopSecondaryOverworldRegionWeight = BUILDER.comment("The weighting of secondary bop biome regions in the overworld.").defineInRange("bop_secondary_overworld_region_weight", 8, 0, Integer.MAX_VALUE);
            bopNetherRegionWeight = BUILDER.comment("The weighting of bop biome regions in the nether.").defineInRange("bop_nether_region_weight", 13, 0, Integer.MAX_VALUE);
            bopOverworldRareRegionWeight = BUILDER.comment("The weighting of rare bop biome regions in the overworld.").defineInRange("bop_overworld_rare_region_weight", 2, 0, Integer.MAX_VALUE);
            bopNetherRareRegionWeight = BUILDER.comment("The weighting of rare bop biome regions in the nether.").defineInRange("bop_nether_rare_region_weight", 2, 0, Integer.MAX_VALUE);
            BUILDER.pop();

            SPEC = BUILDER.build();
        }
    }

    public static void setup()
    {
        createConfigDirectoryIfNecessary();
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, GenerationConfig.SPEC, "biomesoplenty/generation.toml");
    }

    public static boolean isBiomeEnabled(ResourceKey<Biome> key)
    {
        if (key == null || !key.location().getNamespace().equals(BiomesOPlenty.MOD_ID))
            return false;

        String optionName = getBiomeConfigOptionName(key);
        Map<String, Boolean> biomeToggles = getBiomeToggles();

        // Add the biome toggle if it is missing
        if (!biomeToggles.containsKey(optionName))
        {
            addBiomeToggle(key);
        }

        return biomeToggles.get(optionName);
    }

    private static Map<String, Boolean> getBiomeToggles()
    {
        if (biomeToggles == null)
        {
            createConfigDirectoryIfNecessary();
            biomeToggles = JsonUtil.getOrCreateConfigFile(getBOPConfigPath().toFile(), BIOME_CONFIG_FILE_NAME, defaultBiomeToggles, new TypeToken<TreeMap<String, Boolean>>(){}.getType());
        }
        return biomeToggles;
    }

    private static String getBiomeConfigOptionName(ResourceKey<Biome> key)
    {
        return key.location().getPath() + "_enabled";
    }

    private static void addBiomeToggle(ResourceKey<Biome> key)
    {
        getBiomeToggles().put(getBiomeConfigOptionName(key), true);
        updateConfigFile();
    }

    private static void updateConfigFile()
    {
        JsonUtil.writeFile(getBOPConfigFile(), getBiomeToggles());
    }

    private static void createConfigDirectoryIfNecessary()
    {
        // Create the config folder
        try
        {
            Files.createDirectory(getBOPConfigPath());
        }
        catch (FileAlreadyExistsException e)
        {
            // Do nothing
        }
        catch (IOException e)
        {
            BiomesOPlenty.LOGGER.error("Failed to create biomesoplenty config directory", e);
        }
    }

    private static Path getBOPConfigPath()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        return Paths.get(configPath.toAbsolutePath().toString(), "biomesoplenty");
    }

    private static File getBOPConfigFile()
    {
        return new File(getBOPConfigPath().toFile(), BIOME_CONFIG_FILE_NAME);
    }

    static
    {
        defaultBiomeToggles.putAll(BOPBiomes.getAllBiomes().stream().collect(Collectors.toMap(ModConfig::getBiomeConfigOptionName, key -> true)));
    }
}
