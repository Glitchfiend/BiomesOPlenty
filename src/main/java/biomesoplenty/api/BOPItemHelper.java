package biomesoplenty.api;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import biomesoplenty.common.utils.BOPModInfo;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPItemHelper
{
	public static ToolMaterial toolMaterialMud;
	public static ArmorMaterial armorMaterialMud;
	
	public static ToolMaterial toolMaterialAmethyst;
	public static ArmorMaterial armorMaterialAmethyst;
	
	public static ArmorMaterial armorMaterialUnprotective;
	
	public static Item get(String name)
	{
		return GameRegistry.findItem(BOPModInfo.modID, name);
	}
}
