/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorLogs;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;

public class BiomeGenMountain extends BOPBiome
{
    
    public static enum MountainType {PEAKS, FOOTHILLS}
    
    public MountainType type;
    public IBlockState grassBlock;
    public IBlockState dirtBlock;
    public IBlockState coarseDirtBlock;
    public IBlockState stoneBlock;
    public IBlockState snowBlock;
    public IBlockState packedSnowBlock;
        
    public BiomeGenMountain(MountainType type)
    {
        this.type = type;
        
        this.canSpawnInBiome = false;
        
        // terrain
        switch (type)
        {
            case PEAKS:
                this.terrainSettings.avgHeight(160).heightVariation(70, 70).octaves(1, 1, 2, 2, 3, 2).sidewaysNoise(0.22D); 
                break;
                
            case FOOTHILLS:
                this.terrainSettings.avgHeight(95).heightVariation(70, 70).minHeight(55).octaves(1, 1, 2, 2, 3, 2).sidewaysNoise(0.22D); 
                break;
        }
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.setColor(0x80A355);
        this.setTemperatureRainfall(0.5F, 0.1F);
        
        if (type == MountainType.PEAKS)
        {
            this.canGenerateVillages = false;
            
            // peaks are created in the biome gen layer, foothills don't have a weight - they only appear later around the peaks (in the biome edge layer)
            this.addWeight(BOPClimates.DRY_TEMPERATE, 7);
            this.addWeight(BOPClimates.COOL_TEMPERATE, 7);
            
            // only sheep and wolves on the peaks
            this.spawnableCreatureList.clear();
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 6));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 4, 4, 4));
            
            // make the water in the peaks a bit greener ( TODO: would like to make it lighter too, but I don't think it's possible with just a multiplication)
            this.waterColorMultiplier = 0x00ff99;
        }        
        
        this.topBlock = Blocks.grass.getDefaultState();
        this.fillerBlock = Blocks.dirt.getDefaultState();
        this.grassBlock = this.topBlock;
        this.dirtBlock = this.fillerBlock;
        this.coarseDirtBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
        this.stoneBlock = Blocks.stone.getDefaultState();
        this.snowBlock = Blocks.snow.getDefaultState();
        this.packedSnowBlock = BOPBlocks.hard_ice.getDefaultState();
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(6).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        this.addGenerator("gravel_patches", GeneratorStage.SAND_PASS2, (new GeneratorSplotches.Builder()).amountPerChunk(2).splotchSize(16).replace(this.topBlock).with(Blocks.gravel.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
      
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(1.8F).waterLakeForBiome(this).create());        
        
        // trees & logs        
        IBlockPosQuery suitableTreePosition = BlockQuery.buildAnd().withAltitudeBetween(40, 140).materials(Material.ground, Material.grass).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("pine", 1, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).log(BOPWoods.PINE).leaves(BOPTrees.PINE).placeOn(suitableTreePosition).create());        
        GeneratorWeighted logsGenerator = new GeneratorWeighted(2.5F);
        this.addGenerator("logs", GeneratorStage.TREE, logsGenerator);
        logsGenerator.add("pine_logs", 1, (new GeneratorLogs.Builder()).placeOn(suitableTreePosition).with(BOPWoods.PINE).create());
        logsGenerator.add("dead_logs", 1, (new GeneratorLogs.Builder()).placeOn(suitableTreePosition).with(BOPWoods.DEAD).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).generationAttempts(128).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).generationAttempts(128).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).generationAttempts(128).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).generationAttempts(128).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).generationAttempts(128).create());

        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.SHRUB).generationAttempts(type == MountainType.FOOTHILLS ? 64 : 32).create());
        this.addGenerator("ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BlockTallGrass.EnumType.FERN).generationAttempts(type == MountainType.FOOTHILLS ? 64 : 32).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.2F).with(BOPPlants.DEADLEAFPILE).create());
        
        if (type == MountainType.FOOTHILLS)
        {
            this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
            this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.BERRYBUSH).create());
            this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.CLOVERPATCH).create());
            this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.REED).generationAttempts(32).create());
            this.addGenerator("pumpkins", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).with(Blocks.pumpkin.getDefaultState()).create());
        }
        
        // TODO: some flowers?
                
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
   
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.grassBlock = this.topBlock;
        this.dirtBlock = this.fillerBlock;        
        this.coarseDirtBlock = conf.getBlockState("coarseDirtBlock", this.coarseDirtBlock);
        this.stoneBlock = conf.getBlockState("stoneBlock", this.stoneBlock);
        //this.snowBlock = conf.getBlockState("snowBlock", this.snowBlock);
        //this.packedSnowBlock = conf.getBlockState("packedSnowBlock", this.packedSnowBlock);
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("emeralds");}
    }
    
    
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        int localX = x & 15;
        int localZ = z & 15;
        int height = 255;
        while (height > 0 && primer.getBlockState(localX, height, localZ).getBlock().getMaterial() == Material.air) {height--;}
        int snowLine = 160 + (int)(noise * 5);
        
        if (height > snowLine)
        {
            if (noise > 1.7D)
            {
                this.topBlock = this.stoneBlock;
                this.fillerBlock = this.stoneBlock;
            }
            else
            {
                this.topBlock = this.grassBlock;
                this.fillerBlock = this.dirtBlock;
            }
        }
        else
        {
            if (noise < -1.4D) 
            {
                this.topBlock = this.coarseDirtBlock;
                this.fillerBlock = this.coarseDirtBlock;
            }
            else if (this.type == MountainType.PEAKS && noise > 1.7D)
            {
                this.topBlock = this.stoneBlock;
                this.fillerBlock = this.stoneBlock;
            }
            else
            {
                this.topBlock = this.grassBlock;
                this.fillerBlock = this.dirtBlock;
            }
        }
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
    
}