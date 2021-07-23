/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.Random;

public class ColdDesertSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderBaseConfiguration>
{
	public ColdDesertSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> deserializer)
	{
		super(deserializer);
	}

	@Override
	public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int startY, long seed, SurfaceBuilderBaseConfiguration config)
	{
		if (noise > 2.6D)
		{
			SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, startY, seed, BOPSurfaceBuilders.POWDER_SNOW_SURFACE);
		}
		else
		{
			SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, startY, seed, SurfaceBuilder.CONFIG_GRAVEL);
		}
	}
}