package biomesoplenty.api;

import net.minecraft.item.Item;
import biomesoplenty.common.lib.BOPModInfo;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPItemHelper
{
	public static Item get(String name)
	{
		return GameRegistry.findItem(BOPModInfo.modID, name);
	}
}
