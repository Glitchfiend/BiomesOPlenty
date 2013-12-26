package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.FakePlayer;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFoliage extends BlockTallGrass implements IShearable
{
	private static final String[] foliageTypes = new String[] {"algae", "shortgrass", "mediumgrass", "highgrassbottom", "bush", "sprout", "highgrasstop", "poisonivy", "berrybush", "shrub", "wheatgrass", "dampgrass", "koru", "cloverpatch"};

	private IIcon[] textures;
	public IIcon shrubBranch;
	public IIcon berryBushBerry;

	private static final int ALGAE = 0;

	public BlockBOPFoliage()
	{
		float f = 0.4F;
		
		//TODO:		setBurnProperties() getIdFromBlock()
		Blocks.fire.func_149842_a(func_149682_b(this), 60, 100);
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		//TODO: this.setHardness
		this.func_149711_c(0.0F);
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);

		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[foliageTypes.length];

		for (int i = 0; i < textures.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+foliageTypes[i]);
		}
		
		shrubBranch = iconRegister.registerIcon("biomesoplenty:" + "shrub_branch");
		berryBushBerry = iconRegister.registerIcon("biomesoplenty:" + "berrybush_berry");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < foliageTypes.length; ++i)
		{
			if (i != GRASSTOP) 
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		switch (meta)
		{
		case 1:
		case 2:
		case 3:
		case 10:
		case 11:
			if (world.rand.nextInt(8) != 0)
				return ret;

			ItemStack item = ForgeHooks.getGrassSeed(world);
			if (item != null) {
				ret.add(item);
			}
			break;

		case 5:
			if (world.rand.nextInt(50) != 0)
				return ret;

			if (world.rand.nextInt(2) == 0) {
				ret.add(new ItemStack(Item.carrot,1));
			} else {
				ret.add(new ItemStack(Item.potato,1));
			}
			break;
			
		case 12:
			if (world.rand.nextInt(32) != 0)
				return ret;

			if (world.rand.nextInt(2) == 0) {
				ret.add(new ItemStack(Items.turnipseeds.get(),1));
			}
			break;
			
		case 8:
		    ret.add(new ItemStack(Items.food.get(), 1, 0));
		    break;
		}

		return ret;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case ALGAE: // Dead Grass
			return id == Block.waterStill.blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.tilledField.blockID || id == Blocks.longGrass.get().blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int blockID, int metadata)
	{
		if (metadata == GRASSTOP)
			return blockID == this.blockID;
		else if (metadata == ALGAE)
			return blockID == Block.waterStill.blockID;
		else
			return blockID == Block.grass.blockID || blockID == Block.dirt.blockID || blockID == Block.tilledField.blockID || blockID == Blocks.longGrass.get().blockID || blockID == Blocks.overgrownNetherrack.get().blockID;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlockId(x, y, z) != blockID)
		{
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z))
					&& this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		}
		
			return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z))
					&& this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
	    int metadata = world.getBlockMetadata(x, y, z);
	    
	    if (world.getBlockMetadata(x, y, z) == GRASSBOTTOM) 
	    {
	        if (world.getBlockId(x, y + 1, z) != blockID)
	        {
	            world.setBlock(x, y, z, Block.tallGrass.blockID, 1, 2);
	        }
	        else if (!canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z)))
	        {
	            this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y + 1, z), 0);
	            world.setBlockToAir(x, y + 1, z);
	        }
	    }

		super.func_149695_a(world, x, y, z, neighborBlock);
		this.checkFlowerChange(world, x, y, z);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);

		if (!world.isRemote && meta == 7) 
		{
			if (entity instanceof EntityLivingBase)
			{
				if (entity instanceof EntityPlayer)
				{
					InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

					if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings)))
					{
						((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
					}
				}
				else
				{
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 100));
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		double var1 = 0.5D;
		double var3 = 1.0D;
		
		return ColorizerGrass.getGrassColor(var1, var3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int par1)
	{
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return RenderUtils.foliageModel;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
        if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 9)
        {
                return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeFoliageColor();
        }
        
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == GRASSTOP) {
			meta = GRASSBOTTOM;
		}
		return meta;
	}

	@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		return -1;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case ALGAE:
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.015625D, z + 1.0D);

		case 1: // Short Grass
		return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.25D, z + 0.9D);

		case 2: // Medium Grass
		return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.6D, z + 0.9D);
		
		case 13: //Clover Patch
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.015625D, z + 1.0D);

		default:
			return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.8D, z + 0.9D);
		}
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		float minX;
		float minY;
		float minZ;
		float maxX;
		float maxY;
		float maxZ;

		switch (meta)
		{
		case ALGAE:
			minX = minY = minZ = 0F;
			maxX = maxZ = 1.0F;
			maxY = 0.015625F;
			break;

		case 1: // Short grass
			minX = minZ = 0.1F;
			minY = 0.0F;
			maxX = maxZ = 0.9F;
			maxY = 0.25F;
			break;

		case 2: // Medium grass
			minX = minZ = 0.1F;
			minY = 0.0F;
			maxX = maxZ = 0.9F;
			maxY = 0.6F;
			break;
			
		case 13:
			minX = minY = minZ = 0F;
			maxX = maxZ = 1.0F;
			maxY = 0.015625F;
			break;

		default:
			minX = minZ = 0.1F;
			minY = 0.0F;
			maxX = maxZ = 0.9F;
			maxY = 0.8F;
			break;
		}

		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	//TODO:		harvestBlock()
	public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 8)
		{
			world.setBlock(x, y, z, blockID, 4, 3);
			EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(Items.food.get(), 1, 0));
			if (!world.isRemote) {
				world.spawnEntityInWorld(entityitem);
				if (!(player instanceof FakePlayer))
                    entityitem.onCollideWithPlayer(player);
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (world.getBlockMetadata(x, y, z) == GRASSTOP) 
		{
			ret.add(new ItemStack(Block.tallGrass, 1, 1));
		} 
		else if (world.getBlockMetadata(x, y, z) == 8) 
		{
			ret.add(new ItemStack(Blocks.foliage.get(), 1, 4));
		} 
		else 
		{
			ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		}

		return ret;
	}

	@Override
	public boolean isBlockFoliage(World world, int x, int y, int z)
	{
		return true;
	}
}
