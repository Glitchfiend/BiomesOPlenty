/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.surfacebuilders.NetherCappedSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class BrimstoneSurfaceBuilder extends NetherCappedSurfaceBuilder {
	private static final ImmutableList<BlockState> FLOOR_STATES = ImmutableList.of(BOPBlocks.BRIMSTONE.defaultBlockState(), Blocks.NETHERRACK.defaultBlockState());
	private static final ImmutableList<BlockState> CEILING_STATES = ImmutableList.of(Blocks.NETHERRACK.defaultBlockState());

	public BrimstoneSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232135_1_) {
		super(p_i232135_1_);
	}

	protected ImmutableList<BlockState> getFloorBlockStates() {
		return FLOOR_STATES;
	}

	protected ImmutableList<BlockState> getCeilingBlockStates() {
		return CEILING_STATES;
	}

	protected BlockState getPatchBlockState() {
		return Blocks.TUFF.defaultBlockState();
	}
}