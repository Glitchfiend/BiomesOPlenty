/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class DeadCoralBlockFeature implements Feature
{
    public static final MapCodec<DeadCoralBlockFeature> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("to_place").forGetter(f -> f.toPlace)
            ).apply(i, DeadCoralBlockFeature::new)
    );

    @Override
    public MapCodec<DeadCoralBlockFeature> codec()
    {
        return CODEC;
    }

    private final BlockStateProvider toPlace;

    public DeadCoralBlockFeature(BlockStateProvider toPlace)
    {
        this.toPlace = toPlace;
    }

    public DeadCoralBlockFeature(Block block)
    {
        this(BlockStateProvider.of(block));
    }


    @Override
    public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
    {
        BlockPos above = origin.above();
        BlockState existing = level.getBlockState(origin);

        if (!((existing.isAir() || existing.is(ModTags.Blocks.DEAD_CORALS) || existing.getBlock() == BOPBlocks.BARNACLES) && level.getBlockState(above).isAir()))
        {
            return false;
        }

        level.setBlock(origin, this.toPlace.getState(level, random, origin), 3);

        if (random.nextFloat() < 0.25F)
        {
            BuiltInRegistries.BLOCK.get(ModTags.Blocks.DEAD_CORALS).flatMap(corals -> corals.getRandomElement(random)).map(Holder::value).ifPresent(coral -> {
                level.setBlock(above, coral.defaultBlockState().setValue(BaseCoralPlantTypeBlock.WATERLOGGED, false), 2);
            });
        }
        else if (random.nextFloat() < 0.05F)
        {
            level.setBlock(above, Blocks.GLOW_LICHEN.defaultBlockState().setValue(PipeBlock.DOWN, true).setValue(SeaPickleBlock.WATERLOGGED, false), 2);
        }

        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            if (random.nextFloat() >= 0.2F)
            {
                continue;
            }

            BlockPos sidePos = origin.relative(direction);

            if (level.getBlockState(sidePos).isAir() || level.getBlockState(sidePos).getBlock() == BOPBlocks.BARNACLES)
            {
                BuiltInRegistries.BLOCK.get(ModTags.Blocks.DEAD_WALL_CORALS).flatMap(corals -> corals.getRandomElement(random)).map(Holder::value).ifPresent(coral -> {
                    BlockState wallState = coral.defaultBlockState().setValue(BaseCoralPlantTypeBlock.WATERLOGGED, false);

                    if (wallState.hasProperty(BaseCoralWallFanBlock.FACING))
                    {
                        wallState = wallState.setValue(BaseCoralWallFanBlock.FACING, direction);
                    }

                    level.setBlock(sidePos, wallState, 2);
                });
            }
        }

        return true;
    }
}
