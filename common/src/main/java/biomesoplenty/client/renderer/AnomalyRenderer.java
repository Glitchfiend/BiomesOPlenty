/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.block.AnomalyBlock;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AnomalyRenderer implements BlockEntityRenderer<AnomalyBlockEntity> {
    private final BlockRenderDispatcher dispatcher;

    public AnomalyRenderer(BlockEntityRendererProvider.Context context) {
        this.dispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public int getViewDistance() {
        return 32;
    }

    @Override
    public void render(AnomalyBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        // Do regular model rendering for stable anomalies
        if (blockEntity.getBlockState().getValue(AnomalyBlock.ANOMALY_TYPE) == AnomalyBlock.AnomalyType.STABLE)
            return;

        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        BlockState renderState = blockEntity.getRenderState();
        this.dispatcher.getModelRenderer().tesselateBlock(level, this.dispatcher.getBlockModel(renderState), renderState, pos, poseStack, buffer.getBuffer(ItemBlockRenderTypes.getRenderType(renderState, true)), false, RandomSource.create(), renderState.getSeed(pos), OverlayTexture.NO_OVERLAY);
    }
}


