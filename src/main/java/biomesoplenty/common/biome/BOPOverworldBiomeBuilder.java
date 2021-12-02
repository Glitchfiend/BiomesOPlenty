/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.SharedConstants;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TerrainShaper;

public final class BOPOverworldBiomeBuilder
{
    private static final float VALLEY_SIZE = 0.05F;
    private static final float LOW_START = 0.26666668F;
    public static final float HIGH_START = 0.4F;
    private static final float HIGH_END = 0.93333334F;
    private static final float PEAK_SIZE = 0.1F;
    public static final float PEAK_START = 0.56666666F;
    private static final float PEAK_END = 0.7666667F;
    public static final float NEAR_INLAND_START = -0.11F;
    public static final float MID_INLAND_START = 0.03F;
    public static final float FAR_INLAND_START = 0.3F;
    public static final float EROSION_INDEX_1_START = -0.78F;
    public static final float EROSION_INDEX_2_START = -0.375F;
    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    private final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F)};
    private final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
    private final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
    private final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
    private final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);
    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{{Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN}, {Biomes.FROZEN_OCEAN, Biomes.COLD_OCEAN, Biomes.OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN}};
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{{Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.TAIGA}, {Biomes.PLAINS, Biomes.PLAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA}, {Biomes.FLOWER_FOREST, Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.DARK_FOREST}, {Biomes.SAVANNA, Biomes.SAVANNA, Biomes.FOREST, Biomes.JUNGLE, Biomes.JUNGLE}, {Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT, Biomes.DESERT}};
    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{{Biomes.ICE_SPIKES, null, Biomes.SNOWY_TAIGA, null, null}, {null, null, null, null, Biomes.OLD_GROWTH_PINE_TAIGA}, {Biomes.SUNFLOWER_PLAINS, null, null, Biomes.OLD_GROWTH_BIRCH_FOREST, null}, {null, null, Biomes.PLAINS, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE}, {null, null, null, null, null}};
    private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{{Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA}, {Biomes.MEADOW, Biomes.MEADOW, Biomes.FOREST, Biomes.TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA}, {Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.MEADOW, Biomes.DARK_FOREST}, {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST, Biomes.FOREST, Biomes.JUNGLE}, {Biomes.BADLANDS, Biomes.BADLANDS, Biomes.BADLANDS, Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}};
    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{{Biomes.ICE_SPIKES, null, null, null, null}, {null, null, Biomes.MEADOW, Biomes.MEADOW, Biomes.OLD_GROWTH_PINE_TAIGA}, {null, null, Biomes.FOREST, Biomes.BIRCH_FOREST, null}, {null, null, null, null, null}, {Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null, null, null}};
    private final ResourceKey<Biome>[][] EXTREME_HILLS = new ResourceKey[][]{{Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST}, {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST}, {Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST}, {null, null, null, null, null}, {null, null, null, null, null}};

    public List<Climate.ParameterPoint> spawnTarget() {
        Climate.Parameter climate$parameter = Climate.Parameter.point(0.0F);
        float f = 0.16F;
        return List.of(new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(-1.0F, -0.16F), 0L), new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(0.16F, 1.0F), 0L));
    }

    protected void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise)
        {
            TerrainProvider.overworld(false).addDebugBiomesToVisualizeSplinePoints(mapper);
        }
        else
        {
            this.addOffCoastBiomes(mapper);
            this.addInlandBiomes(mapper);
            this.addUndergroundBiomes(mapper);
        }
    }

    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.MUSHROOM_FIELDS);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addMidSlice(mapper, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(mapper, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(mapper, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(mapper, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(mapper, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(mapper, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(mapper, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(mapper, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(mapper, Climate.Parameter.span(0.26666668F, 0.4F));
        this.addHighSlice(mapper, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(mapper, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(mapper, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(mapper, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter temperature = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> middleBiome = this.pickMiddleBiome(i, j, weirdness);
                ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> plateauBiome = this.pickPlateauBiome(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome = this.pickExtremeHillsBiome(i, j, weirdness);
                ResourceKey<Biome> shatteredBiome = this.maybePickShatteredBiome(i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiome = this.pickPeakBiome(i, j, weirdness);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey2);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, peakBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, resourcekey1);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiome);
            }
        }

    }

    private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187198_, Climate.Parameter p_187199_) {
        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter climate$parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter climate$parameter1 = this.humidities[j];
                ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187199_);
                ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187199_);
                ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187199_);
                ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, p_187199_);
                ResourceKey<Biome> resourcekey4 = this.pickExtremeHillsBiome(i, j, p_187199_);
                ResourceKey<Biome> resourcekey5 = this.maybePickShatteredBiome(i, j, p_187199_, resourcekey);
                ResourceKey<Biome> resourcekey6 = this.pickSlopeBiome(i, j, p_187199_);
                ResourceKey<Biome> resourcekey7 = this.pickPeakBiome(i, j, p_187199_);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187199_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[0], p_187199_, 0.0F, resourcekey6);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], p_187199_, 0.0F, resourcekey7);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[1], p_187199_, 0.0F, resourcekey2);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], p_187199_, 0.0F, resourcekey6);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187199_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], p_187199_, 0.0F, resourcekey3);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], p_187199_, 0.0F, resourcekey1);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], p_187199_, 0.0F, resourcekey3);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], p_187199_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], p_187199_, 0.0F, resourcekey5);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187199_, 0.0F, resourcekey4);
                this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], p_187199_, 0.0F, resourcekey);
            }
        }

    }

    private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187218_, Climate.Parameter weirdness) {
        this.addSurfaceBiome(p_187218_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, Biomes.STONY_SHORE);
        this.addSurfaceBiome(p_187218_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.SWAMP);

        for(int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperatureParam = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidityParam = this.humidities[j];
                ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, weirdness);
                ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, weirdness);
                ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, weirdness);
                ResourceKey<Biome> resourcekey3 = this.pickExtremeHillsBiome(i, j, weirdness);
                ResourceKey<Biome> resourcekey4 = this.pickPlateauBiome(i, j, weirdness);
                ResourceKey<Biome> beachBiome = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourcekey6 = this.maybePickShatteredBiome(i, j, weirdness, resourcekey);
                ResourceKey<Biome> resourcekey7 = this.pickShatteredCoastBiome(i, j, weirdness);
                ResourceKey<Biome> resourcekey8 = this.pickSlopeBiome(i, j, weirdness);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, resourcekey8);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, resourcekey2);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? resourcekey8 : resourcekey4);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey1);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, resourcekey4);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, resourcekey1);
                if (weirdness.max() < 0L) {
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, beachBiome);
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
                } else {
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, resourcekey);
                }

                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey7);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, resourcekey6);
                this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, resourcekey3);
                if (weirdness.max() < 0L) {
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);
                } else {
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, resourcekey);
                }

                if (i == 0) {
                    this.addSurfaceBiome(p_187218_, temperatureParam, humidityParam, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, resourcekey);
                }
            }
        }

    }

    private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187229_, Climate.Parameter p_187230_) {
        this.addSurfaceBiome(p_187229_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), p_187230_, 0.0F, Biomes.STONY_SHORE);
        this.addSurfaceBiome(p_187229_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187230_, 0.0F, Biomes.SWAMP);

        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter climate$parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter climate$parameter1 = this.humidities[j];
                ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187230_);
                ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187230_);
                ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187230_);
                ResourceKey<Biome> resourcekey3 = this.pickBeachBiome(i, j);
                ResourceKey<Biome> resourcekey4 = this.maybePickShatteredBiome(i, j, p_187230_, resourcekey);
                ResourceKey<Biome> resourcekey5 = this.pickShatteredCoastBiome(i, j, p_187230_);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187230_, 0.0F, resourcekey1);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187230_, 0.0F, resourcekey2);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187230_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187230_, 0.0F, resourcekey1);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), p_187230_, 0.0F, resourcekey3);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], p_187230_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], p_187230_, 0.0F, resourcekey5);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], p_187230_, 0.0F, resourcekey4);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187230_, 0.0F, resourcekey);
                this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], p_187230_, 0.0F, resourcekey3);
                if (i == 0) {
                    this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187230_, 0.0F, resourcekey);
                }
            }
        }

    }

    private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187238_, Climate.Parameter p_187239_) {
        this.addSurfaceBiome(p_187238_, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, p_187239_.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, p_187239_.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(p_187238_, this.FROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(p_187238_, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), p_187239_, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), p_187239_, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(p_187238_, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], p_187239_, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], p_187239_, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187239_, 0.0F, Biomes.SWAMP);
        this.addSurfaceBiome(p_187238_, this.FROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187239_, 0.0F, Biomes.FROZEN_RIVER);

        for(int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter climate$parameter = this.temperatures[i];

            for(int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter climate$parameter1 = this.humidities[j];
                ResourceKey<Biome> resourcekey = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187239_);
                this.addSurfaceBiome(p_187238_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, resourcekey);
            }
        }

    }

    private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addUndergroundBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.DRIPSTONE_CAVES);
        this.addUndergroundBiome(mapper, this.FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.LUSH_CAVES);
    }

    private ResourceKey<Biome> pickMiddleBiome(int p_187164_, int p_187165_, Climate.Parameter p_187166_) {
        if (p_187166_.max() < 0L) {
            return this.MIDDLE_BIOMES[p_187164_][p_187165_];
        } else {
            ResourceKey<Biome> resourcekey = this.MIDDLE_BIOMES_VARIANT[p_187164_][p_187165_];
            return resourcekey == null ? this.MIDDLE_BIOMES[p_187164_][p_187165_] : resourcekey;
        }
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int p_187192_, int p_187193_, Climate.Parameter p_187194_) {
        return p_187192_ == 4 ? this.pickBadlandsBiome(p_187193_, p_187194_) : this.pickMiddleBiome(p_187192_, p_187193_, p_187194_);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int p_187212_, int p_187213_, Climate.Parameter p_187214_) {
        return p_187212_ == 0 ? this.pickSlopeBiome(p_187212_, p_187213_, p_187214_) : this.pickMiddleBiomeOrBadlandsIfHot(p_187212_, p_187213_, p_187214_);
    }

    private ResourceKey<Biome> maybePickShatteredBiome(int p_187168_, int p_187169_, Climate.Parameter p_187170_, ResourceKey<Biome> p_187171_) {
        return p_187168_ > 1 && p_187169_ < 4 && p_187170_.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : p_187171_;
    }

    private ResourceKey<Biome> pickShatteredCoastBiome(int p_187223_, int p_187224_, Climate.Parameter p_187225_) {
        ResourceKey<Biome> resourcekey = p_187225_.max() >= 0L ? this.pickMiddleBiome(p_187223_, p_187224_, p_187225_) : this.pickBeachBiome(p_187223_, p_187224_);
        return this.maybePickShatteredBiome(p_187223_, p_187224_, p_187225_, resourcekey);
    }

    private ResourceKey<Biome> pickBeachBiome(int p_187161_, int p_187162_) {
        if (p_187161_ == 0) {
            return Biomes.SNOWY_BEACH;
        } else {
            return p_187161_ == 4 ? Biomes.DESERT : Biomes.BEACH;
        }
    }

    private ResourceKey<Biome> pickBadlandsBiome(int p_187173_, Climate.Parameter p_187174_) {
        if (p_187173_ < 2) {
            return p_187174_.max() < 0L ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS;
        } else {
            return p_187173_ < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
        }
    }

    private ResourceKey<Biome> pickPlateauBiome(int p_187234_, int p_187235_, Climate.Parameter p_187236_) {
        if (p_187236_.max() < 0L) {
            return this.PLATEAU_BIOMES[p_187234_][p_187235_];
        } else {
            ResourceKey<Biome> resourcekey = this.PLATEAU_BIOMES_VARIANT[p_187234_][p_187235_];
            return resourcekey == null ? this.PLATEAU_BIOMES[p_187234_][p_187235_] : resourcekey;
        }
    }

    private ResourceKey<Biome> pickPeakBiome(int p_187241_, int p_187242_, Climate.Parameter p_187243_) {
        if (p_187241_ <= 2) {
            return p_187243_.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
        } else {
            return p_187241_ == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(p_187242_, p_187243_);
        }
    }

    private ResourceKey<Biome> pickSlopeBiome(int p_187245_, int p_187246_, Climate.Parameter p_187247_) {
        if (p_187245_ >= 3) {
            return this.pickPlateauBiome(p_187245_, p_187246_, p_187247_);
        } else {
            return p_187246_ <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
        }
    }

    private ResourceKey<Biome> pickExtremeHillsBiome(int p_187249_, int p_187250_, Climate.Parameter p_187251_) {
        ResourceKey<Biome> resourcekey = this.EXTREME_HILLS[p_187249_][p_187250_];
        return resourcekey == null ? this.pickMiddleBiome(p_187249_, p_187250_, p_187251_) : resourcekey;
    }

    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
    }

    private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), biome));
    }

    public static String getDebugStringForPeaksAndValleys(double p_187156_) {
        if (p_187156_ < (double)TerrainShaper.peaksAndValleys(0.05F)) {
            return "Valley";
        } else if (p_187156_ < (double) TerrainShaper.peaksAndValleys(0.26666668F)) {
            return "Low";
        } else if (p_187156_ < (double)TerrainShaper.peaksAndValleys(0.4F)) {
            return "Mid";
        } else {
            return p_187156_ < (double)TerrainShaper.peaksAndValleys(0.56666666F) ? "High" : "Peak";
        }
    }

    public String getDebugStringForContinentalness(double p_187190_) {
        double d0 = (double)Climate.quantizeCoord((float)p_187190_);
        if (d0 < (double)this.mushroomFieldsContinentalness.max()) {
            return "Mushroom fields";
        } else if (d0 < (double)this.deepOceanContinentalness.max()) {
            return "Deep ocean";
        } else if (d0 < (double)this.oceanContinentalness.max()) {
            return "Ocean";
        } else if (d0 < (double)this.coastContinentalness.max()) {
            return "Coast";
        } else if (d0 < (double)this.nearInlandContinentalness.max()) {
            return "Near inland";
        } else {
            return d0 < (double)this.midInlandContinentalness.max() ? "Mid inland" : "Far inland";
        }
    }

    public String getDebugStringForErosion(double p_187210_) {
        return getDebugStringForNoiseValue(p_187210_, this.erosions);
    }

    public String getDebugStringForTemperature(double p_187221_) {
        return getDebugStringForNoiseValue(p_187221_, this.temperatures);
    }

    public String getDebugStringForHumidity(double p_187232_) {
        return getDebugStringForNoiseValue(p_187232_, this.humidities);
    }

    private static String getDebugStringForNoiseValue(double p_187158_, Climate.Parameter[] p_187159_) {
        double d0 = (double)Climate.quantizeCoord((float)p_187158_);

        for(int i = 0; i < p_187159_.length; ++i) {
            if (d0 < (double)p_187159_[i].max()) {
                return "" + i;
            }
        }

        return "?";
    }
}