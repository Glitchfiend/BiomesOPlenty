/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.ValleySurfaceBuilder;

import java.util.Random;

public class WitheredAbyssSurfaceBuilder extends ValleySurfaceBuilder {
	private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
	private static final BlockState OBSIDIAN = Blocks.OBSIDIAN.defaultBlockState();
	private static final ImmutableList<BlockState> BLOCK_STATES = ImmutableList.of(BLACKSTONE);

	public WitheredAbyssSurfaceBuilder(Codec<SurfaceBuilderConfig> p_i232135_1_) {
		super(p_i232135_1_);
	}

	protected ImmutableList<BlockState> getFloorBlockStates() {
		return BLOCK_STATES;
	}

	protected ImmutableList<BlockState> getCeilingBlockStates() {
		return BLOCK_STATES;
	}

	protected BlockState getPatchBlockState() {
		return OBSIDIAN;
	}
}