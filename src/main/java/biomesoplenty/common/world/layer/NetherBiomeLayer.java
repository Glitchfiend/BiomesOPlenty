/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
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
