/*package biomesoplenty.common.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.client.gui.GuiScreenBiomeBook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBiomeBook extends ItemEditableBook
{
    public ItemBiomeBook()
    {
        super();
        
        this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        setBookInfo(itemStack);
        
        if (world.isRemote)
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBiomeBook(player, itemStack));
        }
        
        return itemStack;
    }
    
    @Override
    //TODO: public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
        ItemStack biomeBook = new ItemStack(item);
        
        setBookInfo(biomeBook);
        list.add(biomeBook);
    }
    
    private void setBookInfo(ItemStack itemStack)
    {
        if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        
        itemStack.getTagCompound().setString("title", this.getItemStackDisplayName(itemStack));
        itemStack.getTagCompound().setString("author", "The BoP Team");
        
        NBTTagList tagList = new NBTTagList();
        
        tagList.appendTag(new NBTTagString("Bla \n<link>Hi<2></link>"));
        tagList.appendTag(new NBTTagString("Test I"));
        tagList.appendTag(new NBTTagString("Test II"));
        
        itemStack.getTagCompound().setTag("pages", tagList);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("biomesoplenty:biomebook");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack)
    {
        return false;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return EnumChatFormatting.AQUA + super.getItemStackDisplayName(itemStack);
    }
}*/
