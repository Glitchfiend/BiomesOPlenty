package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
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
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower2 extends BOPBlockWorldDecor
{
	private static final String[] plants2 = new String[] {"hibiscus", "lilyofthevalley", "burningblossom", "lavender", "goldenrod", "bluebells", "minersdelight", "icyiris", "rose"};
	private IIcon[] textures;

	public BlockBOPFlower2()
	{
		super(Material.plants);

		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		float var4 = 0.2F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants2.length];

		for (int i = 0; i < plants2.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants2[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

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
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

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
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants2.length; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	@Override
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);
		
		switch (metadata)
		{
		case 2: // Burning Blossom
			return block == Blocks.netherrack || block == BOPCBlocks.overgrownNetherrack;

		case 6: // Miner's Delight
			return block == Blocks.stone;
			
		case 8: // Rose
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack || block == BOPCBlocks.originGrass || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack || block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
		}
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
}
