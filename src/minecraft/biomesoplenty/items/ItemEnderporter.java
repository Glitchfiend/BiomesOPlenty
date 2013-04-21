package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEnderporter extends Item
{
	
    public ItemEnderporter(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
		this.setMaxDamage(9);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		setUnlocalizedName("enderporter");
    }
	
	public void registerIcons(IconRegister iconRegister)
	{
    	itemIcon = iconRegister.registerIcon("BiomesOPlenty:enderporter");
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
			if (par3EntityPlayer.dimension == 0)
			{
				par1ItemStack.damageItem(1, par3EntityPlayer);
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 999));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 999));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 100, 999));
				par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 3));
				par3EntityPlayer.setPosition(par2World.getSpawnPoint().posX, 256, par2World.getSpawnPoint().posZ);
				par2World.playSoundAtEntity(par3EntityPlayer, "random.levelup", 1.0F, 5.0F);
			}
			else 
			{
				if (!par3EntityPlayer.worldObj.isRemote)
				{
					par3EntityPlayer.addChatMessage("\u00a75A mystical energy is preventing you from using this in the current world.");
				}
			}

            return par1ItemStack;
        }
    }
}
