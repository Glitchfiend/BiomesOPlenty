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
		super(Material.field_151585_k);
		
		//TODO: this.setHardness
		this.func_149711_c(0.0F);
		
		//TODO setStepSound(Block.soundGrassFootstep)
		this.func_149672_a(Block.field_149779_h);
		
		//TODO: setTickRandomly()
		this.func_149675_a(true);
		
		float var4 = 0.2F;
		
		//TODO: setBlockBounds
		this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
		
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
	public void func_149719_a(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);

		switch (meta)
		{
		case 0:
					//TODO: setBlockBounds
		this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;

		default:
					//TODO: setBlockBounds
		this.func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
			break;
		}
	}

	@Override
	//TODO:		getSubBlocks()
	public void func_149666_a(Item block, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) 
		{
			list.add(new ItemStack(block, 1, i));
		}
	}
	
	public boolean isValidPosition(World world, int x, int y, int z)
	{
		//TODO:					  getBlock()
		Block block = world.func_147439_a(x, y - 1, z);
		int metadata = world.getBlockMetadata(x, y, z);
		
		switch (metadata)
		{
		case 0: // Toadstool
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.netherrack || block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("overgrownNetherrack");

		case 1: // Portobello
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("holyGrass");

		case 2: // Blue Milk Cap
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("holyGrass");

		case 3: // Glowshroom
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == Blocks.stone || block == Blocks.netherrack || block == BOPBlockHelper.get("overgrownNetherrack");

		default:
			return block == Blocks.grass || block == Blocks.dirt || block == Blocks.mycelium || block == BOPBlockHelper.get("overgrownNetherrack");
		}
	}

	@Override
	//TODO:		   canPlaceBlockOnSide
	public boolean func_149707_d(World world, int x, int y, int z, int side)
	{
		return isValidPosition(world, x, y, z);
	} 

	@Override
	//TODO:		   canBlockStay()
	public boolean func_149718_j(World world, int x, int y, int z)
	{
		return isValidPosition(world, x, y, z);
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 15;
	}
}
