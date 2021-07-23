/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.util.biome.BiomeUtil;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer0;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

public enum NetherBiomeLayer implements AreaTransformer0, DimensionOffset0Transformer
{
    INSTANCE;

    @Override
    public int applyPixel(Context context, int x, int z)
    {
        return BiomeUtil.getBiomeId(BOPClimates.NETHER.getRandomBiome(context, Biomes.NETHER_WASTES));
    }
}
