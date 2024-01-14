/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.block.AnomalyBlock;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class AnomalyRenderer implements BlockEntityRenderer<AnomalyBlockEntity>
{
    private final BlockRenderDispatcher dispatcher;

    public AnomalyRenderer(BlockEntityRendererProvider.Context context)
    {
        this.dispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(AnomalyBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay)
    {
        // Do regular model rendering for stable anomalies
        if (blockEntity.getBlockState().getValue(AnomalyBlock.ANOMALY_TYPE) == AnomalyBlock.AnomalyType.STABLE)
            return;

        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        BlockState renderState = getRenderState(RandomSource.create(Mth.getSeed(pos)), blockEntity);
        this.dispatcher.getModelRenderer().tesselateBlock(level, this.dispatcher.getBlockModel(renderState), renderState, pos, poseStack, buffer.getBuffer(ItemBlockRenderTypes.getRenderType(renderState, true)), false, RandomSource.create(), renderState.getSeed(pos), OverlayTexture.NO_OVERLAY);
    }

    private BlockState getRenderState(RandomSource random, AnomalyBlockEntity blockEntity)
    {
        Level level = blockEntity.getLevel();
        BlockState state = blockEntity.getBlockState();
        Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Registries.BLOCK);
        int index = random.nextInt(blockRegistry.size());

        switch (state.getValue(AnomalyBlock.ANOMALY_TYPE))
        {
            case VOLATILE -> index *= level.getGameTime() / 2L;
            case QUIRKY -> index += level.getGameTime() / 10L;
            case UNSTABLE -> {
                // Changes slowly most of the time, but has sudden bursts of rapid changes
                final float slowWeight = 0.98F;
                int mode = (Mth.sign(Mth.sin(level.getGameTime() / 20L) + slowWeight) + 1) / 2;
                if (mode > 0) index += (int)(level.getGameTime() / 100L);
                else index += level.getGameTime();
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

        return renderState;
    }
}
