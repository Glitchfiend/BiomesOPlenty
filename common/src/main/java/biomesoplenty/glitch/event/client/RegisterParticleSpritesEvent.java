package biomesoplenty.glitch.event.client;

import biomesoplenty.glitch.event.Event;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;

import java.util.function.BiConsumer;

public class RegisterParticleSpritesEvent extends Event
{
  private final BiConsumer<ParticleType<?>, ParticleEngine.SpriteParticleRegistration<?>> registerSpriteSetFunc;

  public RegisterParticleSpritesEvent(BiConsumer<ParticleType<?>, ParticleEngine.SpriteParticleRegistration<?>> registerSpriteSetFunc)
  {
    this.registerSpriteSetFunc = registerSpriteSetFunc;
  }

  public <T extends ParticleOptions> void registerSpriteSet(ParticleType<T> type, ParticleEngine.SpriteParticleRegistration<T> registration)
  {
    this.registerSpriteSetFunc.accept(type, registration);
  }
}
