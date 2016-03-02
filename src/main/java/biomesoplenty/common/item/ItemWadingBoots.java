package biomesoplenty.common.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemWadingBoots extends ItemArmor
{
    
    public ItemWadingBoots(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // boots are always on your feet - armorType = 3
        super(material, renderIndex, EntityEquipmentSlot.FEET);        
    }
    
}

