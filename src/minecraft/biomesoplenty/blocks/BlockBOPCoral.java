package biomesoplenty.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BlockFlower
{
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral"};
	private Icon[] textures;

	public BlockBOPCoral(int blockID)
	{
		super(blockID, Material.water);
		this.setTickRandomly(true);
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[coral.length];

		for (int i = 0; i < coral.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + coral[i]);
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
	public int getRenderType()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < coral.length; ++i)
		{
			if (i > 2)
			{
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.dirt.blockID || id == Block.sand.blockID || id == blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
	{
		if (metadata == 1)
			return id == blockID;
		if (metadata == 2)
			return id == blockID;
		else
			return id == Block.dirt.blockID || id == Block.sand.blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == blockID) {
			switch (meta)
			{
			case 1: // Kelp Middle
				return id == blockID;

			case 2: // Kelp Top
				return id == blockID;

			default:
				return id == Block.dirt.blockID || id == Block.sand.blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		
		if (world.getBlockMetadata(x, y, z) == 0 && world.getBlockId(x, y + 1, z) != blockID)
		{
			world.setBlock(x, y, z, blockID, 3, 2);
		}
		
		if (world.getBlockMetadata(x, y, z) == 1 && world.getBlockId(x, y + 1, z) != blockID)
		{
			if (world.getBlockId(x, y - 1, z) == blockID)
			{
				world.setBlock(x, y, z, blockID, 2, 2);
			}
		}
		
		if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 2)
		{
			this.checkBlockCoordValid(world, x, y, z);
		}
	}
	
	protected final void checkBlockCoordValid(World world, int x, int y, int z)
	{
		for (int i = 1; world.getBlockId(x, y + i, z) == blockID; i++)
		{
			if (!this.canBlockStay(world, x, y + i, z))
			{
				this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
				world.setBlock(x, y + i, z, Block.waterStill.blockID, 0, 2 + 1);
			}
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
		if (meta < 3) {
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
		//ItemStack itemstack = new ItemStack(Blocks.flowers.get(), 1, 10);

		if (world.getBlockMetadata(x, y, z) == 10)
			//if (!world.isRemote)
			//world.spawnEntityInWorld(new EntityItem(world, x, y, z, itemstack));
			return true;
		return false;
	}
}
