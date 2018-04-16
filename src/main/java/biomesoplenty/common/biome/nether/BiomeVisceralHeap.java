/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorLakes;
import net.minecraft.block.BlockBone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

public class BiomeVisceralHeap extends BOPHellBiome
{
    
    public BiomeVisceralHeap()
    {
        super("visceral_heap", new PropsBuilder("Visceral Heap").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 5);
        
        this.hasBiomeEssence = false;

        this.topBlock = BOPBlocks.flesh.getDefaultState();
        this.fillerBlock = BOPBlocks.flesh.getDefaultState();
        this.wallBlock = BOPBlocks.flesh.getDefaultState();
        
        // blood pools
        this.addGenerator("blood_pools", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(6.0F).liquid(BOPBlocks.blood).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
    
        IBlockPosQuery emptyFlesh = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("bones", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.5F).placeOn(emptyFlesh).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(Blocks.BONE_BLOCK.getDefaultState().withProperty(BlockBone.AXIS, EnumFacing.Axis.Y)).minHeight(1).maxHeight(4).create());
        this.addGenerator("eyebulbs", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).generationAttempts(16).amountPerChunk(1.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPDoublePlant.DoublePlantType.EYEBULB).create());
    }
}
