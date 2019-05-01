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
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.biome.overworld.AlpsBiome;
import biomesoplenty.common.biome.overworld.CherryBlossomGroveBiome;
import biomesoplenty.common.biome.overworld.ConiferousForestBiome;
import biomesoplenty.common.biome.overworld.GrasslandBiome;
import biomesoplenty.common.biome.overworld.MeadowBiome;
import biomesoplenty.common.biome.overworld.RedwoodForestBiome;
import biomesoplenty.common.biome.overworld.ShrublandBiome;
import biomesoplenty.common.biome.overworld.SnowyConiferousForestBiome;
import biomesoplenty.common.biome.overworld.SteppeBiome;
import biomesoplenty.common.biome.overworld.TropicalRainforestBiome;
import biomesoplenty.common.biome.overworld.WetlandBiome;
import biomesoplenty.common.biome.overworld.WoodlandBiome;
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
    	alps = registerBiome(new AlpsBiome(), "alps");
    	cherry_blossom_grove = registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
        coniferous_forest = registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        grassland = registerBiome(new GrasslandBiome(), "grassland");
        meadow = registerBiome(new MeadowBiome(), "meadow");
        redwood_forest = registerBiome(new RedwoodForestBiome(), "redwood_forest");
        shrubland = registerBiome(new ShrublandBiome(), "shrubland");
        snowy_coniferous_forest = registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        steppe = registerBiome(new SteppeBiome(), "steppe");
        tropical_rainforest = registerBiome(new TropicalRainforestBiome(), "tropical_rainforest");
        wetland = registerBiome(new WetlandBiome(), "wetland");
        woodland = registerBiome(new WoodlandBiome(), "woodland");
    }

    public static Optional<Biome> registerBiome(BiomeBOP biome, String name)
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
