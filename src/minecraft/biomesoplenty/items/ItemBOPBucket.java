package biomesoplenty.items;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Liquids;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBOPBucket extends Item
{
	private int isFull;

	private static final String[] bucketTypes = new String[] {"spring_water", "liquid_poison"};

	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemBOPBucket(int i)
	{
		super(i);
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		float f = 1.0F;
		double d0 = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * f;
		double d1 = par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * f + 1.62D - par3EntityPlayer.yOffset;
		double d2 = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * f;
		isFull = getLiquidIDFromMeta(par1ItemStack.getItemDamage());
		boolean flag = isFull == 0;
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, flag);

		if (movingobjectposition == null)
			return par1ItemStack;
		else
		{
			FillBucketEvent event = new FillBucketEvent(par3EntityPlayer, par1ItemStack, par2World, movingobjectposition);
			if (MinecraftForge.EVENT_BUS.post(event))
				return par1ItemStack;

			if (event.getResult() == Event.Result.ALLOW)
			{
				if (par3EntityPlayer.capabilities.isCreativeMode)
					return par1ItemStack;

				if (--par1ItemStack.stackSize <= 0)
					return event.result;

				if (!par3EntityPlayer.inventory.addItemStackToInventory(event.result))
				{
					par3EntityPlayer.dropPlayerItem(event.result);
				}

				return par1ItemStack;
			}

			if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
			{
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!par2World.canMineBlock(par3EntityPlayer, i, j, k))
					return par1ItemStack;

				if (isFull == 0)
				{
					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
						return par1ItemStack;

					if (par2World.getBlockMaterial(i, j, k) == Material.water && (par2World.getBlockMetadata(i, j, k) == 0))
					{
						par2World.setBlockToAir(i, j, k);

						if (par3EntityPlayer.capabilities.isCreativeMode)
							return par1ItemStack;

						if (--par1ItemStack.stackSize <= 0)
							return new ItemStack(Item.bucketWater);

						if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.bucketWater)))
						{
							par3EntityPlayer.dropPlayerItem(new ItemStack(Item.bucketWater.itemID, 1, 0));
						}

						return par1ItemStack;
					}

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
						return par1ItemStack;
				}
				else
				{
					if (isFull < 0)
						return new ItemStack(Item.bucketEmpty);

					if (movingobjectposition.sideHit == 0)
					{
						--j;
					}

					if (movingobjectposition.sideHit == 1)
					{
						++j;
					}

					if (movingobjectposition.sideHit == 2)
					{
						--k;
					}

					if (movingobjectposition.sideHit == 3)
					{
						++k;
					}

					if (movingobjectposition.sideHit == 4)
					{
						--i;
					}

					if (movingobjectposition.sideHit == 5)
					{
						++i;
					}

					if (!par3EntityPlayer.canPlayerEdit(i, j, k, movingobjectposition.sideHit, par1ItemStack))
						return par1ItemStack;

					if (this.tryPlaceContainedLiquid(par2World, d0, d1, d2, i, j, k) && !par3EntityPlayer.capabilities.isCreativeMode)
						return new ItemStack(Item.bucketEmpty);
				}
			}

			return par1ItemStack;
		}
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
			if (par1World.provider.isHellWorld && isFull == Liquids.springWater.get().blockID)
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

	private static int getLiquidIDFromMeta(int meta)
	{
		switch (meta)
		{
		case 0:
			return Liquids.springWater.get().blockID;

		case 1:
			return Liquids.liquidPoison.get().blockID;

		default:
			return Liquids.springWater.get().blockID;
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
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + "bucket_" + bucketTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return textures[meta];
	}
}
