package tdwp_ftw.biomesop.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemEnderporter extends Item
{
	
    public ItemEnderporter(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
		this.setMaxDamage(9);
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
		if (par3EntityPlayer.ridingEntity != null)
        {
            return par1ItemStack;
        }
        else
        {
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 999));
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 999));
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 100, 999));
			par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 3));
			par3EntityPlayer.setPosition(par2World.getSpawnPoint().posX, 256, par2World.getSpawnPoint().posZ);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.levelup", 1.0F, 5.0F);

            return par1ItemStack;
        }
    }
}
