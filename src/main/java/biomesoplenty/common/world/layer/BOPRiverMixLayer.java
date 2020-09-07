/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.biome.BiomeMetadata;
import biomesoplenty.common.biome.BiomeRegistry;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.common.world.BOPLayerUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum BOPRiverMixLayer implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;

    private static final int FROZEN_RIVER = BiomeUtil.getBiomeId(Biomes.FROZEN_RIVER);
    private static final int SNOWY_TUNDRA = BiomeUtil.getBiomeId(Biomes.SNOWY_TUNDRA);
    private static final int MUSHROOM_FIELDS = BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELDS);
    private static final int MUSHROOM_FIELD_SHORE = BiomeUtil.getBiomeId(Biomes.MUSHROOM_FIELD_SHORE);
    private static final int RIVER = BiomeUtil.getBiomeId(Biomes.RIVER);

    @Override
    public int applyPixel(INoiseRandom context, IArea biomeArea, IArea riverArea, int x, int z)
    {
        int biomeId = biomeArea.get(x, z);
        int riverId = riverArea.get(x, z);
        RegistryKey<Biome> biome = BiomeUtil.createKey(biomeId);

        if (BOPLayerUtil.isOcean(biomeId))
        {
            return biomeId;
        }
        else if (riverId == RIVER)
        {
            if (biomeId == SNOWY_TUNDRA)
            {
                return FROZEN_RIVER;
            }
            else if (BiomeUtil.hasMetadata(biome))
            {
                BiomeMetadata meta = BiomeUtil.getMetadata(biome);

                if (meta.getRiverBiome() != null)
                    return BiomeUtil.getBiomeId(meta.getRiverBiome());
                else
                    return biomeId;
            }
            else
            {
                return biomeId != MUSHROOM_FIELDS && biomeId != MUSHROOM_FIELD_SHORE ? riverId & 255 : MUSHROOM_FIELD_SHORE;
            }
        }
        else
        {
            return biomeId;
        }
    }
}
