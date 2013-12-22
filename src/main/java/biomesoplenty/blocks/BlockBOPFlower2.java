package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower2 extends BlockFlower
{
	private static final String[] plants2 = new String[] {"hibiscus", "lilyofthevalley", "burningblossom", "lavender", "goldenrod", "bluebells", "minersdelight", "icyiris"};
	private Icon[] textures;

	protected BlockBOPFlower2(int blockID, Material material)
	{
		super(blockID, material);
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	public BlockBOPFlower2(int blockID)
	{
		this(blockID, Material.plants);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[plants2.length];

		for (int i = 0; i < plants2.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants2[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 2)
			return 9;
		else
			return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

		switch (meta)
		{
		default:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 2)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID)))
				{
					entity.setFire(1);
				}
			}
			else
			{
				entity.setFire(1);
			}
		}
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		
		ItemStack equippedItem = player.getCurrentEquippedItem();
		
		if (equippedItem != null)
		{
			if (equippedItem.itemID != Item.shears.itemID)
			{
				if (meta == 2)
				{
					player.setFire(5);
				}
			}
		}
		else
		{
			if (meta == 2)
			{
				player.setFire(5);
			}
		}
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if (meta == 2)
		{
			if (par5Random.nextInt(2) == 0)
			{
				par1World.spawnParticle("smoke", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
			if (par5Random.nextInt(4) == 0)
			{
				par1World.spawnParticle("flame", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < plants2.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Block.netherrack.blockID || id == Blocks.longGrass.get().blockID || id == Block.stone.blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 2) // Burning Blossom
			return id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;
		else if (metadata == 6) // Burning Blossom
			return id == Block.stone.blockID;
		else
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();
		//boolean sky = world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z);

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 2: // Burning Blossom
				return id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;
				
			case 6: // Miner's Delight
				return id == Block.stone.blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		//super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.checkFlowerChange(world, x, y, z);
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (world.getBlockId(x, y, z) != blockID)
		{
			if (meta == 2 || meta == 6)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			if (meta == 2 || meta == 6)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		//ItemStack itemstack = new ItemStack(Blocks.flowers.get(), 1, 10);

		if (world.getBlockMetadata(x, y, z) == 10)
			//if (!world.isRemote)
			//world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemstack));
			return true;
		return false;
	}
}
