/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorBigMushroom;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeVisceralHeap extends BOPHellBiome
{
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeVisceralHeap()
    {
        super("visceral_heap", new PropsBuilder("Visceral Heap").withGuiColour(0xA93C3E));

        this.addWeight(BOPClimates.HELL, 20);

        this.topBlock = BOPBlocks.flesh.getDefaultState();
        this.fillerBlock = BOPBlocks.flesh.getDefaultState();
        this.wallBlock = BOPBlocks.flesh.getDefaultState();
        
        // blood pools
        this.addGenerator("blood_pools", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(2.0F).waterLakeForBiome(this).liquid(BOPBlocks.blood).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
    
        this.addGenerator("eyebulbs", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.7F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPDoublePlant.DoublePlantType.EYEBULB).create());
    }
}
