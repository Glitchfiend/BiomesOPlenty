/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.decoration.extensions;

import net.minecraft.world.gen.feature.WorldGenCactus;
import biomesoplenty.api.biome.IGenerator;

public interface IExtendedCactusGen extends IGenerator<WorldGenCactus>
{
	public void setCactiPerChunk(int amount);
	public int getCactiPerChunk();
}
