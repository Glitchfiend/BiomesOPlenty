package biomesoplenty.forge.handler;

import biomesoplenty.glitch.event.EventManager;
import biomesoplenty.glitch.event.RegistryEvent;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEventHandler
{
  @SubscribeEvent
  public static void onRegister(RegisterEvent forgeEvent)
  {
    var registryKey = forgeEvent.getRegistryKey();
    EventManager.fire(new RegistryEvent(registryKey, (location, value) -> forgeEvent.register((ResourceKey<? extends Registry<Object>>)registryKey, location, () -> value)));
  }
}
