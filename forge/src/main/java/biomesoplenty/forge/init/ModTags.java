/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.init;

import biomesoplenty.forge.core.BiomesOPlentyForge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags
{
    public static void setup()
    {
        Blocks.setup();
        Fluids.setup();
    }

    public static class Blocks
    {
        private static void setup() {}

        public static final TagKey<Block> BLACKSTONE_DECORATION_PLACEABLE = BlockTags.create(new ResourceLocation(BiomesOPlentyForge.MOD_ID, "blackstone_decoration_placeable"));
        public static final TagKey<Block> BRIMSTONE_DECORATION_PLACEABLE = BlockTags.create(new ResourceLocation(BiomesOPlentyForge.MOD_ID, "brimstone_decoration_placeable"));
        public static final TagKey<Block> FLESH = BlockTags.create(new ResourceLocation(BiomesOPlentyForge.MOD_ID, "flesh"));
        public static final TagKey<Block> FLESH_DECORATION_PLACEABLE = BlockTags.create(new ResourceLocation(BiomesOPlentyForge.MOD_ID, "flesh_decoration_placeable"));
    }

    public static class Fluids
    {
        private static void setup() {}

        public static final TagKey<Fluid> BLOOD = FluidTags.create(new ResourceLocation(BiomesOPlentyForge.MOD_ID, "blood"));
    }
}
