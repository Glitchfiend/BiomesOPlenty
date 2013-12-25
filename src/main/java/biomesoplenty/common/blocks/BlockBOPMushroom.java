package biomesoplenty.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
		
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.4F, 0.7F);
		
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
	public void setBlockBoundsBasedOnState(IBlockAccess world, int par2, int par3, int par4)
	{
		int meta = world.getBlockMetadata(par2, par3, par4);

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
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) 
	{
		for (int i = 0; i < plants.length; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id)
	{
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.mycelium.blockID || id == Blocks.overgrownNetherrack.get().blockID;
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id, int metadata)
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
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return this.canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z), world.getBlockMetadata(x, y, z));
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & 15;
	}
}
