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
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class FleshTendonFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.flesh;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock() == BOPBlocks.nether_crystal;

    public FleshTendonFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_, Random rand, BlockPos pos, NoFeatureConfig p_230362_6_)
    {
        if (!world.isEmptyBlock(pos))
        {
            return false;
        }
        else
        {
            BlockState blockstate = world.getBlockState(pos.below());
            if (!blockstate.is(BOPBlocks.flesh))
            {
                return false;
            }
            else
            {
                int height = 120;
                double baseSlant = rand.nextInt(35) / 100D;
                double slantOffset = baseSlant;
                double slantMultiplier = 1.3D;
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

                for(int step = 0; step <= height; step++)
                {
                    BlockPos offsetPos = pos.above(step).relative(direction, (int)Math.floor(slantOffset));

                    if (offsetPos.getY() < 127)
                    {
                        this.setBlock(world, offsetPos, BOPBlocks.flesh.defaultBlockState());
                    }

                    //As the height increases, slant more drastically
                    slantOffset *= slantMultiplier;
                }

                return true;
            }
        }
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
}