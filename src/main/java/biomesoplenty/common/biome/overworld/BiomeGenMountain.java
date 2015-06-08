/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorLogs;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;

public class BiomeGenMountain extends BOPBiome
{
    
    public IBlockState grassBlock;
    public IBlockState dirtBlock;
    public IBlockState coarseDirtBlock;
    public IBlockState stoneBlock;
        
    public BiomeGenMountain()
    {
        // terrain
        this.bopMinHeight = 40;
        this.bopMaxHeight = 200;
        this.sidewaysNoiseAmount = 0.2F;
        this.setOctaveWeights(1, 2, 2, 2, 3, 2);
        
        this.setColor(0x80A355);
        this.setTemperatureRainfall(0.5F, 0.1F);
        
        this.addWeight(BiomeType.WARM, 10);
        
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();
        this.grassBlock = this.topBlock;
        this.dirtBlock = this.fillerBlock;
        this.coarseDirtBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
        this.stoneBlock = Blocks.stone.getDefaultState();
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(6).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        this.addGenerator("gravel_patches", GeneratorStage.SAND_PASS2, (new GeneratorSplotches.Builder()).amountPerChunk(2).splotchSize(16).replace(this.topBlock).with(Blocks.gravel.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
      
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.8F).waterLakeForBiome(this).create());        
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(4);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        // TODO: need a generator for pine trees - or adjust existing ones
        treeGenerator.add("pine", 1, (new GeneratorBasicTree.Builder()).minHeight(4).maxHeight(17).log(BOPWoods.PINE).leaves(BOPTrees.PINE).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());

        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.SHRUB).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.2F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockTallGrass.EnumType.FERN).create());
      
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.REED).generationAttempts(32).create());
        
        // logs
        GeneratorWeighted logsGenerator = new GeneratorWeighted(1.5F);
        this.addGenerator("logs", GeneratorStage.TREE, logsGenerator);
        logsGenerator.add("pine_logs", 1, (new GeneratorLogs.Builder()).log(BOPWoods.PINE).create());
        logsGenerator.add("dead_logs", 1, (new GeneratorLogs.Builder()).log(BOPWoods.DEAD).create());
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
   
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        if (noise < -1.4D) 
        {
            this.topBlock = this.coarseDirtBlock;
            this.fillerBlock = this.coarseDirtBlock;
        }
        else if (noise > 1.7D)
        {
            this.topBlock = this.stoneBlock;
            this.fillerBlock = this.stoneBlock;
        }
        else
        {
            this.topBlock = this.grassBlock;
            this.fillerBlock = this.dirtBlock;
        }
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
    
}