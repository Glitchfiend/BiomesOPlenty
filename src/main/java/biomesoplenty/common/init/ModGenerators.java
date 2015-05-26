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
        registerGenerator("ore_single", GeneratorOreSingle.class);
        registerGenerator("ore_cluster", GeneratorOreCluster.class);
        registerGenerator("weighted", GeneratorWeighted.class);
        registerGenerator("basic_tree", GeneratorBasicTree.class);
        registerGenerator("big_tree", GeneratorBigTree.class);
        registerGenerator("bush", GeneratorBush.class);
        registerGenerator("flora", GeneratorFlora.class);
        registerGenerator("double_flora", GeneratorDoubleFlora.class);
        registerGenerator("grass", GeneratorGrass.class);
        registerGenerator("waterside", GeneratorWaterside.class);
    }
}
