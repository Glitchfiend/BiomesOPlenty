package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemDart extends Item
{
	public ItemDart(int par1)
	{
		super(par1);
		setUnlocalizedName("shroomPowder");
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	itemIcon = iconRegister.registerIcon("BiomesOPlenty:dart");
	}
}
