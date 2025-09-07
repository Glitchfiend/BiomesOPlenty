package biomesoplenty.block;

import biomesoplenty.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

public class OriginGrassBlock extends GrassBlock
{
    public OriginGrassBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_153797_, BlockPos p_153798_, BlockState p_153799_)
    {
        return p_153797_.getBlockState(p_153798_.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level p_153802_, RandomSource p_153803_, BlockPos p_153804_, BlockState p_153805_)
    {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_221270_, RandomSource p_221271_, BlockPos p_221272_, BlockState p_221273_)
    {
        BlockPos blockpos = p_221272_.above();
        Optional<Holder.Reference<PlacedFeature>> dandelion = p_221270_.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(BOPVegetationPlacements.ORIGIN_DANDELION_BONEMEAL);
        Optional<Holder.Reference<PlacedFeature>> rose = p_221270_.registryAccess().lookupOrThrow(Registries.PLACED_FEATURE).get(BOPVegetationPlacements.ORIGIN_ROSE_BONEMEAL);

        label49:
        for(int i = 0; i < 64; ++i)
        {
            BlockPos blockpos1 = blockpos;

            for(int j = 0; j < i / 16; ++j)
            {
                blockpos1 = blockpos1.offset(p_221271_.nextInt(3) - 1, (p_221271_.nextInt(3) - 1) * p_221271_.nextInt(3) / 2, p_221271_.nextInt(3) - 1);
                if (!p_221270_.getBlockState(blockpos1.below()).is(this) || p_221270_.getBlockState(blockpos1).isCollisionShapeFullBlock(p_221270_, blockpos1)) {
                    continue label49;
                }
            }

            BlockState blockstate1 = p_221270_.getBlockState(blockpos1);

            if (blockstate1.isAir())
            {
                Holder<PlacedFeature> holder;

                if (!dandelion.isPresent()) {
                    continue;
                }

                holder = dandelion.get();

                if (p_221271_.nextInt(3) == 0)
                {
                    if (!rose.isPresent()) {
                        continue;
                    }

                    holder = rose.get();
                }

                holder.value().place(p_221270_, p_221270_.getChunkSource().getGenerator(), p_221271_, blockpos1);
            }
        }

    }
}
