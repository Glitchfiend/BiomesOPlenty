/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.feature.misc.*;
import biomesoplenty.worldgen.feature.tree.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.function.BiConsumer;

public class BOPBaseFeatures
{
    public static void registerFeatureTypes(BiConsumer<Identifier, MapCodec<? extends Feature>> func)
    {
        register(func, "anomaly", AnomalyFeature.CODEC);
        register(func, "barnacles", BarnacleFeature.CODEC);
        register(func, "basic_tree", BasicTreeFeature.CODEC);
        register(func, "bayou_tree", BayouTreeFeature.CODEC);
        register(func, "big_dripleaf", BigDripleafFeature.CODEC);
        register(func, "big_pumpkin", BigPumpkinFeature.CODEC);
        register(func, "big_tree", BigTreeFeature.CODEC);
        register(func, "black_sand_splatter", BlackSandSplatterFeature.CODEC);
        register(func, "bone_spine", BoneSpineFeature.CODEC);
        register(func, "bramble", BrambleFeature.CODEC);
        register(func, "bush_tree", BushTreeFeature.CODEC);
        register(func, "corner_cobwebs", CornerCobwebFeature.CODEC);
        register(func, "crag_moss", CragMossFeature.CODEC);
        register(func, "crag_splatter", CragSplatterFeature.CODEC);
        register(func, "cypress_tree", CypressTreeFeature.CODEC);
        register(func, "dead_coral_block", DeadCoralBlockFeature.CODEC);
        register(func, "dead_coral_patch", DeadCoralPatchFeature.CODEC);
        register(func, "dripstone_splatter", DripstoneSplatterFeature.CODEC);
        register(func, "empyreal_tree", EmpyrealTreeFeature.CODEC);
        register(func, "endscraper", EndscraperFeature.CODEC);
        register(func, "eroded_pillar", ErodedPillarFeature.CODEC);
        register(func, "extra_glow_lichen", ExtraGlowLichenFeature.CODEC);
        register(func, "fallen_birch_log", FallenBirchLogFeature.CODEC);
        register(func, "fallen_dead_log", FallenDeadLogFeature.CODEC);
        register(func, "fallen_fir_log", FallenFirLogFeature.CODEC);
        register(func, "fallen_jacaranda_log", FallenJacarandaLogFeature.CODEC);
        register(func, "fallen_log", FallenLogFeature.CODEC);
        register(func, "flesh_tendon", FleshTendonFeature.CODEC);
        register(func, "giant_glowshroom", GiantGlowshroomFeature.CODEC);
        register(func, "hanging_flesh_tendon", HangingFleshTendonFeature.CODEC);
        register(func, "high_grass", HighGrassFeature.CODEC);
        register(func, "hot_spring_vents", HotSpringVentFeature.CODEC);
        register(func, "huge_clover", HugeCloverFeature.CODEC);
        register(func, "huge_flower", HugeFlowerFeature.CODEC);
        register(func, "huge_glowshroom", HugeGlowshroomFeature.CODEC);
        register(func, "huge_lily_pad", HugeLilyPadFeature.CODEC);
        register(func, "huge_toadstool", HugeToadstoolFeature.CODEC);
        register(func, "inferno_splatter", InfernoSplatterFeature.CODEC);
        register(func, "jagged_sandstone", JaggedSandstoneFeature.CODEC);
        register(func, "lake", BOPLakeFeature.CODEC);
        register(func, "large_fumarole", LargeFumaroleFeature.CODEC);
        register(func, "large_rose_quartz", LargeRoseQuartzFeature.CODEC);
        register(func, "lumaloop", LumaloopFeature.CODEC);
        register(func, "magic_tree", MagicTreeFeature.CODEC);
        register(func, "mahogany_tree", MahoganyTreeFeature.CODEC);
        register(func, "medium_glowshroom", MediumGlowshroomFeature.CODEC);
        register(func, "monolith", MonolithFeature.CODEC);
        register(func, "moss_splatter", MossSplatterFeature.CODEC);
        register(func, "mossy_black_sand_splatter", GrassSplatterFeature.CODEC);
        register(func, "mud_splatter", MudSplatterFeature.CODEC);
        register(func, "mycelium_splatter", MyceliumSplatterFeature.CODEC);
        register(func, "nether_vines", NetherVinesFeature.CODEC);
        register(func, "obsidian_splatter", ObsidianSplatterFeature.CODEC);
        register(func, "origin_gravel_cliffs", OriginGravelCliffFeature.CODEC);
        register(func, "palm_tree", PalmTreeFeature.CODEC);
        register(func, "pine_tree", PineTreeFeature.CODEC);
        register(func, "pumpkin_patch", PumpkinPatchFeature.CODEC);
        register(func, "rainforest_cliffs_vines", RainforestCliffsVinesFeature.CODEC);
        register(func, "redwood_tree", RedwoodTreeFeature.CODEC);
        register(func, "rooted_stump", RootedStumpFeature.CODEC);
        register(func, "scattered_rocks", ScatteredRocksFeature.CODEC);
        register(func, "scrub", ScrubFeature.CODEC);
        register(func, "short_bamboo", ShortBambooFeature.CODEC);
        register(func, "small_brown_mushroom", SmallBrownMushroomFeature.CODEC);
        register(func, "small_crystal", SmallCrystalFeature.CODEC);
        register(func, "small_dripleaf", SmallDripleafFeature.CODEC);
        register(func, "small_fumarole", SmallFumaroleFeature.CODEC);
        register(func, "small_glowshroom", SmallGlowshroomFeature.CODEC);
        register(func, "small_red_mushroom", SmallRedMushroomFeature.CODEC);
        register(func, "small_toadstool", SmallToadstoolFeature.CODEC);
        register(func, "sparse_dune_grass", SparseDuneGrassFeature.CODEC);
        register(func, "stringy_cobweb", StringyCobwebFeature.CODEC);
        register(func, "taiga_tree", TaigaTreeFeature.CODEC);
        register(func, "termite_mound", TermiteMoundFeature.CODEC);
        register(func, "thin_bamboo", ThinBambooFeature.CODEC);
        register(func, "tidepool", TidepoolFeature.CODEC);
        register(func, "twiglet_tree", TwigletTreeFeature.CODEC);
        register(func, "umbran_tree", UmbranTreeFeature.CODEC);
        register(func, "webbing", WebbingFeature.CODEC);
        register(func, "wispjelly", WispjellyFeature.CODEC);
    }

    private static void register(BiConsumer<Identifier, MapCodec<? extends Feature>> func, String name, MapCodec<? extends Feature> codec)
    {
        func.accept(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), codec);
    }
}
