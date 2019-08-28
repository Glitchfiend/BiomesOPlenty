package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class NetherVinesFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    int minHeight = 8;
    int maxHeight = 20;

    public NetherVinesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos startPos, NoFeatureConfig p_212245_5_)
    {
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.down();}

        if (!this.placeOn.matches(world, startPos.add(2, 0, 2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        for (int i = 0; i < 128; ++i)
        {
            BlockPos genPos = startPos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));

            if (!this.replace.matches(world, genPos) || !this.placeOn.matches(world, genPos.up())) continue;

            BlockState vineState = Blocks.VINE.getDefaultState();

            // make sure there is an adjacent block for the vine to attach to
            List<Direction> validDirections = Lists.newArrayList();

            for (Direction facing : Direction.values()) {
                if (facing == Direction.UP || facing == Direction.DOWN) continue;
                if (this.placeOn.matches(world, genPos.offset(facing))) validDirections.add(facing);
            }

            if (validDirections.isEmpty()) continue;

            Direction direction = validDirections.get(rand.nextInt(validDirections.size()));
            vineState = vineState.with(VineBlock.getPropertyFor(direction), Boolean.valueOf(true));

            // choose random target height
            int targetHeight = minHeight + rand.nextInt(maxHeight);

            // keep placing blocks upwards (if there's room)
            for (int height = 0; height <= targetHeight; height++)
            {
                BlockPos offsetPos = genPos.down(height);

                if (replace.matches(world, offsetPos) && vineState.getBlock().isValidPosition(vineState, world, offsetPos))
                {
                    world.setBlockState(offsetPos, vineState, 2);
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
            this.setBlockState(world, pos, state);
            return true;
        }
        return false;
    }
}