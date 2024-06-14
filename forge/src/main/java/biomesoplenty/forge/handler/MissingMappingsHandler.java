/*******************************************************************************
 * Copyright 2023, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.handler;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.forge.core.BiomesOPlentyForge;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MissingMappingsHandler
{
    @SubscribeEvent
    public static void onMissingMapping(MissingMappingsEvent event)
    {
        Remapper.create(Registries.BLOCK)
                // Cherry
                .remap("white_cherry_sapling", BOPBlocks.SNOWBLOSSOM_SAPLING)
                .remap("white_cherry_leaves", BOPBlocks.SNOWBLOSSOM_LEAVES)
                .remap("pink_cherry_sapling", Blocks.CHERRY_SAPLING)
                .remap("pink_cherry_leaves", Blocks.CHERRY_LEAVES)
                .remap("cherry_log", Blocks.CHERRY_LOG)
                .remap("cherry_wood", Blocks.CHERRY_WOOD)
                .remap("stripped_cherry_log", Blocks.STRIPPED_CHERRY_LOG)
                .remap("stripped_cherry_wood", Blocks.STRIPPED_CHERRY_WOOD)
                .remap("cherry_planks", Blocks.CHERRY_PLANKS)
                .remap("cherry_stairs", Blocks.CHERRY_STAIRS)
                .remap("cherry_slab", Blocks.CHERRY_SLAB)
                .remap("cherry_fence", Blocks.CHERRY_FENCE)
                .remap("cherry_fence_gate", Blocks.CHERRY_FENCE_GATE)
                .remap("cherry_door", Blocks.CHERRY_DOOR)
                .remap("cherry_trapdoor", Blocks.CHERRY_TRAPDOOR)
                .remap("cherry_pressure_plate", Blocks.CHERRY_PRESSURE_PLATE)
                .remap("cherry_button", Blocks.CHERRY_BUTTON)
                .remap("cherry_sign", Blocks.CHERRY_SIGN)
                .remap("cherry_wall_sign", Blocks.CHERRY_WALL_SIGN)
                .remap("potted_white_cherry_sapling", BOPBlocks.POTTED_SNOWBLOSSOM_SAPLING)
                .remap("potted_pink_cherry_sapling", Blocks.POTTED_CHERRY_SAPLING)
                // Maple
                .remap("maple_sapling", BOPBlocks.RED_MAPLE_SAPLING)
                .remap("orange_autumn_sapling", BOPBlocks.ORANGE_MAPLE_SAPLING)
                .remap("yellow_autumn_sapling", BOPBlocks.YELLOW_MAPLE_SAPLING)
                .remap("maple_leaves", BOPBlocks.RED_MAPLE_LEAVES)
                .remap("orange_autumn_leaves", BOPBlocks.ORANGE_MAPLE_LEAVES)
                .remap("yellow_autumn_leaves", BOPBlocks.YELLOW_MAPLE_LEAVES)
                .remap("potted_maple_sapling", BOPBlocks.POTTED_RED_MAPLE_SAPLING)
                .remap("potted_orange_autumn_sapling", BOPBlocks.POTTED_ORANGE_MAPLE_SAPLING)
                .remap("potted_yellow_autumn_sapling", BOPBlocks.POTTED_YELLOW_MAPLE_SAPLING)
                // Mud
                .remap("mud", Blocks.MUD)
                .remap("mud_bricks", Blocks.MUD_BRICKS)
                .remap("mud_brick_block", Blocks.MUD_BRICKS)
                .remap("mud_brick_stairs", Blocks.MUD_BRICK_STAIRS)
                .remap("mud_brick_slab", Blocks.MUD_BRICK_SLAB)
                .remap("mud_brick_wall", Blocks.MUD_BRICK_WALL)
                // Misc
                .remap("loamy_grass_block", Blocks.GRASS_BLOCK)
                .remap("loamy_dirt", Blocks.DIRT)
                .remap("coarse_loamy_dirt", Blocks.COARSE_DIRT)
                .remap("loamy_grass_path", Blocks.DIRT_PATH)
                .remap("loamy_farmland", Blocks.FARMLAND)
                .remap("silty_grass_block", Blocks.GRASS_BLOCK)
                .remap("silty_dirt", Blocks.DIRT)
                .remap("coarse_silty_dirt", Blocks.COARSE_DIRT)
                .remap("silty_grass_path", Blocks.DIRT_PATH)
                .remap("silty_farmland", Blocks.FARMLAND)
                .remap("sandy_grass_block", Blocks.GRASS_BLOCK)
                .remap("sandy_dirt", Blocks.DIRT)
                .remap("coarse_sandy_dirt", Blocks.COARSE_DIRT)
                .remap("sandy_grass_path", Blocks.DIRT_PATH)
                .remap("sandy_farmland", Blocks.FARMLAND)
                .remap("overgrown_black_sand", BOPBlocks.MOSSY_BLACK_SAND)
                .remap("dried_sand", BOPBlocks.DRIED_SALT)
                .remap("ash_block", Blocks.BASALT)
                .remap("rooted_sand", Blocks.SAND)
                .remap("root", Blocks.HANGING_ROOTS)
                .remap("nether_sprout", BOPBlocks.SPROUT)
                .remap("tall_cattail", BOPBlocks.CATTAIL)
                .remap("ivy", BOPBlocks.WILLOW_VINE)
                .remap("short_grass", Blocks.SHORT_GRASS)
                .remap("thorn", Blocks.DEAD_BUSH)
                .remap("deathbloom", Blocks.WITHER_ROSE)
                .remap("devilweed", BOPBlocks.SPROUT)
                .remap("flowering_sapling", BOPBlocks.FLOWERING_OAK_SAPLING)
                .remap("flowering_leaves", BOPBlocks.FLOWERING_OAK_LEAVES)
                .remap("nether_crystal_block", BOPBlocks.ROSE_QUARTZ_BLOCK)
                .remap("nether_crystal", BOPBlocks.ROSE_QUARTZ_CLUSTER)
                .remap("tall_wheat", BOPBlocks.BARLEY)
                .remap("potted_clover", Blocks.FLOWER_POT)
                .remap("potted_wildflower", Blocks.FLOWER_POT)
                .run(event);

        Remapper.create(Registries.ITEM)
                .remap("cherry_sign", Items.CHERRY_SIGN)
                .remap("cherry_boat", Items.CHERRY_BOAT)
                .remap("cherry_chest_boat", Items.CHERRY_CHEST_BOAT)
                .remap("record_wanderer", BOPItems.MUSIC_DISC_WANDERER)
                .remap("rose_quartz_shard", BOPItems.ROSE_QUARTZ_CHUNK)
                .run(event);

        Remapper.create(Registries.BIOME)
                .remap("alps", Biomes.JAGGED_PEAKS)
                .remap("alps_foothills", Biomes.GROVE)
                .remap("ashen_inferno", BOPBiomes.ERUPTING_INFERNO)
                .remap("aspen_forest", BOPBiomes.ASPEN_GLADE)
                .remap("bamboo_grove", Biomes.CHERRY_GROVE)
                .remap("bamboo_blossom_grove", Biomes.CHERRY_GROVE)
                .remap("bayou_mangrove", BOPBiomes.BAYOU)
                .remap("boreal_forest", BOPBiomes.ASPEN_GLADE)
                .remap("brushland", BOPBiomes.DRYLAND)
                .remap("burnt_forest", BOPBiomes.OLD_GROWTH_DEAD_FOREST)
                .remap("chaparral", BOPBiomes.ROCKY_SHRUBLAND)
                .remap("cherry_blossom_grove", Biomes.CHERRY_GROVE)
                .remap("clover_patch", BOPBiomes.GRASSLAND)
                .remap("coniferous_lakes", BOPBiomes.CONIFEROUS_FOREST)
                .remap("dead_swamp", Biomes.SWAMP)
                .remap("deep_bayou", BOPBiomes.BAYOU)
                .remap("dense_marsh", BOPBiomes.MARSH)
                .remap("dense_woodland", BOPBiomes.OLD_GROWTH_WOODLAND)
                .remap("dry_boneyard", BOPBiomes.DRYLAND)
                .remap("dry_plains", BOPBiomes.SCRUBLAND)
                .remap("dry_steppe", BOPBiomes.DRYLAND)
                .remap("dunes", BOPBiomes.DUNE_BEACH)
                .remap("flower_meadow", BOPBiomes.FIELD)
                .remap("fungal_field", BOPBiomes.FUNGAL_JUNGLE)
                .remap("ghost_forest", BOPBiomes.MUSKEG)
                .remap("glowstone_grotto", BOPBiomes.CRYSTALLINE_CHASM)
                .remap("golden_prairie", BOPBiomes.PASTURE)
                .remap("grassland_clover_patch", BOPBiomes.GRASSLAND)
                .remap("gravel_beach", Biomes.STONY_SHORE)
                .remap("grove", BOPBiomes.MEDITERRANEAN_FOREST)
                .remap("grove_clearing", BOPBiomes.MEDITERRANEAN_FOREST)
                .remap("grove_lakes", BOPBiomes.MEDITERRANEAN_FOREST)
                .remap("highland_crag", BOPBiomes.CRAG)
                .remap("highland_moor", BOPBiomes.MOOR)
                .remap("infernal_ashlands", BOPBiomes.ERUPTING_INFERNO)
                .remap("jacaranda_forest", BOPBiomes.JACARANDA_GLADE)
                .remap("lavender_forest", BOPBiomes.JACARANDA_GLADE)
                .remap("lush_grassland", Biomes.SPARSE_JUNGLE)
                .remap("lush_swamp", Biomes.SWAMP)
                .remap("mangrove", Biomes.MANGROVE_SWAMP)
                .remap("meadow", BOPBiomes.FIELD)
                .remap("meadow_forest", BOPBiomes.FORESTED_FIELD)
                .remap("mediterranean_lakes", BOPBiomes.MEDITERRANEAN_FOREST)
                .remap("mire", BOPBiomes.MUSKEG)
                .remap("mystic_plains", BOPBiomes.MYSTIC_GROVE)
                .remap("oasis", Biomes.DESERT)
                .remap("ominous_mire", BOPBiomes.OMINOUS_WOODS)
                .remap("origin_beach", BOPBiomes.ORIGIN_VALLEY)
                .remap("origin_hills", BOPBiomes.ORIGIN_VALLEY)
                .remap("outback", BOPBiomes.LUSH_DESERT)
                .remap("overgrown_cliffs", BOPBiomes.ROCKY_RAINFOREST)
                .remap("poppy_field", BOPBiomes.LUSH_SAVANNA)
                .remap("rainbow_hills", BOPBiomes.AURORAL_GARDEN)
                .remap("rainbow_valley", BOPBiomes.AURORAL_GARDEN)
                .remap("rainforest_cliffs", BOPBiomes.ROCKY_RAINFOREST)
                .remap("rainforest_floodplain", BOPBiomes.FLOODPLAIN)
                .remap("redwood_forest_edge", BOPBiomes.REDWOOD_FOREST)
                .remap("redwood_hills", BOPBiomes.REDWOOD_FOREST)
                .remap("seasonal_orchard", BOPBiomes.ASPEN_GLADE)
                .remap("seasonal_pumpkin_patch", BOPBiomes.PUMPKIN_PATCH)
                .remap("shadowlands", BOPBiomes.WITHERED_ABYSS)
                .remap("shield", BOPBiomes.CONIFEROUS_FOREST)
                .remap("shroomy_wetland", BOPBiomes.WETLAND)
                .remap("shrubland_hills", BOPBiomes.ROCKY_SHRUBLAND)
                .remap("silkglade", BOPBiomes.WETLAND)
                .remap("snowy_forest", BOPBiomes.SNOWY_MAPLE_WOODS)
                .remap("steppe", BOPBiomes.WASTELAND_STEPPE)
                .remap("tall_dead_forest", BOPBiomes.OLD_GROWTH_DEAD_FOREST)
                .remap("temperate_rainforest", BOPBiomes.REDWOOD_FOREST)
                .remap("temperate_rainforest_hills", BOPBiomes.REDWOOD_FOREST)
                .remap("tropic_beach", BOPBiomes.TROPICS)
                .remap("tropical_rainforest", BOPBiomes.RAINFOREST)
                .remap("tundra_basin", BOPBiomes.TUNDRA)
                .remap("tundra_bog", BOPBiomes.BOG)
                .remap("undergarden", BOPBiomes.UNDERGROWTH)
                .remap("volcano_edge", BOPBiomes.VOLCANO)
                .remap("wetland_forest", BOPBiomes.WETLAND)
                .remap("white_beach", BOPBiomes.TROPICS)
                .remap("wooded_scrubland", BOPBiomes.SCRUBLAND)
                .remap("wooded_wasteland", BOPBiomes.WASTELAND)
                .remap("xeric_shrubland", BOPBiomes.DRYLAND)
                .run(event);
    }

    private static class Remapper<T>
    {
        private final ResourceKey<Registry<T>> registryKey;
        private Map<ResourceLocation, T> remaps = new HashMap<>();
        private Map<ResourceLocation, ResourceKey<T>> remapResourceKeys = new HashMap<>();

        private Remapper(ResourceKey<Registry<T>> registryKey)
        {
            this.registryKey = registryKey;
        }

        public static <T> Remapper<T> create(ResourceKey<Registry<T>> registry)
        {
            return new Remapper<>(registry);
        }

        public Remapper<T> remap(String oldId, T replacement)
        {
            this.remaps.put(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, oldId), replacement);
            return this;
        }

        public Remapper<T> remap(String oldId, RegistryObject<T> replacement)
        {
            return remap(oldId, replacement.get());
        }

        public Remapper<T> remap(String oldId, ResourceKey<T> replacement)
        {
            this.remapResourceKeys.put(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, oldId), replacement);
            return this;
        }

        public void run(MissingMappingsEvent event)
        {
            for (var mapping : event.getMappings(this.registryKey, BiomesOPlenty.MOD_ID))
            {
                if (this.remaps.containsKey(mapping.getKey()))
                {
                    mapping.remap(this.remaps.get(mapping.getKey()));
                }
                else if (this.remapResourceKeys.containsKey(mapping.getKey()))
                {
                    IForgeRegistry<T> registry = mapping.getRegistry();
                    ResourceKey<T> replacement = this.remapResourceKeys.get(mapping.getKey());

                    if (registry.containsKey(replacement.location()))
                    {
                        mapping.remap(registry.getValue(replacement.location()));
                    }
                }
            }

            // Attempt to remap items based on block remappings
            if ((ResourceKey)this.registryKey == Registries.BLOCK)
            {
                for (var mapping : event.getMappings(Registries.ITEM, BiomesOPlenty.MOD_ID))
                {
                    if (this.remaps.containsKey(mapping.getKey()))
                    {
                        Block block = (Block)this.remaps.get(mapping.getKey());
                        mapping.remap(block.asItem());
                    }
                }
            }
        }
    }
}
