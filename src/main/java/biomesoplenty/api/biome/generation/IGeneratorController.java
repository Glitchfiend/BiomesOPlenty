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
 * Responsible for handling its own associated generation, or referring it
 * to delegate generators.
 */
public interface IGeneratorController extends IGeneratorBase
{
    public void scatter(World world, Random random, BlockPos pos);
    public boolean generate(World world, Random random, BlockPos pos);
    
    public void setName(String name);
    
    /**A unique name use to classify the purpose of a generator. For example, emeralds and ruby use the
     * same generator (and thus, have the same identifier) but have differing names.
     */
    public String getName();
}
