/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class HugeFlowerFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).is(BlockTags.DIRT);
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof VegetationBlock || world.getBlockState(pos).getBlock() instanceof VegetationBlock || world.getBlockState(pos).getBlock() == BOPBlocks.HIGH_GRASS || world.getBlockState(pos).getBlock() == BOPBlocks.HIGH_GRASS_PLANT;

    public HugeFlowerFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        while (startPos.getY() >= world.getMinY()+1 && this.replace.matches(world, startPos)) {
            startPos = startPos.below();
        }

        if (!this.placeOn.matches(world, startPos.offset(0, 0, 0))) {
            // Abandon if we can't place the tree on this block
            return false;
        }

        int height = 3 + rand.nextInt(14);

        if (!this.checkSpace(world, startPos.above(), height)) {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();



        int flowerType = rand.nextInt(6);
        if (flowerType == 5) { height = 2 + rand.nextInt(7); }

        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

        for (int y = 0; y < height; y++)
        {
            this.setBlock(world, pos.above(y), BOPBlocks.FLOWER_STEM.defaultBlockState());

            if (y % 2 == 0)
            {
                direction = direction.getClockWise();

                switch (flowerType)
                {
                    default: case 4: case 5:
                        if (y > 2 && y < height-2 && rand.nextInt(2) == 0) { generateSmallLeaf(world, pos.above(y), direction); }
                        break;

                    case 1: case 2:
                        if (y > 2 && y < height-2 && rand.nextInt(2) == 0) { generateLargeLeaf(world, pos.above(y), direction); }
                        break;

                    case 3:
                        if (rand.nextInt(4) == 0)
                        {
                            if (y > 2 && y < height-2) { generateLargeLeaf(world, pos.above(y), direction); }
                        }
                        else
                        {
                            if (y > 0 && y < height) { this.setBlock(world, pos.above(y).relative(direction, 1), BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState()); }
                        }
                        break;
                }
            }
        }

        switch (flowerType)
        {
            default:
                generateDandelionPuff(world, pos, height);
                break;

            case 1:
                generateSunflower(world, pos, height);
                break;

            case 2:
                generateHyacinth(world, pos, height);
                break;

            case 3:
                generateRose(world, pos, height);
                break;

            case 4:
                generateDaffodil(world, pos, height);
                break;

            case 5:
                generateFlowerBud(world, pos, height);
                break;
        }

        return true;
    }

    public void generateFlowerBud(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState budColor;

        switch (flowerColor)
        {
            default:
                budColor = BOPBlocks.LIGHT_GRAY_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                budColor = BOPBlocks.PINK_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                budColor = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, 0, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, 0, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 0, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 0, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, 1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, 1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 1, -1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 1, 1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, 1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-1, 1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, 1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, 1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, 2, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, 2, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 2, -1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 2, 1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(0, 1, 0), budColor);
        this.setBlock(world, pos.offset(0, 2, 0), budColor);
        this.setBlock(world, pos.offset(0, 3, 0), budColor);
    }

    public void generateDandelionPuff(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState innerDark;
        BlockState innerLight;
        BlockState outerDark;
        BlockState outerLight;

        switch (flowerColor)
        {
            default:
                innerDark = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.GRAY_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.LIGHT_GRAY_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.WHITE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.YELLOW_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, innerDark);

        this.setBlock(world, pos.offset(-1, 0, 0), innerLight);
        this.setBlock(world, pos.offset(1, 0, 0), innerLight);
        this.setBlock(world, pos.offset(0, 0, -1), innerLight);
        this.setBlock(world, pos.offset(0, 0, 1), innerLight);
        this.setBlock(world, pos.offset(0, -1, 0), innerLight);
        this.setBlock(world, pos.offset(0, 1, 0), innerLight);

        this.setBlock(world, pos.offset(-1, 0, -1), outerDark);
        this.setBlock(world, pos.offset(-1, 0, 1), outerDark);
        this.setBlock(world, pos.offset(1, 0, -1), outerDark);
        this.setBlock(world, pos.offset(1, 0, 1), outerDark);
        this.setBlock(world, pos.offset(-1, 1, 0), outerDark);
        this.setBlock(world, pos.offset(1, 1, 0), outerDark);
        this.setBlock(world, pos.offset(0, 1, -1), outerDark);
        this.setBlock(world, pos.offset(0, 1, 1), outerDark);
        this.setBlock(world, pos.offset(-1, -1, 0), outerDark);
        this.setBlock(world, pos.offset(1, -1, 0), outerDark);
        this.setBlock(world, pos.offset(0, -1, -1), outerDark);
        this.setBlock(world, pos.offset(0, -1, 1), outerDark);

        this.setBlock(world, pos.offset(-2, 0, 0), outerDark);
        this.setBlock(world, pos.offset(2, 0, 0), outerDark);
        this.setBlock(world, pos.offset(0, 0, -2), outerDark);
        this.setBlock(world, pos.offset(0, 0, 2), outerDark);
        this.setBlock(world, pos.offset(0, -2, 0), outerDark);
        this.setBlock(world, pos.offset(0, 2, 0), outerDark);

        this.setBlock(world, pos.offset(-1, 1, -1), outerLight);
        this.setBlock(world, pos.offset(-1, 1, 1), outerLight);
        this.setBlock(world, pos.offset(1, 1, -1), outerLight);
        this.setBlock(world, pos.offset(1, 1, 1), outerLight);
        this.setBlock(world, pos.offset(-1, -1, -1), outerLight);
        this.setBlock(world, pos.offset(-1, -1, 1), outerLight);
        this.setBlock(world, pos.offset(1, -1, -1), outerLight);
        this.setBlock(world, pos.offset(1, -1, 1), outerLight);

        this.setBlock(world, pos.offset(-3, 0, 0), outerLight);
        this.setBlock(world, pos.offset(3, 0, 0), outerLight);
        this.setBlock(world, pos.offset(0, 0, -3), outerLight);
        this.setBlock(world, pos.offset(0, 0, 3), outerLight);
        this.setBlock(world, pos.offset(0, -3, 0), outerLight);
        this.setBlock(world, pos.offset(0, 3, 0), outerLight);
        this.setBlock(world, pos.offset(-2, 0, -2), outerLight);
        this.setBlock(world, pos.offset(-2, 0, 2), outerLight);
        this.setBlock(world, pos.offset(2, 0, -2), outerLight);
        this.setBlock(world, pos.offset(2, 0, 2), outerLight);

        this.setBlock(world, pos.offset(-2, -2, 0), outerLight);
        this.setBlock(world, pos.offset(2, -2, 0), outerLight);
        this.setBlock(world, pos.offset(-2, 2, 0), outerLight);
        this.setBlock(world, pos.offset(2, 2, 0), outerLight);
        this.setBlock(world, pos.offset(0, -2, -2), outerLight);
        this.setBlock(world, pos.offset(0, -2, 2), outerLight);
        this.setBlock(world, pos.offset(0, 2, -2), outerLight);
        this.setBlock(world, pos.offset(0, 2, 2), outerLight);
        this.setBlock(world, pos.offset(-2, 0, 0), outerLight);
        this.setBlock(world, pos.offset(2, 0, 0), outerLight);
        this.setBlock(world, pos.offset(0, 0, -2), outerLight);
        this.setBlock(world, pos.offset(0, 0, 2), outerLight);

        this.setBlock(world, pos.offset(-1, -2, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -2, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -3, -1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -3, 1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
    }

    public void generateSunflower(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState innerDark;
        BlockState innerLight;
        BlockState outerDark;
        BlockState outerLight;

        switch (flowerColor)
        {
            default:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.YELLOW_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.RED_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerDark = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerLight = BOPBlocks.RED_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, innerLight);

        this.setBlock(world, pos.offset(-1, 0, -1), innerLight);
        this.setBlock(world, pos.offset(-1, 0, 1), innerLight);
        this.setBlock(world, pos.offset(1, 0, -1), innerLight);
        this.setBlock(world, pos.offset(1, 0, 1), innerLight);
        this.setBlock(world, pos.offset(-1, 0, 0), innerDark);
        this.setBlock(world, pos.offset(1, 0, 0), innerDark);
        this.setBlock(world, pos.offset(0, 0, -1), innerDark);
        this.setBlock(world, pos.offset(0, 0, 1), innerDark);

        this.setBlock(world, pos.offset(-2, 0, -1), outerDark);
        this.setBlock(world, pos.offset(-2, 0, 0), outerDark);
        this.setBlock(world, pos.offset(-2, 0, 1), outerDark);
        this.setBlock(world, pos.offset(2, 0, -1), outerDark);
        this.setBlock(world, pos.offset(2, 0, 0), outerDark);
        this.setBlock(world, pos.offset(2, 0, 1), outerDark);
        this.setBlock(world, pos.offset(-1, 0, -2), outerDark);
        this.setBlock(world, pos.offset(0, 0, -2), outerDark);
        this.setBlock(world, pos.offset(1, 0, -2), outerDark);
        this.setBlock(world, pos.offset(-1, 0, 2), outerDark);
        this.setBlock(world, pos.offset(0, 0, 2), outerDark);
        this.setBlock(world, pos.offset(1, 0, 2), outerDark);

        this.setBlock(world, pos.offset(-2, 0, -2), outerLight);
        this.setBlock(world, pos.offset(-2, 0, 2), outerLight);
        this.setBlock(world, pos.offset(2, 0, -2), outerLight);
        this.setBlock(world, pos.offset(2, 0, 2), outerLight);

        this.setBlock(world, pos.offset(-3, 0, -1), outerLight);
        this.setBlock(world, pos.offset(-3, 0, 0), outerLight);
        this.setBlock(world, pos.offset(-3, 0, 1), outerLight);
        this.setBlock(world, pos.offset(-4, 0, -1), outerLight);
        this.setBlock(world, pos.offset(-4, 0, 0), outerLight);
        this.setBlock(world, pos.offset(-4, 0, 1), outerLight);
        this.setBlock(world, pos.offset(-5, 0, 0), outerLight);

        this.setBlock(world, pos.offset(3, 0, -1), outerLight);
        this.setBlock(world, pos.offset(3, 0, 0), outerLight);
        this.setBlock(world, pos.offset(3, 0, 1), outerLight);
        this.setBlock(world, pos.offset(4, 0, -1), outerLight);
        this.setBlock(world, pos.offset(4, 0, 0), outerLight);
        this.setBlock(world, pos.offset(4, 0, 1), outerLight);
        this.setBlock(world, pos.offset(5, 0, 0), outerLight);

        this.setBlock(world, pos.offset(-1, 0, -3), outerLight);
        this.setBlock(world, pos.offset(0, 0, -3), outerLight);
        this.setBlock(world, pos.offset(1, 0, -3), outerLight);
        this.setBlock(world, pos.offset(-1, 0, -4), outerLight);
        this.setBlock(world, pos.offset(0, 0, -4), outerLight);
        this.setBlock(world, pos.offset(1, 0, -4), outerLight);
        this.setBlock(world, pos.offset(0, 0, -5), outerLight);

        this.setBlock(world, pos.offset(-1, 0, 3), outerLight);
        this.setBlock(world, pos.offset(0, 0, 3), outerLight);
        this.setBlock(world, pos.offset(1, 0, 3), outerLight);
        this.setBlock(world, pos.offset(-1, 0, 4), outerLight);
        this.setBlock(world, pos.offset(0, 0, 4), outerLight);
        this.setBlock(world, pos.offset(1, 0, 4), outerLight);
        this.setBlock(world, pos.offset(0, 0, 5), outerLight);

        this.setBlock(world, pos.offset(-3, -1, -2), outerDark);
        this.setBlock(world, pos.offset(-2, -1, -3), outerDark);
        this.setBlock(world, pos.offset(-3, -1, -3), outerLight);
        this.setBlock(world, pos.offset(-4, -1, -3), outerLight);
        this.setBlock(world, pos.offset(-3, -1, -4), outerLight);
        this.setBlock(world, pos.offset(-4, -1, -4), outerLight);

        this.setBlock(world, pos.offset(-3, -1, 2), outerDark);
        this.setBlock(world, pos.offset(-2, -1, 3), outerDark);
        this.setBlock(world, pos.offset(-3, -1, 3), outerLight);
        this.setBlock(world, pos.offset(-4, -1, 3), outerLight);
        this.setBlock(world, pos.offset(-3, -1, 4), outerLight);
        this.setBlock(world, pos.offset(-4, -1, 4), outerLight);

        this.setBlock(world, pos.offset(3, -1, -2), outerDark);
        this.setBlock(world, pos.offset(2, -1, -3), outerDark);
        this.setBlock(world, pos.offset(3, -1, -3), outerLight);
        this.setBlock(world, pos.offset(4, -1, -3), outerLight);
        this.setBlock(world, pos.offset(3, -1, -4), outerLight);
        this.setBlock(world, pos.offset(4, -1, -4), outerLight);

        this.setBlock(world, pos.offset(3, -1, 2), outerDark);
        this.setBlock(world, pos.offset(2, -1, 3), outerDark);
        this.setBlock(world, pos.offset(3, -1, 3), outerLight);
        this.setBlock(world, pos.offset(4, -1, 3), outerLight);
        this.setBlock(world, pos.offset(3, -1, 4), outerLight);
        this.setBlock(world, pos.offset(4, -1, 4), outerLight);

        this.setBlock(world, pos.offset(-1, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, -1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, 1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-1, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-2, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-2, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-2, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(2, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(2, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(2, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-1, -1, -2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, -2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -1, -2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-1, -1, 2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, 2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(1, -1, 2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-2, -2, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(2, -2, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, -2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, 2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(-3, -2, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(3, -2, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, -3), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -2, 3), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
    }

    public void generateHyacinth(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState bottomDark;
        BlockState bottomLight;
        BlockState topDark;
        BlockState topLight;

        switch (flowerColor)
        {
            default:
                bottomDark = BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.BLUE_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.LIGHT_BLUE_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.CYAN_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                bottomDark = BOPBlocks.BLUE_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.LIGHT_BLUE_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.CYAN_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                bottomDark = BOPBlocks.LIGHT_BLUE_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.CYAN_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(3), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(4), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(5), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(6), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(7), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(8), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.above(9), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-2, 0, 0), bottomDark);
        this.setBlock(world, pos.offset(2, 0, 0), bottomDark);
        this.setBlock(world, pos.offset(0, 0, -2), bottomDark);
        this.setBlock(world, pos.offset(0, 0, 2), bottomDark);

        this.setBlock(world, pos.offset(-1, 1, -1), bottomDark);
        this.setBlock(world, pos.offset(-1, 1, 1), bottomDark);
        this.setBlock(world, pos.offset(1, 1, -1), bottomDark);
        this.setBlock(world, pos.offset(1, 1, 1), bottomDark);

        this.setBlock(world, pos.offset(-1, 2, 0), bottomLight);
        this.setBlock(world, pos.offset(1, 2, 0), bottomLight);
        this.setBlock(world, pos.offset(0, 2, -1), bottomLight);
        this.setBlock(world, pos.offset(0, 2, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 2, -1), bottomLight);
        this.setBlock(world, pos.offset(-1, 2, 1), bottomLight);
        this.setBlock(world, pos.offset(1, 2, -1), bottomLight);
        this.setBlock(world, pos.offset(1, 2, 1), bottomLight);
        this.setBlock(world, pos.offset(-2, 2, -1), bottomLight);
        this.setBlock(world, pos.offset(-2, 2, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 2, -2), bottomLight);
        this.setBlock(world, pos.offset(1, 2, -2), bottomLight);
        this.setBlock(world, pos.offset(2, 2, -1), bottomLight);
        this.setBlock(world, pos.offset(2, 2, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 2, 2), bottomLight);
        this.setBlock(world, pos.offset(1, 2, 2), bottomLight);

        this.setBlock(world, pos.offset(-1, 3, -1), bottomLight);
        this.setBlock(world, pos.offset(-1, 3, 1), bottomLight);
        this.setBlock(world, pos.offset(1, 3, -1), bottomLight);
        this.setBlock(world, pos.offset(1, 3, 1), bottomLight);

        this.setBlock(world, pos.offset(-1, 4, 0), bottomLight);
        this.setBlock(world, pos.offset(1, 4, 0), bottomLight);
        this.setBlock(world, pos.offset(0, 4, -1), bottomLight);
        this.setBlock(world, pos.offset(0, 4, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 4, -1), bottomLight);
        this.setBlock(world, pos.offset(-1, 4, 1), bottomLight);
        this.setBlock(world, pos.offset(1, 4, -1), bottomLight);
        this.setBlock(world, pos.offset(1, 4, 1), bottomLight);
        this.setBlock(world, pos.offset(-2, 4, -1), bottomLight);
        this.setBlock(world, pos.offset(-2, 4, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 4, -2), bottomLight);
        this.setBlock(world, pos.offset(1, 4, -2), bottomLight);
        this.setBlock(world, pos.offset(2, 4, -1), bottomLight);
        this.setBlock(world, pos.offset(2, 4, 1), bottomLight);
        this.setBlock(world, pos.offset(-1, 4, 2), bottomLight);
        this.setBlock(world, pos.offset(1, 4, 2), bottomLight);

        this.setBlock(world, pos.offset(-1, 5, -1), topDark);
        this.setBlock(world, pos.offset(-1, 5, 1), topDark);
        this.setBlock(world, pos.offset(1, 5, -1), topDark);
        this.setBlock(world, pos.offset(1, 5, 1), topDark);

        this.setBlock(world, pos.offset(-1, 6, 0), topDark);
        this.setBlock(world, pos.offset(1, 6, 0), topDark);
        this.setBlock(world, pos.offset(0, 6, -1), topDark);
        this.setBlock(world, pos.offset(0, 6, 1), topDark);
        this.setBlock(world, pos.offset(-1, 6, -1), topDark);
        this.setBlock(world, pos.offset(-1, 6, 1), topDark);
        this.setBlock(world, pos.offset(1, 6, -1), topDark);
        this.setBlock(world, pos.offset(1, 6, 1), topDark);
        this.setBlock(world, pos.offset(-2, 6, -1), topDark);
        this.setBlock(world, pos.offset(-2, 6, 1), topDark);
        this.setBlock(world, pos.offset(-1, 6, -2), topDark);
        this.setBlock(world, pos.offset(1, 6, -2), topDark);
        this.setBlock(world, pos.offset(2, 6, -1), topDark);
        this.setBlock(world, pos.offset(2, 6, 1), topDark);
        this.setBlock(world, pos.offset(-1, 6, 2), topDark);
        this.setBlock(world, pos.offset(1, 6, 2), topDark);

        this.setBlock(world, pos.offset(-1, 7, -1), topDark);
        this.setBlock(world, pos.offset(-1, 7, 1), topDark);
        this.setBlock(world, pos.offset(1, 7, -1), topDark);
        this.setBlock(world, pos.offset(1, 7, 1), topDark);

        this.setBlock(world, pos.offset(-1, 8, 0), topLight);
        this.setBlock(world, pos.offset(1, 8, 0), topLight);
        this.setBlock(world, pos.offset(0, 8, -1), topLight);
        this.setBlock(world, pos.offset(0, 8, 1), topLight);
        this.setBlock(world, pos.offset(-1, 8, -1), topLight);
        this.setBlock(world, pos.offset(-1, 8, 1), topLight);
        this.setBlock(world, pos.offset(1, 8, -1), topLight);
        this.setBlock(world, pos.offset(1, 8, 1), topLight);
        this.setBlock(world, pos.offset(-2, 8, -1), topLight);
        this.setBlock(world, pos.offset(-2, 8, 1), topLight);
        this.setBlock(world, pos.offset(-1, 8, -2), topLight);
        this.setBlock(world, pos.offset(1, 8, -2), topLight);
        this.setBlock(world, pos.offset(2, 8, -1), topLight);
        this.setBlock(world, pos.offset(2, 8, 1), topLight);
        this.setBlock(world, pos.offset(-1, 8, 2), topLight);
        this.setBlock(world, pos.offset(1, 8, 2), topLight);

        this.setBlock(world, pos.offset(-1, 9, -1), topLight);
        this.setBlock(world, pos.offset(-1, 9, 1), topLight);
        this.setBlock(world, pos.offset(1, 9, -1), topLight);
        this.setBlock(world, pos.offset(1, 9, 1), topLight);

        this.setBlock(world, pos.above(10), topLight);

        this.setBlock(world, pos.offset(-1, 11, -1), topLight);
        this.setBlock(world, pos.offset(-1, 11, 1), topLight);
        this.setBlock(world, pos.offset(1, 11, -1), topLight);
        this.setBlock(world, pos.offset(1, 11, 1), topLight);
    }

    public void generateRose(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState bottomDark;
        BlockState bottomLight;
        BlockState topDark;
        BlockState topLight;

        switch (flowerColor)
        {
            default:
                bottomDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.RED_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                bottomDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.GRAY_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.LIGHT_GRAY_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.WHITE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                bottomDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                bottomLight = BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK.defaultBlockState();
                topDark = BOPBlocks.MAGENTA_FLOWER_PETAL_BLOCK.defaultBlockState();
                topLight = BOPBlocks.PINK_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, bottomDark);

        this.setBlock(world, pos.offset(-1, 0, -1), bottomDark);
        this.setBlock(world, pos.offset(-1, 0, 1), bottomDark);
        this.setBlock(world, pos.offset(1, 0, -1), bottomDark);
        this.setBlock(world, pos.offset(1, 0, 1), bottomDark);

        this.setBlock(world, pos.offset(-1, 0, 0), bottomLight);
        this.setBlock(world, pos.offset(1, 0, 0), bottomLight);
        this.setBlock(world, pos.offset(0, 0, -1), bottomLight);
        this.setBlock(world, pos.offset(0, 0, 1), bottomLight);

        this.setBlock(world, pos.offset(-2, 1, 1), bottomLight);
        this.setBlock(world, pos.offset(-2, 1, -1), bottomLight);
        this.setBlock(world, pos.offset(2, 1, 1), bottomLight);
        this.setBlock(world, pos.offset(2, 1, -1), bottomLight);
        this.setBlock(world, pos.offset(1, 1, -2), bottomLight);
        this.setBlock(world, pos.offset(-1, 1, -2), bottomLight);
        this.setBlock(world, pos.offset(1, 1, 2), bottomLight);
        this.setBlock(world, pos.offset(-1, 1, 2), bottomLight);

        this.setBlock(world, pos.offset(-1, 1, 0), topDark);
        this.setBlock(world, pos.offset(1, 1, 0), topDark);
        this.setBlock(world, pos.offset(0, 1, -1), topDark);
        this.setBlock(world, pos.offset(0, 1, 1), topDark);
        this.setBlock(world, pos.offset(-1, 1, -1), topDark);
        this.setBlock(world, pos.offset(-1, 1, 1), topDark);
        this.setBlock(world, pos.offset(1, 1, -1), topDark);
        this.setBlock(world, pos.offset(1, 1, 1), topDark);
        this.setBlock(world, pos.offset(-2, 1, 0), topDark);
        this.setBlock(world, pos.offset(2, 1, 0), topDark);
        this.setBlock(world, pos.offset(0, 1, -2), topDark);
        this.setBlock(world, pos.offset(0, 1, 2), topDark);

        this.setBlock(world, pos.offset(-2, 2, -2), topDark);
        this.setBlock(world, pos.offset(-2, 2, 2), topDark);
        this.setBlock(world, pos.offset(2, 2, -2), topDark);
        this.setBlock(world, pos.offset(2, 2, 2), topDark);

        this.setBlock(world, pos.offset(-1, 2, 0), topDark);
        this.setBlock(world, pos.offset(1, 2, 0), topDark);
        this.setBlock(world, pos.offset(0, 2, -1), topDark);
        this.setBlock(world, pos.offset(0, 2, 1), topDark);
        this.setBlock(world, pos.offset(-1, 2, -1), topDark);
        this.setBlock(world, pos.offset(-1, 2, 1), topDark);
        this.setBlock(world, pos.offset(1, 2, -1), topDark);
        this.setBlock(world, pos.offset(1, 2, 1), topDark);

        this.setBlock(world, pos.offset(-3, 2, -1), topDark);
        this.setBlock(world, pos.offset(-3, 2, 1), topDark);
        this.setBlock(world, pos.offset(-1, 2, -3), topDark);
        this.setBlock(world, pos.offset(1, 2, -3), topDark);
        this.setBlock(world, pos.offset(3, 2, -1), topDark);
        this.setBlock(world, pos.offset(3, 2, 1), topDark);
        this.setBlock(world, pos.offset(-1, 2, 3), topDark);
        this.setBlock(world, pos.offset(1, 2, 3), topDark);
        this.setBlock(world, pos.offset(-2, 2, -1), topDark);
        this.setBlock(world, pos.offset(-2, 2, 0), topDark);
        this.setBlock(world, pos.offset(-2, 2, 1), topDark);
        this.setBlock(world, pos.offset(2, 2, -1), topDark);
        this.setBlock(world, pos.offset(2, 2, 0), topDark);
        this.setBlock(world, pos.offset(2, 2, 1), topDark);
        this.setBlock(world, pos.offset(-1, 2, -2), topDark);
        this.setBlock(world, pos.offset(0, 2, -2), topDark);
        this.setBlock(world, pos.offset(1, 2, -2), topDark);
        this.setBlock(world, pos.offset(-1, 2, 2), topDark);
        this.setBlock(world, pos.offset(0, 2, 2), topDark);
        this.setBlock(world, pos.offset(1, 2, 2), topDark);

        this.setBlock(world, pos.offset(-3, 3, -2), topDark);
        this.setBlock(world, pos.offset(-3, 3, 2), topDark);
        this.setBlock(world, pos.offset(-2, 3, -3), topDark);
        this.setBlock(world, pos.offset(2, 3, -3), topDark);
        this.setBlock(world, pos.offset(3, 3, -2), topDark);
        this.setBlock(world, pos.offset(3, 3, 2), topDark);
        this.setBlock(world, pos.offset(-2, 3, 3), topDark);
        this.setBlock(world, pos.offset(2, 3, 3), topDark);
        this.setBlock(world, pos.offset(-3, 4, -2), topDark);
        this.setBlock(world, pos.offset(-3, 4, 2), topDark);
        this.setBlock(world, pos.offset(-2, 4, -3), topDark);
        this.setBlock(world, pos.offset(2, 4, -3), topDark);
        this.setBlock(world, pos.offset(3, 4, -2), topDark);
        this.setBlock(world, pos.offset(3, 4, 2), topDark);
        this.setBlock(world, pos.offset(-2, 4, 3), topDark);
        this.setBlock(world, pos.offset(2, 4, 3), topDark);

        this.setBlock(world, pos.offset(-1, 3, -1), topDark);
        this.setBlock(world, pos.offset(-1, 3, 1), topDark);
        this.setBlock(world, pos.offset(1, 3, -1), topDark);
        this.setBlock(world, pos.offset(1, 3, 1), topDark);

        this.setBlock(world, pos.offset(-3, 3, -1), topDark);
        this.setBlock(world, pos.offset(-3, 3, 1), topDark);
        this.setBlock(world, pos.offset(-1, 3, -3), topDark);
        this.setBlock(world, pos.offset(1, 3, -3), topDark);
        this.setBlock(world, pos.offset(3, 3, -1), topDark);
        this.setBlock(world, pos.offset(3, 3, 1), topDark);
        this.setBlock(world, pos.offset(-1, 3, 3), topDark);
        this.setBlock(world, pos.offset(1, 3, 3), topDark);

        this.setBlock(world, pos.offset(-2, 3, -1), topLight);
        this.setBlock(world, pos.offset(-2, 3, 0), topLight);
        this.setBlock(world, pos.offset(-2, 3, 1), topLight);
        this.setBlock(world, pos.offset(2, 3, -1), topLight);
        this.setBlock(world, pos.offset(2, 3, 0), topLight);
        this.setBlock(world, pos.offset(2, 3, 1), topLight);
        this.setBlock(world, pos.offset(-1, 3, -2), topLight);
        this.setBlock(world, pos.offset(0, 3, -2), topLight);
        this.setBlock(world, pos.offset(1, 3, -2), topLight);
        this.setBlock(world, pos.offset(-1, 3, 2), topLight);
        this.setBlock(world, pos.offset(0, 3, 2), topLight);
        this.setBlock(world, pos.offset(1, 3, 2), topLight);

        this.setBlock(world, pos.offset(-2, 4, -1), topLight);
        this.setBlock(world, pos.offset(-2, 4, 0), topLight);
        this.setBlock(world, pos.offset(-2, 4, 1), topLight);
        this.setBlock(world, pos.offset(2, 4, -1), topLight);
        this.setBlock(world, pos.offset(2, 4, 0), topLight);
        this.setBlock(world, pos.offset(2, 4, 1), topLight);
        this.setBlock(world, pos.offset(-1, 4, -2), topLight);
        this.setBlock(world, pos.offset(0, 4, -2), topLight);
        this.setBlock(world, pos.offset(1, 4, -2), topLight);
        this.setBlock(world, pos.offset(-1, 4, 2), topLight);
        this.setBlock(world, pos.offset(0, 4, 2), topLight);
        this.setBlock(world, pos.offset(1, 4, 2), topLight);

        this.setBlock(world, pos.offset(-2, 5, 0), topDark);
        this.setBlock(world, pos.offset(2, 5, 0), topDark);
        this.setBlock(world, pos.offset(0, 5, -2), topDark);
        this.setBlock(world, pos.offset(0, 5, 2), topDark);

        if (world.getRandom().nextInt(2) == 0)
        {
            this.setBlock(world, pos.offset(-1, -1, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-2, 0, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-2, 0, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-2, 0, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-3, 0, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-3, 0, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-3, 0, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-4, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

            this.setBlock(world, pos.offset(1, -1, 0), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(2, 0, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(2, 0, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(2, 0, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(3, 0, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(3, 0, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(3, 0, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(4, -1, 0), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        }
        else
        {
            this.setBlock(world, pos.offset(0, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, 0, -2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-1, 0, -2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(1, 0, -2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, 0, -3), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-1, 0, -3), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(1, 0, -3), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, -1, -4), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

            this.setBlock(world, pos.offset(0, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, 0, 2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-1, 0, 2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(1, 0, 2), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, 0, 3), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(-1, 0, 3), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(1, 0, 3), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
            this.setBlock(world, pos.offset(0, -1, 4), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        }
    }

    public void generateDaffodil(WorldGenLevel world, BlockPos pos, int height)
    {
        int flowerColor = world.getRandom().nextInt(7);
        BlockState innerDark;
        BlockState innerMid;
        BlockState innerLight;
        BlockState outerColor;

        switch (flowerColor)
        {
            default:
                innerDark = BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerMid = BOPBlocks.MAGENTA_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.PINK_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerColor = BOPBlocks.WHITE_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 1:
                innerDark = BOPBlocks.BROWN_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerMid = BOPBlocks.RED_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.ORANGE_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerColor = BOPBlocks.YELLOW_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;

            case 2:
                innerDark = BOPBlocks.BLACK_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerMid = BOPBlocks.PURPLE_FLOWER_PETAL_BLOCK.defaultBlockState();
                innerLight = BOPBlocks.MAGENTA_FLOWER_PETAL_BLOCK.defaultBlockState();
                outerColor = BOPBlocks.PINK_FLOWER_PETAL_BLOCK.defaultBlockState();
                break;
        }

        pos = pos.above(height);

        this.setBlock(world, pos, innerDark);

        this.setBlock(world, pos.offset(-1, 0, -1), innerLight);
        this.setBlock(world, pos.offset(-1, 0, 1), innerLight);
        this.setBlock(world, pos.offset(1, 0, -1), innerLight);
        this.setBlock(world, pos.offset(1, 0, 1), innerLight);

        this.setBlock(world, pos.offset(-2, 0, 0), innerLight);
        this.setBlock(world, pos.offset(2, 0, 0), innerLight);

        this.setBlock(world, pos.offset(-1, 0, 0), outerColor);
        this.setBlock(world, pos.offset(1, 0, 0), outerColor);

        this.setBlock(world, pos.offset(0, 0, -1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 0, 1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 0, -2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, 0, 2), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, -1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.offset(0, -1, 1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.offset(-1, 1, -2), innerLight);
        this.setBlock(world, pos.offset(1, 1, -2), innerLight);
        this.setBlock(world, pos.offset(-1, 1, 2), innerLight);
        this.setBlock(world, pos.offset(1, 1, 2), innerLight);

        this.setBlock(world, pos.offset(-2, 1, -2), outerColor);
        this.setBlock(world, pos.offset(-2, 1, 2), outerColor);
        this.setBlock(world, pos.offset(2, 1, -2), outerColor);
        this.setBlock(world, pos.offset(2, 1, 2), outerColor);
        this.setBlock(world, pos.offset(-1, 1, -3), outerColor);
        this.setBlock(world, pos.offset(1, 1, -3), outerColor);
        this.setBlock(world, pos.offset(-1, 1, 3), outerColor);
        this.setBlock(world, pos.offset(1, 1, 3), outerColor);
        this.setBlock(world, pos.offset(-2, 1, -3), outerColor);
        this.setBlock(world, pos.offset(2, 1, -3), outerColor);
        this.setBlock(world, pos.offset(-2, 1, 3), outerColor);
        this.setBlock(world, pos.offset(2, 1, 3), outerColor);

        this.setBlock(world, pos.offset(-2, 0, -1), outerColor);
        this.setBlock(world, pos.offset(-2, 0, 1), outerColor);
        this.setBlock(world, pos.offset(-3, 0, 0), outerColor);

        this.setBlock(world, pos.offset(2, 0, -1), outerColor);
        this.setBlock(world, pos.offset(2, 0, 1), outerColor);
        this.setBlock(world, pos.offset(3, 0, 0), outerColor);

        this.setBlock(world, pos.offset(-1, 1, 0), innerDark);
        this.setBlock(world, pos.offset(1, 1, 0), innerDark);
        this.setBlock(world, pos.offset(0, 1, -1), innerDark);
        this.setBlock(world, pos.offset(0, 1, 1), innerDark);

        this.setBlock(world, pos.offset(-1, 2, 0), innerMid);
        this.setBlock(world, pos.offset(1, 2, 0), innerMid);
        this.setBlock(world, pos.offset(0, 2, -1), innerMid);
        this.setBlock(world, pos.offset(0, 2, 1), innerMid);

        this.setBlock(world, pos.offset(-1, 3, -1), innerMid);
        this.setBlock(world, pos.offset(-1, 3, 1), innerMid);
        this.setBlock(world, pos.offset(1, 3, -1), innerMid);
        this.setBlock(world, pos.offset(1, 3, 1), innerMid);

        this.setBlock(world, pos.offset(0, 1, 0), innerMid);
        this.setBlock(world, pos.offset(0, 2, 0), innerLight);
        this.setBlock(world, pos.offset(0, 3, 0), innerLight);
        this.setBlock(world, pos.offset(0, 4, 0), outerColor);

        this.setBlock(world, pos.offset(-2, 3, 0), innerLight);
        this.setBlock(world, pos.offset(2, 3, 0), innerLight);
        this.setBlock(world, pos.offset(0, 3, -2), innerLight);
        this.setBlock(world, pos.offset(0, 3, 2), innerLight);

        this.setBlock(world, pos.offset(-2, 4, -1), innerLight);
        this.setBlock(world, pos.offset(-2, 4, 0), innerLight);
        this.setBlock(world, pos.offset(-2, 4, 1), innerLight);
        this.setBlock(world, pos.offset(2, 4, -1), innerLight);
        this.setBlock(world, pos.offset(2, 4, 0), innerLight);
        this.setBlock(world, pos.offset(2, 4, 1), innerLight);
        this.setBlock(world, pos.offset(-1, 4, -2), innerLight);
        this.setBlock(world, pos.offset(0, 4, -2), innerLight);
        this.setBlock(world, pos.offset(1, 4, -2), innerLight);
        this.setBlock(world, pos.offset(-1, 4, 2), innerLight);
        this.setBlock(world, pos.offset(0, 4, 2), innerLight);
        this.setBlock(world, pos.offset(1, 4, 2), innerLight);

        this.setBlock(world, pos.offset(-2, 5, -1), innerLight);
        this.setBlock(world, pos.offset(-2, 5, 0), innerLight);
        this.setBlock(world, pos.offset(-2, 5, 1), innerLight);
        this.setBlock(world, pos.offset(2, 5, -1), innerLight);
        this.setBlock(world, pos.offset(2, 5, 0), innerLight);
        this.setBlock(world, pos.offset(2, 5, 1), innerLight);
        this.setBlock(world, pos.offset(-1, 5, -2), innerLight);
        this.setBlock(world, pos.offset(0, 5, -2), innerLight);
        this.setBlock(world, pos.offset(1, 5, -2), innerLight);
        this.setBlock(world, pos.offset(-1, 5, 2), innerLight);
        this.setBlock(world, pos.offset(0, 5, 2), innerLight);
        this.setBlock(world, pos.offset(1, 5, 2), innerLight);

        this.setBlock(world, pos.offset(-2, 6, -1), innerLight);
        this.setBlock(world, pos.offset(-2, 6, 1), innerLight);
        this.setBlock(world, pos.offset(2, 6, -1), innerLight);
        this.setBlock(world, pos.offset(2, 6, 1), innerLight);
        this.setBlock(world, pos.offset(-1, 6, -2), innerLight);
        this.setBlock(world, pos.offset(1, 6, -2), innerLight);
        this.setBlock(world, pos.offset(-1, 6, 2), innerLight);
        this.setBlock(world, pos.offset(1, 6, 2), innerLight);
    }

        public void generateSmallLeaf(WorldGenLevel world, BlockPos pos, Direction direction)
    {
        this.setBlock(world, pos.relative(direction, 1), BOPBlocks.FLOWER_STEM.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        this.setBlock(world, pos.relative(direction, 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 2).above(1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
    }

    public void generateLargeLeaf(WorldGenLevel world, BlockPos pos, Direction direction)
    {
        this.setBlock(world, pos.relative(direction, 1), BOPBlocks.FLOWER_STEM.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
        this.setBlock(world, pos.relative(direction, 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 2).above(1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 3).above(1), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());

        this.setBlock(world, pos.relative(direction, 2).relative(direction.getClockWise(), 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 2).relative(direction.getCounterClockWise(), 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 3).relative(direction.getClockWise(), 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 3).relative(direction.getCounterClockWise(), 1).above(1), BOPBlocks.GREEN_FLOWER_PETAL_BLOCK.defaultBlockState());
        this.setBlock(world, pos.relative(direction, 4), BOPBlocks.LIME_FLOWER_PETAL_BLOCK.defaultBlockState());
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, int height)
    {
        for (int y = 0; y <= height; y++)
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

        for (int y = (height-1); y <= (height+10); y++)
        {
            for (int x = -3; x <= 3; x++)
            {
                for (int z = -3; z <= 3; z++)
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
