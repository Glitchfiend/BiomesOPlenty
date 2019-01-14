/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BOPBiome;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.CompositeSurfaceBuilder;

public class BiomeConiferousForest extends BOPBiome
{
    public BiomeConiferousForest()
    {
        super((new Biome.BiomeBuilder()).surfaceBuilder(new CompositeSurfaceBuilder(SWAMP_SURFACE_BUILDER, GRASS_DIRT_GRAVEL_SURFACE)).precipitation(Biome.RainType.RAIN).category(Biome.Category.SWAMP).depth(-0.2F).scale(0.1F).temperature(0.8F).downfall(0.9F).waterColor(6388580).waterFogColor(2302743).parent((String)null));

        for (BOPClimates climate : BOPClimates.values())
        {
            this.addWeight(climate, 100);
        }
    }
}
