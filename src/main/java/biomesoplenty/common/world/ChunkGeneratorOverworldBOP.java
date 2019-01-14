/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.OverworldGenSettings;

public class ChunkGeneratorOverworldBOP extends ChunkGeneratorOverworld
{
    public ChunkGeneratorOverworldBOP(IWorld world, BiomeProvider provider, OverworldGenSettings settings)
    {
        super(world, provider, settings);
    }
}
