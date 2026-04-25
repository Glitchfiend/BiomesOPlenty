/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.renderer;

import biomesoplenty.block.AnomalyBlock;
import biomesoplenty.block.entity.AnomalyBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class AnomalyRenderer implements BlockEntityRenderer<AnomalyBlockEntity, AnomalyRenderer.AnomalyRenderState> {
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
    private final BlockModelResolver blockModelResolver;

    public AnomalyRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }

    @Override
    public AnomalyRenderState createRenderState() {
        return new AnomalyRenderState();
    }

    @Override
    public void extractRenderState(AnomalyBlockEntity blockEntity, AnomalyRenderState renderState, float $$2, Vec3 $$3, @Nullable ModelFeatureRenderer.CrumblingOverlay $$4)
    {
        BlockEntityRenderState.extractBase(blockEntity, renderState, $$4);
        renderState.state = blockEntity.getBlockState();
        this.blockModelResolver.update(renderState.anomalyRenderState, blockEntity.getRenderState(), BLOCK_DISPLAY_CONTEXT);
    }

    @Override
    public void submit(AnomalyRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        BlockState state = renderState.state;

        // Do regular model rendering for stable anomalies
        if (state.getValue(AnomalyBlock.ANOMALY_TYPE) == AnomalyBlock.AnomalyType.STABLE)
            return;

        // Certain modded blocks (e.g. Immersive Engineering's bottling machine) crash when rendering for seemingly no reason.
        // In these cases, we'll just fail silently
        try
        {
            poseStack.pushPose();
            renderState.anomalyRenderState.submit(poseStack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
        catch (Exception e) {}
    }

    @Override
    public int getViewDistance() {
        return 32;
    }

    public static class AnomalyRenderState extends BlockEntityRenderState
    {
        public final BlockModelRenderState anomalyRenderState = new BlockModelRenderState();
        public BlockState state;
    }
}


