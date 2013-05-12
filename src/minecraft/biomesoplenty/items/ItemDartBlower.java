package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import biomesoplenty.items.projectiles.EntityDart;
import biomesoplenty.items.projectiles.EntityPoisonDart;

public class ItemDartBlower extends Item
{
    public ItemDartBlower(int par1)
    {
        super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(63);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        setUnlocalizedName("dartblower");
    }

    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("BiomesOPlenty:dartblower");
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

        if (par3EntityPlayer.inventory.hasItem(Items.dart.get().itemID))
        {
            //EntityArrow entitydart = new EntityArrow(par2World, par3EntityPlayer, 2.0F);
            
            itemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);

            if (!flag)
                par3EntityPlayer.inventory.consumeInventoryItem(Items.dart.get().itemID);

            if (!par2World.isRemote)
            	if (par3EntityPlayer.inventory.hasItemStack(new ItemStack(Items.dart.get().itemID, 1, 0)))
                    par2World.spawnEntityInWorld(new EntityDart(par2World, par3EntityPlayer, 2.0F));
            	else
                    par2World.spawnEntityInWorld(new EntityPoisonDart(par2World, par3EntityPlayer, 2.0F));
        }

        return itemStack;
    } 
}
