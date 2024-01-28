/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.mixin;

import biomesoplenty.api.block.BOPFluids;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(FogRenderer.class)
public abstract class MixinLiquidNullFluid
{
    @Shadow
    private static float fogRed;

    @Shadow
    private static float fogGreen;

    @Shadow
    private static float fogBlue;

    @ModifyArgs(method = "setupColor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V", remap = false))
    private static void modifyFlogColors(Args args, Camera camera, float partialTicks, ClientLevel level, int renderDistanceChunks, float bossColorModifier)
    {
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);
        if(camera.getPosition().y > blockPos.getY() + fluidState.getHeight(level, blockPos))
        {
            return;
        }

        Fluid fluid = fluidState.getType();

        if(BOPFluids.LIQUID_NULL.isSame(fluid))
        {
            fogRed = 0.0F;
            fogGreen = 0.0F;
            fogBlue = 0.0F;
        }
    }

    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogType, float viewDistance, boolean thickFog, float thickDelta, CallbackInfo ci)
    {
        Level level = Minecraft.getInstance().level;
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);
        if(camera.getPosition().y >= blockPos.getY() + fluidState.getHeight(level, blockPos))
        {
            return;
        }

        Fluid fluid = fluidState.getType();

        if(BOPFluids.LIQUID_NULL.isSame(fluid))
        {
            RenderSystem.setShaderFogStart(0.1F);
            RenderSystem.setShaderFogEnd(2.5F);
        }
    }

}

