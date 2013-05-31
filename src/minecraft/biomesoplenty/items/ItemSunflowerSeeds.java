package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import biomesoplenty.BiomesOPlenty;

public class ItemSunflowerSeeds extends ItemFood
{
	public ItemSunflowerSeeds(int par1, int par2, float par3, boolean par4)
	{
		super(par1, par2, par3, par4);
		setAlwaysEdible().setUnlocalizedName("sunflowerSeeds");
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("BiomesOPlenty:sunflowerseeds");
	}
}
