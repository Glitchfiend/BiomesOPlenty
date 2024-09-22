package biomesoplenty.fabric.client;

import biomesoplenty.fabric.init.ModClientFabric;
import biomesoplenty.glitch.event.EventManager;
import biomesoplenty.glitch.event.client.RegisterColorsEvent;
import biomesoplenty.glitch.event.client.RegisterParticleSpritesEvent;
import biomesoplenty.init.ModClient;
import java.util.function.BiConsumer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.particle.ParticleEngine.SpriteParticleRegistration;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.ParticleType;

public class BiomesOPlentyClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ModClient.setup();
    ModClientFabric.setup();

    EventManager.fire(new RegisterColorsEvent.Block(ColorProviderRegistry.BLOCK::register));
    EventManager.fire(new RegisterColorsEvent.Item(ColorProviderRegistry.ITEM::register));

    BiConsumer<ParticleType<?>, SpriteParticleRegistration<?>> particleSpriteRegisterFunc = (type, registration) -> {
      ParticleFactoryRegistry.getInstance().register(type, provider -> (ParticleProvider)registration.create(provider));
    };
    EventManager.fire(new RegisterParticleSpritesEvent(particleSpriteRegisterFunc));
  }
}
