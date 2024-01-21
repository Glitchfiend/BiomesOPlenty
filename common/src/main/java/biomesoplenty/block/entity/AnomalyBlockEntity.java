/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block.entity;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.block.AnomalyBlock;
import com.google.common.base.Suppliers;
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

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AnomalyBlockEntity extends BlockEntity
{
    private long lastTime = -1;
    private BlockState lastState = null;

    private final Supplier<LinkedHashSet<BlockState>> renderStates = Suppliers.memoize(() -> {
        Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Registries.BLOCK);
        return blockRegistry.entrySet().stream().map(e -> e.getValue().defaultBlockState()).filter(state -> state.getRenderShape() == RenderShape.MODEL).collect(Collectors.toCollection(LinkedHashSet::new));
    });

    public AnomalyBlockEntity(BlockPos pos, BlockState state) {
        super(BOPBlockEntities.ANOMALY, pos, state);
    }

    public BlockState getRenderState()
    {
        Level level = this.getLevel();

        if (level == null)
            return Blocks.AIR.defaultBlockState();

        final long time = level.getGameTime();
        if (lastTime == time && lastState != null)
            return lastState;

        RandomSource random = RandomSource.create(Mth.getSeed(this.getBlockPos()));
        BlockState state = this.getBlockState();

        final var renderStates = this.renderStates.get();
        int index = random.nextInt(renderStates.size());

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

        index %= renderStates.size();
        BlockState renderState = renderStates.stream().skip(index).findFirst().orElseThrow();

        lastState = renderState;
        lastTime = time;

        return renderState;
    }
}
