/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerSubBiomesBOPHell extends GenLayerSubBiomesBOP
{
    public GenLayerSubBiomesBOPHell(long seed, GenLayer biomesLayer, GenLayer subBiomesInit)
    {
        super(seed, biomesLayer, subBiomesInit);
    }

    // Only replace with the sub biome if it's not ocean
    @Override
    public int getRareSubBiome(int biomeId)
    {
        int subBiomeId = super.getRareSubBiome(biomeId);
        return subBiomeId != 0 ? subBiomeId : biomeId;
    }

    // Only replace with the sub biome if it's not ocean
    @Override
    public int getCommonSubBiome(int biomeId)
    {
        int subBiomeId = super.getCommonSubBiome(biomeId);
        return subBiomeId != 0 ? subBiomeId : biomeId;
    }
}
