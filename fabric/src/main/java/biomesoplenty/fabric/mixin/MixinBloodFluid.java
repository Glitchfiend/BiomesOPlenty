/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.api.block.BOPFluids;
import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MappableRingBuffer;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.ByteBuffer;

@Environment(EnvType.CLIENT)
@Mixin(FogRenderer.class)
public abstract class MixinBloodFluid
{
    @Shadow
    @Final
    private MappableRingBuffer regularBuffer;

    @Shadow
    private void updateBuffer(ByteBuffer byteBuffer, int i, Vector4f vector4f, float f, float g, float h, float j, float k, float l) {}

    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private void setupFog(Camera camera, int i, boolean bl, DeltaTracker deltaTracker, float f, ClientLevel level, CallbackInfoReturnable<Vector4f> cir)
    {
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);
        Fluid fluid = fluidState.getType();

        if(camera.getPosition().y > blockPos.getY() + fluidState.getHeight(level, blockPos))
        {
            return;
        }

        if (!BOPFluids.BLOOD.isSame(fluid))
            return;

        float g = deltaTracker.getGameTimeDeltaPartialTick(false);
        Vector4f vector4f = new Vector4f(0.407F, 0.121F, 0.137F, 1.0F);
        float h = (float)(i * 16);

        Entity entity = camera.getEntity();
        FogData fogData = new FogData();

        float j = Mth.clamp(h / 10.0F, 4.0F, 64.0F);
        fogData.renderDistanceStart = h - j;
        fogData.renderDistanceEnd = h;
        fogData.environmentalStart = 0.125F;
        fogData.environmentalEnd = 5.0F;

        try (GpuBuffer.MappedView mappedView = RenderSystem.getDevice().createCommandEncoder().mapBuffer(this.regularBuffer.currentBuffer(), false, true)) {
            this.updateBuffer(mappedView.data(), 0, vector4f, fogData.environmentalStart, fogData.environmentalEnd, fogData.renderDistanceStart, fogData.renderDistanceEnd, fogData.skyEnd, fogData.cloudEnd);
        }

        cir.setReturnValue(vector4f);
    }
}
