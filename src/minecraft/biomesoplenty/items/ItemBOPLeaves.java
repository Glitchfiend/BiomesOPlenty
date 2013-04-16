package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPLeaves extends ItemBlock
{
    private static final String[] leaves = new String[] {"autumn", "bamboo", "blue", "dark", "dead", "fir", "holy", "orange", "origin", "pink", "red", "white"};
    
    public ItemBOPLeaves(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @Override
    public int getMetadata(int meta)
    {
        return meta & 15;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return (new StringBuilder()).append(leaves[itemStack.getItemDamage()]).append("Leaves").toString();
    }
}
