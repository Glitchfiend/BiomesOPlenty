package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class LargeCrystalFeature extends Feature<NoneFeatureConfiguration>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK;
    protected IBlockPosQuery replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() == BOPBlocks.ROSE_QUARTZ_CLUSTER || world.getBlockState(pos).getBlock() == BOPBlocks.LARGE_ROSE_QUARTZ_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.SMALL_ROSE_QUARTZ_BUD;
    private int minRadius = 2;
    private int maxRadius = 3;
    private int minHeight = 3;
    private int maxHeight = 15;

    public LargeCrystalFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        Random rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        if (!world.isEmptyBlock(pos))
        {
            return false;
        }
        else
        {
            BlockState blockstate = world.getBlockState(pos.above());
            if (!blockstate.is(Blocks.NETHERRACK) && !blockstate.is(Blocks.BASALT) && !blockstate.is(Blocks.BLACKSTONE) && !blockstate.is(Blocks.NETHER_QUARTZ_ORE) && !blockstate.is(Blocks.NETHER_GOLD_ORE))
            {
                return false;
            }
            else
            {
                int randRadius = this.minRadius + rand.nextInt(this.maxRadius - this.minRadius);
                int height = this.minHeight + rand.nextInt(this.maxHeight - this.minHeight);

                for (int y = 0; y <= height; y++)
                {
                    int radius = (randRadius * (height - y) / height) + 1;
                    int radiusStart = Mth.ceil(0.25D - radius / 2.0D);
                    int radiusEnd = Mth.floor(0.25D + radius / 2.0D);

                    for (int x = radiusStart; x <= radiusEnd; x++)
                    {
                        for (int z = radiusStart; z <= radiusEnd; z++)
                        {
                            BlockState state = BOPBlocks.ROSE_QUARTZ_BLOCK.defaultBlockState();
                            this.setBlock(world, pos.offset(x, -y, z), state);
                        }
                    }

                    this.generateCrystals(world, pos.offset(0, -y, 0), rand);
                }

                return true;
            }
        }
    }

    public boolean generateCrystals(WorldGenLevel world, BlockPos pos, Random rand)
    {
        int i = 0;

        for(int j = 0; j < 48; ++j)
        {
            Direction direction = Direction.getRandom(rand);

            BlockState cluster_state;
            switch (rand.nextInt(5))
            {
                case 3:
                default:
                    cluster_state = BOPBlocks.ROSE_QUARTZ_CLUSTER.defaultBlockState();
                    break;

                case 2:
                    cluster_state = BOPBlocks.LARGE_ROSE_QUARTZ_BUD.defaultBlockState();
                    break;

                case 1:
                    cluster_state = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD.defaultBlockState();
                    break;

                case 0:
                    cluster_state = BOPBlocks.SMALL_ROSE_QUARTZ_BUD.defaultBlockState();
                    break;
            }

            BlockState state = cluster_state.setValue(AmethystClusterBlock.FACING, direction);
            BlockPos blockpos = pos.offset(rand.nextInt(3) - rand.nextInt(3), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - rand.nextInt(3));

            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos))
            {
                world.setBlock(blockpos, state, 2);

                ++i;
            }
        }

        return i > 0;
    }

    public boolean setBlock(LevelAccessor world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos)
    {
        for (int y = 0; y <= 15; y--)
        {
            for (int x = -1; x <= 1; x++)
            {
                for (int z = -1; z <= 1; z++)
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