/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import static biomesoplenty.api.biome.BOPBiomes.*;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.biome.overworld.AlpsBiome;
import biomesoplenty.common.biome.overworld.AlpsFoothillsBiome;
import biomesoplenty.common.biome.overworld.BayouBiome;
import biomesoplenty.common.biome.overworld.CherryBlossomGroveBiome;
import biomesoplenty.common.biome.overworld.ColdDesertBiome;
import biomesoplenty.common.biome.overworld.ConiferousForestBiome;
import biomesoplenty.common.biome.overworld.DeadForestBiome;
import biomesoplenty.common.biome.overworld.FloodplainBiome;
import biomesoplenty.common.biome.overworld.FlowerMeadowBiome;
import biomesoplenty.common.biome.overworld.BogBiome;
import biomesoplenty.common.biome.overworld.BogMireBiome;
import biomesoplenty.common.biome.overworld.BorealForestBiome;
import biomesoplenty.common.biome.overworld.BrushlandBiome;
import biomesoplenty.common.biome.overworld.ChaparralBiome;
import biomesoplenty.common.biome.overworld.GrasslandBiome;
import biomesoplenty.common.biome.overworld.GravelBeachBiome;
import biomesoplenty.common.biome.overworld.GroveBiome;
import biomesoplenty.common.biome.overworld.LavenderFieldBiome;
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
import biomesoplenty.common.biome.overworld.SilkgladeBiome;
import biomesoplenty.common.biome.overworld.SnowyConiferousForestBiome;
import biomesoplenty.common.biome.overworld.SnowyForestBiome;
import biomesoplenty.common.biome.overworld.SteppeBiome;
import biomesoplenty.common.biome.overworld.TemperateRainforestBiome;
import biomesoplenty.common.biome.overworld.TropicalRainforestBiome;
import biomesoplenty.common.biome.overworld.TropicsBiome;
import biomesoplenty.common.biome.overworld.TundraBiome;
import biomesoplenty.common.biome.overworld.VolcanoBiome;
import biomesoplenty.common.biome.overworld.WastelandBiome;
import biomesoplenty.common.biome.overworld.WetlandBiome;
import biomesoplenty.common.biome.overworld.WhiteBeachBiome;
import biomesoplenty.common.biome.overworld.WoodlandBiome;
import biomesoplenty.common.biome.overworld.XericShrublandBiome;
import biomesoplenty.common.world.WorldTypeBOP;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModBiomes
{
    public static WorldTypeBOP worldType;

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();
    public static List<Integer> islandBiomes = Lists.newArrayList();

    public static void init()
    {
        worldType = new WorldTypeBOP();

        registerBiomes();
        registerBiomeDictionaryTags();
    }

    private static void registerBiomes()
    {
        gravel_beach = registerBiome(new GravelBeachBiome(), "gravel_beach");
        origin_beach = registerBiome(new OriginBeachBiome(), "origin_beach");
        white_beach = registerBiome(new WhiteBeachBiome(), "white_beach");
    	alps_foothills = registerBiome(new AlpsFoothillsBiome(), "alps_foothills");
        redwood_forest_edge = registerBiome(new RedwoodForestEdgeBiome(), "redwood_forest_edge");
    	
    	alps = registerBiome(new AlpsBiome(), "alps");
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
        floodplain = registerBiome(new FloodplainBiome(), "floodplain");
        flower_meadow = registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        grassland = registerBiome(new GrasslandBiome(), "grassland");
        grove = registerBiome(new GroveBiome(), "grove");
        grove_orchard = registerBiome(new GroveOrchardBiome(), "grove_orchard");
        highland = registerBiome(new HighlandBiome(), "highland");
        highland_moor = registerBiome(new HighlandMoorBiome(), "highland_moor");
        lavender_field = registerBiome(new LavenderFieldBiome(), "lavender_field");
        lush_swamp = registerBiome(new LushSwampBiome(), "lush_swamp");
        maple_woods = registerBiome(new MapleWoodsBiome(), "maple_woods");
        marsh = registerBiome(new MarshBiome(), "marsh");
        meadow = registerBiome(new MeadowBiome(), "meadow");
        mystic_grove = registerBiome(new MysticGroveBiome(), "mystic_grove");
        oasis = registerBiome(new OasisBiome(), "oasis");
        ominous_woods = registerBiome(new OminousWoodsBiome(), "ominous_woods");
        origin_hills = registerBiome(new OriginHillsBiome(), "origin_hills");
        outback = registerBiome(new OutbackBiome(), "outback");
        overgrown_cliffs = registerBiome(new OvergrownCliffsBiome(), "overgrown_cliffs");
        pasture = registerBiome(new PastureBiome(), "pasture");
        prairie = registerBiome(new PrairieBiome(), "prairie");
        rainforest = registerBiome(new RainforestBiome(), "rainforest");
        redwood_forest = registerBiome(new RedwoodForestBiome(), "redwood_forest");
        scrubland = registerBiome(new ScrublandBiome(), "scrubland");
        seasonal_forest = registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        shield = registerBiome(new ShieldBiome(), "shield");
        shrubland = registerBiome(new ShrublandBiome(), "shrubland");
        silkglade = registerBiome(new SilkgladeBiome(), "silkglade");
        snowy_coniferous_forest = registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        snowy_forest = registerBiome(new SnowyForestBiome(), "snowy_forest");
        steppe = registerBiome(new SteppeBiome(), "steppe");
        temperate_rainforest = registerBiome(new TemperateRainforestBiome(), "temperate_rainforest");
        tropical_rainforest = registerBiome(new TropicalRainforestBiome(), "tropical_rainforest");
        tropics = registerBiome(new TropicsBiome(), "tropics");
        tundra = registerBiome(new TundraBiome(), "tundra");
        volcano = registerBiome(new VolcanoBiome(), "volcano");
        wasteland = registerBiome(new WastelandBiome(), "wasteland");
        wetland = registerBiome(new WetlandBiome(), "wetland");
        woodland = registerBiome(new WoodlandBiome(), "woodland");
        xeric_shrubland = registerBiome(new XericShrublandBiome(), "xeric_shrubland");

        // Note: Rarity supports two decimal places
        registerSubBiome(Biomes.DESERT, oasis, 0.1F, 100);
        registerSubBiome(bog, bog_mire, 0.5F, 100);
        registerSubBiome(brushland, xeric_shrubland, 1.0F, 100);
        registerSubBiome(grove, grove_orchard, 0.75F, 100);
        registerSubBiome(highland, highland_moor, 0.75F, 100);
        registerSubBiome(meadow, flower_meadow, 0.5F, 100);
        registerSubBiome(prairie, pasture, 1.0F, 100);

        registerIslandBiome(origin_hills, BOPClimates.COOL_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.DRY_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.WET_TEMPERATE, 50);
        
        registerIslandBiome(tropics, BOPClimates.SUBTROPICAL, 50);
        registerIslandBiome(tropics, BOPClimates.TROPICAL, 50);
        registerIslandBiome(tropics, BOPClimates.HOT_DESERT, 50);
    }
    
    private static void registerBiomeDictionaryTags()
    {
    	registerBiomeToDictionary(BOPBiomes.alps_foothills, Type.MOUNTAIN, Type.SNOWY, Type.FOREST, Type.SPARSE, Type.COLD, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.alps, Type.MOUNTAIN, Type.SNOWY, Type.COLD, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.bayou, Type.SWAMP, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.bog, Type.SWAMP, Type.DEAD, Type.WET);
        registerBiomeToDictionary(BOPBiomes.bog_mire, Type.SWAMP, Type.DEAD, Type.WASTELAND, Type.WET);
        registerBiomeToDictionary(BOPBiomes.boreal_forest, Type.FOREST, Type.CONIFEROUS, Type.HILLS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.brushland, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.chaparral, Type.PLAINS, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.cherry_blossom_grove, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.cold_desert, Type.SNOWY, Type.DRY, Type.COLD);
        registerBiomeToDictionary(BOPBiomes.coniferous_forest, Type.CONIFEROUS, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.dead_forest, Type.FOREST, Type.DEAD, Type.COLD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.floodplain, Type.JUNGLE, Type.WATER, Type.HOT, Type.WET);
        registerBiomeToDictionary(BOPBiomes.flower_meadow, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.grassland, Type.PLAINS, Type.HILLS, Type.WET);    
        registerBiomeToDictionary(BOPBiomes.gravel_beach, Type.BEACH);
        registerBiomeToDictionary(BOPBiomes.grove, Type.FOREST, Type.PLAINS, Type.LUSH, Type.SPARSE);   
        registerBiomeToDictionary(BOPBiomes.grove_orchard, Type.FOREST, Type.PLAINS, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.highland, Type.MOUNTAIN, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.highland_moor, Type.HILLS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.lavender_field, Type.PLAINS, Type.MAGICAL, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.lush_swamp, Type.SWAMP, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.maple_woods, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.marsh, Type.WATER, Type.WET, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.meadow, Type.PLAINS, Type.FOREST, Type.LUSH, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.mystic_grove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.oasis, Type.SANDY, Type.LUSH, Type.JUNGLE, Type.HOT, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.ominous_woods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.DEAD, Type.WET, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.origin_beach, Type.BEACH, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.origin_hills, Type.WATER, Type.FOREST, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.outback, Type.SANDY, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.overgrown_cliffs, Type.MOUNTAIN, Type.HILLS, Type.LUSH, Type.JUNGLE, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.pasture, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.rainforest, Type.JUNGLE, Type.FOREST, Type.LUSH, Type.HILLS, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.redwood_forest, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.redwood_forest_edge, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.scrubland, Type.SAVANNA, Type.SPARSE, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.seasonal_forest, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.shield, Type.FOREST, Type.COLD, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.shrubland, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.silkglade, Type.FOREST, Type.DEAD, Type.SPOOKY, Type.DRY, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.snowy_coniferous_forest, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.snowy_forest, Type.SNOWY, Type.FOREST, Type.COLD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.steppe, Type.PLAINS, Type.SANDY, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.temperate_rainforest, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.tropical_rainforest, Type.JUNGLE, Type.LUSH, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.tropics, Type.WATER, Type.JUNGLE, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.tundra, Type.COLD, Type.WASTELAND, Type.DEAD, Type.WET, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.volcano, Type.WATER, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.wasteland, Type.WASTELAND, Type.DEAD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.wetland, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.white_beach, Type.BEACH);
        registerBiomeToDictionary(BOPBiomes.woodland, Type.FOREST, Type.DRY, Type.DENSE);
        registerBiomeToDictionary(BOPBiomes.xeric_shrubland, Type.SANDY, Type.SAVANNA, Type.LUSH, Type.HOT, Type.DRY, Type.SPARSE);
        
        //registerBiomeToDictionary(BOPBiomes.corrupted_sands, Type.NETHER, Type.HOT, Type.DRY, Type.SANDY, Type.DENSE);
        //registerBiomeToDictionary(BOPBiomes.fungi_forest, Type.NETHER, Type.HOT, Type.MUSHROOM, Type.DENSE);
        //registerBiomeToDictionary(BOPBiomes.phantasmagoric_inferno, Type.NETHER, Type.HOT, Type.WASTELAND, Type.DRY, Type.MAGICAL, Type.SPOOKY);
        //registerBiomeToDictionary(BOPBiomes.undergarden, Type.NETHER, Type.HOT, Type.LUSH);
        //registerBiomeToDictionary(BOPBiomes.visceral_heap, Type.NETHER, Type.HOT, Type.WET);
        
    }
    
    private static void registerBiomeToDictionary(Optional<Biome> biome, Type...types)
    {
        if (biome.isPresent())
        {
            BiomeDictionary.addTypes(biome.get(), types);
        }
    }

    public static Optional<Biome> registerBiome(BiomeBOP biome, String name)
    {
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        
        if (biome.canSpawnInBiome)
        {
        	BiomeManager.addSpawnBiome(biome);
        }

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

    public static void registerIslandBiome(Biome biome, BOPClimates climate, int weight)
    {
        islandBiomes.add(IRegistry.BIOME.getId(biome));
        climate.addIslandBiome(weight, biome);
    }

    public static void registerIslandBiome(Optional<Biome> biome, BOPClimates climate, int weight)
    {
        if (!biome.isPresent())
            return;

        registerIslandBiome(biome.get(), climate, weight);
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
