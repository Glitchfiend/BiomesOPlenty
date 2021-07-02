package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.Random;

public class RainforestCliffsVinesFeature extends Feature<NoneFeatureConfiguration>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.DIRT || world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.STONE || world.getBlockState(pos).getBlock() == Blocks.TERRACOTTA || world.getBlockState(pos).getBlock() == Blocks.ANDESITE || world.getBlockState(pos).getBlock() == Blocks.GRANITE || world.getBlockState(pos).getBlock() == Blocks.DIORITE;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    int minHeight = 7;
    int maxHeight = 14;

    public RainforestCliffsVinesFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, Random rand, BlockPos startPos, NoneFeatureConfiguration config)
    {
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos.offset(2, 0, 2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos genPos = startPos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));

            if (!this.replace.matches(world, genPos)) continue;

            BlockState vineState = Blocks.VINE.defaultBlockState();

            // make sure there is an adjacent block for the vine to attach to
            List<Direction> validDirections = Lists.newArrayList();

            for (Direction facing : Direction.values()) {
                if (facing == Direction.UP || facing == Direction.DOWN) continue;
                if (this.placeOn.matches(world, genPos.relative(facing))) validDirections.add(facing);
            }

            if (validDirections.isEmpty()) continue;

            Direction direction = validDirections.get(rand.nextInt(validDirections.size()));
            vineState = vineState.setValue(VineBlock.getPropertyForFace(direction), Boolean.valueOf(true));

            // choose random target height
            int targetHeight = minHeight + rand.nextInt(maxHeight);

            // keep placing blocks upwards (if there's room)
            for (int height = 0; height <= targetHeight; height++)
            {
                BlockPos offsetPos = genPos.below(height);

                if (replace.matches(world, offsetPos) && vineState.getBlock().canSurvive(vineState, world, offsetPos))
                {
                    world.setBlock(offsetPos, vineState, 2);
                }
                else
                {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean setBlock(LevelAccessor world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }
}