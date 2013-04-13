package tdwp_ftw.biomesop.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBOPFlower extends ItemBlock
{
    private static final String[] plants = new String[] {"swampflower", "deadbloom", "glowflower", "hydrangea", "orangeflower", "pinkflower", "purpleflower", "violet", "whiteflower"};
    
    public ItemBOPFlower(int par1)
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
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return (new StringBuilder()).append(plants[itemStack.getItemDamage()]).toString();
    }
    
    public Icon getIconFromDamage(int meta)
    {
        return Block.blocksList[this.itemID].getBlockTextureFromSideAndMetadata(0, meta);
    }
}
