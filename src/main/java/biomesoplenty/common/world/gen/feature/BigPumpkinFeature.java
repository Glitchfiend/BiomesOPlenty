package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;
import java.util.function.Function;

public class BigPumpkinFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos);

    public BigPumpkinFeature(Codec<NoFeatureConfig> deserializer)
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

        if (!this.checkSpace(world, startPos.above()))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        for (int x = 1; x < 4; x++)
        {
            for (int y = 0; y < 5; y++)
            {
                for (int z = 1; z < 4; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        for (int x = 0; x < 5; x++)
        {
            for (int y = 1; y < 4; y++)
            {
                for (int z = 1; z < 4; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        for (int x = 1; x < 4; x++)
        {
            for (int y = 1; y < 4; y++)
            {
                for (int z = 0; z < 5; z++)
                {
                    this.setBlock(world, pos.offset(x,y,z), Blocks.PUMPKIN.defaultBlockState());
                }
            }
        }

        this.setBlock(world, pos.offset(2,5,2), Blocks.OAK_LOG.defaultBlockState());

        this.setBlock(world, pos.offset(1,5,2), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(0,4,1), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

        this.setBlock(world, pos.offset(2,5,3), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,5,3), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(2,4,4), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,4,4), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

        this.setBlock(world, pos.offset(3,5,1), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(3,4,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,4,2), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,3,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
        this.setBlock(world, pos.offset(4,2,0), Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));

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
        for (int y = 0; y <= 6; y++)
        {
            for (int x = 0; x <= 5; x++)
            {
                for (int z = 0; z <= 5; z++)
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