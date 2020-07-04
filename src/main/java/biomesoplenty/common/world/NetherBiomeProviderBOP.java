/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.layer.Layer;

import java.util.List;
import java.util.Optional;

public class NetherBiomeProviderBOP extends NetherBiomeProvider
{
    private final Layer noiseBiomeLayer;

    public NetherBiomeProviderBOP(long seed)
    {
        super(seed, getParameters(), Optional.of(NetherBiomeProvider.Preset.NETHER));
        this.noiseBiomeLayer = BOPNetherLayerUtil.createGenLayers(seed);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.noiseBiomeLayer.get(x, z);
    }

    private static List<Pair<Biome.Attributes, Biome>> getParameters()
    {
        ImmutableList<Biome> biomeList = ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.BASALT_DELTAS);
        return biomeList.stream().flatMap((biome) -> biome.optimalParameters().map((attribute) -> Pair.of(attribute, biome))).collect(ImmutableList.toImmutableList());
    }
}
