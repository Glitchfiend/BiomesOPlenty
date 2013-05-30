package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPAncientStaff extends Item
{
    private static String[] parts = {"ancientstaff", "staffhandle", "staffpole", "stafftopper", "ancientstaffbroken"};
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
        
        //textures[parts.length - 1] = iconRegister.registerIcon("BiomesOPlenty:ancientstaff");
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
            int i = MathHelper.floor_double(par3EntityPlayer.posX);
            int j = 130;
            int k = MathHelper.floor_double(par3EntityPlayer.posZ);
            
            boolean isAir = true;
            
            for (int iy = -1; iy < 4; iy++)
            {
                for (int ix = -2; ix < 3; ix++)
                    for (int iz = -2; iz < 3; iz++)
                        if (!par2World.isAirBlock(i + ix, j + iy, k + iz))
                        {
                            isAir = false;
                        }
            }

            if (isAir)
            {

                if (!par3EntityPlayer.worldObj.isRemote)
                {
                    par3EntityPlayer.addChatMessage("A gateway to the Promised Land has appeared in the sky above.");
                }
                
                
                
                boolean flag;

                for (int iy = -1; iy < 4; iy++)
                {
                    for (int ix = -2; ix < 3; ix++)
                        for (int iz = -2; iz < 3; iz++)
                        {
                            flag = ix == -2 || ix == 2 || iz == -2 || iz == 2 || iy == -1 || iy == 3;
                            par2World.setBlock(i + ix, j + iy, k + iz, flag ? Block.whiteStone.blockID : 0);
                        }
                    
                    for (int ix = -2; ix < 3; ix++)
                        for (int iz = -2; iz < 3; iz++)
                        {
                            par2World.notifyBlocksOfNeighborChange(i + ix, j + iy, k + iz, par2World.getBlockId(i + ix, j + iy, k + iz));
                        }
                }

                par2World.setBlock(i, j + 1, k, Blocks.promisedPortal.get().blockID);
                
                par1ItemStack.setItemDamage(4);
            }
        }

        return par1ItemStack;
    }
}
