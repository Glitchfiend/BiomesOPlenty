/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.NetherCappedSurfaceBuilder;

public class FleshSurfaceBuilder extends NetherCappedSurfaceBuilder {
	private static final BlockState FLESH = BOPBlocks.flesh.defaultBlockState();
	private static final ImmutableList<BlockState> BLOCK_STATES = ImmutableList.of(FLESH);

	public FleshSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232135_1_) {
		super(p_i232135_1_);
	}

	protected ImmutableList<BlockState> getFloorBlockStates() {
		return BLOCK_STATES;
	}

	protected ImmutableList<BlockState> getCeilingBlockStates() {
		return BLOCK_STATES;
	}

	protected BlockState getPatchBlockState() {
		return FLESH;
	}
}