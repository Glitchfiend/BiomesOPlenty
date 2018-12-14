/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPFlatPlant;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorProfileTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;

public class BiomeGenGrove extends BOPOverworldBiome
{
    
    public BiomeGenGrove()
    {
        super("grove", new PropsBuilder("Grove").withGuiColour(0x517F51).withTemperature(0.75F).withRainfall(0.3F));

        // terrain
        this.terrainSettings.avgHeight(85).heightVariation(20, 20);
        
        this.addWeight(BOPClimates.MEDITERRANEAN, 10);
        
        this.canGenerateVillages = true;
        
        this.beachBiomeLocation = null;
                 
        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPFoliage.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPFoliage.BUSH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).placeOn(BlockQueries.fertile).with(BlockBOPFlatPlant.PlantType.LEAFPILE).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_tree", 1, (new GeneratorBigTree.Builder()).minHeight(11).maxHeight(15).foliageHeight(2).create());
        treeGenerator.add("dark_poplar", 5, (new GeneratorProfileTree.Builder()).minHeight(9).maxHeight(15).log(BlockPlanks.EnumType.DARK_OAK).leaves(BlockPlanks.EnumType.DARK_OAK).profile(GeneratorProfileTree.TreeProfile.POPLAR).create());
        treeGenerator.add("poplar", 5, (new GeneratorProfileTree.Builder()).minHeight(9).maxHeight(18).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).profile(GeneratorProfileTree.TreeProfile.POPLAR).create());
        treeGenerator.add("bush", 8, (new GeneratorBush.Builder()).maxHeight(2).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.75F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("syringa", 3, (new GeneratorDoubleFlora.Builder().with(BlockDoublePlant.EnumPlantType.SYRINGA).create()));
        flowerGenerator.add("paeonias", 2, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.PAEONIA).create());
        flowerGenerator.add("oxeye_daisy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("dandelion", 2, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 3, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.5F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
    }
}
