package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BlockBush
{
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral"};
	private IIcon[] textures;

	public BlockBOPCoral()
	{
		super(Material.water);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		float f = 0.4F;
		//TODO: setBlockBounds
		this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		//TODO: this.setCreativeTab()
		this.func_149647_a(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void func_149651_a(IIconRegister iconRegister)
	{
		textures = new IIcon[coral.length];

		for (int i = 0; i < coral.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + coral[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon func_149691_a(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO		getRenderType()
	public int func_149645_b()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < coral.length; ++i)
		{
			if (i > 2)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.dirt.blockID || id == Block.sand.blockID || id == Block.sponge.blockID || id == Block.stone.blockID || id == Block.blockClay.blockID || id == blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 1)
			return id == blockID;
		if (metadata == 2)
			return id == blockID;
		else
			return id == Block.dirt.blockID || id == Block.sand.blockID || id == Block.sponge.blockID || id == Block.stone.blockID || id == Block.blockClay.blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int block = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 1: // Kelp Middle
				return block == blockID;

			case 2: // Kelp Top
				return block == blockID;

			default:
				return block == Block.dirt.blockID || block == Block.sand.blockID || block == Blocks.sponge || block == Block.stone || block == Block.blockClay.blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}
	
	@Override
	//TODO:		onNeighborBlockChange()
	public void func_149695_a(World world, int x, int y, int z, Block neighborBlock)
	{
		//TODO: onNeighborBlockChange()
		super.func_149695_a(world, x, y, z, neighborBlock);
		
		if (world.getBlockMetadata(x, y, z) == 0 && world.getBlockId(x, y + 1, z) != blockID)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if (world.getBlockMetadata(x, y, z) == 1 && world.getBlockId(x, y + 1, z) != blockID)
		{
			if (world.getBlockId(x, y - 1, z) == blockID)
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			}
		}
		
		if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 2)
		{
			this.checkBlockCoordValid(world, x, y, z);
		}
		
		if (world.getBlockId(x, y, z) != this.blockID)
		{
			world.setBlock(x, y, z, Block.waterMoving.blockID, 0, 2);
		}
	}
	
	protected final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		for (int i = 1; world.getBlockId(x, y + i, z) == blockID; i++)
		{
			if (!this.canBlockStay(world, x, y + i, z))
			{
				this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
				world.setBlock(x, y + i, z, Block.waterMoving.blockID, 0, 2);
			}
		}
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		if (meta < 3)
		{
			return 3;
		}
		
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 7)
			return 10;
		else
			return 0;
	}
	
	@Override
	//TODO:	   getDamageValue()
	public int func_149643_k(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta < 3) 
		{
			meta = 3;
		}
		
		return meta;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		if (world.getBlockId(x, y, z) != blockID)
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
		else
			return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;
		
		return false;
	}
}
