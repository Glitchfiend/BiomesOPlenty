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
    public static SimpleParticleType DRIPPING_BLOOD;
    public static SimpleParticleType FALLING_BLOOD;
    public static SimpleParticleType LANDING_BLOOD;
    public static SimpleParticleType PUS;
    public static SimpleParticleType GLOWWORM;
    public static SimpleParticleType STEAM;
    public static SimpleParticleType JACARANDA_LEAVES;
    public static SimpleParticleType SNOWBLOSSOM_LEAVES;
    public static SimpleParticleType RED_MAPLE_LEAVES;
    public static SimpleParticleType ORANGE_MAPLE_LEAVES;
    public static SimpleParticleType YELLOW_MAPLE_LEAVES;
    public static SimpleParticleType END_SPORE;
    public static SimpleParticleType WISP_BUBBLE;
    public static SimpleParticleType NULL;
    public static SimpleParticleType BINARY;

    public static void registerParticles(BiConsumer<ResourceLocation, ParticleType<?>> func)
    {
        DRIPPING_BLOOD = register(func, "dripping_blood",  new SimpleParticleType(false));
        FALLING_BLOOD = register(func, "falling_blood",  new SimpleParticleType(false));
        LANDING_BLOOD = register(func, "landing_blood",  new SimpleParticleType(false));
        PUS = register(func, "pus",  new SimpleParticleType(false));
        GLOWWORM = register(func, "glowworm",  new SimpleParticleType(false));
        STEAM = register(func, "steam",  new SimpleParticleType(false));
        JACARANDA_LEAVES = register(func, "jacaranda_leaves",  new SimpleParticleType(false));
        SNOWBLOSSOM_LEAVES = register(func, "snowblossom_leaves",  new SimpleParticleType(false));
        RED_MAPLE_LEAVES = register(func, "red_maple_leaves",  new SimpleParticleType(false));
        ORANGE_MAPLE_LEAVES = register(func, "orange_maple_leaves",  new SimpleParticleType(false));
        YELLOW_MAPLE_LEAVES = register(func, "yellow_maple_leaves",  new SimpleParticleType(false));
        END_SPORE = register(func, "end_spore",  new SimpleParticleType(false));
        WISP_BUBBLE = register(func, "wisp_bubble",  new SimpleParticleType(false));
        NULL = register(func, "null",  new SimpleParticleType(false));
        BINARY = register(func, "binary",  new SimpleParticleType(false));
    }

    private static <T extends ParticleType<? extends ParticleOptions>> T register(BiConsumer<ResourceLocation, ParticleType<?>> func, String name, T particle)
    {
        func.accept(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), particle);
        return particle;
    }
}
