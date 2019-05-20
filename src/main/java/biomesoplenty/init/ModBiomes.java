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
import biomesoplenty.common.biome.overworld.AlpsFoothillsBiome;
import biomesoplenty.common.biome.overworld.BayouBiome;
import biomesoplenty.common.biome.overworld.CherryBlossomGroveBiome;
import biomesoplenty.common.biome.overworld.ColdDesertBiome;
import biomesoplenty.common.biome.overworld.ConiferousForestBiome;
import biomesoplenty.common.biome.overworld.DeadForestBiome;
import biomesoplenty.common.biome.overworld.FloodplainsBiome;
import biomesoplenty.common.biome.overworld.FlowerMeadowBiome;
import biomesoplenty.common.biome.overworld.BogBiome;
import biomesoplenty.common.biome.overworld.BogMireBiome;
import biomesoplenty.common.biome.overworld.BorealForestBiome;
import biomesoplenty.common.biome.overworld.BrushlandBiome;
import biomesoplenty.common.biome.overworld.ChaparralBiome;
import biomesoplenty.common.biome.overworld.GrasslandBiome;
import biomesoplenty.common.biome.overworld.GravelBeachBiome;
import biomesoplenty.common.biome.overworld.GroveBiome;
import biomesoplenty.common.biome.overworld.LavenderFieldsBiome;
import biomesoplenty.common.biome.overworld.LushSwampBiome;
import biomesoplenty.common.biome.overworld.MapleWoodsBiome;
import biomesoplenty.common.biome.overworld.MarshBiome;
import biomesoplenty.common.biome.overworld.MeadowBiome;
import biomesoplenty.common.biome.overworld.MysticGroveBiome;
import biomesoplenty.common.biome.overworld.OasisBiome;
import biomesoplenty.common.biome.overworld.OminousWoodsBiome;
import biomesoplenty.common.biome.overworld.OriginBeachBiome;
import biomesoplenty.common.biome.overworld.GroveOrchardBiome;
import biomesoplenty.common.biome.overworld.HighlandBiome;
import biomesoplenty.common.biome.overworld.HighlandMoorBiome;
import biomesoplenty.common.biome.overworld.OriginHillsBiome;
import biomesoplenty.common.biome.overworld.OutbackBiome;
import biomesoplenty.common.biome.overworld.OvergrownCliffsBiome;
import biomesoplenty.common.biome.overworld.PastureBiome;
import biomesoplenty.common.biome.overworld.PrairieBiome;
import biomesoplenty.common.biome.overworld.RainforestBiome;
import biomesoplenty.common.biome.overworld.RedwoodForestBiome;
import biomesoplenty.common.biome.overworld.RedwoodForestEdgeBiome;
import biomesoplenty.common.biome.overworld.ScrublandBiome;
import biomesoplenty.common.biome.overworld.SeasonalForestBiome;
import biomesoplenty.common.biome.overworld.ShieldBiome;
import biomesoplenty.common.biome.overworld.ShrublandBiome;
import biomesoplenty.common.biome.overworld.SnowyConiferousForestBiome;
import biomesoplenty.common.biome.overworld.SnowyForestBiome;
import biomesoplenty.common.biome.overworld.SteppeBiome;
import biomesoplenty.common.biome.overworld.TemperateRainforestBiome;
import biomesoplenty.common.biome.overworld.TropicalRainforestBiome;
import biomesoplenty.common.biome.overworld.TropicsBiome;
import biomesoplenty.common.biome.overworld.TundraBiome;
import biomesoplenty.common.biome.overworld.WastelandBiome;
import biomesoplenty.common.biome.overworld.WetlandBiome;
import biomesoplenty.common.biome.overworld.WhiteBeachBiome;
import biomesoplenty.common.biome.overworld.WoodlandBiome;
import biomesoplenty.common.biome.overworld.XericShrublandBiome;
import biomesoplenty.common.world.WorldTypeBOP;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Optional;

public class ModBiomes
{
    public static WorldTypeBOP worldType;

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();

    public static void init()
    {
        worldType = new WorldTypeBOP();

        registerBiomes();
    }

    private static void registerBiomes()
    {
    	alps = registerBiome(new AlpsBiome(), "alps");
    	alps_foothills = registerBiome(new AlpsFoothillsBiome(), "alps_foothills");
    	bayou = registerBiome(new BayouBiome(), "bayou");
        bog = registerBiome(new BogBiome(), "bog");
        bog_mire = registerBiome(new BogMireBiome(), "bog_mire");
        boreal_forest = registerBiome(new BorealForestBiome(), "boreal_forest");
    	brushland = registerBiome(new BrushlandBiome(), "brushland");
        chaparral = registerBiome(new ChaparralBiome(), "chaparral");
    	cherry_blossom_grove = registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
    	cold_desert = registerBiome(new ColdDesertBiome(), "cold_desert");
        coniferous_forest = registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        dead_forest = registerBiome(new DeadForestBiome(), "dead_forest");
        floodplains = registerBiome(new FloodplainsBiome(), "floodplains");
        flower_meadow = registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        grassland = registerBiome(new GrasslandBiome(), "grassland");
        gravel_beach = registerBiome(new GravelBeachBiome(), "gravel_beach");
        grove = registerBiome(new GroveBiome(), "grove");
        grove_orchard = registerBiome(new GroveOrchardBiome(), "grove_orchard");
        highland = registerBiome(new HighlandBiome(), "highland");
        highland_moor = registerBiome(new HighlandMoorBiome(), "highland_moor");
        lavender_fields = registerBiome(new LavenderFieldsBiome(), "lavender_fields");
        lush_swamp = registerBiome(new LushSwampBiome(), "lush_swamp");
        maple_woods = registerBiome(new MapleWoodsBiome(), "maple_woods");
        marsh = registerBiome(new MarshBiome(), "marsh");
        meadow = registerBiome(new MeadowBiome(), "meadow");
        mystic_grove = registerBiome(new MysticGroveBiome(), "mystic_grove");
        oasis = registerBiome(new OasisBiome(), "oasis");
        ominous_woods = registerBiome(new OminousWoodsBiome(), "ominous_woods");
        origin_beach = registerBiome(new OriginBeachBiome(), "origin_beach");
        origin_hills = registerBiome(new OriginHillsBiome(), "origin_hills");
        outback = registerBiome(new OutbackBiome(), "outback");
        overgrown_cliffs = registerBiome(new OvergrownCliffsBiome(), "overgrown_cliffs");
        pasture = registerBiome(new PastureBiome(), "pasture");
        prairie = registerBiome(new PrairieBiome(), "prairie");
        rainforest = registerBiome(new RainforestBiome(), "rainforest");
        redwood_forest = registerBiome(new RedwoodForestBiome(), "redwood_forest");
        redwood_forest_edge = registerBiome(new RedwoodForestEdgeBiome(), "redwood_forest_edge");
        scrubland = registerBiome(new ScrublandBiome(), "scrubland");
        seasonal_forest = registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        shield = registerBiome(new ShieldBiome(), "shield");
        shrubland = registerBiome(new ShrublandBiome(), "shrubland");
        snowy_coniferous_forest = registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        snowy_forest = registerBiome(new SnowyForestBiome(), "snowy_forest");
        steppe = registerBiome(new SteppeBiome(), "steppe");
        temperate_rainforest = registerBiome(new TemperateRainforestBiome(), "temperate_rainforest");
        tropical_rainforest = registerBiome(new TropicalRainforestBiome(), "tropical_rainforest");
        tropics = registerBiome(new TropicsBiome(), "tropics");
        tundra = registerBiome(new TundraBiome(), "tundra");
        wasteland = registerBiome(new WastelandBiome(), "wasteland");
        wetland = registerBiome(new WetlandBiome(), "wetland");
        white_beach = registerBiome(new WhiteBeachBiome(), "white_beach");
        woodland = registerBiome(new WoodlandBiome(), "woodland");
        xeric_shrubland = registerBiome(new XericShrublandBiome(), "xeric_shrubland");

        // Note: Rarity supports two decimal places
        registerSubBiome(Biomes.DESERT, oasis, 0.25F, 100);
        registerSubBiome(bog, bog_mire, 0.5F, 100);
        registerSubBiome(brushland, xeric_shrubland, 1.5F, 100);
        registerSubBiome(grove, grove_orchard, 0.75F, 100);
        registerSubBiome(highland, highland_moor, 1.0F, 100);
        registerSubBiome(meadow, flower_meadow, 0.75F, 100);
        registerSubBiome(prairie, pasture, 2.0F, 100);
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

    public static void registerSubBiome(Biome parent, Optional<Biome> child, float rarity, int weight)
    {
        if (!child.isPresent())
            return;

        subBiomes.put(IRegistry.BIOME.getId(parent), new WeightedSubBiome(child.get(), rarity, weight));
    }
    
    public static void registerSubBiome(Optional<Biome> parent, Optional<Biome> child, float rarity, int weight)
    {
    	if (!parent.isPresent())
            return;
    	
        if (!child.isPresent())
            return;

        subBiomes.put(IRegistry.BIOME.getId(parent.get()), new WeightedSubBiome(child.get(), rarity, weight));
    }

    public static class WeightedSubBiome
    {
        public final Biome biome;
        public final float rarity;
        public final int weight;

        public WeightedSubBiome(Biome biome, float rarity, int weight)
        {
            this.biome = biome;
            this.rarity = rarity;
            this.weight = weight;
        }
    }
}
