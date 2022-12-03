/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.init;

import biomesoplenty.core.BiomesOPlenty;
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

        public static final TagKey<Block> FLESH = BlockTags.create(new ResourceLocation(BiomesOPlenty.MOD_ID, "flesh"));
    }

    public static class Fluids
    {
        private static void setup() {}

        public static final TagKey<Fluid> BLOOD = FluidTags.create(new ResourceLocation(BiomesOPlenty.MOD_ID, "blood"));
    }
}
