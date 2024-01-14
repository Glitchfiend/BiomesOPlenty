/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block.entity;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.block.AnomalyBlock;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class AnomalyBlockEntity extends BlockEntity
{
    private long lastTime = -1;
    private BlockState lastState = null;

    public AnomalyBlockEntity(BlockPos pos, BlockState state) {
        super(BOPBlockEntities.ANOMALY, pos, state);
    }

    public BlockState getRenderState()
    {
        Level level = this.getLevel();
        final long time = level.getGameTime();

        if (lastTime == time && lastState != null)
            return lastState;

        RandomSource random = RandomSource.create(Mth.getSeed(this.getBlockPos()));
        BlockState state = this.getBlockState();
        Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Registries.BLOCK);
        int index = random.nextInt(blockRegistry.size());

        switch (state.getValue(AnomalyBlock.ANOMALY_TYPE))
        {
            case VOLATILE -> index *= (int) (time / 2L);
            case QUIRKY -> index += (int) (time / 10L);
            case UNSTABLE -> {
                // Changes slowly most of the time, but has sudden bursts of rapid changes
                final float slowWeight = 0.98F;
                int mode = (Mth.sign(Mth.sin((float)time / 20.0F) + slowWeight) + 1) / 2;
                if (mode > 0) index += (int)(time/ 100L);
                else index += (int) time;
            }
        }

        index %= blockRegistry.size();
        BlockState renderState = Blocks.AIR.defaultBlockState();

        while (renderState.getRenderShape() != RenderShape.MODEL)
        {
            Block renderBlock = blockRegistry.entrySet().stream().skip(index).map(Map.Entry::getValue).findFirst().orElseThrow();
            ImmutableList<BlockState> possibleStates = renderBlock.getStateDefinition().getPossibleStates();
            renderState = possibleStates.get(random.nextInt(possibleStates.size()));
            index = (index + 1) % blockRegistry.size();
        }

        lastState = renderState;
        lastTime = time;

        return renderState;
    }
}
