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
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.layer.Layer;

import java.util.List;
import java.util.Optional;
import java.util.function.LongFunction;
import java.util.stream.Collectors;

public class BOPNetherBiomeProvider extends NetherBiomeProvider
{
    private final Layer noiseBiomeLayer;

    public BOPNetherBiomeProvider(long seed, List<Pair<Biome.Attributes, Biome>> parameters, Optional<NetherBiomeProvider.Preset> preset)
    {
        super(seed, parameters, preset);
        this.noiseBiomeLayer = BOPNetherLayerUtil.createGenLayers(seed);
    }

    public static NetherBiomeProvider bopNether(long seed)
    {
        List<Biome> biomeList = Lists.newArrayList(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.BASALT_DELTAS);

        /* Add bop biomes to the biome list. */
        for (Biome biome : BOPClimates.NETHER.getLandBiomes().stream().map((entry) -> entry.biome).collect(Collectors.toList()))
        {
            biomeList.add(biome);
        }

        return new BOPNetherBiomeProvider(seed, biomeList.stream().flatMap((biome) -> biome.optimalParameters().map((attribute) -> Pair.of(attribute, biome))).collect(ImmutableList.toImmutableList()), Optional.of(Preset.NETHER));
    }

//    @Override
//    public Biome getNoiseBiome(int x, int y, int z)
//    {
//        return this.noiseBiomeLayer.get(x, z);
//    }

    public static class Preset extends NetherBiomeProvider.Preset
    {
        public Preset(ResourceLocation location, LongFunction<NetherBiomeProvider> biomeProvider)
        {
            super(location, biomeProvider);
        }

        public static final NetherBiomeProvider.Preset NETHER = new NetherBiomeProvider.Preset(new ResourceLocation("nether"), (seed) -> BOPNetherBiomeProvider.bopNether(seed) );
    };
}
