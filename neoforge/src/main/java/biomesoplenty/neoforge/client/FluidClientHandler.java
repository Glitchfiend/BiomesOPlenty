/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.neoforge.client;

import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.neoforge.init.ModFluidTypes;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector4f;

@EventBusSubscriber(modid = BiomesOPlenty.MOD_ID)
public class FluidClientHandler
{
    @SubscribeEvent
    public static void onRegisterFluidModels(RegisterFluidModelsEvent event)
    {
        event.register(new FluidModel.Unbaked(
                new Material(Identifier.parse("biomesoplenty:block/blood_still")),
                new Material(Identifier.parse("biomesoplenty:block/blood_flow")),
                new Material(Identifier.parse("biomesoplenty:block/blood_underwater")),
                null), BOPFluids.BLOOD, BOPFluids.FLOWING_BLOOD);

        event.register(new FluidModel.Unbaked(
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_still")),
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_flow")),
                new Material(Identifier.parse("biomesoplenty:block/liquid_null_underwater")),
                null), BOPFluids.LIQUID_NULL, BOPFluids.FLOWING_LIQUID_NULL);
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event)
    {
        event.registerFluidType(new IClientFluidTypeExtensions()
        {
            private static final Identifier BLOOD_UNDERWATER = Identifier.parse("biomesoplenty:textures/block/blood_underwater.png");

            @Override
            public Identifier getRenderOverlayTexture(Minecraft mc) { return BLOOD_UNDERWATER; }

            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor)
            {
                fluidFogColor.x = 0.407F;
                fluidFogColor.y = 0.121F;
                fluidFogColor.z = 0.137F;
            }

            @Override
            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData)
            {
                fogData.environmentalStart = 0.125F;
                fogData.environmentalEnd = 5.0F;
            }
        }, ModFluidTypes.BLOOD_TYPE);

        event.registerFluidType(new IClientFluidTypeExtensions()
        {
            private static final Identifier LIQUID_NULL_UNDERWATER = Identifier.parse("biomesoplenty:textures/block/liquid_null_underwater.png"),
                    LIQUID_NULL_STILL = Identifier.parse("biomesoplenty:block/liquid_null_still"),
                    LIQUID_NULL_FLOW = Identifier.parse("biomesoplenty:block/liquid_null_flow");

            @Override
            public Identifier getRenderOverlayTexture(Minecraft mc) { return LIQUID_NULL_UNDERWATER; }

            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor)
            {
                fluidFogColor.x = 0;
                fluidFogColor.y = 0;
                fluidFogColor.z = 0;
            }

            @Override
            public void modifyFogRender(Camera camera, @Nullable FogEnvironment environment, float renderDistance, float partialTick, FogData fogData)
            {
                fogData.environmentalStart = 0.1F;
                fogData.environmentalEnd = 2.5F;
            }
        }, ModFluidTypes.LIQUID_NULL_TYPE);
    }
}
