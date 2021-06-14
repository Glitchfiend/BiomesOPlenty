package biomesoplenty.common.world;

import biomesoplenty.init.ModConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.FuzzedBiomeMagnifier;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.EndBiomeProvider;
import net.minecraft.world.biome.provider.NetherBiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.NoiseChunkGenerator;

import java.util.Optional;
import java.util.OptionalLong;

/***
 * This class mainly exists as a means of putting static methods in the same place Vanilla has them and access to protected methods.
 * We don't need to create our own BOPDimensionType instances.
 */
public class BOPDimensionType extends DimensionType
{
    protected BOPDimensionType(OptionalLong p_i241972_1_, boolean p_i241972_2_, boolean p_i241972_3_, boolean p_i241972_4_, boolean p_i241972_5_, double p_i241972_6_, boolean p_i241972_8_, boolean p_i241972_9_, boolean p_i241972_10_, boolean p_i241972_11_, int p_i241972_12_, ResourceLocation p_i241972_13_, ResourceLocation p_i241972_14_, float p_i241972_15_)
    {
        super(p_i241972_1_, p_i241972_2_, p_i241972_3_, p_i241972_4_, p_i241972_5_, p_i241972_6_, p_i241972_8_, p_i241972_9_, p_i241972_10_, p_i241972_11_, p_i241972_12_, p_i241972_13_, p_i241972_14_, p_i241972_15_);
    }

    private static ChunkGenerator bopNetherGenerator(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed)
    {
        return new NoiseChunkGenerator(new BOPNetherBiomeProvider(seed, biomeRegistry), seed, () -> dimensionSettingsRegistry.getOrThrow(DimensionSettings.NETHER));
    }

    public static SimpleRegistry<Dimension> bopDimensions(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed)
    {
        SimpleRegistry<Dimension> registry = new SimpleRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
        registry.register(Dimension.NETHER, new Dimension(() -> DEFAULT_NETHER, ModConfig.GenerationConfig.useBopNether.get() ? bopNetherGenerator(biomeRegistry, dimensionSettingsRegistry, seed) : DimensionType.defaultNetherGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        registry.register(Dimension.END, new Dimension(() -> DEFAULT_END, DimensionType.defaultEndGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        return registry;
    }
}
