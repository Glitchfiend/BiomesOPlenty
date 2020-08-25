/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum NetherBiomeLayer implements IAreaTransformer0, IDimOffset0Transformer
{
    INSTANCE;

    @Override
    public int applyPixel(INoiseRandom context, int x, int z)
    {
        return BiomeUtil.getBiomeId(BOPClimates.NETHER.getRandomBiome(context, Biomes.NETHER_WASTES));
    }
}
