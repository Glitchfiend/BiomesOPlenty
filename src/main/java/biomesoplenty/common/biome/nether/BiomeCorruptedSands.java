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
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomeCorruptedSands extends BOPHellBiome
{
    public BiomeCorruptedSands()
    {
        super("corrupted_sands", new PropsBuilder("Corrupted Sands").withGuiColour(0xA93C3E));

        this.addWeight(BOPClimates.HELL, 20);
        
        // splatter top blocks
        IBlockPosQuery emptyNetherrack = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("soulsand_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(0.7F).generationAttempts(128).replace(emptyNetherrack).with(Blocks.SOUL_SAND.getDefaultState()).create());
    
        this.addGenerator("thorns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.THORN).create());
    }
}
