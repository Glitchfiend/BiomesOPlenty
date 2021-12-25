/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPNetherBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomes;
import biomesoplenty.common.worldgen.BOPNoises;
import biomesoplenty.common.worldgen.BOPWorldType;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
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
    private static BOPWorldType bopWorldType = new BOPWorldType();

    public static void setup()
    {
        registerNoise(BOPNoises.UNIQUENESS, -6, 1.0D, 1.5D, 1.0D, 1.5D, 0.0D);
        registerNoise(BOPNoises.RARENESS, -9, 0.6D, 1.5D, 0.6D, 0.0D, 0.0D);

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
        registerBiome(BOPBiomes.DENSE_WOODLAND, BOPOverworldBiomes.woodland(true));
        registerBiome(BOPBiomes.DRY_BONEYARD, BOPOverworldBiomes.dryBoneyard());
        registerBiome(BOPBiomes.DRYLAND, BOPOverworldBiomes.dryland());
        registerBiome(BOPBiomes.DUNE_BEACH, BOPOverworldBiomes.duneBeach());
        registerBiome(BOPBiomes.FIELD, BOPOverworldBiomes.field(false));
        registerBiome(BOPBiomes.FIR_CLEARING, BOPOverworldBiomes.firClearing(false));
        registerBiome(BOPBiomes.FLOODPLAIN, BOPOverworldBiomes.floodplain());
        registerBiome(BOPBiomes.FUNGAL_JUNGLE, BOPOverworldBiomes.fungalJungle());
        registerBiome(BOPBiomes.FORESTED_FIELD, BOPOverworldBiomes.field(true));
        registerBiome(BOPBiomes.GLOWING_GROTTO, BOPOverworldBiomes.glowingGrotto());
        registerBiome(BOPBiomes.GRASSLAND, BOPOverworldBiomes.grassland());
        registerBiome(BOPBiomes.HIGHLAND, BOPOverworldBiomes.highland(false));
        registerBiome(BOPBiomes.HIGHLAND_MOOR, BOPOverworldBiomes.highland(true));
        registerBiome(BOPBiomes.JADE_CLIFFS, BOPOverworldBiomes.jadeCliffs());
        registerBiome(BOPBiomes.LAVENDER_FIELD, BOPOverworldBiomes.lavenderField(false));
        registerBiome(BOPBiomes.LAVENDER_FOREST, BOPOverworldBiomes.lavenderField(true));
        registerBiome(BOPBiomes.LUSH_DESERT, BOPOverworldBiomes.lushDesert());
        registerBiome(BOPBiomes.LUSH_SAVANNA, BOPOverworldBiomes.lushSavanna());
        registerBiome(BOPBiomes.MARSH, BOPOverworldBiomes.marsh());
        registerBiome(BOPBiomes.MEDITERRANEAN_FOREST, BOPOverworldBiomes.mediterraneanForest());
        registerBiome(BOPBiomes.MEDITERRANEAN_LAKES, BOPOverworldBiomes.mediterraneanLakes());
        registerBiome(BOPBiomes.MUSKEG, BOPOverworldBiomes.muskeg());
        registerBiome(BOPBiomes.MYSTIC_GROVE, BOPOverworldBiomes.mysticGrove());
        registerBiome(BOPBiomes.OLD_GROWTH_DEAD_FOREST, BOPOverworldBiomes.oldGrowthDeadForest());
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
        registerBiome(BOPBiomes.SHRUBLAND, BOPOverworldBiomes.shrubland());
        registerBiome(BOPBiomes.SEASONAL_FOREST, BOPOverworldBiomes.seasonalForest());
        registerBiome(BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(true));
        registerBiome(BOPBiomes.SNOWY_FIR_CLEARING, BOPOverworldBiomes.firClearing(true));
        registerBiome(BOPBiomes.SNOWY_MAPLE_FOREST, BOPOverworldBiomes.snowyMapleForest());
        registerBiome(BOPBiomes.SPIDER_NEST, BOPOverworldBiomes.spiderNest());
        registerBiome(BOPBiomes.TROPICS, BOPOverworldBiomes.tropics());
        registerBiome(BOPBiomes.TUNDRA, BOPOverworldBiomes.tundra());
        registerBiome(BOPBiomes.VOLCANO, BOPOverworldBiomes.volcano());
        registerBiome(BOPBiomes.VOLCANIC_PLAINS, BOPOverworldBiomes.volcanicPlains());
        registerBiome(BOPBiomes.WASTELAND, BOPOverworldBiomes.wasteland());
        registerBiome(BOPBiomes.WETLAND, BOPOverworldBiomes.wetland());
        registerBiome(BOPBiomes.WITHERED_ABYSS, BOPNetherBiomes.witheredAbyss());
        registerBiome(BOPBiomes.WOODED_SCRUBLAND, BOPOverworldBiomes.scrubland(true));
        registerBiome(BOPBiomes.WOODLAND, BOPOverworldBiomes.woodland(false));
    }

    // TODO: Villagers, other stuff

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
}
