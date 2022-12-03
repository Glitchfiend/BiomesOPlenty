/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BOPWorldCarvers
{
    //Carvers

    public static final RegistryObject<WorldCarver<CaveCarverConfiguration>> ORIGIN_CAVE = register("origin_cave", () -> new OriginCaveWorldCarver(CaveCarverConfiguration.CODEC));

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> RegistryObject<F> register(String key, Supplier<F> carverSupplier)
    {
        return BiomesOPlenty.CARVER_REGISTER.register(key, carverSupplier);
    }

    public static void setup() {}
}
