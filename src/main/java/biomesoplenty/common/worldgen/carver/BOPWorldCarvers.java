/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.carver;

import biomesoplenty.common.worldgen.carver.OriginCaveWorldCarver;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPWorldCarvers
{
    //Carvers
    public static final WorldCarver<CaveCarverConfiguration> ORIGIN_CAVE = register("origin_cave", new OriginCaveWorldCarver(CaveCarverConfiguration.CODEC));

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> F register(String key, F carver)
    {
        carver.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.WORLD_CARVERS.register(carver);
        return carver;
    }
}
