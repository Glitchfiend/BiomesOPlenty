package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBOPColorizedSapling extends ItemBlock
{
    private static final String[] saplings = new String[] {"acacia", "mangrove", "palm", "redwood", "willow"};
    
    public ItemBOPColorizedSapling(int par1)
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
        return (new StringBuilder()).append(saplings[itemStack.getItemDamage()]).append("Sapling").toString();
    }
    
    
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return Block.blocksList[this.itemID].getBlockTextureFromSideAndMetadata(0, meta);
    }
}
