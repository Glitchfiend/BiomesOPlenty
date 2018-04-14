/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorBramble;
import biomesoplenty.common.world.generator.GeneratorFlora;
import net.minecraft.init.Blocks;

public class BiomeCorruptedSands extends BOPHellBiome
{
    public BiomeCorruptedSands()
    {
        super("corrupted_sands", new PropsBuilder("Corrupted Sands").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 75);
        
        this.topBlock = Blocks.SOUL_SAND.getDefaultState();
        this.fillerBlock = Blocks.SOUL_SAND.getDefaultState();
        this.wallBlock = Blocks.SOUL_SAND.getDefaultState();
        
        this.hasBiomeEssence = false;
        
        IBlockPosQuery emptySoulsand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("bramble", GeneratorStage.FLOWERS,(new GeneratorBramble.Builder()).amountPerChunk(40.0F).placeOn(emptySoulsand).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        
        // splatter top blocks
        //this.addGenerator("netherrack_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(5.0F).generationAttempts(128).scatterYMethod(ScatterYMethod.NETHER_SURFACE).replace(emptySoulsand).with(Blocks.NETHERRACK.getDefaultState()).create());
        this.addGenerator("dead_grass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(5.0F).with(BOPPlants.DEADGRASS).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(3.0F).placeOn(emptySoulsand).with(BOPPlants.THORN).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
    }
}
