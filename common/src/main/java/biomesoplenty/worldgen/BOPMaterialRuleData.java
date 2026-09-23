/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class BOPMaterialRuleData
{
    private static final MaterialCondition ON_FLOOR = MaterialRules.stoneDepthCheck(0, false, CaveSurface.FLOOR);
    private static final MaterialCondition UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, CaveSurface.FLOOR);
    private static final MaterialCondition DEEP_UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, 6, CaveSurface.FLOOR);
    private static final MaterialCondition VERY_DEEP_UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, 30, CaveSurface.FLOOR);
    private static final MaterialCondition ON_CEILING = MaterialRules.stoneDepthCheck(0, false, CaveSurface.CEILING);
    private static final MaterialCondition UNDER_CEILING = MaterialRules.stoneDepthCheck(0, true, CaveSurface.CEILING);

    private static final MaterialRule AIR = makeStateRule(Blocks.AIR);
    private static final MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final MaterialRule LIGHT_BLUE_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.lightBlue());
    private static final MaterialRule CYAN_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.cyan());
    private static final MaterialRule LIGHT_GRAY_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.lightGray());
    private static final MaterialRule TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final MaterialRule STONE = makeStateRule(Blocks.STONE);
    private static final MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRule PODZOL = makeStateRule(Blocks.PODZOL);
    private static final MaterialRule MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final MaterialRule SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final MaterialRule POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final MaterialRule WATER = makeStateRule(Blocks.WATER);
    private static final MaterialRule LAVA = makeStateRule(Blocks.LAVA);
    private static final MaterialRule MAGMA = makeStateRule(Blocks.MAGMA_BLOCK);
    private static final MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRule TUFF = makeStateRule(Blocks.TUFF);
    private static final MaterialRule SMOOTH_BASALT = makeStateRule(Blocks.SMOOTH_BASALT);

    // Nether
    private static final MaterialRule NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final MaterialRule BASALT = makeStateRule(Blocks.BASALT);
    private static final MaterialRule BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);

    // BOP
    private static final MaterialRule ALGAL_END_STONE = makeStateRule(BOPBlocks.ALGAL_END_STONE);
    private static final MaterialRule UNMAPPED_END_STONE = makeStateRule(BOPBlocks.UNMAPPED_END_STONE);
    private static final MaterialRule DRIED_SALT = makeStateRule(BOPBlocks.DRIED_SALT);
    private static final MaterialRule WHITE_SAND = makeStateRule(BOPBlocks.WHITE_SAND);
    private static final MaterialRule WHITE_SANDSTONE = makeStateRule(BOPBlocks.WHITE_SANDSTONE);
    private static final MaterialRule ORANGE_SAND = makeStateRule(BOPBlocks.ORANGE_SAND);
    private static final MaterialRule ORANGE_SANDSTONE = makeStateRule(BOPBlocks.ORANGE_SANDSTONE);
    private static final MaterialRule BLACK_SAND = makeStateRule(BOPBlocks.BLACK_SAND);
    private static final MaterialRule BLACK_SANDSTONE = makeStateRule(BOPBlocks.BLACK_SANDSTONE);
    private static final MaterialRule ORIGIN_GRASS = makeStateRule(BOPBlocks.ORIGIN_GRASS_BLOCK);
    private static final MaterialRule FLESH = makeStateRule(BOPBlocks.FLESH);
    private static final MaterialRule ORPIMENT = makeStateRule(BOPBlocks.ORPIMENT);

    private static MaterialRule makeStateRule(Block p_194811_) {
        return MaterialRules.state(p_194811_.defaultBlockState());
    }

    public static MaterialRule overworld(RegistryAccess registryAccess)
    {
        HolderGetter<Biome> biomes = registryAccess.lookupOrThrow(Registries.BIOME);
        MaterialRule surfaceRules = MaterialRules.sequence(
            makeBOPOverworldRules(biomes));

        ImmutableList.Builder<MaterialRule> builder = ImmutableList.builder();

        MaterialRule surfacerules$rulesource9 = MaterialRules.ifTrue(MaterialRules.abovePreliminarySurface(), surfaceRules);
        builder.add(surfacerules$rulesource9);
        return MaterialRules.sequence(builder.build().toArray((p_198379_) -> {
            return new MaterialRule[p_198379_];
        }));
    }

    public static MaterialRule nether(RegistryAccess registryAccess)
    {
        HolderGetter<Biome> biomes = registryAccess.lookupOrThrow(Registries.BIOME);
        MaterialCondition surfacerules$conditionsource1 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        MaterialCondition isTop5Blocks = MaterialRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        MaterialCondition isHole = MaterialRules.hole();

        return MaterialRules.sequence(
            MaterialRules.ifTrue(
                MaterialRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
                BEDROCK
            ),
            MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
            MaterialRules.ifTrue(isTop5Blocks, NETHERRACK),
            makeBOPNetherRules(biomes),
            MaterialRules.ifTrue(
                ON_FLOOR,
                MaterialRules.sequence(
                    MaterialRules.ifTrue(
                        MaterialRules.not(surfacerules$conditionsource1),
                        MaterialRules.ifTrue(isHole, LAVA)
                    )
                )
            ),
            NETHERRACK);
    }

    private static MaterialRule makeBOPOverworldRules(HolderGetter<Biome> biomes)
    {
        // Conditions
        MaterialCondition isAtOrAboveWaterLevel = MaterialRules.waterBlockCheck(-1, 0);
        MaterialCondition sixBelowWater = MaterialRules.waterStartCheck(-6, -1);
        MaterialCondition isAbove62 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        MaterialCondition isAbove63 = MaterialRules.yBlockCheck(VerticalAnchor.absolute(63), 0);

        MaterialRule powderedSnowSurface = MaterialRules.sequence(
            MaterialRules.ifTrue(ON_FLOOR, POWDER_SNOW),
            MaterialRules.ifTrue(UNDER_FLOOR, POWDER_SNOW)
        );

        MaterialRule snowSurface = MaterialRules.sequence(
                MaterialRules.ifTrue(ON_FLOOR, SNOW_BLOCK),
                MaterialRules.ifTrue(UNDER_FLOOR, SNOW_BLOCK)
        );

        MaterialRule gravelStoneSurface = MaterialRules.sequence(
                MaterialRules.ifTrue(ON_FLOOR, GRAVEL),
                MaterialRules.ifTrue(UNDER_FLOOR, STONE)
        );

        MaterialRule gravelBeachSurface = MaterialRules.sequence(
                MaterialRules.ifTrue(ON_FLOOR, MaterialRules.sequence(MaterialRules.ifTrue(isAbove63, AIR), MaterialRules.sequence(MaterialRules.ifTrue(isAbove62, WATER), GRAVEL))),
                MaterialRules.ifTrue(UNDER_FLOOR, GRAVEL)
        );

        MaterialRule originBeach = MaterialRules.sequence(
                MaterialRules.ifTrue(surfaceNoiseAbove(1.5D), GRAVEL), SAND
        );

        MaterialRule mixedLushDesertSurface = MaterialRules.sequence(
            MaterialRules.ifTrue(surfaceNoiseAbove(1.9D), ORANGE_SANDSTONE),
            ORANGE_SAND
        );

        MaterialRule mixedColdDesertSurface = MaterialRules.sequence(
            MaterialRules.ifTrue(surfaceNoiseAbove(3.4D), powderedSnowSurface),
            MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(2.6D), snowSurface),
            gravelStoneSurface
        ));

        MaterialRule volcanicPlainsUnderground = MaterialRules.sequence(
                MaterialRules.ifTrue(surfaceNoiseAbove(2.4D), TUFF),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(1.2D), SMOOTH_BASALT),
                BLACK_SANDSTONE)
        );

        MaterialRule volcanicPlainsLining = MaterialRules.sequence(
                MaterialRules.ifTrue(surfaceNoiseAbove(2.4D), TUFF),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(1.2D), SMOOTH_BASALT),
                MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, BLACK_SANDSTONE), BLACK_SAND)
        ));

        MaterialRule volcanoSurface = MaterialRules.sequence(
                MaterialRules.ifTrue(ON_FLOOR, BASALT),
                SMOOTH_BASALT
        );

        // Sandstone linings
        MaterialRule sandstoneLinedSand = MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, SANDSTONE), SAND);
        MaterialRule lushDesertSandstoneLinedOrangeSand = MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, ORANGE_SANDSTONE), mixedLushDesertSurface);
        MaterialRule blackSandstoneLining = MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, BLACK_SANDSTONE), BLACK_SAND);
        MaterialRule coldDesertStoneLinedGravelSnow = MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, STONE), mixedColdDesertSurface);

        return MaterialRules.sequence(
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.CRAG),
                MaterialRules.ifTrue(surfaceNoiseAbove(1.9D), STONE)
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.ROCKY_RAINFOREST),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(4.0D), LIGHT_BLUE_TERRACOTTA),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(3.0D), CYAN_TERRACOTTA),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(2.0D), LIGHT_GRAY_TERRACOTTA),
                MaterialRules.ifTrue(surfaceNoiseAbove(1.0D), TERRACOTTA))))
            ),
            MaterialRules.ifTrue(
                sixBelowWater,
                MaterialRules.sequence(
                    MaterialRules.ifTrue(
                        UNDER_FLOOR,
                        MaterialRules.sequence(
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.DRYLAND),
                                MaterialRules.ifTrue(surfaceNoiseAbove(1.75D), sandstoneLinedSand)
                            ),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.DUNE_BEACH), sandstoneLinedSand),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.COLD_DESERT), coldDesertStoneLinedGravelSnow),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.LUSH_DESERT), lushDesertSandstoneLinedOrangeSand),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.VOLCANIC_PLAINS), volcanicPlainsLining)
                        )
                    ),
                    MaterialRules.ifTrue(
                        DEEP_UNDER_FLOOR,
                        MaterialRules.sequence(
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.DRYLAND),
                                MaterialRules.ifTrue(surfaceNoiseAbove(1.75D), SANDSTONE)
                            ),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.COLD_DESERT), STONE),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.LUSH_DESERT), ORANGE_SANDSTONE),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.VOLCANIC_PLAINS), volcanicPlainsUnderground)
                        )
                    )
                )
            ),
            MaterialRules.ifTrue(
                ON_FLOOR,
                MaterialRules.sequence(
                    MaterialRules.ifTrue(
                        // Swamp water noise
                        MaterialRules.isBiome(biomes, BOPBiomes.MARSH, BOPBiomes.FLOODPLAIN),
                        MaterialRules.ifTrue(
                            isAbove62,
                            MaterialRules.ifTrue(
                                MaterialRules.not(isAbove63),
                                MaterialRules.ifTrue(
                                    MaterialRules.noiseCondition2d(Noises.SWAMP, 0.0D),
                                    WATER
                                )
                            )
                        )
                    ),
                    MaterialRules.ifTrue(
                        // Grass substitutes
                        isAtOrAboveWaterLevel,
                        MaterialRules.sequence(
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.LUSH_SAVANNA),
                                MaterialRules.ifTrue(surfaceNoiseAbove(2.0D), ORANGE_SAND)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.OVERGROWN_GREENS),
                                MaterialRules.ifTrue(surfaceNoiseAbove(1.7D), COARSE_DIRT)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.HOT_SPRINGS),
                                MaterialRules.ifTrue(surfaceNoiseAbove(2.0D), COARSE_DIRT)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.FUNGAL_JUNGLE),
                                MaterialRules.ifTrue(MaterialRules.noiseCondition2d(Noises.PATCH, 0.05D, 0.2D), MYCELIUM)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.OLD_GROWTH_WOODLAND),
                                MaterialRules.ifTrue(surfaceNoiseAbove(2.6D), COARSE_DIRT)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.OLD_GROWTH_DEAD_FOREST),
                                MaterialRules.ifTrue(surfaceNoiseAbove(2.3D), PODZOL)
                            ),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.MEDITERRANEAN_FOREST),
                                MaterialRules.ifTrue(surfaceNoiseAbove(1.9D), PODZOL)
                            ),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY), MaterialRules.sequence(MaterialRules.ifTrue(MaterialRules.yStartCheck(VerticalAnchor.absolute(66), 0), ORIGIN_GRASS), MaterialRules.ifTrue(MaterialRules.yBlockCheck(VerticalAnchor.absolute(65), 0), originBeach))),
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.REDWOOD_FOREST), PODZOL)
                        )
                    ),
                    MaterialRules.ifTrue(
                        // Underwater lining
                        MaterialRules.not(isAtOrAboveWaterLevel),
                        MaterialRules.sequence(
                            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.CRAG, BOPBiomes.ROCKY_RAINFOREST), GRAVEL),
                            MaterialRules.ifTrue(
                                MaterialRules.isBiome(biomes, BOPBiomes.VOLCANO),
                                MaterialRules.ifTrue(surfaceNoiseAbove(2.7D), SMOOTH_BASALT)
                            )
                        )
                    )
                )
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.COLD_DESERT),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(3.4D), powderedSnowSurface),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(2.6D), snowSurface),
                    gravelStoneSurface
                ))
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.CRAG), STONE),
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.VOLCANO),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(3.7D), MAGMA),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(2.6D), BLACK_SANDSTONE),
                MaterialRules.sequence(MaterialRules.ifTrue(surfaceNoiseAbove(1.5D), SMOOTH_BASALT),
                    volcanoSurface
                )))
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.GRAVEL_BEACH), gravelBeachSurface),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.ORIGIN_VALLEY, BOPBiomes.WINTRY_ORIGIN_VALLEY), MaterialRules.sequence(MaterialRules.ifTrue(MaterialRules.yStartCheck(VerticalAnchor.absolute(66), 0), MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), DIRT)), MaterialRules.ifTrue(MaterialRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), originBeach))),

            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.ROCKY_SHRUBLAND),
                MaterialRules.ifTrue(surfaceNoiseAbove(1.4D), STONE)
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.WASTELAND), DRIED_SALT)
        );
    }

    private static MaterialRule makeBOPNetherRules(HolderGetter<Biome> biomes)
    {
        MaterialCondition isAbove30 = MaterialRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        MaterialCondition isBelow35 = MaterialRules.not(MaterialRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        MaterialCondition isSuitablePatchNoise = MaterialRules.noiseCondition2d(Noises.PATCH, -0.012D);
        MaterialCondition isStateSelectorNoiseSuitable = MaterialRules.noiseCondition2d(Noises.NETHER_STATE_SELECTOR, 0.2D);

        MaterialRule obsidianPatchRules = MaterialRules.ifTrue(isSuitablePatchNoise, MaterialRules.ifTrue(isAbove30, MaterialRules.ifTrue(isBelow35, OBSIDIAN)));
        MaterialRule tuffPatchRules = MaterialRules.ifTrue(isSuitablePatchNoise, MaterialRules.ifTrue(isAbove30, MaterialRules.ifTrue(isBelow35, TUFF)));

        return MaterialRules.sequence(
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.ERUPTING_INFERNO),
                MaterialRules.sequence(
                    MaterialRules.ifTrue(UNDER_CEILING, NETHERRACK),
                    MaterialRules.ifTrue(UNDER_FLOOR,
                        MaterialRules.sequence(
                            tuffPatchRules,
                            MaterialRules.ifTrue(isStateSelectorNoiseSuitable, NETHERRACK),
                            ORPIMENT
                        )
                    )
                )
            ),
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.VISCERAL_HEAP),
                MaterialRules.sequence(
                    MaterialRules.ifTrue(UNDER_CEILING, FLESH),
                    MaterialRules.ifTrue(UNDER_FLOOR, FLESH)
                )
            ),
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.WITHERED_ABYSS),
                MaterialRules.sequence(
                    MaterialRules.ifTrue(UNDER_CEILING, BLACKSTONE),
                    MaterialRules.ifTrue(UNDER_FLOOR,
                        MaterialRules.sequence(
                            obsidianPatchRules,
                            BLACKSTONE
                        )
                    )
                )
            )
        );
    }

    public static MaterialRule end(RegistryAccess registryAccess)
    {
        HolderGetter<Biome> biomes = registryAccess.lookupOrThrow(Registries.BIOME);
        MaterialRule whiteSandstoneLining = MaterialRules.sequence(MaterialRules.ifTrue(ON_CEILING, WHITE_SANDSTONE), WHITE_SAND);

        return MaterialRules.sequence(
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.END_WILDS),
                MaterialRules.sequence(
                    MaterialRules.ifTrue(ON_FLOOR,
                        MaterialRules.sequence(
                            ALGAL_END_STONE
                        )
                    )
                )
            ),
            MaterialRules.ifTrue(MaterialRules.isBiome(biomes, BOPBiomes.END_CORRUPTION), UNMAPPED_END_STONE),
            MaterialRules.ifTrue(
                MaterialRules.isBiome(biomes, BOPBiomes.END_REEF),
                MaterialRules.sequence(
                    MaterialRules.ifTrue(UNDER_FLOOR,
                        MaterialRules.sequence(
                            whiteSandstoneLining
                        )
                    )
                )
            )
        );
    }


    private static MaterialCondition surfaceNoiseAbove(double p_194809_) {
        return MaterialRules.noiseCondition2d(Noises.SURFACE, p_194809_ / 8.25D, Double.MAX_VALUE);
    }
}
