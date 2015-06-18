package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
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
            this.bopMinHeight = 144;
            this.bopMaxHeight = 158;
            
            this.addWeight(BiomeType.DESERT, 5);    
        }
        else
        {
            this.bopMinHeight = 50;
            this.bopMaxHeight = 94;
        }

        this.setColor(0xB49C70);
        this.setTemperatureRainfall(1.0F, 0.3F);
        

        this.spawnableCreatureList.clear();

        this.topBlock = BOPBlocks.hard_dirt.getDefaultState();
        this.fillerBlock = BOPBlocks.hard_dirt.getDefaultState();
        
        // splatter top blocks
        IBlockPosQuery emptyHardDirt = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("grass_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.0F).generationAttempts(128).replace(emptyHardDirt).with(Blocks.grass.getDefaultState()).create());
        
        // trees and logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("pine", 1, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).placeOn(emptyHardDirt).create());        
        treeGenerator.add("brush", 2, (new GeneratorBush.Builder()).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).placeOn(emptyHardDirt).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("bromeliad", 2, (new GeneratorFlora.Builder().with(BOPFlowers.BROMELIAD).create()));
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.REED).generationAttempts(32).create());

        // grasses (note weighting must be quite high as the grasses will only grow on the splattered grass blocks)
        GeneratorWeighted grassGenerator = new GeneratorWeighted(12.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
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
        return 0xA9BA64;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xA9BA64;
    }
}
