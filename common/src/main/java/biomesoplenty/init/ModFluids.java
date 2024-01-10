/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.block.BloodFluid;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.function.BiConsumer;

import static biomesoplenty.api.block.BOPFluids.BLOOD;
import static biomesoplenty.api.block.BOPFluids.FLOWING_BLOOD;

public class ModFluids
{
    public static void registerFluids(BiConsumer<ResourceLocation, Fluid> func)
    {
        FLOWING_BLOOD = (FlowingFluid)register(func, new BloodFluid.Flowing(), "flowing_blood");
        BLOOD = register(func, new BloodFluid.Source(), "blood");
    }

    private static Fluid register(BiConsumer<ResourceLocation, Fluid> func, Fluid fluid, String name)
    {
        func.accept(new ResourceLocation(BiomesOPlenty.MOD_ID, name), fluid);
        return fluid;
    }

    // TODO: Forge fluids

//    BLOOD_TYPE = registerFluidType(() ->
//        new FluidType(FluidType.Properties.create()
//                        .descriptionId("block.biomesoplenty.blood")
//                        .fallDistanceModifier(0F)
//                        .canExtinguish(true)
//                        .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
//                        .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
//                        .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
//                        .density(3000)
//                        .viscosity(6000))
//    {
//        @Override
//        public @Nullable BlockPathTypes getBlockPathType(FluidState state, BlockGetter level, BlockPos pos, @Nullable Mob mob, boolean canFluidLog)
//        {
//            return canFluidLog ? super.getBlockPathType(state, level, pos, mob, true) : null;
//        }
//
//        @Override
//        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer)
//        {
//            consumer.accept(new IClientFluidTypeExtensions()
//            {
//                private static final ResourceLocation BLOOD_UNDERWATER = new ResourceLocation("biomesoplenty:textures/block/blood_underwater.png"),
//                        BLOOD_STILL = new ResourceLocation("biomesoplenty:block/blood_still"),
//                        BLOOD_FLOW = new ResourceLocation("biomesoplenty:block/blood_flow");
//
//                @Override
//                public ResourceLocation getStillTexture()
//                {
//                    return BLOOD_STILL;
//                }
//
//                @Override
//                public ResourceLocation getFlowingTexture() { return BLOOD_FLOW; }
//
//                @Override
//                public ResourceLocation getRenderOverlayTexture(Minecraft mc) { return BLOOD_UNDERWATER; }
//
//                @Override
//                public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor)
//                {
//                    return new Vector3f(0.407F, 0.121F, 0.137F);
//                }
//
//                @Override
//                public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
//                {
//                    RenderSystem.setShaderFogStart(0.125F);
//                    RenderSystem.setShaderFogEnd(5.0F);
//                }
//            });
//        }
//    }, "blood");
//
//    public static void registerFluidInteractions()
//    {
//        for (Map.Entry<ResourceKey<FluidType>, FluidType> fluidType : ForgeRegistries.FLUID_TYPES.get().getEntries())
//        {
//            if (fluidType.getValue() != ForgeMod.EMPTY_TYPE.get() && fluidType.getValue() != BOPFluids.BLOOD_TYPE)
//            {
//                FluidInteractionRegistry.addInteraction(fluidType.getValue(), new FluidInteractionRegistry.InteractionInformation(
//                    BOPFluids.BLOOD_TYPE,
//                    fluidState -> fluidState.isSource() ? BOPBlocks.FLESH.defaultBlockState() : BOPBlocks.POROUS_FLESH.defaultBlockState()
//                ));
//            }
//        }
//    }
}