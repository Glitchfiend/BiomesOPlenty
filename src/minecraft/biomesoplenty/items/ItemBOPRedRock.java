package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPRedRock extends ItemBlock
{
    private static final String[] types = new String[] {"redrock", "redcobble", "redbrick"};
    
    public ItemBOPRedRock(int par1)
    {
        super(par1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return types[itemstack.getItemDamage() & 15];
    }
}
