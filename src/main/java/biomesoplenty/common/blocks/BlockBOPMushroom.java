package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBOPMushroom extends BlockFlower
{
	private static final String[] plants = new String[] {"toadstool", "portobello", "bluemilk", "glowshroom", "flatmushroom"};
	private IIcon[] textures;

	protected BlockBOPMushroom()
	{
		super(bmaterial);
		
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

	protected boolean canThisPlantGrowOnThisBlock(Block block, int metadata)
	{
		if (metadata == 0) //Toadstool
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID || id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;
		if (metadata == 1) //Portobello
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID | id == Blocks.holyGrass.get().blockID;
		if (metadata == 2) //Blue Milk Cap
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;
		if (metadata == 3) //Glowshroom
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Block.stone.blockID || id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;
		else
			return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();
		//boolean sky = world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z);

		if (itemStack.itemID == blockID)
		{
			switch (meta)
			{
			case 0: // Toadstool
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID || id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;

			case 1: // Portobello
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;

			case 2: // Blue Milk Cap
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.holyGrass.get().blockID;

			case 3: // Glowshroom
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Block.stone.blockID || id == Block.netherrack.blockID || id == Blocks.overgrownNetherrack.get().blockID;

			default:
				return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.overgrownNetherrack.get().blockID;
			}
		} else
			return this.canPlaceBlockOnSide(world, x, y, z, side);
	}

	@Override
	//TODO:		   canBlockStay()
	public boolean func_149718_j(World world, int x, int y, int z)
	{
		//TODO:			   								getBlock()
		return this.canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	//TODO     damageDropped()
	public int func_149692_a(int meta)
	{
		return meta & 15;
	}
}
