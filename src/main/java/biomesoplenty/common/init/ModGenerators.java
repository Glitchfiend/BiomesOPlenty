/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.common.world.GeneratorRegistry.registerGenerator;

import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorBlobs;
import biomesoplenty.common.world.generator.GeneratorBramble;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorCrystals;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorLogs;
import biomesoplenty.common.world.generator.GeneratorOreCluster;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorSpike;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorSplotches;
import biomesoplenty.common.world.generator.GeneratorVines;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBayouTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorBulbTree;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorMahoganyTree;
import biomesoplenty.common.world.generator.tree.GeneratorMangroveTree;
import biomesoplenty.common.world.generator.tree.GeneratorMegaJungleTree;
import biomesoplenty.common.world.generator.tree.GeneratorPalmTree;
import biomesoplenty.common.world.generator.tree.GeneratorPineTree;
import biomesoplenty.common.world.generator.tree.GeneratorProfileTree;
import biomesoplenty.common.world.generator.tree.GeneratorRedwoodTree;
import biomesoplenty.common.world.generator.tree.GeneratorRedwoodTreeThin;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;

public class ModGenerators
{
    public static void init()
    {
        registerGenerator("ore_single", GeneratorOreSingle.class, new GeneratorOreSingle.Builder());
        registerGenerator("ore_cluster", GeneratorOreCluster.class, new GeneratorOreCluster.Builder());
        registerGenerator("weighted", GeneratorWeighted.class, new GeneratorWeighted.Builder());
        registerGenerator("basic_tree", GeneratorBasicTree.class, new GeneratorBasicTree.Builder());
        registerGenerator("big_tree", GeneratorBigTree.class, new GeneratorBigTree.Builder());
        registerGenerator("bush", GeneratorBush.class, new GeneratorBush.Builder());
        registerGenerator("twiglet_tree", GeneratorTwigletTree.class, new GeneratorTwigletTree.Builder());
        registerGenerator("pine_tree", GeneratorPineTree.class, new GeneratorPineTree.Builder());
        registerGenerator("bulb_tree", GeneratorBulbTree.class, new GeneratorBulbTree.Builder());
        registerGenerator("mega_jungle_tree", GeneratorMegaJungleTree.class, new GeneratorMegaJungleTree.Builder());
        registerGenerator("bayou_tree", GeneratorBayouTree.class, new GeneratorBayouTree.Builder());
        registerGenerator("mangrove_tree", GeneratorMangroveTree.class, new GeneratorMangroveTree.Builder());
        registerGenerator("taiga_tree", GeneratorTaigaTree.class, new GeneratorTaigaTree.Builder());
        registerGenerator("profile_tree", GeneratorProfileTree.class, new GeneratorProfileTree.Builder());
        registerGenerator("flora", GeneratorFlora.class, new GeneratorFlora.Builder());
        registerGenerator("double_flora", GeneratorDoubleFlora.class, new GeneratorDoubleFlora.Builder());
        registerGenerator("grass", GeneratorGrass.class, new GeneratorGrass.Builder());
        registerGenerator("logs", GeneratorLogs.class, new GeneratorLogs.Builder());
        registerGenerator("big_mushrooms", GeneratorBigMushroom.class, new GeneratorBigMushroom.Builder());
        registerGenerator("waterside", GeneratorWaterside.class, new GeneratorWaterside.Builder());
        registerGenerator("splatter", GeneratorSplatter.class, new GeneratorSplatter.Builder());
        registerGenerator("splotches", GeneratorSplotches.class, new GeneratorSplotches.Builder());
        registerGenerator("blobs", GeneratorBlobs.class, new GeneratorBlobs.Builder());
        registerGenerator("lakes", GeneratorLakes.class, new GeneratorLakes.Builder());
        registerGenerator("columns", GeneratorColumns.class, new GeneratorColumns.Builder());
        registerGenerator("bramble", GeneratorBramble.class, new GeneratorBramble.Builder());
        registerGenerator("vines", GeneratorVines.class, new GeneratorVines.Builder());
        registerGenerator("crystals", GeneratorCrystals.class, new GeneratorCrystals.Builder());
        registerGenerator("spike", GeneratorSpike.class, new GeneratorSpike.Builder());
        registerGenerator("redwood_tree", GeneratorRedwoodTree.class, new GeneratorRedwoodTree.Builder());
        registerGenerator("redwood_tree_thin", GeneratorRedwoodTreeThin.class, new GeneratorRedwoodTreeThin.Builder());
        registerGenerator("mahogany_tree", GeneratorMahoganyTree.class, new GeneratorMahoganyTree.Builder());
        registerGenerator("palm_tree", GeneratorPalmTree.class, new GeneratorPalmTree.Builder());
    }
}
