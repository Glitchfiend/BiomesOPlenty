/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeBOP;
import biomesoplenty.common.biome.nether.*;
import biomesoplenty.common.biome.overworld.*;
import biomesoplenty.common.world.WorldTypeBOP;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static biomesoplenty.api.biome.BOPBiomes.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static WorldTypeBOP worldType;

    public static Multimap<Integer, WeightedSubBiome> subBiomes = HashMultimap.create();
    public static List<Integer> islandBiomes = Lists.newArrayList();

    public static void setup()
    {
        worldType = new WorldTypeBOP();
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        //Technical Biomes (Need to be registered before main biomes that use them)
        mangrove = registerBiome(new MangroveBiome(), "mangrove");
        gravel_beach = registerBiome(new GravelBeachBiome(), "gravel_beach");
        origin_beach = registerBiome(new OriginBeachBiome(), "origin_beach");
        white_beach = registerBiome(new WhiteBeachBiome(), "white_beach");
    	alps_foothills = registerBiome(new AlpsFoothillsBiome(), "alps_foothills");
        redwood_forest_edge = registerBiome(new RedwoodForestEdgeBiome(), "redwood_forest_edge");
        volcano_edge = registerBiome(new VolcanoEdgeBiome(), "volcano_edge");

        //Overworld Biomes
    	alps = registerBiome(new AlpsBiome(), "alps");
    	bayou = registerBiome(new BayouBiome(), "bayou");
        bog = registerBiome(new BogBiome(), "bog");
        boreal_forest = registerBiome(new BorealForestBiome(), "boreal_forest");
    	brushland = registerBiome(new BrushlandBiome(), "brushland");
        chaparral = registerBiome(new ChaparralBiome(), "chaparral");
    	cherry_blossom_grove = registerBiome(new CherryBlossomGroveBiome(), "cherry_blossom_grove");
    	cold_desert = registerBiome(new ColdDesertBiome(), "cold_desert");
        coniferous_forest = registerBiome(new ConiferousForestBiome(), "coniferous_forest");
        dead_forest = registerBiome(new DeadForestBiome(), "dead_forest");
        fir_clearing = registerBiome(new FirClearingBiome(), "fir_clearing");
        floodplain = registerBiome(new FloodplainBiome(), "floodplain");
        flower_meadow = registerBiome(new FlowerMeadowBiome(), "flower_meadow");
        grassland = registerBiome(new GrasslandBiome(), "grassland");
        grove = registerBiome(new GroveBiome(), "grove");
        orchard = registerBiome(new OrchardBiome(), "orchard");
        highland = registerBiome(new HighlandBiome(), "highland");
        highland_moor = registerBiome(new HighlandMoorBiome(), "highland_moor");
        lavender_field = registerBiome(new LavenderFieldBiome(), "lavender_field");
        lush_grassland = registerBiome(new LushGrasslandBiome(), "lush_grassland");
        lush_swamp = registerBiome(new LushSwampBiome(), "lush_swamp");
        maple_woods = registerBiome(new MapleWoodsBiome(), "maple_woods");
        marsh = registerBiome(new MarshBiome(), "marsh");
        meadow = registerBiome(new MeadowBiome(), "meadow");
        mire = registerBiome(new MireBiome(), "mire");
        mystic_grove = registerBiome(new MysticGroveBiome(), "mystic_grove");
        oasis = registerBiome(new OasisBiome(), "oasis");
        ominous_woods = registerBiome(new OminousWoodsBiome(), "ominous_woods");
        origin_hills = registerBiome(new OriginHillsBiome(), "origin_hills");
        outback = registerBiome(new OutbackBiome(), "outback");
        overgrown_cliffs = registerBiome(new OvergrownCliffsBiome(), "overgrown_cliffs");
        pasture = registerBiome(new PastureBiome(), "pasture");
        prairie = registerBiome(new PrairieBiome(), "prairie");
        pumpkin_patch = registerBiome(new PumpkinPatchBiome(), "pumpkin_patch");
        rainbow_valley = registerBiome(new RainbowValleyBiome(), "rainbow_valley");
        rainforest = registerBiome(new RainforestBiome(), "rainforest");
        redwood_forest = registerBiome(new RedwoodForestBiome(), "redwood_forest");
        scrubland = registerBiome(new ScrublandBiome(), "scrubland");
        seasonal_forest = registerBiome(new SeasonalForestBiome(), "seasonal_forest");
        shield = registerBiome(new ShieldBiome(), "shield");
        shrubland = registerBiome(new ShrublandBiome(), "shrubland");
        silkglade = registerBiome(new SilkgladeBiome(), "silkglade");
        snowy_coniferous_forest = registerBiome(new SnowyConiferousForestBiome(), "snowy_coniferous_forest");
        snowy_fir_clearing = registerBiome(new SnowyFirClearingBiome(), "snowy_fir_clearing");
        snowy_forest = registerBiome(new SnowyForestBiome(), "snowy_forest");
        steppe = registerBiome(new SteppeBiome(), "steppe");
        temperate_rainforest = registerBiome(new TemperateRainforestBiome(), "temperate_rainforest");
        temperate_rainforest_hills = registerBiome(new TemperateRainforestHillsBiome(), "temperate_rainforest_hills");
        tropical_rainforest = registerBiome(new TropicalRainforestBiome(), "tropical_rainforest");
        tropics = registerBiome(new TropicsBiome(), "tropics");
        tundra = registerBiome(new TundraBiome(), "tundra");
        volcano = registerBiome(new VolcanoBiome(), "volcano");
        wasteland = registerBiome(new WastelandBiome(), "wasteland");
        wetland = registerBiome(new WetlandBiome(), "wetland");
        woodland = registerBiome(new WoodlandBiome(), "woodland");
        xeric_shrubland = registerBiome(new XericShrublandBiome(), "xeric_shrubland");

        //Nether Biomes
        ashen_inferno = registerBiome(new AshenInfernoBiome(), "ashen_inferno");
        fungi_forest = registerBiome(new FungiForestBiome(), "fungi_forest");
        glowstone_grotto = registerBiome(new GlowstoneGrottoBiome(), "glowstone_grotto");
        undergarden = registerBiome(new UndergardenBiome(), "undergarden");
        visceral_heap = registerBiome(new VisceralHeapBiome(), "visceral_heap");

        //Sub/Island Biomes (Note: Rarity supports two decimal places)
        registerSubBiome(Biomes.DESERT, oasis, 0.1F, 100);
        registerSubBiome(brushland, xeric_shrubland, 1.0F, 100);
        registerSubBiome(coniferous_forest, fir_clearing, 0.38F, 100);
        registerSubBiome(highland, highland_moor, 0.75F, 100);
        registerSubBiome(meadow, flower_meadow, 0.5F, 100);
        registerSubBiome(prairie, pasture, 1.0F, 100);
        registerSubBiome(seasonal_forest, pumpkin_patch, 0.45F, 100);
        registerSubBiome(snowy_coniferous_forest, snowy_fir_clearing, 0.5F, 100);
        registerSubBiome(temperate_rainforest, temperate_rainforest_hills, 0.9F, 100);

        registerIslandBiome(origin_hills, BOPClimates.COOL_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.DRY_TEMPERATE, 50);
        registerIslandBiome(origin_hills, BOPClimates.WET_TEMPERATE, 75);
        
        registerIslandBiome(volcano, BOPClimates.WARM_TEMPERATE, 75);
        registerIslandBiome(volcano, BOPClimates.MEDITERRANEAN, 75);
        registerIslandBiome(volcano, BOPClimates.SAVANNA, 50);

        registerIslandBiome(rainbow_valley, BOPClimates.WET_TEMPERATE, 25);
        registerIslandBiome(rainbow_valley, BOPClimates.WARM_TEMPERATE, 25);
        registerIslandBiome(rainbow_valley, BOPClimates.MEDITERRANEAN, 25);

        registerIslandBiome(tropics, BOPClimates.SUBTROPICAL, 75);
        registerIslandBiome(tropics, BOPClimates.TROPICAL, 50);
        registerIslandBiome(tropics, BOPClimates.HOT_DESERT, 50);

        registerBiomeDictionaryTags();
        registerVillagerTypes();
    }
    
    private static void registerBiomeDictionaryTags()
    {
        //Overworld Biomes
        registerBiomeToDictionary(alps, Type.MOUNTAIN, Type.SNOWY, Type.COLD);
        registerBiomeToDictionary(alps_foothills, Type.MOUNTAIN, Type.SNOWY, Type.FOREST, Type.SPARSE, Type.COLD);
        registerBiomeToDictionary(bayou, Type.SWAMP, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(bog, Type.SWAMP, Type.CONIFEROUS, Type.COLD, Type.LUSH, Type.WET);
        registerBiomeToDictionary(boreal_forest, Type.FOREST, Type.CONIFEROUS, Type.HILLS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(brushland, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(chaparral, Type.PLAINS, Type.DRY, Type.HILLS);
        registerBiomeToDictionary(cherry_blossom_grove, Type.FOREST, Type.MAGICAL, Type.LUSH, Type.SPARSE);
        registerBiomeToDictionary(cold_desert, Type.SNOWY, Type.DRY, Type.COLD);
        registerBiomeToDictionary(coniferous_forest, Type.CONIFEROUS, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(dead_forest, Type.FOREST, Type.DEAD, Type.COLD, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(fir_clearing, Type.CONIFEROUS, Type.FOREST, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(floodplain, Type.JUNGLE, Type.WATER, Type.HOT, Type.WET);
        registerBiomeToDictionary(flower_meadow, Type.PLAINS, Type.LUSH);
        registerBiomeToDictionary(grassland, Type.PLAINS, Type.HILLS, Type.WET);
        registerBiomeToDictionary(gravel_beach, Type.BEACH);
        registerBiomeToDictionary(grove, Type.FOREST, Type.PLAINS, Type.LUSH, Type.SPARSE);
        registerBiomeToDictionary(highland, Type.MOUNTAIN, Type.HILLS, Type.WET);
        registerBiomeToDictionary(highland_moor, Type.HILLS, Type.WET);
        registerBiomeToDictionary(lavender_field, Type.PLAINS, Type.MAGICAL, Type.LUSH);
        registerBiomeToDictionary(lush_grassland, Type.JUNGLE, Type.PLAINS, Type.HILLS, Type.WET, Type.HOT, Type.LUSH);
        registerBiomeToDictionary(lush_swamp, Type.SWAMP, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(mangrove, Type.WATER, Type.WET, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(maple_woods, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(marsh, Type.WATER, Type.WET, Type.LUSH);
        registerBiomeToDictionary(meadow, Type.PLAINS, Type.FOREST, Type.LUSH, Type.COLD);
        registerBiomeToDictionary(mire, Type.SWAMP, Type.DEAD, Type.WET);
        registerBiomeToDictionary(mystic_grove, Type.MAGICAL, Type.FOREST, Type.LUSH, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(oasis, Type.SANDY, Type.LUSH, Type.JUNGLE, Type.HOT, Type.SPARSE);
        registerBiomeToDictionary(ominous_woods, Type.MAGICAL, Type.FOREST, Type.SPOOKY, Type.DEAD, Type.DENSE, Type.RARE);
        registerBiomeToDictionary(orchard, Type.FOREST, Type.PLAINS, Type.DENSE, Type.LUSH);
        registerBiomeToDictionary(origin_beach, Type.BEACH, Type.RARE);
        registerBiomeToDictionary(origin_hills, Type.WATER, Type.FOREST, Type.RARE);
        registerBiomeToDictionary(outback, Type.SANDY, Type.SAVANNA, Type.HOT, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(overgrown_cliffs, Type.MOUNTAIN, Type.HILLS, Type.LUSH, Type.JUNGLE, Type.DENSE, Type.HOT);
        registerBiomeToDictionary(pasture, Type.PLAINS, Type.DRY);
        registerBiomeToDictionary(prairie, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(pumpkin_patch, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(rainbow_valley, Type.WATER, Type.FOREST, Type.LUSH, Type.DENSE, Type.MAGICAL, Type.RARE);
        registerBiomeToDictionary(rainforest, Type.JUNGLE, Type.FOREST, Type.LUSH, Type.HILLS, Type.WET, Type.DENSE);
        registerBiomeToDictionary(redwood_forest, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(redwood_forest_edge, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(scrubland, Type.SAVANNA, Type.SPARSE, Type.DRY);
        registerBiomeToDictionary(seasonal_forest, Type.FOREST, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(shield, Type.FOREST, Type.COLD, Type.WET, Type.DENSE);
        registerBiomeToDictionary(shrubland, Type.PLAINS, Type.DRY, Type.SPARSE);
        registerBiomeToDictionary(silkglade, Type.FOREST, Type.DEAD, Type.SPOOKY, Type.DRY, Type.DENSE);
        registerBiomeToDictionary(snowy_coniferous_forest, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.DENSE);
        registerBiomeToDictionary(snowy_fir_clearing, Type.FOREST, Type.CONIFEROUS, Type.SNOWY, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(snowy_forest, Type.SNOWY, Type.FOREST, Type.COLD, Type.SPARSE);
        registerBiomeToDictionary(steppe, Type.PLAINS, Type.HILLS, Type.DRY);
        registerBiomeToDictionary(temperate_rainforest, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(temperate_rainforest_hills, Type.FOREST, Type.HILLS, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(tropical_rainforest, Type.JUNGLE, Type.LUSH, Type.HOT, Type.WET, Type.DENSE);
        registerBiomeToDictionary(tropics, Type.WATER, Type.JUNGLE, Type.LUSH, Type.DENSE);
        registerBiomeToDictionary(tundra, Type.COLD, Type.WASTELAND, Type.DEAD, Type.SPARSE);
        registerBiomeToDictionary(volcano, Type.WATER, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(volcano_edge, Type.WATER, Type.DEAD, Type.WASTELAND, Type.MOUNTAIN, Type.HOT, Type.DRY);
        registerBiomeToDictionary(wasteland, Type.WASTELAND, Type.DEAD, Type.DRY, Type.SPARSE, Type.HOT);
        registerBiomeToDictionary(wetland, Type.SWAMP, Type.FOREST, Type.LUSH, Type.WET, Type.DENSE);
        registerBiomeToDictionary(white_beach, Type.BEACH);
        registerBiomeToDictionary(woodland, Type.FOREST, Type.DENSE);
        registerBiomeToDictionary(xeric_shrubland, Type.SANDY, Type.SAVANNA, Type.LUSH, Type.HOT, Type.DRY, Type.SPARSE);

        //Nether Biomes
        registerBiomeToDictionary(ashen_inferno, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(fungi_forest, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(glowstone_grotto, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(undergarden, Type.NETHER, Type.HOT);
        registerBiomeToDictionary(visceral_heap, Type.NETHER, Type.HOT);
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(alps, IVillagerType.SNOW);
        registerVillagerType(alps_foothills, IVillagerType.SNOW);
        registerVillagerType(bayou, IVillagerType.SWAMP);
        registerVillagerType(bog, IVillagerType.SWAMP);
        registerVillagerType(boreal_forest, IVillagerType.TAIGA);
        registerVillagerType(brushland, IVillagerType.SAVANNA);
        registerVillagerType(chaparral, IVillagerType.PLAINS);
        registerVillagerType(cherry_blossom_grove, IVillagerType.PLAINS);
        registerVillagerType(cold_desert, IVillagerType.SNOW);
        registerVillagerType(coniferous_forest, IVillagerType.TAIGA);
        registerVillagerType(dead_forest, IVillagerType.TAIGA);
        registerVillagerType(fir_clearing, IVillagerType.TAIGA);
        registerVillagerType(floodplain, IVillagerType.JUNGLE);
        registerVillagerType(flower_meadow, IVillagerType.TAIGA);
        registerVillagerType(grassland, IVillagerType.PLAINS);
        registerVillagerType(gravel_beach, IVillagerType.PLAINS);
        registerVillagerType(grove, IVillagerType.PLAINS);
        registerVillagerType(highland, IVillagerType.PLAINS);
        registerVillagerType(highland_moor, IVillagerType.PLAINS);
        registerVillagerType(lavender_field, IVillagerType.PLAINS);
        registerVillagerType(lush_grassland, IVillagerType.JUNGLE);
        registerVillagerType(lush_swamp, IVillagerType.JUNGLE);
        registerVillagerType(mangrove, IVillagerType.SWAMP);
        registerVillagerType(maple_woods, IVillagerType.TAIGA);
        registerVillagerType(marsh, IVillagerType.SWAMP);
        registerVillagerType(meadow, IVillagerType.TAIGA);
        registerVillagerType(mire, IVillagerType.SWAMP);
        registerVillagerType(mystic_grove, IVillagerType.PLAINS);
        registerVillagerType(oasis, IVillagerType.DESERT);
        registerVillagerType(ominous_woods, IVillagerType.SWAMP);
        registerVillagerType(orchard, IVillagerType.PLAINS);
        registerVillagerType(origin_beach, IVillagerType.PLAINS);
        registerVillagerType(origin_hills, IVillagerType.PLAINS);
        registerVillagerType(outback, IVillagerType.SAVANNA);
        registerVillagerType(overgrown_cliffs, IVillagerType.JUNGLE);
        registerVillagerType(pasture, IVillagerType.PLAINS);
        registerVillagerType(prairie, IVillagerType.PLAINS);
        registerVillagerType(pumpkin_patch, IVillagerType.PLAINS);
        registerVillagerType(rainbow_valley, IVillagerType.PLAINS);
        registerVillagerType(rainforest, IVillagerType.JUNGLE);
        registerVillagerType(redwood_forest, IVillagerType.PLAINS);
        registerVillagerType(redwood_forest_edge, IVillagerType.PLAINS);
        registerVillagerType(scrubland, IVillagerType.SAVANNA);
        registerVillagerType(seasonal_forest, IVillagerType.PLAINS);
        registerVillagerType(shield, IVillagerType.TAIGA);
        registerVillagerType(shrubland, IVillagerType.PLAINS);
        registerVillagerType(silkglade, IVillagerType.SWAMP);
        registerVillagerType(snowy_coniferous_forest, IVillagerType.SNOW);
        registerVillagerType(snowy_fir_clearing, IVillagerType.SNOW);
        registerVillagerType(snowy_forest, IVillagerType.SNOW);
        registerVillagerType(steppe, IVillagerType.PLAINS);
        registerVillagerType(temperate_rainforest, IVillagerType.PLAINS);
        registerVillagerType(temperate_rainforest_hills, IVillagerType.PLAINS);
        registerVillagerType(tropical_rainforest, IVillagerType.JUNGLE);
        registerVillagerType(tropics, IVillagerType.JUNGLE);
        registerVillagerType(tundra, IVillagerType.TAIGA);
        registerVillagerType(volcano, IVillagerType.PLAINS);
        registerVillagerType(volcano_edge, IVillagerType.PLAINS);
        registerVillagerType(wasteland, IVillagerType.PLAINS);
        registerVillagerType(wetland, IVillagerType.SWAMP);
        registerVillagerType(white_beach, IVillagerType.JUNGLE);
        registerVillagerType(woodland, IVillagerType.PLAINS);
        registerVillagerType(xeric_shrubland, IVillagerType.DESERT);
    }
    
    private static void registerBiomeToDictionary(Optional<Biome> biome, Type...types)
    {
        if (biome.isPresent())
        {
            BiomeDictionary.addTypes(biome.get(), types);
        }
    }

    private static void registerVillagerType(Optional<Biome> biome, IVillagerType type)
    {
        if (biome.isPresent())
        {
            IVillagerType.BY_BIOME.put(biome.get(), type);
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

        subBiomes.put(Registry.BIOME.getId(parent), new WeightedSubBiome(child.get(), rarity, weight));
    }
    
    public static void registerSubBiome(Optional<Biome> parent, Optional<Biome> child, float rarity, int weight)
    {
    	if (!parent.isPresent())
            return;
    	
        if (!child.isPresent())
            return;

        subBiomes.put(Registry.BIOME.getId(parent.get()), new WeightedSubBiome(child.get(), rarity, weight));
    }

    public static void registerIslandBiome(Biome biome, BOPClimates climate, int weight)
    {
        islandBiomes.add(Registry.BIOME.getId(biome));
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
