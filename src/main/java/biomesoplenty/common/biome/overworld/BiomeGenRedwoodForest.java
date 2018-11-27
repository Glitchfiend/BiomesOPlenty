package biomesoplenty.common.biome.overworld;

import java.util.Random;

import com.google.common.base.CaseFormat;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorRedwoodTree;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenRedwoodForest extends BOPOverworldBiome
{
	public enum ForestType {REDWOOD_FOREST, REDWOOD_FOREST_EDGE}
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public ForestType type;
	
    public BiomeGenRedwoodForest(ForestType type)
    {
        super(type.name().toLowerCase(), new PropsBuilder(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, type.toString())).withGuiColour(0x6DAA3C).withTemperature(0.7F).withRainfall(0.7F));

        this.type = type;
        
        // terrain
        this.terrainSettings.avgHeight(70).heightVariation(4, 10);
        
        this.topBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL);
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = this.topBlock;
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        if (type == ForestType.REDWOOD_FOREST)
        {
        	this.addWeight(BOPClimates.WARM_TEMPERATE, 7);
        	
        	//trees
        	GeneratorWeighted treeGenerator = new GeneratorWeighted(15.0F);
            this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
            treeGenerator.add("redwood", 6, (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(45).maxHeight(60).trunkWidth(3).create());
            treeGenerator.add("redwood_medium", 4, (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(25).maxHeight(40).trunkWidth(2).create());
            treeGenerator.add("redwood_small", 2, (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(10).maxHeight(30).trunkWidth(1).create());
            treeGenerator.add("oak_bush", 1, (new GeneratorBush.Builder()).amountPerChunk(3).maxHeight(2).create());
            treeGenerator.add("oak_tree", 1, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(4).maxHeight(10).foliageHeight(1).create());
            
            this.addGenerator("double_fern", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(5.0F).with(BlockDoublePlant.EnumPlantType.FERN).create());
        }
	        	
        if (type == ForestType.REDWOOD_FOREST_EDGE)
        {
        	this.hasBiomeEssence = false;
        	this.alternateTopBlock = Blocks.GRASS.getDefaultState();
        	
            // trees
        	GeneratorWeighted treeGenerator = new GeneratorWeighted(8.0F);
            this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
            treeGenerator.add("redwood_small", 6, (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(10).maxHeight(30).trunkWidth(1).create());
            treeGenerator.add("redwood_medium", 1, (new GeneratorRedwoodTree.Builder()).log(BOPWoods.REDWOOD).leaves(BOPTrees.REDWOOD).minHeight(25).maxHeight(40).trunkWidth(2).create());
            treeGenerator.add("oak_bush", 2, (new GeneratorBush.Builder()).amountPerChunk(3).maxHeight(2).create());
            treeGenerator.add("oak_tree", 1, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(4).maxHeight(10).foliageHeight(1).create());
        }
        
        // other plants
        //this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).placeOn(BlockQueries.fertile).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).placeOn(BlockQueries.fertile).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BOPPlants.SPROUT).create());
        
        // shrooms
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("fern", 8, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).generationAttempts(128).create());
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
    	this.topBlock = (noise + rand.nextDouble() * 3.0D > 1.5D) ? this.alternateTopBlock : this.usualTopBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
}
