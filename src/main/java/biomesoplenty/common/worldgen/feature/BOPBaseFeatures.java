/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.common.worldgen.feature.configurations.*;
import biomesoplenty.common.worldgen.feature.misc.*;
import biomesoplenty.common.worldgen.feature.tree.*;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPBaseFeatures
{
    public static final BOPTreeFeature<BasicTreeConfiguration> BASIC_TREE = register("basic_tree", new BasicTreeFeature(BasicTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BIG_DRIPLEAF = register("big_dripleaf", new BigDripleafFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BIG_PUMPKIN = register("big_pumpkin", new BigPumpkinFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BLACK_SAND_SPLATTER = register("black_sand_splatter", new BlackSandSplatterFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<BasicTreeConfiguration> BUSH_TREE = register("bush_tree", new BushTreeFeature(BasicTreeConfiguration.CODEC));
    public static final BOPTreeFeature<BigTreeConfiguration> BIG_TREE = register("big_tree", new BigTreeFeature(BigTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BRAMBLE = register("bramble", new BrambleFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> CRAG_SPLATTER = register("crag_splatter", new CragSplatterFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<CypressTreeConfiguration> CYPRESS_TREE = register("cypress_tree", new CypressTreeFeature(CypressTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BONE_SPINE = register("bone_spine", new BoneSpineFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> GRASS_SPLATTER = register("grass_splatter", new GrassSplatterFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> HUGE_TOADSTOOL = register("huge_toadstool", new HugeToadstoolFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> HUGE_CLOVER = register("huge_clover", new HugeCloverFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> HUGE_GLOWSHROOM = register("huge_glowshroom", new HugeGlowshroomFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<MahoganyTreeConfiguration> MAHOGANY_TREE = register("mahogany_tree", new MahoganyTreeFeature(MahoganyTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MOSS_SPLATTER = register("moss_splatter", new MossSplatterFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> MYCELIUM_SPLATTER = register("mycelium_splatter", new MyceliumSplatterFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<PalmTreeConfiguration> PALM_TREE = register("palm_tree", new PalmTreeFeature(PalmTreeConfiguration.CODEC));
    public static final BOPTreeFeature<PoplarTreeConfiguration> POPLAR_TREE = register("poplar_tree", new PoplarTreeFeature(PoplarTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> PUMPKIN_PATCH = register("pumpkin_patch", new PumpkinPatchFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> RAINFOREST_CLIFFS_VINES = register("rainforest_cliffs_vines", new RainforestCliffsVinesFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<TaigaTreeConfiguration> REDWOOD_TREE = register("redwood_tree", new RedwoodTreeFeature(TaigaTreeConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SCATTERED_ROCKS = register("scattered_rocks", new ScatteredRocksFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SCRUB = register("scrub", new ScrubFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SHORT_BAMBOO = register("short_bamboo", new ShortBambooFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SMALL_DRIPLEAF = register("small_dripleaf", new SmallDripleafFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SMALL_GLOWSHROOM = register("small_glowshroom", new SmallGlowshroomFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SMALL_RED_MUSHROOM = register("small_red_mushroom", new SmallRedMushroomFeature(NoneFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> SMALL_TOADSTOOL = register("small_toadstool", new SmallToadstoolFeature(NoneFeatureConfiguration.CODEC));
    public static final BOPTreeFeature<TaigaTreeConfiguration> TAIGA_TREE = register("taiga_tree", new TaigaTreeFeature(TaigaTreeConfiguration.CODEC));
    public static final BOPTreeFeature<TwigletTreeConfiguration> TWIGLET_TREE = register("twiglet_tree", new TwigletTreeFeature(TwigletTreeConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String key, F value)
    {
        value.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.FEATURES.register(value);
        return value;
    }
}
