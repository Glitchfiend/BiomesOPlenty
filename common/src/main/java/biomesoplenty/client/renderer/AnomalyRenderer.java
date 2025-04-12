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
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class AnomalyRenderer implements BlockEntityRenderer<AnomalyBlockEntity> {
    private final BlockRenderDispatcher dispatcher;

    public AnomalyRenderer(BlockEntityRendererProvider.Context context) {
        this.dispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(AnomalyBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, Vec3 vec3)
    {
        // Do regular model rendering for stable anomalies
        if (blockEntity.getBlockState().getValue(AnomalyBlock.ANOMALY_TYPE) == AnomalyBlock.AnomalyType.STABLE)
            return;

        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        BlockState renderState = blockEntity.getRenderState();

        // Certain modded blocks (e.g. Immersive Engineering's bottling machine) crash when rendering for seemingly no reason.
        // In these cases, we'll just fail silently
        try
        {
            List<BlockModelPart> parts = this.dispatcher.getBlockModel(renderState).collectParts(RandomSource.create(renderState.getSeed(pos)));
            this.dispatcher.getModelRenderer().tesselateBlock(level, parts, renderState, pos, poseStack, buffer.getBuffer(ItemBlockRenderTypes.getRenderType(renderState)), false, OverlayTexture.NO_OVERLAY);
        }
        catch (Exception e) {}
    }

    @Override
    public int getViewDistance() {
        return 32;
    }
}


