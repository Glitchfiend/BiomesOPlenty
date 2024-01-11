/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.neoforge.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModClient;
import biomesoplenty.neoforge.init.ModFluidTypes;
import glitchcore.neoforge.GlitchCoreNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlentyNeoForge
{
    public static final DeferredRegister<FluidType> FORGE_FLUID_REGISTER = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, BiomesOPlenty.MOD_ID);

    public BiomesOPlentyNeoForge(IEventBus bus)
    {
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        FORGE_FLUID_REGISTER.register(bus);

        BiomesOPlenty.init();
        GlitchCoreNeoForge.prepareModEventHandlers(bus);

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
        event.enqueueWork(ModClient::setup);
    }
}
