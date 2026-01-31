/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.fabric.extensions.IBOPFabricEntityExtension;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.FluidTags;
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
    public void onRenderScreenDoorEffect(boolean bl, float f, SubmitNodeCollector submitNodeCollector, CallbackInfo ci, PoseStack poseStack)
    {
        FluidState state = ((IBOPFabricEntityExtension)this.minecraft.player).getFluidStateOnEyes();
        if (state == null) return;

        Fluid fluid = state.getType();

        if (fluid == BOPFluids.BLOOD || fluid == BOPFluids.FLOWING_BLOOD)
        {
            renderOverlay(this.minecraft, poseStack, this.bufferSource, Identifier.parse("biomesoplenty:textures/block/blood_underwater.png"));
        }
        else if (fluid == BOPFluids.LIQUID_NULL || fluid == BOPFluids.FLOWING_LIQUID_NULL)
        {
            renderOverlay(this.minecraft, poseStack, this.bufferSource, Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png"));
        }
    }

    private static void renderOverlay(Minecraft minecraft, PoseStack poseStack, MultiBufferSource multiBufferSource, Identifier location)
    {
        BlockPos blockPos = BlockPos.containing(minecraft.player.getX(), minecraft.player.getEyeY(), minecraft.player.getZ());
        float f = LightTexture.getBrightness(minecraft.player.level().dimensionType(), minecraft.player.level().getMaxLocalRawBrightness(blockPos));
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
