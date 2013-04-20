package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPAppleLeaves extends ItemBlock
{
    @SideOnly(Side.CLIENT)
    private Icon texture;
    
    public ItemBOPAppleLeaves(int par1)
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
}