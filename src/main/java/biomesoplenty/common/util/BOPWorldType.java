package biomesoplenty.common.util;

import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.worldselection.WorldPreset;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.function.Supplier;

public class BOPWorldType extends WorldPreset {
    public BOPWorldType() {
        super("biomesoplenty");
        PRESETS.add(this);
    }

    @Override
    protected ChunkGenerator generator(RegistryAccess p_194083_, long p_194084_) {
        return new NoiseBasedChunkGenerator(
                p_194083_.registryOrThrow(Registry.NOISE_REGISTRY),
                BOP.biomeSource(p_194083_.registryOrThrow(Registry.BIOME_REGISTRY),
                        false), p_194084_, () -> {
            return p_194083_.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY).getOrThrow(NoiseGeneratorSettings.OVERWORLD);
        });
    }

    public static final MultiNoiseBiomeSource.Preset BOP = new MultiNoiseBiomeSource.Preset(new ResourceLocation("bop"), (p_187108_) -> {
        ImmutableList.Builder<Pair<Climate.ParameterPoint, Supplier<Biome>>> builder = ImmutableList.builder();
        (new BOPOverworldBiomeBuilder()).addBiomes((p_187098_) -> {
            builder.add(p_187098_.mapSecond((p_187103_) -> {
                return () -> {
                    return p_187108_.getOrThrow(p_187103_);
                };
            }));
        });
        return new Climate.ParameterList<>(builder.build());
    });
}
