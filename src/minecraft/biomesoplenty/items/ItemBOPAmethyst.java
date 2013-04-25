package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPAmethyst extends ItemBlock
{
    private static final String[] types = new String[] {"amethystOre", "amethystBlock"};

    public ItemBOPAmethyst(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return types[itemstack.getItemDamage() & 15];
    }
}
