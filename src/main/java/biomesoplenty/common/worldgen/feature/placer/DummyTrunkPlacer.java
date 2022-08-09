/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.placer;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class DummyTrunkPlacer extends TrunkPlacer
{
    public DummyTrunkPlacer()
    {
        super(0, 0, 0);
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return null;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader p_226157_, BiConsumer<BlockPos, BlockState> p_226158_, RandomSource p_226159_, int p_226160_, BlockPos p_226161_, TreeConfiguration p_226162_)
    {
        return null;
    }
}
