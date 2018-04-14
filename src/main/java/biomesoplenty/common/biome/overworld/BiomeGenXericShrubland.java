/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenXericShrubland extends BOPOverworldBiome
{
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
	
    public BiomeGenXericShrubland()
    {
        super("xeric_shrubland", new PropsBuilder("Xeric Shrubland").withGuiColour(0xE2CDA5).withTemperature(1.75F).withRainfall(0.1F).withRainDisabled());

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(1, 5);

        this.topBlock = Blocks.SAND.getDefaultState();
        this.fillerBlock = Blocks.SAND.getDefaultState();
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = Blocks.GRAVEL.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = true;
        
        this.addWeight(BOPClimates.HOT_DESERT, 3);
        
        this.spawnableCreatureList.clear();
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);        
        treeGenerator.add("brush_twiglet", 2, (new GeneratorTwigletTree.Builder()).placeOn(BlockQueries.litSand).minHeight(1).maxHeight(2).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).create());        
        treeGenerator.add("brush_bush", 3, (new GeneratorFlora.Builder()).placeOn(BlockQueries.litSand).replace(Material.AIR).withNonDecayingLeaf(BlockPlanks.EnumType.ACACIA).create());
        treeGenerator.add("oak_bush", 1, (new GeneratorBush.Builder()).placeOn(BlockQueries.litSand).maxHeight(2).create());
        
        // other plants
        this.addGenerator("dunegrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(15.0F).with(BOPPlants.DUNEGRASS).placeOn(this.topBlock).generationAttempts(8).create());
        this.addGenerator("desertgrass", GeneratorStage.GRASS, (new GeneratorGrass.Builder()).amountPerChunk(4.0F).with(BOPPlants.DESERTGRASS).generationAttempts(8).create());
        this.addGenerator("desert_sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.DESERTSPROUTS).generationAttempts(8).create());
        this.addGenerator("bromeliad", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder().amountPerChunk(0.5F).with(BOPFlowers.BROMELIAD).generationAttempts(8).create()));
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(Blocks.DEADBUSH.getDefaultState()).create());
        this.addGenerator("cacti", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.5F).generationAttempts(3).placeOn(this.topBlock).with(Blocks.CACTUS.getDefaultState()).minHeight(1).maxHeight(2).create());
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());    
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
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xD4E0A6;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xD4E0A6;
    }
}
