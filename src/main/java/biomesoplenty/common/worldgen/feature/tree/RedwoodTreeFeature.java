package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.worldgen.feature.configurations.TaigaTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.function.BiConsumer;

public class RedwoodTreeFeature extends BOPTreeFeature<TaigaTreeConfiguration>
{
    public RedwoodTreeFeature(Codec<TaigaTreeConfiguration> codec)
    {
        super(codec);
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height, TaigaTreeConfiguration config)
    {
        for (int y = 0; y <= height; y++)
        {

            int trunkWidth = (config.trunkWidth * (height - y) / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            // require 3x3 for the leaves, 1x1 for the trunk
            int start = (y <= baseHeight ? trunkStart : trunkStart - 1);
            int end = (y <= baseHeight ? trunkEnd : trunkEnd + 1);

            for (int x = start; x <= end; x++)
            {
                for (int z = start; z <= end; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.canReplace(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // generates a layer of leafs
    public void generateLeafLayer(LevelAccessor world, Random rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd, BiConsumer<BlockPos, BlockState> leaves, TaigaTreeConfiguration config)
    {
        int start = trunkStart - leavesRadius;
        int end = trunkEnd + leavesRadius;

        for (int x = start; x <= end; x++)
        {
            for (int z = start; z <= end; z++)
            {
                // skip corners
                if ((leavesRadius > 0 ) && (x == start || x == end) && (z == start || z == end)) {continue;}
                int distFromTrunk = (x < 0 ? trunkStart - x : x - trunkEnd) + (z < 0 ? trunkStart - z : z - trunkEnd);

                // set leaves as long as it's not too far from the trunk to survive
                if (distFromTrunk < 4 || (distFromTrunk == 4 && rand.nextInt(2) == 0))
                {
                    this.placeLeaves(world, pos.offset(x, 0, z), leaves, config);
                }
            }
        }
    }

    public void generateBranch(LevelAccessor world, Random rand, BlockPos pos, Direction direction, int length, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, TaigaTreeConfiguration config)
    {
        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.getClockWise();
        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.relative(direction, i);
            int r = (i == 1 || i == length) ? 1 : 2;
            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.placeLeaves(world, pos1.relative(sideways, j), leaves, config);
                }
            }
            if (length - i > 2)
            {
                this.placeLeaves(world, pos1.above(), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), leaves, config);
                this.placeLog(world, pos1, axis, logs, config);
            }
        }
    }


    @Override
    public boolean doPlace(WorldGenLevel world, Random random, BlockPos startPos, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, TreeConfiguration configBase)
    {
        TaigaTreeConfiguration config = (TaigaTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.below();}

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);
        int baseHeight = GeneratorUtil.nextIntBetween(random, (int)(height * 0.6F), (int)(height * 0.4F));
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}

        if (!this.checkSpace(world, startPos.above(), baseHeight, height, config))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        this.placeLeaves(world, pos, leaves, config);
        pos.below();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {
            int trunkWidth = (config.trunkWidth * i / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);


            int radius = Math.min(Math.min((i + 2) / 4, 2 + (leavesHeight - i)), 4);
            if (radius == 0)
            {
                this.placeLeaves(world, pos, leaves, config);
            }
            else if (radius < 2)
            {
                this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd, leaves, config);
            }
            else
            {
                this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius, logs, leaves, config);
                this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius, logs, leaves, config);
                this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius, logs, leaves, config);
                this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius, logs, leaves, config);
            }
            pos = pos.below();
        }

        // Create the trunk widths scales
        double[] scalingFactors = new double[]{
                (0.35 + random.nextDouble() * 0.15),
                (0.07 + random.nextDouble() * 0.1),
                (random.nextDouble() * 0.06)
        };

        if (config.trunkWidth == 3) {
            scalingFactors = new double[]{
                    (0.6 + random.nextDouble() * 0.2),
                    (0.1 + random.nextDouble() * 0.2),
                    (0.03 + random.nextDouble() * 0.09)
            };
        }

        // Generate the trunk
        for (int x = -config.trunkWidth; x <= config.trunkWidth; x++) {
            for (int z = -config.trunkWidth; z <= config.trunkWidth; z++) {
                int dist = Math.abs(x) + Math.abs(z);

                int heightHere = height - 2;

                // If we're not the center of the trunk on a single trunk width, give up
                if (config.trunkWidth == 1 && dist > 0) {
                    continue;
                }

                // Scale bigger widths
                if (dist == 1) {
                    heightHere = (int) (height * scalingFactors[0]);
                } else if (dist == 2) {
                    heightHere = (int) (height * scalingFactors[1]);
                } else if (dist == 3) {
                    heightHere = (int) (height * scalingFactors[2]);
                } else if (dist > 3) {
                    continue;
                }

                heightHere += random.nextInt(2);

                for (int y = 0; y < heightHere; y++)
                {
                    BlockPos local = startPos.offset(x, y, z);
                    this.placeLog(world, local, logs, config);

                    if (dist > 0 && y > 4 && y < (baseHeight - 2) && random.nextInt(10) == 0) {
                        double theta;
                        if (x == 0 && z == 0) {
                            // Prevents bushes originating from the center from generating too low
                            if (y < 10) {
                                continue;
                            }

                            theta = Math.PI * random.nextDouble() * 2;
                        } else {
                            // Make sure the branches only go in the same direction of the current trunk position from the center
                            double angleFromCenter = Math.atan2(x, z);

                            theta = angleFromCenter + (Math.PI * (random.nextDouble() * 0.5 - 0.25));
                        }

                        int branchLength = (3 - dist) + 1 + random.nextInt(2);

                        BlockPos branchPos = null;
                        for (int i = 0; i < branchLength; i++) {
                            branchPos = local.offset(Math.cos(theta) * i, i / 2, Math.sin(theta) * i);

                            this.placeLog(world, branchPos, logs, config);
                        }

                        this.generateBush(logs, leaves, world, random, branchPos, config);
                    }
                }
            }
        }

        return true;
    }

    protected boolean generateBush(BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, LevelAccessor world, Random random, BlockPos pos, TaigaTreeConfiguration config)
    {
        //Generate a bush 3 blocks tall, with the center block set to a log
        for (int y = -1; y < 2; ++y)
        {
            // log in the center
            if (y == 0)
            {
                this.placeLog(world, pos.offset(0, y, 0), logs, config);
            }

            //Reduces the radius closer to the top of the bush
            int leavesRadius = y == 0 ? 2 : 1;

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
