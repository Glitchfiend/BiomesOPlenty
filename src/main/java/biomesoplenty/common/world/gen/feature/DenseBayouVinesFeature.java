package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;
import java.util.Random;

public class DenseBayouVinesFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.willow_log || world.getBlockState(pos).getBlock() == BOPBlocks.willow_leaves || world.getBlockState(pos).getBlock() == Blocks.OAK_LEAVES || world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.DIRT;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    int minHeight = 2;
    int maxHeight = 6;

    public DenseBayouVinesFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos startPos, NoFeatureConfig config)
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

            BlockState vineState = BOPBlocks.willow_vine.defaultBlockState();

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

    public boolean setBlock(IWorld world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }
}