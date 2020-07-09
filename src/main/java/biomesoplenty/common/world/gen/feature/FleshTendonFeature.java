package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.NetherCrystalBlock;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class FleshTendonFeature extends Feature<NoFeatureConfig>
{
    private static final int MIN_DISTANCE = 8;
    private static final int MAX_DISTANCE = 16;
    private static final float MID_POS_MULTIPLIER = 0.1F;
    private static final float TENDON_STEP = 0.005f;

    public FleshTendonFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    private static BlockPos quadratic(float t, BlockPos v0, BlockPos v1, BlockPos v2)
    {
        float dt = 1f - t;
        Vector3d v = new Vector3d(v0.getX(), v0.getY(), v0.getZ()).scale(dt * dt).add(new Vector3d(v1.getX(), v1.getY(), v1.getZ()).scale(2 * dt * t)).add(new Vector3d(v2.getX(), v2.getY(), v2.getZ()).scale(t * t));
        return new BlockPos(v.x, v.y, v.z);
    }

    @Override
    public boolean place(ISeedReader world, StructureManager structureManager, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        BlockState below = world.getBlockState(pos.below());
        if (!below.is(BOPBlocks.flesh))
        {
            return false;
        }

        int minX = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        int minZ = rand.nextBoolean() ? MIN_DISTANCE : -MIN_DISTANCE;
        BlockPos endPos = pos.offset(rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE + minX, pos.getY(), rand.nextInt(MAX_DISTANCE * 2) - MAX_DISTANCE + minZ);

        while (world.isEmptyBlock(endPos) && endPos.getY() < 120)
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
            this.setBlock(world, curPos, BOPBlocks.flesh.defaultBlockState());
        }

        return true;
    }
}