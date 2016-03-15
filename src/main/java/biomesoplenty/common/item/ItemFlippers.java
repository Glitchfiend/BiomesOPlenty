package biomesoplenty.common.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemFlippers extends ItemArmor
{
    
    public ItemFlippers(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // flippers are always on your feet - armorType = 3
        super(material, renderIndex, EntityEquipmentSlot.FEET);
    }
    
}

