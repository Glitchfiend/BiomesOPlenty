/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPGrass.BOPGrassType;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;

public class BiomeGenOriginValley extends BOPBiome
{
    public BiomeGenOriginValley()
    {
        // terrain
        this.bopMinHeight = 57;
        this.bopMaxHeight = 91;
        this.sidewaysNoiseAmount = 0.0D;
        
        this.setColor(10341485);
        this.setTemperatureRainfall(0.7F, 0.8F);
        this.skyColor = 8441086;
        
        this.addWeight(BiomeType.WARM, 1);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BOPGrassType.ORIGIN);

        // trees
        this.addGenerator("trees", GeneratorStage.TREE, (new GeneratorBasicTree.Builder()).amountPerChunk(4).minHeight(5).maxHeight(8).leaves(BOPTrees.ORIGIN).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.4F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 8, (new GeneratorFlora.Builder().flora(BOPFlowers.ROSE).create()));
        flowerGenerator.add("yellow_flower", 10, (new GeneratorFlora.Builder().flora(EnumFlowerType.DANDELION).create()));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 10682207;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 3866368;
    }
    
}
