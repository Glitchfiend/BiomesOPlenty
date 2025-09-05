/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.worldgen.feature.BOPVegetationFeatures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FlowerBudBlock extends VegetationBlockBOP implements BonemealableBlock
{
    public static final MapCodec<FlowerBudBlock> CODEC = simpleCodec(FlowerBudBlock::new);
    protected static final VoxelShape NORMAL = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 8.0D, 11.0D);

    public FlowerBudBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public MapCodec<FlowerBudBlock> codec()
    {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        Vec3 vec3 = state.getOffset(pos);
        return NORMAL.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {}

    public boolean growHugeFlower(ServerLevel level, BlockPos p_226940_2_, BlockState p_226940_3_, RandomSource p_226940_4_)
    {
        level.removeBlock(p_226940_2_, false);

        Registry<ConfiguredFeature<?, ?>> configuredFeatureRegistry = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        ConfiguredFeature<?, ?> feature;

        if (this != BOPBlocks.FLOWER_BUD)
        {
            level.setBlock(p_226940_2_, p_226940_3_, 3);
            return false;
        }

        feature = configuredFeatureRegistry.get(BOPVegetationFeatures.HUGE_FLOWER).orElseThrow().value();

        if (feature.place(level, level.getChunkSource().getGenerator(), p_226940_4_, p_226940_2_))
        {
            return true;
        }
        else
        {
            level.setBlock(p_226940_2_, p_226940_3_, 3);
            return false;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) { return rand.nextInt(32) == 0; }

    @Override
    public void performBonemeal(ServerLevel p_225535_1_, RandomSource p_225535_2_, BlockPos p_225535_3_, BlockState p_225535_4_) {
        this.growHugeFlower(p_225535_1_, p_225535_3_, p_225535_4_, p_225535_2_);
    }
}
