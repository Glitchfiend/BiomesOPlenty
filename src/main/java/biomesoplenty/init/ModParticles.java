/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.client.particle.DripParticleBOP;
import biomesoplenty.client.particle.GlowwormParticle;
import biomesoplenty.client.particle.PusParticle;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModParticles
{
    public static final RegistryObject<SimpleParticleType> DRIPPING_BLOOD = register("dripping_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FALLING_BLOOD = register("falling_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LANDING_BLOOD = register("landing_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PUS = register("pus", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOWWORM = register("glowworm", () -> new SimpleParticleType(false));

    public static void setup() {}

    public static void registerProviders()
    {
        // Use the particle engine for registration given Forge is unable to provide a stable API
        ParticleEngine engine = Minecraft.getInstance().particleEngine;
        engine.register(ModParticles.DRIPPING_BLOOD.get(), DripParticleBOP.BloodHangProvider::new);
        engine.register(ModParticles.FALLING_BLOOD.get(), DripParticleBOP.BloodFallProvider::new);
        engine.register(ModParticles.LANDING_BLOOD.get(), DripParticleBOP.BloodLandProvider::new);
        engine.register(ModParticles.PUS.get(), PusParticle.Provider::new);
        engine.register(ModParticles.GLOWWORM.get(), GlowwormParticle.Provider::new);
    }

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return BiomesOPlenty.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
