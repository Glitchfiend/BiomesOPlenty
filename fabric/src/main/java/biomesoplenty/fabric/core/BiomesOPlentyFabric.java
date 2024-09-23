/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.fabric.core;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.glitch.event.EventManager;
import biomesoplenty.glitch.event.RegistryEvent;
import biomesoplenty.glitch.event.village.WandererTradesEvent;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.Supplier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.TerraBlenderApi;

public class BiomesOPlentyFabric implements ModInitializer, TerraBlenderApi
{
    @Override
    public void onInitialize()
    {
      BiomesOPlenty.init();
      postRegisterEvents();

      var wandererTradesEvent = new WandererTradesEvent();
      EventManager.fire(wandererTradesEvent);

      TradeOfferHelper.registerWanderingTraderOffers(1, (list) -> {
        list.addAll(wandererTradesEvent.getGenericTrades());
      });

      TradeOfferHelper.registerWanderingTraderOffers(2, (list) -> {
        list.addAll(wandererTradesEvent.getRareTrades());
      });
    }

    @Override
    public void onTerraBlenderInitialized()
    {
        BiomesOPlenty.setupTerraBlender();
    }

    private static void postRegisterEvents()
    {
      // We use LOADERS to ensure objects are registered at the correct time relative to each other
        for (ResourceLocation registryName : BuiltInRegistries.LOADERS.keySet())
        {
            ResourceKey<? extends Registry<?>> registryKey = ResourceKey.createRegistryKey(registryName);
            Registry<?> registry = BuiltInRegistries.REGISTRY.get(registryName);
            EventManager.fire(new RegistryEvent(registryKey, (location, value) -> Registry.register((Registry<? super Object>)registry, location, value)));
        }
    }
}