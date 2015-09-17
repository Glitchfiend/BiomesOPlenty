package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
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
import biomesoplenty.api.content.BOPCBlocks;

public class BlockBOPMushroom extends BlockBush
{
	private static final String[] plants = new String[] {"toadstool", "portobello", "bluemilk", "glowshroom", "flatmushroom", "shadowshroom"};
	private IIcon[] textures;

	public BlockBOPMushroom()
	{
		super(Material.plants);
		
		this.setHardness(0.0F);
		
		this.setStepSound(Block.soundTypeGrass);
		
		this.setTickRandomly(true);
		
		float var4 = 0.2F;
		
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
	
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i)
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
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
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == 3)
			return 6;
		else
			return 0;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		default:
			this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;
		}
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		Block block = world.getBlock(x, y - 1, z);
		
		switch (metadata)
		{
		case 0: // Toadstool
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.netherrack || block == BOPCBlocks.overgrownNetherrack;

		case 1: // Portobello
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium;

		case 2: // Blue Milk Cap
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium;

		case 3: // Glowshroom
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.stone || block == Blocks.netherrack || block == BOPCBlocks.overgrownNetherrack;

		case 5: // Shadow Shroom
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.end_stone || block == BOPCBlocks.bopGrass;
			
		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPCBlocks.overgrownNetherrack || block == BOPCBlocks.bopGrass;
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z, -1);
	} 

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isValidPosition(world, x, y, z, -1);
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}
}
