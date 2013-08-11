package biomesoplenty.items;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPFood extends ItemFood
{
	private static final String[] foodTypes = new String[] {"berries", "shroompowder", "wildcarrots", "sunflowerseeds", "saladfruit", "saladveggie", "saladshroom", "earth"};
	private Icon[] textures;
	
	private int healAmount;
    private float saturationModifier;
	
	public ItemBOPFood(int par1)
	{
		super(par1, 0, 0.0F, false);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
		setHasSubtypes(true);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
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
		switch (itemstack.getItemDamage())
		{
			case 0:
				this.healAmount = 2;
				this.saturationModifier = 0.2F;
				break;
				
			case 1:
				this.healAmount = 1;
				this.saturationModifier = 0.1F;
				break;
				
			case 2:
				this.healAmount = 3;
				this.saturationModifier = 0.5F;
				break;
				
			case 3:
				this.healAmount = 2;
				this.saturationModifier = 0.5F;
				break;
				
			case 4:
				this.healAmount = 6;
				this.saturationModifier = 0.8F;
				break;
				
			case 5:
				this.healAmount = 6;
				this.saturationModifier = 1.2F;
				break;
				
			case 6:
				this.healAmount = 6;
				this.saturationModifier = 1.6F;
				break;
				
			default:
				this.healAmount = 0;
				this.saturationModifier = 0.0F;
				break;
		}
		
		--itemstack.stackSize;
        player.getFoodStats().addStats(this);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemstack, world, player);
        
        switch (itemstack.getItemDamage())
        {
        	case 4:
        	case 5:
        	case 6:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
                {
                    player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
                }
        }
        
        return itemstack;
    }
	
	@Override
	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            switch (itemstack.getItemDamage())
            {
            	case 1:
            		if (world.rand.nextFloat() < 0.6F)
           				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 225, 0));
            		break;
            		
            	case 2:
            		if (world.rand.nextFloat() < 0.6F)
           				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 225, 1));
            		break;
            		
            	case 4:
            		if (world.rand.nextFloat() < 0.05F)
            			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 775, 1));
            		break;
            			
            	case 5:
            		if (world.rand.nextFloat() < 0.05F)
            			player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 1100, 1));
            		break;
            			
            	case 6:
            		if (world.rand.nextFloat() < 0.05F)
            			player.addPotionEffect(new PotionEffect(Potion.jump.id, 550, 1));
            		break;
            }
        }
    }
	
	@Override
	public int getHealAmount()
    {
        return this.healAmount;
    }

	@Override
    public float getSaturationModifier()
    {
        return this.saturationModifier;
    }
	
	private ItemStack addFoodAndSaturation(World world, ItemStack itemstack, EntityPlayer player, int food, float saturation)
	{
        --itemstack.stackSize;
        
        FoodStats foodstats = player.getFoodStats();
        
		Field flfield = null;
		Field[] fields = foodstats.getClass().getDeclaredFields();

		for (Field f : fields)
		{
			if (f.getName().equals("foodLevel") || f.getName().equals("a"))
			{
				flfield = f;
				break;
			}
		}

        try 
        {
          flfield.setAccessible(true);
          flfield.setInt(foodstats, flfield.getInt(foodstats) + food);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
		Field slfield = null;

		for (Field f : fields)
		{
			if (f.getName().equals("foodSaturationLevel") || f.getName().equals("b"))
			{
				slfield = f;
				break;
			}
		}

        try 
        {
          slfield.setAccessible(true);
          slfield.setFloat(foodstats, slfield.getFloat(foodstats) + saturation);
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
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
