package biomesoplenty.api;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import cpw.mods.fml.common.registry.GameData;

public class BOPItemHelper
{
    public static ToolMaterial  toolMaterialMud;
    public static ArmorMaterial armorMaterialMud;
    
    public static ToolMaterial  toolMaterialAmethyst;
    public static ArmorMaterial armorMaterialAmethyst;
    
    public static ArmorMaterial armorMaterialUnprotective;
    
    public static String getUniqueName(Item item)
    {
        return GameData.getItemRegistry().getNameForObject(item);
    }
}
