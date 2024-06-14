/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen;

import biomesoplenty.biome.BOPSecondaryOverworldBiomeBuilder;
import biomesoplenty.core.BiomesOPlenty;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class BOPOverworldRegionSecondary extends Region
{
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "overworld_secondary");

    public BOPOverworldRegionSecondary(int weight)
    {
        super(LOCATION, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        (new BOPSecondaryOverworldBiomeBuilder()).addBiomes(registry, mapper);
    }
}
