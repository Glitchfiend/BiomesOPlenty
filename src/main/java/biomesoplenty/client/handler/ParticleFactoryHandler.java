/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.client.handler;

import biomesoplenty.client.particle.*;
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
        event.registerSpriteSet(ModParticles.JACARANDA_LEAVES.get(), (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.SNOWBLOSSOM_LEAVES.get(), (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.RED_MAPLE_LEAVES.get(), (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.ORANGE_MAPLE_LEAVES.get(), (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
        event.registerSpriteSet(ModParticles.YELLOW_MAPLE_LEAVES.get(), (p_277215_) -> {
            return (p_277217_, p_277218_, p_277219_, p_277220_, p_277221_, p_277222_, p_277223_, p_277224_) -> {
                return new LargeLeafParticle(p_277218_, p_277219_, p_277220_, p_277221_, p_277215_);
            };
        });
    }
}