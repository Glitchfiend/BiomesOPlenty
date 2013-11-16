package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.Items;

public class ItemJarEmpty extends Item
{
	public ItemJarEmpty(int par1)
	{
		super(par1);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:jarempty");
	}
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (movingobjectposition == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
            {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
                {
                    return par1ItemStack;
                }

                if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
                {
                    return par1ItemStack;
                }

                if (par2World.getBlockId(i, j, k) == Fluids.honey.get().blockID && par2World.getBlockMetadata(i, j, k) == 7)
                {
                    --par1ItemStack.stackSize;
                    
                    par2World.setBlockToAir(i, j, k);

                    if (par1ItemStack.stackSize <= 0)
                    {
                        return new ItemStack(Items.jarFilled.get(), 1, 0);
                    }

                    if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.jarFilled.get(), 1, 0)))
                    {
                        par3EntityPlayer.dropPlayerItem(new ItemStack(Items.jarFilled.get(), 1, 0));
                    }
                }
            }

            return par1ItemStack;
        }
    }
}
