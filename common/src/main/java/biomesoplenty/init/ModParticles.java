/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.Identifier;

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

    public static final SimpleParticleType WHITE_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType LIGHT_GRAY_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType GRAY_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType BLACK_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType BROWN_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType RED_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType ORANGE_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType YELLOW_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType LIME_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType GREEN_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType CYAN_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType LIGHT_BLUE_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType BLUE_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType PURPLE_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType MAGENTA_FLOWER_PETAL = new SimpleParticleType(false);
    public static final SimpleParticleType PINK_FLOWER_PETAL = new SimpleParticleType(false);

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

    public static void registerParticles(BiConsumer<Identifier, ParticleType<?>> func)
    {
        register(func, "dripping_blood", DRIPPING_BLOOD);
        register(func, "falling_blood", FALLING_BLOOD);
        register(func, "landing_blood", LANDING_BLOOD);
        register(func, "pus", PUS);
        register(func, "glowworm", GLOWWORM);
        register(func, "steam", STEAM);

        register(func, "white_flower_petal", WHITE_FLOWER_PETAL);
        register(func, "light_gray_flower_petal", LIGHT_GRAY_FLOWER_PETAL);
        register(func, "gray_flower_petal", GRAY_FLOWER_PETAL);
        register(func, "black_flower_petal", BLACK_FLOWER_PETAL);
        register(func, "brown_flower_petal", BROWN_FLOWER_PETAL);
        register(func, "red_flower_petal", RED_FLOWER_PETAL);
        register(func, "orange_flower_petal", ORANGE_FLOWER_PETAL);
        register(func, "yellow_flower_petal", YELLOW_FLOWER_PETAL);
        register(func, "lime_flower_petal", LIME_FLOWER_PETAL);
        register(func, "green_flower_petal", GREEN_FLOWER_PETAL);
        register(func, "cyan_flower_petal", CYAN_FLOWER_PETAL);
        register(func, "light_blue_flower_petal", LIGHT_BLUE_FLOWER_PETAL);
        register(func, "blue_flower_petal", BLUE_FLOWER_PETAL);
        register(func, "purple_flower_petal", PURPLE_FLOWER_PETAL);
        register(func, "magenta_flower_petal", MAGENTA_FLOWER_PETAL);
        register(func, "pink_flower_petal", PINK_FLOWER_PETAL);

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

    private static <T extends ParticleType<? extends ParticleOptions>> T register(BiConsumer<Identifier, ParticleType<?>> func, String name, T particle)
    {
        func.accept(Identifier.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name), particle);
        return particle;
    }
}
