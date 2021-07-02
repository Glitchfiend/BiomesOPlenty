package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class ShortBambooFeature extends Feature<NoneFeatureConfiguration>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos);
    private static final BlockState field_214566_a = Blocks.BAMBOO.defaultBlockState().setValue(BambooBlock.AGE, Integer.valueOf(1)).setValue(BambooBlock.LEAVES, BambooLeaves.NONE).setValue(BambooBlock.STAGE, Integer.valueOf(1));
    private static final BlockState field_214567_aS = field_214566_a.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooBlock.STAGE, Integer.valueOf(1));
    private static final BlockState field_214568_aT = field_214566_a.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE).setValue(BambooBlock.STAGE, Integer.valueOf(1));
    private static final BlockState field_214569_aU = field_214566_a.setValue(BambooBlock.LEAVES, BambooLeaves.SMALL).setValue(BambooBlock.STAGE, Integer.valueOf(1));

    public ShortBambooFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoneFeatureConfiguration config)
    {
        while (pos.getY() > 1 && this.replace.matches(world, pos)) {pos = pos.below();}

        if (!this.placeOn.matches(world, pos.offset(2, 0, 2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        pos = pos.above();

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(pos.getX(), pos.getY(), pos.getZ());
        if (world.isEmptyBlock(blockpos$mutableblockpos))
        {
            if (Blocks.BAMBOO.defaultBlockState().canSurvive(world, blockpos$mutableblockpos))
            {
                int j = rand.nextInt(2) + 3;

                for(int l1 = 0; l1 < j && world.isEmptyBlock(blockpos$mutableblockpos); ++l1)
                {
                    world.setBlock(blockpos$mutableblockpos, field_214566_a, 2);
                    blockpos$mutableblockpos.move(Direction.UP, 1);
                }

                if (blockpos$mutableblockpos.getY() - pos.getY() >= 3)
                {
                    world.setBlock(blockpos$mutableblockpos, field_214567_aS, 2);
                    world.setBlock(blockpos$mutableblockpos.move(Direction.DOWN, 1), field_214568_aT, 2);
                    world.setBlock(blockpos$mutableblockpos.move(Direction.DOWN, 1), field_214569_aU, 2);
                }
            }
        }

        return true;
    }
}