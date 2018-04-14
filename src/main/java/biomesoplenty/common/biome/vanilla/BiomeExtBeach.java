/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.vanilla;

import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

public class BiomeExtBeach extends ExtendedBiomeWrapper
{
    public BiomeExtBeach()
    {
        super(Biomes.BEACH);

        this.addGenerator("sea_oats", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(6.0F).placeOn(Blocks.SAND.getDefaultState()).with(BlockBOPDoublePlant.DoublePlantType.SEA_OATS).generationAttempts(96).create());
    }
}

