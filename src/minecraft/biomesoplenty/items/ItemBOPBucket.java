package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPBucket extends ItemBucket 
{
	public ItemBOPBucket(int i, int liquidID) 
	{
		super(i, liquidID);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("BiomesOPlenty:spring_water_bucket");
	}
}
