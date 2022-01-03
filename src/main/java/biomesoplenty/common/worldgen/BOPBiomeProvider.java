/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen;

import biomesoplenty.common.biome.BOPOverworldBiomeBuilder;
import biomesoplenty.core.BiomesOPlenty;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class BOPBiomeProvider extends BiomeProvider
{
    public static final ResourceLocation LOCATION = new ResourceLocation(BiomesOPlenty.MOD_ID, "biome_provider");
    public static final ResourceLocation RARE_LOCATION = new ResourceLocation(BiomesOPlenty.MOD_ID, "rare_biome_provider");

    public BOPBiomeProvider(int weight)
    {
        super(LOCATION, weight);
    }

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new BOPOverworldBiomeBuilder()).addBiomes(registry, mapper);
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getOverworldSurfaceRules()
    {
        return Optional.of(BOPSurfaceRuleData.overworld());
    }

    @Override
    public Optional<SurfaceRules.RuleSource> getNetherSurfaceRules()
    {
        return Optional.of(BOPSurfaceRuleData.nether());
    }

    @Override
    public List<TBClimate.ParameterPoint> getSpawnTargets()
    {
        return (new BOPOverworldBiomeBuilder()).spawnTarget();
    }
}
