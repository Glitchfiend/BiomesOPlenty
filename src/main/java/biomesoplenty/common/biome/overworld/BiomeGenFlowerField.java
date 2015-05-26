/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenFlowerField extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.125F, 0.05F);
    
    public BiomeGenFlowerField()
    {
        this.setHeight(biomeHeight);
        this.setColor(4044093);
        this.setTemperatureRainfall(0.6F, 0.7F);

        this.addWeight(BiomeType.WARM, 3);
        
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(999);
        flowerGenerator.add("pink_tuilp", 2, new GeneratorFlora(1, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.PINK_TULIP)));
        flowerGenerator.add("white_tulip", 5, new GeneratorFlora(1, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.WHITE_TULIP)));
        flowerGenerator.add("orange_tulip", 7, new GeneratorFlora(1, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.ORANGE_TULIP)));
        flowerGenerator.add("red_tulip", 10, new GeneratorFlora(1, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.RED_TULIP)));
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(35);
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        grassGenerator.add("tallgrass", 2, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, BOPGems.PERIDOT), 12, 4, 32));
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 7390273;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 7390273;
    }
}
