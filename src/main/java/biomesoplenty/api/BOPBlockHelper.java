package biomesoplenty.api;

import net.minecraft.block.Block;
import biomesoplenty.common.utils.BOPModInfo;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlockHelper
{
    public static Block get(String name)
    {
        return GameRegistry.findBlock(BOPModInfo.modID, name);
    }
    
    public static String getUniqueName(Block block)
    {
        return GameData.blockRegistry.getNameForObject(block);
    }
}
