/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
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
    public static class ServerConfig
    {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.BooleanValue useWorldType;

        static
        {
            BUILDER.comment("Server-related options. Please ignore this file if you are not running a server.");
            BUILDER.push("biomes");
            useWorldType = BUILDER.comment("Use the Biomes O' Plenty world type on a dedicated server.").define("use_world_type", true);
            BUILDER.pop();

            SPEC = BUILDER.build();
        }
    }

    public static void init()
    {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path bopConfigPath = Paths.get(configPath.toAbsolutePath().toString(), "biomesoplenty");

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

        // The server specific config type seems to load too late. Use common instead.
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ServerConfig.SPEC, "biomesoplenty/server.toml");
    }
}
