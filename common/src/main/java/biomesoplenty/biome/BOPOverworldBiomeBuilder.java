/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.init.ModConfig;
import biomesoplenty.util.biome.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class BOPOverworldBiomeBuilder
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

    protected final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    /* Terminology:
        Continentalness: Low to generate near coasts, far to generate away from coasts
        Erosion: Low is hilly terrain, high is flat terrain
     */

    protected final Climate.Parameter[] temperatures = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.45F),
            Climate.Parameter.span(-0.45F, -0.15F),
            Climate.Parameter.span(-0.15F, 0.2F),
            Climate.Parameter.span(0.2F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };

    protected final Climate.Parameter[] humidities = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.35F),
            Climate.Parameter.span(-0.35F, -0.1F),
            Climate.Parameter.span(-0.1F, 0.1F),
            Climate.Parameter.span(0.1F, 0.3F),
            Climate.Parameter.span(0.3F, 1.0F)
    };

    protected final Climate.Parameter[] erosions = new Climate.Parameter[]{
            Climate.Parameter.span(-1.0F, -0.78F),
            Climate.Parameter.span(-0.78F, -0.375F),
            Climate.Parameter.span(-0.375F, -0.2225F),
            Climate.Parameter.span(-0.2225F, 0.05F),
            Climate.Parameter.span(0.05F, 0.45F),
            Climate.Parameter.span(0.45F, 0.55F),
            Climate.Parameter.span(0.55F, 1.0F)
    };

    protected static final Climate.Parameter COMMON_RARENESS_RANGE = Climate.Parameter.span(-1.0F, 0.35F);
    protected static final Climate.Parameter RARE_RARENESS_RANGE = Climate.Parameter.span(0.35F, 1.0F);

    protected final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
    protected final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
    protected final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
    protected final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
    protected final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
    protected final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
    protected final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
    protected final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
    protected final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
    protected final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

    /******************************************************************************************************************************/

    // Vanilla Biomes
    private final ResourceKey<Biome>[][] OCEANS = new ResourceKey[][]{
            {Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN},
            {Biomes.FROZEN_OCEAN,      Biomes.COLD_OCEAN,      Biomes.OCEAN,      Biomes.LUKEWARM_OCEAN,      Biomes.WARM_OCEAN}
    };

    protected final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{
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

    protected final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS,    Biomes.SNOWY_PLAINS, Biomes.SNOWY_TAIGA,     Biomes.SNOWY_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.FOREST,       Biomes.TAIGA,           Biomes.OLD_GROWTH_SPRUCE_TAIGA},
            {Biomes.MEADOW,          Biomes.MEADOW,          Biomes.MEADOW,       Biomes.MEADOW,          Biomes.DARK_FOREST},
            {Biomes.SAVANNA_PLATEAU, Biomes.SAVANNA_PLATEAU, Biomes.FOREST,       Biomes.FOREST,          Biomes.JUNGLE},
            {Biomes.BADLANDS,        Biomes.BADLANDS,        Biomes.BADLANDS,     Biomes.WOODED_BADLANDS, Biomes.WOODED_BADLANDS}
    };

    protected final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{
            {Biomes.ICE_SPIKES,      null,                   null,          null,                null},
            {Biomes.CHERRY_GROVE,    null,                   Biomes.MEADOW, Biomes.MEADOW,       Biomes.OLD_GROWTH_PINE_TAIGA},
            {Biomes.CHERRY_GROVE,    Biomes.CHERRY_GROVE,    Biomes.FOREST, Biomes.BIRCH_FOREST, null},
            {null,                   null,                   null,          null,                null},
            {Biomes.ERODED_BADLANDS, Biomes.ERODED_BADLANDS, null,          null,                null}
    };

    private final ResourceKey<Biome>[][] EXTREME_HILLS_BIOMES = new ResourceKey[][]{
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS,          Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST},
            {null,                            null,                            null,                   null,                    null},
            {null,                            null,                            null,                   null,                    null}
    };

    private final ResourceKey<Biome>[][] SWAMP_BIOMES = new ResourceKey[][]{
            {Biomes.FROZEN_RIVER,   Biomes.FROZEN_RIVER,   Biomes.FROZEN_RIVER,   Biomes.FROZEN_RIVER,   Biomes.FROZEN_RIVER},
            {Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP},
            {Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP,          Biomes.SWAMP},
            {Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP},
            {Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP, Biomes.MANGROVE_SWAMP}
    };

    protected final ResourceKey<Biome>[][] BEACH_BIOMES = new ResourceKey[][]{
            {Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH, Biomes.SNOWY_BEACH},
            {Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH},
            {Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH},
            {Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH,       Biomes.BEACH},
            {Biomes.DESERT,      Biomes.DESERT,      Biomes.DESERT,      Biomes.DESERT,      Biomes.DESERT}
    };

    /******************************************************************************************************************************/

    // BOP Biomes
    protected final ResourceKey<Biome>[][] MIDDLE_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,       BOPBiomes.COLD_DESERT,       BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPBiomes.SNOWY_CONIFEROUS_FOREST, BOPBiomes.SNOWY_CONIFEROUS_FOREST},
            {BOPBiomes.CONIFEROUS_FOREST, BOPBiomes.CONIFEROUS_FOREST, BOPBiomes.CONIFEROUS_FOREST,       BOPBiomes.FIELD,                   BOPBiomes.FIELD},
            {BOPBiomes.PASTURE,           BOPBiomes.PRAIRIE,           BOPBiomes.REDWOOD_FOREST,          BOPBiomes.OVERGROWN_GREENS,        BOPBiomes.OVERGROWN_GREENS},
            {BOPBiomes.SCRUBLAND,         BOPBiomes.SCRUBLAND,         BOPBiomes.WOODLAND,                BOPBiomes.RAINFOREST,              BOPBiomes.RAINFOREST},
            {BOPBiomes.DRYLAND,           BOPBiomes.DRYLAND,           BOPBiomes.LUSH_DESERT,             BOPBiomes.LUSH_DESERT,             BOPBiomes.LUSH_DESERT}
    };

    protected final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null,              null,                   BOPBiomes.SNOWY_FIR_CLEARING,  BOPBiomes.SNOWY_FIR_CLEARING, BOPBiomes.SNOWY_MAPLE_WOODS},
            {null,              BOPBiomes.FIR_CLEARING, null,                          null,                         BOPBiomes.FORESTED_FIELD},
            {null,              null,                   null,                          null,                         BOPBiomes.GRASSLAND},
            {null,              null,                   BOPBiomes.OLD_GROWTH_WOODLAND, null,                         BOPBiomes.ROCKY_RAINFOREST},
            {null,              null,                   null,                          BOPBiomes.LUSH_SAVANNA,       BOPBiomes.LUSH_SAVANNA}
    };

    protected final ResourceKey<Biome>[][] PLATEAU_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.COLD_DESERT,       BOPBiomes.COLD_DESERT,       BOPBiomes.SNOWY_FIR_CLEARING, BOPBiomes.SNOWY_FIR_CLEARING, BOPBiomes.SNOWY_CONIFEROUS_FOREST},
            {BOPBiomes.CONIFEROUS_FOREST, BOPBiomes.CONIFEROUS_FOREST, BOPBiomes.HIGHLAND,           BOPBiomes.HIGHLAND,           BOPBiomes.FIELD},
            {BOPBiomes.PRAIRIE,           BOPBiomes.PRAIRIE,           BOPBiomes.HIGHLAND,           BOPBiomes.HIGHLAND,           BOPBiomes.HIGHLAND},
            {BOPBiomes.SCRUBLAND,         BOPBiomes.SCRUBLAND,         BOPBiomes.WOODLAND,           BOPBiomes.RAINFOREST,         BOPBiomes.ROCKY_RAINFOREST},
            {BOPBiomes.DRYLAND,           BOPBiomes.DRYLAND,           BOPBiomes.LUSH_DESERT,        BOPBiomes.LUSH_DESERT,        BOPBiomes.LUSH_DESERT}
    };

    protected final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT_BOP = new ResourceKey[][]{
            {null,              null, null,                        null,                       null},
            {null,              null, BOPBiomes.CONIFEROUS_FOREST, BOPBiomes.FIELD,            null},
            {BOPBiomes.PASTURE, null, BOPBiomes.REDWOOD_FOREST,    BOPBiomes.OVERGROWN_GREENS, BOPBiomes.MOOR},
            {null,              null, null,                        BOPBiomes.ROCKY_RAINFOREST, null},
            {null,              null, null,                        null,                       BOPBiomes.LUSH_SAVANNA}
    };

    protected final ResourceKey<Biome>[][] SLOPE_BIOMES_BOP = new ResourceKey[][]{
            {null,                  null,                  null,                  null,                  null},
            {BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG},
            {BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS},
            {null,                  null,                  null,                  BOPBiomes.VOLCANO,     BOPBiomes.VOLCANO},
            {null,                  null,                  null,                  null,                  null}
    };

    protected final ResourceKey<Biome>[][] PEAK_BIOMES_BOP = new ResourceKey[][]{
            {null,                  null,                  null,                  null,                  null},
            {BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG,        BOPBiomes.CRAG},
            {BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS, BOPBiomes.JADE_CLIFFS},
            {null,                  null,                  null,                  BOPBiomes.VOLCANO,     BOPBiomes.VOLCANO},
            {null,                  null,                  null,                  null,                  null}
    };

    protected final ResourceKey<Biome>[][] EXTREME_HILLS_BIOMES_BOP = new ResourceKey[][]{
            {null,               null,               null,               null,               null},
            {BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND},
            {BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND, BOPBiomes.HIGHLAND},
            {null,               null,               null,               BOPBiomes.VOLCANO,  BOPBiomes.VOLCANO},
            {null,               null,               null,               null,               null}
    };

    protected final ResourceKey<Biome>[][] SWAMP_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.HOT_SPRINGS, BOPBiomes.HOT_SPRINGS, BOPBiomes.HOT_SPRINGS, BOPBiomes.HOT_SPRINGS, BOPBiomes.HOT_SPRINGS},
            {BOPBiomes.WETLAND,     BOPBiomes.WETLAND,     BOPBiomes.WETLAND,     BOPBiomes.WETLAND,     BOPBiomes.WETLAND},
            {BOPBiomes.MARSH,       BOPBiomes.MARSH,       BOPBiomes.MARSH,       BOPBiomes.MARSH,       BOPBiomes.MARSH},
            {BOPBiomes.BAYOU,       BOPBiomes.BAYOU,       BOPBiomes.BAYOU,       BOPBiomes.FLOODPLAIN,  BOPBiomes.FLOODPLAIN},
            {BOPBiomes.BAYOU,       BOPBiomes.BAYOU,       BOPBiomes.BAYOU,       BOPBiomes.FLOODPLAIN,  BOPBiomes.FLOODPLAIN}
    };

    protected final ResourceKey<Biome>[][] RIVER_BIOMES_BOP = new ResourceKey[][]{
            {Biomes.FROZEN_RIVER, Biomes.FROZEN_RIVER, Biomes.FROZEN_RIVER, Biomes.FROZEN_RIVER,  Biomes.FROZEN_RIVER},
            {null,                null,                null,                null,                 null},
            {null,                null,                null,                null,                 null},
            {null,                null,                null,                BOPBiomes.FLOODPLAIN, BOPBiomes.FLOODPLAIN},
            {null,                null,                null,                null,                 null}
    };

    protected final ResourceKey<Biome>[][] BEACH_BIOMES_BOP = new ResourceKey[][]{
            {Biomes.SNOWY_BEACH,     Biomes.SNOWY_BEACH,     Biomes.SNOWY_BEACH,     Biomes.SNOWY_BEACH,     Biomes.SNOWY_BEACH},
            {BOPBiomes.GRAVEL_BEACH, BOPBiomes.GRAVEL_BEACH, BOPBiomes.GRAVEL_BEACH, BOPBiomes.GRAVEL_BEACH, BOPBiomes.GRAVEL_BEACH},
            {BOPBiomes.DUNE_BEACH,   BOPBiomes.DUNE_BEACH,   BOPBiomes.DUNE_BEACH,   Biomes.BEACH,           Biomes.BEACH},
            {Biomes.BEACH,           Biomes.BEACH,           Biomes.BEACH,           BOPBiomes.FLOODPLAIN,   BOPBiomes.FLOODPLAIN},
            {BOPBiomes.DRYLAND,      BOPBiomes.DRYLAND,      BOPBiomes.LUSH_DESERT,  BOPBiomes.LUSH_DESERT,  BOPBiomes.LUSH_DESERT}
    };

    protected final ResourceKey<Biome>[][] STONY_SHORES_BIOMES_BOP = new ResourceKey[][]{
            {null, null, null,                  null,                       null},
            {null, null, null,                  null,                       null},
            {null, null, null,                  null,                       null},
            {null, null, null,                  BOPBiomes.ROCKY_RAINFOREST, BOPBiomes.ROCKY_RAINFOREST},
            {null, null, BOPBiomes.LUSH_DESERT, BOPBiomes.LUSH_DESERT,      BOPBiomes.LUSH_DESERT}
    };

    protected final ResourceKey<Biome>[][] ISLAND_BIOMES_BOP = new ResourceKey[][]{
            {BOPBiomes.WINTRY_ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY},
            {BOPBiomes.ORIGIN_VALLEY,        BOPBiomes.ORIGIN_VALLEY,        BOPBiomes.ORIGIN_VALLEY,        BOPBiomes.ORIGIN_VALLEY,        BOPBiomes.ORIGIN_VALLEY},
            {null,                           null,                           null,                           null,                           null},
            {BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS},
            {BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS,              BOPBiomes.TROPICS}
    };

    /******************************************************************************************************************************/

    public void addBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addOffCoastBiomes(biomeRegistry, mapper);
        this.addInlandBiomes(biomeRegistry, mapper);
        this.addUndergroundBiomes(biomeRegistry, mapper);
    }

    private void addOffCoastBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> islandBiomeBOP = this.pickIslandBiomeBOP(biomeRegistry, i, j);

                this.addSurfaceBiome(mapper, temperature, humidity, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, islandBiomeBOP);
            }

            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[0][i]);
            this.addSurfaceBiome(mapper, temperature, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, this.OCEANS[1][i]);
        }
    }

    private void addInlandBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
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
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(biomeRegistry, mapper, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(biomeRegistry, mapper, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addValleys(biomeRegistry, mapper, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(biomeRegistry, mapper, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(0.26666668F, 0.4F));

        // Second cycle is truncated
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(biomeRegistry, mapper, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(biomeRegistry, mapper, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(biomeRegistry, mapper, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    protected void addPeaks(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeBOP                         = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP          = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeBOP                        = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiome                      = this.pickExtremeHillsBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeBOP                   = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome                         = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, extremeHillsBiome);
                ResourceKey<Biome> peakBiomeBOP                           = this.pickPeakBiomeBOP(biomeRegistry,  i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, peakBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeBOP);
            }
        }

    }

    protected void addHighSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                    = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP     = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> plateauBiomeBOP            = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> extremeHillsBiomeBOP       = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> slopeBiomeBOP              = this.pickSlopeBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> peakBiomeBOP               = this.pickPeakBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[0], weirdness, 0.0F, slopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, peakBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, slopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], weirdness, 0.0F, plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[3], weirdness, 0.0F, plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, middleBiomeBOP);
            }
        }

    }

    protected void addMidSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                      = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> extremeHillsBiomeBOP       = this.pickExtremeHillsBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> plateauBiomeBOP            = this.pickPlateauBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> beachBiomeBOP              = this.pickBeachBiomeBOP(biomeRegistry, i, j);
                ResourceKey<Biome> stonyShoresBiomeBOP        = this.pickStonyShoresBiomeBOP(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome             = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome        = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> slopeBiomeBOP              = this.pickSlopeBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeBOP              = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, stonyShoresBiomeBOP);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], weirdness, 0.0F, slopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], weirdness, 0.0F, middleBadlandsOrSlopeBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[1], weirdness, 0.0F, i == 0 ? slopeBiomeBOP : plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.midInlandContinentalness, this.erosions[2], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.farInlandContinentalness, this.erosions[2], weirdness, 0.0F, plateauBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], weirdness, 0.0F, middleBiomeBOP);

                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[4], weirdness, 0.0F, beachBiomeBOP);
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeBOP);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeBOP);
                }

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, extremeHillsBiomeBOP);
                if (weirdness.max() < 0L)
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiomeBOP);
                }
                else
                {
                    this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, middleBiomeBOP);
                }

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeBOP);
            }
        }

    }

    protected void addLowSlice(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];

                ResourceKey<Biome> middleBiomeVanilla                  = this.pickMiddleBiomeVanilla(i, j, weirdness);
                ResourceKey<Biome> middleBiomeBOP                      = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> middleBadlandsOrSlopeBiomeBOP       = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> beachBiome                   = this.pickBeachBiomeBOP(biomeRegistry, i, j);
                ResourceKey<Biome> stonyShoresBiomeBOP          = this.pickStonyShoresBiomeBOP(biomeRegistry, i, j);
                ResourceKey<Biome> shatteredBiome               = this.maybePickShatteredBiome(biomeRegistry, i, j, weirdness, middleBiomeVanilla);
                ResourceKey<Biome> shatteredCoastBiome          = this.pickShatteredCoastBiome(biomeRegistry, i, j, weirdness);

                ResourceKey<Biome> swampBiomeBOP                = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), weirdness, 0.0F, stonyShoresBiomeBOP);

                // Lowest to low erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBadlandsOrSlopeBiomeBOP);

                // Reduced to moderate erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0F, middleBiomeBOP);

                // Moderate to increased erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), weirdness, 0.0F, beachBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, middleBiomeBOP);

                // High erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[5], weirdness, 0.0F, shatteredCoastBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, this.erosions[5], weirdness, 0.0F, shatteredBiome);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, middleBiomeBOP);

                // Highest erosion
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, beachBiome);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeBOP);
            }
        }

    }

    protected void addValleys(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter weirdness)
    {
        // BOP River biomes
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> riverBiomeBOP       = this.pickRiverBiomeBOP(biomeRegistry, i, j);
                ResourceKey<Biome> stonyShoresBiomeBOP = this.pickStonyShoresBiomeBOP(biomeRegistry, i, j);

                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, weirdness.max() < 0L ? stonyShoresBiomeBOP : riverBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, riverBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), weirdness, 0.0F, riverBiomeBOP);
                this.addSurfaceBiome(mapper, temperature, humidity, this.coastContinentalness, this.erosions[6], weirdness, 0.0F, riverBiomeBOP);
            }
        }

        // BOP Swamp biomes
        for (int i = 0; i < this.temperatures.length; ++i)
        {
            Climate.Parameter temperature = this.temperatures[i];

            for (int j = 0; j < this.humidities.length; ++j)
            {
                Climate.Parameter humidity = this.humidities[j];
                ResourceKey<Biome> middleBiomeBOP               = this.pickMiddleBiomeBOP(biomeRegistry, i, j, weirdness);
                ResourceKey<Biome> swampBiomeBOP                = this.pickSwampBiomeBOP(biomeRegistry, i, j, weirdness);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0F, middleBiomeBOP);

                this.addSurfaceBiome(mapper, temperature, humidity, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0F, swampBiomeBOP);
            }
        }
    }

    protected void addUndergroundBiomes(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addUndergroundBiome(biomeRegistry, mapper, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, BOPBiomes.SPIDER_NEST);
        this.addUndergroundBiome(biomeRegistry, mapper, this.FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, BOPBiomes.GLOWING_GROTTO);
        this.addBottomBiome(mapper, this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.erosions[0], this.erosions[1]), this.FULL_RANGE, 0.0F, Biomes.DEEP_DARK);
    }

    protected ResourceKey<Biome> pickMiddleBiomeVanilla(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
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

    protected ResourceKey<Biome> pickMiddleBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> middleBiome = BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_BOP[temperatureIndex][humidityIndex], this.MIDDLE_BIOMES[temperatureIndex][humidityIndex]);

        if (weirdness.max() < 0) return middleBiome;
        else
        {
            return BiomeUtil.biomeOrFallback(biomeRegistry, this.MIDDLE_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], middleBiome);
        }
    }

    protected ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfColdBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return temperatureIndex == 0 ? this.pickSlopeBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness) : this.pickMiddleBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness);
    }

    protected ResourceKey<Biome> maybePickShatteredBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness, ResourceKey<Biome> extremeHillsBiome)
    {
        return temperatureIndex > 1 && humidityIndex < 4 && weirdness.max() >= 0L ? BiomeUtil.biomeOrFallback(biomeRegistry, BOPBiomes.CRAG, Biomes.WINDSWEPT_SAVANNA) : extremeHillsBiome;
    }

    protected ResourceKey<Biome> pickShatteredCoastBiome(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = weirdness.max() >= 0L ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : this.pickBeachBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex);
        return this.maybePickShatteredBiome(biomeRegistry, temperatureIndex, humidityIndex, weirdness, resourcekey);
    }

    protected ResourceKey<Biome> pickSwampBiomeVanilla(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = this.SWAMP_BIOMES[temperatureIndex][humidityIndex];
        return resourcekey == null ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : resourcekey;
    }

    protected ResourceKey<Biome> pickSwampBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.SWAMP_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickSwampBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }

    protected ResourceKey<Biome> pickRiverBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.RIVER_BIOMES_BOP[temperatureIndex][humidityIndex], temperatureIndex == 0 ? Biomes.FROZEN_RIVER : Biomes.RIVER);
    }

    protected ResourceKey<Biome> pickBeachBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.BEACH_BIOMES_BOP[temperatureIndex][humidityIndex], this.BEACH_BIOMES[temperatureIndex][humidityIndex]);
    }

    protected ResourceKey<Biome> pickStonyShoresBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.STONY_SHORES_BIOMES_BOP[temperatureIndex][humidityIndex], Biomes.STONY_SHORE);
    }

    protected ResourceKey<Biome> pickIslandBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.ISLAND_BIOMES_BOP[temperatureIndex][humidityIndex], Biomes.MUSHROOM_FIELDS);
    }

    protected ResourceKey<Biome> pickBadlandsBiome(int humidityIndex, Climate.Parameter weirdness)
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

    protected ResourceKey<Biome> pickPlateauBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        if (weirdness.max() < 0L) return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
        else return BiomeUtil.biomeOrFallback(biomeRegistry, this.PLATEAU_BIOMES_VARIANT_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_BOP[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES_VARIANT[temperatureIndex][humidityIndex], this.PLATEAU_BIOMES[temperatureIndex][humidityIndex]);
    }

    protected ResourceKey<Biome> pickPeakBiome(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
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

    protected ResourceKey<Biome> pickPeakBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.PEAK_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickPeakBiome(temperatureIndex, humidityIndex, weirdness));
    }

    protected ResourceKey<Biome> pickSlopeBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.SLOPE_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickPlateauBiomeBOP(biomeRegistry, temperatureIndex, humidityIndex, weirdness));
    }

    protected ResourceKey<Biome> pickExtremeHillsBiomeVanilla(int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        ResourceKey<Biome> resourcekey = this.EXTREME_HILLS_BIOMES[temperatureIndex][humidityIndex];
        return resourcekey == null ? this.pickMiddleBiomeVanilla(temperatureIndex, humidityIndex, weirdness) : resourcekey;
    }

    protected ResourceKey<Biome> pickExtremeHillsBiomeBOP(Registry<Biome> biomeRegistry, int temperatureIndex, int humidityIndex, Climate.Parameter weirdness)
    {
        return BiomeUtil.biomeOrFallback(biomeRegistry, this.EXTREME_HILLS_BIOMES_BOP[temperatureIndex][humidityIndex], this.pickExtremeHillsBiomeVanilla(temperatureIndex, humidityIndex, weirdness));
    }

    protected void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(0.0F), weirdness, offset), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.0F), weirdness, offset), biome));
    }

    protected void addUndergroundBiome(Registry<Biome> biomeRegistry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        if (!ModConfig.isBiomeEnabled(biome))
            return;

        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.span(0.2F, 0.9F), weirdness, offset), biome));
    }

    private void addBottomBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter continentalness, Climate.Parameter erosion, Climate.Parameter weirdness, float offset, ResourceKey<Biome> biome)
    {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, Climate.Parameter.point(1.1F), weirdness, offset), biome));
    }
}