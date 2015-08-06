/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.init;

import static biomesoplenty.api.biome.generation.GeneratorRegistry.registerGenerator;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.world.feature.*;
import biomesoplenty.common.world.feature.tree.*;

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
        registerGenerator("taiga_tree", GeneratorTaigaTree.class, new GeneratorTaigaTree.Builder());
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
        registerGenerator("mixed_lily", GeneratorMixedLily.class, new GeneratorMixedLily.Builder());
    }
}
