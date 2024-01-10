/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/

package biomesoplenty.forge.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.*;
import glitchcore.forge.GlitchCoreForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = BiomesOPlenty.MOD_ID)
public class BiomesOPlentyForge
{
    public BiomesOPlentyForge()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);

        BiomesOPlenty.init();
        GlitchCoreForge.prepareModEventHandlers(bus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            BiomesOPlenty.setupTerraBlender();
            ModVanillaCompat.setup();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(BiomesOPlenty::setupClient);
    }
}
