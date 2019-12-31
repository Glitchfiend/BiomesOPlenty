package biomesoplenty.common.world.gen.feature;

import biomesoplenty.common.util.block.IBlockPosQuery;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class GlowstoneSpikesFeature extends Feature<NoFeatureConfig>
{
    protected IBlockPosQuery placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK;
    protected IBlockPosQuery replace = (world, pos) -> world.getBlockState(pos).isAir(world, pos);
    int minRadius = 2;
    int maxRadius = 3;
    int minHeight = 4;
    int maxHeight = 11;

    public GlowstoneSpikesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> p_212245_2_, Random rand, BlockPos startPos, NoFeatureConfig p_212245_5_)
    {
        int randRadius = this.minRadius + rand.nextInt(this.maxRadius - this.minRadius);
        int height = this.minHeight + rand.nextInt(this.maxHeight - this.minHeight);

        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        for (int x = 0; x <= randRadius - 1; x++)
        {
            for (int z = 0; z <= randRadius - 1; z++)
            {
                if (!this.placeOn.matches(world, startPos.offset(x, 0, z)))
                {
                    return false;
                }
            }
        }

        BlockPos pos = startPos.above();

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
                    if (this.replace.matches(world, pos.offset(x, y, z)))
                    {
                        world.setBlock(pos.offset(x, y, z), Blocks.GLOWSTONE.defaultBlockState(), 2);
                    }
                }
            }
        }

        return true;
    }
}