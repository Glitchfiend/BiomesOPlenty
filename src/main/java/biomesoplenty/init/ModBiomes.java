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
import net.minecraft.world.level.levelgen.Noises;
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
        registerNoise(BOPNoises.UNIQUENESS, -8, 1.0D, 2.0D, 1.0D, 0.0D, 0.0D, 0.0D);
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
        registerBiome(BOPBiomes.BAMBOO_BLOSSOM_GROVE, BOPOverworldBiomes.bambooBlossomGrove());
        registerBiome(BOPBiomes.CHERRY_BLOSSOM_GROVE, BOPOverworldBiomes.cherryBlossomGrove());
        registerBiome(BOPBiomes.CONIFEROUS_FOREST, BOPOverworldBiomes.coniferousForest());
        registerBiome(BOPBiomes.LAVENDER_FIELD, BOPOverworldBiomes.lavenderField());
        registerBiome(BOPBiomes.WOODLAND, BOPOverworldBiomes.woodland());
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
