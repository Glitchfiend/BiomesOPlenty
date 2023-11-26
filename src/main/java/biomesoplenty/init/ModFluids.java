/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.common.block.BloodFluid;
import biomesoplenty.core.BiomesOPlenty;
import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static biomesoplenty.api.block.BOPFluids.*;

public class ModFluids
{
    public static void setup()
    {
        registerFluids();
    }

    public static void registerFluids()
    {
        FLOWING_BLOOD = registerFluid(() -> new BloodFluid.Flowing(), "flowing_blood");
        BLOOD = registerFluid(() -> new BloodFluid.Source(), "blood");

        BLOOD_TYPE = registerFluidType(() ->
                new FluidType(FluidType.Properties.create()
                        .descriptionId("block.biomesoplenty.blood")
                        .fallDistanceModifier(0F)
                        .canExtinguish(true)
                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                        .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                        .density(3000)
                        .viscosity(6000))
                {
                    @Override
                    public @Nullable BlockPathTypes getBlockPathType(FluidState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, boolean canFluidLog)
                    {
                        return canFluidLog ? super.getBlockPathType(state, level, pos, mob, true) : null;
                    }

                    @Override
                    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer)
                    {
                        consumer.accept(new IClientFluidTypeExtensions()
                        {
                            private static final ResourceLocation BLOOD_UNDERWATER = new ResourceLocation("biomesoplenty:textures/block/blood_underwater.png"),
                                BLOOD_STILL = new ResourceLocation("biomesoplenty:block/blood_still"),
                                BLOOD_FLOW = new ResourceLocation("biomesoplenty:block/blood_flow");

                            @Override
                            public ResourceLocation getStillTexture()
                            {
                                return BLOOD_STILL;
                            }

                            @Override
                            public ResourceLocation getFlowingTexture() { return BLOOD_FLOW; }

                            @Override
                            public ResourceLocation getRenderOverlayTexture(Minecraft mc) { return BLOOD_UNDERWATER; }

                            @Override
                            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
                            {
                                return new Vector3f(0.407F, 0.121F, 0.137F);
                            }

                            @Override
                            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
                            {
                                RenderSystem.setShaderFogStart(0.125F);
                                RenderSystem.setShaderFogEnd(5.0F);
                            }
                        });
                    }
                }, "blood");
    }

    public static RegistryObject<Fluid> registerFluid(Supplier<Fluid> fluidSupplier, String name)
    {
        return BiomesOPlenty.FLUID_REGISTER.register(name, fluidSupplier);
    }

    public static RegistryObject<FluidType> registerFluidType(Supplier<FluidType> fluidSupplier, String name)
    {
        return BiomesOPlenty.FORGE_FLUID_REGISTER.register(name, fluidSupplier);
    }

    public static void registerFluidInteractions()
    {
        for (Map.Entry<ResourceKey<FluidType>, FluidType> fluidType : ForgeRegistries.FLUID_TYPES.get().getEntries())
        {
            if (fluidType.getValue() != ForgeMod.EMPTY_TYPE.get() && fluidType.getValue() != BOPFluids.BLOOD_TYPE.get())
            {
                FluidInteractionRegistry.addInteraction(fluidType.getValue(), new FluidInteractionRegistry.InteractionInformation(
                    BOPFluids.BLOOD_TYPE.get(),
                    fluidState -> fluidState.isSource() ? BOPBlocks.FLESH.get().defaultBlockState() : BOPBlocks.POROUS_FLESH.get().defaultBlockState()
                ));
            }
        }
    }
}