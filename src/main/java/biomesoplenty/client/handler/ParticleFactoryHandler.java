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
        event.register(ModParticles.DRIPPING_BLOOD.get(), DripParticleBOP.BloodHangProvider::new);
        event.register(ModParticles.FALLING_BLOOD.get(), DripParticleBOP.BloodFallProvider::new);
        event.register(ModParticles.LANDING_BLOOD.get(), DripParticleBOP.BloodLandProvider::new);
        event.register(ModParticles.PUS.get(), PusParticle.Provider::new);
        event.register(ModParticles.GLOWWORM.get(), GlowwormParticle.Provider::new);
    }
}
