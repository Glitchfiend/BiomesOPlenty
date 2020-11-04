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
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPSurfaceBuilders
{
    public static final SurfaceBuilderConfig BLACKSTONE_SURFACE = new SurfaceBuilderConfig(Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState());
    public static final SurfaceBuilderConfig BASALT_SURFACE = new SurfaceBuilderConfig(Blocks.BASALT.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig TERRACOTTA_SURFACE = new SurfaceBuilderConfig(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig DIORITE_SURFACE = new SurfaceBuilderConfig(Blocks.DIORITE.defaultBlockState(), Blocks.DIORITE.defaultBlockState(), Blocks.DIORITE.defaultBlockState());
    public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.BASALT.defaultBlockState());
    public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState());
    public static final SurfaceBuilderConfig WHITE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState());
    public static final SurfaceBuilderConfig ORANGE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.orange_sand.defaultBlockState(), BOPBlocks.orange_sand.defaultBlockState(), BOPBlocks.orange_sand.defaultBlockState());
    public static final SurfaceBuilderConfig ORANGE_SANDSTONE_SURFACE = new SurfaceBuilderConfig(BOPBlocks.orange_sandstone.defaultBlockState(), BOPBlocks.orange_sandstone.defaultBlockState(), BOPBlocks.orange_sandstone.defaultBlockState());
    public static final SurfaceBuilderConfig BLACK_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.black_sand.defaultBlockState(), BOPBlocks.black_sand.defaultBlockState(), BOPBlocks.black_sand.defaultBlockState());
    public static final SurfaceBuilderConfig DRIED_SALT_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState());
    public static final SurfaceBuilderConfig ORIGIN_GRASS_SURFACE = new SurfaceBuilderConfig(BOPBlocks.origin_grass_block.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

    public static final SurfaceBuilder<SurfaceBuilderConfig> DRYLAND = register("dryland", new DrylandSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> HIGHLAND_CRAG = register("highland_crag", new HighlandCragSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> EXPOSED_STONE = register("exposed_stone", new ExposedStoneSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> TERRACOTTA = register("terracotta", new TerracottaSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MARSH = register("marsh", new MarshSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MANGROVE = register("mangrove", new MangroveSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> PODZOL = register("podzol", new PodzolSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new VolcanoSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEEP_TOP_LAYER = register("deep_top_layer", new DeepTopLayerSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> ORANGE_SANDSTONE = register("orange_sandstone", new OrangeSandstoneSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> LUSH_SAVANNA = register("lush_savanna", new LushSavannaSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> WITHERED_ABYSS = register("withered_abyss", new WitheredAbyssSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> FLESH = register("flesh", new FleshSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> ORIGIN_VALLEY = register("origin_valley", new OriginValleySurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> TROPICS = register("tropics", new TropicsSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> LUSH_DESERT = register("lush_desert", new LushDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> BLACK_SAND = register("black_sand", new BlackSandSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));

    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        builder.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
        ForgeRegistries.SURFACE_BUILDERS.register(builder);
        return builder;
    }
}
