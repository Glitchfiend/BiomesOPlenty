/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.handler;

import biomesoplenty.glitch.event.EventManager;
import biomesoplenty.glitch.event.client.RegisterParticleSpritesEvent;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterParticleProvidersEventHandler
{
  @SubscribeEvent
  public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event)
  {
    EventManager.fire(new RegisterParticleSpritesEvent((type, registration) -> event.registerSpriteSet((ParticleType)type, (ParticleEngine.SpriteParticleRegistration)registration)));
  }
}