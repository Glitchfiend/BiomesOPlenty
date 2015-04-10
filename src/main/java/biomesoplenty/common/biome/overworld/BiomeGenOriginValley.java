/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPTreeEnums.AllTrees;
import biomesoplenty.common.block.BlockBOPFlower2;
import biomesoplenty.common.block.BlockBOPFlower2.FlowerType;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLeaves2;
import biomesoplenty.common.block.BlockBOPGrass.BOPGrassType;
import biomesoplenty.common.config.MiscConfigurationHandler;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;

public class BiomeGenOriginValley extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.3F);

    public BiomeGenOriginValley()
    {
        this.setHeight(biomeHeight);
        this.setColor(10341485);
        this.setTemperatureRainfall(0.7F, 0.8F);
        
        this.addWeight(BiomeType.WARM, 1);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BOPGrassType.ORIGIN);
    
        GeneratorBasicTree treeGenerator = new GeneratorBasicTree(4, false, 5, 8, Blocks.log.getDefaultState(), 
                BOPBlocks.leaves_2.getDefaultState().withProperty(BlockBOPLeaves.getVariantProperty(BlockBOPLeaves2.PAGENUM), AllTrees.ORIGIN));
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        
        GeneratorWeighted flowerGenerator = new GeneratorWeighted();
        flowerGenerator.add(8, new GeneratorFlora(4, BOPBlocks.flower2.getDefaultState().withProperty(BlockBOPFlower2.VARIANT, FlowerType.ROSE)));
        flowerGenerator.add(10, new GeneratorFlora(4, Blocks.yellow_flower.getDefaultState()));
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
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
    
    @Override
    public int getSkyColorByTemp(float temperature)
    {
        if (MiscConfigurationHandler.skyColors) 
            return 8441086;
        
       return super.getSkyColorByTemp(temperature);
    }
}
