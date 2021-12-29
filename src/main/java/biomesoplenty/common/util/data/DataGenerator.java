/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.data;

import biomesoplenty.common.util.config.JsonUtil;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class DataGenerator
{
    private static final Path DATA_PATH = FMLPaths.GAMEDIR.get().resolve("bop_worldgen");

    private static Map<Integer, ResourceLocation> locationSubstitions = Maps.newHashMap();

    public static void generateData()
    {
        WorldGenSettings settings = ModBiomes.bopWorldType.createSettings(RegistryAccess.builtin(), 0, true, false, "");

        RegistryAccess registryAccess = RegistryAccess.builtin();
        Registry<DimensionType> dimensionTypeRegistry = registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);

        addRegistrySubstitutions(BuiltinRegistries.BIOME, Biome.CODEC);
        addRegistrySubstitutions(dimensionTypeRegistry, DimensionType.CODEC);
        addRegistrySubstitutions(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, NoiseGeneratorSettings.CODEC);

        try
        {
            generateRegistryJsons(settings.dimensions(), LevelStem.CODEC);
            generateRegistryJsons(dimensionTypeRegistry, DimensionType.CODEC);
            generateRegistryJsons(biomeRegistry, Biome.CODEC);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static <T> void addRegistrySubstitutions(Registry<T> registry, Codec<Supplier<T>> codec)
    {
        for (Map.Entry<ResourceKey<T>, T> entry : registry.entrySet())
        {
            encodeJson(entry.getValue(), codec).ifPresent(element ->
            {
                locationSubstitions.put(element.hashCode(), entry.getKey().location());
            });
        }
    }

    private static <T> void generateRegistryJsons(Registry<T> registry, Codec codec) throws IOException
    {
        for (var entry : registry.entrySet())
        {
            generateJson(registry, entry.getKey(), codec);
        }
    }

    private static <T> void generateJson(Registry<T> registry, ResourceKey<T> key, Codec codec)
    {
        T type = registry.get(key);
        Optional<JsonElement> encodedResult;
        Path path = DATA_PATH.resolve(key.getRegistryName().getPath());

        try
        {
            encodedResult = ((Codec<Supplier<T>>)codec).encodeStart(JsonOps.INSTANCE, () -> type).result();
        }
        catch (ClassCastException e)
        {
            encodedResult = ((Codec<T>)codec).encodeStart(JsonOps.INSTANCE, type).result();
        }

        if (encodedResult.isPresent())
        {
            JsonElement element = encodedResult.get();
            substituteWithLocations(element, Set.of("biome", "settings", "type"));
            JsonUtil.writeFile(path.resolve(key.location().getPath() + ".json").toFile(), element);
        }
    }

    private static void substituteWithLocations(JsonElement element, Set<String> substitutions)
    {
        if (element.isJsonArray())
        {
            element.getAsJsonArray().forEach(arrayElement -> substituteWithLocations(arrayElement, substitutions));
            return;
        }
        else if (!element.isJsonObject()) return;

        JsonObject obj = element.getAsJsonObject();

        for (var entry : obj.entrySet())
        {
            String fieldName = entry.getKey();

            if (substitutions.contains(fieldName))
            {
                int hash = entry.getValue().hashCode();

                if (locationSubstitions.containsKey(hash))
                {
                    obj.add(fieldName, new JsonPrimitive(locationSubstitions.get(hash).toString()));
                    continue;
                }
            }

            if (entry.getValue().isJsonObject() || entry.getValue().isJsonArray())
                substituteWithLocations(entry.getValue(), substitutions);
        }
    }

    private static <T> Optional<JsonElement> encodeJson(T type, Codec<Supplier<T>> codec)
    {
        return codec.encodeStart(JsonOps.INSTANCE, () -> type).result();
    }
}
