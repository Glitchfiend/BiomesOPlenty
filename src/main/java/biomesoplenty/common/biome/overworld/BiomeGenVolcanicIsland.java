/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import net.minecraft.init.Blocks;
 
public class BiomeGenVolcanicIsland extends BOPBiome
{
    public BiomeGenVolcanicIsland()
    {
        super("volcanic_island", new PropsBuilder("Volcanic Island").withGuiColour(6645093).withTemperature(1.2F).withRainfall(0.2F));

        // terrain
        this.terrainSettings.avgHeight(120).heightVariation(50, 50).octaves(1, 1, 2, 2, 3, 2).sidewaysNoise(0.1D);
        
        this.topBlock = BOPBlocks.ash_block.getDefaultState();
        this.fillerBlock = BOPBlocks.ash_block.getDefaultState();
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.theBiomeDecorator.generateLakes = false;
        
        this.beachBiomeLocation = null;
        
        this.avgDirtDepth = 16;
        
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        
        clearWeights();
        
        // lava
        IBlockPosQuery emptyAshBlock = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("lava_flows", GeneratorStage.FLOWERS,(new GeneratorSplotches.Builder()).placeOn(emptyAshBlock).replace(BOPBlocks.ash_block).amountPerChunk(0.1F).splotchSize(12).scatterYMethod(ScatterYMethod.AT_SURFACE).with(Blocks.flowing_lava.getDefaultState()).create());
        this.addGenerator("lava_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(2.5F).lavaLake().create());
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
    }
}
