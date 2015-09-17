package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.common.entities.EntityPixie;

public class ItemJarEmpty extends Item
{
	public ItemJarEmpty()
	{
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:jarempty");
	}
	
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
            if (movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
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

                if (par2World.getBlock(i, j, k) == BOPCBlocks.honey && par2World.getBlockMetadata(i, j, k) == 7)
                {
                    --par1ItemStack.stackSize;
                    
                    par2World.setBlockToAir(i, j, k);

                    if (par1ItemStack.stackSize <= 0)
                    {
                        return new ItemStack(BOPCItems.jarFilled, 1, 0);
                    }

                    if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(BOPCItems.jarFilled, 1, 0)))
                    {
                        par3EntityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(BOPCItems.jarFilled, 1, 0), false);
                    }
                }
            }

            return par1ItemStack;
        }
    }
	
    @Override
    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase)
    {
        if (par3EntityLivingBase instanceof EntityPixie)
        {
            EntityPixie entitypixie = (EntityPixie)par3EntityLivingBase;

            entitypixie.setDead();
                
            --par1ItemStack.stackSize;
            
    		if (par2EntityPlayer.inventory.getFirstEmptyStack() >= 0)
			{
        		EntityItem entityitem = new EntityItem(par2EntityPlayer.worldObj, par2EntityPlayer.posX, par2EntityPlayer.posY, par2EntityPlayer.posZ, new ItemStack(BOPCItems.jarFilled, 1, 2));
    			if (!par2EntityPlayer.worldObj.isRemote)
    			{
    				par2EntityPlayer.worldObj.spawnEntityInWorld(entityitem);
    				
    				if (!(par2EntityPlayer instanceof FakePlayer))
    				{
    					entityitem.onCollideWithPlayer(par2EntityPlayer);
    				}
    			}
			}
    		else
    		{
                par2EntityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(BOPCItems.jarFilled, 1, 2), false);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
