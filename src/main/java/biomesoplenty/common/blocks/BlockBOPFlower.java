package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
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
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower extends BOPBlockWorldDecor
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "rainbowflower", "bromeliad", "sunflowerbottom", "sunflowertop", "dandelion"};
	private IIcon[] textures;

	private static final int SUNFLOWERTOP = 14;
	private static final int SUNFLOWERBOTTOM = 13;

	public BlockBOPFlower()
	{
		//TODO:	Material.plants
		super(Material.plants);

		//TODO: this.setHardness
		this.setHardness(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);

		float var4 = 0.2F;
		//TODO: setBlockBounds
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);

		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO		getRenderType()
	public int getRenderType()
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			//TODO: setBlockBounds
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
			break;

		case 5:
			//TODO: setBlockBounds
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
			break;

		case 6:
			//TODO: setBlockBounds
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

		case 9:
			//TODO: setBlockBounds
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
			break;

		case 10:
			//TODO: setBlockBounds
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			break;

		case 11:
			//TODO: setBlockBounds
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		case 15:
			//TODO: setBlockBounds
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

		default:
			//TODO: setBlockBounds
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
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
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		//TODO: randomDisplayTick()
		super.randomDisplayTick(world, x, y, z, random);

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
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i)
		{
			if (i != 14)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);
		
		switch (metadata)
		{
		case 6: // Tulip
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("grass") || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");

		case 10: // Lily Flower
			return block == Blocks.waterlily;

		case 11: // Rainbow Flower
			return block == BOPBlockHelper.get("grass") || block == BOPBlockHelper.get("holyDirt") || block == Blocks.grass || block == Blocks.dirt;

		case 12: // Bromeliad
			return block == BOPBlockHelper.get("hardDirt") || block == Blocks.hardened_clay || block == Blocks.sand;

		case 14: // Sunflower Top
			return block == this;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}
	@Override
	//TODO:		onNeighborBlockChange()
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		//TODO:	onNeighborBlockChange()
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);

		//TODO:														getBlock()
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERTOP && world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) != SUNFLOWERBOTTOM) 
		{
			//TODO: setBlockToAir()
			world.setBlockToAir(x, y, z);
		}
		//TODO:														getBlock()
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERBOTTOM && world.getBlock(x, y + 1, z) != this) 
		{
			//TODO: setBlockToAir()
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	//TODO:	   getDamageValue()
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == SUNFLOWERTOP) {
			meta = 13;
		}
		return meta;
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		if (meta == 14) return 13;
		else return meta;
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
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;

		return false;
	}
}
