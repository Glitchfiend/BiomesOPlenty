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
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomePhantasmagoricInferno extends BOPHellBiome
{
    public BiomePhantasmagoricInferno()
    {
        super("phantasmagoric_inferno", new PropsBuilder("Phantasmagoric Inferno").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 75);
        
        this.hasBiomeEssence = false;
        
        this.topBlock = BOPBlocks.ash_block.getDefaultState();
        this.fillerBlock = BOPBlocks.ash_block.getDefaultState();
        
        // lava lakes
        this.addGenerator("lava_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(3.0F).lineWith(BOPBlocks.ash_block.getDefaultState()).liquid(Blocks.LAVA).frozenLiquid((IBlockState)null).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        
        // splatter top blocks
        IBlockPosQuery emptyAsh = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();

        this.addGenerator("fire", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(6.0F).with(Blocks.FIRE.getDefaultState()).placeOn(emptyAsh).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.NETHER_HIVES)) {this.removeGenerator("hive");}
    }
}
