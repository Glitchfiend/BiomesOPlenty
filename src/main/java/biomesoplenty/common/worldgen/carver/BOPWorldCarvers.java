/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

public class BOPWorldCarvers
{
    //Carvers

    public static final WorldCarver<CaveCarverConfiguration> ORIGIN_CAVE = register("origin_cave", new OriginCaveWorldCarver(CaveCarverConfiguration.CODEC));

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> F register(String key, F carver)
    {
        BiomesOPlenty.CARVER_REGISTER.register(key, () -> carver);
        return carver;
    }
}
