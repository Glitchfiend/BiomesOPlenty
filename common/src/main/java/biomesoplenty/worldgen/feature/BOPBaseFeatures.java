/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.feature.configurations.*;
import biomesoplenty.worldgen.feature.misc.*;
import biomesoplenty.worldgen.feature.tree.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.BiConsumer;

public class BOPBaseFeatures
{

    public static BOPTreeFeature<BasicTreeConfiguration> BASIC_TREE;
    public static Feature<NoneFeatureConfiguration> HIGH_GRASS;
    public static Feature<NoneFeatureConfiguration> LUMALOOP;
    public static Feature<NoneFeatureConfiguration> TIDEPOOL;
    public static Feature<NoneFeatureConfiguration> DEAD_CORAL_PATCH;
    public static Feature<NoneFeatureConfiguration> WISPJELLY;
    public static Feature<NoneFeatureConfiguration> JAGGED_SANDSTONE;
    public static Feature<NoneFeatureConfiguration> MONOLITH;
    public static Feature<NoneFeatureConfiguration> ANOMALY;
    public static Feature<NoneFeatureConfiguration> BIG_DRIPLEAF;
    public static Feature<NoneFeatureConfiguration> BIG_PUMPKIN;
    public static Feature<NoneFeatureConfiguration> BLACK_SAND_SPLATTER;
    public static BOPTreeFeature<BasicTreeConfiguration> BUSH_TREE;
    public static BOPTreeFeature<BigTreeConfiguration> BIG_TREE;
    public static Feature<NoneFeatureConfiguration> BRAMBLE;
    public static Feature<NoneFeatureConfiguration> CORNER_COBWEBS;
    public static Feature<NoneFeatureConfiguration> CRAG_MOSS;
    public static Feature<NoneFeatureConfiguration> CRAG_SPLATTER;
    public static BOPTreeFeature<BayouTreeConfiguration> BAYOU_TREE;
    public static Feature<NoneFeatureConfiguration> DRIPSTONE_SPLATTER;
    public static Feature<NoneFeatureConfiguration> BONE_SPINE;
    public static BOPTreeFeature<EmpyrealTreeConfiguration> EMPYREAL_TREE;
    public static Feature<NoneFeatureConfiguration> EXTRA_GLOW_LICHEN;
    public static Feature<NoneFeatureConfiguration> FALLEN_LOG;
    public static Feature<NoneFeatureConfiguration> FALLEN_FIR_LOG;
    public static Feature<NoneFeatureConfiguration> FALLEN_BIRCH_LOG;
    public static Feature<NoneFeatureConfiguration> FALLEN_JACARANDA_LOG;
    public static Feature<NoneFeatureConfiguration> FLESH_TENDON;
    public static Feature<NoneFeatureConfiguration> GIANT_GLOWSHROOM;
    public static Feature<NoneFeatureConfiguration> HOT_SPRING_VENTS;
    public static Feature<NoneFeatureConfiguration> MOSSY_BLACK_SAND_SPLATTER;
    public static Feature<NoneFeatureConfiguration> HANGING_FLESH_TENDON;
    public static Feature<NoneFeatureConfiguration> HUGE_TOADSTOOL;
    public static Feature<NoneFeatureConfiguration> HUGE_CLOVER;
    public static Feature<NoneFeatureConfiguration> HUGE_LILY_PAD;
    public static Feature<NoneFeatureConfiguration> HUGE_GLOWSHROOM;
    public static Feature<NoneFeatureConfiguration> INFERNO_SPLATTER;
    public static Feature<NoneFeatureConfiguration> LARGE_FUMAROLE;
    public static Feature<LargeDripstoneConfiguration> LARGE_ROSE_QUARTZ;
    public static BOPTreeFeature<MagicTreeConfiguration> MAGIC_TREE;
    public static BOPTreeFeature<MahoganyTreeConfiguration> MAHOGANY_TREE;
    public static Feature<NoneFeatureConfiguration> MEDIUM_GLOWSHROOM;
    public static Feature<NoneFeatureConfiguration> MOSS_SPLATTER;
    public static Feature<NoneFeatureConfiguration> MUD_SPLATTER;
    public static Feature<NoneFeatureConfiguration> MYCELIUM_SPLATTER;
    public static Feature<NoneFeatureConfiguration> NETHER_VINES;
    public static Feature<NoneFeatureConfiguration> OBSIDIAN_SPLATTER;
    public static BOPTreeFeature<PalmTreeConfiguration> PALM_TREE;
    public static BOPTreeFeature<PineTreeConfiguration> PINE_TREE;
    public static BOPTreeFeature<CypressTreeConfiguration> CYPRESS_TREE;
    public static Feature<NoneFeatureConfiguration> PUMPKIN_PATCH;
    public static Feature<NoneFeatureConfiguration> RED_MAPLE_LEAF_PILE;
    public static Feature<NoneFeatureConfiguration> ORANGE_MAPLE_LEAF_PILE;
    public static Feature<NoneFeatureConfiguration> YELLOW_MAPLE_LEAF_PILE;
    public static Feature<NoneFeatureConfiguration> SPARSE_DUNE_GRASS;
    public static Feature<NoneFeatureConfiguration> RAINFOREST_CLIFFS_VINES;
    public static BOPTreeFeature<TaigaTreeConfiguration> REDWOOD_TREE;
    public static Feature<NoneFeatureConfiguration> ROOTED_STUMP;
    public static Feature<NoneFeatureConfiguration> SCATTERED_ROCKS;
    public static Feature<NoneFeatureConfiguration> SCRUB;
    public static Feature<NoneFeatureConfiguration> SHORT_BAMBOO;
    public static Feature<NoneFeatureConfiguration> THIN_BAMBOO;
    public static Feature<NoneFeatureConfiguration> SMALL_BROWN_MUSHROOM;
    public static Feature<NoneFeatureConfiguration> SMALL_CRYSTAL;
    public static Feature<NoneFeatureConfiguration> SMALL_DRIPLEAF;
    public static Feature<NoneFeatureConfiguration> SMALL_FUMAROLE;
    public static Feature<NoneFeatureConfiguration> SMALL_GLOWSHROOM;
    public static Feature<NoneFeatureConfiguration> SMALL_RED_MUSHROOM;
    public static Feature<NoneFeatureConfiguration> SMALL_TOADSTOOL;
    public static Feature<NoneFeatureConfiguration> STRINGY_COBWEB;
    public static BOPTreeFeature<TaigaTreeConfiguration> TAIGA_TREE;
    public static Feature<NoneFeatureConfiguration> TERMITE_MOUND;
    public static BOPTreeFeature<TwigletTreeConfiguration> TWIGLET_TREE;
    public static BOPTreeFeature<TaigaTreeConfiguration> UMBRAN_TREE;
    public static Feature<NoneFeatureConfiguration> WEBBING;
    public static Feature<NoneFeatureConfiguration> ORIGIN_GRAVEL_CLIFFS;
    public static Feature<NoneFeatureConfiguration> DEAD_CORAL_TREE;
    public static Feature<NoneFeatureConfiguration> DEAD_CORAL_MUSHROOM;
    public static Feature<NoneFeatureConfiguration> DEAD_CORAL_CLAW;
    public static Feature<NoneFeatureConfiguration> BARNACLES;
    public static BOPLakeFeature LAKE;

    public static void registerFeatures(BiConsumer<ResourceLocation, Feature<?>> func)
    {
        BASIC_TREE = register(func, "basic_tree", new BasicTreeFeature(BasicTreeConfiguration.CODEC));
        HIGH_GRASS = register(func, "high_grass", new HighGrassFeature(NoneFeatureConfiguration.CODEC));
        LUMALOOP = register(func, "lumaloop", new LumaloopFeature(NoneFeatureConfiguration.CODEC));
        TIDEPOOL = register(func, "tidepool", new TidepoolFeature(NoneFeatureConfiguration.CODEC));
        DEAD_CORAL_PATCH = register(func, "dead_coral_patch", new DeadCoralPatchFeature(NoneFeatureConfiguration.CODEC));
        WISPJELLY = register(func, "wispjelly", new WispjellyFeature(NoneFeatureConfiguration.CODEC));
        JAGGED_SANDSTONE = register(func, "jagged_sandstone", new JaggedSandstoneFeature(NoneFeatureConfiguration.CODEC));
        ANOMALY = register(func, "anomaly", new AnomalyFeature(NoneFeatureConfiguration.CODEC));
        MONOLITH = register(func, "monolith", new MonolithFeature(NoneFeatureConfiguration.CODEC));
        BIG_DRIPLEAF = register(func, "big_dripleaf", new BigDripleafFeature(NoneFeatureConfiguration.CODEC));
        BIG_PUMPKIN = register(func, "big_pumpkin", new BigPumpkinFeature(NoneFeatureConfiguration.CODEC));
        BLACK_SAND_SPLATTER = register(func, "black_sand_splatter", new BlackSandSplatterFeature(NoneFeatureConfiguration.CODEC));
        BUSH_TREE = register(func, "bush_tree", new BushTreeFeature(BasicTreeConfiguration.CODEC));
        BIG_TREE = register(func, "big_tree", new BigTreeFeature(BigTreeConfiguration.CODEC));
        BRAMBLE = register(func, "bramble", new BrambleFeature(NoneFeatureConfiguration.CODEC));
        CORNER_COBWEBS = register(func, "corner_cobwebs", new CornerCobwebFeature(NoneFeatureConfiguration.CODEC));
        CRAG_MOSS = register(func, "crag_moss", new CragMossFeature(NoneFeatureConfiguration.CODEC));
        CRAG_SPLATTER = register(func, "crag_splatter", new CragSplatterFeature(NoneFeatureConfiguration.CODEC));
        BAYOU_TREE = register(func, "bayou_tree", new BayouTreeFeature(BayouTreeConfiguration.CODEC));
        DRIPSTONE_SPLATTER = register(func, "dripstone_splatter", new DripstoneSplatterFeature(NoneFeatureConfiguration.CODEC));
        BONE_SPINE = register(func, "bone_spine", new BoneSpineFeature(NoneFeatureConfiguration.CODEC));
        EMPYREAL_TREE = register(func, "empyreal_tree", new EmpyrealTreeFeature(EmpyrealTreeConfiguration.CODEC));
        EXTRA_GLOW_LICHEN = register(func, "extra_glow_lichen", new ExtraGlowLichenFeature(NoneFeatureConfiguration.CODEC));
        FALLEN_LOG = register(func, "fallen_log", new FallenLogFeature(NoneFeatureConfiguration.CODEC));
        FALLEN_FIR_LOG = register(func, "fallen_fir_log", new FallenFirLogFeature(NoneFeatureConfiguration.CODEC));
        FALLEN_BIRCH_LOG = register(func, "fallen_birch_log", new FallenBirchLogFeature(NoneFeatureConfiguration.CODEC));
        FALLEN_JACARANDA_LOG = register(func, "fallen_jacaranda_log", new FallenJacarandaLogFeature(NoneFeatureConfiguration.CODEC));
        FLESH_TENDON = register(func, "flesh_tendon", new FleshTendonFeature(NoneFeatureConfiguration.CODEC));
        GIANT_GLOWSHROOM = register(func, "giant_glowshroom", new GiantGlowshroomFeature(NoneFeatureConfiguration.CODEC));
        HOT_SPRING_VENTS = register(func, "hot_spring_vents", new HotSpringVentFeature(NoneFeatureConfiguration.CODEC));
        MOSSY_BLACK_SAND_SPLATTER = register(func, "mossy_black_sand_splatter", new GrassSplatterFeature(NoneFeatureConfiguration.CODEC));
        HANGING_FLESH_TENDON = register(func, "hanging_flesh_tendon", new HangingFleshTendonFeature(NoneFeatureConfiguration.CODEC));
        HUGE_TOADSTOOL = register(func, "huge_toadstool", new HugeToadstoolFeature(NoneFeatureConfiguration.CODEC));
        HUGE_CLOVER = register(func, "huge_clover", new HugeCloverFeature(NoneFeatureConfiguration.CODEC));
        HUGE_LILY_PAD = register(func, "huge_lily_pad", new HugeLilyPadFeature(NoneFeatureConfiguration.CODEC));
        HUGE_GLOWSHROOM = register(func, "huge_glowshroom", new HugeGlowshroomFeature(NoneFeatureConfiguration.CODEC));
        INFERNO_SPLATTER = register(func, "inferno_splatter", new InfernoSplatterFeature(NoneFeatureConfiguration.CODEC));
        LARGE_FUMAROLE = register(func, "large_fumarole", new LargeFumaroleFeature(NoneFeatureConfiguration.CODEC));
        LARGE_ROSE_QUARTZ = register(func, "large_rose_quartz", new LargeRoseQuartzFeature(LargeDripstoneConfiguration.CODEC));
        MAGIC_TREE = register(func, "magic_tree", new MagicTreeFeature(MagicTreeConfiguration.CODEC));
        MAHOGANY_TREE = register(func, "mahogany_tree", new MahoganyTreeFeature(MahoganyTreeConfiguration.CODEC));
        MEDIUM_GLOWSHROOM = register(func, "medium_glowshroom", new MediumGlowshroomFeature(NoneFeatureConfiguration.CODEC));
        MOSS_SPLATTER = register(func, "moss_splatter", new MossSplatterFeature(NoneFeatureConfiguration.CODEC));
        MUD_SPLATTER = register(func, "mud_splatter", new MudSplatterFeature(NoneFeatureConfiguration.CODEC));
        MYCELIUM_SPLATTER = register(func, "mycelium_splatter", new MyceliumSplatterFeature(NoneFeatureConfiguration.CODEC));
        NETHER_VINES = register(func, "nether_vines", new NetherVinesFeature(NoneFeatureConfiguration.CODEC));
        OBSIDIAN_SPLATTER = register(func, "obsidian_splatter", new ObsidianSplatterFeature(NoneFeatureConfiguration.CODEC));
        PALM_TREE = register(func, "palm_tree", new PalmTreeFeature(PalmTreeConfiguration.CODEC));
        PINE_TREE = register(func, "pine_tree", new PineTreeFeature(PineTreeConfiguration.CODEC));
        CYPRESS_TREE = register(func, "cypress_tree", new CypressTreeFeature(CypressTreeConfiguration.CODEC));
        PUMPKIN_PATCH = register(func, "pumpkin_patch", new PumpkinPatchFeature(NoneFeatureConfiguration.CODEC));
        RED_MAPLE_LEAF_PILE = register(func, "red_maple_leaf_pile", new RedMapleLeafPileFeature(NoneFeatureConfiguration.CODEC));
        ORANGE_MAPLE_LEAF_PILE = register(func, "orange_maple_leaf_pile", new OrangeMapleLeafPileFeature(NoneFeatureConfiguration.CODEC));
        YELLOW_MAPLE_LEAF_PILE = register(func, "yellow_maple_leaf_pile", new YellowMapleLeafPileFeature(NoneFeatureConfiguration.CODEC));
        SPARSE_DUNE_GRASS = register(func, "sparse_dune_grass", new SparseDuneGrassFeature(NoneFeatureConfiguration.CODEC));
        RAINFOREST_CLIFFS_VINES = register(func, "rainforest_cliffs_vines", new RainforestCliffsVinesFeature(NoneFeatureConfiguration.CODEC));
        REDWOOD_TREE = register(func, "redwood_tree", new RedwoodTreeFeature(TaigaTreeConfiguration.CODEC));
        ROOTED_STUMP = register(func, "rooted_stump", new RootedStumpFeature(NoneFeatureConfiguration.CODEC));
        SCATTERED_ROCKS = register(func, "scattered_rocks", new ScatteredRocksFeature(NoneFeatureConfiguration.CODEC));
        SCRUB = register(func, "scrub", new ScrubFeature(NoneFeatureConfiguration.CODEC));
        SHORT_BAMBOO = register(func, "short_bamboo", new ShortBambooFeature(NoneFeatureConfiguration.CODEC));
        THIN_BAMBOO = register(func, "thin_bamboo", new ThinBambooFeature(NoneFeatureConfiguration.CODEC));
        SMALL_BROWN_MUSHROOM = register(func, "small_brown_mushroom", new SmallBrownMushroomFeature(NoneFeatureConfiguration.CODEC));
        SMALL_CRYSTAL = register(func, "small_crystal", new SmallCrystalFeature(NoneFeatureConfiguration.CODEC));
        SMALL_DRIPLEAF = register(func, "small_dripleaf", new SmallDripleafFeature(NoneFeatureConfiguration.CODEC));
        SMALL_FUMAROLE = register(func, "small_fumarole", new SmallFumaroleFeature(NoneFeatureConfiguration.CODEC));
        SMALL_GLOWSHROOM = register(func, "small_glowshroom", new SmallGlowshroomFeature(NoneFeatureConfiguration.CODEC));
        SMALL_RED_MUSHROOM = register(func, "small_red_mushroom", new SmallRedMushroomFeature(NoneFeatureConfiguration.CODEC));
        SMALL_TOADSTOOL = register(func, "small_toadstool", new SmallToadstoolFeature(NoneFeatureConfiguration.CODEC));
        STRINGY_COBWEB = register(func, "stringy_cobweb", new StringyCobwebFeature(NoneFeatureConfiguration.CODEC));
        TAIGA_TREE = register(func, "taiga_tree", new TaigaTreeFeature(TaigaTreeConfiguration.CODEC));
        TERMITE_MOUND = register(func, "termite_mound", new TermiteMoundFeature(NoneFeatureConfiguration.CODEC));
        TWIGLET_TREE = register(func, "twiglet_tree", new TwigletTreeFeature(TwigletTreeConfiguration.CODEC));
        UMBRAN_TREE = register(func, "umbran_tree", new UmbranTreeFeature(TaigaTreeConfiguration.CODEC));
        WEBBING = register(func, "webbing", new WebbingFeature(NoneFeatureConfiguration.CODEC));
        ORIGIN_GRAVEL_CLIFFS = register(func, "origin_gravel_cliffs", new OriginGravelCliffFeature(NoneFeatureConfiguration.CODEC));
        DEAD_CORAL_TREE = register(func, "dead_coral_tree", new DeadCoralTreeFeature(NoneFeatureConfiguration.CODEC));
        DEAD_CORAL_MUSHROOM = register(func, "dead_coral_mushroom", new DeadCoralMushroomFeature(NoneFeatureConfiguration.CODEC));
        DEAD_CORAL_CLAW = register(func, "dead_coral_claw", new DeadCoralClawFeature(NoneFeatureConfiguration.CODEC));
        BARNACLES = register(func, "barnacles", new BarnacleFeature(NoneFeatureConfiguration.CODEC));
        LAKE = register(func, "lake", new BOPLakeFeature(LakeFeature.Configuration.CODEC));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(BiConsumer<ResourceLocation, Feature<?>> func, String name, F feature)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), feature);
        return feature;
    }
}
