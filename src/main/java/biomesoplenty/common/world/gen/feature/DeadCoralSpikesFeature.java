package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class DeadCoralSpikesFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.END_STONE;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos);
    int minRadius = 2;
    int maxRadius = 5;
    int minHeight = 3;
    int maxHeight = 17;

    public DeadCoralSpikesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos startPos, NoFeatureConfig p_212245_5_)
    {
        int randRadius = this.minRadius + rand.nextInt(this.maxRadius - this.minRadius);
        int height = this.minHeight + rand.nextInt(this.maxHeight - this.minHeight);

        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.down();}

        for (int x = 0; x <= randRadius - 1; x++)
        {
            for (int z = 0; z <= randRadius - 1; z++)
            {
                if (!this.placeOn.matches(world, startPos.add(x, 0, z)))
                {
                    return false;
                }
            }
        }

        BlockPos pos = startPos.up();

        // Select coral type
        BlockState block;
        int randCoral = rand.nextInt(5);

        switch (randCoral)
        {
            case 0:
            default:
                block = Blocks.DEAD_BRAIN_CORAL_BLOCK.getDefaultState();
                break;

            case 1:
                block = Blocks.DEAD_BUBBLE_CORAL_BLOCK.getDefaultState();
                break;

            case 2:
                block = Blocks.DEAD_FIRE_CORAL_BLOCK.getDefaultState();
                break;

            case 3:
                block = Blocks.DEAD_HORN_CORAL_BLOCK.getDefaultState();
                break;

            case 4:
                block = Blocks.DEAD_TUBE_CORAL_BLOCK.getDefaultState();
                break;
        }

        // Generate
        for (int y = 0; y < height - 1; y++)
        {
            int radius = (randRadius * (height - y) / height) + 1;
            int radiusStart = MathHelper.ceil(0.25D - radius / 2.0D);
            int radiusEnd = MathHelper.floor(0.25D + radius / 2.0D);

            for (int x = radiusStart; x <= radiusEnd; x++)
            {
                for (int z = radiusStart; z <= radiusEnd; z++)
                {
                    if (this.replace.matches(world, pos.add(x, y, z)))
                    {
                        this.setBlockState(world, pos.add(x, y, z), block);
                    }
                }
            }
        }

        return true;
    }
}