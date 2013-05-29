package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPCoral extends ItemBlock
{
    private static final String[] coral = new String[] {"kelp"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;    

    public ItemBOPCoral(int par1)
    {
        super(par1);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta)
    {
        return meta & 15;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        int meta = itemStack.getItemDamage();
        if (meta < 0 || meta >= coral.length)
            meta = 0;
        
        return coral[meta];
    }
	
    @Override
    public Icon getIconFromDamage(int meta)
    {
       return Block.blocksList[this.itemID].getIcon(0, meta);
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
		return null;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 20;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	int id = world.getBlockId(x, y, z);

    	if (id == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
    	{
    		side = 1;
    	}
    	else if (id != Block.vine.blockID && id != Block.tallGrass.blockID && id != Block.deadBush.blockID && (Block.blocksList[id] == null || !Block.blocksList[id].isBlockReplaceable(world, x, y, z)))
    	{
    		if (side == 0)
    		{
    			--y;
    		}

    		if (side == 1)
    		{
    			++y;
    		}

    		if (side == 2)
    		{
    			--z;
    		}

    		if (side == 3)
    		{
    			++z;
    		}

    		if (side == 4)
    		{
    			--x;
    		}

    		if (side == 5)
    		{
    			++x;
    		}
    	}

    	if (itemstack.stackSize == 0)
    	{
    		return false;
    	}
    	else if (!player.canPlayerEdit(x, y, z, side, itemstack))
    	{
    		return false;
    	}
    	else if (y == 255 && Block.blocksList[this.itemID].blockMaterial.isSolid())
    	{
    		return false;
    	}
    	else if (world.canPlaceEntityOnSide(this.itemID, x, y, z, false, side, player, itemstack))
    	{	
    		if (itemstack.getItemDamage() == 0) 
    		{
    			if (world.getBlockId(x, y + 1, z) == Block.waterStill.blockID || world.getBlockId(x, y + 1, z) == Block.waterMoving.blockID)
    			{
    	    		onItemUsePlaceBlock(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
    	    		
    				return true;
    			}
    		}
    		else
    		{
    			onItemUsePlaceBlock(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);

    			return true;
    		}
    	}

    	return false;
    }
    
    public void onItemUsePlaceBlock(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		Block block = Block.blocksList[this.itemID];
		int j1 = this.getMetadata(itemstack.getItemDamage());
		int k1 = Block.blocksList[this.itemID].onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, j1);

		if (placeBlockAt(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ, k1))
		{	
			world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			--itemstack.stackSize;
		}
    }
}
