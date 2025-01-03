/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block.entity;

import biomesoplenty.api.block.BOPBlockEntities;
import biomesoplenty.block.AnomalyBlock;
import com.google.common.base.Suppliers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AnomalyBlockEntity extends BlockEntity
{
    private long lastTime = -1;
    private BlockState lastState = null;

    private static final int MAX_NUM_MODEL_STATES = 500;
    private static final Supplier<List<BlockState>> MODEL_STATES = Suppliers.memoize(() -> {
        // Choose MAX_NUM_MODEL_STATES random blocks first, use a random blockstate from each
        var allBlocks = BuiltInRegistries.BLOCK.listElements().filter(b -> b.key().location().getNamespace().equals("minecraft") && b.value().defaultBlockState().getRenderShape() == RenderShape.MODEL).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(allBlocks);
        List<BlockState> states = new ArrayList<>(MAX_NUM_MODEL_STATES);
        var random = RandomSource.create();
        for (int i = 0; i < MAX_NUM_MODEL_STATES; i++) {
            var block = allBlocks.get(i % allBlocks.size());
            var blockPossibleStates = block.value().getStateDefinition().getPossibleStates();
            states.add(blockPossibleStates.get(random.nextInt(blockPossibleStates.size())));
        }
        return states;
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

        final var renderStates = MODEL_STATES.get();
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

        index = Mth.positiveModulo(index, renderStates.size()); // For some bizarre reason some people have a negative time?
        BlockState renderState = renderStates.stream().skip(index).findFirst().orElseThrow();

        lastState = renderState;
        lastTime = time;

        return renderState;
    }
}
