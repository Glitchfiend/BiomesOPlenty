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
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorCrystals;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomePolarChasm extends BOPHellBiome
{
    public BiomePolarChasm()
    {
        super("polar_chasm", new PropsBuilder("Polar Chasm").withGuiColour(0xA93C3E).withTemperature(0.25F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 1);

        this.fogColor = 0x86C9EF;
        this.fogDensity = 0.4F;
        
        this.hasBiomeEssence = false;

        this.topBlock = BOPBlocks.hard_ice.getDefaultState();
        this.fillerBlock = BOPBlocks.hard_ice.getDefaultState();
        this.roofTopBlock = BOPBlocks.hard_ice.getDefaultState();
        this.roofFillerBlock = BOPBlocks.hard_ice.getDefaultState();
        this.wallBlock = BOPBlocks.hard_ice.getDefaultState();
        
        // ice pools
        this.addGenerator("ice_pools", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(5.0F).liquid(Blocks.ICE.getDefaultState()).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        
        // hot springs
        this.addGenerator("hot_springs", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(8.0F).liquid(BOPBlocks.hot_spring_water).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        
        //ice crystals
        IBlockPosQuery emptyHardIce = BlockQuery.buildAnd().withAltitudeBetween(60, 120).withAirBelow().states(BOPBlocks.hard_ice.getDefaultState()).create();
        this.addGenerator("ice_crystals", GeneratorStage.ORE_PRE, (new GeneratorCrystals.Builder()).amountPerChunk(50.0F).placeOn(emptyHardIce).with(Blocks.ICE.getDefaultState()).create());
        this.addGenerator("glowstone_crystals", GeneratorStage.ORE_PRE, (new GeneratorCrystals.Builder()).amountPerChunk(20.0F).placeOn(emptyHardIce).with(Blocks.GLOWSTONE.getDefaultState()).create());
        
        // splatter top blocks
        IBlockPosQuery emptySurface = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("snow_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(7.0F).generationAttempts(128).scatterYMethod(ScatterYMethod.NETHER_SURFACE).replace(emptySurface).with(Blocks.SNOW.getDefaultState()).create());
    }
}
