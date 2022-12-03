/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.common.block.BloodFluid;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

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
                            private static final ResourceLocation UNDERWATER_LOCATION = new ResourceLocation("textures/misc/underwater.png"),
                                BLOOD_STILL = new ResourceLocation("biomesoplenty:block/blood_still"),
                                BLOOD_FLOW = new ResourceLocation("biomesoplenty:block/blood_flow");

                            @Override
                            public ResourceLocation getStillTexture()
                            {
                                return BLOOD_STILL;
                            }

                            @Override
                            public ResourceLocation getFlowingTexture()
                            {
                                return BLOOD_FLOW;
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
}