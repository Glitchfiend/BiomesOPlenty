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
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorMangroveTree;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
 
public class BiomeGenMangrove extends BOPOverworldBiome
{
    public BiomeGenMangrove()
    {
        super("mangrove", new PropsBuilder("Mangrove").withTemperature(0.85F).withRainfall(0.3F).withGuiColour(7251289).withWaterColor(0xCDFF51));

        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(8, 2).octaves(0, 1, 2, 2, 1, 0);
        
        this.addWeight(BOPClimates.HOT_SWAMP, 7);
        
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.topBlock = BOPBlocks.mud.getDefaultState();
        this.fillerBlock = BOPBlocks.mud.getDefaultState();
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.spawnableCreatureList.clear();
        
        this.beachBiomeLocation = null;
        
        // quicksand
        this.addGenerator("quicksand", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).liquid(BOPBlocks.sand).frozenLiquid((IBlockState)null).create());
        
        // trees & logs
        IBlockPosQuery emptyMud = BlockQuery.buildAnd().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mangrove", 1, (new GeneratorMangroveTree.Builder()).placeOn(emptyMud).log(BOPWoods.MANGROVE).leaves(BOPTrees.MANGROVE).create());
        
        // gem
        this.addGenerator("sapphire", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.SAPPHIRE).create()); 
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        double d0 = GRASS_COLOR_NOISE.getValue((double)x * 0.25D, (double)z * 0.25D);

        if (d0 > 0.0D)
        {
            int i = x & 15;
            int j = z & 15;

            for (int k = 255; k >= 0; --k)
            {
                if (primer.getBlockState(j, k, i).getMaterial() != Material.AIR)
                {
                    if (k == 62 && primer.getBlockState(j, k, i).getBlock() != Blocks.WATER)
                    {
                        primer.setBlockState(j, k, i, WATER);
                    }

                    break;
                }
            }
        }

        this.generateBiomeTerrain(world, rand, primer, x, z, noise);
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("sapphire");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.isEnabled(GeneratorType.TREES)) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5);
        IBlockPosQuery emptyMud = BlockQuery.buildAnd().states(this.topBlock).create();
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mangrove", 1, (new GeneratorMangroveTree.Builder()).placeOn(emptyMud).log(BlockPlanks.EnumType.OAK).leaves(BlockPlanks.EnumType.OAK).create());
        }
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
}
