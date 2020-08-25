package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class SmallToadstoolFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.MYCELIUM;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock;

    public SmallToadstoolFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos startPos, NoFeatureConfig config) {
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

        int height = 1 + rand.nextInt(2);

        for (int y = 0; y < height; y++) {
            this.setBlock(world, pos.above(y), Blocks.MUSHROOM_STEM.defaultBlockState());
        }

        this.setBlock(world, pos.offset(0, height, 0), BOPBlocks.toadstool_block.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false));

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