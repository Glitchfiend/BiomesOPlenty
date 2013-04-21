package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.items.projectiles.EntityMudball;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOP extends Item
{
    private static String[] items = {"mudball", "ash", "amethyst", "mudbrick"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;

	public ItemBOP(int id)
	{
		super(id);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	public void registerIcons(IconRegister iconRegister)
	{
	    textures = new Icon[items.length];
        
        for (int i = 0; i < items.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+items[i]);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return items[itemStack.getItemDamage()];
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return textures[meta];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
    {
        for(int meta = 0; meta < items.length; ++meta)
            subTypes.add(new ItemStack(itemId, 1, meta));
    }

	public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (itemStack.getItemDamage() == 0)
		{
			if (!par3EntityPlayer.capabilities.isCreativeMode)
			{
				--itemStack.stackSize;
			}

			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!par2World.isRemote)
			{
				par2World.spawnEntityInWorld(new EntityMudball(par2World, par3EntityPlayer));
			}
		}
		return itemStack;
	} 
}
