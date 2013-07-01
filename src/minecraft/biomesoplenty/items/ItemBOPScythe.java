package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPItems;

public class ItemBOPScythe extends Item
{
	public int textureID = 0;
	
    public int damageVsEntity;
	
    protected EnumToolMaterial toolMaterial;

	public ItemBOPScythe(int id, int damage, EnumToolMaterial enumtoolmat, int texture)
	{
		super(id);
        this.toolMaterial = enumtoolmat;
        this.maxStackSize = 1;
        this.setMaxDamage(enumtoolmat.getMaxUses());
		textureID = texture;
        this.damageVsEntity = damage + enumtoolmat.getDamageVsEntity();
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
    @Override
	public boolean onBlockDestroyed(ItemStack itemstack, World world, int meta, int x, int y, int z, EntityLiving entity)
    {
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        int radius = 1;
        int height = 3;
        
        if (toolMaterial == EnumToolMaterial.IRON || toolMaterial == EnumToolMaterial.GOLD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 2;
        	}
        	
        	radius = 2;
        }
        else if (toolMaterial == EnumToolMaterial.EMERALD)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 3;
        	}
        	
        	radius = 3;
        }
        else if (toolMaterial == BOPItems.EnumToolMaterialAmethyst)
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 4;
        	}
        	
        	radius = 4;
        }
        else
        {
        	if (block != null)
        	{
        		if (block.isLeaves(world, x, y, z))
        			height = 0;
        	}
        }
    	
    	if (block != null)
    	{
    		if (block.isLeaves(world, x, y, z))
    		{
        		if (height > 0)
        		{
        			trimLeaves(world, x, y, z, height, radius);

        			itemstack.damageItem(1, entity);

        			return true;
        		}
    		}
    		else
    		{
    			trimCutCorner(world, x, y, z, height, radius);

    			if (world.rand.nextInt(3) == 0)
    			{
    				trim(world, x, y, z, height, radius - 1);

    				itemstack.damageItem(1, entity);

    				return true;
    			}
    		}
    	}
    	
        return false;
    }
    
    @Override
    public int getDamageVsEntity(Entity par1Entity)
    {
        return this.damageVsEntity;
    }
    
    public void trim(World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				Block block = Block.blocksList[world.getBlockId(x + aX, y + aY, z + aZ)];
    				int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    				if (block != null)
    				{
    					if (block.blockID == Blocks.foliage.get().blockID && (meta == 1 || meta == 2 || meta == 6))
    					{
    						if (meta == 1)
    						{
    							block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    							world.setBlockToAir(x + aX, y + aY, z + aZ);
    						}
    						else if (meta == 2)
    						{
    							block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    							world.setBlock(x + aX, y + aY, z + aZ, Blocks.foliage.get().blockID, 1, 2);
    						}
    						else if (meta == 6)
    						{
    							block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    							world.setBlockToAir(x + aX, y + aY, z + aZ);
    						}
    					}
    					else if (block.blockID == Block.tallGrass.blockID)
    					{
    						block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    						world.setBlock(x + aX, y + aY, z + aZ, Blocks.foliage.get().blockID, 2, 2);
    					}
    					else if (block.blockID != Block.waterlily.blockID && block instanceof BlockFlower)
    					{
    						System.out.println(block.getBlockDropped(world, x + aX, y + aY, z + aZ, meta, 1));

    						block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    						world.setBlockToAir(x + aX, y + aY, z + aZ);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimLeaves(World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = -radius; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				Block block = Block.blocksList[world.getBlockId(x + aX, y + aY, z + aZ)];
    				int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    				if (block != null)
    				{
    					if (block.isLeaves(world, x + aX, y + aY, z + aZ))
    					{
    						block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    						world.setBlockToAir(x + aX, y + aY, z + aZ);
    					}
    				}
    			}
    		}
    	}
    }
    
    public void trimCutCorner(World world, int x, int y, int z, int height, int radius)
    {
    	for (int aX = -radius; aX <= radius; aX++)
    	{
    		for (int aY = 0; aY <= radius; aY++)
    		{
    			for (int aZ = -radius; aZ <= radius; aZ++)
    			{
    				if ((aX + aZ < radius * 2) && (-aX + aZ < radius * 2) && (aX + -aZ < radius * 2) && (-aX + -aZ < radius * 2))
    				{
    					Block block = Block.blocksList[world.getBlockId(x + aX, y + aY, z + aZ)];
    					int meta = world.getBlockMetadata(x + aX, y + aY, z + aZ);

    					if (block != null)
    					{
    						if (block.blockID == Blocks.foliage.get().blockID && (meta == 1 || meta == 2 || meta == 6))
    						{
    							if (meta == 1)
    							{
    								block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    								world.setBlockToAir(x + aX, y + aY, z + aZ);
    							}
    							else if (meta == 2)
    							{
    								block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    								world.setBlock(x + aX, y + aY, z + aZ, Blocks.foliage.get().blockID, 1, 2);
    							}
    							else if (meta == 6)
    							{
    								block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    								world.setBlockToAir(x + aX, y + aY, z + aZ);
    							}
    						}
    						else if (block.blockID == Block.tallGrass.blockID)
    						{
    							block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    							world.setBlock(x + aX, y + aY, z + aZ, Blocks.foliage.get().blockID, 2, 2);
    						}
    						else if (block.blockID != Block.waterlily.blockID && block instanceof BlockFlower)
    						{
    							block.dropBlockAsItem(world, x + aX, y + aY, z + aZ, meta, 0);
    							world.setBlockToAir(x + aX, y + aY, z + aZ);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		float strength = 0;

		if (Blocks.shearBlockIds.get(par2Block.blockID) != null)
		{
			strength = Float.parseFloat(Blocks.shearBlockIds.get(par2Block.blockID).toString());
		}
		else
		{
			strength = super.getStrVsBlock(par1ItemStack, par2Block);
		}

		return strength;
	}

	@Override
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (textureID == 0 && par2ItemStack.itemID == Block.wood.blockID)
		{
			return true;
		}
		if (textureID == 1 && par2ItemStack.itemID == Block.cobblestone.blockID)
		{
			return true;
		}
		if (textureID == 2 && par2ItemStack.itemID == Item.ingotIron.itemID)
		{
			return true;
		}
		if (textureID == 3 && par2ItemStack.itemID == Item.ingotGold.itemID)
		{
			return true;
		}
		if (textureID == 4 && par2ItemStack.itemID == Item.diamond.itemID)
		{
			return true;
		}
		if (textureID == 6 && par2ItemStack.itemID == Items.miscItems.get().itemID && par2ItemStack.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
    public boolean isFull3D()
    {
        return true;
    }

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (textureID == 0)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:woodscythe"); 
		}
		else if (textureID == 1)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:stonescythe"); 
		}
		else if (textureID == 2)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:ironscythe"); 
		}
		else if (textureID == 3)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:goldscythe"); 
		}
		else if (textureID == 4)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:diamondscythe"); 
		}
		else if (textureID == 5)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudscythe"); 
		}
		else if (textureID == 6)
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystscythe"); 
		}
		else 
		{ 
			itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); 
		}
	}
}
