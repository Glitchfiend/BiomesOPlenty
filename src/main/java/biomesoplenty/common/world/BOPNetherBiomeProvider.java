/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

public class BOPNetherBiomeProvider extends NetherBiomeProvider
{
    private final Layer noiseBiomeLayer;

    public BOPNetherBiomeProvider(long seed)
    {
        super(seed, Lists.newArrayList(), Optional.empty());
        this.noiseBiomeLayer = BOPNetherLayerUtil.createGenLayers(seed);
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z)
    {
        return this.noiseBiomeLayer.get(x, z);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BiomeProvider withSeed(long seed)
    {
        return new BOPNetherBiomeProvider(seed);
    }
}
