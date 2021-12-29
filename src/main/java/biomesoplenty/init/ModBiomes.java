/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPNetherBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomes;
import biomesoplenty.common.worldgen.BOPMultiNoiseBiomeSource;
import biomesoplenty.common.worldgen.BOPNoiseBasedChunkGenerator;
import biomesoplenty.common.worldgen.BOPNoises;
import biomesoplenty.common.worldgen.BOPWorldType;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static BOPWorldType bopWorldType = new BOPWorldType();

    public static void setup()
    {
        registerNoise(BOPNoises.UNIQUENESS, -6, 1.0D, 1.5D, 1.0D, 1.5D, 0.0D);
        registerNoise(BOPNoises.UNIQUENESS_LARGE, -8, 1.0D, 1.5D, 1.0D, 1.5D, 0.0D);
        registerNoise(BOPNoises.RARENESS, -9, 0.6D, 1.5D, 0.6D, 0.0D, 0.0D);
        registerNoise(BOPNoises.RARENESS_LARGE, -11, 0.6D, 1.5D, 0.6D, 0.0D, 0.0D);

        // Obtain the game data logger and disable it temporarily
        Logger gameDataLogger = (Logger) LogManager.getLogger(GameData.class);
        Level oldLevel = gameDataLogger.getLevel();
        gameDataLogger.setLevel(Level.OFF);

        // Register our world type
        // We intentionally use the minecraft namespace so we continue using "biomesoplenty" in server.properties
        // This is markedly better than the alternative of biomesoplenty:biomesoplenty.
        // We do this with GameData logging disabled to prevent people whining at us.
        bopWorldType.setRegistryName(new ResourceLocation("biomesoplenty"));
        ForgeRegistries.WORLD_TYPES.register(bopWorldType);

        // Re-enable the game data logger
        gameDataLogger.setLevel(oldLevel);

        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(BiomesOPlenty.MOD_ID, "multi_noise"), BOPMultiNoiseBiomeSource.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(BiomesOPlenty.MOD_ID, "noise"), BOPNoiseBasedChunkGenerator.CODEC);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        registerBiome(BOPBiomes.BAMBOO_GROVE, BOPOverworldBiomes.bambooGrove());
        registerBiome(BOPBiomes.BAYOU, BOPOverworldBiomes.bayou());
        registerBiome(BOPBiomes.BOG, BOPOverworldBiomes.bog());
        registerBiome(BOPBiomes.BOREAL_FOREST, BOPOverworldBiomes.borealForest());
        registerBiome(BOPBiomes.CHERRY_BLOSSOM_GROVE, BOPOverworldBiomes.cherryBlossomGrove());
        registerBiome(BOPBiomes.COLD_DESERT, BOPOverworldBiomes.coldDesert());
        registerBiome(BOPBiomes.CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(false));
        registerBiome(BOPBiomes.CRAG, BOPOverworldBiomes.crag());
        registerBiome(BOPBiomes.DEAD_FOREST, BOPOverworldBiomes.deadForest());
        registerBiome(BOPBiomes.DRYLAND, BOPOverworldBiomes.dryland());
        registerBiome(BOPBiomes.DUNE_BEACH, BOPOverworldBiomes.duneBeach());
        registerBiome(BOPBiomes.FIELD, BOPOverworldBiomes.field(false));
        registerBiome(BOPBiomes.FIR_CLEARING, BOPOverworldBiomes.firClearing(false));
        registerBiome(BOPBiomes.FLOODPLAIN, BOPOverworldBiomes.floodplain());
        registerBiome(BOPBiomes.FORESTED_FIELD, BOPOverworldBiomes.field(true));
        registerBiome(BOPBiomes.FUNGAL_JUNGLE, BOPOverworldBiomes.fungalJungle());
        registerBiome(BOPBiomes.GRASSLAND, BOPOverworldBiomes.grassland());
        registerBiome(BOPBiomes.HIGHLAND, BOPOverworldBiomes.highland(false));
        registerBiome(BOPBiomes.HIGHLAND_MOOR, BOPOverworldBiomes.highland(true));
        registerBiome(BOPBiomes.JADE_CLIFFS, BOPOverworldBiomes.jadeCliffs());
        registerBiome(BOPBiomes.LAVENDER_FIELD, BOPOverworldBiomes.lavenderField(false));
        registerBiome(BOPBiomes.LAVENDER_FOREST, BOPOverworldBiomes.lavenderField(true));
        registerBiome(BOPBiomes.LUSH_DESERT, BOPOverworldBiomes.lushDesert());
        registerBiome(BOPBiomes.LUSH_SAVANNA, BOPOverworldBiomes.lushSavanna());
        registerBiome(BOPBiomes.MAPLE_WOODS, BOPOverworldBiomes.mapleWoods(false));
        registerBiome(BOPBiomes.MARSH, BOPOverworldBiomes.marsh());
        registerBiome(BOPBiomes.MEDITERRANEAN_FOREST, BOPOverworldBiomes.mediterraneanForest());
        registerBiome(BOPBiomes.MEDITERRANEAN_LAKES, BOPOverworldBiomes.mediterraneanLakes());
        registerBiome(BOPBiomes.MUSKEG, BOPOverworldBiomes.muskeg());
        registerBiome(BOPBiomes.MYSTIC_GROVE, BOPOverworldBiomes.mysticGrove());
        registerBiome(BOPBiomes.OLD_GROWTH_DEAD_FOREST, BOPOverworldBiomes.oldGrowthDeadForest());
        registerBiome(BOPBiomes.OLD_GROWTH_WOODLAND, BOPOverworldBiomes.woodland(true));
        registerBiome(BOPBiomes.OMINOUS_WOODS, BOPOverworldBiomes.ominousWoods());
        registerBiome(BOPBiomes.ORCHARD, BOPOverworldBiomes.orchard());
        registerBiome(BOPBiomes.ORIGIN_VALLEY, BOPOverworldBiomes.originValley());
        registerBiome(BOPBiomes.PASTURE, BOPOverworldBiomes.pasture());
        registerBiome(BOPBiomes.PRAIRIE, BOPOverworldBiomes.prairie());
        registerBiome(BOPBiomes.PUMPKIN_PATCH, BOPOverworldBiomes.pumpkinPatch());
        registerBiome(BOPBiomes.RAINBOW_HILLS, BOPOverworldBiomes.rainbowHills());
        registerBiome(BOPBiomes.RAINFOREST, BOPOverworldBiomes.rainforest());
        registerBiome(BOPBiomes.RAINFOREST_CLIFFS, BOPOverworldBiomes.rainforestCliffs());
        registerBiome(BOPBiomes.REDWOOD_FOREST, BOPOverworldBiomes.redwoodForest());
        registerBiome(BOPBiomes.ROCKY_SHRUBLAND, BOPOverworldBiomes.rockyShrubland());
        registerBiome(BOPBiomes.SCRUBLAND, BOPOverworldBiomes.scrubland(false));
        registerBiome(BOPBiomes.SEASONAL_FOREST, BOPOverworldBiomes.seasonalForest());
        registerBiome(BOPBiomes.SHRUBLAND, BOPOverworldBiomes.shrubland());
        registerBiome(BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(true));
        registerBiome(BOPBiomes.SNOWY_FIR_CLEARING, BOPOverworldBiomes.firClearing(true));
        registerBiome(BOPBiomes.SNOWY_MAPLE_WOODS, BOPOverworldBiomes.mapleWoods(true));
        registerBiome(BOPBiomes.TROPICS, BOPOverworldBiomes.tropics());
        registerBiome(BOPBiomes.TUNDRA, BOPOverworldBiomes.tundra());
        registerBiome(BOPBiomes.VOLCANIC_PLAINS, BOPOverworldBiomes.volcanicPlains());
        registerBiome(BOPBiomes.VOLCANO, BOPOverworldBiomes.volcano());
        registerBiome(BOPBiomes.WASTELAND, BOPOverworldBiomes.wasteland(false));
        registerBiome(BOPBiomes.WETLAND, BOPOverworldBiomes.wetland());
        registerBiome(BOPBiomes.WOODED_SCRUBLAND, BOPOverworldBiomes.scrubland(true));
        registerBiome(BOPBiomes.WOODED_WASTELAND, BOPOverworldBiomes.wasteland(true));
        registerBiome(BOPBiomes.WOODLAND, BOPOverworldBiomes.woodland(false));

        // Cave biomes
        registerBiome(BOPBiomes.GLOWING_GROTTO, BOPOverworldBiomes.glowingGrotto());
        registerBiome(BOPBiomes.SPIDER_NEST, BOPOverworldBiomes.spiderNest());

        // Nether biomes
        registerBiome(BOPBiomes.CRYSTALLINE_CHASM, BOPNetherBiomes.crystallineChasm());
        registerBiome(BOPBiomes.ERUPTING_INFERNO, BOPNetherBiomes.eruptingInferno());
        registerBiome(BOPBiomes.UNDERGROWTH, BOPNetherBiomes.undergrowth());
        registerBiome(BOPBiomes.VISCERAL_HEAP, BOPNetherBiomes.visceralHeap());
        registerBiome(BOPBiomes.WITHERED_ABYSS, BOPNetherBiomes.witheredAbyss());

        registerBiomeDictionaryTags();
        registerVillagerTypes();
    }

    private static void registerBiomeDictionaryTags()
    {
        //Overworld Biomes
        registerBiomeToDictionary(BOPBiomes.BAMBOO_GROVE, Type.OVERWORLD, Type.WET, Type.LUSH, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.BAYOU, Type.OVERWORLD, Type.HOT, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.BOG, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.BOREAL_FOREST, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.CHERRY_BLOSSOM_GROVE, Type.OVERWORLD, Type.WET, Type.LUSH, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.COLD_DESERT, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.SNOWY, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.CONIFEROUS_FOREST, Type.OVERWORLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.CRAG, Type.OVERWORLD, Type.SPARSE, Type.MOUNTAIN, Type.WASTELAND);
        registerBiomeToDictionary(BOPBiomes.DEAD_FOREST, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DEAD, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.DRYLAND, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.SANDY);
        registerBiomeToDictionary(BOPBiomes.DUNE_BEACH, Type.OVERWORLD, Type.BEACH, Type.LUSH);
        registerBiomeToDictionary(BOPBiomes.FIR_CLEARING, Type.OVERWORLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.FIELD, Type.OVERWORLD, Type.WET, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.FLOODPLAIN, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.FORESTED_FIELD, Type.OVERWORLD, Type.WET, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.FUNGAL_JUNGLE, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.MUSHROOM, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.GRASSLAND, Type.OVERWORLD, Type.SPARSE, Type.PLAINS, Type.WET);
        registerBiomeToDictionary(BOPBiomes.HIGHLAND, Type.OVERWORLD, Type.SPARSE, Type.MOUNTAIN, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.HIGHLAND_MOOR, Type.OVERWORLD, Type.SPARSE, Type.WET, Type.MOUNTAIN, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.JADE_CLIFFS, Type.OVERWORLD, Type.CONIFEROUS, Type.MAGICAL, Type.RARE, Type.FOREST, Type.MOUNTAIN);
        registerBiomeToDictionary(BOPBiomes.LAVENDER_FIELD, Type.OVERWORLD, Type.LUSH, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.LAVENDER_FOREST, Type.OVERWORLD, Type.DENSE, Type.LUSH, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.LUSH_DESERT, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA, Type.LUSH, Type.RARE, Type.SANDY);
        registerBiomeToDictionary(BOPBiomes.LUSH_SAVANNA, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA, Type.LUSH, Type.RARE, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.MAPLE_WOODS, Type.OVERWORLD, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.MARSH, Type.OVERWORLD, Type.SPARSE, Type.WET, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.MEDITERRANEAN_FOREST, Type.OVERWORLD, Type.FOREST, Type.HILLS);
        registerBiomeToDictionary(BOPBiomes.MEDITERRANEAN_LAKES, Type.OVERWORLD, Type.SPARSE);
        registerBiomeToDictionary(BOPBiomes.MUSKEG, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.WET, Type.DEAD, Type.RARE, Type.SWAMP, Type.SNOWY);
        registerBiomeToDictionary(BOPBiomes.MYSTIC_GROVE, Type.OVERWORLD, Type.WET, Type.LUSH, Type.MAGICAL, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.OLD_GROWTH_DEAD_FOREST, Type.OVERWORLD, Type.COLD, Type.DEAD, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.OLD_GROWTH_WOODLAND, Type.OVERWORLD, Type.DENSE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.OMINOUS_WOODS, Type.OVERWORLD, Type.COLD, Type.WET, Type.CONIFEROUS, Type.SPOOKY, Type.RARE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.ORCHARD, Type.OVERWORLD, Type.SPARSE, Type.LUSH, Type.FOREST, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.ORIGIN_VALLEY, Type.OVERWORLD, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.PASTURE, Type.OVERWORLD, Type.DRY, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.PRAIRIE, Type.OVERWORLD, Type.SPARSE, Type.DRY, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.PUMPKIN_PATCH, Type.OVERWORLD, Type.SPARSE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.RAINBOW_HILLS, Type.OVERWORLD, Type.COLD, Type.DENSE, Type.MAGICAL, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(BOPBiomes.RAINFOREST, Type.OVERWORLD, Type.HOT, Type.DENSE, Type.WET, Type.JUNGLE, Type.LUSH, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.RAINFOREST_CLIFFS, Type.OVERWORLD, Type.HOT, Type.DENSE, Type.WET, Type.JUNGLE, Type.LUSH, Type.PLATEAU);
        registerBiomeToDictionary(BOPBiomes.REDWOOD_FOREST, Type.OVERWORLD, Type.DENSE, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.ROCKY_SHRUBLAND, Type.OVERWORLD, Type.PLAINS, Type.HILLS);
        registerBiomeToDictionary(BOPBiomes.SCRUBLAND, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA);
        registerBiomeToDictionary(BOPBiomes.SEASONAL_FOREST, Type.OVERWORLD, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.SHRUBLAND, Type.OVERWORLD, Type.SPARSE, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.SNOWY_CONIFEROUS_FOREST, Type.OVERWORLD, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(BOPBiomes.SNOWY_FIR_CLEARING, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(BOPBiomes.SNOWY_MAPLE_WOODS, Type.OVERWORLD, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
        registerBiomeToDictionary(BOPBiomes.TROPICS, Type.OVERWORLD, Type.HOT, Type.WET, Type.JUNGLE, Type.LUSH, Type.RARE);
        registerBiomeToDictionary(BOPBiomes.TUNDRA, Type.OVERWORLD, Type.COLD, Type.SPARSE, Type.DEAD, Type.PLAINS);
        registerBiomeToDictionary(BOPBiomes.VOLCANIC_PLAINS, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.RARE, Type.BEACH, Type.WASTELAND);
        registerBiomeToDictionary(BOPBiomes.VOLCANO, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.DEAD, Type.RARE, Type.MOUNTAIN, Type.WASTELAND);
        registerBiomeToDictionary(BOPBiomes.WASTELAND, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.DEAD, Type.RARE, Type.WASTELAND);
        registerBiomeToDictionary(BOPBiomes.WETLAND, Type.OVERWORLD, Type.WET, Type.FOREST, Type.SWAMP);
        registerBiomeToDictionary(BOPBiomes.WOODED_SCRUBLAND, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.SAVANNA);
        registerBiomeToDictionary(BOPBiomes.WOODED_WASTELAND, Type.OVERWORLD, Type.HOT, Type.SPARSE, Type.DRY, Type.SAVANNA, Type.DEAD, Type.RARE, Type.WASTELAND);
        registerBiomeToDictionary(BOPBiomes.WOODLAND, Type.OVERWORLD, Type.FOREST);

        // TODO: Glowing Grotto
        // TODO: Spider Nest

        //Nether Biomes
        registerBiomeToDictionary(BOPBiomes.CRYSTALLINE_CHASM, Type.NETHER, Type.HOT, Type.DRY, Type.MAGICAL);
        registerBiomeToDictionary(BOPBiomes.ERUPTING_INFERNO, Type.NETHER, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.UNDERGROWTH, Type.NETHER, Type.HOT, Type.DRY, Type.FOREST);
        registerBiomeToDictionary(BOPBiomes.VISCERAL_HEAP, Type.NETHER, Type.HOT, Type.DRY);
        registerBiomeToDictionary(BOPBiomes.WITHERED_ABYSS, Type.NETHER, Type.HOT, Type.DRY, Type.VOID);
    }

    private static void registerVillagerTypes()
    {
        registerVillagerType(BOPBiomes.BAMBOO_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.BAYOU, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.BOG, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.BOREAL_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.CHERRY_BLOSSOM_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.COLD_DESERT, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.CONIFEROUS_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.CRAG, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.DRYLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.DUNE_BEACH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.FIR_CLEARING, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FLOODPLAIN, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.FORESTED_FIELD, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.FUNGAL_JUNGLE, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.GRASSLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HIGHLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.HIGHLAND_MOOR, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.JADE_CLIFFS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LAVENDER_FIELD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LAVENDER_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.LUSH_DESERT, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.LUSH_SAVANNA, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.MARSH, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.MEDITERRANEAN_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.MEDITERRANEAN_LAKES, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.MUSKEG, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.MYSTIC_GROVE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OLD_GROWTH_DEAD_FOREST, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.OLD_GROWTH_WOODLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.OMINOUS_WOODS, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.ORCHARD, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ORIGIN_VALLEY, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PASTURE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PRAIRIE, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.PUMPKIN_PATCH, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.RAINBOW_HILLS, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.RAINFOREST, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.RAINFOREST_CLIFFS, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.REDWOOD_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.ROCKY_SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SCRUBLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.SEASONAL_FOREST, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SHRUBLAND, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.SNOWY_CONIFEROUS_FOREST, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.SNOWY_FIR_CLEARING, VillagerType.SNOW);
        registerVillagerType(BOPBiomes.SNOWY_MAPLE_WOODS, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.TROPICS, VillagerType.JUNGLE);
        registerVillagerType(BOPBiomes.TUNDRA, VillagerType.TAIGA);
        registerVillagerType(BOPBiomes.VOLCANIC_PLAINS, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.VOLCANO, VillagerType.PLAINS);
        registerVillagerType(BOPBiomes.WASTELAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WETLAND, VillagerType.SWAMP);
        registerVillagerType(BOPBiomes.WOODED_SCRUBLAND, VillagerType.SAVANNA);
        registerVillagerType(BOPBiomes.WOODED_WASTELAND, VillagerType.DESERT);
        registerVillagerType(BOPBiomes.WOODLAND, VillagerType.PLAINS);
    }


    public static void registerBiome(ResourceKey<Biome> key, Biome biome)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            biome.setRegistryName(key.location());
            ForgeRegistries.BIOMES.register(biome);
        }
    }

    private static void registerNoise(ResourceKey<NormalNoise.NoiseParameters> key, int firstOctave, double firstAmplitude, double... amplitudes)
    {
        BuiltinRegistries.register(BuiltinRegistries.NOISE, key, new NormalNoise.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
    }

    private static void registerBiomeToDictionary(ResourceKey<Biome> key, BiomeDictionary.Type...type)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            BiomeDictionary.addTypes(key, type);
        }
    }

    private static void registerVillagerType(ResourceKey<Biome> key, VillagerType type)
    {
        if (ModConfig.isBiomeEnabled(key))
        {
            VillagerType.BY_BIOME.put(key, type);
        }
    }
}
