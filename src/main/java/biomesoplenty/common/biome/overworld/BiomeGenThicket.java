/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenThicket extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
    public BiomeGenThicket()
    {
        this.setHeight(biomeHeight);
        this.setColor(7248193);
        this.setTemperatureRainfall(0.6F, 0.2F);
        
        this.addWeight(BiomeType.COOL, 5);
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(17);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak", 1, (new GeneratorBasicTree.Builder()).create());
        treeGenerator.add("oak_bush", 4, (new GeneratorBush.Builder()).create());
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(6).maxRadius(7).to(Blocks.gravel.getDefaultState()).create());

        // other plants
        this.addGenerator("flowers", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).flora(EnumFlowerType.POPPY).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(5.5F).flora(BOPPlants.THORN).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).flora(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).flora(BOPPlants.LEAFPILE).generationAttempts(32).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).flora(BOPPlants.DEADLEAFPILE).generationAttempts(32).create());

        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).gemOre(BOPGems.AMBER).create());
        
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 11049591;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 10854765;
    }
}
