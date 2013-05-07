package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.items.projectiles.EntityDart;

public class ItemDartBlower extends Item
{
    public ItemDartBlower(int par1)
    {
        super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(192);
        setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
        setUnlocalizedName("dartblower");
    }

    public void registerIcons(IconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon("BiomesOPlenty:dartblower");
    }
    
    public ItemStack onItemRightClick(ItemStack itemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
            --itemStack.stackSize;


        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);

        if (!par2World.isRemote)
            par2World.spawnEntityInWorld(new EntityDart(par2World, par3EntityPlayer));

        return itemStack;
    } 
}
