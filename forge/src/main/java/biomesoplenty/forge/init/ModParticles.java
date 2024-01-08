/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.init;

import biomesoplenty.forge.core.BiomesOPlentyForge;
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
    public static final RegistryObject<SimpleParticleType> STEAM = register("steam", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> JACARANDA_LEAVES = register("jacaranda_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SNOWBLOSSOM_LEAVES = register("snowblossom_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RED_MAPLE_LEAVES = register("red_maple_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ORANGE_MAPLE_LEAVES = register("orange_maple_leaves", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> YELLOW_MAPLE_LEAVES = register("yellow_maple_leaves", () -> new SimpleParticleType(false));

    public static void setup() {}

    private static RegistryObject<SimpleParticleType> register(String key, Supplier<SimpleParticleType> particleTypeSupplier)
    {
        return BiomesOPlentyForge.PARTICLES_REGISTER.register(key, particleTypeSupplier);
    }
}
