package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.worldgen.feature.configurations.BasicTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.function.BiConsumer;

public class BushTreeFeature extends BOPTreeFeature<BasicTreeConfiguration>
{
    public BushTreeFeature(Codec<BasicTreeConfiguration> codec)
    {
        super(codec);
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, TreeConfiguration configBase)
    {
        BasicTreeConfiguration config = (BasicTreeConfiguration)configBase;
        // Move down until we reach the ground
        while (startPos.getY() > 1 && (world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.AIR)) {startPos = startPos.below();}

        if (!this.canReplace(world, startPos.above()))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        // choose a random height
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);

        // start from the block above the ground block
        BlockPos pos = startPos.above();

        //Generate a bush 3 blocks tall, with the bottom block already set to a log
        for (int y = 0; y < height; ++y)
        {
            // log in the center
            if (height - y > 1)
            {
                this.placeLog(world, pos.offset(0, y, 0), logs, config);
            }

            //Reduces the radius closer to the top of the bush
            int leavesRadius = (height - y > 1 ? 2 : 1);

            for (int x = -leavesRadius; x <= leavesRadius; ++x)
            {
                for (int z = -leavesRadius; z <= leavesRadius; ++z)
                {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < leavesRadius || Math.abs(z) < leavesRadius || random.nextInt(2) != 0)
                    {
                        if (config.altFoliageProvider.getState(random, pos) != Blocks.AIR.defaultBlockState())
                        {
                            if (random.nextInt(4) == 0)
                            {
                                this.placeAltLeaves(world, pos.offset(x, y, z), leaves, config);
                            }
                            else
                            {
                                this.placeLeaves(world, pos.offset(x, y, z), leaves, config);
                            }
                        }
                        else
                        {
                            this.placeLeaves(world, pos.offset(x, y, z), leaves, config);
                        }
                    }
                }
            }
        }

        return true;
    }
}
