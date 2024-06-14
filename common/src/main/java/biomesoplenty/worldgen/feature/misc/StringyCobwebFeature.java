/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.StringyCobwebBlock;
import biomesoplenty.block.properties.ConnectedProperty;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StringyCobwebFeature extends Feature<NoneFeatureConfiguration>
{
    private static final int MIN_DISTANCE = 2;
    private static final int MAX_DISTANCE = 32;

    public StringyCobwebFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    public boolean canPlace(WorldGenLevel world, BlockPos pos, int length, Direction dir)
    {
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);

        if ((!world.getBlockState(pos).isAir() && world.getBlockState(pos).getBlock() != Blocks.COBWEB && world.getBlockState(pos).getBlock() != Blocks.GLOW_LICHEN && world.getBlockState(pos).getBlock() != BOPBlocks.WEBBING) || !belowState.isFaceSturdy(world, belowPos, Direction.UP) || !this.respectsCutoff((WorldGenRegion)world, pos))
        {
            return false;
        }

        BlockPos nextStringPos = pos;

        for (int i = 0; i < length; i++)
        {
            nextStringPos = nextStringPos.relative(dir, 1).above(1);
            BlockState nextStringState = world.getBlockState(nextStringPos);

            if ((!nextStringState.isAir() && world.getBlockState(nextStringPos).getBlock() != Blocks.COBWEB && world.getBlockState(nextStringPos).getBlock() != Blocks.GLOW_LICHEN && world.getBlockState(nextStringPos).getBlock() != BOPBlocks.WEBBING) || !this.respectsCutoff((WorldGenRegion)world, nextStringPos))
            {
                return false;
            }
        }

        BlockPos abovePos = nextStringPos.above();
        BlockState aboveState = world.getBlockState(abovePos);

        if (!aboveState.isFaceSturdy(world, abovePos, Direction.DOWN) || !this.respectsCutoff((WorldGenRegion)world, nextStringPos))
        {
            return false;
        }

        return true;
    }

    public void placeCobweb(WorldGenLevel world, BlockPos pos, int length, Direction dir)
    {
        if (this.respectsCutoff((WorldGenRegion)world, pos))
        {
            world.setBlock(pos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.BOTTOM), 2);
        }

        BlockPos nextStringPos = pos;

        for (int i = 0; i < length; ++i)
        {
            nextStringPos = nextStringPos.relative(dir, 1).above(1);

            if (this.respectsCutoff((WorldGenRegion)world, nextStringPos))
            {
                world.setBlock(nextStringPos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.MIDDLE), 2);
            }
        }

        if (this.respectsCutoff((WorldGenRegion)world, nextStringPos))
        {
            world.setBlock(nextStringPos, BOPBlocks.STRINGY_COBWEB.defaultBlockState().setValue(StringyCobwebBlock.FACING, dir).setValue(StringyCobwebBlock.CONNECTED, ConnectedProperty.TOP), 2);
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        int k = 0;

        for(int j = 0; j < 128; ++j)
        {
            int length = MIN_DISTANCE + rand.nextInt(MAX_DISTANCE - MIN_DISTANCE);
            Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
            BlockPos blockPos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (canPlace(world, blockPos, length, dir))
            {
                placeCobweb(world, blockPos, length, dir);
                ++k;
            }
        }

        return k > 0;
    }

    private boolean respectsCutoff(WorldGenRegion region, BlockPos pos)
    {
        int i = SectionPos.blockToSectionCoord(pos.getX());
        int j = SectionPos.blockToSectionCoord(pos.getZ());
        ChunkPos chunkpos = region.getCenter();
        int k = Math.abs(chunkpos.x - i);
        int l = Math.abs(chunkpos.z - j);

        if (k <= region.generatingStep.blockStateWriteRadius() && l <= region.generatingStep.blockStateWriteRadius())
        {
            return true;
        }

        return false;
    }
}
