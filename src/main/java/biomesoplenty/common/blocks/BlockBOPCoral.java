package biomesoplenty.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BOPBlockWorldDecor
{
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral", "algae"};
	private IIcon[] textures;

	public BlockBOPCoral()
	{
		super(Material.water);
		
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		this.setTickRandomly(true);
		
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[coral.length];

		for (int i = 0; i < coral.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + coral[i]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < coral.length; ++i)
		{
			if (i > 2)
			{
				list.add(new ItemStack(block, 1, i));
			}
		}
	}

	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);

		switch (metadata)
		{
		case 1: // Kelp Middle
			return block == this;

		case 2: // Kelp Top
			return block == this;

		default:
			return block == Blocks.dirt || block == Blocks.sand|| block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay || block == Blocks.gravel || block == Blocks.grass || block == BOPBlockHelper.get("mud");
		}
	}
	
	@Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int silkTouch)
	{
		world.setBlock(x, y, z, Blocks.water);
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
		
		if (world.getBlockMetadata(x, y, z) == 0 && world.getBlock(x, y + 1, z) != this)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		if (world.getBlockMetadata(x, y, z) == 1 && world.getBlock(x, y + 1, z) != this)
		{
			if (world.getBlock(x, y - 1, z) == this)
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			}
		}
		
		if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 2)
		{
			for (int i = 1; world.getBlock(x, y + i, z) == this; i++)
			{
				if (!this.canBlockStay(world, x, y + i, z))
				{
					this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
					world.setBlock(x, y + i, z, Blocks.water, 0, 2);
				}
			}
		}
		
		if (world.getBlock(x, y, z) != this)
		{
			world.setBlock(x, y, z, Blocks.water, 0, 2);
		}
	}

	@Override
	public int damageDropped(int meta)
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
	public int getDamageValue(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta < 3) 
		{
			meta = 3;
		}
		
		return meta;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;
		
		return false;
	}
}
