package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BiomesOPlenty.MOD_ID);

    public static final RegistryObject<SimpleParticleType> DRIPPING_BLOOD = PARTICLES.register("dripping_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> FALLING_BLOOD = PARTICLES.register("falling_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> LANDING_BLOOD = PARTICLES.register("landing_blood", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PUS = PARTICLES.register("pus", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GLOWWORM = PARTICLES.register("glowworm", () -> new SimpleParticleType(false));
}
