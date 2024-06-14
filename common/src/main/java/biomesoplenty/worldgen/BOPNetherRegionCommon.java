/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.util.biome.BiomeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class BOPNetherRegionCommon extends Region
{
    public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "nether_common");

    public BOPNetherRegionCommon(int weight)
    {
        super(LOCATION, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, Biomes.NETHER_WASTES);
        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.CRYSTALLINE_CHASM, Biomes.SOUL_SAND_VALLEY));
        this.addBiome(mapper, Climate.Parameter.point(0.4F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.0F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.UNDERGROWTH, Biomes.CRIMSON_FOREST));
        this.addBiome(mapper, Climate.Parameter.point(0.0F), Climate.Parameter.point(0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.375F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.VISCERAL_HEAP, Biomes.WARPED_FOREST));
        this.addBiome(mapper, Climate.Parameter.point(-0.5F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), Climate.Parameter.point(0.0F), 0.175F, BiomeUtil.biomeOrFallback(registry, BOPBiomes.ERUPTING_INFERNO, Biomes.BASALT_DELTAS));
    }
}
