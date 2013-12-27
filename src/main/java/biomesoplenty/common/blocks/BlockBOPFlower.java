package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.client.render.blocks.RenderUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower extends BlockBush
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "rainbowflower", "bromeliad", "sunflowerbottom", "sunflowertop", "dandelion"};
	private IIcon[] textures;

	private static final int SUNFLOWERTOP = 14;
	private static final int SUNFLOWERBOTTOM = 13;

	public BlockBOPFlower()
	{
		//TODO:	Material.plants
		super(Material.field_151585_k);

		//TODO: this.setHardness
		this.func_149711_c(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
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
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
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
		return RenderUtils.foliageModel;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3)
			return 9;
		else if (meta == 11)
			return 5;
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
		case 0:
			//TODO: setBlockBounds
			this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
			break;

		case 5:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
			break;

		case 6:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

		case 9:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
			break;

		case 10:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, -0.97F, 0.3F, 0.7F, -0.7F, 0.7F);
			//		//TODO: setBlockBounds
			this.func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			break;

		case 11:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		case 15:
			//TODO: setBlockBounds
			this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

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
		int meta = world.getBlockMetadata(x, y, z);

		if (!world.isRemote && meta == 2 && entity instanceof EntityLivingBase) 
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings)))
				{
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
				}
			}
			else
			{
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
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
			if (random.nextInt(4) != 0)
			{
				world.spawnParticle("townaura", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
			if (random.nextInt(4) == 0)
			{
				world.spawnParticle("smoke", x + random.nextFloat(), y, z + random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i)
		{
			if (i != 14)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	public boolean isValidPosition(World world, int x, int y, int z)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch (metadata)
		{
		case 6: // Tulip
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");

		case 10: // Lily Flower
			return block == Blocks.waterlily;

		case 11: // Rainbow Flower
			return block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("holyDirt") || block == Blocks.grass || block == Blocks.dirt;

		case 12: // Aloe
			return block == BOPBlockHelper.get("hardDirt") || block == BOPBlockHelper.get("redRock") || block == Blocks.sand;

		case 14: // Sunflower Top
			return block == this;

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
	//TODO:		   canBlockStay()
	public boolean func_149718_j(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (meta == 11)
			return this.isValidPosition(world, x, y, z);
		else
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && isValidPosition(world, x, y, z);
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		super.func_149695_a(world, x, y, z, neighborBlock);

		//TODO:														getBlock()
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERTOP && world.func_147439_a(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) != SUNFLOWERBOTTOM) 
		{
			//TODO: setBlockToAir()
			world.func_147468_f(x, y, z);
		}
		//TODO:														getBlock()
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERBOTTOM && world.func_147439_a(x, y + 1, z) != this) 
		{
			//TODO: setBlockToAir()
			world.func_147468_f(x, y, z);
		}
	}

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == SUNFLOWERTOP) {
			meta = 13;
		}
		return meta;
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		if (meta == 14)
			return 13 & 15;
		else
			return meta & 15;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 13)
			return 0;
		else
			return 1;
	}

	@Override
	//TODO:		harvestBlock()
	public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.func_149636_a(world, player, x, y, z, meta);

		ItemStack equippedItem = player.getCurrentEquippedItem();

		if (equippedItem != null)
		{
			if (equippedItem.getItem() != Items.shears)
			{
				if (meta == 2)
				{
					if (!world.isRemote) 
					{
						player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
					}
				}
			}
		}
		else
		{
			if (meta == 2)
			{
				if (!world.isRemote) 
				{
					player.addPotionEffect(new PotionEffect(Potion.wither.id, 300));
				}
			}
		}
	}

	@Override
	//TODO: 	   isBlockReplaceable
	public boolean func_149742_c(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;

		return false;
	}
}
