package biomesoplenty.common.worldgen;

import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import biomesoplenty.common.worldgen.BOPClimate;
import biomesoplenty.common.worldgen.BOPMultiNoiseBiomeSource;
import biomesoplenty.common.worldgen.BOPNoiseBasedChunkGenerator;
import biomesoplenty.common.worldgen.BOPNoiseGeneratorSettings;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.worldselection.WorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraftforge.common.world.ForgeWorldPreset;

import java.util.function.Supplier;

public class BOPWorldType extends ForgeWorldPreset
{
    public BOPWorldType()
    {
        super((RegistryAccess registryAccess, long seed, String generatorSettings) -> {
            return new BOPNoiseBasedChunkGenerator(registryAccess.registryOrThrow(Registry.NOISE_REGISTRY), BOPMultiNoiseBiomeSource.Preset.OVERWORLD.biomeSource(registryAccess.registryOrThrow(Registry.BIOME_REGISTRY), false), seed, () -> {
                return BOPNoiseGeneratorSettings.BOP;
            });
        });
    }
}
