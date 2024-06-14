/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags
{
    public static void setup()
    {
        Blocks.setup();
        Items.setup();
        Fluids.setup();
    }

    public static class Blocks
    {
        private static void setup() {}

        public static final TagKey<Block> FIR_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "fir_logs"));
        public static final TagKey<Block> PINE_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "pine_logs"));
        public static final TagKey<Block> MAPLE_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "maple_logs"));
        public static final TagKey<Block> REDWOOD_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "redwood_logs"));
        public static final TagKey<Block> MAHOGANY_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "mahogany_logs"));
        public static final TagKey<Block> JACARANDA_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "jacaranda_logs"));
        public static final TagKey<Block> PALM_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "palm_logs"));
        public static final TagKey<Block> WILLOW_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "willow_logs"));
        public static final TagKey<Block> DEAD_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "dead_logs"));
        public static final TagKey<Block> MAGIC_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "magic_logs"));
        public static final TagKey<Block> UMBRAN_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "umbran_logs"));
        public static final TagKey<Block> HELLBARK_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "hellbark_logs"));
        public static final TagKey<Block> EMPYREAL_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "empyreal_logs"));

        public static final TagKey<Block> BLACKSTONE_DECORATION_PLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "blackstone_decoration_placeable"));
        public static final TagKey<Block> BRIMSTONE_DECORATION_PLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "brimstone_decoration_placeable"));
        public static final TagKey<Block> DEAD_CORALS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "dead_corals"));
        public static final TagKey<Block> DEAD_CORAL_BLOCKS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "dead_coral_blocks"));
        public static final TagKey<Block> DEAD_WALL_CORALS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "dead_wall_corals"));
        public static final TagKey<Block> FLESH = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "flesh"));
        public static final TagKey<Block> FLESH_DECORATION_PLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "flesh_decoration_placeable"));
        public static final TagKey<Block> NULL_PLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "null_replaceable"));
        public static final TagKey<Block> NULL_REPLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "null_replaceable"));
        public static final TagKey<Block> TIDEPOOL_REPLACEABLE = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "tidepool_replaceable"));

        public static TagKey<Block> create(ResourceLocation name)
        {
            return TagKey.create(Registries.BLOCK, name);
        }
    }

    public static class Items
    {
        private static void setup() {}

        public static final TagKey<Item> FIR_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "fir_logs"));
        public static final TagKey<Item> PINE_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "pine_logs"));
        public static final TagKey<Item> MAPLE_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "maple_logs"));
        public static final TagKey<Item> REDWOOD_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "redwood_logs"));
        public static final TagKey<Item> MAHOGANY_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "mahogany_logs"));
        public static final TagKey<Item> JACARANDA_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "jacaranda_logs"));
        public static final TagKey<Item> PALM_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "palm_logs"));
        public static final TagKey<Item> WILLOW_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "willow_logs"));
        public static final TagKey<Item> DEAD_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "dead_logs"));
        public static final TagKey<Item> MAGIC_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "magic_logs"));
        public static final TagKey<Item> UMBRAN_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "umbran_logs"));
        public static final TagKey<Item> HELLBARK_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "hellbark_logs"));
        public static final TagKey<Item> EMPYREAL_LOGS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "empyreal_logs"));

        public static final TagKey<Item> SHEARS = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "shears"));

        public static TagKey<Item> create(ResourceLocation name)
        {
            return TagKey.create(Registries.ITEM, name);
        }
    }

    public static class Fluids
    {
        private static void setup() {}

        public static final TagKey<Fluid> BLOOD = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "blood"));
        public static final TagKey<Fluid> NULL = create(ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, "null"));

        public static TagKey<Fluid> create(final ResourceLocation name)
        {
            return TagKey.create(Registries.FLUID, name);
        }
    }
}
