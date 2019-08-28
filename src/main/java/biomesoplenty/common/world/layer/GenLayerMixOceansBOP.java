/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.world.BOPLayerUtil;
import biomesoplenty.common.world.layer.traits.IAreaTransformer3;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum GenLayerMixOceansBOP implements IAreaTransformer3, IDimOffset0Transformer
{
    INSTANCE;

    @Override
    public int apply(INoiseRandom context, IArea biomeArea, IArea oceanArea, IArea climateArea, int x, int z)
    {
        int biomeId = biomeArea.getValue(x, z);
        int oceanId = oceanArea.getValue(x, z);
        int climateVal = climateArea.getValue(x, z);
        BOPClimates climate = BOPClimates.lookup(climateVal);

        if (!BOPLayerUtil.isOcean(biomeId))
        {
            return biomeId;
        }
        else
        {
        	switch (climate)
            {
                case ICE_CAP:
                    oceanId = BOPLayerUtil.FROZEN_OCEAN;
                    break;

                case TUNDRA:
                case WET_BOREAL:
                case DRY_BOREAL:
                    oceanId = BOPLayerUtil.COLD_OCEAN;
                    break;

                case WARM_TEMPERATE:
                case SUBTROPICAL:
                case MEDITERRANEAN:
                case SAVANNA:
                case WASTELAND:
                    oceanId = BOPLayerUtil.LUKEWARM_OCEAN;
                    break;

                case TROPICAL:
                case HOT_DESERT:
                    oceanId = BOPLayerUtil.WARM_OCEAN;
                    break;

                default:
                    oceanId = BOPLayerUtil.OCEAN;
                    break;
            }
        	
            // When far from land, warm oceans should become lukewarm and frozen oceans should become cold
            /*for (int xOff = -8; xOff <= 8; xOff += 4)
            {
                for (int zOff = -8; zOff <= 8; zOff += 4)
                {
                    int offsetBiomeId = biomeArea.getValue(x + xOff, z + zOff);

                    if (!BOPLayerUtil.isOcean(offsetBiomeId))
                    {
                        if (oceanId == BOPLayerUtil.WARM_OCEAN)
                        {
                            return BOPLayerUtil.LUKEWARM_OCEAN;
                        }

                        if (oceanId == BOPLayerUtil.FROZEN_OCEAN)
                        {
                            return BOPLayerUtil.COLD_OCEAN;
                        }
                    }
                }
            }*/

            if (biomeId == BOPLayerUtil.DEEP_OCEAN)
            {
                if (oceanId == BOPLayerUtil.WARM_OCEAN)
                {
                    return BOPLayerUtil.DEEP_WARM_OCEAN;
                }

                if (oceanId == BOPLayerUtil.LUKEWARM_OCEAN)
                {
                    return BOPLayerUtil.DEEP_LUKEWARM_OCEAN;
                }

                if (oceanId == BOPLayerUtil.OCEAN)
                {
                    return BOPLayerUtil.DEEP_OCEAN;
                }

                if (oceanId == BOPLayerUtil.COLD_OCEAN)
                {
                    return BOPLayerUtil.DEEP_COLD_OCEAN;
                }

                if (oceanId == BOPLayerUtil.FROZEN_OCEAN)
                {
                    return BOPLayerUtil.DEEP_FROZEN_OCEAN;
                }
            }

            return oceanId;
        }
    }
}
