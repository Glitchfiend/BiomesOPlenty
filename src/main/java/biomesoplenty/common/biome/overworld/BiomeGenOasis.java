/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPFoliage;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorPalmTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;
 
public class BiomeGenOasis extends BOPOverworldBiome
{
    public BiomeGenOasis()
    {
        super("oasis", new PropsBuilder("Oasis").withGuiColour(7712283).withTemperature(2.0F).withRainfall(0.5F).withRainDisabled());

        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(5, 7);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        this.hasBiomeEssence = false;
        
        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 4, 2, 3));
        
        clearWeights();
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("palm", 3, (new GeneratorPalmTree.Builder()).placeOn(BlockQueries.litSand).log(BOPWoods.PALM).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.PALM).withProperty(BlockOldLeaf.DECAYABLE, Boolean.valueOf(false))).create());
        treeGenerator.add("jungle_twiglet", 2, (new GeneratorTwigletTree.Builder()).placeOn(BlockQueries.litSand).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).trunkFruit(Blocks.COCOA.getDefaultState()).create());
        
        // other plants
        this.addGenerator("dunegrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(10.0F).with(BOPFoliage.DUNEGRASS).placeOn(BlockQueries.litDry).generationAttempts(8).create());
        this.addGenerator("desert_sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(5.0F).with(BOPFoliage.DESERTSPROUTS).generationAttempts(8).create());
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(1.0F).with(BOPFoliage.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.TINYCACTUS).generationAttempts(8).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(2.0F).generationAttempts(24).placeOn(BlockQueries.litDryWaterside).with(Blocks.REEDS.getDefaultState()).minHeight(1).maxHeight(3).create());
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
}
