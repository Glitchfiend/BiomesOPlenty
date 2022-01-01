/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.data;

import biomesoplenty.common.util.config.JsonUtil;
import biomesoplenty.init.ModBiomes;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.mojang.datafixers.util.Pair;
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
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class DataGenerator
{
    private static final Path DATA_PATH = FMLPaths.GAMEDIR.get().resolve("bop_worldgen/data");

    private static Map<Integer, ResourceLocation> locationSubstitions = Maps.newHashMap();

    public static void generateData(RegistryAccess registryAccess)
    {
        RegistryAccess.RegistryHolder newRegistryAccess = RegistryAccess.builtin();
        Registry<DimensionType> dimensionTypeRegistry = registryAccess.registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
        WorldGenSettings settings = ModBiomes.bopWorldType.createSettings(newRegistryAccess, 0, true, false, "");

        ImmutableSet<Pair<Registry, Codec>> registryCodecs = ImmutableSet.<Pair<Registry, Codec>>builder()
            .add(Pair.of(settings.dimensions(), LevelStem.CODEC))
            .add(Pair.of(dimensionTypeRegistry, DimensionType.CODEC))
            .add(Pair.of(BuiltinRegistries.BIOME, Biome.CODEC))
            .add(Pair.of(BuiltinRegistries.CONFIGURED_CARVER, ConfiguredWorldCarver.CODEC))
            .add(Pair.of(BuiltinRegistries.CONFIGURED_FEATURE, ConfiguredFeature.CODEC))
            .add(Pair.of(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, ConfiguredStructureFeature.CODEC))
            .add(Pair.of(BuiltinRegistries.NOISE, NormalNoise.NoiseParameters.CODEC))
            .add(Pair.of(BuiltinRegistries.NOISE_GENERATOR_SETTINGS, NoiseGeneratorSettings.CODEC))
            .add(Pair.of(BuiltinRegistries.PLACED_FEATURE, PlacedFeature.CODEC))
            .add(Pair.of(BuiltinRegistries.PROCESSOR_LIST, StructureProcessorType.LIST_CODEC))
            .add(Pair.of(BuiltinRegistries.TEMPLATE_POOL, StructureTemplatePool.CODEC))
            .build();

        addRegistrySubstitutions(registryAccess.registryOrThrow(Registry.BIOME_REGISTRY), Biome.CODEC);

        for (Pair<Registry, Codec> registryCodecPair : registryCodecs)
        {
            addRegistrySubstitutions(registryCodecPair.getFirst(), registryCodecPair.getSecond());
        }

        for (Pair<Registry, Codec> registryCodecPair : registryCodecs)
        {
            generateRegistryJsons(registryCodecPair.getFirst(), registryCodecPair.getSecond());
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

    private static <T> void generateRegistryJsons(Registry<T> registry, Codec codec)
    {
        for (var entry : registry.entrySet())
        {
            generateJson(registry, entry.getKey(), codec);
        }
    }

    private static <T> void generateJson(Registry<T> registry, ResourceKey<T> key, Codec codec)
    {
        T type = registry.get(key);
        Optional<JsonElement> encodedResult = encodeJson(type, codec);
        Path path = DATA_PATH.resolve(key.location().getNamespace() + "/" + key.getRegistryName().getPath());

        if (encodedResult.isPresent())
        {
            JsonElement element = encodedResult.get();
            substituteWithLocations(element);
            JsonUtil.writeFile(path.resolve(key.location().getPath() + ".json").toFile(), element);
        }
    }

    private static void substituteWithLocations(JsonElement element)
    {
        if (element.isJsonArray())
        {
            JsonArray array = element.getAsJsonArray();
            Iterator<JsonElement> it = array.iterator();
            List<JsonElement> elementsToInsert = Lists.newArrayList();

            while (it.hasNext())
            {
                JsonElement arrayElement = it.next();
                int hash = arrayElement.hashCode();

                if (locationSubstitions.containsKey(hash))
                {
                    it.remove();
                    elementsToInsert.add(new JsonPrimitive(locationSubstitions.get(hash).toString()));
                }
                else substituteWithLocations(arrayElement);
            }

            elementsToInsert.forEach(elementToInsert -> array.add(elementToInsert));
            return;
        }
        else if (!element.isJsonObject()) return;

        JsonObject obj = element.getAsJsonObject();

        for (var entry : obj.entrySet())
        {
            String fieldName = entry.getKey();
            int hash = entry.getValue().hashCode();

            if (locationSubstitions.containsKey(hash))
            {
                obj.add(fieldName, new JsonPrimitive(locationSubstitions.get(hash).toString()));
                continue;
            }

            if (entry.getValue().isJsonObject() || entry.getValue().isJsonArray())
                substituteWithLocations(entry.getValue());
        }
    }

    private static <T> Optional<JsonElement> encodeJson(T type, Codec codec)
    {
        try
        {
            return ((Codec<Supplier<T>>)codec).encodeStart(JsonOps.INSTANCE, () -> type).result();
        }
        catch (ClassCastException e)
        {
            return ((Codec<T>)codec).encodeStart(JsonOps.INSTANCE, type).result();
        }
    }
}
