/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.surfacebuilders.NetherCappedSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

public class WitheredAbyssSurfaceBuilder extends NetherCappedSurfaceBuilder {
	private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.defaultBlockState();
	private static final BlockState OBSIDIAN = Blocks.OBSIDIAN.defaultBlockState();
	private static final ImmutableList<BlockState> BLOCK_STATES = ImmutableList.of(BLACKSTONE);

	public WitheredAbyssSurfaceBuilder(Codec<SurfaceBuilderBaseConfiguration> p_i232135_1_) {
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