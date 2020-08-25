package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.NetherCrystalBlock;
import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
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

public class LargeCrystalFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock() == BOPBlocks.nether_crystal;
    private int minRadius = 2;
    private int maxRadius = 3;
    private int minHeight = 3;
    private int maxHeight = 15;

    public LargeCrystalFeature(Codec<NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator p_230362_3_, Random rand, BlockPos pos, NoFeatureConfig p_230362_6_)
    {
        if (!world.isEmptyBlock(pos))
        {
            return false;
        }
        else
        {
            BlockState blockstate = world.getBlockState(pos.above());
            if (!blockstate.is(Blocks.NETHERRACK) && !blockstate.is(Blocks.BASALT) && !blockstate.is(Blocks.BLACKSTONE) && !blockstate.is(Blocks.NETHER_QUARTZ_ORE))
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
                    int radiusStart = MathHelper.ceil(0.25D - radius / 2.0D);
                    int radiusEnd = MathHelper.floor(0.25D + radius / 2.0D);

                    for (int x = radiusStart; x <= radiusEnd; x++) {
                        for (int z = radiusStart; z <= radiusEnd; z++) {
                            this.setBlock(world, pos.offset(x, -y, z), BOPBlocks.nether_crystal_block.defaultBlockState());
                        }
                    }

                    this.generateCrystals(world, pos.offset(0, -y, 0), rand);
                }

                return true;
            }
        }
    }

    public boolean generateCrystals(ISeedReader world, BlockPos pos, Random rand)
    {
        int i = 0;

        for(int j = 0; j < 48; ++j)
        {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
            AttachFace face;
            switch (rand.nextInt(3))
            {
                default:
                case 0:
                    face = AttachFace.FLOOR;
                    break;

                case 1:
                    face = AttachFace.CEILING;
                    break;

                case 2:
                    face = AttachFace.WALL;
                    break;
            }

            BlockState state = BOPBlocks.nether_crystal.defaultBlockState().setValue(NetherCrystalBlock.FACING, direction).setValue(NetherCrystalBlock.FACE, face);
            BlockPos blockpos = pos.offset(rand.nextInt(3) - rand.nextInt(3), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - rand.nextInt(3));

            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos))
            {
                world.setBlock(blockpos, state, 2);

                ++i;
            }
        }

        return i > 0;
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