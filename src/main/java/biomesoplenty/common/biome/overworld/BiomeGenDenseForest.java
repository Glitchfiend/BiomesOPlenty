/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class BiomeGenDenseForest extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.075F, 0.05F);
    
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeGenDenseForest()
    {
        this.setHeight(biomeHeight);
        this.setColor(8246897);
        this.setTemperatureRainfall(0.7F, 0.7F);
        
        this.addWeight(BiomeType.WARM, 7);
        
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
        
        // other plants
        this.addGenerator("bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1).flora(BOPPlants.BUSH).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1).flora(BOPPlants.BERRYBUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(1).flora(BOPPlants.SHRUB).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2).flora(BOPPlants.REED).generationAttempts(128).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(15).flora(BOPPlants.LEAFPILE).generationAttempts(256).create());        
        this.addGenerator("huge_trees", GeneratorStage.TREE, (new GeneratorBigTree.Builder()).amountPerChunk(10).minHeight(15).maxHeight(25).create());
        this.addGenerator("leaves_clusters", GeneratorStage.POST, (new GeneratorBush.Builder()).amountPerChunk(7).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).grass(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 1, (new GeneratorGrass.Builder()).grass(BlockTallGrass.EnumType.FERN).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.DAMPGRASS).create());
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).gemOre(BOPGems.AMBER).create());
         
    }

    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
    }
    
    @Override
    public void genTerrainBlocks(World world, Random random, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise > 1.75D) ? this.alternateTopBlock : this.usualTopBlock;
        this.generateBiomeTerrain(world, random, primer, x, z, noise);
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 8246897;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 10022742;
    }
}
