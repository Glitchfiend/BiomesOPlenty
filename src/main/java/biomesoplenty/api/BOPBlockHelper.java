package biomesoplenty.api;

import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameData;

public class BOPBlockHelper
{
    public static String getUniqueName(Block block)
    {
        return GameData.getBlockRegistry().getNameForObject(block);
    }
}
