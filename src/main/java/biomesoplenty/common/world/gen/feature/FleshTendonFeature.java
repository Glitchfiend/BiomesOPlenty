package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class FleshTendonFeature extends Feature<NoneFeatureConfiguration>
{
    protected IBlockPosQuery replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() == BOPBlocks.ROSE_QUARTZ_CLUSTER || world.getBlockState(pos).getBlock() == BOPBlocks.LARGE_ROSE_QUARTZ_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.SMALL_ROSE_QUARTZ_BUD;

    private static final int MIN_DISTANCE = 8;
    private static final int MAX_DISTANCE = 32;
    private static final float MID_POS_MULTIPLIER = 0.9F;
    private static final float TENDON_STEP = 0.005f;

    public FleshTendonFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    private static BlockPos quadratic(float t, BlockPos v0, BlockPos v1, BlockPos v2)
    {
        float dt = 1f - t;
        Vec3 v = new Vec3(v0.getX(), v0.getY(), v0.getZ()).scale(dt * dt).add(new Vec3(v1.getX(), v1.getY(), v1.getZ()).scale(2 * dt * t)).add(new Vec3(v2.getX(), v2.getY(), v2.getZ()).scale(t * t));
        return new BlockPos(v.x, v.y, v.z);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        WorldGenLevel world = context.level();
        Random rand = context.random();
        BlockPos pos = context.origin();

        BlockState below = world.getBlockState(pos.below());
        if (!below.is(ModTags.Blocks.FLESH))
        {
            return false;
        }

        int xOff = rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE;
        int zOff = rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE;
        int minX = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        int minZ = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        BlockPos endPos = pos.offset(Math.abs(xOff) < MIN_DISTANCE ? minX : xOff, pos.getY(), Math.abs(zOff) < MIN_DISTANCE ? minZ : zOff);

        while (world.isEmptyBlock(endPos) && endPos.getY() < 126)
        {
            endPos = endPos.above();
        }

        // No room for the tendon
        if (endPos.getY() == pos.getY())
        {
            return false;
        }

        BlockPos midPos = endPos.offset(0, -(endPos.getY() - pos.getY()) * MID_POS_MULTIPLIER, 0);

        for (float d = 0.0f; d < 1.0f; d += TENDON_STEP)
        {
            BlockPos curPos = quadratic(d, pos, midPos, endPos);

            if (curPos.getY() < 126)
            {
                BlockState fleshBlock = BOPBlocks.FLESH.defaultBlockState();
                if (rand.nextInt(5) == 0)
                {
                    fleshBlock = BOPBlocks.POROUS_FLESH.defaultBlockState();
                }

                this.setBlock(world, curPos, fleshBlock);
                if (rand.nextInt(75) == 0)
                {
                    this.generateFleshBall(world, curPos, rand);
                }
            }
            else
            {
                break;
            }
        }

        return true;
    }

    public boolean generateFleshBall(WorldGenLevel world, BlockPos pos, Random rand)
    {
        this.setBlock(world, pos, BOPBlocks.POROUS_FLESH.defaultBlockState());
        this.setBlock(world, pos.north(), Blocks.SHROOMLIGHT.defaultBlockState(), 2);
        this.setBlock(world, pos.south(), Blocks.SHROOMLIGHT.defaultBlockState(), 2);
        this.setBlock(world, pos.east(), Blocks.SHROOMLIGHT.defaultBlockState(), 2);
        this.setBlock(world, pos.west(), Blocks.SHROOMLIGHT.defaultBlockState(), 2);
        this.setBlock(world, pos.north().west(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.south().west(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.north().east(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.south().east(), BOPBlocks.FLESH.defaultBlockState());

        this.setBlock(world, pos.above(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.above().north(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.above().south(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.above().east(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.above().west(), BOPBlocks.FLESH.defaultBlockState());

        this.setBlock(world, pos.below(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.below().north(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.below().south(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.below().east(), BOPBlocks.FLESH.defaultBlockState());
        this.setBlock(world, pos.below().west(), BOPBlocks.FLESH.defaultBlockState());

        return true;
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

    public boolean setBlock(LevelAccessor world, BlockPos pos, BlockState state, int flags)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlock(pos, state, flags);
            return true;
        }
        return false;
    }
}