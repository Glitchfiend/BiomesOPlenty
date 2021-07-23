/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import biomesoplenty.api.enums.BOPClimates;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.newbiome.layer.Layer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BOPNetherBiomeProvider extends BiomeSource
{
    public static final Codec<BOPNetherBiomeProvider> CODEC = RecordCodecBuilder.create((builder) ->
    {
        return builder.group(
                Codec.LONG.fieldOf("seed").stable().forGetter((biomeProvider) -> biomeProvider.seed),
                RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter((biomeProvider) -> biomeProvider.biomes)
        ).apply(builder, builder.stable(BOPNetherBiomeProvider::new));
    });
    private static final List<ResourceKey<Biome>> VANILLA_POSSIBLE_BIOMES = ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.BASALT_DELTAS);

    private final long seed;
    private final Layer noiseBiomeLayer;
    private final Registry<Biome> biomes;

    public BOPNetherBiomeProvider(long seed, Registry<Biome> biomes)
    {
        super(Stream.concat(VANILLA_POSSIBLE_BIOMES.stream(), BOPClimates.NETHER.getLandBiomes().stream().map((entry) -> entry.biome)).map(biomes::get).collect(Collectors.toList()));
        this.seed = seed;
        this.noiseBiomeLayer = BOPNetherLayerUtil.createGenLayers(seed);
        this.biomes = biomes;
    }

    @Override
    protected Codec<? extends BiomeSource> codec()
    {
        return CODEC;
    }

    @Override
    public BiomeSource withSeed(long seed)
    {
        return new BOPNetherBiomeProvider(seed, this.biomes);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.noiseBiomeLayer.get(this.biomes, x, z);
    }
}
