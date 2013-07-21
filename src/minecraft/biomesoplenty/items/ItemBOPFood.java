package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class ItemBOPFood extends ItemFood
{
	private static final String[] foodTypes = new String[] {"berries", "shroomPowder", "wildcarrots", "sunflowerseeds", "saladfruit", "saladveggie", "saladshroom", "earth"};
	private Icon[] textures;
	
	public ItemBOPFood(int par1)
	{
		super(par1, 0, 0.0F, false);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		setHasSubtypes(true);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
		boolean alwaysEdible = ReflectionHelper.getPrivateValue(ItemFood.class, (ItemFood)Item.itemsList[itemstack.itemID], "alwaysEdible");
		
		if (itemstack.getItemDamage() == 1)
		{
			if (player.canEat(true))
			{
				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
			}
		}
		else
		{
			if (player.canEat(false))
			{
				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
			}
		}

        return itemstack;
    }
	
	@Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
		if (itemstack.getItemDamage() == 0)
		{
			return addFoodAndSaturation(world, itemstack, player, 2, 0.2F);
		}
		else if (itemstack.getItemDamage() == 1)
		{
			if (world.rand.nextFloat() < 0.6F)
			{
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 225, 0));
			}

			return addFoodAndSaturation(world, itemstack, player, 1, 0.1F);
		}
		else if (itemstack.getItemDamage() == 2)
		{
			if (world.rand.nextFloat() < 0.6F)
			{
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 225, 1));
			}
			
			return addFoodAndSaturation(world, itemstack, player, 3, 0.5F);
		}
		else if (itemstack.getItemDamage() == 3)
		{
			return addFoodAndSaturation(world, itemstack, player, 2, 0.5F);
		}
		else if (itemstack.getItemDamage() == 4)
		{
			if (world.rand.nextFloat() < 0.05F)
			{
				player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 775, 1));
			}
			
			return addFoodAndSaturation(world, itemstack, player, 6, 0.8F);
		}
		else if (itemstack.getItemDamage() == 5)
		{
			if (world.rand.nextFloat() < 0.05F)
			{
				player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 1100, 1));
			}
			
			return addFoodAndSaturation(world, itemstack, player, 6, 1.2F);
		}
		else if (itemstack.getItemDamage() == 6)
		{
			if (world.rand.nextFloat() < 0.05F)
			{
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 550, 1));
			}
			
			return addFoodAndSaturation(world, itemstack, player, 6, 1.6F);
		}
		
		return addFoodAndSaturation(world, itemstack, player, 0, 0);
    }
	
	private ItemStack addFoodAndSaturation(World world, ItemStack itemstack, EntityPlayer player, int food, float saturation)
	{
        --itemstack.stackSize;
        player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + food);
        player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + saturation);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        super.onFoodEaten(itemstack, world, player);
        if (itemstack.getItemDamage() == 4)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
            {
                player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
            }
        }
        if (itemstack.getItemDamage() == 5)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
            {
                player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
            }
        }
        if (itemstack.getItemDamage() == 6)
        {
            if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
            {
                player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
            }
        }
        
        return itemstack;
	}
	
	@Override
    public void getSubItems(int itemID, CreativeTabs par2CreativeTabs, List list)
    {
		for (int i = 0; i < foodTypes.length; ++i) 
		{
			if (i != 7)
			{
				list.add(new ItemStack(itemID, 1, i));
			}
		}
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[foodTypes.length];

		for (int i = 0; i < foodTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+ foodTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= foodTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + foodTypes[meta];
	}
}
