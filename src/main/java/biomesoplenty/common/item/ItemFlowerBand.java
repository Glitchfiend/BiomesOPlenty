package biomesoplenty.common.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ItemFlowerBand extends ItemArmor
{
    
    public ItemFlowerBand(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // flower bands are always on your head - armorType = 0
        super(material, renderIndex, EntityEquipmentSlot.HEAD);
        this.maxStackSize = 8;
    }
    
    
}
