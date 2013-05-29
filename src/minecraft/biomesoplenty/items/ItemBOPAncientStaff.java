package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPAncientStaff extends Item
{
    private static String[] parts = {"ancientstaff", "staffhandle", "staffpole", "stafftopper"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public ItemBOPAncientStaff(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
    }

    @Override
    public boolean hasEffect(ItemStack itemStack)
    {
        if (itemStack.getItemDamage() == 0)
            return true;
        else
            return false;
    }
    
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
        textures = new Icon[parts.length];
        
        for (int i = 0; i < parts.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+parts[i]);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta >= parts.length)
            meta = 0;
        
        return parts[meta];
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        if (meta < 0 || meta >= textures.length)
            meta = 0;
        
        return textures[meta];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
    {
        for(int meta = 0; meta < parts.length; ++meta)
            subTypes.add(new ItemStack(itemId, 1, meta));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.dimension == 0)
        {

            if (par2World.getBlockId(0, 64 - 32, 0) != Blocks.promisedPortal.get().blockID)
            {

                if (!par3EntityPlayer.worldObj.isRemote)
                {
                    par3EntityPlayer.addChatMessage("\u00a75A gateway to the \u00a76\u00a7l\u00a7nPromised Land\u00a75 has been buried at the origin of this world.");
                }

                int var99 = 32;

                par2World.setBlock(-1, 62 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(0, 62 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(1, 62 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(1, 62 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(1, 62 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(0, 62 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-1, 62 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-1, 62 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(0, 62 - var99, 0, Block.whiteStone.blockID);

                par2World.setBlock(-1, 63 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(0, 63 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(1, 63 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(2, 63 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(2, 63 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(2, 63 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(1, 63 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(0, 63 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-1, 63 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-2, 63 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-2, 63 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(-2, 63 - var99, 1, Block.whiteStone.blockID);

                par2World.setBlock(-1, 64 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(0, 64 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(1, 64 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(2, 64 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(2, 64 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(2, 64 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(1, 64 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(0, 64 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-1, 64 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-2, 64 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-2, 64 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(-2, 64 - var99, 1, Block.whiteStone.blockID);

                par2World.setBlock(-1, 65 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(0, 65 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(1, 65 - var99, 2, Block.whiteStone.blockID);
                par2World.setBlock(2, 65 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(2, 65 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(2, 65 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(1, 65 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(0, 65 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-1, 65 - var99, -2, Block.whiteStone.blockID);
                par2World.setBlock(-2, 65 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-2, 65 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(-2, 65 - var99, 1, Block.whiteStone.blockID);

                par2World.setBlock(-1, 66 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(0, 66 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(1, 66 - var99, 1, Block.whiteStone.blockID);
                par2World.setBlock(1, 66 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(1, 66 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(0, 66 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-1, 66 - var99, -1, Block.whiteStone.blockID);
                par2World.setBlock(-1, 66 - var99, 0, Block.whiteStone.blockID);
                par2World.setBlock(0, 66 - var99, 0, Block.whiteStone.blockID);

                par2World.setBlock(-1, 63 - var99, 1, 0);
                par2World.setBlock(0, 63 - var99, 1, 0);
                par2World.setBlock(1, 63 - var99, 1, 0);
                par2World.setBlock(1, 63 - var99, 0, 0);
                par2World.setBlock(1, 63 - var99, -1, 0);
                par2World.setBlock(0, 63 - var99, -1, 0);
                par2World.setBlock(-1, 63 - var99, -1, 0);
                par2World.setBlock(-1, 63 - var99, 0, 0);
                par2World.setBlock(0, 63 - var99, 0, 0);

                par2World.setBlock(-1, 64 - var99, 1, 0);
                par2World.setBlock(0, 64 - var99, 1, 0);
                par2World.setBlock(1, 64 - var99, 1, 0);
                par2World.setBlock(1, 64 - var99, 0, 0);
                par2World.setBlock(1, 64 - var99, -1, 0);
                par2World.setBlock(0, 64 - var99, -1, 0);
                par2World.setBlock(-1, 64 - var99, -1, 0);
                par2World.setBlock(-1, 64 - var99, 0, 0);

                par2World.setBlock(-1, 65 - var99, 1, 0);
                par2World.setBlock(0, 65 - var99, 1, 0);
                par2World.setBlock(1, 65 - var99, 1, 0);
                par2World.setBlock(1, 65 - var99, 0, 0);
                par2World.setBlock(1, 65 - var99, -1, 0);
                par2World.setBlock(0, 65 - var99, -1, 0);
                par2World.setBlock(-1, 65 - var99, -1, 0);
                par2World.setBlock(-1, 65 - var99, 0, 0);
                par2World.setBlock(0, 65 - var99, 0, 0);

                par2World.setBlock(0, 64 - var99, 0, Blocks.promisedPortal.get().blockID);

                par2World.setBlock(-2, 64 - var99, 2, 0);
                par2World.setBlock(2, 64 - var99, 2, 0);
                par2World.setBlock(2, 64 - var99, -2, 0);
                par2World.setBlock(-2, 64 - var99, -2, 0);

                par2World.setBlock(-2, 65 - var99, 2, 0);
                par2World.setBlock(2, 65 - var99, 2, 0);
                par2World.setBlock(2, 65 - var99, -2, 0);
                par2World.setBlock(-2, 65 - var99, -2, 0);

                par2World.setBlock(-2, 66 - var99, 2, 0);
                par2World.setBlock(2, 66 - var99, 2, 0);
                par2World.setBlock(2, 66 - var99, -2, 0);
                par2World.setBlock(-2, 66 - var99, -2, 0);
            }
        }

        return par1ItemStack;
    }
}
