package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class ItemBOPFood extends ItemFood
{
	private static final String[] foodTypes = new String[] {"berries", "shroompowder", "wildcarrots", "sunflowerseeds", "saladfruit", "saladveggie", "saladshroom", "earth", "persimmon", "filledhoneycomb", "ambrosia", "beetroot", "beetrootsoup"};
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
		if (itemstack.getItemDamage() == 1)
		{
			if (player.canEat(true))
			{
				player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
			}
		}
		else if (itemstack.getItemDamage() == 10)
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
		--itemstack.stackSize;
		switch (itemstack.getItemDamage())
		{
			case 0:
				player.getFoodStats().addStats(1, 0.1F);
				break;
				
			case 1:
				player.getFoodStats().addStats(1, 0.1F);
				break;
				
			case 2:
				player.getFoodStats().addStats(3, 0.5F);
				break;
				
			case 3:
				player.getFoodStats().addStats(2, 0.5F);
				break;
				
			case 4:
				player.getFoodStats().addStats(6, 0.6F);
				break;
				
			case 5:
				player.getFoodStats().addStats(6, 0.6F);
				break;
				
			case 6:
				player.getFoodStats().addStats(6, 0.6F);
				break;
				
			case 8:
				player.getFoodStats().addStats(5, 0.2F);
				break;
				
			case 9:
				player.getFoodStats().addStats(3, 0.4F);
				break;
				
			case 10:
				player.getFoodStats().addStats(6, 0.8F);
				break;
				
			case 11:
				player.getFoodStats().addStats(0, 0.4F);
				break;
				
			case 12:
				player.getFoodStats().addStats(4, 0.2F);
				break;
				
			default:
				player.getFoodStats().addStats(0, 0.0F);
				break;
		}
		
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemstack, world, player);
        
        switch (itemstack.getItemDamage())
        {
        	case 4:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
                    player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
        	case 5:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
                    player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
        	case 6:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
                    player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
        		
        	case 10:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle)))
                    player.dropPlayerItem(new ItemStack(Item.glassBottle.itemID, 1, 0));
        		
        	case 12:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Item.bowlEmpty)))
                    player.dropPlayerItem(new ItemStack(Item.bowlEmpty.itemID, 1, 0));
        		break;
        }
        
        return itemstack;
    }
	
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 10)
    	{
    		return EnumAction.drink;
    	}
    	
    	return EnumAction.eat;
    }
    
	@Override
    public int getItemStackLimit(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 4)
    	{
    		return 1;
    	}
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 5)
    	{
    		return 1;
    	}
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 6)
    	{
    		return 1;
    	}
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 10)
    	{
    		return 1;
    	}
    	
        return 64;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 0)
    	{
    		return 8;
    	}
    	
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 3)
    	{
    		return 12;
    	}
    	
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 9)
    	{
    		return 16;
    	}
    	
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 10)
    	{
    		return 64;
    	}
    	
        return 32;
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
            		
            	case 10:
            		player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 5000, 4));
            		player.addPotionEffect(new PotionEffect(Potion.field_76443_y.id, 100, 1));
            		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 500, 2));
            		player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 400, 2));
            		player.addPotionEffect(new PotionEffect(Potion.resistance.id, 600, 1));
            		break;
            }
        }
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
