/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.block.AnomalyBlock;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class AnomalyRenderer implements BlockEntityRenderer<AnomalyBlockEntity, AnomalyRenderer.AnomalyRenderState> {
    private final BlockRenderDispatcher dispatcher;

    public AnomalyRenderer(BlockEntityRendererProvider.Context context) {
        this.dispatcher = context.blockRenderDispatcher();
    }

    @Override
    public AnomalyRenderState createRenderState() {
        return new AnomalyRenderState();
    }

    @Override
    public void extractRenderState(AnomalyBlockEntity blockEntity, AnomalyRenderState renderState, float $$2, Vec3 $$3, @Nullable ModelFeatureRenderer.CrumblingOverlay $$4)
    {
        BlockEntityRenderState.extractBase(blockEntity, renderState, $$4);
        renderState.anomalyState = blockEntity.getRenderState();
    }

    @Override
    public void submit(AnomalyRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        BlockState state = renderState.blockState;

        // Do regular model rendering for stable anomalies
        if (state.getValue(AnomalyBlock.ANOMALY_TYPE) == AnomalyBlock.AnomalyType.STABLE)
            return;

        // Certain modded blocks (e.g. Immersive Engineering's bottling machine) crash when rendering for seemingly no reason.
        // In these cases, we'll just fail silently
        try
        {
            submitNodeCollector.submitBlock(poseStack, renderState.anomalyState, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        }
        catch (Exception e) {}
    }

    @Override
    public int getViewDistance() {
        return 32;
    }

    public static class AnomalyRenderState extends BlockEntityRenderState
    {
         BlockState anomalyState = Blocks.AIR.defaultBlockState();
    }
}


