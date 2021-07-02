/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPWorldCarvers
{
    //Carvers
    public static final WorldCarver<ProbabilityFeatureConfiguration> ORIGIN_CAVE = register("origin_cave", new OriginCaveWorldCarver(ProbabilityFeatureConfiguration.CODEC, 256));

    private static <C extends CarverConfiguration, F extends WorldCarver<C>> F register(String key, F carver)
    {
        carver.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.WORLD_CARVERS.register(carver);
        return carver;
    }
}
