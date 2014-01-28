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
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPBiomeEssence extends Item
{
    public ItemBOPBiomeEssence()
    {
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public boolean hasContainerItem(ItemStack itemStack)
    {
        return true;
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return itemStack;
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
            if (itemStack.getTagCompound().hasKey("biomeID")) 
            {
                BiomeGenBase biome = BiomeGenBase.func_150565_n()[itemStack.getTagCompound().getInteger("biomeID")];

                if (biome != null)
                {
                    infoList.add(biome.biomeName);
                }
            }
        }
    }

	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		return true;
	}
}
