/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.worldgen.BOPClimate;
import com.mojang.datafixers.util.Pair;

import java.util.List;
import java.util.function.Consumer;

import net.minecraft.SharedConstants;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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

    private final BOPClimate.Parameter FULL_RANGE = BOPClimate.Parameter.span(-1.0F, 1.0F);

    /* Terminology:
        Continentalness: Low to generate near coasts, far to generate away from coasts
        Erosion: Low is hilly terrain, high is flat terrain
     */

    private final BOPClimate.Parameter[] temperatures = new BOPClimate.Parameter[]{
            BOPClimate.Parameter.span(-1.0F, -0.45F),
            BOPClimate.Parameter.span(-0.45F, -0.15F),
            BOPClimate.Parameter.span(-0.15F, 0.2F),
            BOPClimate.Parameter.span(0.2F, 0.55F),
            BOPClimate.Parameter.span(0.55F, 1.0F)
    };

    private final BOPClimate.Parameter[] humidities = new BOPClimate.Parameter[]{
            BOPClimate.Parameter.span(-1.0F, -0.35F),
            BOPClimate.Parameter.span(-0.35F, -0.1F),
            BOPClimate.Parameter.span(-0.1F, 0.1F),
            BOPClimate.Parameter.span(0.1F, 0.3F),
            BOPClimate.Parameter.span(0.3F, 1.0F)
    };

    private final BOPClimate.Parameter[] erosions = new BOPClimate.Parameter[]{
            BOPClimate.Parameter.span(-1.0F, -0.78F),
            BOPClimate.Parameter.span(-0.78F, -0.375F),
            BOPClimate.Parameter.span(-0.375F, -0.2225F),
            BOPClimate.Parameter.span(-0.2225F, 0.05F),
            BOPClimate.Parameter.span(0.05F, 0.45F),
            BOPClimate.Parameter.span(0.45F, 0.55F),
            BOPClimate.Parameter.span(0.55F, 1.0F)
    };

    private static final BOPClimate.Parameter VANILLA_UNIQUENESS_RANGE = BOPClimate.Parameter.span(-1.0F, -0.1F);
    private static final BOPClimate.Parameter BOP_UNIQUENESS_RANGE = BOPClimate.Parameter.span(-0.1F, 1.0F);

    private static final BOPClimate.Parameter VANILLA_UNIQUENESS_RANGE_MODERATE_BOP_BIAS = BOPClimate.Parameter.span(-1.0F, -0.35F);
    private static final BOPClimate.Parameter BOP_UNIQUENESS_RANGE_MODERATE_BOP_BIAS = BOPClimate.Parameter.span(-0.35F, 1.0F);

    private static final BOPClimate.Parameter COMMON_RARENESS_RANGE = BOPClimate.Parameter.span(-1.0F, 0.35F);
    private static final BOPClimate.Parameter RARE_RARENESS_RANGE = BOPClimate.Parameter.span(0.35F, 1.0F);

    private final BOPClimate.Parameter FROZEN_RANGE = this.temperatures[0];
    private final BOPClimate.Parameter UNFROZEN_RANGE = BOPClimate.Parameter.span(this.temperatures[1], this.temperatures[4]);
    private final BOPClimate.Parameter mushroomFieldsContinentalness = BOPClimate.Parameter.span(-1.2F, -1.05F);
    private final BOPClimate.Parameter deepOceanContinentalness = BOPClimate.Parameter.span(-1.05F, -0.455F);
    private final BOPClimate.Parameter oceanContinentalness = BOPClimate.Parameter.span(-0.455F, -0.19F);
    private final BOPClimate.Parameter coastContinentalness = BOPClimate.Parameter.span(-0.19F, -0.11F);
    private final BOPClimate.Parameter inlandContinentalness = BOPClimate.Parameter.span(-0.11F, 0.55F);
    private final BOPClimate.Parameter nearInlandContinentalness = BOPClimate.Parameter.span(-0.11F, 0.03F);
    private final BOPClimate.Parameter midInlandContinentalness = BOPClimate.Parameter.span(0.03F, 0.3F);
    private final BOPClimate.Parameter farInlandContinentalness = BOPClimate.Parameter.span(0.3F, 1.0F);

    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN,      Biomes.COLD_OCEAN,      Biomes.OCEAN,      Biomes.LUKEWARM_OCEAN,      Biomes.WARM_OCEAN}
    };

    private final ResourceKey<Biome>[][] ISLAND_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.RAINBOW_HILLS,         BOPBiomes.RAINBOW_HILLS,         BOPBiomes.RAINBOW_HILLS, BOPBiomes.RAINBOW_HILLS, BOPBiomes.RAINBOW_HILLS},
            {null,                            null,                            null,                    null,                    null},
            {BOPBiomes.ORIGIN_VALLEY,         BOPBiomes.ORIGIN_VALLEY,         BOPBiomes.ORIGIN_VALLEY, BOPBiomes.ORIGIN_VALLEY, BOPBiomes.ORIGIN_VALLEY},
            {BOPBiomes.TROPICS,               BOPBiomes.TROPICS,               BOPBiomes.TROPICS,       BOPBiomes.TROPICS,       BOPBiomes.TROPICS},
            {BOPBiomes.TROPICS,               BOPBiomes.TROPICS,               BOPBiomes.TROPICS,       BOPBiomes.TROPICS,       BOPBiomes.TROPICS}
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,  Biomes.SNOWY_PLAINS, Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,  Biomes.TAIGA},
            {Biomes.PLAINS,        Biomes.PLAINS,       Biomes.FOREST,       Biomes.TAIGA,        Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.FLOWER_FOREST, Biomes.PLAINS,       Biomes.FOREST,       Biomes.BIRCH_FOREST, Biomes.DARK_FOREST},
            {Biomes.SAVANNA,       Biomes.SAVANNA,      Biomes.FOREST,       Biomes.JUNGLE,       Biomes.JUNGLE},
            {Biomes.DESERT,        Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT,       Biomes.DESERT}
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,       null, Biomes.SNOWY_TAIGA, null,                           null},
            {null,                    null, null,               null,                           Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.SUNFLOWER_PLAINS, null, null,               Biomes.OLD_GROWTH_BIRCH_FOREST, null},
            {null,                    null, Biomes.PLAINS,      Biomes.SPARSE_JUNGLE,           Biomes.BAMBOO_JUNGLE},
            {null,                    null, null,               null,                           null}
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,     BOPBiomes.TUNDRA,               BOPBiomes.SNOWY_CONIFEROUS_FOREST, null,                  BOPBiomes.DEAD_FOREST},
            {BOPBiomes.SEASONAL_FOREST, BOPBiomes.CONIFEROUS_FOREST,    BOPBiomes.CONIFEROUS_FOREST,       BOPBiomes.FIELD,       BOPBiomes.FIELD},
            {BOPBiomes.PRAIRIE,         BOPBiomes.SHRUBLAND,            BOPBiomes.SHRUBLAND,               BOPBiomes.GRASSLAND,   BOPBiomes.GRASSLAND},
            {BOPBiomes.SCRUBLAND,       BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.WOODLAND,                BOPBiomes.RAINFOREST,  BOPBiomes.RAINFOREST},
            {BOPBiomes.WASTELAND,       BOPBiomes.WASTELAND,            BOPBiomes.DRYLAND,                 BOPBiomes.LUSH_DESERT, null}
    };

    private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null,                             null,                     BOPBiomes.SNOWY_FIR_CLEARING, BOPBiomes.SNOWY_MAPLE_FOREST, BOPBiomes.OLD_GROWTH_DEAD_FOREST},
            {BOPBiomes.PUMPKIN_PATCH, BOPBiomes.FIR_CLEARING,   null,                         null,                         BOPBiomes.FORESTED_FIELD},
            {BOPBiomes.PASTURE,         BOPBiomes.LAVENDER_FIELD, BOPBiomes.REDWOOD_FOREST,     null,                         BOPBiomes.CHERRY_BLOSSOM_GROVE},
            {null,                             null,                     BOPBiomes.DENSE_WOODLAND,     null,                         null},
            {null,                             null,                     BOPBiomes.DRY_BONEYARD,       BOPBiomes.LUSH_SAVANNA,       null}
    };

    private final ResourceKey<Biome>[][] SWAMP_BIOMES_BOP = new ResourceKey[][]{
            // NOTE: Frozen biomes not applicable for swamp biomes
            {null,            null,                          null,              null,              null},
            {BOPBiomes.BOG,   BOPBiomes.BOG,                 BOPBiomes.WETLAND, BOPBiomes.WETLAND, BOPBiomes.WETLAND},
            {BOPBiomes.MARSH, BOPBiomes.MARSH,               BOPBiomes.MARSH,   BOPBiomes.MARSH,   BOPBiomes.MARSH},
            {BOPBiomes.BAYOU, BOPBiomes.MEDITERRANEAN_LAKES, BOPBiomes.BAYOU,   BOPBiomes.BAYOU,   BOPBiomes.FLOODPLAIN},
            {null,            null,                          null,              null,              null}
    };

    private final ResourceKey<Biome>[][] RARE_BIOMES_BOP = new ResourceKey[][]{
            {null,                            null,                            null,                    null,                    null},
            {null,                            null,                            BOPBiomes.OMINOUS_WOODS, null,                    null},
            {null,                            null,                            null,                    BOPBiomes.MYSTIC_GROVE,  null},
            {null,                            null,                            null,                    null,                    BOPBiomes.FUNGAL_JUNGLE},
            {null,                            null,                            null,                    null,                    null}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,     Biomes.SNOWY_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.FOREST,       Biomes.TAIGA,           Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.MEADOW,       Biomes.MEADOW,          Biomes.DARK_FOREST},
            {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST,       Biomes.FOREST,          Biomes.JUNGLE},
            {Biomes.BADLANDS,        Biomes.BADLANDS,        Biomes.BADLANDS,     Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,      null,                   null,          null,                null},
            {null,                   null,                   Biomes.MEADOW, Biomes.MEADOW,       Biomes.OLD_GROWTH_PINE_TAIGA},
            {null,                   null,                   Biomes.FOREST, Biomes.BIRCH_FOREST, null},
            {null,                   null,                   null,          null,                null},
            {Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null,          null,                null}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,      BOPBiomes.TUNDRA,               BOPBiomes.SNOWY_FIR_CLEARING, null,                  BOPBiomes.DEAD_FOREST},
            {BOPBiomes.SEASONAL_FOREST,  BOPBiomes.CONIFEROUS_FOREST,    BOPBiomes.HIGHLAND,           BOPBiomes.HIGHLAND,    BOPBiomes.FIELD},
            {BOPBiomes.ORCHARD,          BOPBiomes.ROCKY_SHRUBLAND,      BOPBiomes.ROCKY_SHRUBLAND,    BOPBiomes.HIGHLAND,    BOPBiomes.HIGHLAND},
            {BOPBiomes.WOODED_SCRUBLAND, BOPBiomes.MEDITERRANEAN_FOREST, BOPBiomes.WOODLAND,           BOPBiomes.RAINFOREST,  BOPBiomes.RAINFOREST_CLIFFS},
            {BOPBiomes.WASTELAND,        BOPBiomes.WASTELAND,            BOPBiomes.DRYLAND,            BOPBiomes.LUSH_DESERT, BOPBiomes.VOLCANO}
    };

    private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null,                    null,                      BOPBiomes.SNOWY_MAPLE_FOREST, null,                    null},
            {BOPBiomes.BOREAL_FOREST, null,                      null,                         null,                    null},
            {null,                    BOPBiomes.LAVENDER_FOREST, null,                         BOPBiomes.HIGHLAND_MOOR, BOPBiomes.BAMBOO_GROVE},
            {null,                    null,                      null,                         null,                    null},
            {null,                    null,                      null,                         BOPBiomes.LUSH_SAVANNA,  BOPBiomes.VOLCANIC_PLAINS}
    };

    private final ResourceKey<Biome>[][] EXTREME_HILLS = new ResourceKey[][]{
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {null,                            null,                            null,                   null,                    null},
            {null,                            null,                            null,                   null,                    null}
    };

    private final ResourceKey<Biome>[][] EXTREME_HILLS_BOP = new ResourceKey[][]{
            {null,                            null,                            null,                   null,           null},
            {null,                            null,                            BOPBiomes.CRAG,         BOPBiomes.CRAG, null},
            {null,                            null,                            BOPBiomes.JADE_CLIFFS,  BOPBiomes.CRAG, BOPBiomes.CRAG},
            {null,                            null,                            null,                   null,           BOPBiomes.RAINFOREST_CLIFFS},
            {null,                            null,                            null,                   null,           BOPBiomes.VOLCANO}
    };

    public List<BOPClimate.ParameterPoint> spawnTarget()
    {
        BOPClimate.Parameter climate$parameter = BOPClimate.Parameter.point(0.0F);
        float f = 0.16F;
        return List.of(new BOPClimate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, BOPClimate.Parameter.span(-1.0F, -0.16F), this.FULL_RANGE, this.FULL_RANGE, 0L), new BOPClimate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, BOPClimate.Parameter.span(0.16F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0L));
    }

    public void addBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        if (SharedConstants.debugGenerateSquareTerrainWithoutNoise)
        {
            // TODO:
            // TerrainProvider.overworld(false).addDebugBiomesToVisualizeSplinePoints(mapper);
        }
        else
        {
            this.addOffCoastBiomes(biomeRegistry, mapper);
            this.addInlandBiomes(biomeRegistry, mapper);
            this.addUndergroundBiomes(biomeRegistry, mapper);
        }
    }

    private void addOffCoastBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> islandBiomeBOP = this.pickIslandBiomeBOP(biomeRegistry, i, j);

                this.addSurfaceBiomeGlobal(mapper, temperature, humidity, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, islandBiomeBOP);
            }

            this.addSurfaceBiomeGlobal(mapper, temperature, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiomeGlobal(mapper, temperature, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    private void addInlandBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        /*
            Weirdness ranges map to specific slices in a repeating triangle wave fashion.
                   PEAKS                           PEAKS
               HIGH     HIGH                   HIGH     HIGH
            MID             MID             MID             MID
                               LOW       LOW
                                  VALLEYS
         */

        // First cycle
        this.addMidSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(biomeRegistry, mapper, BOPClimate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(0.26666668F, 0.4F));

        // Second cycle is truncated
        this.addHighSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(biomeRegistry, mapper, BOPClimate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(biomeRegistry, mapper, BOPClimate.Parameter.span(0.93333334F, 1.0F));
    }

    private void addPeaks(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                     = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                         = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleOrBadlandsBiomeVanilla           = this.pickMiddleBiomeOrBadlandsIfHotVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeVanilla      = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP          = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> rareBiomeBOP                           = this.pickRareBiomeBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiome                           = this.pickPlateauBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> plateauBiomeBOP                        = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome                      = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeBOP                   = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome                         = this.maybePickShatteredBiome(i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiome                              = this.pickPeakBiome(i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeVanilla, middleBadlandsOrSlopeBiomeBOP, rareBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, peakBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), BOPClimate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiome, plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiome, plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiome, extremeHillsBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
            }
        }

    }

    private void addHighSlice(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                    = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleOrBadlandsBiomeVanilla      = this.pickMiddleBiomeOrBadlandsIfHotVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeVanilla = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP     = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> rareBiomeBOP                      = this.pickRareBiomeBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiome               = this.pickPlateauBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> plateauBiomeBOP            = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome          = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeBOP       = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> slopeBiomeVanilla          = this.pickSlopeBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> slopeBiomeBOP              = this.pickSlopeBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> peakBiome                  = this.pickPeakBiome(i, j, weirdness);

                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[0], weirdness, 0.0F, slopeBiomeVanilla, slopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeVanilla, middleBadlandsOrSlopeBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, slopeBiomeVanilla, slopeBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), BOPClimate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiome, plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiome, plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiome, extremeHillsBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
            }
        }

    }

    private void addMidSlice(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                      = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleOrBadlandsBiomeVanilla        = this.pickMiddleBiomeOrBadlandsIfHotVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeVanilla   = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> extremeHillsBiome          = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeBOP       = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> plateauBiomeVanilla        = this.pickPlateauBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> plateauBiomeBOP            = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> beachBiome                 = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome        = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> slopeBiomeVanilla          = this.pickSlopeBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> slopeBiomeBOP              = this.pickSlopeBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeBOP              = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> rareBiomeBOP               = this.pickRareBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, slopeBiomeVanilla, slopeBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeVanilla, middleBadlandsOrSlopeBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? slopeBiomeVanilla : plateauBiomeVanilla, i == 0 ? slopeBiomeBOP : plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, plateauBiomeVanilla, plateauBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);

                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiomeGlobal(mapper, temperature, humidity, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, beachBiome);
                    this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                }
                else
                {
                    this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                }

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiome, extremeHillsBiomeBOP);
                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiomeGlobal(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);
                }
                else
                {
                    this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                }

                if (i == 0)
                {
                    this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                }
                else
                {
                    this.addParallelSurfaceBiomesWithModerateBOPBias(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.SWAMP, swampBiomeBOP);
                }
            }
        }

    }

    private void addLowSlice(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, Biomes.STONY_SHORE);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                      = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleOrBadlandsBiomeVanilla        = this.pickMiddleBiomeOrBadlandsIfHotVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeVanilla   = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> beachBiome                   = this.pickBeachBiome(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome               = this.maybePickShatteredBiome(i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome          = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> rareBiomeBOP                 = this.pickRareBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeBOP                = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);

                // Lowest to low erosion
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.nearInlandContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBadlandsOrSlopeBiomeVanilla, middleBadlandsOrSlopeBiomeBOP, rareBiomeBOP);

                // Reduced to moderate erosion
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, this.nearInlandContinentalness, BOPClimate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), BOPClimate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);

                // Moderate to increased erosion
                this.addSurfaceBiomeGlobal(mapper, temperature, humidity, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0.0F, beachBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);

                // High erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);

                // Highest erosion
                this.addSurfaceBiomeGlobal(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);

                if (i == 0)
                {
                    this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeVanilla, middleBiomeBOP, rareBiomeBOP);
                }
                else
                {
                    this.addParallelSurfaceBiomesWithModerateBOPBias(mapper, temperature, humidity, BOPClimate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.SWAMP, swampBiomeBOP);
                }
            }
        }

    }

    private void addValleys(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter weirdness)
    {
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? Biomes.STONY_SHORE : Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, Biomes.RIVER);
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), BOPClimate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), BOPClimate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, Biomes.RIVER);

        // Coastal watery valleys
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);
        this.addSurfaceBiome(mapper, this.UNFROZEN_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, Biomes.RIVER);

        // Inland watery valleys
        this.addSurfaceBiome(mapper, this.FROZEN_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.FROZEN_RIVER);

        for (int i = 0; i < this.temperatures.length; ++i)
        {
            BOPClimate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                BOPClimate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> middleOrBadlandsBiomeVanilla = this.pickMiddleBiomeOrBadlandsIfHotVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP               = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> rareBiomeBOP                 = this.pickRareBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeBOP                = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addParallelSurfaceBiomes(mapper, temperature, humidity, BOPClimate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), BOPClimate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleOrBadlandsBiomeVanilla, middleBiomeBOP, rareBiomeBOP);

                if (i != 0)
                {
                    this.addParallelSurfaceBiomesWithModerateBOPBias(mapper, temperature, humidity, BOPClimate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, Biomes.SWAMP, swampBiomeBOP);
                }
            }
        }
    }

    private void addUndergroundBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addUndergroundBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.DRIPSTONE_CAVES);
        this.addUndergroundBiome(mapper, this.FULL_RANGE, BOPClimate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.LUSH_CAVES);
        this.addUndergroundBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, BOPClimate.Parameter.span(0.4F, 0.8F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, biomeOrFallback(biomeRegistry, BOPBiomes.GLOWING_GROTTO, Biomes.LUSH_CAVES));
        this.addUndergroundBiome(mapper, this.FULL_RANGE, BOPClimate.Parameter.span(0.3F, 0.7F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, biomeOrFallback(biomeRegistry, BOPBiomes.SPIDER_NEST, Biomes.DRIPSTONE_CAVES));
    }

    private ResourceKey<Biome> pickIslandBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return biomeOrFallback(biomeRegistry, this.ISLAND_BIOMES_BOP[temperatureIndex][humidityIndex], Biomes.MUSHROOM_FIELDS);
    }

    private ResourceKey<Biome> pickMiddleBiomeVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (weirdness.max() < 0L)
        {
            return this.MIDDLE_BIOMES[temperatureIndex][humidityIndex];
        }
        else
        {
            ResourceKey<Biome> variantBiome = this.MIDDLE_BIOMES_VARIANT[temperatureIndex][humidityIndex];
            return variantBiome == null ? this.MIDDLE_BIOMES[temperatureIndex][humidityIndex] : variantBiome;
        }
    }

    private ResourceKey<Biome> pickMiddleBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    private ResourceKey<Biome> pickRareBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = this.pickMiddleBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
        return biomeOrFallback(biomeRegistry, this.RARE_BIOMES_BOP[temperatureIndex][humidityIndex], middleBiome);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        return temperatureIndex == 4 ? this.pickBadlandsBiome(humidityIndex, weirdness) : this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        return temperatureIndex == 0 ? this.pickSlopeBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeOrBadlandsIfHotVanilla(temperatureIndex, humidityIndex, weirdness);
    }

    private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        return temperatureIndex == 0 ? this.pickSlopeBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
    }

    private ResourceKey<Biome> pickSwampBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        return biomeOrFallback(biomeRegistry, this.SWAMP_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickMiddleBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness), Biomes.SWAMP);
    }

    private ResourceKey<Biome> maybePickShatteredBiome(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness, ResourceKey<Biome> extremeHillsBiome)
    {
        return temperatureIndex > 1 && humidityIndex < 4 && weirdness.max() >= 0L ? Biomes.WINDSWEPT_SAVANNA : extremeHillsBiome;
    }

    private ResourceKey<Biome> pickShatteredCoastBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = weirdness.max() >= 0L ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiome(biomeRegistry, temperatureIndex, humidityIndex);
        return this.maybePickShatteredBiome(temperatureIndex, humidityIndex, weirdness, resourcekey);
    }

    private ResourceKey<Biome> pickBeachBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        if (temperatureIndex == 0)
            return Biomes.SNOWY_BEACH;
        else if (temperatureIndex == 2 && humidityIndex <= 2)
        {
            return biomeOrFallback(biomeRegistry, BOPBiomes.DUNE_BEACH, Biomes.BEACH);
        }
        else
        {
            return temperatureIndex == 4 ? Biomes.DESERT : Biomes.BEACH;
        }
    }

    private ResourceKey<Biome> pickBadlandsBiome(int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (humidityIndex < 2)
        {
            return weirdness.max() < 0L ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS;
        }
        else
        {
            return humidityIndex < 3 ? Biomes.BADLANDS : Biomes.WOODED_BADLANDS;
        }
    }

    private ResourceKey<Biome> pickPlateauBiomeVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (weirdness.max() < 0L)
        {
            return this.PLATEAU_BIOMES[temperatureIndex][humidityIndex];
        }
        else
        {
            ResourceKey<Biome> resourcekey = this.PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex];
            return resourcekey == null ? this.PLATEAU_BIOMES[temperatureIndex][humidityIndex] : resourcekey;
        }
    }

    private ResourceKey<Biome> pickPlateauBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
        else return biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
    }

    private ResourceKey<Biome> pickPeakBiome(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (temperatureIndex <= 2)
        {
            return weirdness.max() < 0L ? Biomes.JAGGED_PEAKS : Biomes.FROZEN_PEAKS;
        }
        else
        {
            return temperatureIndex == 3 ? Biomes.STONY_PEAKS : this.pickBadlandsBiome(humidityIndex, weirdness);
        }
    }

    private ResourceKey<Biome> pickSlopeBiomeVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (temperatureIndex >= 3)
        {
            return this.pickPlateauBiomeVanilla(temperatureIndex, humidityIndex, weirdness);
        }
        else
        {
            return humidityIndex <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
        }
    }

    private ResourceKey<Biome> pickSlopeBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        if (temperatureIndex >= 3)
        {
            return this.pickPlateauBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
        }
        else
        {
            return humidityIndex <= 1 ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
        }
    }

    private ResourceKey<Biome> pickExtremeHillsBiomeVanilla(int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = this.EXTREME_HILLS[temperatureIndex][humidityIndex];
        return resourcekey == null ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : resourcekey;
    }

    private ResourceKey<Biome> pickExtremeHillsBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, BOPClimate.Parameter weirdness)
    {
        return biomeOrFallback(biomeRegistry, this.EXTREME_HILLS_BOP[temperatureIndex][humidityIndex], this.pickExtremeHillsBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }

    private void addParallelSurfaceBiomes(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> vanillaBiome, ResourceKey<Biome> bopBiome)
    {
        addParallelSurfaceBiomes(mapper, temperature, humidity, continentalness, erosion, weirdness, offset, vanillaBiome, bopBiome, bopBiome);
    }

    private void addParallelSurfaceBiomes(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> vanillaBiome, ResourceKey<Biome> bopBiome, ResourceKey<Biome> rareBiome)
    {
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, VANILLA_UNIQUENESS_RANGE, COMMON_RARENESS_RANGE, offset, vanillaBiome);
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, VANILLA_UNIQUENESS_RANGE, RARE_RARENESS_RANGE, offset, rareBiome);
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, BOP_UNIQUENESS_RANGE, COMMON_RARENESS_RANGE, offset, bopBiome);
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, BOP_UNIQUENESS_RANGE, RARE_RARENESS_RANGE, offset, rareBiome);
    }

    private void addParallelSurfaceBiomesWithModerateBOPBias(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> vanillaBiome, ResourceKey<Biome> bopBiome)
    {
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, VANILLA_UNIQUENESS_RANGE_MODERATE_BOP_BIAS, this.FULL_RANGE, offset, vanillaBiome);
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, BOP_UNIQUENESS_RANGE_MODERATE_BOP_BIAS, this.FULL_RANGE,  offset, bopBiome);
    }

    private void addSurfaceBiome(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, VANILLA_UNIQUENESS_RANGE, this.FULL_RANGE, offset, biome);
    }

    private void addSurfaceBiomeGlobal(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        addSurfaceBiome(mapper, temperature, humidity, continentalness, erosion, weirdness, this.FULL_RANGE, this.FULL_RANGE, offset, biome);
    }

    private void addSurfaceBiome(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, BOPClimate.Parameter uniqueness, BOPClimate.Parameter rareness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(BOPClimate.parameters(temperature, humidity, continentalness, erosion, BOPClimate.Parameter.point(0.0F), weirdness, uniqueness, rareness, offset), biome));
        mapper.accept(Pair.of(BOPClimate.parameters(temperature, humidity, continentalness, erosion, BOPClimate.Parameter.point(1.0F), weirdness, uniqueness, rareness, offset), biome));
    }

    private void addUndergroundBiome(Consumer<Pair<BOPClimate.ParameterPoint, ResourceKey<Biome>>> mapper, BOPClimate.Parameter temperature, BOPClimate.Parameter humidity, BOPClimate.Parameter continentalness, BOPClimate.Parameter erosion, BOPClimate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(BOPClimate.parameters(temperature, humidity, continentalness, erosion, BOPClimate.Parameter.span(0.2F, 0.9F), weirdness, this.FULL_RANGE, this.FULL_RANGE, offset), biome));
    }

    private static ResourceKey<Biome> biomeOrFallback(Registry<Biome> biomeRegistry, ResourceKey<Biome>... biomes)
    {
        for (ResourceKey<Biome> key : biomes)
        {
            if (key == null)
                continue;

            Biome biome = biomeRegistry.get(key);

            if (biome != null)
                return key;
        }

        throw new RuntimeException("Failed to find fallback for biome!");
    }

    public static String getDebugStringForPeaksAndValleys(double p_187156_)
    {
        if (p_187156_ < (double) TerrainShaper.peaksAndValleys(0.05F))
        {
            return "Valley";
        }
        else if (p_187156_ < (double) TerrainShaper.peaksAndValleys(0.26666668F))
        {
            return "Low";
        }
        else if (p_187156_ < (double) TerrainShaper.peaksAndValleys(0.4F))
        {
            return "Mid";
        }
        else
        {
            return p_187156_ < (double) TerrainShaper.peaksAndValleys(0.56666666F) ? "High" : "Peak";
        }
    }

    public String getDebugStringForContinentalness(double p_187190_)
    {
        double d0 = (double) BOPClimate.quantizeCoord((float) p_187190_);
        if (d0 < (double) this.mushroomFieldsContinentalness.max())
        {
            return "Mushroom fields";
        }
        else if (d0 < (double) this.deepOceanContinentalness.max())
        {
            return "Deep ocean";
        }
        else if (d0 < (double) this.oceanContinentalness.max())
        {
            return "Ocean";
        }
        else if (d0 < (double) this.coastContinentalness.max())
        {
            return "Coast";
        }
        else if (d0 < (double) this.nearInlandContinentalness.max())
        {
            return "Near inland";
        }
        else
        {
            return d0 < (double) this.midInlandContinentalness.max() ? "Mid inland" : "Far inland";
        }
    }

    public String getDebugStringForErosion(double p_187210_)
    {
        return getDebugStringForNoiseValue(p_187210_, this.erosions);
    }

    public String getDebugStringForTemperature(double p_187221_)
    {
        return getDebugStringForNoiseValue(p_187221_, this.temperatures);
    }

    public String getDebugStringForHumidity(double p_187232_)
    {
        return getDebugStringForNoiseValue(p_187232_, this.humidities);
    }

    private static String getDebugStringForNoiseValue(double p_187158_, BOPClimate.Parameter[] p_187159_)
    {
        double d0 = (double) BOPClimate.quantizeCoord((float) p_187158_);

        for (int i = 0; i < p_187159_.length; ++i)
        {
            if (d0 < (double) p_187159_[i].max())
            {
                return "" + i;
            }
        }

        return "?";
    }
}