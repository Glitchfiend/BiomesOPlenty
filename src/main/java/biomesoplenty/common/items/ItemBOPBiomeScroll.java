package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPBiomeScroll extends Item
{
    public ItemBOPBiomeScroll()
    {
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("biomesoplenty:biomescroll");
    }
    
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		return true;
	}
}
