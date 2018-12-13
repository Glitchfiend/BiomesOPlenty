/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.world.generator.GeneratorBramble;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import biomesoplenty.common.world.generator.tree.GeneratorBigTree;
import biomesoplenty.common.world.generator.tree.GeneratorTaigaTree;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenFossils;

public class BiomeGenOminousWoods extends BOPOverworldBiome
{    
    
    public BiomeGenOminousWoods()
    {
        super("ominous_woods", new PropsBuilder("Ominous Woods").withGuiColour(0x3F4151).withTemperature(0.6F).withRainfall(0.6F).withWaterColor(0x1E1B26));

        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(5, 10);

        this.skyColor = 0x384460;

        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        this.canSpawnInBiome = false;
        
        this.beachBiomeLocation = null;

        this.fogColor = 0x3B3D4C;
        this.fogDensity = 0.175F;

        this.addWeight(BOPClimates.COLD_SWAMP, 1);
        
        this.spawnableCreatureList.clear();

        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(11);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow_tree", 3, (new GeneratorBasicTree.Builder()).log(BOPWoods.WILLOW).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.WILLOW).withProperty(BlockOldLeaf.CHECK_DECAY, Boolean.valueOf(false))).minHeight(5).maxHeight(9).maxLeavesRadius(2).vine(BOPBlocks.willow_vine.getDefaultState()).create());
        treeGenerator.add("umbran_spruce", 4, (new GeneratorTaigaTree.Builder()).log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).maxHeight(20).create()); // TODO: implement pine cones
        treeGenerator.add("dead_tree", 2, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.AIR.getDefaultState()).create());
        treeGenerator.add("mega_umbran", 5, (new GeneratorTaigaTree.Builder()).log(BOPWoods.UMBRAN).leaves(BOPTrees.UMBRAN).minHeight(20).maxHeight(30).trunkWidth(2).create());
        treeGenerator.add("dying_tree", 1, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(1).log(BOPWoods.DEAD).leaves(BOPTrees.DEAD).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(3.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.4F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("deathbloom", 2, (new GeneratorFlora.Builder()).with(BOPFlowers.DEATHBLOOM).create());
        
        // other plants
        this.addGenerator("bramble", GeneratorStage.FLOWERS,(new GeneratorBramble.Builder()).maxHeight(4).minLength(5).maxLength(15).amountPerChunk(0.75F).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.THORN).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).placeOn(BlockQueries.fertile).with(BOPPlants.DEADLEAFPILE).create());

        // shrooms
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).generationAttempts(16).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).generationAttempts(16).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
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
        return getModdedBiomeGrassColor(0x43415B);
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return getModdedBiomeFoliageColor(0x5A4166);
    }
}
