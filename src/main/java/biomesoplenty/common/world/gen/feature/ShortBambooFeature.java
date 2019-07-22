package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.properties.BambooLeaves;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ShortBambooFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos);
    private static final BlockState field_214566_a = Blocks.BAMBOO.getDefaultState().with(BambooBlock.PROPERTY_AGE, Integer.valueOf(1)).with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.NONE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));
    private static final BlockState field_214567_aS = field_214566_a.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));
    private static final BlockState field_214568_aT = field_214566_a.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.LARGE).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));
    private static final BlockState field_214569_aU = field_214566_a.with(BambooBlock.PROPERTY_BAMBOO_LEAVES, BambooLeaves.SMALL).with(BambooBlock.PROPERTY_STAGE, Integer.valueOf(1));

    public ShortBambooFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        while (pos.getY() > 1 && this.replace.matches(world, pos)) {pos = pos.down();}

        if (!this.placeOn.matches(world, pos.add(2, 0, 2)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        pos = pos.up();

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(pos);
        if (world.isAirBlock(blockpos$mutableblockpos))
        {
            if (Blocks.BAMBOO.getDefaultState().isValidPosition(world, blockpos$mutableblockpos))
            {
                int j = rand.nextInt(2) + 3;

                for(int l1 = 0; l1 < j && world.isAirBlock(blockpos$mutableblockpos); ++l1)
                {
                    world.setBlockState(blockpos$mutableblockpos, field_214566_a, 2);
                    blockpos$mutableblockpos.move(Direction.UP, 1);
                }

                if (blockpos$mutableblockpos.getY() - pos.getY() >= 3)
                {
                    world.setBlockState(blockpos$mutableblockpos, field_214567_aS, 2);
                    world.setBlockState(blockpos$mutableblockpos.move(Direction.DOWN, 1), field_214568_aT, 2);
                    world.setBlockState(blockpos$mutableblockpos.move(Direction.DOWN, 1), field_214569_aU, 2);
                }
            }
        }

        return true;
    }
}