/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

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

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ClientConfig.SPEC, "biomesoplenty/client.toml");
    }
}
