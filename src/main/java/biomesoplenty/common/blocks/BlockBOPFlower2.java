package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.client.render.blocks.RenderUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower2 extends BlockBush
{
	private static final String[] plants2 = new String[] {"hibiscus", "lilyofthevalley", "burningblossom", "lavender", "goldenrod", "bluebells", "minersdelight", "icyiris"};
	private IIcon[] textures;

	public BlockBOPFlower2()
	{
		//TODO:	Material.plants
		super(Material.field_151585_k);

		//TODO: setTickRandomly()
		this.func_149675_a(true);
		float var4 = 0.2F;
		//TODO: setBlockBounds
		this.func_149676_a(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[plants2.length];

		for (int i = 0; i < plants2.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants2[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
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
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		default:
			//TODO: setBlockBounds
			this.func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 2)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				entity.setFire(1);
			}
		}
	}

	@Override
	//TODO:		harvestBlock()
	public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		//TODO: harvestBlock()
		super.func_149636_a(world, player, x, y, z, meta);

		ItemStack equippedItem = player.getCurrentEquippedItem();

		if (equippedItem != null)
		{
			if (equippedItem.getItem() != Items.shears)
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

	@Override
	//TODO: 	randomDisplayTick()
	public void func_149734_b(World world, int x, int y, int z, Random random)
	{
		//TODO: randomDisplayTick()
		super.func_149734_b(world, x, y, z, random);

		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 2)
		{
			if (random.nextInt(2) == 0)
			{
				world.spawnParticle("smoke", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
			if (random.nextInt(4) == 0)
			{
				world.spawnParticle("flame", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants2.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	public boolean isValidPosition(World world, int x, int y, int z)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch (metadata)
		{
		case 2: // Burning Blossom
			return block == Blocks.netherrack || block == BOPBlockHelper.get("overgrownNetherrack");

		case 6: // Miner's Delight
			return block == Blocks.stone;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean func_149707_d(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z);
	} 

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);

		return meta;
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 15;
	}

	@Override
	//TODO:		   canBlockStay()
	public boolean func_149718_j(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 2 || meta == 6)
			return this.isValidPosition(world, x, y, z);
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.isValidPosition(world, x, y, z);
	}
}
