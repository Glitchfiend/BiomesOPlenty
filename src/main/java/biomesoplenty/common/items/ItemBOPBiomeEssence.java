package biomesoplenty.common.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;

public class ItemBOPBiomeEssence extends Item
{
    public ItemBOPBiomeEssence()
    {
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("biomesoplenty:biomeessence");
    }
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List infoList, boolean advancedItemTooltips)
    {
        if (itemStack.hasTagCompound())
        {
            if (itemStack.getTagCompound().hasKey("biome")) infoList.add(itemStack.getTagCompound().getString("biome"));
        }
    }
    
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int par2)
	{
		if (itemStack.hasTagCompound())
        {
            if (itemStack.getTagCompound().hasKey("color"))
            {
            	return itemStack.getTagCompound().getInteger("color");
            }
        }
		
		return 16777215;
	}
    
	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		return true;
	}
}
