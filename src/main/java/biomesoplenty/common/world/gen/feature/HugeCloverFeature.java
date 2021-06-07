package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.ibm.icu.impl.CalendarAstronomer;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class HugeCloverFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock;

    public HugeCloverFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos startPos, NoFeatureConfig config)
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

        this.setBlock(world, pos, BOPBlocks.huge_clover_petal.defaultBlockState().setValue(HorizontalBlock.FACING, Direction.NORTH));
        this.setBlock(world, pos.south(), BOPBlocks.huge_clover_petal.defaultBlockState().setValue(HorizontalBlock.FACING, Direction.WEST));
        this.setBlock(world, pos.east(), BOPBlocks.huge_clover_petal.defaultBlockState().setValue(HorizontalBlock.FACING, Direction.EAST));
        this.setBlock(world, pos.south().east(), BOPBlocks.huge_clover_petal.defaultBlockState().setValue(HorizontalBlock.FACING, Direction.SOUTH));

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
        for (int x = 0; x <= 1; x++)
        {
            for (int z = 0; z <= 1; z++)
            {
                BlockPos pos1 = pos.offset(x, 0, z);
                if (pos1.getY() >= 255 || !this.replace.matches(world, pos1) || !this.placeOn.matches(world, pos1.below()))
                {
                    return false;
                }
            }
        }

        return true;
    }
}