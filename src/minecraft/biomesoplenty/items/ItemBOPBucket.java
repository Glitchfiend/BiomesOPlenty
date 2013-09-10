package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Fluids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPBucket extends Item
{
	private int isFull;

	private static final String[] bucketTypes = new String[] {"amethyst_empty", "liquid_poison", "amethyst_spring_water"};

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemBOPBucket(int i)
	{
		super(i);
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		float f = 1.0F;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		isFull = getLiquidIDFromMeta(itemstack.getItemDamage());
		boolean flag = isFull == 0;

		if (itemstack.getItemDamage() == 0)
		{
			MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
			
	        if (pos == null)
	        {
	        	return itemstack;
	        }
	        else
	        {
	        	int blockID = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);
	        	int meta = world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ);

	        	if ((blockID == Fluids.springWater.get().blockID) && meta == 0)
	        	{
	        		if (player.capabilities.isCreativeMode)
	        		{
	        			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);
	        			
	        			return itemstack;
	        		}

	        		if (--itemstack.stackSize <= 0)
	        		{
	        			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

	        			return new ItemStack(Fluids.bopBucket.get(), 1, 2);
	        		}

	        		if (!player.inventory.addItemStackToInventory(new ItemStack(Fluids.bopBucket.get(), 1, 2)))
	        		{
	        			world.setBlock(pos.blockX, pos.blockY, pos.blockZ, 0);

	        			player.dropPlayerItem(new ItemStack(Fluids.bopBucket.get(), 1, 2));
	        		}

	        		return itemstack;
	        	}
	        	else
	        	{
	        		return itemstack;
	        	}
	        }
		}
		else
		{
			MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, flag);
			
	        if (pos == null)
	        {
	        	return itemstack;
	        }
	        else if (pos.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = pos.blockX;
				int j = pos.blockY;
				int k = pos.blockZ;

				if (!world.canMineBlock(player, i, j, k))
					return itemstack;

				if (isFull < 0)
					return new ItemStack(Item.bucketEmpty);

				if (pos.sideHit == 0)
				{
					--j;
				}

				if (pos.sideHit == 1)
				{
					++j;
				}

				if (pos.sideHit == 2)
				{
					--k;
				}

				if (pos.sideHit == 3)
				{
					++k;
				}

				if (pos.sideHit == 4)
				{
					--i;
				}

				if (pos.sideHit == 5)
				{
					++i;
				}

				if (!player.canPlayerEdit(i, j, k, pos.sideHit, itemstack))
					return itemstack;

				if (this.tryPlaceContainedLiquid(world, d0, d1, d2, i, j, k) && !player.capabilities.isCreativeMode)
				{
					if (itemstack.getItemDamage() != 2)
						return new ItemStack(Item.bucketEmpty);
					else
						return new ItemStack(Fluids.bopBucket.get(), 1, 0);
				}
			}
		}

		return itemstack;
	}

	/**
	 * Attempts to place the liquid contained inside the bucket.
	 */
	public boolean tryPlaceContainedLiquid(World par1World, double par2, double par4, double par6, int par8, int par9, int par10)
	{
		if (isFull <= 0)
			return false;
		else if (!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid())
			return false;
		else
		{
			if (par1World.provider.isHellWorld && isFull == Fluids.springWater.get().blockID)
			{
				par1World.playSoundEffect(par2 + 0.5D, par4 + 0.5D, par6 + 0.5D, "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

				for (int l = 0; l < 8; ++l)
				{
					par1World.spawnParticle("largesmoke", par8 + Math.random(), par9 + Math.random(), par10 + Math.random(), 0.0D, 0.0D, 0.0D);
				}
			}
			else
			{
				par1World.setBlock(par8, par9, par10, isFull, 0, 3);
			}

			return true;
		}
	}
	
	@Override
    public boolean hasContainerItem()
    {
        return true;
    }
	
	@Override
	public ItemStack getContainerItemStack(ItemStack itemstack) 
	{
		return itemstack.getItemDamage() == 2 ? new ItemStack(Fluids.bopBucket.get(), 1, 0) : new ItemStack(Item.bucketEmpty, 1);
	}

	private static int getLiquidIDFromMeta(int meta)
	{
		switch (meta)
		{
			case 1:
				return Fluids.liquidPoison.get().blockID;

			case 2:
				return Fluids.springWater.get().blockID;

			default:
				return Fluids.liquidPoison.get().blockID;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < bucketTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= bucketTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + bucketTypes[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[bucketTypes.length];

		for (int i = 0; i < bucketTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + "bucket_" + bucketTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return textures[meta];
	}
}
