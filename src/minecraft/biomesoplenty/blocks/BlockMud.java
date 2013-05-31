package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class BlockMud extends Block
{
	private static final String[] types = new String[] {"mud", "quicksand"};

	private Icon[] textures;

	public BlockMud(int par1)
	{
		super(par1, Material.sand);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[types.length];

		for (int i = 0; i < types.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+types[i]);
		}
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 0)
			return par5 == 0 && minY > 0.0D ? true : (par5 == 1 && maxY < 1.0D ? true : (par5 == 2 && minZ > 0.0D ? true : (par5 == 3 && maxZ < 1.0D ? true : (par5 == 4 && minX > 0.0D ? true : (par5 == 5 && maxX < 1.0D ? true : !par1IBlockAccess.isBlockOpaqueCube(par2, par3, par4))))));
			else
				return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
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
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			entity.motionX *= 0.1D;
			entity.motionZ *= 0.1D;
		} else {
			entity.setInWeb();
		}
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3)
	{
		if (par1 == 0)
			return Items.mudball.get().itemID;
		else
			return blockID;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		if (meta == 0)
			return 4;
		else
			return 1;
	}
}