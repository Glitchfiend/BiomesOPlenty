/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Handles its own generation when referred to by a
 * controller.
 */
public interface IGeneratorDelegate extends IGeneratorBase
{
    public void scatter(World world, Random random, BlockPos pos, int amountPerChunk);
    public boolean generate(World world, Random random, BlockPos pos, int amountPerChunk);
}
