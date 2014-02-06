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
import biomesoplenty.api.BOPBlockHelper;

public class BlockBOPMushroom extends BlockBush
{
	private static final String[] plants = new String[] {"toadstool", "portobello", "bluemilk", "glowshroom", "flatmushroom"};
	private IIcon[] textures;

	public BlockBOPMushroom()
	{
		//TODO:	Material.plants
		super(Material.plants);
		
		//TODO: this.setHardness
		this.setHardness(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.setStepSound(Block.soundTypeGrass);
		
		//TODO: setTickRandomly()
		this.setTickRandomly(true);
		
		float var4 = 0.2F;
		
		//TODO: setBlockBounds
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
		
		//TODO: this.setCreativeTab()
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	//TODO:		registerIcons()
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[plants.length];

		for (int i = 0; i < plants.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + plants[i]);
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
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 3)
			return 6;
		else
			return 0;
	}

	@Override
	//TODO:     setBlockBoundsBasedOnState()
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
					//TODO: setBlockBounds
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		default:
					//TODO: setBlockBounds
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;
		}
	}

	@Override
	//TODO:		getSubBlocks()
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	public boolean isValidPosition(World world, int x, int y, int z, int metadata)
	{
		//TODO:					  getBlock()
		Block block = world.getBlock(x, y - 1, z);
		
		switch (metadata)
		{
		case 0: // Toadstool
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.netherrack || block == BOPBlockHelper.get("grass") || block == BOPBlockHelper.get("overgrownNetherrack");

		case 1: // Portobello
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("grass");

		case 2: // Blue Milk Cap
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("grass");

		case 3: // Glowshroom
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.stone || block == Blocks.netherrack || block == BOPBlockHelper.get("overgrownNetherrack");

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z, -1);
	} 

	@Override
	//TODO:		   canBlockStay()
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isValidPosition(world, x, y, z, -1);
	}

	@Override
	//TODO     damageDropped()
	public int damageDropped(int meta)
	{
		return meta & 15;
	}
}
