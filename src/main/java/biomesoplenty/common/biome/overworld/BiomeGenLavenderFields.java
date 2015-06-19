/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
 
public class BiomeGenLavenderFields extends BOPBiome
{
    public BiomeGenLavenderFields()
    {
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(4, 12); 
        
        this.setColor(11035852);
        this.setTemperatureRainfall(0.6F, 0.7F);
    
        this.addWeight(BiomeType.WARM, 3);
        
        // flowers
        this.addGenerator("lavender", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(99).with(BOPFlowers.LAVENDER).create());

        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(1);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("jacaranda", 3, (new GeneratorBasicTree.Builder()).minHeight(4).maxHeight(7).log(BOPWoods.JACARANDA).leaves(BOPTrees.JACARANDA).create());
        treeGenerator.add("oak", 1, (new GeneratorBigTree.Builder()).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("tallgrass", 3, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
         
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create()); 
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 10601325;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 10601325;
    }
}
