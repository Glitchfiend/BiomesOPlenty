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
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQueryUtils.*;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
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
        
        // terrain
        switch (type)
        {
            case PEAKS:
                this.bopMinHeight = 90;
                this.bopMaxHeight = 230;
                break;
            case FOOTHILLS:
                this.bopMinHeight = 30;
                this.bopMaxHeight = 160;
                break;
        }
        this.sidewaysNoiseAmount = 0.22F;
        this.setOctaveWeights(1, 1, 2, 2, 3, 2);
        
        this.setColor(0x80A355);
        this.setTemperatureRainfall(0.25F, 0.1F);
        
        if (type == MountainType.PEAKS)
        {
            // peaks are created in the biome gen layer, foothills don't have a weight - they only appear later around the peaks (in the biome edge layer)
            this.addWeight(BiomeType.COOL, 10);
            
            // only sheep and wolves on the peaks
            this.spawnableCreatureList.clear();
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 6));
            this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 4, 4, 4));
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
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.8F).waterLakeForBiome(this).create());        
        
        // trees
        IBlockPosQuery suitableTreePosition = new BlockPosQueryAnd(new BlockPosQueryAltitude(40, 140), new BlockPosQueryOr(new BlockQueryMaterial(Material.ground), new BlockQueryMaterial(Material.grass)));
        GeneratorWeighted treeGenerator = new GeneratorWeighted(6);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("pine", 1, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).placeOn(suitableTreePosition).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).generationAttempts(128).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).generationAttempts(128).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).generationAttempts(128).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).generationAttempts(128).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).generationAttempts(128).create());

        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.SHRUB).generationAttempts(64).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.2F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BlockTallGrass.EnumType.FERN).generationAttempts(64).create());
      
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
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.grassBlock = this.topBlock;
        this.dirtBlock = this.fillerBlock;        
        this.coarseDirtBlock = conf.getBlockState("coarseDirtBlock", this.coarseDirtBlock);
        this.stoneBlock = conf.getBlockState("stoneBlock", this.stoneBlock);
        this.snowBlock = conf.getBlockState("snowBlock", this.snowBlock);
        this.packedSnowBlock = conf.getBlockState("packedSnowBlock", this.packedSnowBlock);
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
                this.topBlock = this.snowBlock;
                this.fillerBlock = this.packedSnowBlock;
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