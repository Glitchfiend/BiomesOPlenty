package biomesoplenty.client.handler;

import biomesoplenty.client.particle.DripParticleBOP;
import biomesoplenty.client.particle.GlowwormParticle;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BiomesOPlenty.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleFactoryHandler
{
    @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particleEngine.register(ModParticles.DRIPPING_BLOOD.get(), DripParticleBOP.BloodHangProvider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.FALLING_BLOOD.get(), DripParticleBOP.BloodFallProvider::new);
        Minecraft.getInstance().particleEngine.register(ModParticles.LANDING_BLOOD.get(), DripParticleBOP.BloodLandProvider::new);

        Minecraft.getInstance().particleEngine.register(ModParticles.GLOWWORM.get(), GlowwormParticle.Provider::new);
    }
}
