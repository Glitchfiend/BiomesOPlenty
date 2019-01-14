/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;
import net.minecraftforge.common.BiomeManager;

public enum GenLayerBiomeBOP implements IAreaTransformer2, IDimOffset0Transformer
{
    INSTANCE;

    private static final int DEEP_OCEAN = Biome.func_185362_a(Biomes.DEEP_OCEAN);
    private static final int MUSHROOM_FIELDS = Biome.func_185362_a(Biomes.MUSHROOM_FIELDS);

    @Override
    public int apply(IContext context, AreaDimension dimension, IArea area1, IArea area2, int x, int z)
    {
        int landSeaVal = area1.getValue(x, z);
        int climateVal = area2.getValue(x, z);

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

        // At this point, oceans and land have been assigned, and so have mushroom islands
        if (landSeaVal == DEEP_OCEAN)
        {
            return Biome.func_185362_a(climate.getRandomOceanBiome(context, true));
        }
        else if ((landSeaVal == MUSHROOM_FIELDS /*|| ModBiomes.islandBiomesMap.containsKey(landSeaVal)*/) && climate.biomeType != BiomeManager.BiomeType.ICY) // TODO
        {
            // keep islands, unless it's in an icy climate in which case, replace
            return landSeaVal;
        }
        else if (landSeaVal == 0)
        {
            return Biome.func_185362_a(climate.getRandomOceanBiome(context, false));
        }
        else
        {
            return Biome.func_185362_a(climate.getRandomBiome(context));
        }
    }
}
