/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import biomesoplenty.common.util.biome.BiomeUtil;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.NoiseSampler;
import net.minecraft.world.level.levelgen.TerrainInfo;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class BOPMultiNoiseBiomeSource extends BiomeSource
{
    public static final MapCodec<BOPMultiNoiseBiomeSource> DIRECT_CODEC = RecordCodecBuilder.mapCodec((p_187070_) -> {
        return p_187070_.group(ExtraCodecs.<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>>nonEmptyList(RecordCodecBuilder.<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>>create((p_187078_) -> {
            return p_187078_.group(BOPClimate.ParameterPoint.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(p_187078_, Pair::of);
        }).listOf()).xmap(BOPClimate.ParameterList::new, (Function<BOPClimate.ParameterList<Supplier<Biome>>, List<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>>>) BOPClimate.ParameterList::values).fieldOf("biomes").forGetter((p_187080_) -> {
            return p_187080_.parameters;
        })).apply(p_187070_, BOPMultiNoiseBiomeSource::new);
    });
    public static final Codec<BOPMultiNoiseBiomeSource> CODEC = Codec.mapEither(BOPMultiNoiseBiomeSource.PresetInstance.CODEC, DIRECT_CODEC).xmap((p_187068_) -> {
        return p_187068_.map(BOPMultiNoiseBiomeSource.PresetInstance::biomeSource, Function.identity());
    }, (p_187066_) -> {
        return p_187066_.preset().map(Either::<BOPMultiNoiseBiomeSource.PresetInstance, BOPMultiNoiseBiomeSource>left).orElseGet(() -> {
            return Either.right(p_187066_);
        });
    }).codec();
    private final BOPClimate.ParameterList<Supplier<Biome>> parameters;
    private final Optional<PresetInstance> preset;

    private BOPMultiNoiseBiomeSource(BOPClimate.ParameterList<Supplier<Biome>> p_187057_)
    {
        this(p_187057_, Optional.empty());
    }

    BOPMultiNoiseBiomeSource(BOPClimate.ParameterList<Supplier<Biome>> p_187059_, Optional<PresetInstance> p_187060_)
    {
        super(p_187059_.values().stream().map(Pair::getSecond));
        this.preset = p_187060_;
        this.parameters = p_187059_;
    }

    protected Codec<? extends BiomeSource> codec()
    {
        return CODEC;
    }

    public BiomeSource withSeed(long p_48466_)
    {
        return this;
    }

    @Override
    public Biome getNoiseBiome(int p_186735_, int p_186736_, int p_186737_, Climate.Sampler sampler)
    {
        if (!(sampler instanceof BOPClimate.Sampler))
            throw new RuntimeException("Sampler must be a BOPClimate sampler");

        return this.getNoiseBiome(((BOPClimate.Sampler)sampler).sampleBOP(p_186735_, p_186736_, p_186737_));
    }

    private Optional<BOPMultiNoiseBiomeSource.PresetInstance> preset()
    {
        return this.preset;
    }

    public boolean stable(BOPMultiNoiseBiomeSource.Preset p_187064_)
    {
        return this.preset.isPresent() && Objects.equals(this.preset.get().preset(), p_187064_);
    }

    @VisibleForDebug
    public Biome getNoiseBiome(BOPClimate.TargetPoint p_187062_)
    {
        return this.parameters.findValue(p_187062_, () -> {
            return net.minecraft.data.worldgen.biome.Biomes.THE_VOID;
        }).get();
    }

    public void addMultinoiseDebugInfo(List<String> p_187072_, BlockPos p_187073_, BOPClimate.Sampler p_187074_)
    {
        int i = QuartPos.fromBlock(p_187073_.getX());
        int j = QuartPos.fromBlock(p_187073_.getY());
        int k = QuartPos.fromBlock(p_187073_.getZ());
        BOPClimate.TargetPoint climate$targetpoint = p_187074_.sampleBOP(i, j, k);
        float f = BOPClimate.unquantizeCoord(climate$targetpoint.continentalness());
        float f1 = BOPClimate.unquantizeCoord(climate$targetpoint.erosion());
        float f2 = BOPClimate.unquantizeCoord(climate$targetpoint.temperature());
        float f3 = BOPClimate.unquantizeCoord(climate$targetpoint.humidity());
        float f4 = BOPClimate.unquantizeCoord(climate$targetpoint.weirdness());
        double d0 = (double) TerrainShaper.peaksAndValleys(f4);
        DecimalFormat decimalformat = new DecimalFormat("0.000");
        p_187072_.add("Multinoise C: " + decimalformat.format((double) f) + " E: " + decimalformat.format((double) f1) + " T: " + decimalformat.format((double) f2) + " H: " + decimalformat.format((double) f3) + " W: " + decimalformat.format((double) f4));
        BOPOverworldBiomeBuilder overworldbiomebuilder = new BOPOverworldBiomeBuilder();
        p_187072_.add("Biome builder PV: " + BOPOverworldBiomeBuilder.getDebugStringForPeaksAndValleys(d0) + " C: " + overworldbiomebuilder.getDebugStringForContinentalness((double) f) + " E: " + overworldbiomebuilder.getDebugStringForErosion((double) f1) + " T: " + overworldbiomebuilder.getDebugStringForTemperature((double) f2) + " H: " + overworldbiomebuilder.getDebugStringForHumidity((double) f3));
        if (p_187074_ instanceof NoiseSampler)
        {
            NoiseSampler noisesampler = (NoiseSampler) p_187074_;
            TerrainInfo terraininfo = noisesampler.terrainInfo(p_187073_.getX(), p_187073_.getZ(), f, f4, f1, Blender.empty());
            p_187072_.add("Terrain PV: " + decimalformat.format(d0) + " O: " + decimalformat.format(terraininfo.offset()) + " F: " + decimalformat.format(terraininfo.factor()) + " JA: " + decimalformat.format(terraininfo.jaggedness()));
        }
    }

    public static class Preset
    {
        static final Map<ResourceLocation, BOPMultiNoiseBiomeSource.Preset> BY_NAME = Maps.newHashMap();

        public static final BOPMultiNoiseBiomeSource.Preset NETHER = new BOPMultiNoiseBiomeSource.Preset(new ResourceLocation("nether"), (biomeRegistry) -> {
            return new BOPClimate.ParameterList<>(ImmutableList.of(Pair.of(BOPClimate.parameters(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.NETHER_WASTES)),
                Pair.of(BOPClimate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.SOUL_SAND_VALLEY)),
                Pair.of(BOPClimate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(Biomes.CRIMSON_FOREST)),
                Pair.of(BOPClimate.parameters(0.0F, 0.5F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.375F), () -> biomeRegistry.getOrThrow(Biomes.WARPED_FOREST)),
                Pair.of(BOPClimate.parameters(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.175F), () -> biomeRegistry.getOrThrow(Biomes.BASALT_DELTAS)),
                Pair.of(BOPClimate.parameters(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F), () ->  biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.VISCERAL_HEAP, Biomes.SOUL_SAND_VALLEY))),
                Pair.of(BOPClimate.parameters(0.4F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.CRYSTALLINE_CHASM, Biomes.CRIMSON_FOREST))),
                Pair.of(BOPClimate.parameters(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F), () -> biomeRegistry.getOrThrow(BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.WITHERED_ABYSS, Biomes.NETHER_WASTES)))));
        });

        public static final BOPMultiNoiseBiomeSource.Preset OVERWORLD = new BOPMultiNoiseBiomeSource.Preset(new ResourceLocation(BiomesOPlenty.MOD_ID, "overworld"), (biomeRegistry) -> {
            ImmutableList.Builder<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> builder = ImmutableList.builder();
            (new BOPOverworldBiomeBuilder()).addBiomes(biomeRegistry, (p_187098_) -> {
                builder.add(p_187098_.mapSecond((p_187103_) -> {
                    return () -> {
                        return biomeRegistry.getOrThrow(p_187103_);
                    };
                }));
            });
            return new BOPClimate.ParameterList<>(builder.build());
        });
        final ResourceLocation name;
        private final Function<Registry<Biome>, BOPClimate.ParameterList<Supplier<Biome>>> parameterSource;

        public Preset(ResourceLocation p_187090_, Function<Registry<Biome>, BOPClimate.ParameterList<Supplier<Biome>>> p_187091_)
        {
            this.name = p_187090_;
            this.parameterSource = p_187091_;
            BY_NAME.put(p_187090_, this);
        }

        BOPMultiNoiseBiomeSource biomeSource(BOPMultiNoiseBiomeSource.PresetInstance p_187093_, boolean p_187094_)
        {
            BOPClimate.ParameterList<Supplier<Biome>> parameterlist = this.parameterSource.apply(p_187093_.biomes());
            return new BOPMultiNoiseBiomeSource(parameterlist, p_187094_ ? Optional.of(p_187093_) : Optional.empty());
        }

        public BOPMultiNoiseBiomeSource biomeSource(Registry<Biome> p_187105_, boolean p_187106_)
        {
            return this.biomeSource(new BOPMultiNoiseBiomeSource.PresetInstance(this, p_187105_), p_187106_);
        }

        public BOPMultiNoiseBiomeSource biomeSource(Registry<Biome> p_187100_)
        {
            return this.biomeSource(p_187100_, true);
        }
    }

    record PresetInstance(BOPMultiNoiseBiomeSource.Preset preset, Registry<Biome> biomes)
    {
        public static final MapCodec<BOPMultiNoiseBiomeSource.PresetInstance> CODEC = RecordCodecBuilder.mapCodec((p_48558_) -> {
            return p_48558_.group(ResourceLocation.CODEC.flatXmap((p_151869_) -> {
                return Optional.ofNullable(BOPMultiNoiseBiomeSource.Preset.BY_NAME.get(p_151869_)).map(DataResult::success).orElseGet(() -> {
                    return DataResult.error("Unknown preset: " + p_151869_);
                });
            }, (p_151867_) -> {
                return DataResult.success(p_151867_.name);
            }).fieldOf("preset").stable().forGetter(BOPMultiNoiseBiomeSource.PresetInstance::preset), RegistryLookupCodec.create(Registry.BIOME_REGISTRY).forGetter(BOPMultiNoiseBiomeSource.PresetInstance::biomes)).apply(p_48558_, p_48558_.stable(BOPMultiNoiseBiomeSource.PresetInstance::new));
        });

        public BOPMultiNoiseBiomeSource biomeSource()
        {
            return this.preset.biomeSource(this, true);
        }
    }

    static {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(BiomesOPlenty.MOD_ID, "multi_noise"), BOPMultiNoiseBiomeSource.CODEC);
    }
}