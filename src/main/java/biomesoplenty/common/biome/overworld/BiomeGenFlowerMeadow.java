/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;

public class BiomeGenFlowerMeadow extends BOPOverworldBiome
{    
    public BiomeGenFlowerMeadow()
    {
        super("flower_meadow", new PropsBuilder("Flower Meadow").withGuiColour(0x75D17F).withTemperature(0.4F).withRainfall(0.7F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 5).octaves(0, 1, 2, 2, 1, 0).sidewaysNoise(0.1D);

        this.clearWeights();
        
        this.canGenerateVillages = false;
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
        	this.beachBiomeLocation = ((BOPOverworldBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("spruce", 1, (new GeneratorTaigaTree.Builder()).maxHeight(13).create());
       
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(20.0F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("pink_tulip", 6, (new GeneratorFlora.Builder().with(EnumFlowerType.PINK_TULIP).create()));
        flowerGenerator.add("white_tulip", 9, (new GeneratorFlora.Builder().with(EnumFlowerType.WHITE_TULIP).create()));
        flowerGenerator.add("orange_tulip", 11, (new GeneratorFlora.Builder().with(EnumFlowerType.ORANGE_TULIP).create()));
        flowerGenerator.add("red_tulip", 14, (new GeneratorFlora.Builder().with(EnumFlowerType.RED_TULIP).create()));
        flowerGenerator.add("oxeye_daisy", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("dandelion", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("houstonia", 2, (new GeneratorFlora.Builder().with(EnumFlowerType.HOUSTONIA).create()));
        flowerGenerator.add("rose", 3, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).with(BlockDoublePlant.EnumPlantType.ROSE).create());
        flowerGenerator.add("syringa", 1, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).with(BlockDoublePlant.EnumPlantType.SYRINGA).create());
        flowerGenerator.add("sunflower", 2, (new GeneratorDoubleFlora.Builder().with(BlockDoublePlant.EnumPlantType.SUNFLOWER).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(12.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // other plants
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPFoliage.BUSH).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0x63B26D);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x63B26D);
    }
}
