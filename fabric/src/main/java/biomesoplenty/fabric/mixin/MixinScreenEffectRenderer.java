/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.init.ModTags;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.Lightmap;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ScreenEffectRenderer.class)
public class MixinScreenEffectRenderer
{
    @Shadow
    private static void buildQuad(VertexConsumer builder, Matrix4f pose, float x0, float y0, float x1, float y1, float z, float u0, float v0, float u1, float v1, int color) {}

    @Inject(method = "submit", at = @At("HEAD"))
    public void onSubmit(float partialTicks, SubmitNodeCollector submitNodeCollector, PlayerRenderState playerRenderState, CameraRenderState cameraRenderState, boolean hideGui, CallbackInfo ci)
    {
        AvatarRenderState avatarRenderState = playerRenderState.avatarRenderState;

        if (cameraRenderState.entityRenderState.isSleeping || !playerRenderState.hasPlayer || avatarRenderState == null)
            return;

        if (!cameraRenderState.isFirstPerson || avatarRenderState.isSpectator)
            return;

        LocalPlayer player = Minecraft.getInstance().player;

        if (player == null)
            return;

        if (player.isEyeInFluid(ModTags.Fluids.BLOOD))
        {
            submitCustomOverlay(player, submitNodeCollector, Identifier.parse("biomesoplenty:textures/block/blood_underwater.png"));
        }
        else if (player.isEyeInFluid(ModTags.Fluids.NULL))
        {
            submitCustomOverlay(player, submitNodeCollector, Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png"));
        }
    }

    private static void submitCustomOverlay(LocalPlayer player, SubmitNodeCollector submitNodeCollector, Identifier location)
    {
        BlockPos pos = BlockPos.containing(player.getEyePosition());
        float brightness = Lightmap.getBrightness(player.level().dimensionType(), player.level().getMaxLocalRawBrightness(pos));
        int color = ARGB.colorFromFloat(0.1F, brightness, brightness, brightness);
        float u0 = -player.getYRot() / 64.0F;
        float v0 = player.getXRot() / 64.0F;

        submitNodeCollector.submitCustomGeometry(new PoseStack(), RenderTypes.blockScreenEffect(location), (pose, builder) -> {
            buildQuad(builder, pose.pose(), -1.0F, -1.0F, 1.0F, 1.0F, -0.5F, u0 + 4.0F, v0 + 4.0F, u0, v0, color);
        });
    }
}
