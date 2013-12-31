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
import biomesoplenty.client.render.blocks.RenderUtils;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPPlant extends BOPBlockWorldDecor implements IShearable
{
	private static final String[] plants = new String[] {"deadgrass", "desertgrass", "desertsprouts", "dunegrass", "holytallgrass", "thorn", "barley", "cattail", "rivercane", "cattailtop", "cattailbottom", "wildcarrot", "cactus", "witherwart", "reed", "root"};
	private IIcon[] textures;
	public IIcon reedbottom;

	private static final int CATTAILTOP = 9;
	private static final int CATTAILBOTTOM = 10;

	public BlockBOPPlant()
	{
		//TODO:	Material.plants
		super(Material.field_151585_k);
		
		//TODO: this.setHardness
		this.func_149711_c(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		float var3 = 0.4F;
		//TODO:		setBurnProperties() getIdFromBlock()
		Blocks.fire.func_149842_a(func_149682_b(this), 60, 100);
		//TODO: setBlockBounds
		this.func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
		
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
		
		reedbottom = iconRegister.registerIcon("biomesoplenty:" + "reedbottom");
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
		return RenderUtils.plantsModel;
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 6:
		case 7:
			//TODO: setBlockBounds
			this.func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 1.00F, 0.875F);
			break;

		default:
			//TODO: setBlockBounds
			this.func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
			break;
		}
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) 
		{
			if (i != CATTAILTOP && i!= CATTAILBOTTOM && i!= 11)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		//TODO:					  getBlock()
		Block root = world.func_147439_a(x, y + 1, z);
		
		switch (metadata)
		{
		case 0: // Dead Grass
		return block == BOPBlockHelper.get("driedDirt") || block == Blocks.sand;

		case 1: // Desert Grass
			return block == BOPBlockHelper.get("redRock");

		case 2: // Desert Sprouts
		case 3: // Dune Grass
			return block == Blocks.sand;

		case 4: // Holy Tall Grass
			return block == BOPBlockHelper.get("holyGrass");

		case 5: // Thorns
			return block == Blocks.grass|| block == Blocks.dirt || block == Blocks.soul_sand;
			
		case 6: // Barley
			return block == Blocks.grass || block == Blocks.dirt;

		case 7: // Cattail
			//TODO:										 getBlock()						getMaterial()				water							getBlock()						getMaterial()				water						getBlock()						getMaterial()				water							getBlock()						getMaterial()				water
			return block != Blocks.grass ? false : (world.func_147439_a(x - 1, y - 1, z).func_149688_o() == Material.field_151586_h ? true : (world.func_147439_a(x + 1, y - 1, z).func_149688_o() == Material.field_151586_h ? true : (world.func_147439_a(x, y - 1, z - 1).func_149688_o() == Material.field_151586_h ? true : world.func_147439_a(x, y - 1, z + 1).func_149688_o() == Material.field_151586_h)));

		case 8: // River Cane
			return block == this || block == Blocks.grass;

		case 10: // High Cattail Bottom
			//TODO:										 getBlock()						getMaterial()				water							getBlock()						getMaterial()				water						getBlock()						getMaterial()				water							getBlock()						getMaterial()				water
			return block != Blocks.grass ? false : (world.func_147439_a(x - 1, y - 1, z).func_149688_o() == Material.field_151586_h ? true : (world.func_147439_a(x + 1, y - 1, z).func_149688_o() == Material.field_151586_h ? true : (world.func_147439_a(x, y - 1, z - 1).func_149688_o() == Material.field_151586_h ? true : world.func_147439_a(x, y - 1, z + 1).func_149688_o() == Material.field_151586_h)));

		case 12: // Tiny Cactus
			return block == Blocks.sand || block == BOPBlockHelper.get("redRock") || block == Blocks.soul_sand;
			
		case 13: // Wither Wart
			return block == Blocks.soul_sand;
			
		case 14: // Reed
			return block == Blocks.water;
			
		case 15: // Reed
			return root == Blocks.grass || root == Blocks.dirt || root == Blocks.farmland || root == BOPBlockHelper.get("longGrass") || root == BOPBlockHelper.get("holyGrass") || root == BOPBlockHelper.get("holyDirt");
			
		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.farmland || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
	//TODO:			canReplace()
    public boolean func_149705_a(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int metadata = itemStack.getItemDamage();
		
        if (metadata == 5 || metadata == 13 || metadata == 15)
        	return this.isValidPosition(world, x, y, z, metadata);
        else
        	return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && this.isValidPosition(world, x, y, z, metadata);
	}

	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		super.func_149695_a(world, x, y, z, neighborBlock);

		//TODO:													   getBlock()
		if (world.getBlockMetadata(x, y, z) == CATTAILTOP && world.func_147439_a(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) != CATTAILBOTTOM) 
		{
			//TODO: setBlockToAir()
			world.func_147468_f(x, y, z);
		}
		//TODO:																getBlock()
		else if (world.getBlockMetadata(x, y, z) == CATTAILBOTTOM && world.func_147439_a(x, y + 1, z) != this) 
		{
			//TODO: setBlockToAir()
			world.func_147468_f(x, y, z);
		}
		//TODO:			getBlock()
		else if (world.func_147439_a(x, y, z) != this || world.getBlockMetadata(x, y, z) != 8) 
		{
			//TODO:				  getBlock()
			for (int i = 1; world.func_147439_a(x, y + i, z) == this; i++)
			{
				//TODO:	  canBlockStay()
				if (!this.func_149718_j(world, x, y + i, z))
				{
					//TODO: dropBlockAsItem
					this.func_149697_b(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
					//TODO: setBlockToAir()
					world.func_147468_f(x, y, z);
				}
			}
		}
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
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
		
		if (meta == 11)
		{
			return new ItemStack(BOPItemHelper.get("food"));
		}
		
        return new ItemStack(this, 1);
    }

	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
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
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int fortune)
	{
		if (metadata > 5 && metadata != 11)
		{
			//TODO:		getItemFromBlock()
			return Item.func_150898_a(this);
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
	//TODO     damageDropped()
	public int func_149692_a(int meta)
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
	//TODO:		harvestBlock()
	public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta)
	{
		super.func_149636_a(world, player, x, y, z, meta);
		
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
	//TODO: 	   isBlockReplaceable
	public boolean func_149742_c(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 5 || meta == 8) return false;
		
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 7 || world.getBlockMetadata(x, y, z) == 8 || world.getBlockMetadata(x, y, z) == 9)
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
