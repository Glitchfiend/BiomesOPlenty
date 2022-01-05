/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.config.JsonUtil;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.lang3.StringUtils;

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

    public static class ClientConfig
    {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.BooleanValue useWorldType;

        static
        {
            BUILDER.comment("Client-related options.");
            BUILDER.push("gui");
            useWorldType = BUILDER.comment("Select the Biomes O' Plenty world type by default.").define("use_world_type", true);
            BUILDER.pop();

            SPEC = BUILDER.build();
        }
    }

    public static class GenerationConfig
    {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.IntValue bopRegionWeight;
        public static final ForgeConfigSpec.IntValue bopRareRegionWeight;
        static
        {
            BUILDER.comment("World generation related options.");
            BUILDER.push("overworld");
            bopRegionWeight = BUILDER.comment("The weighting of bop biome regions.").defineInRange("bop_region_weight", 10, 0, Integer.MAX_VALUE);
            bopRareRegionWeight = BUILDER.comment("The weighting of rare bop biome regions.").defineInRange("bop_rare_region_weight", 5, 0, Integer.MAX_VALUE);
            BUILDER.pop();

            SPEC = BUILDER.build();
        }
    }

    public static void setup()
    {
        createConfigDirectoryIfNecessary();
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, GenerationConfig.SPEC, "biomesoplenty/generation.toml");
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ClientConfig.SPEC, "biomesoplenty/client.toml");
    }

    public static boolean isBiomeEnabled(ResourceKey<Biome> key)
    {
        String optionName = getBiomeConfigOptionName(key);
        Map<String, Boolean> biomeToggles = getBiomeToggles();

        // Add the biome toggle if it is missing
        if (!biomeToggles.containsKey(optionName) && key.location().getNamespace().equals("biomesoplenty"))
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
            BiomesOPlenty.logger.error("Failed to create biomesoplenty config directory", e);
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
