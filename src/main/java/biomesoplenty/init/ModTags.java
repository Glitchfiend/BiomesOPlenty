package biomesoplenty.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
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

        public static final Tag.Named<Block> FLESH = BlockTags.bind("biomesoplenty:flesh");
    }

    public static class Fluids
    {
        private static void setup() {}

        public static final Tag.Named<Fluid> BLOOD = FluidTags.bind("biomesoplenty:blood");
    }
}
