/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset1Transformer;

public enum GenLayerLargeIsland implements IAreaTransformer2, IDimOffset1Transformer
{
    INSTANCE;

    @Override
    public int apply(IContext context, AreaDimension dimension, IArea landSeaArea, IArea climateArea, int x, int z)
    {
        int northVal = landSeaArea.getValue(x + 1, z + 0);
        int eastVal = landSeaArea.getValue(x + 2, z + 1);
        int southVal = landSeaArea.getValue(x + 1, z + 2);
        int westVal = landSeaArea.getValue(x + 0, z + 1);
        int centerVal = landSeaArea.getValue(x + 1, z + 1);
        int climateVal = climateArea.getValue(x, z);

        BOPClimates climate;
        try
        {
            climate = BOPClimates.lookup(climateVal);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            // This shouldn't happen - but apparently it (rarely) does (https://github.com/Glitchfiend/BiomesOPlenty/issues/983)
            // If it does it means that something weird happened with the climate layer / lookup
            // Rethrow with hopefully a more useful message
            String msg = "Climate lookup failed climateOrdinal: " + climateVal;
            throw new RuntimeException(msg,e);
        }

        if (centerVal == 0 && northVal == 0 && eastVal == 0 && southVal == 0 && westVal == 0 && context.random(50) == 0)
        {
            Biome islandBiome = climate.getRandomIslandBiome(context, null);

            if (islandBiome == null)
            {
                return centerVal;
            }
            else
            {
                return IRegistry.BIOME.getId(islandBiome);
            }
        }
        else return centerVal;
    }
}
