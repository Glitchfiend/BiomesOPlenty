/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.neoforge.core;

import biomesoplenty.core.BiomesOPlenty;
import glitchcore.neoforge.GlitchCoreNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlentyNeoForge
{
    public BiomesOPlentyNeoForge(IEventBus bus)
    {
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        BiomesOPlenty.init();
        GlitchCoreNeoForge.prepareModEventHandlers(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            BiomesOPlenty.setupTerraBlender();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(BiomesOPlenty::setupClient);
    }
}
