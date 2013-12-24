package biomesoplenty.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import biomesoplenty.common.lib.BOPModInfo;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class BOPBlockHelper
{
	public static Block get(String name)
	{
		return GameRegistry.findBlock(BOPModInfo.modID, name);
	}
}
