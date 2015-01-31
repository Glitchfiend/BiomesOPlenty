/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import com.google.gson.JsonElement;

public interface IGenerator<T>
{
    public void generate(World world, Random random, BlockPos pos);
    
    public JsonElement serialize(IGenerator<T> src);

    public IGenerator<T> deserialize(JsonElement json);
}
