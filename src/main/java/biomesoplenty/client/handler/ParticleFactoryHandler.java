/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.client.particle.DripParticleBOP;
import biomesoplenty.client.particle.GlowwormParticle;
import biomesoplenty.client.particle.PusParticle;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesOPlenty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleFactoryHandler
{
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticles.DRIPPING_BLOOD.get(), DripParticleBOP.BloodHangProvider::new);
        event.registerSpriteSet(ModParticles.FALLING_BLOOD.get(), DripParticleBOP.BloodFallProvider::new);
        event.registerSpriteSet(ModParticles.LANDING_BLOOD.get(), DripParticleBOP.BloodLandProvider::new);
        event.registerSpriteSet(ModParticles.PUS.get(), PusParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GLOWWORM.get(), GlowwormParticle.Provider::new);
    }
}