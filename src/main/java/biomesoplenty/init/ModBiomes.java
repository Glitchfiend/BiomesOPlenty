/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.biome.BOPBiomes.*;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BOPBiome;
import biomesoplenty.common.biome.overworld.BiomeConiferousForest;
import biomesoplenty.common.world.WorldTypeBOP;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Optional;

public class ModBiomes
{
    public static WorldTypeBOP worldType;

    public static void init()
    {
        worldType = new WorldTypeBOP();

        registerBiomes();
    }

    private static void registerBiomes()
    {
        coniferous_forest = registerBiome(new BiomeConiferousForest(), "coniferous_forest");
    }

    public static Optional<Biome> registerBiome(BOPBiome biome, String name)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);

        for (Map.Entry<BOPClimates, Integer> entry : biome.getWeightMap().entrySet())
        {
            if (entry != null)
            {
                BOPClimates climate = entry.getKey();
                int weight = entry.getValue();
                climate.addBiome(weight, biome);
            }
        }

        return Optional.of(biome);
    }
}
