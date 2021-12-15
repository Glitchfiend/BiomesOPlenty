/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.api.biome;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class BOPBiomes
{
    public static final ResourceKey<Biome> BAMBOO_BLOSSOM_GROVE = register("bamboo_blossom_grove");
    public static final ResourceKey<Biome> CHERRY_BLOSSOM_GROVE = register("cherry_blossom_grove");
    public static final ResourceKey<Biome> COLD_DESERT = register("cold_desert");
    public static final ResourceKey<Biome> CONIFEROUS_FOREST = register("coniferous_forest");
    public static final ResourceKey<Biome> DEAD_FOREST = register("dead_forest");
    public static final ResourceKey<Biome> DRYLAND = register("dryland");
    public static final ResourceKey<Biome> FIELD = register("field");
    public static final ResourceKey<Biome> GRASSLAND = register("grassland");
    public static final ResourceKey<Biome> LAVENDER_FIELD = register("lavender_field");
    public static final ResourceKey<Biome> REDWOOD_FOREST = register("redwood_forest");
    public static final ResourceKey<Biome> SEASONAL_FOREST = register("seasonal_forest");
    public static final ResourceKey<Biome> SNOWY_CONIFEROUS_FOREST = register("snowy_coniferous_forest");
    public static final ResourceKey<Biome> WASTELAND = register("wasteland");
    public static final ResourceKey<Biome> WOODLAND = register("woodland");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
