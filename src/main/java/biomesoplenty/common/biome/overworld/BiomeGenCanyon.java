package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplatter;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;

public class BiomeGenCanyon extends BOPBiome
{
    
    public static enum CanyonType {PLATEAU, RAVINE}

    // TODO: placement of ravine is not ideal - out to be tied to the rivers layer somehow, instead of being a subbiome
    public BiomeGenCanyon(CanyonType type)
    {
        // terrain
        if (type == CanyonType.PLATEAU)
        {
            this.terrainSettings.avgHeight(140).heightVariation(10, 10);
            
            this.addWeight(BOPClimates.SAVANNA, 3);    
        }
        else
        {
            this.terrainSettings.avgHeight(63).heightVariation(9, 40);
        }

        this.setColor(0xB49C70);
        this.setTemperatureRainfall(1.2F, 0.3F);
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeId = -1;

        this.spawnableCreatureList.clear();

        this.topBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY).withProperty(BlockBOPDirt.COARSE, true);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY).withProperty(BlockBOPDirt.COARSE, true);
        
        // splatter top blocks
        IBlockPosQuery emptyCoarseDirt = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("grass_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.0F).generationAttempts(128).replace(emptyCoarseDirt).with(Blocks.grass.getDefaultState()).create());
        
        // trees and logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("pine", 1, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).placeOn(emptyCoarseDirt).create());        
        treeGenerator.add("brush", 2, (new GeneratorBush.Builder()).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).placeOn(emptyCoarseDirt).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("bromeliad", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BROMELIAD).create()));
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.REED).generationAttempts(32).create());

        // grasses (note weighting must be quite high as the grasses will only grow on the splattered grass blocks)
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("caveweed"); this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        if (!settings.generateBopFlowers) {this.removeGenerator("flowers");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xA9BA64;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xA9BA64;
    }
}
