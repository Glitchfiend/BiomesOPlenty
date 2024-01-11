/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.forge.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.forge.init.ModFluidTypes;
import biomesoplenty.init.*;
import glitchcore.forge.GlitchCoreForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlentyForge
{
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BiomesOPlenty.MOD_ID);

    public BiomesOPlentyForge()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        FORGE_FLUID_REGISTER.register(bus);

        BiomesOPlenty.init();
        GlitchCoreForge.prepareModEventHandlers(bus);

        ModFluidTypes.setup();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            BiomesOPlenty.setupTerraBlender();
            ModFluidTypes.registerFluidInteractions();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(BiomesOPlenty::setupClient);
    }
}
