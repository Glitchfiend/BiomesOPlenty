/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderConfiguration;

public class BOPSurfaceBuilders
{
    public static final SurfaceBuilderBaseConfiguration BLACKSTONE_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration BASALT_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.BASALT.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration TERRACOTTA_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration CALCITE_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.CALCITE.defaultBlockState(), Blocks.CALCITE.defaultBlockState(), Blocks.CALCITE.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration MAGMA_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.BASALT.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration MUD_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.MUD.defaultBlockState(), BOPBlocks.MUD.defaultBlockState(), BOPBlocks.MUD.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration WHITE_SAND_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.WHITE_SAND.defaultBlockState(), BOPBlocks.WHITE_SAND.defaultBlockState(), BOPBlocks.WHITE_SAND.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration ORANGE_SAND_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.ORANGE_SAND.defaultBlockState(), BOPBlocks.ORANGE_SAND.defaultBlockState(), BOPBlocks.ORANGE_SAND.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration ORANGE_SANDSTONE_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.ORANGE_SANDSTONE.defaultBlockState(), BOPBlocks.ORANGE_SANDSTONE.defaultBlockState(), BOPBlocks.ORANGE_SANDSTONE.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration BLACK_SAND_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.BLACK_SAND.defaultBlockState(), BOPBlocks.BLACK_SAND.defaultBlockState(), BOPBlocks.BLACK_SAND.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration DRIED_SALT_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.DRIED_SALT.defaultBlockState(), BOPBlocks.DRIED_SALT.defaultBlockState(), BOPBlocks.DRIED_SALT.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration ORIGIN_GRASS_SURFACE = new SurfaceBuilderBaseConfiguration(BOPBlocks.ORIGIN_GRASS_BLOCK.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration BASIN_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.AIR.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration GRAVEL_BEACH_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.AIR.defaultBlockState(), Blocks.GRAVEL.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderBaseConfiguration POWDER_SNOW_SURFACE = new SurfaceBuilderBaseConfiguration(Blocks.POWDER_SNOW.defaultBlockState(), Blocks.POWDER_SNOW.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> DRYLAND = register("dryland", new DrylandSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> HIGHLAND_CRAG = register("highland_crag", new HighlandCragSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> EXPOSED_STONE = register("exposed_stone", new ExposedStoneSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> TERRACOTTA = register("terracotta", new TerracottaSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> MARSH = register("marsh", new MarshSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> MANGROVE = register("mangrove", new MangroveSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> PODZOL = register("podzol", new PodzolSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> VOLCANO = register("volcano", new VolcanoSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> DEEP_TOP_LAYER = register("deep_top_layer", new DeepTopLayerSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> ORANGE_SANDSTONE = register("orange_sandstone", new OrangeSandstoneSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> LUSH_SAVANNA = register("lush_savanna", new LushSavannaSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> WITHERED_ABYSS = register("withered_abyss", new WitheredAbyssSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> FLESH = register("flesh", new FleshSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> ORIGIN_VALLEY = register("origin_valley", new OriginValleySurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> TROPICS = register("tropics", new TropicsSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> LUSH_DESERT = register("lush_desert", new LushDesertSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> BLACK_SAND = register("black_sand", new BlackSandSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderBaseConfiguration> COLD_DESERT = register("cold_desert", new ColdDesertSurfaceBuilder(SurfaceBuilderBaseConfiguration.CODEC.stable()));

    private static <C extends SurfaceBuilderConfiguration, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        Registry.register(Registry.SURFACE_BUILDER, new ResourceLocation(BiomesOPlenty.MOD_ID, key), builder);
        return builder;
    }
}
