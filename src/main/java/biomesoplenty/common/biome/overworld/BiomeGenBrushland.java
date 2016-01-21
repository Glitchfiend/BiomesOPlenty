package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPSand;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorTwigletTree;

public class BiomeGenBrushland extends BOPBiome
{   
    
    public BiomeGenBrushland()
    {
        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(3, 20);

        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
        this.setColor(0xC9C17F);
        this.setTemperatureRainfall(1.2F, 0.1F);
        this.addWeight(BOPClimates.SAVANNA, 10);
        
        // quicksand
        this.addGenerator("quicksand_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(0.6F).splotchSize(16).with(BOPBlocks.sand.getDefaultState().withProperty(BlockBOPSand.VARIANT, BlockBOPSand.SandType.QUICKSAND)).splotchSize(20).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2.4F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("brush", 1, (new GeneratorBasicTree.Builder()).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.OAK).minHeight(3).maxHeight(5).leafLayers(2).create());
        treeGenerator.add("brush_twiglet", 2, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.OAK).create());        
        treeGenerator.add("brush_bush", 1, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.air).withNonDecayingLeaf(BlockPlanks.EnumType.OAK).generationAttempts(8).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("allium", 2, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.ALLIUM).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.4F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.SHRUB).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.THORN).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).with(BOPPlants.REED).generationAttempts(32).create());

        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("ruby");}
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xC9C17F;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xB2C67F;
    }
    
}