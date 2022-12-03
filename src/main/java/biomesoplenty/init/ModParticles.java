/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
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

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return BiomesOPlenty.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
