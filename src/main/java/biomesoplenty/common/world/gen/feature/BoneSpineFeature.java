package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class BoneSpineFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.flesh;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    private int maxHeight = 8;

    public BoneSpineFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random p_212245_3_, BlockPos startPos, NoFeatureConfig p_212245_5_)
    {
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above()))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        int height = 2 + p_212245_3_.nextInt(2);

        boolean spine = false;
        if (p_212245_3_.nextInt(3) == 0)
        {
            spine = true;
            height = 2 + p_212245_3_.nextInt(maxHeight + 1);
        }

        for (int y = 0; y < height; y++)
        {
            this.setBlock(world, pos.offset(0, y, 0), Blocks.BONE_BLOCK.defaultBlockState());

            if (spine == true && y % 2 == 1)
            {
                this.setBlock(world, pos.offset(1, y, 0), Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                this.setBlock(world, pos.offset(-1, y, 0), Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.X));
                this.setBlock(world, pos.offset(0, y, 1), Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
                this.setBlock(world, pos.offset(0, y, -1), Blocks.BONE_BLOCK.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z));
            }
        }

        this.setBlock(world, pos.offset(0, height, 0), Blocks.BONE_BLOCK.defaultBlockState());
        if (height % 2 == 0)
        {
            this.setBlock(world, pos.offset(0, height + 1, 0), Blocks.BONE_BLOCK.defaultBlockState());
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
        for (int y = 0; y <= maxHeight + 1; y++)
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