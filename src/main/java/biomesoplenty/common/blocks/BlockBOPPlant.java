package biomesoplenty.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.client.render.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPPlant extends BOPBlockWorldDecor implements IShearable
{
	private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "spectralfern", "thorn", "barley", "cattail", "rivercane", "cattailtop", "cattailbottom", "wildcarrot", "cactus", "witherwart", "reed", "root"};
	private IIcon[] textures;
	public IIcon reedbottom;

	private static final int CATTAILTOP = 9;
	private static final int CATTAILBOTTOM = 10;

	public BlockBOPPlant()
	{
		super(Material.plants);
		
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		float var3 = 0.4F;
		this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
		}
		
		reedbottom = iconRegister.registerIcon("biomesoplenty:" + "reedbottom");
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
		return RenderUtils.plantsModel;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		long i1 = x * 3129871 ^ z * 116129781L ^ y;
		i1 = i1 * i1 * 42317861L + i1 * 11L;
		float d0 = (float)(((i1 >> 16 & 15L) / 15.0F - 0.5D) * 0.5D);
		float d2 = (float)(((i1 >> 24 & 15L) / 15.0F - 0.5D) * 0.5D);

		switch (meta)
		{
		case 7:
			this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.00F, 0.875F);
			break;
			
		case 12:
			this.setBlockBounds(0.3F + d0, 0.0F, 0.3F + d2, 0.7F + d0, 0.4F, 0.7F + d2);
			break;

		default:
			this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) 
		{
			if (i != CATTAILTOP && i!= CATTAILBOTTOM && i!= 11)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);
		Block root = world.getBlock(x, y + 1, z);
		Block reedwater = world.getBlock(x, y - 2, z);
		
		switch (metadata)
		{
		case 0: // Dead Grass
		return block == BOPBlockHelper.get("driedDirt") || block == Blocks.sand;

		case 1: // Desert Grass
			return block == Blocks.hardened_clay;

		case 2: // Desert Sprouts
		case 3: // Dune Grass
			return block == Blocks.sand;

		case 4: // Spectral Fern
			return block == BOPBlockHelper.get("grass");

		case 5: // Thorns
			return block == Blocks.grass|| block == Blocks.dirt || block == Blocks.soul_sand;
			
		case 6: // Barley
			return block == Blocks.grass || block == Blocks.dirt;

		case 7: // Cattail
			return block != Blocks.grass ? false : (world.getBlock(x - 1, y - 1, z).getMaterial() == Material.water ? true : (world.getBlock(x + 1, y - 1, z).getMaterial() == Material.water ? true : (world.getBlock(x, y - 1, z - 1).getMaterial() == Material.water ? true : world.getBlock(x, y - 1, z + 1).getMaterial() == Material.water)));

		case 8: // River Cane
			return block == this || block == Blocks.grass;
			
		case 9:
			return block == this;

		case 10: // High Cattail Bottom
			return block != Blocks.grass ? false : (world.getBlock(x - 1, y - 1, z).getMaterial() == Material.water ? true : (world.getBlock(x + 1, y - 1, z).getMaterial() == Material.water ? true : (world.getBlock(x, y - 1, z - 1).getMaterial() == Material.water ? true : world.getBlock(x, y - 1, z + 1).getMaterial() == Material.water)));

		case 12: // Tiny Cactus
			return block == Blocks.sand || block == Blocks.hardened_clay || block == Blocks.soul_sand;
			
		case 13: // Wither Wart
			return block == Blocks.soul_sand;
			
		case 14: // Reed
			return block == Blocks.water && reedwater != Blocks.water;
			
		case 15: // Root
			return root != Blocks.air && (root == Blocks.grass || root == Blocks.dirt || root == Blocks.farmland || root == BOPBlockHelper.get("longGrass") || root == BOPBlockHelper.get("grass") || root == BOPBlockHelper.get("spectralSoil"));
			
		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int metadata = itemStack.getItemDamage();
		
        if (metadata == 5 || metadata == 13 || metadata == 15)
        	return this.isValidPosition(world, x, y, z, metadata);
        else
        	return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.isValidPosition(world, x, y, z, metadata);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);

		if (world.getBlockMetadata(x, y, z) == CATTAILTOP && world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) != CATTAILBOTTOM) 
		{
			world.setBlockToAir(x, y, z);
		}
		else if (world.getBlockMetadata(x, y, z) == CATTAILBOTTOM && world.getBlock(x, y + 1, z) != this) 
		{
			world.setBlockToAir(x, y, z);
		}
		else if (world.getBlockMetadata(x, y, z) == 8) 
		{
			if (!this.canReplace(world, x, y, z, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 8)))
			{
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 5)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings)))
				{
					entity.attackEntityFrom(DamageSource.cactus, 1);
				}
			}
			else
			{
				entity.attackEntityFrom(DamageSource.cactus, 1);
			}
		}
		if (meta == 12)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				if (!((inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == Items.leather_boots) && (inventory.armorInventory[1] != null && inventory.armorInventory[1].getItem() == Items.leather_leggings)))
				{
					entity.attackEntityFrom(DamageSource.cactus, 1);
				}
			}
			else
			{
				entity.attackEntityFrom(DamageSource.cactus, 1);
			}
		}
	}
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 9:
			return new ItemStack(this, 1, 7);

		case 10:
			return new ItemStack(this, 1, 7);

		case 11:
			return new ItemStack(BOPItemHelper.get("food"), 1, 2);
		}

		return new ItemStack(this, 1, meta);
    }

	@Override
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == CATTAILTOP || meta == CATTAILBOTTOM) 
		{
			meta = 7;
		}
		else if (meta == 11) 
		{
			meta = 2;
		}
		
		return meta;
	}

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune)
	{
		if (metadata > 5 && metadata != 11)
		{
			return Item.getItemFromBlock(this);
		}
		else if (metadata == 11)
		{
			return BOPItemHelper.get("food");
		}
		else
		{
			return null;
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		if (meta == 9)
		{
			return 7;
		}
		else if (meta == 11)
		{
			return 2;
		}
		else
		{
			return meta;
		}
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 6)
			return random.nextInt(5) == 0 ? 1 : 0;
		else if (meta == 7 || meta == 8 || meta == 9)
			return 1;
		else if (meta == 11)
			return random.nextInt(7) == 0 ? 2 : 1;
		else if (meta == 13)
			return 1;
		else
			return 0;
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.harvestBlock(world, player, x, y, z, meta);
		
		if (meta == 13)
		{
			player.addPotionEffect(new PotionEffect(Potion.wither.id, 250, 1));
		}
		
		ItemStack equippedItem = player.getCurrentEquippedItem();
		
		if (equippedItem != null)
		{
			if (equippedItem.getItem() != Items.shears)
			{
				if (meta == 5)
				{
					player.attackEntityFrom(DamageSource.cactus, 2);
				}
			}
		}
		else
		{
			if (meta == 5)
			{
				player.attackEntityFrom(DamageSource.cactus, 2);
			}
		}
	}
	
    @Override
	@SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
		if (meta == 13)
		{
            byte b0 = 3;

            for (int j1 = 0; j1 < b0; ++j1)
            {
                for (int k1 = 0; k1 < b0; ++k1)
                {
                    for (int l1 = 0; l1 < b0; ++l1)
                    {
                        double d0 = (double)x + ((double)j1 + 0.5D) / (double)b0;
                        double d1 = (double)y + ((double)k1 + 0.5D) / (double)b0;
                        double d2 = (double)z + ((double)l1 + 0.5D) / (double)b0;
                        world.spawnParticle("smoke", d0, d1, d2, d0 - (double)x - 0.5D, d1 - (double)y - 0.5D, d2 - (double)z - 0.5D);
                    }
                }
            }
		}
		
		return false;
    }

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 5 || meta == 8) return false;
		
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
	    int metadata = world.getBlockMetadata(x, y, z);
	    
		if (metadata == 7 || metadata == 8 || metadata == 9 || metadata == 11)
		    return false;
		else
			return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune)
	{
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}
}
