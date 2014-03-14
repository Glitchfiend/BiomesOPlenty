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
import net.minecraftforge.common.util.ForgeDirection;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.common.blocks.templates.BOPBlockWorldDecor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPCoral extends BOPBlockWorldDecor
{
	private static final String[] coral = new String[] {"kelpbottom", "kelpmiddle", "kelptop", "kelpsingle", "pinkcoral", "orangecoral", "bluecoral", "glowcoral"};
	private IIcon[] textures;

	public BlockBOPCoral()
	{
		//TODO: Material.water
		super(Material.water);
		
		//TODO: this.setHardness
		this.setHardness(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		
		float f = 0.4F;
		//TODO: setBlockBounds
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[coral.length];

		for (int i = 0; i < coral.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + coral[i]);
		}
	}

	@Override
	//TODO:		 getIcon()
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
	//TODO		getRenderType()
	public int getRenderType()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	//TODO:		getSubBlocks()
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
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);

		switch (metadata)
		{
		case 1: // Kelp Middle
			return block == this;

		case 2: // Kelp Top
			return block == this;

		default:
			return block == Blocks.dirt || block == Blocks.sand|| block == Blocks.sponge || block == Blocks.stone || block == Blocks.clay;
		}
	}
	
	@Override
	//TODO:		onNeighborBlockChange()
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock)
	{
		//TODO: onNeighborBlockChange()
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
		
		//TODO:					  							getBlock()
		if (world.getBlockMetadata(x, y, z) == 0 && world.getBlock(x, y + 1, z) != this)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		
		//TODO:					  							getBlock()
		if (world.getBlockMetadata(x, y, z) == 1 && world.getBlock(x, y + 1, z) != this)
		{
			//TODO:		getBlock()
			if (world.getBlock(x, y - 1, z) == this)
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			}
		}
		
		if (world.getBlockMetadata(x, y, z) == 0 || world.getBlockMetadata(x, y, z) == 1 || world.getBlockMetadata(x, y, z) == 2)
		{
			//TODO:				  getBlock()
			for (int i = 1; world.getBlock(x, y + i, z) == this; i++)
			{
				//TODO:	  canBlockStay()
				if (!this.canBlockStay(world, x, y + i, z))
				{
					//TODO: dropBlockAsItem()
					this.dropBlockAsItem(world, x, y + i, z, world.getBlockMetadata(x, y + i, z), 0);
					//TODO: setBlock()
					world.setBlock(x, y + i, z, Blocks.water, 0, 2);
				}
			}
		}
		
		//TODO:		getBlock()
		if (world.getBlock(x, y, z) != this)
		{
			//TODO: setBlock()
			world.setBlock(x, y, z, Blocks.water, 0, 2);
		}
	}

	@Override
	//TODO     damageDropped()
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
	//TODO:	   getDamageValue()
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
	//TODO: 	   isBlockReplaceable
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 10) return true;
		
		return false;
	}
}
