package biomesoplenty.common.item;

import net.minecraft.item.ItemArmor;

public class ItemFlowerBand extends ItemArmor
{
    
    public ItemFlowerBand(ItemArmor.ArmorMaterial material, int renderIndex)
    {
        // flower bands are always on your head - armorType = 0
        super(material, renderIndex, 0);
        this.maxStackSize = 8;
    }
    
    
}
