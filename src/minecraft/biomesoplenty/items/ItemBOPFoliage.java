package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPFoliage extends ItemBlock
{
    private static final String[] foliageTypes = new String[] {"shortgrass", "mediumgrass", "highgrass", "bush", "sprout", "highgrasstop"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;
    
    public ItemBOPFoliage(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
        textures = new Icon[foliageTypes.length - 1];
        
        for (int i = 0; i < foliageTypes.length - 1; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:item" + foliageTypes[i]);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return (new StringBuilder()).append(foliageTypes[itemStack.getItemDamage()]).toString();
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        if (meta == 5)
            meta = 2;
        return textures[meta];
    }
    
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        
        if (itemStack.getItemDamage() != 2)
        {
            return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }
        else 
        {
            ++y;
            
            if (!(player.canPlayerEdit(x, y, z, side, itemStack) || player.canPlayerEdit(x, y + 1, z, side, itemStack)))
                return false;
            else if (world.getBlockMaterial(x, y, z).isReplaceable() && world.getBlockMaterial(x, y + 1, z).isReplaceable())
            {
                world.setBlock(x, y, z, itemStack.itemID, 2, 2);
                world.setBlock(x, y + 1, z, itemStack.itemID, 5, 2);
                world.notifyBlocksOfNeighborChange(x, y, z, itemStack.itemID);
                world.notifyBlocksOfNeighborChange(x, y + 1, z, itemStack.itemID);
                Block block = Block.blocksList[itemStack.itemID];
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                --itemStack.stackSize;
            }
            return true;
        }
    }
}
