package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.FakePlayer;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.api.content.BOPCItems;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFoliage extends BOPBlockWorldDecor implements IShearable
{
	private static final String[] foliageTypes = new String[] {"duckweed", "shortgrass", "mediumgrass", "flaxbottom", "bush", "sprout", "flaxtop", "poisonivy", "berrybush", "shrub", "wheatgrass", "dampgrass", "koru", "cloverpatch", "leafpile", "deadleafpile"};

	private IIcon[] textures;
	public IIcon flaxFlowers;
	public IIcon shrubBranch;
	public IIcon berryBushBerry;

    private static final int FLAXTOP = 6;
    private static final int FLAXBOTTOM = 3;

	public BlockBOPFoliage()
	{
		super(Material.plants);
		
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);

		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[foliageTypes.length];

		for (int i = 0; i < textures.length; ++i)
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+foliageTypes[i]);
		}
		
		flaxFlowers = iconRegister.registerIcon("biomesoplenty:" + "flax_flowers");
		shrubBranch = iconRegister.registerIcon("biomesoplenty:" + "shrub_branch");
		berryBushBerry = iconRegister.registerIcon("biomesoplenty:" + "berrybush_berry");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < foliageTypes.length; ++i)
		{
			if (i != FLAXTOP) 
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
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

			if (world.rand.nextInt(2) == 0)
			{
				ret.add(new ItemStack(Items.carrot, 1));
			} else {
				ret.add(new ItemStack(Items.potato, 1));
			}
			break;
			
		case 12:
			if (world.rand.nextInt(32) != 0)
				return ret;

			if (world.rand.nextInt(2) == 0) 
			{
				if(BOPConfigurationMisc.dropTurnipSeeds)
					ret.add(new ItemStack(BOPCItems.turnipSeeds, 1));
			}
			break;
			
		case 8:
		    ret.add(new ItemStack(BOPCItems.food, 1, 0));
		    break;
		}

		return ret;
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);
		
    	if (block == Blocks.air && world.provider.dimensionId != -1 ? (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) : false) return false;
		
		switch (metadata)
		{
		case FLAXTOP:
			return block == this;

		case 0: //Duckweed
			return block == Blocks.water;
			
		case 14: // Leaf Pile
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack;
			
		case 15: // Dead Leaf Pile
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack;

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPCBlocks.longGrass || block == BOPCBlocks.overgrownNetherrack;
		}
	}
	
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		super.randomDisplayTick(world, x, y, z, random);

		int meta = world.getBlockMetadata(x, y, z);
		int i = 5149489;
		double d0 = (double)(i >> 16 & 255) / 255.0D;
        double d1 = (double)(i >> 8 & 255) / 255.0D;
        double d2 = (double)(i >> 0 & 255) / 255.0D;

		if (meta == 7)
		{
			if (random.nextInt(32) == 0)
			{
				
				world.spawnParticle("mobSpell", x + random.nextFloat(), y + random.nextFloat(), z + random.nextFloat(), d0, d1, d2);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
		
	    int metadata = world.getBlockMetadata(x, y, z);
	    
	    if (world.getBlockMetadata(x, y, z) == FLAXBOTTOM) 
	    {
	        if (world.getBlock(x, y + 1, z) != this)
	        {
	        	this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
	        	world.setBlockToAir(x, y, z);
	        }
	        else if (!this.isValidPosition(world, x, y, z, metadata))
	        {
	            this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y + 1, z), 0);
	            world.setBlockToAir(x, y + 1, z);
	        }
	    } 
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
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
		return ColorizerGrass.getGrassColor(0.5D, 1.0D);
	}

	@Override
	public int getRenderType()
	{
		return RenderUtils.foliageModel;
	}

	@Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 9 || world.getBlockMetadata(x, y, z) == 14)
		{
			return world.getBiomeGenForCoords(x, z).getBiomeFoliageColor(x, y, z);
		}
		
		if (world.getBlockMetadata(x, y, z) == 15)
		{
			return 16777215;
		}
		
		return world.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z);
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == FLAXTOP) 
		{
			meta = FLAXBOTTOM;
		}
		return meta;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return null;
	}
	
    @Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0: //Duckweed
			this.setBlockBounds(0F, 0F, 0F, 1F, 0.015625F, 1F);
			break;
			
		case 1: //Short Grass
			this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.25F, 0.9F);
			break;
			
		case 2: //Medium Grass
			this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.6F, 0.9F);
			break;
			
		case 13: //Clover Patch
			this.setBlockBounds(0F, 0F, 0F, 1F, 0.015625F, 1F);
			break;
			
		case 14: //Leaf Pile
		case 15: //Dead Leaf Pile
			this.setBlockBounds(0F, 0F, 0F, 1F, 0.015625F, 1F);
			break;
			
		default:
			this.setBlockBounds(0.1F, 0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 8)
		{
			world.setBlock(x, y, z, this, 4, 3);
			
			EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(BOPCItems.food, 1, 0));
			
			if (!world.isRemote)
			{
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
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		if (world.getBlockMetadata(x, y, z) == FLAXTOP) 
		{
			ret.add(new ItemStack(BOPCBlocks.foliage, 1, 3));
		} 
		else if (world.getBlockMetadata(x, y, z) == 8) 
		{
			ret.add(new ItemStack(BOPCBlocks.foliage, 1, 4));
		} 
		else 
		{
			ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		}

		return ret;
	}
}
