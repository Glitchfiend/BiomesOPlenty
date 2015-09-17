package biomesoplenty.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.blocks.BlockBOPPlant;
import biomesoplenty.api.content.BOPCBlocks;

public class ItemBOPFood extends ItemFood
{
	private static final String[] foodTypes = new String[] {"berries", "shroompowder", "wildcarrots", "peach", "saladfruit", "saladveggie", "saladshroom", "earth", "persimmon", "filledhoneycomb", "ambrosia", "turnip", "pear", "ricebowl"};
	private static final int[] foodHunger = new int[] {1, 1, 3, 5, 6, 6, 6, 0, 5, 3, 6, 3, 5, 2};
	private static final float[] foodSaturation = new float[] {0.1F, 0.1F, 0.5F, 0.2F, 0.6F, 0.6F, 0.6F, 0.0F, 0.2F, 0.4F, 0.8F, 0.4F, 0.3F, 0.1F};
	private IIcon[] textures;
	
	public ItemBOPFood(int healAmount)
	{
		super(healAmount, 0, false);
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
	public int func_150905_g(ItemStack itemstack)
	{
		return foodHunger[itemstack.getItemDamage()];
	}
	
	@Override
	public float func_150906_h(ItemStack itemstack)
	{
		return foodSaturation[itemstack.getItemDamage()];
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
	
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitVecX, float hitVecY, float hitVecZ)
    {
    	if (stack.getItemDamage() != 2)
    	{
    		return false;
    	}
        if (side != 1)
        {
            return false;
        }
        else if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack))
        {
            if (((BlockBOPPlant)BOPCBlocks.plants).isValidPosition(world, x, y + 1, z, 11) && world.isAirBlock(x, y + 1, z))
            {
                world.setBlock(x, y + 1, z, BOPCBlocks.plants);
                world.setBlockMetadataWithNotify(x, y + 1, z, 11, 2);
                --stack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer player)
    {
		--itemstack.stackSize;
		
		player.getFoodStats().func_151686_a(this, itemstack);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemstack, world, player);
        
        switch (itemstack.getItemDamage())
        {
        	case 4:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl)))
                    player.dropPlayerItemWithRandomChoice(new ItemStack(Items.bowl, 1, 0), false);
        		break;
        		
        	case 5:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl)))
                    player.dropPlayerItemWithRandomChoice(new ItemStack(Items.bowl, 1, 0), false);
        		break;
        		
        	case 6:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl)))
                    player.dropPlayerItemWithRandomChoice(new ItemStack(Items.bowl, 1, 0), false);
        		break;
        		
        	case 10:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
                    player.dropPlayerItemWithRandomChoice(new ItemStack(Items.glass_bottle, 1, 0), false);
        		break;
        		
        	case 13:
        		if (!player.inventory.addItemStackToInventory(new ItemStack(Items.bowl)))
                    player.dropPlayerItemWithRandomChoice(new ItemStack(Items.bowl, 1, 0), false);
        		break;
        }
        
        return itemstack;
    }
	
    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 10)
    	{
    		return EnumAction.drink;
    	}
    	
    	return EnumAction.eat;
    }
    
	@Override
    public int getItemStackLimit(ItemStack itemStack)
    {
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 4)
    	{
    		return 1;
    	}
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 5)
    	{
    		return 1;
    	}
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 6)
    	{
    		return 1;
    	}
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 10)
    	{
    		return 1;
    	}
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 13)
    	{
    		return 1;
    	}
    	
        return 64;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 0)
    	{             
    		return 8; 
    	}
    	              
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 9)
    	{             
    		return 16;
    	}             
    	              
    	if (itemStack.getItem() == this && itemStack.getItemDamage() == 10)
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
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
    {
		for (int i = 0; i < foodTypes.length; ++i) 
		{
			if (i != 7)
			{
				list.add(new ItemStack(item, 1, i));
			}
		}
    }

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[foodTypes.length];

		for (int i = 0; i < foodTypes.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+ foodTypes[i]);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= foodTypes.length) 
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + foodTypes[meta];
	}
}
