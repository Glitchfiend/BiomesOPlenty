package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class SmallBrownMushroomFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.MYCELIUM;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock;

    public SmallBrownMushroomFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos startPos, NoFeatureConfig p_212245_5_) {
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {
            startPos = startPos.below();
        }

        if (!this.placeOn.matches(world, startPos.offset(0, 0, 0))) {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above())) {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        int height = 1 + p_212245_3_.nextInt(2);

        for (int y = 0; y < height; y++) {
            this.setBlock(world, pos.above(y), Blocks.MUSHROOM_STEM.defaultBlockState());
        }

        this.setBlock(world, pos.offset(0, height, 0), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
        this.setBlock(world, pos.offset(-1, height, 0), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
        this.setBlock(world, pos.offset(1, height, 0), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
        this.setBlock(world, pos.offset(0, height, -1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
        this.setBlock(world, pos.offset(0, height, 1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));

        if (p_212245_3_.nextInt(2) == 0)
        {
            this.setBlock(world, pos.offset(1, height, 1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
            this.setBlock(world, pos.offset(-1, height, -1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
        }
        else
        {
            this.setBlock(world, pos.offset(-1, height, 1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
            this.setBlock(world, pos.offset(1, height, -1), Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));
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

    public boolean checkSpace(IWorld world, BlockPos pos)
    {
        for (int y = 0; y <= 4; y++)
        {
            for (int x = -2; x <= 2; x++)
            {
                for (int z = -2; z <= 2; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}