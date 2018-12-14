package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenBrushland extends BOPOverworldBiome
{   
    
    public BiomeGenBrushland()
    {
        super("brushland", new PropsBuilder("Brushland").withGuiColour(0xC6C19B).withTemperature(1.5F).withRainfall(0.1F).withRainDisabled());
        
        // terrain
        this.terrainSettings.avgHeight(65).heightVariation(2, 9);

        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY);
        
        this.addWeight(BOPClimates.HOT_DESERT, 3);
        
        this.canGenerateVillages = true;
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        
        // splatter top blocks
        IBlockPosQuery emptyGrass = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("dirt_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(4.75F).generationAttempts(128).replace(emptyGrass).with(BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.SANDY).withProperty(BlockBOPDirt.COARSE, true)).create());
        
        //sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.SAND.getDefaultState()).create());

        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).minHeight(7).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("brush_twiglet", 18, (new GeneratorTwigletTree.Builder()).minHeight(1).maxHeight(4).log(BlockPlanks.EnumType.ACACIA).leaves(BOPTrees.DEAD).create());        
        treeGenerator.add("decaying_tree", 3, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).log(BOPWoods.EBONY).leaves(BOPTrees.EBONY).minHeight(4).maxHeight(11).foliageHeight(1).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 3, (new GeneratorGrass.Builder()).with(BOPFoliage.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // other plants
        this.addGenerator("cacti", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.2F).generationAttempts(24).placeOn(this.topBlock).with(Blocks.CACTUS.getDefaultState()).minHeight(1).maxHeight(2).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(2.0F).with(BOPFoliage.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("desert_sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BOPFoliage.DESERTSPROUTS).generationAttempts(8).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.THORN).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1.75F).with(Blocks.DEADBUSH.getDefaultState()).create());
    }
    
    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos)
    {
        super.decorate(worldIn, rand, pos);

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, pos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FOSSIL))
        if (rand.nextInt(64) == 0)
        {
            (new WorldGenFossils()).generate(worldIn, rand, pos);
        }
    }

    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return getModdedBiomeGrassColor(0xE5DFA9);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        	return getModdedBiomeFoliageColor(0xDAE0B3);
    }
}