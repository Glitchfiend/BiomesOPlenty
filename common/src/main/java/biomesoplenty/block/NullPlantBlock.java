/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.init.ModTags;
import biomesoplenty.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NullPlantBlock extends Block implements BonemealableBlock
{
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public NullPlantBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState groundState = worldIn.getBlockState(pos.below());
        return groundState.is(ModTags.Blocks.NULL_PLACEABLE) && groundState.isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }

    @Override
    public BlockState updateShape(BlockState p_51032_, Direction p_51033_, BlockState p_51034_, LevelAccessor p_51035_, BlockPos p_51036_, BlockPos p_51037_) {
        return !p_51032_.canSurvive(p_51035_, p_51036_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51032_, p_51033_, p_51034_, p_51035_, p_51036_, p_51037_);
    }

    @Override
    public boolean canBeReplaced(BlockState p_53910_, BlockPlaceContext p_53911_)
    {
        return true;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState)
    {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
    {
        return (double)randomSource.nextFloat() < 0.1D;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
    {
        this.growTree(serverLevel, randomSource, blockPos, blockState);
    }

    public boolean growTree(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState)
    {
        serverLevel.removeBlock(blockPos, false);

        Registry<ConfiguredFeature<?, ?>> configuredFeatureRegistry = serverLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        ConfiguredFeature<?, ?> feature = configuredFeatureRegistry.get(BOPTreeFeatures.NULL_TREE);

        if (feature.place(serverLevel, serverLevel.getChunkSource().getGenerator(), randomSource, blockPos))
        {
            return true;
        }
        else
        {
            serverLevel.setBlock(blockPos, blockState, 3);
            return false;
        }
    }
}
