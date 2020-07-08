/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.base.MoreObjects;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.dedicated.ServerProperties;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.storage.ServerWorldInfo;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class BOPWorldTypeUtil
{
    private static boolean isUsingBopWorldType(DedicatedServer server)
    {
        String levelType = Optional.ofNullable((String)server.getProperties().properties.get("level-type")).map((str) -> str.toLowerCase(Locale.ROOT)).orElse("default");
        return levelType.equals("biomesoplenty") || levelType.equals("biomesop");
    }

    public static ChunkGenerator createChunkGenerator(long seed)
    {
        return new NoiseChunkGenerator(new BOPBiomeProvider(seed), seed, DimensionSettings.Preset.OVERWORLD.settings());
    }

    public static DimensionGeneratorSettings createDimensionGeneratorSettings(long seed, boolean generateFeatures, boolean generateBonusChest)
    {
        return new DimensionGeneratorSettings(seed, generateFeatures, generateBonusChest, DimensionGeneratorSettings.withOverworld(BOPDimensionType.bopDimensions(seed), createChunkGenerator(seed)));
    }

    public static void setupForDedicatedServer(DedicatedServer server)
    {
        // Ensure we are using the bop world type
        if (!isUsingBopWorldType(server))
            return;

        ServerProperties properties = server.getProperties();
        DimensionGeneratorSettings oldWorldGenSettings = properties.worldGenSettings;

        // Obtain the original settings
        long seed = oldWorldGenSettings.seed();
        boolean generateFeatures = oldWorldGenSettings.generateFeatures();
        boolean generateBonusChest = oldWorldGenSettings.generateBonusChest();

        BiomesOPlenty.logger.info("Setting world generator settings to biomesoplenty");
        DimensionGeneratorSettings newWorldGenSettings = createDimensionGeneratorSettings(seed, generateFeatures, generateBonusChest);

        if (server.getWorldData() instanceof ServerWorldInfo)
        {
            ServerWorldInfo worldInfo  = (ServerWorldInfo)server.getWorldData();
            worldInfo.worldGenSettings = newWorldGenSettings;
        }

        // Replace the world gen settings in server.properties
        properties.worldGenSettings = newWorldGenSettings;
    }
}
