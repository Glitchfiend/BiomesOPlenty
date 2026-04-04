/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(ScreenEffectRenderer.class)
public class MixinScreenEffectRenderer
{
    @Final
    @Shadow
    private Minecraft minecraft;

    @Final
    @Shadow
    private MultiBufferSource bufferSource;

    @Inject(method = "renderScreenEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isOnFire()Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onRenderScreenDoorEffect(final boolean isFirstPerson, final boolean isSleeping, final float partialTicks, final SubmitNodeCollector submitNodeCollector, final boolean hideGui, CallbackInfo ci, PoseStack poseStack)
    {
        if (this.minecraft.player.isEyeInFluid(ModTags.Fluids.BLOOD))
        {
            renderOverlay(this.minecraft, poseStack, this.bufferSource, Identifier.parse("biomesoplenty:textures/block/blood_underwater.png"));
        }
        else if (this.minecraft.player.isEyeInFluid(ModTags.Fluids.NULL))
        {
            renderOverlay(this.minecraft, poseStack, this.bufferSource, Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png"));
        }
    }

    private static void renderOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource multiBufferSource, Identifier location)
    {
        BlockPos blockPos = BlockPos.containing(minecraft.player.getX(), minecraft.player.getEyeY(), minecraft.player.getZ());
        float f = Lightmap.getBrightness(minecraft.player.level().dimensionType(), minecraft.player.level().getMaxLocalRawBrightness(blockPos));
        int i = ARGB.colorFromFloat(0.1F, f, f, f);
        float g = 4.0F;
        float h = -1.0F;
        float j = 1.0F;
        float k = -1.0F;
        float l = 1.0F;
        float m = -0.5F;
        float n = -minecraft.player.getYRot() / 64.0F;
        float o = minecraft.player.getXRot() / 64.0F;
        Matrix4f matrix4f = poseStack.last().pose();
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderTypes.blockScreenEffect(location));
        vertexConsumer.addVertex(matrix4f, -1.0F, -1.0F, -0.5F).setUv(4.0F + n, 4.0F + o).setColor(i);
        vertexConsumer.addVertex(matrix4f, 1.0F, -1.0F, -0.5F).setUv(0.0F + n, 4.0F + o).setColor(i);
        vertexConsumer.addVertex(matrix4f, 1.0F, 1.0F, -0.5F).setUv(0.0F + n, 0.0F + o).setColor(i);
        vertexConsumer.addVertex(matrix4f, -1.0F, 1.0F, -0.5F).setUv(4.0F + n, 0.0F + o).setColor(i);
    }
}
