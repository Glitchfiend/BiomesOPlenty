/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.worldgen.feature.misc;

import biomesoplenty.forge.api.block.BOPBlocks;
import biomesoplenty.forge.common.block.HugeLilyPadBlock;
import biomesoplenty.forge.common.block.state.properties.QuarterProperty;
import biomesoplenty.forge.common.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Fluids;

public class HugeLilyPadFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> (world.getBlockState(pos).getFluidState().getType() == Fluids.WATER || world.getBlockState(pos).getBlock() instanceof IceBlock) && world.getBlockState(pos.above()).getFluidState().getType() == Fluids.EMPTY;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() == BOPBlocks.WATERGRASS.get();

    public HugeLilyPadFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
        NoneFeatureConfiguration config = featurePlaceContext.config();
        while (startPos.getY() >= world.getMinBuildHeight()+1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above(), direction))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        this.setBlock(world, pos, BOPBlocks.HUGE_LILY_PAD.get().defaultBlockState().setValue(HugeLilyPadBlock.QUARTER, QuarterProperty.SOUTH_WEST).setValue(HorizontalDirectionalBlock.FACING, direction));
        this.setBlock(world, pos.relative(direction), BOPBlocks.HUGE_LILY_PAD.get().defaultBlockState().setValue(HugeLilyPadBlock.QUARTER, QuarterProperty.NORTH_WEST).setValue(HorizontalDirectionalBlock.FACING, direction));
        this.setBlock(world, pos.relative(direction).relative(direction.getClockWise()), BOPBlocks.HUGE_LILY_PAD.get().defaultBlockState().setValue(HugeLilyPadBlock.QUARTER, QuarterProperty.NORTH_EAST).setValue(HorizontalDirectionalBlock.FACING, direction));
        this.setBlock(world, pos.relative(direction.getClockWise()), BOPBlocks.HUGE_LILY_PAD.get().defaultBlockState().setValue(HugeLilyPadBlock.QUARTER, QuarterProperty.SOUTH_EAST).setValue(HorizontalDirectionalBlock.FACING, direction));

        return true;
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            super.markAboveForPostProcessing(world, pos.below());
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, Direction direction)
    {
        BlockPos pos1 = pos;
        BlockPos pos2 = pos.relative(direction);
        BlockPos pos3 = pos2.relative(direction.getClockWise());
        BlockPos pos4 = pos.relative(direction.getClockWise());
        if (pos1.getY() >= 255 || !this.replace.matches(world, pos1) || !this.placeOn.matches(world, pos1.below()) ||
            pos2.getY() >= 255 || !this.replace.matches(world, pos2) || !this.placeOn.matches(world, pos2.below()) ||
            pos3.getY() >= 255 || !this.replace.matches(world, pos3) || !this.placeOn.matches(world, pos3.below()) ||
            pos4.getY() >= 255 || !this.replace.matches(world, pos4) || !this.placeOn.matches(world, pos4.below()))
        {
            return false;
        }

        return true;
    }
}
