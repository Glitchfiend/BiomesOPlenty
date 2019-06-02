/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class GraniteSurfaceBuilder implements ISurfaceBuilder<SurfaceBuilderConfig> {
	   public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, IBlockState defaultBlock, IBlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
	      if (noise > 1.9D) {
	         Biome.DEFAULT_SURFACE_BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, BOPBiomeFeatures.GRANITE_SURFACE);
	      } else {
	         Biome.DEFAULT_SURFACE_BUILDER.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, Biome.GRASS_DIRT_GRAVEL_SURFACE);
	      }

	   }
	}