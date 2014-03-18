package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
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
import net.minecraft.potion.PotionHelper;
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
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.client.render.RenderUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPFoliage extends BlockTallGrass implements IShearable
{
	private static final String[] foliageTypes = new String[] {"algae", "shortgrass", "mediumgrass", "hedgebottom", "bush", "sprout", "hedgetop", "poisonivy", "berrybush", "shrub", "wheatgrass", "dampgrass", "koru", "cloverpatch", "leafpile", "deadleafpile"};

	private IIcon[] textures;
	public IIcon hedgeTrunk;
	public IIcon shrubBranch;
	public IIcon berryBushBerry;

    private static final int HEDGETOP = 6;
    private static final int ALGAE = 0;
    private static final int HEDGEBOTTOM = 3;

	public BlockBOPFoliage()
	{
		float f = 0.4F;
		
		//TODO: this.setHardness
		this.setHardness(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);
		
		//TODO: setBlockBounds
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);

		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[foliageTypes.length];

		for (int i = 0; i < textures.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+foliageTypes[i]);
		}
		
		hedgeTrunk = iconRegister.registerIcon("biomesoplenty:" + "hedge_trunk");
		shrubBranch = iconRegister.registerIcon("biomesoplenty:" + "shrub_branch");
		berryBushBerry = iconRegister.registerIcon("biomesoplenty:" + "berrybush_berry");
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < foliageTypes.length; ++i)
		{
			if (i != HEDGETOP) 
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
				ret.add(new ItemStack(BOPItemHelper.get("turnipSeeds"), 1));
			}
			break;
			
		case 8:
		    ret.add(new ItemStack(BOPItemHelper.get("food"), 1, 0));
		    break;
		}

		return ret;
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);
		
    	if (block == Blocks.air) return false;
		
		switch (metadata)
		{
		case HEDGETOP:
			return block == this;

		case ALGAE: // Algae
			return block == Blocks.water;
			
		case 14: // Leaf Pile
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == Blocks.stone || block == Blocks.sand || block == Blocks.gravel || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");
			
		case 15: // Dead Leaf Pile
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == Blocks.stone || block == Blocks.sand || block == Blocks.gravel || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("longGrass") || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
	//TODO:			canReplace()
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
    	//TODO:	  getBlock()
    	if (world.getBlock(x, y - 1, z) == Blocks.air) return false;
		
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.isValidPosition(world, x, y, z, itemStack.getItemDamage());
	}
	
	@Override
	//TODO: 	randomDisplayTick()
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
	{
		//TODO: randomDisplayTick()
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
	//TODO:		updateTick()
	public void updateTick(World world, int x, int y, int z, Random random)
    {
    	//TODO:				getBlock()
    	Block block = world.getBlock(x, y, z);
        
        this.dropIfCantStay(world, x, y, z, new ItemStack(block, 1, world.getBlockMetadata(x, y, z)));
    }
	
    public void dropIfCantStay(World world, int x, int y, int z, ItemStack stack)
    {
    	//TODO:	  canReplace
        if (!this.canReplace(world, x, y, z, 0, stack))
        {
        	//TODO:	dropBlockAsItem()
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            //TODO:	setBlockToAir()
            world.setBlockToAir(x, y, z);
        }
    }

	@Override
	//TODO:		onNeighborBlockChange()
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		//TODO:												getBlock()
		dropIfCantStay(world, x, y, z, new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z)));
		
	    int metadata = world.getBlockMetadata(x, y, z);
	    
	    if (world.getBlockMetadata(x, y, z) == HEDGEBOTTOM) 
	    {
	    	//TODO:	 getBlock()
	        if (world.getBlock(x, y + 1, z) != this)
	        {
	        	//TODO: setBlock()
	            world.setBlock(x, y, z, Blocks.tallgrass, 1, 2);
	        }
	        //TODO:											getBlock()
	        else if (!this.isValidPosition(world, x, y, z, metadata))
	        {
	        	//TODO: dropBlockAsItem()
	            this.dropBlockAsItem(world, x, y + 1, z, world.getBlockMetadata(x, y + 1, z), 0);
	            //TODO:	setBlockToAir()
	            world.setBlockToAir(x, y + 1, z);
	        }
	    }

		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
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
    //TODO:	   getBlockColor()
    public int getBlockColor()
    {
		double var1 = 0.5D;
		double var3 = 1.0D;
		
		return ColorizerGrass.getGrassColor(var1, var3);
	}

	@Override
	@SideOnly(Side.CLIENT)
    //TODO:	   getRenderColor()
    public int getRenderColor(int par1)
    {
		return ColorizerGrass.getGrassColor(0.5D, 1.0D);
	}

	@Override
	//TODO		getRenderType()
	public int getRenderType()
	{
		return RenderUtils.foliageModel;
	}

	@Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 3 || world.getBlockMetadata(x, y, z) == 6 || world.getBlockMetadata(x, y, z) == 9 || world.getBlockMetadata(x, y, z) == 14)
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
	//TODO:	   getDamageValue()
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == HEDGETOP) {
			meta = HEDGEBOTTOM;
		}
		return meta;
	}

	@Override
	//TODO:	   getItemDropped()
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		return null;
	}

	@Override
	//TODO:				 getSelectedBoundingBoxFromPool()
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
			
		case 14: //Leaf Pile
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.015625D, z + 1.0D);
			
		case 15: //Dead Leaf Pile
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.015625D, z + 1.0D);

		default:
			return AxisAlignedBB.getBoundingBox(x + 0.1D, y, z + 0.1D, x + 0.9D, y + 0.8D, z + 0.9D);
		}
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
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

		//TODO: setBlockBounds()
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	//TODO:			onBlockActivated
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitVecX, float hitVecY, float hitVecZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 8)
		{
			//TODO:	setBlock()
			world.setBlock(x, y, z, this, 4, 3);
			
			EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(BOPItemHelper.get("food"), 1, 0));
			
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

		if (world.getBlockMetadata(x, y, z) == HEDGETOP) 
		{
			ret.add(new ItemStack(Blocks.tallgrass, 1, 1));
		} 
		else if (world.getBlockMetadata(x, y, z) == 8) 
		{
			ret.add(new ItemStack(BOPBlockHelper.get("foliage"), 1, 4));
		} 
		else 
		{
			ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		}

		return ret;
	}
}
