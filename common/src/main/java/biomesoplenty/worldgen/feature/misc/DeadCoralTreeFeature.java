package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.CoralTreeFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Iterator;
import java.util.Optional;

public class DeadCoralTreeFeature extends CoralTreeFeature
{
    public DeadCoralTreeFeature(Codec<NoneFeatureConfiguration> $$0)
    {
        super($$0);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> $$0) {
        RandomSource $$1 = $$0.random();
        WorldGenLevel $$2 = $$0.level();
        BlockPos $$3 = $$0.origin();
        Optional<Block> $$4 = BuiltInRegistries.BLOCK.getTag(ModTags.Blocks.DEAD_CORAL_BLOCKS).flatMap(($$1x) -> {
            return $$1x.getRandomElement($$1);
        }).map(Holder::value);
        return $$4.isEmpty() ? false : this.placeFeature($$2, $$1, $$3, ((Block)$$4.get()).defaultBlockState());
    }

    @Override
    protected boolean placeCoralBlock(LevelAccessor $$0, RandomSource $$1, BlockPos $$2, BlockState $$3)
    {
        BlockPos $$4 = $$2.above();
        BlockState $$5 = $$0.getBlockState($$2);
        if (($$5.isAir() || $$5.is(ModTags.Blocks.DEAD_CORALS) || $$5.getBlock() == BOPBlocks.BARNACLES) && $$0.getBlockState($$4).isAir())
        {
            $$0.setBlock($$2, $$3, 3);
            if ($$1.nextFloat() < 0.25F)
            {
                BuiltInRegistries.BLOCK.getTag(ModTags.Blocks.DEAD_CORALS).flatMap(($$1x) -> {
                    return $$1x.getRandomElement($$1);
                }).map(Holder::value).ifPresent(($$2x) -> {
                    $$0.setBlock($$4, $$2x.defaultBlockState().setValue(BaseCoralPlantTypeBlock.WATERLOGGED, false), 2);
                });
            }
            else if ($$1.nextFloat() < 0.05F)
            {
                $$0.setBlock($$4, (BlockState)Blocks.GLOW_LICHEN.defaultBlockState().setValue(PipeBlock.DOWN, true).setValue(SeaPickleBlock.WATERLOGGED, false), 2);
            }

            Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

            while(var7.hasNext())
            {
                Direction $$6 = (Direction)var7.next();
                if ($$1.nextFloat() < 0.2F)
                {
                    BlockPos $$7 = $$2.relative($$6);
                    if ($$0.getBlockState($$7).isAir() || $$0.getBlockState($$7).getBlock() == BOPBlocks.BARNACLES)
                    {
                        BuiltInRegistries.BLOCK.getTag(ModTags.Blocks.DEAD_WALL_CORALS).flatMap(($$1x) -> {
                            return $$1x.getRandomElement($$1);
                        }).map(Holder::value).ifPresent(($$3x) -> {
                            BlockState $$4x = $$3x.defaultBlockState().setValue(BaseCoralPlantTypeBlock.WATERLOGGED, false);
                            if ($$4x.hasProperty(BaseCoralWallFanBlock.FACING))
                            {
                                $$4x = (BlockState)$$4x.setValue(BaseCoralWallFanBlock.FACING, $$6);
                            }

                            $$0.setBlock($$7, $$4x, 2);
                        });
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
