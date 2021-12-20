/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomes;
import biomesoplenty.common.worldgen.BOPNoises;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes
{
    public static void setup()
    {
        registerNoise(BOPNoises.UNIQUENESS, -6, 1.0D, 1.5D, 1.0D, 1.5D, 0.0D);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        registerBiome(BOPBiomes.BAMBOO_BLOSSOM_GROVE, BOPOverworldBiomes.bambooBlossomGrove());
        registerBiome(BOPBiomes.BAYOU, BOPOverworldBiomes.bayou());
        registerBiome(BOPBiomes.BOG, BOPOverworldBiomes.bog());
        registerBiome(BOPBiomes.CHERRY_BLOSSOM_GROVE, BOPOverworldBiomes.cherryBlossomGrove());
        registerBiome(BOPBiomes.COLD_DESERT, BOPOverworldBiomes.coldDesert());
        registerBiome(BOPBiomes.CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(false));
        registerBiome(BOPBiomes.DEAD_FOREST, BOPOverworldBiomes.deadForest());
        registerBiome(BOPBiomes.DENSE_WOODLAND, BOPOverworldBiomes.woodland(true));
        registerBiome(BOPBiomes.DRY_BONEYARD, BOPOverworldBiomes.dryBoneyard());
        registerBiome(BOPBiomes.DRYLAND, BOPOverworldBiomes.dryland());
        registerBiome(BOPBiomes.FIELD, BOPOverworldBiomes.field(false));
        registerBiome(BOPBiomes.FIR_CLEARING, BOPOverworldBiomes.firClearing(false));
        registerBiome(BOPBiomes.FORESTED_FIELD, BOPOverworldBiomes.field(true));
        registerBiome(BOPBiomes.GRASSLAND, BOPOverworldBiomes.grassland());
        registerBiome(BOPBiomes.GOLDEN_PRAIRIE, BOPOverworldBiomes.goldenPrairie());
        registerBiome(BOPBiomes.LAVENDER_FIELD, BOPOverworldBiomes.lavenderField(false));
        registerBiome(BOPBiomes.LAVENDER_FOREST, BOPOverworldBiomes.lavenderField(true));
        registerBiome(BOPBiomes.LUSH_DESERT, BOPOverworldBiomes.lushDesert());
        registerBiome(BOPBiomes.MARSH, BOPOverworldBiomes.marsh());
        registerBiome(BOPBiomes.MEDITERRANEAN_FOREST, BOPOverworldBiomes.mediterraneanForest());
        registerBiome(BOPBiomes.MEDITERRANEAN_LAKES, BOPOverworldBiomes.mediterraneanLakes());
        registerBiome(BOPBiomes.MUSKEG, BOPOverworldBiomes.muskeg());
        registerBiome(BOPBiomes.OLD_GROWTH_DEAD_FOREST, BOPOverworldBiomes.oldGrowthDeadForest());
        registerBiome(BOPBiomes.ORCHARD, BOPOverworldBiomes.orchard());
        registerBiome(BOPBiomes.PRAIRIE, BOPOverworldBiomes.prairie());
        registerBiome(BOPBiomes.RAINFOREST, BOPOverworldBiomes.rainforest());
        registerBiome(BOPBiomes.RAINFOREST_FLOODPLAIN, BOPOverworldBiomes.rainforestFloodplain());
        registerBiome(BOPBiomes.REDWOOD_FOREST, BOPOverworldBiomes.redwoodForest());
        registerBiome(BOPBiomes.SCRUBLAND, BOPOverworldBiomes.scrubland(false));
        registerBiome(BOPBiomes.SHRUBLAND, BOPOverworldBiomes.shrubland());
        registerBiome(BOPBiomes.SEASONAL_FOREST, BOPOverworldBiomes.seasonalForest());
        registerBiome(BOPBiomes.SEASONAL_PUMPKIN_PATCH, BOPOverworldBiomes.seasonalPumpkinPatch());
        registerBiome(BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest(true));
        registerBiome(BOPBiomes.SNOWY_FIR_CLEARING, BOPOverworldBiomes.firClearing(true));
        registerBiome(BOPBiomes.SNOWY_MAPLE_FOREST, BOPOverworldBiomes.snowyMapleForest());
        registerBiome(BOPBiomes.TUNDRA, BOPOverworldBiomes.tundra());
        registerBiome(BOPBiomes.TUNDRA_BASIN, BOPOverworldBiomes.tundraBasin());
        registerBiome(BOPBiomes.WASTELAND, BOPOverworldBiomes.wasteland());
        registerBiome(BOPBiomes.WETLAND, BOPOverworldBiomes.wetland());
        registerBiome(BOPBiomes.WOODED_SCRUBLAND, BOPOverworldBiomes.scrubland(true));
        registerBiome(BOPBiomes.WOODLAND, BOPOverworldBiomes.woodland(false));
    }

    // TODO: Villagers, other stuff

    public static void registerBiome(ResourceKey<Biome> key, Biome biome)
    {
        biome.setRegistryName(key.location());
        ForgeRegistries.BIOMES.register(biome);
    }

    private static void registerNoise(ResourceKey<NormalNoise.NoiseParameters> key, int firstOctave, double firstAmplitude, double... amplitudes)
    {
        BuiltinRegistries.register(BuiltinRegistries.NOISE, key, new NormalNoise.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
    }
}
