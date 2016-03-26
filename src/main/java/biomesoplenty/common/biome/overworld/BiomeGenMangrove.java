/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPSand;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBayouTree;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
 
public class BiomeGenMangrove extends BOPBiome
{
	public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeGenMangrove()
    {
        super("mangrove", new PropsBuilder("Mangrove").withTemperature(0.8F).withRainfall(0.3F).withGuiColour(7251289).withWaterColor(0xCDFF51));

        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(2, 4).octaves(0, 1, 2, 2, 1, 0);
        
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.topBlock = BOPBlocks.mud.getDefaultState();
        this.fillerBlock = BOPBlocks.mud.getDefaultState();
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = Blocks.sand.getDefaultState();
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.spawnableCreatureList.clear();
        
        this.beachBiomeLocation = null;
        
        clearWeights();
        
        // quicksand
        this.addGenerator("quicksand", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).waterLakeForBiome(this).liquid(BOPBlocks.sand.getDefaultState().withProperty(BlockBOPSand.VARIANT, BlockBOPSand.SandType.QUICKSAND)).frozenLiquid((IBlockState)null).create());
        
        // trees & logs
        IBlockPosQuery emptySandMud = BlockQuery.buildAnd().states(this.usualTopBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mangrove", 1, (new GeneratorBayouTree.Builder()).placeOn(emptySandMud).log(BOPWoods.MANGROVE).leaves(BOPTrees.MANGROVE).minHeight(6).maxHeight(8).minLeavesRadius(1).vine(null).leavesGradient(1).create());
        
        // gem
        this.addGenerator("sapphire", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.SAPPHIRE).create()); 
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
        double d0 = GRASS_COLOR_NOISE.func_151601_a((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (primer.getBlockState(j, k, i).getMaterial() != Material.air)
                {
                    if (k == 62 && primer.getBlockState(j, k, i).getBlock() != Blocks.water)
                    {
                        primer.setBlockState(j, k, i, WATER);
                    }

                    break;
                }
            }
        }
        
        this.topBlock = (noise + rand.nextDouble() * 1.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;

        this.generateBiomeTerrain(world, rand, primer, x, z, noise);
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("sapphire");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
}
