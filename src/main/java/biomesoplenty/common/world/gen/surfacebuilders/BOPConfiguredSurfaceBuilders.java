/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPConfiguredSurfaceBuilders
{
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ALPS = register("alps", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.DEEP_TOP_LAYER, BOPSurfaceBuilders.SNOW_SNOW_GRAVEL_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ALPS_FOOTHILLS = register("alps_foothills", new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_STONE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL = register("chaparral", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.CHAPARRAL, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> COLD_DESERT = register("cold_desert", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.COLD_DESERT, SurfaceBuilder.CONFIG_GRAVEL));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> GRAVEL_BEACH = register("gravel_beach", new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRAVEL));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PODZOL_MIXED = register("podzol_mixed", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.PODZOL, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> PODZOL_FULL = register("podzol_full", new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_PODZOL));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> JADE_CLIFFS = register("jade_cliffs", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.DEEP_TOP_LAYER, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> LUSH_DESERT = register("lush_desert", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.LUSH_DESERT, BOPSurfaceBuilders.ORANGE_SAND_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> MANGROVE = register("mangrove", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.MANGROVE, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> MARSH = register("marsh", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.MARSH, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> MIRE = register("mire", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.MUD, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ORIGIN_VALLEY = register("origin_valley", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.ORIGIN_VALLEY, BOPSurfaceBuilders.ORIGIN_GRASS_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> OUTBACK = register("outback", new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, BOPSurfaceBuilders.RED_SAND_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> OVERGROWN_CLIFFS = register("overgrown_cliffs", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.TERRACOTTA, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> POPPY_FIELD = register("poppy_field", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.POPPY_FIELD, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> TROPICS = register("tropics", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.TROPICS, SurfaceBuilder.CONFIG_GRASS));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> TROPIC_BEACH = register("tropic_beach", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.TROPICS, BOPSurfaceBuilders.WHITE_SAND_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VOLCANIC_PLAINS = register("volcanic_plains", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.BLACK_SAND, BOPSurfaceBuilders.BLACK_SAND_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.VOLCANO, BOPSurfaceBuilders.BASALT_SURFACE));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> WASTELAND = register("wasteland", new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, BOPSurfaceBuilders.DRIED_SALT_SURFACE));

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VISCERAL_HEAP = register("visceral_heap", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.FLESH, SurfaceBuilder.CONFIG_HELL));
    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> WITHERED_ABYSS = register("withered_abyss", new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.WITHERED_ABYSS, BOPSurfaceBuilders.BLACKSTONE_SURFACE));

    private static <C extends ISurfaceBuilderConfig, F extends ConfiguredSurfaceBuilder<C>> F register(String key, F builder)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(BiomesOPlenty.MOD_ID, key), builder);
    }
}