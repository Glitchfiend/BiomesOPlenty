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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;

public class BlockMud extends Block
{
	private static final String[] types = new String[] {"mud", "quicksand"};

	private IIcon[] textures;

	public BlockMud()
	{
		super(Material.field_151595_p);
		
		//TODO: this.setHardness
		this.func_149711_c(0.6F);
		
		//TODO: this.setStepSound(Block.soundSandFootstep);
		this.func_149672_a(Block.field_149776_m);
	
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[types.length];

		for (int i = 0; i < types.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+ types[i]);
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
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < types.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

	//@Override
	//TODO: getCollisionBoundingBoxFromPool
	public AxisAlignedBB func_149668_a(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			float var5 = 0.35F;
			return AxisAlignedBB.getAABBPool().getAABB(x, y, z, x + 1, y + 1 - var5, z + 1);
		}
		else
			return null;
	}

	@Override
	//TODO:		onEntityCollidedWithBlock()
	public void func_149670_a(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			if (entity instanceof EntityPlayer)
			{
				InventoryPlayer inventory = ((EntityPlayer)entity).inventory;

				/*TODO: FEATURE if (inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == BOPItems.wadingBoots.get().itemID)
				{
					return;
				}*/
			}

			entity.motionX *= 0.1D;
			entity.motionZ *= 0.1D;
		}
		else
		{
			entity.setInWeb();
		}
	}

	//@Override
	//TODO:	   getItemDropped()
	public Item func_149650_a(int metadata, Random random, int par3)
	{
		/*TODO: FEATURE if (metadata == 0)
			return BOPItems.mudball.get().itemID;
		else*/
			//TODO: getItemFromBlock()
			return Item.func_150898_a(this);
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta;
	}

	//@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return 4;
		else
			return 1;
	}
}