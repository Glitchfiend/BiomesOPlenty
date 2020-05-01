/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.world.BOPOverworldGenSettings;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig
{
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

        public static final ForgeConfigSpec.EnumValue<BOPOverworldGenSettings.BiomeSize> biomeSize;
        public static final ForgeConfigSpec.EnumValue<BOPOverworldGenSettings.RiverSize> riverSize;
        public static final ForgeConfigSpec.EnumValue<BOPOverworldGenSettings.TemperatureVariationScheme> temperatureVariationScheme;
        public static final ForgeConfigSpec.EnumValue<BOPOverworldGenSettings.RainfallVariationScheme> rainfallVariationScheme;
        public static final ForgeConfigSpec.BooleanValue enhanceVanillaBiomes;

        static
        {
            BUILDER.comment("World generation related options.");
            BUILDER.push("overworld");
            biomeSize = BUILDER.comment("The size of generated biomes.").defineEnum("biome_size", BOPOverworldGenSettings.BiomeSize.MEDIUM);
            riverSize = BUILDER.comment("The size of generated rivers.").defineEnum("river_size", BOPOverworldGenSettings.RiverSize.MEDIUM);
            temperatureVariationScheme = BUILDER.comment("Type of temperature zones to use during biome placement.").defineEnum("temperature_variation_scheme", BOPOverworldGenSettings.TemperatureVariationScheme.MEDIUM_ZONES);
            rainfallVariationScheme = BUILDER.comment("Type of rainfall zones to use during biome placement.").defineEnum("rainfall_variation_scheme", BOPOverworldGenSettings.RainfallVariationScheme.MEDIUM_ZONES);
            enhanceVanillaBiomes = BUILDER.comment("Enhance vanilla biomes by adding additional decoration.").define("enhance_vanilla_biomes", true);
            BUILDER.pop();

            SPEC = BUILDER.build();
        }
    }

    public static void setup()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path bopConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "biomesoplenty");

        // Create the config folder
        try
        {
            Files.createDirectory(bopConfigPath);
        }
        catch (FileAlreadyExistsException e)
        {
            // Do nothing
        }
        catch (IOException e)
        {
            BiomesOPlenty.logger.error("Failed to create biomesoplenty config directory", e);
        }

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, GenerationConfig.SPEC, "biomesoplenty/generation.toml");
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ClientConfig.SPEC, "biomesoplenty/client.toml");
    }
}
