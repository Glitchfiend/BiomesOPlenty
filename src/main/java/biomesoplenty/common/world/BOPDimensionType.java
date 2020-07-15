package biomesoplenty.common.world;

import biomesoplenty.init.ModConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
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
    protected BOPDimensionType(OptionalLong p_i241242_1_, boolean p_i241242_2_, boolean p_i241242_3_, boolean p_i241242_4_, boolean p_i241242_5_, boolean p_i241242_6_, boolean p_i241242_7_, boolean p_i241242_8_, boolean p_i241242_9_, boolean p_i241242_10_, int p_i241242_11_, ResourceLocation p_i241242_12_, float p_i241242_13_)
    {
        super(p_i241242_1_, p_i241242_2_, p_i241242_3_, p_i241242_4_, p_i241242_5_, p_i241242_6_, p_i241242_7_, p_i241242_8_, p_i241242_9_, p_i241242_10_, p_i241242_11_, p_i241242_12_, p_i241242_13_);
    }

    private static ChunkGenerator bopEndGenerator(long seed)
    {
        return new NoiseChunkGenerator(new EndBiomeProvider(seed), seed, DimensionSettings.Preset.END.settings());
    }

    private static ChunkGenerator bopNetherGenerator(long seed)
    {
        BiomeProvider biomeProvider;

        if (ModConfig.GenerationConfig.useBopNether.get())
        {
            biomeProvider = new BOPNetherBiomeProvider(seed);
        }
        else
        {
            ImmutableList<Biome> netherBiomes = ImmutableList.of(Biomes.NETHER_WASTES, Biomes.SOUL_SAND_VALLEY, Biomes.CRIMSON_FOREST, Biomes.WARPED_FOREST, Biomes.BASALT_DELTAS);
            biomeProvider = new NetherBiomeProvider(seed, netherBiomes.stream().flatMap((biome) -> biome.optimalParameters().map((parameter) -> Pair.of(parameter, biome))).collect(ImmutableList.toImmutableList()), Optional.of(NetherBiomeProvider.Preset.NETHER));
        }

        return new NoiseChunkGenerator(biomeProvider, seed, DimensionSettings.Preset.NETHER.settings());
    }

    public static SimpleRegistry<Dimension> bopDimensions(long seed)
    {
        SimpleRegistry<Dimension> registry = new SimpleRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental());
        registry.register(Dimension.NETHER, new Dimension(() -> DEFAULT_NETHER, bopNetherGenerator(seed)));
        registry.register(Dimension.END, new Dimension(() -> DEFAULT_END, bopEndGenerator(seed)));
        registry.setPersistent(Dimension.NETHER);
        registry.setPersistent(Dimension.END);
        return registry;
    }
}
