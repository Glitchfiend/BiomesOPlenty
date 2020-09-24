package biomesoplenty.common.world.gen.carver;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.Set;

public class OriginCaveWorldCarver extends CaveWorldCarver
{
    protected Set<Block> replaceableBlocks = ImmutableSet.of(BOPBlocks.origin_grass_block, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE);

    public OriginCaveWorldCarver(Codec<ProbabilityConfig> p_i231917_1_, int p_i231917_2_)
    {
        super(p_i231917_1_, p_i231917_2_);
    }

    @Override
    protected boolean canReplaceBlock(BlockState p_222706_1_) {
        return this.replaceableBlocks.contains(p_222706_1_.getBlock());
    }

    @Override
    protected boolean canReplaceBlock(BlockState state, BlockState aboveState) {
        return this.canReplaceBlock(state) || (state.is(Blocks.SAND) || state.is(Blocks.GRAVEL)) && !aboveState.getFluidState().is(FluidTags.WATER);
    }
}