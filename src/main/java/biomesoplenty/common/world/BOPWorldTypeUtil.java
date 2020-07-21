/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.Lists;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.dedicated.ServerProperties;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.storage.ServerWorldInfo;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class BOPWorldTypeUtil
{
    private static boolean isServerLevelTypeBop(DedicatedServer server)
    {
        String levelType = Optional.ofNullable((String)server.getProperties().properties.get("level-type")).map((str) -> str.toLowerCase(Locale.ROOT)).orElse("default");
        return levelType.equals("biomesoplenty") || levelType.equals("biomesop");
    }

    // Derived from Dimension.stable
    public static boolean isUsingBopWorldType(DimensionGeneratorSettings settings)
    {
        List<Map.Entry<RegistryKey<Dimension>, Dimension>> dimensions = Lists.newArrayList(settings.dimensions().entrySet());
        Map.Entry<RegistryKey<Dimension>, Dimension> dimensionEntry0 = dimensions.get(0);
        Map.Entry<RegistryKey<Dimension>, Dimension> dimensionEntry1 = dimensions.get(1);
        Map.Entry<RegistryKey<Dimension>, Dimension> dimensionEntry2 = dimensions.get(2);

        // BoP uses the standard dimension layout
        if (dimensionEntry0.getKey() != Dimension.OVERWORLD || dimensionEntry1.getKey() != Dimension.NETHER && dimensionEntry2.getKey() != Dimension.END)
        {
            return false;
        }

        Dimension overworld = dimensionEntry0.getValue();
        Dimension nether = dimensionEntry1.getValue();
        Dimension end = dimensionEntry2.getValue();

        // Ensure noise chunk generators are used in all dimensions
        if (!(overworld.generator() instanceof NoiseChunkGenerator) || !(nether.generator() instanceof NoiseChunkGenerator) || !(end.generator() instanceof NoiseChunkGenerator))
        {
            return false;
        }

        // Ensure our nether and overworld biome providers are being used
        if (!(overworld.generator().getBiomeSource() instanceof BOPBiomeProvider) || !(nether.generator().getBiomeSource() instanceof BOPNetherBiomeProvider || nether.generator().getBiomeSource() instanceof NetherBiomeProvider))
        {
            return false;
        }

        return true;
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
        if (!isServerLevelTypeBop(server))
            return;

        ServerProperties properties = server.getProperties();
        BiomesOPlenty.logger.info("Setting world generator settings to biomesoplenty");

        if (server.getWorldData() instanceof ServerWorldInfo)
        {
            ServerWorldInfo worldInfo  = (ServerWorldInfo)server.getWorldData();
            worldInfo.worldGenSettings = createDimensionGeneratorSettings(worldInfo.worldGenSettings.seed(), worldInfo.worldGenSettings.generateFeatures(), worldInfo.worldGenSettings.generateBonusChest());
        }

        // Replace the world gen settings in server.properties
        properties.worldGenSettings = createDimensionGeneratorSettings(properties.worldGenSettings.seed(), properties.worldGenSettings.generateFeatures(), properties.worldGenSettings.generateBonusChest());
    }
}
