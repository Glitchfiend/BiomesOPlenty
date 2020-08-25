package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class BoneSpineFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.flesh;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    private int maxHeight = 3;

    public BoneSpineFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator p_230362_3_, Random rand, BlockPos startPos, NoFeatureConfig p_230362_6_)
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

        int height = 1 + rand.nextInt(maxHeight - 1);

        for (int y = 0; y <= height; y++)
        {
            this.setBlock(world, pos.offset(0, y, 0), Blocks.BONE_BLOCK.defaultBlockState());
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