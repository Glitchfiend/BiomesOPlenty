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

        public static final TagKey<Block> BLACKSTONE_DECORATION_PLACEABLE = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "blackstone_decoration_placeable"));
        public static final TagKey<Block> BRIMSTONE_DECORATION_PLACEABLE = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "brimstone_decoration_placeable"));
        public static final TagKey<Block> DEAD_CORALS = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "dead_corals"));
        public static final TagKey<Block> DEAD_CORAL_BLOCKS = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "dead_coral_blocks"));
        public static final TagKey<Block> DEAD_WALL_CORALS = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "dead_wall_corals"));
        public static final TagKey<Block> FLESH = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "flesh"));
        public static final TagKey<Block> FLESH_DECORATION_PLACEABLE = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "flesh_decoration_placeable"));
        public static final TagKey<Block> TIDEPOOL_REPLACEABLE = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "tidepool_replaceable"));

        public static TagKey<Block> create(ResourceLocation name)
        {
            return TagKey.create(Registries.BLOCK, name);
        }
    }

    public static class Items
    {
        private static void setup() {}

        public static final TagKey<Item> SHEARS = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "shears"));

        public static TagKey<Item> create(ResourceLocation name)
        {
            return TagKey.create(Registries.ITEM, name);
        }
    }

    public static class Fluids
    {
        private static void setup() {}

        public static final TagKey<Fluid> BLOOD = create(new ResourceLocation(BiomesOPlenty.MOD_ID, "blood"));

        public static TagKey<Fluid> create(final ResourceLocation name)
        {
            return TagKey.create(Registries.FLUID, name);
        }
    }
}
