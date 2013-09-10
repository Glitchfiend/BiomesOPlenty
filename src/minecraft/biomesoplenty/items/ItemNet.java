package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class ItemNet extends Item
{

	public ItemNet(int par1)
	{
		super(par1);
		maxStackSize = 1;
		this.setMaxDamage(63);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:net");
	}
}
