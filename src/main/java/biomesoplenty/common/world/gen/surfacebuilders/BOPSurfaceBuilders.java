/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.surfacebuilders;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.gen.feature.*;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPSurfaceBuilders
{
    public static final SurfaceBuilderConfig BLACKSTONE_SURFACE = new SurfaceBuilderConfig(Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState());
    public static final SurfaceBuilderConfig BASALT_SURFACE = new SurfaceBuilderConfig(Blocks.BASALT.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig TERRACOTTA_SURFACE = new SurfaceBuilderConfig(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.BASALT.defaultBlockState());
    public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState());
    public static final SurfaceBuilderConfig RED_SAND_SURFACE = new SurfaceBuilderConfig(Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());
    public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig WHITE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState());
    public static final SurfaceBuilderConfig BLACK_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.black_sand.defaultBlockState(), BOPBlocks.black_sand.defaultBlockState(), BOPBlocks.black_sand.defaultBlockState());
    public static final SurfaceBuilderConfig DRIED_SALT_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState());
    public static final SurfaceBuilderConfig ORIGIN_GRASS_SURFACE = new SurfaceBuilderConfig(BOPBlocks.origin_grass_block.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    
    public static final SurfaceBuilder<SurfaceBuilderConfig> BRUSHLAND = register("brushland", new BrushlandSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL = register("chaparral", new ChaparralSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> COLD_DESERT = register("cold_desert", new ColdDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> TERRACOTTA = register("terracotta", new TerracottaSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MARSH = register("marsh", new MarshSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MANGROVE = register("mangrove", new MangroveSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> MUD = register("mud", new MudSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> PODZOL = register("podzol", new PodzolSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new VolcanoSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEEP_TOP_LAYER = register("deep_top_layer", new DeepTopLayerSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> POPPY_FIELD = register("poppy_field", new PoppyFieldSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> WITHERED_ABYSS = register("withered_abyss", new WitheredAbyssSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> FLESH = register("flesh", new FleshSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> ORIGIN_HILLS = register("origin_hills", new OriginHillsSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> TROPICS = register("tropics", new TropicsSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    public static final SurfaceBuilder<SurfaceBuilderConfig> BLACK_SAND = register("black_sand", new BlackSandSurfaceBuilder(SurfaceBuilderConfig.CODEC));

    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        return Registry.register(Registry.SURFACE_BUILDER, new ResourceLocation(BiomesOPlenty.MOD_ID, key), builder);
    }
}
