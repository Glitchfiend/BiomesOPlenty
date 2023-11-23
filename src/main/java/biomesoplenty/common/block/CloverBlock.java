/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CloverBlock extends PinkPetalsBlock
{
    public CloverBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state)
    {
        return (double)rand.nextFloat() < 0.4D;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
    {
        int i = state.getValue(AMOUNT);
        if (i < 4)
        {
            level.setBlock(pos, state.setValue(AMOUNT, Integer.valueOf(i + 1)), 2);
        }
        else
        {
            this.growHugeClover(level, rand, pos, state);
            //popResource(level, pos, new ItemStack(this));
        }
    }

    public boolean growHugeClover(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state)
    {
        level.removeBlock(pos, false);
        Registry<ConfiguredFeature<?, ?>> configuredFeatureRegistry = level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE);
        ConfiguredFeature<?, ?> configuredfeature = configuredFeatureRegistry.get(BOPVegetationFeatures.HUGE_CLOVER);

        if (configuredfeature.place(level, level.getChunkSource().getGenerator(), rand, pos))
        {
            return true;
        }
        else
        {
            level.setBlock(pos, state, 3);
            return false;
        }
    }
}
