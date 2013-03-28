package tdwp_ftw.biomesop.items;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.helpers.TeleporterPromised;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemAncientStaff extends Item
{
	
    public ItemAncientStaff(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
    }
	
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if (par3EntityPlayer.dimension == 0)
			{
			par3EntityPlayer.addChatMessage("A gateway to the Promised Land has been buried at the origin of this world.");
			
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
			
			par2World.setBlock(0, 64 - var99, 0, mod_BiomesOPlenty.promisedPortal.blockID);
			
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
		
		return par1ItemStack;
    }
}
