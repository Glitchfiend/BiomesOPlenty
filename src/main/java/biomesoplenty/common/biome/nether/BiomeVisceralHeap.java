/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorLakes;
import net.minecraft.block.state.IBlockState;

public class BiomeVisceralHeap extends BOPHellBiome
{
    
    public BiomeVisceralHeap()
    {
        super("visceral_heap", new PropsBuilder("Visceral Heap").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 75);
        
        this.hasBiomeEssence = false;

        this.topBlock = BOPBlocks.flesh.getDefaultState();
        this.fillerBlock = BOPBlocks.flesh.getDefaultState();
        this.wallBlock = BOPBlocks.flesh.getDefaultState();
        
        // blood pools
        this.addGenerator("blood_pools", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(2.0F).liquid(BOPBlocks.blood).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
    
        this.addGenerator("eyebulbs", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.7F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPDoublePlant.DoublePlantType.EYEBULB).create());
    }
}
