package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFlower extends BlockFlower
{
	private static final String[] plants = new String[] {"clover", "swampflower", "deadbloom", "glowflower", "hydrangea", "cosmos", "daffodil", "wildflower", "violet", "anemone", "lilyflower", "rainbowflower", "bromeliad", "sunflowerbottom", "sunflowertop", "dandelion"};
	private IIcon[] textures;

	private static final int SUNFLOWERTOP = 14;
	private static final int SUNFLOWERBOTTOM = 13;

	protected BlockBOPFlower()
	{
		super(material);
		
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
		this.func_149676_a0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
			break;

		case 5:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, 0.0F, 0.3F, 0.7F, 0.8F, 0.7F);
			break;

		case 6:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

		case 9:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
			break;

		case 10:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, -0.97F, 0.3F, 0.7F, -0.7F, 0.7F);
			//		//TODO: setBlockBounds
		this.func_149676_a0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			break;

		case 11:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		case 15:
					//TODO: setBlockBounds
		this.func_149676_a0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
			break;

		default:
					//TODO: setBlockBounds
		this.func_149676_a0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
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

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Item.bootsLeather.itemID) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].itemID == Item.legsLeather.itemID)))
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
		super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
		
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		
		if (meta == 2)
		{
			if (par5Random.nextInt(4) != 0)
			{
				par1World.spawnParticle("townaura", par2 + par5Random.nextFloat(), par3 + par5Random.nextFloat(), par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
			}
			if (par5Random.nextInt(4) == 0)
			{
				par1World.spawnParticle("smoke", par2 + par5Random.nextFloat(), (par3), par4 + par5Random.nextFloat(), 0.0D, 0.0D, 0.0D);
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

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Block.sand.blockID || id == Blocks.hardDirt.get().blockID || id == Blocks.redRock.get().blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 6) //Tulip
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.holyGrass.get().blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
		if (metadata == 10) //Lily Flower
			return id == Block.waterlily.blockID;
		if (metadata == 11) //Rainbow Flower
			return id == Blocks.holyGrass.get().blockID || id == Blocks.holyDirt.get().blockID || id == Block.grass.blockID || id == Block.dirt.blockID;
		if (metadata == 12) //Aloe
			return id == Blocks.hardDirt.get().blockID || id == Blocks.redRock.get().blockID || id == Block.sand.blockID;
		if (metadata == 14) //Sunflower Top
			return id == blockID;
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
			case 6: // Tulip
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.holyGrass.get().blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;

			case 10: // Lily Flower
				return id == Block.waterlily.blockID;

			case 11: // Rainbow Flower
				return id == Blocks.holyGrass.get().blockID || id == Blocks.holyDirt.get().blockID || id == Block.grass.blockID || id == Block.dirt.blockID;

			case 12: // Aloe
				return id == Blocks.hardDirt.get().blockID || id == Blocks.redRock.get().blockID || id == Block.sand.blockID;

			case 14: // Sunflower Top
				return id == blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		super.func_149695_a(world, x, y, z, neighborBlock);
		
		this.checkFlowerChange(world, x, y, z);
		
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERTOP && world.getBlockId(x, y - 1, z) == blockID && world.getBlockMetadata(x, y - 1, z) != SUNFLOWERBOTTOM) 
		{
			world.setBlockToAir(x, y, z);
		}
		if (world.getBlockMetadata(x, y, z) == SUNFLOWERBOTTOM && world.getBlockId(x, y + 1, z) != blockID) 
		{
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
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
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (world.getBlockId(x, y, z) != blockID)
		{
			if (meta == 11)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		else
		{
			if (meta == 11)
				return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
			else
				return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
		}
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
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;
		
		return false;
	}
}
