/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModParticles
{
    public static final SimpleParticleType DRIPPING_BLOOD = new SimpleParticleType(false);
    public static final SimpleParticleType FALLING_BLOOD = new SimpleParticleType(false);
    public static final SimpleParticleType LANDING_BLOOD = new SimpleParticleType(false);
    public static final SimpleParticleType PUS = new SimpleParticleType(false);
    public static final SimpleParticleType GLOWWORM = new SimpleParticleType(false);
    public static final SimpleParticleType STEAM = new SimpleParticleType(false);
    public static final SimpleParticleType JACARANDA_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType SNOWBLOSSOM_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType RED_MAPLE_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType ORANGE_MAPLE_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType YELLOW_MAPLE_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType FIR_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType REDWOOD_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType CYPRESS_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType MAGIC_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType UMBRAN_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType HELLBARK_LEAVES = new SimpleParticleType(false);
    public static final SimpleParticleType END_SPORE = new SimpleParticleType(false);
    public static final SimpleParticleType WISP_BUBBLE = new SimpleParticleType(false);
    public static final SimpleParticleType NULL = new SimpleParticleType(false);
    public static final SimpleParticleType BINARY = new SimpleParticleType(false);

    public static void registerParticles(BiConsumer<ResourceLocation, ParticleType<?>> func)
    {
        register(func, "dripping_blood", DRIPPING_BLOOD);
        register(func, "falling_blood", FALLING_BLOOD);
        register(func, "landing_blood", LANDING_BLOOD);
        register(func, "pus", PUS);
        register(func, "glowworm", GLOWWORM);
        register(func, "steam", STEAM);
        register(func, "jacaranda_leaves", JACARANDA_LEAVES);
        register(func, "snowblossom_leaves", SNOWBLOSSOM_LEAVES);
        register(func, "red_maple_leaves", RED_MAPLE_LEAVES);
        register(func, "orange_maple_leaves", ORANGE_MAPLE_LEAVES);
        register(func, "yellow_maple_leaves", YELLOW_MAPLE_LEAVES);
        register(func, "fir_leaves", FIR_LEAVES);
        register(func, "redwood_leaves", REDWOOD_LEAVES);
        register(func, "cypress_leaves", CYPRESS_LEAVES);
        register(func, "magic_leaves", MAGIC_LEAVES);
        register(func, "umbran_leaves", UMBRAN_LEAVES);
        register(func, "hellbark_leaves", HELLBARK_LEAVES);
        register(func, "end_spore", END_SPORE);
        register(func, "wisp_bubble", WISP_BUBBLE);
        register(func, "null", NULL);
        register(func, "binary", BINARY);
    }

    private static <T extends ParticleType<? extends ParticleOptions>> T register(BiConsumer<ResourceLocation, ParticleType<?>> func, String name, T particle)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), particle);
        return particle;
    }
}
