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
import net.minecraft.client.player.LocalPlayer;
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

    @Shadow
    private static void buildQuad(VertexConsumer builder, Matrix4f pose, float x0, float y0, float x1, float y1, float z, float u0, float v0, float u1, float v1, int color) {}

    @Inject(method = "submit", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isOnFire()Z"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onSubmit(final boolean isFirstPerson, final boolean isSleeping, final float partialTicks, final SubmitNodeCollector submitNodeCollector, final boolean hideGui, CallbackInfo ci, PoseStack poseStack)
    {
        if (this.minecraft.player.isEyeInFluid(ModTags.Fluids.BLOOD))
        {
            submitCustomOverlay(this.minecraft, poseStack, submitNodeCollector, Identifier.parse("biomesoplenty:textures/block/blood_underwater.png"));
        }
        else if (this.minecraft.player.isEyeInFluid(ModTags.Fluids.NULL))
        {
            submitCustomOverlay(this.minecraft, poseStack, submitNodeCollector, Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png"));
        }
    }

    private static void submitCustomOverlay(Minecraft minecraft, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, Identifier location) {
        LocalPlayer player = minecraft.player;
        BlockPos pos = BlockPos.containing(player.getEyePosition());
        float brightness = Lightmap.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(pos));
        int color = ARGB.colorFromFloat(0.1F, brightness, brightness, brightness);
        float u0 = -player.getYRot() / 64.0F;
        float v0 = player.getXRot() / 64.0F;
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.blockScreenEffect(location), (pose, builder) -> {
            float uvSize = 4.0F;
            buildQuad(builder, pose.pose(), -1.0F, -1.0F, 1.0F, 1.0F, -0.5F, u0 + 4.0F, v0 + 4.0F, u0, v0, color);
        });
    }
}
